package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "TYPE_EQUIPEMENT")
public class TypeEquipement {
    @PrimaryKey
    @ColumnInfo(name = "CODE_TYPE_EQUIPEMENT")
    @SerializedName("CODE_TYPE_EQUIPEMENT")
    public String codeTypeEquipement;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
}
