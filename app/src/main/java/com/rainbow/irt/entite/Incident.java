package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "INCIDENT", foreignKeys = {
        @ForeignKey(entity = Panne.class, parentColumns = "CODE_PANNE", childColumns = "CODE_PANNE")
})
public class Incident {
    @PrimaryKey
    @ColumnInfo(name = "NUMERO_TICKET")
    @SerializedName("NUMERO_TICKET")
    public String numeroTicket;
    @ColumnInfo(name = "CODE_PANNE")
    @SerializedName("CODE_PANNE")
    public String codePanne;
    @ColumnInfo(name = "DATE_OUVERTURE")
    @SerializedName("DATE_OUVERTURE")
    public Date dateOuverture;
    @ColumnInfo(name = "DATE_FERMETURE")
    @SerializedName("DATE_FERMETURE")
    public Date dateFermeture;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
