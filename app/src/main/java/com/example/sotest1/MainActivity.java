package com.example.sotest1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {


    private final OkHttpClient client = new OkHttpClient();

    public void GetFromServer(String _serviceUrl) throws Exception {
        String baseUrl = "https://dummyjson.com";
        String absoluteUrl = baseUrl + "/" + _serviceUrl;
        Request request = new Request.Builder()
                .url(absoluteUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                //Logging.Error("GetFromServer is Failure. Endpoint : " + absoluteUrl + " Error Message : " + e.getMessage());
                e.printStackTrace();
            }
            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (response.isSuccessful()) {
                        String json = response.body().string();

                        Gson gson = new Gson();
                        TestModel.ProductList productList = gson.fromJson(json, TestModel.ProductList.class);

                        List<TestModel.Product> products = productList.getProducts();
                        for (TestModel.Product product : products) {
                            System.out.println("Product Title: " + product.getTitle());
                            System.out.println("Description: " + product.getDescription());

                        }
                    }
                    else {
                        throw new IOException("Unexpected code " + response);
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            GetFromServer("products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}