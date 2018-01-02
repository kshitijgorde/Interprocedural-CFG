import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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
import java.io.IOException;
import javax.swing.JLabel;
import lib.DChTCP;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IDGap extends JApplet implements Runnable
{
    DChTCP dch;
    Thread thread;
    static String DChServer;
    static int DChPort;
    JLabel lblSGU01CurtGap;
    JLabel lblU02CurtGap;
    JLabel lblSGU03CurtGap;
    JLabel lblMPW05CurtGap;
    JLabel lblMPW13CurtGap;
    JLabel lblU161RhoHalf;
    JLabel lblU161Mode;
    JLabel lblU162RhoHalf;
    JLabel lblU162Mode;
    JLabel lblSGU17CurtGap;
    JLabel lblR19CurtGap;
    JLabel lblR19CurtMode;
    JLabel lblEMPW28CurtGap;
    JLabel lblEMPW28CurtMode;
    JLabel lblDate_Time;
    float SGU01CurtGap;
    float U02CurtGap;
    float SGU03CurtGap;
    float MPW05CurtGap;
    float MPW13CurtGap;
    float U161RhoHalf;
    String U161Mode;
    float U162RhoHalf;
    String U162Mode;
    float SGU17CurtGap;
    float R19CurtGap;
    String R19Mode;
    float EMPW28CurtGap;
    String EMPW28Mode;
    String strSGU01CurtGap;
    String strU02CurtGap;
    String strSGU03CurtGap;
    String strMPW05CurtGap;
    String strMPW13CurtGap;
    String strU161RhoHalf;
    String strU161Mode;
    String strU162RhoHalf;
    String strU162Mode;
    String strSGU17CurtGap;
    String strR19CurtGap;
    String strR19CurtMode;
    String strEMPW28CurtGap;
    String strEMPW28CurtMode;
    String Date_Time;
    
    public IDGap() {
        this.thread = null;
        this.lblSGU01CurtGap = new JLabel("", 4);
        this.lblU02CurtGap = new JLabel("", 4);
        this.lblSGU03CurtGap = new JLabel("", 4);
        this.lblMPW05CurtGap = new JLabel("", 4);
        this.lblMPW13CurtGap = new JLabel("", 4);
        this.lblU161RhoHalf = new JLabel("", 4);
        this.lblU161Mode = new JLabel("", 2);
        this.lblU162RhoHalf = new JLabel("", 4);
        this.lblU162Mode = new JLabel("", 2);
        this.lblSGU17CurtGap = new JLabel("", 4);
        this.lblR19CurtGap = new JLabel("", 4);
        this.lblR19CurtMode = new JLabel("", 2);
        this.lblEMPW28CurtGap = new JLabel("", 4);
        this.lblEMPW28CurtMode = new JLabel("", 2);
        this.lblDate_Time = new JLabel("", 2);
        this.SGU01CurtGap = 0.0f;
        this.U02CurtGap = 0.0f;
        this.SGU03CurtGap = 0.0f;
        this.MPW05CurtGap = 0.0f;
        this.MPW13CurtGap = 0.0f;
        this.U161RhoHalf = 0.0f;
        this.U162RhoHalf = 0.0f;
        this.SGU17CurtGap = 0.0f;
        this.R19CurtGap = 0.0f;
        this.EMPW28CurtGap = 0.0f;
        this.Date_Time = "";
    }
    
    @Override
    public void init() {
        try {
            try {
                this.dch = new DChTCP(this.getDocumentBase().getHost(), 5500);
            }
            catch (NullPointerException ex) {
                this.dch = new DChTCP(IDGap.DChServer, IDGap.DChPort);
            }
            this.dch.open_dch();
        }
        catch (IOException ex2) {}
        final Font font = new Font("Serif", 0, 30);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(10, 3, 10, 5));
        final JLabel label = new JLabel("SGU#01", 2);
        label.setFont(font);
        panel.add(label);
        this.lblSGU01CurtGap.setFont(font);
        panel.add(this.lblSGU01CurtGap);
        final JLabel label2 = new JLabel("", 2);
        label2.setFont(font);
        panel.add(label2);
        final JLabel label3 = new JLabel("U#02", 2);
        label3.setFont(font);
        panel.add(label3);
        this.lblU02CurtGap.setFont(font);
        panel.add(this.lblU02CurtGap);
        final JLabel label4 = new JLabel("", 2);
        label4.setFont(font);
        panel.add(label4);
        final JLabel label5 = new JLabel("SGU#03", 2);
        label5.setFont(font);
        panel.add(label5);
        this.lblSGU03CurtGap.setFont(font);
        panel.add(this.lblSGU03CurtGap);
        final JLabel label6 = new JLabel("", 2);
        label6.setFont(font);
        panel.add(label6);
        final JLabel label7 = new JLabel("MPW#05", 2);
        label7.setFont(font);
        panel.add(label7);
        this.lblMPW05CurtGap.setFont(font);
        panel.add(this.lblMPW05CurtGap);
        final JLabel label8 = new JLabel("", 2);
        label8.setFont(font);
        panel.add(label8);
        final JLabel label9 = new JLabel("MPW#13", 2);
        label9.setFont(font);
        panel.add(label9);
        this.lblMPW13CurtGap.setFont(font);
        panel.add(this.lblMPW13CurtGap);
        final JLabel label10 = new JLabel("", 2);
        label10.setFont(font);
        panel.add(label10);
        final JLabel label11 = new JLabel("U#161 Rho/2", 2);
        label11.setFont(font);
        panel.add(label11);
        this.lblU161RhoHalf.setFont(font);
        panel.add(this.lblU161RhoHalf);
        this.lblU161Mode.setFont(font);
        panel.add(this.lblU161Mode);
        final JLabel label12 = new JLabel("U#162 Rho/2", 2);
        label12.setFont(font);
        panel.add(label12);
        this.lblU162RhoHalf.setFont(font);
        panel.add(this.lblU162RhoHalf);
        this.lblU162Mode.setFont(font);
        panel.add(this.lblU162Mode);
        final JLabel label13 = new JLabel("SGU#17", 2);
        label13.setFont(font);
        panel.add(label13);
        this.lblSGU17CurtGap.setFont(font);
        panel.add(this.lblSGU17CurtGap);
        final JLabel label14 = new JLabel("", 2);
        label14.setFont(font);
        panel.add(label14);
        final JLabel label15 = new JLabel("R#19", 2);
        label15.setFont(font);
        panel.add(label15);
        this.lblR19CurtGap.setFont(font);
        panel.add(this.lblR19CurtGap);
        this.lblR19CurtMode.setFont(font);
        panel.add(this.lblR19CurtMode);
        final JLabel label16 = new JLabel("EMPW#28", 2);
        label16.setFont(font);
        panel.add(label16);
        this.lblEMPW28CurtGap.setFont(font);
        panel.add(this.lblEMPW28CurtGap);
        this.lblEMPW28CurtMode.setFont(font);
        panel.add(this.lblEMPW28CurtMode);
        final JLabel label17 = new JLabel("Network connection was broken...", 0);
        label17.setFont(new Font("SansSerif", 0, 24));
        label17.setForeground(Color.yellow);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(255, 0, 0, 192));
        panel2.add(label17, "Center");
        final Container container = (Container)this.getGlassPane();
        container.setLayout(new BorderLayout());
        container.add(panel2, "Center");
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(new BorderLayout());
        final JLabel label18 = new JLabel("Insertion Devices Current Gap", 0);
        label18.setFont(font);
        this.lblDate_Time.setFont(new Font("SansSerif", 1, 12));
        contentPane.add(label18, "North");
        contentPane.add(panel, "Center");
        contentPane.add(this.lblDate_Time, "South");
        this.setVisible(true);
    }
    
    @Override
    public void run() {
        while (this.thread != null) {
            try {
                this.Date_Time = this.dch.readString("general", "Date_Time", 20);
                this.SGU01CurtGap = this.dch.readFloat("insertion", "#01CurtGap");
                this.U02CurtGap = this.dch.readFloat("insertion", "#02CurtGap1");
                this.SGU03CurtGap = this.dch.readFloat("insertion", "#03CurtGap");
                this.MPW05CurtGap = this.dch.readFloat("insertion", "#05CurtGap");
                this.MPW13CurtGap = this.dch.readFloat("insertion", "#13CurtGap");
                this.U161RhoHalf = this.dch.readFloat("insertion", "#161RhoHalf");
                this.U161Mode = this.dch.readString("insertion", "#161Mode", 16);
                this.U162RhoHalf = this.dch.readFloat("insertion", "#162RhoHalf");
                this.U162Mode = this.dch.readString("insertion", "#162Mode", 16);
                this.SGU17CurtGap = this.dch.readFloat("insertion", "#17CurtGap");
                this.R19CurtGap = this.dch.readFloat("insertion", "#19CurtGap");
                this.R19Mode = this.dch.readString("insertion", "#19Mode", 16);
                this.EMPW28CurtGap = this.dch.readFloat("insertion", "#28CurtGap");
                this.EMPW28Mode = this.dch.readString("insertion", "#28Mode", 3);
            }
            catch (IOException ex) {
                System.out.println("IOException");
                break;
            }
            this.strSGU01CurtGap = String.format("%6.2f mm", this.SGU01CurtGap);
            this.strU02CurtGap = String.format("%6.2f mm", this.U02CurtGap);
            this.strSGU03CurtGap = String.format("%6.2f mm", this.SGU03CurtGap);
            this.strMPW05CurtGap = String.format("%6.2f mm", this.MPW05CurtGap);
            this.strMPW13CurtGap = String.format("%6.2f mm", this.MPW13CurtGap);
            this.strU161RhoHalf = String.format("%6.2f mm", this.U161RhoHalf);
            this.strU161Mode = "Mode " + this.U161Mode.substring(0, this.U161Mode.indexOf(0));
            this.strU162RhoHalf = String.format("%6.2f mm", this.U162RhoHalf);
            this.strU162Mode = "Mode " + this.U162Mode.substring(0, this.U162Mode.indexOf(0));
            this.strSGU17CurtGap = String.format("%6.2f mm", this.SGU17CurtGap);
            this.strR19CurtGap = String.format("%6.2f mm", this.R19CurtGap);
            this.strR19CurtMode = "Surface " + this.R19Mode.substring(0, this.R19Mode.indexOf(0));
            this.strEMPW28CurtGap = String.format("%6.2f mm", this.EMPW28CurtGap);
            this.strEMPW28CurtMode = "Mode " + this.EMPW28Mode;
            this.lblSGU01CurtGap.setText(this.strSGU01CurtGap);
            this.lblU02CurtGap.setText(this.strU02CurtGap);
            this.lblSGU03CurtGap.setText(this.strSGU03CurtGap);
            this.lblMPW05CurtGap.setText(this.strMPW05CurtGap);
            this.lblMPW13CurtGap.setText(this.strMPW13CurtGap);
            this.lblU161RhoHalf.setText(this.strU161RhoHalf);
            this.lblU161Mode.setText(this.strU161Mode);
            this.lblU162RhoHalf.setText(this.strU162RhoHalf);
            this.lblU162Mode.setText(this.strU162Mode);
            this.lblSGU17CurtGap.setText(this.strSGU17CurtGap);
            this.lblR19CurtGap.setText(this.strR19CurtGap);
            this.lblR19CurtMode.setText(this.strR19CurtMode);
            this.lblEMPW28CurtGap.setText(this.strEMPW28CurtGap);
            this.lblEMPW28CurtMode.setText(this.strEMPW28CurtMode);
            this.lblDate_Time.setText(this.Date_Time);
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
        final JFrame frame = new JFrame("IDs Current Gap");
        final IDGap idGap = new IDGap();
        frame.getContentPane().add(idGap);
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);
        if (array.length >= 1) {
            IDGap.DChServer = array[0];
        }
        if (array.length >= 2) {
            IDGap.DChPort = Integer.parseInt(array[1]);
        }
        idGap.init();
        idGap.start();
        final Insets insets = frame.getInsets();
        frame.setSize(512 + insets.left + insets.right, 333 + insets.top + insets.bottom);
    }
    
    static {
        IDGap.DChServer = "www-pfring.kek.jp";
        IDGap.DChPort = 5500;
    }
    
    static class WindowEventHandler extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
            System.exit(0);
        }
    }
}
