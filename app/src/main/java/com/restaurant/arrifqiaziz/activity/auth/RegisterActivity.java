package com.restaurant.arrifqiaziz.activity.auth;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.model.register.RegisterResponse;
import com.restaurant.arrifqiaziz.util.CommonUtil;
import com.restaurant.arrifqiaziz.util.DialogUtils;

import static com.restaurant.arrifqiaziz.data.Constant.REGISTER;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_nama_lengkap)
    TextInputEditText etNamaLengkap;
    @BindView(R.id.et_user_id)
    TextInputEditText etUserId;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_daftar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_daftar:
                checkValidasi();
                break;
        }
    }

    private void checkValidasi() {
        ArrayList<View> list = new ArrayList<>();
        list.add(etNamaLengkap);
        list.add(etUserId);
        list.add(etPassword);
        if (CommonUtil.validateEmptyEntries(list)) {
            register();
        }
    }

    private void register() {
        DialogUtils.openDialog(this);
        AndroidNetworking.post(REGISTER)
                .addHeaders("X-Requested-With", "XMLHttpRequest")
                .addBodyParameter("userid", etUserId.getText().toString())
                .addBodyParameter("nama", etNamaLengkap.getText().toString())
                .addBodyParameter("password", etPassword.getText().toString())
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof RegisterResponse) {
                            RegisterResponse response1 = (RegisterResponse) response;
                            DialogUtils.closeDialog();
                            if (response1.getStatus().equalsIgnoreCase("success")) {
                                Toast.makeText(RegisterActivity.this, "Selamat, registrasi berhasil..", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Mohon maaf, user id sudah digunakan !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        DialogUtils.closeDialog();
                        if (anError.getErrorCode() == 422) {
                            Toast.makeText(RegisterActivity.this, "Mohon maaf, user id sudah digunakan !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Mohon maaf, kesalahan teknis !", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}