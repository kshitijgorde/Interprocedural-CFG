// 
// Decompiled by Procyon v0.5.30
// 

package reloj;

import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import javax.swing.JComponent;
import java.awt.Cursor;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import javax.swing.TransferHandler;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JFrame;

public class CajaControles2 extends JFrame
{
    private JApplet8 a;
    private int[] sel1;
    private String ayudaTablero;
    private Color colorFondo;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton15;
    private JButton jButton16;
    private JButton jButton17;
    private JButton jButton18;
    private JButton jButton2;
    private JButton jButton20;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel25;
    private JLabel jLabel26;
    private JLabel jLabel27;
    private JLabel jLabel28;
    private JLabel jLabel29;
    private JLabel jLabel3;
    private JLabel jLabel30;
    private JLabel jLabel31;
    private JLabel jLabel32;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel13;
    private JPanel jPanel14;
    private JPanel jPanel15;
    private JPanel jPanel16;
    private JPanel jPanel17;
    private JPanel jPanel18;
    private JPanel jPanel19;
    private JPanel jPanel2;
    private JPanel jPanel20;
    private JPanel jPanel21;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel8;
    private JPanel jPanel9;
    
    public CajaControles2(final JApplet8 applet) {
        super("Selecci\u00f3n de Indicadores C\u00edclicos");
        this.sel1 = new int[16];
        this.colorFondo = new Color(236, 233, 216);
        this.ayudaTablero = "Las gr\u00e1ficas peque\u00f1as ubicadas a la izquierda de la lista\nse\u00f1alan la selecci\u00f3n actual de gr\u00e1ficas que aparecen en\nla pantalla principal. Si se desea cambiar la selecci\u00f3n,\narrastre con el mouse las gr\u00e1ficas peque\u00f1as hacia el nombre\ndel indicador cuyo componente c\u00edclico desee visualizar.\n\n";
        this.a = applet;
        this.sel1[0] = 1;
        this.sel1[1] = 1;
        this.sel1[2] = 1;
        this.sel1[3] = 0;
        this.sel1[4] = 0;
        this.sel1[5] = 0;
        this.sel1[6] = 0;
        this.sel1[7] = 1;
        this.sel1[8] = 0;
        this.sel1[9] = 0;
        this.sel1[10] = 0;
        this.sel1[11] = 0;
        this.sel1[12] = 0;
        this.sel1[13] = 0;
        this.sel1[14] = 1;
        this.sel1[15] = 1;
        this.pack();
        this.setResizable(false);
        this.setLocation(100, 50);
        this.initComponents();
        this.setVisible(false);
        this.jLabel9.setTransferHandler(new DragTransferHandler("text", 1, this));
        this.jLabel9.addMouseListener(new DragMouseAdapter(1, this));
        this.jLabel10.setTransferHandler(new DragTransferHandler("text", 2, this));
        this.jLabel10.addMouseListener(new DragMouseAdapter(2, this));
        this.jLabel11.setTransferHandler(new DragTransferHandler("text", 3, this));
        this.jLabel11.addMouseListener(new DragMouseAdapter(3, this));
        this.jLabel12.setTransferHandler(new DragTransferHandler("text", 4, this));
        this.jLabel12.addMouseListener(new DragMouseAdapter(4, this));
        this.jLabel13.setTransferHandler(new DragTransferHandler("text", 5, this));
        this.jLabel13.addMouseListener(new DragMouseAdapter(5, this));
        this.jLabel14.setTransferHandler(new DragTransferHandler("text", 6, this));
        this.jLabel14.addMouseListener(new DragMouseAdapter(6, this));
        this.jLabel15.setTransferHandler(new DragTransferHandler("text", 7, this));
        this.jLabel15.addMouseListener(new DragMouseAdapter(7, this));
        this.jLabel16.setTransferHandler(new DragTransferHandler("text", 8, this));
        this.jLabel16.addMouseListener(new DragMouseAdapter(8, this));
        this.jLabel17.setTransferHandler(new DragTransferHandler("text", 9, this));
        this.jLabel17.addMouseListener(new DragMouseAdapter(9, this));
        this.jLabel19.setTransferHandler(new DragTransferHandler("text", 10, this));
        this.jLabel19.addMouseListener(new DragMouseAdapter(10, this));
        this.jLabel21.setTransferHandler(new DragTransferHandler("text", 11, this));
        this.jLabel21.addMouseListener(new DragMouseAdapter(11, this));
        this.jLabel23.setTransferHandler(new DragTransferHandler("text", 12, this));
        this.jLabel23.addMouseListener(new DragMouseAdapter(12, this));
        this.jLabel25.setTransferHandler(new DragTransferHandler("text", 13, this));
        this.jLabel25.addMouseListener(new DragMouseAdapter(13, this));
        this.jLabel27.setTransferHandler(new DragTransferHandler("text", 14, this));
        this.jLabel27.addMouseListener(new DragMouseAdapter(14, this));
        this.jLabel29.setTransferHandler(new DragTransferHandler("text", 15, this));
        this.jLabel29.addMouseListener(new DragMouseAdapter(15, this));
        this.jLabel31.setTransferHandler(new DragTransferHandler("text", 16, this));
        this.jLabel31.addMouseListener(new DragMouseAdapter(16, this));
        this.jLabel1.setTransferHandler(new DragTransferHandler("text", 1, this));
        this.jLabel2.setTransferHandler(new DragTransferHandler("text", 2, this));
        this.jLabel3.setTransferHandler(new DragTransferHandler("text", 3, this));
        this.jLabel4.setTransferHandler(new DragTransferHandler("text", 4, this));
        this.jLabel5.setTransferHandler(new DragTransferHandler("text", 5, this));
        this.jLabel6.setTransferHandler(new DragTransferHandler("text", 6, this));
        this.jLabel7.setTransferHandler(new DragTransferHandler("text", 7, this));
        this.jLabel8.setTransferHandler(new DragTransferHandler("text", 8, this));
        this.jLabel18.setTransferHandler(new DragTransferHandler("text", 9, this));
        this.jLabel20.setTransferHandler(new DragTransferHandler("text", 10, this));
        this.jLabel22.setTransferHandler(new DragTransferHandler("text", 11, this));
        this.jLabel24.setTransferHandler(new DragTransferHandler("text", 12, this));
        this.jLabel26.setTransferHandler(new DragTransferHandler("text", 13, this));
        this.jLabel28.setTransferHandler(new DragTransferHandler("text", 14, this));
        this.jLabel30.setTransferHandler(new DragTransferHandler("text", 15, this));
        this.jLabel32.setTransferHandler(new DragTransferHandler("text", 16, this));
        this.jLabel9.setBackground(new Color(0, 0, 0));
        this.jLabel10.setBackground(new Color(0, 0, 0));
        this.jLabel11.setBackground(new Color(0, 0, 0));
        this.jLabel12.setBackground(this.colorFondo);
        this.jLabel13.setBackground(this.colorFondo);
        this.jLabel14.setBackground(this.colorFondo);
        this.jLabel15.setBackground(this.colorFondo);
        this.jLabel16.setBackground(new Color(0, 0, 0));
        this.jLabel17.setBackground(this.colorFondo);
        this.jLabel19.setBackground(this.colorFondo);
        this.jLabel21.setBackground(this.colorFondo);
        this.jLabel23.setBackground(this.colorFondo);
        this.jLabel25.setBackground(this.colorFondo);
        this.jLabel27.setBackground(this.colorFondo);
        this.jLabel29.setBackground(new Color(0, 0, 0));
        this.jLabel31.setBackground(new Color(0, 0, 0));
        this.jPanel18.setBackground(this.colorFondo);
        this.jPanel19.setBackground(this.colorFondo);
        this.jPanel20.setBackground(this.colorFondo);
        this.jPanel21.setBackground(this.colorFondo);
        this.jPanel11.setBackground(this.colorFondo);
        this.jPanel13.setBackground(this.colorFondo);
        this.jPanel14.setBackground(this.colorFondo);
        this.jPanel15.setBackground(this.colorFondo);
        this.jPanel16.setBackground(this.colorFondo);
        this.jPanel17.setBackground(this.colorFondo);
        this.jPanel4.setBackground(this.colorFondo);
        this.jPanel5.setBackground(this.colorFondo);
        this.jPanel6.setBackground(this.colorFondo);
        this.jPanel8.setBackground(this.colorFondo);
        this.jPanel9.setBackground(this.colorFondo);
        this.jPanel10.setBackground(this.colorFondo);
        this.addWindowListener(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent we) {
                CajaControles2.this.a.appletActivado = true;
                int j = -1;
                for (int i = 0; i < 16; ++i) {
                    if (CajaControles2.this.sel1[i] == 1) {
                        ++j;
                        CajaControles2.this.a.sts2[j] = i + 1;
                    }
                }
            }
        });
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jPanel18 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel9 = new JLabel();
        this.jButton10 = new JButton();
        this.jPanel19 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jButton11 = new JButton();
        this.jPanel20 = new JPanel();
        this.jLabel3 = new JLabel();
        this.jLabel11 = new JLabel();
        this.jButton12 = new JButton();
        this.jPanel21 = new JPanel();
        this.jLabel4 = new JLabel();
        this.jLabel12 = new JLabel();
        this.jButton13 = new JButton();
        this.jPanel11 = new JPanel();
        this.jLabel5 = new JLabel();
        this.jLabel13 = new JLabel();
        this.jButton14 = new JButton();
        this.jPanel13 = new JPanel();
        this.jLabel6 = new JLabel();
        this.jButton15 = new JButton();
        this.jLabel14 = new JLabel();
        this.jPanel14 = new JPanel();
        this.jLabel7 = new JLabel();
        this.jLabel15 = new JLabel();
        this.jButton16 = new JButton();
        this.jButton20 = new JButton();
        this.jButton2 = new JButton();
        this.jPanel2 = new JPanel();
        this.jPanel15 = new JPanel();
        this.jLabel8 = new JLabel();
        this.jLabel16 = new JLabel();
        this.jButton17 = new JButton();
        this.jPanel16 = new JPanel();
        this.jLabel18 = new JLabel();
        this.jLabel17 = new JLabel();
        this.jButton18 = new JButton();
        this.jPanel17 = new JPanel();
        this.jLabel20 = new JLabel();
        this.jLabel19 = new JLabel();
        this.jButton9 = new JButton();
        this.jPanel4 = new JPanel();
        this.jLabel22 = new JLabel();
        this.jLabel21 = new JLabel();
        this.jButton8 = new JButton();
        this.jPanel5 = new JPanel();
        this.jLabel24 = new JLabel();
        this.jLabel23 = new JLabel();
        this.jButton7 = new JButton();
        this.jPanel6 = new JPanel();
        this.jLabel26 = new JLabel();
        this.jLabel25 = new JLabel();
        this.jButton6 = new JButton();
        this.jPanel8 = new JPanel();
        this.jLabel28 = new JLabel();
        this.jLabel27 = new JLabel();
        this.jButton5 = new JButton();
        this.jPanel3 = new JPanel();
        this.jPanel9 = new JPanel();
        this.jLabel30 = new JLabel();
        this.jLabel29 = new JLabel();
        this.jButton4 = new JButton();
        this.jPanel10 = new JPanel();
        this.jLabel32 = new JLabel();
        this.jLabel31 = new JLabel();
        this.jButton3 = new JButton();
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "INDICADOR COINCIDENTE Y SUS COMPONENTES", 0, 0, new Font("Tahoma", 1, 12)));
        this.jPanel18.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel1.setText("   C. Indicador Coincidente");
        this.jLabel1.setDoubleBuffered(true);
        this.jLabel1.setPreferredSize(new Dimension(290, 25));
        this.jLabel9.setBackground(new Color(0, 0, 0));
        this.jLabel9.setHorizontalAlignment(2);
        this.jLabel9.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel9.setDoubleBuffered(true);
        this.jLabel9.setOpaque(true);
        this.jButton10.setText("?");
        this.jButton10.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton10MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton10MouseExited(evt);
            }
        });
        this.jButton10.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton10ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel18Layout = new GroupLayout(this.jPanel18);
        this.jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(jPanel18Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.jLabel9, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel1, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton10)));
        jPanel18Layout.setVerticalGroup(jPanel18Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addGroup(jPanel18Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton10, -2, 17, -2).addComponent(this.jLabel1, -1, 17, 32767)).addComponent(this.jLabel9, -2, 17, -2)).addContainerGap()));
        this.jPanel19.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel2.setText(" C1. Indicador de la Actividad Econ\u00f3mica Mensual");
        this.jLabel2.setDoubleBuffered(true);
        this.jLabel2.setPreferredSize(new Dimension(290, 25));
        this.jLabel10.setBackground(new Color(0, 0, 0));
        this.jLabel10.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel10.setDoubleBuffered(true);
        this.jLabel10.setOpaque(true);
        this.jButton11.setText("?");
        this.jButton11.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton11MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton11MouseExited(evt);
            }
        });
        this.jButton11.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton11ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel19Layout = new GroupLayout(this.jPanel19);
        this.jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(jPanel19Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel19Layout.createSequentialGroup().addComponent(this.jLabel10, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton11)));
        jPanel19Layout.setVerticalGroup(jPanel19Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel19Layout.createSequentialGroup().addGroup(jPanel19Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel19Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton11, -2, 17, -2).addComponent(this.jLabel2, -1, 17, 32767)).addComponent(this.jLabel10, -2, 17, -2)).addContainerGap()));
        this.jPanel20.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel20.setPreferredSize(new Dimension(392, 27));
        this.jLabel3.setText(" C2. Indicador de la Actividad Industrial");
        this.jLabel3.setDoubleBuffered(true);
        this.jLabel3.setPreferredSize(new Dimension(290, 25));
        this.jLabel11.setBackground(new Color(0, 0, 0));
        this.jLabel11.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel11.setDoubleBuffered(true);
        this.jLabel11.setOpaque(true);
        this.jButton12.setText("?");
        this.jButton12.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton12MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton12MouseExited(evt);
            }
        });
        this.jButton12.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton12ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel20Layout = new GroupLayout(this.jPanel20);
        this.jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(jPanel20Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel20Layout.createSequentialGroup().addComponent(this.jLabel11, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel3, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton12)));
        jPanel20Layout.setVerticalGroup(jPanel20Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel20Layout.createSequentialGroup().addGroup(jPanel20Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel20Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton12, -2, 17, -2).addComponent(this.jLabel3, -1, 17, 32767)).addComponent(this.jLabel11, -2, 17, -2)).addContainerGap()));
        this.jPanel21.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel21.setPreferredSize(new Dimension(401, 27));
        this.jLabel4.setText(" C3. \u00cdndice de Ventas Netas al por menor en los Establecimientos Comerciales");
        this.jLabel4.setDoubleBuffered(true);
        this.jLabel4.setPreferredSize(new Dimension(290, 25));
        this.jLabel12.setDoubleBuffered(true);
        this.jLabel12.setOpaque(true);
        this.jButton13.setText("?");
        this.jButton13.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton13MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton13MouseExited(evt);
            }
        });
        this.jButton13.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton13ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel21Layout = new GroupLayout(this.jPanel21);
        this.jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(jPanel21Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel21Layout.createSequentialGroup().addComponent(this.jLabel12, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton13)));
        jPanel21Layout.setVerticalGroup(jPanel21Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel21Layout.createSequentialGroup().addGroup(jPanel21Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel21Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton13, -2, 17, -2).addComponent(this.jLabel4, -1, 17, 32767)).addComponent(this.jLabel12, -2, 17, -2)).addContainerGap()));
        this.jPanel11.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel11.setPreferredSize(new Dimension(391, 27));
        this.jLabel5.setText(" C4. N\u00famero de Asegurados Permanentes en el IMSS");
        this.jLabel5.setDoubleBuffered(true);
        this.jLabel5.setPreferredSize(new Dimension(290, 25));
        this.jLabel13.setDoubleBuffered(true);
        this.jLabel13.setOpaque(true);
        this.jButton14.setText("?");
        this.jButton14.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton14MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton14MouseExited(evt);
            }
        });
        this.jButton14.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton14ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel11Layout = new GroupLayout(this.jPanel11);
        this.jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addComponent(this.jLabel13, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton14)));
        jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton14, -2, 17, -2).addComponent(this.jLabel5, -2, 17, -2)).addComponent(this.jLabel13, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel13.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel13.setPreferredSize(new Dimension(391, 27));
        this.jLabel6.setText(" C5. Tasa de Desocupaci\u00f3n Urbana");
        this.jLabel6.setDoubleBuffered(true);
        this.jLabel6.setPreferredSize(new Dimension(290, 25));
        this.jButton15.setText("?");
        this.jButton15.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton15MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton15MouseExited(evt);
            }
        });
        this.jButton15.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton15ActionPerformed(evt);
            }
        });
        this.jLabel14.setDoubleBuffered(true);
        this.jLabel14.setOpaque(true);
        final GroupLayout jPanel13Layout = new GroupLayout(this.jPanel13);
        this.jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addComponent(this.jLabel14, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton15)));
        jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton15, -2, 17, -2).addComponent(this.jLabel6, -1, 17, 32767)).addComponent(this.jLabel14, -2, 17, -2)).addContainerGap()));
        this.jPanel14.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel7.setText(" C6. Importaciones Totales");
        this.jLabel7.setDoubleBuffered(true);
        this.jLabel7.setPreferredSize(new Dimension(290, 25));
        this.jLabel15.setDoubleBuffered(true);
        this.jLabel15.setOpaque(true);
        this.jButton16.setText("?");
        this.jButton16.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton16MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton16MouseExited(evt);
            }
        });
        this.jButton16.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton16ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel14Layout = new GroupLayout(this.jPanel14);
        this.jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel14Layout.createSequentialGroup().addComponent(this.jLabel15, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton16)));
        jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel14Layout.createSequentialGroup().addGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton16, -2, 17, -2).addComponent(this.jLabel7, -2, 17, -2)).addComponent(this.jLabel15, -2, 17, -2)).addContainerGap(-1, 32767)));
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel11, -1, 489, 32767).addComponent(this.jPanel21, -1, 489, 32767).addComponent(this.jPanel19, -1, -1, 32767).addComponent(this.jPanel18, -1, -1, 32767).addComponent(this.jPanel20, -1, 489, 32767).addComponent(this.jPanel13, GroupLayout.Alignment.TRAILING, -1, 489, 32767).addComponent(this.jPanel14, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jPanel18, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel19, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel20, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel21, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel11, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel13, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel14, -2, 19, -2)));
        this.jButton20.setText("Ayuda");
        this.jButton20.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton20MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton20MouseExited(evt);
            }
        });
        this.jButton20.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton20ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Regresar al Reloj");
        this.jButton2.setDoubleBuffered(true);
        this.jButton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton2MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton2MouseExited(evt);
            }
        });
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton2ActionPerformed(evt);
            }
        });
        this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "INDICADOR ADELANTADO Y SUS COMPONENTES", 0, 0, new Font("Tahoma", 1, 12)));
        this.jPanel15.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel8.setText("   A. Indicador Adelantado  o/");
        this.jLabel8.setDoubleBuffered(true);
        this.jLabel8.setPreferredSize(new Dimension(290, 25));
        this.jLabel16.setBackground(new Color(0, 0, 0));
        this.jLabel16.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel16.setDoubleBuffered(true);
        this.jLabel16.setOpaque(true);
        this.jButton17.setText("?");
        this.jButton17.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton17MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton17MouseExited(evt);
            }
        });
        this.jButton17.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton17ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel15Layout = new GroupLayout(this.jPanel15);
        this.jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(jPanel15Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel15Layout.createSequentialGroup().addComponent(this.jLabel16, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel8, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton17)));
        jPanel15Layout.setVerticalGroup(jPanel15Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel15Layout.createSequentialGroup().addGroup(jPanel15Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel15Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton17, -2, 17, -2).addComponent(this.jLabel8, -2, 17, -2)).addComponent(this.jLabel16, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel16.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel18.setText(" A1. Tendencia del Empleo en las Manufacturas");
        this.jLabel18.setDoubleBuffered(true);
        this.jLabel18.setPreferredSize(new Dimension(290, 25));
        this.jLabel17.setDoubleBuffered(true);
        this.jLabel17.setOpaque(true);
        this.jButton18.setText("?");
        this.jButton18.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton18MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton18MouseExited(evt);
            }
        });
        this.jButton18.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton18ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel16Layout = new GroupLayout(this.jPanel16);
        this.jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(jPanel16Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel16Layout.createSequentialGroup().addComponent(this.jLabel17, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel18, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton18)));
        jPanel16Layout.setVerticalGroup(jPanel16Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel16Layout.createSequentialGroup().addGroup(jPanel16Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel16Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton18, -2, 17, -2).addComponent(this.jLabel18, -2, 17, -2)).addComponent(this.jLabel17, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel17.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel20.setText(" A2. Exportaciones no Petroleras");
        this.jLabel20.setDoubleBuffered(true);
        this.jLabel20.setPreferredSize(new Dimension(290, 25));
        this.jLabel19.setDoubleBuffered(true);
        this.jLabel19.setMaximumSize(new Dimension(25, 25));
        this.jLabel19.setMinimumSize(new Dimension(25, 25));
        this.jLabel19.setOpaque(true);
        this.jLabel19.setPreferredSize(new Dimension(25, 25));
        this.jButton9.setText("?");
        this.jButton9.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton9MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton9MouseExited(evt);
            }
        });
        this.jButton9.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton9ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel17Layout = new GroupLayout(this.jPanel17);
        this.jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(jPanel17Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel17Layout.createSequentialGroup().addComponent(this.jLabel19, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel20, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton9)));
        jPanel17Layout.setVerticalGroup(jPanel17Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel17Layout.createSequentialGroup().addGroup(jPanel17Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton9, -2, 17, -2).addComponent(this.jLabel20, -2, 17, -2)).addComponent(this.jLabel19, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel4.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel4.setPreferredSize(new Dimension(401, 27));
        this.jLabel22.setText(" A3. \u00cdndice de Precios y Cotizaciones de la Bolsa Mexicana de Valores en t\u00e9rminos reales");
        this.jLabel22.setDoubleBuffered(true);
        this.jLabel22.setPreferredSize(new Dimension(290, 25));
        this.jLabel21.setDoubleBuffered(true);
        this.jLabel21.setOpaque(true);
        this.jButton8.setText("?");
        this.jButton8.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton8MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton8MouseExited(evt);
            }
        });
        this.jButton8.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton8ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
        this.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jLabel21, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel22, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton8)));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton8, -2, 17, -2).addComponent(this.jLabel22, -1, 17, 32767)).addComponent(this.jLabel21, -2, 17, -2)).addContainerGap()));
        this.jPanel5.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel5.setPreferredSize(new Dimension(401, 27));
        this.jLabel24.setText(" A4. Tipo de Cambio Real  o/");
        this.jLabel24.setDoubleBuffered(true);
        this.jLabel24.setPreferredSize(new Dimension(290, 25));
        this.jLabel23.setDoubleBuffered(true);
        this.jLabel23.setOpaque(true);
        this.jButton7.setText("?");
        this.jButton7.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton7MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton7MouseExited(evt);
            }
        });
        this.jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton7ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jLabel23, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel24, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton7)));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton7, -2, 17, -2).addComponent(this.jLabel24, -2, 17, -2)).addComponent(this.jLabel23, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel6.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel6.setPreferredSize(new Dimension(401, 27));
        this.jLabel26.setText(" A5. Tasa de Inter\u00e9s Interbancaria de Equilibrio");
        this.jLabel26.setDoubleBuffered(true);
        this.jLabel26.setPreferredSize(new Dimension(290, 25));
        this.jLabel25.setDoubleBuffered(true);
        this.jLabel25.setOpaque(true);
        this.jButton6.setText("?");
        this.jButton6.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton6MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton6MouseExited(evt);
            }
        });
        this.jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton6ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
        this.jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jLabel25, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel26, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton6)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton6, -2, 17, -2).addComponent(this.jLabel26, -1, 17, 32767)).addComponent(this.jLabel25, -2, 17, -2)).addContainerGap()));
        this.jPanel8.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel8.setPreferredSize(new Dimension(401, 27));
        this.jLabel28.setText(" A6. \u00cdndice Standard & Poor's 500 (\u00edndice burs\u00e1til de Estados Unidos) ");
        this.jLabel28.setDoubleBuffered(true);
        this.jLabel27.setDoubleBuffered(true);
        this.jLabel27.setMaximumSize(new Dimension(25, 25));
        this.jLabel27.setMinimumSize(new Dimension(25, 25));
        this.jLabel27.setOpaque(true);
        this.jLabel27.setPreferredSize(new Dimension(25, 25));
        this.jButton5.setText("?");
        this.jButton5.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton5MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton5MouseExited(evt);
            }
        });
        this.jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton5ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
        this.jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jLabel27, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel28, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton5)));
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton5, -2, 17, -2).addComponent(this.jLabel28, -1, 17, 32767)).addComponent(this.jLabel27, -2, 17, -2)).addContainerGap()));
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel15, -1, -1, 32767).addComponent(this.jPanel16, -1, -1, 32767).addComponent(this.jPanel17, -1, -1, 32767).addComponent(this.jPanel5, -1, 489, 32767).addComponent(this.jPanel4, -1, 489, 32767).addComponent(this.jPanel6, -1, 489, 32767).addComponent(this.jPanel8, -1, 489, 32767)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel15, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel16, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel17, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel4, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel5, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel6, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel8, -2, 19, -2)));
        this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "OTROS INDICADORES", 0, 0, new Font("Tahoma", 1, 12)));
        this.jPanel9.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jLabel30.setText(" IP. \u00cdndice de Confianza del Productor");
        this.jLabel30.setDoubleBuffered(true);
        this.jLabel29.setBackground(new Color(0, 0, 0));
        this.jLabel29.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel29.setDoubleBuffered(true);
        this.jLabel29.setOpaque(true);
        this.jButton4.setText("?");
        this.jButton4.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton4MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton4MouseExited(evt);
            }
        });
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton4ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
        this.jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.jLabel29, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel30, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton4)));
        jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton4, -2, 17, -2).addComponent(this.jLabel30, -2, 17, -2)).addComponent(this.jLabel29, -2, 17, -2)).addContainerGap(-1, 32767)));
        this.jPanel10.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel10.setPreferredSize(new Dimension(392, 27));
        this.jLabel32.setText(" IC. \u00cdndice de Confianza del Consumidor");
        this.jLabel32.setDoubleBuffered(true);
        this.jLabel31.setBackground(new Color(0, 0, 0));
        this.jLabel31.setIcon(new ImageIcon(this.a.imgCiclo));
        this.jLabel31.setDoubleBuffered(true);
        this.jLabel31.setOpaque(true);
        this.jButton3.setText("?");
        this.jButton3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                CajaControles2.this.jButton3MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                CajaControles2.this.jButton3MouseExited(evt);
            }
        });
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CajaControles2.this.jButton3ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
        this.jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jLabel31, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel32, -1, 422, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton3)));
        jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton3, -2, 17, -2).addComponent(this.jLabel32, -1, 17, 32767)).addComponent(this.jLabel31, -2, 17, -2)).addContainerGap()));
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jPanel10, GroupLayout.Alignment.LEADING, -1, 489, 32767).addComponent(this.jPanel9, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jPanel9, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel10, -2, 19, -2)));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel3, 0, -1, 32767).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jButton20, GroupLayout.Alignment.TRAILING).addComponent(this.jButton2)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(15, 15, 15).addComponent(this.jButton20).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void jButton10ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[1] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[1]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[1]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda01.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[0], 1);
    }
    
    private void jButton11ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[2] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[2]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[2]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda02.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[1], 1);
    }
    
    private void jButton12ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[3] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[3]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[3]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda03.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[2], 1);
    }
    
    private void jButton13ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[4] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[4]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[4]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda04.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[3], 1);
    }
    
    private void jButton20ActionPerformed(final ActionEvent evt) {
        final JTextArea textArea = new JTextArea(7, 32);
        textArea.setText(this.ayudaTablero);
        textArea.setEditable(false);
        textArea.moveCaretPosition(1);
        final JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Selecci\u00f3n de Componentes C\u00edclicos", 1);
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
        this.a.appletActivado = true;
        int j = -1;
        for (int i = 0; i < 16; ++i) {
            if (this.sel1[i] == 1) {
                ++j;
                this.a.sts2[j] = i + 1;
            }
        }
    }
    
    private void jButton14ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[5] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[5]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[5]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda05.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[4], 1);
    }
    
    private void jButton15ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[6] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[6]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[6]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda06.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[5], 1);
    }
    
    private void jButton16ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[7] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[7]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[7]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda07.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[6], 1);
    }
    
    private void jButton17ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[8] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[8]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[8]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda08.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[7], 1);
    }
    
    private void jButton18ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[9] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[9]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[9]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda09.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[8], 1);
    }
    
    private void jButton9ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[10] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[10]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[10]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda10.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[9], 1);
    }
    
    private void jButton8ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[11] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[11]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[11]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda11.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[10], 1);
    }
    
    private void jButton7ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[12] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[12]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[12]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda12.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[11], 1);
    }
    
    private void jButton6ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[13] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[13]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[13]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda13.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[12], 1);
    }
    
    private void jButton5ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[14] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[14]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[14]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda14.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[13], 1);
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[15] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[15]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[15]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda15.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[14], 1);
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (this.a.mensajeAyuda[16] != null) {
            try {
                editorPane.setPage(this.a.mensajeAyuda[16]);
            }
            catch (IOException e) {
                System.out.println("Intento de leer un URL defectuoso: " + this.a.mensajeAyuda[16]);
                this.a.stop();
            }
        }
        else {
            System.out.println("No se puede encontrar el archivo: Ayuda16.html");
            this.a.stop();
        }
        final JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(327, 164));
        scrollPane.setMinimumSize(new Dimension(10, 10));
        JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.a.nombreCorto[15], 1);
    }
    
    private void jButton20MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton20MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton10MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton10MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton11MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton11MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton12MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton12MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton13MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton13MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton14MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton14MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton15MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton15MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton16MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton16MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton17MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton17MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton18MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton18MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton9MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton9MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton8MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton8MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton7MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton7MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton6MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton6MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton5MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton5MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton4MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton4MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton3MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton3MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton2MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton2MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private class DragMouseAdapter extends MouseAdapter
    {
        int id;
        CajaControles2 fr;
        
        DragMouseAdapter(final int i, final CajaControles2 f) {
            this.id = i;
            this.fr = f;
        }
        
        public void mousePressed(final MouseEvent e) {
            boolean puedeExportar = false;
            switch (this.id) {
                case 1: {
                    puedeExportar = this.fr.jLabel9.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 2: {
                    puedeExportar = this.fr.jLabel10.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 3: {
                    puedeExportar = this.fr.jLabel11.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 4: {
                    puedeExportar = this.fr.jLabel12.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 5: {
                    puedeExportar = this.fr.jLabel13.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 6: {
                    puedeExportar = this.fr.jLabel14.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 7: {
                    puedeExportar = this.fr.jLabel15.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 8: {
                    puedeExportar = this.fr.jLabel16.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 9: {
                    puedeExportar = this.fr.jLabel17.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 10: {
                    puedeExportar = this.fr.jLabel19.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 11: {
                    puedeExportar = this.fr.jLabel21.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 12: {
                    puedeExportar = this.fr.jLabel23.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 13: {
                    puedeExportar = this.fr.jLabel25.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 14: {
                    puedeExportar = this.fr.jLabel27.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 15: {
                    puedeExportar = this.fr.jLabel29.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 16: {
                    puedeExportar = this.fr.jLabel31.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
            }
            if (puedeExportar) {
                final JComponent c = (JComponent)e.getSource();
                final TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, 1);
            }
        }
    }
    
    private class DragTransferHandler extends TransferHandler
    {
        int id;
        CajaControles2 fr;
        
        DragTransferHandler(final String s, final int i, final CajaControles2 f) {
            super(s);
            this.id = i;
            this.fr = f;
        }
        
        public boolean canImport(final TransferSupport support) {
            boolean puede = false;
            switch (this.id) {
                case 1: {
                    puede = !this.fr.jLabel9.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 2: {
                    puede = !this.fr.jLabel10.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 3: {
                    puede = !this.fr.jLabel11.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 4: {
                    puede = !this.fr.jLabel12.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 5: {
                    puede = !this.fr.jLabel13.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 6: {
                    puede = !this.fr.jLabel14.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 7: {
                    puede = !this.fr.jLabel15.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 8: {
                    puede = !this.fr.jLabel16.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 9: {
                    puede = !this.fr.jLabel17.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 10: {
                    puede = !this.fr.jLabel19.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 11: {
                    puede = !this.fr.jLabel21.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 12: {
                    puede = !this.fr.jLabel23.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 13: {
                    puede = !this.fr.jLabel25.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 14: {
                    puede = !this.fr.jLabel27.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 15: {
                    puede = !this.fr.jLabel29.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
                case 16: {
                    puede = !this.fr.jLabel31.getBackground().equals(new Color(0, 0, 0));
                    break;
                }
            }
            return puede;
        }
        
        public void exportDone(final JComponent c, final Transferable t, final int action) {
            if (action == 1) {
                switch (this.id) {
                    case 1: {
                        this.fr.jLabel9.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel9.setIcon(null);
                        this.fr.sel1[0] = 0;
                        break;
                    }
                    case 2: {
                        this.fr.jLabel10.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel10.setIcon(null);
                        this.fr.sel1[1] = 0;
                        break;
                    }
                    case 3: {
                        this.fr.jLabel11.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel11.setIcon(null);
                        this.fr.sel1[2] = 0;
                        break;
                    }
                    case 4: {
                        this.fr.jLabel12.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel12.setIcon(null);
                        this.fr.sel1[3] = 0;
                        break;
                    }
                    case 5: {
                        this.fr.jLabel13.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel13.setIcon(null);
                        this.fr.sel1[4] = 0;
                        break;
                    }
                    case 6: {
                        this.fr.jLabel14.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel14.setIcon(null);
                        this.fr.sel1[5] = 0;
                        break;
                    }
                    case 7: {
                        this.fr.jLabel15.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel15.setIcon(null);
                        this.fr.sel1[6] = 0;
                        break;
                    }
                    case 8: {
                        this.fr.jLabel16.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel16.setIcon(null);
                        this.fr.sel1[7] = 0;
                        break;
                    }
                    case 9: {
                        this.fr.jLabel17.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel17.setIcon(null);
                        this.fr.sel1[8] = 0;
                        break;
                    }
                    case 10: {
                        this.fr.jLabel19.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel19.setIcon(null);
                        this.fr.sel1[9] = 0;
                        break;
                    }
                    case 11: {
                        this.fr.jLabel21.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel21.setIcon(null);
                        this.fr.sel1[10] = 0;
                        break;
                    }
                    case 12: {
                        this.fr.jLabel23.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel23.setIcon(null);
                        this.fr.sel1[11] = 0;
                        break;
                    }
                    case 13: {
                        this.fr.jLabel25.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel25.setIcon(null);
                        this.fr.sel1[12] = 0;
                        break;
                    }
                    case 14: {
                        this.fr.jLabel27.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel27.setIcon(null);
                        this.fr.sel1[13] = 0;
                        break;
                    }
                    case 15: {
                        this.fr.jLabel29.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel29.setIcon(null);
                        this.fr.sel1[14] = 0;
                        break;
                    }
                    case 16: {
                        this.fr.jLabel31.setBackground(CajaControles2.this.colorFondo);
                        this.fr.jLabel31.setIcon(null);
                        this.fr.sel1[15] = 0;
                        break;
                    }
                }
            }
        }
        
        public boolean importData(final TransferSupport support) {
            switch (this.id) {
                case 1: {
                    this.fr.sel1[0] = 1;
                    this.fr.jLabel9.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel9.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 2: {
                    this.fr.sel1[1] = 1;
                    this.fr.jLabel10.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel10.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 3: {
                    this.fr.sel1[2] = 1;
                    this.fr.jLabel11.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel11.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 4: {
                    this.fr.sel1[3] = 1;
                    this.fr.jLabel12.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel12.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 5: {
                    this.fr.sel1[4] = 1;
                    this.fr.jLabel13.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel13.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 6: {
                    this.fr.sel1[5] = 1;
                    this.fr.jLabel14.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel14.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 7: {
                    this.fr.sel1[6] = 1;
                    this.fr.jLabel15.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel15.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 8: {
                    this.fr.sel1[7] = 1;
                    this.fr.jLabel16.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel16.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 9: {
                    this.fr.sel1[8] = 1;
                    this.fr.jLabel17.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel17.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 10: {
                    this.fr.sel1[9] = 1;
                    this.fr.jLabel19.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel19.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 11: {
                    this.fr.sel1[10] = 1;
                    this.fr.jLabel21.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel21.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 12: {
                    this.fr.sel1[11] = 1;
                    this.fr.jLabel23.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel23.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 13: {
                    this.fr.sel1[12] = 1;
                    this.fr.jLabel25.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel25.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 14: {
                    this.fr.sel1[13] = 1;
                    this.fr.jLabel27.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel27.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 15: {
                    this.fr.sel1[14] = 1;
                    this.fr.jLabel29.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel29.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
                case 16: {
                    this.fr.sel1[15] = 1;
                    this.fr.jLabel31.setBackground(new Color(0, 0, 0));
                    this.fr.jLabel31.setIcon(new ImageIcon(CajaControles2.this.a.imgCiclo));
                    break;
                }
            }
            return true;
        }
    }
}
