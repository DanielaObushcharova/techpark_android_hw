package ru.mail.android_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, RecyclerViewFragment.newInstance())
                    .commit();
        }
    }

    public void numberClickListener(int num) {
        SingleNumberFragment fragment = new SingleNumberFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("textVal", num);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}