/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menus;

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
public class m_principal {
    public Toolkit t = Toolkit.getDefaultToolkit();
    public Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    FlowLayout mimenu;
    JPanel p_archivo,p_catalogos,menu;
    JLabel fondo_menu,m1_archivo,m1_sb1_configuracion,m2_catalogos,m2_sb1_marcas,m2_sb2_tiposart,m2_sb3_ubicaciones;
    Font  fuente_menu  =   new Font("Tahoma", Font.BOLD, 16);
    Color color_fuente,color_fondo,color_fentered;
    Color color_borde=new Color(6,102,76);
    Color color_bentered=new Color(29,138,109);
    int m_fondo_r,m_fondo_g,m_fondo_b,m_fuente_r,m_fuente_g,m_fuente_b,m_fentered_r,m_fentered_g,m_fentered_b;
    javax.swing.border.Border border = BorderFactory.createLineBorder(color_borde, 2);
    ResourceBundle rb = ResourceBundle.getBundle("configuracion.diseño");
    
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
        bt_letra = rb.getString("bt_letra");
        bt_letra_tamaño =Integer.parseInt(rb.getString("bt_letra_tamaño"));
        fuente_titulo =   new Font(bt_letra, Font.BOLD + Font.ITALIC, bt_letra_tamaño);
        btn_cerrar_inactivo = rb.getString("btn_cerrar_inactivo");
        btn_cerrar_activo = rb.getString("btn_cerrar_activo");
        btn_min_inactivo = rb.getString("btn_min_inactivo");
        btn_min_activo = rb.getString("btn_min_activo");
        btn_bloq_inactivo = rb.getString("btn_bloq_inactivo");
        btn_bloq_activo = rb.getString("btn_bloq_activo");
        btn_cerrar_tooltip=rb.getString("btn_cerrar_tooltip");
        btn_min_tooltip=rb.getString("btn_min_tooltip");
        btn_bloq_tooltip=rb.getString("btn_bloq_tooltip");
  }
}
