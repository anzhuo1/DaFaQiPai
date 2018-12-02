package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/4/14.
 */

public class CoinOperEnumBean implements Serializable {


    private int result;
    private String description;
    private List<CoinOperEntity> entityList;

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

    public List<CoinOperEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<CoinOperEntity> entityList) {
        this.entityList = entityList;
    }

    public static class CoinOperEntity {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
