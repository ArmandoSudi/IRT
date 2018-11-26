package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "BUREAU_VOTE_EQUIPEMENT",
        primaryKeys = { "CODE_EQUIPEMENT", "CODE_BUREAU_VOTE" },
        foreignKeys = {
        @ForeignKey(entity = BureauVote.class, parentColumns = "CODE_BUREAU_VOTE", childColumns = "CODE_BUREAU_VOTE"),
                @ForeignKey(entity = Equipement.class, parentColumns = "CODE_EQUIPEMENT", childColumns = "CODE_EQUIPEMENT")
})
public class BureauVoteEquipement {
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CODE_EQUIPEMENT")
    @NonNull
    public String codeEquipement;
    @ColumnInfo(name = "CODE_BUREAU_VOTE")
    @SerializedName("CODE_BUREAU_VOTE")
    @NonNull
    public String codeBureauVote;
    @ColumnInfo(name = "DATE_JOUR")
    @SerializedName("DATE_JOUR")
    public Date dateJour;
}
