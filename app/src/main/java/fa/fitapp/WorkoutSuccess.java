package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutSuccess extends AppCompatActivity {
    TextView tScore;
    TextView tTime;
    Integer Time;
    Integer Score;

    Button bEndTraining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_success);
        Intent i = getIntent();
        Time = i.getIntExtra("TimeElapsed", 0);
        Score = i.getIntExtra("Score", 0);

        tScore = findViewById(R.id.tScore);
        tTime = findViewById(R.id.tTime);
        tScore.setText(String.valueOf(Score));
        bEndTraining = findViewById(R.id.bEndTraining);
        tTime.setText(Time + " mins");
        bEndTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndTraining();
            }
        });
    }

    private void EndTraining() {
        finish();
    }
}