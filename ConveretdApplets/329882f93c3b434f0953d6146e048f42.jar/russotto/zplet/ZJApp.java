// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet;

import java.io.File;
import java.awt.FileDialog;
import russotto.zplet.zmachine.zmachine5.ZMachine8;
import russotto.zplet.zmachine.zmachine5.ZMachine5;
import russotto.zplet.zmachine.zmachine3.ZMachine3;
import java.net.MalformedURLException;
import java.io.IOException;
import java.awt.Label;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.screenmodel.ZStatus;
import russotto.zplet.screenmodel.ZScreen;
import java.awt.Frame;

public class ZJApp extends Frame
{
    ZScreen screen;
    ZStatus status_line;
    ZMachine zm;
    static String pstatusfg;
    static String pstatusbg;
    static String pmainfg;
    static String pmainbg;
    static String pzcodefile;
    String statusfg;
    String statusbg;
    String mainfg;
    String mainbg;
    String zcodefile;
    boolean failed;
    
    public static synchronized void main(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].charAt(0) == '-') {
                switch (array[i].charAt(1)) {
                    case 'f': {
                        ZJApp.pmainfg = (ZJApp.pstatusbg = array[++i]);
                        break;
                    }
                    case 'b': {
                        ZJApp.pmainbg = (ZJApp.pstatusfg = array[++i]);
                        break;
                    }
                }
            }
            else {
                ZJApp.pzcodefile = array[i];
            }
        }
        final ZJApp zjApp = new ZJApp(ZJApp.pzcodefile, ZJApp.pstatusfg, ZJApp.pstatusbg, ZJApp.pmainfg, ZJApp.pmainbg);
        System.err.println("Location = " + zjApp.bounds().x + " " + zjApp.bounds().y);
        System.err.println("Parent = " + zjApp.getParent());
        zjApp.setTitle("ZJApp");
        zjApp.pack();
        zjApp.show();
        zjApp.start();
    }
    
    ZJApp() {
        this(null, null, null, null, null);
    }
    
    ZJApp(final String zcodefile, final String statusfg, final String statusbg, final String mainfg, final String mainbg) {
        this.zcodefile = null;
        this.failed = false;
        this.mainfg = mainfg;
        this.mainbg = mainbg;
        this.statusfg = statusfg;
        this.statusbg = statusbg;
        this.zcodefile = zcodefile;
        this.setLayout(new BorderLayout());
        this.screen = new ZScreen();
        (this.status_line = new ZStatus()).setForeground(Color.black);
        this.status_line.setBackground(Color.white);
        this.screen.setZForeground(9);
        this.screen.setZBackground(2);
        if (this.statusfg != null) {
            this.status_line.setForeground(ZColor.getcolor(this.statusfg));
        }
        if (this.statusbg != null) {
            this.status_line.setBackground(ZColor.getcolor(this.statusbg));
        }
        if (this.mainfg != null) {
            this.screen.setZForeground(ZColor.getcolornumber(this.mainfg));
        }
        if (this.mainbg != null) {
            this.screen.setZBackground(ZColor.getcolornumber(this.mainbg));
        }
        this.add("North", this.status_line);
        this.add("Center", this.screen);
    }
    
    void startzm() {
        byte[] array = null;
        try {
            System.err.println(this.zcodefile);
            array = this.suckstream(new URL(this.zcodefile).openStream());
        }
        catch (MalformedURLException ex) {
            try {
                array = this.suckstream(new FileInputStream(this.zcodefile));
            }
            catch (IOException ex2) {
                this.add("North", new Label("Malformed URL"));
                this.failed = true;
            }
        }
        catch (IOException ex3) {
            this.add("North", new Label("I/O Error"));
        }
        if (array != null) {
            switch (array[0]) {
                case 3: {
                    this.zm = new ZMachine3(this.screen, this.status_line, array);
                    break;
                }
                case 5: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine5(this.screen, array);
                    break;
                }
                case 8: {
                    this.remove(this.status_line);
                    this.zm = new ZMachine8(this.screen, array);
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
        boolean b = false;
        if (array != null) {
            while (!b) {
                try {
                    this.zm.join();
                    b = true;
                }
                catch (InterruptedException ex4) {}
            }
        }
        System.exit(0);
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
        if (this.zcodefile == null) {
            final FileDialog fileDialog = new FileDialog(this, "Select game file", 0);
            fileDialog.show();
            this.zcodefile = new File(fileDialog.getDirectory(), fileDialog.getFile()).getAbsolutePath();
        }
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
    
    static {
        ZJApp.pzcodefile = null;
    }
}
