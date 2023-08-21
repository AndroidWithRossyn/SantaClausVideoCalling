package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.apiInterface;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;

import xmas.callwithsanta.videocallingsanta.RetrofitResponce.DataItem;
import xmas.callwithsanta.videocallingsanta.RetrofitResponce.LocaladsResponce;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewYearGIF_App extends Application {

    private int success;
    public static ArrayList<DataItem> arrAdDataStart = new ArrayList<>();
    private StartAdListener startAdListener;
    public static NewYearGIF_Touch_AppOpenManager valeAppOpenManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize((Context) this, (OnInitializationCompleteListener) new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        valeAppOpenManager = new NewYearGIF_Touch_AppOpenManager(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void FatchStartApps() {
        arrAdDataStart.clear();
        Call<LocaladsResponce> call1 = apiInterface.localads("xmas.callwithsanta.videocallingsanta");

        call1.enqueue(new Callback<LocaladsResponce>() {
            @Override
            public void onResponse(Call<LocaladsResponce> call, Response<LocaladsResponce> response) {
//                Toast.makeText(App.this, response.message(), Toast.LENGTH_LONG).show();
//                Toast.makeText(App.this, "Success", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getData() != null && response.body().getData().size() > 0) {
                        arrAdDataStart.addAll(response.body().getData());
                        if (startAdListener != null)
                            startAdListener.onStartAdLoaded();
                    }
                }

            }

            @Override
            public void onFailure(Call<LocaladsResponce> call, Throwable t) {
                call.cancel();

//                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();

                if (startAdListener != null)
                    startAdListener.onStartAdError();
            }
        });
    }

    // Listener defined earlier
    public interface StartAdListener {

        public void onStartAdError();

        public void onStartAdLoaded();
    }

    public void setStartAdListener(StartAdListener listener) {
        this.startAdListener = listener;
        FatchStartApps();

    }

}
