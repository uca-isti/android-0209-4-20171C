package uca.apps.isi.Auapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import uca.apps.isi.Auapp.R;
import uca.apps.isi.Auapp.models.Asignatura;

import static uca.apps.isi.Auapp.R.id.v;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsignaturaFragment extends Fragment  {
    private EditText editTextAsignatura;
    private  Button buttonGuardar;




    public AsignaturaFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_asignatura, container, false);
        editTextAsignatura=(EditText) view.findViewById(R.id.editTextAsignatura);
        buttonGuardar=(Button) view.findViewById(R.id.buttonGuardar);




        return view;

    }

  @Override
  public void onCreate(Bundle savedInstanceState) {



    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);





  }

public void save(){
    buttonGuardar.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            guardarAsignatura();
        }
    });

}

  public boolean validate(){
      if(editTextAsignatura.getText().toString()==null){
          Toast toast =
                  Toast.makeText(getContext(),"Digita algo porfavor", Toast.LENGTH_SHORT);

          toast.show();
          return false;
      }


      else{
          return true;

      }
  }













  public void guardarAsignatura() {
      if(validate())
      {
          Realm.init(getContext());
          Realm realm = Realm.getDefaultInstance();

          realm.beginTransaction();
          //
          Asignatura asignatura =realm.createObject(Asignatura.class);
          asignatura.setAsignatura(editTextAsignatura.getText().toString());
        //  asignatura.setId_asignatura(asig.getId_asignatura());
          //asignatura.setPrecio(asig.getPrecio());
          //
          realm.commitTransaction();


      }




  }


}


