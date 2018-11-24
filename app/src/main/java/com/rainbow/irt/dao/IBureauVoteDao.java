package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.BureauVote;

/**
 * Created by Sugar on 11/24/2018
 */
@Dao
public interface IBureauVoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(BureauVote...bureauVotes);

    @Update
    int update(BureauVote...bureauVotes);

    @Delete
    void delete(BureauVote...bureauVotes);

    @Query("DELETE FROM BUREAU_VOTE")
    void deleteAll();
}
