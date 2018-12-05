package com.rainbow.irt.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rainbow.irt.entite.SiteFormation;

import java.util.List;

/**
 * Created by Sugar on 11/23/2018
 */
@Dao
public interface ISiteFormationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(SiteFormation...siteFormations);

    @Update
    int update(SiteFormation...siteFormations);

    @Delete
    void delete(SiteFormation...siteFormations);

    @Query("DELETE FROM SITE_FORMATION")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiteFormation> siteFormations);

    @Query("SELECT * FROM SITE_FORMATION")
    List<SiteFormation> getAll();

    @Query("SELECT * FROM SITE_FORMATION WHERE CODE_TERRITOIRE_VILLE=:codeTerritoireVille")
    List<SiteFormation> getSiteFormationByTerritoireVille(String codeTerritoireVille);
}
