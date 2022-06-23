package com.example.circlechat.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.circlechat.entities.Contact;

import java.util.List;

import retrofit2.http.Header;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contact")
    List<Contact> GetAll();

    @Query("SELECT * FROM contact WHERE id = :username")
    List<Contact> Find(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(List<Contact> contacts);

    @Update
    void Update(List<Contact> contacts);

    @Delete
    void Delete(Contact... contacts);

    @Query("DELETE FROM contact")
    void Clear();
}
