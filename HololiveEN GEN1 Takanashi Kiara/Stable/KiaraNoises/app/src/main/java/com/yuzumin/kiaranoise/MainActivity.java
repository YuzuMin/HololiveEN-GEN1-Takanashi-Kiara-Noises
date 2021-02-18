package com.yuzumin.kiaranoise;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Count.CountListener{
    ImageView image;
    TextView textCount;

    MediaPlayer kiaranoise;
    MediaPlayer.OnCompletionListener listener;

    ObjectAnimator anim1, anim2, anim3;
    AnimatorSet set1, set2;

    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;

    boolean switch2;
    boolean switch3;
    boolean switch4;
    boolean switch5;
    boolean switch6;
    boolean switch7;
    boolean switch8;
    boolean switch9;
    boolean switch10;
    boolean switch11;



    Count count;
    SharedPreferences sp;

    @Override
    public void OnUpdated(int count) {
        sp.edit().putInt("count", count).apply();
        textCount.setText(String.format("Count: %d", count));
        retrievedata();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                cleanupMediaPlayer();
            }
        };

        image = findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences2=getSharedPreferences("save2",MODE_PRIVATE);
                switch2=sharedPreferences2.getBoolean("value2",true);
                if(switch2){
                    editor2 =getSharedPreferences("save2",MODE_PRIVATE).edit();
                    editor2.putBoolean("value2",true);
                    editor2.apply();
                }else{
                    editor2 =getSharedPreferences("save2",MODE_PRIVATE).edit();
                    editor2.putBoolean("value2",false);
                    editor2.apply();
                }

                if(switch2){
                    count.increment();
                    startRandomSound();
                }
                else{
                    Toast.makeText(v.getContext(), "NOT ACTIVE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textCount = findViewById(R.id.count);


        anim1 = ObjectAnimator.ofFloat(image,"translationY", 0f,-50f, 50f, -50f, 50f, 0);
        anim1.setDuration(600);
        set1 = new AnimatorSet();
        ArrayList<Animator> setArray = new ArrayList<>();


        anim2 = ObjectAnimator.ofFloat(image,"translationY", 0f,-50f, 50f, -50f, 50f, -50f, 50f, -50f, 50f,0);
        anim2.setRepeatCount(ValueAnimator.INFINITE);
        setArray.add(anim2);
        anim2 = ObjectAnimator.ofFloat(image,"translationX", 0f,-70f, 70f, -70f, 70f, 0);
        anim2.setRepeatCount(ValueAnimator.INFINITE);
        setArray.add(anim2);
        set1.playTogether(setArray);
        set1.setDuration(2000);
        anim2 = null;


        set2 = new AnimatorSet();
        setArray = new ArrayList<>();
        anim3 = ObjectAnimator.ofFloat(image, "rotation", 0f, -90f, 0f, 90f, 180f, 270f, 360f);
        anim3.setRepeatCount(ValueAnimator.INFINITE);
        setArray.add(anim3);
        anim3 = ObjectAnimator.ofFloat(image, "scaleX", 1f,0.5f, 1.5f, 0.5f, 1.5f, 1f);
        anim3.setRepeatCount(ValueAnimator.INFINITE);
        setArray.add(anim3);
        anim3 = ObjectAnimator.ofFloat(image, "scaleY", 1f,.5f, 1.5f, 0.5f, 1.5f, 1f);
        anim3.setRepeatCount(ValueAnimator.INFINITE);
        setArray.add(anim3);
        set2.playTogether(setArray);
        set2.setDuration(2000);
        anim3 = null;
        count = new Count();
        count.setListener(this);

        sp = this.getSharedPreferences("count", MODE_PRIVATE);
        count.setCount(sp.getInt("count", 0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case(R.id.about):
                Intent intent;
                intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
                break;
            case(R.id.settings):
                Intent settingmenu;
                settingmenu = new Intent(MainActivity.this,Settings.class);
                startActivity(settingmenu);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void stopAnimations(){
        if (anim1.isRunning())
            anim1.end();
        if (set1.isRunning())
            set1.end();
        if (set2.isRunning())
            set2.end();
    }

    private void cleanupMediaPlayer(){
        stopAnimations();
        if(kiaranoise!=null) {
            if(kiaranoise.isPlaying())
                kiaranoise.stop();
            kiaranoise.release();
            kiaranoise = null;
        }
    }

    private void startRandomSound() {
        Random random = new Random();
        float next = random.nextFloat();
        int counter =count.getCount();

        cleanupMediaPlayer();

        System.out.println(next);
        if (counter%31==0&&switch3) {

            //play Muah

            kiaranoise = MediaPlayer.create(this, R.raw.noise2);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Muah", Toast.LENGTH_SHORT).show();
            anim1.start();

        } else if (counter%19==0&&switch4) {

            //play Eat me

            kiaranoise = MediaPlayer.create(this, R.raw.noise1);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "EAT ME", Toast.LENGTH_SHORT).show();
            set2.start();

        } else if (counter%69==0&&switch5) {

            //Play I offer my body to you

            kiaranoise = MediaPlayer.create(this, R.raw.noise3);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "I offer my body to you", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (counter%50==0&&switch6) {

            //play Let me make my dream come true I sleep next to you

            kiaranoise = MediaPlayer.create(this, R.raw.noise4);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Let me make my dream come true I sleep next to you", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (next < 0.7&&switch7){

            //Play Kiara's Real Voice

            kiaranoise = MediaPlayer.create(this, R.raw.noise5);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Kiara's Real Voice", Toast.LENGTH_SHORT).show();
            set2.start();

        }else if (next < .9&&switch8){

            //Play Ugh, Fine, I guess you are my little pogchamp, Come here

            kiaranoise = MediaPlayer.create(this, R.raw.noise6);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Ugh, Fine, I guess you are my little pogchamp, Come here", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (counter%500==0&&switch9){

            //Play VERY LOUD NOISE

            kiaranoise = MediaPlayer.create(this, R.raw.noise7);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "VERY LOUD NOISE", Toast.LENGTH_SHORT).show();
            set2.start();

        }else if (counter%10==0&&switch10){

            //Play Signature Laugh

            kiaranoise = MediaPlayer.create(this, R.raw.noise9);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Signature Laugh", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (switch11){

            //play Kikkeriki

            kiaranoise = MediaPlayer.create(this, R.raw.noise8);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Kikkeriki", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (switch3){

            //play Muah

            kiaranoise = MediaPlayer.create(this, R.raw.noise2);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Muah", Toast.LENGTH_SHORT).show();
            anim1.start();

        }else if (switch4){

            //play Eat me

            kiaranoise = MediaPlayer.create(this, R.raw.noise1);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "EAT ME", Toast.LENGTH_SHORT).show();
            set2.start();

        }else if (switch5){

            //Play I offer my body to you

            kiaranoise = MediaPlayer.create(this, R.raw.noise3);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "I offer my body to you", Toast.LENGTH_SHORT).show();
            set1.start();


        }else if (switch6){

            //play Let me make my dream come true I sleep next to you

            kiaranoise = MediaPlayer.create(this, R.raw.noise4);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Let me make my dream come true I sleep next to you", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (switch7){

            //Play Kiara's Real Voice

            kiaranoise = MediaPlayer.create(this, R.raw.noise5);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Kiara's Real Voice", Toast.LENGTH_SHORT).show();
            set2.start();

        }else if (switch8){

            //Play Ugh, Fine, I guess you are my little pogchamp, Come here

            kiaranoise = MediaPlayer.create(this, R.raw.noise6);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Ugh, Fine, I guess you are my little pogchamp, Come here", Toast.LENGTH_SHORT).show();
            set1.start();

        }else if (switch9){

            //Play VERY LOUD NOISE

            kiaranoise = MediaPlayer.create(this, R.raw.noise7);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "VERY LOUD NOISE", Toast.LENGTH_SHORT).show();
            set2.start();

        }else if (switch10){

            //Play Signature Laugh

            kiaranoise = MediaPlayer.create(this, R.raw.noise9);
            kiaranoise.setOnCompletionListener(listener);
            kiaranoise.start();
            Toast.makeText(this, "Signature Laugh", Toast.LENGTH_SHORT).show();
            set1.start();
        }

    }


    private void retrievedata(){
        sharedPreferences2=getSharedPreferences("save2",MODE_PRIVATE);
        switch2=sharedPreferences2.getBoolean("value2",true);
        if(switch2){
            editor2 =getSharedPreferences("save2",MODE_PRIVATE).edit();
            editor2.putBoolean("value2",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save2",MODE_PRIVATE).edit();
            editor2.putBoolean("value2",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save3",MODE_PRIVATE);
        switch3=sharedPreferences2.getBoolean("value3",true);
        if(switch3){
            editor2 =getSharedPreferences("save3",MODE_PRIVATE).edit();
            editor2.putBoolean("value3",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save3",MODE_PRIVATE).edit();
            editor2.putBoolean("value3",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save4",MODE_PRIVATE);
        switch4=sharedPreferences2.getBoolean("value4",true);
        if(switch4){
            editor2 =getSharedPreferences("save4",MODE_PRIVATE).edit();
            editor2.putBoolean("value4",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save4",MODE_PRIVATE).edit();
            editor2.putBoolean("value4",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save5",MODE_PRIVATE);
        switch5=sharedPreferences2.getBoolean("value5",true);
        if(switch5){
            editor2 =getSharedPreferences("save5",MODE_PRIVATE).edit();
            editor2.putBoolean("value5",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save5",MODE_PRIVATE).edit();
            editor2.putBoolean("value5",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save6",MODE_PRIVATE);
        switch6=sharedPreferences2.getBoolean("value6",true);
        if(switch6){
            editor2 =getSharedPreferences("save6",MODE_PRIVATE).edit();
            editor2.putBoolean("value6",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save6",MODE_PRIVATE).edit();
            editor2.putBoolean("value6",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save7",MODE_PRIVATE);
        switch7=sharedPreferences2.getBoolean("value7",true);
        if(switch7){
            editor2 =getSharedPreferences("save7",MODE_PRIVATE).edit();
            editor2.putBoolean("value7",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save7",MODE_PRIVATE).edit();
            editor2.putBoolean("value7",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save8",MODE_PRIVATE);
        switch8=sharedPreferences2.getBoolean("value8",true);
        if(switch8){
            editor2 =getSharedPreferences("save8",MODE_PRIVATE).edit();
            editor2.putBoolean("value8",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save8",MODE_PRIVATE).edit();
            editor2.putBoolean("value8",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save9",MODE_PRIVATE);
        switch9=sharedPreferences2.getBoolean("value9",true);
        if(switch9){
            editor2 =getSharedPreferences("save9",MODE_PRIVATE).edit();
            editor2.putBoolean("value9",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save9",MODE_PRIVATE).edit();
            editor2.putBoolean("value9",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save10",MODE_PRIVATE);
        switch10=sharedPreferences2.getBoolean("value10",true);
        if(switch10){
            editor2 =getSharedPreferences("save10",MODE_PRIVATE).edit();
            editor2.putBoolean("value10",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save10",MODE_PRIVATE).edit();
            editor2.putBoolean("value10",false);
            editor2.apply();
        }

        sharedPreferences2=getSharedPreferences("save11",MODE_PRIVATE);
        switch11=sharedPreferences2.getBoolean("value11",true);
        if(switch11){
            editor2 =getSharedPreferences("save11",MODE_PRIVATE).edit();
            editor2.putBoolean("value11",true);
            editor2.apply();
        }else{
            editor2 =getSharedPreferences("save11",MODE_PRIVATE).edit();
            editor2.putBoolean("value11",false);
            editor2.apply();
        }

    }

    @Override
    protected void onDestroy() {
        cleanupMediaPlayer();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        cleanupMediaPlayer();
        super.onStop();
    }

}