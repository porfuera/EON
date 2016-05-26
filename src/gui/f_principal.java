/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.sun.awt.AWTUtilities; 
import java.awt.Shape; 
import java.awt.geom.RoundRectangle2D; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Fam
 */
public class f_principal extends JFrame{
    public Toolkit t = Toolkit.getDefaultToolkit();
    public Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    JLabel  barra_titulo,boton_salir,boton_minimizar,boton_bloquear,fondo;
    int bt_fondo_r,bt_fonfo_g,bt_fondo_b,bt_fuente_r,bt_fuente_g,bt_fuente_b,bt_letra_tamaño;
    ResourceBundle rb = ResourceBundle.getBundle("configuracion.diseño");
    Color   bt_color_fondo,bt_color_fuente;
    String  titulo_ventana ,bt_letra,btn_cerrar_inactivo,btn_cerrar_activo,btn_min_inactivo,btn_min_activo,btn_bloq_inactivo,btn_bloq_activo;
    String  btn_cerrar_tooltip,btn_min_tooltip,btn_bloq_tooltip;
    Font    fuente_titulo  ;
    m_principal m_principal;
    
    
    public f_principal(){
        setLayout(null);
        Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 30, 30); 
        AWTUtilities.setWindowShape(this, forma)  ; 
        diseño();
        propiedades();
        resize();
        this.add(boton_bloquear);
        this.add(boton_minimizar);
        this.add(boton_salir);
        this.add(m_principal);
        this.add(barra_titulo);
        this.add(fondo);
    }
    
  private abstract class MouseAdapter implements MouseListener {
      
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == boton_salir ){
            System.exit(0);
        }
        if (e.getSource() == boton_minimizar ){
            setExtendedState(ICONIFIED); 
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == boton_salir ){
        ImageIcon icon_salir=new ImageIcon(btn_cerrar_activo);
        Icon btn_salir = new ImageIcon(icon_salir.getImage().getScaledInstance(boton_salir.getWidth(), boton_salir.getHeight(), Image.SCALE_DEFAULT));
        boton_salir.setIcon(btn_salir);
        f_principal.this.repaint();
        boton_salir.setToolTipText(btn_cerrar_tooltip);}
        if (e.getSource() == boton_minimizar ){
        ImageIcon icon_min=new ImageIcon(btn_min_activo);
        Icon btn_min = new ImageIcon(icon_min.getImage().getScaledInstance(boton_minimizar.getWidth(), boton_minimizar.getHeight(), Image.SCALE_DEFAULT));
        boton_minimizar.setIcon(btn_min);
        f_principal.this.repaint();
        boton_minimizar.setToolTipText(btn_min_tooltip);}
        if (e.getSource() == boton_bloquear ){
        ImageIcon icon_bloq=new ImageIcon(btn_bloq_activo);
        Icon btn_bloq = new ImageIcon(icon_bloq.getImage().getScaledInstance(boton_bloquear.getWidth(), boton_bloquear.getHeight(), Image.SCALE_DEFAULT));
        boton_bloquear.setIcon(btn_bloq);
        f_principal.this.repaint();
        boton_bloquear.setToolTipText(btn_bloq_tooltip);}
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == boton_salir ){
        ImageIcon icon_salir=new ImageIcon(btn_cerrar_inactivo);
        Icon btn_salir = new ImageIcon(icon_salir.getImage().getScaledInstance(boton_salir.getWidth(), boton_salir.getHeight(), Image.SCALE_DEFAULT));
        boton_salir.setIcon(btn_salir);
        f_principal.this.repaint();}
        if (e.getSource() == boton_minimizar ){
        ImageIcon icon_min=new ImageIcon(btn_min_inactivo);
        Icon btn_min = new ImageIcon(icon_min.getImage().getScaledInstance(boton_minimizar.getWidth(), boton_minimizar.getHeight(), Image.SCALE_DEFAULT));
        boton_minimizar.setIcon(btn_min);
        f_principal.this.repaint();}
        if (e.getSource() == boton_bloquear ){
        ImageIcon icon_bloq=new ImageIcon(btn_bloq_inactivo);
        Icon btn_bloq = new ImageIcon(icon_bloq.getImage().getScaledInstance(boton_bloquear.getWidth(), boton_bloquear.getHeight(), Image.SCALE_DEFAULT));
        boton_bloquear.setIcon(btn_bloq);
        f_principal.this.repaint();}
    }
}
  public void diseño(){
        titulo_ventana = rb.getString("barratitulo");
        bt_fondo_r = Integer.parseInt(rb.getString("bt_fondo_r"));
        bt_fonfo_g = Integer.parseInt(rb.getString("bt_fondo_g"));
        bt_fondo_b = Integer.parseInt(rb.getString("bt_fondo_b"));
        bt_color_fondo = new Color(bt_fondo_r,bt_fonfo_g,bt_fondo_b);
        bt_fuente_r = Integer.parseInt(rb.getString("bt_fuente_r"));
        bt_fuente_g = Integer.parseInt(rb.getString("bt_fuente_g"));
        bt_fuente_b = Integer.parseInt(rb.getString("bt_fuente_b"));
        bt_color_fuente = new Color(bt_fuente_r,bt_fuente_g,bt_fuente_b);
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
  public void propiedades(){
        MouseAdapter raton=new MouseAdapter() {};
        barra_titulo    =   new JLabel();
        barra_titulo.setBackground(bt_color_fondo); 
        barra_titulo.setVisible(true);
        barra_titulo.setOpaque(true);
        barra_titulo.setText(titulo_ventana);
        barra_titulo.setForeground(bt_color_fuente); 
        barra_titulo.setFont(fuente_titulo);
        barra_titulo.setFocusable(false);
        m_principal=new m_principal();
        m_principal.setAlignmentX(SwingConstants.CENTER);
        boton_salir =     new JLabel();
        boton_salir.setVisible(true);
        boton_salir.setFocusable(false);
        ImageIcon icon_salir=new ImageIcon(btn_cerrar_inactivo);
        Icon btn_salir = new ImageIcon(icon_salir.getImage().getScaledInstance(boton_salir.getWidth(), boton_salir.getHeight(), Image.SCALE_DEFAULT));
        boton_salir.setIcon(btn_salir);
        boton_salir.setOpaque(true);
        boton_salir.setBackground(bt_color_fondo); 
        boton_salir.addMouseListener(raton);
        boton_minimizar =     new JLabel();
        boton_minimizar.setVisible(true);
        boton_minimizar.setFocusable(false);
        ImageIcon icon_min=new ImageIcon(btn_min_inactivo);
        Icon btn_min = new ImageIcon(icon_min.getImage().getScaledInstance(boton_minimizar.getWidth(), boton_minimizar.getHeight(), Image.SCALE_DEFAULT));
        boton_minimizar.setIcon(btn_min);
        boton_minimizar.setOpaque(true);
        boton_minimizar.setBackground(bt_color_fondo); 
        boton_minimizar.addMouseListener(raton);
        boton_bloquear =     new JLabel();
        boton_bloquear.setVisible(true);
        boton_bloquear.setFocusable(false);
        ImageIcon icon_bloq=new ImageIcon(btn_bloq_inactivo);
        Icon btn_bloq = new ImageIcon(icon_bloq.getImage().getScaledInstance(boton_bloquear.getWidth(), boton_bloquear.getHeight(), Image.SCALE_DEFAULT));
        boton_bloquear.setIcon(btn_bloq);
        boton_bloquear.setOpaque(true);
        boton_bloquear.setBackground(new Color(25,106,167)); 
        boton_bloquear.addMouseListener(raton);
        fondo    =   new JLabel();
        fondo.setBackground(new Color(195,227,251)); 
        fondo.setOpaque(true);
        fondo.setVisible(true);
        fondo.setBounds(0, 0, pantalla.width, pantalla.height);
  }
  public void resize(){
      barra_titulo.setBounds(0, 0, pantalla.width, 35);
      boton_salir.setBounds(pantalla.width - 35, 0, 30, 30);
      boton_minimizar.setBounds(pantalla.width - 70, 0, 30, 30);
      boton_bloquear.setBounds(pantalla.width - 105, 0, 30, 30);
      m_principal.setBounds(0, 35, m_principal.getWidth(), m_principal.getHeight());
  }
}
