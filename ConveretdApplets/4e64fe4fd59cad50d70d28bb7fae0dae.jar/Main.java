import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main extends Applet implements Runnable
{
    Thread m_Demo;
    ControlPanel m_ControlPanel;
    int m_nFPS;
    static final int MAX_FPS = 500;
    static final int FPS_STEP = 10;
    String m_sInitialColor;
    String[] m_asText;
    int m_nTextIndex;
    int m_nNumTexts;
    ThreeDText m_ThisApplet;
    ThreeDTextParams[] m_aParam;
    int m_nAppletWidth;
    int m_nAppletHeight;
    private Image m_image;
    private Graphics m_g;
    Dimension m_dimImage;
    boolean m_bPanelAvailable;
    boolean m_bStartStopButtonAvailable;
    boolean m_bFPSButtonAvailable;
    boolean m_bColorButtonAvailable;
    boolean m_bHideButtonAvailable;
    
    public void start() {
        if (this.m_Demo == null) {
            (this.m_Demo = new Thread(this)).start();
        }
    }
    
    public void stopDrawing() {
        this.stop();
    }
    
    public void stop() {
        if (this.m_Demo != null) {
            this.m_Demo.stop();
            this.m_Demo = null;
        }
    }
    
    public int getFPS() {
        return this.m_nFPS;
    }
    
    private synchronized void PaintData(final Graphics graphics) {
        if (this.m_ThisApplet.DrawPlane(graphics)) {
            this.stop();
            this.m_nTextIndex = (this.m_nTextIndex + 1) % this.m_nNumTexts;
            this.m_ThisApplet = new ThreeDText(this.m_asText[this.m_nTextIndex], this.m_aParam[this.m_nTextIndex], this.m_sInitialColor, this.m_nAppletWidth, this.m_nAppletHeight);
            this.start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_bPanelAvailable) {
            if (this.m_ControlPanel == null) {
                this.m_ControlPanel = new ControlPanel(this.m_bStartStopButtonAvailable, this.m_bFPSButtonAvailable, this.m_bColorButtonAvailable, this.m_bHideButtonAvailable, "3D Text Applet Control Panel", this);
            }
            else {
                this.m_ControlPanel.show();
            }
        }
        else {
            new AboutBox("About 3D Text Applet");
        }
        return true;
    }
    
    public void startDrawing() {
        this.start();
    }
    
    public void decrementFPS() {
        final int nfps = this.m_nFPS - 10;
        if (nfps > 0) {
            this.m_nFPS = nfps;
            this.m_ControlPanel.setFPS(this.m_nFPS);
            this.stop();
            this.start();
        }
    }
    
    public void finalize() {
    }
    
    public String getAppletInfo() {
        return "Name: ThreeDText applet v1.05\r\n" + "Author: Jari Lehtonen, 1998\r\n" + "Purpose: Just showing off ;-)";
    }
    
    public void incrementFPS() {
        final int nfps = this.m_nFPS + 10;
        if (nfps < 500) {
            this.m_nFPS = nfps;
            this.m_ControlPanel.setFPS(this.m_nFPS);
            this.stop();
            this.start();
        }
    }
    
    public String getInitialColor() {
        return this.m_sInitialColor;
    }
    
    public void run() {
        final int n = 1000 / this.m_nFPS;
    Label_0009_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.repaint();
                        Thread.sleep(n);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue Label_0009_Outer;
                }
                continue;
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        return this.m_ControlPanel != null && this.m_ControlPanel.action(event, o);
    }
    
    public void destroy() {
    }
    
    public void init() {
        this.m_nNumTexts = Integer.parseInt(this.getParameter("NUMBER_OF_TEXTS"));
        this.m_asText = new String[this.m_nNumTexts];
        this.m_aParam = new ThreeDTextParams[this.m_nNumTexts];
        this.m_sInitialColor = new String(this.getParameter("COLOR"));
        for (int i = 0; i < this.m_nNumTexts; ++i) {
            this.m_asText[i] = new String(this.getParameter(new String("TEXT_") + String.valueOf(i)));
            final String string = new String("TEXT_") + String.valueOf(i);
            this.m_aParam[i] = new ThreeDTextParams(new Double(this.getParameter(string + "_ZOOMINSPEED")), new Double(this.getParameter(string + "_DESTINATIONZ")), new Double(this.getParameter(string + "_ZOOMOUTSPEED")), new Double(this.getParameter(string + "_TIMETODIEZ")), Integer.parseInt(this.getParameter(string + "_DELAY")), Integer.parseInt(this.getParameter(string + "_SPIN")), Integer.parseInt(this.getParameter(string + "_TIP")), this.m_sInitialColor);
        }
        if (new String(this.getParameter("CONTROL_PANEL_AVAILABLE")).equalsIgnoreCase("true")) {
            this.m_bPanelAvailable = true;
        }
        else {
            this.m_bPanelAvailable = false;
        }
        if (this.getParameter("STARTSTOP_BUTTON").equalsIgnoreCase("true")) {
            this.m_bStartStopButtonAvailable = true;
        }
        else {
            this.m_bStartStopButtonAvailable = false;
        }
        if (this.getParameter("FPS_BUTTON").equalsIgnoreCase("true")) {
            this.m_bFPSButtonAvailable = true;
        }
        else {
            this.m_bFPSButtonAvailable = false;
        }
        if (this.getParameter("COLOR_BUTTON").equalsIgnoreCase("true")) {
            this.m_bColorButtonAvailable = true;
        }
        else {
            this.m_bColorButtonAvailable = false;
        }
        if (this.getParameter("HIDE_BUTTON").equalsIgnoreCase("true")) {
            this.m_bHideButtonAvailable = true;
        }
        else {
            this.m_bHideButtonAvailable = false;
        }
        this.m_nAppletWidth = this.size().width;
        this.m_nAppletHeight = this.size().height;
        if (this.m_bPanelAvailable) {
            this.m_ControlPanel = new ControlPanel(this.m_bStartStopButtonAvailable, this.m_bFPSButtonAvailable, this.m_bColorButtonAvailable, this.m_bHideButtonAvailable, "3D Text Applet Control Panel", this);
        }
        this.resize(this.m_nAppletWidth, this.m_nAppletHeight);
        this.setBackground(Color.black);
        this.m_dimImage = new Dimension(this.m_nAppletWidth, this.m_nAppletHeight);
        this.m_image = this.createImage(this.m_nAppletWidth, this.m_nAppletHeight);
        this.m_g = this.m_image.getGraphics();
        this.m_nTextIndex = 0;
        this.m_ThisApplet = new ThreeDText(this.m_asText[this.m_nTextIndex], this.m_aParam[this.m_nTextIndex], this.m_sInitialColor, this.m_nAppletWidth, this.m_nAppletHeight);
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_image != null) {
            graphics.drawImage(this.m_image, 0, 0, null);
        }
    }
    
    public void update(final Graphics graphics) {
        final Color foreground = this.getForeground();
        this.m_g.setColor(this.getBackground());
        this.m_g.fillRect(0, 0, this.m_dimImage.width, this.m_dimImage.height);
        this.m_g.setColor(foreground);
        this.PaintData(this.m_g);
        this.paint(graphics);
    }
    
    public void setTextColor(final String s) {
        for (int i = 0; i < this.m_nNumTexts; ++i) {
            this.m_aParam[i].m_Color = ThreeDTextParams.GetColorFromString(s);
        }
        this.m_ThisApplet.SetColor(s);
    }
    
    public Main() {
        this.m_nFPS = 50;
    }
}
