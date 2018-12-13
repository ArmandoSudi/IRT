package com.rainbow.irt.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.activity.DetailsEquipementActivity;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.utils.Constant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sugar on 11/26/2018
 */
public class BureauVoteAdapter extends RecyclerView.Adapter<BureauVoteAdapter.VH> {

    Activity mActivity;
    List<BureauVote> mBureauVoteList = new ArrayList<>();
    String mCodeEquipement;
    boolean mIsAffected;
    private Calendar mCalendar = Calendar.getInstance();

    static class VH extends RecyclerView.ViewHolder {
        TextView libelleTV, centreVoteTV;
        public VH(View view) {
            super(view);
             libelleTV = view.findViewById(R.id.libelle_tv);
             centreVoteTV = view.findViewById(R.id.centre_vote_tv);
        }
    }

    public BureauVoteAdapter(Activity activity, List<BureauVote> bureauVotes, String codeEquipement, boolean isAffected) {
        this.mActivity = activity;
        this.mBureauVoteList = bureauVotes;
        this.mCodeEquipement = codeEquipement;
        this.mIsAffected = isAffected;
    }


    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        final BureauVote bureauVote = mBureauVoteList.get(i);
        vh.libelleTV.setText(bureauVote.libelle);
        vh.centreVoteTV.setText(bureauVote.centreVote);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "Confirm affectation", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("Voulez affecter cet equipement au Bureau de vote: "
                 + bureauVote.libelle + " ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                affecterEquipement(mCodeEquipement, bureauVote.codeBureauVote);
                            }
                        })
                        .setNegativeButton("Non", null).show();
            }
        });
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bureau_vote_item, viewGroup, false);

        return new VH(view);
    }

    public void affecterEquipement(String codeEquipement, String codeBureauVote) {
        Date dateAffecation = mCalendar.getTime();
        BureauVoteEquipement affectation = new BureauVoteEquipement(codeEquipement, codeBureauVote, dateAffecation);
        saveAffecation(affectation);
    }

    public void addAll(List<BureauVote> bureauVotes){
        this.mBureauVoteList.addAll(bureauVotes);
    }

    public void clearAll() {
        this.mBureauVoteList.clear();
    }

    @Override
    public int getItemCount() {
        return mBureauVoteList.size();
    }

    public void saveAffecation(final BureauVoteEquipement bureauVoteEquipement) {
        (new AsyncTask<BureauVoteEquipement, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mActivity, "Equipement affecte", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, DetailsEquipementActivity.class);
                intent.putExtra(Constant.KEY_CODE_EQUIPEMENT, mCodeEquipement);
                intent.putExtra(Constant.KEY_IS_AFFECTED, mIsAffected);
                mActivity.startActivity(intent);
            }

            @Override
            protected Void doInBackground(BureauVoteEquipement... bureauVoteEquipements) {
                IrtDatabase.getInstance(mActivity).getIBureauVoteEquipementDao().insert(bureauVoteEquipements);
                return null;
            }
        }).execute(bureauVoteEquipement);
    }
}
