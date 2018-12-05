package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Province;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IProvinceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Province...provinces);

    @Update
    int update(Province...provinces);

    @Delete
    void delete(Province...provinces);

    @Query("DELETE FROM PROVINCE")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Province> provinces);

    @Query("SELECT * FROM PROVINCE")
    List<Province> getAll();
}
