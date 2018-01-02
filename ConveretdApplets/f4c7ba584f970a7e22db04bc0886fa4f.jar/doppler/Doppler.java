// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.net.URL;
import java.awt.Event;
import edu.davidson.graphics.EtchedBorder;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Checkbox;
import java.applet.Applet;

public class Doppler extends Applet implements Runnable
{
    private Thread m_Doppler;
    private VarScrollBar speedBar;
    private DopplerCanvas dc;
    private int sleepTime;
    private Checkbox cCheck;
    private Checkbox rCheck;
    private SizedButton runBtn;
    private boolean runOnStart;
    private boolean m_fStandAlone;
    private double m_speed;
    private int m_fps;
    private boolean m_relativistic;
    private String m_helpFile;
    private boolean m_showControls;
    private boolean m_showButtons;
    private final String PARAM_speed = "speed";
    private final String PARAM_fps = "fps";
    private final String PARAM_relativistic = "relativistic";
    private final String PARAM_helpFile = "helpFile";
    private final String PARAM_showControls = "showControls";
    private final String PARAM_showButtons = "showButtons";
    
    String GetParameter(final String s, final String[] array) {
        if (array == null) {
            return this.getParameter(s);
        }
        final String concat = String.valueOf(s).concat(String.valueOf("="));
        String s2 = null;
        final int length = concat.length();
        try {
            for (int i = 0; i < array.length; ++i) {
                if (concat.equalsIgnoreCase(array[i].substring(0, length))) {
                    s2 = array[i].substring(length);
                    if (s2.startsWith("\"")) {
                        s2 = s2.substring(1);
                        if (s2.endsWith("\"")) {
                            s2 = s2.substring(0, s2.length() - 1);
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    void GetParameters(final String[] array) {
        final String getParameter = this.GetParameter("speed", array);
        if (getParameter != null) {
            this.m_speed = Double.valueOf(getParameter);
        }
        final String getParameter2 = this.GetParameter("fps", array);
        if (getParameter2 != null) {
            this.m_fps = Integer.parseInt(getParameter2);
        }
        final String getParameter3 = this.GetParameter("relativistic", array);
        if (getParameter3 != null) {
            this.m_relativistic = Boolean.valueOf(getParameter3);
        }
        final String getParameter4 = this.GetParameter("showControls", array);
        if (getParameter4 != null) {
            this.m_showControls = Boolean.valueOf(getParameter4);
        }
        final String getParameter5 = this.GetParameter("showButtons", array);
        if (getParameter5 != null) {
            this.m_showButtons = Boolean.valueOf(getParameter5);
        }
        final String getParameter6 = this.GetParameter("helpFile", array);
        if (getParameter6 != null) {
            this.m_helpFile = getParameter6;
        }
    }
    
    public static void main(final String[] array) {
        final DopplerFrame dopplerFrame = new DopplerFrame("Doppler");
        dopplerFrame.show();
        dopplerFrame.hide();
        dopplerFrame.resize(dopplerFrame.insets().left + dopplerFrame.insets().right + 320, dopplerFrame.insets().top + dopplerFrame.insets().bottom + 340);
        final Doppler doppler = new Doppler();
        dopplerFrame.add("Center", doppler);
        doppler.m_fStandAlone = true;
        doppler.GetParameters(array);
        doppler.init();
        doppler.start();
        dopplerFrame.show();
    }
    
    public Doppler() {
        this.m_Doppler = null;
        this.sleepTime = 50;
        this.runOnStart = false;
        this.m_fStandAlone = false;
        this.m_speed = 0.5;
        this.m_fps = 20;
        this.m_relativistic = false;
        this.m_helpFile = null;
        this.m_showControls = true;
        this.m_showButtons = true;
    }
    
    public String getAppletInfo() {
        return "Name: Doppler ver 1.2\r\nAuthor: Wolfgang Christian\r\nemail: wochristian@davidson.edu\r\n";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "speed", "double", "Source speed" }, { "fps", "int", "Frames per second" }, { "relativistic", "boolean", "Relativistic effects." }, { "showControls", "boolean", "Show controls and buttons." }, { "showButtons", "boolean", "Show classical/relativistic options." }, { "helpFile", "String", "Applet help file URL." } };
    }
    
    public void init() {
        this.runOnStart = false;
        if (!this.m_fStandAlone) {
            this.GetParameters(null);
        }
        this.resize(320, 340);
        this.sleepTime = 1000 / this.m_fps;
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        final Panel panel2 = new Panel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new GridLayout(1, 2, 4, 4));
        panel2.add(this.cCheck = new Checkbox("Classical", checkboxGroup, true));
        panel2.add(this.rCheck = new Checkbox("Relativistic", checkboxGroup, false));
        if (this.m_showButtons) {
            panel.add("North", panel2);
        }
        if (this.m_relativistic) {
            if (this.m_speed > 0.99) {
                this.m_speed = 0.99;
            }
            this.speedBar = new VarScrollBar("speed= ", this.m_speed, 0.0, 0.99);
        }
        else {
            if (this.m_speed > 2.0) {
                this.m_speed = 2.0;
            }
            this.speedBar = new VarScrollBar("speed", this.m_speed, 0.0, 2.0);
        }
        panel.add("Center", new EtchedBorder(this.speedBar));
        panel.add("West", this.runBtn = new SizedButton("Run "));
        if (this.m_showControls) {
            this.add("South", panel);
        }
        (this.dc = new DopplerCanvas(this)).setBackground(Color.white);
        this.add("Center", this.dc);
        this.dc.setSpeed(this.m_speed, 0.0);
        this.dc.setRelativistic(this.m_relativistic);
    }
    
    public void destroy() {
        if (this.m_Doppler != null) {
            this.m_Doppler.stop();
            this.m_Doppler = null;
        }
    }
    
    public void reset() {
        this.dc.reset();
        this.dc.repaint();
    }
    
    public void start() {
        if (this.m_Doppler == null) {
            (this.m_Doppler = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_Doppler != null) {
            this.m_Doppler.stop();
            this.m_Doppler = null;
        }
    }
    
    public void forward() {
        this.runOnStart = true;
        this.start();
    }
    
    public void pause() {
        this.runOnStart = false;
        this.stop();
    }
    
    public void step() {
        if (this.m_Doppler == null) {
            this.dc.incTime(this.m_speed);
        }
    }
    
    public void run() {
        final boolean b = true;
        while (this.runOnStart && b) {
            try {
                this.dc.incTime(this.m_speed);
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
        this.m_Doppler = null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target.equals(this.speedBar)) {
            this.m_speed = this.speedBar.getValue();
            this.dc.setSpeed(this.m_speed, 0.0);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x4) != 0x0 || (event.modifiers & 0x8) != 0x0) {
            final String helpFile = this.m_helpFile;
            if (helpFile != null) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), helpFile), "_blank");
                }
                catch (Exception ex) {
                    System.out.println("Failed to load help file!");
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.runBtn)) {
            if (this.runOnStart) {
                this.runOnStart = false;
                this.runBtn.setLabel("Stop");
                this.pause();
            }
            else {
                this.runOnStart = false;
                this.runBtn.setLabel("Run ");
                this.forward();
            }
            return true;
        }
        if (event.target.equals(this.rCheck)) {
            this.m_relativistic = true;
            if (this.m_speed > 1.0) {
                this.m_speed = 0.99;
            }
            this.speedBar.setMinMax(0.0, 0.99);
            this.speedBar.setValue(this.m_speed);
            this.dc.setSpeed(this.m_speed, 0.0);
            this.dc.setRelativistic(this.m_relativistic);
            return true;
        }
        if (event.target.equals(this.cCheck)) {
            this.m_relativistic = false;
            this.speedBar.setMinMax(0.0, 2.0);
            this.speedBar.setValue(this.m_speed);
            this.dc.setRelativistic(this.m_relativistic);
            return true;
        }
        return false;
    }
    
