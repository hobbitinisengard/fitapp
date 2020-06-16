package fa.fitapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Workout implements Parcelable {
    public long Id;
    public String Author;
    public String Name;
    public ExerciseSet[] Exercises;
    public String TypesString = "";
    public String ExerciseList = "";
    public Workout(String Name, String Author, ExerciseSet[] ES) {
        this.Name = Name;
        this.Author = Author;
        Exercises = ES;
    }
    public Workout(long Id, String Name, String Author, ExerciseSet[] ES) {
        this.Id = Id;
        this.Name = Name;
        this.Author = Author;
        Exercises = ES;
    }


    protected Workout(Parcel in) {
        Id = in.readLong();
        Author = in.readString();
        Name = in.readString();
        Exercises = in.createTypedArray(ExerciseSet.CREATOR);
        //Exercises = (ExerciseSet[]) in.readParcelableArray(ExerciseSet.class.getClassLoader());
        TypesString = in.readString();
        ExerciseList = in.readString();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.Id);
        dest.writeString(this.Author);
        dest.writeString(this.Name);
        dest.writeTypedArray(this.Exercises,0);
        //dest.writeParcelableArray(this.Exercises, 0);
        dest.writeString(this.TypesString);
        dest.writeString(this.ExerciseList);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
