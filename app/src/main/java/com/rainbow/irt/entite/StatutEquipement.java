package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "STATUT_EQUIPEMENT", foreignKeys =  {
        @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
        @ForeignKey(entity = Equipement.class, parentColumns = "CODE_EQUIPEMENT", childColumns = "CODE_EQUIPEMENT")
})
public class StatutEquipement {
    @PrimaryKey
    @ColumnInfo(name = "CODE_STATUT_EQUIPEMENT")
    @SerializedName("CODE_STATUT_EQUIPEMENT")
    @NonNull
    public String codeStatutEquipement;
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CODE_EQUIPEMENT")
    public String codeEquipement;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
    @ColumnInfo(name = "STATUT")
    @SerializedName("STATUT")
    public String statut;
    @ColumnInfo(name = "DATE_JOUR")
    @SerializedName("DATE_JOUR")
    public Date dateJour;
}
