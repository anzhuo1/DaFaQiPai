package com.dafa.qipai.dafaqipai.util;

import android.content.Context;



/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-10-24 20:15                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */
public class UserUtil {
    /**
     * 是否登录
     *
     * @param context
     * @return 优化  不为空才是登录状态   剩下全部是未登录
     */
    public static boolean isLoginApp(Context context) {

        try {
            String mobileLogKey = getToken(context);
            if ("".equals(mobileLogKey)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * @param context
     * @return
     */
    public static String getToken(Context context) {


        try {
            String token = ACache.get(context).getAsString("token");

            if ("".equals(token) || token == null) {
                return "";
            } else {
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 退出登录
     *
     * @param context
     * @return
     */
    public static void loginOut(Context context) {

        ACache.get(context).put("token", "");
        ACache.get(context).put("userId", "");
        ACache.get(context).put("account", "");


    }


    /**
     * @param context
     * @return
     */
    public static String getUserID(Context context) {

        try {
            return ACache.get(context).getAsString("userId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    /**
     * @param context
     * @return
     */
    public static String getAccount(Context context) {

        return ACache.get(context).getAsString("account");
    }


    /**
     * @param context
     * @return
     */
    public static String getWanfa(Context context) {

        return ACache.get(context).getAsString("px");
    }




}