package com.example.gundammobile.ui.fragment.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>(false);

//    public UserViewModel() {
//        if(isLoggedIn.getValue() == null){
//        // Set initial value to false
//        isLoggedIn.setValue(false);
//        }
//    }1

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean loggedIn) {
        isLoggedIn.setValue(loggedIn);
    }
}
