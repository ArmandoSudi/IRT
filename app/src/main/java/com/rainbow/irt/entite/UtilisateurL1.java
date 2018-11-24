package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "UTILISATEUR_L1",
        primaryKeys = {"CODE_UTILISATEUR", "CODE_SITE_FORMATION"},
        foreignKeys = {
            @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
                @ForeignKey(entity = SiteFormation.class, parentColumns = "CODE_SITE_FORMATION", childColumns = "CODE_SITE_FORMATION")
        }
)
public class UtilisateurL1 {
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
    @ColumnInfo(name = "CODE_SITE_FORMATION")
    @SerializedName("CODE_SITE_FORMATION")
    public String codeSiteFormation;
}
