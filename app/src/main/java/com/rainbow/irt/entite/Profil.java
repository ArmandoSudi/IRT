package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "PROFIL")
public class Profil {

    @PrimaryKey
    @ColumnInfo(name = "CODE_PROFIL")
    @SerializedName("CODE_PROFIL")
    public String codeProfil;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
}
