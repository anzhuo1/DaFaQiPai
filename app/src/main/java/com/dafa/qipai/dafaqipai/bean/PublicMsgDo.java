package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2017/4/5.
 */

public class PublicMsgDo implements Serializable {


    /**
     * result : 1
     * description : null
     * webNoticeList : [{"pk":"95e4d2aa67e94476b7fe0bdbfbaad82d","title":"戎马歌","content":"戎马歌戎马歌戎马歌","createTime":1482622082000},{"pk":"70d8e1bb0f0443709b99db6a0463e81d","title":"测试公告3","content":"测试公告3","createTime":1482129795000},{"pk":"860a2ae78bba4a8a9778ba07ea83f8ec","title":"重要公告","content":"【★重要公告★】敬请玩家留意：目前本站已推出【手机版支付宝】入款，广大玩家可进行存款操作了哦，望玩家互相转告通知，非常感谢您的支持与配合，祝您生活愉快！！","createTime":1482129785000}]
     */

    private int result;
    private String description;
    private List<WebNoticeListBean> webNoticeList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WebNoticeListBean> getWebNoticeList() {
        return webNoticeList;
    }

    public void setWebNoticeList(List<WebNoticeListBean> webNoticeList) {
        this.webNoticeList = webNoticeList;
    }

    public static class WebNoticeListBean {
        /**
         * pk : 95e4d2aa67e94476b7fe0bdbfbaad82d
         * title : 戎马歌
         * content : 戎马歌戎马歌戎马歌
         * createTime : 1482622082000
         */

        private String pk;
        private String title;
        private String content;
        private long createTime;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WebNoticeListBean that = (WebNoticeListBean) o;

            if (createTime != that.createTime) return false;
            if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
            if (title != null ? !title.equals(that.title) : that.title != null) return false;
            return content != null ? content.equals(that.content) : that.content == null;

        }

        @Override
        public int hashCode() {
            int result = pk != null ? pk.hashCode() : 0;
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + (content != null ? content.hashCode() : 0);
            result = 31 * result + (int) (createTime ^ (createTime >>> 32));
            return result;
        }
    }
}
