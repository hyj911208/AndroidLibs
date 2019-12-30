package com.kunpeng.common.http.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggerInterceptor implements Interceptor {
    private final String TAG = "接口请求";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
//        LogHelper.d(String.format("请求接口=====>Sending(%s) %s [headers=%s]",
//                request.method(), request.url(), request.headers()));

//        Response response = chain.proceed(request);
//        LogHelper.d(String.format("请求接口=====>Received(%s) %s\n%s",
//                request.method(), response.request().url(), response.body() == null ? "" : response.body().string()));


        return chain.proceed(request);
    }
}
