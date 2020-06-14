package fa.fitapp.ui.exercises;

import android.graphics.Bitmap;

public class Exercise {
    public String Name;
    public String Author;
    public ExerciseType Type;
    public String Description;
    public String Url;
    Exercise(String name, ExerciseType type, String description, String video_url, String author){
        Name = name;
        Author = author;
        Type = type;
        Description = description;
        Url = video_url;
    }

}
