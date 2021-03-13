package com.bbva.petagram;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutV ();

    public PetsAdaptador creaAdaptador(ArrayList<ListaPets> lista);

    public void inicializarAdaptadorRV(PetsAdaptador adaptador);

}
