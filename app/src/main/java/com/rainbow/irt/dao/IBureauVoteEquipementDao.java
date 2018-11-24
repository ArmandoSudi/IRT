package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.BureauVoteEquipement;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface IBureauVoteEquipementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(BureauVoteEquipement...bureauVoteEquipements);

    @Update
    int update(BureauVoteEquipement...bureauVoteEquipements);

    @Delete
    void delete(BureauVoteEquipement...bureauVoteEquipements);

    @Query("DELETE FROM BUREAU_VOTE_EQUIPEMENT")
    void deleteAll();
}
