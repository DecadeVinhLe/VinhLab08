//Vinh Le
//N01518620
package vinh.le.n01518620;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {
    private ImageView imageView;
    private Button button;
    private TextView textView;
    private int clickCount = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        textView = rootView.findViewById(R.id.vinhTextView);
        button = rootView.findViewById(R.id.vinhButton);
        imageView = rootView.findViewById(R.id.vinhImageView);

        // Set initial click count text
        updateTextView();

        // Button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rotate between 4 different images
                switchImage();

                // Display Snackbar with click count
                Snackbar.make(view, "Vinh Le - Click Count: " + clickCount, Snackbar.LENGTH_SHORT).show();

                // Increment click count
                clickCount++;
                updateTextView();
            }
        });

        return rootView;
    }

    private void switchImage() {
        // Rotate between 4 different images
        switch (clickCount % 4) {
            case 0:
                imageView.setImageResource(R.drawable.image_1);
                break;
            case 1:
                imageView.setImageResource(R.drawable.image_2);
                break;
            case 2:
                imageView.setImageResource(R.drawable.image_3);
                break;
            case 3:
                imageView.setImageResource(R.drawable.image_4);
                break;
        }
    }

    private void updateTextView() {
        textView.setText(getString(R.string.Vinh)+ getString(R.string.student_id) +"Click Count: " + clickCount);
    }
}

