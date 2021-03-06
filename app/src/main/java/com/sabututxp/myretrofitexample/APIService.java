package com.sabututxp.myretrofitexample;


import com.sabututxp.myretrofitexample.model.LoginResponse;
import com.sabututxp.myretrofitexample.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by s on 9/22/17.
 */

public interface APIService {
    //The register call
    @FormUrlEncoded
    @POST("registration")
    Call<User> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone_no") String phoneNumber,
            @Field("password_confirmation") String password_confirmation);
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("username") String email,
            @Field("password") String password
    );
}
