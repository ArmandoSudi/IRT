package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.SiteVote;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface ISiteVoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(SiteVote...siteVotes);

    @Update
    int update(SiteVote...siteVotes);

    @Delete
    void delete(SiteVote...siteVotes);

    @Query("DELETE FROM SITE_VOTE")
    void deleteAll();
}
