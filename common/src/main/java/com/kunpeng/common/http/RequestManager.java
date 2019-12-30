package com.kunpeng.common.http;

import androidx.annotation.NonNull;

import com.kunpeng.common.config.Constants;
import com.kunpeng.common.http.data.HttpResult;
import com.kunpeng.common.http.data.RequestModel;
import com.kunpeng.common.http.interceptor.LoggerInterceptor;
import com.kunpeng.common.utils.FileHelper;
import com.kunpeng.common.utils.LogHelper;
import com.kunpeng.common.utils.ToolsHelper;
import com.kunpeng.common.utils.json.GsonImplHelp;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RequestManager {
    private final String TAG = RequestManager.class.getSimpleName();
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");

    //base地址(环境地址)
    private String BASE_URL = Constants.getSystemConfig().getServerHost();
    //正在请假的事件，用来取消请求或者去重
    private static HashMap<String, Call> httpHandlerList = new HashMap<>();

    private OkHttpClient mOkHttpClient;//okHttpClient 实例


    public static RequestManager getInstance() {
        return RequestHolder.instance;
    }

    /**
     * 获取httpClient实例
     *
     * @return OkHttpClient
     */
    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 取消所有正在进行的请求
     */
    public void cancelAll() {
        for (Call call : httpHandlerList.values()) {
            call.cancel();
        }
        httpHandlerList.clear();
    }

    public void cancel(String apiNo) {
        if (ToolsHelper.isNull(apiNo)
                || !httpHandlerList.containsKey(apiNo))
            return;

        Call call = httpHandlerList.get(apiNo);
        if (null != call)
            call.cancel();
        httpHandlerList.remove(apiNo);
    }

    /**
     * get请求
     *
     * @param actionUrl 请求地址
     * @param apiNo     key
     */
    public void get(String actionUrl, String apiNo) {
        get(actionUrl, apiNo, null);
    }

    /**
     * get请求
     *
     * @param actionUrl 请求地址
     * @param apiNo     key
     * @param extras    说明参数，请求完成时可以带到事件处理的地方
     */
    public void get(@NonNull String actionUrl, @NonNull String apiNo, Map<String, String> extras) {
        enqueue(new Request.Builder().url(getRequestUrl(actionUrl)).build(), apiNo, extras);
    }


    public void post(@NonNull String actionUrl, @NonNull String apiNo, String jsonParams, Map<String, String> extras) {
        enqueue(new Request.Builder().url(getRequestUrl(actionUrl)).post(createBody(jsonParams)).build(), apiNo, extras);
    }


    public void put(@NonNull String actionUrl, @NonNull String apiNo, String jsonParams, Map<String, String> extras) {
        enqueue(new Request.Builder().url(getRequestUrl(actionUrl)).put(createBody(jsonParams)).build(), apiNo, extras);
    }

    public void delete(@NonNull String actionUrl, @NonNull String apiNo, String jsonParams, Map<String, String> extras) {
        enqueue(new Request.Builder().url(getRequestUrl(actionUrl)).delete(createBody(jsonParams)).build(), apiNo, extras);
    }


    private void enqueue(Request request, @NonNull final String apiNo, final Map<String, String> extras) {
        //执行一下取消，避免接口重复执行
        //执行取消而不是继续执行，保证接口存在状态的情况下，一直是最后一个状态的请求生效
        cancel(apiNo);

        LogHelper.d(String.format("请求接口=====>Sending(%s) %s [headers=%s]",
                request.method(), request.url(), request.headers()));

        Call call = mOkHttpClient.newCall(request);
        httpHandlerList.put(apiNo, call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                failureHandler(call, apiNo, extras, e);
            }


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                successHandler(apiNo, extras, response);
            }
        });
    }

    private void successHandler(String apiNo, Map<String, String> extras, Response response) {
        HttpResult httpResult = new HttpResult(apiNo, extras);

        String body = "";
        try {
            ResponseBody responseBody = response.body();
            body = null == responseBody ? "" : responseBody.string();
        } catch (IOException e) {
            httpResult.setError(true);
            httpResult.setResult("服务异常");
        }
        if (200 == response.code() && !ToolsHelper.isNull(body)) {
            LogHelper.d(String.format("请求接口=====>Received(%s) %s\n%s",
                    response.request().method(), response.request().url(), body));
            RequestModel model = GsonImplHelp.get().toObject(body, RequestModel.class);
            if (null == model || ToolsHelper.isNull(model.getStatus()) || "success".equals(model.getStatus())) {
                httpResult.setError(false);
                httpResult.setResult(body);
            } else {
                httpResult.setError(true);
                if (null == model.getErrors()) {
                    httpResult.setResult("服务异常");
                } else {
                    String err = "";
                    for (RequestModel.ErrorsBean bean : model.getErrors()) {
                        if (ToolsHelper.isNull(err))
                            err += bean.getErrmsg();
                        else err = String.format("%s;%s", err, bean.getErrmsg());
                    }
                    httpResult.setResult(err);
                }
            }
        } else {
            LogHelper.e(String.format("请求接口=====>Received(%s) %s\n%s",
                    response.request().method(), response.request().url(), body));
            httpResult.setError(true);
            if (!ToolsHelper.isNull(body)) {
                httpResult.setResult(body);
            } else {
                httpResult.setResult("服务异常");
            }

        }
        EventBus.getDefault().post(httpResult);

    }

    private void failureHandler(Call call, String apiNo, Map<String, String> extras, IOException e) {
        HttpResult httpResult = new HttpResult(apiNo, extras);


        if (e instanceof SocketTimeoutException) {
            httpResult.setResult("访问超时");
        } else {
            httpResult.setResult(e.getMessage());
        }

        httpResult.setError(true);
        LogHelper.e(String.format("请求接口=====>Received(%s) %s\n%s",
                call.request().method(), call.request().url(), e.getMessage()));
        EventBus.getDefault().post(httpResult);

    }

    @NonNull
    private RequestBody createBody(String jsonParams) {
        LogHelper.e("=====>" + jsonParams);
        return RequestBody.create(MEDIA_TYPE_JSON, ToolsHelper.toString(jsonParams));
    }

    private String getRequestUrl(String actionUrl) {
        if (actionUrl.startsWith("http"))
            return actionUrl;
        if (actionUrl.startsWith("/"))
            throw new IllegalArgumentException("接口字段不能以/开头");
        if (BASE_URL.endsWith("/"))
            throw new IllegalArgumentException("环境地址(ServerHost)不能以/结尾");
        return String.format("%s/%s", BASE_URL, actionUrl);
    }

    private static class RequestHolder {
        private static RequestManager instance = new RequestManager();
    }


    private RequestManager() {
        File file = new File(FileHelper.getRootPath(), "cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(20, TimeUnit.SECONDS)//设置写入超时时间
                .cache(new Cache(file, FileHelper.calculateDiskCacheSize(file)))
                .addInterceptor(new LoggerInterceptor())
                .sslSocketFactory(RxUtils.createSSLSocketFactory())
                .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
                .build();
    }

}
