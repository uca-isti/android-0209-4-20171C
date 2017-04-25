package uca.apps.isi.Auapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.Auapp.Adapters.AsignaturaAdapter;
import uca.apps.isi.Auapp.api.Api;
import uca.apps.isi.Auapp.models.Asignatura;

public class MainActivity2 extends AppCompatActivity {
    private final static String TAG = "MainActivity2";
    private EditText editTextAsignatura;
    private Button buttonGuardar;
    ListView lv;

    ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
    ArrayList<String> arreglo=new ArrayList<String>();
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static MainActivity2 mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextAsignatura=(EditText) findViewById(R.id.editTextAsignatura);
        buttonGuardar=(Button) findViewById(R.id.buttonGuardar);
       //mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
       // mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        lv = (ListView) findViewById(R.id.listAsignaturas);




        //Peticion al api
        Call<List<Asignatura>> call = Api.instance().getAsignaturas();
        call.enqueue(new Callback<List<Asignatura>>() {
            @Override
            public void onResponse(Call<List<Asignatura>> call, Response<List<Asignatura>> response) {


                if(response != null) {

                    for(Asignatura asignatura : response.body()) {

                        Log.i(TAG, "Asignatura "+ asignatura.getAsignatura());
                        asignaturas.add(asignatura);


                    }
                    mostrarDatosArreglo();
                    sincronizar();

                } else {
                    Log.i(TAG, "Response es nulo");
                }

            }

            @Override
            public void onFailure(Call<List<Asignatura>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
        //****


    }



  /*  public void save(){
        buttonGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast =
                        Toast.makeText(getApplicationContext(),"Llegoo", Toast.LENGTH_SHORT);

                toast.show();
               // guardarAsignatura();
            }
        });

    }*/

    public boolean validate(){
        if(editTextAsignatura.getText().toString().isEmpty()){
            Toast toast =
                    Toast.makeText(getApplicationContext(),"Digita algo porfavor", Toast.LENGTH_SHORT);

            toast.show();
            return false;
        }


        else{
            return true;

        }
    }











    private void mostrarDatosDB(){

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        // realm.beginTransaction();
        final RealmResults<Asignatura> asignaturass = realm
                .where(Asignatura.class).findAll();

        for(Asignatura a:asignaturass){

            Log.i(TAG, "registro de la bd "+a.getAsignatura());
            arreglo.add(a.getAsignatura());




        }


        }

    public void guardarAsignatura(View view) {


        if(validate())
        {
            Realm.init(getApplicationContext());
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            //
            Asignatura asignatura =realm.createObject(Asignatura.class);
            asignatura.setAsignatura(editTextAsignatura.getText().toString());
            //  asignatura.setId_asignatura(asig.getId_asignatura());
            //asignatura.setPrecio(asig.getPrecio());
            //
            realm.commitTransaction();
            Toast toast =
                    Toast.makeText(getApplicationContext(),"Asignatura guardada", Toast.LENGTH_SHORT);

            toast.show();
            arreglo.add(asignatura.getAsignatura());
            listar();


        }




    }

    private void sincronizar(){
        //limpiar primero

        for(Asignatura a:this.asignaturas){
            Log.i(TAG,"entra al for del sincronizar");
            this.guardar(a);

        }
        mostrarDatosDB();

    }

    private void mostrarDatosArreglo(){

        for(Asignatura as:asignaturas){
            Log.i(TAG, "RECORRIO AQUI");
            Log.i(TAG,"asignatura "+as.getAsignatura());
           // notificar("ASIGNATURA"+ as.getAsignatura());
        }


    }


    private void guardar(Asignatura asig){
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //
        Asignatura asignatura =realm.createObject(Asignatura.class);
        asignatura.setAsignatura(asig.getAsignatura());
        asignatura.setId_asignatura(asig.getId_asignatura());
        asignatura.setPrecio(asig.getPrecio());
        //
        realm.commitTransaction();
        Log.i(TAG,"se supone que guardo");


    }

    public static void loadData() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Asignatura> asignaturasss = realm
                .where(Asignatura.class)
                .findAll();

        // Log.i("TAG", "" + Asignaturas.size());

        // specify an adapter (see also next example)
        mAdapter = new AsignaturaAdapter(asignaturasss, mainActivity);




    }

    public void listar(View view){





        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.arreglo );

        lv.setAdapter(arrayAdapter);
        //loadData();

    }

    public void listar(){





        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.arreglo );

        lv.setAdapter(arrayAdapter);
        //loadData();

    }

}
