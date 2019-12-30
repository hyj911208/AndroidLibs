package com.kunpeng.userchat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.city.CityBean;
import com.kunpeng.userchat.model.city.DistrictBean;
import com.kunpeng.userchat.model.city.ProvinceBean;
import com.kunpeng.userchat.util.DateUtil;
import com.kunpeng.userchat.util.JsonParseControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by huangminzheng on 2018/1/15 下午8:42.
 * Email:ahtchmz@gmail.com
 */
public class CustomInfoEditTextView extends LinearLayout implements View.OnClickListener {
    //数字类型
    public static final int TYPE_CLASS_NUMBER = 1;
    //可以带小数点的浮点格式
    public static final int TYPE_NUMBER_FLAG_DECIMAL = 3;
    //身份证类型
    public static final int TYPE_ID = 4;
    public static final int MODE_BIRTHDATE = 1;
    public static final int MODE_WEIGHT = 2;
    public static final int MODE_HEIGHT = 3;
    public static final int MODE_CITY = 4;
    public static final int MODE_CONSTELLATION = 5;
    public static final int MODE_LABEL = 6;
    public static final int MODE_SEX = 7;
    public static final int MODE_OLD = 8;
    private TextView tv_prompt;//编辑时提示textview
    private EditText et_info;
    private TextView tv_content;//不可编辑时候textview
    private ImageView iv_mandatory;//是否必填小图标
    private ImageView iv_arrow;
    private TextView tv_unit;
    private View line;
    //sUnit 单位
    private String sPrompt, sContent, sHint, sUnit;
    private boolean bShowArrow, bEditable;
    private int iMandatoryVisibility, iMode = 0;
    private int iPromptTextColor, iEditTextColor, iLength, iInputType;
    private Context mContext;
    private OptionsPickerView pvHeightOptions;
    private OptionsPickerView pvWeightOptions;
    private OptionsPickerView pvConstellationOptions;
    private OptionsPickerView pvLableOptions;
    private OptionsPickerView sexOptions;
    private TimePickerView pvBirthdayOptions;
    private long birthdayDate;
    private boolean isShowLine = true;
    private String selectAddressCode;//省市区地址编码
    private boolean isClickEnable = true;//是否可点击(默认可点击)
    //是否允许输入0  数字类型时候调用
    private boolean isZero = true;
    //是否允许首位是0 也不能输入单独的0
    private boolean isFirstZero = true;
    /**
     * 年龄数据
     */
    private List<Integer> mAgeList = new ArrayList<>();


    public CustomInfoEditTextView(Context context) {
        super(context);
        initAttrs(context, null);
        init();
    }


