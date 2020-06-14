package fa.fitapp.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import fa.fitapp.R;

public class ExerciseListActivity extends AppCompatActivity implements ExerciseListRecyclerAdapter.ItemClickListener {

    ExerciseListRecyclerAdapter adapter;
    ExerciseType exerciseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exerciseslist);
        // get type of exercises
        // !for testing only legs!
        Intent i = getIntent();
        exerciseType = (ExerciseType) i.getSerializableExtra("ExerciseType");

        // data to populate the RecyclerView with
        ArrayList<Exercise> ExerciseFeed = new ArrayList<>();
        ExerciseFeed.add(new Exercise("Lunges with dumbbells", exerciseType, "The lunge is basically a giant step forward. Although the lunge exercise can be done without weights, a lunge with weights such as dumbbells provides additional work for the upper leg muscles and the muscles of the buttocks. Lunges with weights require good balance, so if you have issues keeping your balance, start off by doing the exercise without weights as you learn the proper form. This functional exercise is a great addition to any lower body strengthening routine as well as circuit training workouts. ", "https://www.youtube.com/watch?v=fqymGym7YL0", "fitapp"));
        ExerciseFeed.add(new Exercise("45 Degree Leg Press", exerciseType, "The 45-degree leg press machine is an outstanding compound push exercise to target the quadriceps and glutes. This plate-loaded machine can be found in even the most hardcore gyms, using a lever or sled apparatus to hold the weight.","https://www.youtube.com/watch?v=OqjdUTh-GEE", "fitapp"));
        ExerciseFeed.add(new Exercise("Barbell Lunge", exerciseType, "The barbell forward lunge is a popular lower-body exercise targeting the quads, glutes, and hamstrings. Using a barbell allows you to overload the exercise beyond body weight and perform the movement in strength or muscle-focused rep ranges. The forward lunge can be performed as part of a barbell complex, in a circuit, or on its own in the lower-body portion of any workout.", "https://www.youtube.com/watch?v=pEN-dCjh5cQ", "fitapp"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rwExerciseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ExerciseListRecyclerAdapter(this, ExerciseFeed);
        adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    // onclick przycisku Add
//    public void AddQuestion(View v){
//        Intent intent = new Intent();
//        intent.putExtra("QuestionText", EditQuestionText.getText().toString());
//        intent.putExtra("IsAnswerTrue", isAnswerTrueSwitch.isChecked());
//        setResult(1, intent);
//        finish();
//    }

}
