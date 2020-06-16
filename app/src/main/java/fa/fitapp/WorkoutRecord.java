package fa.fitapp;

import java.util.Date;

public class WorkoutRecord {
    public long id;
    public long WorkoutID;
    public Date WorkoutDate;
    public int Score;
    public WorkoutRecord(long id, long WorkoutID, Date WorkoutDate, int Score){
        this.id = id;
        this.WorkoutID = WorkoutID;
        this.WorkoutDate = WorkoutDate;
        this.Score = Score;
    }
}
