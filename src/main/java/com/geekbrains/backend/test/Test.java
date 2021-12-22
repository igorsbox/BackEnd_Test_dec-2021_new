package com.geekbrains.backend.test;

import com.geekbrains.backend.test.miniMarket.MiniMarketService;
import com.geekbrains.backend.test.miniMarket.model.ProductResponse;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        MiniMarketService service = new MiniMarketService();
        ProductResponse product = service.getProduct(89L);
        System.out.println(product);
    }
}

