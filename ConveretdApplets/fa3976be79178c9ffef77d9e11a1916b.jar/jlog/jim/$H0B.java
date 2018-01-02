// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.awt.Toolkit;
import jlog.io.$P4;
import jlog.awt.$W5;
import java.awt.Canvas;
import java.awt.MenuContainer;
import java.awt.Cursor;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jlog.awt.$ZSB.$FTB;
import java.io.InputStream;
import jlog.$BI.$M4;
import java.net.MalformedURLException;
import jlog.awt.image.$OKB;
import jlog.util.$UD.$VD;
import jlog.awt.$I8.$J8;
import java.util.ResourceBundle;
import java.util.Locale;
import jlog.awt.image.$A6;
import java.io.IOException;
import java.net.URL;
import jlog.awt.$UHB.$HYB;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextArea;
import jlog.io.URLSecurityChecker;
import jlog.applet.$HEC;
import jlog.util.$UD.$XD;
import jlog.util.$MC.$NC;
import jlog.awt.image.$ZKB;
import jlog.awt.$X2B.$U3B;
import jlog.awt.text.$XOB;
import jlog.awt.text.$AOB;
import java.awt.Window;
import jlog.util.$MB;
import java.awt.Container;
import java.awt.Button;
import jlog.awt.text.$HPB;
import jlog.awt.$ZSB.$BTB;
import java.awt.Component;
import jlog.awt.$ZSB.$UTB;
import jlog.$BI.$Z2C;
import jlog.awt.image.$MKB;
import jlog.awt.image.$Q0;
import jlog.$H4;
import java.beans.PropertyChangeListener;
import jlog.applet.$IQC;
import java.applet.Applet;

public class $H0B extends Applet implements $IQC, $X5B, PropertyChangeListener, $H4, $GBC, $Q0, $MKB, $Z2C, $DCC, $CMC, $UTB
{
    private static final boolean debug = false;
    public Object $A3C;
    public static final String $O0B = "please wait a moment - loading background";
    public static final String $P0B = "images ready";
    public static final String $Q0B = "Error: image could not be loaded.";
    public static final String $R0B = "Error: {0} images could not be loaded.";
    public static final String $I0B = "Please wait a moment";
    public static final String $L0B = "Initializing application..";
    public static final String $M0B = "Preparing user interface..";
    public static final String $N0B = "Preparing map..";
    boolean $A2;
    Component controls;
    Component $ISC;
    Component $VMC;
    String $G_C;
    $BTB $CTB;
    $HPB $F1C;
    Button $I1C;
    Button $H1C;
    public Container $UFC;
    public static final String $EGC = "CARD_APPLICATION";
    $J0B $D1C;
    $BBC $CBC;
    $NKC $RFC;
    $LMC $K_C;
    $JHC $V_C;
    $MB $TEC;
    Window $B3C;
    $AOB $S_C;
    $XOB $T_C;
    String $L1C;
    $AOB $X_C;
    Applet $L_B;
    String $IYC;
    $U3B $F5B;
    $ZKB $Z_C;
    static final int $B1C = 1;
    static final int $HYC = 2;
    int $LL;
    private static int $C3C;
    Thread $LYC;
    boolean $D3C;
    Object $E3C;
    $NC $PD;
    $XD $UF;
    $HEC showDocument;
    URLSecurityChecker $A_C;
    $Q0 $XKB;
    TextArea ta;
    boolean $O3C;
    Object $P3C;
    
    public void $BI(final Object o) {
        this.showStatus((o instanceof Throwable) ? ((Throwable)o).getMessage() : String.valueOf(String.valueOf(o)));
    }
    
    public void $BI(final Object o, final Object o2) {
        this.showStatus(String.valueOf(String.valueOf(o)) + " " + o2);
    }
    
    public synchronized void $CIC(final $BBC $cbc) {
        if (this.$CBC == $cbc) {
            return;
        }
        if (this.$CBC != null) {
            this.$CBC.removePropertyChangeListener(this);
            this.$CBC.$QKC.$AMB(this);
            this.$CBC.$PPC.$AMB(this);
            this.$CBC.$XPC(null);
        }
        this.$CBC = $cbc;
        this.$RFC.$CIC(this.$CBC, this);
        this.$K_C.$CIC(this.$CBC);
        if (this.$CBC != null) {
            final Frame frame = this.getFrame();
            if (frame != null) {
                frame.setTitle(this.$CBC.getName());
            }
            this.$CBC.addPropertyChangeListener(this, false);
            Image image = this.$CBC.$PPC.getImage();
            if (image == null) {
                image = this.$CBC.$QKC.getImage();
            }
            this.$Z_C.setImage(image);
            this.$CBC.$QKC.$FLB(this);
            this.$CBC.$PPC.$FLB(this);
            final float $npc = this.$CBC.$NPC;
            if ($npc > 0.0f) {
                this.$RFC.$T_B($npc, $npc);
                this.$V_C.$WHC();
            }
        }
        else {
            this.$Z_C.setImage(null);
        }
    }
    
