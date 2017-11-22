package com.example.jorgelopez.proyectofinal.Vistas.Presenter;

import com.example.jorgelopez.proyectofinal.Dominio.CallBackInteractor;
import com.example.jorgelopez.proyectofinal.Dominio.ILUsuario;
import com.example.jorgelopez.proyectofinal.Dominio.LUsuario;
import com.example.jorgelopez.proyectofinal.Modelo.Usuario;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.IPerfilFragmentView;

/**
 * Created by Jorge Lopez on 21/11/2017.
 */

public class PerfilPresenter implements IPerfilPresenter{

    private IPerfilFragmentView view;
    private ILUsuario lUsuario;

    public PerfilPresenter(IPerfilFragmentView view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void guardarDatos(String nombre, int edad, long celular) {

        view.deshabilitarVistas();

        try {

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setCelular(celular);

            lUsuario.guardarDatos(nombre, edad, celular, new CallBackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarVistas();
                }

                @Override
                public void error(String error) {
                    view.mostrarError(error);
                }
            });
        }catch (Exception e){
            view.mostrarError(e.getMessage());
        }
    }

    @Override
    public void cargarDatos(String nombre, int edad, long celular) {

        try{
            lUsuario.cargarDatos(nombre, edad, celular,new CallBackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarVistas();
                }

                @Override
                public void error(String error) {
                    view.mostrarError(error);
                }
            });
        }catch (Exception e){
            view.mostrarError(e.getMessage());
        }

    }
}
