package fa.fitapp.ui.exercises;

import android.graphics.Bitmap;

public class Exercise {
    public String Name;
    public Author Author;
    public ExerciseType Type;
    public String Description;
    public Bitmap Image;
    Exercise(String name, ExerciseType type, String description, Bitmap img){
        Name = name;
        Author = fa.fitapp.ui.exercises.Author.standard;
        Type = type;
        Description = description;
        Image = img;
    }

}
