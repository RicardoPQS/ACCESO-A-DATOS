import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    private final int saldoInicial; 
    private final int númeroDeCuentas;
    private boolean abierto; 
    private Connection conexión; 

    public Banco(int numCuentas, int saldoInicial) {
        this.abierto = true; // El banco comienza abierto
        this.saldoInicial = saldoInicial;
        this.númeroDeCuentas = numCuentas;

        try {
            conexión = DriverManager.getConnection("jdbc:mysql://localhost/adat4?allowPublicKeyRetrieval=true", 
                                                   "dam2", "asdf.1234");

            // Inicializa la base de datos con las cuentas y sus saldos
            Statement sql = conexión.createStatement();
            sql.execute("DROP TABLE IF EXISTS cuentas "); 
            sql.execute("create table cuentas(id int primary key, saldo float)"); 
            for (int i = 0; i < numCuentas; i++) {
                sql.execute(String.format("insert into cuentas values(%d,%d)", i, saldoInicial));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            conexión = null; // Marca la conexión como inválida
            this.abierto = false; // El banco no se puede abrir si hay errores
        }
    }

    // Método para realizar una transferencia entre cuentas usando el procedimiento almacenado
    public void transfiere(int cuentaOrigen, int cuentaDestino, int cantidad, Connection conexión) {
        String llamadaProcedimiento = "{CALL transferencia(?, ?, ?)}"; // Llamada al procedimiento almacenado

        try (PreparedStatement stmt = conexión.prepareStatement(llamadaProcedimiento)) {
            stmt.setInt(1, cuentaOrigen); // Configura la cuenta origen
            stmt.setInt(2, cuentaDestino); // Configura la cuenta destino
            stmt.setDouble(3, cantidad); // Configura la cantidad a transferir
            stmt.execute(); // Ejecuta el procedimiento almacenado
        } catch (SQLException e) {
            System.err.println("Error en transferencia: " + e.getMessage());
        }
    }

    // Método para comprobar el estado de las cuentas en la base de datos
    public void comprueba() throws SQLException {
        int saldoTotal = 0; 
        Statement sql = conexión.createStatement();

        // Calcula la suma de todos los saldos en la tabla `cuentas`
        ResultSet res = sql.executeQuery("SELECT SUM(saldo) FROM cuentas");
        if (res.next()) {
            saldoTotal = (int) res.getFloat(1); 
            // Verifica si el saldo total coincide con el saldo esperado
            if (saldoTotal != (númeroDeCuentas * saldoInicial)) {
                System.err.println("¡¡¡¡¡No cuadran las cuentas!!!! saldo total " + saldoTotal);
            } else {
                System.out.println("Balance correcto"); 
            }
        }

        // Busca cuentas con saldo negativo (descubiertos)
        res = sql.executeQuery("SELECT id FROM cuentas WHERE saldo<0");
        while (res.next()) {
            System.err.println("DESCUBIERTO en cuenta " + res.getInt(1));
        }

        /* 
         * Detalle opcional para imprimir saldos individuales (comentado por defecto):
         * ResultSet res = sql.executeQuery("SELECT id,saldo FROM cuentas");
         * while (res.next()) { 
         *     int saldo = (int) res.getFloat(2); 
         *     saldoTotal += saldo;
         *     System.out.printf("Cuenta %d , saldo %d, parcial %d\n", res.getInt(1), saldo, saldoTotal); 
         * }
         */
    }

    
    public int getNúmeroDeCuentas() {
        return númeroDeCuentas;
    }

    // Verifica si el banco sigue abierto para operaciones
    boolean abierto() {
        return abierto;
    }

    // Cierra el banco para nuevas operaciones
    void cierraBanco() {
        abierto = false;
    }

    // Cierra la conexión a la base de datos
    void cierraConexiónBD() {
        try {
            conexión.close(); // Intenta cerrar la conexión
        } catch (SQLException e) {
            System.err.println("Error cerrando conexión de BBDD " + e.getMessage());
        }
    }
}
