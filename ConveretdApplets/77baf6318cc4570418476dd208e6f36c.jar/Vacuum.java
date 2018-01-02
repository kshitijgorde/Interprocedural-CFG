import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.awt.Image;
import javax.swing.JLabel;
import lib.DChTCP;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vacuum extends JApplet implements Runnable
{
    DChTCP dch;
    Thread thread;
    static String DChServer;
    static int DChPort;
    MyJPanel pnlGraph;
    JLabel lblMode;
    JLabel lblCurrent;
    JLabel lblIntCurrent;
    JLabel lblLife;
    JLabel lblPav;
    JLabel lblPoverI;
    JLabel lblItau;
    JLabel lblLifeCap;
    JLabel lblLifeUni;
    Image imgNum;
    int imgx;
    int imgy;
    String Date_Time;
    int Mode;
    int OnOff;
    int ChannelPermit;
    int Topup;
    int GateOpen;
    float InjRate;
    float InjFreq;
    float BH32L_IMON;
    double Current;
    float Life_fast;
    double IntCurrent;
    double Itau;
    double PoverI;
    double Pav;
    double[] RingBAG;
    double[] PBL21;
    double[] BTVac;
    final double LogN;
    final int GScale = 9;
    final int GraphLX = 605;
    final int GraphLY = 250;
    final int GraphOX = 25;
    final int GraphOY = 25;
    final int GraphRingLX = 486;
    final int GraphRingLY = 250;
    final int GraphRingOX = 0;
    final int GraphRingOY = 0;
    final int GraphBL21LX = 36;
    final int GraphBL21LY = 250;
    final int GraphBL21OX = 489;
    final int GraphBL21OY = 0;
    final int GraphBTLX = 72;
    final int GraphBTLY = 250;
    final int GraphBTOX = 534;
    final int GraphBTOY = 0;
    
    public Vacuum() {
        this.thread = null;
        this.lblMode = new JLabel("", 0);
        this.lblCurrent = new JLabel("", 4);
        this.lblIntCurrent = new JLabel("", 4);
        this.lblLife = new JLabel("", 4);
        this.lblPav = new JLabel("", 4);
        this.lblPoverI = new JLabel("", 4);
        this.lblItau = new JLabel("", 4);
        this.lblLifeCap = new JLabel("", 4);
        this.lblLifeUni = new JLabel("", 2);
        this.imgx = 4;
        this.imgy = 5;
        this.Date_Time = "";
        this.Mode = 0;
        this.OnOff = 0;
        this.ChannelPermit = 0;
        this.Topup = 0;
        this.GateOpen = 0;
        this.InjRate = 0.0f;
        this.InjFreq = 0.0f;
        this.BH32L_IMON = 0.0f;
        this.Current = 0.0;
        this.Life_fast = 0.0f;
        this.IntCurrent = 0.0;
        this.Itau = 0.0;
        this.PoverI = 0.0;
        this.Pav = 0.0;
        this.RingBAG = new double[54];
        this.PBL21 = new double[4];
        this.BTVac = new double[8];
        this.LogN = Math.log(10.0);
    }
    
    @Override
    public void init() {
        try {
            try {
                this.dch = new DChTCP(this.getDocumentBase().getHost(), 5500);
            }
            catch (NullPointerException ex) {
                this.dch = new DChTCP(Vacuum.DChServer, Vacuum.DChPort);
            }
            this.dch.open_dch();
        }
        catch (IOException ex2) {}
        this.imgNum = new ImageIcon(this.getClass().getResource("imgNum.png")).getImage();
        final Font font = new Font("SansSerif", 0, 18);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(3, 1));
        final JLabel label = new JLabel("Averaged Pressure", 4);
        label.setFont(font);
        panel.add(label);
        final JLabel label2 = new JLabel("Pav/Current", 4);
        label2.setFont(font);
        panel.add(label2);
        final JLabel label3 = new JLabel("Integrated Current", 4);
        label3.setFont(font);
        panel.add(label3);
        final JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new GridLayout(3, 1));
        this.lblPav.setFont(font);
        panel2.add(this.lblPav);
        this.lblPoverI.setFont(font);
        panel2.add(this.lblPoverI);
        this.lblIntCurrent.setFont(font);
        panel2.add(this.lblIntCurrent);
        final JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        panel3.setLayout(new GridLayout(3, 1));
        final JLabel label4 = new JLabel("Pa", 2);
        label4.setFont(font);
        panel3.add(label4);
        final JLabel label5 = new JLabel("Pa/A", 2);
        label5.setFont(font);
        panel3.add(label5);
        final JLabel label6 = new JLabel("A*h", 2);
        label6.setFont(font);
        panel3.add(label6);
        final JPanel panel4 = new JPanel();
        panel4.setBackground(Color.lightGray);
        panel4.setLayout(new BorderLayout(5, 0));
        panel4.add(panel2, "Center");
        panel4.add(panel3, "East");
        final JPanel panel5 = new JPanel();
        panel5.setBackground(Color.lightGray);
        panel5.setLayout(new BorderLayout(20, 0));
        panel5.add(panel, "Center");
        panel5.add(panel4, "East");
        final JPanel panel6 = new JPanel();
        panel6.setBackground(Color.lightGray);
        panel6.setLayout(new GridLayout(3, 1));
        final JLabel label7 = new JLabel("Beam Current", 4);
        label7.setFont(font);
        panel6.add(label7);
        this.lblLifeCap.setFont(font);
        panel6.add(this.lblLifeCap);
        final JLabel label8 = new JLabel("I*tau", 4);
        label8.setFont(font);
        panel6.add(label8);
        final JPanel panel7 = new JPanel();
        panel7.setBackground(Color.lightGray);
        panel7.setLayout(new GridLayout(3, 1));
        this.lblCurrent.setFont(font);
        panel7.add(this.lblCurrent);
        this.lblLife.setFont(font);
        panel7.add(this.lblLife);
        this.lblItau.setFont(font);
        panel7.add(this.lblItau);
        final JPanel panel8 = new JPanel();
        panel8.setBackground(Color.lightGray);
        panel8.setLayout(new GridLayout(3, 1));
        final JLabel label9 = new JLabel("mA", 2);
        label9.setFont(font);
        panel8.add(label9);
        this.lblLifeUni.setFont(font);
        panel8.add(this.lblLifeUni);
        final JLabel label10 = new JLabel("A*min", 2);
        label10.setFont(font);
        panel8.add(label10);
        final JPanel panel9 = new JPanel();
        panel9.setBackground(Color.lightGray);
        panel9.setLayout(new BorderLayout(5, 0));
        panel9.add(panel7, "Center");
        panel9.add(panel8, "East");
        final JPanel panel10 = new JPanel();
        panel10.setBackground(Color.lightGray);
        panel10.setLayout(new BorderLayout(20, 0));
        panel10.add(panel6, "Center");
        panel10.add(panel9, "East");
        final JPanel panel11 = new JPanel();
        panel11.setBackground(Color.lightGray);
        panel11.setLayout(new GridLayout(1, 2));
        panel11.add(panel5);
        panel11.add(panel10);
        final Font font2 = new Font("Serif", 0, 18);
        final JPanel panel12 = new JPanel();
        panel12.setBackground(new Color(0, 0, 0, 0));
        panel12.setLayout(new BorderLayout());
        final JLabel label11 = new JLabel("       PF-Ring Vacuum");
        label11.setFont(font2);
        panel12.add(label11, "West");
        final JLabel label12 = new JLabel("BT Vac.       ");
        label12.setFont(font2);
        panel12.add(label12, "East");
        this.lblMode.setFont(new Font("SansSerif", 0, 18));
        panel12.add(this.lblMode, "Center");
        (this.pnlGraph = new MyJPanel()).setBackground(Color.lightGray);
        this.pnlGraph.setLayout(new BorderLayout());
        this.pnlGraph.add(panel12, "North");
        this.pnlGraph.setSize(659, 325);
        final JLabel label13 = new JLabel("Network connection was broken...", 0);
        label13.setFont(new Font("SansSerif", 0, 24));
        label13.setForeground(Color.yellow);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new BorderLayout());
        panel13.setBackground(new Color(255, 0, 0, 192));
        panel13.add(label13, "Center");
        final Container container = (Container)this.getGlassPane();
        container.setLayout(new BorderLayout());
        container.add(panel13, "Center");
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.pnlGraph, "Center");
        contentPane.add(panel11, "South");
        this.setVisible(true);
        this.pnlGraph.DrawBackgroundImage();
    }
    
    @Override
    public void run() {
        while (this.thread != null) {
            try {
                this.Date_Time = this.dch.readString("general", "Date_Time", 20);
                this.Mode = this.dch.readInt("general", "Mode");
                this.OnOff = this.dch.readInt("general", "OnOff");
                this.ChannelPermit = this.dch.readInt("general", "ChannelPermit");
                this.Topup = this.dch.readInt("general", "Topup");
                this.GateOpen = this.dch.readInt("general", "GateOpen");
                this.InjRate = this.dch.readFloat("general", "InjRate");
                this.InjFreq = this.dch.readFloat("general", "InjFreq");
                this.Current = this.dch.readDouble("general", "Current");
                this.Life_fast = this.dch.readFloat("general", "Life_fast");
                this.IntCurrent = this.dch.readDouble("general", "IntCurrent");
                this.Itau = this.dch.readDouble("vacuum", "Itau");
                this.PoverI = this.dch.readDouble("vacuum", "PoverI");
                this.Pav = this.dch.readDouble("vacuum", "Pav");
                this.RingBAG = this.dch.readDouble("vacuum", "RingBAG", 0, 53);
                this.PBL21 = this.dch.readDouble("vacuum", "PBL21", 0, 3);
                this.BTVac = this.dch.readDouble("vacuum", "BTVac", 0, 7);
                this.BH32L_IMON = this.dch.readFloat("magnet", "BH32L_IMON");
            }
            catch (IOException ex) {
                System.out.println("IOException");
                break;
            }
            final String format = String.format("%6.3f", this.InjRate);
            final String format2 = String.format("%6.2f", this.Current);
            final String format3 = String.format("%6.2f", this.Life_fast);
            final String format4 = String.format("%6.2f", this.IntCurrent);
            final String format5 = String.format("%6.2f", this.Itau);
            final String format6 = String.format("%6.2E", this.PoverI);
            final String text = new String(format6.substring(0, 6) + format6.substring(7, 8));
            final String format7 = String.format("%6.2E", this.Pav);
            final String text2 = new String(format7.substring(0, 6) + format7.substring(7, 8));
            this.lblCurrent.setText(format2);
            this.lblIntCurrent.setText(format4);
            this.lblItau.setText(format5);
            this.lblPoverI.setText(text);
            this.lblPav.setText(text2);
            this.lblLifeCap.setText("Lifetime");
            this.lblLife.setText(format3);
            this.lblLifeUni.setText("min");
            switch (this.Mode) {
                case 0: {
                    this.lblMode.setText("Shutdown");
                    break;
                }
                case 1: {
                    this.lblMode.setText("Linac");
                    break;
                }
                case 2: {
                    if (this.OnOff == 1 && this.GateOpen == 1) {
                        this.lblMode.setText("Injecting@" + this.InjFreq + "Hz,GATE OPEN");
                    }
                    else {
                        this.lblMode.setText("Injection");
                    }
                    this.lblLifeCap.setText("Inj.Rate@" + this.InjFreq + "Hz");
                    this.lblLife.setText(format);
                    this.lblLifeUni.setText("mA/sec");
                    break;
                }
                case 3: {
                    this.lblMode.setText("Storage");
                    break;
                }
                case 4: {
                    if (this.Topup == 1) {
                        if (this.ChannelPermit != 1) {
                            this.lblMode.setText("");
                            break;
                        }
                        if (this.OnOff != 1) {
                            this.lblMode.setText("Experiment");
                            break;
                        }
                        if (this.BH32L_IMON > 300.0f) {
                            if (this.GateOpen == 1) {
                                this.lblMode.setText("Experiment,Injecting@" + this.InjFreq + "Hz,GATE OPEN");
                            }
                            else {
                                this.lblMode.setText("Experiment,Injection");
                            }
                            this.lblLifeCap.setText("Inj.Rate@" + this.InjFreq + "Hz");
                            this.lblLife.setText(format);
                            this.lblLifeUni.setText("mA/sec");
                            break;
                        }
                        this.lblMode.setText("Experiment");
                        break;
                    }
                    else {
                        if (this.ChannelPermit == 1) {
                            this.lblMode.setText("Experiment");
                            break;
                        }
                        this.lblMode.setText("Injection");
                        break;
                    }
                    break;
                }
                default: {
                    this.lblMode.setText("");
                    break;
                }
            }
            this.pnlGraph.DrawGraphVac();
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex2) {}
        }
        this.getGlassPane().setVisible(true);
        this.showStatus("Network connection was broken...");
        this.thread = null;
    }
    
    @Override
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    @Override
    public void destroy() {
        if (this.thread != null) {
            try {
                this.dch.exit_dch();
            }
            catch (IOException ex) {}
            this.thread = null;
        }
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame("PF-Ring Vacuum");
        final Vacuum vacuum = new Vacuum();
        frame.getContentPane().add(vacuum);
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);
        if (array.length >= 1) {
            Vacuum.DChServer = array[0];
        }
        if (array.length >= 2) {
            Vacuum.DChPort = Integer.parseInt(array[1]);
        }
        vacuum.init();
        vacuum.start();
        final Insets insets = frame.getInsets();
        frame.setSize(659 + insets.left + insets.right, 397 + insets.top + insets.bottom);
    }
    
    static {
        Vacuum.DChServer = "www-pfring.kek.jp";
        Vacuum.DChPort = 5500;
    }
    
    class MyJPanel extends JPanel
    {
        Image imgBackground;
        Image imgGraph;
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(this.imgBackground, 0, 0, this);
            graphics.drawImage(this.imgGraph, 25, 25, this);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 1, 12));
            graphics.drawString(Vacuum.this.Date_Time, 530, 315);
        }
        
        public void drawString7(final Graphics graphics, final String s, int n, final int n2) {
            for (int i = 0; i < s.length(); ++i) {
                final int n3 = (s.charAt(i) - ' ') % '\u0010' * Vacuum.this.imgx;
                final int n4 = (s.charAt(i) - ' ') / '\u0010' * Vacuum.this.imgy;
                graphics.drawImage(Vacuum.this.imgNum, n, n2 - Vacuum.this.imgy, n + Vacuum.this.imgx, n2, n3, n4, n3 + Vacuum.this.imgx, n4 + Vacuum.this.imgy, null);
                n += Vacuum.this.imgx;
            }
        }
        
        public void DrawGraphVac() {
            int n = 0;
            this.imgGraph = this.createImage(606, 251);
            final Graphics graphics = this.imgGraph.getGraphics();
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, 606, 251);
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 486, 250);
            graphics.fillRect(489, 0, 36, 250);
            graphics.fillRect(534, 0, 72, 250);
            graphics.setColor(Color.white);
            int n2 = 1;
            for (int i = 1; i < 6; ++i) {
                for (int j = 1; j < 10; ++j) {
                    n = (int)(Math.log(j * n2) / Vacuum.this.LogN * 50.0);
                    graphics.drawLine(0, 250 - n, 486, 250 - n);
                    graphics.drawLine(489, 250 - n, 525, 250 - n);
                    graphics.drawLine(534, 250 - n, 606, 250 - n);
                }
                n2 *= 10;
            }
            final double n3 = 1.0E9;
            for (int k = 0; k < 54; ++k) {
                final int n4 = k * 9;
                if (Vacuum.this.RingBAG[k] != 0.0) {
                    n = (int)(Math.log(Vacuum.this.RingBAG[k] * n3) / Vacuum.this.LogN * 50.0);
                    if (n < 0) {
                        n = 0;
                        graphics.setColor(Color.white);
                    }
                    else if (n < 50) {
                        graphics.setColor(Color.white);
                    }
                    else if (n < 100) {
                        graphics.setColor(Color.cyan);
                    }
                    else if (n < 150) {
                        graphics.setColor(Color.green);
                    }
                    else if (n < 200) {
                        graphics.setColor(Color.yellow);
                    }
                    else if (n < 250) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        n = 250;
                        graphics.setColor(Color.red);
                    }
                }
                else {
                    n = 250;
                    graphics.setColor(new Color(16764108));
                }
                graphics.fillRect(0 + n4 + 1, 250 - n, 6, n);
                graphics.setColor(Color.black);
                graphics.drawRect(0 + n4 + 1, 250 - n, 6, n);
            }
            final double n5 = 1.0E9;
            for (int l = 0; l < 4; ++l) {
                final int n6 = l * 9;
                if (Vacuum.this.PBL21[l] != 0.0) {
                    n = (int)(Math.log(Vacuum.this.PBL21[l] * n5) / Vacuum.this.LogN * 50.0);
                    if (n < 0) {
                        n = 0;
                        graphics.setColor(Color.white);
                    }
                    else if (n < 50) {
                        graphics.setColor(Color.white);
                    }
                    else if (n < 100) {
                        graphics.setColor(Color.cyan);
                    }
                    else if (n < 150) {
                        graphics.setColor(Color.green);
                    }
                    else if (n < 200) {
                        graphics.setColor(Color.yellow);
                    }
                    else if (n < 250) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        n = 250;
                        graphics.setColor(Color.red);
                    }
                }
                graphics.fillRect(489 + n6 + 1, 250 - n, 6, n);
                graphics.setColor(Color.black);
                graphics.drawRect(489 + n6 + 1, 250 - n, 6, n);
            }
            final double n7 = 1.0E7;
            for (int n8 = 0; n8 < 8; ++n8) {
                final int n9 = n8 * 9;
                if (Vacuum.this.BTVac[n8] != 0.0) {
                    n = (int)(Math.log(Vacuum.this.BTVac[n8] * n7) / Vacuum.this.LogN * 50.0);
                    if (n < 0) {
                        n = 0;
                        graphics.setColor(Color.white);
                    }
                    else if (n < 50) {
                        graphics.setColor(Color.white);
                    }
                    else if (n < 100) {
                        graphics.setColor(Color.cyan);
                    }
                    else if (n < 150) {
                        graphics.setColor(Color.green);
                    }
                    else if (n < 200) {
                        graphics.setColor(Color.yellow);
                    }
                    else if (n < 250) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        n = 250;
                        graphics.setColor(Color.red);
                    }
                }
                graphics.fillRect(534 + n9 + 1, 250 - n, 6, n);
                graphics.setColor(Color.black);
                graphics.drawRect(534 + n9 + 1, 250 - n, 6, n);
            }
            this.repaint();
        }
        
        public void DrawBackgroundImage() {
            final int n = 9;
            final int n2 = 18;
            final int n3 = -1;
            final int n4 = 30;
            final int n5 = 4;
            final int n6 = 9;
            final int n7 = 2;
            this.imgBackground = this.createImage(this.getWidth(), this.getHeight());
            final Graphics graphics = this.imgBackground.getGraphics();
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(Color.black);
            graphics.drawLine(21, 275 + n4 + 4, 515, 275 + n4 + 4);
            graphics.setColor(Color.blue);
            graphics.fillRect(25 + n3, 275 + n4, n5, n6);
            graphics.fillRect(61 + n3, 275 + n4, n5, n6);
            graphics.fillRect(79 + n3, 275 + n4, n5, n6);
            graphics.fillRect(106 + n3, 275 + n4, n5, n6);
            graphics.fillRect(133 + n3, 275 + n4, n5, n6);
            graphics.fillRect(142 + n3, 275 + n4, n5, n6);
            graphics.fillRect(151 + n3, 275 + n4, n5, n6);
            graphics.fillRect(160 + n3, 275 + n4, n5, n6);
            graphics.fillRect(169 + n3, 275 + n4, n5, n6);
            graphics.fillRect(178 + n3, 275 + n4, n5, n6);
            graphics.fillRect(187 + n3, 275 + n4, n5, n6);
            graphics.fillRect(196 + n3, 275 + n4, n5, n6);
            graphics.fillRect(223 + n3, 275 + n4, n5, n6);
            graphics.fillRect(250 + n3, 275 + n4, n5, n6);
            graphics.fillRect(268 + n3, 275 + n4, n5, n6);
            graphics.fillRect(304 + n3, 275 + n4, n5, n6);
            graphics.fillRect(322 + n3, 275 + n4, n5, n6);
            graphics.fillRect(349 + n3, 275 + n4, n5, n6);
            graphics.fillRect(376 + n3, 275 + n4, n5, n6);
            graphics.fillRect(385 + n3, 275 + n4, n5, n6);
            graphics.fillRect(394 + n3, 275 + n4, n5, n6);
            graphics.fillRect(403 + n3, 275 + n4, n5, n6);
            graphics.fillRect(412 + n3, 275 + n4, n5, n6);
            graphics.fillRect(421 + n3, 275 + n4, n5, n6);
            graphics.fillRect(430 + n3, 275 + n4, n5, n6);
            graphics.fillRect(439 + n3, 275 + n4, n5, n6);
            graphics.fillRect(457 + n3, 275 + n4, n5, n6);
            graphics.fillRect(484 + n3, 275 + n4, n5, n6);
            graphics.fillRect(502 + n3, 275 + n4, n5, n6);
            graphics.setColor(Color.yellow);
            graphics.fillRect(43 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(70 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(97 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(124 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(205 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(241 + n3 + 0, 275 + n4, n5, n6);
            graphics.fillRect(277 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(286 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(313 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(340 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(367 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(448 + n3 + 4, 275 + n4, n5, n6);
            graphics.fillRect(475 + n3 + 0, 275 + n4, n5, n6);
            graphics.fillRect(493 + n3 + 4, 275 + n4, n5, n6);
            graphics.setColor(Color.red);
            graphics.fillRect(43 + n3 + 2, 275 + n4, n7, n6);
            graphics.fillRect(97 + n3 + 1, 275 + n4, n7, n6);
            graphics.fillRect(106 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(133 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(178 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(232 + n3 + 2, 275 + n4, n7, n6);
            graphics.fillRect(340 + n3 + 1, 275 + n4, n7, n6);
            graphics.fillRect(349 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(376 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(421 + n3 - 1, 275 + n4, n7, n6);
            graphics.fillRect(457 + n3 - 1, 275 + n4, n7, n6);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 0, 10));
            graphics.drawString("P [Pa]", 0, 18);
            graphics.drawString("1E-4", 0, 28);
            graphics.drawString("1E-5", 0, 78);
            graphics.drawString("1E-6", 0, 128);
            graphics.drawString("1E-7", 0, 178);
            graphics.drawString("1E-8", 0, 228);
            graphics.drawString("1E-9", 0, 278);
            graphics.drawString("P [Pa]", 630, 18);
            graphics.drawString("1E-2", 635, 28);
            graphics.drawString("1E-3", 635, 78);
            graphics.drawString("1E-4", 635, 128);
            graphics.drawString("1E-5", 635, 178);
            graphics.drawString("1E-6", 635, 228);
            graphics.drawString("1E-7", 635, 278);
            this.drawString7(graphics, "011", 25, 275 + n);
            this.drawString7(graphics, "012", 34, 275 + n2);
            this.drawString7(graphics, "013", 43, 275 + n);
            this.drawString7(graphics, "014", 52, 275 + n2);
            this.drawString7(graphics, "021", 61, 275 + n);
            this.drawString7(graphics, "022", 70, 275 + n2);
            this.drawString7(graphics, "031", 79, 275 + n);
            this.drawString7(graphics, "032", 88, 275 + n2);
            this.drawString7(graphics, "033", 97, 275 + n);
            this.drawString7(graphics, "041", 106, 275 + n2);
            this.drawString7(graphics, "042", 115, 275 + n);
            this.drawString7(graphics, "043", 124, 275 + n2);
            this.drawString7(graphics, "051", 133, 275 + n);
            this.drawString7(graphics, "061", 142, 275 + n2);
            this.drawString7(graphics, "071", 151, 275 + n);
            this.drawString7(graphics, "081", 160, 275 + n2);
            this.drawString7(graphics, "091", 169, 275 + n);
            this.drawString7(graphics, "101", 178, 275 + n2);
            this.drawString7(graphics, "111", 187, 275 + n);
            this.drawString7(graphics, "121", 196, 275 + n2);
            this.drawString7(graphics, "122", 205, 275 + n);
            this.drawString7(graphics, "123", 214, 275 + n2);
            this.drawString7(graphics, "131", 223, 275 + n);
            this.drawString7(graphics, "132", 232, 275 + n2);
            this.drawString7(graphics, "133", 241, 275 + n);
            this.drawString7(graphics, "141", 250, 275 + n2);
            this.drawString7(graphics, "142", 259, 275 + n);
            this.drawString7(graphics, "151", 268, 275 + n2);
            this.drawString7(graphics, "152", 277, 275 + n);
            this.drawString7(graphics, "153", 286, 275 + n2);
            this.drawString7(graphics, "154", 295, 275 + n);
            this.drawString7(graphics, "161", 304, 275 + n2);
            this.drawString7(graphics, "162", 313, 275 + n);
            this.drawString7(graphics, "171", 322, 275 + n2);
            this.drawString7(graphics, "172", 331, 275 + n);
            this.drawString7(graphics, "173", 340, 275 + n2);
            this.drawString7(graphics, "181", 349, 275 + n);
            this.drawString7(graphics, "182", 358, 275 + n2);
            this.drawString7(graphics, "183", 367, 275 + n);
            this.drawString7(graphics, "191", 376, 275 + n2);
            this.drawString7(graphics, "201", 385, 275 + n);
            this.drawString7(graphics, "211", 394, 275 + n2);
            this.drawString7(graphics, "221", 403, 275 + n);
            this.drawString7(graphics, "231", 412, 275 + n2);
            this.drawString7(graphics, "241", 421, 275 + n);
            this.drawString7(graphics, "251", 430, 275 + n2);
            this.drawString7(graphics, "261", 439, 275 + n);
            this.drawString7(graphics, "262", 448, 275 + n2);
            this.drawString7(graphics, "271", 457, 275 + n);
            this.drawString7(graphics, "272", 466, 275 + n2);
            this.drawString7(graphics, "273", 475, 275 + n);
            this.drawString7(graphics, "281", 484, 275 + n2);
            this.drawString7(graphics, "282", 493, 275 + n);
            this.drawString7(graphics, "283", 502, 275 + n2);
            this.drawString7(graphics, "in", 514, 275 + n);
            this.drawString7(graphics, "com", 523, 275 + n2);
            this.drawString7(graphics, "V", 535, 275 + n);
            this.drawString7(graphics, "M", 544, 275 + n2);
            this.drawString7(graphics, "G1", 559, 275 + n);
            this.drawString7(graphics, "G2", 568, 275 + n2);
            this.drawString7(graphics, "G3", 577, 275 + n);
            this.drawString7(graphics, "G4", 586, 275 + n2);
            this.drawString7(graphics, "G5", 595, 275 + n);
            this.drawString7(graphics, "G6", 604, 275 + n2);
            this.drawString7(graphics, "S1", 613, 275 + n);
            this.drawString7(graphics, "S2", 622, 275 + n2);
            this.drawString7(graphics, "1", 25 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "2", 61 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "3", 79 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "4", 106 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "5", 133 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "6", 142 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "7", 151 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "8", 160 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "9", 169 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "10", 178 + n3 - 2, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "12", 196 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "13", 223 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "14", 250 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "15", 268 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "16", 304 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "17", 322 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "18", 349 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "19", 376 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "21", 394 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "24", 421 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "26", 439 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "27", 457 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "28", 484 + n3 - 3, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "1", 502 + n3 + 0, 275 + n4 + n6 + 8);
            this.drawString7(graphics, "U2", 25 + n3 + 20, 275 + n4 - 3);
            this.drawString7(graphics, "U3", 25 + n3 + 48, 275 + n4 - 3);
            this.drawString7(graphics, "RF", 25 + n3 + 74, 275 + n4 - 3);
            this.drawString7(graphics, "MPW5", 25 + n3 + 95, 275 + n4 - 3);
            this.drawString7(graphics, "MPW13", 25 + n3 + 173, 275 + n4 - 3);
            this.drawString7(graphics, "VW14", 25 + n3 + 207, 275 + n4 - 3);
            this.drawString7(graphics, "U16", 25 + n3 + 258, 275 + n4 - 3);
            this.drawString7(graphics, "U17", 25 + n3 + 288, 275 + n4 - 3);
            this.drawString7(graphics, "RF", 25 + n3 + 317, 275 + n4 - 3);
            this.drawString7(graphics, "R19", 25 + n3 + 342, 275 + n4 - 3);
            this.drawString7(graphics, "INJ", 25 + n3 + 422, 275 + n4 - 3);
            this.drawString7(graphics, "EMPW28", 25 + n3 + 438, 275 + n4 - 3);
            this.drawString7(graphics, "U1", 25 + n3 + 470, 275 + n4 - 3);
            this.repaint();
        }
    }
    
    static class WindowEventHandler extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
            System.exit(0);
        }
    }
}
