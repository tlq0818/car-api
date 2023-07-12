package com.example.demo.carService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mojo.brand;

/**
 * @Description
 * @Author tanlinqing
 * @Date 2023/07/12 0012 下午 3:36
 */
@Service
public interface carService {
   void savebabnd();
   List<brand> getBrand(String letter);
}
