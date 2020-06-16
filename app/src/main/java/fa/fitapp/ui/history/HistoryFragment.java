package fa.fitapp.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import fa.fitapp.AddNewExerciseActivity;
import fa.fitapp.Exercise;
import fa.fitapp.ExerciseEditDeleteActivity;
import fa.fitapp.ExerciseListRecyclerAdapter;
import fa.fitapp.ExerciseRecord;
import fa.fitapp.ExerciseRecordRecyclerAdapter;
import fa.fitapp.ExerciseType;
import fa.fitapp.FitAppDBAdapter;
import fa.fitapp.R;

public class HistoryFragment extends Fragment implements ExerciseRecordRecyclerAdapter.ItemClickListener {

    ExerciseRecordRecyclerAdapter adapter;
    ExerciseType exerciseType;
    Toolbar mTopToolbar;
    FloatingActionButton bNewExercise;
    FitAppDBAdapter dbAdapter;
    GraphView graph;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        dbAdapter = new FitAppDBAdapter(getContext());

        super.onCreate(savedInstanceState);
        graph = (GraphView) root.findViewById(R.id.graph);
        // data to populate the RecyclerView with
        ArrayList<Exercise> ExerciseRecords = dbAdapter.GetExercises();

        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.rw_ExRecords);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ExerciseRecordRecyclerAdapter(getContext(), ExerciseRecords);
        adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) throws ParseException {
        Exercise e = adapter.getItem(position);
        ArrayList<ExerciseRecord> Records = dbAdapter.GetExerciseRecords(e.Id);
        if(Records.size() != 0)
            Toast.makeText(, )
        ArrayList<DataPoint> Points = new ArrayList<>();
        for (ExerciseRecord r : Records) {
            Points.add(new DataPoint(r.date, r.Load));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(Points.toArray(new DataPoint[Points.size()]));
        graph.removeAllSeries();
        graph.addSeries(series);
    }
}