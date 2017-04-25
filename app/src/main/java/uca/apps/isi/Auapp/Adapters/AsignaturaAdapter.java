package uca.apps.isi.Auapp.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import uca.apps.isi.Auapp.MainActivity;
import uca.apps.isi.Auapp.MainActivity2;
import uca.apps.isi.Auapp.R;
import uca.apps.isi.Auapp.models.Asignatura;

/**
 * Created by cvande on 24/4/2017.
 */

public class AsignaturaAdapter extends RecyclerView.Adapter<AsignaturaAdapter.ViewHolder> {

    private RealmResults<Asignatura> asignaturas;
    private MainActivity2 mainActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView asignatura;

        public LinearLayout item;
        public CardView card;
        public ImageView img;
        public ViewHolder(View view) {
            super(view);
            asignatura = (TextView) view.findViewById(R.id.asignatura);

            //item = (LinearLayout) view.findViewById(R.id.item);
           // img=(ImageView) view.findViewById(R.id.imagen);
           // card = (CardView) view.findViewById(R.id.card_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AsignaturaAdapter(RealmResults<Asignatura> asignaturaModels, MainActivity2 mainActivity) {
        this.asignaturas = asignaturaModels;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)

    public AsignaturaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_asignaturas, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)

    public void onBindViewHolder(ViewHolder holder, final int position) {

        Asignatura asignatura = this.asignaturas.get(position);


        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.user.setText(tweetModel.getUserModel().getName());
        holder.asignatura.setText(asignatura.getAsignatura());
        // if((Math.random()*2+1)>2)
        if(position % 2==0)
        {
            holder.img.setImageResource(R.drawable.ic_account_box_black_24dp);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)

    public int getItemCount() {
        return this.asignaturas.size();
    }

    private void deleteItem(int position) {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Asignatura tweetModel = this.asignaturas.get(position);
        tweetModel.deleteFromRealm();

        realm.commitTransaction();


        mainActivity.loadData();
    }


}
