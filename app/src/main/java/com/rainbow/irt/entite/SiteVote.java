package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "SITE_VOTE",foreignKeys = {
        @ForeignKey(entity = SiteFormation.class, parentColumns = "CODE_SITE_FORMATION", childColumns = "CODE_SITE_FORMATION")
})
public class SiteVote {
    @PrimaryKey
    @ColumnInfo(name = "CODE_SITE_VOTE")
    @SerializedName("CODE_SITE_VOTE")
    public String codeSiteVote;
    @ColumnInfo(name = "LATITUDE")
    @SerializedName("LATITUDE")
    public double latitude;
    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("LONGITUDE")
    public double longitude;
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CODE_SITE_FORMATION")
    public String codeSiteFormation;
}
