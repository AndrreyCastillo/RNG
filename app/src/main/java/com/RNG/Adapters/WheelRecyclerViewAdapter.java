package com.RNG.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RNG.R;
import com.RNG.WheelOfRNGActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WheelRecyclerViewAdapter extends RecyclerView.Adapter<WheelRecyclerViewAdapter.ViewHolder> {

    //private static final String TAG = "WheelRecyclerViewAdapter";

    private Context mContext;    


    private ArrayList<String> items = new ArrayList<>();

    public ImageView trashcan;

    public WheelRecyclerViewAdapter(Context mContext, ArrayList<String> items) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wheel_of_rng_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder: called.");

        holder.editText.setText(items.get(position));
        holder.textView.setText(new StringBuilder().append(position + 1).append(".").toString());

        //TODO Find a better implementation for this
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(holder.imageView)) {
                    try {
                        items.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, items.size());
                    }
                    catch (Exception ex) {
;
                    }
                }
            }
        });

        //TODO either be able to edit an item editView and save for that session or just use textView
//        holder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (holder.editText.getText().equals("") && hasFocus) {
//                    Toast.makeText(mContext, "REE", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        EditText editText;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.number_listItem);
            imageView = itemView.findViewById(R.id.trashcan);
            editText = itemView.findViewById(R.id.editText_listItem);
            relativeLayout = itemView.findViewById(R.id.list_layout_of_wheel);
        }
    }
}
