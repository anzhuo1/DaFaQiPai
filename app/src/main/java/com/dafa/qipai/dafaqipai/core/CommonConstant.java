package com.dafa.qipai.dafaqipai.core;


public class CommonConstant {

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 1;

    /**
     * 未登录状态码
     */
    public static final int NOT_LOGIN_CODE = 108;

    /**
     * 失败状态码
     */
    public static final int ERROR_CODE = -1;

    /**
     * 未知状态码
     */
    public static final int UNKNOW_ERROR_CODE = 0;


    public static final Long CQSSCID = 1L;
    public static final Long TJSSCID = 2L;
    public static final Long XJSSCID = 3L;
    public static final Long TCPL3 = 4L;
    public static final Long FC3D = 5L;
    public static final Long LHC = 6L;
    public static final Long XY28 = 7L;
    public static final Long BJKL8 = 8L;
    public static final Long BJPK10 = 9L;
    public static final Long XYNC = 10L;
    public static final Long KLSF = 11L;
    public static final Long SSQ = 12L;
    public static final Long SFSSC = 13L;
    public static final Long XYFT = 14L;
    public static final Long FFSSCID = 15L;
    public static final Long LFSSC = 16L;
    public static final Long WFSSC = 17L;
    public static final Long JSK3 = 18L;
    public static final Long AHK3 = 20L;
    public static final Long JSPK10 = 23L;
    public static final Long SYXW = 24L;
    public static final Long SYXWSD = 25L;//lybdtt -- 添加山东11选5的groupid(long 类型)
    public static final Long SYXWSH= 26L;//lybdtt -- 添加上海11选5的groupid(long 类型)
    public static final Long HBK3 = 19L;

    public static final Long FFK3 = 27L;


    public static final long[] HIEGHTS = new long[]{BJPK10, JSPK10, XYFT,
            SFSSC, FFSSCID, LFSSC, JSK3,
            CQSSCID, TJSSCID, XJSSCID,
            JSK3, AHK3, HBK3,
            XY28, BJKL8, XYNC,
            KLSF};

    public static final long[] LOWS = new long[]{LHC, FC3D, TCPL3};

    public static final long[] PK10S = new long[]{BJPK10, JSPK10};

    public static final long[] SSCS = new long[]{SFSSC, FFSSCID, LFSSC,
            CQSSCID, TJSSCID, XJSSCID
    };

    public static final long[] K3 = new long[]{AHK3, HBK3};
    public static final long[] KL10= new long[]{10, 11};
    public static final int SSCTimeListsize = 14;

    public static final int CQSSCID_j = 1;
    public static final int TJSSCID_j = 2;
    public static final int XJSSCID_j = 3;
    public static final int TCPL3_j = 4;
    public static final int FC3D_j = 5;
    public static final int LHC_j = 6;
    public static final int XY28_j = 7;
    public static final int BJKL8_j = 8;
    public static final int BJPK10_j = 9;
    public static final int XYNC_j = 10;
    public static final int KLSF_j = 11;
    public static final int SSQ_j = 12;
    public static final int SFSSC_j = 13;
    public static final int XYFT_j = 14;
    public static final int FFSSCID_j = 15;
    public static final int LFSSC_j = 16;
    public static final int WFSSC_j = 17;
    public static final int JSK3_j = 18;
    public static final int AHK3_j = 20;
    public static final int JSPK10_j = 23;
    public static final int SYXW_j = 24;
    public static final int SDSYXW_j = 25;//lybdtt -- 添加山东11选5的groupid(int 类型)
    public static final int SHSYXW_j = 26;//lybdtt -- 添加上海11选5的groupid（int 类型）
    public static final int HBK3_j = 19;
    public static final int FFK3_j = 27;

    public static final int ALL = 0;


    public static final int ALLTIME = 0;
    public static final int HIEGHTTIME = 1;
    public static final int LOWTIME = 2;
    public static final int SSCTIME = 3;
    public static final int PK10TIME = 4;
    public static final int HIEGHT = 1;
    public static final int LOW = 2;

    public static final  String KEY_CQSSC="cqssc";
    public static final  String KEY_TJSSC="tjssc";
    public static final  String KEY_XJSSC="xjssc";
    public static final  String KEY_FFSSC="ffssc";
    public static final  String KEY_LFSSC="lfssc";
    public static final  String KEY_SFSSC="sfssc";
    public static final  String KEY_JSPK10="jspk10";
    public static final  String KEY_BJPK10="bjpk10";
    public static final  String KEY_XYFT="xyft";
    public static final  String KEY_LHC="lhc";
    public static final  String KEY_FC3D="fc3d";
    public static final  String KEY_TCPL3="tcpl3";
    public static final  String KEY_XYNC="xync";
    public static final  String KEY_KLSF="klsf";
    public static final  String KEY_HBK3="hbk3";
    public static final  String KEY_AHK3="ahk3";
    public static final  String KEY_BJ28="bj28";
    public static final  String KEY_BJKL8="bjkl8";
    public static final  String KEY_SSQ="ssq";
    public static final  String KEY_SYXW="syxw";
    public static final  String FFKS="ffk3";
}
