package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "BUREAU_VOTE_EQUIPEMENT",
        primaryKeys = { "CODE_EQUIPEMENT", "CODE_BUREAU_VOTE" },
        foreignKeys = {
        @ForeignKey(entity = SiteVote.class, parentColumns = "CODE_CENTRE_VOTE", childColumns = "CODE_CENTRE_VOTE")
})
public class BureauVoteEquipement {
    @ColumnInfo(name = "CODE_EQUIPEMENT")
    @SerializedName("CODE_EQUIPEMENT")
    public String codeEquipement;
    @ColumnInfo(name = "CODE_BUREAU_VOTE")
    @SerializedName("CODE_BUREAU_VOTE")
    public String codeBureauVote;
    @ColumnInfo(name = "DATE_JOUR")
    @SerializedName("DATE_JOUR")
    public Date dateJour;
}
