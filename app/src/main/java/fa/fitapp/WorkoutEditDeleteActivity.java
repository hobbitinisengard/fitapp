package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutEditDeleteActivity extends AppCompatActivity {

    EditDeleteWorkoutRecyclerAdapter adapter;
    TextView tEditWorkoutName;
    Button bDeleteWorkout;
    Button bEditWorkout;
    FitAppDBAdapter dbAdapter;
    long WorkoutID;
    String WorkoutName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbAdapter = new FitAppDBAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_edit_delete);

        Intent i = getIntent();
        WorkoutID = i.getLongExtra("WorkoutID",1);
        WorkoutName = i.getStringExtra("WorkoutName");
        bEditWorkout = findViewById(R.id.bEditWorkout);
        bEditWorkout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                EditWorkout();
            }
        });
        bDeleteWorkout = findViewById(R.id.bDeleteWorkout);
        bDeleteWorkout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                RemoveWorkout();
            }
        });

        tEditWorkoutName = findViewById(R.id.tEditWorkoutName);
        tEditWorkoutName.setText(WorkoutName);
        // data to populate the RecyclerView with
        ArrayList<WorkoutExerciseSet> ExerciseFeed = dbAdapter.GetNewExerciseActivities(WorkoutID);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rwEditWorkout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new EditDeleteWorkoutRecyclerAdapter(this, ExerciseFeed);
        //adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
    private void RemoveWorkout(){
        dbAdapter.RemoveExistingWorkout(WorkoutID);
        Toast.makeText(this, "Workout deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
    private void EditWorkout(){
        ArrayList<WorkoutExerciseSet> Sets = adapter.getSelectedSets();
        if(Sets.isEmpty())
            return;
        ExerciseSet[] ES = new ExerciseSet[Sets.size()];
        for(int i=0; i<ES.length; i++){
            ES[i] = Sets.get(i).ToExerciseSet();
        }
        Workout w = new Workout(tEditWorkoutName.getText().toString(), String.valueOf(dbAdapter.GetAuthorID("Kuba")), ES);
        dbAdapter.UpdateWorkout(w, WorkoutID);
        finish();
    }
}