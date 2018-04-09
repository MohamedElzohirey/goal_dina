package dina.filgoalapp.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.models.ModelPages;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.SliderHolder> {

    List<ModelPages> modelPages;

    public RecyclerviewAdapter(List<ModelPages> modelPages) {
        this.modelPages = modelPages;

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
        ModelPages pages = modelPages.get(position);
        holder.viewPager.setAdapter(pages.getImageURL());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class SliderHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        public SliderHolder(View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.view_pager);
        }
    }


}
