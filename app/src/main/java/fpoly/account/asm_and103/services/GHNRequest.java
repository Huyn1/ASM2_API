package fpoly.account.asm_and103.services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GHNRequest {
    private static final String SHOP_ID = "195521";
    private static final String TOKEN_GHN = "bf778801-aeb3-11ef-9083-dadc35c0870d";

    private ApiServices apiService;

    public GHNRequest() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("ShopId", SHOP_ID)
                        .addHeader("Token", TOKEN_GHN)
                        .build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev-online-gateway.ghn.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        apiService = retrofit.create(ApiServices.class);
    }

    public ApiServices getApiService() {
        return apiService;
    }
}
