// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.awt.$GRB.$SUB;
import jlog.util.$VL.$WL;
import jlog.awt.image.$ZKB;
import jlog.awt.$X2B.$U3B;
import jlog.awt.$X2B.$G3B;
import jlog.awt.text.$XOB;
import jlog.awt.$I$B.$S$B;
import java.applet.Applet;
import jlog.awt.$ZSB.$UTB;
import jlog.io.URLSecurityChecker;
import jlog.awt.text.$AOB;
import jlog.awt.$B8.$GJB;
import java.awt.event.MouseListener;
import jlog.$T5.$D7.$XDC.$SRC;
import java.util.Locale;
import jlog.util.$UD.$VD;
import jlog.awt.$B4;
import jlog.awt.$X2B.$H3B;
import java.awt.Dimension;
import jlog.$BI.$M4;
import jlog.awt.image.$A6;
import java.io.IOException;
import java.io.InputStream;
import jlog.io.$P4;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.awt.event.WindowListener;
import jlog.awt.$ZSB.$ATB;
import java.awt.Window;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Component;
import jlog.awt.text.$HPB;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import jlog.awt.$UHB.$CRB;
import jlog.awt.$ZSB.$BTB;
import java.awt.Container;
import jlog.util.$UD.$XD;
import jlog.util.zip.$VF;
import jlog.$H4;

class $ZEC extends Thread implements $H4, $DCC, $GBC, $VF, $X5B
{
    $H0B $SHC;
    private static final boolean debug = false;
    $XD $UF;
    
    private void $A1C() {
        this.$SHC.$D1C = new $J0B();
    }
    
    $BTB $B_C(final Container container) {
        final $CRB $crb = new $CRB();
        Font $df = null;
        try {
            $df = new Font("default", 1, 24);
            $df = this.$UF.$DF("FLIPINFO_FONT", $df);
            $crb.setFont($df);
            $crb.setBackground(this.$UF.$BF("FLIPINFO_BACKGROUND", SystemColor.info));
            $crb.setForeground(this.$UF.$BF("FLIPINFO_FOREGROUND", SystemColor.infoText));
        }
        catch (Exception ex) {}
        final Panel panel = new Panel(new BorderLayout(12, 12));
        panel.add("Center", this.$SHC.$F1C = new $HPB("", 100));
        final Panel panel2 = new Panel(new GridLayout(0, 1, 8, 8));
        panel2.add(this.$SHC.$H1C = new Button());
        panel2.add(this.$SHC.$I1C = new Button());
        final Panel panel3 = new Panel();
        panel3.add(panel2);
        panel.add("South", panel3);
        this.$SHC.$I1C.addActionListener(new $J1C(this.$SHC));
        this.$SHC.$H1C.addActionListener(new $J1C(this.$SHC));
        final $BTB $btb = new $BTB(container, $crb, new Frame(), panel);
        $btb.$HUB().setFont($df);
        $btb.getWindow().addWindowListener(new $ATB($btb));
        return $btb;
    }
    
    private $BBC $C1C() throws IOException, $A6, $U5B {
        String s = this.$UF.getProperty("MAP_SRC");
        if (s == null) {
            final InputStream resourceAsStream = this.$SHC.$PD.getResourceAsStream("archive.properties");
            if (resourceAsStream == null) {
                throw new FileNotFoundException("archive.properties");
            }
            try {
                final Properties properties = new Properties();
                properties.load(resourceAsStream);
                s = properties.getProperty("ARCHIVE_STARTDOCUMENT");
            }
            finally {
                resourceAsStream.close();
            }
        }
        if (s != null) {
            return this.$SHC.$HFC($P4.$SXC(this.$SHC.getCodeBase(), s));
        }
        return null;
    }
    
    void $C_C(final $BTB $btb) {
        $btb.$F2("CARD_APPLICATION", this.$SHC.$UFC = new Panel(new BorderLayout()));
        try {
            $btb.$LTB(this.$SHC.$REC(), this.$UF.$BF("ICON_BACKGROUND", Color.white));
            final Frame frame = (Frame)$btb.getWindow();
            frame.setIconImage(this.$SHC.$REC());
            frame.setTitle("BlinkMap");
        }
        catch ($A6 $a6) {}
        catch (Exception ex) {
            $M4.print(ex);
        }
        this.$SHC.$T_C = new $E1C(this.$SHC);
        $btb.$KTB().$FOB().$GQB(this.$SHC.$T_C);
        this.$SHC.validate();
        this.$SHC.repaint();
    }
    
    $BBC $D_C() throws Exception {
        this.$A1C();
        if (this.$SHC.$LL == 1) {
            return this.$C1C();
        }
        return null;
    }
    
