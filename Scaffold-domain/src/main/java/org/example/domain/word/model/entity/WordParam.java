package com.wxb.uplus.upluscommon.utils;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoupengcheng on 2019/10/30 .
 * Desc:  ....
 * word替换数据对象
 *
 * @author zhoupengcheng
 */
public class WordParam {

    /**
     * 模板文件流
     */
    private InputStream modelStream;
    /**
     * 替换word字段集合
     */
    private Map<String,Object> map;

    /**
     * 需要添加图片的key
     */
    private List<String> addPic = new ArrayList<>();
    /**
     * 模板中图片宽高属性
     */
    private Map<String,Integer> picAttribute;

    /**
     *需要增加行的集合
     */
    private Map<String,List<Map<String,String>>> annualActivitiesMap;

    /**
     * 需要合成行的集合
     */
    private List<Map<String,Integer>> megresList = new ArrayList<>();

    /**
     * 使用模板
     */
    private int model = 1;

    public InputStream getModelStream() {
        return modelStream;
    }

    public void setModelStream(InputStream modelStream) {
        this.modelStream = modelStream;
    }

    public List<String> getAddPic() {
        return addPic;
    }

    public void setAddPic(List<String> addPic) {
        this.addPic = addPic;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public List<Map<String, Integer>> getMegresList() {
        return megresList;
    }

    public void setMegresList(List<Map<String, Integer>> megresList) {
        this.megresList = megresList;
    }
    public Map<String, Integer> getPicAttribute() {
        Map<String, Integer> returnMap = new HashMap();
        returnMap.put("wordHeight",120);
        returnMap.put("wordWidth",120);
        return returnMap;
    }

    public void setPicAttribute(Map<String, Integer> picAttribute) {
        this.picAttribute = picAttribute;
    }
    public Map<String, List<Map<String, String>>> getAnnualActivitiesMap() {

        return this.annualActivitiesMap;
    }

    public void setAnnualActivitiesMap(Map<String, List<Map<String, String>>> annualActivitiesMap) {
        this.annualActivitiesMap = annualActivitiesMap;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

}
