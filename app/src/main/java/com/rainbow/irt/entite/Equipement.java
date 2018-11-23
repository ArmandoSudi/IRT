package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "EQUIPEMENT", foreignKeys =  {
        @ForeignKey(entity = TypeEquipement.class, parentColumns = "CODE_TYPE_EQUIPEMENT", childColumns = "CODE_TYPE_EQUIPEMENT")
})
public class Equipement {
    @PrimaryKey
    @ColumnInfo(name = "NUMERO_SERIE")
    @SerializedName("NUMERO_SERIE")
    public String numeroSerie;
    @ColumnInfo(name = "CODE_TYPE_EQUIPEMENT")
    @SerializedName("CODE_TYPE_EQUIPEMENT")
    public String codeTypeEquipement;
}