    void $E_C() {
        final Frame frame = this.$SHC.getFrame();
        if (frame != null) {
            final Dimension screenSize = frame.getToolkit().getScreenSize();
            frame.setBounds(0, 0, screenSize.width, screenSize.height);
            frame.setVisible(true);
            frame.toFront();
        }
    }
    
    private Container $J_C(final Container container, final $H3B $h3B) throws $VD {
        (this.$SHC.$K_C = new $LMC(null, this.$SHC)).setBackground(SystemColor.control);
        this.$SHC.$K_C.setForeground(SystemColor.controlText);
        this.$L_C(this.$SHC.$K_C);
        this.$M_C(this.$SHC.$K_C, $h3B);
        final Container $n_C = this.$N_C(new $CRB());
        this.$SHC.$L_B = this.$O_C();
        final Panel panel = new Panel(new BorderLayout());
        if (this.$SHC.$L_B != null) {
            final Panel panel2 = new Panel(new BorderLayout());
            panel2.add("North", new $B4());
            panel2.add("Center", this.$SHC.$L_B);
            panel2.add("South", new $B4());
            panel.add("North", panel2);
        }
        panel.add("Center", this.$SHC.$K_C);
        container.setLayout(new BorderLayout());
        container.add("North", panel);
        container.add("Center", $n_C);
        return container;
    }
    
    void $LFC(final $BBC $bbc) throws Exception {
        this.$SHC.$EHC(this.$SHC.$TEC.getString("Preparing user interface.."));
        this.$SHC.$G_C = "";
        final $NKC $rfc = new $NKC(null, this.$SHC);
        (this.$SHC.$RFC = $rfc).setBackground(this.$UF.$BF("MAP_BACKGROUND", null));
        $rfc.setForeground(this.$UF.$BF("MAP_FOREGROUND", null));
        final Container $j_C = this.$J_C(new Panel(), $rfc);
        this.$SHC.$UFC.add("Center", $rfc);
        this.$SHC.$UFC.add("East", $j_C);
        this.$SHC.$EHC(this.$SHC.$TEC.getString("Preparing map.."));
        this.$SHC.$CIC($bbc);
        this.$SHC.$EHC(this.$SHC.$TEC.getString("Please wait a moment"));
    }
    
    void $LUC() throws Exception {
        final Locale default1 = Locale.getDefault();
        this.$SHC.$LUC(new Locale(this.$UF.getProperty("LANGUAGE", default1.getLanguage()), default1.getCountry()));
    }
    
    Panel $L_C(final $LMC $lmc) {
        final Panel panel = new Panel(new GridLayout(0, 1));
        this.$P_C(panel);
        final $GJB addItem = $lmc.addItem("Info", panel);
        final $SRC component = new $SRC("i", true);
        component.$NSC = true;
        component.setFont(this.$SHC.$VMC.getFont());
        component.setBackground(new Color(12632319));
        component.setForeground(Color.black);
        addItem.setComponent(component);
        addItem.addMouseListener(new $UMC(null, this.$SHC));
        return panel;
    }
    
    Container $M_C(final $LMC $lmc, final $H3B $h3B) throws $VD {
        final Container $r_C = this.$R_C(new Panel(), $h3B);
        final $GJB addItem = $lmc.addItem("Zoom", $r_C);
        final $SRC component = new $SRC("Z", true);
        component.$NSC = true;
        component.setFont(this.$SHC.$VMC.getFont());
        component.setBackground(new Color(16760943));
        component.setForeground(Color.black);
        addItem.setComponent(component);
        addItem.addMouseListener(new $UMC(null, this.$SHC));
        return $r_C;
    }
    
    private Container $N_C(final Container container) throws $VD {
        (this.$SHC.$S_C = new $AOB(this.$SHC.$G_C, 234)).$GQB(this.$SHC.$T_C);
        this.$SHC.$S_C.$PHB(true);
        this.$SHC.$S_C.setBackground(this.$UF.$BF("MAPINFOTEXT_BACKGROUND", SystemColor.info));
        this.$SHC.$S_C.setForeground(this.$UF.$BF("MAPINFOTEXT_FOREGROUND", SystemColor.infoText));
        this.$SHC.$S_C.setFont(this.$UF.$DF("MAPINFOTEXT_FONT", null));
        container.setLayout(new BorderLayout());
        container.add("North", new $B4());
        container.add("Center", this.$SHC.$S_C);
        container.add("South", new $B4());
        return container;
    }
    
