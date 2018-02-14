package com.example.rckja.userinterface;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by rckja on 07-02-2018.
 */

public class TestAdapter extends BaseAdapter {

    Context mContext;
    List<ExampleObject> mExamples;
    boolean btnNextFactClicked =false;

    TestAdapter(Context context,List<ExampleObject> exampleObjectList){
        mContext = context;
        mExamples =exampleObjectList;
    }
    @Override
    public int getCount() {
        return mExamples.size();
    }

    @Override
    public Object getItem(int position) {
        return mExamples.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview, null);
            ViewHolder viewHolder = new ViewHolder((ImageView) convertView.findViewById(R.id.imageView),
                    (TextView) convertView.findViewById(R.id.textView),(Button)convertView.findViewById(R.id.nextFact),
                    (Button)convertView.findViewById(R.id.deleteFact),(Button)convertView.findViewById(R.id.addFact),(EditText)convertView.findViewById(R.id.editText));
            convertView.setTag(viewHolder);
        }
            final ExampleObject object = (ExampleObject)getItem(position);
            final ViewHolder viewHolder =(ViewHolder)convertView.getTag();
            Button mNextFactButton = convertView.findViewById(R.id.nextFact);
            Button mDeleteFactButton = convertView.findViewById(R.id.deleteFact);
            viewHolder.mImageView.setImageResource(object.mColor);
            viewHolder.mTextView.setSingleLine(false);
            if(object.mColorPicker != Color.BLACK){
                viewHolder.mImageView.setColorFilter(object.mColorPicker);
            }

            if(object.addIsSelected()){
                if(!object.mName.contains(object.mEditText)){
                    object.mName.add(object.mEditText);
                    viewHolder.mTextView.setText(object.mAnimalName +Html.fromHtml(" \n") + object.getmName(0));

                }
            }
            else if(btnNextFactClicked){
                int index = object.getIndex((String) viewHolder.mTextView.getText());
                if(object.mName.size() > index +1  ){
                    viewHolder.mTextView.setText(object.mAnimalName +Html.fromHtml(" \n")+  object.getmName(index +1));
                }
                else if(object.mName.size() == index +1){
                    viewHolder.mTextView.setText(object.mAnimalName +Html.fromHtml(" \n") + object.getmName(0));
                }
            }
            else
            {
                viewHolder.mTextView.setText(object.mAnimalName +Html.fromHtml(" \n") + object.getmName(0));
            }
            viewHolder.mNextFact.setTag(object);

            viewHolder.mDeleteFact.setTag(object);
             if (object.isSelected())
             {
            viewHolder.mTextView.setVisibility(View.VISIBLE);
                 viewHolder.mNextFact.setVisibility(View.VISIBLE);
                 viewHolder.mDeleteFact.setVisibility(View.VISIBLE);}
            else
             {viewHolder.mTextView.setVisibility(View.INVISIBLE);
                viewHolder.mNextFact.setVisibility(View.INVISIBLE);
                viewHolder.mDeleteFact.setVisibility(View.INVISIBLE);
             }

        mNextFactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.addSetSelected(false);
                if(object.getIndex((String) viewHolder.mTextView.getText()) >= object.getIndex((String) viewHolder.mTextView.getText()) ){
                       btnNextFactClicked =true;
                       notifyDataSetChanged();
                }
                else if(object.mName.size() ==1)
                {
                    btnNextFactClicked =false;
                    //Toast.makeText(view.getContext(),"There is only one item in the list.",Toast.LENGTH_LONG).show();
                }

            }
        });
        mDeleteFactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNextFactClicked =false;
                object.addSetSelected(false);
                if(object.mName.size() >1){
                    object.mName.remove(object.getIndex((String) viewHolder.mTextView.getText()));
                    notifyDataSetChanged();
                }
                else
                {
                     Toast.makeText(view.getContext(), Html.fromHtml("<b>There is only one item in the list.</b>"),Toast.LENGTH_LONG).show();
                }
            }
        });
        return convertView;
    }

    static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
        Button mNextFact;
        Button mDeleteFact;
        Button mAddFact;
        EditText mEditText;
        ViewHolder(ImageView imageView, TextView textView,Button mBtnNextFact,Button mBtnDeleteFact,Button mBtnAddFact,EditText mEditText){
            this.mImageView =imageView;
            this.mTextView =textView;
            this.mNextFact =mBtnNextFact;
            this.mDeleteFact =mBtnDeleteFact;
            this.mAddFact =mBtnAddFact;
            this.mEditText =mEditText;
        }
    }
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
