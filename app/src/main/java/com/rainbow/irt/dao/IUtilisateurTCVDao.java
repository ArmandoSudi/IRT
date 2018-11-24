package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.UtilisateurTCV;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IUtilisateurTCVDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(UtilisateurTCV...utilisateurTCVS);

    @Update
    int update(UtilisateurTCV...utilisateurTCVS);

    @Delete
    void delete(UtilisateurTCV...utilisateurTCVS);

    @Query("DELETE FROM UTILISATEUR_TCV")
    void deleteAll();
}
