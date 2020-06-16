package fa.fitapp.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fa.fitapp.ExerciseType;
import fa.fitapp.R;
import fa.fitapp.ExerciseListActivity;

public class ExercisesFragment extends Fragment {

    //private ExercisesViewModel exercisesViewModel;
    private Button bChest;
    private Button bBack;
    private Button bShoulder;
    private Button bBiceps;
    private Button bTriceps;
    private Button bLegsBuns;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercises, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        //exercisesViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) { textView.setText(s);
//
//            }
//        });

        bChest = root.findViewById(R.id.bChest);
        bBack = root.findViewById(R.id.bBack);
        bShoulder = root.findViewById(R.id.bShoulder);
        bBiceps = root.findViewById(R.id.bBiceps);
        bTriceps = root.findViewById(R.id.bTriceps);
        bLegsBuns = root.findViewById(R.id.bLegsBuns);

        bChest.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.chest);
            }
        });
        bBack.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.back);
            }
        });
        bShoulder.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.shoulder);
            }
        });
        bBiceps.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.biceps);
            }
        });
        bTriceps.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.triceps);
            }
        });
        bLegsBuns.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ToExerciseActivity(v, ExerciseType.legs_buns);
            }
        });
        return root;
    }
    private void ToExerciseActivity(View v, ExerciseType ET) {
        Intent i = new Intent(getActivity(), ExerciseListActivity.class);
        i.putExtra("ExerciseType", ET);
        startActivity(i);
    }

}