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

    private final static String REMEMBER_SIZE= "size";
    private final static int INITIAL_SIZE = 100;
    private int mSize = INITIAL_SIZE;

    private NumAdapter mAdapter;
    private final ArrayList<Integer> nums = new ArrayList<>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mSize = savedInstanceState.getInt(REMEMBER_SIZE);
        }

        for (int i = nums.size() + 1; i <= mSize; i++) {
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
        mAdapter = new NumAdapter(nums, getActivity());
        recyclerView.setAdapter(mAdapter);

        Button newNum = listItemsView.findViewById(R.id.addNumBtn);
        newNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSize++;
                int nextNum = mAdapter.getItemCount() + 1;
                nums.add(nextNum);
                mAdapter.notifyItemInserted(nextNum);
            }
        });

        return listItemsView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(REMEMBER_SIZE, mSize);
    }
}
