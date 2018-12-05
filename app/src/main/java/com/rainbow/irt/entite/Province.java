package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "PROVINCE")
public class Province {
    @PrimaryKey
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CodeProvince")
    @NonNull
    public String codeProvince;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;

    public Province(@NonNull String codeProvince, String libelle) {
        this.codeProvince = codeProvince;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
