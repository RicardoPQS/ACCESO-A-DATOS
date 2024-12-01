import java.sql.SQLException;

public class SimulaBanco {

	public static final int NÚMERO_DE_CUENTAS = 10;
	public static final int NÚMERO_DE_HILOS = 20;
	// mysql> show status like 'max_used_connections';
	public static final int SALDO_INICIAL = 10000;

	public static void main(String[] args) throws InterruptedException, SQLException {
		Banco banco = new Banco(NÚMERO_DE_CUENTAS, SALDO_INICIAL);
		
		 // Lanza muchos hilos para realizar transferencias simultáneas:
        Thread[] hilos = new Thread[NÚMERO_DE_HILOS]; 
        for (int i=0; i<NÚMERO_DE_HILOS;i++) {
        	hilos[i] = new Thread(new HiloTransferencia(banco,i,SALDO_INICIAL));
        	hilos[i].start();
        }
        
        // Esperar a que terminen todos los hilos
        int numHilos = NÚMERO_DE_HILOS;
        while (numHilos>0) {
        	banco.comprueba();// Verifica la consistencia del saldo total en cada iteración.
        	Thread.sleep(1000);// Pausa para evitar comprobar constantemente.
        	numHilos = 0;
	        for (int i=0; i<NÚMERO_DE_HILOS;i++) {
	        	// Cuenta los hilos que aún están activos.
	        	if (hilos[i].isAlive()) {   		
	        		numHilos++;
	        	}
	        }
	        System.out.println("Hilos vivos: " + numHilos);
	     // Si queda solo un hilo activo, cierra el banco para evitar bloqueos.
	        if (numHilos<2) {  
	        	banco.cierraBanco(); // Cuando solo quede un hilo cerramos el banco para evitar bloqueo
	        }
        }

		System.out.println("Terminadas todas las transferencias");

		// Comprobación:
		banco.comprueba();
		// Ciere final:
		banco.cierraConexiónBD();
	}

}