    public void $DHC() {
        synchronized (this.$CTB) {
            this.$CTB.$OTB("CARD_APPLICATION");
            if (this.$L_B != null) {
                this.$L_B.start();
            }
            Container container = this.getParent();
            if (container != null) {
                while (container.getParent() != null) {
                    container = container.getParent();
                }
                container.validate();
                container.repaint();
                this.$FZC();
            }
            this.$ZGC(false);
        }
        // monitorexit(this.$CTB)
    }
    
    public void $EHC(String string) {
        final $BTB $ctb = this.$CTB;
        if ($ctb == null) {
            this.showStatus(string);
            return;
        }
        synchronized ($ctb) {
            if (!$V8(string)) {
                this.showStatus("");
                $ctb.$MTB(false);
                $ctb.$JTB("");
            }
            else {
                string = String.valueOf(this.$N3C()) + "\n \n" + string;
                this.showStatus(string.replace('\n', ' '));
                $ctb.$JTB(string);
                $ctb.$MTB(true);
            }
        }
    }
    
    void $FZC() {
        final $NKC $rfc = this.$RFC;
        if ($rfc != null) {
            final Container $p4B = $rfc.$P4B();
            if ($p4B != null) {
                (($HYB)$p4B).repaintAll();
            }
        }
    }
    
    void $H3C(final long n) {
        try {
            synchronized (this.$E3C) {
                if (!this.$JYC(200L)) {
                    this.$LYC.interrupt();
                    if (!this.$JYC(n)) {
                        System.err.print("\ninit_thread did not shut down within 30 seconds.");
                        this.$LYC.stop();
                    }
                }
            }
            // monitorexit(this.$E3C)
        }
        catch (Exception ex) {
            System.err.print("\nwait init_thread interrupted: " + ex);
        }
        finally {
            this.$Y$C(this.$LYC);
        }
    }
    
    public $BBC $HFC(final URL url) throws IOException, $A6, $U5B {
        this.$EHC(this.$TEC.getString("LOAD_MAP"));
        try {
            return this.$D1C.$UJC(url, this);
        }
        finally {
            if (this.isShowing()) {
                this.$EHC(String.valueOf(this.$TEC.getString("LOAD_MAP")) + " - OK");
            }
        }
    }
    
    private void $I3C() {
        this.$CBC = null;
        this.$D1C.destroy();
        this.$D1C = null;
    }
    
    public $BTB $INC() {
        return this.$CTB;
    }
    
    void $J3C() {
        if (this.$CTB != null) {
            this.$CTB.$DTB(false);
            this.$CTB.getWindow().dispose();
            this.$CTB = null;
        }
    }
    
    public boolean $JYC(final long n) throws InterruptedException {
        synchronized (this.$E3C) {
            if (!this.$D3C && n != 0L) {
                this.$E3C.wait(n);
            }
        }
        // monitorexit(this.$E3C)
        return this.$D3C;
    }
    
    public void $K3C() {
        this.$EHC(String.valueOf(this.$N3C()) + " stopped");
        this.$CTB.$DTB(false);
    }
    
    void $KYC() {
        (this.$LYC = new $ZEC(this)).start();
    }
    
