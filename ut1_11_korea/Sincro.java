package korea;

import java.util.concurrent.Semaphore;

public class Sincro {

    Semaphore comunicaciones = new Semaphore(1);

    public void bloquearComunicaciones() {
        try {
            comunicaciones.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void desbloquearComunicaciones() {
        comunicaciones.release();
    }
}
