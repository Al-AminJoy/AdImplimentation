package com.trustedoffers.adimplimentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    Button btClick;
 private AdView adView;
 private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        adView=findViewById(R.id.avHomeBanner);
        btClick=findViewById(R.id.btClick);
        //This Is For Banner Ad
        AdRequest adRequest=new AdRequest.Builder().addTestDevice("87886E0D2745CC6D24339622C5AEFAFA").build();
        adView.loadAd(adRequest);
        //This Is For Interstial Ad
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                else {
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    Toast.makeText(getApplicationContext(),"Not Loaded",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
