package fa.fitapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutListRecyclerAdapter extends RecyclerView.Adapter<WorkoutListRecyclerAdapter.ViewHolder> {

    private List<Workout> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public WorkoutListRecyclerAdapter(Context context, List<Workout> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_training, parent, false);
        return new ViewHolder(view);
    }

    // bind data to row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Workout workout = mData.get(position);

        holder.tAuthor.setText(workout.Author);
        holder.tWorkoutName.setText(workout.Name);
        holder.tTypes.setText(workout.TypesString(context));
        holder.bListExercises.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(holder.tWorkoutName.getContext()).create(); //Read Update
                alertDialog.setTitle(workout.Name);
                alertDialog.setMessage(workout.ExerciseList(context));
                alertDialog.setCancelable(true);
                alertDialog.show();
            }
        });
        holder.bStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                RunTraining(workout);
            }
        });
    }

    private void RunTraining(Workout w) {
        Intent i = new Intent(context, InitialStepWorkout.class);
        //Log.d("TEST", String.valueOf(w.Exercises[0].exerciseID));
        i.putExtra("Workout", w);
        context.startActivity(i);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tWorkoutName;
        TextView tAuthor;
        TextView tTypes;
        Button bListExercises;
        Button bStart;
        ViewHolder(View itemView) {
            super(itemView);
            tWorkoutName = itemView.findViewById(R.id.tTrainingName);
            tAuthor = itemView.findViewById(R.id.tAuthorName);
            bListExercises = itemView.findViewById(R.id.bList);
            tTypes = itemView.findViewById(R.id.tTypes);
            bStart = itemView.findViewById(R.id.bStart);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Workout getItem(int id) {
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
