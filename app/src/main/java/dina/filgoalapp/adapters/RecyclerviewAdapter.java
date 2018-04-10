package dina.filgoalapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.fragments.HomeFragment;
import dina.filgoalapp.models.Model;
import dina.filgoalapp.models.ModelPages;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.SliderHolder> {

    List<ModelPages> modelsList;
    Context mContext;

    public RecyclerviewAdapter(Context mContext,  List<ModelPages> modelsList) {
        this.modelsList =  modelsList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public SliderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        SliderHolder sliderHolder = new SliderHolder(view);
        return sliderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderHolder holder, int position) {

        ModelPages item = modelsList.get(position);
        holder.textView.setText(item.getTitle());
        Picasso.with(mContext).load(modelsList.get(position).getImageURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }


    class SliderHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;


        public SliderHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_landingPager);
            textView = itemView.findViewById(R.id.txt_landingPager);

        }
    }


}
