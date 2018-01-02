import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvCaptureButton extends WvImageButton
{
    Applet applet;
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = true;
        this.repaint();
        final StringBuffer sb = new StringBuffer();
        sb.append(super.wvDispatcher.getUrlBaseStr() + "GetOneShot".trim() + "?");
        int n3 = 0;
        if (super.wvDispatcher.getVcHostStr() != null) {
            if (n3 != 0) {
                sb.append("&");
            }
            sb.append("vc_host=" + super.wvDispatcher.getVcHostStr());
            n3 = 1;
        }
        if (super.wvDispatcher.getVcPort() > 0) {
            if (n3 != 0) {
                sb.append("&");
            }
            sb.append("vc_port=" + super.wvDispatcher.getVcPort());
            n3 = 1;
        }
        final String s = (String)super.wvDispatcher.getObject("image_size");
        if (s != null) {
            if (n3 != 0) {
                sb.append("&");
            }
            sb.append(s);
            n3 = 1;
        }
        if (n3 != 0) {
            sb.append("&");
        }
        sb.append("REQUEST_ID=" + System.currentTimeMillis());
        try {
            this.applet.getAppletContext().showDocument(new URL(new String(sb)), "LiveApplet Capture");
        }
        catch (MalformedURLException ex) {
            WvDebug.println(this + " " + ex);
        }
        return true;
    }
    
    public void videoConnected(final boolean b) {
        if (b) {
            this.enable();
        }
    }
    
    public WvCaptureButton(final WvDispatcher wvDispatcher, final Rectangle[] array, final Applet applet) {
        super(wvDispatcher, array, true);
        this.applet = applet;
        this.disable();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = false;
        this.repaint();
        return true;
    }
}
