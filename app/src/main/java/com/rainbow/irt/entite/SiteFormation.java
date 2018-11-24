package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
    @SerializedName("CODE_SITE_FORMATION")
    public String codeSiteFormation;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
    @ColumnInfo(name = "CODE_TERRITOIRE_VILLE")
    @SerializedName("CODE_TERRITOIRE_VILLE")
    public String codeTerritoireVille;
    @ColumnInfo(name = "LATITUDE")
    @SerializedName("LATITUDE")
    public double latitude;
    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("LONGITUDE")
    public double longitude;
}
