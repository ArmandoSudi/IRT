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
@Entity(tableName = "EQUIPEMENT", foreignKeys =  {
        @ForeignKey(entity = SiteFormation.class, parentColumns = "CODE_SITE_FORMATION", childColumns = "CODE_SITE_FORMATION")
})
public class Equipement {
    @PrimaryKey
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CODE_EQUIPEMENT")
    @NonNull
    public String codeEquipement;
    @ColumnInfo(name = "NUMERO_SERIE")
    @SerializedName("NUMERO_SERIE")
    public String numeroSerie;
    @ColumnInfo(name = "DESCRIPTION")
    @SerializedName("DESCRIPTION")
    public String description;
    @ColumnInfo(name = "TYPE_EQUIPEMENT")
    @SerializedName("TYPE_EQUIPEMENT")
    public String typeEquipement;
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CODE_SITE_FORMATION")
    public String codeSiteFormation;

    public Equipement(String numeroSerie, String description, String typeEquipement, String codeSiteFormation) {
        this.numeroSerie = numeroSerie;
        this.description = description;
        this.typeEquipement = typeEquipement;
        this.codeSiteFormation = codeSiteFormation;
    }
}
