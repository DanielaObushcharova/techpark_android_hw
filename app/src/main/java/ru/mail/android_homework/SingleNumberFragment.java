package ru.mail.android_homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class SingleNumberFragment extends Fragment {

    private static final String TEXT = "TEXT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_number_fragment, container, false);
        TextView num = view.findViewById(R.id.showNum);
        Bundle bundle = getArguments();
        int number = bundle.getInt(TEXT);
        num.setText(String.valueOf(number));
        int color = (number % 2 == 0? R.color.colorEven : R.color.colorOdd);
        num.setTextColor(ContextCompat.getColor(getContext(), color));
        return view;
    }
}
