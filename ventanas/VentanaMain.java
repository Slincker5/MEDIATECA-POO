package ventanas;

import vistas.VistaMain;

public class VentanaMain extends Ventana {

    public VentanaMain() {
        super("Mediateca - Mi crud", 800, 500);
        VistaMain div = new VistaMain();
        add(div);
    }
}
