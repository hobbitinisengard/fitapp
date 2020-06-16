package fa.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class ExerciseListActivity extends AppCompatActivity implements ExerciseListRecyclerAdapter.ItemClickListener {

    ExerciseListRecyclerAdapter adapter;
    ExerciseType exerciseType;
    Toolbar mTopToolbar;
    FloatingActionButton bNewExercise;
    FitAppDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbAdapter = new FitAppDBAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseslist);

        Intent i = getIntent();
        exerciseType = (ExerciseType) i.getSerializableExtra("ExerciseType");

        mTopToolbar = findViewById(R.id.toolbarExercises);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setTitle(exerciseType.toString());

        bNewExercise = findViewById(R.id.bAddExercise);
        bNewExercise.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToAddNewExerciseActivity();
            }
        });
        // data to populate the RecyclerView with
        ArrayList<Exercise> ExerciseFeed = dbAdapter.GetExercises(exerciseType);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rwExerciseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ExerciseListRecyclerAdapter(this, ExerciseFeed);
        adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    private void ToAddNewExerciseActivity() {
        Intent i = new Intent(this, AddNewExerciseActivity.class);
        i.putExtra("ExerciseType", exerciseType);
        startActivityForResult(i, 1);
    }

    @Override
    public void onItemClick(View view, int position) {
        Exercise e = adapter.getItem(position);
        // do not allow for editing/deleting original exercises
        if(e.Author.equals("fitapp"))
            return;

        Intent i = new Intent(this, ExerciseEditDeleteActivity.class);
        i.putExtra("ExId", e.Id);
        startActivityForResult(i,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Refresh();
    }
    private void Refresh() {
        Intent intent = getIntent();
        intent.putExtra("ExerciseType", exerciseType);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
