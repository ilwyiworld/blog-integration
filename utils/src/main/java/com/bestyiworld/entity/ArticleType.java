package com.bestyiworld.entity;

/**
 * Created by Administrator on 2017/11/9.
 */
public enum ArticleType {

    /*原创*/
    Original(0),

    /*转载*/
    Copied (1),

    /*翻译*/
    Translator(2);

    private int val;

    ArticleType(int value) {
        val = value;
    }

    public static ArticleType getType(int val) {
        for (ArticleType type : values()) {
            if (type.getValue() == val) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return val;
    }
}
