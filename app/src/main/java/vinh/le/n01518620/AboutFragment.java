//Vinh Le
//N01518620
package vinh.le.n01518620;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {

    private static final String COUNTER_KEY = "counter";
    private int counter = 1;
    private SharedPreferences sharedPreferences;
    private TextView firstNameTextView, lastNameTextView;
    private ToggleButton orientationToggleButton;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Initialize views
        firstNameTextView = view.findViewById(R.id.firstNameTextView);
        lastNameTextView = view.findViewById(R.id.lastNameTextView);
        orientationToggleButton = view.findViewById(R.id.orientationToggleButton);

        // Restore counter if it exists
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY);
        }

        // Display counter and full name in a toast
        displayCounterAndFullName();

        // Load SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", 0);

        // Display data from SharedPreferences
        displayDataFromSharedPreferences();

        orientationToggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Lock screen orientation to portrait
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            } else {
                // Set to auto orientation
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, counter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Increment counter
        counter++;
        // Display counter and full name in a toast
        displayCounterAndFullName();
    }

    private void displayCounterAndFullName() {
        String fullName = getString(R.string.vinh_le);
        String toastMessage = getString(R.string.counter) + counter + "\n" +
                getString(R.string.vinh) + fullName;

        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("SetTextI18n")
    private void displayDataFromSharedPreferences() {
        boolean isChecked = sharedPreferences.getBoolean(getString(R.string.checkbox_label), true);
        String email = sharedPreferences.getString(getString(R.string.email), "");
        String id = sharedPreferences.getString(getString(R.string.id), "");

        // Update TextViews with retrieved data
        firstNameTextView.setText(getString(R.string.vinH) + " " + (isChecked ? getString(R.string.checked) : getString(R.string.unchecked)));
        lastNameTextView.setText(getString(R.string.le) + " " + (email.isEmpty() ? getString(R.string.no_data) : email));
    }
}

