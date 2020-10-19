package ru.mail.android_homework;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private final static String rememberSize= "size";
    private final static int initialSize = 100;
    private int size = initialSize;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Integer> nums = new ArrayList<>();
    private View listItemsView;
    private Button newNum;


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
        listItemsView = inflater.inflate(R.layout.first_fragment, container, false);
        recyclerView = listItemsView.findViewById(R.id.recycler);
        int orientation = getResources().getConfiguration().orientation;
        int landColumns = getResources().getInteger(R.integer.landscapeCol);
        int portColumns = getResources().getInteger(R.integer.portraitCol);
        int columns = orientation == Configuration.ORIENTATION_LANDSCAPE? landColumns: portColumns;
        layoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(layoutManager);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        adapter = new NumAdapter(nums, fm);
        recyclerView.setAdapter(adapter);

        newNum = listItemsView.findViewById(R.id.addNumBtn);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(rememberSize, size);
    }
}