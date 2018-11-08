package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shn on 2017/4/26.
 */
public class DoGetPromotion {

    /**
     * result : 1
     * description : null
     * promotionList : [{"id":2,"content":"<p>美丽的花儿香，快来望一望~~<\/p>","name":"优惠活动（测试2）","bigImageId":75,"smallImageId":56,"createTime":1482623245000,"updateTime":null,"weight":null,"bigImageData":null,"smallImageData":null,"startTime":1482595200000,"endTime":1483113600000,"enable":null},{"id":1,"content":"<p>内容测试<\/p>","name":"优惠活动（测试）","bigImageId":1,"smallImageId":2,"createTime":1472773996000,"updateTime":null,"weight":null,"bigImageData":null,"smallImageData":null,"startTime":null,"endTime":null,"enable":null}]
     */

    private int result;
    private String description;
    /**
     * id : 2
     * content : <p>美丽的花儿香，快来望一望~~</p>
     * name : 优惠活动（测试2）
     * bigImageId : 75
     * smallImageId : 56
     * createTime : 1482623245000
     * updateTime : null
     * weight : null
     * bigImageData : null
     * smallImageData : null
     * startTime : 1482595200000
     * endTime : 1483113600000
     * enable : null
     */

    private List<PromotionListBean> promotionList;

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

    public List<PromotionListBean> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<PromotionListBean> promotionList) {
        this.promotionList = promotionList;
    }

    public static class PromotionListBean implements Serializable {
        private int id;
        private String content;
        private String name;
        private int bigImageId;
        private int smallImageId;
        private long createTime;
        private String updateTime;
        private String weight;
        private String bigImageData;
        private String smallImageData;
        private long startTime;
        private long endTime;
        private String enable;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        private boolean isShow;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBigImageId() {
            return bigImageId;
        }

        public void setBigImageId(int bigImageId) {
            this.bigImageId = bigImageId;
        }

        public int getSmallImageId() {
            return smallImageId;
        }

        public void setSmallImageId(int smallImageId) {
            this.smallImageId = smallImageId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getBigImageData() {
            return bigImageData;
        }

        public void setBigImageData(String bigImageData) {
            this.bigImageData = bigImageData;
        }

        public String getSmallImageData() {
            return smallImageData;
        }

        public void setSmallImageData(String smallImageData) {
            this.smallImageData = smallImageData;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        @Override
        public String toString() {
            return "PromotionListBean{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", name='" + name + '\'' +
                    ", bigImageId=" + bigImageId +
                    ", smallImageId=" + smallImageId +
                    ", createTime=" + createTime +
                    ", updateTime='" + updateTime + '\'' +
                    ", weight='" + weight + '\'' +
                    ", bigImageData='" + bigImageData + '\'' +
                    ", smallImageData='" + smallImageData + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", enable='" + enable + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DoGetPromotion{" +
                "result=" + result +
                ", description='" + description + '\'' +
                ", promotionList=" + promotionList +
                '}';
    }
}
