package com.example.gwen.facebookgraph;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicacionAdapter extends  RecyclerView.Adapter<PublicacionAdapter.ViewHolder>{
    Context context;
    String cadena;
    JSONArray publicaciones=new JSONArray();
    public PublicacionAdapter(Context context, String json){
        this.context=context;
        this.cadena=json;

        try {
            JSONObject obj= new JSONObject(json);
            publicaciones=new JSONArray(obj.getString("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.publicacion_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try {
            JSONObject publicacion=publicaciones.getJSONObject(i);
            viewHolder.id.setText("ID: "+publicacion.getString("id"));
            viewHolder.fecha.setText("Fecha: "+publicacion.getString("created_time"));
            if(!publicacion.isNull("message")){
                viewHolder.mensaje.setText("Publicación: "+publicacion.getString("message"));
            }else{
                viewHolder.mensaje.setText("Publicación: "+publicacion.getString("story"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return publicaciones.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView fecha;
        TextView mensaje;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.txtid);
            fecha=itemView.findViewById(R.id.txtFecha);
            mensaje=itemView.findViewById(R.id.txtMessage);
        }
    }
}
