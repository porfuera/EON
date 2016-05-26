/*
 * Seguridad.java
 *
 * Created on 30 de abril de 2008, 07:05 AM
 *
 */

package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Gilberto Adan Gonzalez Silva
 */
public class Seguridad extends abstractt.ClaseAbstracta{
  
  /** Creates a new instance of Seguridad */
  public Seguridad() {
  }
  
  public boolean equipoValido(){
    
    String consulta;    
    String idDisco = obtenerIdDisco();    
    
    //System.out.println("volumen "+idDisco);
    
    consulta = "SELECT * FROM Equipo WHERE numeroSerieHDD = '" + idDisco + "'" ;    
    mbd.consulta( consulta );
    
    if( mbd.getRowCount() == 0 ){
      
      return false;
    }
    
    return true;
  }
  
  public String obtenerIdDisco(){
    
    String[] cmd = new String[3];
    cmd[0] = "cmd.exe";
    cmd[1] = "/c";
    cmd[2] = "vol.bat";
    Process proc = null;
    
    try {
      proc = Runtime.getRuntime().exec(cmd);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
    InputStreamReader isr = new InputStreamReader(proc.getInputStream());
    BufferedReader br = new BufferedReader(isr);
    String text ="";
    String line;
    
    try {
      
      while ((line = br.readLine()) != null) {
        
        text = text + line + "\n";
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
    StringTokenizer stt = new StringTokenizer(text);
    
    int to = stt.countTokens();
    
    for(int i = 0 ; i < to - 1 ; i ++ )
      stt.nextToken();
    
    String id = stt.nextToken();
   // System.out.println(id);
    return id;    
  }
  
  public int obtenerIdEquipo(){
    
    String idDisco = this.obtenerIdDisco();    
    String consulta = "SELECT idEquipo FROM Equipo WHERE numeroSerieHDD = '"+idDisco+"'";   
    //System.out.println(consulta);
    mbd.consulta(consulta);    
    return mbd.getValorInt(0,0);
  }
  
  public int obtenerIdCaja(){
    
    String idDisco = obtenerIdDisco();    
    String consulta = "SELECT idCaja FROM Equipo WHERE numeroSerieHDD = '"+idDisco+"'";    
    mbd.consulta(consulta);    
    return mbd.getValorInt(0,0);
  }
  
  /**
   *Devuelve el Id del Usuario Activo
   */
  public int obtenerIdUsuario(){
    
    int id = 0;
    String consulta = "SELECT idUsuario FROM Usuario WHERE idEquipo = "+obtenerIdEquipo();    
    mbd.consulta(consulta);    
    id = Integer.parseInt( mbd.getValueAt(0,0).toString() );    
    return id;
  }
  
   /**
   *Devuelve el Id del Usuario Activo
   */
  public String obtenerNombreUsuario(){
    
    String nombre = "";
    String consulta = "SELECT nombre FROM Usuario WHERE idEquipo = "+obtenerIdEquipo();    
    mbd.consulta(consulta);    
   nombre = mbd.getValorString(0,0);
    return nombre;
  }
  
  
  /**
   *
   */
  public int obtenerPerfilUsuario(){
    
    int idPerfil = 0;
    String consulta = "SELECT idPerfil FROM Usuario WHERE idEquipo = "+obtenerIdEquipo();  
    //System.out.println(consulta);
    mbd.consulta(consulta);
    idPerfil = Integer.parseInt( mbd.getValueAt(0,0).toString() );    
    return idPerfil;    
  }
  
 }
