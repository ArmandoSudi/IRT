package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.LexiquePanne;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface ILexiquePanneDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(LexiquePanne...lexiquePannes);

    @Update
    int update(LexiquePanne...lexiquePannes);

    @Delete
    void delete(LexiquePanne...lexiquePannes);

    @Query("DELETE FROM LEXIQUE_PANNE")
    void deleteAll();
}
