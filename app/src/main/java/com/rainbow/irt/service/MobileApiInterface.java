package com.rainbow.irt.service;

import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.entite.LexiquePanne;
import com.rainbow.irt.entite.Profil;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;
import com.rainbow.irt.entite.TerritoireVille;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.entite.UtilisateurTCV;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Sugar on 12/3/2018
 */
public interface MobileApiInterface {

    @GET("provinces/")
    public Call<List<Province>> getProvinceList();

    @GET("territoires/")
    public Call<List<TerritoireVille>> getTerritoireVilleList();

    @GET("sitesformation/")
    public Call<List<SiteFormation>> getSiteFormationList();

    @GET("sitesvotes/")
    public Call<List<SiteVote>> getSiteVoteList();

    @GET("bureauxvote/")
    public Call<List<BureauVote>> getBureauVoteList();

    @GET("equipements/")
    public Call<List<Equipement>> getEquipementList();

    @GET("lexiquepannes/")
    public Call<List<LexiquePanne>> getLexiquePanneList();

    @GET("profile/")
    public Call<List<Profil>> getProfilList();

    @GET("utilisateur/{codeUtilisateur}/")
    public Call<Utilisateur> getUtilisateur(String codeUtilisateur);

    @Headers({"Content-Type: application/json"})
    @POST("saveincidents/")
    public Call<String> postIncidentList(@Body List<Incident> incidents);

    @POST("post/affecation/")
    public Call<List<BureauVoteEquipement>> postBureauVoteEquipement(@Body List<BureauVoteEquipement> bureauVoteEquipements);

    @POST("post/utilisateur/")
    public Call<Utilisateur> postUtilisateur(@Body Utilisateur utilisateur);


    @POST("savetcv/")
    public Call<List<Utilisateur>> postUtilisateurs(@Body List<Utilisateur> utilisateurs);

    @POST("post/utilisateur_tcv/")
    public Call<List<UtilisateurTCV>> postUtilisateurTCV(@Body List<UtilisateurTCV> utilisateurTCVS);

}
