package com.example.dqtech.bloodbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DQTECH on 10/26/2017.
 */

public class ArtistList extends ArrayAdapter<Users> {
    private Activity context;
    List<Users> artists;

    public ArtistList(Activity context, List<Users> artists) {
        super(context, R.layout.layout_artist_list, artists);
        this.context = context;
        this.artists = artists;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName1 = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre2 = (TextView) listViewItem.findViewById(R.id.textViewGender);
        TextView textViewName3 = (TextView) listViewItem.findViewById(R.id.textViewemail);
        TextView textViewGenre4 = (TextView) listViewItem.findViewById(R.id.textViewcontact);
        TextView textViewName5 = (TextView) listViewItem.findViewById(R.id.textViewaddress);
        TextView textViewGenre6 = (TextView) listViewItem.findViewById(R.id.textViewcity);
      // TextView textViewName7 = (TextView) listViewItem.findViewById(R.id.textViewusername);
        TextView textViewGenre8 = (TextView) listViewItem.findViewById(R.id.textViewblood);

        Users artist = artists.get(position);
        textViewName1.setText(artist.getName());
        textViewGenre2.setText(artist.getGender());
        textViewName3.setText(artist.getEmail());
        textViewGenre4.setText(artist.getContact());
        textViewName5.setText(artist.getAddress());
        textViewGenre6.setText(artist.getCity());
    //    textViewName7.setText(artist.getUsername());
        textViewGenre8.setText(artist.getBgroup());


        return listViewItem;
    }
}

