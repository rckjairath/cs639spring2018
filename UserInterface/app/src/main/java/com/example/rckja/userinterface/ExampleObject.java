package com.example.rckja.userinterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rckja on 07-02-2018.
 */

public class ExampleObject {
    int mColor;
    List<String> mName;
    String mAnimalName;
    int mNextFact;
    int mDeleteFact;
    int mAddFact;
    int mColorPicker;
    String mEditText;
    private boolean isSelected;
    private boolean addIsSelected;


    ExampleObject(int color,String animalName,String name,int nextFact,int deleteFact,int addFact,String editText, int colorPicker){
        mName =new ArrayList<>();
        mAnimalName =animalName;
        mColor =color;
        mName.add(name);
        mNextFact= nextFact;
        mDeleteFact = deleteFact;
        mAddFact =addFact;
        mEditText =editText;
        mColorPicker=colorPicker;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public boolean addIsSelected() {
        return addIsSelected;
    }
    public  String getmName(int i){
        return  mName.get(i);
    }

    public  int getIndex(String gName){

        return mName.indexOf(gName);
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public void addSetSelected(boolean isSelected) {
        this.addIsSelected = isSelected;
    }
}
