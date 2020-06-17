package fa.fitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

public class FitAppDBAdapter {
    MyDBHelper dbHelper;
    public FitAppDBAdapter(Context context)
    {
        if(dbHelper == null){
            dbHelper = new MyDBHelper(context);

            if(!doesDatabaseExist(context, "Fitapp")) {
                // feed data
                insertUser("fitapp", (byte) 0, "00/00/0000");
                insertUser("Kuba", (byte) 83, "16/05/2019");
                insertNewExercise(new Exercise("Lunges with dumbbells", ExerciseType.legs_buns, "The lunge is basically a giant step forward. Although the lunge exercise can be done without weights, a lunge with weights such as dumbbells provides additional work for the upper leg muscles and the muscles of the buttocks. Lunges with weights require good balance, so if you have issues keeping your balance, start off by doing the exercise without weights as you learn the proper form. This functional exercise is a great addition to any lower body strengthening routine as well as circuit training workouts. ", "https://www.youtube.com/watch?v=fqymGym7YL0", "Kuba"));
                insertNewExercise(new Exercise("45 Degree Leg Press", ExerciseType.legs_buns, "The 45-degree leg press machine is an outstanding compound push exercise to target the quadriceps and glutes. This plate-loaded machine can be found in even the most hardcore gyms, using a lever or sled apparatus to hold the weight.", "https://www.youtube.com/watch?v=OqjdUTh-GEE", "Kuba"));
                insertNewExercise(new Exercise("Barbell Lunge", ExerciseType.legs_buns, "The barbell forward lunge is a popular lower-body exercise targeting the quads, glutes, and hamstrings. Using a barbell allows you to overload the exercise beyond body weight and perform the movement in strength or muscle-focused rep ranges. The forward lunge can be performed as part of a barbell complex, in a circuit, or on its own in the lower-body portion of any workout.", "https://www.youtube.com/watch?v=pEN-dCjh5cQ", "fitapp"));
                insertNewExercise(new Exercise("Barbell Bench Press", ExerciseType.chest, "The barbell bench press is a classic exercise popular among all weight lifting circles. ... By performing the bench press, you primarily work your pectoralis major (your chest). Other muscles which assist in moving the barbell during a bench press are other muscles of the chest, triceps, and shoulders.", "https://www.youtube.com/watch?v=9n4lgCrxr0I", "fitapp"));
                insertNewExercise(new Exercise("Incline Bench Press", ExerciseType.chest, "The purpose of the incline press is to focus more of the work on the upper pecs. The main benefit in performing incline presses is to develop the upper portion of the pectoral muscles. When the bench is set at an incline (15 to 30 degrees), you activate your shoulders more since it's comparable to a shoulder press.", "https://www.youtube.com/watch?v=xuc0O4ZziaM", "fitapp"));
                insertNewExercise(new Exercise("Dumbbell Flys", ExerciseType.chest, "The dumbbell flye targets all areas of the pecs, but most significantly the sternal fibres – those that attach directly to your sternum. Growth in this particular area creates the defined “chest separation” look. The move is also better at stimulating chest fibres across the spectrum than the flat bench press. What’s more, you don’t have to lift anywhere near as heavy, and since the benches at any gym fill up quickly, this offers a viable alternative.", "https://www.youtube.com/watch?v=pzb2P55g8vc", "fitapp"));
                insertNewExercise(new Exercise("Barbell Shrug", ExerciseType.back, "Small movements can result in big gains, and the barbell shrug demonstrates this perhaps better than any other exercise. Since the movement involved is so small, you can load up a lot of weight on your bar, and all that weight means the strength and power gains in your upper back will be considerable.", "https://www.youtube.com/watch?v=9xGqgGFAtiM", "fitapp"));
                insertNewExercise(new Exercise("Chin Up", ExerciseType.back, "The chin-up is a strength training exercise. People frequently do this exercise with the intention of strengthening muscles such as the latissimus dorsi and biceps, which extend the shoulder and flex the elbow, respectively. In this maneuver, the palms are faced towards the body.", "https://www.youtube.com/watch?v=HkZH3rgnTUo", "fitapp"));
                insertNewExercise(new Exercise("Seated Cable Row", ExerciseType.back, "The seated cable row develops the muscles of the back and the forearms. It is an excellent all-around compound exercise for developing mainly the middle back while offering useful arm work as well. The seated cable row is performed on a weighted horizontal cable machine with a bench and foot plates. This can be a stand-alone piece of equipment or part of a multi-gym. It can be used as part of an upper body strength workout. ", "https://www.youtube.com/watch?v=SXkVJJUO3hY", "fitapp"));
                insertNewExercise(new Exercise("Standing Barbell Curl", ExerciseType.biceps, "A barbell curl is a pull-type, isolation exercise which works primarily your biceps, but also trains muscles in your forearms and shoulders to some degree, as well. ", "https://www.youtube.com/watch?v=rOY9-_qysgc", "fitapp"));
                insertNewExercise(new Exercise("Standing Hammer Curl", ExerciseType.biceps, "The hammer curl is a great exercise for the biceps and forearms. Set up by grasping a set of dumbbells and standing straight up with the dumbbells by your sides. ... Bend your arms slightly to take tension into the biceps. Keeping your body fixed and elbows in at your sides, slowly curl the dumbbells up as far as possible.", "https://www.youtube.com/watch?v=_qAeN7MlScc", "fitapp"));
                insertNewExercise(new Exercise("Cable Curl", ExerciseType.biceps, "The cable straight-bar biceps curl takes a classic bodybuilding exercise, the standing barbell curl, and replaces the barbell with a handle attached to a cable. This is a common burnout move performed for moderate to high reps, such as 8-12 reps per set, as part of an upper-body or arm-focused workout. ", "https://www.youtube.com/watch?v=KtiFlsbjG8U", "fitapp"));
                insertNewExercise(new Exercise("Back Barbell Press", ExerciseType.shoulder, "Grip the bar, hands wider than shoulder-width and palms facing forward. Move the barbell out of the rack and hold it at chin-level. Brace your core, squeeze your shoulder blades together, and point your elbows forward. Exhale and extend your arms to press the barbell upward, lining it up with your head", "https://www.youtube.com/watch?v=stmLGecD0UI", "fitapp"));
                insertNewExercise(new Exercise("Seated French Press", ExerciseType.triceps, "Lift the bar above your head and bend at your elbows slightly to take the tension onto your triceps. Your palms are now facing upward. ... Keeping your elbows fixed and pointing straight up toward the ceiling, slowly lower the bar down behind your head as far as comfortably possible.", "https://www.youtube.com/watch?v=PtHYHQVWCz8", "fitapp"));
                insertNewWorkout(new Workout("LegsWorkout", "fitapp",
                        new ExerciseSet[]{
                                new ExerciseSet(1, 12, 4),
                                new ExerciseSet(2, 8, 3),
                                new ExerciseSet(3, 7, 4),
                        }));
                insertNewWorkout(new Workout("Chest and back", "fitapp",
                        new ExerciseSet[]{
                                new ExerciseSet(4, 12, 4),
                                new ExerciseSet(5, 8, 3),
                                new ExerciseSet(6, 5, 4),
                        }));
                insertNewWorkout(new Workout("Biceps and triceps", "fitapp",
                        new ExerciseSet[]{
                                new ExerciseSet(7, 12, 4),
                                new ExerciseSet(8, 8, 3),
                                new ExerciseSet(9, 10, 5),
                        }));
                insertExerciseRecord(new ExerciseRecord(3, "01/12/2018", (byte)10));
                insertExerciseRecord(new ExerciseRecord(3, "01/01/2019", (byte)10));
                insertExerciseRecord(new ExerciseRecord(3, "01/02/2019", (byte)10));
                insertExerciseRecord(new ExerciseRecord(3, "01/03/2019", (byte)11));
                insertExerciseRecord(new ExerciseRecord(3, "01/04/2019", (byte)12));
                insertExerciseRecord(new ExerciseRecord(3, "01/05/2019", (byte)12));
                insertExerciseRecord(new ExerciseRecord(3, "01/06/2019", (byte)14));
                insertExerciseRecord(new ExerciseRecord(3, "01/07/2019", (byte)13));
                insertExerciseRecord(new ExerciseRecord(3, "01/08/2019", (byte)15));
                insertExerciseRecord(new ExerciseRecord(3, "01/09/2019", (byte)15));
            }
        }

    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
    public void insertUser(String name, byte weight, String dateString) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Weight", weight);
        contentValues.put("LastWorkout", dateString);
        db.insert("USER", null , contentValues);
    }
    public long GetAuthorID(String Author)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "Name"};
        Cursor cursor = db.query("USER", columns, "Name = ?", new String[]{Author}, null,null,null);
        cursor.moveToNext();
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }
    public String GetAuthorName(long UserId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "Name"};
        Cursor cursor = db.query("USER", columns, "_id = ?", new String[]{String.valueOf(UserId)}, null,null,null);
        cursor.moveToNext();
        String name =cursor.getString(cursor.getColumnIndex("Name"));
        cursor.close();
        return name;
    }
    public void insertNewExercise(Exercise e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", e.Name);
        contentValues.put("Author", GetAuthorID(e.Author));
        contentValues.put("Description", e.Description);
        contentValues.put("Link", e.Url);
        contentValues.put("Type", e.Type.toString());
        db.insert("EXERCISE", null , contentValues);
    }
    public void insertExerciseRecord(ExerciseRecord er){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ExerciseID", er.ExerciseID);
        contentValues.put("Date", String.valueOf(er.date));
        contentValues.put("Load", er.Load);
        db.insert("EXERCISE_HISTORY", null , contentValues);
    }
    public void RemoveExistingExercise(long exerciseID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String table = "EXERCISE";
        String whereClause = "_id=?";
        String[] whereArgs = new String[] { String.valueOf(exerciseID)};
        db.delete(table, whereClause, whereArgs);
    }



   public ArrayList<Exercise> GetExercises(ExerciseType exerciseType) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("EXERCISE",null,"Type=?",new String[]{exerciseType.toString()},null,null,null);
        ArrayList<Exercise> to_return = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String description =cursor.getString(cursor.getColumnIndex("Description"));
            String author = GetAuthorName(cursor.getLong(cursor.getColumnIndex("Author")));
            String link = cursor.getString(cursor.getColumnIndex("Link"));
            ExerciseType type = ExerciseType.valueOf(cursor.getString(cursor.getColumnIndex("Type")));

            to_return.add(new Exercise(cid, name, type, description, link, author));
        }
        cursor.close();
        return to_return;
    }
    public ArrayList<Exercise> GetExercises() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("EXERCISE",null,null,null,null,null,null);
        ArrayList<Exercise> to_return = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String description =cursor.getString(cursor.getColumnIndex("Description"));
            String author = GetAuthorName(cursor.getLong(cursor.getColumnIndex("Author")));
            String link = cursor.getString(cursor.getColumnIndex("Link"));
            ExerciseType type = ExerciseType.valueOf(cursor.getString(cursor.getColumnIndex("Type")));

            to_return.add(new Exercise(cid, name, type, description, link, author));
        }
        cursor.close();
        return to_return;
    }

    public Exercise GetExercise(long exerciseId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("EXERCISE",null,"_id=?",new String[]{String.valueOf(exerciseId)},null,null,null);
        cursor.moveToNext();
        long cid =cursor.getLong(cursor.getColumnIndex("_id"));
        String name = cursor.getString(cursor.getColumnIndex("Name"));
        String description =cursor.getString(cursor.getColumnIndex("Description"));
        String author = cursor.getString(cursor.getColumnIndex("Author"));
        String link = cursor.getString(cursor.getColumnIndex("Link"));
        ExerciseType type = ExerciseType.valueOf(cursor.getString(cursor.getColumnIndex("Type")));
        cursor.close();
        return new Exercise(cid, name, type, description, link, author);
    }
    public void UpdateExercise(Exercise e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", e.Name);
        contentValues.put("Author", e.Author);
        contentValues.put("Description", e.Description);
        contentValues.put("Link", e.Url);
        contentValues.put("Type", e.Type.toString());
        String[] whereArgs= {String.valueOf(e.Id)};
        db.update("EXERCISE",contentValues, "_id = ?",whereArgs );
    }
    public void UpdateWorkout(Workout w, long WorkoutID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", w.Name);
        contentValues.put("Author", w.Author);
        String[] whereArgs= {String.valueOf(WorkoutID)};
        db.update("WORKOUT",contentValues, "_id = ?",whereArgs );
        UpdateExercises(WorkoutID, w);
    }

    private void UpdateExercises(long workoutID, Workout w) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //db.delete("WORKOUT_EXERCISE", "WorkoutID=?", new String[]{String.valueOf(workoutID)});
        for(ExerciseSet es : w.Exercises) {
            contentValues.put("Series", es.series_number);
            contentValues.put("Breaks", es.breaks_number);
            db.update("WORKOUT_EXERCISE", contentValues, "WorkoutID=? and ExID=?", new String[]{String.valueOf(workoutID), String.valueOf(es.exerciseID)});
            contentValues.clear();
        }
    }

    public void insertNewWorkout(Workout w)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", w.Name);
        contentValues.put("Author", GetAuthorID(w.Author));
        long workoutID = db.insert("WORKOUT", null , contentValues);
        insertExercises(workoutID, w);
    }
    private void insertExercises(long workoutID, Workout w){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(ExerciseSet es : w.Exercises) {
            contentValues.put("WorkoutID", workoutID);
            contentValues.put("ExID", es.exerciseID);
            contentValues.put("Series", es.series_number);
            contentValues.put("Breaks", es.breaks_number);
            db.insert("WORKOUT_EXERCISE", null , contentValues);
            contentValues.clear();
        }
    }
    public void RemoveExistingWorkout(long workoutID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String table = "WORKOUT";
        String whereClause = "_id=?";
        String[] whereArgs = new String[] { String.valueOf(workoutID)};
        db.delete(table, whereClause, whereArgs);
    }


    public String getNow() {
        Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
        Calendar cal = Calendar.getInstance();
        cal.setTime(today); // don't forget this if date is arbitrary e.g. 01-01-2014
        String Day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if(Day.length() == 1)
            Day = "0"+Day;
        String Month = String.valueOf(cal.get(Calendar.MONTH));
        if(Month.length() == 1)
            Month = "0"+Month;
        String Year = String.valueOf(cal.get(Calendar.YEAR));
        return Day + "/" + Month + "/" + Year;
    }

    public ArrayList<Workout> GetWorkouts() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("WORKOUT",null,null, null,null,null,null);
        ArrayList<Workout> workoutArray = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String author = GetAuthorName(cursor.getLong(cursor.getColumnIndex("Author")));
            Cursor exercises_cursor =db.query("WORKOUT_EXERCISE",null,"WorkoutID=?", new String[]{String.valueOf(cid)},null,null,null);
            ArrayList<ExerciseSet> ExerciseSet = new ArrayList<>();
            while(exercises_cursor.moveToNext())
            {
                long exId = exercises_cursor.getLong(exercises_cursor.getColumnIndex("ExID"));
                int series = exercises_cursor.getInt(exercises_cursor.getColumnIndex("Series"));
                int breaks = exercises_cursor.getInt(exercises_cursor.getColumnIndex("Breaks"));
                ExerciseSet.add(new ExerciseSet(exId, series, breaks));
            }
            workoutArray.add(new Workout(cid, name, author, (ExerciseSet[]) ExerciseSet.toArray(new ExerciseSet[ExerciseSet.size()])));
        }
        for(int i=0; i<workoutArray.size(); i++){
            SetWorkoutDescriptionStrings(workoutArray.get(i));
        }
        cursor.close();
        return workoutArray;
    }
    public void SetWorkoutDescriptionStrings(Workout w){
        ArrayList<String> Types = new ArrayList<>();
        for(int j = 0; j< w.Exercises.length; j++){
            Exercise e = GetExercise(w.Exercises[j].exerciseID);
            if(!Types.contains(e.Type.toString())){
                Types.add(e.Type.toString());
                w.TypesString += e.Type.toString() + " ";
            }
            w.ExerciseList += (j + 1) + ". " + e.Name + " " + w.Exercises[j].series_number + "x"+w.Exercises[j].breaks_number+"\n";
        }
    }
    public ArrayList<WorkoutExerciseSet> GetNewExerciseActivities() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] cols = new String[]{"_id", "Name"};
        Cursor cursor =db.query("EXERCISE",cols,null,null,null,null,null);
        ArrayList<WorkoutExerciseSet> to_return = new ArrayList<WorkoutExerciseSet>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            to_return.add(new WorkoutExerciseSet(cid, name, 0,0));
        }
        cursor.close();
        return to_return;
    }
    public ArrayList<WorkoutExerciseSet> GetNewExerciseActivities(long WorkoutID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] cols = new String[]{"_id", "Name"};
        Cursor cursor =db.query("EXERCISE",cols,null,null,null,null,null);
        ArrayList<WorkoutExerciseSet> to_return = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            to_return.add(new WorkoutExerciseSet(cid, name, 0,0));
        }
        SetSeriesBreaksAndTicks(to_return, WorkoutID);
        cursor.close();
        return to_return;
    }
    private void SetSeriesBreaksAndTicks(ArrayList<WorkoutExerciseSet> sets, long WorkoutID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(WorkoutExerciseSet wes : sets){
            Cursor cursor = db.query("WORKOUT_EXERCISE",null,"WorkoutID=? AND ExID=?",new String[]{String.valueOf(WorkoutID), String.valueOf(wes.ExerciseID)},null,null,null);
            if(cursor.moveToNext()){
                wes.Series = (byte) cursor.getInt(cursor.getColumnIndex("Series"));
                wes.Breaks = (byte) cursor.getInt(cursor.getColumnIndex("Breaks"));
                if(wes.Series != 0 && wes.Breaks != 0)
                    wes.isSelected = true;
            }
            cursor.close();
        }

    }
    public void AddWorkoutToHistory(long workoutID, int score){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("WorkoutID", workoutID);
        contentValues.put("Date", getNow());
        contentValues.put("Score", score);
        db.insert("WORKOUT_HISTORY", null , contentValues);
    }
    public void AddExerciseToHistory(long exerciseID, byte load){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ExerciseID", exerciseID);
        contentValues.put("Date", getNow());
        contentValues.put("Load", load);
        db.insert("EXERCISE_HISTORY", null , contentValues);
    }

    public ArrayList<ExerciseRecord> GetExerciseRecords(long exID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("EXERCISE_HISTORY",null,"ExerciseID=?",new String[]{String.valueOf(exID)},null,null,null);
        ArrayList<ExerciseRecord> to_return = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            long ExID = cursor.getLong(cursor.getColumnIndex("ExerciseID"));
            String date =cursor.getString(cursor.getColumnIndex("Date"));
            byte Load = (byte)cursor.getInt(cursor.getColumnIndex("Load"));

            to_return.add(new ExerciseRecord(cid, ExID, date, Load));
        }
        cursor.close();
        return to_return;
    }

    public ArrayList<WorkoutRecord> GetWorkoutRecords(long workoutID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("WORKOUT_HISTORY",null,"WorkoutID=?",new String[]{String.valueOf(workoutID)},null,null,null);
        ArrayList<WorkoutRecord> to_return = new ArrayList<>();
        while (cursor.moveToNext())
        {
            long cid =cursor.getLong(cursor.getColumnIndex("_id"));
            long WorkoutID = cursor.getLong(cursor.getColumnIndex("WorkoutID"));
            String date =cursor.getString(cursor.getColumnIndex("Date"));
            int Score = cursor.getInt(cursor.getColumnIndex("Score"));

            to_return.add(new WorkoutRecord(cid, WorkoutID, date, Score));
        }
        cursor.close();
        return to_return;
    }

    public String GetLastTrainingDate(long UserID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.query("USER",null,"_id=?",new String[]{String.valueOf(UserID)},null,null,null);
        cursor.moveToNext();
        String LastDate = cursor.getString(cursor.getColumnIndex("LastWorkout"));
        cursor.close();
        return LastDate;
    }
    public void SetLastTrainingDate(long UserID, Date date) {
        String dateString = date.getDate() + "/" + (date.getMonth()+1) + "/" + (1900+date.getYear());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LastWorkout", dateString);
        db.update("USER", contentValues, "_id=?", new String[]{String.valueOf(UserID)});
    }


    static class MyDBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "Fitapp";
        private static final int DATABASE_Version = 1;

        private static final String CREATE_TABLE_USERS = "CREATE TABLE USER (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Name VARCHAR(128) UNIQUE, Weight TINYINT, LastWorkout VARCHAR(10));";

        private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE EXERCISE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Name VARCHAR(255), Description VARCHAR(1024), Author INTEGER NOT NULL REFERENCES USER(_id), Type VARCHAR(32), Link VARCHAR(255));";

        private static final String CREATE_TABLE_WORKOUT = "CREATE TABLE WORKOUT (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Name VARCHAR(255), Author INTEGER NOT NULL REFERENCES USER(_id));";

        private static final String CREATE_TABLE_WORKOUT_EXERCISES = "CREATE TABLE WORKOUT_EXERCISE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "WorkoutID INTEGER NOT NULL REFERENCES WORKOUT(_id), ExID INTEGER NOT NULL REFERENCES EXERCISE(_id), Series TINYINT, Breaks TINYINT);";

        private static final String CREATE_TABLE_WORKOUTS_DONE = "CREATE TABLE WORKOUT_HISTORY (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "WorkoutID INTEGER NOT NULL REFERENCES WORKOUT(_id), Date VARCHAR(10), Score INT);";

        private static final String CREATE_TABLE_EXERCISE_RECORDS = "CREATE TABLE EXERCISE_HISTORY (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ExerciseID INTEGER NOT NULL REFERENCES EXERCISE(_id), Date VARCHAR(10), Load TINYINT);";

        private Context context;

        public MyDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_USERS);
                db.execSQL(CREATE_TABLE_EXERCISES);
                db.execSQL(CREATE_TABLE_WORKOUT);
                db.execSQL(CREATE_TABLE_WORKOUT_EXERCISES);
                db.execSQL(CREATE_TABLE_WORKOUTS_DONE);
                db.execSQL(CREATE_TABLE_EXERCISE_RECORDS);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL("DROP TABLE IF EXISTS USER");
                db.execSQL("DROP TABLE IF EXISTS EXERCISE");
                db.execSQL("DROP TABLE IF EXISTS WORKOUT");
                db.execSQL("DROP TABLE IF EXISTS WORKOUT_EXERCISE");
                db.execSQL("DROP TABLE IF EXISTS HISTORY");
                db.execSQL("DROP TABLE IF EXISTS EXERCISE_RECORD");
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }
}
