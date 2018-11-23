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
    @NonNull
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
    @ColumnInfo(name = "NOM_COMPLET")
    public String nomComplet;
    @ColumnInfo(name = "EMAIL")
    public String email;
    @ColumnInfo(name = "PASSWORD")
    public String password;
    @ColumnInfo(name = "ACTIF")
    public String actif;
    @ColumnInfo(name = "CODE_PROFIL")
    public String codeProfil;

}
