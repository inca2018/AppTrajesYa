package inca.jesus.trajesya.Sesion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import inca.jesus.trajesya.R;

public class SesionGoogleLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    ProgressDialog progressDialog;
    private GoogleApiClient googleApliClient;
    public static final int SIGN_IN_CODE=777;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_google_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Iniciando Sesión:");
        progressDialog.setMessage("Iniciando.......");
        progressDialog.show();

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApliClient = new GoogleApiClient.Builder(this)
                //.enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApliClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);

        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });

        }
    }
    private void inicieSesion() {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApliClient);
        startActivityForResult(intent,SIGN_IN_CODE);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_CODE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
           /* Sesion.USUARIO.setId(account.getId());
            Sesion.USUARIO.setFoto(account.getPhotoUrl());
            Sesion.USUARIO.setCorreo(account.getEmail());
            Sesion.USUARIO.setNombre(account.getDisplayName());
            System.out.println("INKA: USUARIO: Bienvenido "+account.getDisplayName());*/
            progressDialog.dismiss();
            goMainScreen();
        }else{
            inicieSesion();
        }
    }

    private void goMainScreen() {
       /* Intent intent=new Intent(this,Activity_Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }
}
