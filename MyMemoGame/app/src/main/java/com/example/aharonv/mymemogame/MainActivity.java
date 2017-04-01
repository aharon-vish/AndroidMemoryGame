package com.example.aharonv.mymemogame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {

    ImageView iv;
    boolean flag;

    //load images from xml
    int[] images = {R.id.imageView0,R.id.imageView1,R.id.imageView2,
            R.id.imageView3,R.id.imageView4,R.id.imageView5,
            R.id.imageView6,R.id.imageView7,R.id.imageView8,
            R.id.imageView9,R.id.imageView10,R.id.imageView11
    };
// load images from res.drawable "id"
    int[] myImageList = new int[]{R.drawable.chip, R.drawable.coco, R.drawable.cola,
            R.drawable.cup, R.drawable.mafin,R.drawable.icecream,
            R.drawable.chip, R.drawable.coco, R.drawable.cola,
            R.drawable.cup, R.drawable.mafin,R.drawable.icecream};

    //random for shuffel
    Random rnd = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // shuffel myImageList
        for (int i = myImageList.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = myImageList[index];
            myImageList[index] = myImageList[i];
            myImageList[i] = a;
        }

       // exit from app when press on let me go home

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        // class that implements View.OnClickListener for all images in view
        MemGameListener myListener = new MemGameListener();
        for(int i = 0; i < images.length; i++) {
            iv = (ImageView) findViewById(images[i]);
            iv.setOnClickListener(myListener);
            iv.setImageResource(R.drawable.icon);
            iv.setTag(myImageList[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
