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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CODE_INCIDENT")
    @SerializedName("CodeIncident")
    @NonNull
    public int codeIncident;
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CodeEquipement")
    public String codeEquipement;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CodeUtilisateur")
    public String codeUtilisateur;
    @ColumnInfo(name = "DATE_OUVERTURE")
    @SerializedName("DateOuverture")
    public Date dateOuverture;
    @ColumnInfo(name = "CODE_LEXIQUE_PANNE")
    @SerializedName("CodeLexiquePanne")
    public String codeLexiquePanne;
    @ColumnInfo(name = "DATE_FERMETURE")
    @SerializedName("DateFermeture")
    public Date dateFermeture;
    @ColumnInfo(name = "DATE_RESOLUTION")
    @SerializedName("DateResolution")
    public Date dateResolution;
    @ColumnInfo(name = "DETAILS_RESOLUTION")
    @SerializedName("DetailsResolution")
    public String detailsResolution;

    public Incident(String codeEquipement, String codeUtilisateur, Date dateOuverture, String codeLexiquePanne, Date dateFermeture, Date dateResolution, String detailsResolution) {
        this.codeEquipement = codeEquipement;
        this.codeUtilisateur = codeUtilisateur;
        this.dateOuverture = dateOuverture;
        this.codeLexiquePanne = codeLexiquePanne;
        this.dateFermeture = dateFermeture;
        this.dateResolution = dateResolution;
        this.detailsResolution = detailsResolution;
    }

    @Override
    public String toString() {
        return this.dateOuverture + " " + codeLexiquePanne + " " + this.detailsResolution;
    }
}
