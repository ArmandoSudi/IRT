package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "LEXIQUE_PANNE")
public class LexiquePanne {
    @PrimaryKey
    @ColumnInfo(name = "CODE_LEXIQUE_PANNE")
    @SerializedName("CODE_LEXIQUE_PANNE")
    public String codeLexiquePanne;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
}
