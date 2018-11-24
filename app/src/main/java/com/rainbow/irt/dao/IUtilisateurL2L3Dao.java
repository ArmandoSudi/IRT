package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.UtilisateurL2L3;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IUtilisateurL2L3Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(UtilisateurL2L3...centreSupportUtilisateurs);

    @Update
    int update(UtilisateurL2L3...centreSupportUtilisateurs);

    @Delete
    void delete(UtilisateurL2L3...centreSupportUtilisateurs);

    @Query("DELETE FROM UTILISATEUR_L2_L3")
    void deleteAll();
}
