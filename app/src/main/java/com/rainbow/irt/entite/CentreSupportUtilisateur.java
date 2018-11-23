package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "CENTRE_SUPPORT_UTILISATEUR",
        primaryKeys = {"CODE_CENTRE_SUPPORT", "CODE_UTILISATEUR"},
        foreignKeys = {
            @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
            @ForeignKey(entity = CentreSupport.class, parentColumns = "CODE_CENTRE_SUPPORT", childColumns = "CODE_CENTRE_SUPPORT")
                
})
public class CentreSupportUtilisateur {
    @ColumnInfo(name = "CODE_CENTRE_SUPPORT")
    @SerializedName("CODE_CENTRE_SUPPORT")
    public String codeCentreSupport;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
