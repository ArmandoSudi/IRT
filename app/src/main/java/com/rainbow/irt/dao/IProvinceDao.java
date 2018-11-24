package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Province;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IProvinceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(Province...provinces);

    @Update
    int update(Province...provinces);

    @Delete
    void delete(Province...provinces);

    @Query("DELETE FROM PROVINCE")
    void deleteAll();
}
