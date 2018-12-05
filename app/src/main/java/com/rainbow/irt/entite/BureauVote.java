package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/24/2018
 */
@Entity(tableName = "BUREAU_VOTE", foreignKeys = {
        @ForeignKey(entity = SiteVote.class, parentColumns = "CODE_SITE_VOTE", childColumns = "CODE_SITE_VOTE")
})
public class BureauVote {
    @PrimaryKey
    @ColumnInfo(name = "CODE_BUREAU_VOTE")
    @SerializedName("CodeBureauVote")
    @NonNull
    public String codeBureauVote;
    @ColumnInfo(name = "LIBELLE")
    @SerializedName("Libelle")
    public String libelle;
    @ColumnInfo(name = "CODE_SITE_VOTE")
    @SerializedName("CodeSiteVote")
    public String codeSiteVote;
    @ColumnInfo(name = "CENTRE_VOTE")
    @SerializedName("CentreVote")
    public String centreVote;

    public BureauVote(String codeBureauVote, String libelle, String codeSiteVote, String centreVote) {
        this.codeBureauVote = codeBureauVote;
        this.libelle = libelle;
        this.codeSiteVote = codeSiteVote;
        this.centreVote = centreVote;
    }
}
