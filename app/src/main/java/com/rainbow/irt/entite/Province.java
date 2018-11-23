package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "PROVINCE")
public class Province {
    @PrimaryKey
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CODE_PROVINCE")
    public String codeProvince;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
}
