package xmas.callwithsanta.videocallingsanta;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_StartActivity;
import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_functions;
import xmas.callwithsanta.videocallingsanta.adapter.ChatAdapter;
import xmas.callwithsanta.videocallingsanta.startscreen.Chat;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ChatAdapter adapter;
    ArrayList<Chat> chatArrayList;
    boolean check = false;
    int count = 0;

    ImageView img_call;
    ImageView img_chat_refresh;

    MediaPlayer mediaPlayer;
    RecyclerView recyclerView;
    FloatingActionButton send_msg_txt;
    String[] sender = {"Hello,... ! ☺", "What's your name 😍 😍 ?", "Nice to meet you %s, I am %s , do you know me ? 😊", "How old are you 😊 ?", "Have you been naughty or nice this year ? ☺", "Oh ho ho ho ho fantastic ☺ ☺", "Where do you live 😍 😍 ?", "Now tell me what do you want for Christmas ? 😍 ☺", "Say it again ? ☺ 😍 😍 ", "Oh Okay, Got it ? 💖 😍", "I will see you soon ☺ 😍", "Do you Like this Application ?💖 😍", "Rate your experience and leave a comment. ☺ 😍 😍 \" ☺ 😍 😍"};
    Toolbar toolbar;
    int finalcount = this.sender.length;
    EditText txt_view_msg_txt;

    CardView q_native_banner, q_banner2;
    private RelativeLayout ad_space;

    public void onResume() {
        NewYearGIF_functions.callAutoQureka(this);
        super.onResume();
    }

    public void onPause() {
        NewYearGIF_functions.destroyThread();
        super.onPause();
    }

    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
    }

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.chat_call_layout);

        q_native_banner = (CardView) findViewById(R.id.q_native_banner);
        q_banner2 = (CardView) findViewById(R.id.q_banner2);
        adContainerView = findViewById(R.id.ad_view_container);
        ad_space = findViewById(R.id.ad_space);

        NewYearGIF_functions.iplscr_flag = true;

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

            // TODO: 11-09-2020  Adaptive banner ads
            switch (adsdata.get(0).getCheckAdBanner()) {
                case "admob":
                    admob_loadbanner();
                    break;
                case "qureka":
                    Q_Banner();
                    break;
            }
        }

        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        findViewById();
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        this.mediaPlayer = MediaPlayer.create(this, R.raw.ring_msg);
        this.chatArrayList = new ArrayList<>();
        this.chatArrayList.add(new Chat(2, this.sender[this.count]));
        this.adapter = new ChatAdapter(this, this.chatArrayList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.recyclerView.setAdapter(this.adapter);
        this.send_msg_txt.setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                final String obj = ChatActivity.this.txt_view_msg_txt.getText().toString();
                if (obj.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Empty Message", 0).show();
                    return;
                }
                ChatActivity.this.count++;
                if (ChatActivity.this.count == ChatActivity.this.finalcount) {
                    ChatActivity chatActivity = ChatActivity.this;
                    chatActivity.check = true;
                    AlertDialog create = new Builder(chatActivity).create();
                    create.setMessage("How was your experience with us?");
                    create.setButton(-1, (CharSequence) "Rate us", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            StringBuilder sb = new StringBuilder();
                            sb.append("market://details?id=");
                            sb.append(ChatActivity.this.getPackageName());
                            intent.setData(Uri.parse(sb.toString()));
                            ChatActivity.this.startActivity(intent);
                        }
                    });
                    create.setButton(-2, (CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    create.show();
                } else if (!ChatActivity.this.check) {
                    ChatActivity.this.chatArrayList.add(new Chat(1, obj));
                    ChatActivity.this.txt_view_msg_txt.setText("");
                    ChatActivity.this.adapter.notifyDataSetChanged();
                    ChatActivity.this.recyclerView.post(new Runnable() {
                        public void run() {
                            ChatActivity.this.recyclerView.smoothScrollToPosition(ChatActivity.this.adapter.getItemCount() - 1);
                        }
                    });
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (ChatActivity.this.count == 2) {
                                ArrayList<Chat> arrayList = ChatActivity.this.chatArrayList;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Nice to meet you ");
                                sb.append(obj);
                                sb.append(", I am santa claus , do you know me  ? 😁 😊");
                                arrayList.add(new Chat(2, sb.toString()));
                            } else {
                                ChatActivity.this.chatArrayList.add(new Chat(2, ChatActivity.this.sender[ChatActivity.this.count]));
                            }
                            ChatActivity.this.adapter.notifyDataSetChanged();
                            ChatActivity.this.mediaPlayer.start();
                            ChatActivity.this.recyclerView.post(new Runnable() {
                                public void run() {
                                    ChatActivity.this.recyclerView.smoothScrollToPosition(ChatActivity.this.adapter.getItemCount() - 1);
                                }
                            });
                        }
                    }, 500);
                } else {
                    ChatActivity.this.img_chat_refresh.performClick();
                }
            }
        });
        this.img_chat_refresh.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AlertDialog create = new Builder(ChatActivity.this).create();
                create.setMessage("Do you want to Replay Chat ?");
                create.setButton(-1, (CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChatActivity.this.count = 0;
                        ChatActivity.this.chatArrayList.clear();
                        ChatActivity.this.chatArrayList.add(new Chat(2, ChatActivity.this.sender[ChatActivity.this.count]));
                        ChatActivity.this.adapter.notifyDataSetChanged();
                        ChatActivity.this.check = false;
                    }
                });
                create.setButton(-2, (CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                create.show();
            }
        });
    }

    private void findViewById() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.txt_view_msg_txt = (EditText) findViewById(R.id.txt_view_msg_txt);
        this.send_msg_txt = (FloatingActionButton) findViewById(R.id.send_msg_txt);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.img_chat_refresh = (ImageView) findViewById(R.id.img_chat_refresh);
        this.img_call = (ImageView) findViewById(R.id.img_call);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
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

            /*AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            TemplateView template = findViewById(R.id.admobsmallnative);
                            template.setVisibility(View.VISIBLE);
                            template.setNativeAd(unifiedNativeAd);
                        }
                    })
                    .build();
            adLoader.loadAd(new AdRequest.Builder().build());*/
        } catch (Exception e) {
        }
    }

    private void Q_Native_banner() {
        q_native_banner.setVisibility(View.VISIBLE);
        q_native_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(ChatActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    // TODO: 16-09-2020  ADMOB bANNER ads

    private FrameLayout adContainerView;
    private AdView adView;

    public void admob_loadbanner() {
        try {
            adView = new AdView(this);
            adView.setAdUnitId(adsdata.get(0).getAdmobBannerid());
            adContainerView.setVisibility(View.VISIBLE);
            ad_space.setVisibility(View.GONE);
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
                customTabsIntent.launchUrl(ChatActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

}
