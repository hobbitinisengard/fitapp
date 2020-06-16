package fa.fitapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseListRecyclerAdapter extends RecyclerView.Adapter<ExerciseListRecyclerAdapter.ViewHolder> {

    private List<Exercise> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ExerciseListRecyclerAdapter(Context context, List<Exercise> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_exercise, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Exercise exercise = mData.get(position);

        holder.tAuthor.setText(exercise.Author);
        holder.tExerciseName.setText(exercise.Name);

        holder.bOpenVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                watchYoutubeVideo(holder.tExerciseName.getContext(), exercise.Url);
            }
        });
        holder.bDescription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(holder.tExerciseName.getContext()).create(); //Read Update
                alertDialog.setTitle(exercise.Name);
                alertDialog.setMessage(exercise.Description);
                alertDialog.setCancelable(true);
                alertDialog.show();
            }
        });
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tExerciseName;
        TextView tAuthor;
        Button bOpenVideo;
        Button bDescription;

        ViewHolder(View itemView) {
            super(itemView);
            tExerciseName = itemView.findViewById(R.id.tExerciseName);
            tAuthor = itemView.findViewById(R.id.tAuthorName);
            bOpenVideo = itemView.findViewById(R.id.bOpenVideo);
            bDescription = itemView.findViewById(R.id.bDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    Exercise getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
