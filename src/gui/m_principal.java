/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Frame.ICONIFIED;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.ResourceBundle;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Fam
 */
public class m_principal  extends JPanel{
    public Toolkit t = Toolkit.getDefaultToolkit();
    public Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    FlowLayout mimenu;
    JPanel p_archivo,p_catalogos,menu;
    JLabel fondo_menu,m1_archivo,m1_sb1_configuracion,m2_catalogos,m2_sb1_marcas,m2_sb2_tiposart,m2_sb3_ubicaciones;
    Font  fuente_menu  =   new Font("Tahoma", Font.BOLD, 16);
    Color color_fuente,color_fondo,color_fentered,color_bentered,color_borde;
    int m_fondo_r,m_fondo_g,m_fondo_b,m_fuente_r,m_fuente_g,m_fuente_b,m_fentered_r,m_fentered_g,m_fentered_b;
    int m_bentered_r,m_bentered_g,m_bentered_b,m_borde_r,m_borde_g,m_borde_b,m_letra_tamaño;
    String m_letra;
    javax.swing.border.Border border = BorderFactory.createLineBorder(color_borde, 2);
    ResourceBundle rb = ResourceBundle.getBundle("config.diseño");
    
    public m_principal(){
        
    }
    
     public void diseño(){
        m_fondo_r = Integer.parseInt(rb.getString("mp_fondo_r"));
        m_fondo_g = Integer.parseInt(rb.getString("mp_fondo_g"));
        m_fondo_b = Integer.parseInt(rb.getString("mp_fondo_b"));
        color_fondo = new Color(m_fondo_r,m_fondo_g,m_fondo_b);
        m_fuente_r = Integer.parseInt(rb.getString("mp_fuente_r"));
        m_fuente_g = Integer.parseInt(rb.getString("mp_fuente_g"));
        m_fuente_b = Integer.parseInt(rb.getString("mp_fuente_b"));
        color_fuente = new Color(m_fuente_r,m_fuente_g,m_fuente_b);
        m_fentered_r = Integer.parseInt(rb.getString("mp_fentered_r"));
        m_fentered_g = Integer.parseInt(rb.getString("mp_fentered_g"));
        m_fentered_b = Integer.parseInt(rb.getString("mp_fentered_b"));
        color_fentered = new Color(m_fentered_r,m_fentered_g,m_fentered_b);
        m_bentered_r = Integer.parseInt(rb.getString("mp_bentered_r"));
        m_bentered_g = Integer.parseInt(rb.getString("mp_bentered_g"));
        m_bentered_b = Integer.parseInt(rb.getString("mp_bentered_b"));
        color_bentered = new Color(m_fentered_r,m_fentered_g,m_fentered_b);
        m_borde_r = Integer.parseInt(rb.getString("mp_bentered_r"));
        m_borde_g = Integer.parseInt(rb.getString("mp_bentered_g"));
        m_borde_b = Integer.parseInt(rb.getString("mp_bentered_b"));
        color_borde = new Color(m_borde_r,m_borde_g,m_borde_b);  
        m_letra = rb.getString("mp_letra");
        m_letra_tamaño =Integer.parseInt(rb.getString("mp_letra_tamaño"));
  }
     public void propiedades(){
        fondo_menu=new JLabel();
        fondo_menu.setBounds(0,0, 250, (int) pantalla.getHeight());
        fondo_menu.setBackground(color_fondo); 
        fondo_menu.setOpaque(true);
        fondo_menu.setBorder(border);
        fondo_menu.setVisible(true);
     }
     
}
