package fa.fitapp;

public class WorkoutExerciseSet {
    public long ExerciseID;
    public String ExerciseName;
    public byte Series;
    public byte Breaks;
    public boolean isSelected = false;

    WorkoutExerciseSet(long ExerciseID, String ExerciseName, int Series, int Breaks){
        this.ExerciseID = ExerciseID;
        this.Series = (byte)Series;
        this.Breaks = (byte)Breaks;
        this.ExerciseName = ExerciseName;
    }

    public ExerciseSet ToExerciseSet() {
        return new ExerciseSet(ExerciseID, Series, Breaks);
    }
}
