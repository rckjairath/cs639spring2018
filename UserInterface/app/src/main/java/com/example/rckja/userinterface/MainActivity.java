package com.example.rckja.userinterface;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] pos = new int[1];
        pos[0] =-1;
        setContentView(R.layout.activity_main);
        ListView listview =findViewById(R.id.list_item);
        Button btnAddFact = findViewById(R.id.addFact);
        Button btnBlue =(Button) findViewById(R.id.btnBlue);
        Button btnGreen =(Button) findViewById(R.id.btnGreen);
        Button btnPink=(Button) findViewById(R.id.btnPink);
        Button btnRed =(Button) findViewById(R.id.btnRed);
        Button btnYellow=(Button) findViewById(R.id.btnYellow);
        final EditText editText =findViewById(R.id.editText);
        final List<ExampleObject> listItem =new ArrayList<>();
        listItem.add(new ExampleObject(R.drawable.bird, "Bird " ,getString(R.string.birdDesc), R.id.nextFact, R.id.deleteFact,R.id.addFact,"", Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.dog,"DOG ",getString(R.string.dosgDesc),R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.cat,"CAT ",getString(R.string.catDesc), R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.lizard, "Lizard ",getString(R.string.lizrdDesc), R.id.nextFact, R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.fish,"Fish " ,getString(R.string.fishDesc),R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.panda,"Panda ",getString(R.string.pandaDesc), R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.giraffe, "Giraffe ",getString(R.string.gorillaDesc), R.id.nextFact, R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.whale,"Whale " ,getString(R.string.whaleDesc),R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.leopard,"Leopard ",getString(R.string.leopardDesc), R.id.nextFact,R.id.deleteFact,R.id.addFact,"",Color.BLACK));
        listItem.add(new ExampleObject(R.drawable.lion, "Lion ",getString(R.string.lionDesc), R.id.nextFact, R.id.deleteFact,R.id.addFact,"",Color.BLACK));

        final TestAdapter adapter = new TestAdapter(this,listItem);
        listview.setAdapter(adapter);
        btnAddFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ExampleObject item :listItem){
                    item.addSetSelected(false);
                }
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(editText.getText().toString()))
                {
                    listItem.get(pos[0]).addSetSelected(false);
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please add Fact to be added</b>"),Toast.LENGTH_LONG).show();
                }
                else
                {
                    listItem.get(pos[0]).addSetSelected(true);
                    listItem.get(pos[0]).mEditText =editText.getText().toString();
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }


            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else {
                    listItem.get(pos[0]).mColorPicker = Color.parseColor("#303F9F ");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this, Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else {
                    listItem.get(pos[0]).mColorPicker = Color.parseColor("#42f468");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else {
                    listItem.get(pos[0]).mColorPicker = Color.parseColor("#f441ca");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else {
                    listItem.get(pos[0]).mColorPicker = Color.parseColor("#f45041");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( pos[0] < 0) {
                    Toast.makeText(MainActivity.this,Html.fromHtml("<b>Please select image first</b>"),Toast.LENGTH_LONG).show();
                }
                else {
                    listItem.get(pos[0]).mColorPicker = Color.parseColor("#f7e520");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos[0] =position;
                for (ExampleObject item :listItem){
                    item.setSelected(false);
                    item.addSetSelected(false);
                }
                listItem.get(position).addSetSelected(false);
                listItem.get(position).setSelected(true);
                adapter.notifyDataSetChanged();
            }
        });



    }
}
