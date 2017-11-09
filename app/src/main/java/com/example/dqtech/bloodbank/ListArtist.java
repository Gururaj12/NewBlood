package com.example.dqtech.bloodbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DQTECH on 10/30/2017.
 */

public class ListArtist extends ArrayAdapter<Users>{
    private Activity context;
    private List<Users> artistList;

    public ListArtist(Activity context,List<Users> artistList){
        super(context,R.layout.layout_artist_list,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listviewitem=layoutInflater.inflate(R.layout.layout_artist_list,null,true);
        TextView tname= (TextView) listviewitem.findViewById(R.id.textViewName);
       // TextView tcontact= (TextView) listviewitem.findViewById(R.id.textViewcontact);
        TextView tgender= (TextView) listviewitem.findViewById(R.id.textViewGender);
        TextView temail= (TextView) listviewitem.findViewById(R.id.textViewemail);
        TextView tcont= (TextView) listviewitem.findViewById(R.id.textViewcontact);
        TextView taddre= (TextView) listviewitem.findViewById(R.id.textViewaddress);
        TextView tcity= (TextView) listviewitem.findViewById(R.id.textViewcity);
        TextView tusernam= (TextView) listviewitem.findViewById(R.id.textViewusername);
        TextView tblood= (TextView) listviewitem.findViewById(R.id.textViewblood);
        Users artist=artistList.get(position);
        tname.setText(artist.getName());
        tgender.setText(artist.getGender());
        temail.setText(artist.getEmail());
        tcont.setText(artist.getContact());
        taddre.setText(artist.getAddress());
        tcity.setText(artist.getCity());
        tusernam.setText(artist.getUsername());
        tblood.setText(artist.getBgroup());
        Intent  intent=new Intent(context,Mydetails.class);
        Bundle b=new Bundle();
        b.putString("name",artist.getName());

        return listviewitem;
    }

}
