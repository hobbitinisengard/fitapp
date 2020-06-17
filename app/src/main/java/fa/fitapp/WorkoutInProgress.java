package fa.fitapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

public class WorkoutInProgress extends AppCompatActivity {
    Exercise CurrentExercise;
    FitAppDBAdapter dbAdapter;
    Workout workout;
    private int BreakTime;
    private int MaxTime;
    private int CurrentExerciseNumber = 0;
    private boolean Autoplay;
    ProgressBar progressBar;
    TextView tExerBreakCounter;
    TextView tExerBreakStatus;
    TextView tExerciseNow;
    Button bVideoNow;
    Button bDescription;
    TextView tRepsxReps;
    EditText etLoad;
    TextView tRemainingTime;
    Button bEndWorkout;
    CheckBox cbAutoplayWorkout;
    Button bNextExercise;
    Button bMarkDone;
    //Declare timer
    CountDownTimer GlobalTimer = null;
    CountDownTimer LocalTimer = null;
    long GlobalTimerValue = 0;
    long LocalTimerValue = 0;
    boolean BreakMode = false;
    byte[] kg_loads;
    void StartGlobalTimer(long millisInFuture) {
        GlobalTimer = new CountDownTimer(millisInFuture, 1000) {
            public void onTick(long millisUntilFinished) {
                GlobalTimerValue = millisUntilFinished;
                millisUntilFinished/=1000;
                int seconds = (int) (millisUntilFinished%60);
                int minutes = (int) (millisUntilFinished/60);
                tRemainingTime.setText(minutes+":"+seconds);
            }
            public void onFinish() {
                tRemainingTime.setText("0:0");
            }
        };
        GlobalTimer.start();
    }
    void StartExerciseTimer(long AdditionalTime) {
        LocalTimer = new CountUpTimer(MaxTime*60000, AdditionalTime) {
            public void onTick(int second) {
                LocalTimerValue = second*1000;
                int seconds = second%60;
                int minutes = second/60;
                tExerBreakCounter.setText(minutes+":"+seconds);
            }
        };
        LocalTimer.start();
    }
    void StopExerciseTimerStartBreakTimer(long millisInFuture) {
        if(LocalTimer != null)
            LocalTimer.cancel();

        LocalTimer = new CountDownTimer(millisInFuture, 1000) {
            public void onTick(long millisUntilFinished) {
                LocalTimerValue = millisUntilFinished;
                millisUntilFinished/=1000;
                int seconds = (int) (millisUntilFinished%60);
                int minutes = (int) (millisUntilFinished/60);
                tExerBreakCounter.setText(minutes+":"+seconds);
            }
            public void onFinish() {
                tExerBreakCounter.setText("0:0");
                if(Autoplay)
                    ToNextExerciseActivity();
            }
        };
        LocalTimer.start();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("BigCounter", GlobalTimerValue);
        outState.putLong("SmolCounter", LocalTimerValue);
        outState.putBoolean("BreakMode", BreakMode);
        outState.putBoolean("Autoplay", Autoplay);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbAdapter = new FitAppDBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_in_progress);
        Intent i = getIntent();
        workout = i.getParcelableExtra("Workout");
        BreakTime = i.getIntExtra("BreakTime", 5);
        MaxTime = i.getIntExtra("MaxTime", 60);
        Autoplay = i.getBooleanExtra("Autoplay", false);
        progressBar = findViewById(R.id.progressBar);
        tExerBreakCounter = findViewById(R.id.tBreakTime2);
        tExerBreakStatus = findViewById(R.id.tExerBreakStatus);
        tExerciseNow = findViewById(R.id.tExerciseNow);
        bVideoNow = findViewById(R.id.bVideoNow);
        bDescription = findViewById(R.id.button2);
        tRepsxReps = findViewById(R.id.tRepsxReps);
        etLoad = findViewById(R.id.etLoad);
        bMarkDone = findViewById(R.id.bMarkDone);
        tRemainingTime = findViewById(R.id.tRemainingTime);
        bEndWorkout = findViewById(R.id.bEndWorkout);
        cbAutoplayWorkout = findViewById(R.id.cbAutoplayWorkout);
        bNextExercise = findViewById(R.id.bNextExercise);

