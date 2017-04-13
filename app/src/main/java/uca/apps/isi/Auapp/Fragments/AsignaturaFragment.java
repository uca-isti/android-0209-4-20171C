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
public class AsignaturaFragment extends ListFragment {


    public AsignaturaFragment() {
        // Required empty public constructor
    }

  // Array de String que contiene nuestros queridos Sistemas Operativos
  private String[] asignaturas = { "Programación de bases de datos", "Matemática discreta ", "Laboratorio de programacion 1 ", "Implementación de software",
    "Análisis de bases de datos", "Género y equidad", "Taller de lectura", "Taller de redacción",
    "Arquitectura de software", "Desarrollo de aplicaciones web", "Matemática fundamental","Laboratorio de programación 2", "Hardware y software"};


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

    // Establecemos el Adapter a la Lista del Fragment
    setListAdapter(new ArrayAdapter<String>(getActivity(),
      android.R.layout.simple_list_item_1, asignaturas));
  }


  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    // TODO Auto-generated method stub
    super.onListItemClick(l, v, position, id);

    // Mostramos un mensaje con el elemento pulsado
    Toast.makeText(getActivity(), "Ha pulsado " + asignaturas[position],
      Toast.LENGTH_SHORT).show();
  }

}


