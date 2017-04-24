package uca.apps.isi.Auapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import uca.apps.isi.Auapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsignaturaFragment extends Fragment {


    public AsignaturaFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asignatura, container, false);
    }

  @Override
  public void onCreate(Bundle savedInstanceState) {


    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);

  }




}


