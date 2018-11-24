package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/20/2018
 */
@Entity(tableName = "UTILISATEUR", foreignKeys = {
        @ForeignKey(entity = Profil.class, parentColumns = "CODE_PROFIL", childColumns = "CODE_PROFIL")
})
public class Utilisateur {

    @PrimaryKey
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
    @ColumnInfo(name = "NOM")
    @SerializedName("NOM")
    public String nom;
    @ColumnInfo(name = "POSTNOM")
    @SerializedName("POSTNOM")
    public String postnom;
    @ColumnInfo(name = "PRENOM")
    @SerializedName("PRENOM")
    public String prenom;
    @ColumnInfo(name = "PASSWORD")
    @SerializedName("PASSWORD")
    public String password;
    @ColumnInfo(name = "ACTIF")
    @SerializedName("ACTIF")
    public boolean actif;
    @ColumnInfo(name = "CODE_PROFIL")
    @SerializedName("CODE_PROFIL")
    public String codeProfil;
    @ColumnInfo(name = "TELEPHONE")
    @SerializedName("TELEPHONE")
    public String telephone;

}
