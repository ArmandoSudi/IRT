package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IProfilDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(IProfilDao...profils);

    @Update
    int update(IProfilDao...profils);

    @Delete
    void delete(IProfilDao...profils);

    @Query("DELETE FROM PROFIL")
    void deleteAll();
}
