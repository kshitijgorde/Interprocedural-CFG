// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Window;
import jlog.awt.$I8.$J8;
import java.awt.TextArea;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletStub;
import java.applet.AppletContext;
import jlog.applet.$GMC;
import java.util.Dictionary;
import jlog.io.$P4;
import jlog.util.$UD.$XD;
import java.applet.Applet;
import java.io.File;
import jlog.util.$UD.$FE;
import java.io.IOException;
import java.awt.Image;
import java.util.Properties;
import java.awt.Component;
import jlog.util.$MC.$NC;
import jlog.util.$MC.$QC;
import java.awt.Label;
import java.awt.Frame;
import jlog.applet.$KEC;

public class $PDC extends Thread implements $KEC, $CMC
{
    Frame $KB;
    String[] $IB;
    static Label $TDC;
    
    void $O6() throws IOException {
        final $QC $qc = new $QC();
        final Properties $qdc = $QDC(this.$IB, $qc, "jlog/jim/Viewer.properties");
        this.showStatus("loading icon");
        final Image $pc = $qc.$PC("jlog/jim/images/jim_icon.jpg");
        if ($pc != null) {
            this.$KB.setIconImage($pc);
        }
        this.showStatus("loading classes");
        final $H0B $h0B = new $H0B();
        this.$RDC($h0B, $qc, $qdc);
        this.showStatus("embedding application");
        this.$KB.add("Center", $h0B);
        this.$KB.validate();
        this.$KB.repaint();
        this.showStatus("init application");
        $h0B.init();
        this.showStatus("start application");
        $h0B.start();
    }
    
    static Properties $QDC(String[] $ge, final $NC $nc, final String s) throws IOException {
        final $FE $fe = new $FE();
        $fe.$ZD(s, ".java" + File.separator + s);
        $ge = $fe.$GE($ge);
        return $fe;
    }
    
    Applet $RDC(final Component component, final $NC $pd, final Properties properties) throws MalformedURLException {
        final $H0B $h0B = ($H0B)component;
        $h0B.$PD = $pd;
        $h0B.$UF = ($XD)properties;
        $h0B.$A2 = true;
        final String property = System.getProperty("user.dir");
        final URL $u4 = $P4.$U4(String.valueOf(property) + '/');
        final URL $u5 = $P4.$U4(String.valueOf(property) + '/' + "index.htm");
        final $GEC showDocument = new $GEC(properties, this);
        final $GMC stub = new $GMC(this.$KB, showDocument, $u4, $u5, properties);
        $h0B.showDocument = showDocument;
        $h0B.setStub(stub);
        return $h0B;
    }
    
    static Frame $SDC(final String s, final boolean b) {
        final Frame frame = new Frame(s);
        if (b) {
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    windowEvent.getWindow().dispose();
                    System.exit(0);
                }
            });
        }
        try {
            frame.setBackground(SystemColor.control);
            frame.setForeground(SystemColor.controlText);
        }
        catch (Exception ex) {}
        return frame;
    }
    
    static void $UDC(final Exception ex) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(byteArrayOutputStream));
            final TextArea textArea = new TextArea(String.valueOf(byteArrayOutputStream.toString()) + "\n\nclose window to exit application", 20, 60);
            textArea.setEditable(false);
            final Frame $sdc = $SDC(String.valueOf(ex.getMessage()), true);
            $sdc.add("Center", textArea);
            $sdc.pack();
            $J8.$K8($sdc);
            $sdc.show();
        }
        catch (Exception ex2) {
            ex.printStackTrace();
        }
    }
    
    static {
        $PDC.$TDC = null;
    }
    
    $PDC(final Frame $kb, final String[] $ib) {
        this.$KB = null;
        this.$IB = null;
        this.$KB = $kb;
        this.$IB = $ib;
    }
    
    public static void main(final String[] array) {
        try {
            final Frame $sdc = $SDC("BlinkMap", true);
            $sdc.setSize(500, 200);
            $sdc.add("South", $PDC.$TDC = new Label("loading.."));
            new $PDC($sdc, array).start();
            $J8.$K8($sdc);
            $sdc.setVisible(true);
        }
        catch (Exception ex) {
            $UDC(ex);
        }
    }
    
    public void run() {
        try {
            this.$O6();
        }
        catch (Exception ex) {
            $UDC(ex);
        }
    }
    
    public void showStatus(final String text) {
        $PDC.$TDC.setText(text);
    }
}
