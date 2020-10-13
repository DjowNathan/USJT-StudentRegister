package br.ujst.jonathan.studentregister.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {
    public UserRepository userRepository;

    public LiveData<User> user;

    public UserViewModel (Application application) {
        super(application);
        userRepository = new UserRepository(application);
        user = userRepository.getUser();
    }

    public LiveData<User> getUser() { return user; }

    public void insert(User user) { userRepository.insert(user); }

}