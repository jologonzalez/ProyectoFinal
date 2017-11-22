package com.example.jorgelopez.proyectofinal.Vistas.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jorgelopez.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MsjRecFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MsjRecFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MsjRecFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MsjRecFragment() {
        // Required empty public constructor
    }

    public static MsjRecFragment newInstance() {
        MsjRecFragment fragment = new MsjRecFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_msj_rec, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
    }
}
