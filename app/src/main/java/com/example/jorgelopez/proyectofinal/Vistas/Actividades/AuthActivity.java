package com.example.jorgelopez.proyectofinal.Vistas.Actividades;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jorgelopez.proyectofinal.R;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.LoginFragment;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.RegistroFragment;

public class AuthActivity extends AppCompatActivity implements LoginFragment.OnLoginFramentInteraction, RegistroFragment.OnRegistroInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initFragment();
    }

    private void initFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();

    }

    @Override
    public void finalizarLogin() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void irARegistro() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, RegistroFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void irALogin() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void finalizarRegistro() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

}