    public void setSpeed(double n) {
        if (n < 0) {
            n = 0.0;
        }
        if (this.m_relativistic) {
            if (n > 0.99) {
                this.m_speed = 0.99;
            }
            else {
                this.m_speed = n;
            }
        }
        else if (n > 2.0) {
            this.m_speed = 2.0;
        }
        else {
            this.m_speed = n;
        }
        this.speedBar.setValue(this.m_speed);
        this.dc.setSpeed(this.m_speed, 0.0);
    }
    
    public void setSourceX(final double n) {
        this.dc.setX((int)n);
    }
    
    public void setClassical() {
        if (this.m_relativistic) {
            this.m_relativistic = false;
            this.speedBar.setMinMax(0.0, 2.0);
            this.speedBar.setValue(this.m_speed);
            this.dc.setRelativistic(this.m_relativistic);
            this.cCheck.setState(true);
        }
    }
    
    public void setRelativistic() {
        if (this.m_relativistic) {
            return;
        }
        this.m_relativistic = true;
        if (this.m_speed > 1.0) {
            this.m_speed = 0.99;
        }
        this.speedBar.setMinMax(0.0, 0.99);
        this.speedBar.setValue(this.m_speed);
        this.dc.setSpeed(this.m_speed, 0.0);
        this.dc.setRelativistic(this.m_relativistic);
        this.rCheck.setState(true);
    }
    
    public void setCaption(final String caption) {
        this.dc.setCaption(caption);
    }
}
