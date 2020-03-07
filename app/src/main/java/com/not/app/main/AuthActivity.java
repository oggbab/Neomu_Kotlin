package com.not.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.neomu.neomu.R;
import com.not.app.club.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        googleDefaultConfig();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                //구글 로그인 성공해서 파베에 인증
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else{
                //구글 로그인 실패
            }
        }
    }

    private void googleDefaultConfig() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        showToast("인증 실패",false);
                    }else{
                        Intent intent = new Intent(AuthActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @OnClick({R.id.btn_auth_join,R.id.btn_auth_google})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_auth_join:
                Intent intent = new Intent(AuthActivity.this,JoinActivity.class);
                startActivity(intent);
            case R.id.btn_auth_google:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);
        }
    }

}