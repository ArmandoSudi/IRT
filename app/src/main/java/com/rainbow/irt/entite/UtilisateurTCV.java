package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "UTILISATEUR_TCV",
    primaryKeys = {"CODE_BUREAU_VOTE", "CODE_UTILISATEUR"},
    foreignKeys = {
        @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
            @ForeignKey(entity = BureauVote.class , parentColumns = "CODE_BUREAU_VOTE", childColumns = "CODE_BUREAU_VOTE")
    })
public class UtilisateurTCV {
    @ColumnInfo(name = "CODE_BUREAU_VOTE")
    @SerializedName("CODE_BUREAU_VOTE")
    public String codeBureauVote;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
