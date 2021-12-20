package co.edu.usa.reto5.vista.ui.favoritos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.edu.usa.reto5.R;
import co.edu.usa.reto5.modelo.Adaptador;
import co.edu.usa.reto5.modelo.Entidad;
import co.edu.usa.reto5.modelo.MotorBaseDatosSQLite;

public class FavoritosFragment extends Fragment {

    View v;
    String tag = "FavoritosFragment";
    MotorBaseDatosSQLite conectar = new MotorBaseDatosSQLite(getContext(), "favoritos", null, 1);
    public FavoritosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favoritos,container,false);

        ListView listView = (ListView) v.findViewById(R.id.lista_favoritos);
        Adaptador adaptador = new Adaptador(getTablaFavoritos(), v.getContext());


        listView.setAdapter(adaptador);

        return v;
    }

    private ArrayList<Entidad> getTablaFavoritos(){
        ArrayList<Entidad> listaFavoritos = new ArrayList<>();
        conectar = new MotorBaseDatosSQLite(getContext(),"TiendaProductos", null, 1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        //conectar.onUpgrade(db_leer,1,2);
        Cursor cursor = db_leer.rawQuery("SELECT * FROM favoritos", null);

        while(cursor.moveToNext()){
            Log.v(tag, "dentro de while");
            listaFavoritos.add(new Entidad(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            Log.v(tag, "despues del while");
        }


        return listaFavoritos;
    }

}
