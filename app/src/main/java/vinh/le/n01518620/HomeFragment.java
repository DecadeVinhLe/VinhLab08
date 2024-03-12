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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private ImageView imageView;
    private Button button;
    private int clickCount = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                int resourceId = getResources().getIdentifier("image_" + (clickCount % 4 + 1), "drawable", requireActivity().getPackageName());
                imageView.setImageResource(resourceId);

                // Increment click count
                clickCount++;
            }
        });

        return view;
    }
}
