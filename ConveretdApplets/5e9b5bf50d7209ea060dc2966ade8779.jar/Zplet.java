import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Label;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Zplet extends Applet
{
    ZScreen screen;
    ZStatus status_line;
    ZMachine zm;
    boolean failed;
    
    public void init() {
        final String statusfg = this.getParameter("StatusForeground");
        final String statusbg = this.getParameter("StatusBackground");
        final String mainfg = this.getParameter("Foreground");
        final String mainbg = this.getParameter("Background");
        this.setLayout(new BorderLayout());
        this.screen = new ZScreen();
        (this.status_line = new ZStatus()).setForeground(Color.black);
        this.status_line.setBackground(Color.white);
        this.screen.setZForeground(9);
        this.screen.setZBackground(2);
        if (statusfg != null) {
            this.status_line.setForeground(ZColor.getcolor(statusfg));
        }
        if (statusbg != null) {
            this.status_line.setBackground(ZColor.getcolor(statusbg));
        }
        if (mainfg != null) {
            this.screen.setZForeground(ZColor.getcolornumber(mainfg));
        }
        if (mainbg != null) {
            this.screen.setZBackground(ZColor.getcolornumber(mainbg));
        }
        this.add("North", this.status_line);
        this.add("Center", this.screen);
    }
    
    void startzm() {
        byte[] zmemimage = null;
        try {
            final String zcodefile = this.getParameter("StoryFile");
            final URL myzfile = new URL(this.getCodeBase(), zcodefile);
            final InputStream myzstream = myzfile.openStream();
            zmemimage = this.suckstream(myzstream);
        }
        catch (MalformedURLException ex) {
            this.add("North", new Label("Malformed URL"));
            this.failed = true;
        }
        catch (IOException ex2) {
            this.add("North", new Label("I/O Error"));
        }
        if (zmemimage != null) {
            switch (zmemimage[0]) {
                case 3: {
                    this.zm = new ZMachine3(this.screen, this.status_line, zmemimage);
                    break;
                }
                case 5: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine5(this.screen, zmemimage);
                    break;
                }
                case 8: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine8(this.screen, zmemimage);
                    break;
                }
                default: {
                    this.add("North", new Label("Not a valid V3,V5, or V8 story file"));
                    break;
                }
            }
            if (this.zm != null) {
                this.zm.start();
            }
        }
    }
    
    byte[] suckstream(final InputStream mystream) throws IOException {
        int currentbytes = 0;
        final int buffersize = 2048;
        byte[] buffer = new byte[buffersize];
        for (int bytesleft = buffersize, got = 0; got != -1; got = mystream.read(buffer, currentbytes, bytesleft)) {
            bytesleft -= got;
            currentbytes += got;
            if (bytesleft == 0) {
                byte[] oldbuffer = buffer;
                buffer = new byte[buffersize + currentbytes];
                System.arraycopy(oldbuffer, 0, buffer, 0, currentbytes);
                oldbuffer = null;
                bytesleft = buffersize;
            }
        }
        if (buffer.length != currentbytes) {
            final byte[] oldbuffer = buffer;
            buffer = new byte[currentbytes];
            System.arraycopy(oldbuffer, 0, buffer, 0, currentbytes);
        }
        return buffer;
    }
    
    public void start() {
        if (!this.failed && (this.zm == null || !this.zm.isAlive())) {
            this.startzm();
        }
    }
    
    public void destroy() {
        this.zm.stop();
        this.zm = null;
        this.remove(this.screen);
        this.screen = null;
    }
    
    public Zplet() {
        this.failed = false;
    }
}
