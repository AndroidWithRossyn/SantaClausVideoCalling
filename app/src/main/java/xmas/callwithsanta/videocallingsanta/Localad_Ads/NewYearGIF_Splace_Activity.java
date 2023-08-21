package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import xmas.callwithsanta.videocallingsanta.MainActivity;
import xmas.callwithsanta.videocallingsanta.R;
import xmas.callwithsanta.videocallingsanta.RetrofitResponce.AdListResponse;
import xmas.callwithsanta.videocallingsanta.RetrofitResponce.DataItem;
import xmas.callwithsanta.videocallingsanta.retrofit.APIClient;
import xmas.callwithsanta.videocallingsanta.retrofit.RetrofitInterface;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewYearGIF_Splace_Activity extends Activity {

    private Context mContext;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    public static ArrayList<DataItem> adsdata = new ArrayList<>();
    private int success;
    private Timer tm;
    boolean isTimerStarted;

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    public static final String ACTION_CLOSE = "ACTION_CLOSE";
    private FirstReceiver firstReceiver;

    private InterstitialAd mInterstitialAd;

    public static RetrofitInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.thop_activity_splace__screen);

        apiInterface = APIClient.getClient().create(RetrofitInterface.class);

        mContext = this;
        activity = this;
        isTimerStarted = false;

        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                if (checkPermission()) {
                    fatchHeliNative();
                    starttimer();
                } else {
                    requestPermission();
                }
            }
        } catch (Exception e) {
        }

        try {
            IntentFilter filter = new IntentFilter(ACTION_CLOSE);
            firstReceiver = new FirstReceiver();
            registerReceiver(firstReceiver, filter);

            String INSTALL_PREF = "install_pref_vd";
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            if (!sharedPreferences.getBoolean(INSTALL_PREF, false)) {
                updatecounter();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(INSTALL_PREF, true);
                editor.apply();
            }

        } catch (Exception e) {
        }
    }

    public void starttimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isTimerStarted = true;
                if (adsdata != null && adsdata.size() > 0) {
                    if (adsdata.get(0).getTaptostart().equals("on")) {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
            }
        };
        tm = new Timer();
        tm.schedule(task, 2500);
    }

    public static int initalize_appopenad = 0;

    private void fatchHeliNative() {
        Call<AdListResponse> call1 = apiInterface.getadsdetail("xmas.callwithsanta.videocallingsanta");

        call1.enqueue(new Callback<AdListResponse>() {
            @Override
            public void onResponse(Call<AdListResponse> call, Response<AdListResponse> response) {
//                Toast.makeText(footballscore_Splace_Activity.this, response.message(), Toast.LENGTH_LONG).show();
//                Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getData() != null && response.body().getData().size() > 0) {

                        DataItem item = response.body().getData().get(0);


                        adsdata.add(item);

                        if (adsdata.get(0).getCheckAdSplash().equals("admob")) {
                            if (tm != null) {
                                tm.cancel();
                                tm.purge();
                            }
                            if (!isTimerStarted)
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(NewYearGIF_Splace_Activity.this);
                                    Admob_callSplashAd();
                                }
                        } else if (adsdata.get(0).getCheckAdSplash().equals("appopen")) {
                            if (tm != null) {
                                tm.cancel();
                                tm.purge();
                            }
                            if (!isTimerStarted)
                                fetchAd();
                        } else if (adsdata.get(0).getCheckAdSplash().equals("off")) {
                            if (adsdata.get(0).getTaptostart().equals("on")) {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            if (adsdata.get(0).getTaptostart().equals("on")) {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AdListResponse> call, Throwable t) {
                call.cancel();

//                Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                if (adsdata != null && adsdata.size() > 0) {
                    if (adsdata.get(0).getTaptostart().equals("on")) {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    public void updatecounter() {
        Call<Object> call1 = apiInterface.updatecounter("xmas.callwithsanta.videocallingsanta");

        call1.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                Toast.makeText(NewYearGIF_Splace_Activity.this, response.message(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful() && response.body() != null) {

                    String data = new Gson().toJson(response.body());

                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        success = jsonObject.getInt("success");
                        Toast.makeText(getApplicationContext(), "updatecounter = " + success, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                call.cancel();
            }
        });
    }

    class FirstReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FirstReceiver", "FirstReceiver");
            if (intent.getAction().equals(ACTION_CLOSE)) {
                NewYearGIF_Splace_Activity.this.finish();
            }
        }
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null;
    }

    public void fetchAd() {
        if (!isAdAvailable()) {
            fullScreenContentCallback = new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    if (adsdata.get(0).getTaptostart().equals("on")) {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    }
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());
                    Admob_callSplashAd();
                }

                public void onAdShowedFullScreenContent() {
                    Log.e("TAG", "onAdDismissedFullScreenContent:====> show ");
                }
            };
            getAdsLoad();
        }
    }

    private void getAdsLoad() {
        this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            public void onAdLoaded(final AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                appOpenAd.show(NewYearGIF_Splace_Activity.this);
                appOpenAd.setFullScreenContentCallback(NewYearGIF_Splace_Activity.this.fullScreenContentCallback);
                AppOpenAd unused = NewYearGIF_Splace_Activity.this.appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (adsdata.get(0).getTaptostart().equals("on")) {
                    Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load((Context) this, adsdata.get(0).getAppopenadId(), getAdRequest(), 1, this.loadCallback);
    }


    private void Admob_callSplashAd() {
        try {

//            AdRequest adRequest = new AdRequest.Builder().build();
//            MobileAds.initialize(NewYearGIF_Splace_Activity.this, adsdata.get(0).getAdmobSplashInterid());
            InterstitialAd.load(NewYearGIF_Splace_Activity.this, adsdata.get(0).getAdmobSplashInterid(), new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
//                            mInterstitialAd = interstitialAd;
//                            mInterstitialAd.show(mContext);
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(NewYearGIF_Splace_Activity.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
//                            Log.i(TAG, loadAdError.getMessage());
                            if (adsdata.get(0).getTaptostart().equals("on")) {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            }
                        }
                    });

            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                    if (adsdata.get(0).getTaptostart().equals("on")) {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d("TAG", "The ad failed to show.");
                    if (adsdata.get(0).getTaptostart().equals("on")) {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, NewYearGIF_StartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(NewYearGIF_Splace_Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });

        } catch (Exception e) {
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED || result1 == PackageManager.PERMISSION_GRANTED|| result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    fatchHeliNative();
                    starttimer();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isTimerStarted = false;
    }
}