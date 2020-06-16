package fa.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExerciseEditDeleteActivity extends AppCompatActivity {

    EditText etName;
    EditText etDesc;
    EditText etYTLink;
    Button bAddNew;
    Button bDeletethis;
    Exercise e;
    FitAppDBAdapter dbAdapter;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbAdapter = new FitAppDBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_edit_delete);

        bAddNew = findViewById(R.id.bAddNewExercise);
        bDeletethis = findViewById(R.id.bDeletethis);
        etYTLink = findViewById(R.id.etLink);
        etDesc = findViewById(R.id.etDesc);
        etName = findViewById(R.id.etName);

        i = getIntent();
        long ExerciseId = i.getLongExtra("ExId", 1);
        e = dbAdapter.GetExercise(ExerciseId);
        etName.setText(e.Name);
        etDesc.setText( e.Description);
        etYTLink.setText(e.Url);

//        toolbar = findViewById(R.id.toolbarExercises);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Edit exercise for " + e.Type.toString());

        bAddNew.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ApplyChanges();
            }
        });
        bDeletethis.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    Deletethis();
                }
        });
    }

    private void Deletethis() {
        dbAdapter.RemoveExistingExercise(e.Id);
        Toast.makeText(this, "Exercise deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void ApplyChanges() {
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();
        String link = etYTLink.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(link))
            return;
        Toast.makeText(this, "Exercise edited", Toast.LENGTH_SHORT).show();
        setResult(1, i);
        e.Name = name;
        e.Description = desc;
        e.Url = link;
        dbAdapter.UpdateExercise(e);
        finish();
    }


}