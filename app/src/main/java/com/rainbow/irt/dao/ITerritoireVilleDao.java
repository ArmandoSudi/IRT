package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.TerritoireVille;

import java.util.List;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface ITerritoireVilleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(TerritoireVille...territoireVilles);

    @Update
    int update(TerritoireVille...territoireVilles);

    @Delete
    void delete(TerritoireVille...territoireVilles);

    @Query("DELETE FROM TERRITOIRE_VILLE")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TerritoireVille> territoireVilles);

    @Query("SELECT * FROM TERRITOIRE_VILLE")
    List<TerritoireVille> getAll();

    @Query("SELECT * FROM TERRITOIRE_VILLE WHERE CODE_PROVINCE=:codeProvince")
    List<TerritoireVille> getTerritoireVilleByProvince(String codeProvince);
}
