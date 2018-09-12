package com.localfridge.localfridge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentOptions extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_options, container, false);
        Button btnLogin = v.findViewById(R.id.btnLogin);
        Button btnReg   = v.findViewById(R.id.btnReg);

        btnLogin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new FragmentLogin();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_main, fragment);
                ft.commit();
            }
        });

        btnReg.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new FragmentRegistration();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_main, fragment);
                ft.commit();
            }
        });
        return v;
    }

}
