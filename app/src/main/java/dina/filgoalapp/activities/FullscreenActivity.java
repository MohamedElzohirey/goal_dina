package dina.filgoalapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dina.filgoalapp.R;
import dina.filgoalapp.models.ModelPages;


public class FullscreenActivity extends AppCompatActivity {
   ImageView imageView;
    String modelPages;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        imageView=findViewById(R.id.img__FullScreen);
        modelPages =  getIntent().getStringExtra("ModelPagesDeltails");
        Picasso.with(mContext).load(modelPages).into(imageView);


    }
}
