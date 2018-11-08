package com.dafa.qipai.dafaqipai.wihget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dafa.qipai.dafaqipai.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Select {

//    private static TimePickerView mTimePickerView;
//    private static OptionsPickerView optionsPickerView;
//
//    public static void selectYmd(Context context, TimePickerView.OnTimeSelectListener listener) {
//        mTimePickerView = new TimePickerView(context, TimePickerView.Type.YEAR_MONTH_DAY);
//        mTimePickerView.setHeadBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        // 设置点击外部是否消失
//        mTimePickerView.setCancelable(true);
//        // 设置滚轮字体大小
////        mCityPickerView.setTextSize(18f);
//        // 设置标题
////        mCityPickerView.setTitle("我是标题");
//        // 设置取消文字
////        mCityPickerView.setCancelText("我是取消文字");
//        // 设置取消文字颜色
//        mTimePickerView.setCancelTextColor(Color.WHITE);
//        // 设置取消文字大小
////        mCityPickerView.setCancelTextSize(14f);
//        // 设置确定文字
////        mCityPickerView.setSubmitText("我是确定文字");
//        // 设置确定文字颜色
//        mTimePickerView.setSubmitTextColor(Color.WHITE);
//        // 设置确定文字大小
////        mCityPickerView.setSubmitTextSize(14f);
//        // 设置头部背景
////            headbacf
//        // TimePickerView 同样有上面设置样式的方法
//        // 设置是否循环
////        mTimePickerView.setCyclic(true);
//        // 设置滚轮文字大小
////        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
//        // 设置时间可选范围(结合 setTime 方法使用,必须在)
////        Calendar calendar = Calendar.getInstance();
////        mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
//        // 设置选中时间
////        mTimePickerView.setTime(new Date());
//
//        mTimePickerView.setOnTimeSelectListener(listener);
//        mTimePickerView.show();
//    }
//
//    public static void setTime(Context context, Date date, TimePickerView.OnTimeSelectListener listener) {
//        mTimePickerView = new TimePickerView(context, TimePickerView.Type.YEAR_MONTH_DAY);
//        mTimePickerView.setHeadBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        // 设置点击外部是否消失
//        mTimePickerView.setCancelable(true);
//        // 设置滚轮字体大小
////        mCityPickerView.setTextSize(18f);
//        // 设置标题
////        mCityPickerView.setTitle("我是标题");
//        // 设置取消文字
////        mCityPickerView.setCancelText("我是取消文字");
//        // 设置取消文字颜色
//        mTimePickerView.setCancelTextColor(Color.WHITE);
//        // 设置取消文字大小
////        mCityPickerView.setCancelTextSize(14f);
//        // 设置确定文字
////        mCityPickerView.setSubmitText("我是确定文字");
//        // 设置确定文字颜色
//        mTimePickerView.setSubmitTextColor(Color.WHITE);
//        // 设置确定文字大小
////        mCityPickerView.setSubmitTextSize(14f);
//        // 设置头部背景
////            headbacf
//        // TimePickerView 同样有上面设置样式的方法
//        // 设置是否循环
////        mTimePickerView.setCyclic(true);
//        // 设置滚轮文字大小
////        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
//        // 设置时间可选范围(结合 setTime 方法使用,必须在)
////        Calendar calendar = Calendar.getInstance();
////        mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
//        // 设置选中时间
//        mTimePickerView.setTime(date);
//
//        mTimePickerView.setOnTimeSelectListener(listener);
//        mTimePickerView.show();
//    }
//
    public static String format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String format1 = format.format(date);

        return format1;
    }
//
//
//    public static void selectYmdHm(Context context, TimePickerView.OnTimeSelectListener listener) {
//        mTimePickerView = new TimePickerView(context, TimePickerView.Type.ALL);
//        mTimePickerView.setHeadBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        // 设置点击外部是否消失
//        mTimePickerView.setCancelable(true);
//        // 设置滚轮字体大小
////        mCityPickerView.setTextSize(18f);
//        // 设置标题
////        mCityPickerView.setTitle("我是标题");
//        // 设置取消文字
////        mCityPickerView.setCancelText("我是取消文字");
//        // 设置取消文字颜色
//        mTimePickerView.setCancelTextColor(Color.WHITE);
//        // 设置取消文字大小
////        mCityPickerView.setCancelTextSize(14f);
//        // 设置确定文字
////        mCityPickerView.setSubmitText("我是确定文字");
//        // 设置确定文字颜色
//        mTimePickerView.setSubmitTextColor(Color.WHITE);
//        // 设置确定文字大小
////        mCityPickerView.setSubmitTextSize(14f);
//        // 设置头部背景
////            headbacf
//        // TimePickerView 同样有上面设置样式的方法
//        // 设置是否循环
////        mTimePickerView.setCyclic(true);
//        // 设置滚轮文字大小
////        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
//        // 设置时间可选范围(结合 setTime 方法使用,必须在)
////        Calendar calendar = Calendar.getInstance();
////        mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
//        // 设置选中时间
////        mTimePickerView.setTime(new Date());
//
//        mTimePickerView.setOnTimeSelectListener(listener);
//        mTimePickerView.show();
//    }
//
    public static String format2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String format1 = format.format(date);

        return format1;
    }
//
//
//    public static void selectOption(Context context, ArrayList list, OptionsPickerView.OnOptionsSelectListener listener) {
//        optionsPickerView = new OptionsPickerView(context);
//        optionsPickerView.setHeadBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        // 设置点击外部是否消失
//        optionsPickerView.setCancelable(true);
//        // 设置滚轮字体大小
//        optionsPickerView.setCancelTextColor(Color.WHITE);
//
//        // 设置确定文字颜色
//        optionsPickerView.setSubmitTextColor(Color.WHITE);
//
//        optionsPickerView.setPicker(list);
//        optionsPickerView.setOnOptionsSelectListener(listener);
//        optionsPickerView.show();
//
//    }


    public static void selectOption(Context context,String title, ArrayList list, OnOptionsSelectListener listener) {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, listener)

                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(context.getResources().getColor(R.color.colorText))
                .setSubmitColor(context.getResources().getColor(R.color.colorText))
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x00000000) //设置外部遮罩颜色
                .isDialog(true)
                .build();

        pvOptions.setPicker(list);
        pvOptions.show();
    }

    public static void selectYmdHm(Context context, OnTimeSelectListener listener){

        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(context,listener )

                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(context.getResources().getColor(R.color.colorText))
                .setSubmitColor(context.getResources().getColor(R.color.colorText))
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x00000000) //设置外部遮罩颜色
                .isDialog(true)
                .build();

        pvTime.show();
    }

}