import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.Timer;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_CpuScheduling extends JApplet implements ChangeListener
{
    private boolean _boolApplet;
    private boolean _boolLoaded;
    public static JTabbedPane _tabMain;
    private SetupPanel _setupPanel;
    private RunPanel _runPanel;
    private Timer _timer;
    public static final int PANEL_SETUP = 0;
    public static final int PANEL_RUN = 1;
    
    public UI_CpuScheduling() {
        this(true);
    }
    
    public UI_CpuScheduling(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        this.getContentPane().setBackground(Main.WINDOW_COLOR);
        this.getContentPane().setLayout(null);
        (UI_CpuScheduling._tabMain = new JTabbedPane()).setBackground(Main.WINDOW_COLOR);
        UI_CpuScheduling._tabMain.setBounds(45, 20, 705, 515);
        UI_CpuScheduling._tabMain.addChangeListener(this);
        this._setupPanel = new SetupPanel();
        this._runPanel = new RunPanel(this._setupPanel);
        UI_CpuScheduling._tabMain.add("Setup", this._setupPanel);
        UI_CpuScheduling._tabMain.add("Run", this._runPanel);
        this.getContentPane().add(UI_CpuScheduling._tabMain);
        UI_CpuScheduling._tabMain.setSelectedIndex(0);
        this.pInitTitle("CPU Scheduling");
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
    
    public void stateChanged(final ChangeEvent e) {
        if (!this._boolLoaded) {
            return;
        }
        this._runPanel.timerRunStop();
        (this._timer = new Timer(0, new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_CpuScheduling.this.timerTimer(UI_CpuScheduling._tabMain.getSelectedIndex());
            }
        })).start();
    }
    
    public void timerTimer(final int index) {
        if (index == 1) {
            this._setupPanel.updateQueues();
            this._runPanel.updateQueues();
        }
        else if (index == 0) {}
        this._timer.stop();
    }
}
