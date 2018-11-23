package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "PANNE", foreignKeys = {
        @ForeignKey(entity = NiveauCriticitePanne.class, parentColumns = "CODE_CRITICITE", childColumns = "CODE_CRITICITE")
})
public class Panne {
    @PrimaryKey
    @ColumnInfo(name = "CODE_PANNE")
    @SerializedName("CODE_PANNE")
    public String codePanne;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
    @ColumnInfo(name = "CODE_CRITICITE")
    @SerializedName("CODE_CRITICITE")
    public String codeCriticite;
}
