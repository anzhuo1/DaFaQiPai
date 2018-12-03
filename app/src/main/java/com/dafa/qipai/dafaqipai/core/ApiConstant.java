package com.dafa.qipai.dafaqipai.core;

public class ApiConstant {


    /**
     * 统一接口地址
     */


//    public static String API_DOMAIN = "http://43.230.171.202:8080/api/";
//    public static String VERSION = "1";
//    public static String COMPANY_SHORT_NAME = "a2";


    public static String VERSION = "1";
    public static String COMPANY_SHORT_NAME = "ky";
    public static String API_DOMAIN = "http://api.ky.bb.nf/";


//    public static String VERSION = "1";
//    public static String COMPANY_SHORT_NAME = "ky";
//    public static String API_DOMAIN = "http://api.cs.bb.nf/";


    public static String CLIENT_TYPE = "Android";


    public static String API_WEIHU = "http://weihu.bb.nf/11086.json";
    public static String API_REGISTER = API_DOMAIN + "member/register.json";    // 用户注册接口
    public static String API_LOGIN = API_DOMAIN + "member/login.json";          // 用户登录接口
    public static String API_GET_USER_SESSION = API_DOMAIN + "member/getUserSession.json";   // 获取用户SESSION信息
    public static String API_GET_USER_LAST_INFO = API_DOMAIN + "member/getUserLastInfo.json";   // 获取用户上次信息，登录，游戏，存款，取款
    public static String API_GET_BIND_STATUS = API_DOMAIN + "member/getBindStatus.json";   // 获取用户绑定状态，手机，银行卡，邮箱
    public static String API_GET_USER_INBOX_LIST = API_DOMAIN + "member/getUserInboxList.json";   // 获取用户站内信列表
    public static String API_GET_USER_INBOX = API_DOMAIN + "member/getUserInbox.json";   // 获取用户站内信列表
    public static String API_CHECK_ONLINE = API_DOMAIN + "member/checkOnline.json";   // 检查用户在线
    public static String API_GET_PROMOTION = API_DOMAIN + "promotion/getPromotion.json";   // 获取优惠活动
    public static String API_GET_LOGO = API_DOMAIN + "webSetting/getLogo.json";   // 获取优惠活动
    public static String API_GET_SERVER_TIME = API_DOMAIN + "time/getServerTime.json";  // 服务器时间接口
    public static String API_GET_ALL_SSC_OPEN_TIME = API_DOMAIN + "ssc/getAllSscOpenTime.json";//获取彩票开奖信息
    public static String API_GET_ALL_SSC_OPEN_TIME_TWO = API_DOMAIN + "ssc/getAllSscOpenTime2.json";//获取彩票开奖信息2
    public static String API_GET_SSC_CURRENT_OPEN_TIME = API_DOMAIN + "/ssc/getSscOpenTime2.json";//获取彩种当前开奖时间
    public static String API_GET_SYSTEM_BANK_CARD = API_DOMAIN + "member/getSystemBankCard.json";//获取系统银行账号
    public static String API_GET_DEPOSIT_CHANNEL = API_DOMAIN + "member/getDepositChannel.json";//获取存款渠道信息
    public static String API_SUBMIT_YHZZ = API_DOMAIN + "member/submitYhzz.json";//提交银行转账
    public static String API_WEI_XIN_ZZ = API_DOMAIN + "member/submitWeixinzz.json";//微信转账
    public static String API_ALIPAY_ZZ = API_DOMAIN + "member/submitAlipayzz.json";//提交支付宝转账
    public static String API_TENPAY_ZZ = API_DOMAIN + "member/submitTenpayzz.json";//提交财付通转账
    public static String API_SUBMIT_WITHDRAW = API_DOMAIN + "member/submitWithdraw.json";//快速取款
    public static String API_GET_SYS_ALIPAY = API_DOMAIN + "member/getSystemAlipay.json";//获取系统支付宝账号
    public static String API_GET_SYS_WEIXIN = API_DOMAIN + "member/getSystemWeixin.json";//获取系统微信账号
    public static String API_GET_SYS_TENPAY = API_DOMAIN + "member/getSystemTenpay.json";//获取系统财付通账号
    public static String API_EDIT_USER_INFO = API_DOMAIN + "member/editUserInfo.json";//编辑用户信息
    public static String API_RESET_PASSWORD = API_DOMAIN + "member/resetPassword.json";//修改登录密码
    public static String API_RESET_DRAW_PASSWORD = API_DOMAIN + "member/resetDrawPassword.json";//修改取款密码
    public static String API_GET_USER_BANK_CARD_LIST = API_DOMAIN + "member/getUserBankCardList.json";//获取用户银行卡列表
    public static String API_GET_USER_BANK_CARD = API_DOMAIN + "member/getUserBankCard.json";//获取用户银行卡
    public static String API_GET_SAFE_INFO = API_DOMAIN + "member/getSafeInfo.json";//获得安全信息
    public static String API_GET_SYS_PAYON_ONLINE = API_DOMAIN + "member/getSystemPayonline.json";//获取系统在线支付列表
    public static String API_GET_DEPOSIT_LIST = API_DOMAIN + "member/getDepositList.json";//获取用户存款记录
    public static String API_GET_DEPOSIT = API_DOMAIN + "member/getDeposit.json";//获取用户存款记录
    public static String API_GET_SSC_BETS_LIST = API_DOMAIN + "member/getSscBetsList.json";//获取用户彩票投注记录
    public static String API_GET_SSC_BETS = API_DOMAIN + "member/getSscBets.json";//获取用户彩票投注记录
    public static String API_GET_WITHDRAW_LIST = API_DOMAIN + "member/getWithdrawList.json";//获取用户提款记录
    public static String API_GET_WITHDRAW = API_DOMAIN + "member/getWithdraw.json";//获取用户提款记录
    public static String API_GET_SSC_PLAY_GROUP = API_DOMAIN + "ssc/getSscPlayGroupList.json";//获取彩种和玩法列表
    public static String API_READ_USER_INBOX_NOTICE = API_DOMAIN + "member/readUserInboxNotice.json";//阅读站内信
    public static String API_DEL_USER_INBOX_NOTICE = API_DOMAIN + "member/delUserInboxNotice.json";//删除站内信
    public static String API_ADD_USER_BANK = API_DOMAIN + "member/addUserBank.json";//添加用户银行卡
    public static String API_UPDATE_USER_INFO = API_DOMAIN + "member/updateUserInfo.json";//更新用户信息
    public static String API_GET_KEFU = API_DOMAIN + "webSetting/getKefu.json";//更新用户信息
    public static String API_BET = API_DOMAIN + "ssc/bet.json";//下注
    public static String API_GET_SSC_OPEN_TIME = API_DOMAIN + "ssc/getSscOpenTime.json";//获取彩种开奖时间和玩法赔率
    public static String API_CHEDAN = API_DOMAIN + "ssc/chedan.json";//撤单
    public static String API_SIGOUT = API_DOMAIN + "member/sigout.json";//退出
    public static String API_GET_BET_DETAILS = API_DOMAIN + "ssc/getBetDetails.json";//获取投注列表
    public static String API_GET_CAROUSEL_LIST = API_DOMAIN + "carousel/getCarouselList.json";//获取投注列表
    public static String API_GET_POPUP_NOTICE_LIST = API_DOMAIN + "notice/getPopupNoticeList.json";//获取公告列表
    public static String API_GET_POPUP_NOTICE = API_DOMAIN + "notice/getPopupNotice.json";//获取公告列表
    public static String API_GET_SCROLL_NOTICE_LIST = API_DOMAIN + "notice/getScrollNoticeList.json";//获取公告列表
    public static String API_GET_WEB_INFO = API_DOMAIN + "webSetting/getWebInfo.json";//获取网站信息
    public static String API_GET_HISTORY = API_DOMAIN + "ssc/getHistory.json";//获取彩票开奖历史
    public static String API_DEL_USER_BANK = API_DOMAIN + "member/delUserBank.json";//删除银行卡
    public static String API_UPDATE_ONLINE_INFO = API_DOMAIN + "member/updateOnlineInfo.json";//更新在线信息
    public static String API_GET_SSC_PLAY_JJ_DESCRIPTION = API_DOMAIN + "ssc/getSscPlayJjDescription.json";//获取玩法奖金计算说明
    public static String API_AGENT_GET_USER_LIST = API_DOMAIN + "agent/getUserList.json";//获取代理下属会员列表
    public static String API_AGENT_ADD_USER = API_DOMAIN + "agent/addUser.json";//添加会员
    public static String API_AGENT_GET_TOUZHU = API_DOMAIN + "agent/getTouzhu.json";//获得投注
    public static String API_AGENT_GET_LIUSHUI = API_DOMAIN + "agent/getLiushui.json";//获得流水
    public static String API_AGENT_ENABLE_USER = API_DOMAIN + "agent/enableUser.json";//禁用启用会员
    //    public static String API_AGENT_GET_USER_BALANCE_LIST= API_DOMAIN+"agent/getUserBalanceList.json";//获得会员余额列表
    public static String API_AGENT_GET_AGENT_REPORT = API_DOMAIN + "agent/getAgentReport.json";//获得财务报表
    public static String API_SET_USER_BANK_DEFAULT = API_DOMAIN + "member/setUserBankDefault.json";//获得财务报表
    public static String API_GET_IMAGE_DATA = API_DOMAIN + "image/getImageData.json";//获得图片资源
    public static String API_GET_KHXY = API_DOMAIN + "webSetting/getKhxy.json";//获得开户协议
    public static String API_AGENT_GET_USER_YJ_LIST = API_DOMAIN + "agent/getUserYjList.json";//获得佣金列表
    public static String API_MEMBER_GET_USER_COIN_LIST = API_DOMAIN + "member/getLogUserCoinList.json";//获得资金记录
    public static String API_PROMOTION_GET_BY_ID = API_DOMAIN + "promotion/getById.json";//获得优惠活动详情
    public static String API_MEMBER_SHIWAN_LOGIN = API_DOMAIN + "member/shiwanLogin.json";   // 试玩用户登录
    public static String API_MEMBER_GET_LAYER = API_DOMAIN + "member/getLayer.json";   // 分层权限
    public static String API_MEMBER_COUNT_USER_INBOX_UN_READ = API_DOMAIN + "member/countUserInboxUnRead.json";   // 统计未读站内信
    public static String API_WEB_SETTING_GET_QK_SETTING = API_DOMAIN + "webSetting/getQkSetting.json";   // 取款设置
    public static String API_SSC_GET_SSC_OPEN_TIME_PER = API_DOMAIN + "ssc/getSscOpenTimePer.json";   // 彩种封盘开盘时间
    public static String API_AGENT_GET_SSC_BETS = API_DOMAIN + "agent/getSscBets.json";//获取用户彩票投注记录
    public static String API_SSC_GET_SSC_DATA_HISTORY = API_DOMAIN + "ssc/getSscDataHistory.json";//获取彩票开奖记录
    public static String API_SSC_GET_SSC_PLAY_PL = API_DOMAIN + "ssc/getSscPlayPl.json";//获取彩票赔率
    public static String API_SSC_GET_LEFT_TIME = API_DOMAIN + "ssc/getSscLeftTime.json";//获取彩票剩余时间
    public static String API_SSC_GET_DATA_HISTORY = API_DOMAIN + "ssc/getDataHistory.json";//获取彩票开奖历史记录
    public static String API_SSC_GET_ALL_DATA_HISTORY = API_DOMAIN + "ssc/getAllDataHistory.json";//获取彩票开奖历史记录
    public static String API_AGENT_GET_USER_REPORT = API_DOMAIN + "agent/getUserReport.json";  // 团队报表
    public static String API_REDPACKET_CHOUJIANG = API_DOMAIN + "redpacket/choujiang.json";  // 抢红包
    public static String API_REDPACKET_GET_LIST = API_DOMAIN + "redpacket/getList.json";  // 抢红包
    public static String API_SSC_GET_PLAN_OPEN_DATA_HISTORY = API_DOMAIN + "ssc/getPlanOpenDataHistory.json";  // 获得计划内开奖结果（包含开奖中）
    public static String API_SSC_GET_SSC_OPEN_CODE = API_DOMAIN + "ssc/getSscOpenCode.json";  // 获得开奖结果
    public static String API_SSC_GET_SSC_OPEN_TIME_2 = API_DOMAIN + "ssc/getSscOpenTime2.json";  // 获得开奖结果
    public static String API_SSC_GET_ALL_SSC_OPEN_TIME_2 = API_DOMAIN + "ssc/getAllSscOpenTime2.json";  // 获得开奖结果
    public static String API_SSC_GET_HOT_SSC_DATA_HISTORY_3 = API_DOMAIN + "ssc/getHotSscDataHistory3.json";  // 获取热门开奖3（针对手机web版）
    public static String API_SSC_GET_SSC_PLAY_GROUP = API_DOMAIN + "ssc/getSscPlayGroup.json";  // 获取热门开奖3（针对手机web版）
    public static String API_GET_SYS_QQ = API_DOMAIN + "member/getSystemQQ.json";//获取系统QQ账号
    public static String API_QQ_ZZ = API_DOMAIN + "member/submitQQzz.json";//QQ转账
    public static String API_WEB_SETTING_GET_COMPANY_SHORT_NAME = API_DOMAIN + "webSetting/getCompanyShortName.json";  // 获取公司标志
    public static String API_WEB_SETTING_GET_FENGGE = API_DOMAIN + "webSetting/getFengge.json";  // 获取风格
    public static String API_SSC_GET_SSC_PLAY_PL_GFWF = API_DOMAIN + "ssc/getSscPlayPl_gfwf.json"; // 官方玩法赔率
    public static String API_SSC_GET_LATEST_OPEN_TIME_LIST = API_DOMAIN + "ssc/getLatestOpenTimeList.json"; // 最近最新开奖时间

}
