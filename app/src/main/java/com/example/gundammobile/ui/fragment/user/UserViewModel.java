package com.example.gundammobile.ui.fragment.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    // This is just a placeholder for your condition.
    // You might have a more complex logic to determine which card to show.
    private MutableLiveData<Boolean> shouldShowFirstCard = new MutableLiveData<>();

    public LiveData<Boolean> shouldShowFirstCard() {
        return shouldShowFirstCard;
    }

    // Call this method to update the condition
    public void setShouldShowFirstCard(boolean show) {
        shouldShowFirstCard.setValue(show);
    }
}