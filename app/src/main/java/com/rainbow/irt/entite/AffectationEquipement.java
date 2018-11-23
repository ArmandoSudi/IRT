package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "AFFECTATION_EQUIPEMENT", foreignKeys = {
        @ForeignKey(entity = CentreVote.class, parentColumns = "CODE_CENTRE_VOTE", childColumns = "CODE_CENTRE_VOTE")
})
public class AffectationEquipement {
    @PrimaryKey
    @ColumnInfo(name = "CODE_AFFECTATION_EQUIEPEMENT")
    @SerializedName("CODE_AFFECTATION_EQUIEPEMENT")
    public String codeAffectationEquipement;
    @ColumnInfo(name = "CODE_CENTRE_VOTE")
    @SerializedName("CODE_CENTRE_VOTE")
    public String codeCentreVote;
    @ColumnInfo(name = "NUMERO_SERIE")
    @SerializedName("NUMERO_SERIE")
    public String numeroSerie;
    @ColumnInfo(name = "DATE_AFFECTATION")
    @SerializedName("DATE_AFFECTATION")
    public Date dateAffectation;
}
