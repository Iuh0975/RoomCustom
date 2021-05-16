package com.example.roombasiccustom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user WHERE id = :userId ")
    User getById(int userId);

    @Insert
    void add(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
