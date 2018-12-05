package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Profil;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IProfilDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Profil...profils);

    @Update
    int update(Profil...profils);

    @Delete
    void delete(Profil...profils);

    @Query("DELETE FROM PROFIL")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Profil> profils);
}
