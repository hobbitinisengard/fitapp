package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class InitialStepWorkout extends AppCompatActivity {
    public Workout workout;
    TextView tTrainingName;
    EditText etBreakTime;
    EditText etMaxTime;
    CheckBox cbAutoplay;
    Button bStartTraining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_step_workout);
        Intent i = getIntent();
        workout = i.getParcelableExtra("Workout");
        tTrainingName = findViewById(R.id.tTrainingName);
        etBreakTime = findViewById(R.id.etBreakTime);
        etMaxTime = findViewById(R.id.etMaxTime);
        cbAutoplay = findViewById(R.id.cbAutoplay);
        bStartTraining = findViewById(R.id.bStartTraining);
        tTrainingName.setText(workout.Name);
        bStartTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToWorkoutInProgressActivity();
            }
        });
    }

    private void ToWorkoutInProgressActivity() {
        String BreakString = etBreakTime.getText().toString();
        String MaxTimeString = etMaxTime.getText().toString();
        if(TextUtils.isEmpty(BreakString) || TextUtils.isEmpty(MaxTimeString)){
            BreakString = "5";
            MaxTimeString = "60";
        }
        int BreakTime = Integer.parseInt(BreakString);
        int MaxTime = Integer.parseInt(MaxTimeString);
        boolean Autoplay = cbAutoplay.isChecked();
        Intent i = new Intent(this, WorkoutInProgress.class);
        i.putExtra("Workout", workout);
        i.putExtra("BreakTime", BreakTime);
        i.putExtra("MaxTime", MaxTime);
        i.putExtra("Autoplay", Autoplay);
        i.putExtra("LastGlobalTimerPosition", 0);
        startActivity(i);
    }

}