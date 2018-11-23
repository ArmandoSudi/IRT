package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "ETAT_EQUIPEMENT")
public class EtatEquipement {
    @PrimaryKey
    @ColumnInfo(name = "NUMERO_SERIE")
    @SerializedName("NUMERO_SERIE")
    public String numeroSerie;
    @ColumnInfo(name = "ETAT")
    @SerializedName("ETAT")
    public String etat;
    @ColumnInfo(name = "DATE_ENREGISTREMENT_ETAT")
    @SerializedName("DATE_ENREGISTREMENT_ETAT")
    public Date dateEnregistrementEtat;
}
