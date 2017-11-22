package com.example.jorgelopez.proyectofinal.Vistas.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jorgelopez.proyectofinal.Modelo.Usuario;
import com.example.jorgelopez.proyectofinal.R;
import com.example.jorgelopez.proyectofinal.Vistas.Presenter.IPerfilPresenter;
import com.example.jorgelopez.proyectofinal.Vistas.Presenter.PerfilPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerfilFragment extends Fragment implements IPerfilFragmentView{

    @BindView(R.id.txtNombrePerfil)
    EditText txtNombrePerfil;

    @BindView(R.id.txtEdad)
    EditText txtEdad;

    @BindView(R.id.txtCel)
    EditText txtCel;

    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    private IPerfilPresenter perfilPresenter;

    private OnPerfilFragmentInteractionListener mListener;

    private FirebaseAuth Auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ButterKnife.bind(this, view);

        perfilPresenter = new PerfilPresenter(this);

        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usuarios");

        cargarDatos();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPerfilFragmentInteractionListener) {
            mListener = (OnPerfilFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPerfilFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void habilitarVistas() {
        txtNombrePerfil.setEnabled(true);
        txtEdad.setEnabled(true);
        txtCel.setEnabled(true);
        btnGuardar.setEnabled(true);
    }

    @Override
    public void deshabilitarVistas() {
        txtNombrePerfil.setEnabled(false);
        txtEdad.setEnabled(false);
        txtCel.setEnabled(false);
        //btnGuardar.setEnabled(false);
    }

    @OnClick(R.id.btnGuardar)
    @Override
    public void guardarDatos() {
        String nombre = txtNombrePerfil.getText().toString();
        int edad = Integer.parseInt(txtEdad.getText().toString());
        long celular = Long.parseLong(txtCel.getText().toString());

        /*if(txtEdad.getText()==null||txtCel.getText()==null||txtNombrePerfil.getText()==null){
            Snackbar.make(getView(), "Llene todos los campos", Snackbar.LENGTH_SHORT).show();
        }*/

        perfilPresenter.guardarDatos(nombre, edad, celular);
    }

    @Override
    public void cargarDatos() {
        /*perfilPresenter.cargarDatos();

        txtNombrePerfil.setText(nombre);
        txtEdad.setText((String.valueOf(edad)));
        txtCel.setText((String.valueOf(celular)));
        */
        String Uid = Auth.getCurrentUser().getUid();

        reference.child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                txtNombrePerfil.setText(usuario.getNombre());
                txtEdad.setText((String.valueOf(usuario.getEdad())));
                txtCel.setText((String.valueOf(usuario.getCelular())));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void mostrarError(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_SHORT).show();
    }

    public interface OnPerfilFragmentInteractionListener {

    }
}
