package com.restaurant.arrifqiaziz.activity.profil;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.data.Session;
import com.restaurant.arrifqiaziz.model.login.User;
import com.restaurant.arrifqiaziz.model.register.RegisterResponse;
import com.restaurant.arrifqiaziz.util.CommonUtil;
import com.restaurant.arrifqiaziz.util.DialogUtils;
import static com.restaurant.arrifqiaziz.data.Constant.UPDATE_PROFILE;

public class UbahProfilActivity extends AppCompatActivity {

    @BindView(R.id.et_nama_lengkap)
    TextInputEditText etNamaLengkap;
    @BindView(R.id.et_user_id)
    TextInputEditText etUserId;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.et_password_new)
    TextInputEditText etPasswordNew;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;

    User user;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
        ButterKnife.bind(this);

        session = new Session(this);

        user = (User) getIntent().getSerializableExtra("profil");

        initView();
    }

    private void initView() {
        etNamaLengkap.setText(user.getNama());
        etUserId.setText(user.getUserid());
    }

    @OnClick({R.id.btn_back, R.id.btn_simpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_simpan:
                cekValidasi();
                break;
        }
    }

    private void cekValidasi() {
        ArrayList<View> list = new ArrayList<>();
        list.add(etNamaLengkap);
        list.add(etPassword);
        list.add(etPasswordNew);
        if (CommonUtil.validateEmptyEntries(list)) {
            editProfil();
        } else {
            Toast.makeText(UbahProfilActivity.this, "Pastikan data yang diminta sudah terisi.", Toast.LENGTH_SHORT).show();
        }
    }

    private void editProfil() {
        ANRequest.PutRequestBuilder builder = new ANRequest.PutRequestBuilder(UPDATE_PROFILE + "/" + user.getUserid());
        builder.addHeaders("X-Requested-With", "XMLHttpRequest")
                .addBodyParameter("name", etNamaLengkap.getText().toString())
                .addBodyParameter("old_password", etPassword.getText().toString())
                .addBodyParameter("new_password", etPasswordNew.getText().toString());
        builder
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof RegisterResponse) {
                            RegisterResponse response1 = (RegisterResponse) response;
                            DialogUtils.closeDialog();
                            if (response1.getStatus() != null) {
                                Toast.makeText(UbahProfilActivity.this, "Profil berhasil disimpan..", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UbahProfilActivity.this, "Profil gagal disimpan !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        DialogUtils.closeDialog();
                        Toast.makeText(UbahProfilActivity.this, "Mohon maaf, kesalahan teknis !", Toast.LENGTH_SHORT).show();
                    }

                });
    }
}

