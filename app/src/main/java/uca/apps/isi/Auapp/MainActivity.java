package uca.apps.isi.Auapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.Auapp.Adapters.AsignaturaAdapter;
import uca.apps.isi.Auapp.Fragments.AdministrarCuentaFragment;
import uca.apps.isi.Auapp.Fragments.AsignaturaFragment;
import uca.apps.isi.Auapp.Fragments.ConfigFragment;
import uca.apps.isi.Auapp.Fragments.CuentaFragment;
import uca.apps.isi.Auapp.Fragments.HomeFragment;
import uca.apps.isi.Auapp.UI.Asignaturas;
import uca.apps.isi.Auapp.api.Api;
import uca.apps.isi.Auapp.api.ApiInterface;
import uca.apps.isi.Auapp.models.Asignatura;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = "MainActivity";
    ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass=null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_config) {
            fragmentClass= ConfigFragment.class;

        } else if (id == R.id.nav_asignatura) {
            fragmentClass= AsignaturaFragment.class;
        } else if (id == R.id.nav_ecuenta) {
            fragmentClass= CuentaFragment.class;
        } else if (id == R.id.nav_home) {
            fragmentClass= HomeFragment.class;
        }else if (id == R.id.nav_administrarCuenta) {
          fragmentClass= AdministrarCuentaFragment.class;
        }




        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
private void eliminarTodo(){
    Realm.init(getApplicationContext());
    Realm realm = Realm.getDefaultInstance();
    RealmResults<Asignatura> results = realm.where(Asignatura.class).findAll();

// All changes to data must happen in a transaction
    realm.beginTransaction();

// Delete all matches
    results.deleteAllFromRealm();

    realm.commitTransaction();
    Log.i(TAG,"Se supone que elimino todo");
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

    private void sincronizar(){
        //limpiar primero
        this.eliminarTodo();
        for(Asignatura a:this.asignaturas){
            Log.i(TAG,"entra al for del sincronizar");
            this.guardar(a);

        }
        mostrarDatosDB();

    }

    private void mostrarDatosDB(){
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

       // realm.beginTransaction();
        final RealmResults<Asignatura> asignaturass = realm
                .where(Asignatura.class).findAll();

        for(Asignatura a:asignaturass){

                Log.i(TAG, "registro de la bd "+a.getAsignatura());




        }
      //  realm.commitTransaction();
       // mAdapter = new TweetsAdapter(tweetModels, mainActivity);
      //  mRecyclerView.setAdapter(mAdapter);
    }

    private void mostrarDatosArreglo(){

        for(Asignatura as:asignaturas){
            Log.i(TAG, "RECORRIO AQUI");
            Log.i(TAG,"asignatura "+as.getAsignatura());
            notificar("ASIGNATURA"+ as.getAsignatura());
        }


    }

    private void notificar(String mensaje)
    {
        Toast toast1 =
                Toast.makeText(this,
                        mensaje, Toast.LENGTH_SHORT);

        toast1.show();
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
        mRecyclerView.setAdapter(mAdapter);
    }
}
