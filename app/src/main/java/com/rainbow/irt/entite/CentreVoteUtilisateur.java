package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "CENTRE_VOTE_UTILISATEUR",
    primaryKeys = {"CODE_CENTRE_VOTE", "CODE_UTILISATEUR"},
    foreignKeys = {
        @ForeignKey(entity = Utilisateur.class, parentColumns = "CODE_UTILISATEUR", childColumns = "CODE_UTILISATEUR"),
            @ForeignKey(entity = CentreVote.class , parentColumns = "CODE_CENTRE_VOTE", childColumns = "CODE_CENTRE_VOTE")
    })
public class CentreVoteUtilisateur {
    @ColumnInfo(name = "CODE_CENTRE_VOTE")
    @SerializedName("CODE_CENTRE_VOTE")
    public String codeCentreVote;
    @ColumnInfo(name = "CODE_UTILISATEUR")
    @SerializedName("CODE_UTILISATEUR")
    public String codeUtilisateur;
}
