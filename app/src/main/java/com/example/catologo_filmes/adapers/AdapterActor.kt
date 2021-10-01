package com.example.catologo_filmes.adapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catologo_filmes.data.ActorList
import com.example.catologo_filmes.databinding.ItemListActorBinding

import com.squareup.picasso.Picasso

class AdapterActor(private val actors: List<ActorList>):
    RecyclerView.Adapter<AdapterActor.ViewHolder>() {


    class ViewHolder(view: ItemListActorBinding) : RecyclerView.ViewHolder(view.root) {
        val imgActor = view.imageViewActor
        val nameActor = view.textViewNameActor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListActorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(actors[position].image).into(holder.imgActor)
        holder.nameActor.text = actors[position].name
    }

    override fun getItemCount(): Int {
        return actors.size
    }

}