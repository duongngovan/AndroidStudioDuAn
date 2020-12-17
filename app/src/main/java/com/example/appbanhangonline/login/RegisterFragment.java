package com.example.appbanhangonline.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.appbanhangonline.MainActivity;
import com.example.appbanhangonline.model.UserModel;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitUser;
import com.example.appbanhangonline.service.RetrofitContact;

import java.security.MessageDigest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
    }
    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;
    private EditText phone;
    private EditText address;

    private ImageButton closeBtn;
    private Button signUpBtn;
    private ProgressBar progressBar;
    private ImageView img_back;


    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";

    public static boolean disableCloseBtn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.login_framelayout);

        alreadyHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account);

        email = view.findViewById(R.id.editTextEmail);
        fullName = view.findViewById(R.id.editTextName);
        phone = view.findViewById(R.id.editTextMobile);
        password = view.findViewById(R.id.editTextPassword);
        confirmPassword = view.findViewById(R.id.editTextPasswords);
        progressBar = view.findViewById(R.id.sign_up_progressbar);
        signUpBtn = view.findViewById(R.id.cirRegisterButton);
        address = view.findViewById(R.id.editTextAddress);
        //closeBtn = view.findViewById(R.id.sign_up_close_btn);
        img_back = view.findViewById(R.id.image_register_back);




        if (disableCloseBtn) {
            //closeBtn.setVisibility(View.GONE);
        } else {
            // closeBtn.setVisibility(View.VISIBLE);
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new LoginFragment());
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

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailAndPassword();

                }



        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 setFragment(new LoginFragment());
            }
        });
    }

    private void  checkEmailAndPassword() {
        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.custom_error_icon);
        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());
        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {

                progressBar.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
                String email_input = email.getText().toString();
                String password_input = password.getText().toString();
                String fullname_input = fullName.getText().toString();
                String phone_input = phone.getText().toString();
                String addresss = address.getText().toString();
                UserModel user = new UserModel(email_input,fullname_input,password_input,phone_input,addresss);
                GetDataRetrofitUser service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitUser.class);
                Call<UserModel> call = service.add(user);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()){
                            UserModel user1 = response.body();
                            //mainIntent();
                            Toast.makeText(getContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),"Email ton tai",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(getContext(),"That bai",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

            } else {
                confirmPassword.setError("Password doesn't matched!", customErrorIcon);

            }
        } else {
            email.setError("Invalid Email", customErrorIcon);

        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullName.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (!TextUtils.isEmpty(confirmPassword.getText())) {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(0, 0,0));
                    } else {
                        signUpBtn.setEnabled(false);
                        //signUpBtn.setTextColor(Color.argb(50f, 255, 255, 255));
                    }
                } else {
                    signUpBtn.setEnabled(false);

                    //signUpBtn.setTextColor(Color.argb(50f, 255, 255, 255));
                }
            } else {
                signUpBtn.setEnabled(false);
                //signUpBtn.setTextColor(Color.argb(50f, 255, 255, 255));
            }
        } else {
            signUpBtn.setEnabled(false);
            //signUpBtn.setTextColor(Color.argb(50f, 255, 255, 255));
            signUpBtn.setTextColor(Color.rgb(255, 0, 0));
        }
    }

    private void mainIntent() {
        if (disableCloseBtn) {
            disableCloseBtn = false;
        } else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);
        }
        getActivity().finish();
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
