//Vinh Le
//N01518620
package vinh.le.n01518620;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShareFragment extends Fragment {

    private CheckBox checkbox;
    private EditText emailEditText, idEditText;
    private ImageButton imageButton;
    private SharedPreferences sharedPreferences;

    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        // Initialize Views
        checkbox = view.findViewById(R.id.checkbox);
        emailEditText = view.findViewById(R.id.emailEditText);
        idEditText = view.findViewById(R.id.idEditText);
        imageButton = view.findViewById(R.id.imageButton);

        // Load SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", 0);

        // Set OnClickListener for ImageButton
        imageButton.setOnClickListener(v -> saveUserInfo());

        // Display current time and user's full name in a Toast
        displayCurrentTime();

        return view;
    }

    private void saveUserInfo() {
        // Validate email and ID
        if (validateInput()) {
            // Save user info using SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.checkbox_label), checkbox.isChecked());
            editor.putString(getString(R.string.email), emailEditText.getText().toString());
            editor.putString(getString(R.string.id), idEditText.getText().toString());
            editor.apply();

            // Display user info in a Toast
            displayUserInfo();

            // Clear EditText fields
            emailEditText.getText().clear();
            idEditText.getText().clear();
        } else {
            // Display error message
            Toast.makeText(requireContext(),getString(R.string.error_email), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput() {
        // Validate email format
        String email = emailEditText.getText().toString().trim();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError(getString(R.string.pattern));
            return false;
        }

        // Validate ID length
        String id = idEditText.getText().toString().trim();
        if (id.length() <= 6) {
            idEditText.setError(getString(R.string.length));
            return false;
        }

        return true;
    }

    private void displayUserInfo() {
        boolean isChecked = sharedPreferences.getBoolean(getString(R.string.checkbox_label), false);
        String email = sharedPreferences.getString(getString(R.string.email), "");
        String id = sharedPreferences.getString(getString(R.string.id), "");

        if (isChecked || !email.isEmpty() || !id.isEmpty()) {
            String userInfo = getString(R.string.checkbox) + (isChecked ? getString(R.string.checked) : getString(R.string.unchecked)) + "\n" +
                    getString(R.string.Email) + (email.isEmpty() ? getString(R.string.no_data) : email) + "\n" +
                    getString(R.string.Id) + (id.isEmpty() ? getString(R.string.no_data): id);

            Toast.makeText(requireContext(), userInfo, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_data), Toast.LENGTH_LONG).show();
        }
    }

    private void displayCurrentTime() {
        // Display current time in GMT with full name in a Toast
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        String fullName = getString(R.string.vinh_le); // Replace with your full name
        String toastMessage =getString(R.string.current_time) + currentTime + "\n" +
                getString(R.string.vinh) + fullName;

        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show();
    }
}
