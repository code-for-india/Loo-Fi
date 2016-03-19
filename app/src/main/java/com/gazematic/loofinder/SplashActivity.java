package com.gazematic.loofinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    private ImageView imageView;
    private Animation imageRotation;
    private SharedPreferencesController sharedPreferencesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferencesController = SharedPreferencesController.getSharedPreferencesController(this);

        imageView = (ImageView) findViewById(R.id.applogo);
        imageRotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        imageView.startAnimation(imageRotation);

        //Thread to start home activity after sleep time
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

//                    if(sharedPreferencesController.getBoolean("visited_tutorial") == false){
//                        Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
//                        //finish();
//                    } else {

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
                    //}
                }
            }
        };
        timerThread.start();





    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }









}
