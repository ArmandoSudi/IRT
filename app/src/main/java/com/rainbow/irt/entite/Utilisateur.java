package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
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
    @SerializedName("CodeUtilisateur")
    @NonNull
    public String codeUtilisateur;
    @ColumnInfo(name = "NOM")
    @SerializedName("Nom")
    public String nom;
    @ColumnInfo(name = "POSTNOM")
    @SerializedName("Postnom")
    public String postnom;
    @ColumnInfo(name = "PRENOM")
    @SerializedName("Prenom")
    public String prenom;
    @ColumnInfo(name = "PASSWORD")
    @SerializedName("Password")
    public String password;
    @ColumnInfo(name = "ACTIF")
    @SerializedName("Actif")
    public boolean actif;
    @ColumnInfo(name = "CODE_PROFIL")
    @SerializedName("CodeProfil")
    public String codeProfil;
    @ColumnInfo(name = "TELEPHONE")
    @SerializedName("Telephone")
    public String telephone;

    @Ignore
    @SerializedName("CodeProvince")
    public String codeProvince;

    @Ignore
    @SerializedName("CodeTerritoireVille")
    public String codeTerritoireVille;

    @Ignore
    @SerializedName("CodeSiteFormation")
    public String codeSiteFormation;

    @Ignore
    @SerializedName("CodeBureauVote")
    public String codeBureauVote;

    public Utilisateur(String codeUtilisateur, String nom, boolean actif, String codeProfil, String telephone) {
        this.codeUtilisateur = codeUtilisateur;
        this.nom = nom;
        this.actif = actif;
        this.codeProfil = codeProfil;
        this.telephone = telephone;
    }

    @Ignore
    public Utilisateur(@NonNull String codeUtilisateur, String nom, String postnom, String prenom, String password, boolean actif, String codeProfil, String telephone) {
        this.codeUtilisateur = codeUtilisateur;
        this.nom = nom;
        this.postnom = postnom;
        this.prenom = prenom;
        this.password = password;
        this.actif = actif;
        this.codeProfil = codeProfil;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + " " + this.postnom + " " + this.codeProfil + " " + this.actif +  " " + this.telephone;
    }
}
