package com.example.location.dummy;

import com.example.location.R;
import com.example.location.model.GrupoDeImagenes;

import java.util.ArrayList;
import java.util.List;

public class GruposLocales {
    List<GrupoDeImagenes> ListaDeGrupos = new ArrayList<>();

    public GruposLocales() {
        ListaDeGrupos.add(new GrupoDeImagenes(1, "Dinosaurios","", R.drawable.fish));
        ListaDeGrupos.add(new GrupoDeImagenes(2, "Peces","", R.drawable.fish));
        ListaDeGrupos.add(new GrupoDeImagenes(3, "Pajaros","", R.drawable.fish));
        ListaDeGrupos.add(new GrupoDeImagenes(4, "Plantas","", R.drawable.fish));
        ListaDeGrupos.add(new GrupoDeImagenes(5, "Huesos","", R.drawable.fish));
        ListaDeGrupos.add(new GrupoDeImagenes(6, "Pinturas","", R.drawable.fish));
    }

    public List<GrupoDeImagenes> list() {
        return ListaDeGrupos;
    }
}