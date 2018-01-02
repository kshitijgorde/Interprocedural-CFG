// 
// Decompiled by Procyon v0.5.30
// 

package reloj;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.io.IOException;
import javax.swing.JEditorPane;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import java.awt.EventQueue;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Image;
import java.net.URL;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JApplet;

public class JApplet8 extends JApplet implements Runnable
{
    private Thread runner;
    private boolean runnerSuspended;
    private boolean muevePeriodo;
    private boolean muestraNota;
    private int rapidez;
    private int numeroDeSegmentos;
    private int sentido;
    private int periodo;
    protected String[] nombreCorto;
    private String[] etiqueta;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private double semiRangoX;
    private double semiRangoY;
    private double rangoT;
    private double[] fY1;
    private double[] fM1;
    private double[] fY2;
    private double[] fM2;
    private double[] fY3;
    private double[] fM3;
    private double[] fY4;
    private double[] fM4;
    private double[] fY5;
    private double[] fM5;
    private double[] fY6;
    private double[] fM6;
    private double[] fY7;
    private double[] fM7;
    private double[] fY8;
    private double[] fM8;
    private double[] fY9;
    private double[] fM9;
    private double[] fY10;
    private double[] fM10;
    private double[] fY11;
    private double[] fM11;
    private double[] fY12;
    private double[] fM12;
    private double[] fY13;
    private double[] fM13;
    private double[] fY14;
    private double[] fM14;
    private double[] fY15;
    private double[] fM15;
    private double[] fY16;
    private double[] fM16;
    private int[] month1;
    private int[] year1;
    private int[] month2;
    private int[] year2;
    private int[] month3;
    private int[] year3;
    private int[] month4;
    private int[] year4;
    private int[] month5;
    private int[] year5;
    private int[] month6;
    private int[] year6;
    private int[] month7;
    private int[] year7;
    private int[] month8;
    private int[] year8;
    private int[] month9;
    private int[] year9;
    private int[] month10;
    private int[] year10;
    private int[] month11;
    private int[] year11;
    private int[] month12;
    private int[] year12;
    private int[] month13;
    private int[] year13;
    private int[] month14;
    private int[] year14;
    private int[] month15;
    private int[] year15;
    private int[] month16;
    private int[] year16;
    private double[][] fY;
    private double[][] fM;
    private int[] month;
    private int[] year;
    private double[][] xPos;
    private double[][] yPos;
    private Color[] colorPeriodo;
    private Color[] colorSegmento;
    private Color[] colorNumero;
    private Point mousePos;
    private StringBuffer buf;
    private String FileToRead;
    protected URL[] mensajeAyuda;
    private int recycle;
    protected boolean appletActivado;
    private CajaControles2 tablero;
    private int[] sts1;
    protected int[] sts2;
    private int[] o;
    public Image imgCiclo;
    private Image imgPlay;
    private Image imgPause;
    private Image imgPlayBack;
    private JButton botonPausa;
    private JButton botonPlay;
    private JButton botonPlayReverse;
    private JButton jButton1;
    private JButton jButton2;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox10;
    private JCheckBox jCheckBox11;
    private JCheckBox jCheckBox12;
    private JCheckBox jCheckBox13;
    private JCheckBox jCheckBox14;
    private JCheckBox jCheckBox15;
    private JCheckBox jCheckBox16;
    private JCheckBox jCheckBox17;
    private JCheckBox jCheckBox18;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JCheckBox jCheckBox4;
    private JCheckBox jCheckBox5;
    private JCheckBox jCheckBox6;
    private JCheckBox jCheckBox7;
    private JCheckBox jCheckBox8;
    private JCheckBox jCheckBox9;
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
    private JLabel jLabel27;
    private JLabel jLabel29;
    private JLabel jLabel30;
    private JLabel jLabel32;
    private JLabel jLabel33;
    private JLabel jLabel35;
    private JLabel jLabel36;
    private JLabel jLabel38;
    private JLabel jLabel39;
    private JLabel jLabel4;
    private JLabel jLabel41;
    private JLabel jLabel42;
    private JLabel jLabel44;
    private JLabel jLabel45;
    private JLabel jLabel47;
    private JLabel jLabel48;
    private JLabel jLabel5;
    private JLabel jLabel50;
    private JLabel jLabel51;
    private JLabel jLabel53;
    private JLabel jLabel54;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JSlider jSlider1;
    private JSpinner jSpinnerRapidez;
    private JSpinner jSpinnerRezagos;
    
    public JApplet8() {
        this.xPos = new double[16][25];
        this.yPos = new double[16][25];
        this.sts1 = new int[16];
        this.sts2 = new int[6];
        this.o = new int[16];
    }
    