        cbAutoplayWorkout.setChecked(Autoplay);
        cbAutoplayWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    Autoplay = true;
                } else {
                    Autoplay = false;
                }
            }
        });
        bMarkDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etLoad.getText().toString()))
                    return;
                SetBreakMode();
                StopExerciseTimerStartBreakTimer(BreakTime*60000);
            }
        });
        bNextExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToNextExerciseActivity();
            }
        });
        progressBar.setMax(workout.Exercises.length);
        progressBar.setProgress(CurrentExerciseNumber);

        CurrentExercise = dbAdapter.GetExercise(workout.Exercises[CurrentExerciseNumber].exerciseID);
        tExerciseNow.setText(CurrentExercise.Name);
        bDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDescription(CurrentExercise.Description);
            }
        });
        tRepsxReps.setText(workout.Exercises[CurrentExerciseNumber].series_number+"x"+
                workout.Exercises[CurrentExerciseNumber].breaks_number);
        bVideoNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(CurrentExercise.Url);
            }
        });
        bEndWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndWorkoutAlertDialog();
            }
        });
        if(savedInstanceState != null){
            GlobalTimerValue = savedInstanceState.getLong("BigCounter");
            LocalTimerValue = savedInstanceState.getLong("SmolCounter");
            BreakMode = savedInstanceState.getBoolean("BreakMode");
            Autoplay = savedInstanceState.getBoolean("Autoplay");
            StartGlobalTimer(GlobalTimerValue);
            if(BreakMode){
                SetBreakMode();
                StopExerciseTimerStartBreakTimer(LocalTimerValue);
            }
            else
                StartExerciseTimer(LocalTimerValue);
        }
        else{
            StartGlobalTimer(MaxTime*60000);
            StartExerciseTimer(0);
        }
    }

    private void SetBreakMode() {
        BreakMode = true;
        bNextExercise.setVisibility(View.VISIBLE);
        bMarkDone.setVisibility(View.INVISIBLE);
        tExerBreakStatus.setText("Remaining break time");
    }

    private void EndWorkoutAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("End workout");
        alertDialog.setMessage("Are you sure you want to quit?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void ToNextExerciseActivity() {
        if(!TextUtils.isDigitsOnly(etLoad.getText()))
            return;
        //kg_loads[CurrentExerciseNumber] =
        byte load = Byte.parseByte(etLoad.getText().toString());
        // save current exercise to exercise history
        dbAdapter.AddExerciseToHistory(CurrentExercise.Id, load);

        // if all exercises done
        if(CurrentExerciseNumber == progressBar.getMax() - 1){
            // save workout
            dbAdapter.AddWorkoutToHistory(workout.Id, CalculateScore());
            dbAdapter.SetLastTrainingDate(dbAdapter.GetAuthorID("Kuba"), new Date());
            // go final activity
            Intent i = new Intent(this, WorkoutSuccess.class);
            i.putExtra("TimeElapsed", (int)(MaxTime - GlobalTimerValue/60000));
            i.putExtra("Score", CalculateScore());
            finish();
            startActivity(i);
        }
        else{ // run next exercise

            CurrentExerciseNumber++;
            // run exercise timer every new exercise
            LocalTimer.cancel();
            StartExerciseTimer(0);
            tExerBreakStatus.setText("Exercise time");
            bNextExercise.setVisibility(View.INVISIBLE);
            bMarkDone.setVisibility(View.VISIBLE);
            progressBar.setProgress(CurrentExerciseNumber);

            CurrentExercise = dbAdapter.GetExercise(workout.Exercises[CurrentExerciseNumber].exerciseID);
            tExerciseNow.setText(CurrentExercise.Name);
            bDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowDescription(CurrentExercise.Description);
                }
            });
            tRepsxReps.setText(workout.Exercises[CurrentExerciseNumber].series_number+"x"+
                    workout.Exercises[CurrentExerciseNumber].breaks_number);
            bVideoNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    watchYoutubeVideo(CurrentExercise.Url);
                }
            });
        }
    }

    private int CalculateScore() {
        float score = 0;
//        for(int i=0; i<workout.Exercises.length; i++){
//            score += (workout.Exercises[i].series_number * workout.Exercises[i].breaks_number * kg_loads[i])/100f;
//        }
        return Math.round(score);
    }

    public void ShowDescription(String description){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(description);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
    public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}