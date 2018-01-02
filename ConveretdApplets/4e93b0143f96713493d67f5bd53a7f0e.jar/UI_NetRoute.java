import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JTabbedPane;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_NetRoute extends JApplet
{
    private boolean _boolApplet;
    private boolean _boolLoaded;
    public static JTabbedPane _tabMain;
    
    public UI_NetRoute() {
        this(true);
    }
    
    public UI_NetRoute(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        this.getContentPane().setBackground(Main.WINDOW_COLOR);
        this.getContentPane().setLayout(null);
        (UI_NetRoute._tabMain = new JTabbedPane()).setBackground(Main.WINDOW_COLOR);
        UI_NetRoute._tabMain.setBounds(45, 20, 705, 515);
        new NetManager();
        UI_NetRoute._tabMain.add("Network", NetManager.getNetwork());
        UI_NetRoute._tabMain.add("Network A", NetManager.getNetworkA());
        UI_NetRoute._tabMain.add("Network B", NetManager.getNetworkB());
        UI_NetRoute._tabMain.add("Network C", NetManager.getNetworkC());
        UI_NetRoute._tabMain.add("Network D", NetManager.getNetworkD());
        UI_NetRoute._tabMain.add("Network E", NetManager.getNetworkE());
        this.getContentPane().add(UI_NetRoute._tabMain);
        this.pInitTitle("Network Routing");
        if (!this._boolLoaded) {}
        this._boolLoaded = true;
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(4);
        labelTitle.setHorizontalAlignment(4);
        labelTitle.setFont(new Font("Dialog", 1, 24));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 0, 735, 50);
        this.getContentPane().add(labelTitle);
    }
}
