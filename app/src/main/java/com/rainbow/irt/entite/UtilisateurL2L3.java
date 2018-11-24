package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "UTILISATEUR_L2_L3",
        primaryKeys = {"CODE_TERRIOTOIRE_VILLE", "CODE_UTILISATEUR"},
        foreignKeys = {
            @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
            @ForeignKey(entity = TerritoireVille.class, parentColumns = "CODE_TERRIOTOIRE_VILLE", childColumns = "CODE_TERRIOTOIRE_VILLE")
                
})
public class UtilisateurL2L3 {
    @ColumnInfo(name = "CODE_TERRIOTOIRE_VILLE")
    @SerializedName("CODE_TERRIOTOIRE_VILLE")
    public String codeTerritoireVille;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
