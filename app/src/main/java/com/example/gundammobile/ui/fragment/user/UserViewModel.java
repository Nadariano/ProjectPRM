package com.example.gundammobile.ui.fragment.user;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gundammobile.model.User;
import com.google.gson.Gson;

public class UserViewModel extends AndroidViewModel {

    private String USER_PREFS = "UserInfo";
    private SharedPreferences userPreference;
    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>(false);
    private MutableLiveData<User> user = new MutableLiveData<>(null);

    public UserViewModel(@NonNull Application application) {
        super(application);
        getUserPref();
        if(user.getValue() != null){
            setIsLoggedIn(true);
        } else setIsLoggedIn(false);
    }


    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean loggedIn) {
        isLoggedIn.setValue(loggedIn);
    }

    private void getUserPref(){
        userPreference = getApplication().getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        // Retrieve the values using the keys
        int userId = userPreference.getInt("userId", -1);
        String accountName = userPreference.getString("accountName", "");
        String accountPhone = userPreference.getString("accountPhone", "");
        String accountAddress = userPreference.getString("accountAddress", "");

        // Create a User object with the retrieved data
        User userObject = new User(userId, accountName, accountPhone, accountAddress);

        // Set the User object inside the MutableLiveData
        user.setValue(userObject);
        if (userId == -1 || accountName.isEmpty() || accountPhone.isEmpty() || accountAddress.isEmpty()) {
            user.setValue(null);
        }
    }

    public void logOut(){
        userPreference.edit().clear().apply();
        setUser(null);
        setIsLoggedIn(false);
    }
    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;
    }
}
