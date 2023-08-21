package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_App.arrAdDataStart;
import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.squareup.picasso.Picasso;

import net.khirr.android.privacypolicy.PrivacyPolicyDialog;

import xmas.callwithsanta.videocallingsanta.R;

public class NewYearGIF_StartActivity extends AppCompatActivity {
    private Context mContext;
    private Activity activity;
    ImageView btn_getStarted;
    public static final String ACTION_CLOSE = "ACTION_CLOSE";
    private FirstReceiver firstReceiver;
    RelativeLayout Liner_Localad;
    //    public static ArrayList<DataItem> arrAdDataMain = new ArrayList<>();
    private AdViewAdapter_Start adViewAdapter;
    RecyclerView ad_recycle_view;
    int success = 0;
    ImageView acion_qureka;

    private ImageView helinative;
    LinearLayout Liner_helinativAd;

    CardView q_native, q_banner2;

    private Dialog adprogress;

    private InterstitialAd mInterstitialAd;

    private FrameLayout adContainerView;
    private AdView adView;
    RelativeLayout ad_space;

    public void admob_inter() {
        InterstitialAd.load(this, adsdata.get(0).getAdmobInterid(), new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(NewYearGIF_StartActivity.this);
                        }

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                adprogress.dismiss();
                                Intent i = new Intent(NewYearGIF_StartActivity.this, NewYearGIF_TapToStartActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                adprogress.dismiss();
                                Intent i = new Intent(NewYearGIF_StartActivity.this, NewYearGIF_TapToStartActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
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

                        mInterstitialAd = null;
                        adprogress.dismiss();
                        Intent i = new Intent(NewYearGIF_StartActivity.this, NewYearGIF_TapToStartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                });
    }

    // TODO: 16-09-2020  ADMOB bANNER ads
    public void admob_loadbanner() {
        try {
            adView = new AdView(this);
            adView.setAdUnitId("ca-app-pub-9939518381636264~1092563270");
            adContainerView.removeAllViews();
            adContainerView.addView(adView);
            AdSize adSize = getAdSize();
            adView.setAdSize(adSize);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);

        } catch (Exception e) {
        }
    }

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    private void Q_Banner() {
        q_banner2.setVisibility(View.VISIBLE);
        q_banner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(NewYearGIF_StartActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    // TODO: 08-Oct-20  admob Native
    private void admob_native() {
        try {

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
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

//            MobileAds.initialize(this, adsdata.get(0).getAdmobAppid());
            /*AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            findViewById(R.id.admobmediumnative).setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            TemplateView template = findViewById(R.id.admobmediumnative);
                            template.setStyles(styles);
                            template.setNativeAd(unifiedNativeAd);
                        }
                    })
                    .build();
            adLoader.loadAd(new AdRequest.Builder().build());*/
        } catch (Exception e) {
        }
    }

    private void Q_Native() {
        q_native.setVisibility(View.VISIBLE);
        q_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(NewYearGIF_StartActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thop_activity_start);

        mContext = this;
        activity = this;
        acion_qureka = (ImageView) findViewById(R.id.acion_qureka);
        q_native = (CardView) findViewById(R.id.q_native);
        q_banner2 = (CardView) findViewById(R.id.q_banner2);
        helinative = (ImageView) findViewById(R.id.heli_native);
        adContainerView = findViewById(R.id.ad_view_container);
        ad_space = findViewById(R.id.ad_space);

        Liner_helinativAd = (LinearLayout) findViewById(R.id.nativad_hels);
        Liner_helinativAd.setVisibility(View.GONE);

        //todo Ad Loading Dialog
        adprogress = new Dialog(NewYearGIF_StartActivity.this, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.z1_ads_loading_dialog_inter);

        NewYearGIF_functions.iplscr_flag = true;

        if (adsdata != null && adsdata.size() > 0) {

            if (adsdata.get(0).getCheckNative()) {
                try {
                    Liner_helinativAd.setVisibility(View.VISIBLE);
                    Picasso.get()
                            .load(adsdata.get(0).getNativeUrl())
                            .into(helinative);
                } catch (Exception e) {
                }
            }
            helinative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse("market://details?id=" + adsdata.get(0).getPackagename()));
                        getApplicationContext().startActivity(intent);
                    } catch (ActivityNotFoundException anfe) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse("market://details?id=" + adsdata.get(0).getPackagename()));
                        getApplicationContext().startActivity(intent);
                    }
                }
            });


            if (adsdata.get(0).getCheckQurekaBtn().equals("on")) {
                acion_qureka.setVisibility(View.VISIBLE);
            } else {
                acion_qureka.setVisibility(View.GONE);
            }

            // TODO: 11-09-2020  Adaptive banner ads
            switch (adsdata.get(0).getCheckAdBanner()) {
                case "admob":
                    admob_loadbanner();
                    break;
                case "qureka":
                    Q_Banner();
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

        btn_getStarted = (ImageView) findViewById(R.id.btn_getStarted);
        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adsdata != null && adsdata.size() > 0) {
                    if (adsdata.get(0).getCheckAdStart().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckAdStart().equals("off")) {
                        Intent i = new Intent(NewYearGIF_StartActivity.this, NewYearGIF_TapToStartActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                } else {
                    Intent i = new Intent(NewYearGIF_StartActivity.this, NewYearGIF_TapToStartActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });

        acion_qureka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(NewYearGIF_StartActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });

        Liner_Localad = (RelativeLayout) findViewById(R.id.localad);
        Liner_Localad.setVisibility(View.GONE);

        try {

            NewYearGIF_App app = (NewYearGIF_App) getApplication();
            app.setStartAdListener(new NewYearGIF_App.StartAdListener() {
                @Override
                public void onStartAdError() {
                    offline_viewprivacypolicy();
                }

                @Override
                public void onStartAdLoaded() {
                    online_viewprivacypolicy();
                    showStartApps();
                }
            });

        } catch (Exception e) {
        }
    }

    public void onResume() {
        NewYearGIF_functions.callAutoQureka(this);
        super.onResume();
    }

    public void onPause() {
        NewYearGIF_functions.destroyThread();
        super.onPause();
    }

    private void online_viewprivacypolicy() {
        try {
            final PrivacyPolicyDialog dialog = new PrivacyPolicyDialog(this,
                    arrAdDataStart.get(0).getPrivacypolicy(),
                    arrAdDataStart.get(0).getPrivacypolicy());

            dialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
                @Override
                public void onAccept(boolean isFirstTime) {

                }

                @Override
                public void onCancel() {
                    Log.e("Music_MainActivity", "Policies not accepted");
                    finish();
                }
            });

            dialog.addPoliceLine("We DO NOT collect or gather any personal information while you visit, download or upgrade any application, your personal information like your first name and last name, physical addresses, telephone numbers, fax numbers and information stored within your device. We will not collect or store your Personal Information and we will not use, transfer or disclose your Personal Information, excepting the personal information that you submit to us when you create a user account, send an error report or participate in online surveys and other activities. In the following circumstances, we may disclose your personal information according to your wish or regulations by law:");
            dialog.addPoliceLine("Protecting your privacy is important to us. We hope the following statement will help you understand how InstaShot deals with the personal identifiable information ('PII') you may occasionally provide to us via Internet (the'Google Play'Platform)");
            dialog.addPoliceLine("1. Your prior permission.");
            dialog.addPoliceLine("2. By the applicable law within or outside your country of residence, legal process, litigation requests.");
            dialog.addPoliceLine("3. By requests from public and governmental authorities.");

            dialog.addPoliceLine("2. Ad Networks and Cross Promotion Ads\n" +
                    "\n" +
                    "We welcome the third parties ad networks for accepting advertisements (banners, interstitials and video ads etc…). In our apps and games, these advertisements authorized to be displayed. Advertisers may use cookies and other web-tracking technologies to collect data, in case that user clicks on any of these advertisements.\n" +
                    "\n" +
                    "We promote third parties games, apps and services in different types of ways. That might include cross promoting of third parties games or app while you are using a different games or apps of ours.\n" +
                    "\n" +
                    "We display ads to cross promote apps and games of third parties.\n" +
                    "\n" +
                    "We do not gather or share any of your personal identification information to display ads.");

            dialog.addPoliceLine("Privacy Policy Changes.");
            dialog.addPoliceLine("Our Privacy Policy may change from time to time, we will post any privacy policy changes on this page, so please review it periodically. We may provide you additional forms of notice of modifications or updates as appropriate under the circumstances.");
            //  Customizing (Optional)
            dialog.setTitleTextColor(Color.parseColor("#222222"));
            dialog.setAcceptButtonColor(ContextCompat.getColor(this, R.color.colorAccent));

            //  Title
            dialog.setTitle("Terms of Service");
            dialog.setTermsOfServiceSubtitle("If you click on {accept}, you acknowledge that it makes the content present and all the content of our {terms}Terms of Service{/terms} and implies that you have read our {privacy}Privacy Policy{privacy}.");
            dialog.show();
        } catch (Exception e) {

        }
    }

    private void offline_viewprivacypolicy() {
        try {
            final PrivacyPolicyDialog dialog = new PrivacyPolicyDialog(this,
                    arrAdDataStart.get(0).getPrivacypolicy(),
                    arrAdDataStart.get(0).getPrivacypolicy());

            dialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
                @Override
                public void onAccept(boolean isFirstTime) {

                }

                @Override
                public void onCancel() {
                    Log.e("Music_MainActivity", "Policies not accepted");
                    finish();
                }
            });

            dialog.addPoliceLine("We DO NOT collect or gather any personal information while you visit, download or upgrade any application, your personal information like your first name and last name, physical addresses, telephone numbers, fax numbers and information stored within your device. We will not collect or store your Personal Information and we will not use, transfer or disclose your Personal Information, excepting the personal information that you submit to us when you create a user account, send an error report or participate in online surveys and other activities. In the following circumstances, we may disclose your personal information according to your wish or regulations by law:");
            dialog.addPoliceLine("Protecting your privacy is important to us. We hope the following statement will help you understand how InstaShot deals with the personal identifiable information ('PII') you may occasionally provide to us via Internet (the'Google Play'Platform)");
            dialog.addPoliceLine("1. Your prior permission.");
            dialog.addPoliceLine("2. By the applicable law within or outside your country of residence, legal process, litigation requests.");
            dialog.addPoliceLine("3. By requests from public and governmental authorities.");

            dialog.addPoliceLine("2. Ad Networks and Cross Promotion Ads\n" +
                    "\n" +
                    "We welcome the third parties ad networks for accepting advertisements (banners, interstitials and video ads etc…). In our apps and games, these advertisements authorized to be displayed. Advertisers may use cookies and other web-tracking technologies to collect data, in case that user clicks on any of these advertisements.\n" +
                    "\n" +
                    "We promote third parties games, apps and services in different types of ways. That might include cross promoting of third parties games or app while you are using a different games or apps of ours.\n" +
                    "\n" +
                    "We display ads to cross promote apps and games of third parties.\n" +
                    "\n" +
                    "We do not gather or share any of your personal identification information to display ads.");

            dialog.addPoliceLine("Privacy Policy Changes.");
            dialog.addPoliceLine("Our Privacy Policy may change from time to time, we will post any privacy policy changes on this page, so please review it periodically. We may provide you additional forms of notice of modifications or updates as appropriate under the circumstances.");
            //  Customizing (Optional)
            dialog.setTitleTextColor(Color.parseColor("#222222"));
            dialog.setAcceptButtonColor(ContextCompat.getColor(this, R.color.colorAccent));

            //  Title
            dialog.setTitle("Terms of Service");
            dialog.setTermsOfServiceSubtitle("If you click on {accept}, you acknowledge that it makes the content present and all the content of our {terms}Terms of Service{/terms} and implies that you have read our {privacy}Privacy Policy{privacy}.");
            dialog.show();
        } catch (Exception e) {

        }
    }

    private void showStartApps() {

        boolean isAdVisible = false;

        Liner_Localad.setVisibility(View.VISIBLE);

        if (arrAdDataStart != null && arrAdDataStart.size() > 0) {
            isAdVisible = true;
        } else {
            isAdVisible = false;
        }

        if (isAdVisible) {

            ad_recycle_view = (RecyclerView) findViewById(R.id.ad_recycle_view);
            ad_recycle_view.setHasFixedSize(true);
            ad_recycle_view.setLayoutFrozen(true);
            GridLayoutManager llm = new GridLayoutManager(NewYearGIF_StartActivity.this, 3);
            llm.setOrientation(GridLayoutManager.VERTICAL);
            ad_recycle_view.setLayoutManager(llm);
            adViewAdapter = new AdViewAdapter_Start(mContext);
            ad_recycle_view.setAdapter(adViewAdapter);

            NewYearGIF_ItemClickSupport.addTo(ad_recycle_view).setOnItemClickListener(new NewYearGIF_ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    gotoAppStore(arrAdDataStart.get(position).getAppName(),
                            arrAdDataStart.get(position).getPackagename());
                }
            });

        }
    }

    private void gotoAppStore(final String appname, final String packagename) {
        // TODO Auto-generated method stub
        // new UpdateCounter().execute(packagename);
        try {
            mContext.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id="
                            + packagename)));
        } catch (ActivityNotFoundException anfe) {
            mContext.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + packagename)));
        }
    }


    public class AdViewAdapter_Start extends RecyclerView.Adapter<NewYearGIF_AdViewHolderView> {
        Context context;

        public AdViewAdapter_Start(Context context) {
            this.context = context;
        }

        @Override
        public NewYearGIF_AdViewHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.thop_adview_listitem, parent, false);
            NewYearGIF_AdViewHolderView viewHolder = new NewYearGIF_AdViewHolderView(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final NewYearGIF_AdViewHolderView holder, int position) {
            try {
                Picasso.get().load(arrAdDataStart.get(position).getAppIcon())
                        .into(holder.appicon);
                holder.appname.setText(arrAdDataStart.get(position).getAppName());
                holder.appname.setTextSize(11);
                holder.appname.setSelected(true);
            } catch (Exception e) {
            }
        }

        @Override
        public int getItemCount() {

            return arrAdDataStart.size();
        }
    }

    class FirstReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FirstReceiver", "FirstReceiver");
            if (intent.getAction().equals(ACTION_CLOSE)) {
                NewYearGIF_StartActivity.this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}