package co.edu.usa.reto5.vista.ui.productos;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.edu.usa.reto5.R;
import co.edu.usa.reto5.modelo.Adaptador;
import co.edu.usa.reto5.modelo.Entidad;
import co.edu.usa.reto5.modelo.MotorBaseDatosSQLite;

public class ProductosFragment extends Fragment {
    ArrayList<Entidad> listaProductos = new ArrayList<>();
    ListView listaProductosView;
    Adaptador adaptador;
    View v;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_gallery,container,false);


        listaProductosView = (ListView) v.findViewById(R.id.lista_productos);
        adaptador = new Adaptador(getTablaProductos(), getContext());

        listaProductosView.setAdapter(adaptador);
        Log.v("Fragment Productos", "nombre : "+ adaptador.toString());
        

        return v;
    }

    private ArrayList<Entidad> getTablaProductos(){


        String url = "https://gb1e0cf6c74dee9-ciclo4.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/productos/productos";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int imagen = jsonObject.getInt("imagen");
                        String nombre = jsonObject.getString("nombre");
                        String descripcion = jsonObject.getString("descripcion");

                        listaProductos.add(new Entidad(imagen, nombre, descripcion));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //***********************************************************
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        /* ================================================================================================== */



        return listaProductos;
    }



}