package com.pace.cs639spring.hw1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kachi on 1/31/18.
 */

public class AnimalDisplayFragment extends Fragment {
    ImageView birdImg;
    ImageView catImg;
    ImageView dogImg;
    Button btnBlue;
    Button btnGreen;
    Button btnPink;
    Button btnRed;
    Button btnYellow;

    @Nullable
    @Override
    private View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animal_display, container, false);
        return view;

        birdImg =(ImageView)findViewById(R.id.bird) ;
        catImg =(ImageView)findViewById(R.id.cat);
        dogImg =(ImageView)findViewById(R.id.dog);

        final TextView txtbird = (TextView)findViewById(R.id.birddesc);
        final TextView txtcat = (TextView)findViewById(R.id.catdesc);
        final TextView txtdog = (TextView)findViewById(R.id.dogdesc);

        btnBlue =(Button) findViewById(R.id.btnBlue);
        btnBlue =(Button) findViewById(R.id.btnBlue);
        btnGreen =(Button) findViewById(R.id.btnGreen);
        btnPink=(Button) findViewById(R.id.btnPink);
        btnRed =(Button) findViewById(R.id.btnRed);
        btnYellow=(Button) findViewById(R.id.btnYellow);

        //To choose different images for different layouts
        Bitmap birdbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bird);
        Bitmap catbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        Bitmap dogbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        birdImg.setImageBitmap(birdbitmap);
        catImg.setImageBitmap(catbitmap);
        dogImg.setImageBitmap(dogbitmap);
        // On Click of Bird Image set description of bird to text view
        birdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtbird.getText() ==null || txtbird.getText() =="") {
                    txtbird.setText(getString(R.string.birdDesc));
                }
                else
                    txtbird.setText("");
                txtcat.setText("");
                txtdog.setText("");
            }
        });

        // On Click of cat Image set description of cat to text view
        catImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtcat.getText() ==null || txtcat.getText() =="") {
                    txtcat.setText(getString(R.string.catDesc));
                }
                else
                    txtcat.setText("");
                txtbird.setText("");
                txtdog.setText("");
            }
        });

        // On Click of dog Image set description of dog to text view
        dogImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtdog.getText() ==null || txtdog.getText() =="") {
                    txtdog.setText(getString(R.string.dosgDesc));
                }
                else
                    txtdog.setText("");

                txtbird.setText("");
                txtcat.setText("");

            }
        });
        //On click of a color button set color of the selected image
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                CharSequence text = "You have not selected any image. Please select any image first!";
                if( txtbird.getText() !="") {
                    birdImg.setColorFilter(Color.rgb(63, 81, 181));
                }else if( txtcat.getText() !=""){
                    catImg.setColorFilter(Color.rgb(63, 81, 181));
                }
                else if(txtdog.getText() !=""){
                    dogImg.setColorFilter(Color.rgb(63, 81, 181));
                }else{
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT ).show();
                }
            }
        });
        //On click of a color button set color of the selected image
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                CharSequence text = "You have not selected any image. Please select any image first!";
                if( txtbird.getText() !="") {
                    birdImg.setColorFilter(Color.rgb(66, 244, 104));
                }else if( txtcat.getText() !=""){
                    catImg.setColorFilter(Color.rgb(66, 244, 104));
                }
                else if(txtdog.getText() !=""){
                    dogImg.setColorFilter(Color.rgb(66, 244, 104));
                }else{
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT ).show();
                }
            }
        });
        //On click of a color button set color of the selected image
        btnPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {

                CharSequence text = "You have not selected any image. Please select any image first!";
                if( txtbird.getText() !="") {
                    birdImg.setColorFilter(Color.rgb(244, 65, 202));
                }else if( txtcat.getText() !=""){
                    catImg.setColorFilter(Color.rgb(244, 65, 202));
                }
                else if(txtdog.getText() !=""){
                    dogImg.setColorFilter(Color.rgb(244, 65, 202));
                }else{
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT ).show();
                }
            }
        });
        //On click of a color button set color of the selected image
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                CharSequence text = "You have not selected any image. Please select any image first!";
                if( txtbird.getText() !="") {
                    birdImg.setColorFilter(Color.rgb(244, 80, 65));
                }else if( txtcat.getText() !=""){
                    catImg.setColorFilter(Color.rgb(244, 80, 65));
                }
                else if(txtdog.getText() !=""){
                    dogImg.setColorFilter(Color.rgb(244, 80, 65));
                }else{
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT ).show();
                }
            }
        });



        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                CharSequence text = "You have not selected any image. Please select any image first!";
                if( txtbird.getText() !="") {
                    birdImg.setColorFilter(Color.rgb(247, 229, 32));
                }else if( txtcat.getText() !=""){
                    catImg.setColorFilter(Color.rgb(247, 229, 32));
                }
                else if(txtdog.getText() !=""){
                    dogImg.setColorFilter(Color.rgb(247, 229, 32));
                }else{
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    }
}
