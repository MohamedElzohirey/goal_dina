package dina.filgoalapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.activities.NewsDetailsActivity;
import dina.filgoalapp.models.ModelPages;

public class MyAdapterSlider extends PagerAdapter {

    List<ModelPages> modelsList;
    Context mContext;
    private LayoutInflater inflater;


    public MyAdapterSlider(Context mContext,List<ModelPages> modelsList) {

        this.modelsList =  modelsList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return modelsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {

        final ModelPages item = modelsList.get(position);

        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        View item_container = myImageLayout.findViewById(R.id.item_container);
        ImageView myImage =  myImageLayout
                .findViewById(R.id.img_Slider);
        TextView textView = myImageLayout.findViewById(R.id.txt_Slider);
        textView.setText(modelsList.get(position).getTitle());
        Picasso.with(mContext).load(modelsList.get(position).getImageURL()).into(myImage);
        view.addView(myImageLayout, 0);
        item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ModelPagesDeltails",item);
                intent.putExtras(bundle);
                ((Activity) mContext).startActivity(intent);

            }
        });
        return myImageLayout;


    }
}
