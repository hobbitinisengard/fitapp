package fa.fitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditDeleteWorkoutRecyclerAdapter extends RecyclerView.Adapter<EditDeleteWorkoutRecyclerAdapter.ViewHolder> {

    private List<WorkoutExerciseSet> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    EditDeleteWorkoutRecyclerAdapter(Context context, List<WorkoutExerciseSet> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_newworkout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkoutExerciseSet wes = mData.get(position);

        holder.tNewExName.setText(wes.ExerciseName);
        holder.etSeries.setText(String.valueOf(wes.Series));
        holder.etBreaks.setText(String.valueOf(wes.Breaks));
        holder.cbAddExercise.setChecked(wes.isSelected);
        holder.cbAddExercise.setTag(mData.get(position));
        holder.cbAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutExerciseSet wes1 = (WorkoutExerciseSet)holder.cbAddExercise.getTag();

                wes1.isSelected = holder.cbAddExercise.isChecked();
                mData.get(position).isSelected = holder.cbAddExercise.isChecked();
                mData.get(position).Series = Byte.parseByte(holder.etSeries.getText().toString());
                mData.get(position).Breaks = Byte.parseByte(holder.etBreaks.getText().toString());
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ArrayList<WorkoutExerciseSet> getSelectedSets(){
        ArrayList<WorkoutExerciseSet> to_return = new ArrayList<>();
        for (int i=0; i<mData.size();i++){
            if (mData.get(i).isSelected){
                to_return.add(mData.get(i));
            }
        }
        return to_return;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tNewExName;
        EditText etSeries;
        EditText etBreaks;
        CheckBox cbAddExercise;

        ViewHolder(View itemView) {
            super(itemView);
            tNewExName = itemView.findViewById(R.id.tNewExName);
            etSeries = itemView.findViewById(R.id.etSeries);
            etBreaks = itemView.findViewById(R.id.etBreaks);
            cbAddExercise = itemView.findViewById(R.id.cbAddExercise);
        }
    }

    // convenience method for getting data at click position
    WorkoutExerciseSet getItem(int id) {
        return mData.get(id);
    }
}
