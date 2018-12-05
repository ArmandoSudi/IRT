package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;

import java.util.List;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SiteVote> siteVotes);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll

    @Query("SELECT * FROM SITE_VOTE")
    List<SiteVote> get();

    @Query("SELECT * FROM SITE_VOTE WHERE CODE_SITE_FORMATION=:codeSiteFormation")
    List<SiteVote> getSiteVoteBySiteFormation(String codeSiteFormation);
}
