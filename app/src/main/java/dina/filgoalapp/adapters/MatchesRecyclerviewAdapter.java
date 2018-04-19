package dina.filgoalapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import dina.filgoalapp.R;
import dina.filgoalapp.models.ModelDays;

public class MatchesRecyclerviewAdapter extends RecyclerView.Adapter<MatchesRecyclerviewAdapter.MatchesViewHolder> {

    ArrayList<ModelDays> modelDays = new ArrayList<>();
    Context mContext;


    public MatchesRecyclerviewAdapter(Context mContext, ArrayList<ModelDays> modelDays) {

        this.mContext = mContext;
        this.modelDays = modelDays;
    }


    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_recycler_row, parent, false);
        MatchesViewHolder matchesViewHolder = new MatchesViewHolder(view);
        return matchesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {

        final ModelDays item = modelDays.get(position);
        holder.firstTeamName.setText(item.getFirstTeam());
        holder.secondTeamName.setText(item.getSecondTeam());


        String[] parts = item.getResult().split("-");
        String first = parts[0];
        String second = parts[1];
        holder.resultOne.setText(first);
        holder.resultTwo.setText(second);


        holder.data.setText(getTime(item.getDate()));
        holder.tv.setText(item.getTv());



        if (!modelDays.get(position).getFirstTeamFlag().isEmpty()) {
            if (!modelDays.get(position).getSecondTeamFlag().isEmpty()) {
                holder.firstTeamImage.setVisibility(View.VISIBLE);
                holder.secondTeamName.setVisibility(View.VISIBLE);

                Picasso.with(mContext).load(modelDays.get(position).getFirstTeamFlag()).into(holder.secondTeamImage);
                Picasso.with(mContext).load(modelDays.get(position).getSecondTeamFlag()).into(holder.firstTeamImage);

            }
        }


    }







    public String getTime(String dateString) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMMMM-yy HH.mm.ss.000000 aa ");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat(" hh:mm aa");
        return fmtOut.format(date);
    }



    @Override
    public int getItemCount() {
        return modelDays.size();
    }


    class MatchesViewHolder extends RecyclerView.ViewHolder {
        CircleImageView firstTeamImage, secondTeamImage;
        TextView firstTeamName, secondTeamName, resultOne, resultTwo, data, tv;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            firstTeamImage = (CircleImageView) itemView.findViewById(R.id.img_firstTeamFlag);
            secondTeamImage = (CircleImageView) itemView.findViewById(R.id.img_secondTeamFlag);
            firstTeamName = itemView.findViewById(R.id.txt_firstTeam);
            secondTeamName = itemView.findViewById(R.id.txt_secondTeam);


            resultOne = itemView.findViewById(R.id.txt_result);
            resultTwo = itemView.findViewById(R.id.txt_result2);
            data = itemView.findViewById(R.id.txt_data);
            tv = itemView.findViewById(R.id.txt_tv);


        }
    }
}
