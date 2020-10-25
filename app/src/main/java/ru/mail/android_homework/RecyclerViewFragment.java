package ru.mail.android_homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private final static String rememberSize= "size";
    private final static int initialSize = 100;
    private int size = initialSize;

    private RecyclerView.Adapter adapter;
    private final ArrayList<Integer> nums = new ArrayList<>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            size = savedInstanceState.getInt(rememberSize);
        }

        for (int i = nums.size() + 1; i <= size; i++) {
            nums.add(i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listItemsView = inflater.inflate(R.layout.first_fragment, container, false);
        RecyclerView recyclerView = listItemsView.findViewById(R.id.recycler);
        int columns = getResources().getInteger(R.integer.column_number);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NumAdapter(nums, getActivity());
        recyclerView.setAdapter(adapter);

        Button newNum = listItemsView.findViewById(R.id.addNumBtn);
        newNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size++;
                int nextNum = adapter.getItemCount() + 1;
                nums.add(nextNum);
                adapter.notifyItemInserted(nextNum);
            }
        });

        return listItemsView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(rememberSize, size);
    }
}
