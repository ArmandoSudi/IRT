package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
    @SerializedName("CODE_TERRITOIRE_VILLE")
    public String codeTerritoireVille;
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CODE_PROVINCE")
    public String codeProvince;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
    @ColumnInfo(name = "NIVEAU")
    @SerializedName("NIVEAU")
    public String niveau;
}