    public CustomInfoEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }


    public CustomInfoEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.CustomInfoEditTextView);
        sPrompt = typedArray.getString(R.styleable.CustomInfoEditTextView_cie_prompt_text);
        sContent = typedArray.getString(R.styleable.CustomInfoEditTextView_cie_edit_text);
        sHint = typedArray.getString(R.styleable.CustomInfoEditTextView_cie_edit_hint);
        sUnit = typedArray.getString(R.styleable.CustomInfoEditTextView_cie_unit);
        isShowLine = typedArray.getBoolean(R.styleable.CustomInfoEditTextView_cie_show_line, true);
        iMandatoryVisibility = typedArray
                .getInteger(R.styleable.CustomInfoEditTextView_cie_mandatory_visibility, 1);
        iEditTextColor = typedArray
                .getResourceId(R.styleable.CustomInfoEditTextView_cie_edit_textcolor, R.color.color_222222);
        iPromptTextColor = typedArray
                .getResourceId(R.styleable.CustomInfoEditTextView_cie_prompt_textcolor, R.color.color_222222);
        bShowArrow = typedArray
                .getBoolean(R.styleable.CustomInfoEditTextView_cie_show_arrow, false);
        bEditable = typedArray.getBoolean(R.styleable.CustomInfoEditTextView_cie_editable, true);
        iLength = typedArray.getInteger(R.styleable.CustomInfoEditTextView_cie_edit_text_length, 0);
        iInputType = typedArray
                .getInteger(R.styleable.CustomInfoEditTextView_cie_edit_text_inputtype, 2);
        iMode = typedArray.getInteger(R.styleable.CustomInfoEditTextView_cie_mode, 0);
        isZero = typedArray.getBoolean(R.styleable.CustomInfoEditTextView_cie_is_zero, true);
        isFirstZero = typedArray
                .getBoolean(R.styleable.CustomInfoEditTextView_cie_first_zero, true);
        typedArray.recycle();
    }


    private void init() {
        setBackgroundResource(R.color.y_common_bg_color);
        LayoutInflater.from(getContext()).inflate(R.layout.view_custom_info_edit_text, this, true);
        tv_prompt = findViewById(R.id.tv_info);
        et_info = findViewById(R.id.et_info);
        line = findViewById(R.id.line);
        tv_content = findViewById(R.id.tv_content);
        iv_mandatory = findViewById(R.id.iv_mandatory);
        tv_unit = findViewById(R.id.tv_unit);
        if (!TextUtils.isEmpty(sUnit)) {
            tv_unit.setText(sUnit);
        }
        if (isShowLine) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(GONE);
        }
        iv_arrow = findViewById(R.id.iv_arrow);
        if (iLength > 0) {
            et_info.setFilters(new InputFilter[] { new InputFilter.LengthFilter(iLength) });
        }
        if (iInputType == TYPE_CLASS_NUMBER) {
            if (isZero) {
                et_info.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else {
                et_info.setKeyListener(new NumberKeyListener() {
                    @Override
                    public int getInputType() {
                        return android.text.InputType.TYPE_CLASS_NUMBER;
                    }


                    @Override
                    protected char[] getAcceptedChars() {
                        char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
                        return numberChars;
                    }
                });
            }
        } else if (iInputType == TYPE_NUMBER_FLAG_DECIMAL) {
            et_info.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            //默认控制输入9位数，小数点前6位，后2位，小数一位，共9位
            //            InputFilter[] filters = {new CashierInputFilter(iLength), new InputFilter.LengthFilter(iLength + 3)};
            //            et_info.setFilters(filters);
            // TODO: 2018/2/11 以下注释
            //            et_info.setFilters(new InputFilter[]{new InputFilter.LengthFilter(iLength + 3)});
            //            et_info.addTextChangedListener(new MyWatcher(iLength, 2));//限制小数点后最多2位
        } else if (iInputType == TYPE_ID) {
            et_info.setKeyListener(new NumberKeyListener() {
                @Override
                public int getInputType() {
                    return InputType.TYPE_CLASS_TEXT;
                }


                @Override
                protected char[] getAcceptedChars() {
                    char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X',
                            'x' };
                    return numberChars;
                }
            });
        }
        switch (iMandatoryVisibility) {
            case 0:
                iv_mandatory.setVisibility(GONE);
                break;
            case 1:
                iv_mandatory.setVisibility(VISIBLE);
                break;
            case 2:
                iv_mandatory.setVisibility(INVISIBLE);
                break;
            default:
                break;
        }
        tv_prompt.setTextColor(ContextCompat.getColor(mContext, iPromptTextColor));
        et_info.setTextColor(ContextCompat.getColor(mContext, iEditTextColor));
        tv_prompt.setText(sPrompt);
        setEditText(sContent);
        if (bShowArrow) {
            iv_arrow.setVisibility(VISIBLE);
        } else {
            iv_arrow.setVisibility(GONE);
        }

        if (iMode != 0 && isClickEnable) {
            setOnClickListener(this);
        }
        if (iMode == MODE_CITY) {
            initCityData();
        }
        if (iMode == MODE_OLD) {
            for (int i = 18; i <= 65; i++) {
                mAgeList.add(i);
            }
        }
        if (!isFirstZero) {
            et_info.addTextChangedListener(new TextWatcher() {   // 这是主要方法，下面为一些处理

                @Override
                public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                }


                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }


                @Override
                public void afterTextChanged(Editable s) {
                    String text = s.toString();
                    if (text.indexOf('0') == 0) {
                        s.replace(0, 1, "");
                    }
                }
            });
        }
    }


    /**
     * 设置是否可点击
     */
    public void setClickEnable(boolean clickEnable) {
        isClickEnable = clickEnable;
        if (isClickEnable) {
            setClickable(true);
        } else {
            setClickable(false);
        }
        et_info.setEnabled(false);
    }


    /**
     * 初始化城市数据，解析json
     */
    private void initCityData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        if (iMode == MODE_CITY) {
            //MobclickAgent.onEvent(mContext, "mj_editInfoCity");
            //城市选择
            if (isLoaded) {
                hideSoftInput(et_info);
                //showSelectAddressPickerView();
                showCommOptionsPicker(MODE_CITY);
            }
        } else if (iMode == MODE_WEIGHT) {
            hideSoftInput(et_info);
            showWeightOptionsPicker();
        } else if (iMode == MODE_BIRTHDATE) {
            //MobclickAgent.onEvent(mContext, "mj_editInfoBirthday");
            hideSoftInput(et_info);
            showBirthdayOptionsPicker();
        } else if (iMode == MODE_HEIGHT) {
            hideSoftInput(et_info);
            showHeightOptionsPicker();
        } else if (iMode == MODE_CONSTELLATION) {
            hideSoftInput(et_info);
            showConstellationOptionsPicker();
        } else if (iMode == MODE_LABEL) {
            //            showLableOptionsPicker();
        } else if (iMode == MODE_SEX) {
            hideSoftInput(et_info);
            showCommOptionsPicker(MODE_SEX);
            //showSexOptionsPicker();
        } else if (iMode == MODE_OLD) {
            hideSoftInput(et_info);
            showCommOptionsPicker(MODE_OLD);
        }
    }


    public void showSexOptionsPicker() {
        if (sexOptions == null) {
            sexOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    sContent = getSexData().get(options1);
                    tv_content.setText(new StringBuilder().append(sContent));
                    //ToastUtil.show(mContext, "性别选择后不能修改");
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择性别")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .setSelectOptions(0).build();
            sexOptions.setNPicker(getSexData(), null, null);
        }
        sexOptions.show();
    }


    private void showLableOptionsPicker() {
        if (pvLableOptions == null) {
            pvLableOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    sContent = getLableData().get(options1);
                    tv_content.setText(new StringBuilder().append(sContent));
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择标签")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .setSelectOptions(1).build();
            pvLableOptions.setNPicker(getLableData(), null, null);
        }
        pvLableOptions.show();
    }


    private void showCommOptionsPicker(final int mode) {
        if (null == pvLableOptions) {
            pvLableOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    if (mode == MODE_SEX) {
                        sContent = getSexData().get(options1);
                        tv_content.setText(new StringBuilder().append(sContent));
                        //ToastUtil.show(mContext, "性别选择后不能修改");
                    } else if (mode == MODE_CITY) {
                        //selectAddressCode = mDistrictBeanArrayList.get(options1).get(options2)
                        //                                          .get(options3).getId();
                        //sContent = mCityBeanArrayList.get(options1).get(options2).getName();
                        tv_content.setText(sContent);
                    } else if (mode == MODE_OLD) {
                        sContent = String.valueOf(mAgeList.get(options1));
                        tv_content.setText(sContent);
                    }
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.color_6139F0))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.color_C1C7CF))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setSelectOptions(0).setLineSpacingMultiplier(2.0f)
              //        .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
              //    @Override
              //    public void onOptionsSelectChanged(int options1, int options2, int options3) {
              //        if (mode == MODE_SEX) {
              //            sContent = getSexData().get(options1);
              //            tv_content.setText(new StringBuilder().append(sContent));
              //        } else if (mode == MODE_CITY) {
              //            selectAddressCode = mDistrictBeanArrayList.get(options1).get(options2)
              //                                                      .get(options3).getId();
              //            sContent = mCityBeanArrayList.get(options1).get(options2).getName();
              //            tv_content.setText(sContent);
              //        } else if (mode == MODE_OLD) {
              //            sContent = String.valueOf(mAgeList.get(options1));
              //            tv_content.setText(sContent);
              //        }
              //    }
              //}).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
              //    @Override
              //    public void customLayout(View v) {
              //        //自定义布局中的控件初始化及事件处理
              //        final TextView tvCecal = (TextView) v.findViewById(R.id.tv_cecal);
              //        tvCecal.setOnClickListener(new View.OnClickListener() {
              //            @Override
              //            public void onClick(View v) {
              //                pvLableOptions.dismiss();
              //            }
              //        });
              //    }
              //})
              .setTextColorCenter(ContextCompat.getColor(mContext, R.color.color_EF1263))
              .setTextColorOut(ContextCompat.getColor(mContext, R.color.color_222222)).build();
        }
        if (mode == MODE_SEX) {
            pvLableOptions.setPicker(getSexData());
        } else if (mode == MODE_CITY) {
            pvLableOptions.setPicker(mProvinceBeanArrayList, mCityBeanArrayList);
        } else if (mode == MODE_OLD) {
            pvLableOptions.setPicker(mAgeList);
        }
        pvLableOptions.show();
    }


    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(EditText view) {
        InputMethodManager mInputMethodManager = (InputMethodManager) mContext
                .getSystemService(this.mContext.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void showConstellationOptionsPicker() {
        if (pvConstellationOptions == null) {
            pvConstellationOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    sContent = getConstellationData().get(options1);
                    tv_content.setText(new StringBuilder().append(sContent));
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择星座")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .setSelectOptions(1).build();
            pvConstellationOptions.setNPicker(getConstellationData(), null, null);
        }
        pvConstellationOptions.show();
    }


    private void showHeightOptionsPicker() {
        if (pvHeightOptions == null) {
            pvHeightOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    sContent = getHeightData().get(options1);
                    tv_content.setText(new StringBuilder().append(sContent).append("cm"));
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择身高（cm）")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .setSelectOptions(120).build();
            pvHeightOptions.setNPicker(getHeightData(), null, null);
        }
        pvHeightOptions.show();
    }


    private void showWeightOptionsPicker() {
        if (pvWeightOptions == null) {
            pvWeightOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    sContent = getWeightData().get(options1);
                    tv_content.setText(new StringBuilder().append(sContent).append("kg"));
                }
            }).setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择体重（kg）")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .setSelectOptions(50).build();
            pvWeightOptions.setNPicker(getWeightData(), null, null);
        }
        pvWeightOptions.show();
    }


    private void showBirthdayOptionsPicker() {
        if (pvBirthdayOptions == null) {
            Calendar selectedDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.set(DateUtil.getInstance().getYear(),
                    DateUtil.getInstance().getMonth() - 1, DateUtil.getInstance().getDay());

            pvBirthdayOptions = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    sContent = DateUtil.getInstance().date2Str(date, "yyyy.MM.dd");
                    birthdayDate = date.getTime();
                    tv_content.setText(sContent);
                }
            }).setType(new boolean[] { true, true, true, false, false, false })// 默认全部显示
              .setSubmitText("确定")//确定按钮文字
              .setCancelText("取消")//取消按钮文字
              .setTitleText("选择生日")//标题
              .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
              .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
              .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
              .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
              .isCyclic(false)//是否循环滚动
              .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
              .setRangDate(null, endDate)//起始终止年月日设定
              .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
              .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
              .build();
        }
        pvBirthdayOptions.show();
    }


    private List<String> getConstellationData() {
        String[] astro = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座",
                "处女座", "天秤座", "天蝎座", "射手座" };
        List list = Arrays.asList(astro);
        return list;
    }


    private List<String> getLableData() {
        String[] lable = new String[] { "女神", "萝莉", "可爱", "高冷", "邻家", "性感", "男神", "正太", "帅气", "阳光",
                "型男" };
        List list = Arrays.asList(lable);
        return list;
    }


    private List<String> getSexData() {
        List<String> weightList = new ArrayList<>();
        weightList.add("男");
        weightList.add("女");
        return weightList;
    }


    private List<String> getWeightData() {
        List<String> weightList = new ArrayList<>();
        for (int i = 20; i < 200; i++) {
            weightList.add(String.valueOf(i));
        }
        return weightList;
    }


    private List<String> getHeightData() {
        List<String> weightList = new ArrayList<>();
        for (int i = 50; i < 230; i++) {
            weightList.add(String.valueOf(i));
        }
        return weightList;
    }


    /**
     * 弹出省市区选择器
     */
    private void showSelectAddressPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //                selectAddressName = mProvinceBeanArrayList.get(options1).getName() + "/" +
                //                        mCityBeanArrayList.get(options1).get(options2).getName() + "/" +
                //                        mDistrictBeanArrayList.get(options1).get(options2).get(options3).getName();
                //selectAddressCode = mDistrictBeanArrayList.get(options1).get(options2).get(options3)
                //                                          .getId();
                //sContent = mCityBeanArrayList.get(options1).get(options2).getName();
                tv_content.setText(sContent);
            }
        }).setTitleText("城市选择")
          .setSubmitColor(ContextCompat.getColor(mContext, R.color.y_blue_color))//确定按钮文字颜色
          .setCancelColor(ContextCompat.getColor(mContext, R.color.gary_82))
          .setTitleBgColor(ContextCompat.getColor(mContext, R.color.gray_d8))//标题背景颜色 Night mode
          .setTitleColor(ContextCompat.getColor(mContext, R.color.gary_82))//标题背景颜色 Night mode
          .setContentTextSize(20).build();
        pvOptions.setPicker(mProvinceBeanArrayList, mCityBeanArrayList);//三级选择器
        pvOptions.show();
    }


    //省份数据
    ArrayList<ProvinceBean> mProvinceBeanArrayList = new ArrayList<>();
    //城市数据
    ArrayList<ArrayList<CityBean>> mCityBeanArrayList;
    //地区数据
    ArrayList<ArrayList<ArrayList<DistrictBean>>> mDistrictBeanArrayList;
    private ProvinceBean[] mProvinceBeenArray;

    private boolean isLoaded;//省市区是否加载了


    private void initJsonData() {
        try {
            String cityJson = JsonParseControl.parseJson(mContext, "city.json");
            mProvinceBeanArrayList = JsonParseControl.parseArray(cityJson, ProvinceBean.class);
            mCityBeanArrayList = new ArrayList<>(mProvinceBeanArrayList.size());
            mDistrictBeanArrayList = new ArrayList<>(mProvinceBeanArrayList.size());

            //省份数据
            mProvinceBeenArray = new ProvinceBean[mProvinceBeanArrayList.size()];
            for (int p = 0; p < mProvinceBeanArrayList.size(); p++) {
                //遍历每个省份
                ProvinceBean itemProvince = mProvinceBeanArrayList.get(p);
                //每个省份对应下面的市
                ArrayList<CityBean> cityList = itemProvince.getCityList();
                //当前省份下面的所有城市
                CityBean[] cityNames = new CityBean[cityList.size()];

                //遍历当前省份下面城市的所有数据
                for (int j = 0; j < cityList.size(); j++) {
                    cityNames[j] = cityList.get(j);
                    //当前省份下面每个城市下面再次对应的区或者县
                    List<DistrictBean> districtList = cityList.get(j).getCityList();
                    DistrictBean[] distrinctArray = new DistrictBean[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictBean districtModel = districtList.get(k);
                        distrinctArray[k] = districtModel;
                    }
                }

                mCityBeanArrayList.add(cityList);
                ArrayList<ArrayList<DistrictBean>> array2DistrictLists = new ArrayList<>(cityList
                        .size());
                for (int c = 0; c < cityList.size(); c++) {
                    CityBean cityBean = cityList.get(c);
                    array2DistrictLists.add(cityBean.getCityList());
                }
                mDistrictBeanArrayList.add(array2DistrictLists);
                //赋值所有省份的名称
                mProvinceBeenArray[p] = itemProvince;
            }
            isLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getsContent() {
        return sContent;
    }


    public long getBirthdayDate() {
        return birthdayDate;
    }


    public void setEditText(String s) {
        sContent = s;
        if (!bEditable) {
            et_info.setVisibility(GONE);
            tv_content.setVisibility(VISIBLE);
            if (sContent == null || TextUtils.isEmpty(sContent)) {
                tv_content.setHint(sHint);
            } else {
                tv_content.setText(s);
            }
        } else {
            tv_content.setVisibility(GONE);
            et_info.setVisibility(VISIBLE);
            if (sContent == null || TextUtils.isEmpty(sContent)) {
                et_info.setHint(sHint);
            } else {
                et_info.setText(s);
                try {
                    if (iLength > 0 && s.length() > iLength) {
                        et_info.setSelection(iLength);
                    } else {
                        if (!TextUtils.isEmpty(s)) {
                            et_info.setSelection(s.length());
                        }
                    }
                } catch (Exception e) {
                    Log.e("msg", e.getMessage());
                }
                //
            }
        }
    }


    public EditText getEditTextView() {
        return et_info;
    }


    public TextView getTv_content() {
        return tv_content;
    }


    public String getEditTextContent() {
        return et_info.getText().toString();
    }


    public String getTextContent() {
        return tv_content.getText().toString();
    }
}
