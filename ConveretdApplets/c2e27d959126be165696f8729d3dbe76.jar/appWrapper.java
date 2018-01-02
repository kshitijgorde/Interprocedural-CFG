import java.awt.Window;
import java.awt.Container;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Button;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class appWrapper extends Applet implements AppletStub, Runnable
{
    Thread loader;
    String appletName;
    Applet applet;
    Button startButton;
    String startLabel;
    String stopLabel;
    String frameTitle;
    frame f;
    
    public appWrapper() {
        this.loader = null;
        this.appletName = null;
        this.applet = null;
        this.startButton = null;
    }
    
    public void appletResize(final int width, final int height) {
        System.err.println("appWrapper: appletResize()");
        if (this.applet != null) {
            this.applet.resize(width, height);
        }
    }
    
    public String getAppletInfo() {
        String info = "appWrapper: $Id: appWrapper.java,v 1.9 1997/07/24 13:26:24 leo Exp $\n";
        if (this.applet != null) {
            info = String.valueOf(info) + this.applet.getAppletInfo();
        }
        return info;
    }
    
    public String[][] getParameterInfo() {
        final String[][] wrapper = { { "applet", "String", "appWrapper: Applet to load" } };
        String[][] info;
        if (this.applet != null) {
            final String[][] tmp = this.applet.getParameterInfo();
            info = new String[tmp.length + 1][3];
            System.arraycopy(tmp, 0, info, 1, tmp.length);
        }
        else {
            info = new String[1][3];
        }
        System.arraycopy(wrapper, 0, info, 0, 1);
        return info;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target == this.startButton && evt.id == 1001) {
            if (this.applet == null) {
                this.startButton.setLabel("Loading ...");
                (this.loader = new Thread(this)).start();
            }
            else {
                if (this.applet.getParent() instanceof Frame) {
                    final Frame frame = (Frame)this.applet.getParent();
                    frame.hide();
                    frame.dispose();
                }
                this.applet.stop();
                this.applet.destroy();
                this.applet = null;
                this.startButton.setLabel(this.startLabel);
            }
            return true;
        }
        if (evt.id == 502 && this.applet.getParent() instanceof Frame) {
            final Frame frame = (Frame)this.applet.getParent();
            frame.hide();
            frame.dispose();
            this.add("Center", this.applet);
            this.validate();
            this.layout();
            return true;
        }
        return false;
    }
    
    public void init() {
        final String parameter = this.getParameter("applet");
        this.appletName = parameter;
        if (parameter == null) {
            this.showStatus("appWrapper: missing applet parameter, nothing loaded");
            System.err.println("appWrapper: missing applet parameter");
            return;
        }
        this.setLayout(new BorderLayout());
        if ((this.startLabel = this.getParameter("startButton")) == null) {
            this.run();
        }
        else {
            this.add("Center", this.startButton = new Button(this.getParameter("startButton")));
            if ((this.stopLabel = this.getParameter("stopButton")) == null) {
                this.stopLabel = "STOP!";
            }
            if ((this.frameTitle = this.getParameter("frameTitle")) == null) {
                this.frameTitle = "The Java Telnet Applet";
            }
        }
    }
    
    public void paint(final Graphics g) {
        String message;
        if (this.applet != null) {
            message = "Click to reattach the Applet!";
        }
        else {
            message = "The was no applet load (maybe an error)!";
        }
        final int width = this.size().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(message) / 2;
        final int height = this.size().height / 2;
        g.setColor(Color.red);
        g.drawString(message, width, height);
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        if (this.applet != null) {
            this.applet.reshape(x, y, w, h);
        }
        super.reshape(x, y, w, h);
    }
    
    public void run() {
        Label_0068: {
            if (this.applet == null) {
                Label_0077: {
                    try {
                        (this.applet = (Applet)Class.forName(this.getParameter("applet")).newInstance()).setStub(this);
                        break Label_0077;
                    }
                    catch (Exception e) {
                        System.err.println("appWrapper: could not load " + this.appletName);
                        e.printStackTrace();
                        return;
                    }
                    break Label_0068;
                }
                if (this.startButton == null) {
                    this.add("Center", this.applet);
                    this.applet.init();
                }
                else {
                    ((Container)(this.f = new frame(this.frameTitle))).setLayout(new BorderLayout());
                    ((Container)this.f).add("Center", this.applet);
                    this.applet.init();
                    ((Component)this.f).resize(this.applet.minimumSize());
                    ((Window)this.f).pack();
                    ((Window)this.f).show();
                }
                this.applet.start();
                if (this.startButton != null) {
                    this.startButton.setLabel(this.stopLabel);
                }
                while (this.loader != null) {
                    if (this.f == null || !((Component)this.f).isVisible()) {
                        this.startButton.setLabel(this.startLabel);
                        this.loader.stop();
                        this.loader = null;
                    }
                    try {
                        Thread.sleep(5000L);
                    }
                    catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                return;
            }
        }
        System.err.println("appWrapper: applet already loaded");
    }
}
