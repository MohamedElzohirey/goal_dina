package dina.filgoalapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator;
import com.github.piasy.biv.loader.fresco.FrescoImageLoader;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.squareup.picasso.Picasso;

import dina.filgoalapp.R;
import dina.filgoalapp.models.ModelPages;


public class FullscreenActivity extends AppCompatActivity {
    BigImageView imageView;
    String modelPages;
    Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        BigImageViewer.initialize(FrescoImageLoader.with(getApplicationContext()));

        setContentView(R.layout.activity_fullscreen);
        imageView=(BigImageView)findViewById(R.id.img__FullScreen);
        modelPages =  getIntent().getStringExtra("ModelPagesDeltails");
        imageView.setProgressIndicator(new ProgressPieIndicator());
        imageView.showImage(Uri.parse(modelPages));




    }
}
