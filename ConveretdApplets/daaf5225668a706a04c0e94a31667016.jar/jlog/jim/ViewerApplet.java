// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.applet.AppletStub;
import java.net.URL;
import jlog.applet.$HEC;
import java.util.Dictionary;
import jlog.applet.$GMC;
import java.io.InputStream;
import jlog.util.$UD.$WD;
import jlog.util.$MC.$BD;
import java.awt.TextArea;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.Component;
import jlog.applet.$WQC;
import java.io.PrintStream;
import java.awt.Label;
import jlog.applet.$IQC;
import java.applet.Applet;

public class ViewerApplet extends Applet implements Runnable, $CMC
{
    $V5B $HQC;
    $IQC $JQC;
    boolean $KQC;
    boolean $LQC;
    Object $MQC;
    Applet $NQC;
    Label $TDC;
    Label $OQC;
    Thread $PQC;
    static final boolean DEBUG = false;
    PrintStream $SQC;
    
    void $O6() throws IOException {
        this.showStatus("loading classes..");
        final $H0B $nqc = new $H0B();
        this.$JQC = new $WQC($nqc, 5000);
        this.showStatus("setting interfaces..");
        this.$XQC($nqc);
        this.$YQC($nqc, "/jlog/jim/Viewer.properties");
        this.$ZQC($nqc);
        synchronized (this.$MQC) {
            if (this.$LQC) {
                this.showStatus("stateInitializing Viewer..");
                this.removeAll();
                this.add("Center", $nqc);
                this.$JQC.init();
                this.$NQC = $nqc;
                if (this.$KQC && this.$LQC) {
                    this.showStatus("start Viewer..");
                    this.$JQC.start();
                }
                this.validate();
            }
        }
        // monitorexit(this.$MQC)
    }
    
    void $QQC() {
        synchronized (this.$MQC) {
            this.showStatus("stateInitializing");
            this.setLayout(new BorderLayout());
            this.setBackground(Color.white);
            this.setForeground(Color.darkGray);
            this.$OQC.setText("BlinkMap");
            this.$OQC.setFont(new Font("Default", 1, 36));
            this.add("Center", this.$OQC);
            this.add("South", this.$TDC);
            this.$LQC = true;
            (this.$PQC = new Thread(this)).start();
        }
        // monitorexit(this.$MQC)
    }
    
    void $RQC(final Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath)t;
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            t.printStackTrace(new PrintStream(byteArrayOutputStream));
            final TextArea textArea = new TextArea(String.valueOf(byteArrayOutputStream.toString()) + "\n\nclose window to exit application", 20, 60);
            textArea.setEditable(false);
            if (this.$NQC != null) {
                this.remove(this.$NQC);
            }
            this.add("Center", textArea);
        }
        catch (Exception ex) {
            t.printStackTrace(System.err);
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
    }
    
    void $TQC() {
        synchronized (this.$MQC) {
            if (this.$KQC) {
                System.err.println("warning: Applet.destroy() without Applet.stop()");
                this.stop();
            }
            this.$LQC = false;
            if (this.$PQC != null) {
                try {
                    this.$MQC.wait(20000L);
                }
                catch (InterruptedException ex) {}
                if (this.$PQC != null) {
                    this.$PQC.stop();
                }
            }
            if (this.$NQC != null) {
                this.$JQC.destroy();
                this.$NQC = null;
            }
            this.removeAll();
        }
        // monitorexit(this.$MQC)
    }
    
    void $UQC() {
        synchronized (this.$MQC) {
            this.$KQC = true;
            if (this.$NQC != null) {
                this.$JQC.start();
            }
        }
        // monitorexit(this.$MQC)
    }
    
    void $VQC() {
        synchronized (this.$MQC) {
            this.$KQC = false;
            if (this.$NQC != null) {
                this.$JQC.stop();
            }
        }
        // monitorexit(this.$MQC)
    }
    
    void $XQC(final Object o) {
        (($H0B)o).$PD = new $BD(this);
    }
    
    void $YQC(final Object o, final String s) throws IOException {
        final $H0B $h0B = ($H0B)o;
        final InputStream resourceAsStream = $h0B.$PD.getResourceAsStream(s);
        try {
            ($h0B.$UF = new $WD(this)).load(resourceAsStream);
        }
        finally {
            resourceAsStream.close();
        }
    }
    
    void $ZQC(final Object o) {
        final $H0B $h0B = ($H0B)o;
        final $GMC stub = new $GMC($h0B, this.getAppletContext(), this.getCodeBase(), this.getDocumentBase(), null);
        $h0B.showDocument = new $HEC() {
            public void showDocument(final URL url) {
                this.showDocument(url, "_this");
            }
            
            public void showDocument(final URL url, final String s) {
                ViewerApplet.this.getAppletContext().showDocument(url, s);
            }
        };
        $h0B.setStub(stub);
    }
    
    public ViewerApplet() {
        this.$KQC = false;
        this.$LQC = false;
        this.$MQC = new Object();
        this.$NQC = null;
        this.$TDC = new Label();
        this.$OQC = new Label();
        this.$PQC = null;
        this.$SQC = null;
    }
    
    void debug(final Object o) {
        final PrintStream $sqc = this.$SQC;
    }
    
    public void destroy() {
        try {
            this.$TQC();
        }
        catch (Throwable t) {
            this.$RQC(t);
        }
    }
    
    public void init() {
        try {
            this.$QQC();
        }
        catch (Throwable t) {
            this.$RQC(t);
        }
    }
    
    public void run() {
        try {
            this.$O6();
        }
        catch (Throwable t) {
            this.$RQC(t);
        }
        finally {
            synchronized (this.$MQC) {
                this.$PQC = null;
                this.$LQC = false;
                this.$MQC.notifyAll();
            }
            // monitorexit(this.$MQC)
        }
    }
    
    public void showStatus(final String text) {
        if (this.$NQC == null) {
            this.$TDC.setText(text);
        }
        else {
            (($H0B)this.$NQC).$YJC(text);
        }
        super.showStatus(text);
    }
    
    public void start() {
        try {
            this.$UQC();
        }
        catch (Throwable t) {
            this.$RQC(t);
        }
    }
    
    public void stop() {
        try {
            this.$VQC();
        }
        catch (Throwable t) {
            this.$RQC(t);
        }
    }
}
