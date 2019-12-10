package com.cm.config;


public class ConfigBean {
    private static String indexName;

    public static String getIndexName() {
        return indexName;
    }

    public static void setIndexName(String index) {
        indexName = index;
    }
}
