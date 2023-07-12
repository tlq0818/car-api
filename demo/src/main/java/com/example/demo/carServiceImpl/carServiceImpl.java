package com.example.demo.carServiceImpl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.demo.carService.carService;
import com.example.demo.mojo.brand;
import com.jayway.jsonpath.JsonPath;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import net.minidev.json.JSONArray;

import static com.example.demo.Const2.dcdUrl.bandUrl;

/**
 * @Description
 * @Author tanlinqing
 * @Date 2023/07/12 0012 下午 3:37
 */

public class carServiceImpl implements carService {
    @Override
    public void savebabnd(){
        String result2= HttpUtil.get(bandUrl, CharsetUtil.CHARSET_UTF_8);
        List<Map<String, Object>> titles = JsonPath.parse(result2).read("$.data.*");
        titles.forEach(e->{
            Object shouzimu = e.get("pinyin");
            JSONArray data =(JSONArray) e.get("data");
            data.forEach(f->{
                JSONObject obj = JSONUtil.parseObj(f);
                brand brand = JSON.parseObject(obj.toString(), brand.class);
                brand.setSzm((String) shouzimu);
                //查询数据库是否存在不存在存入数据库
                //存入数据库
                System.out.println(brand);
            });
        });
    }

    @Override
    public List<brand> getBrand(String letter){
        //return branddao.selectByLetter(letter);
    return null;
    }

    public static void main(String[] args){
        String result2= HttpUtil.get(bandUrl, CharsetUtil.CHARSET_UTF_8);
        List<Map<String, Object>> titles = JsonPath.parse(result2).read("$.data.*");
        titles.forEach(e->{
            Object shouzimu = e.get("pinyin");
            JSONArray data =(JSONArray) e.get("data");
            data.forEach(f->{
                JSONObject obj = JSONUtil.parseObj(f);
                String ob=obj.toString();
                brand brand = JSON.parseObject(ob, brand.class);
                brand.setSzm((String) shouzimu);
                //存入数据库
                System.out.println(brand);
            });
        });
    }
}
