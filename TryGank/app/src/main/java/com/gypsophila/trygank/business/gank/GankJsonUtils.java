package com.gypsophila.trygank.business.gank;

import com.google.gson.Gson;
import com.gypsophila.trygank.business.gank.model.GankPlusBean;
import com.gypsophila.trygank.business.gank.model.SearchPlusBean;

/**
 * Description :
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/10/4
 */
public class GankJsonUtils {

    public static SearchPlusBean readJsonSearchPlusBean(String strJson) {
        Gson gson = new Gson();
        SearchPlusBean bean = gson.fromJson(strJson, SearchPlusBean.class);
        return bean;
    }

    public static GankPlusBean readJsonGankPlusBean(String strJson) {
        Gson gson = new Gson();
        GankPlusBean bean = gson.fromJson(strJson, GankPlusBean.class);
        return bean;
    }

}