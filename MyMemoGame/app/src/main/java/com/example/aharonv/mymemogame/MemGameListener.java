package com.example.aharonv.mymemogame;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewAnimator;


public class MemGameListener implements View.OnClickListener {

    int TurnCount=0;//how turn is
    boolean CheckIfMatch=false;// true after second click
    int ImageOne=0; // keep the res of tag1 img
    int ImageTow=0;// keep the res of tag2 img
    ImageView ImageV1=null; // keep the click img1
    ImageView ImageV2=null; // keep the click img2

    int Correct_guesses=0; // when 5 guess go to second activity
    Animation animshake; // Animation for shake the img on click

// when click go here
    @Override
    public void onClick(View v) {
        ImageView iv = (ImageView) v;//convert v to imageview
        Integer resId = (Integer) iv.getTag();

        if(TurnCount==0){ //check turn
            if(CheckIfMatch){ // if is 2 image open check
                if(ImageOne==ImageTow)
                {
                    ImageV1.setClickable(false);
                    ImageV2.setClickable(false);
                    Correct_guesses++; // if even image ++


                }
                else {
                    //if not even set  back to "icon"
                    ImageV1.setImageResource(R.drawable.icon);
                    ImageV2.setImageResource((R.drawable.icon));
                }
            }
            // go to shakefunc
            ShakeME(iv);
            iv.setImageResource(resId);
            TurnCount++;
            ImageOne=resId;
            ImageV1=iv;

        }
     else if(TurnCount==1){
            iv.setImageResource(resId);
            ImageTow=resId;
             ImageV2=iv;
            ShakeME(iv);
            TurnCount=0;

            if(Correct_guesses==5)
            {
                final Context context = v.getContext();
                Intent intent = new Intent(context, CongratulationScreen.class);
                context.startActivity(intent);
            }

            CheckIfMatch=true;

        }

    }

    public void ShakeME(ImageView shakeimg){
        //load animation
        animshake = AnimationUtils.loadAnimation(shakeimg.getContext(), R.anim.shakeimage);
        //shake
        shakeimg.startAnimation(animshake);
    }

}
