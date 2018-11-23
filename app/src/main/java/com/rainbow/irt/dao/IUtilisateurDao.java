package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Utilisateur;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IUtilisateurDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(Utilisateur...utilisateurs);

    @Update
    int update(Utilisateur...utilisateurs);

    @Delete
    void delete(Utilisateur...utilisateurs);

    @Query("DELETE FROM UTILISATEUR")
    void deleteAll();
}
