package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "INCIDENT", foreignKeys = {
        @ForeignKey(entity = LexiquePanne.class, parentColumns = "CODE_LEXIQUE_PANNE", childColumns = "CODE_LEXIQUE_PANNE"),
        @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
        @ForeignKey(entity = Equipement.class, parentColumns = "CODE_EQUIPEMENT", childColumns = "CODE_EQUIPEMENT")
})
public class Incident {
    @PrimaryKey
    @ColumnInfo(name = "CODE_INCIDENT")
    @SerializedName("CODE_INCIDENT")
    @NonNull
    public String codeIncident;
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CODE_EQUIPEMENT")
    public String codeEquipement;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
    @ColumnInfo(name = "DATE_OUVERTURE")
    @SerializedName("DATE_OUVERTURE")
    public Date dateOuverture;
    @ColumnInfo(name = "CODE_LEXIQUE_PANNE")
    @SerializedName("CODE_LEXIQUE_PANNE")
    public String codeLexiquePanne;
    @ColumnInfo(name = "DATE_FERMETURE")
    @SerializedName("DATE_FERMETURE")
    public Date dateFermeture;
    @ColumnInfo(name = "DATE_RESOLUTION")
    @SerializedName("DATE_RESOLUTION")
    public Date dateResolution;
    @ColumnInfo(name = "DETAILS_RESOLUTION")
    @SerializedName("DETAILS_RESOLUTION")
    public String detailsResolution;
}
