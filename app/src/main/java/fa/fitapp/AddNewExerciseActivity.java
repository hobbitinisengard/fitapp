package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewExerciseActivity extends AppCompatActivity {

    //Toolbar toolbar;
    EditText etName;
    EditText etDesc;
    EditText etYTLink;
    Button bAddNew;
    ExerciseType exerciseType;
    FitAppDBAdapter dbAdapter;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbAdapter = new FitAppDBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_exercise);

        i = getIntent();
        exerciseType = (ExerciseType) i.getSerializableExtra("ExerciseType");

        //toolbar = findViewById(R.id.toolbarExercises);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("New exercise for " + exerciseType.toString());

        bAddNew = findViewById(R.id.bAddNewExercise);
        etYTLink = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etName = findViewById(R.id.etName);

        bAddNew.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AddNewExercise();
            }

        });
    }

    private void AddNewExercise() {
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();
        String link = etYTLink.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(link))
            return;
        setResult(1, i);
        Exercise e = new Exercise(name, exerciseType, desc, link, "Kuba");
        //Add to DATABASE
        dbAdapter.insertNewExercise(e);
        finish();
    }

}