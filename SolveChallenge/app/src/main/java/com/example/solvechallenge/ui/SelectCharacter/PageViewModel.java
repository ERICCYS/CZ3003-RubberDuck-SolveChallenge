package com.example.solvechallenge.ui.SelectCharacter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public MutableLiveData<Integer> getIndex() {
        return mIndex;
    }
}