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
    Font  fuente_menu ;
    Color color_fuente,color_fondo,color_fentered,color_bentered,color_borde;
    int m_fondo_r,m_fondo_g,m_fondo_b,m_fuente_r,m_fuente_g,m_fuente_b,m_fentered_r,m_fentered_g,m_fentered_b;
    int m_bentered_r,m_bentered_g,m_bentered_b,m_borde_r,m_borde_g,m_borde_b,m_letra_tamaño,m_ancho;
    String m_letra;
    javax.swing.border.Border border ;
    ResourceBundle rb = ResourceBundle.getBundle("config.diseño");
    
    public m_principal(){
        m_ancho=Integer.parseInt(rb.getString("mp_ancho"));
        diseño();
        propiedades();
        menu= new JPanel();
        menu.setBounds(0,0, m_ancho, 500);
        menu.setOpaque(false);
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        
        p_archivo = new JPanel();
        p_archivo.setLayout(new BoxLayout(p_archivo,BoxLayout.Y_AXIS));
        p_archivo.add(m1_archivo);
        p_archivo.setBackground(color_fondo);
        p_archivo.setMinimumSize(new Dimension(m_ancho, p_archivo.getHeight()));
        p_archivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        p_catalogos = new JPanel();
        p_catalogos.setLayout(new BoxLayout(p_catalogos,BoxLayout.Y_AXIS));
        p_catalogos.setBackground(color_fondo);
        
        m1_archivo.setMaximumSize(new Dimension(m_ancho, 35));
        m1_sb1_configuracion.setMaximumSize(new Dimension(m_ancho, 35));
        m2_catalogos.setMaximumSize(new Dimension(m_ancho, 35));
        m2_sb1_marcas.setMaximumSize(new Dimension(m_ancho, 35));
        m2_sb2_tiposart.setMaximumSize(new Dimension(m_ancho, 35));
        m2_sb3_ubicaciones.setMaximumSize(new Dimension(m_ancho, 35));
        
        p_archivo.add(m1_archivo);
        p_catalogos.add(m2_catalogos);
        
        
        this.setLayout(null);
        this.setSize(m_ancho,pantalla.height - 35);
        this.setAlignmentX(SwingConstants.CENTER);
        //this.setBackground(new Color(195,227,251)); 
        
        menu.add(p_archivo);
        menu.add(p_catalogos);
        this.add(menu);
        this.add(fondo_menu);
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
        color_bentered = new Color(m_bentered_r,m_bentered_g,m_bentered_b);
        m_borde_r = Integer.parseInt(rb.getString("mp_bentered_r"));
        m_borde_g = Integer.parseInt(rb.getString("mp_bentered_g"));
        m_borde_b = Integer.parseInt(rb.getString("mp_bentered_b"));
        color_borde = new Color(m_borde_r,m_borde_g,m_borde_b);  
        border = BorderFactory.createLineBorder(color_borde, 2);
        m_letra = rb.getString("mp_letra");
        m_letra_tamaño =Integer.parseInt(rb.getString("mp_letra_tamaño"));
        fuente_menu  =   new Font(m_letra, Font.BOLD, m_letra_tamaño);
  }
     public void propiedades(){
        m_principal.MouseAdapter raton=new m_principal.MouseAdapter(){};
        fondo_menu=new JLabel();
                fondo_menu.setBounds(0,0, m_ancho, (int) pantalla.getHeight());
                fondo_menu.setBackground(color_fondo); 
                fondo_menu.setOpaque(true);
                fondo_menu.setBorder(border);
                fondo_menu.setVisible(true);
        m1_archivo=new JLabel("Archivo");
                m1_archivo.setFont(fuente_menu);
                m1_archivo.setForeground(color_fuente);
                m1_archivo.setBackground(color_fondo); 
                m1_archivo.setOpaque(true);
                m1_archivo.setVisible(true);
                m1_archivo.setBorder(border);
                m1_archivo.setAlignmentX(Component.CENTER_ALIGNMENT);
                m1_archivo.addMouseListener(raton);
        m2_catalogos=new JLabel("Catalogos");
                m2_catalogos.setFont(fuente_menu);
                m2_catalogos.setForeground(color_fuente);
                m2_catalogos.setBackground(color_fondo); 
                m2_catalogos.setOpaque(true);
                m2_catalogos.setVisible(true);
                m2_catalogos.setBorder(border);
                m2_catalogos.setAlignmentX(Component.CENTER_ALIGNMENT);
                m2_catalogos.addMouseListener(raton);
        m1_sb1_configuracion=new JLabel("Configuraciones Generales");
                m1_sb1_configuracion.setFont(fuente_menu);
                m1_sb1_configuracion.setForeground(color_fuente);
                m1_sb1_configuracion.setBackground(color_fondo); 
                m1_sb1_configuracion.setOpaque(true);
                m1_sb1_configuracion.setVisible(true);
                m1_sb1_configuracion.setBorder(border);
                m1_sb1_configuracion.setAlignmentX(Component.CENTER_ALIGNMENT);
                m1_sb1_configuracion.addMouseListener(raton);
        m2_sb1_marcas=new JLabel("Marcas");
                m2_sb1_marcas.setFont(fuente_menu);
                m2_sb1_marcas.setForeground(color_fuente);
                m2_sb1_marcas.setBackground(color_fondo); 
                m2_sb1_marcas.setOpaque(true);
                m2_sb1_marcas.setVisible(true);
                m2_sb1_marcas.setBorder(border);
                m2_sb1_marcas.setAlignmentX(Component.CENTER_ALIGNMENT);
                m2_sb1_marcas.addMouseListener(raton);
        m2_sb2_tiposart=new JLabel("Tipo de Articulo");
                m2_sb2_tiposart.setFont(fuente_menu);
                m2_sb2_tiposart.setForeground(color_fuente);
                m2_sb2_tiposart.setBackground(color_fondo); 
                m2_sb2_tiposart.setOpaque(true);
                m2_sb2_tiposart.setVisible(true);
                m2_sb2_tiposart.setBorder(border);
                m2_sb2_tiposart.setAlignmentX(Component.CENTER_ALIGNMENT);
                m2_sb2_tiposart.addMouseListener(raton);
        m2_sb3_ubicaciones=new JLabel("Ubicaciones");
                m2_sb3_ubicaciones.setFont(fuente_menu);
                m2_sb3_ubicaciones.setForeground(color_fuente);
                m2_sb3_ubicaciones.setBackground(color_fondo); 
                m2_sb3_ubicaciones.setOpaque(true);
                m2_sb3_ubicaciones.setVisible(true);
                m2_sb3_ubicaciones.setBorder(border);
                m2_sb3_ubicaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
                m2_sb3_ubicaciones.addMouseListener(raton);
     }
     
     private abstract class MouseAdapter implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        if (e.getSource() == m1_archivo ){
            if (p_archivo.getBorder() == null ){
                p_archivo.add(m1_sb1_configuracion);
                p_archivo.setBorder(border);
            }else{
                p_archivo.remove(m1_sb1_configuracion);
                p_archivo.setBorder(null);
            }    
        }
        if (e.getSource() == m2_catalogos ){
            if (p_catalogos.getBorder() == null ){
                p_catalogos.add(m2_sb1_marcas);
                p_catalogos.add(m2_sb2_tiposart);
                p_catalogos.add(m2_sb3_ubicaciones);
                p_catalogos.setBorder(border);
            }else{
                p_catalogos.remove(m2_sb1_marcas);
                p_catalogos.remove(m2_sb2_tiposart);
                p_catalogos.remove(m2_sb3_ubicaciones);
                p_catalogos.setBorder(null);
            }    
        }}
        public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == m1_archivo ){
            m1_archivo.setBackground(color_bentered); 
            m1_archivo.setForeground(color_fentered);
        }
        if (e.getSource() == m2_catalogos ){
            m2_catalogos.setBackground(color_bentered); 
            m2_catalogos.setForeground(color_fentered);
        }
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == m1_archivo ){
            m1_archivo.setBackground(color_fondo); 
            m1_archivo.setForeground(color_fuente);
        }
        if (e.getSource() == m2_catalogos ){
            m2_catalogos.setBackground(color_fondo); 
            m2_catalogos.setForeground(color_fuente);
        }
    }   
}   
     
}
