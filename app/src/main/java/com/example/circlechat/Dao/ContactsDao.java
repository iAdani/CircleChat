package com.example.circlechat.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.circlechat.entities.Contact;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contact")
    List<Contact> GetAll();

    @Query("SELECT * FROM contact WHERE username = :username")
    List<Contact> Find(String username);

    @Insert
    void Insert(Contact... contacts);

    @Update
    void Update(Contact... contacts);

    @Delete
    void Delete(Contact... contacts);
}
