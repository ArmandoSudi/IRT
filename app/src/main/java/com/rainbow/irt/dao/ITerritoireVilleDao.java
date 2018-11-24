package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.TerritoireVille;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface ITerritoireVilleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(TerritoireVille...territoireVilles);

    @Update
    int update(TerritoireVille...territoireVilles);

    @Delete
    void delete(TerritoireVille...territoireVilles);

    @Query("DELETE FROM TERRITOIRE_VILLE")
    void deleteAll();
}
