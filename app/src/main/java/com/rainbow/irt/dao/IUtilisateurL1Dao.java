package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.UtilisateurL1;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface IUtilisateurL1Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(UtilisateurL1...utilisateurL1s);

    @Update
    int update(UtilisateurL1...utilisateurL1s);

    @Delete
    void delete(UtilisateurL1...utilisateurL1s);

    @Query("DELETE FROM UTILISATEUR_L1")
    void deleteAll();
}

