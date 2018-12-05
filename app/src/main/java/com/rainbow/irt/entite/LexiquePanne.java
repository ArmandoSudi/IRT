package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "LEXIQUE_PANNE")
public class LexiquePanne {
    @PrimaryKey
    @ColumnInfo(name = "CODE_LEXIQUE_PANNE")
    @SerializedName("CodeLexiquePanne")
    @NonNull
    public String codeLexiquePanne;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;

    public LexiquePanne(@NonNull String codeLexiquePanne, String libelle) {
        this.codeLexiquePanne = codeLexiquePanne;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
