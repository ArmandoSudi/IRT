package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Equipement;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IEquipementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(Equipement...equipements);

    @Update
    int update(Equipement...equipements);

    @Delete
    void delete(Equipement...equipements);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Equipement> equipements);

    @Query("DELETE FROM EQUIPEMENT")
    void deleteAll();

    @Query("SELECT * FROM EQUIPEMENT")
    List<Equipement> getAll();

    @Query("SELECT * FROM EQUIPEMENT WHERE CODE_EQUIPEMENT=:codeEquipement")
    Equipement get(String codeEquipement);
}
