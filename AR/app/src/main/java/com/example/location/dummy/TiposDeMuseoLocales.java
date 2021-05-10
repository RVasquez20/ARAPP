package com.example.location.dummy;

import com.example.location.R;
import com.example.location.model.TiposDeMuseo;

import java.util.ArrayList;
import java.util.List;

public class TiposDeMuseoLocales {
    List<com.example.location.model.TiposDeMuseo> TiposDeMuseo = new ArrayList<>();

    public TiposDeMuseoLocales() {
        TiposDeMuseo.add(new TiposDeMuseo(1, "", R.drawable.museo1,""));
        TiposDeMuseo.add(new TiposDeMuseo(2, "MUSEO VACIO",R.drawable.baotang,"DISPONIBLE"));

    }

    public List<com.example.location.model.TiposDeMuseo> list() {
        return TiposDeMuseo;
    }
}
