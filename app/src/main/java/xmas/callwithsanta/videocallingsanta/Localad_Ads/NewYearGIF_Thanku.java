package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;

import xmas.callwithsanta.videocallingsanta.R;

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

public class NewYearGIF_Thanku extends AppCompatActivity {

    ImageView yes, no, rateus;
    CardView q_native, q_native_banner;

    private Dialog adprogress;
    private int admob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thop_activity_thanku);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        rateus = findViewById(R.id.rateus);
        q_native = findViewById(R.id.q_native);
        q_native_banner = (CardView) findViewById(R.id.q_native_banner);

        //todo Ad Loading Dialog
        adprogress = new Dialog(NewYearGIF_Thanku.this, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.z1_ads_loading_dialog_inter);


        if (adsdata != null && adsdata.size() > 0) {
            switch (adsdata.get(0).getCheckAdNative()) {
                case "admob":
                    admob_small_native();
                    break;
                case "qureka":
                    qureka();
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

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adsdata != null && adsdata.size() > 0) {
                    admob = 1;
                    if (adsdata.get(0).getCheckAdFinalExit().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckAdFinalExit().equals("off")) {
                        Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                        startActivity(i);
                    }
                } else {
                    Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                    startActivity(i);
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adsdata != null && adsdata.size() > 0) {
                    admob = 2;
                    if (adsdata.get(0).getCheckAdFinalExit().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckAdFinalExit().equals("off")) {
                        Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // TODO: 08-Oct-20  admob Native
    @SuppressLint("MissingPermission")
    private void admob_small_native() {
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


    public void qureka() {
        q_native_banner.setVisibility(View.VISIBLE);
        q_native_banner.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(NewYearGIF_Thanku.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
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
                customTabsIntent.launchUrl(NewYearGIF_Thanku.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    // TODO: 08-Oct-20  Admob Inter
    private InterstitialAd mInterstitialAd;

    public void admob_inter() {

        InterstitialAd.load(this, adsdata.get(0).getAdmobInterid(), new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(NewYearGIF_Thanku.this);
                        }
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                adprogress.dismiss();
                                if (admob == 1) {
                                    Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                                    startActivity(i);
                                }
                                if (admob == 2) {
                                    Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                adprogress.dismiss();
                                if (admob == 1) {
                                    Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                                    startActivity(i);
                                }
                                if (admob == 2) {
                                    Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                                    startActivity(intent);
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
                        if (admob == 1) {
                            Intent i = new Intent(NewYearGIF_Thanku.this, NewYearGIF_ExitActivity.class);
                            startActivity(i);
                        }
                        if (admob == 2) {
                            Intent intent = new Intent(NewYearGIF_Thanku.this, NewYearGIF_StartActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {

    }
}