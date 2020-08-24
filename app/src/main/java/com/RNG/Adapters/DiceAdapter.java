package com.RNG.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.RNG.R;

public class DiceAdapter extends BaseAdapter {
    private int[] dice;
    private LayoutInflater inflater;

    public DiceAdapter(Context context, int[] dice) {
        this.inflater = LayoutInflater.from(context);
        this.dice = dice;
    }

    @Override
    public int getCount() {
        return dice.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dice_grid_item, parent, false);

            ImageView diceFaceImage = (ImageView) convertView.findViewById(R.id.diceFaceImage);

            diceFaceImage.setImageResource(dice[position]);
        }
        return convertView;
    }
}
