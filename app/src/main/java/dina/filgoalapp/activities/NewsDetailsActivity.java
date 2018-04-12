package dina.filgoalapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.squareup.picasso.Picasso;

import java.io.File;

import dina.filgoalapp.R;
import dina.filgoalapp.models.ModelPages;

public class NewsDetailsActivity extends AppCompatActivity {
    ModelPages modelPages;
    TextView textView;
    ImageView Image;
    Context mContext;
    Button playVideoButton;
    Button fullScreenButton;
TextView screen_title_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.txt_News);
        Image = findViewById(R.id.img_News);
        playVideoButton = findViewById(R.id.btn_playButton);
        fullScreenButton = findViewById(R.id.btn_FullScreen);
        screen_title_tv = findViewById(R.id.screen_title_tv);
        modelPages = (ModelPages) getIntent().getSerializableExtra("ModelPagesDeltails");
        if (modelPages.getVideo() != null && !modelPages.getVideo().isEmpty()){
            fullScreenButton.setVisibility(View.GONE);
            playVideoButton.setVisibility(View.VISIBLE);
        }else {
            fullScreenButton.setVisibility(View.VISIBLE);
            playVideoButton.setVisibility(View.GONE);
        }
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetailsActivity.this, FullscreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ModelPagesDeltails", modelPages.getImageURL());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + modelPages.getVideo()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
            }
        });

        screen_title_tv.setText(modelPages.getTitle());
        textView.setText(modelPages.getDetails());
        Picasso.with(mContext).load(modelPages.getImageURL()).into(Image);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, modelPages.getImageURL());
                //  shareIntent.putExtra(Intent.EXTRA_STREAM, modelPages.getTitle());
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });
    }
}
