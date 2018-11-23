package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "PROVINCE_UTILISATEUR",
    primaryKeys = {"CODE_PROVINCE", "CODE_UTILISATEUR"},
        foreignKeys = {
            @ForeignKey(entity = Province.class, parentColumns = "CODE_PROVINCE", childColumns = "CODE_PROVINCE"),
                @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR")
        } 
)
public class ProvinceUtilisateur {
    @ColumnInfo(name = "CODE_PROVINCE")
    @SerializedName("CODE_PROVINCE")
    public String codeProvince;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
