package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "TERRITOIRE_VILLE", foreignKeys = {
        @ForeignKey(entity = Province.class, parentColumns = "CODE_PROVINCE", childColumns = "CODE_PROVINCE")
})
public class TerritoireVille {
    @PrimaryKey
    @ColumnInfo(name = "CODE_TERRITOIRE_VILLE")
    @SerializedName("CodeTerritoireVille")
    @NonNull
    public String codeTerritoireVille;
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CodeProvince")
    public String codeProvince;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;
    @ColumnInfo(name = "NIVEAU")
    @SerializedName("Niveau")
    public String niveau;

    public TerritoireVille(@NonNull String codeTerritoireVille, String codeProvince, String libelle, String niveau) {
        this.codeTerritoireVille = codeTerritoireVille;
        this.codeProvince = codeProvince;
        this.libelle = libelle;
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return libelle;
    }
}

