package com.rainbow.irt.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.entite.BureauVote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sugar on 11/26/2018
 */
public class BureauVoteAdapter extends RecyclerView.Adapter<BureauVoteAdapter.VH> {

    Activity mActivity;
    List<BureauVote> mBureauVoteList = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView libelleTV, centreVoteTV;
        public VH(View view) {
            super(view);
             libelleTV = view.findViewById(R.id.libelle_tv);
             centreVoteTV = view.findViewById(R.id.centre_vote_tv);
        }
    }

    public BureauVoteAdapter(Activity activity, List<BureauVote> bureauVotes) {
        this.mActivity = activity;
        this.mBureauVoteList = bureauVotes;
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

    @Override
    public int getItemCount() {
        return mBureauVoteList.size();
    }
}
