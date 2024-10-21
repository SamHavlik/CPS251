package com.example.ddnamesavadata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<String>> _names = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<String>> getNames() {
        return _names;
    }

    public void addName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            List<String> currentNames = new ArrayList<>(_names.getValue());
            currentNames.add(name);
            _names.setValue(currentNames);
        }
    }
}
