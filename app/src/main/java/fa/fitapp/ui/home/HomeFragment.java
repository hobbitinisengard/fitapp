package fa.fitapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import fa.fitapp.FitAppDBAdapter;
import fa.fitapp.R;

public class HomeFragment extends Fragment {

    TextView t_lastTrainingDate;
    FitAppDBAdapter dbAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dbAdapter = new FitAppDBAdapter(root.getContext());
        t_lastTrainingDate = root.findViewById(R.id.t_lastTrainingDate);
        t_lastTrainingDate.setText(dbAdapter.GetLastTrainingDate(dbAdapter.GetAuthorID("Kuba")));
        return root;
    }
}