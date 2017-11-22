package com.example.jorgelopez.proyectofinal.Vistas.Actividades;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.jorgelopez.proyectofinal.R;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.MsjEnvFragment;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.MsjRecFragment;
import com.example.jorgelopez.proyectofinal.Vistas.Fragmentos.PerfilFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrincipalTabActivity extends AppCompatActivity implements PerfilFragment.OnPerfilFragmentInteractionListener, MsjEnvFragment.OnFragmentInteractionListener, MsjRecFragment.OnFragmentInteractionListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_tab);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_op, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cerrarSesion) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance() {
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_principal_tab, container, false);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {switch (position) {
            case 0:
                PerfilFragment perfilFragment = new PerfilFragment();
                return perfilFragment;
            case 1:
                MsjEnvFragment msjEnvFragment = new MsjEnvFragment();
                return msjEnvFragment;
            case 2:
                MsjRecFragment msjRecFragment = new MsjRecFragment();
                return msjRecFragment;
        }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "perfil";
                case 1:
                    return "mensajes enviados";
                case 2:
                    return "mensajes recibidos";
            }
            return null;
        }
    }
}
