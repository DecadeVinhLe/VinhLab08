//Vinh Le
//N01518620
package vinh.le.n01518620;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {
    private ImageView imageView;
    private Button button;
    private int clickCount = 1;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        TextView textView = view.findViewById(R.id.vinhTextView);
        button = view.findViewById(R.id.vinhButton);
        imageView = view.findViewById(R.id.vinhImageView);

        // Set initial click count text
        textView.setText(getString(R.string.vinh_le) + "\n" + getString(R.string.student_id) + "\nClick Count: " + clickCount);

        // Button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rotate between 4 different images
                int resourceId = getResources().getIdentifier("image_" + (clickCount % 4 + 1), "drawable", requireContext().getPackageName());
                imageView.setImageResource(resourceId);

                // Display Snackbar with click count
                Snackbar.make(view, getString(R.string.vinh_le) + " - Click Count: " + clickCount, Snackbar.LENGTH_SHORT).show();

                // Increment click count
                clickCount++;
            }
        });

        return view;
    }
}
