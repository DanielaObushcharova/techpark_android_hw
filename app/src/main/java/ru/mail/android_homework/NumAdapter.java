package ru.mail.android_homework;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.NumViewHolder> {

    private final ArrayList<Integer> numData;
    private final Activity activity;
    private LayoutInflater mLayoutInflater;

    public NumAdapter(ArrayList<Integer> data, Activity activ) {
        numData = data;
        activity = activ;
    }

    @NonNull
    @Override
    public NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = mLayoutInflater.from(parent.getContext());
        View view = mLayoutInflater.inflate(R.layout.number, parent, false);
        return new NumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumViewHolder holder, final int position) {
        int num = numData.get(position);
        int color = (position % 2 == 0? R.color.colorOdd : R.color.colorEven);
        holder.mNum.setText(String.valueOf(num));
        holder.mNum.setTextColor(ContextCompat.getColor(mLayoutInflater.getContext(), color));
        holder.mNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).numberClickListener(position + 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numData.size();
    }

    public static class NumViewHolder extends RecyclerView.ViewHolder {
        final TextView mNum;
        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            mNum = itemView.findViewById(R.id.number);
        }
    }
}
