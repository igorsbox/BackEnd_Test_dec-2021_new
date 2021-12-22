package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketApi {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("products")
    Call<Object> createProduct(@Body Product product);

    @PUT("products")
    Call<Object> updateProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Object> deleteProduct(@Path("id") Long id);
}
