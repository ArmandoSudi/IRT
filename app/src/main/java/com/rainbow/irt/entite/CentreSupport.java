package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "CENTRE_SUPPORT", foreignKeys = {
        @ForeignKey(entity = Province.class, parentColumns = "CODE_PROVINCE", childColumns = "CODE_PROVINCE")
})
public class CentreSupport {
    @PrimaryKey
    @ColumnInfo(name = "CODE_CENTRE_SUPPORT")
    @SerializedName("CODE_CENTRE_SUPPORT")
    public String codeCentreSupport;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("LIBELLE")
    public String libelle;
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CODE_PROVINCE")
    public String codeProvince;
}
