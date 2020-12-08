package com.example.appbanhangonline.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.appbanhangonline.MainActivity;
import com.example.appbanhangonline.model.UserModel;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitUser;
import com.example.appbanhangonline.service.RetrofitContact;

import java.security.MessageDigest;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText passwrod;

    private ProgressBar progressBar;

    private ImageButton closeBtn;
    private Button signInBtn;

    private TextView forgotPassword;
    private ImageView img_rigister;


    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";

    public static boolean disableCloseBtn = false;

    public static boolean onResetPasswordFragment = false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        dontHaveAnAccount = view.findViewById(R.id.register_show);
        parentFrameLayout = getActivity().findViewById(R.id.login_framelayout);

        email = view.findViewById(R.id.edtEmail);
        passwrod = view.findViewById(R.id.edtPassword);

        forgotPassword = view.findViewById(R.id.tv_forgot_password);

        progressBar = view.findViewById(R.id.sign_in_progressbar);

        //closeBtn = view.findViewById(R.id.sign_up_close_btn);
        signInBtn = view.findViewById(R.id.cirLoginButton);
        img_rigister = view.findViewById(R.id.image_register);

        if (disableCloseBtn) {
            //closeBtn.setVisibility(View.GONE);
        } else {
            //closeBtn.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new com.example.appbanhangonline.login.RegisterFragment());
            }


        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  onResetPasswordFragment = true;
                  setFragment(new ForgotPasswordFragment());
            }
        });
        img_rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new com.example.appbanhangonline.login.RegisterFragment());
            }
        });
//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainIntent();
//            }
//        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwrod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmailAndPassword()){
                    String email_input = email.getText().toString();
                    String password_input = passwrod.getText().toString();
                    GetDataRetrofitUser service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitUser.class);
                    Call<List<UserModel>> call = service.login(email_input,md5(password_input));
                    call.enqueue(new Callback<List<UserModel>>() {
                        @Override
                        public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                            if (response.isSuccessful()){
                                mainIntent();
                                Toast.makeText(getContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(),"That bai",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<UserModel>> call, Throwable t) {

                        }
                    });
                }


            }
        });

    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(passwrod.getText())) {
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(0, 0,0));
            } else {
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private boolean checkEmailAndPassword() {
        if (email.getText().toString().matches(emailPattern)) {
            if (passwrod.length() >= 8) {

                progressBar.setVisibility(View.VISIBLE);
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
                return true;
            } else {
                Toast.makeText(getActivity(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                signInBtn.setEnabled(true);
                return false;
            }
        }else {
            signInBtn.setEnabled(false);
            Toast.makeText(getActivity(), "Email", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void mainIntent(){
        if (disableCloseBtn) {
            disableCloseBtn = false;
        } else {
            Intent mainIntentIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntentIntent);
        }
        getActivity().finish();
    }
    public void mainInte(View view){

    }

    public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
