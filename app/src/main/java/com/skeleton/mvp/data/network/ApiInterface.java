package com.skeleton.mvp.data.network;

import com.skeleton.mvp.data.model.CommonResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Developer: Click Labs
 * <p>
 * The API interface for your application
 */
public interface ApiInterface {

    //todo Declare your API endpoints here

    /**
     * Dummy sign in endpoint
     *
     * @param map the map of params to go along with reqquest
     * @return parsed common response object
     */
    @FormUrlEncoded
    @POST("/signIn")
    Call<CommonResponse> signIn(@FieldMap Map<String, String> map);


    /**
     * @param map map
     * @return return the response
     */
    @FormUrlEncoded
    @POST("/signUp")
    Call<CommonResponse> signUp(@FieldMap Map<String, String> map);


    /**
     * @param map map
     * @return return the response
     */
    @FormUrlEncoded
    @POST("/forgotPassword")
    Call<CommonResponse> forgotPassword(@FieldMap Map<String, String> map);


}
