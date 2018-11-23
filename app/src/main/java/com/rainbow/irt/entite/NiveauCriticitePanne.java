package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "NIVEAU_CRITICITE_PANNE")
public class NiveauCriticitePanne {
    @PrimaryKey
    @ColumnInfo(name = "CODE_CRITICITE")
    @SerializedName("CODE_CRITICITE")
    public String codeCriticite;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
}

