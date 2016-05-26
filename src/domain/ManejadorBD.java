/*
 * ManejadorBD.java
 *
 * Created on 9 de febrero de 2008, 12:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package domain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Clase para controlar cualquier base de datos
 *
 * @author Gilbeto Adan Gonz�lez Silva
 */
public class ManejadorBD extends AbstractTableModel {

    static String controlador_jdbc;
    static String url_basededatos;
    static String usuario;
    static String palabraClave;

    private Connection conexion;
    private Statement sentencia;
    private ResultSet conjuntoResultados;
    private ResultSetMetaData metaDatos;
    private int numeroFilas;
    private boolean conectado;
    private String tablaRestringida;

    /**
     * Creates a new instance of ManejadorBD
     */
    public ManejadorBD() {

        numeroFilas = 0;

        conectado = false;

    }

    public Connection getConexion() {
        return conexion;
    }

    /**
     */
    public void conectar(String controlador, String url, String nombreUsuario, String clave)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        controlador_jdbc = controlador;

        url_basededatos = url;

        usuario = nombreUsuario;

        palabraClave = clave;

        if (!conectado) {

            Class.forName(controlador_jdbc).newInstance();

            conexion = DriverManager.getConnection(url_basededatos, usuario, palabraClave);

            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            conectado = true;
        }
    }

    public void reconectar() {

        desconectar();
        conectar();
    }

    public void conectar() {
        if (!conectado) {
            try {

                Class.forName(controlador_jdbc).newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            try {

                conexion = DriverManager.getConnection(url_basededatos, usuario, palabraClave);
            } catch (SQLException ex) {

                ex.printStackTrace();
            }
            try {

                sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException ex) {

                ex.printStackTrace();
            }

            conectado = true;
        }
    }

    public ManejadorBD nuevaConexion() {
        ManejadorBD nMbd = new ManejadorBD();

        try {

            nMbd.conectar(this.controlador_jdbc, this.url_basededatos, this.usuario, this.palabraClave);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nMbd;
    }

    /**
     *
     *
     */
    private void executarSP(String procedure, ParametrosSP parametrosSP) {

        List<Parametro> parametros;
        Parametro parametro;

        CallableStatement SP;
        try {
            SP = conexion.prepareCall("{ call NuestroProcedimientoAlmacenado(?,?,?) }");
            // cargar parametros al SP

            parametros = parametrosSP.getParametros();

            for (Iterator<Parametro> it = parametros.iterator(); it.hasNext();) {

                parametro = it.next();

                switch (parametro.tipo) {
                    case "INT":
                        SP.setInt(parametro.nombre, Integer.parseInt(parametro.valor));
                        break;
                    case "STRING":
                        SP.setString(parametro.nombre, parametro.valor);
                        break;
                    case "DOUBLE":
                        SP.setDouble(parametro.nombre, Double.parseDouble(parametro.valor));
                        break;
                    case "DATETIME":
                        SP.setDate(parametro.nombre, (Date) parametro.calendar.getTime(), parametro.calendar);                      
                        break;
                }
            }
            // ejecutar el SP
            SP.execute();
            // confirmar si se ejecuto sin errores
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    private void setConsulta(String consulta) throws SQLException {

        if (!conectado) {

            throw new IllegalStateException("No hay conexion a la base de datos");
        }

        sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//ResultSet.CONCUR_READ_ONLY);

        conjuntoResultados = sentencia.executeQuery(consulta);

        metaDatos = conjuntoResultados.getMetaData();

        numeroFilas = 0;

        while (conjuntoResultados.next()) {

            try {

                conjuntoResultados.getObject(1);

                numeroFilas++;

            } catch (Exception excepcion) {

                break;
            }

        }

        fireTableStructureChanged();

    }

    public void consulta(String consulta) {

        try {

            setConsulta(consulta);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setInsert(String consulta) throws SQLException {

        if (!conectado) {

            throw new IllegalStateException("No hay conexion a la base de datos");
        }

        sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//ResultSet.CONCUR_READ_ONLY);

        conjuntoResultados = sentencia.executeQuery(consulta);

        metaDatos = conjuntoResultados.getMetaData();

        numeroFilas = 0;

        while (conjuntoResultados.next()) {

            try {

                conjuntoResultados.getObject(1);

                numeroFilas++;

            } catch (Exception excepcion) {

                break;

            }
        }

        fireTableStructureChanged();
    }

    /**
     *
     */
    private void setUpDate(String update) throws SQLException {

        if (!conectado) {

            throw new IllegalStateException("No hay conexion a la base de datos");
        }

        sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//.CONCUR_READ_ONLY);

        sentencia.executeUpdate(update);

    }

    public void insercion(String insercion) {

        try {

            setUpDate(insercion);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizacion(String actualizacion) {

        try {

            setUpDate(actualizacion);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminacion(String eliminacion) {

        try {

            setUpDate(eliminacion);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void desconectar() {

        if (conectado) {
            try {

                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {

                sentencia.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            conectado = false;
        }
    }

    /**
     *
     */
    public Class getColumnClass(int columna) throws IllegalStateException {

        if (!conectado) {

            throw new IllegalStateException("No hay conexion");
        }

        try {

            return Class.forName(new String(metaDatos.getColumnClassName(columna + 1)));

        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        return metaDatos.getClass();
    }

    /**
     *
     */
    public String getColumnName(int columna) throws IllegalStateException {

        if (!conectado) {

            throw new IllegalStateException("Sin conexion");
        }
        try {

            return metaDatos.getColumnName(columna + 1);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    /**
     *
     */
    public int getRowCount() {

        if (!conectado) {

            throw new IllegalStateException("Sin conexion");
        }

        return numeroFilas;
    }

    /**
     *
     */
    public int getColumnCount() {
        if (!conectado) {
            throw new IllegalStateException("Sin conexion");
        }
        try {
            return metaDatos.getColumnCount();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    /**
     *
     */
    public Object getValueAt(int fila, int columna) {
        if (!conectado) {
            throw new IllegalStateException("Sin conexion");
        }
        try {
            conjuntoResultados.absolute(fila + 1);
            return conjuntoResultados.getObject(columna + 1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    /**
     * Devuelve el valor entero de la consulta
     */
    public int getValorInt(int fila, int columna) {

        return Integer.parseInt(getValueAt(fila, columna).toString());
    }

    /**
     * Devuleve el valor boleano de la consulta
     */
    public boolean getValorBoolean(int fila, int columna) {

        if (getValorString(fila, columna).equals("true")) {

            return true;
        } else {

            return false;
        }

    }

    /**
     * Devuelve el valor double de la consulta
     */
    public double getValorDouble(int fila, int columna) {

        //System.out.println(getValueAt(fila, columna).toString());
        // return Double.parseDouble(obtenerNumero(getValueAt(fila, columna).toString()));
        return Double.parseDouble(getValueAt(fila, columna).toString());
    }

    /**
     * Si el numero tiene comas se las quita
     */
    protected String obtenerNumero(String numero) {

        StringTokenizer num = new StringTokenizer(numero, ",");

        numero = "";

        System.out.println(num.countTokens());

        while (num.hasMoreTokens()) {
            numero += num.nextToken();
        }
        //numero += num.nextToken();
        return numero;
    }

    /**
     * Devuelve el valor en un String de la consulta
     */
    public String getValorString(int fila, int columna) {

        return getValueAt(fila, columna).toString();
    }

    /**
     * Devuelve el nombre de la tabla actual
     *
     * @return El nombre de las tablas contenidas en la base de datos
     * @throws IllegalStateException Cuando no hay conexion
     */
    public String getTableName() throws IllegalStateException {
        if (!conectado) {
            throw new IllegalStateException("Sin conexion");
        }

        try {

            return metaDatos.getTableName(1);
        } catch (SQLException excepcionSQL) {
            javax.swing.JOptionPane.showInputDialog("SQL", "No se pudo obtener los metadatos");
        }
        return null;
    }

    /**
     * Establece el nombre de la tabla la cual estara restringida a
     * modificaciones
     *
     * @param tabla Nombre de la tabla que se quiere restringir
     */
    public void setTablaRestringida(String tabla) {
        tablaRestringida = tabla;
    }

    /**
     * Devuelve el nombre de la tabla restringida
     *
     * @return Nombre de la tabla restringida
     */
    public String getTablaRestringida() {
        return tablaRestringida;
    }

    /**
     * Elimina de la tabla las filas contenidas por el vector de enteros
     *
     * @param filas El indice de las filas que se desea borrar
     */
    public void borrarTuplas(int filas[]) {
        String tabla = this.getTableName();
        String columna = this.getColumnName(0);
        String comillas = "";

//	para hacer una consulta con campos string son necesarias las comillas para enteros y dobles no los son
        if (this.getColumnClass(0).equals(String.class)) {
            comillas = "'";
        }
//	se hace en orden inverso para no tener problemas con los indices de las filas cuando son borradas ya que estos van cambiando
        for (int i = filas.length - 1; i >= 0; --i) {
            String fila = this.getValueAt(filas[i], 0).toString();

            String actualizacion = "delete from " + tabla + " where " + columna + " = " + comillas + fila + comillas;

            try {
                this.setActualizacion(actualizacion);
                this.setConsulta("select * from " + tabla);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showInputDialog(e.getMessage());
            }
        }
    }

    /**
     * Actualiza el estado de la tabla abstracta despues de que han ocurrido
     * cambios en su estructura
     *
     * @throws SQLException Cuando la obtencion de los meta-datos no se pudo
     * realizar
     */
    private void actualizar() throws SQLException {
//		obtencion de los metadatos de la consulta actual
        metaDatos = conjuntoResultados.getMetaData();

        numeroFilas = 0;
        while (conjuntoResultados.next()) {
            try {
                conjuntoResultados.getObject(1);
                numeroFilas++;
            } catch (Exception excepcion) {
                break;
            }
        }
//	notifica que han existido modificaciones en el modelo
        fireTableStructureChanged();
    }

    /**
     * Agrega una nueva tupla a la tabla especificada
     *
     */
    public void agregarTupla() {
        String tabla = this.getTableName();
        String columnas[];

        try {
            columnas = this.getConjuntoColumnas(tabla);

            String conjuntoColumnas = "";
            String values = "";

            for (int j = 0; j < columnas.length; ++j) {
                if (!values.equals("")) {
                    conjuntoColumnas += ",";
                    values += ",";
                }

                conjuntoColumnas += columnas[j];

//              si el campo es de tipo string se inicializara con comillas
                if (this.getColumnClass(j).equals(String.class)) {
                    values += "''";
                } //		si no se iniciara con un cero
                else {
                    values += "0";
                }
            }

            String actualizacion = "insert into " + tabla + " (" + conjuntoColumnas + ") values (" + values + ")";

            try {
                this.setActualizacion(actualizacion);
                this.setConsulta("select * from " + tabla);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showInputDialog(e.getMessage());
            }

        } catch (SQLException e) {
            JOptionPane.showInputDialog(e.getMessage());
        }
    }

    /**
     * Ejecuta una actualizacion
     *
     * @param actualizacion Sentencia SQL de actualizacion
     * @throws SQLException Cuando la sentencia SQL establecida falla o es
     * invalida
     */
    public void setActualizacion(String actualizacion) throws SQLException {
        if (!conectado) {
            throw new IllegalStateException("No hay conexion en la base de datos");
        }
//		crear una sentencia predeterminada
        sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//.CONCUR_READ_ONLY);
//		ejecutando la consulta descrita por el usuario
        sentencia.executeUpdate(actualizacion);
    }

    /**
     * Devuelve el nombre de todas las columnas de una tabla especificada
     *
     * @param tabla Nombre de la tabla de la cual se quiere obtener sus columnas
     * @return El nombre de las columnas de la tabla especificada
     * @throws SQLException Cuando no se pueden obtener los meta-datos
     */
    public String[] getConjuntoColumnas(String tabla) throws SQLException {
        String resultado[];

        String consulta = "select * from ";
        consulta += tabla;

//	crear una sentencia predeterminada
        sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//.CONCUR_READ_ONLY);
//	ejecutando la consulta descrita por el usuario
        conjuntoResultados = sentencia.executeQuery(consulta);
//      obtencion de los metadatos
        metaDatos = conjuntoResultados.getMetaData();

        resultado = new String[metaDatos.getColumnCount()];

        for (int i = 0; i < metaDatos.getColumnCount(); ++i) {
            resultado[i] = metaDatos.getColumnName(i + 1);
        }

        return resultado;
    }

}
