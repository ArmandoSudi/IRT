package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "UTILISATEUR_L2_L3",
        primaryKeys = {"CODE_TERRITOIRE_VILLE", "CODE_UTILISATEUR"},
        foreignKeys = {
            @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
            @ForeignKey(entity = TerritoireVille.class, parentColumns = "CODE_TERRITOIRE_VILLE", childColumns = "CODE_TERRITOIRE_VILLE")
                
})
public class UtilisateurL2L3 {
    @ColumnInfo(name = "CODE_TERRITOIRE_VILLE")
    @SerializedName("CODE_TERRITOIRE_VILLE")
    @NonNull
    public String codeTerritoireVille;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    @NonNull
    public String codeUtilisateur;
}
