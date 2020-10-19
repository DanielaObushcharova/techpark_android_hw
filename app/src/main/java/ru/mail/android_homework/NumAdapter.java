package ru.mail.android_homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.NumViewHolder> {

    private ArrayList<Integer> numData;
    private LayoutInflater layoutInflater;
    private FragmentManager fm;

    public NumAdapter(ArrayList data, FragmentManager fragManager) {
        numData = data;
        fm = fragManager;
    }

    @NonNull
    @Override
    public NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = layoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.number, parent, false);
        return new NumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumViewHolder holder, final int position) {
        int num = numData.get(position);
        int color = (position % 2 == 0? R.color.colorOdd : R.color.colorEven);
        holder.mNum.setText(String.valueOf(num));
        holder.mNum.setTextColor(ContextCompat.getColor(layoutInflater.getContext(), color));
        holder.mNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleNumberFragment fragment = new SingleNumberFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("textVal", position + 1);
                fragment.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numData.size();
    }

    public class NumViewHolder extends RecyclerView.ViewHolder {
        TextView mNum;
        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            mNum = itemView.findViewById(R.id.number);
        }
    }
}
