package com.dinhtrongdat.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, botAnim;
    ImageView image;
    TextView title, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this, R.anim.bot_animation);

        image = (ImageView)findViewById(R.id.imgView);
        title = (TextView) findViewById(R.id.txtTitle);
        slogan = (TextView) findViewById(R.id.txtSlogan);

        image.setAnimation(topAnim);
        title.setAnimation(botAnim);
        slogan.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(title, "logo_text");

                ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, option.toBundle());
            }
        }, SPLASH_SCREEN);

    }
}