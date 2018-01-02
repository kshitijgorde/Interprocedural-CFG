import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_SoftEng extends JApplet
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final Color WINOW_COLOR;
    private static final int CVS_PANEL = 0;
    private static final int TEST_PANEL = 1;
    private final boolean _boolApplet;
    private boolean _boolLoaded;
    private static JTabbedPane _tabMain;
    private static JLabel _textProject;
    
    public UI_SoftEng() {
        this(true);
    }
    
    public UI_SoftEng(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public final void init() {
        this._boolLoaded = false;
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(UI_SoftEng.WINOW_COLOR);
        this.pInitTitle("Software Testing");
        final CVSPanel cvsPanel = new CVSPanel();
        final BlackBoxPanel bbPanel = new BlackBoxPanel();
        (UI_SoftEng._textProject = new JLabel("Project: N/A")).setHorizontalTextPosition(2);
        UI_SoftEng._textProject.setHorizontalAlignment(2);
        UI_SoftEng._textProject.setFont(new Font("Dialog", 1, 18));
        UI_SoftEng._textProject.setForeground(Color.orange);
        UI_SoftEng._textProject.setBounds(20, 10, 400, 20);
        this.getContentPane().add(UI_SoftEng._textProject);
        (UI_SoftEng._tabMain = new JTabbedPane()).setBounds(10, 40, 775, 525);
        UI_SoftEng._tabMain.add("Version Control", cvsPanel);
        UI_SoftEng._tabMain.setBackgroundAt(0, UI_SoftEng.WINOW_COLOR);
        UI_SoftEng._tabMain.add("Black Box Testing", bbPanel);
        UI_SoftEng._tabMain.setBackgroundAt(1, UI_SoftEng.WINOW_COLOR);
        UI_SoftEng._tabMain.setEnabledAt(1, false);
        this.getContentPane().add(UI_SoftEng._tabMain);
        if (!this._boolLoaded) {
            this._boolLoaded = true;
        }
    }
    
    public static void showBlackBox() {
        UI_SoftEng._tabMain.setSelectedIndex(1);
        UI_SoftEng._tabMain.setEnabledAt(1, true);
    }
    
    public static void disableBlackBox() {
        UI_SoftEng._tabMain.setEnabledAt(1, false);
    }
    
    public static void setProject(final String x) {
        UI_SoftEng._textProject.setText(x);
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null || sTitle.length() == 0) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(270, 10, 485, 50);
        this.getContentPane().add(labelTitle);
    }
    
    static {
        WINOW_COLOR = new Color(123, 123, 123);
    }
}
