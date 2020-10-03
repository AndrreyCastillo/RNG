package com.RNG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.RNG.Adapters.WheelRecyclerViewAdapter;

import java.util.ArrayList;

public class WheelOfRNGActivity extends AppCompatActivity {

    private ArrayList<String> listOfItems = new ArrayList<>();

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_of_rng);

        mEditText = findViewById(R.id.editText_list_wheel);
    }

    public void add_OnClick(View view) {
        String text = mEditText.getText().toString();

        if (text.isEmpty()) {
            return;
        }
        if (listOfItems.size() >= 50) {
            return;
        }

        listOfItems.add(text);

        refreshRecyclerView();

        mEditText.setText("");
    }

    public void refreshRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        WheelRecyclerViewAdapter adapter = new WheelRecyclerViewAdapter(this, listOfItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.scrollToPosition(listOfItems.size() - 1);
    }
}