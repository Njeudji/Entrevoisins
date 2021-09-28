package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsNeighbourActivity extends AppCompatActivity {
    private NeighbourApiService mApiService;
    Neighbour neighbour;
    @BindView(R.id.neighbour_name_subtitle)
    TextView mNeighbourName;
    @BindView(R.id.item_address_text)
    TextView mNeighbourAddress;
    @BindView(R.id.neighbour_image)
    ImageView mNeighbourAvatar;
    @BindView(R.id.item_phone_text)
    TextView mNeighbourPhone;
    @BindView(R.id.item_link_text)
    TextView mNeighbourLink;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton isFavoriteFab;
    @BindView(R.id.item_about_me_text)
    TextView mNeighbourAboutMe;
    @BindView(R.id.neighbour_name_subtitle2)
    TextView mNeighbourName2;


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
        mNeighbourAboutMe.setText(neighbour.getAboutMe());
        mNeighbourName.setText(neighbour.getName());
        mNeighbourAddress.setText(neighbour.getAddress());
        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .into(mNeighbourAvatar);
        mNeighbourPhone.setText(neighbour.getPhoneNumber());
        mNeighbourLink.setText(neighbour.getAvatarUrl());
        updateFabImage();
        mNeighbourName2.setText(neighbour.getName());

    }

    private void updateFabImage() {
        if (neighbour.isFavorite()) {
            isFavoriteFab.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            isFavoriteFab.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }


    @OnClick(R.id.floatingActionButton)
    void updateFavorite() {
        neighbour.setFavorite(!neighbour.isFavorite());
        updateFabImage();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onItemButtonReturnClicked();
    }

    @OnClick(R.id.item_button_return)
    void onItemButtonReturnClicked() {
        mApiService.updateFabNeighbour(neighbour);
        finish();
    }
}