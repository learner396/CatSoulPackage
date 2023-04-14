package com.catsoul.catsoulpackage.database.user;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


//暂时先不做Room数据库
@Dao
public interface UserDao {


    @Query("SELECT * From User")
    List<User> getAll();
}
