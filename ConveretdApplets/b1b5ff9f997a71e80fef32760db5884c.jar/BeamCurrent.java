import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.util.TimerTask;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.Timer;
import lib.DChTCP;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BeamCurrent extends JApplet implements Runnable
{
    DChTCP dch;
    Thread thread;
    static String DChServer;
    static int DChPort;
    Timer timer;
    String[] strCmbCurrent;
    String[] strCmbLife;
    JComboBox cmbCurrentScale;
    JComboBox cmbLifeScale;
    MyJPanel pnlGraph;
    JLabel lblMode;
    JLabel lblCurrent;
    JLabel lblLife;
    JLabel lblPav;
    JLabel lblItau;
    JLabel lblLifeCap;
    JLabel lblLifeUni;
    JLabel[] lblBLCap;
    JLabel[] lblBLStat;
    float[] LogCurrent;
    float[] LogLife_fast;
    int UnixTime;
    double Current;
    float Life_fast;
    float InjRate;
    float InjFreq;
    float BH32L_IMON;
    double Itau;
    double Pav;
    String Date_Time;
    int Mode;
    int OnOff;
    int ChannelPermit;
    int Topup;
    int GateOpen;
    int[] ChanStatBit;
    int MaxCurrent;
    int MinCurrent;
    int MaxLife;
    float ScaleCurrent;
    float ScaleLife;
    final int GraphLX = 500;
    final int GraphLY = 400;
    final int GraphOX = 30;
    final int GraphOY = 30;
    int ix1;
    int iy1;
    int ix2;
    int iy2;
    int lx1;
    int ly1;
    int lx2;
    int ly2;
    
    public BeamCurrent() {
        this.thread = null;
        this.strCmbCurrent = new String[] { "1000 mA", "500 mA", "250 mA", "100 mA", "Cont.Inj. 450 mA", "Cont.Inj. 440 mA", "Cont.Inj. 430 mA", "Cont.Inj. 420 mA", "Cont.Inj. 410 mA", "Cont.Inj. 400 mA" };
        this.strCmbLife = new String[] { "10000 min", "5000 min", "2500 min", "1000 min", "500 min", "none" };
        this.cmbCurrentScale = new JComboBox((E[])this.strCmbCurrent);
        this.cmbLifeScale = new JComboBox((E[])this.strCmbLife);
        this.lblMode = new JLabel("", 0);
        this.lblCurrent = new JLabel("", 4);
        this.lblLife = new JLabel("", 4);
        this.lblPav = new JLabel("", 4);
        this.lblItau = new JLabel("", 4);
        this.lblLifeCap = new JLabel("", 4);
        this.lblLifeUni = new JLabel("", 2);
        this.lblBLCap = new JLabel[28];
        this.lblBLStat = new JLabel[28];
        this.LogCurrent = new float[1440];
        this.LogLife_fast = new float[1440];
        this.UnixTime = 0;
        this.Current = 0.0;
        this.Life_fast = 0.0f;
        this.InjRate = 0.0f;
        this.InjFreq = 0.0f;
        this.BH32L_IMON = 0.0f;
        this.Itau = 0.0;
        this.Pav = 0.0;
        this.Date_Time = "";
        this.Mode = 0;
        this.OnOff = 0;
        this.ChannelPermit = 0;
        this.Topup = 0;
        this.GateOpen = 0;
        this.ChanStatBit = new int[28];
        this.MaxCurrent = 500;
        this.MinCurrent = 0;
        this.MaxLife = 5000;
        this.ScaleCurrent = 2.0f;
        this.ScaleLife = 20.0f;
        this.ix1 = 0;
        this.iy1 = 400;
        this.ix2 = 0;
        this.iy2 = 400;
        this.lx1 = 0;
        this.ly1 = 400;
        this.lx2 = 0;
        this.ly2 = 400;
    }
    
    @Override
    public void init() {
        try {
            try {
                this.dch = new DChTCP(this.getDocumentBase().getHost(), 5500);
            }
            catch (NullPointerException ex) {
                this.dch = new DChTCP(BeamCurrent.DChServer, BeamCurrent.DChPort);
            }
            this.dch.open_dch();
        }
        catch (IOException ex2) {}
        final Font font = new Font("Serif", 0, 20);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        final String[] array = { "1000 mA", "500 mA", "250 mA", "100 mA", "Cont.Inj. 450 mA", "Cont.Inj. 440 mA", "Cont.Inj. 430 mA", "Cont.Inj. 420 mA", "Cont.Inj. 410 mA", "Cont.Inj. 400 mA" };
        (this.cmbCurrentScale = new JComboBox(array)).setMaximumRowCount(array.length);
        this.cmbCurrentScale.setSelectedIndex(1);
        this.cmbCurrentScale.addActionListener(new cmbCurrentScaleEventListener());
        (this.cmbLifeScale = new JComboBox((E[])new String[] { "10000 min", "5000 min", "2500 min", "1000 min", "500 min", "none" })).setSelectedIndex(1);
        this.cmbLifeScale.addActionListener(new cmbLifeScaleEventListener());
        final JLabel label = new JLabel("PF-Ring   Beam Current and Lifetime", 0);
        label.setFont(font);
        panel.add(this.cmbCurrentScale, "West");
        panel.add(this.cmbLifeScale, "East");
        panel.add(label, "Center");
        final Font font2 = new Font("SansSerif", 0, 18);
        final JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new GridLayout(2, 1));
        final JLabel label2 = new JLabel("Beam Current", 4);
        label2.setFont(font2);
        panel2.add(label2);
        final JLabel label3 = new JLabel("Averaged Pressure", 4);
        label3.setFont(font2);
        panel2.add(label3);
        final JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        panel3.setLayout(new GridLayout(2, 1));
        this.lblCurrent.setFont(font2);
        panel3.add(this.lblCurrent);
        this.lblPav.setFont(font2);
        panel3.add(this.lblPav);
        final JPanel panel4 = new JPanel();
        panel4.setBackground(Color.lightGray);
        panel4.setLayout(new GridLayout(2, 1));
        final JLabel label4 = new JLabel("mA", 2);
        label4.setFont(font2);
        panel4.add(label4);
        final JLabel label5 = new JLabel("Pa", 2);
        label5.setFont(font2);
        panel4.add(label5);
        final JPanel panel5 = new JPanel();
        panel5.setBackground(Color.lightGray);
        panel5.setLayout(new BorderLayout(5, 0));
        panel5.add(panel3, "Center");
        panel5.add(panel4, "East");
        final JPanel panel6 = new JPanel();
        panel6.setBackground(Color.lightGray);
        panel6.setLayout(new BorderLayout(20, 0));
        panel6.add(panel2, "Center");
        panel6.add(panel5, "East");
        final JPanel panel7 = new JPanel();
        panel7.setBackground(Color.lightGray);
        panel7.setLayout(new GridLayout(2, 1));
        this.lblLifeCap.setFont(font2);
        panel7.add(this.lblLifeCap);
        final JLabel label6 = new JLabel("I*tau", 4);
        label6.setFont(font2);
        panel7.add(label6);
        final JPanel panel8 = new JPanel();
        panel8.setBackground(Color.lightGray);
        panel8.setLayout(new GridLayout(2, 1));
        this.lblLife.setFont(font2);
        panel8.add(this.lblLife);
        this.lblItau.setFont(font2);
        panel8.add(this.lblItau);
        final JPanel panel9 = new JPanel();
        panel9.setBackground(Color.lightGray);
        panel9.setLayout(new GridLayout(2, 1));
        this.lblLifeUni.setFont(font2);
        panel9.add(this.lblLifeUni);
        final JLabel label7 = new JLabel("A*min", 2);
        label7.setFont(font2);
        panel9.add(label7);
        final JPanel panel10 = new JPanel();
        panel10.setBackground(Color.lightGray);
        panel10.setLayout(new BorderLayout(5, 0));
        panel10.add(panel8, "Center");
        panel10.add(panel9, "East");
        final JPanel panel11 = new JPanel();
        panel11.setBackground(Color.lightGray);
        panel11.setLayout(new BorderLayout(20, 0));
        panel11.add(panel7, "Center");
        panel11.add(panel10, "East");
        final JPanel panel12 = new JPanel();
        panel12.setBackground(Color.lightGray);
        panel12.setLayout(new GridLayout(1, 2));
        panel12.add(panel6);
        panel12.add(panel11);
        final Font font3 = new Font("SansSerif", 1, 14);
        final JPanel panel13 = new JPanel();
        panel13.setBackground(Color.lightGray);
        panel13.setLayout(new GridLayout(6, 8));
        for (int i = 0; i < 28; ++i) {
            this.lblBLCap[i] = new JLabel("", 2);
            this.lblBLStat[i] = new JLabel("", 2);
            if (i <= 20 || i >= 25) {
                if (i == 25) {
                    panel13.add(this.lblBLCap[i]);
                    panel13.add(this.lblBLStat[i]);
                }
                else {
                    this.lblBLCap[i].setText("   BL- " + (i + 1));
                    this.lblBLCap[i].setFont(font3);
                    this.lblBLStat[i].setFont(font3);
                    panel13.add(this.lblBLCap[i]);
                    panel13.add(this.lblBLStat[i]);
                }
            }
        }
        final Font font4 = new Font("SansSerif", 0, 18);
        final JPanel panel14 = new JPanel();
        panel14.setBackground(Color.lightGray);
        panel14.setLayout(new BorderLayout());
        panel14.add(panel12, "North");
        panel14.add(panel13, "South");
        (this.pnlGraph = new MyJPanel()).setBackground(Color.lightGray);
        this.pnlGraph.setLayout(new BorderLayout());
        this.lblMode.setFont(font4);
        this.pnlGraph.add(this.lblMode, "North");
        this.pnlGraph.setSize(565, 465);
        final JLabel label8 = new JLabel("Network connection was broken...", 0);
        label8.setFont(new Font("SansSerif", 0, 24));
        label8.setForeground(Color.yellow);
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new BorderLayout());
        panel15.setBackground(new Color(255, 0, 0, 192));
        panel15.add(label8, "Center");
        final Container container = (Container)this.getGlassPane();
        container.setLayout(new BorderLayout());
        container.add(panel15, "Center");
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, "North");
        contentPane.add(this.pnlGraph, "Center");
        contentPane.add(panel14, "South");
        this.setVisible(true);
        synchronized (this.pnlGraph) {
            this.pnlGraph.DrawNewGraph();
        }
        (this.timer = new Timer(true)).scheduleAtFixedRate(new tskIncXaxis(), 180000L, 180000L);
    }
    
    @Override
    public void run() {
        while (this.thread != null) {
            synchronized (this.dch) {
                try {
                    this.Date_Time = this.dch.readString("general", "Date_Time", 20);
                    this.Current = this.dch.readDouble("general", "Current");
                    this.Life_fast = this.dch.readFloat("general", "Life_fast");
                    this.InjRate = this.dch.readFloat("general", "InjRate");
                    this.InjFreq = this.dch.readFloat("general", "InjFreq");
                    this.Itau = this.dch.readDouble("vacuum", "Itau");
                    this.Mode = this.dch.readInt("general", "Mode");
                    this.OnOff = this.dch.readInt("general", "OnOff");
                    this.ChannelPermit = this.dch.readInt("general", "ChannelPermit");
                    this.Topup = this.dch.readInt("general", "Topup");
                    this.GateOpen = this.dch.readInt("general", "GateOpen");
                    this.Itau = this.dch.readDouble("vacuum", "Itau");
                    this.Pav = this.dch.readDouble("vacuum", "Pav");
                    this.ChanStatBit = this.dch.readInt("interlock", "ChanStatBit", 0, 27);
                    this.BH32L_IMON = this.dch.readFloat("magnet", "BH32L_IMON");
                }
                catch (IOException ex) {
                    System.out.println("IOException");
                    break;
                }
            }
            final String format = String.format("%6.2f", this.Current);
            final String format2 = String.format("%6.2f", this.Life_fast);
            final String format3 = String.format("%6.3f", this.InjRate);
            final String format4 = String.format("%6.2f", this.Itau);
            final String format5 = String.format("%6.2E", this.Pav);
            final String text = new String(format5.substring(0, 6) + format5.substring(7, 8));
            this.lblCurrent.setText(format);
            this.lblItau.setText(format4);
            this.lblPav.setText(text);
            this.lblLifeCap.setText("Lifetime");
            this.lblLife.setText(format2);
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
                    this.lblLife.setText(format3);
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
                            this.lblLife.setText(format3);
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
            for (int i = 0; i < 28; ++i) {
                if (i <= 20 || i >= 26) {
                    switch (this.ChanStatBit[i]) {
                        case 0: {
                            this.lblBLStat[i].setText("NEUTRAL");
                            this.lblBLStat[i].setForeground(Color.red);
                            break;
                        }
                        case 1: {
                            this.lblBLStat[i].setText("CLOSE");
                            this.lblBLStat[i].setForeground(Color.cyan);
                            break;
                        }
                        case 2: {
                            this.lblBLStat[i].setText("OPEN");
                            this.lblBLStat[i].setForeground(Color.red);
                            break;
                        }
                        case 3: {
                            this.lblBLStat[i].setText("");
                            break;
                        }
                        case 4: {
                            this.lblBLStat[i].setText("NEUTRAL");
                            this.lblBLStat[i].setForeground(Color.red);
                            break;
                        }
                        case 5: {
                            this.lblBLStat[i].setText("CLOSE");
                            this.lblBLStat[i].setForeground(Color.green);
                            break;
                        }
                        case 6: {
                            this.lblBLStat[i].setText("OPEN");
                            this.lblBLStat[i].setForeground(Color.yellow);
                            break;
                        }
                        case 7: {
                            this.lblBLStat[i].setText("");
                            break;
                        }
                        default: {
                            this.lblBLStat[i].setText("");
                            break;
                        }
                    }
                }
            }
            synchronized (this.pnlGraph) {
                this.pnlGraph.DrawGraphCurrentLife();
            }
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex2) {}
        }
        this.timer.cancel();
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
            this.timer.cancel();
            try {
                this.dch.exit_dch();
            }
            catch (IOException ex) {}
            this.thread = null;
        }
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame("PF-Ring BeamCurrent");
        final BeamCurrent beamCurrent = new BeamCurrent();
        frame.getContentPane().add(beamCurrent);
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);
        if (array.length >= 1) {
            BeamCurrent.DChServer = array[0];
        }
        if (array.length >= 2) {
            BeamCurrent.DChPort = Integer.parseInt(array[1]);
        }
        beamCurrent.init();
        beamCurrent.start();
        final Insets insets = frame.getInsets();
        frame.setSize(565 + insets.left + insets.right, 654 + insets.top + insets.bottom);
    }
    
    static {
        BeamCurrent.DChServer = "www-pfring.kek.jp";
        BeamCurrent.DChPort = 5500;
    }
    
    class cmbCurrentScaleEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            synchronized (BeamCurrent.this.pnlGraph) {
                BeamCurrent.this.pnlGraph.DrawNewGraph();
            }
        }
    }
    
    class cmbLifeScaleEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            synchronized (BeamCurrent.this.pnlGraph) {
                BeamCurrent.this.pnlGraph.DrawNewGraph();
            }
        }
    }
    
    class tskIncXaxis extends TimerTask
    {
        @Override
        public void run() {
            final BeamCurrent this$0 = BeamCurrent.this;
            ++this$0.ix2;
            final BeamCurrent this$2 = BeamCurrent.this;
            ++this$2.lx2;
            if (BeamCurrent.this.ix2 >= 500) {
                synchronized (BeamCurrent.this.pnlGraph) {
                    BeamCurrent.this.pnlGraph.DrawNewGraph();
                }
            }
        }
    }
    
    class MyJPanel extends JPanel
    {
        Image imgBackground;
        Image imgGraph;
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(this.imgBackground, 0, 0, this);
            graphics.drawImage(this.imgGraph, 30, 30, this);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 1, 12));
            graphics.drawString(BeamCurrent.this.Date_Time, 56, 460);
        }
        
        public void DrawGraphCurrentLife() {
            final Graphics2D graphics2D = (Graphics2D)this.imgGraph.getGraphics();
            graphics2D.setStroke(new BasicStroke(2.0f));
            if (BeamCurrent.this.cmbLifeScale.getSelectedIndex() != 5) {
                if (BeamCurrent.this.Life_fast < 0.0f) {
                    BeamCurrent.this.ly2 = 400;
                }
                else if (BeamCurrent.this.Life_fast > BeamCurrent.this.MaxLife) {
                    BeamCurrent.this.ly2 = 0;
                }
                else {
                    BeamCurrent.this.ly2 = (int)(400.0f - BeamCurrent.this.Life_fast / BeamCurrent.this.ScaleLife);
                }
                graphics2D.setColor(Color.green);
                graphics2D.drawLine(BeamCurrent.this.lx1, BeamCurrent.this.ly1, BeamCurrent.this.lx2, BeamCurrent.this.ly2);
                BeamCurrent.this.lx1 = BeamCurrent.this.lx2;
                BeamCurrent.this.ly1 = BeamCurrent.this.ly2;
            }
            if (BeamCurrent.this.Current < BeamCurrent.this.MinCurrent) {
                BeamCurrent.this.iy2 = 400;
            }
            else if (BeamCurrent.this.Current > BeamCurrent.this.MaxCurrent) {
                BeamCurrent.this.iy2 = 0;
            }
            else {
                BeamCurrent.this.iy2 = (int)(400.0 - (BeamCurrent.this.Current - BeamCurrent.this.MinCurrent) / BeamCurrent.this.ScaleCurrent);
            }
            graphics2D.setColor(Color.red);
            graphics2D.drawLine(BeamCurrent.this.ix1, BeamCurrent.this.iy1, BeamCurrent.this.ix2, BeamCurrent.this.iy2);
            BeamCurrent.this.ix1 = BeamCurrent.this.ix2;
            BeamCurrent.this.iy1 = BeamCurrent.this.iy2;
            this.repaint();
        }
        
        public void DrawNewGraph() {
            synchronized (BeamCurrent.this.dch) {
                try {
                    BeamCurrent.this.LogCurrent = BeamCurrent.this.dch.readFloat("LogData", "LogCurrent", 0, 1439);
                    BeamCurrent.this.LogLife_fast = BeamCurrent.this.dch.readFloat("LogData", "LogLife_fast", 0, 1439);
                    BeamCurrent.this.Current = BeamCurrent.this.dch.readDouble("general", "Current");
                    BeamCurrent.this.Life_fast = BeamCurrent.this.dch.readFloat("general", "Life_fast");
                }
                catch (IOException ex) {}
            }
            this.imgBackground = this.createImage(this.getWidth(), this.getHeight());
            final Graphics2D graphics2D = (Graphics2D)this.imgBackground.getGraphics();
            graphics2D.setColor(Color.lightGray);
            graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics2D.setColor(Color.red);
            graphics2D.drawLine(65, 17, 85, 17);
            graphics2D.setColor(Color.black);
            graphics2D.setFont(new Font("SansSerif", 0, 10));
            graphics2D.drawString("Current [mA]", 0, 20);
            switch (BeamCurrent.this.cmbCurrentScale.getSelectedIndex()) {
                case 0: {
                    graphics2D.drawString("1000", 5, 34);
                    graphics2D.drawString(" 900", 5, 74);
                    graphics2D.drawString(" 800", 5, 114);
                    graphics2D.drawString(" 700", 5, 154);
                    graphics2D.drawString(" 600", 5, 194);
                    graphics2D.drawString(" 500", 5, 234);
                    graphics2D.drawString(" 400", 5, 274);
                    graphics2D.drawString(" 300", 5, 314);
                    graphics2D.drawString(" 200", 5, 354);
                    graphics2D.drawString(" 100", 5, 394);
                    graphics2D.drawString("   0", 5, 434);
                    BeamCurrent.this.MinCurrent = 0;
                    BeamCurrent.this.MaxCurrent = 1000;
                    BeamCurrent.this.ScaleCurrent = 2.5f;
                    break;
                }
                case 1: {
                    graphics2D.drawString(" 500", 5, 34);
                    graphics2D.drawString(" 450", 5, 74);
                    graphics2D.drawString(" 400", 5, 114);
                    graphics2D.drawString(" 350", 5, 154);
                    graphics2D.drawString(" 300", 5, 194);
                    graphics2D.drawString(" 250", 5, 234);
                    graphics2D.drawString(" 200", 5, 274);
                    graphics2D.drawString(" 150", 5, 314);
                    graphics2D.drawString(" 100", 5, 354);
                    graphics2D.drawString("  50", 5, 394);
                    graphics2D.drawString("   0", 5, 434);
                    BeamCurrent.this.MinCurrent = 0;
                    BeamCurrent.this.MaxCurrent = 500;
                    BeamCurrent.this.ScaleCurrent = 1.25f;
                    break;
                }
                case 2: {
                    graphics2D.drawString(" 250", 5, 34);
                    graphics2D.drawString(" 225", 5, 74);
                    graphics2D.drawString(" 200", 5, 114);
                    graphics2D.drawString(" 175", 5, 154);
                    graphics2D.drawString(" 150", 5, 194);
                    graphics2D.drawString(" 125", 5, 234);
                    graphics2D.drawString(" 100", 5, 274);
                    graphics2D.drawString("  75", 5, 314);
                    graphics2D.drawString("  50", 5, 354);
                    graphics2D.drawString("  25", 5, 394);
                    graphics2D.drawString("   0", 5, 434);
                    BeamCurrent.this.MinCurrent = 0;
                    BeamCurrent.this.MaxCurrent = 250;
                    BeamCurrent.this.ScaleCurrent = 0.625f;
                    break;
                }
                case 3: {
                    graphics2D.drawString(" 100", 5, 34);
                    graphics2D.drawString("  90", 5, 74);
                    graphics2D.drawString("  80", 5, 114);
                    graphics2D.drawString("  70", 5, 154);
                    graphics2D.drawString("  60", 5, 194);
                    graphics2D.drawString("  50", 5, 234);
                    graphics2D.drawString("  40", 5, 274);
                    graphics2D.drawString("  30", 5, 314);
                    graphics2D.drawString("  20", 5, 354);
                    graphics2D.drawString("  10", 5, 394);
                    graphics2D.drawString("   0", 5, 434);
                    BeamCurrent.this.MinCurrent = 0;
                    BeamCurrent.this.MaxCurrent = 100;
                    BeamCurrent.this.ScaleCurrent = 0.25f;
                    break;
                }
                case 4: {
                    graphics2D.drawString(" 452", 5, 34);
                    graphics2D.drawString(" 451", 5, 74);
                    graphics2D.drawString(" 450", 5, 114);
                    graphics2D.drawString(" 449", 5, 154);
                    graphics2D.drawString(" 448", 5, 194);
                    graphics2D.drawString(" 447", 5, 234);
                    graphics2D.drawString(" 446", 5, 274);
                    graphics2D.drawString(" 445", 5, 314);
                    graphics2D.drawString(" 444", 5, 354);
                    graphics2D.drawString(" 443", 5, 394);
                    graphics2D.drawString(" 442", 5, 434);
                    BeamCurrent.this.MinCurrent = 442;
                    BeamCurrent.this.MaxCurrent = 452;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
                case 5: {
                    graphics2D.drawString(" 442", 5, 34);
                    graphics2D.drawString(" 441", 5, 74);
                    graphics2D.drawString(" 440", 5, 114);
                    graphics2D.drawString(" 439", 5, 154);
                    graphics2D.drawString(" 438", 5, 194);
                    graphics2D.drawString(" 437", 5, 234);
                    graphics2D.drawString(" 436", 5, 274);
                    graphics2D.drawString(" 435", 5, 314);
                    graphics2D.drawString(" 434", 5, 354);
                    graphics2D.drawString(" 433", 5, 394);
                    graphics2D.drawString(" 432", 5, 434);
                    BeamCurrent.this.MinCurrent = 432;
                    BeamCurrent.this.MaxCurrent = 442;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
                case 6: {
                    graphics2D.drawString(" 432", 5, 34);
                    graphics2D.drawString(" 431", 5, 74);
                    graphics2D.drawString(" 430", 5, 114);
                    graphics2D.drawString(" 429", 5, 154);
                    graphics2D.drawString(" 428", 5, 194);
                    graphics2D.drawString(" 427", 5, 234);
                    graphics2D.drawString(" 426", 5, 274);
                    graphics2D.drawString(" 425", 5, 314);
                    graphics2D.drawString(" 424", 5, 354);
                    graphics2D.drawString(" 423", 5, 394);
                    graphics2D.drawString(" 422", 5, 434);
                    BeamCurrent.this.MinCurrent = 422;
                    BeamCurrent.this.MaxCurrent = 432;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
                case 7: {
                    graphics2D.drawString(" 422", 5, 34);
                    graphics2D.drawString(" 421", 5, 74);
                    graphics2D.drawString(" 420", 5, 114);
                    graphics2D.drawString(" 419", 5, 154);
                    graphics2D.drawString(" 418", 5, 194);
                    graphics2D.drawString(" 417", 5, 234);
                    graphics2D.drawString(" 416", 5, 274);
                    graphics2D.drawString(" 415", 5, 314);
                    graphics2D.drawString(" 414", 5, 354);
                    graphics2D.drawString(" 413", 5, 394);
                    graphics2D.drawString(" 412", 5, 434);
                    BeamCurrent.this.MinCurrent = 412;
                    BeamCurrent.this.MaxCurrent = 422;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
                case 8: {
                    graphics2D.drawString(" 412", 5, 34);
                    graphics2D.drawString(" 411", 5, 74);
                    graphics2D.drawString(" 410", 5, 114);
                    graphics2D.drawString(" 409", 5, 154);
                    graphics2D.drawString(" 408", 5, 194);
                    graphics2D.drawString(" 407", 5, 234);
                    graphics2D.drawString(" 406", 5, 274);
                    graphics2D.drawString(" 405", 5, 314);
                    graphics2D.drawString(" 404", 5, 354);
                    graphics2D.drawString(" 403", 5, 394);
                    graphics2D.drawString(" 402", 5, 434);
                    BeamCurrent.this.MinCurrent = 402;
                    BeamCurrent.this.MaxCurrent = 412;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
                case 9: {
                    graphics2D.drawString(" 402", 5, 34);
                    graphics2D.drawString(" 401", 5, 74);
                    graphics2D.drawString(" 400", 5, 114);
                    graphics2D.drawString(" 399", 5, 154);
                    graphics2D.drawString(" 398", 5, 194);
                    graphics2D.drawString(" 397", 5, 234);
                    graphics2D.drawString(" 396", 5, 274);
                    graphics2D.drawString(" 395", 5, 314);
                    graphics2D.drawString(" 394", 5, 354);
                    graphics2D.drawString(" 393", 5, 394);
                    graphics2D.drawString(" 392", 5, 434);
                    BeamCurrent.this.MinCurrent = 392;
                    BeamCurrent.this.MaxCurrent = 402;
                    BeamCurrent.this.ScaleCurrent = 0.025f;
                    break;
                }
            }
            graphics2D.setColor(Color.green);
            graphics2D.drawLine(475, 17, 495, 17);
            graphics2D.setColor(Color.black);
            graphics2D.drawString("Lifetime [min]", 500, 20);
            switch (BeamCurrent.this.cmbLifeScale.getSelectedIndex()) {
                case 0: {
                    graphics2D.drawString("10000", 535, 34);
                    graphics2D.drawString(" 9000", 535, 74);
                    graphics2D.drawString(" 8000", 535, 114);
                    graphics2D.drawString(" 7000", 535, 154);
                    graphics2D.drawString(" 6000", 535, 194);
                    graphics2D.drawString(" 5000", 535, 234);
                    graphics2D.drawString(" 4000", 535, 274);
                    graphics2D.drawString(" 3000", 535, 314);
                    graphics2D.drawString(" 2000", 535, 354);
                    graphics2D.drawString(" 1000", 535, 394);
                    graphics2D.drawString("    0", 535, 434);
                    BeamCurrent.this.MaxLife = 10000;
                    BeamCurrent.this.ScaleLife = 25.0f;
                    break;
                }
                case 1: {
                    graphics2D.drawString(" 5000", 535, 34);
                    graphics2D.drawString(" 4500", 535, 74);
                    graphics2D.drawString(" 4000", 535, 114);
                    graphics2D.drawString(" 3500", 535, 154);
                    graphics2D.drawString(" 3000", 535, 194);
                    graphics2D.drawString(" 2500", 535, 234);
                    graphics2D.drawString(" 2000", 535, 274);
                    graphics2D.drawString(" 1500", 535, 314);
                    graphics2D.drawString(" 1000", 535, 354);
                    graphics2D.drawString("  500", 535, 394);
                    graphics2D.drawString("    0", 535, 434);
                    BeamCurrent.this.MaxLife = 5000;
                    BeamCurrent.this.ScaleLife = 12.5f;
                    break;
                }
                case 2: {
                    graphics2D.drawString(" 2500", 535, 34);
                    graphics2D.drawString(" 2250", 535, 74);
                    graphics2D.drawString(" 2000", 535, 114);
                    graphics2D.drawString(" 1750", 535, 154);
                    graphics2D.drawString(" 1500", 535, 194);
                    graphics2D.drawString(" 1250", 535, 234);
                    graphics2D.drawString(" 1000", 535, 274);
                    graphics2D.drawString("  750", 535, 314);
                    graphics2D.drawString("  500", 535, 354);
                    graphics2D.drawString("  250", 535, 394);
                    graphics2D.drawString("    0", 535, 434);
                    BeamCurrent.this.MaxLife = 2500;
                    BeamCurrent.this.ScaleLife = 6.25f;
                    break;
                }
                case 3: {
                    graphics2D.drawString(" 1000", 535, 34);
                    graphics2D.drawString("  900", 535, 74);
                    graphics2D.drawString("  800", 535, 114);
                    graphics2D.drawString("  700", 535, 154);
                    graphics2D.drawString("  600", 535, 194);
                    graphics2D.drawString("  500", 535, 234);
                    graphics2D.drawString("  400", 535, 274);
                    graphics2D.drawString("  300", 535, 314);
                    graphics2D.drawString("  200", 535, 354);
                    graphics2D.drawString("  100", 535, 394);
                    graphics2D.drawString("    0", 535, 434);
                    BeamCurrent.this.MaxLife = 1000;
                    BeamCurrent.this.ScaleLife = 2.5f;
                    break;
                }
                case 4: {
                    graphics2D.drawString("  500", 535, 34);
                    graphics2D.drawString("  450", 535, 74);
                    graphics2D.drawString("  400", 535, 114);
                    graphics2D.drawString("  350", 535, 154);
                    graphics2D.drawString("  300", 535, 194);
                    graphics2D.drawString("  250", 535, 234);
                    graphics2D.drawString("  200", 535, 274);
                    graphics2D.drawString("  150", 535, 314);
                    graphics2D.drawString("  100", 535, 354);
                    graphics2D.drawString("   50", 535, 394);
                    graphics2D.drawString("    0", 535, 434);
                    BeamCurrent.this.MaxLife = 500;
                    BeamCurrent.this.ScaleLife = 1.25f;
                    break;
                }
            }
            graphics2D.drawString("Time [hour]", 250, 455);
            graphics2D.drawString("-24", 24, 445);
            graphics2D.drawString("-23", 44, 445);
            graphics2D.drawString("-22", 64, 445);
            graphics2D.drawString("-21", 84, 445);
            graphics2D.drawString("-20", 104, 445);
            graphics2D.drawString("-19", 124, 445);
            graphics2D.drawString("-18", 144, 445);
            graphics2D.drawString("-17", 164, 445);
            graphics2D.drawString("-16", 184, 445);
            graphics2D.drawString("-15", 204, 445);
            graphics2D.drawString("-14", 224, 445);
            graphics2D.drawString("-13", 244, 445);
            graphics2D.drawString("-12", 264, 445);
            graphics2D.drawString("-11", 284, 445);
            graphics2D.drawString("-10", 304, 445);
            graphics2D.drawString("-9", 327, 445);
            graphics2D.drawString("-8", 347, 445);
            graphics2D.drawString("-7", 367, 445);
            graphics2D.drawString("-6", 387, 445);
            graphics2D.drawString("-5", 407, 445);
            graphics2D.drawString("-4", 427, 445);
            graphics2D.drawString("-3", 447, 445);
            graphics2D.drawString("-2", 467, 445);
            graphics2D.drawString("-1", 487, 445);
            graphics2D.drawString("0", 508, 445);
            graphics2D.drawString("1", 528, 445);
            this.imgGraph = this.createImage(501, 401);
            final Graphics2D graphics2D2 = (Graphics2D)this.imgGraph.getGraphics();
            graphics2D2.setColor(Color.lightGray);
            graphics2D2.fillRect(0, 0, 501, 401);
            graphics2D2.setColor(Color.black);
            graphics2D2.drawRect(0, 0, 500, 400);
            graphics2D2.setColor(Color.gray);
            graphics2D2.drawLine(0, 40, 500, 40);
            graphics2D2.drawLine(0, 80, 500, 80);
            graphics2D2.drawLine(0, 120, 500, 120);
            graphics2D2.drawLine(0, 160, 500, 160);
            graphics2D2.drawLine(0, 200, 500, 200);
            graphics2D2.drawLine(0, 240, 500, 240);
            graphics2D2.drawLine(0, 280, 500, 280);
            graphics2D2.drawLine(0, 320, 500, 320);
            graphics2D2.drawLine(0, 360, 500, 360);
            graphics2D2.drawLine(20, 0, 20, 400);
            graphics2D2.drawLine(40, 0, 40, 400);
            graphics2D2.drawLine(60, 0, 60, 400);
            graphics2D2.drawLine(80, 0, 80, 400);
            graphics2D2.drawLine(100, 0, 100, 400);
            graphics2D2.drawLine(120, 0, 120, 400);
            graphics2D2.drawLine(140, 0, 140, 400);
            graphics2D2.drawLine(160, 0, 160, 400);
            graphics2D2.drawLine(180, 0, 180, 400);
            graphics2D2.drawLine(200, 0, 200, 400);
            graphics2D2.drawLine(220, 0, 220, 400);
            graphics2D2.drawLine(240, 0, 240, 400);
            graphics2D2.drawLine(260, 0, 260, 400);
            graphics2D2.drawLine(280, 0, 280, 400);
            graphics2D2.drawLine(300, 0, 300, 400);
            graphics2D2.drawLine(320, 0, 320, 400);
            graphics2D2.drawLine(340, 0, 340, 400);
            graphics2D2.drawLine(360, 0, 360, 400);
            graphics2D2.drawLine(380, 0, 380, 400);
            graphics2D2.drawLine(400, 0, 400, 400);
            graphics2D2.drawLine(420, 0, 420, 400);
            graphics2D2.drawLine(440, 0, 440, 400);
            graphics2D2.drawLine(460, 0, 460, 400);
            graphics2D2.drawLine(480, 0, 480, 400);
            graphics2D2.setStroke(new BasicStroke(2.0f));
            if (BeamCurrent.this.cmbLifeScale.getSelectedIndex() != 5) {
                BeamCurrent.this.lx1 = 0;
                BeamCurrent.this.lx2 = 1;
                if (BeamCurrent.this.LogLife_fast[0] < 0.0f) {
                    BeamCurrent.this.ly1 = 400;
                }
                else if (BeamCurrent.this.LogLife_fast[0] > BeamCurrent.this.MaxLife) {
                    BeamCurrent.this.ly1 = 0;
                }
                else {
                    BeamCurrent.this.ly1 = (int)(400.0f - BeamCurrent.this.LogLife_fast[0] / BeamCurrent.this.ScaleLife);
                }
                for (int i = 1; i < 1440; ++i) {
                    if (BeamCurrent.this.LogLife_fast[i] < 0.0f) {
                        BeamCurrent.this.ly2 = 400;
                    }
                    else if (BeamCurrent.this.LogLife_fast[i] > BeamCurrent.this.MaxLife) {
                        BeamCurrent.this.ly2 = 0;
                    }
                    else {
                        BeamCurrent.this.ly2 = (int)(400.0f - BeamCurrent.this.LogLife_fast[i] / BeamCurrent.this.ScaleLife);
                    }
                    graphics2D2.setColor(Color.green);
                    graphics2D2.drawLine(BeamCurrent.this.lx1, BeamCurrent.this.ly1, BeamCurrent.this.lx2, BeamCurrent.this.ly2);
                    if (i % 3 == 0) {
                        BeamCurrent.this.lx1 = BeamCurrent.this.lx2++;
                        BeamCurrent.this.ly1 = BeamCurrent.this.ly2;
                    }
                }
                BeamCurrent.this.lx1 = BeamCurrent.this.lx2;
                BeamCurrent.this.ly1 = BeamCurrent.this.ly2;
            }
            BeamCurrent.this.ix1 = 0;
            BeamCurrent.this.ix2 = 1;
            if (BeamCurrent.this.LogCurrent[0] < BeamCurrent.this.MinCurrent) {
                BeamCurrent.this.iy1 = 400;
            }
            else if (BeamCurrent.this.LogCurrent[0] > BeamCurrent.this.MaxCurrent) {
                BeamCurrent.this.iy1 = 0;
            }
            else {
                BeamCurrent.this.iy1 = (int)(400.0f - (BeamCurrent.this.LogCurrent[0] - BeamCurrent.this.MinCurrent) / BeamCurrent.this.ScaleCurrent);
            }
            for (int j = 1; j < 1440; ++j) {
                if (BeamCurrent.this.LogCurrent[j] < BeamCurrent.this.MinCurrent) {
                    BeamCurrent.this.iy2 = 400;
                }
                else if (BeamCurrent.this.LogCurrent[j] > BeamCurrent.this.MaxCurrent) {
                    BeamCurrent.this.iy2 = 0;
                }
                else {
                    BeamCurrent.this.iy2 = (int)(400.0f - (BeamCurrent.this.LogCurrent[j] - BeamCurrent.this.MinCurrent) / BeamCurrent.this.ScaleCurrent);
                }
                graphics2D2.setColor(Color.red);
                graphics2D2.drawLine(BeamCurrent.this.ix1, BeamCurrent.this.iy1, BeamCurrent.this.ix2, BeamCurrent.this.iy2);
                if (j % 3 == 0) {
                    BeamCurrent.this.ix1 = BeamCurrent.this.ix2++;
                    BeamCurrent.this.iy1 = BeamCurrent.this.iy2;
                }
            }
            BeamCurrent.this.ix1 = BeamCurrent.this.ix2;
            BeamCurrent.this.iy1 = BeamCurrent.this.iy2;
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
