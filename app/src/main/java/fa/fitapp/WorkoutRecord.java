package fa.fitapp;

import java.util.Date;

public class WorkoutRecord {
    public long id;
    public long WorkoutID;
    public String Date;
    public int Score;
    public WorkoutRecord(long id, long WorkoutID, String Date, int Score){
        this.id = id;
        this.WorkoutID = WorkoutID;
        this.Date = Date;
        this.Score = Score;
    }
}
