package com.example.appbanhangonline.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



import com.bumptech.glide.request.RequestOptions;
import com.example.appbanhangonline.MainActivity;
import com.example.appbanhangonline.activity.Prefconfig;
import com.example.appbanhangonline.model.UserModel;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitUser;
import com.example.appbanhangonline.service.RetrofitContact;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
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

    public static final String USER = "user";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String FULLNAME = "fullname";
    public static final String ADDRESS = "address";

    private SharedPreferences sharedPreferences;
    private UserModel userModel;
    private LoginButton facebook_btn;
    private CallbackManager callbackManager;
    private SignInButton signInButton;
    private GoogleSignInClient googleApiClient;
    private static final int SIGN_IN = 1;
    private static final String TAG = "facebook log";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        dontHaveAnAccount = view.findViewById(R.id.register_show);
        parentFrameLayout = getActivity().findViewById(R.id.login_framelayout);


        email = view.findViewById(R.id.edtEmail);
        passwrod = view.findViewById(R.id.edtPassword);
       // facebook_btn = view.findViewById(R.id.login_facebook);
        //facebook_btn.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();


        ///
        sharedPreferences = getActivity().getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        String idsss = sharedPreferences.getString(LoginFragment.ID,"");
//        if (idsss.trim() != null){
//            mainIntent();
//        }

        //
//        signInButton = view.findViewById(R.id.login_google);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();

//        googleApiClient = new GoogleApiClient.Builder(getContext())
//                .addApi(Auth.GOOGLE_SIGN_IN_API)
//                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) getContext())
//                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) getContext())
//                .build();
//        googleApiClient = GoogleSignIn.getClient(getContext(),gso);
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = googleApiClient.getSignInIntent();
//                startActivityForResult(intent,SIGN_IN);
//            }
//        });


//        facebook_btn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "======Facebook login success======");
//                Log.d(TAG, "Facebook Access Token: " + loginResult.getAccessToken().getToken());
//                Toast.makeText(getActivity(), "Login Facebook success.", Toast.LENGTH_SHORT).show();
//
//                getFbInfo();
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(getActivity(), "Login Facebook cancelled.", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.e(TAG, "======Facebook login error======");
//                Log.e(TAG, "Error: " + error.toString());
//                Toast.makeText(getActivity(), "Login Facebook error.", Toast.LENGTH_SHORT).show();
//            }
//        });

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
        userModel = new UserModel();
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


        sharedPreferences = getContext().getSharedPreferences(USER, Context.MODE_PRIVATE);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();


                if (checkEmailAndPassword()){
                    String email_input = email.getText().toString();
                    String password_input = passwrod.getText().toString();
                    GetDataRetrofitUser service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitUser.class);
                    Call<UserModel> call = service.login(email_input,password_input);
                    call.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            if (response.isSuccessful() && response.body() != null){
                                userModel = response.body();

                               if (response.body() != null){
                                   editor.putString(ID,String.valueOf(userModel.getId()));
                                   editor.putString(FULLNAME,String.valueOf(userModel.getFullname()));
                                   editor.putString(EMAIL,String.valueOf(userModel.getEmail()));
                                   editor.putString(ADDRESS,String.valueOf(userModel.getDiaChi()));
                                   editor.putString("dienthoai",String.valueOf(userModel.getPhone()));
                                   editor.commit();
                                   mainIntent();
                               }


                                Toast.makeText(getContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(),"Sai mật khẩu hoặc tài khoản",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            Toast.makeText(getContext(),"That bai",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Sai tài khoản với mật khẩu", Toast.LENGTH_SHORT).show();
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

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("a", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//
//
//    }
//    private void getFbInfo() {
//        if (AccessToken.getCurrentAccessToken() != null) {
//            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(final JSONObject me, GraphResponse response) {
//                            if (me != null) {
//                                Log.i("Login: ", me.optString("name"));
//                                Log.i("ID: ", me.optString("id"));
//
//                                Toast.makeText(getActivity(), "Name: " + me.optString("name"), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getActivity(), "ID: " + me.optString("id"), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//            Bundle parameters = new Bundle();
//            parameters.putString("fields", "id,name,link");
//            request.setParameters(parameters);
//            request.executeAsync();
//        }
//    }
//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//              if (accessTokenTracker == null){
//
//              }else {
//                  loadProfile(currentAccessToken);
//              }
//        }
//    };
//    private void loadProfile(AccessToken accessToken){
//        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try {
//                    String emails = object.getString("email");
//                    String last_name = object.getString("last_name");
//                    String first_name = object.getString("first_name");
//                    email.setText(emails);
//
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        Bundle bundle = new Bundle();
//        bundle.putString("email,first_name","last_name");
//        request.setParameters(bundle);
//        request.executeAsync();
//    }




}
