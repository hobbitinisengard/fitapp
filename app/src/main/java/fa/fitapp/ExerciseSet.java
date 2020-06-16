package fa.fitapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseSet implements Parcelable {
    public long exerciseID;
    public byte series_number;
    public byte breaks_number;

    ExerciseSet(long exerciseID, int series, int breaks){
        this.exerciseID = exerciseID;
        this.series_number = (byte)series;
        this.breaks_number = (byte)breaks;
    }

    protected ExerciseSet(Parcel in) {
        exerciseID = in.readLong();
        series_number = in.readByte();
        breaks_number = in.readByte();
    }

    public static final Creator<ExerciseSet> CREATOR = new Creator<ExerciseSet>() {
        @Override
        public ExerciseSet createFromParcel(Parcel in) {
            return new ExerciseSet(in);
        }

        @Override
        public ExerciseSet[] newArray(int size) {
            return new ExerciseSet[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.exerciseID);
        dest.writeByte(this.series_number);
        dest.writeByte(this.breaks_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