    public void init() {
        final ParserDelegator workaround = new ParserDelegator();
        this.cargaDatosEnMemoria();
        this.inicializaParametros();
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    JApplet8.this.initComponents();
                }
            });
        }
        catch (Exception ex) {}
        this.tablero = new CajaControles2(this);
        this.runner = null;
        UIManager.put("ToolTip.background", new ColorUIResource(new Color(255, 255, 215)));
        ToolTipManager.sharedInstance().setDismissDelay(8000);
        final Hashtable<Object, Object> labelTable = new Hashtable<Object, Object>();
        for (int i = 0; i <= this.rangoT - 1.0; i += 60) {
            labelTable.put(new Integer(i), new JLabel("" + this.year[i]));
        }
        this.jSlider1.setLabelTable(labelTable);
    }
    
    public void start() {
        this.setSize(950, 480);
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runnerSuspended = false;
            this.runner.start();
        }
        else if (this.runnerSuspended) {
            this.runnerSuspended = false;
            synchronized (this) {
                this.notify();
            }
        }
    }
    
    public void stop() {
        this.runnerSuspended = true;
        this.tablero = null;
    }
    
    public void run() {
        try {
            while (true) {
                switch (this.sentido) {
                    case 0: {
                        for (int j = 0; j <= this.numeroDeSegmentos; ++j) {
                            for (int k = 0; k < 16; ++k) {
                                this.xPos[k][j] = this.fM[this.periodo - j][k];
                                this.yPos[k][j] = this.fY[this.periodo - j][k];
                            }
                        }
                        if (!this.jButton1.isEnabled() && this.appletActivado) {
                            this.botonPausa.setEnabled(true);
                            this.botonPlay.setEnabled(true);
                            this.botonPlayReverse.setEnabled(true);
                            this.jCheckBox7.setEnabled(true);
                            this.jSlider1.setEnabled(true);
                            this.jSpinnerRapidez.setEnabled(true);
                            this.jSpinnerRezagos.setEnabled(true);
                            this.jButton1.setEnabled(true);
                            this.jButton2.setEnabled(true);
                            this.jLabel19.setBackground(this.colorPeriodo[0]);
                            this.jLabel5.setBackground(this.colorPeriodo[1]);
                            this.jLabel4.setBackground(this.colorPeriodo[2]);
                            this.jLabel2.setBackground(this.colorPeriodo[3]);
                            this.jLabel15.setBackground(this.colorPeriodo[4]);
                            this.jLabel17.setBackground(this.colorPeriodo[5]);
                            this.jLabel20.setBackground(this.colorPeriodo[6]);
                            this.jLabel29.setBackground(this.colorPeriodo[7]);
                            this.jLabel32.setBackground(this.colorPeriodo[8]);
                            this.jLabel35.setBackground(this.colorPeriodo[9]);
                            this.jLabel38.setBackground(this.colorPeriodo[10]);
                            this.jLabel41.setBackground(this.colorPeriodo[11]);
                            this.jLabel44.setBackground(this.colorPeriodo[12]);
                            this.jLabel47.setBackground(this.colorPeriodo[13]);
                            this.jLabel50.setBackground(this.colorPeriodo[14]);
                            this.jLabel53.setBackground(this.colorPeriodo[15]);
                            this.jCheckBox2.setEnabled(true);
                            this.jCheckBox18.setEnabled(true);
                            this.jCheckBox3.setEnabled(true);
                            this.jCheckBox4.setEnabled(true);
                            this.jCheckBox5.setEnabled(true);
                            this.jCheckBox6.setEnabled(true);
                            this.jCheckBox8.setEnabled(true);
                            this.jCheckBox9.setEnabled(true);
                            this.jCheckBox10.setEnabled(true);
                            this.jCheckBox11.setEnabled(true);
                            this.jCheckBox12.setEnabled(true);
                            this.jCheckBox13.setEnabled(true);
                            this.jCheckBox14.setEnabled(true);
                            this.jCheckBox15.setEnabled(true);
                            this.jCheckBox16.setEnabled(true);
                            this.jCheckBox17.setEnabled(true);
                            this.jCheckBox1.setEnabled(true);
                        }
                        if (!this.appletActivado && this.botonPlay.isEnabled()) {
                            this.botonPausa.setEnabled(false);
                            this.botonPlay.setEnabled(false);
                            this.botonPlayReverse.setEnabled(false);
                            this.jCheckBox7.setEnabled(false);
                            this.jSlider1.setEnabled(false);
                            this.jSpinnerRapidez.setEnabled(false);
                            this.jSpinnerRezagos.setEnabled(false);
                            this.jButton1.setEnabled(false);
                            this.jButton2.setEnabled(false);
                            this.jLabel19.setBackground(Color.gray);
                            this.jLabel5.setBackground(Color.gray);
                            this.jLabel4.setBackground(Color.gray);
                            this.jLabel2.setBackground(Color.gray);
                            this.jLabel15.setBackground(Color.gray);
                            this.jLabel17.setBackground(Color.gray);
                            this.jLabel20.setBackground(Color.gray);
                            this.jLabel29.setBackground(Color.gray);
                            this.jLabel32.setBackground(Color.gray);
                            this.jLabel35.setBackground(Color.gray);
                            this.jLabel38.setBackground(Color.gray);
                            this.jLabel41.setBackground(Color.gray);
                            this.jLabel44.setBackground(Color.gray);
                            this.jLabel47.setBackground(Color.gray);
                            this.jLabel50.setBackground(Color.gray);
                            this.jLabel53.setBackground(Color.gray);
                            this.jCheckBox2.setEnabled(false);
                            this.jCheckBox18.setEnabled(false);
                            this.jCheckBox3.setEnabled(false);
                            this.jCheckBox4.setEnabled(false);
                            this.jCheckBox5.setEnabled(false);
                            this.jCheckBox6.setEnabled(false);
                            this.jCheckBox8.setEnabled(false);
                            this.jCheckBox9.setEnabled(false);
                            this.jCheckBox10.setEnabled(false);
                            this.jCheckBox11.setEnabled(false);
                            this.jCheckBox12.setEnabled(false);
                            this.jCheckBox13.setEnabled(false);
                            this.jCheckBox14.setEnabled(false);
                            this.jCheckBox15.setEnabled(false);
                            this.jCheckBox16.setEnabled(false);
                            this.jCheckBox17.setEnabled(false);
                            this.jCheckBox1.setEnabled(false);
                        }
                        try {
                            Thread.sleep(this.muevePeriodo ? 53L : 212L);
                        }
                        catch (InterruptedException ex) {}
                        this.repaint();
                        this.jPanel1.repaint();
                        this.jPanel2.repaint();
                        this.jPanel3.repaint();
                        break;
                    }
                    case 1: {
                        boolean salioDelCiclo = false;
                        for (int i = Math.max(this.periodo, this.numeroDeSegmentos); i < this.fY.length; ++i) {
                            this.jSlider1.setValue(i);
                            for (int l = 0; l <= this.numeroDeSegmentos; ++l) {
                                for (int m = 0; m < 16; ++m) {
                                    this.xPos[m][l] = this.fM[i - l][m];
                                    this.yPos[m][l] = this.fY[i - l][m];
                                }
                            }
                            try {
                                Thread.sleep(2010 - 103 * (this.rapidez - 1));
                            }
                            catch (InterruptedException ex2) {}
                            this.repaint();
                            this.jPanel1.repaint();
                            this.jPanel2.repaint();
                            this.jPanel3.repaint();
                            if (this.recycle == 0 && i == this.fY.length - 1) {
                                this.sentido = 0;
                                this.jSlider1.setEnabled(true);
                            }
                            if (this.sentido != 1) {
                                salioDelCiclo = true;
                                break;
                            }
                        }
                        if (!salioDelCiclo) {
                            this.periodo = 0;
                            break;
                        }
                        break;
                    }
                    case -1: {
                        boolean salioDelCiclo = false;
                        for (int i = Math.max(this.periodo, this.numeroDeSegmentos); i >= this.numeroDeSegmentos; --i) {
                            this.jSlider1.setValue(i);
                            for (int l = 0; l <= this.numeroDeSegmentos; ++l) {
                                for (int m = 0; m < 16; ++m) {
                                    this.xPos[m][l] = this.fM[i - l][m];
                                    this.yPos[m][l] = this.fY[i - l][m];
                                }
                            }
                            try {
                                Thread.sleep(2010 - 103 * (this.rapidez - 1));
                            }
                            catch (InterruptedException ex3) {}
                            this.repaint();
                            this.jPanel1.repaint();
                            this.jPanel2.repaint();
                            this.jPanel3.repaint();
                            if (this.recycle == 0 && i == this.numeroDeSegmentos) {
                                this.sentido = 0;
                                this.jSlider1.setEnabled(true);
                            }
                            if (this.sentido != -1) {
                                salioDelCiclo = true;
                                break;
                            }
                        }
                        if (!salioDelCiclo) {
                            this.periodo = this.fY.length - 1;
                            break;
                        }
                        break;
                    }
                }
                if (this.runnerSuspended) {
                    synchronized (this) {
                        while (this.runnerSuspended) {
                            this.wait();
                        }
                    }
                }
            }
        }
        catch (InterruptedException e) {
            this.stop();
        }
    }
    
    public void paint(final Graphics g) {
        final Image image = this.createImage(750, 410);
        final Graphics2D offG = (Graphics2D)image.getGraphics();
        final Rectangle2D cuadro = new Rectangle2D.Double(-200.0, -200.0, 810.0, 810.0);
        final float[] distribuciOn = { 0.15f, 1.0f };
        final Color[] grises = { Color.WHITE, Color.GRAY };
        final RadialGradientPaint cuadroGris = new RadialGradientPaint(cuadro, distribuciOn, grises, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        final Color[] colores1 = { Color.WHITE, Color.RED };
        final RadialGradientPaint cuadroGr1 = new RadialGradientPaint(cuadro, distribuciOn, colores1, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        if (this.appletActivado) {
            offG.setPaint(cuadroGr1);
        }
        else {
            offG.setPaint(cuadroGris);
        }
        offG.fillRect(4, 205, 200, 200);
        final Color[] colores2 = { Color.WHITE, Color.YELLOW };
        final RadialGradientPaint cuadroGr2 = new RadialGradientPaint(cuadro, distribuciOn, colores2, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        if (this.appletActivado) {
            offG.setPaint(cuadroGr2);
        }
        else {
            offG.setPaint(cuadroGris);
        }
        offG.fillRect(205, 205, 200, 200);
        final Color[] colores3 = { Color.WHITE, Color.GREEN };
        final RadialGradientPaint cuadroGr3 = new RadialGradientPaint(cuadro, distribuciOn, colores3, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        if (this.appletActivado) {
            offG.setPaint(cuadroGr3);
        }
        else {
            offG.setPaint(cuadroGris);
        }
        offG.fillRect(205, 4, 200, 200);
        final Color[] colores4 = { Color.WHITE, new Color(254, 102, 0) };
        final RadialGradientPaint cuadroGr4 = new RadialGradientPaint(cuadro, distribuciOn, colores4, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        if (this.appletActivado) {
            offG.setPaint(cuadroGr4);
        }
        else {
            offG.setPaint(cuadroGris);
        }
        offG.fillRect(4, 4, 200, 200);
        offG.setColor(Color.black);
        offG.drawLine(this.xT_A(100.0 - this.semiRangoX), this.yT_A(100.0), this.xT_A(100.0 + this.semiRangoX), this.yT_A(100.0));
        offG.drawLine(this.xT_A(100.0), this.yT_A(100.0 - this.semiRangoY), this.xT_A(100.0), this.yT_A(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial Black", 0, 15));
        offG.setColor(Color.black);
        offG.drawString("Expansi\u00f3n", this.xT_A(100.0 + 0.55 * this.semiRangoX), this.yT_A(100.0 + 0.93 * this.semiRangoY));
        offG.drawString("Desaceleraci\u00f3n", this.xT_A(100.0 - 0.97 * this.semiRangoX), this.yT_A(100.0 + 0.93 * this.semiRangoY));
        offG.drawString("Recesi\u00f3n", this.xT_A(100.0 - 0.97 * this.semiRangoX), this.yT_A(100.0 - 0.98 * this.semiRangoY));
        offG.drawString("Recuperaci\u00f3n", this.xT_A(100.0 + 0.41 * this.semiRangoX), this.yT_A(100.0 - 0.98 * this.semiRangoY));
        offG.setFont(new Font("Arial Black", 0, 34));
        offG.setColor(Color.black);
        offG.setComposite(AlphaComposite.getInstance(3, 0.5f));
        offG.drawString(this.nombreMes(this.month[this.periodo]) + "/" + this.year[this.periodo], this.xT_A(100.0 - 0.45 * this.semiRangoX), this.yT_A(100.0 + 0.7 * this.semiRangoY));
        offG.setComposite(AlphaComposite.getInstance(3, 1.0f));
        this.muestraNota = false;
        for (int s = 0; s < 16; ++s) {
            if (this.sts1[s] == 1 && this.yPos[s][0] < 0.0 && this.rangoT - this.periodo - 1.0 < 12.0) {
                this.muestraNota = true;
                break;
            }
        }
        if (this.muestraNota) {
            offG.setFont(new Font("Arial", 0, 12));
            offG.setColor(Color.black);
            offG.drawString("NOTA: Los indicadores se van actualizando conforme est\u00e1n", 45, 100);
            offG.drawString("disponibles debido a las diferentes fechas de difusi\u00f3n.", 45, 115);
        }
        offG.setStroke(new BasicStroke(3.0f));
        if (this.numeroDeSegmentos > 0) {
            for (int i = 0; i < this.numeroDeSegmentos; ++i) {
                for (int s2 = 0; s2 < 16; ++s2) {
                    if (this.sts1[this.o[s2]] == 1 && this.yPos[this.o[s2]][i] > 0.0 && this.yPos[this.o[s2]][i + 1] > 0.0 && this.yPos[this.o[s2]][0] > 0.0) {
                        if (this.appletActivado) {
                            offG.setColor(this.colorSegmento[this.o[s2]]);
                        }
                        else {
                            offG.setColor(Color.gray);
                        }
                        offG.drawLine(this.xT_A(this.FDEx(this.xPos[this.o[s2]][i])), this.yT_A(this.FDEy(this.yPos[this.o[s2]][i])), this.xT_A(this.FDEx(this.xPos[this.o[s2]][i + 1])), this.yT_A(this.FDEy(this.yPos[this.o[s2]][i + 1])));
                    }
                }
            }
        }
        offG.setStroke(new BasicStroke(1.0f));
        offG.setFont(new Font("Arial", 0, 13));
        for (int s = 0; s < 16; ++s) {
            if (this.sts1[this.o[s]] == 1 && this.yPos[this.o[s]][0] > 0.0) {
                if (this.appletActivado) {
                    offG.setColor(this.colorPeriodo[this.o[s]]);
                }
                else {
                    offG.setColor(Color.gray);
                }
                offG.fillRect(this.xT_A(this.FDEx(this.xPos[this.o[s]][0])) - 8, this.yT_A(this.FDEy(this.yPos[this.o[s]][0])) - 8, 17, 17);
                offG.setColor(this.colorNumero[this.o[s]]);
                if (this.o[s] == 0 || this.o[s] == 7) {
                    offG.drawString(this.etiqueta[this.o[s]], this.xT_A(this.FDEx(this.xPos[this.o[s]][0])) - 4, this.yT_A(this.FDEy(this.yPos[this.o[s]][0])) + 5);
                }
                else if (this.o[s] == 14 || this.o[s] == 15) {
                    offG.drawString(this.etiqueta[this.o[s]], this.xT_A(this.FDEx(this.xPos[this.o[s]][0])) - 5, this.yT_A(this.FDEy(this.yPos[this.o[s]][0])) + 5);
                }
                else {
                    offG.drawString(this.etiqueta[this.o[s]], this.xT_A(this.FDEx(this.xPos[this.o[s]][0])) - 7, this.yT_A(this.FDEy(this.yPos[this.o[s]][0])) + 5);
                }
            }
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(new Color(240, 240, 240));
        offG.fillRect(this.xT_B(0.0), this.yT_B1(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B1(100.0 - this.semiRangoY) - this.yT_B1(100.0 + this.semiRangoY));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B1(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B1(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B1(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B1(100.0 - this.semiRangoY) - this.yT_B1(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B1(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[0] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[0] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B1(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[0] - 1]);
        if (this.sts2[0] - 1 == 0 || this.sts2[0] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[0] - 1], this.xT_B(0.0) + 9, this.yT_B1(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[0] - 1 == 14 || this.sts2[0] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[0] - 1], this.xT_B(0.0) + 8, this.yT_B1(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[0] - 1], this.xT_B(0.0) + 6, this.yT_B1(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[0] - 1], this.xT_B(0.0) + 30, this.yT_B1(100.0 + this.semiRangoY) + 18);
        if (this.sts2[0] == 6 || this.sts2[0] == 12 || this.sts2[0] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B1(100.0 - this.semiRangoY) - 2);
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B2(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B2(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B2(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B2(100.0 - this.semiRangoY) - this.yT_B2(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B2(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[1] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[1] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B2(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[1] - 1]);
        if (this.sts2[1] - 1 == 0 || this.sts2[1] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[1] - 1], this.xT_B(0.0) + 9, this.yT_B2(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[1] - 1 == 14 || this.sts2[1] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[1] - 1], this.xT_B(0.0) + 8, this.yT_B2(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[1] - 1], this.xT_B(0.0) + 6, this.yT_B2(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[1] - 1], this.xT_B(0.0) + 30, this.yT_B2(100.0 + this.semiRangoY) + 18);
        if (this.sts2[1] == 6 || this.sts2[1] == 12 || this.sts2[1] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B2(100.0 - this.semiRangoY) - 2);
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(new Color(240, 240, 240));
        offG.fillRect(this.xT_B(0.0), this.yT_B3(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B3(100.0 - this.semiRangoY) - this.yT_B3(100.0 + this.semiRangoY));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B3(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B3(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B3(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B3(100.0 - this.semiRangoY) - this.yT_B3(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B3(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[2] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[2] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B3(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[2] - 1]);
        if (this.sts2[2] - 1 == 0 || this.sts2[2] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[2] - 1], this.xT_B(0.0) + 9, this.yT_B3(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[2] - 1 == 14 || this.sts2[2] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[2] - 1], this.xT_B(0.0) + 8, this.yT_B3(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[2] - 1], this.xT_B(0.0) + 6, this.yT_B3(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[2] - 1], this.xT_B(0.0) + 30, this.yT_B3(100.0 + this.semiRangoY) + 18);
        if (this.sts2[2] == 6 || this.sts2[2] == 12 || this.sts2[2] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B3(100.0 - this.semiRangoY) - 2);
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B4(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B4(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B4(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B4(100.0 - this.semiRangoY) - this.yT_B4(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B4(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[3] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[3] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B4(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[3] - 1]);
        if (this.sts2[3] - 1 == 0 || this.sts2[3] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[3] - 1], this.xT_B(0.0) + 9, this.yT_B4(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[3] - 1 == 14 || this.sts2[3] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[3] - 1], this.xT_B(0.0) + 8, this.yT_B4(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[3] - 1], this.xT_B(0.0) + 6, this.yT_B4(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[3] - 1], this.xT_B(0.0) + 30, this.yT_B4(100.0 + this.semiRangoY) + 18);
        if (this.sts2[3] == 6 || this.sts2[3] == 12 || this.sts2[3] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B4(100.0 - this.semiRangoY) - 2);
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(new Color(240, 240, 240));
        offG.fillRect(this.xT_B(0.0), this.yT_B5(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B5(100.0 - this.semiRangoY) - this.yT_B5(100.0 + this.semiRangoY));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B5(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B5(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B5(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B5(100.0 - this.semiRangoY) - this.yT_B5(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B5(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[4] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[4] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B5(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[4] - 1]);
        if (this.sts2[4] - 1 == 0 || this.sts2[4] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[4] - 1], this.xT_B(0.0) + 9, this.yT_B5(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[4] - 1 == 14 || this.sts2[4] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[4] - 1], this.xT_B(0.0) + 8, this.yT_B5(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[4] - 1], this.xT_B(0.0) + 6, this.yT_B5(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[4] - 1], this.xT_B(0.0) + 30, this.yT_B5(100.0 + this.semiRangoY) + 18);
        if (this.sts2[4] == 6 || this.sts2[4] == 12 || this.sts2[4] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B5(100.0 - this.semiRangoY) - 2);
        }
        offG.setFont(new Font("Arial", 0, 13));
        offG.setColor(Color.black);
        offG.drawLine(this.xT_B(0.0), this.yT_B6(100.0), this.xT_B(this.rangoT + 5.0), this.yT_B6(100.0));
        offG.drawRect(this.xT_B(0.0), this.yT_B6(100.0 + this.semiRangoY), this.xT_B(this.rangoT + 5.0) - this.xT_B(0.0), this.yT_B6(100.0 - this.semiRangoY) - this.yT_B6(100.0 + this.semiRangoY));
        offG.setFont(new Font("Arial", 0, 12));
        offG.drawString("100", this.xT_B(0.0) - 22, this.yT_B6(100.0) + 5);
        offG.setFont(new Font("Arial", 0, 13));
        if (this.appletActivado && this.sts1[this.sts2[5] - 1] == 1) {
            offG.setColor(this.colorPeriodo[this.sts2[5] - 1]);
        }
        else {
            offG.setColor(Color.gray);
        }
        offG.fillRect(this.xT_B(0.0) + 5, this.yT_B6(100.0 + this.semiRangoY) + 5, 17, 17);
        offG.setColor(this.colorNumero[this.sts2[5] - 1]);
        if (this.sts2[5] - 1 == 0 || this.sts2[5] - 1 == 7) {
            offG.drawString(this.etiqueta[this.sts2[5] - 1], this.xT_B(0.0) + 9, this.yT_B6(100.0 + this.semiRangoY) + 18);
        }
        else if (this.sts2[5] - 1 == 14 || this.sts2[5] - 1 == 15) {
            offG.drawString(this.etiqueta[this.sts2[5] - 1], this.xT_B(0.0) + 8, this.yT_B6(100.0 + this.semiRangoY) + 18);
        }
        else {
            offG.drawString(this.etiqueta[this.sts2[5] - 1], this.xT_B(0.0) + 6, this.yT_B6(100.0 + this.semiRangoY) + 18);
        }
        offG.setColor(Color.black);
        offG.drawString(this.nombreCorto[this.sts2[5] - 1], this.xT_B(0.0) + 30, this.yT_B6(100.0 + this.semiRangoY) + 18);
        if (this.sts2[5] == 6 || this.sts2[5] == 12 || this.sts2[5] == 13) {
            offG.setFont(new Font("Arial", 0, 10));
            offG.drawString("Tiene una relaci\u00f3n inversa con la actividad econ\u00f3mica", this.xT_B(0.0) + 3, this.yT_B6(100.0 - this.semiRangoY) - 2);
        }
        for (int i = 1; i < this.month.length; ++i) {
            if (this.fY[i][this.sts2[0] - 1] > 0.0 && this.fY[i - 1][this.sts2[0] - 1] > 0.0) {
                if (this.sts1[this.sts2[0] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[0] != 6 && this.sts2[0] != 12 && this.sts2[0] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B1(this.TCC(this.fY[i][this.sts2[0] - 1])), this.xT_B(i - 1), this.yT_B1(this.TCC(this.fY[i - 1][this.sts2[0] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B1(this.TCC(200.0 - this.fY[i][this.sts2[0] - 1])), this.xT_B(i - 1), this.yT_B1(this.TCC(200.0 - this.fY[i - 1][this.sts2[0] - 1])));
                }
            }
            if (this.fY[i][this.sts2[1] - 1] > 0.0 && this.fY[i - 1][this.sts2[1] - 1] > 0.0) {
                if (this.sts1[this.sts2[1] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[1] != 6 && this.sts2[1] != 12 && this.sts2[1] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B2(this.TCC(this.fY[i][this.sts2[1] - 1])), this.xT_B(i - 1), this.yT_B2(this.TCC(this.fY[i - 1][this.sts2[1] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B2(this.TCC(200.0 - this.fY[i][this.sts2[1] - 1])), this.xT_B(i - 1), this.yT_B2(this.TCC(200.0 - this.fY[i - 1][this.sts2[1] - 1])));
                }
            }
            if (this.fY[i][this.sts2[2] - 1] > 0.0 && this.fY[i - 1][this.sts2[2] - 1] > 0.0) {
                if (this.sts1[this.sts2[2] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[2] != 6 && this.sts2[2] != 12 && this.sts2[2] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B3(this.TCC(this.fY[i][this.sts2[2] - 1])), this.xT_B(i - 1), this.yT_B3(this.TCC(this.fY[i - 1][this.sts2[2] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B3(this.TCC(200.0 - this.fY[i][this.sts2[2] - 1])), this.xT_B(i - 1), this.yT_B3(this.TCC(200.0 - this.fY[i - 1][this.sts2[2] - 1])));
                }
            }
            if (this.fY[i][this.sts2[3] - 1] > 0.0 && this.fY[i - 1][this.sts2[3] - 1] > 0.0) {
                if (this.sts1[this.sts2[3] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[3] != 6 && this.sts2[3] != 12 && this.sts2[3] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B4(this.TCC(this.fY[i][this.sts2[3] - 1])), this.xT_B(i - 1), this.yT_B4(this.TCC(this.fY[i - 1][this.sts2[3] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B4(this.TCC(200.0 - this.fY[i][this.sts2[3] - 1])), this.xT_B(i - 1), this.yT_B4(this.TCC(200.0 - this.fY[i - 1][this.sts2[3] - 1])));
                }
            }
            if (this.fY[i][this.sts2[4] - 1] > 0.0 && this.fY[i - 1][this.sts2[4] - 1] > 0.0) {
                if (this.sts1[this.sts2[4] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[4] != 6 && this.sts2[4] != 12 && this.sts2[4] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B5(this.TCC(this.fY[i][this.sts2[4] - 1])), this.xT_B(i - 1), this.yT_B5(this.TCC(this.fY[i - 1][this.sts2[4] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B5(this.TCC(200.0 - this.fY[i][this.sts2[4] - 1])), this.xT_B(i - 1), this.yT_B5(this.TCC(200.0 - this.fY[i - 1][this.sts2[4] - 1])));
                }
            }
            if (this.fY[i][this.sts2[5] - 1] > 0.0 && this.fY[i - 1][this.sts2[5] - 1] > 0.0) {
                if (this.sts1[this.sts2[5] - 1] == 1 && this.appletActivado) {
                    offG.setColor(Color.blue);
                }
                else {
                    offG.setColor(Color.lightGray);
                }
                if (this.sts2[5] != 6 && this.sts2[5] != 12 && this.sts2[5] != 13) {
                    offG.drawLine(this.xT_B(i), this.yT_B6(this.TCC(this.fY[i][this.sts2[5] - 1])), this.xT_B(i - 1), this.yT_B6(this.TCC(this.fY[i - 1][this.sts2[5] - 1])));
                }
                else {
                    offG.drawLine(this.xT_B(i), this.yT_B6(this.TCC(200.0 - this.fY[i][this.sts2[5] - 1])), this.xT_B(i - 1), this.yT_B6(this.TCC(200.0 - this.fY[i - 1][this.sts2[5] - 1])));
                }
            }
        }
        if (this.appletActivado) {
            offG.setColor(new Color(150, 150, 255));
        }
        else {
            offG.setColor(new Color(230, 230, 230));
        }
        offG.drawLine(this.xT_B(this.periodo), this.yT_B1(100.0 + this.semiRangoY), this.xT_B(this.periodo), this.yT_B6(100.0 - this.semiRangoY));
        if (this.sts1[this.sts2[0] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[0] - 1][0] >= 100.0 && this.yPos[this.sts2[0] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[0] - 1][0] < 100.0 && this.yPos[this.sts2[0] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[0] - 1][0] <= 100.0 && this.yPos[this.sts2[0] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[0] - 1][0] > 100.0 && this.yPos[this.sts2[0] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[0] != 6 && this.sts2[0] != 12 && this.sts2[0] != 13) {
            if (this.fY[this.periodo][this.sts2[0] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B1(this.TCC(this.fY[this.periodo][this.sts2[0] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[0] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B1(this.TCC(this.fY[this.periodo][this.sts2[0] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[0] - 1] > 0.0 && this.fY[this.periodo][this.sts2[0] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B1(this.TCC(200.0 - this.fY[this.periodo][this.sts2[0] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[0] - 1] > 0.0 && this.fY[this.periodo][this.sts2[0] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B1(this.TCC(200.0 - this.fY[this.periodo][this.sts2[0] - 1])) - 2, 5, 5);
            }
        }
        if (this.sts1[this.sts2[1] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[1] - 1][0] >= 100.0 && this.yPos[this.sts2[1] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[1] - 1][0] < 100.0 && this.yPos[this.sts2[1] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[1] - 1][0] <= 100.0 && this.yPos[this.sts2[1] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[1] - 1][0] > 100.0 && this.yPos[this.sts2[1] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[1] != 6 && this.sts2[1] != 12 && this.sts2[1] != 13) {
            if (this.fY[this.periodo][this.sts2[1] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B2(this.TCC(this.fY[this.periodo][this.sts2[1] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[1] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B2(this.TCC(this.fY[this.periodo][this.sts2[1] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[1] - 1] > 0.0 && this.fY[this.periodo][this.sts2[1] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B2(this.TCC(200.0 - this.fY[this.periodo][this.sts2[1] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[1] - 1] > 0.0 && this.fY[this.periodo][this.sts2[1] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B2(this.TCC(200.0 - this.fY[this.periodo][this.sts2[1] - 1])) - 2, 5, 5);
            }
        }
        if (this.sts1[this.sts2[2] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[2] - 1][0] >= 100.0 && this.yPos[this.sts2[2] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[2] - 1][0] < 100.0 && this.yPos[this.sts2[2] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[2] - 1][0] <= 100.0 && this.yPos[this.sts2[2] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[2] - 1][0] > 100.0 && this.yPos[this.sts2[2] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[2] != 6 && this.sts2[2] != 12 && this.sts2[2] != 13) {
            if (this.fY[this.periodo][this.sts2[2] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B3(this.TCC(this.fY[this.periodo][this.sts2[2] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[2] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B3(this.TCC(this.fY[this.periodo][this.sts2[2] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[2] - 1] > 0.0 && this.fY[this.periodo][this.sts2[2] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B3(this.TCC(200.0 - this.fY[this.periodo][this.sts2[2] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[2] - 1] > 0.0 && this.fY[this.periodo][this.sts2[2] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B3(this.TCC(200.0 - this.fY[this.periodo][this.sts2[2] - 1])) - 2, 5, 5);
            }
        }
        if (this.sts1[this.sts2[3] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[3] - 1][0] >= 100.0 && this.yPos[this.sts2[3] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[3] - 1][0] < 100.0 && this.yPos[this.sts2[3] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[3] - 1][0] <= 100.0 && this.yPos[this.sts2[3] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[3] - 1][0] > 100.0 && this.yPos[this.sts2[3] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[3] != 6 && this.sts2[3] != 12 && this.sts2[3] != 13) {
            if (this.fY[this.periodo][this.sts2[3] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B4(this.TCC(this.fY[this.periodo][this.sts2[3] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[3] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B4(this.TCC(this.fY[this.periodo][this.sts2[3] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[3] - 1] > 0.0 && this.fY[this.periodo][this.sts2[3] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B4(this.TCC(200.0 - this.fY[this.periodo][this.sts2[3] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[3] - 1] > 0.0 && this.fY[this.periodo][this.sts2[3] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B4(this.TCC(200.0 - this.fY[this.periodo][this.sts2[3] - 1])) - 2, 5, 5);
            }
        }
        if (this.sts1[this.sts2[4] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[4] - 1][0] >= 100.0 && this.yPos[this.sts2[4] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[4] - 1][0] < 100.0 && this.yPos[this.sts2[4] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[4] - 1][0] <= 100.0 && this.yPos[this.sts2[4] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[4] - 1][0] > 100.0 && this.yPos[this.sts2[4] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[4] != 6 && this.sts2[4] != 12 && this.sts2[4] != 13) {
            if (this.fY[this.periodo][this.sts2[4] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B5(this.TCC(this.fY[this.periodo][this.sts2[4] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[4] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B5(this.TCC(this.fY[this.periodo][this.sts2[4] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[4] - 1] > 0.0 && this.fY[this.periodo][this.sts2[4] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B5(this.TCC(200.0 - this.fY[this.periodo][this.sts2[4] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[4] - 1] > 0.0 && this.fY[this.periodo][this.sts2[4] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B5(this.TCC(200.0 - this.fY[this.periodo][this.sts2[4] - 1])) - 2, 5, 5);
            }
        }
        if (this.sts1[this.sts2[5] - 1] == 1 && this.appletActivado) {
            if (this.xPos[this.sts2[5] - 1][0] >= 100.0 && this.yPos[this.sts2[5] - 1][0] > 100.0) {
                offG.setColor(Color.green);
            }
            if (this.xPos[this.sts2[5] - 1][0] < 100.0 && this.yPos[this.sts2[5] - 1][0] >= 100.0) {
                offG.setColor(new Color(254, 180, 0));
            }
            if (this.xPos[this.sts2[5] - 1][0] <= 100.0 && this.yPos[this.sts2[5] - 1][0] < 100.0) {
                offG.setColor(Color.red);
            }
            if (this.xPos[this.sts2[5] - 1][0] > 100.0 && this.yPos[this.sts2[5] - 1][0] <= 100.0) {
                offG.setColor(Color.yellow);
            }
        }
        else {
            offG.setColor(Color.lightGray);
        }
        if (this.sts2[5] != 6 && this.sts2[5] != 12 && this.sts2[5] != 13) {
            if (this.fY[this.periodo][this.sts2[5] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B6(this.TCC(this.fY[this.periodo][this.sts2[5] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (this.fY[this.periodo][this.sts2[5] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B6(this.TCC(this.fY[this.periodo][this.sts2[5] - 1])) - 2, 5, 5);
            }
        }
        else {
            if (200.0 - this.fY[this.periodo][this.sts2[5] - 1] > 0.0 && this.fY[this.periodo][this.sts2[5] - 1] > 0.0) {
                offG.fillRect(this.xT_B(this.periodo) - 2, this.yT_B6(this.TCC(200.0 - this.fY[this.periodo][this.sts2[5] - 1])) - 2, 5, 5);
            }
            if (this.appletActivado) {
                offG.setColor(new Color(0, 0, 0));
            }
            else {
                offG.setColor(new Color(230, 230, 230));
            }
            if (200.0 - this.fY[this.periodo][this.sts2[5] - 1] > 0.0 && this.fY[this.periodo][this.sts2[5] - 1] > 0.0) {
                offG.drawRect(this.xT_B(this.periodo) - 2, this.yT_B6(this.TCC(200.0 - this.fY[this.periodo][this.sts2[5] - 1])) - 2, 5, 5);
            }
        }
        offG.drawRect(0, 0, 749, 409);
        offG.drawRect(2, 2, 745, 405);
        offG.drawLine(406, 0, 406, 749);
        for (int i = 0; i <= this.numeroDeSegmentos; ++i) {
            int verSerie1 = 0;
            int verSerie2 = 0;
            int verSerie3 = 0;
            int verSerie4 = 0;
            int verSerie5 = 0;
            int verSerie6 = 0;
            int verSerie7 = 0;
            int verSerie8 = 0;
            int verSerie9 = 0;
            int verSerie10 = 0;
            int verSerie11 = 0;
            int verSerie12 = 0;
            int verSerie13 = 0;
            int verSerie14 = 0;
            int verSerie15 = 0;
            int verSerie16 = 0;
            int index1 = 0;
            try {
                if (this.sentido == 0 && ((Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[0][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[0][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[1][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[1][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[2][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[2][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[3][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[3][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[4][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[4][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[5][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[5][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[6][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[6][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[7][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[7][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[8][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[8][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[9][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[9][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[10][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[10][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[11][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[11][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[12][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[12][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[13][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[13][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[14][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[14][i]))) <= 3) || (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[15][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[15][i]))) <= 3))) {
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[0][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[0][i]))) <= 3 && this.sts1[0] == 1 && this.yPos[0][0] > 0.0) {
                        verSerie1 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[1][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[1][i]))) <= 3 && this.sts1[1] == 1 && this.yPos[1][0] > 0.0) {
                        verSerie2 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[2][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[2][i]))) <= 3 && this.sts1[2] == 1 && this.yPos[2][0] > 0.0) {
                        verSerie3 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[3][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[3][i]))) <= 3 && this.sts1[3] == 1 && this.yPos[3][0] > 0.0) {
                        verSerie4 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[4][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[4][i]))) <= 3 && this.sts1[4] == 1 && this.yPos[4][0] > 0.0) {
                        verSerie5 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[5][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[5][i]))) <= 3 && this.sts1[5] == 1 && this.yPos[5][0] > 0.0) {
                        verSerie6 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[6][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[6][i]))) <= 3 && this.sts1[6] == 1 && this.yPos[6][0] > 0.0) {
                        verSerie7 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[7][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[7][i]))) <= 3 && this.sts1[7] == 1 && this.yPos[7][0] > 0.0) {
                        verSerie8 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[8][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[8][i]))) <= 3 && this.sts1[8] == 1 && this.yPos[8][0] > 0.0) {
                        verSerie9 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[9][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[9][i]))) <= 3 && this.sts1[9] == 1 && this.yPos[9][0] > 0.0) {
                        verSerie10 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[10][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[10][i]))) <= 3 && this.sts1[10] == 1 && this.yPos[10][0] > 0.0) {
                        verSerie11 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[11][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[11][i]))) <= 3 && this.sts1[11] == 1 && this.yPos[11][0] > 0.0) {
                        verSerie12 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[12][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[12][i]))) <= 3 && this.sts1[12] == 1 && this.yPos[12][0] > 0.0) {
                        verSerie13 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[13][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[13][i]))) <= 3 && this.sts1[13] == 1 && this.yPos[13][0] > 0.0) {
                        verSerie14 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[14][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[14][i]))) <= 3 && this.sts1[14] == 1 && this.yPos[14][0] > 0.0) {
                        verSerie15 = 1;
                    }
                    if (Math.abs((int)this.mousePos.getX() - this.xT_A(this.FDEx(this.xPos[15][i]))) <= 3 && Math.abs((int)this.mousePos.getY() - this.yT_A(this.FDEy(this.yPos[15][i]))) <= 3 && this.sts1[15] == 1 && this.yPos[15][0] > 0.0) {
                        verSerie16 = 1;
                    }
                    if (verSerie1 + verSerie2 + verSerie3 + verSerie4 + verSerie5 + verSerie6 + verSerie7 + verSerie8 + verSerie9 + verSerie10 + verSerie11 + verSerie12 + verSerie13 + verSerie14 + verSerie15 + verSerie16 == 1) {
                        if (verSerie1 == 1) {
                            index1 = 0;
                        }
                        if (verSerie2 == 1) {
                            index1 = 1;
                        }
                        if (verSerie3 == 1) {
                            index1 = 2;
                        }
                        if (verSerie4 == 1) {
                            index1 = 3;
                        }
                        if (verSerie5 == 1) {
                            index1 = 4;
                        }
                        if (verSerie6 == 1) {
                            index1 = 5;
                        }
                        if (verSerie7 == 1) {
                            index1 = 6;
                        }
                        if (verSerie8 == 1) {
                            index1 = 7;
                        }
                        if (verSerie9 == 1) {
                            index1 = 8;
                        }
                        if (verSerie10 == 1) {
                            index1 = 9;
                        }
                        if (verSerie11 == 1) {
                            index1 = 10;
                        }
                        if (verSerie12 == 1) {
                            index1 = 11;
                        }
                        if (verSerie13 == 1) {
                            index1 = 12;
                        }
                        if (verSerie14 == 1) {
                            index1 = 13;
                        }
                        if (verSerie15 == 1) {
                            index1 = 14;
                        }
                        if (verSerie16 == 1) {
                            index1 = 15;
                        }
                        if (this.appletActivado) {
                            offG.setColor(this.colorPeriodo[index1]);
                        }
                        else {
                            offG.setColor(Color.lightGray);
                        }
                        if (this.yPos[index1][i] > 100.0) {
                            offG.setComposite(AlphaComposite.getInstance(3, 0.8f));
                            offG.fillRect(this.xT_A(this.FDEx(this.xPos[index1][i])) + 15, this.yT_A(this.FDEy(this.yPos[index1][i])), 220, 46);
                            offG.setComposite(AlphaComposite.getInstance(3, 1.0f));
                            offG.setColor(Color.black);
                            offG.drawRect(this.xT_A(this.FDEx(this.xPos[index1][i])) + 15, this.yT_A(this.FDEy(this.yPos[index1][i])), 220, 46);
                        }
                        else {
                            offG.setComposite(AlphaComposite.getInstance(3, 0.8f));
                            offG.fillRect(this.xT_A(this.FDEx(this.xPos[index1][i])) + 15, this.yT_A(this.FDEy(this.yPos[index1][i])) - 46, 220, 46);
                            offG.setComposite(AlphaComposite.getInstance(3, 1.0f));
                            offG.setColor(Color.black);
                            offG.drawRect(this.xT_A(this.FDEx(this.xPos[index1][i])) + 15, this.yT_A(this.FDEy(this.yPos[index1][i])) - 46, 220, 46);
                        }
                        offG.fillOval(this.xT_A(this.FDEx(this.xPos[index1][i])) - 5, this.yT_A(this.FDEy(this.yPos[index1][i])) - 5, 11, 11);
                        offG.setColor(Color.white);
                        offG.fillOval(this.xT_A(this.FDEx(this.xPos[index1][i])) - 4, this.yT_A(this.FDEy(this.yPos[index1][i])) - 4, 9, 9);
                        offG.setColor(Color.black);
                        offG.fillOval(this.xT_A(this.FDEx(this.xPos[index1][i])) - 2, this.yT_A(this.FDEy(this.yPos[index1][i])) - 2, 5, 5);
                        final String cadenaSerie = this.nombreCorto[index1];
                        final String cadenaFecha = this.nombreMes(this.month[this.periodo - i]) + "/" + this.year[this.periodo - i];
                        String cadenaCoord = "ciclo = " + new DecimalFormat("#00.00").format(this.yPos[index1][i]);
                        if (index1 == 5 || index1 == 11 || index1 == 12) {
                            cadenaCoord += "  (datos inversos)";
                        }
                        if (this.yPos[index1][i] > 100.0) {
                            offG.setColor(this.colorNumero[index1]);
                            offG.setFont(new Font("Times Roman", 1, 14));
                            offG.drawString(cadenaSerie, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) + 13);
                            offG.setFont(new Font("Times Roman", 0, 14));
                            offG.drawString(cadenaFecha, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) + 27);
                            offG.drawString(cadenaCoord, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) + 41);
                        }
                        else {
                            offG.setColor(this.colorNumero[index1]);
                            offG.setFont(new Font("Times Roman", 1, 14));
                            offG.drawString(cadenaSerie, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) - 33);
                            offG.setFont(new Font("Times Roman", 0, 14));
                            offG.drawString(cadenaFecha, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) - 19);
                            offG.drawString(cadenaCoord, this.xT_A(this.FDEx(this.xPos[index1][i])) + 19, this.yT_A(this.FDEy(this.yPos[index1][i])) - 5);
                        }
                        break;
                    }
                }
            }
            catch (NullPointerException ex) {}
        }
        g.drawImage(image, 0, 0, 750, 410, this.rootPane);
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.botonPausa = new JButton();
        this.botonPlayReverse = new JButton();
        this.botonPlay = new JButton();
        this.jSpinnerRapidez = new JSpinner();
        this.jLabel10 = new JLabel();
        this.jSpinnerRezagos = new JSpinner();
        this.jLabel11 = new JLabel();
        this.jSlider1 = new JSlider();
        this.jLabel12 = new JLabel();
        this.jLabel13 = new JLabel();
        this.jLabel14 = new JLabel();
        this.jCheckBox7 = new JCheckBox();
        this.jPanel3 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jPanel2 = new JPanel();
        this.jLabel9 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel8 = new JLabel();
        this.jLabel15 = new JLabel();
        this.jLabel16 = new JLabel();
        this.jLabel17 = new JLabel();
        this.jLabel18 = new JLabel();
        this.jLabel19 = new JLabel();
        this.jLabel20 = new JLabel();
        this.jLabel27 = new JLabel();
        this.jLabel29 = new JLabel();
        this.jLabel30 = new JLabel();
        this.jLabel32 = new JLabel();
        this.jLabel33 = new JLabel();
        this.jLabel35 = new JLabel();
        this.jLabel36 = new JLabel();
        this.jLabel38 = new JLabel();
        this.jLabel39 = new JLabel();
        this.jLabel41 = new JLabel();
        this.jLabel42 = new JLabel();
        this.jLabel44 = new JLabel();
        this.jLabel45 = new JLabel();
        this.jLabel47 = new JLabel();
        this.jLabel48 = new JLabel();
        this.jLabel50 = new JLabel();
        this.jLabel51 = new JLabel();
        this.jLabel53 = new JLabel();
        this.jLabel54 = new JLabel();
        this.jCheckBox1 = new JCheckBox();
        this.jCheckBox2 = new JCheckBox();
        this.jCheckBox3 = new JCheckBox();
        this.jCheckBox4 = new JCheckBox();
        this.jCheckBox5 = new JCheckBox();
        this.jCheckBox6 = new JCheckBox();
        this.jCheckBox8 = new JCheckBox();
        this.jCheckBox9 = new JCheckBox();
        this.jCheckBox10 = new JCheckBox();
        this.jCheckBox11 = new JCheckBox();
        this.jCheckBox12 = new JCheckBox();
        this.jCheckBox13 = new JCheckBox();
        this.jCheckBox14 = new JCheckBox();
        this.jCheckBox15 = new JCheckBox();
        this.jCheckBox16 = new JCheckBox();
        this.jCheckBox17 = new JCheckBox();
        this.jCheckBox18 = new JCheckBox();
        this.jLabel1 = new JLabel();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent evt) {
                JApplet8.this.formMouseDragged(evt);
            }
            
            public void mouseMoved(final MouseEvent evt) {
                JApplet8.this.formMouseMoved(evt);
            }
        });
        this.getContentPane().setLayout(null);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Controles de Animaci\u00f3n", 0, 0, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        this.jPanel1.setLayout(null);
        this.botonPausa.setIcon(new ImageIcon(this.imgPause));
        this.botonPausa.setToolTipText("<html>D\u00e9 clic en este bot\u00f3n para<br>\ndetener la animaci\u00f3n del reloj");
        this.botonPausa.setDoubleBuffered(true);
        this.botonPausa.setMaximumSize(new Dimension(32, 31));
        this.botonPausa.setMinimumSize(new Dimension(32, 31));
        this.botonPausa.setPreferredSize(new Dimension(32, 31));
        this.botonPausa.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.botonPausaMouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.botonPausaMouseExited(evt);
            }
        });
        this.botonPausa.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JApplet8.this.botonPausaActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.botonPausa);
        this.botonPausa.setBounds(18, 27, 32, 31);
        this.botonPlayReverse.setIcon(new ImageIcon(this.imgPlayBack));
        this.botonPlayReverse.setToolTipText("<html>D\u00e9 clic en este bot\u00f3n para que<br>\nel reloj se mueva hacia atr\u00e1s");
        this.botonPlayReverse.setDoubleBuffered(true);
        this.botonPlayReverse.setMaximumSize(new Dimension(32, 31));
        this.botonPlayReverse.setMinimumSize(new Dimension(32, 31));
        this.botonPlayReverse.setPreferredSize(new Dimension(32, 31));
        this.botonPlayReverse.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.botonPlayReverseMouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.botonPlayReverseMouseExited(evt);
            }
        });
        this.botonPlayReverse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JApplet8.this.botonPlayReverseActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.botonPlayReverse);
        this.botonPlayReverse.setBounds(68, 27, 32, 31);
        this.botonPlay.setIcon(new ImageIcon(this.imgPlay));
        this.botonPlay.setToolTipText("<html>D\u00e9 clic en este bot\u00f3n para que<br>\nel reloj se mueva hacia adelante");
        this.botonPlay.setDoubleBuffered(true);
        this.botonPlay.setMaximumSize(new Dimension(32, 31));
        this.botonPlay.setMinimumSize(new Dimension(32, 31));
        this.botonPlay.setPreferredSize(new Dimension(32, 31));
        this.botonPlay.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.botonPlayMouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.botonPlayMouseExited(evt);
            }
        });
        this.botonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JApplet8.this.botonPlayActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.botonPlay);
        this.botonPlay.setBounds(118, 27, 32, 31);
        this.jSpinnerRapidez.setModel(new SpinnerNumberModel(10, 1, 20, 1));
        this.jSpinnerRapidez.setToolTipText("<html>Ajuste este Control para cambiar<br> \nla velocidad de la animaci\u00f3n ");
        this.jSpinnerRapidez.setDoubleBuffered(true);
        this.jSpinnerRapidez.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                JApplet8.this.jSpinnerRapidezStateChanged(evt);
            }
        });
        this.jPanel1.add(this.jSpinnerRapidez);
        this.jSpinnerRapidez.setBounds(180, 34, 44, 20);
        this.jLabel10.setFont(new Font("Dialog", 0, 11));
        this.jLabel10.setText("Velocidad:");
        this.jLabel10.setDoubleBuffered(true);
        this.jPanel1.add(this.jLabel10);
        this.jLabel10.setBounds(180, 14, 60, 20);
        this.jSpinnerRezagos.setModel(new SpinnerNumberModel(0, 0, 24, 1));
        this.jSpinnerRezagos.setToolTipText("<html>Ajuste este Control para seleccionar el n\u00famero<br>\nde periodos que desee visualizar <b>previos</b> al mes<br>\nde inicio del periodo de an\u00e1lisis");
        this.jSpinnerRezagos.setDoubleBuffered(true);
        this.jSpinnerRezagos.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                JApplet8.this.jSpinnerRezagosStateChanged(evt);
            }
        });
        this.jPanel1.add(this.jSpinnerRezagos);
        this.jSpinnerRezagos.setBounds(242, 34, 48, 20);
        this.jLabel11.setFont(new Font("Dialog", 0, 11));
        this.jLabel11.setText("Rezagos:");
        this.jLabel11.setDoubleBuffered(true);
        this.jPanel1.add(this.jLabel11);
        this.jLabel11.setBounds(242, 14, 46, 20);
        this.jSlider1.setMajorTickSpacing(120);
        this.jSlider1.setMaximum((int)(this.rangoT - 1.0));
        this.jSlider1.setMinorTickSpacing(12);
        this.jSlider1.setPaintLabels(true);
        this.jSlider1.setToolTipText("<html> Deslice este control para ajustar el mes de inicio del<br>\nperiodo de an\u00e1lisis (tambi\u00e9n puede arrastrar la l\u00ednea<br>\nazul que cruza las gr\u00e1ficas de los Indicadores C\u00edclicos)");
        this.jSlider1.setValue(this.periodo);
        this.jSlider1.setBorder(BorderFactory.createTitledBorder("Periodo " + this.nombreMes(this.month[this.periodo]) + "/" + this.year[this.periodo]));
        this.jSlider1.setDoubleBuffered(true);
        this.jSlider1.setMaximumSize(new Dimension(200, 77));
        this.jSlider1.setMinimumSize(new Dimension(200, 77));
        this.jSlider1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jSlider1MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jSlider1MouseExited(evt);
            }
        });
        this.jSlider1.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                JApplet8.this.jSlider1StateChanged(evt);
            }
        });
        this.jPanel1.add(this.jSlider1);
        this.jSlider1.setBounds(410, 10, 335, 55);
        this.jLabel12.setFont(new Font("Dialog", 0, 11));
        this.jLabel12.setText("Pausa");
        this.jLabel12.setDoubleBuffered(true);
        this.jLabel12.setMaximumSize(new Dimension(30, 12));
        this.jLabel12.setMinimumSize(new Dimension(30, 12));
        this.jLabel12.setPreferredSize(new Dimension(30, 12));
        this.jPanel1.add(this.jLabel12);
        this.jLabel12.setBounds(18, 14, 30, 12);
        this.jLabel13.setFont(new Font("Dialog", 0, 11));
        this.jLabel13.setText(" Atr\u00e1s");
        this.jLabel13.setDoubleBuffered(true);
        this.jPanel1.add(this.jLabel13);
        this.jLabel13.setBounds(66, 14, 32, 12);
        this.jLabel14.setFont(new Font("Dialog", 0, 11));
        this.jLabel14.setText("Adelante");
        this.jLabel14.setDoubleBuffered(true);
        this.jPanel1.add(this.jLabel14);
        this.jLabel14.setBounds(118, 14, 44, 12);
        this.jCheckBox7.setFont(new Font("Dialog", 0, 11));
        this.jCheckBox7.setText("<html>Repetir<br> \nAnimaci\u00f3n");
        this.jCheckBox7.setToolTipText("<html>Marque esta casilla de verificaci\u00f3n para que<br>\nla animaci\u00f3n contin\u00fae sin interrupci\u00f3n");
        this.jCheckBox7.setDoubleBuffered(true);
        this.jCheckBox7.setFocusPainted(false);
        this.jCheckBox7.setFocusable(false);
        this.jCheckBox7.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox7ItemStateChanged(evt);
            }
        });
        this.jPanel1.add(this.jCheckBox7);
        this.jCheckBox7.setBounds(300, 20, 75, 38);
        this.getContentPane().add(this.jPanel1);
        this.jPanel1.setBounds(0, 410, 750, 70);
        this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, " ", 1, 0, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        this.jPanel3.setMaximumSize(new Dimension(200, 120));
        this.jPanel3.setMinimumSize(new Dimension(200, 120));
        this.jPanel3.setVerifyInputWhenFocusTarget(false);
        this.jPanel3.setLayout(null);
        this.jButton1.setText("<html><CENTER>Configurar<br>\nGr\u00e1ficas</CENTER>");
        this.jButton1.setToolTipText("<html>D\u00e9 clic en este bot\u00f3n para seleccionar<br>\nlas gr\u00e1ficas de los Indicadores c\u00edclicos<br> \nque desee visualizar");
        this.jButton1.setDoubleBuffered(true);
        this.jButton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jButton1MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jButton1MouseExited(evt);
            }
        });
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JApplet8.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.jButton1);
        this.jButton1.setBounds(10, 20, 90, 37);
        this.jButton2.setText("<html><CENTER>Ayuda<br>\nGeneral</CENTER>");
        this.jButton2.setToolTipText("<html>D\u00e9 clic en este bot\u00f3n para obtener ayuda<br>\nacerca de como utilizar e interpretar el<br> \nreloj de ciclos econ\u00f3micos ");
        this.jButton2.setDoubleBuffered(true);
        this.jButton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jButton2MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jButton2MouseExited(evt);
            }
        });
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JApplet8.this.jButton2ActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.jButton2);
        this.jButton2.setBounds(110, 20, 80, 37);
        this.getContentPane().add(this.jPanel3);
        this.jPanel3.setBounds(750, 410, 200, 70);
        this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Indicadores", 2, 0, new Font("Tahoma", 1, 12), new Color(0, 0, 0)));
        this.jPanel2.setMaximumSize(new Dimension(200, 650));
        this.jPanel2.setMinimumSize(new Dimension(200, 650));
        this.jPanel2.setLayout(null);
        this.jLabel9.setBackground(new Color(225, 225, 255));
        this.jLabel9.setText("Indicador Coincidente");
        this.jLabel9.setToolTipText("Indicador Coincidente");
        this.jLabel9.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel9.setDoubleBuffered(true);
        this.jLabel9.setMaximumSize(new Dimension(120, 60));
        this.jLabel9.setMinimumSize(new Dimension(120, 60));
        this.jLabel9.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel9);
        this.jLabel9.setBounds(30, 42, 140, 17);
        this.jLabel5.setBackground(new Color(255, 215, 0));
        this.jLabel5.setFont(new Font("Arial", 0, 13));
        this.jLabel5.setHorizontalAlignment(0);
        this.jLabel5.setText("C1");
        this.jLabel5.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel5.setDoubleBuffered(true);
        this.jLabel5.setMaximumSize(new Dimension(17, 17));
        this.jLabel5.setMinimumSize(new Dimension(17, 17));
        this.jLabel5.setOpaque(true);
        this.jLabel5.setPreferredSize(new Dimension(17, 17));
        this.jLabel5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel5MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel5MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel5MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel5);
        this.jLabel5.setBounds(10, 65, 17, 17);
        this.jLabel6.setBackground(new Color(225, 225, 255));
        this.jLabel6.setText("Actividad Econ\u00f3mica");
        this.jLabel6.setToolTipText("<html>Indicador de la Actividad<br>\nEcon\u00f3mica mensual");
        this.jLabel6.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel6.setDoubleBuffered(true);
        this.jLabel6.setMaximumSize(new Dimension(120, 60));
        this.jLabel6.setMinimumSize(new Dimension(120, 60));
        this.jLabel6.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel6);
        this.jLabel6.setBounds(30, 65, 140, 17);
        this.jLabel4.setBackground(new Color(255, 180, 0));
        this.jLabel4.setFont(new Font("Arial", 0, 13));
        this.jLabel4.setHorizontalAlignment(0);
        this.jLabel4.setText("C2");
        this.jLabel4.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel4.setDoubleBuffered(true);
        this.jLabel4.setMaximumSize(new Dimension(17, 17));
        this.jLabel4.setMinimumSize(new Dimension(17, 17));
        this.jLabel4.setOpaque(true);
        this.jLabel4.setPreferredSize(new Dimension(17, 17));
        this.jLabel4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel4MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel4MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel4MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel4);
        this.jLabel4.setBounds(10, 88, 17, 17);
        this.jLabel7.setBackground(new Color(225, 225, 255));
        this.jLabel7.setText("Actividad Industrial");
        this.jLabel7.setToolTipText("Indicador de la Actividad Industrial");
        this.jLabel7.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel7.setDoubleBuffered(true);
        this.jLabel7.setMaximumSize(new Dimension(120, 60));
        this.jLabel7.setMinimumSize(new Dimension(120, 60));
        this.jLabel7.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel7);
        this.jLabel7.setBounds(30, 88, 140, 17);
        this.jLabel2.setBackground(new Color(255, 120, 0));
        this.jLabel2.setFont(new Font("Arial", 0, 13));
        this.jLabel2.setForeground(new Color(255, 255, 255));
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("C3");
        this.jLabel2.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel2.setDoubleBuffered(true);
        this.jLabel2.setMaximumSize(new Dimension(17, 17));
        this.jLabel2.setMinimumSize(new Dimension(17, 17));
        this.jLabel2.setOpaque(true);
        this.jLabel2.setPreferredSize(new Dimension(17, 17));
        this.jLabel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel2MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel2MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel2MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel2);
        this.jLabel2.setBounds(10, 111, 17, 17);
        this.jLabel8.setBackground(new Color(225, 225, 255));
        this.jLabel8.setText("Ventas al por Menor");
        this.jLabel8.setToolTipText("<html>\u00cdndice de Ventas Netas al por menor<br> \nen los Establecimientos Comerciales");
        this.jLabel8.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel8.setDoubleBuffered(true);
        this.jLabel8.setMaximumSize(new Dimension(120, 60));
        this.jLabel8.setMinimumSize(new Dimension(120, 60));
        this.jLabel8.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel8);
        this.jLabel8.setBounds(30, 111, 140, 17);
        this.jLabel15.setBackground(new Color(200, 60, 0));
        this.jLabel15.setFont(new Font("Arial", 0, 13));
        this.jLabel15.setForeground(new Color(255, 255, 255));
        this.jLabel15.setHorizontalAlignment(0);
        this.jLabel15.setText("C4");
        this.jLabel15.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel15.setDoubleBuffered(true);
        this.jLabel15.setMaximumSize(new Dimension(17, 17));
        this.jLabel15.setMinimumSize(new Dimension(17, 17));
        this.jLabel15.setOpaque(true);
        this.jLabel15.setPreferredSize(new Dimension(17, 17));
        this.jLabel15.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel15MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel15MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel15MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel15);
        this.jLabel15.setBounds(10, 134, 17, 17);
        this.jLabel16.setBackground(new Color(225, 225, 255));
        this.jLabel16.setText("Asegurados en el IMSS");
        this.jLabel16.setToolTipText("<html>N\u00famero de Asegurados<br>\npermanentes en el IMSS  ");
        this.jLabel16.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel16.setDoubleBuffered(true);
        this.jLabel16.setMaximumSize(new Dimension(120, 60));
        this.jLabel16.setMinimumSize(new Dimension(120, 60));
        this.jLabel16.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel16);
        this.jLabel16.setBounds(30, 134, 140, 17);
        this.jLabel17.setBackground(new Color(130, 30, 0));
        this.jLabel17.setFont(new Font("Arial", 0, 13));
        this.jLabel17.setForeground(new Color(255, 255, 255));
        this.jLabel17.setHorizontalAlignment(0);
        this.jLabel17.setText("C5");
        this.jLabel17.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel17.setDoubleBuffered(true);
        this.jLabel17.setMaximumSize(new Dimension(17, 17));
        this.jLabel17.setMinimumSize(new Dimension(17, 17));
        this.jLabel17.setOpaque(true);
        this.jLabel17.setPreferredSize(new Dimension(17, 17));
        this.jLabel17.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel17MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel17MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel17MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel17);
        this.jLabel17.setBounds(10, 157, 17, 17);
        this.jLabel18.setBackground(new Color(225, 225, 255));
        this.jLabel18.setText("Desocupaci\u00f3n Urbana");
        this.jLabel18.setToolTipText("Tasa de Desocupaci\u00f3n Urbana");
        this.jLabel18.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel18.setDoubleBuffered(true);
        this.jLabel18.setMaximumSize(new Dimension(120, 60));
        this.jLabel18.setMinimumSize(new Dimension(120, 60));
        this.jLabel18.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel18);
        this.jLabel18.setBounds(30, 157, 140, 17);
        this.jLabel19.setBackground(new Color(255, 0, 0));
        this.jLabel19.setFont(new Font("Arial", 0, 13));
        this.jLabel19.setForeground(new Color(255, 255, 255));
        this.jLabel19.setHorizontalAlignment(0);
        this.jLabel19.setText("C");
        this.jLabel19.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel19.setDoubleBuffered(true);
        this.jLabel19.setMaximumSize(new Dimension(17, 17));
        this.jLabel19.setMinimumSize(new Dimension(17, 17));
        this.jLabel19.setOpaque(true);
        this.jLabel19.setPreferredSize(new Dimension(17, 17));
        this.jLabel19.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel19MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel19MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel19MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel19);
        this.jLabel19.setBounds(10, 42, 17, 17);
        this.jLabel20.setBackground(new Color(70, 0, 0));
        this.jLabel20.setFont(new Font("Arial", 0, 13));
        this.jLabel20.setForeground(new Color(255, 255, 255));
        this.jLabel20.setHorizontalAlignment(0);
        this.jLabel20.setText("C6");
        this.jLabel20.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel20.setDoubleBuffered(true);
        this.jLabel20.setMaximumSize(new Dimension(17, 17));
        this.jLabel20.setMinimumSize(new Dimension(17, 17));
        this.jLabel20.setOpaque(true);
        this.jLabel20.setPreferredSize(new Dimension(17, 17));
        this.jLabel20.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel20MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel20MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel20MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel20);
        this.jLabel20.setBounds(10, 180, 17, 17);
        this.jLabel27.setBackground(new Color(225, 225, 255));
        this.jLabel27.setText("Importaciones Totales");
        this.jLabel27.setToolTipText("Importaciones Totales");
        this.jLabel27.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel27.setDoubleBuffered(true);
        this.jLabel27.setMaximumSize(new Dimension(120, 60));
        this.jLabel27.setMinimumSize(new Dimension(120, 60));
        this.jLabel27.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel27);
        this.jLabel27.setBounds(30, 180, 140, 17);
        this.jLabel29.setBackground(new Color(0, 0, 255));
        this.jLabel29.setFont(new Font("Arial", 0, 13));
        this.jLabel29.setForeground(new Color(255, 255, 255));
        this.jLabel29.setHorizontalAlignment(0);
        this.jLabel29.setText("A");
        this.jLabel29.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel29.setDoubleBuffered(true);
        this.jLabel29.setMaximumSize(new Dimension(17, 17));
        this.jLabel29.setMinimumSize(new Dimension(17, 17));
        this.jLabel29.setOpaque(true);
        this.jLabel29.setPreferredSize(new Dimension(17, 17));
        this.jLabel29.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel29MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel29MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel29MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel29);
        this.jLabel29.setBounds(10, 203, 17, 17);
        this.jLabel30.setBackground(new Color(225, 225, 255));
        this.jLabel30.setText("Indicador Adelantado o/");
        this.jLabel30.setToolTipText("Indicador Adelantado");
        this.jLabel30.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel30.setDoubleBuffered(true);
        this.jLabel30.setMaximumSize(new Dimension(120, 60));
        this.jLabel30.setMinimumSize(new Dimension(120, 60));
        this.jLabel30.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel30);
        this.jLabel30.setBounds(30, 203, 140, 17);
        this.jLabel32.setBackground(new Color(130, 240, 255));
        this.jLabel32.setFont(new Font("Arial", 0, 13));
        this.jLabel32.setHorizontalAlignment(0);
        this.jLabel32.setText("A1");
        this.jLabel32.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel32.setDoubleBuffered(true);
        this.jLabel32.setMaximumSize(new Dimension(17, 17));
        this.jLabel32.setMinimumSize(new Dimension(17, 17));
        this.jLabel32.setOpaque(true);
        this.jLabel32.setPreferredSize(new Dimension(17, 17));
        this.jLabel32.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel32MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel32MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel32MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel32);
        this.jLabel32.setBounds(10, 226, 17, 17);
        this.jLabel33.setBackground(new Color(225, 225, 255));
        this.jLabel33.setText("Tendencia del Empleo");
        this.jLabel33.setToolTipText("<html>Tendencia del Empleo<br> \nen las Manufacturas");
        this.jLabel33.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel33.setDoubleBuffered(true);
        this.jLabel33.setMaximumSize(new Dimension(120, 60));
        this.jLabel33.setMinimumSize(new Dimension(120, 60));
        this.jLabel33.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel33);
        this.jLabel33.setBounds(30, 226, 140, 17);
        this.jLabel35.setBackground(new Color(20, 220, 255));
        this.jLabel35.setFont(new Font("Arial", 0, 13));
        this.jLabel35.setHorizontalAlignment(0);
        this.jLabel35.setText("A2");
        this.jLabel35.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel35.setDoubleBuffered(true);
        this.jLabel35.setMaximumSize(new Dimension(17, 17));
        this.jLabel35.setMinimumSize(new Dimension(17, 17));
        this.jLabel35.setOpaque(true);
        this.jLabel35.setPreferredSize(new Dimension(17, 17));
        this.jLabel35.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel35MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel35MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel35MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel35);
        this.jLabel35.setBounds(10, 249, 17, 17);
        this.jLabel36.setBackground(new Color(225, 225, 255));
        this.jLabel36.setText("Export. no Petroleras");
        this.jLabel36.setToolTipText("Exportaciones no Petroleras");
        this.jLabel36.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel36.setDoubleBuffered(true);
        this.jLabel36.setMaximumSize(new Dimension(120, 60));
        this.jLabel36.setMinimumSize(new Dimension(120, 60));
        this.jLabel36.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel36);
        this.jLabel36.setBounds(30, 249, 140, 17);
        this.jLabel38.setBackground(new Color(20, 150, 255));
        this.jLabel38.setFont(new Font("Arial", 0, 13));
        this.jLabel38.setForeground(new Color(255, 255, 255));
        this.jLabel38.setHorizontalAlignment(0);
        this.jLabel38.setText("A3");
        this.jLabel38.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel38.setDoubleBuffered(true);
        this.jLabel38.setMaximumSize(new Dimension(17, 17));
        this.jLabel38.setMinimumSize(new Dimension(17, 17));
        this.jLabel38.setOpaque(true);
        this.jLabel38.setPreferredSize(new Dimension(17, 17));
        this.jLabel38.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel38MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel38MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel38MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel38);
        this.jLabel38.setBounds(10, 272, 17, 17);
        this.jLabel39.setBackground(new Color(225, 225, 255));
        this.jLabel39.setText("IPC de la BMV");
        this.jLabel39.setToolTipText("<html>\u00cdndice de Precios y Cotizaciones<br>\nde la Bolsa Mexicana de<br> \nValores en t\u00e9rminos reales");
        this.jLabel39.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel39.setDoubleBuffered(true);
        this.jLabel39.setMaximumSize(new Dimension(120, 60));
        this.jLabel39.setMinimumSize(new Dimension(120, 60));
        this.jLabel39.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel39);
        this.jLabel39.setBounds(30, 272, 140, 17);
        this.jLabel41.setBackground(new Color(0, 90, 255));
        this.jLabel41.setFont(new Font("Arial", 0, 13));
        this.jLabel41.setForeground(new Color(255, 255, 255));
        this.jLabel41.setHorizontalAlignment(0);
        this.jLabel41.setText("A4");
        this.jLabel41.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel41.setDoubleBuffered(true);
        this.jLabel41.setMaximumSize(new Dimension(17, 17));
        this.jLabel41.setMinimumSize(new Dimension(17, 17));
        this.jLabel41.setOpaque(true);
        this.jLabel41.setPreferredSize(new Dimension(17, 17));
        this.jLabel41.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel41MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel41MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel41MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel41);
        this.jLabel41.setBounds(10, 295, 17, 17);
        this.jLabel42.setBackground(new Color(225, 225, 255));
        this.jLabel42.setText("Tipo de Cambio Real o/");
        this.jLabel42.setToolTipText("Tipo de Cambio Real");
        this.jLabel42.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel42.setDoubleBuffered(true);
        this.jLabel42.setMaximumSize(new Dimension(120, 60));
        this.jLabel42.setMinimumSize(new Dimension(120, 60));
        this.jLabel42.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel42);
        this.jLabel42.setBounds(30, 295, 140, 17);
        this.jLabel44.setBackground(new Color(90, 20, 255));
        this.jLabel44.setFont(new Font("Arial", 0, 13));
        this.jLabel44.setForeground(new Color(255, 255, 255));
        this.jLabel44.setHorizontalAlignment(0);
        this.jLabel44.setText("A5");
        this.jLabel44.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel44.setDoubleBuffered(true);
        this.jLabel44.setMaximumSize(new Dimension(17, 17));
        this.jLabel44.setMinimumSize(new Dimension(17, 17));
        this.jLabel44.setOpaque(true);
        this.jLabel44.setPreferredSize(new Dimension(17, 17));
        this.jLabel44.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel44MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel44MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel44MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel44);
        this.jLabel44.setBounds(10, 318, 17, 17);
        this.jLabel45.setBackground(new Color(225, 225, 255));
        this.jLabel45.setText("Tasa de Inter\u00e9s (TIIE)");
        this.jLabel45.setToolTipText("<html>Tasa de Inter\u00e9s Inter-<br> \nbancaria de Equilibrio");
        this.jLabel45.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel45.setDoubleBuffered(true);
        this.jLabel45.setMaximumSize(new Dimension(120, 60));
        this.jLabel45.setMinimumSize(new Dimension(120, 60));
        this.jLabel45.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel45);
        this.jLabel45.setBounds(30, 318, 140, 17);
        this.jLabel47.setBackground(new Color(180, 0, 180));
        this.jLabel47.setFont(new Font("Arial", 0, 13));
        this.jLabel47.setForeground(new Color(255, 255, 255));
        this.jLabel47.setHorizontalAlignment(0);
        this.jLabel47.setText("A6");
        this.jLabel47.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel47.setDoubleBuffered(true);
        this.jLabel47.setMaximumSize(new Dimension(17, 17));
        this.jLabel47.setMinimumSize(new Dimension(17, 17));
        this.jLabel47.setOpaque(true);
        this.jLabel47.setPreferredSize(new Dimension(17, 17));
        this.jLabel47.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel47MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel47MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel47MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel47);
        this.jLabel47.setBounds(10, 341, 17, 17);
        this.jLabel48.setBackground(new Color(225, 225, 255));
        this.jLabel48.setText("\u00cdndice S&P 500 (EUA)");
        this.jLabel48.setToolTipText("<html>\u00cdndice Standard & Poor's 500<br>\n(\u00edndice burs\u00e1til de Estados Unidos)");
        this.jLabel48.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel48.setDoubleBuffered(true);
        this.jLabel48.setMaximumSize(new Dimension(120, 60));
        this.jLabel48.setMinimumSize(new Dimension(120, 60));
        this.jLabel48.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel48);
        this.jLabel48.setBounds(30, 341, 140, 17);
        this.jLabel50.setBackground(new Color(0, 153, 0));
        this.jLabel50.setFont(new Font("Arial", 0, 13));
        this.jLabel50.setForeground(new Color(255, 255, 255));
        this.jLabel50.setHorizontalAlignment(0);
        this.jLabel50.setText("IP");
        this.jLabel50.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel50.setDoubleBuffered(true);
        this.jLabel50.setMaximumSize(new Dimension(17, 17));
        this.jLabel50.setMinimumSize(new Dimension(17, 17));
        this.jLabel50.setOpaque(true);
        this.jLabel50.setPreferredSize(new Dimension(17, 17));
        this.jLabel50.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel50MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel50MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel50MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel50);
        this.jLabel50.setBounds(10, 364, 17, 17);
        this.jLabel51.setBackground(new Color(225, 225, 255));
        this.jLabel51.setText("Confianza Productor");
        this.jLabel51.setToolTipText("<html>\u00cdndice de Confianza<br> \ndel Productor");
        this.jLabel51.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel51.setDoubleBuffered(true);
        this.jLabel51.setMaximumSize(new Dimension(120, 60));
        this.jLabel51.setMinimumSize(new Dimension(120, 60));
        this.jLabel51.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel51);
        this.jLabel51.setBounds(30, 364, 140, 17);
        this.jLabel53.setBackground(new Color(0, 255, 0));
        this.jLabel53.setFont(new Font("Arial", 0, 13));
        this.jLabel53.setHorizontalAlignment(0);
        this.jLabel53.setText("IC");
        this.jLabel53.setToolTipText("<html>D\u00e9 clic aqu\u00ed para mayor informaci\u00f3n<br>\nacerca de este indicador");
        this.jLabel53.setDoubleBuffered(true);
        this.jLabel53.setMaximumSize(new Dimension(17, 17));
        this.jLabel53.setMinimumSize(new Dimension(17, 17));
        this.jLabel53.setOpaque(true);
        this.jLabel53.setPreferredSize(new Dimension(17, 17));
        this.jLabel53.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JApplet8.this.jLabel53MouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                JApplet8.this.jLabel53MouseEntered(evt);
            }
            
            public void mouseExited(final MouseEvent evt) {
                JApplet8.this.jLabel53MouseExited(evt);
            }
        });
        this.jPanel2.add(this.jLabel53);
        this.jLabel53.setBounds(10, 387, 17, 17);
        this.jLabel54.setBackground(new Color(225, 225, 255));
        this.jLabel54.setText("Confianza Consumidor");
        this.jLabel54.setToolTipText("<html>\u00cdndice de Confianza\n<br>del Consumidor");
        this.jLabel54.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel54.setDoubleBuffered(true);
        this.jLabel54.setMaximumSize(new Dimension(120, 60));
        this.jLabel54.setMinimumSize(new Dimension(120, 60));
        this.jLabel54.setPreferredSize(new Dimension(120, 60));
        this.jPanel2.add(this.jLabel54);
        this.jLabel54.setBounds(30, 387, 140, 17);
        this.jCheckBox1.setSelected(true);
        this.jCheckBox1.setDoubleBuffered(true);
        this.jCheckBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox1ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox1);
        this.jCheckBox1.setBounds(173, 384, 20, 20);
        this.jCheckBox2.setToolTipText("<html>Marque esta casilla de verificaci\u00f3n para<br>\nvisualizar todos los indicadores en los<br>\ncuatro cuadrantes del reloj");
        this.jCheckBox2.setDoubleBuffered(true);
        this.jCheckBox2.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox2ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox2);
        this.jCheckBox2.setBounds(173, 16, 20, 20);
        this.jCheckBox3.setSelected(true);
        this.jCheckBox3.setDoubleBuffered(true);
        this.jCheckBox3.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox3ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox3);
        this.jCheckBox3.setBounds(173, 62, 20, 20);
        this.jCheckBox4.setSelected(true);
        this.jCheckBox4.setDoubleBuffered(true);
        this.jCheckBox4.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox4ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox4);
        this.jCheckBox4.setBounds(173, 85, 20, 20);
        this.jCheckBox5.setDoubleBuffered(true);
        this.jCheckBox5.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox5ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox5);
        this.jCheckBox5.setBounds(173, 108, 20, 20);
        this.jCheckBox6.setDoubleBuffered(true);
        this.jCheckBox6.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox6ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox6);
        this.jCheckBox6.setBounds(173, 131, 20, 20);
        this.jCheckBox8.setDoubleBuffered(true);
        this.jCheckBox8.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox8ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox8);
        this.jCheckBox8.setBounds(173, 154, 20, 20);
        this.jCheckBox9.setDoubleBuffered(true);
        this.jCheckBox9.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox9ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox9);
        this.jCheckBox9.setBounds(173, 177, 20, 20);
        this.jCheckBox10.setSelected(true);
        this.jCheckBox10.setDoubleBuffered(true);
        this.jCheckBox10.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox10ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox10);
        this.jCheckBox10.setBounds(173, 200, 20, 20);
        this.jCheckBox11.setDoubleBuffered(true);
        this.jCheckBox11.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox11ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox11);
        this.jCheckBox11.setBounds(173, 223, 20, 20);
        this.jCheckBox12.setDoubleBuffered(true);
        this.jCheckBox12.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox12ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox12);
        this.jCheckBox12.setBounds(173, 246, 20, 20);
        this.jCheckBox13.setDoubleBuffered(true);
        this.jCheckBox13.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox13ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox13);
        this.jCheckBox13.setBounds(173, 269, 20, 20);
        this.jCheckBox14.setDoubleBuffered(true);
        this.jCheckBox14.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox14ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox14);
        this.jCheckBox14.setBounds(173, 292, 20, 20);
        this.jCheckBox15.setDoubleBuffered(true);
        this.jCheckBox15.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox15ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox15);
        this.jCheckBox15.setBounds(173, 315, 20, 20);
        this.jCheckBox16.setDoubleBuffered(true);
        this.jCheckBox16.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox16ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox16);
        this.jCheckBox16.setBounds(173, 338, 20, 20);
        this.jCheckBox17.setSelected(true);
        this.jCheckBox17.setDoubleBuffered(true);
        this.jCheckBox17.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox17ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox17);
        this.jCheckBox17.setBounds(173, 361, 20, 20);
        this.jCheckBox18.setSelected(true);
        this.jCheckBox18.setDoubleBuffered(true);
        this.jCheckBox18.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                JApplet8.this.jCheckBox18ItemStateChanged(evt);
            }
        });
        this.jPanel2.add(this.jCheckBox18);
        this.jCheckBox18.setBounds(173, 39, 20, 20);
        this.jLabel1.setBackground(new Color(225, 225, 255));
        this.jLabel1.setFont(new Font("Tahoma", 1, 11));
        this.jLabel1.setHorizontalAlignment(4);
        this.jLabel1.setText("Todos    ");
        this.jLabel1.setToolTipText("<html>Marque la casilla de verificaci\u00f3n que est\u00e1 a la<br>\nderecha para visualizar todos los indicadores<br>\nen los cuatro cuadrantes del reloj");
        this.jLabel1.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel1.setDoubleBuffered(true);
        this.jPanel2.add(this.jLabel1);
        this.jLabel1.setBounds(30, 19, 140, 17);
        this.getContentPane().add(this.jPanel2);
        this.jPanel2.setBounds(750, 0, 200, 410);
    }
    
    private void botonPausaActionPerformed(final ActionEvent evt) {
        this.sentido = 0;
        this.jSlider1.setEnabled(true);
        this.jButton1.setEnabled(true);
    }
    
    private void botonPlayReverseActionPerformed(final ActionEvent evt) {
        this.sentido = -1;
        this.jSlider1.setEnabled(false);
        this.jButton1.setEnabled(false);
    }
    
    private void botonPlayActionPerformed(final ActionEvent evt) {
        this.sentido = 1;
        this.jSlider1.setEnabled(false);
        this.jButton1.setEnabled(false);
    }
    
    private void jSpinnerRapidezStateChanged(final ChangeEvent evt) {
        final JSpinner spinner = (JSpinner)evt.getSource();
        final Integer i = (Integer)spinner.getValue();
        this.rapidez = i;
    }
    
    private void jSpinnerRezagosStateChanged(final ChangeEvent evt) {
        final JSpinner spinner = (JSpinner)evt.getSource();
        final Integer i = (Integer)spinner.getValue();
        this.numeroDeSegmentos = i;
        this.periodo = Math.max(this.periodo, this.numeroDeSegmentos);
        this.jSlider1.setValue(this.periodo);
    }
    
    private void jSlider1StateChanged(final ChangeEvent evt) {
        final JSlider slider = (JSlider)evt.getSource();
        final Integer i = slider.getValue();
        this.periodo = i;
        if (this.periodo < this.numeroDeSegmentos) {
            this.numeroDeSegmentos = this.periodo;
            this.jSpinnerRezagos.setValue(this.numeroDeSegmentos);
        }
        this.jSlider1.setBorder(BorderFactory.createTitledBorder("Periodo " + this.nombreMes(this.month[this.periodo]) + "/" + this.year[this.periodo]));
    }
    
    private void jCheckBox7ItemStateChanged(final ItemEvent evt) {
        this.recycle = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.botonPausa.setEnabled(false);
        this.botonPlay.setEnabled(false);
        this.botonPlayReverse.setEnabled(false);
        this.jCheckBox7.setEnabled(false);
        this.jSlider1.setEnabled(false);
        this.jSpinnerRapidez.setEnabled(false);
        this.jSpinnerRezagos.setEnabled(false);
        this.jButton1.setEnabled(false);
        this.jButton2.setEnabled(false);
        this.jLabel19.setBackground(Color.gray);
        this.jLabel5.setBackground(Color.gray);
        this.jLabel4.setBackground(Color.gray);
        this.jLabel2.setBackground(Color.gray);
        this.jLabel15.setBackground(Color.gray);
        this.jLabel17.setBackground(Color.gray);
        this.jLabel20.setBackground(Color.gray);
        this.jLabel29.setBackground(Color.gray);
        this.jLabel32.setBackground(Color.gray);
        this.jLabel35.setBackground(Color.gray);
        this.jLabel38.setBackground(Color.gray);
        this.jLabel41.setBackground(Color.gray);
        this.jLabel44.setBackground(Color.gray);
        this.jLabel47.setBackground(Color.gray);
        this.jLabel50.setBackground(Color.gray);
        this.jLabel53.setBackground(Color.gray);
        this.jCheckBox2.setEnabled(false);
        this.jCheckBox18.setEnabled(false);
        this.jCheckBox3.setEnabled(false);
        this.jCheckBox4.setEnabled(false);
        this.jCheckBox5.setEnabled(false);
        this.jCheckBox6.setEnabled(false);
        this.jCheckBox8.setEnabled(false);
        this.jCheckBox9.setEnabled(false);
        this.jCheckBox10.setEnabled(false);
        this.jCheckBox11.setEnabled(false);
        this.jCheckBox12.setEnabled(false);
        this.jCheckBox13.setEnabled(false);
        this.jCheckBox14.setEnabled(false);
        this.jCheckBox15.setEnabled(false);
        this.jCheckBox16.setEnabled(false);
        this.jCheckBox17.setEnabled(false);
        this.jCheckBox1.setEnabled(false);
        this.appletActivado = false;
        this.tablero.setVisible(true);
    }
    
    private void jCheckBox1ItemStateChanged(final ItemEvent evt) {
        this.sts1[15] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox2ItemStateChanged(final ItemEvent evt) {
        if (evt.getStateChange() == 1) {
            this.jCheckBox18.setSelected(true);
            this.jCheckBox3.setSelected(true);
            this.jCheckBox4.setSelected(true);
            this.jCheckBox5.setSelected(true);
            this.jCheckBox6.setSelected(true);
            this.jCheckBox8.setSelected(true);
            this.jCheckBox9.setSelected(true);
            this.jCheckBox10.setSelected(true);
            this.jCheckBox11.setSelected(true);
            this.jCheckBox12.setSelected(true);
            this.jCheckBox13.setSelected(true);
            this.jCheckBox14.setSelected(true);
            this.jCheckBox15.setSelected(true);
            this.jCheckBox16.setSelected(true);
            this.jCheckBox17.setSelected(true);
            this.jCheckBox1.setSelected(true);
        }
        else {
            this.jCheckBox18.setSelected(false);
            this.jCheckBox3.setSelected(false);
            this.jCheckBox4.setSelected(false);
            this.jCheckBox5.setSelected(false);
            this.jCheckBox6.setSelected(false);
            this.jCheckBox8.setSelected(false);
            this.jCheckBox9.setSelected(false);
            this.jCheckBox10.setSelected(false);
            this.jCheckBox11.setSelected(false);
            this.jCheckBox12.setSelected(false);
            this.jCheckBox13.setSelected(false);
            this.jCheckBox14.setSelected(false);
            this.jCheckBox15.setSelected(false);
            this.jCheckBox16.setSelected(false);
            this.jCheckBox17.setSelected(false);
            this.jCheckBox1.setSelected(false);
        }
    }
    
    private void jCheckBox3ItemStateChanged(final ItemEvent evt) {
        this.sts1[1] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox4ItemStateChanged(final ItemEvent evt) {
        this.sts1[2] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox5ItemStateChanged(final ItemEvent evt) {
        this.sts1[3] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox6ItemStateChanged(final ItemEvent evt) {
        this.sts1[4] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox8ItemStateChanged(final ItemEvent evt) {
        this.sts1[5] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox9ItemStateChanged(final ItemEvent evt) {
        this.sts1[6] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox10ItemStateChanged(final ItemEvent evt) {
        this.sts1[7] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox11ItemStateChanged(final ItemEvent evt) {
        this.sts1[8] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox12ItemStateChanged(final ItemEvent evt) {
        this.sts1[9] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox13ItemStateChanged(final ItemEvent evt) {
        this.sts1[10] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox14ItemStateChanged(final ItemEvent evt) {
        this.sts1[11] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox15ItemStateChanged(final ItemEvent evt) {
        this.sts1[12] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox16ItemStateChanged(final ItemEvent evt) {
        this.sts1[13] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox17ItemStateChanged(final ItemEvent evt) {
        this.sts1[14] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void jCheckBox18ItemStateChanged(final ItemEvent evt) {
        this.sts1[0] = ((evt.getStateChange() == 1) ? 1 : 0);
    }
    
    private void formMouseDragged(final MouseEvent evt) {
        if (this.appletActivado) {
            this.mousePos = this.getMousePosition();
            if (this.sentido == 0 && this.mousePos.getY() < 410.0 && Math.abs((int)this.mousePos.getX() - this.xT_B(this.periodo)) <= 15) {
                this.periodo = (int)(this.rangoT * (this.mousePos.getX() - 430.0) / 297.0);
                if (this.periodo < 0) {
                    this.periodo = 0;
                }
                if (this.periodo > this.rangoT - 1.0) {
                    this.periodo = (int)this.rangoT - 1;
                }
                this.jSlider1.setValue(this.periodo);
            }
        }
    }
    
    private void formMouseMoved(final MouseEvent evt) {
        try {
            if (this.appletActivado) {
                this.mousePos = this.getMousePosition();
                if (this.sentido == 0 && this.mousePos.getY() < 410.0 && Math.abs((int)this.mousePos.getX() - this.xT_B(this.periodo)) <= 15) {
                    this.muevePeriodo = true;
                    this.setCursor(new Cursor(12));
                }
                else {
                    this.muevePeriodo = false;
                    this.setCursor(new Cursor(0));
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[0] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[0]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[0]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(590, 320));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, "Ayuda General", 1);
        }
    }
    
    private void botonPausaMouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void botonPausaMouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void botonPlayReverseMouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void botonPlayReverseMouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void botonPlayMouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void botonPlayMouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jSlider1MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
        this.muevePeriodo = true;
    }
    
    private void jSlider1MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
        this.muevePeriodo = false;
    }
    
    private void jButton1MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton1MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jButton2MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jButton2MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel19MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel19MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel5MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel5MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel4MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel4MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel2MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel2MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel15MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel15MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel17MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel17MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel20MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel20MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel29MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel29MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel32MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel32MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel35MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel35MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel38MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel38MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel41MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel41MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel44MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel44MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel47MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel47MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel50MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel50MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel53MouseEntered(final MouseEvent evt) {
        this.setCursor(new Cursor(12));
    }
    
    private void jLabel53MouseExited(final MouseEvent evt) {
        this.setCursor(new Cursor(0));
    }
    
    private void jLabel19MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[1] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[1]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[1]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda01.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[0], 1);
        }
    }
    
    private void jLabel5MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[2] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[2]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[2]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda02.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[1], 1);
        }
    }
    
    private void jLabel4MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[3] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[3]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[3]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda03.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[2], 1);
        }
    }
    
    private void jLabel2MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[4] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[4]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[4]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda04.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[3], 1);
        }
    }
    
    private void jLabel15MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[5] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[5]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[5]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda05.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[4], 1);
        }
    }
    
    private void jLabel17MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[6] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[6]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[6]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda06.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[5], 1);
        }
    }
    
    private void jLabel20MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[7] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[7]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[7]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda07.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[6], 1);
        }
    }
    
    private void jLabel29MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[8] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[8]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[8]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda08.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[7], 1);
        }
    }
    
    private void jLabel32MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[9] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[9]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[9]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda09.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[8], 1);
        }
    }
    
    private void jLabel35MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[10] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[10]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[10]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda10.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[9], 1);
        }
    }
    
    private void jLabel38MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[11] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[11]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[11]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda11.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[10], 1);
        }
    }
    
    private void jLabel41MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[12] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[12]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[12]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda12.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[11], 1);
        }
    }
    
    private void jLabel44MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[13] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[13]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[13]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda13.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[12], 1);
        }
    }
    
    private void jLabel47MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[14] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[14]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[14]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda14.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[13], 1);
        }
    }
    
    private void jLabel50MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[15] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[15]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[15]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda15.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[14], 1);
        }
    }
    
    private void jLabel53MouseClicked(final MouseEvent evt) {
        if (this.appletActivado) {
            final JEditorPane editorPane = new JEditorPane();
            editorPane.setEditable(false);
            if (this.mensajeAyuda[16] != null) {
                try {
                    editorPane.setPage(this.mensajeAyuda[16]);
                }
                catch (IOException e) {
                    System.out.println("Intento de leer un URL defectuoso: " + this.mensajeAyuda[16]);
                    this.stop();
                }
            }
            else {
                System.out.println("No se puede encontrar el archivo: Ayuda16.html");
                this.stop();
            }
            final JScrollPane scrollPane = new JScrollPane(editorPane);
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setPreferredSize(new Dimension(327, 164));
            scrollPane.setMinimumSize(new Dimension(10, 10));
            JOptionPane.showMessageDialog(this.rootPane, scrollPane, this.nombreCorto[15], 1);
        }
    }
    
    private void inicializaParametros() {
        this.mensajeAyuda = new URL[17];
        try {
            this.mensajeAyuda[0] = new URL(this.getCodeBase(), "ayuda/Ayuda.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[1] = new URL(this.getCodeBase(), "ayuda/Ayuda01.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[2] = new URL(this.getCodeBase(), "ayuda/Ayuda02.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[3] = new URL(this.getCodeBase(), "ayuda/Ayuda03.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[4] = new URL(this.getCodeBase(), "ayuda/Ayuda04.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[5] = new URL(this.getCodeBase(), "ayuda/Ayuda05.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[6] = new URL(this.getCodeBase(), "ayuda/Ayuda06.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[7] = new URL(this.getCodeBase(), "ayuda/Ayuda07.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[8] = new URL(this.getCodeBase(), "ayuda/Ayuda08.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[9] = new URL(this.getCodeBase(), "ayuda/Ayuda09.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[10] = new URL(this.getCodeBase(), "ayuda/Ayuda10.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[11] = new URL(this.getCodeBase(), "ayuda/Ayuda11.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[12] = new URL(this.getCodeBase(), "ayuda/Ayuda12.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[13] = new URL(this.getCodeBase(), "ayuda/Ayuda13.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[14] = new URL(this.getCodeBase(), "ayuda/Ayuda14.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[15] = new URL(this.getCodeBase(), "ayuda/Ayuda15.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        try {
            this.mensajeAyuda[16] = new URL(this.getCodeBase(), "ayuda/Ayuda16.html");
        }
        catch (MalformedURLException e) {
            System.out.println("URL defectuoso");
            this.stop();
        }
        this.imgCiclo = null;
        try {
            final URL url = new URL(this.getCodeBase(), "reloj/ciclo.png");
            this.imgCiclo = ImageIO.read(url);
        }
        catch (IOException ex) {}
        this.imgPlay = null;
        try {
            final URL url = new URL(this.getCodeBase(), "reloj/player_play.png");
            this.imgPlay = ImageIO.read(url);
        }
        catch (IOException ex2) {}
        this.imgPause = null;
        try {
            final URL url = new URL(this.getCodeBase(), "reloj/player_pause.png");
            this.imgPause = ImageIO.read(url);
        }
        catch (IOException ex3) {}
        this.imgPlayBack = null;
        try {
            final URL url = new URL(this.getCodeBase(), "reloj/player_play_back.png");
            this.imgPlayBack = ImageIO.read(url);
        }
        catch (IOException ex4) {}
        this.rapidez = 10;
        this.numeroDeSegmentos = 0;
        this.sentido = 0;
        this.recycle = 0;
        this.appletActivado = true;
        this.muevePeriodo = false;
        this.sts2[0] = 1;
        this.sts2[1] = 2;
        this.sts2[2] = 3;
        this.sts2[3] = 8;
        this.sts2[4] = 15;
        this.sts2[5] = 16;
        this.sts1[0] = 1;
        this.sts1[1] = 1;
        this.sts1[2] = 1;
        this.sts1[3] = 0;
        this.sts1[4] = 0;
        this.sts1[5] = 0;
        this.sts1[6] = 0;
        this.sts1[7] = 1;
        this.sts1[8] = 0;
        this.sts1[9] = 0;
        this.sts1[10] = 0;
        this.sts1[11] = 0;
        this.sts1[12] = 0;
        this.sts1[13] = 0;
        this.sts1[14] = 1;
        this.sts1[15] = 1;
        this.o[0] = 1;
        this.o[1] = 2;
        this.o[2] = 3;
        this.o[3] = 4;
        this.o[4] = 5;
        this.o[5] = 6;
        this.o[6] = 8;
        this.o[7] = 9;
        this.o[8] = 10;
        this.o[9] = 11;
        this.o[10] = 12;
        this.o[11] = 13;
        this.o[12] = 14;
        this.o[13] = 15;
        this.o[14] = 0;
        this.o[15] = 7;
        this.colorPeriodo = new Color[16];
        this.colorSegmento = new Color[16];
        this.colorNumero = new Color[16];
        this.colorPeriodo[0] = new Color(255, 0, 0);
        this.colorPeriodo[1] = new Color(255, 215, 0);
        this.colorPeriodo[2] = new Color(255, 180, 0);
        this.colorPeriodo[3] = new Color(255, 120, 0);
        this.colorPeriodo[4] = new Color(200, 60, 0);
        this.colorPeriodo[5] = new Color(130, 30, 0);
        this.colorPeriodo[6] = new Color(70, 0, 0);
        this.colorPeriodo[7] = new Color(0, 0, 255);
        this.colorPeriodo[8] = new Color(130, 240, 255);
        this.colorPeriodo[9] = new Color(20, 220, 255);
        this.colorPeriodo[10] = new Color(20, 150, 255);
        this.colorPeriodo[11] = new Color(0, 90, 255);
        this.colorPeriodo[12] = new Color(90, 20, 255);
        this.colorPeriodo[13] = new Color(180, 0, 180);
        this.colorPeriodo[14] = new Color(0, 153, 0);
        this.colorPeriodo[15] = new Color(0, 255, 0);
        this.colorSegmento[0] = new Color(255, 0, 0);
        this.colorSegmento[1] = new Color(255, 215, 0);
        this.colorSegmento[2] = new Color(255, 180, 0);
        this.colorSegmento[3] = new Color(255, 120, 0);
        this.colorSegmento[4] = new Color(200, 60, 0);
        this.colorSegmento[5] = new Color(130, 30, 0);
        this.colorSegmento[6] = new Color(70, 0, 0);
        this.colorSegmento[7] = new Color(0, 0, 255);
        this.colorSegmento[8] = new Color(130, 240, 255);
        this.colorSegmento[9] = new Color(20, 220, 255);
        this.colorSegmento[10] = new Color(20, 150, 255);
        this.colorSegmento[11] = new Color(0, 90, 255);
        this.colorSegmento[12] = new Color(90, 20, 255);
        this.colorSegmento[13] = new Color(180, 0, 180);
        this.colorSegmento[14] = new Color(0, 153, 0);
        this.colorSegmento[15] = new Color(0, 255, 0);
        this.colorNumero[0] = Color.white;
        this.colorNumero[1] = Color.black;
        this.colorNumero[2] = Color.black;
        this.colorNumero[3] = Color.white;
        this.colorNumero[4] = Color.white;
        this.colorNumero[5] = Color.white;
        this.colorNumero[6] = Color.white;
        this.colorNumero[7] = Color.white;
        this.colorNumero[8] = Color.black;
        this.colorNumero[9] = Color.black;
        this.colorNumero[10] = Color.white;
        this.colorNumero[11] = Color.white;
        this.colorNumero[12] = Color.white;
        this.colorNumero[13] = Color.white;
        this.colorNumero[14] = Color.white;
        this.colorNumero[15] = Color.black;
        (this.nombreCorto = new String[16])[0] = "Indicador Coincidente";
        this.nombreCorto[1] = "Actividad Econ\u00f3mica Mensual";
        this.nombreCorto[2] = "Actividad Industrial";
        this.nombreCorto[3] = "Ventas al Por Menor";
        this.nombreCorto[4] = "Asegurados en el IMSS";
        this.nombreCorto[5] = "Tasa de Desocupaci\u00f3n Urbana";
        this.nombreCorto[6] = "Importaciones Totales";
        this.nombreCorto[7] = "Indicador Adelantado o/";
        this.nombreCorto[8] = "Tendencia del Empleo";
        this.nombreCorto[9] = "Exportaciones no Petroleras";
        this.nombreCorto[10] = "\u00cdndice de P. y C. de la BMV";
        this.nombreCorto[11] = "Tipo de Cambio Real o/";
        this.nombreCorto[12] = "Tasa de Inter\u00e9s Interbancaria";
        this.nombreCorto[13] = "\u00cdndice S&P 500 (EUA)";
        this.nombreCorto[14] = "Confianza del Productor";
        this.nombreCorto[15] = "Confianza del Consumidor";
        (this.etiqueta = new String[16])[0] = "C";
        this.etiqueta[1] = "C1";
        this.etiqueta[2] = "C2";
        this.etiqueta[3] = "C3";
        this.etiqueta[4] = "C4";
        this.etiqueta[5] = "C5";
        this.etiqueta[6] = "C6";
        this.etiqueta[7] = "A";
        this.etiqueta[8] = "A1";
        this.etiqueta[9] = "A2";
        this.etiqueta[10] = "A3";
        this.etiqueta[11] = "A4";
        this.etiqueta[12] = "A5";
        this.etiqueta[13] = "A6";
        this.etiqueta[14] = "IP";
        this.etiqueta[15] = "IC";
        this.minX = this.calculaMinimoX();
        this.maxX = this.calculaMaximoX();
        this.minY = this.calculaMinimoY();
        this.maxY = this.calculaMaximoY();
        final double minT = this.calculaMinimoT();
        final double maxT = this.calculaMaximoT();
        final int anioMin = (int)minT;
        final int anioMax = (int)maxT;
        final int mesMin = (int)(1.0E-4 + (minT - anioMin) * 13.0);
        final int mesMax = (int)(1.0E-4 + (maxT - anioMax) * 13.0);
        this.rangoT = (anioMax - anioMin) * 12 + (mesMax - mesMin + 1);
        this.semiRangoX = Math.max(Math.abs(this.minX - 100.0), Math.abs(this.maxX - 100.0)) + 0.1;
        this.semiRangoY = Math.max(Math.abs(this.minY - 100.0), Math.abs(this.maxY - 100.0)) + 0.1;
        this.month = new int[(int)this.rangoT];
        this.year = new int[(int)this.rangoT];
        int m = mesMin;
        int a = anioMin;
        for (int t = 0; t < this.rangoT; ++t) {
            this.month[t] = m++;
            this.year[t] = a;
            if (m == 13) {
                m = 1;
                ++a;
            }
        }
        int inicio1 = 0;
        int inicio2 = 0;
        int inicio3 = 0;
        int inicio4 = 0;
        int inicio5 = 0;
        int inicio6 = 0;
        int inicio7 = 0;
        int inicio8 = 0;
        int inicio9 = 0;
        int inicio10 = 0;
        int inicio11 = 0;
        int inicio12 = 0;
        int inicio13 = 0;
        int inicio14 = 0;
        int inicio15 = 0;
        int inicio16 = 0;
        int final1 = 0;
        int final2 = 0;
        int final3 = 0;
        int final4 = 0;
        int final5 = 0;
        int final6 = 0;
        int final7 = 0;
        int final8 = 0;
        int final9 = 0;
        int final10 = 0;
        int final11 = 0;
        int final12 = 0;
        int final13 = 0;
        int final14 = 0;
        int final15 = 0;
        int final16 = 0;
        for (int r = 0; r < this.rangoT; ++r) {
            if (this.month1[0] == this.month[r] && this.year1[0] == this.year[r]) {
                inicio1 = r;
            }
            if (this.month2[0] == this.month[r] && this.year2[0] == this.year[r]) {
                inicio2 = r;
            }
            if (this.month3[0] == this.month[r] && this.year3[0] == this.year[r]) {
                inicio3 = r;
            }
            if (this.month4[0] == this.month[r] && this.year4[0] == this.year[r]) {
                inicio4 = r;
            }
            if (this.month5[0] == this.month[r] && this.year5[0] == this.year[r]) {
                inicio5 = r;
            }
            if (this.month6[0] == this.month[r] && this.year6[0] == this.year[r]) {
                inicio6 = r;
            }
            if (this.month7[0] == this.month[r] && this.year7[0] == this.year[r]) {
                inicio7 = r;
            }
            if (this.month8[0] == this.month[r] && this.year8[0] == this.year[r]) {
                inicio8 = r;
            }
            if (this.month9[0] == this.month[r] && this.year9[0] == this.year[r]) {
                inicio9 = r;
            }
            if (this.month10[0] == this.month[r] && this.year10[0] == this.year[r]) {
                inicio10 = r;
            }
            if (this.month11[0] == this.month[r] && this.year11[0] == this.year[r]) {
                inicio11 = r;
            }
            if (this.month12[0] == this.month[r] && this.year12[0] == this.year[r]) {
                inicio12 = r;
            }
            if (this.month13[0] == this.month[r] && this.year13[0] == this.year[r]) {
                inicio13 = r;
            }
            if (this.month14[0] == this.month[r] && this.year14[0] == this.year[r]) {
                inicio14 = r;
            }
            if (this.month15[0] == this.month[r] && this.year15[0] == this.year[r]) {
                inicio15 = r;
            }
            if (this.month16[0] == this.month[r] && this.year16[0] == this.year[r]) {
                inicio16 = r;
            }
            if (this.month1[this.fY1.length - 1] == this.month[r] && this.year1[this.fY1.length - 1] == this.year[r]) {
                final1 = r;
            }
            if (this.month2[this.fY2.length - 1] == this.month[r] && this.year2[this.fY2.length - 1] == this.year[r]) {
                final2 = r;
            }
            if (this.month3[this.fY3.length - 1] == this.month[r] && this.year3[this.fY3.length - 1] == this.year[r]) {
                final3 = r;
            }
            if (this.month4[this.fY4.length - 1] == this.month[r] && this.year4[this.fY4.length - 1] == this.year[r]) {
                final4 = r;
            }
            if (this.month5[this.fY5.length - 1] == this.month[r] && this.year5[this.fY5.length - 1] == this.year[r]) {
                final5 = r;
            }
            if (this.month6[this.fY6.length - 1] == this.month[r] && this.year6[this.fY6.length - 1] == this.year[r]) {
                final6 = r;
            }
            if (this.month7[this.fY7.length - 1] == this.month[r] && this.year7[this.fY7.length - 1] == this.year[r]) {
                final7 = r;
            }
            if (this.month8[this.fY8.length - 1] == this.month[r] && this.year8[this.fY8.length - 1] == this.year[r]) {
                final8 = r;
            }
            if (this.month9[this.fY9.length - 1] == this.month[r] && this.year9[this.fY9.length - 1] == this.year[r]) {
                final9 = r;
            }
            if (this.month10[this.fY10.length - 1] == this.month[r] && this.year10[this.fY10.length - 1] == this.year[r]) {
                final10 = r;
            }
            if (this.month11[this.fY11.length - 1] == this.month[r] && this.year11[this.fY11.length - 1] == this.year[r]) {
                final11 = r;
            }
            if (this.month12[this.fY12.length - 1] == this.month[r] && this.year12[this.fY12.length - 1] == this.year[r]) {
                final12 = r;
            }
            if (this.month13[this.fY13.length - 1] == this.month[r] && this.year13[this.fY13.length - 1] == this.year[r]) {
                final13 = r;
            }
            if (this.month14[this.fY14.length - 1] == this.month[r] && this.year14[this.fY14.length - 1] == this.year[r]) {
                final14 = r;
            }
            if (this.month15[this.fY15.length - 1] == this.month[r] && this.year15[this.fY15.length - 1] == this.year[r]) {
                final15 = r;
            }
            if (this.month16[this.fY16.length - 1] == this.month[r] && this.year16[this.fY16.length - 1] == this.year[r]) {
                final16 = r;
            }
        }
        this.fY = new double[(int)this.rangoT][16];
        this.fM = new double[(int)this.rangoT][16];
        for (int r = 0; r < this.rangoT; ++r) {
            this.fY[r][0] = ((inicio1 <= r && r <= final1) ? this.fY1[r - inicio1] : -1.0);
            this.fM[r][0] = ((inicio1 <= r && r <= final1) ? this.fM1[r - inicio1] : -1.0);
            this.fY[r][1] = ((inicio2 <= r && r <= final2) ? this.fY2[r - inicio2] : -1.0);
            this.fM[r][1] = ((inicio2 <= r && r <= final2) ? this.fM2[r - inicio2] : -1.0);
            this.fY[r][2] = ((inicio3 <= r && r <= final3) ? this.fY3[r - inicio3] : -1.0);
            this.fM[r][2] = ((inicio3 <= r && r <= final3) ? this.fM3[r - inicio3] : -1.0);
            this.fY[r][3] = ((inicio4 <= r && r <= final4) ? this.fY4[r - inicio4] : -1.0);
            this.fM[r][3] = ((inicio4 <= r && r <= final4) ? this.fM4[r - inicio4] : -1.0);
            this.fY[r][4] = ((inicio5 <= r && r <= final5) ? this.fY5[r - inicio5] : -1.0);
            this.fM[r][4] = ((inicio5 <= r && r <= final5) ? this.fM5[r - inicio5] : -1.0);
            this.fY[r][5] = ((inicio6 <= r && r <= final6) ? this.fY6[r - inicio6] : -1.0);
            this.fM[r][5] = ((inicio6 <= r && r <= final6) ? this.fM6[r - inicio6] : -1.0);
            this.fY[r][6] = ((inicio7 <= r && r <= final7) ? this.fY7[r - inicio7] : -1.0);
            this.fM[r][6] = ((inicio7 <= r && r <= final7) ? this.fM7[r - inicio7] : -1.0);
            this.fY[r][7] = ((inicio8 <= r && r <= final8) ? this.fY8[r - inicio8] : -1.0);
            this.fM[r][7] = ((inicio8 <= r && r <= final8) ? this.fM8[r - inicio8] : -1.0);
            this.fY[r][8] = ((inicio9 <= r && r <= final9) ? this.fY9[r - inicio9] : -1.0);
            this.fM[r][8] = ((inicio9 <= r && r <= final9) ? this.fM9[r - inicio9] : -1.0);
            this.fY[r][9] = ((inicio10 <= r && r <= final10) ? this.fY10[r - inicio10] : -1.0);
            this.fM[r][9] = ((inicio10 <= r && r <= final10) ? this.fM10[r - inicio10] : -1.0);
            this.fY[r][10] = ((inicio11 <= r && r <= final11) ? this.fY11[r - inicio11] : -1.0);
            this.fM[r][10] = ((inicio11 <= r && r <= final11) ? this.fM11[r - inicio11] : -1.0);
            this.fY[r][11] = ((inicio12 <= r && r <= final12) ? this.fY12[r - inicio12] : -1.0);
            this.fM[r][11] = ((inicio12 <= r && r <= final12) ? this.fM12[r - inicio12] : -1.0);
            this.fY[r][12] = ((inicio13 <= r && r <= final13) ? this.fY13[r - inicio13] : -1.0);
            this.fM[r][12] = ((inicio13 <= r && r <= final13) ? this.fM13[r - inicio13] : -1.0);
            this.fY[r][13] = ((inicio14 <= r && r <= final14) ? this.fY14[r - inicio14] : -1.0);
            this.fM[r][13] = ((inicio14 <= r && r <= final14) ? this.fM14[r - inicio14] : -1.0);
            this.fY[r][14] = ((inicio15 <= r && r <= final15) ? this.fY15[r - inicio15] : -1.0);
            this.fM[r][14] = ((inicio15 <= r && r <= final15) ? this.fM15[r - inicio15] : -1.0);
            this.fY[r][15] = ((inicio16 <= r && r <= final16) ? this.fY16[r - inicio16] : -1.0);
            this.fM[r][15] = ((inicio16 <= r && r <= final16) ? this.fM16[r - inicio16] : -1.0);
        }
        this.periodo = (int)this.rangoT - 1;
        while (this.fY[this.periodo][0] < 0.0) {
            --this.periodo;
        }
    }
    
    private void cargaDatosEnMemoria() {
        this.FileToRead = "datos/COINCDENTE.csv";
        this.leeArchivo();
        this.fY1 = this.extraeSerieDeTiempo(0);
        this.fM1 = this.calculaDerivada(this.fY1);
        this.FileToRead = "datos/PIB.csv";
        this.leeArchivo();
        this.fY2 = this.extraeSerieDeTiempo(1);
        this.fM2 = this.calculaDerivada(this.fY2);
        this.FileToRead = "datos/IVFP.csv";
        this.leeArchivo();
        this.fY3 = this.extraeSerieDeTiempo(2);
        this.fM3 = this.calculaDerivada(this.fY3);
        this.FileToRead = "datos/VXM.csv";
        this.leeArchivo();
        this.fY4 = this.extraeSerieDeTiempo(3);
        this.fM4 = this.calculaDerivada(this.fY4);
        this.FileToRead = "datos/IMSS.csv";
        this.leeArchivo();
        this.fY5 = this.extraeSerieDeTiempo(4);
        this.fM5 = this.calculaDerivada(this.fY5);
        this.FileToRead = "datos/TDU.csv";
        this.leeArchivo();
        this.fY6 = this.extraeSerieDeTiempo(5);
        this.fM6 = this.calculaDerivada(this.fY6);
        this.FileToRead = "datos/IMP_TOT.csv";
        this.leeArchivo();
        this.fY7 = this.extraeSerieDeTiempo(6);
        this.fM7 = this.calculaDerivada(this.fY7);
        this.FileToRead = "datos/ADELANTADO.csv";
        this.leeArchivo();
        this.fY8 = this.extraeSerieDeTiempo(7);
        this.fM8 = this.calculaDerivada(this.fY8);
        this.FileToRead = "datos/TEM.csv";
        this.leeArchivo();
        this.fY9 = this.extraeSerieDeTiempo(8);
        this.fM9 = this.calculaDerivada(this.fY9);
        this.FileToRead = "datos/ENP.csv";
        this.leeArchivo();
        this.fY10 = this.extraeSerieDeTiempo(9);
        this.fM10 = this.calculaDerivada(this.fY10);
        this.FileToRead = "datos/IBMV.csv";
        this.leeArchivo();
        this.fY11 = this.extraeSerieDeTiempo(10);
        this.fM11 = this.calculaDerivada(this.fY11);
        this.FileToRead = "datos/TCR.csv";
        this.leeArchivo();
        this.fY12 = this.extraeSerieDeTiempo(11);
        this.fM12 = this.calculaDerivada(this.fY12);
        this.FileToRead = "datos/TIIE.csv";
        this.leeArchivo();
        this.fY13 = this.extraeSerieDeTiempo(12);
        this.fM13 = this.calculaDerivada(this.fY13);
        this.FileToRead = "datos/IPEUA.csv";
        this.leeArchivo();
        this.fY14 = this.extraeSerieDeTiempo(13);
        this.fM14 = this.calculaDerivada(this.fY14);
        this.FileToRead = "datos/ICP.csv";
        this.leeArchivo();
        this.fY15 = this.extraeSerieDeTiempo(14);
        this.fM15 = this.calculaDerivada(this.fY15);
        this.FileToRead = "datos/ICC.csv";
        this.leeArchivo();
        this.fY16 = this.extraeSerieDeTiempo(15);
        this.fM16 = this.calculaDerivada(this.fY16);
    }
    
    private double[] extraeSerieDeTiempo(final int index) {
        final StringTokenizer tokens = new StringTokenizer(this.buf.toString(), "[,\n]");
        final int n = tokens.countTokens() / 3 - 1;
        final String[] encabezado = new String[3];
        switch (index) {
            case 0: {
                this.year1 = new int[n];
                this.month1 = new int[n];
                break;
            }
            case 1: {
                this.year2 = new int[n];
                this.month2 = new int[n];
                break;
            }
            case 2: {
                this.year3 = new int[n];
                this.month3 = new int[n];
                break;
            }
            case 3: {
                this.year4 = new int[n];
                this.month4 = new int[n];
                break;
            }
            case 4: {
                this.year5 = new int[n];
                this.month5 = new int[n];
                break;
            }
            case 5: {
                this.year6 = new int[n];
                this.month6 = new int[n];
                break;
            }
            case 6: {
                this.year7 = new int[n];
                this.month7 = new int[n];
                break;
            }
            case 7: {
                this.year8 = new int[n];
                this.month8 = new int[n];
                break;
            }
            case 8: {
                this.year9 = new int[n];
                this.month9 = new int[n];
                break;
            }
            case 9: {
                this.year10 = new int[n];
                this.month10 = new int[n];
                break;
            }
            case 10: {
                this.year11 = new int[n];
                this.month11 = new int[n];
                break;
            }
            case 11: {
                this.year12 = new int[n];
                this.month12 = new int[n];
                break;
            }
            case 12: {
                this.year13 = new int[n];
                this.month13 = new int[n];
                break;
            }
            case 13: {
                this.year14 = new int[n];
                this.month14 = new int[n];
                break;
            }
            case 14: {
                this.year15 = new int[n];
                this.month15 = new int[n];
                break;
            }
            case 15: {
                this.year16 = new int[n];
                this.month16 = new int[n];
                break;
            }
        }
        final double[] y = new double[n];
        for (int i = 0; i < 3; ++i) {
            encabezado[i] = tokens.nextToken();
        }
        for (int k = 0; k < n; ++k) {
            for (int j = 0; j < 3; ++j) {
                if (j == 0) {
                    switch (index) {
                        case 0: {
                            this.year1[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 1: {
                            this.year2[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 2: {
                            this.year3[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 3: {
                            this.year4[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 4: {
                            this.year5[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 5: {
                            this.year6[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 6: {
                            this.year7[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 7: {
                            this.year8[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 8: {
                            this.year9[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 9: {
                            this.year10[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 10: {
                            this.year11[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 11: {
                            this.year12[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 12: {
                            this.year13[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 13: {
                            this.year14[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 14: {
                            this.year15[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 15: {
                            this.year16[k] = new Integer(tokens.nextToken());
                            break;
                        }
                    }
                }
                if (j == 1) {
                    switch (index) {
                        case 0: {
                            this.month1[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 1: {
                            this.month2[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 2: {
                            this.month3[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 3: {
                            this.month4[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 4: {
                            this.month5[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 5: {
                            this.month6[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 6: {
                            this.month7[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 7: {
                            this.month8[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 8: {
                            this.month9[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 9: {
                            this.month10[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 10: {
                            this.month11[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 11: {
                            this.month12[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 12: {
                            this.month13[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 13: {
                            this.month14[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 14: {
                            this.month15[k] = new Integer(tokens.nextToken());
                            break;
                        }
                        case 15: {
                            this.month16[k] = new Integer(tokens.nextToken());
                            break;
                        }
                    }
                }
                if (j == 2) {
                    y[k] = new Double(tokens.nextToken());
                }
            }
        }
        return y;
    }
    
    private double[] calculaDerivada(final double[] st) {
        final int n = st.length;
        final double[] m = new double[n];
        for (int i = 1; i < n; ++i) {
            m[i] = 100.0 + 10.0 * (st[i] - st[i - 1]);
        }
        m[0] = (m[1] + m[2]) / 2.0;
        return m;
    }
    
    private double calculaMinimoX() {
        double minimo = this.fM1[0];
        for (int n = this.fM1.length, i = 1; i < n; ++i) {
            if (this.fM1[i] < minimo) {
                minimo = this.fM1[i];
            }
        }
        for (int n = this.fM2.length, i = 0; i < n; ++i) {
            if (this.fM2[i] < minimo) {
                minimo = this.fM2[i];
            }
        }
        for (int n = this.fM3.length, i = 0; i < n; ++i) {
            if (this.fM3[i] < minimo) {
                minimo = this.fM3[i];
            }
        }
        for (int n = this.fM4.length, i = 0; i < n; ++i) {
            if (this.fM4[i] < minimo) {
                minimo = this.fM4[i];
            }
        }
        for (int n = this.fM5.length, i = 0; i < n; ++i) {
            if (this.fM5[i] < minimo) {
                minimo = this.fM5[i];
            }
        }
        for (int n = this.fM6.length, i = 0; i < n; ++i) {
            if (this.fM6[i] < minimo) {
                minimo = this.fM6[i];
            }
        }
        for (int n = this.fM7.length, i = 0; i < n; ++i) {
            if (this.fM7[i] < minimo) {
                minimo = this.fM7[i];
            }
        }
        for (int n = this.fM8.length, i = 0; i < n; ++i) {
            if (this.fM8[i] < minimo) {
                minimo = this.fM8[i];
            }
        }
        for (int n = this.fM9.length, i = 0; i < n; ++i) {
            if (this.fM9[i] < minimo) {
                minimo = this.fM9[i];
            }
        }
        for (int n = this.fM10.length, i = 0; i < n; ++i) {
            if (this.fM10[i] < minimo) {
                minimo = this.fM10[i];
            }
        }
        for (int n = this.fM11.length, i = 0; i < n; ++i) {
            if (this.fM11[i] < minimo) {
                minimo = this.fM11[i];
            }
        }
        for (int n = this.fM12.length, i = 0; i < n; ++i) {
            if (this.fM12[i] < minimo) {
                minimo = this.fM12[i];
            }
        }
        for (int n = this.fM13.length, i = 0; i < n; ++i) {
            if (this.fM13[i] < minimo) {
                minimo = this.fM13[i];
            }
        }
        for (int n = this.fM14.length, i = 0; i < n; ++i) {
            if (this.fM14[i] < minimo) {
                minimo = this.fM14[i];
            }
        }
        for (int n = this.fM15.length, i = 0; i < n; ++i) {
            if (this.fM15[i] < minimo) {
                minimo = this.fM15[i];
            }
        }
        for (int n = this.fM16.length, i = 0; i < n; ++i) {
            if (this.fM16[i] < minimo) {
                minimo = this.fM16[i];
            }
        }
        return minimo;
    }
    
    private double calculaMaximoX() {
        double maximo = this.fM1[0];
        for (int n = this.fM1.length, i = 1; i < n; ++i) {
            if (this.fM1[i] > maximo) {
                maximo = this.fM1[i];
            }
        }
        for (int n = this.fM2.length, i = 0; i < n; ++i) {
            if (this.fM2[i] > maximo) {
                maximo = this.fM2[i];
            }
        }
        for (int n = this.fM3.length, i = 0; i < n; ++i) {
            if (this.fM3[i] > maximo) {
                maximo = this.fM3[i];
            }
        }
        for (int n = this.fM4.length, i = 0; i < n; ++i) {
            if (this.fM4[i] > maximo) {
                maximo = this.fM4[i];
            }
        }
        for (int n = this.fM5.length, i = 0; i < n; ++i) {
            if (this.fM5[i] > maximo) {
                maximo = this.fM5[i];
            }
        }
        for (int n = this.fM6.length, i = 0; i < n; ++i) {
            if (this.fM6[i] > maximo) {
                maximo = this.fM6[i];
            }
        }
        for (int n = this.fM7.length, i = 0; i < n; ++i) {
            if (this.fM7[i] > maximo) {
                maximo = this.fM7[i];
            }
        }
        for (int n = this.fM8.length, i = 0; i < n; ++i) {
            if (this.fM8[i] > maximo) {
                maximo = this.fM8[i];
            }
        }
        for (int n = this.fM9.length, i = 0; i < n; ++i) {
            if (this.fM9[i] > maximo) {
                maximo = this.fM9[i];
            }
        }
        for (int n = this.fM10.length, i = 0; i < n; ++i) {
            if (this.fM10[i] > maximo) {
                maximo = this.fM10[i];
            }
        }
        for (int n = this.fM11.length, i = 0; i < n; ++i) {
            if (this.fM11[i] > maximo) {
                maximo = this.fM11[i];
            }
        }
        for (int n = this.fM12.length, i = 0; i < n; ++i) {
            if (this.fM12[i] > maximo) {
                maximo = this.fM12[i];
            }
        }
        for (int n = this.fM13.length, i = 0; i < n; ++i) {
            if (this.fM13[i] > maximo) {
                maximo = this.fM13[i];
            }
        }
        for (int n = this.fM14.length, i = 0; i < n; ++i) {
            if (this.fM14[i] > maximo) {
                maximo = this.fM14[i];
            }
        }
        for (int n = this.fM15.length, i = 0; i < n; ++i) {
            if (this.fM15[i] > maximo) {
                maximo = this.fM15[i];
            }
        }
        for (int n = this.fM16.length, i = 0; i < n; ++i) {
            if (this.fM16[i] > maximo) {
                maximo = this.fM16[i];
            }
        }
        return maximo;
    }
    
    private double calculaMinimoY() {
        double minimo = this.fY1[0];
        for (int n = this.fY1.length, i = 1; i < n; ++i) {
            if (this.fY1[i] < minimo) {
                minimo = this.fY1[i];
            }
        }
        for (int n = this.fY2.length, i = 0; i < n; ++i) {
            if (this.fY2[i] < minimo) {
                minimo = this.fY2[i];
            }
        }
        for (int n = this.fY3.length, i = 0; i < n; ++i) {
            if (this.fY3[i] < minimo) {
                minimo = this.fY3[i];
            }
        }
        for (int n = this.fY4.length, i = 0; i < n; ++i) {
            if (this.fY4[i] < minimo) {
                minimo = this.fY4[i];
            }
        }
        for (int n = this.fY5.length, i = 0; i < n; ++i) {
            if (this.fY5[i] < minimo) {
                minimo = this.fY5[i];
            }
        }
        for (int n = this.fY6.length, i = 0; i < n; ++i) {
            if (this.fY6[i] < minimo) {
                minimo = this.fY6[i];
            }
        }
        for (int n = this.fY7.length, i = 0; i < n; ++i) {
            if (this.fY7[i] < minimo) {
                minimo = this.fY7[i];
            }
        }
        for (int n = this.fY8.length, i = 0; i < n; ++i) {
            if (this.fY8[i] < minimo) {
                minimo = this.fY8[i];
            }
        }
        for (int n = this.fY9.length, i = 0; i < n; ++i) {
            if (this.fY9[i] < minimo) {
                minimo = this.fY9[i];
            }
        }
        for (int n = this.fY10.length, i = 0; i < n; ++i) {
            if (this.fY10[i] < minimo) {
                minimo = this.fY10[i];
            }
        }
        for (int n = this.fY11.length, i = 0; i < n; ++i) {
            if (this.fY11[i] < minimo) {
                minimo = this.fY11[i];
            }
        }
        for (int n = this.fY12.length, i = 0; i < n; ++i) {
            if (this.fY12[i] < minimo) {
                minimo = this.fY12[i];
            }
        }
        for (int n = this.fY13.length, i = 0; i < n; ++i) {
            if (this.fY13[i] < minimo) {
                minimo = this.fY13[i];
            }
        }
        for (int n = this.fY14.length, i = 0; i < n; ++i) {
            if (this.fY14[i] < minimo) {
                minimo = this.fY14[i];
            }
        }
        for (int n = this.fY15.length, i = 0; i < n; ++i) {
            if (this.fY15[i] < minimo) {
                minimo = this.fY15[i];
            }
        }
        for (int n = this.fY16.length, i = 0; i < n; ++i) {
            if (this.fY16[i] < minimo) {
                minimo = this.fY16[i];
            }
        }
        return minimo;
    }
    
    private double calculaMaximoY() {
        double maximo = this.fY1[0];
        for (int n = this.fY1.length, i = 1; i < n; ++i) {
            if (this.fY1[i] > maximo) {
                maximo = this.fY1[i];
            }
        }
        for (int n = this.fY2.length, i = 0; i < n; ++i) {
            if (this.fY2[i] > maximo) {
                maximo = this.fY2[i];
            }
        }
        for (int n = this.fY3.length, i = 0; i < n; ++i) {
            if (this.fY3[i] > maximo) {
                maximo = this.fY3[i];
            }
        }
        for (int n = this.fY4.length, i = 0; i < n; ++i) {
            if (this.fY4[i] > maximo) {
                maximo = this.fY4[i];
            }
        }
        for (int n = this.fY5.length, i = 0; i < n; ++i) {
            if (this.fY5[i] > maximo) {
                maximo = this.fY5[i];
            }
        }
        for (int n = this.fY6.length, i = 0; i < n; ++i) {
            if (this.fY6[i] > maximo) {
                maximo = this.fY6[i];
            }
        }
        for (int n = this.fY7.length, i = 0; i < n; ++i) {
            if (this.fY7[i] > maximo) {
                maximo = this.fY7[i];
            }
        }
        for (int n = this.fY8.length, i = 0; i < n; ++i) {
            if (this.fY8[i] > maximo) {
                maximo = this.fY8[i];
            }
        }
        for (int n = this.fY9.length, i = 0; i < n; ++i) {
            if (this.fY9[i] > maximo) {
                maximo = this.fY9[i];
            }
        }
        for (int n = this.fY10.length, i = 0; i < n; ++i) {
            if (this.fY10[i] > maximo) {
                maximo = this.fY10[i];
            }
        }
        for (int n = this.fY11.length, i = 0; i < n; ++i) {
            if (this.fY11[i] > maximo) {
                maximo = this.fY11[i];
            }
        }
        for (int n = this.fY12.length, i = 0; i < n; ++i) {
            if (this.fY12[i] > maximo) {
                maximo = this.fY12[i];
            }
        }
        for (int n = this.fY13.length, i = 0; i < n; ++i) {
            if (this.fY13[i] > maximo) {
                maximo = this.fY13[i];
            }
        }
        for (int n = this.fY14.length, i = 0; i < n; ++i) {
            if (this.fY14[i] > maximo) {
                maximo = this.fY14[i];
            }
        }
        for (int n = this.fY15.length, i = 0; i < n; ++i) {
            if (this.fY15[i] > maximo) {
                maximo = this.fY15[i];
            }
        }
        for (int n = this.fY16.length, i = 0; i < n; ++i) {
            if (this.fY16[i] > maximo) {
                maximo = this.fY16[i];
            }
        }
        return maximo;
    }
    
    private double calculaMinimoT() {
        double minimo = this.year1[0] + this.month1[0] / 13.0;
        double m = this.year2[0] + this.month2[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year3[0] + this.month3[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year4[0] + this.month4[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year5[0] + this.month5[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year6[0] + this.month6[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year7[0] + this.month7[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year8[0] + this.month8[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year9[0] + this.month9[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year10[0] + this.month10[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year11[0] + this.month11[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year12[0] + this.month12[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year13[0] + this.month13[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year14[0] + this.month14[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year15[0] + this.month15[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        m = this.year16[0] + this.month16[0] / 13.0;
        if (m < minimo) {
            minimo = m;
        }
        return minimo;
    }
    
    private double calculaMaximoT() {
        double maximo = this.year1[this.fY1.length - 1] + this.month1[this.fY1.length - 1] / 13.0;
        double m = this.year2[this.fY2.length - 1] + this.month2[this.fY2.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year3[this.fY3.length - 1] + this.month3[this.fY3.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year4[this.fY4.length - 1] + this.month4[this.fY4.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year5[this.fY5.length - 1] + this.month5[this.fY5.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year6[this.fY6.length - 1] + this.month6[this.fY6.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year7[this.fY7.length - 1] + this.month7[this.fY7.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year8[this.fY8.length - 1] + this.month8[this.fY8.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year9[this.fY9.length - 1] + this.month9[this.fY9.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year10[this.fY10.length - 1] + this.month10[this.fY10.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year11[this.fY11.length - 1] + this.month11[this.fY11.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year12[this.fY12.length - 1] + this.month12[this.fY12.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year13[this.fY13.length - 1] + this.month13[this.fY13.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year14[this.fY14.length - 1] + this.month14[this.fY14.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year15[this.fY15.length - 1] + this.month15[this.fY15.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        m = this.year16[this.fY16.length - 1] + this.month16[this.fY16.length - 1] / 13.0;
        if (m > maximo) {
            maximo = m;
        }
        return maximo;
    }
    
    private void leeArchivo() {
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), this.FileToRead);
        }
        catch (MalformedURLException e) {
            System.out.println("URL Defectuoso");
            this.stop();
        }
        try {
            final InputStream in = url.openStream();
            final BufferedReader dis = new BufferedReader(new InputStreamReader(in));
            this.buf = new StringBuffer();
            String linea;
            while ((linea = dis.readLine()) != null) {
                this.buf.append(linea).append("\n");
            }
            in.close();
        }
        catch (IOException ex) {}
    }
    
    private int xT_A(final double x) {
        return (int)Math.round(204.0 + 200.0 * ((x - 100.0) / this.semiRangoX));
    }
    
    private int yT_A(final double y) {
        return (int)Math.round(204.0 - 200.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int xT_B(final double x) {
        return (int)Math.round(430.0 + 297.0 * (x / this.rangoT));
    }
    
    private int yT_B1(final double y) {
        return (int)Math.round(39.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int yT_B2(final double y) {
        return (int)Math.round(105.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int yT_B3(final double y) {
        return (int)Math.round(171.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int yT_B4(final double y) {
        return (int)Math.round(237.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int yT_B5(final double y) {
        return (int)Math.round(303.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private int yT_B6(final double y) {
        return (int)Math.round(369.0 - 33.0 * ((y - 100.0) / this.semiRangoY));
    }
    
    private double FDEx(double x) {
        x = (x - 100.0) / this.semiRangoX;
        double t = 0.0;
        if (-1.0 <= x && x < -0.4) {
            t = (x - 2.0) / 3.0;
        }
        if (-0.4 <= x && x < 0.4) {
            t = 2.0 * x;
        }
        if (0.4 <= x && x <= 1.0) {
            t = (x + 2.0) / 3.0;
        }
        return 100.0 + this.semiRangoX * t;
    }
    
    private double FDEy(double y) {
        y = (y - 100.0) / this.semiRangoY;
        double t = 0.0;
        if (-1.0 <= y && y < -0.4) {
            t = (y - 2.0) / 3.0;
        }
        if (-0.4 <= y && y < 0.4) {
            t = 2.0 * y;
        }
        if (0.4 <= y && y <= 1.0) {
            t = (y + 2.0) / 3.0;
        }
        return 100.0 + this.semiRangoY * t;
    }
    
    private double TCC(final double y) {
        double yt = y - 100.0;
        yt *= 1.5;
        yt = ((Math.abs(yt) <= this.semiRangoY) ? yt : ((yt >= 0.0) ? this.semiRangoY : (-this.semiRangoY)));
        yt += 100.0;
        return yt;
    }
    
    private String nombreMes(final int m) {
        String nombre_mes = "";
        switch (m) {
            case 1: {
                nombre_mes = "Ene";
                break;
            }
            case 2: {
                nombre_mes = "Feb";
                break;
            }
            case 3: {
                nombre_mes = "Mar";
                break;
            }
            case 4: {
                nombre_mes = "Abr";
                break;
            }
            case 5: {
                nombre_mes = "May";
                break;
            }
            case 6: {
                nombre_mes = "Jun";
                break;
            }
            case 7: {
                nombre_mes = "Jul";
                break;
            }
            case 8: {
                nombre_mes = "Ago";
                break;
            }
            case 9: {
                nombre_mes = "Sep";
                break;
            }
            case 10: {
                nombre_mes = "Oct";
                break;
            }
            case 11: {
                nombre_mes = "Nov";
                break;
            }
            case 12: {
                nombre_mes = "Dic";
                break;
            }
        }
        return nombre_mes;
    }
}
