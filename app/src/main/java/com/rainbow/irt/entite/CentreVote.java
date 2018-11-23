package com.rainbow.irt.entite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sugar on 11/22/2018
 */
@Entity(tableName = "CENTRE_VOTE",foreignKeys = {
        @ForeignKey(entity = CentreSupport.class, parentColumns = "CODE_CENTRE_SUPPORT", childColumns = "CODE_CENTRE_SUPPORT")
})
public class CentreVote {
    @PrimaryKey
    @ColumnInfo(name = "CODE_CENTRE_VOTE")
    @SerializedName("CODE_CENTRE_VOTE")
    public String codeCentreVote;
    @ColumnInfo(name = "LATITUDE")
    @SerializedName("LATITUDE")
    public double latitude;
    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("LONGITUDE")
    public double longitude;
    @ColumnInfo(name = "CODE_CENTRE_SUPPORT")
    @SerializedName("CODE_CENTRE_SUPPORT")
    public String codeCentreSupport;
}
