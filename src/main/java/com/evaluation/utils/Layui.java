package com.evaluation.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @author: ChenXing
 * @date: 2023/4/23 18:39
 * @Description:
 */
public class Layui extends HashMap<String, Object> {

    public static Layui data(Integer count,List<?> data){
        Layui r = new Layui();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }
}
