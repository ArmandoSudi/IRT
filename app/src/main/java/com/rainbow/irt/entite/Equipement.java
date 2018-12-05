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
    @SerializedName("CodeEquipement")
    @NonNull
    public String codeEquipement;
    @ColumnInfo(name = "NUMERO_SERIE")
    @SerializedName("NumeroSerie")
    public String numeroSerie;
    @ColumnInfo(name = "DESCRIPTION")
    @SerializedName("Description")
    public String description;
    @ColumnInfo(name = "TYPE_EQUIPEMENT")
    @SerializedName("TypeEquipement")
    public String typeEquipement;
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CodeSiteFormation")
    public String codeSiteFormation;

    public Equipement(String codeEquipement, String numeroSerie, String description, String typeEquipement, String codeSiteFormation) {
        this.codeEquipement = codeEquipement;
        this.numeroSerie = numeroSerie;
        this.description = description;
        this.typeEquipement = typeEquipement;
        this.codeSiteFormation = codeSiteFormation;
    }

    @Override
    public String toString() {
        return numeroSerie;
    }
}
