package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

public class LeYouLei {


    /**
     * result : 1
     * gameList : [{"name":"经典炸金花","gamecode":"zjhdr","serviceId":"6302","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=zjhdr&language=zh-cn"},{"name":"欢乐德州","gamecode":"iPoker","serviceId":"6666","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=iPoker&language=zh-cn"},{"name":"通比牛牛","gamecode":"tbnn","serviceId":"6504","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=tbnn&language=zh-cn"},{"name":"十三水","gamecode":"sss","serviceId":"6011","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=sss&language=zh-cn"},{"name":"梭哈","gamecode":"ShowHand","serviceId":"6303","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=ShowHand&language=zh-cn"},{"name":"斗地主","gamecode":"ddz","serviceId":"6301","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=ddz&language=zh-cn"},{"name":"抢庄牌九","gamecode":"qzpj","serviceId":"6013","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=qzpj&language=zh-cn"},{"name":"二八杠","gamecode":"ebg","serviceId":"6012","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=ebg&language=zh-cn"},{"name":"极速炸金花","gamecode":"jszjh","serviceId":"6014","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=jszjh&language=zh-cn"},{"name":"三公","gamecode":"csg","serviceId":"6502","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=csg&language=zh-cn"},{"name":"骰宝","gamecode":"tz","serviceId":"6005","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=tz&language=zh-cn"},{"name":"抢庄牛牛","gamecode":"cqznn","serviceId":"6501","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=cqznn&language=zh-cn"},{"name":"百家乐","gamecode":"bjl","serviceId":"6006","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=bjl&language=zh-cn"},{"name":"德州牛仔","gamecode":"TexasCb","serviceId":"6009","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=TexasCb&language=zh-cn"},{"name":"皇家德州","gamecode":"rt","serviceId":"6004","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=rt&language=zh-cn"},{"name":"百人牛牛","gamecode":"HB","serviceId":"6003","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=HB&language=zh-cn"},{"name":"皇家宫殿","gamecode":"RC","serviceId":"6002","gameUrl":"https://h5.ppro.98078.net/game?type=h5&gamecode=RC&language=zh-cn"}]
     */

    private int result;
    private List<GameListBean> gameList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<GameListBean> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameListBean> gameList) {
        this.gameList = gameList;
    }

    public static class GameListBean {
        /**
         * name : 经典炸金花
         * gamecode : zjhdr
         * serviceId : 6302
         * gameUrl : https://h5.ppro.98078.net/game?type=h5&gamecode=zjhdr&language=zh-cn
         */

        private String name;
        private String gamecode;
        private String serviceId;
        private String gameUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGamecode() {
            return gamecode;
        }

        public void setGamecode(String gamecode) {
            this.gamecode = gamecode;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getGameUrl() {
            return gameUrl;
        }

        public void setGameUrl(String gameUrl) {
            this.gameUrl = gameUrl;
        }
    }
}
