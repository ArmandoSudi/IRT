package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
    @SerializedName("CodeSiteVote")
    @NonNull
    public String codeSiteVote;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;
    @ColumnInfo(name = "LATITUDE")
    @SerializedName("Latitude")
    public Double latitude;
    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("Longitude")
    public Double longitude;
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CodeSiteFormation")
    public String codeSiteFormation;

    public SiteVote(@NonNull String codeSiteVote, String libelle, Double latitude, Double longitude, String codeSiteFormation) {
        this.codeSiteVote = codeSiteVote;
        this.libelle = libelle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codeSiteFormation = codeSiteFormation;
    }

    @Override
    public String toString() {
        return this.libelle;
    }
}
