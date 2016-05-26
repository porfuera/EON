/*
 * textField.java
 *
 * Created on 16 de mayo de 2008, 02:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package abstractt;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;

/**
 *
 * @author Administrator
 */
public class TextField extends javax.swing.JTextField{
   
   /** Creates a new instance of textField */
   public TextField() {
      
      addFocusListener(new java.awt.event.FocusAdapter() {
         public void focusGained(java.awt.event.FocusEvent evt) {
            FocusGained(evt);
         }
         public void focusLost(java.awt.event.FocusEvent evt) {
            FocusLost(evt);
         }
      });
      
      addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            ActionPerformed(evt);
         }
      });
      
      setFont(new java.awt.Font("Tahoma", 0, 12));
      
   }
   
   private void ActionPerformed(java.awt.event.ActionEvent evt){
      
      
      transferFocus();
   }
   
   private void FocusGained(java.awt.event.FocusEvent evt){
      
      if(isDouble){
         
         this.setText(obtenerNumero(getText()));
      }
      
      seleccionarTexto();
   }
   
   private void FocusLost(java.awt.event.FocusEvent evt){
      
      if(isDouble){
         if(getText().equals("")){
            setText("0.0");
         }
         //this.setText(obtenerNumero(getText()));
      }
   }
   
   public boolean esNumero(char caracter){
      
      if(((caracter < '0') || (caracter > '9') )) {
         
         return true;
      }
      
      return false;
   }
   
   /**
    *Modifica eventos del teclado para la jtextFiled para que solo permita
    *Escribir numeros con punto flotante
    */
   public void textFieldDouble(){
      
      addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            
            char caracter = e.getKeyChar();
            String texto = getText();
            StringTokenizer s = new StringTokenizer(texto,".");
            String textoSeleccionado = getSelectedText();
            
            //*Modificacion, para que pueda empezar a escribir con un punto*///
            if( texto.length() == 0 || ( textoSeleccionado != null && texto.length() == textoSeleccionado.length()) ){
               
               if(caracter == '.'){
                  
                  setText("0.");
                  e.consume();
               }
            }
            ///**///
            if(texto.length() < 1){
               
               if(((caracter < '0') || (caracter > '9') ) && (caracter != KeyEvent.VK_BACK_SPACE)) {
                  e.consume();
               }
            }else{
               
               char ultimoCaracter = texto.charAt(texto.length()-1);
               
               if(s.countTokens()>=2 || ultimoCaracter == '.'){
                  if(((caracter < '0') ||(caracter > '9') ) && (caracter != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                  }
               } else{
                  if(((caracter < '0') || (caracter > '9') ) && (caracter != '.') && (caracter != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                  }
               }
            }
         }
      });
      
      
      isDouble = true;
   }
   
   private boolean isDouble = false;
   
   public double getDouble(){
      
      try{
         
         String texto = getText();
         return Double.parseDouble(texto);
         
      }catch(NumberFormatException e){
         
      }
      
      return 0.0;
   }
   
   public double getInt(){
      
      try{
         
         String texto = getText();
         return Integer.parseInt(texto);
         
      }catch(NumberFormatException e){
         
      }
      
      return 0;
   }
   
   /**
    *Modifica eventos del teclado para la jtextFiled para que solo permita
    *Escribir letras
    */
   public void textFieldSoloLetras(){
      
      addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            char caracter = e.getKeyChar();
            if( ((caracter < 'a') || (caracter > 'z')) &&
               (caracter != KeyEvent.VK_BACK_SPACE)       ) {
               e.consume();
            }
         }
      });
   }
   
   /**
    *Modifica eventos del teclado para la jtextFiled para que solo permita
    *Escribir numeros y letras
    */
   public void textFieldSoloNumerosYLetras(){
      
      addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            char caracter = e.getKeyChar();
            if( ((caracter < '0') || (caracter > '9')) &&
               ((caracter < 'a') || (caracter > 'z')) &&
               (caracter != KeyEvent.VK_BACK_SPACE)        ) {
               e.consume();
            }
         }
      });
   }
   
   /**
    *Modifica eventos del teclado para la jtextFiled para que solo permita
    *Escribir numeros
    */
   public void textFieldSoloNumeros(){
      
      addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            char caracter = e.getKeyChar();
            if( ((caracter < '0') || (caracter > '9')) &&
               (caracter != KeyEvent.VK_BACK_SPACE)       ) {
               e.consume();
            }
         }
      });
   }
   /**
    *Dado un TextFiled el texto se muestra seleccionado para su edicion rapida
    */
   public String seleccionarTexto(){
      
      select(0, getText().length());
      return getText();
   }
   
   public void quitarSeleccion(){
       
       select(getText().length()-3, getText().length()-1);
       //return getText();
   }
   /**
    *Si el numero tiene comas se las quita
    */
   protected String obtenerNumero(String numero){
      
      StringTokenizer num = new StringTokenizer(numero,",");
      
      numero = "";
      
      while(num.hasMoreTokens()) {
         numero += num.nextToken();
      }
      //numero += num.nextToken();
      return numero;
   }
   
}
