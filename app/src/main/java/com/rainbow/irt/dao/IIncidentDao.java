package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Incident;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IIncidentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(Incident...incidents);

    @Update
    int update(Incident...incidents);

    @Delete
    void delete(Incident...incidents);

    @Query("DELETE FROM INCIDENT")
    void deleteAll();

    @Query("SELECT * FROM INCIDENT")
    List<Incident> getAll();

    @Query("SELECT * FROM INCIDENT WHERE CODE_INCIDENT=:codeIncident")
    Incident getByCodeIncident(int codeIncident);

    @Query("SELECT * FROM INCIDENT WHERE CODE_EQUIPEMENT=:codeEquipement")
    List<Incident> getIncidentByEquipement(String codeEquipement);
}
