package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddNewWorkoutActivity extends AppCompatActivity {

    AddNewWorkoutRecyclerAdapter adapter;
    EditText etNewWorkoutName;
    Button bAddNew;
    FitAppDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbAdapter = new FitAppDBAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_workout);

        bAddNew = findViewById(R.id.bAddNew);
        bAddNew.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                CreateNewWorkout();
            }
        });
        etNewWorkoutName = findViewById(R.id.etNewWorkoutName);
        // data to populate the RecyclerView with
        ArrayList<WorkoutExerciseSet> ExerciseFeed = dbAdapter.GetNewExerciseActivities();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rwExercisesToWorkout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AddNewWorkoutRecyclerAdapter(this, ExerciseFeed);
        //adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void CreateNewWorkout() {
        ArrayList<WorkoutExerciseSet> Sets = adapter.getSelectedSets();
        if(TextUtils.isEmpty(etNewWorkoutName.getText().toString()) || Sets.isEmpty())
            return;
        ExerciseSet[] ES = new ExerciseSet[Sets.size()];
        for(int i=0; i<ES.length; i++){
            ES[i] = Sets.get(i).ToExerciseSet();
        }
        Workout w = new Workout(etNewWorkoutName.getText().toString(), "Kuba", ES);
        dbAdapter.insertNewWorkout(w);
        finish();
    }
}