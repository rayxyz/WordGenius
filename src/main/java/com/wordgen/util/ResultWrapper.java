/*
 * Copyright (c) 2012-2014, EpicSaaS Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package com.wordgen.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果包装类。将结果包装成Map。
 * 
 * @author RayWang
 * @date 2016年2月5日
 */
public class ResultWrapper {
    public enum ResultKeyType {
        SUCCESS("success"), DATA("data"), MSG("msg"), CODE("code");
        private String type;

        private ResultKeyType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    // 存储返回数据的map
    private Map<ResultKeyType, Object> resultData = null;

    public ResultWrapper() {
        resultData = new HashMap<ResultKeyType, Object>();
    }

    public static ResultWrapper getInstance() {
        return new ResultWrapper();
    }

    public <T> void put(ResultKeyType keyType, T data) {
        this.resultData.put(keyType, data);
    }

    public Map<ResultKeyType, Object> get() {
        Map<ResultKeyType, Object> map = new HashMap<ResultKeyType, Object>();
        map.putAll(resultData); // 深复制resultData
        this.resultData.clear(); // 清空数据
        return map;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}
