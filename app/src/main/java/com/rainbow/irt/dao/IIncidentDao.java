package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Incident;

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
}
