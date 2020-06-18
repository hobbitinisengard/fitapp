package fa.fitapp.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fa.fitapp.AddNewWorkoutActivity;
import fa.fitapp.Exercise;
import fa.fitapp.ExerciseEditDeleteActivity;
import fa.fitapp.ExerciseListRecyclerAdapter;
import fa.fitapp.FitAppDBAdapter;
import fa.fitapp.R;
import fa.fitapp.WorkoutEditDeleteActivity;
import fa.fitapp.WorkoutListRecyclerAdapter;
import fa.fitapp.Workout;

public class TrainingFragment extends Fragment implements WorkoutListRecyclerAdapter.ItemClickListener {

    private TrainingViewModel trainingViewModel;

    WorkoutListRecyclerAdapter adapter;
    FloatingActionButton bNewWorkout;
    FitAppDBAdapter dbAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbAdapter = new FitAppDBAdapter(getContext());
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_training, container, false);

        bNewWorkout = root.findViewById(R.id.bAddWorkout);
        bNewWorkout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToAddNewWorkoutActivity();
            }
        });
        // data to populate the RecyclerView with
        ArrayList<Workout> WorkoutFeed = dbAdapter.GetWorkouts();

        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.rwWorkoutList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new WorkoutListRecyclerAdapter(getContext(), WorkoutFeed);
        adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return root;
    }


    private void ToAddNewWorkoutActivity() {
        Intent i = new Intent(getContext(), AddNewWorkoutActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(View view, int position) {
        Workout e = adapter.getItem(position);
        // do not allow for editing/deleting original exercises
        if(e.Author.equals("fitapp"))
            return;
        Intent i = new Intent(getContext(), WorkoutEditDeleteActivity.class);
        i.putExtra("WorkoutID", e.Id);
        i.putExtra("WorkoutName", e.Name);
        startActivityForResult(i, 1);
    }
}