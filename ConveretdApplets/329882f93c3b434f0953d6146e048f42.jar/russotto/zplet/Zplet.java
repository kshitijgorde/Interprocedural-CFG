// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet;

import java.io.InputStream;
import russotto.zplet.zmachine.zmachine5.ZMachine8;
import russotto.zplet.zmachine.zmachine5.ZMachine5;
import russotto.zplet.zmachine.zmachine3.ZMachine3;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Label;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.screenmodel.ZStatus;
import russotto.zplet.screenmodel.ZScreen;
import java.applet.Applet;

public class Zplet extends Applet
{
    ZScreen screen;
    ZStatus status_line;
    ZMachine zm;
    boolean failed;
    
    public Zplet() {
        this.failed = false;
    }
    
    public void init() {
        final String parameter = this.getParameter("StatusForeground");
        final String parameter2 = this.getParameter("StatusBackground");
        final String parameter3 = this.getParameter("Foreground");
        final String parameter4 = this.getParameter("Background");
        this.setLayout(new BorderLayout());
        this.screen = new ZScreen();
        (this.status_line = new ZStatus()).setForeground(Color.black);
        this.status_line.setBackground(Color.white);
        this.screen.setZForeground(9);
        this.screen.setZBackground(2);
        if (parameter != null) {
            this.status_line.setForeground(ZColor.getcolor(parameter));
        }
        if (parameter2 != null) {
            this.status_line.setBackground(ZColor.getcolor(parameter2));
        }
        if (parameter3 != null) {
            this.screen.setZForeground(ZColor.getcolornumber(parameter3));
        }
        if (parameter4 != null) {
            this.screen.setZBackground(ZColor.getcolornumber(parameter4));
        }
        this.add("North", this.status_line);
        this.add("Center", this.screen);
    }
    
    void startzm() {
        byte[] suckstream = null;
        try {
            suckstream = this.suckstream(new URL(this.getDocumentBase(), this.getParameter("StoryFile")).openStream());
        }
        catch (MalformedURLException ex) {
            this.add("North", new Label("Malformed URL"));
            this.failed = true;
        }
        catch (IOException ex2) {
            this.add("North", new Label("I/O Error"));
        }
        if (suckstream != null) {
            switch (suckstream[0]) {
                case 3: {
                    this.zm = new ZMachine3(this.screen, this.status_line, suckstream);
                    break;
                }
                case 5: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine5(this.screen, suckstream);
                    break;
                }
                case 8: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine8(this.screen, suckstream);
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
    
    byte[] suckstream(final InputStream inputStream) throws IOException {
        int n = 0;
        final int n2 = 2048;
        byte[] array = new byte[n2];
        for (int n3 = n2, i = 0; i != -1; i = inputStream.read(array, n, n3)) {
            n3 -= i;
            n += i;
            if (n3 == 0) {
                final byte[] array2 = array;
                array = new byte[n2 + n];
                System.arraycopy(array2, 0, array, 0, n);
                n3 = n2;
            }
        }
        if (array.length != n) {
            final byte[] array3 = array;
            array = new byte[n];
            System.arraycopy(array3, 0, array, 0, n);
        }
        return array;
    }
    
    public void start() {
        if (!this.failed && (this.zm == null || !this.zm.isAlive())) {
            this.startzm();
        }
    }
    
    public void destroy() {
        if (this.zm != null) {
            this.zm.stop();
        }
        this.zm = null;
        this.remove(this.screen);
        this.screen = null;
    }
}
