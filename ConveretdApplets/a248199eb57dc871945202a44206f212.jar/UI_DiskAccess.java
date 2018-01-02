import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_DiskAccess extends JApplet implements FileAddMethod, ActionListener
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private boolean _boolApplet;
    private DiskPanelElevator _panelLeft;
    private DiskPanelFCFS _panelRight;
    private DesktopPanel _panelMiddle;
    private Timer _timer;
    
    public UI_DiskAccess() {
        this(true);
    }
    
    public UI_DiskAccess(final boolean applet) {
        this._boolApplet = applet;
        if (applet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    private String pGetParameter(final String key, final String def) {
        if (this._boolApplet) {
            final String param = this.getParameter(key);
            return (param == null) ? def : param;
        }
        return System.getProperty(key, def);
    }
    
    public void init() {
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(123, 123, 123));
        this._panelLeft = new DiskPanelElevator(485, 150, true);
        this.getContentPane().add(this._panelLeft);
        this._panelMiddle = new DesktopPanel(this, 335, 150);
        this.getContentPane().add(this._panelMiddle);
        this._panelRight = new DiskPanelFCFS(50, 150, false);
        this.getContentPane().add(this._panelRight);
        (this._timer = new Timer(50, this)).start();
        final JLabel labelTitle = new JLabel("FCFS");
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 18));
        labelTitle.setForeground(Color.white);
        labelTitle.setBounds(160, 110, 100, 30);
        this.getContentPane().add(labelTitle, 0);
        final JLabel label2 = new JLabel("Elevator");
        label2.setHorizontalTextPosition(0);
        label2.setHorizontalAlignment(0);
        label2.setFont(new Font("Dialog", 1, 18));
        label2.setForeground(Color.white);
        label2.setBounds(540, 110, 100, 30);
        this.getContentPane().add(label2, 0);
        final JLabel labelTitle2 = new JLabel("Queue");
        labelTitle2.setHorizontalTextPosition(2);
        labelTitle2.setHorizontalAlignment(2);
        labelTitle2.setFont(new Font("Dialog", 1, 18));
        labelTitle2.setForeground(Color.white);
        labelTitle2.setBounds(50, 390, 200, 30);
        this.getContentPane().add(labelTitle2, 0);
        final JLabel label3 = new JLabel("Priority Queue");
        label3.setHorizontalTextPosition(0);
        label3.setHorizontalAlignment(0);
        label3.setFont(new Font("Dialog", 1, 18));
        label3.setForeground(Color.white);
        label3.setBounds(590, 390, 200, 30);
        this.getContentPane().add(label3, 0);
        this.pInitTitle("Disk Access Times");
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 10, 800, 50);
        this.getContentPane().add(labelTitle);
    }
    
    public void update() {
        this.getContentPane().repaint();
    }
    
    public void addFile(final FileComponent file) {
        this._panelLeft.addFile(new FileComponent(file));
        this._panelRight.addFile(new FileComponent(file));
    }
    
    public void actionPerformed(final ActionEvent e) {
        this._panelLeft.actionPerformed(e);
        this._panelRight.actionPerformed(e);
    }
}
