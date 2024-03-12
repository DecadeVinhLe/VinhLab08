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
            editor.putBoolean("checkbox", checkbox.isChecked());
            editor.putString("email", emailEditText.getText().toString());
            editor.putString("id", idEditText.getText().toString());
            editor.apply();

            // Display user info in a Toast
            displayUserInfo();

            // Clear EditText fields
            emailEditText.getText().clear();
            idEditText.getText().clear();
        } else {
            // Display error message
            Toast.makeText(requireContext(), "Invalid input. Please check your email and ID.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput() {
        // Validate email format
        String email = emailEditText.getText().toString().trim();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            return false;
        }

        // Validate ID length
        String id = idEditText.getText().toString().trim();
        if (id.length() < 6) {
            idEditText.setError("ID must be at least 6 digits long");
            return false;
        }

        return true;
    }

    private void displayUserInfo() {
        boolean isChecked = sharedPreferences.getBoolean("checkbox", false);
        String email = sharedPreferences.getString("email", "");
        String id = sharedPreferences.getString("id", "");

        String userInfo = "Checkbox: " + (isChecked ? "Checked" : "Unchecked") + "\n" +
                "Email: " + email + "\n" +
                "ID: " + id;

        Toast.makeText(requireContext(), userInfo, Toast.LENGTH_LONG).show();
    }

    private void displayCurrentTime() {
        // Display current time in GMT with full name in a Toast
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        String fullName = "Your Full Name"; // Replace with your full name
        String toastMessage = "Current Time (GMT): " + currentTime + "\n" +
                "Full Name: " + fullName;

        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show();
    }
}
  public ShareFragment() {
    // Required empty public constructor
}
