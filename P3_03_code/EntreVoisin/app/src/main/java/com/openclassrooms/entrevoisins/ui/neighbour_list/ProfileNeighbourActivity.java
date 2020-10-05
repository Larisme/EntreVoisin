package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.im_return)
    public ImageView mBackReturn;
    @BindView(R.id.im_avatar)
    public ImageView mNeighbourAvatar;
    @BindView(R.id.tv_avatar_name)
    public TextView mNeighbourAvatarName;
    @BindView(R.id.fab_favorite)
    public FloatingActionButton mFavoriteButton;
    @BindView(R.id.tv_name)
    public TextView mNeighbourName;
    @BindView(R.id.tv_location)
    public TextView mNeighbourAddress;
    @BindView(R.id.tv_phone)
    public TextView mNeighbourPhoneNumber;
    @BindView(R.id.tv_website)
    public TextView mNeighbourWebsite;
    @BindView(R.id.tv_about_text)
    public TextView mNeighbourAboutMe;

    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);

        mNeighbour = (Neighbour) getIntent().getSerializableExtra("Neighbour");
        NeighbourApiService mNeighbourApiService = DI.getNeighbourApiService();
        List<Neighbour> neighbours = mNeighbourApiService.getNeighbours();

        Glide.with(mNeighbourAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(mNeighbourAvatar);
        mNeighbourAvatarName.setText(mNeighbour.getName());
        mNeighbourName.setText(mNeighbour.getName());
        mNeighbourAddress.setText(mNeighbour.getAddress().replace(';', 'à'));
        mNeighbourPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mNeighbourWebsite.setText(String.format("www.facebook.fr/%s", mNeighbour.getName().toLowerCase()));
        mNeighbourAboutMe.setText(mNeighbour.getAboutMe());

        if (mNeighbour.getFavorite()) {
            Drawable drawableOn = getResources().getDrawable(R.drawable.ic_star_white_24dp);
            int colorOn = getResources().getColor(R.color.colorYellow);
            mFavoriteButton.setColorFilter(colorOn);
            mFavoriteButton.setImageDrawable(drawableOn);
        }
        else {
            Drawable drawableOff = getResources().getDrawable(R.drawable.ic_star_border_white_24dp);
            int colorOff = getResources().getColor(R.color.colorGrey);
            mFavoriteButton.setColorFilter(colorOff);
            mFavoriteButton.setImageDrawable(drawableOff);
        }

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNeighbour.getFavorite()) {
                    mNeighbour.favoriteOff();
                    Drawable drawableOff = getResources().getDrawable(R.drawable.ic_star_border_white_24dp);
                    int colorOff = getResources().getColor(R.color.colorGrey);
                    mFavoriteButton.setColorFilter(colorOff);
                    mFavoriteButton.setImageDrawable(drawableOff);
                }
                else {
                    mNeighbour.favoriteOn();
                    Drawable drawableOn = getResources().getDrawable(R.drawable.ic_star_white_24dp);
                    int colorOn = getResources().getColor(R.color.colorYellow);
                    mFavoriteButton.setColorFilter(colorOn);
                    mFavoriteButton.setImageDrawable(drawableOn);
                }
            }
        });
        mBackReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}
