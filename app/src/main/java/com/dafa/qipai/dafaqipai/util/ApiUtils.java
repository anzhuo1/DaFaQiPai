package com.dafa.qipai.dafaqipai.util;


import android.util.Log;

import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiUtils {
    private static final String TAG = ApiUtils.class.getSimpleName();
    private static ApiUtils instance;

    private ApiUtils() {
    }

    public static ApiUtils getInstance() {
        if (null == instance) {
            instance = new ApiUtils();
        }
        return instance;
    }

    /**
     * 根据玩法获取赔率
     *
     * @param playId
     * @param okGoCallBack
     */
    public void getSscPlayPl(
            Long playId,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("playId", playId);
        this.post(okGoCallBack, ApiConstant.API_SSC_GET_SSC_PLAY_PL, paramsMap);
    }

    /**
     * 根据玩法获取开奖倒计时
     *
     * @param playGroupId
     * @param okGoCallBack
     */
    public void getDataOnpenTime2(
            Long playGroupId,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("playGroupId", playGroupId);
        this.post(okGoCallBack, ApiConstant.API_SSC_GET_SSC_OPEN_TIME_2, paramsMap);
    }

    /**
     * 彩票下注
     *
     * @param uid          UID
     * @param token        TOKEN
     * @param sscBetForm   下注内容
     * @param okGoCallBack 回调
回调     */
//    public void betSsc(
//            String uid,
//            String token,
//            SscBetForm sscBetForm,
//            OkGoCallBack okGoCallBack
//    ) {
//        Log.v("投注内容", JSONUtils.toJSONStr(sscBetForm));
//        Map<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("uid", uid);
//        paramsMap.put("token", token);
//        paramsMap.put("betForm", JSONUtils.toJSONStr(sscBetForm));
//        this.post(okGoCallBack, ApiConstant.API_BET, paramsMap);
//        System.out.println(JSONUtils.toJSONStr(sscBetForm));
//    }

    /**
     * 获得开奖倒计时
     *
     * @param playGroupId  caizhong ID
     * @param okGoCallBack 回调
     */
    public void getSscOpenTime(
            Long playGroupId,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("playGroupId", playGroupId);
        this.post(okGoCallBack, ApiConstant.API_SSC_GET_SSC_OPEN_TIME_2, paramsMap);

    }

    /**
     * 获得计划内开奖结果（包含开奖中）
     *
     * @param playGroupId  caizhong ID
     * @param size         数量
     * @param okGoCallBack 回调
     */
    public void getPlanDataHistory(
            Long playGroupId,
            Integer size,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("playGroupId", playGroupId);
        paramsMap.put("size", size);
        this.post(okGoCallBack, ApiConstant.API_SSC_GET_PLAN_OPEN_DATA_HISTORY, paramsMap);
    }

    private void post(OkGoCallBack okGoCallBack, String apiUrl, Map<String, Object> paramsMap) {
        if (null == paramsMap) {
            paramsMap = new HashMap<>();
        }

        PostRequest postRequest = OkGo.post(apiUrl);
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            if (null != entry.getKey() && null != entry.getValue()) {
                postRequest.params(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        // 公共参数
        paramsMap.put("clientType", ApiConstant.CLIENT_TYPE);
        paramsMap.put("companyShortName", ApiConstant.COMPANY_SHORT_NAME);

        // 执行
        try {
            postRequest.execute(okGoCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取投注列表
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param pageIndex    页码
     * @param number       期号
     * @param pageIndex    第几页
     * @param pageSize     每页数量
     * @param playGroupId  彩种ID
     * @param okGoCallBack 回调
     */
    public void getDateHistoryList(
            String startTime,
            String endTime,
            Long playGroupId,
            String number,
            String openDate,
            Integer pageIndex,
            Integer pageSize,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("startTime", startTime);
        paramsMap.put("endTime", endTime);
        paramsMap.put("playGroupId", playGroupId);
        paramsMap.put("number", number);
        paramsMap.put("openDate", openDate);
        paramsMap.put("pageIndex", pageIndex);
        paramsMap.put("pageSize", pageSize);

        this.post(okGoCallBack, ApiConstant.API_SSC_GET_DATA_HISTORY, paramsMap);
    }

    /**
     * 获取开奖历史
     *
     * @param uid          UID
     * @param token        TOKEN
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param pageSize     每页数量
     * @param pageIndex    页码
     * @param status       结算状态
     * @param playGroupId  彩种ID
     * @param isZhongjiang 是走中奖
     * @param okGoCallBack 回调
     */
    public void getSscBetsList(
            String uid,
            String token,
            Date startTime,
            Date endTime,
            Integer pageSize,
            Integer pageIndex,
            Integer status,
            Long playGroupId,
            Boolean isZhongjiang,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("token", token);
        paramsMap.put("uid", uid);
        paramsMap.put("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("pageIndex", pageIndex);
        paramsMap.put("status", status);
        paramsMap.put("playGroupId", playGroupId);
        paramsMap.put("isZhongjiang", isZhongjiang);
        this.post(okGoCallBack, ApiConstant.API_GET_SSC_BETS_LIST, paramsMap);
    }

    private String formatDate(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 获取充值记录
     *
     * @param uid UID
     * @param token TOKEN
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageIndex 页码
     * @param pageSize 每页数量
     * @param status 状态
     * @param type 类型
     * @param okGoCallBack 回调
     */
    public void getDepositList(
            String uid,
            String token,
            Date startTime,
            Date endTime,
            Integer pageIndex,
            Integer pageSize,
            Integer status,
            Long type,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        paramsMap.put("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("pageIndex", pageIndex);
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("status", status);
        paramsMap.put("type", type);
        this.post(okGoCallBack, ApiConstant.API_GET_DEPOSIT_LIST, paramsMap);
    }

    public void getMoneyRecordList(
            String uid,
            String token,
            Date startTime,
            Date endTime,
            Integer pageIndex,
            Integer pageSize,
            Integer status,
            Long type,
            OkGoCallBack okGoCallBack
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        paramsMap.put("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        paramsMap.put("pageIndex", pageIndex);
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("status", status);
        paramsMap.put("type", type);
        this.post(okGoCallBack, ApiConstant.API_GET_DEPOSIT_LIST, paramsMap);
    }
}
