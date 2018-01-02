import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_LogicGates extends JApplet implements UpdateEvent, UpdateGates
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private static final int GATEPANEL_X = 100;
    private static final int GATEPANEL_Y = 175;
    private static final int GATEPANEL_W = 600;
    private static final int GATEPANEL_H = 310;
    private boolean _boolApplet;
    private boolean _boolLoaded;
    private GatePanel _gatePanel;
    
    public UI_LogicGates() {
        this(true);
    }
    
    public UI_LogicGates(final boolean applet) {
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
        this._boolLoaded = false;
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(123, 123, 123));
        this.pInitTitle("Logic Gates");
        final SelectPanel select = new SelectPanel(this, 100, 75, 600, 75);
        this.getContentPane().add(select);
        this._gatePanel = new GatePanel211(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this._boolLoaded = true;
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
    
    public void setGatePanel211() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanel211(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanel321() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanel321(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanel231() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanel231(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanel431() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanel431(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanel241() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanel241(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanelFF1() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanelFF1(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
    
    public void setGatePanelFF2() {
        this.remove(this._gatePanel);
        this._gatePanel = new GatePanelFF2(100, 175, 600, 310);
        this.getContentPane().add(this._gatePanel);
        this.getContentPane().repaint();
    }
}
