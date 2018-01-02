import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import lib.DChTCP;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class COD extends JApplet implements Runnable
{
    DChTCP dch;
    Thread thread;
    static String DChServer;
    static int DChPort;
    String[] strCmbCODScale;
    JComboBox cmbCODScale;
    MyJPanel pnlGraph;
    JRadioButton rbtDiffRef;
    JRadioButton rbtMeasure;
    JRadioButton rbtRewrite;
    JRadioButton rbtOverwrite;
    JButton btnGraphClear;
    Image imgNum;
    int imgx;
    int imgy;
    float[] BPM;
    float[] RefOrbit;
    float[] COD;
    String Date_Time;
    float ScaleCOD;
    boolean GraphDiffRef;
    boolean DrawRewrite;
    final int GScaleX = 9;
    final int GraphLX = 595;
    final int GraphLY = 200;
    final int GraphOX = 30;
    final int GraphOY = 30;
    final int GraphCODLX = 595;
    final int GraphCODLY = 200;
    final int GraphCODOX = 0;
    final int GraphCODOY = 100;
    final int GraphCODXOY = 0;
    final int GraphCODYOY = 250;
    
    public COD() {
        this.thread = null;
        this.strCmbCODScale = new String[] { "5.0 mm", "2.5 mm", "1.0 mm", "0.5 mm", "0.25 mm", "0.1 mm" };
        this.cmbCODScale = new JComboBox((E[])this.strCmbCODScale);
        this.imgx = 4;
        this.imgy = 5;
        this.BPM = new float[260];
        this.RefOrbit = new float[130];
        this.COD = new float[130];
        this.Date_Time = "";
        this.ScaleCOD = 20.0f;
        this.GraphDiffRef = true;
        this.DrawRewrite = false;
    }
    
    @Override
    public void init() {
        try {
            try {
                this.dch = new DChTCP(this.getDocumentBase().getHost(), 5500);
            }
            catch (NullPointerException ex) {
                this.dch = new DChTCP(COD.DChServer, COD.DChPort);
            }
            this.dch.open_dch();
        }
        catch (IOException ex2) {}
        this.imgNum = new ImageIcon(this.getClass().getResource("imgNum.png")).getImage();
        final Font font = new Font("Serif", 0, 20);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        this.cmbCODScale.setSelectedIndex(4);
        this.cmbCODScale.addActionListener(new cmbCODScaleEventListener());
        final JLabel label = new JLabel("PF-Ring   Current COD", 0);
        label.setFont(font);
        panel.add(this.cmbCODScale, "West");
        panel.add(label, "Center");
        final Font font2 = new Font("SansSerif", 0, 18);
        final JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new GridLayout(2, 1));
        panel2.setBorder(new TitledBorder(new EtchedBorder(), "Graph mode"));
        final rbtGraphModeEventListener rbtGraphModeEventListener = new rbtGraphModeEventListener();
        (this.rbtDiffRef = new JRadioButton("Difference from Reference COD", true)).setBackground(Color.lightGray);
        this.rbtDiffRef.setActionCommand("DiffRef");
        this.rbtDiffRef.addActionListener(rbtGraphModeEventListener);
        (this.rbtMeasure = new JRadioButton("Measured COD")).setBackground(Color.lightGray);
        this.rbtMeasure.setActionCommand("Measure");
        this.rbtMeasure.addActionListener(rbtGraphModeEventListener);
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.rbtDiffRef);
        buttonGroup.add(this.rbtMeasure);
        panel2.add(this.rbtDiffRef);
        panel2.add(this.rbtMeasure);
        final JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        panel3.setLayout(new GridLayout(2, 2));
        panel3.setBorder(new TitledBorder(new EtchedBorder(), "Draw mode"));
        final rbtDrawModeEventListener rbtDrawModeEventListener = new rbtDrawModeEventListener();
        (this.rbtRewrite = new JRadioButton("Rewrite")).setBackground(Color.lightGray);
        this.rbtRewrite.setActionCommand("Rewrite");
        this.rbtRewrite.addActionListener(rbtDrawModeEventListener);
        (this.rbtOverwrite = new JRadioButton("Overwrite", true)).setBackground(Color.lightGray);
        this.rbtOverwrite.setActionCommand("Overwrite");
        this.rbtOverwrite.addActionListener(rbtDrawModeEventListener);
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(this.rbtRewrite);
        buttonGroup2.add(this.rbtOverwrite);
        final JLabel label2 = new JLabel("", 2);
        (this.btnGraphClear = new JButton("Graph Clear")).setEnabled(true);
        this.btnGraphClear.setActionCommand("GraphClear");
        this.btnGraphClear.addActionListener(new btnGraphClearEventListener());
        panel3.add(this.rbtRewrite);
        panel3.add(label2);
        panel3.add(this.rbtOverwrite);
        panel3.add(this.btnGraphClear);
        final JPanel panel4 = new JPanel();
        panel4.setBackground(Color.lightGray);
        panel4.setLayout(new GridLayout(1, 2));
        panel4.add(panel2);
        panel4.add(panel3);
        (this.pnlGraph = new MyJPanel()).setBackground(Color.lightGray);
        this.pnlGraph.setSize(632, 520);
        final JLabel label3 = new JLabel("Network connection was broken...", 0);
        label3.setFont(new Font("SansSerif", 0, 24));
        label3.setForeground(Color.yellow);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        panel5.setBackground(new Color(255, 0, 0, 192));
        panel5.add(label3, "Center");
        final Container container = (Container)this.getGlassPane();
        container.setLayout(new BorderLayout());
        container.add(panel5, "Center");
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, "North");
        contentPane.add(this.pnlGraph, "Center");
        contentPane.add(panel4, "South");
        this.setVisible(true);
        this.pnlGraph.DrawBackgroundImage();
    }
    
    @Override
    public void run() {
        while (this.thread != null) {
            try {
                this.Date_Time = this.dch.readString("general", "Date_Time", 20);
                this.BPM = this.dch.readFloat("monitor", "BPM", 0, 259);
                this.RefOrbit = this.dch.readFloat("magnet", "RefOrbit", 0, 129);
                if (this.GraphDiffRef) {
                    for (int i = 0; i < 130; ++i) {
                        this.COD[i] = this.BPM[i] - this.RefOrbit[i];
                    }
                }
                else {
                    for (int j = 0; j < 130; ++j) {
                        this.COD[j] = this.BPM[j];
                    }
                }
            }
            catch (IOException ex) {
                System.out.println("IOException");
                break;
            }
            this.pnlGraph.DrawGraphCOD();
            try {
                Thread.sleep(3000L);
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
        final JFrame frame = new JFrame("PF-Ring COD");
        final COD cod = new COD();
        frame.getContentPane().add(cod);
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);
        if (array.length >= 1) {
            COD.DChServer = array[0];
        }
        if (array.length >= 2) {
            COD.DChPort = Integer.parseInt(array[1]);
        }
        cod.init();
        cod.start();
        final Insets insets = frame.getInsets();
        frame.setSize(632 + insets.left + insets.right, 625 + insets.top + insets.bottom);
    }
    
    static {
        COD.DChServer = "www-pfring.kek.jp";
        COD.DChPort = 5500;
    }
    
    class cmbCODScaleEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            COD.this.pnlGraph.DrawBackgroundImage();
        }
    }
    
    class rbtGraphModeEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("DiffRef")) {
                COD.this.GraphDiffRef = true;
            }
            else if (actionCommand.equals("Measure")) {
                COD.this.GraphDiffRef = false;
            }
            COD.this.pnlGraph.DrawBackgroundImage();
        }
    }
    
    class rbtDrawModeEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("Rewrite")) {
                COD.this.DrawRewrite = true;
                COD.this.btnGraphClear.setEnabled(false);
            }
            else if (actionCommand.equals("Overwrite")) {
                COD.this.DrawRewrite = false;
                COD.this.btnGraphClear.setEnabled(true);
            }
            COD.this.pnlGraph.DrawBackgroundImage();
        }
    }
    
    class btnGraphClearEventListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getActionCommand().equals("GraphClear")) {
                COD.this.pnlGraph.DrawBackgroundImage();
            }
        }
    }
    
    class MyJPanel extends JPanel
    {
        Image imgBackground;
        Image imgGraphCODX0;
        Image imgGraphCODY0;
        Image imgGraphCODX;
        Image imgGraphCODY;
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(this.imgBackground, 0, 0, this);
            graphics.drawImage(this.imgGraphCODX, 30, 30, this);
            graphics.drawImage(this.imgGraphCODY, 30, 280, this);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 1, 12));
            graphics.drawString(COD.this.Date_Time, 500, 25);
        }
        
        public void drawString7(final Graphics graphics, final String s, int n, final int n2) {
            for (int i = 0; i < s.length(); ++i) {
                final int n3 = (s.charAt(i) - ' ') % '\u0010' * COD.this.imgx;
                final int n4 = (s.charAt(i) - ' ') / '\u0010' * COD.this.imgy;
                graphics.drawImage(COD.this.imgNum, n, n2 - COD.this.imgy, n + COD.this.imgx, n2, n3, n4, n3 + COD.this.imgx, n4 + COD.this.imgy, null);
                n += COD.this.imgx;
            }
        }
        
        public void DrawGraphCOD() {
            if (!COD.this.DrawRewrite) {
                final Graphics graphics = this.imgGraphCODX.getGraphics();
                graphics.drawImage(this.imgGraphCODX0, 0, 0, this);
                graphics.setColor(new Color(0, 0, 128, 64));
                int n = 0;
                int n2 = (int)(COD.this.COD[2] * -COD.this.ScaleCOD + 100.0f);
                for (int i = 3; i < 65; ++i) {
                    final int n3 = (int)(COD.this.COD[i] * -COD.this.ScaleCOD + 100.0f);
                    graphics.drawLine((n + 1) * 9 + 0, n2, (n + 2) * 9 + 0, n3);
                    n2 = n3;
                    ++n;
                }
                for (int j = 0; j < 2; ++j) {
                    final int n4 = (int)(COD.this.COD[j] * -COD.this.ScaleCOD + 100.0f);
                    graphics.drawLine((n + 1) * 9 + 0, n2, (n + 2) * 9 + 0, n4);
                    n2 = n4;
                    ++n;
                }
                this.imgGraphCODX0.getGraphics().drawImage(this.imgGraphCODX, 0, 0, this);
                final Graphics graphics2 = this.imgGraphCODY.getGraphics();
                graphics2.drawImage(this.imgGraphCODY0, 0, 0, this);
                graphics2.setColor(new Color(0, 0, 128, 64));
                int n5 = 0;
                int n6 = (int)(COD.this.COD[67] * -COD.this.ScaleCOD + 100.0f);
                for (int k = 68; k < 130; ++k) {
                    final int n7 = (int)(COD.this.COD[k] * -COD.this.ScaleCOD + 100.0f);
                    graphics2.drawLine((n5 + 1) * 9 + 0, n6, (n5 + 2) * 9 + 0, n7);
                    n6 = n7;
                    ++n5;
                }
                for (int l = 65; l < 67; ++l) {
                    final int n8 = (int)(COD.this.COD[l] * -COD.this.ScaleCOD + 100.0f);
                    graphics2.drawLine((n5 + 1) * 9 + 0, n6, (n5 + 2) * 9 + 0, n8);
                    n6 = n8;
                    ++n5;
                }
                this.imgGraphCODY0.getGraphics().drawImage(this.imgGraphCODY, 0, 0, this);
            }
            final Graphics graphics3 = this.imgGraphCODX.getGraphics();
            graphics3.drawImage(this.imgGraphCODX0, 0, 0, this);
            graphics3.setColor(Color.red);
            int n9 = 0;
            int n10 = (int)(COD.this.COD[2] * -COD.this.ScaleCOD + 100.0f);
            graphics3.fillRect((n9 + 1) * 9 + 0 - 1, n10 - 1, 3, 3);
            for (int n11 = 3; n11 < 65; ++n11) {
                final int n12 = (int)(COD.this.COD[n11] * -COD.this.ScaleCOD + 100.0f);
                graphics3.drawLine((n9 + 1) * 9 + 0, n10, (n9 + 2) * 9 + 0, n12);
                graphics3.fillRect((n9 + 2) * 9 + 0 - 1, n12 - 1, 3, 3);
                n10 = n12;
                ++n9;
            }
            for (int n13 = 0; n13 < 2; ++n13) {
                final int n14 = (int)(COD.this.COD[n13] * -COD.this.ScaleCOD + 100.0f);
                graphics3.drawLine((n9 + 1) * 9 + 0, n10, (n9 + 2) * 9 + 0, n14);
                graphics3.fillRect((n9 + 2) * 9 + 0 - 1, n14 - 1, 3, 3);
                n10 = n14;
                ++n9;
            }
            final Graphics graphics4 = this.imgGraphCODY.getGraphics();
            graphics4.drawImage(this.imgGraphCODY0, 0, 0, this);
            graphics4.setColor(Color.red);
            int n15 = 0;
            int n16 = (int)(COD.this.COD[67] * -COD.this.ScaleCOD + 100.0f);
            graphics4.fillRect((n15 + 1) * 9 + 0 - 1, n16 - 1, 3, 3);
            for (int n17 = 68; n17 < 130; ++n17) {
                final int n18 = (int)(COD.this.COD[n17] * -COD.this.ScaleCOD + 100.0f);
                graphics4.drawLine((n15 + 1) * 9 + 0, n16, (n15 + 2) * 9 + 0, n18);
                graphics4.fillRect((n15 + 2) * 9 + 0 - 1, n18 - 1, 3, 3);
                n16 = n18;
                ++n15;
            }
            for (int n19 = 65; n19 < 67; ++n19) {
                final int n20 = (int)(COD.this.COD[n19] * -COD.this.ScaleCOD + 100.0f);
                graphics4.drawLine((n15 + 1) * 9 + 0, n16, (n15 + 2) * 9 + 0, n20);
                graphics4.fillRect((n15 + 2) * 9 + 0 - 1, n20 - 1, 3, 3);
                n16 = n20;
                ++n15;
            }
            this.repaint();
        }
        
        public void DrawBackgroundImage() {
            final int n = 9;
            final int n2 = 18;
            this.imgBackground = this.createImage(this.getWidth(), this.getHeight());
            final Graphics graphics = this.imgBackground.getGraphics();
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(Color.black);
            graphics.setFont(new Font("SansSerif", 1, 16));
            graphics.drawString("COD X", 290, 25);
            graphics.drawString("COD Y", 290, 275);
            graphics.setFont(new Font("SansSerif", 0, 10));
            graphics.drawString(" X Position [mm]", 0, 20);
            graphics.drawString(" Y Position [mm]", 0, 270);
            switch (COD.this.cmbCODScale.getSelectedIndex()) {
                case 0: {
                    graphics.drawString(" 5.0", 8, 34);
                    graphics.drawString(" 4.0", 8, 54);
                    graphics.drawString(" 3.0", 8, 74);
                    graphics.drawString(" 2.0", 8, 94);
                    graphics.drawString(" 1.0", 8, 114);
                    graphics.drawString("   0", 8, 134);
                    graphics.drawString("-1.0", 8, 154);
                    graphics.drawString("-2.0", 8, 174);
                    graphics.drawString("-3.0", 8, 194);
                    graphics.drawString("-4.0", 8, 214);
                    graphics.drawString("-5.0", 8, 234);
                    graphics.drawString(" 5.0", 8, 284);
                    graphics.drawString(" 4.0", 8, 304);
                    graphics.drawString(" 3.0", 8, 324);
                    graphics.drawString(" 2.0", 8, 344);
                    graphics.drawString(" 1.0", 8, 364);
                    graphics.drawString("   0", 8, 384);
                    graphics.drawString("-1.0", 8, 404);
                    graphics.drawString("-2.0", 8, 424);
                    graphics.drawString("-3.0", 8, 444);
                    graphics.drawString("-4.0", 8, 464);
                    graphics.drawString("-5.0", 8, 484);
                    COD.this.ScaleCOD = 20.0f;
                    break;
                }
                case 1: {
                    graphics.drawString(" 2.5", 8, 34);
                    graphics.drawString(" 2.0", 8, 54);
                    graphics.drawString(" 1.5", 8, 74);
                    graphics.drawString(" 1.0", 8, 94);
                    graphics.drawString(" 0.5", 8, 114);
                    graphics.drawString("   0", 8, 134);
                    graphics.drawString("-0.5", 8, 154);
                    graphics.drawString("-1.0", 8, 174);
                    graphics.drawString("-1.5", 8, 194);
                    graphics.drawString("-2.0", 8, 214);
                    graphics.drawString("-2.5", 8, 234);
                    graphics.drawString(" 2.5", 8, 284);
                    graphics.drawString(" 2.0", 8, 304);
                    graphics.drawString(" 1.5", 8, 324);
                    graphics.drawString(" 1.0", 8, 344);
                    graphics.drawString(" 0.5", 8, 364);
                    graphics.drawString("   0", 8, 384);
                    graphics.drawString("-0.5", 8, 404);
                    graphics.drawString("-1.0", 8, 424);
                    graphics.drawString("-1.5", 8, 444);
                    graphics.drawString("-2.0", 8, 464);
                    graphics.drawString("-2.5", 8, 484);
                    COD.this.ScaleCOD = 40.0f;
                    break;
                }
                case 2: {
                    graphics.drawString(" 1.0", 8, 34);
                    graphics.drawString(" 0.8", 8, 54);
                    graphics.drawString(" 0.6", 8, 74);
                    graphics.drawString(" 0.4", 8, 94);
                    graphics.drawString(" 0.2", 8, 114);
                    graphics.drawString("   0", 8, 134);
                    graphics.drawString("-0.2", 8, 154);
                    graphics.drawString("-0.4", 8, 174);
                    graphics.drawString("-0.6", 8, 194);
                    graphics.drawString("-0.8", 8, 214);
                    graphics.drawString("-1.0", 8, 234);
                    graphics.drawString(" 1.0", 8, 284);
                    graphics.drawString(" 0.8", 8, 304);
                    graphics.drawString(" 0.6", 8, 324);
                    graphics.drawString(" 0.4", 8, 344);
                    graphics.drawString(" 0.2", 8, 364);
                    graphics.drawString("   0", 8, 384);
                    graphics.drawString("-0.2", 8, 404);
                    graphics.drawString("-0.4", 8, 424);
                    graphics.drawString("-0.6", 8, 444);
                    graphics.drawString("-0.8", 8, 464);
                    graphics.drawString("-1.0", 8, 484);
                    COD.this.ScaleCOD = 100.0f;
                    break;
                }
                case 3: {
                    graphics.drawString(" 0.5", 8, 34);
                    graphics.drawString(" 0.4", 8, 54);
                    graphics.drawString(" 0.3", 8, 74);
                    graphics.drawString(" 0.2", 8, 94);
                    graphics.drawString(" 0.1", 8, 114);
                    graphics.drawString("   0", 8, 134);
                    graphics.drawString("-0.1", 8, 154);
                    graphics.drawString("-0.2", 8, 174);
                    graphics.drawString("-0.3", 8, 194);
                    graphics.drawString("-0.4", 8, 214);
                    graphics.drawString("-0.5", 8, 234);
                    graphics.drawString(" 0.5", 8, 284);
                    graphics.drawString(" 0.4", 8, 304);
                    graphics.drawString(" 0.3", 8, 324);
                    graphics.drawString(" 0.2", 8, 344);
                    graphics.drawString(" 0.1", 8, 364);
                    graphics.drawString("   0", 8, 384);
                    graphics.drawString("-0.1", 8, 404);
                    graphics.drawString("-0.2", 8, 424);
                    graphics.drawString("-0.3", 8, 444);
                    graphics.drawString("-0.4", 8, 464);
                    graphics.drawString("-0.5", 8, 484);
                    COD.this.ScaleCOD = 200.0f;
                    break;
                }
                case 4: {
                    graphics.drawString(" 0.25", 3, 34);
                    graphics.drawString(" 0.20", 3, 54);
                    graphics.drawString(" 0.15", 3, 74);
                    graphics.drawString(" 0.10", 3, 94);
                    graphics.drawString(" 0.05", 3, 114);
                    graphics.drawString("    0", 3, 134);
                    graphics.drawString("-0.05", 3, 154);
                    graphics.drawString("-0.10", 3, 174);
                    graphics.drawString("-0.15", 3, 194);
                    graphics.drawString("-0.20", 3, 214);
                    graphics.drawString("-0.25", 3, 234);
                    graphics.drawString(" 0.25", 3, 284);
                    graphics.drawString(" 0.20", 3, 304);
                    graphics.drawString(" 0.15", 3, 324);
                    graphics.drawString(" 0.10", 3, 344);
                    graphics.drawString(" 0.05", 3, 364);
                    graphics.drawString("    0", 3, 384);
                    graphics.drawString("-0.05", 3, 404);
                    graphics.drawString("-0.10", 3, 424);
                    graphics.drawString("-0.15", 3, 444);
                    graphics.drawString("-0.20", 3, 464);
                    graphics.drawString("-0.25", 3, 484);
                    COD.this.ScaleCOD = 400.0f;
                    break;
                }
                case 5: {
                    graphics.drawString("  0.1", 3, 34);
                    graphics.drawString(" 0.08", 3, 54);
                    graphics.drawString(" 0.06", 3, 74);
                    graphics.drawString(" 0.04", 3, 94);
                    graphics.drawString(" 0.02", 3, 114);
                    graphics.drawString("    0", 3, 134);
                    graphics.drawString("-0.02", 3, 154);
                    graphics.drawString("-0.04", 3, 174);
                    graphics.drawString("-0.06", 3, 194);
                    graphics.drawString("-0.08", 3, 214);
                    graphics.drawString(" -0.1", 3, 234);
                    graphics.drawString("  0.1", 3, 284);
                    graphics.drawString(" 0.08", 3, 304);
                    graphics.drawString(" 0.06", 3, 324);
                    graphics.drawString(" 0.04", 3, 344);
                    graphics.drawString(" 0.02", 3, 364);
                    graphics.drawString("    0", 3, 384);
                    graphics.drawString("-0.02", 3, 404);
                    graphics.drawString("-0.04", 3, 424);
                    graphics.drawString("-0.06", 3, 444);
                    graphics.drawString("-0.08", 3, 464);
                    graphics.drawString(" -0.1", 3, 484);
                    COD.this.ScaleCOD = 1000.0f;
                    break;
                }
            }
            this.drawString7(graphics, "013", 34, 230 + n);
            this.drawString7(graphics, "014", 43, 230 + n2);
            this.drawString7(graphics, "021", 52, 230 + n);
            this.drawString7(graphics, "023", 61, 230 + n2);
            this.drawString7(graphics, "032", 70, 230 + n);
            this.drawString7(graphics, "033", 79, 230 + n2);
            this.drawString7(graphics, "041", 88, 230 + n);
            this.drawString7(graphics, "042", 97, 230 + n2);
            this.drawString7(graphics, "043", 106, 230 + n);
            this.drawString7(graphics, "051", 115, 230 + n2);
            this.drawString7(graphics, "052", 124, 230 + n);
            this.drawString7(graphics, "061", 133, 230 + n2);
            this.drawString7(graphics, "062", 142, 230 + n);
            this.drawString7(graphics, "071", 151, 230 + n2);
            this.drawString7(graphics, "072", 160, 230 + n);
            this.drawString7(graphics, "081", 169, 230 + n2);
            this.drawString7(graphics, "082", 178, 230 + n);
            this.drawString7(graphics, "091", 187, 230 + n2);
            this.drawString7(graphics, "092", 196, 230 + n);
            this.drawString7(graphics, "101", 205, 230 + n2);
            this.drawString7(graphics, "102", 214, 230 + n);
            this.drawString7(graphics, "111", 223, 230 + n2);
            this.drawString7(graphics, "112", 232, 230 + n);
            this.drawString7(graphics, "121", 241, 230 + n2);
            this.drawString7(graphics, "122", 250, 230 + n);
            this.drawString7(graphics, "123", 259, 230 + n2);
            this.drawString7(graphics, "124", 268, 230 + n);
            this.drawString7(graphics, "131", 277, 230 + n2);
            this.drawString7(graphics, "133", 286, 230 + n);
            this.drawString7(graphics, "141", 295, 230 + n2);
            this.drawString7(graphics, "143", 304, 230 + n);
            this.drawString7(graphics, "151", 313, 230 + n2);
            this.drawString7(graphics, "152", 322, 230 + n);
            this.drawString7(graphics, "153", 331, 230 + n2);
            this.drawString7(graphics, "154", 340, 230 + n);
            this.drawString7(graphics, "161", 349, 230 + n2);
            this.drawString7(graphics, "163", 358, 230 + n);
            this.drawString7(graphics, "172", 367, 230 + n2);
            this.drawString7(graphics, "173", 376, 230 + n);
            this.drawString7(graphics, "181", 385, 230 + n2);
            this.drawString7(graphics, "182", 394, 230 + n);
            this.drawString7(graphics, "183", 403, 230 + n2);
            this.drawString7(graphics, "191", 412, 230 + n);
            this.drawString7(graphics, "192", 421, 230 + n2);
            this.drawString7(graphics, "201", 430, 230 + n);
            this.drawString7(graphics, "202", 439, 230 + n2);
            this.drawString7(graphics, "211", 448, 230 + n);
            this.drawString7(graphics, "212", 457, 230 + n2);
            this.drawString7(graphics, "221", 466, 230 + n);
            this.drawString7(graphics, "222", 475, 230 + n2);
            this.drawString7(graphics, "231", 484, 230 + n);
            this.drawString7(graphics, "232", 493, 230 + n2);
            this.drawString7(graphics, "241", 502, 230 + n);
            this.drawString7(graphics, "242", 511, 230 + n2);
            this.drawString7(graphics, "251", 520, 230 + n);
            this.drawString7(graphics, "261", 529, 230 + n2);
            this.drawString7(graphics, "262", 538, 230 + n);
            this.drawString7(graphics, "263", 547, 230 + n2);
            this.drawString7(graphics, "264", 556, 230 + n);
            this.drawString7(graphics, "271", 565, 230 + n2);
            this.drawString7(graphics, "272", 574, 230 + n);
            this.drawString7(graphics, "281", 583, 230 + n2);
            this.drawString7(graphics, "283", 592, 230 + n);
            this.drawString7(graphics, "011", 601, 230 + n2);
            this.drawString7(graphics, "012", 610, 230 + n);
            this.drawString7(graphics, "013", 34, 480 + n);
            this.drawString7(graphics, "014", 43, 480 + n2);
            this.drawString7(graphics, "021", 52, 480 + n);
            this.drawString7(graphics, "023", 61, 480 + n2);
            this.drawString7(graphics, "032", 70, 480 + n);
            this.drawString7(graphics, "033", 79, 480 + n2);
            this.drawString7(graphics, "041", 88, 480 + n);
            this.drawString7(graphics, "042", 97, 480 + n2);
            this.drawString7(graphics, "043", 106, 480 + n);
            this.drawString7(graphics, "051", 115, 480 + n2);
            this.drawString7(graphics, "052", 124, 480 + n);
            this.drawString7(graphics, "061", 133, 480 + n2);
            this.drawString7(graphics, "062", 142, 480 + n);
            this.drawString7(graphics, "071", 151, 480 + n2);
            this.drawString7(graphics, "072", 160, 480 + n);
            this.drawString7(graphics, "081", 169, 480 + n2);
            this.drawString7(graphics, "082", 178, 480 + n);
            this.drawString7(graphics, "091", 187, 480 + n2);
            this.drawString7(graphics, "092", 196, 480 + n);
            this.drawString7(graphics, "101", 205, 480 + n2);
            this.drawString7(graphics, "102", 214, 480 + n);
            this.drawString7(graphics, "111", 223, 480 + n2);
            this.drawString7(graphics, "112", 232, 480 + n);
            this.drawString7(graphics, "121", 241, 480 + n2);
            this.drawString7(graphics, "122", 250, 480 + n);
            this.drawString7(graphics, "123", 259, 480 + n2);
            this.drawString7(graphics, "124", 268, 480 + n);
            this.drawString7(graphics, "131", 277, 480 + n2);
            this.drawString7(graphics, "133", 286, 480 + n);
            this.drawString7(graphics, "141", 295, 480 + n2);
            this.drawString7(graphics, "143", 304, 480 + n);
            this.drawString7(graphics, "151", 313, 480 + n2);
            this.drawString7(graphics, "152", 322, 480 + n);
            this.drawString7(graphics, "153", 331, 480 + n2);
            this.drawString7(graphics, "154", 340, 480 + n);
            this.drawString7(graphics, "161", 349, 480 + n2);
            this.drawString7(graphics, "163", 358, 480 + n);
            this.drawString7(graphics, "172", 367, 480 + n2);
            this.drawString7(graphics, "173", 376, 480 + n);
            this.drawString7(graphics, "181", 385, 480 + n2);
            this.drawString7(graphics, "182", 394, 480 + n);
            this.drawString7(graphics, "183", 403, 480 + n2);
            this.drawString7(graphics, "191", 412, 480 + n);
            this.drawString7(graphics, "192", 421, 480 + n2);
            this.drawString7(graphics, "201", 430, 480 + n);
            this.drawString7(graphics, "202", 439, 480 + n2);
            this.drawString7(graphics, "211", 448, 480 + n);
            this.drawString7(graphics, "212", 457, 480 + n2);
            this.drawString7(graphics, "221", 466, 480 + n);
            this.drawString7(graphics, "222", 475, 480 + n2);
            this.drawString7(graphics, "231", 484, 480 + n);
            this.drawString7(graphics, "232", 493, 480 + n2);
            this.drawString7(graphics, "241", 502, 480 + n);
            this.drawString7(graphics, "242", 511, 480 + n2);
            this.drawString7(graphics, "251", 520, 480 + n);
            this.drawString7(graphics, "261", 529, 480 + n2);
            this.drawString7(graphics, "262", 538, 480 + n);
            this.drawString7(graphics, "263", 547, 480 + n2);
            this.drawString7(graphics, "264", 556, 480 + n);
            this.drawString7(graphics, "271", 565, 480 + n2);
            this.drawString7(graphics, "272", 574, 480 + n);
            this.drawString7(graphics, "281", 583, 480 + n2);
            this.drawString7(graphics, "283", 592, 480 + n);
            this.drawString7(graphics, "011", 601, 480 + n2);
            this.drawString7(graphics, "012", 610, 480 + n);
            this.DrawBackgroundCOD();
        }
        
        public void DrawBackgroundCOD() {
            this.imgGraphCODX = this.createImage(596, 201);
            this.imgGraphCODX0 = this.createImage(596, 201);
            final Graphics graphics = this.imgGraphCODX0.getGraphics();
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, 596, 201);
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, 595, 200);
            graphics.drawLine(0, 100, 595, 100);
            graphics.setColor(Color.gray);
            graphics.drawLine(0, 20, 595, 20);
            graphics.drawLine(0, 40, 595, 40);
            graphics.drawLine(0, 60, 595, 60);
            graphics.drawLine(0, 80, 595, 80);
            graphics.drawLine(0, 120, 595, 120);
            graphics.drawLine(0, 140, 595, 140);
            graphics.drawLine(0, 160, 595, 160);
            graphics.drawLine(0, 180, 595, 180);
            for (int i = 0; i < 65; ++i) {
                graphics.drawLine(9 * (i + 1), 0, 9 * (i + 1), 200);
            }
            this.imgGraphCODX.getGraphics().drawImage(this.imgGraphCODX0, 0, 0, this);
            this.imgGraphCODY = this.createImage(596, 201);
            this.imgGraphCODY0 = this.createImage(596, 201);
            final Graphics graphics2 = this.imgGraphCODY0.getGraphics();
            graphics2.setColor(Color.lightGray);
            graphics2.fillRect(0, 0, 596, 201);
            graphics2.setColor(Color.black);
            graphics2.drawRect(0, 0, 595, 200);
            graphics2.drawLine(0, 100, 595, 100);
            graphics2.setColor(Color.gray);
            graphics2.drawLine(0, 20, 595, 20);
            graphics2.drawLine(0, 40, 595, 40);
            graphics2.drawLine(0, 60, 595, 60);
            graphics2.drawLine(0, 80, 595, 80);
            graphics2.drawLine(0, 120, 595, 120);
            graphics2.drawLine(0, 140, 595, 140);
            graphics2.drawLine(0, 160, 595, 160);
            graphics2.drawLine(0, 180, 595, 180);
            for (int j = 0; j < 65; ++j) {
                graphics2.drawLine(9 * (j + 1), 0, 9 * (j + 1), 200);
            }
            this.imgGraphCODY.getGraphics().drawImage(this.imgGraphCODY0, 0, 0, this);
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
