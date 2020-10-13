package br.ujst.jonathan.studentregister.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<User> user;

    UserRepository(Application application) {
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        user = userDAO.getUser();
    }
    LiveData<User> getUser() {
        return user;
    }
    void insert(User user) {
        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }
}