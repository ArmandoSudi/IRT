package com.rainbow.irt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;

import com.rainbow.irt.adapter.BureauVoteAdapter;
import com.rainbow.irt.entite.BureauVote;

import java.util.ArrayList;
import java.util.List;

public class AffecterEquipementActivity extends AppCompatActivity {

    BureauVoteAdapter mBureauVoteAdapter;
    RecyclerView mBureauVoteRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affecter_equipement);

        mBureauVoteAdapter = new BureauVoteAdapter(this, populateBureauVote());
        mBureauVoteRV = findViewById(R.id.bureau_vote_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBureauVoteRV.setLayoutManager(linearLayoutManager);
        mBureauVoteRV.setHasFixedSize(true);
        mBureauVoteRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mBureauVoteRV.setAdapter(mBureauVoteAdapter);

    }

    public List<BureauVote> populateBureauVote() {
        List<BureauVote> bureauVotes = new ArrayList<>();
        bureauVotes.add(new BureauVote("EP 1 Alfajiri", "College Aflajiri", "Nyalukemba" ));
        bureauVotes.add(new BureauVote("EP 2 Alfajiri", "College Aflajiri", "Nyalukemba" ));
        bureauVotes.add(new BureauVote("EP 3 Alfajiri", "College Aflajiri", "Nyalukemba" ));

        return bureauVotes;
    }
}
