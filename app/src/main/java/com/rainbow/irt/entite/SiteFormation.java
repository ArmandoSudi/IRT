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
@Entity(tableName = "SITE_FORMATION", foreignKeys = {
        @ForeignKey(entity = TerritoireVille.class, parentColumns = "CODE_TERRITOIRE_VILLE", childColumns = "CODE_TERRITOIRE_VILLE")
})
public class SiteFormation {
    @PrimaryKey
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CodeSiteFormation")
    @NonNull
    public String codeSiteFormation;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;
    @ColumnInfo(name = "CODE_TERRITOIRE_VILLE")
    @SerializedName("CodeTerritoireVille")
    public String codeTerritoireVille;
    @ColumnInfo(name = "LATITUDE")
    @SerializedName("Latitude")
    public double latitude;
    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("Longitude")
    public double longitude;

    public SiteFormation(@NonNull String codeSiteFormation, String libelle, String codeTerritoireVille, double latitude, double longitude) {
        this.codeSiteFormation = codeSiteFormation;
        this.libelle = libelle;
        this.codeTerritoireVille = codeTerritoireVille;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.libelle;
    }
}
