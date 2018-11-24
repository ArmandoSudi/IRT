package com.rainbow.irt.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.entite.LexiquePanne;
import com.rainbow.irt.entite.Profil;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;
import com.rainbow.irt.entite.StatutEquipement;
import com.rainbow.irt.entite.TerritoireVille;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.entite.UtilisateurL1;
import com.rainbow.irt.entite.UtilisateurL2L3;
import com.rainbow.irt.entite.UtilisateurTCV;
import com.rainbow.irt.utils.DateConverts;

/**
 * Created by Sugar on 11/20/2018
 */
@Database(entities = {
        BureauVote.class,
        BureauVoteEquipement.class,
        Equipement.class,
        Incident.class,
        LexiquePanne.class,
        Profil.class,
        Province.class,
        SiteFormation.class,
        SiteVote.class,
        StatutEquipement.class,
        TerritoireVille.class,
        Utilisateur.class,
        UtilisateurL1.class,
        UtilisateurL2L3.class,
        UtilisateurTCV.class,},
        version = 1, exportSchema = false

)
@TypeConverters(DateConverts.class)
public abstract class IrtDatabase extends RoomDatabase {

    private static final String DB_NAME = "irt.db";
    static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    public static IrtDatabase INSTANCE;

    public static IrtDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (IrtDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IrtDatabase.class, DB_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
