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
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = true;
        this.repaint();
        final StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(String.valueOf(super.wvDispatcher.getUrlBaseStr()) + "GetOneShot".trim() + "?");
        boolean flag = false;
        final String s = super.wvDispatcher.getVcHostStr();
        if (s != null) {
            if (flag) {
                stringbuffer.append("&");
            }
            stringbuffer.append("vc_host=" + super.wvDispatcher.getVcHostStr());
            flag = true;
        }
        if (super.wvDispatcher.getVcPort() > 0) {
            if (flag) {
                stringbuffer.append("&");
            }
            stringbuffer.append("vc_port=" + super.wvDispatcher.getVcPort());
            flag = true;
        }
        final String s2 = (String)super.wvDispatcher.getObject("image_size");
        if (s2 != null) {
            if (flag) {
                stringbuffer.append("&");
            }
            stringbuffer.append(s2);
            flag = true;
        }
        if (flag) {
            stringbuffer.append("&");
        }
        stringbuffer.append("REQUEST_ID=" + System.currentTimeMillis());
        try {
            final URL url = new URL(new String(stringbuffer));
            this.applet.getAppletContext().showDocument(url, "LiveApplet Capture");
        }
        catch (MalformedURLException malformedurlexception) {
            WvDebug.println(this + " " + malformedurlexception);
        }
        return true;
    }
    
    public void videoConnected(final boolean flag) {
        if (flag) {
            this.enable();
        }
    }
    
    public WvCaptureButton(final WvDispatcher wvdispatcher, final Rectangle[] arectangle, final Applet applet1) {
        super(wvdispatcher, arectangle, true);
        this.applet = applet1;
        this.disable();
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = false;
        this.repaint();
        return true;
    }
}
