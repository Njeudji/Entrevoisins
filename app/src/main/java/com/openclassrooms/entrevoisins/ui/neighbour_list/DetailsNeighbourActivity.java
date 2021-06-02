package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends AppCompatActivity {
    private NeighbourApiService mApiService;
    Neighbour neighbour;
    @BindView(R.id.neighbour_name_subtitle)
    TextView mNeighbourName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        Intent intent = getIntent();
        if (intent != null &&
                intent.getSerializableExtra(MyNeighbourRecyclerViewAdapter.KEY_NEIGHBOUR) != null
                && (intent.getSerializableExtra(MyNeighbourRecyclerViewAdapter.KEY_NEIGHBOUR) instanceof Neighbour)
        ) {
            neighbour = (Neighbour) getIntent().getSerializableExtra(MyNeighbourRecyclerViewAdapter.KEY_NEIGHBOUR);
        }
        initView();

    }

    public void initView() {
        mNeighbourName.setText(neighbour.getName());
    }
}