//Vinh Le
//N01518620
package vinh.le.n01518620;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private static final int REQUEST_PERMISSION_CODE = 123;
    private Button accessPhotosButton;
    private ImageView photoImageView;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        accessPhotosButton = view.findViewById(R.id.accessPhotosButton);
        photoImageView = view.findViewById(R.id.photoImageView);

        accessPhotosButton.setOnClickListener(v -> checkPermissionAndAccessPhotos());

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission granted
                displayToast(getString(R.string.grant));
                // Access photos here
                // Replace below code with your logic to access photos from device
                photoImageView.setImageResource(R.drawable.sea);
            } else {
                // Permission denied
                displayToast(getString(R.string.denied));
            }
        });

        return view;
    }

    private void checkPermissionAndAccessPhotos() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Permission rationale shown before, request permission
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            } else {
                // Permission rationale not shown before, direct to app settings
                showSettingsAlertDialog();
            }
        } else {
            // Permission already granted
            displayToast("Permission already granted");
            // Access photos here
            // Replace below code with your logic to access photos from device
            photoImageView.setImageResource(R.drawable.grass);
        }
    }

    private void showSettingsAlertDialog() {
        // Prompt the user to go to app settings
        Toast.makeText(requireContext(), "Please enable permission in app settings", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", requireContext().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void displayToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
