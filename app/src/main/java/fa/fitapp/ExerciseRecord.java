package fa.fitapp;

import java.util.Date;

public class ExerciseRecord {
    public long _id;
    public long ExerciseID;
    public Date date;
    public byte Load;
    public ExerciseRecord(long cid, long ExerciseID, Date date, byte Load){
        this._id = cid;
        this.ExerciseID = ExerciseID;
        this.date = date;
        this.Load = Load;
    }
    public ExerciseRecord(long ExerciseID, Date date, byte Load){
        this.ExerciseID = ExerciseID;
        this.date = date;
        this.Load = Load;
    }
}