    public void $LUC(final Locale locale) throws Exception {
        this.showStatus("loading resources " + locale.getLanguage());
        final ResourceBundle $sb = $MB.$SB(this.$IYC, locale);
        try {
            this.setLocale(locale);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.$TEC.$QB($sb);
        if ($sb != null) {
            this.$UF.setProperty("LANGUAGE", locale.getLanguage());
        }
        this.showStatus("");
    }
    
    public final void $MTC() throws IOException, $VD {
        if (this.$B3C == null) {
            try {
                this.$REC();
            }
            catch (Exception ex) {}
            this.$B3C = new $T0B(this.getFrame(), this.$UF, this.$PD, this.showDocument);
        }
        if (this.$B3C.isVisible()) {
            this.$B3C.setVisible(false);
        }
        else {
            this.$B3C.pack();
            $J8.$K8(this.$B3C);
            this.$B3C.setVisible(true);
            this.$B3C.validate();
            this.$B3C.repaint();
            this.$B3C.toFront();
            this.$B3C.requestFocus();
        }
    }
    
    public String $N3C() {
        return this.$UF.getProperty("PROJECT_NAME", this.$UF.getProperty("APPLICATION_NAME", ""));
    }
    
    public void $NKB(final $OKB $okb) {
        final $BBC $cbc = this.$CBC;
        if ($cbc == null) {
            return;
        }
        this.$Z_C.invalidate();
        if ($okb.getSource() == $cbc.$PPC) {
            final Image image = $okb.getImage();
            if (image != null) {
                this.$Z_C.setImage(image);
                return;
            }
        }
        if ($cbc.$PPC.getImage() == null) {
            this.$Z_C.setImage($cbc.$QKC.getImage());
        }
    }
    
    void $R3C() throws MalformedURLException {
        this.$CIC(this.$S3C());
    }
    
    ResourceBundle $RB() {
        return this.$TEC.$RB();
    }
    
    public Image $REC() throws $A6, $VD {
        return this.createImage(this.$UF.getProperty("PROJECT_ICON", this.$UF.getProperty("APPLICATION_ICON")));
    }
    
    public $BBC $S3C() throws MalformedURLException {
        return new $BBC(new URL(this.getDocumentBase(), "BlinkMap_noname" + $H0B.$C3C++), this, this.$XKB);
    }
    
    public void $TUC() {
        try {
            URL url = new URL(this.getCodeBase(), "manual_" + this.getLocale().getLanguage() + ".html");
            try {
                final InputStream openStream = url.openStream();
                openStream.read();
                openStream.close();
            }
            catch (Exception ex2) {
                url = new URL(this.getCodeBase(), "manual.html");
            }
            this.showDocument(url, "MANUAL");
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    public void $U3C(final boolean b) {
    }
    
    static final boolean $V8(final String s) {
        return s != null && !s.equals("");
    }
    
    public void $VTB(final $FTB $ftb, final String s) {
        this.showStatus("BlinkMap");
    }
    
    void $WMC(final $MJC $mjc) {
    }
    
    synchronized void $X$C(final Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath)t;
        }
        try {
            if (this.ta == null) {
                (this.ta = new TextArea()).append(this.getAppletInfo());
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add("Center", this.ta);
                this.validate();
            }
            this.ta.append("\n\nERROR: \t(" + new Date().toString() + ")\n\n");
            final StringWriter stringWriter;
            t.printStackTrace(new PrintWriter(stringWriter = new StringWriter()));
            this.ta.append(stringWriter.toString());
            this.ta.append("\n\n" + $M4.$M3C());
            this.ta.append("\n\tCodeBase:" + this.getCodeBase());
            this.ta.append("\n\tDocumentBase:" + this.getDocumentBase());
        }
        finally {
            $M4.print(t);
        }
    }
    
    synchronized void $Y$C(final Thread thread) {
        synchronized (this.$E3C) {
            if (thread == this.$LYC) {
                this.$D3C = true;
                this.$LYC = null;
                this.$E3C.notifyAll();
            }
        }
        // monitorexit(this.$E3C)
    }
    
    void $YJC(final String text) {
        if (this.$S_C == null) {
            this.showStatus(text);
            return;
        }
        this.$S_C.setText(text);
        this.$S_C.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    void $YJC(final String s, final String $l1C) {
        if (this.$S_C == null) {
            return;
        }
        this.$L1C = $l1C;
        this.$YJC(s);
    }
    
    public void $ZGC(final boolean $o3C) {
        synchronized (this.$P3C) {
            if ($o3C == this.$O3C) {
                // monitorexit(this.$P3C)
                return;
            }
            this.$O3C = $o3C;
            MenuContainer frame = this.getFrame();
            if (frame == null) {
                frame = this;
            }
            if ($o3C) {
                ((Component)frame).setCursor(Cursor.getPredefinedCursor(3));
            }
            else {
                ((Component)frame).setCursor(Cursor.getPredefinedCursor(0));
            }
        }
        // monitorexit(this.$P3C)
    }
    
    static {
        $H0B.$C3C = 1;
    }
    
    public $H0B() {
        this.$A3C = new Object();
        this.$A2 = false;
        this.controls = new Canvas();
        this.$ISC = new Canvas();
        this.$VMC = new Canvas();
        this.$G_C = "";
        this.$CTB = null;
        this.$UFC = null;
        this.$CBC = null;
        this.$TEC = null;
        this.$B3C = null;
        this.$L1C = null;
        this.$L_B = null;
        this.$IYC = "jlog.jim.Resources";
        this.$LYC = null;
        this.$D3C = false;
        this.$E3C = new Object();
        this.$PD = null;
        this.$UF = null;
        this.showDocument = null;
        this.ta = null;
        this.$O3C = false;
        this.$P3C = new Object();
        $M4.$F3C(this);
        this.$LL = 1;
        this.$TEC = new $MB();
        this.$XKB = this;
        if ($W5.$Y0() == null) {
            $W5.$X0(this);
        }
    }
    
    public Image createImage(final String s) throws $A6 {
        return this.$PD.$PC(s);
    }
    
    public Image createImage(final URL url) throws $A6 {
        Image image;
        try {
            image = this.$PD.$PC($P4.$T$C(this.getCodeBase(), url));
            if (image == null) {
                if (this.$A_C != null) {
                    this.$A_C.$T3C(url);
                }
                image = Toolkit.getDefaultToolkit().getImage(url);
            }
        }
        catch (IOException ex) {
            throw new $A6(ex.getMessage());
        }
        return image;
    }
    
    public Image createImage(final URL url, final String s) throws $A6 {
        try {
            return this.createImage(new URL(url, s));
        }
        catch (MalformedURLException ex) {
            throw new $A6("illegal url " + url + " " + s);
        }
    }
    
    public void destroy() {
        synchronized (this.$A3C) {
            this.$H3C(20000L);
            this.$I3C();
            if (this.$B3C != null) {
                this.$B3C.dispose();
                this.$B3C = null;
            }
            this.$J3C();
            if (this.$L_B != null) {
                this.$L_B.destroy();
                this.$L_B = null;
            }
        }
        // monitorexit(this.$A3C)
    }
    
    public Frame getFrame() {
        if (!this.$A2) {
            return null;
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        return (Frame)parent;
    }
    
    public Image getImage(final URL url) {
        if (this.$A2) {
            throw new NoSuchMethodError("use createImage()");
        }
        return super.getImage(url);
    }
    
    public Image getImage(final URL url, final String s) {
        if (this.$A2) {
            throw new NoSuchMethodError("use createImage()");
        }
        return super.getImage(url, s);
    }
    
    public InputStream getInputStream(final URL url) throws IOException {
        final String $t$C = $P4.$T$C(this.getCodeBase(), url);
        final InputStream resourceAsStream = this.$PD.getResourceAsStream($t$C);
        if (resourceAsStream == null) {
            throw new FileNotFoundException($t$C);
        }
        return resourceAsStream;
    }
    
    public void init() {
        try {
            synchronized (this.$A3C) {
                this.$KYC();
            }
            // monitorexit(this.$A3C)
        }
        catch (Throwable t) {
            this.$X$C(t);
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyChangeEvent.getSource() != this.$CBC) {
            return;
        }
        if (propertyName.equals("name")) {
            final Frame frame = this.getFrame();
            if (frame != null) {
                frame.setTitle((String)newValue);
            }
        }
        else if (propertyName.equals("PROP_SIZE")) {
            final Dimension dimension = (Dimension)newValue;
            if (dimension != null) {
                this.$F5B.$D4B(dimension.width, dimension.height);
            }
        }
    }
    
    public void showDocument(final URL url, final String s) {
        final $HEC showDocument = this.showDocument;
        if (showDocument != null) {
            showDocument.showDocument(url, s);
        }
    }
    
    public void start() {
        try {
            synchronized (this.$E3C) {
                if (this.$JYC(500L)) {
                    this.$DHC();
                    $RKC.$CLC.start();
                    if (this.$L_B != null) {
                        this.$L_B.start();
                    }
                }
            }
            // monitorexit(this.$E3C)
        }
        catch (Throwable t) {
            this.$X$C(t);
        }
    }
    
    public void stop() {
        if (this.$B3C != null) {
            this.$B3C.setVisible(false);
        }
        $RKC.$CLC.stop();
        if (this.$L_B != null) {
            this.$L_B.stop();
        }
        this.$K3C();
    }
}
