package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.StatutEquipement;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface IStatutEquipementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(StatutEquipement...statutEquipements);

    @Update
    int update(StatutEquipement...statutEquipementsv);

    @Delete
    void delete(StatutEquipement...statutEquipements);

    @Query("DELETE FROM STATUT_EQUIPEMENT")
    void deleteAll();
}
