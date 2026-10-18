package com.ucenm.registrocontactos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucenm.registrocontactos.R;
import com.ucenm.registrocontactos.model.Contacto;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
    private final List<Contacto> contactos;

    public ContactoAdapter(List<Contacto> contactos) { this.contactos = contactos; }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        holder.detalle.setText(contacto.getNombre() + " | " + contacto.getCorreo() + " | " +
                contacto.getTelefono() + " | " + contacto.getOpcion());
    }

    @Override
    public int getItemCount() { return contactos.size(); }

    static class ContactoViewHolder extends RecyclerView.ViewHolder {
        final TextView detalle;
        ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            detalle = itemView.findViewById(R.id.tvContacto);
        }
    }
}
