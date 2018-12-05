package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.Utilisateur;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IUtilisateurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Utilisateur...utilisateurs);

    @Update
    int update(Utilisateur...utilisateurs);

    @Delete
    void delete(Utilisateur...utilisateurs);

    @Query("DELETE FROM UTILISATEUR")
    void deleteAll();

    @Query("SELECT * FROM UTILISATEUR")
    List<Utilisateur> getAll();

    @Query("SELECT * FROM UTILISATEUR WHERE CODE_PROFIL=:profileCode")
    List<Utilisateur> getAllByProfileCode(String profileCode);

    @Query("SELECT * FROM UTILISATEUR WHERE CODE_UTILISATEUR=:codeUtilisateur")
    Utilisateur get(String codeUtilisateur);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Utilisateur> utilisateurs);
}
