package xmas.callwithsanta.videocallingsanta;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_FirstActivity;
import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity;
import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_functions;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public boolean flag = false;
    Handler handler = new Handler();
    /* access modifiers changed from: private */

    private ImageView llprankchat;
    private ImageView llsrat;
    int admobinter;
    private Dialog adprogress;
    private View tempview;

    CardView q_native, q_native_banner;


    public void onResume() {
        NewYearGIF_functions.callAutoQureka(this);
        super.onResume();
    }

    public void onPause() {
        NewYearGIF_functions.destroyThread();
        super.onPause();
    }

    // TODO: 11-09-2020  Admob Native Ads
    private void admob_native_banner() {
        try {

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            findViewById(R.id.admobsmallnative).setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            TemplateView template = findViewById(R.id.admobsmallnative);
                            template.setStyles(styles);
                            template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());

        } catch (Exception e) {
        }
    }

    private void Q_Native_banner() {
        q_native_banner.setVisibility(View.VISIBLE);
        q_native_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }


    // TODO: 08-Oct-20  admob Native
    private void admob_native() {
        try {
            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, NewYearGIF_Splace_Activity.adsdata.get(0).getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            findViewById(R.id.admobmediumnative).setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            TemplateView template = findViewById(R.id.admobmediumnative);
                            template.setStyles(styles);
                            template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
        }
    }

    private void Q_Native() {
        q_native.setVisibility(View.VISIBLE);
        q_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    private InterstitialAd mInterstitialAd;

    private void admob_inter() {
        try {
            InterstitialAd.load(this, adsdata.get(0).getAdmobInterid(), new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(MainActivity.this);
                            }

                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    Log.d("TAG", "The ad was dismissed.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                        MainActivity mainActivity = MainActivity.this;
                                        mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                                    }
                                    if (admobinter == 2) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                        MainActivity mainActivity = MainActivity.this;
                                        mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                                    }
                                    if (admobinter == 3) {
                                        startActivity(new Intent(MainActivity.this, NewYearGIF_FirstActivity.class));
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    Log.d("TAG", "The ad failed to show.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                        MainActivity mainActivity = MainActivity.this;
                                        mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                                    }
                                    if (admobinter == 2) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                        MainActivity mainActivity = MainActivity.this;
                                        mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                                    }
                                    if (admobinter == 3) {
                                        startActivity(new Intent(MainActivity.this, NewYearGIF_FirstActivity.class));
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
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
//                            Log.i(TAG, loadAdError.getMessage());
                            adprogress.dismiss();
                            if (admobinter == 1) {
                                tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                MainActivity mainActivity = MainActivity.this;
                                mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                            }
                            if (admobinter == 2) {
                                tempview.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                                MainActivity mainActivity = MainActivity.this;
                                mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                            }
                            if (admobinter == 3) {
                                startActivity(new Intent(MainActivity.this, NewYearGIF_FirstActivity.class));
                            }

                        }
                    });
        } catch (Exception e) {

        }
    }

    /* access modifiers changed from: private */

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);

        q_native = (CardView) findViewById(R.id.q_native);
        q_native_banner = (CardView) findViewById(R.id.q_native_banner);

        NewYearGIF_functions.iplscr_flag = true;

        //todo Ad Loading Dialog
        adprogress = new Dialog(MainActivity.this, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.z1_ads_loading_dialog_inter);

        if (adsdata != null && adsdata.size() > 0) {

            // TODO: 11-09-2020  Native Ads
            switch (adsdata.get(0).getCheckAdNativeBanner()) {
                case "admob":
                    admob_native_banner();
                    break;
                case "qureka":
                    Q_Native_banner();
                    break;
            }


            // TODO: 11-09-2020  Native Ads
            switch (adsdata.get(0).getCheckAdNative()) {
                case "admob":
                    admob_native();
                    break;
                case "qureka":
                    Q_Native();
                    break;
            }
        }

        bind();

    }

    private void bind() {
        this.llsrat = (ImageView) findViewById(R.id.llsrat);
        this.llsrat.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                tempview = view;

                if (adsdata != null && adsdata.size() > 0) {
                    admobinter = 1;
                    if (adsdata.get(0).getCheckadVideocallactivityInter().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckadVideocallactivityInter().equals("off")) {
                        view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                    } else {
                        view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                    }
                } else {
                    view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent(mainActivity, StratestActivity.class));
                }


            }
        });
        this.llprankchat = (ImageView) findViewById(R.id.llprankchat);
        this.llprankchat.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                tempview = view;
                if (adsdata != null && adsdata.size() > 0) {
                    admobinter = 2;
                    if (adsdata.get(0).getCheckadChatactivityInter().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckadChatactivityInter().equals("off")) {
                        view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                    } else {
                        view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                    }
                } else {
                    view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.viewpush));
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent(mainActivity, ChatActivity.class));
                }

            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (adsdata != null && adsdata.size() > 0) {
            admobinter = 3;
            if (adsdata.get(0).getTaptostart().equals("on")) {
                switch (adsdata.get(0).getCheckadMainbackpressInter()) {
                    case "admob":
                        adprogress.show();
                        admob_inter();
                        break;
                    case "off": {
                        startActivity(new Intent(MainActivity.this, NewYearGIF_FirstActivity.class));
                        break;
                    }
                    default: {
                        startActivity(new Intent(MainActivity.this, NewYearGIF_FirstActivity.class));
                        break;
                    }
                }
            } else {
                if (doubleBackToExitPressedOnce) {
                    finishAffinity();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        } else {
            if (doubleBackToExitPressedOnce) {
                finishAffinity();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
