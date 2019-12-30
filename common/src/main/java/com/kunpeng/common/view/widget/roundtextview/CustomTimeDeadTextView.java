package com.kunpeng.common.view.widget.roundtextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

/**
 * @author huangminzheng
 * @date 2018/1/17 下午5:54
 */
@SuppressLint("AppCompatCustomView")
public class CustomTimeDeadTextView extends TextView {

    private Context mContext;
    private Callback callback;


    public CustomTimeDeadTextView(Context context) {
        this(context, null);
    }


    public CustomTimeDeadTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CustomTimeDeadTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    /**
     * 开始倒计时
     *
     * @param timeDead 倒计时时间(以秒为单位)
     */
    public void startTimeDead(int timeDead) {
        TimeDeadLine deadLine = new TimeDeadLine(timeDead * 1000, 1000);
        deadLine.start();
    }


    /**
     * 开始倒计时(默认60秒)
     */
    public void startTimeDead() {
        TimeDeadLine deadLine = new TimeDeadLine(60 * 1000, 1000);
        deadLine.start();
    }


    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    /**
     * 倒计时
     */
    private class TimeDeadLine extends CountDownTimer {

        public TimeDeadLine(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onTick(long millisUntilFinished) {
            setClickable(false);
            setEnabled(false);
            setText(millisUntilFinished / 1000 + "秒");
        }


        @Override
        public void onFinish() {
            setClickable(true);
            setEnabled(true);
            setText("发送验证码");
            if (callback != null) {
                callback.done();
                callback = null;
            }
        }
    }

    public interface Callback {
        void done();
    }
}
