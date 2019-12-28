package com.restaurant.arrifqiaziz.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.activity.profil.UbahProfilActivity;
import com.restaurant.arrifqiaziz.data.Session;
import com.restaurant.arrifqiaziz.model.login.LoginResponse;
import com.restaurant.arrifqiaziz.model.login.User;

import static com.restaurant.arrifqiaziz.data.Constant.GET_PROFILE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_user_id)
    TextView txtUserId;

    Session session;

    User user = new User();

    public ProfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, v);

        unbinder = ButterKnife.bind(this, v);

        session = new Session(getActivity());

        if (session.isLoggedIn()) {
            initView();
        }

        return v;
    }

    private void initView() {
        AndroidNetworking.get(GET_PROFILE + "/" + session.getUser().getUserid())
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof LoginResponse) {
                            if (((LoginResponse) response).getStatus().equalsIgnoreCase("success")) {
                                user = (((LoginResponse) response).getUser());

                                txtNama.setText(user.getNama());
                                txtUserId.setText(user.getUserid());
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("anError", anError.getMessage());
                        Toast.makeText(getActivity(), "Mohon maaf, kesalahan teknis !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_edit_profil, R.id.btn_logout})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btn_edit_profil:
                i = new Intent(getActivity(), UbahProfilActivity.class);
                i.putExtra("profil", user);
                startActivity(i);
                break;
            case R.id.btn_logout:
                session.logoutUser();
                break;
        }
    }
}
