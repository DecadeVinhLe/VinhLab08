//Vinh Le
//N01518620
package vinh.le.n01518620;

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

        // Display counter and full name in a toast
        displayCounterAndFullName();

        // Load SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserData", 0);

        // Display data from SharedPreferences
        displayDataFromSharedPreferences();

        // Set onClickListener for orientation toggle button
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

    private void displayCounterAndFullName() {
        String fullName = getString(R.string.vinh_le); // Replace with your full name
        String toastMessage = getString(R.string.counter) + counter + "\n" +
                getString(R.string.vinh)+ fullName;

        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    private void displayDataFromSharedPreferences() {
        boolean isChecked = sharedPreferences.getBoolean("checkbox", false);
        String email = sharedPreferences.getString("email", "");
        String id = sharedPreferences.getString("id", "");

        if (isChecked || !email.isEmpty() || !id.isEmpty()) {
            String userInfo = getString(R.string.checkbox) + (isChecked ? getString(R.string.checked) : getString(R.string.unchecked)) + "\n" +
                    getString(R.string.Email) + (email.isEmpty() ? getString(R.string.no_data) : email) + "\n" +
                    getString(R.string.Id) + (id.isEmpty() ? getString(R.string.no_data): id);

            Toast.makeText(requireContext(), userInfo, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_data), Toast.LENGTH_LONG).show();
        }
    }

}
