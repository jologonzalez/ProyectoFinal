package com.example.jorgelopez.proyectofinal.Dominio;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.jorgelopez.proyectofinal.Modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Jorge Lopez on 24/10/2017.
 */

public class LUsuario implements ILUsuario{

    private FirebaseAuth Auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public LUsuario() {
        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usuarios");
    }

    @Override
    public void crearUsuario(final Usuario usuario, String password, final CallBackInteractor<String> callBackInteractor) {

        Auth.createUserWithEmailAndPassword(usuario.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    usuario.setUid(task.getResult().getUser().getUid());
                    reference.child(usuario.getUid()).setValue(usuario);
                    callBackInteractor.success(usuario.getNombre());
                }else {
                    callBackInteractor.error(task.getException().getMessage());
                }
            }
        });

    }

    @Override
    public void authUsuario(String email, String password, final CallBackInteractor<String> callBackInteractor) {

        Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    reference.child(task.getResult().getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            callBackInteractor.success(usuario.getNombre());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            callBackInteractor.error(databaseError.getMessage());
                        }
                    });
                }else {
                    callBackInteractor.error(task.getException().getMessage());
                }
            }
        });

    }

    @Override
    public void cargarDatos(final String nombre, int edad, long celular, final CallBackInteractor<String> callBackInteractor) {

        String Uid = Auth.getCurrentUser().getUid();
/*
        reference.child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                nombre = usuario.getNombre();
                edad = usuario.getEdad();
                celular = usuario.getCelular();
                callBackInteractor.success("Exitoso");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
    }

    @Override
    public void guardarDatos(String nombre, int edad, long celular, final CallBackInteractor<String> callBackInteractor) {

        String Uid = Auth.getCurrentUser().getUid();

        try {
            reference.child(Uid).child("nombre").setValue(nombre).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        callBackInteractor.success("Exitoso");
                    } else {
                        callBackInteractor.error(task.getException().getMessage());
                    }
                }
            });
            reference.child(Uid).child("edad").setValue(edad).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        callBackInteractor.success("Exitoso");
                    } else {
                        callBackInteractor.error(task.getException().getMessage());
                    }
                }
            });
            reference.child(Uid).child("celular").setValue(celular).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        callBackInteractor.success("Exitoso");
                    } else {
                        callBackInteractor.error(task.getException().getMessage());
                    }
                }
            });
        }catch (Exception e){
            callBackInteractor.error(e.getMessage());
        }
    }


}
