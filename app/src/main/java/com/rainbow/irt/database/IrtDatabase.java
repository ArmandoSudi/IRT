package com.rainbow.irt.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rainbow.irt.dao.IBureauVoteDao;
import com.rainbow.irt.dao.IBureauVoteEquipementDao;
import com.rainbow.irt.dao.IEquipementDao;
import com.rainbow.irt.dao.IIncidentDao;
import com.rainbow.irt.dao.ILexiquePanneDao;
import com.rainbow.irt.dao.IProfilDao;
import com.rainbow.irt.dao.IProvinceDao;
import com.rainbow.irt.dao.ISiteFormationDao;
import com.rainbow.irt.dao.ISiteVoteDao;
import com.rainbow.irt.dao.IStatutEquipementDao;
import com.rainbow.irt.dao.ITerritoireVilleDao;
import com.rainbow.irt.dao.IUtilisateurDao;
import com.rainbow.irt.dao.IUtilisateurL1Dao;
import com.rainbow.irt.dao.IUtilisateurL2L3Dao;
import com.rainbow.irt.dao.IUtilisateurTCVDao;
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

    public abstract IUtilisateurDao getIUtilisateurDao();
    public abstract IProfilDao getIProfileDao();
    public abstract ISiteFormationDao getISiteFormationDao();
    public abstract ISiteVoteDao getISiteVoteDao();
    public abstract IProvinceDao getIProvinceDao();
    public abstract IUtilisateurL2L3Dao getIUtilisateurL2L3Dao();
    public abstract IUtilisateurTCVDao getIUtilisateurTCVDao();
    public abstract IEquipementDao getIEquipementDao();
    public abstract IBureauVoteEquipementDao getIBureauVoteEquipementDao();
    public abstract IIncidentDao getIIncidentDao();
    public abstract ITerritoireVilleDao getITerritoireVilleDao();
    public abstract IBureauVoteDao getIBureauVoteDao();
    public abstract IUtilisateurL1Dao getIUtilisateurL1Dao();
    public abstract ILexiquePanneDao getILexiquePanneDao();
    public abstract IStatutEquipementDao getIStatutEquipementDao();

}
