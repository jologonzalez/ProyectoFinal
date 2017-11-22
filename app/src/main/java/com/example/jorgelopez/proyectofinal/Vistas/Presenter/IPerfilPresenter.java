package com.example.jorgelopez.proyectofinal.Vistas.Presenter;

import com.example.jorgelopez.proyectofinal.Modelo.Usuario;

/**
 * Created by Jorge Lopez on 21/11/2017.
 */

public interface IPerfilPresenter {

    void guardarDatos(String nombre, int edad, long celular);

    void cargarDatos(String nombre, int edad, long celular);

}
