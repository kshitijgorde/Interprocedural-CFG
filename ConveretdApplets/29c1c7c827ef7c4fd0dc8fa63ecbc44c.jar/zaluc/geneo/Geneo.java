// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.applet.Applet;

public class Geneo extends Applet
{
    static int instanceCount;
    static MainFrame frame;
    Globals globals;
    Rectangle innerRect;
    MainPanel panel;
    boolean embedInPage;
    int borderWidth;
    
    public void init() {
        final String parameter = this.getParameter("Source");
        final String parameter2 = this.getParameter("Width");
        final String parameter3 = this.getParameter("Height");
        final String parameter4 = this.getParameter("Foreground");
        final String parameter5 = this.getParameter("Background");
        final String parameter6 = this.getParameter("PeopleBoxBkg");
        final String parameter7 = this.getParameter("PBoxBorderWidth");
        final String parameter8 = this.getParameter("BkgImage");
        final String parameter9 = this.getParameter("BkgImgLayout");
        final String parameter10 = this.getParameter("ClearBkg");
        final String parameter11 = this.getParameter("BorderWidth");
        final String parameter12 = this.getParameter("EmbedInPage");
        final String parameter13 = this.getParameter("Primary");
        final String parameter14 = this.getParameter("DetailLoc");
        final String parameter15 = this.getParameter("HtmlTarget");
        final String parameter16 = this.getParameter("InitialZoom");
        final String parameter17 = this.getParameter("HelpUrl");
        final String parameter18 = this.getParameter("DumpStats");
        if (parameter != null) {
            if (parameter12 != null && (parameter12.equalsIgnoreCase("true") || parameter12.equalsIgnoreCase("yes") || parameter12.equals("1"))) {
                this.embedInPage = true;
            }
            if (this.embedInPage) {
                if (parameter11 != null) {
                    this.borderWidth = Integer.parseInt(parameter11);
                }
                else {
                    this.borderWidth = 3;
                }
            }
            this.internalBegin(parameter, Integer.parseInt(parameter2), Integer.parseInt(parameter3), parameter4, parameter5, parameter6, parameter7, parameter8, parameter9, parameter10, parameter13, parameter14, parameter15, parameter16, parameter17, parameter18, this.embedInPage);
        }
    }
    
    public void begin(final String s, final int n, final int n2, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13) {
        this.internalBegin(s, n, n2, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, "0", false);
    }
    
    public int getInstanceCount() {
        return Geneo.instanceCount;
    }
    
    public boolean isLoaded() {
        return Geneo.instanceCount > 0;
    }
    
    public void closeInstance() {
        --Geneo.instanceCount;
    }
    
    public void find() {
        if (this.panel != null) {
            this.panel.find();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.find();
        }
    }
    
    public void zoomIn() {
        if (this.panel != null) {
            this.panel.zoomIn();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.zoomIn();
        }
    }
    
    public void zoomOut() {
        if (this.panel != null) {
            this.panel.zoomOut();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.zoomOut();
        }
    }
    
    public void home() {
        if (this.panel != null) {
            this.panel.home();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.home();
        }
    }
    
    public void setPrimary(final int n) {
        if (this.panel != null) {
            this.panel.setPrimary(n);
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.setPrimary(n);
        }
    }
    
    public void setPrimary() {
        if (this.panel != null) {
            this.panel.setPrimary();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.setPrimary();
        }
    }
    
    public void back() {
        if (this.panel != null) {
            this.panel.back();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.back();
        }
    }
    
    public void forward() {
        if (this.panel != null) {
            this.panel.forward();
            return;
        }
        if (Geneo.frame != null) {
            Geneo.frame.forward();
        }
    }
    
    public void showWindow() {
        if (Geneo.frame != null) {
            Geneo.frame.setVisible(true);
        }
    }
    
    private void internalBegin(final String s, final int n, final int n2, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final boolean b) {
        try {
            this.globals = new Globals(this, s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14);
            if (b) {
                this.setForeground(this.globals.foregroundColor);
                this.setBackground(this.globals.backgroundColor);
                this.innerRect = this.getBounds();
                final Rectangle innerRect = this.innerRect;
                innerRect.x += this.borderWidth;
                final Rectangle innerRect2 = this.innerRect;
                innerRect2.y += this.borderWidth;
                final Rectangle innerRect3 = this.innerRect;
                innerRect3.width -= 2 * this.borderWidth;
                final Rectangle innerRect4 = this.innerRect;
                innerRect4.height -= 2 * this.borderWidth;
                this.panel = new MainPanel(this, this.globals);
                this.setLayout(null);
                this.add(this.panel);
                this.panel.setBounds(this.innerRect.x, this.innerRect.y, this.innerRect.width, this.innerRect.height);
                if (this.globals.statusCode != 0) {
                    this.panel.notifyDone();
                }
            }
            else {
                Geneo.frame = new MainFrame(this, this.globals, n, n2);
                this.globals.appletFrameParent = Geneo.frame;
                if (this.globals.statusCode != 0) {
                    Geneo.frame.notifyDone();
                }
                ++Geneo.instanceCount;
            }
        }
        catch (NullPointerException ex) {
            this.globals.statusCode = 1;
            this.globals.statusDesc = "geneo: NullPointerException: " + ex.getMessage();
            if (this.panel != null) {
                this.panel.notifyDone();
                return;
            }
            if (Geneo.frame != null) {
                Geneo.frame.notifyDone();
            }
        }
        catch (NumberFormatException ex2) {
            this.globals.statusCode = 1;
            this.globals.statusDesc = "geneo: NullFormatException: " + ex2.getMessage();
            if (this.panel != null) {
                this.panel.notifyDone();
            }
            else if (Geneo.frame != null) {
                Geneo.frame.notifyDone();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.embedInPage) {
            final Rectangle bounds = this.getBounds();
            for (int i = 0; i < this.borderWidth; ++i) {
                graphics.drawLine(bounds.x + i, bounds.y + i, bounds.x + i, bounds.height - i - 1);
                graphics.drawLine(bounds.x + i, bounds.height - i - 1, bounds.width - i - 1, bounds.height - i - 1);
                graphics.drawLine(bounds.width - i - 1, bounds.height - i - 1, bounds.width - i - 1, bounds.y + i);
                graphics.drawLine(bounds.width - i - 1, bounds.y + i, bounds.x + i, bounds.y + i);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void PrintAll(final Graphics graphics) {
        System.out.println("We should be printing now");
    }
    
    public synchronized void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        if (this.embedInPage && this.panel != null) {
            this.panel.setBounds(n + this.borderWidth, n2 + this.borderWidth, n3 - 2 * this.borderWidth, n4 - 2 * this.borderWidth);
        }
    }
    
    public String getAppletInfo() {
        return "Geneo Version 2.0\nWritten by Don Baldwin\nNov. 1997";
    }
    
    public String[][] getParameterInfo() {
        return Globals.paramInfo;
    }
    
    public Geneo() {
        this.embedInPage = false;
    }
    
    static {
        Geneo.frame = null;
    }
}
