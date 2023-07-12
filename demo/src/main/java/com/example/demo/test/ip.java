package com.example.demo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.lionsoul.ip2region.xdb.Searcher;

/**
 * @Description
 * @Author tanlinqing
 * @Date 2023/4/12 14:22
 */

public class ip {

        public static void main(String[] args) throws IOException{
            // 1、创建 searcher 对象
            String dbPath = "D:\\test\\test\\demo\\src\\main\\resources\\ip2region.xdb";
            Searcher searcher = null;
            try {
                searcher = Searcher.newWithFileOnly(dbPath);
            } catch (IOException e) {
                System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
                return;
            }

            // 2、查询
            try {
                String ip = "222.240.16.183";
                long sTime = System.nanoTime();
                String region = searcher.search(ip);
                String[] split = region.split("\\|");
                HashMap<String, String> map = new HashMap<>();
                map.put("国家",split[0]);
                map.put("大区",split[1]);
                map.put("省",split[2]);
                map.put("市",split[3]);
                map.put("运行商",split[4]);
                System.out.println(map.toString());
                long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
                System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            } catch (Exception e) {
                System.out.printf("发生了异常");
            }

            // 3、关闭资源
            searcher.close();

            // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
        }
    }