    void $O6() throws Exception {
        this.$Z$C();
        this.$SHC.$A_C = new URLSecurityChecker();
        this.$LUC();
        this.$C_C(this.$SHC.$CTB = this.$B_C(this.$SHC));
        this.$SHC.$CTB.$XTB(this.$SHC);
        if (this.$XL()) {
            return;
        }
        this.$SHC.$EHC("init model..");
        final $BBC $d_C = this.$D_C();
        this.$SHC.$EHC("init GUI..");
        this.$LFC($d_C);
        this.$SHC.$EHC("Viewer Ready");
        this.$E_C();
        this.$SHC.$EHC("start");
        this.$SHC.$DHC();
    }
    
    Applet $O_C() {
        try {
            final InputStream resourceAsStream = this.$SHC.$PD.getResourceAsStream(this.$UF.getProperty("BANNER_SCRIPT", "banner.properties"));
            if (resourceAsStream == null) {
                return null;
            }
            resourceAsStream.close();
        }
        catch (IOException ex) {
            return null;
        }
        return new $S$B(new $J$B(this.$SHC));
    }
    
    private Container $P_C(final Container container) {
        final $AOB $x_C = new $AOB("", 200);
        (this.$SHC.$X_C = $x_C).$GQB(new $Y_C(this.$SHC));
        container.add($x_C);
        return container;
    }
    
    private Container $R_C(final Container container, final $H3B $h3B) throws $VD {
        this.$SHC.$F5B = this.$U_C();
        this.$SHC.$V_C = new $JHC(this.$SHC, this.$SHC.$RFC);
        container.setLayout(new BorderLayout());
        final $CRB $crb = new $CRB(new GridLayout(1, 1));
        $crb.add(this.$SHC.$F5B);
        container.add("Center", $crb);
        container.add("South", this.$SHC.$V_C);
        this.$W_C($h3B, this.$SHC.$F5B);
        return container;
    }
    
    private $U3B $U_C() throws $VD {
        final $U3B $u3B = new $U3B();
        (this.$SHC.$Z_C = new $ZKB()).setPreferredSize(0, 0);
        $u3B.$E4B(this.$SHC.$Z_C);
        final int intProperty = this.$UF.getIntProperty("ZOOM_WIDTH");
        final int intProperty2 = this.$UF.getIntProperty("ZOOM_HEIGHT");
        $u3B.setSize(intProperty, intProperty2);
        $u3B.$C4B().setPreferredSize(intProperty, intProperty2);
        $u3B.setBackground(this.$SHC.controls.getBackground());
        $u3B.setForeground(this.$SHC.controls.getForeground());
        return $u3B;
    }
    
    void $W_C(final $H3B $h3B, final $U3B $u3B) {
        $h3B.$Q4B($u3B.$B4B());
        $u3B.$C4B().$TSB($U3B.$YSB($h3B));
    }
    
    boolean $XL() throws Exception {
        final boolean $xl = $WL.$XL(this.$UF.getProperty("expires", null));
        if ($xl) {
            this.$SHC.$EHC(this.$SHC.$TEC.getMessage("LOOPX", this.$UF.getProperty("updateURL", "http:/testversion.de")));
            this.$E_C();
        }
        return $xl;
    }
    
    void $Z$C() throws $VD {
        this.$SHC.setBackground(this.$UF.$BF("DEFAULT_BACKGROUND", null));
        this.$SHC.setForeground(this.$UF.$BF("DEFAULT_FOREGROUND", null));
        this.$SHC.setFont(this.$UF.$DF("DEFAULT_FONT", null));
        this.$SHC.controls.setBackground(this.$UF.$BF("CONTROL_BACKGROUND", SystemColor.control));
        this.$SHC.controls.setForeground(this.$UF.$BF("CONTROL_FOREGROUND", SystemColor.controlText));
        this.$SHC.controls.setFont(this.$UF.$DF("CONTROL_FONT", null));
        $SUB.$VVB = this.$SHC.controls.getBackground();
        $SRC.$F_C = this.$UF.$BF("MAP_XORCOLOR", new Color(16704250));
        this.$SHC.$ISC.setBackground(this.$UF.$BF("POPUP_BACKGROUND", SystemColor.info));
        this.$SHC.$ISC.setForeground(this.$UF.$BF("POPUP_FOREGROUND", SystemColor.infoText));
        this.$SHC.$ISC.setFont(this.$UF.$DF("POPUP_FONT", null));
        this.$SHC.$VMC.setFont(this.$UF.$DF("MARKER_FONT", null));
    }
    
    $ZEC(final $H0B $shc) {
        super("ViewerInitThread");
        this.$SHC = $shc;
        this.$UF = new $XD($shc.$UF);
    }
    
    public void run() {
        try {
            this.$O6();
        }
        catch (Throwable t) {
            this.$SHC.$X$C(t);
        }
        finally {
            this.$SHC.$Y$C(this);
            this.$SHC = null;
        }
    }
}
