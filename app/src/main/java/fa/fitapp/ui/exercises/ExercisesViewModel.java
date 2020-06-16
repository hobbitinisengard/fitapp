package fa.fitapp.ui.exercises;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import fa.fitapp.Exercise;

public class ExercisesViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Exercise>> mExercises;
    public ExercisesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Exercises library");
    }

    public LiveData<String> getText() {
        return mText;
    }
}