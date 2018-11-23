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
@Entity(tableName = "ETAT_RESOLUTION_INCIDENT", foreignKeys = {
        @ForeignKey(entity = Incident.class, parentColumns = "NUMERO_TICKET", childColumns = "NUMERO_TICKET")
})
public class EtatResolutionIncident {
    @PrimaryKey
    @ColumnInfo(name = "NUMERO_TICKET")
    @SerializedName("NUMERO_TICKET")
    public String numeroTicket;
    @ColumnInfo(name = "DETAIL_RESOLUTION")
    @SerializedName("DETAIL_RESOLUTION")
    public String detailResolution;
    @ColumnInfo(name = "DATE_RESOLUTION")
    @SerializedName("DATE_RESOLUTION")
    public Date dateResolution;
}
