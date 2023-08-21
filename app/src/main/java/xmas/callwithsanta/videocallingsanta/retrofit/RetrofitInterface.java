package xmas.callwithsanta.videocallingsanta.retrofit;

import xmas.callwithsanta.videocallingsanta.RetrofitResponce.AdListResponse;
import xmas.callwithsanta.videocallingsanta.RetrofitResponce.LocaladsResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface RetrofitInterface {


    @POST("adservice/get_Aarohi.php")
    @FormUrlEncoded
    Call<AdListResponse> getadsdetail(@Field("packagename") String packagename);

    @POST("localadservice/updatedownloadcount.php")
    @FormUrlEncoded
    Call<Object> updatecounter(@Field("packagename") String packagename);

    @POST("localadservice/get_ShanTech_LocalAds.php")
    @FormUrlEncoded
    Call<LocaladsResponce> localads(@Field("packagename") String packagename);


}
