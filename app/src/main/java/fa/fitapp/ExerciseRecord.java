package fa.fitapp;

import java.util.Calendar;
import java.util.Date;

public class ExerciseRecord implements Comparable<ExerciseRecord>{
    public long _id;
    public long ExerciseID;
    public String date;
    public byte Load;
    public ExerciseRecord(long cid, long ExerciseID, String date, byte Load){
        this._id = cid;
        this.ExerciseID = ExerciseID;
        this.date = date;
        this.Load = Load;
    }
    public ExerciseRecord(long ExerciseID, String date, byte Load){
        this.ExerciseID = ExerciseID;
        this.date = date;
        this.Load = Load;
    }

    @Override
    public int compareTo(ExerciseRecord o) {
        return String2Date(date).compareTo(String2Date(o.date));
    }

    public Date String2Date(String str){
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(str.substring(6));
        int month = Integer.parseInt(str.substring(3,4))-1;
        int day = Integer.parseInt(str.substring(0,2));
        cal.set(year, month, day);
        return cal.getTime();
    }
}
