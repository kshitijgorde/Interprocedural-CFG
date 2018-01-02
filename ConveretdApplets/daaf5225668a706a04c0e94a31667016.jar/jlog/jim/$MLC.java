// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$T5.$D7.$SEC;
import java.awt.event.ItemEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import jlog.awt.$G4;
import java.awt.SystemColor;
import java.util.ResourceBundle;
import jlog.awt.$B4;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import jlog.io.$LB;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Window;
import jlog.awt.$I8.$J8;
import jlog.awt.image.$A6;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Container;
import jlog.awt.$L.$G9;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;
import jlog.awt.$L.$I9;
import jlog.awt.$YH.$SQB;
import jlog.util.$XG.$YG;
import jlog.awt.$YH.$ARB;
import jlog.$BI.$M4;
import jlog.io.$P4;
import jlog.awt.$YH.$UQB;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.FileDialog;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Component;
import jlog.$H4;
import jlog.awt.$I8.$H9;
import java.awt.event.ItemListener;
import java.awt.Panel;

public class $MLC extends Panel implements $Y8B, ItemListener, $H9, $I7B, $H4, $U9B
{
    static final String $BNC = "JERubrikDialog";
    Component $PEC;
    private $F8B $ZDC;
    private $BBC $CBC;
    private Label $ZUC;
    private TextField $VUC;
    private Label $JZC;
    TextField $KZC;
    FileDialog $LZC;
    private Label $MZC;
    private TextField $NZC;
    private Label $AVC;
    private TextArea $OZC;
    private Checkbox $PZC;
    private Checkbox $QZC;
    private Checkbox $RZC;
    private Checkbox $SZC;
    private Label $TZC;
    private Label $UZC;
    private Checkbox $VZC;
    private Checkbox $WZC;
    private Checkbox $XZC;
    private Checkbox $YZC;
    private Checkbox $ZZC;
    private Checkbox $A$C;
    private $VDC $B$C;
    private boolean $C$C;
    private boolean $D$C;
    private String $IVC;
    private String $JVC;
    private Color $E$C;
    private Color $F$C;
    private URL $G$C;
    private Image $H$C;
    private String $I$C;
    private int $J$C;
    private int $K$C;
    private int $L$C;
    boolean $M$C;
    $UQB $VQB;
    Label $XQB;
    $UQB $HWC;
    Label $GWC;
    $YAC $ZAC;
    
    public void $AEC($F8B $zdc, final $BBC $cbc) {
        if (this.$ZDC == $zdc && $zdc != null && $cbc == this.$CBC) {
            return;
        }
        this.$C$C = ($zdc == null);
        if (this.$C$C) {
            $zdc = new $F8B("", "", "", null, null, null);
        }
        this.$D$C = this.$C$C;
        this.$IVC = $zdc.getName();
        this.$I$C = $zdc.getID();
        this.$JVC = $zdc.getDescription();
        this.$H$C = $zdc.getImage().getImage();
        this.$G$C = $zdc.$DPC();
        this.$E$C = $zdc.getColor();
        this.$F$C = $zdc.$XOC;
        this.$J$C = $zdc.$CKC();
        this.$K$C = $zdc.getMouse();
        this.$L$C = $zdc.getIndex();
        this.$ZDC = $zdc;
        this.$CBC = $cbc;
        if (this.$CBC != null) {
            this.$M$C = this.$IVC.equals(this.$CBC.$YMC);
        }
        this.$B$C.$AEC($zdc);
        this.$VUC.setText(this.$IVC);
        this.$OZC.setText(this.$JVC);
        this.$PZC.setState(this.$M$C);
        this.$NZC.setText(this.$I$C);
        this.$VQB.setColor(this.$E$C);
        this.$HWC.setColor(this.$F$C);
        final int $l$C = this.$L$C;
        this.$VZC.setState(($l$C & 0x1) != 0x0);
        this.$WZC.setState(($l$C & 0x4) != 0x0);
        this.$XZC.setState(($l$C & 0x10) != 0x0);
        final int $j$C = this.$J$C;
        this.$QZC.setState(($j$C & 0x1) != 0x0);
        this.$RZC.setState(($j$C & 0x2) != 0x0);
        this.$SZC.setState(($j$C & 0x20) != 0x0);
        final int $k$C = this.$K$C;
        this.$A$C.setState(($k$C & 0x1) != 0x0);
        this.$ZZC.setState(($k$C & 0x4) != 0x0);
        this.$YZC.setState(($k$C & 0x8) != 0x0);
        try {
            String $t$C = "";
            if (this.$G$C != null) {
                $t$C = $P4.$T$C(this.$CBC.$VPC(), this.$G$C);
            }
            this.$KZC.setText($t$C);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void $CIC(final $BBC $cbc) {
        this.$CBC = $cbc;
    }
    
    void $FWC() {
        final $ARB $arb = new $ARB(this.$ZAC.getFrame(), true);
        (this.$VQB = new $UQB($YG.$YH, $YG.$ZH, "user defined", "(default)", $arb)).setLabel("Marker");
        this.$XQB = new Label("Color:");
        (this.$HWC = new $UQB($YG.$YH, $YG.$ZH, "user defined", "(default)", $arb)).setLabel("XOR Color");
        this.$GWC = new Label("XOR Color:");
    }
    
    void $GNC() {
        new $I9(this).$W9(new $K2B() {
            public void $Z9(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 27) {
                    try {
                        $MLC.this.$ZAC.$JJC();
                    }
                    catch (Throwable t) {
                        $M4.print(t);
                    }
                }
            }
        });
    }
    
    public boolean $J7B() throws Exception {
        this.$ZAC.$INC().$OTB("JERubrikDialog");
        this.$PEC.requestFocus();
        return true;
    }
    
    Container $N$C() {
        (this.$KZC = new TextField()).addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                try {
                    $MLC.this.$O$C();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        final Button button = new Button("LOAD_SYMBOL");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $MLC.this.$KZC.setText($MLC.this.$P$C($MLC.this.$ZAC.$CBC));
                    $MLC.this.$O$C();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        final Panel panel = new Panel(new BorderLayout());
        panel.add("Center", this.$KZC);
        panel.add("East", button);
        return panel;
    }
    
    boolean $O$C() throws $A6 {
        URL $sxc = null;
        final String text = this.$KZC.getText();
        Image image = null;
        final $H0B $vjc = this.$CBC.$VJC();
        if ($XVC(text)) {
            this.$KZC.requestFocus();
            $vjc.$YJC("illegal character");
            return false;
        }
        if (!text.equals("")) {
            try {
                $sxc = $P4.$SXC(this.$CBC.$VPC(), text);
            }
            catch (Exception ex) {
                $vjc.$YJC(ex.toString());
                this.$KZC.requestFocus();
                return false;
            }
            image = $vjc.createImage($sxc);
            if (image == null) {
                this.$KZC.requestFocus();
                $vjc.$YJC(String.valueOf(String.valueOf($sxc)) + " not found");
                return false;
            }
        }
        this.$ZDC.$CPC($sxc, image);
        this.$ZDC.setColor(this.$VQB.getColor());
        this.$ZDC.$XOC = this.$HWC.getColor();
        return true;
    }
    
    String $P$C(final $BBC $bbc) throws IOException {
        if (this.$LZC == null) {
            (this.$LZC = new FileDialog(this.$ZAC.getFrame(), "Import Image", 0)).pack();
            $J8.$K8(this.$LZC);
        }
        this.$LZC.show();
        final String directory = this.$LZC.getDirectory();
        final String file = this.$LZC.getFile();
        if (file == null || directory == null || file.length() == 0) {
            return null;
        }
        final FileInputStream fileInputStream = new FileInputStream(new File(directory, file));
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(new File($bbc.$DBC, file));
            try {
                $LB.copy(fileInputStream, fileOutputStream);
            }
            finally {
                fileOutputStream.close();
            }
        }
        finally {
            fileInputStream.close();
        }
        return file;
    }
    
    void $QEC() {
        final Panel panel = new Panel(new FlowLayout(1, 6, 3));
        final Button button = new Button("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    if ($MLC.this.setValues()) {
                        $MLC.this.$ZAC.$JJC();
                    }
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        panel.add(button);
        final Component component = this.$PEC = new Button("Cancel");
        ((Button)component).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $MLC.this.$ZAC.$JJC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        panel.add(component);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add("North", new $B4());
        panel2.add("Center", panel);
        this.add("South", panel2);
    }
    
    public boolean $SVC() {
        final String trim = this.$VUC.getText().trim();
        if (trim.length() == 0 || $XVC(trim)) {
            this.$VUC.requestFocus();
            return false;
        }
        final $F8B $iic = this.$CBC.$LKC.$IIC(trim);
        if ($iic != null && $iic != this.$ZDC) {
            this.$VUC.requestFocus();
            return false;
        }
        this.$ZDC.setName(trim);
        if (this.$PZC.getState()) {
            this.$CBC.$YMC = trim;
        }
        return true;
    }
    
    public boolean $U$C() {
        final String text = this.$NZC.getText();
        if ($XVC(text)) {
            this.$NZC.requestFocus();
            return false;
        }
        this.$ZDC.$BPC(text);
        return true;
    }
    
    public boolean $UVC() {
        final String text = this.$OZC.getText();
        if ($XVC(text)) {
            this.$OZC.requestFocus();
            return false;
        }
        this.$ZDC.setDescription(text);
        return true;
    }
    
    private static boolean $XVC(final String s) {
        return s.indexOf(59) != -1 || s.indexOf(44) != -1 || s.indexOf(124) != -1;
    }
    
    public $MLC(final $YAC $zac, final $F8B $f8B, final $BBC $bbc, final ResourceBundle language) {
        super(new BorderLayout());
        this.$ZUC = new Label();
        this.$VUC = new TextField();
        this.$JZC = new Label();
        this.$LZC = null;
        this.$MZC = new Label();
        this.$NZC = new TextField(1);
        this.$AVC = new Label();
        this.$OZC = new TextArea(2, 40);
        this.$PZC = new Checkbox();
        this.$QZC = new Checkbox();
        this.$RZC = new Checkbox();
        this.$SZC = new Checkbox();
        this.$TZC = new Label();
        this.$UZC = new Label();
        this.$VZC = new Checkbox();
        this.$WZC = new Checkbox();
        this.$XZC = new Checkbox();
        this.$YZC = new Checkbox();
        this.$ZZC = new Checkbox();
        this.$A$C = new Checkbox();
        this.$C$C = false;
        this.$D$C = false;
        this.$ZAC = $zac;
        this.$QEC();
        this.$FWC();
        this.setBackground(SystemColor.control);
        (this.$B$C = new $VDC(($F8B)null)).setFont($zac.$VMC.getFont());
        final Panel panel = new Panel();
        final $G4 $g4 = new $G4(panel);
        final GridBagConstraints constraints = $g4.getConstraints();
        constraints.insets = new Insets(4, 2, 4, 2);
        constraints.anchor = 17;
        constraints.weightx = 1.0;
        constraints.fill = 2;
        $g4.add(this.$ZUC, 0, 0, 1, 1);
        constraints.weightx = 2.0;
        $g4.add(this.$VUC, 1, 0, 1, 1);
        constraints.weightx = 0.0;
        $g4.add(new $B4(), 0, 1, 3, 1);
        $g4.add(this.$JZC, 0, 2, 1, 1);
        $g4.add(this.$N$C(), 1, 2, 2, 1);
        $g4.add(this.$MZC, 0, 3, 1, 1);
        $g4.add(this.$NZC, 1, 3);
        $g4.add(this.$XQB, 0, 4, 1, 1);
        $g4.add(this.$VQB, 1, 4);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = 0;
        constraints.anchor = 10;
        $g4.add(this.$B$C, 2, 3, 1, 2);
        constraints.anchor = 17;
        constraints.fill = 2;
        $g4.add(this.$AVC, 0, 6, 1, 1);
        constraints.fill = 1;
        $g4.add(this.$OZC, 1, 6, 2, 1);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = 2;
        constraints.anchor = 10;
        $g4.add(new $B4(), 0, 1, 3, 1);
        $g4.add(new $B4(), 0, 5, 3, 1);
        $g4.add(new $B4(), 0, 7, 3, 1);
        $g4.add(new Label("Marker"), 0, 8, 1, 1);
        $g4.add(this.$QZC, 1, 8, 1, 1);
        $g4.add(this.$RZC, 1, 9, 1, 1);
        $g4.add(this.$SZC, 1, 10, 1, 1);
        this.$QZC.addItemListener(this);
        this.$RZC.addItemListener(this);
        this.$SZC.addItemListener(this);
        $g4.add(new $B4(), 0, 11, 3, 1);
        $g4.add(this.$TZC = new Label("Index"), 0, 12, 1, 1);
        $g4.add(this.$VZC, 1, 12, 1, 1);
        $g4.add(this.$PZC, 2, 12, 1, 1);
        this.$PZC.addItemListener(this);
        $g4.add(this.$WZC, 1, 13, 1, 1);
        $g4.add(this.$HWC, 2, 13, 1, 1);
        this.$VZC.addItemListener(this);
        this.$WZC.addItemListener(this);
        this.$XZC.addItemListener(this);
        $g4.add(new $B4(), 0, 15, 3, 1);
        $g4.add(new Label("Mouse"), 0, 16, 1, 1);
        $g4.add(this.$YZC, 1, 16, 1, 1);
        $g4.add(this.$ZZC, 1, 17, 1, 1);
        $g4.add(this.$A$C, 1, 18, 1, 1);
        this.$A$C.addItemListener(this);
        this.$ZZC.addItemListener(this);
        this.$YZC.addItemListener(this);
        this.add("Center", panel);
        this.$AEC($f8B, $bbc);
        this.setLanguage(language);
        this.$GNC();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        int n = 0;
        if (this.$QZC.getState()) {
            n |= 0x1;
        }
        if (this.$RZC.getState()) {
            n |= 0x2;
        }
        if (this.$SZC.getState()) {
            n |= 0x20;
        }
        this.$ZDC.$JPC(n);
        int n2 = 0;
        if (this.$VZC.getState()) {
            n2 |= 0x1;
        }
        if (this.$XZC.getState()) {
            n2 |= 0x10;
        }
        if (this.$WZC.getState()) {
            n2 |= 0x4;
        }
        this.$ZDC.$SMC(n2);
        int n3 = 0;
        if (this.$A$C.getState()) {
            n3 |= 0x1;
        }
        if (this.$YZC.getState()) {
            n3 |= 0x8;
        }
        if (this.$ZZC.getState()) {
            n3 |= 0x4;
        }
        this.$ZDC.$KPC(n3);
        if (itemEvent.getSource() == this.$PZC && !this.$PZC.getState()) {
            this.$CBC.$YMC = "";
        }
    }
    
    public void setLanguage(final ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return;
        }
        this.$ZUC.setText("ATTRIBUTE_NAME");
        this.$JZC.setText("ATTRIBUTE_SYMBOL");
        this.$MZC.setText("ID-Letter");
        this.$PZC.setLabel("STARTCARD_ATTRIBUTE");
        this.$UZC.setText("MOUSE");
        this.$TZC.setText("INDEX_CARD");
        this.$AVC.setText("DESCRIPTION");
        this.$QZC.setLabel("marker selectable");
        this.$RZC.setLabel("default selected");
        this.$SZC.setLabel("default for new area");
        this.$VZC.setLabel("index visible");
        this.$XZC.setLabel("blinking area");
        this.$WZC.setLabel("inverse selection");
        this.$A$C.setLabel("activated when marked");
        this.$YZC.setLabel("popup name");
        this.$ZZC.setLabel("inverse area");
        $SEC.setLanguage(this, resourceBundle);
    }
    
    public boolean setUndo() {
        if (this.$C$C) {
            return true;
        }
        if (this.$D$C) {
            this.$C$C = true;
            this.$D$C = false;
            this.$CBC.$LIC(this.$ZDC);
        }
        this.$ZDC.setName(this.$IVC);
        this.$ZDC.setDescription(this.$JVC);
        this.$ZDC.setColor(this.$E$C);
        this.$ZDC.$CPC(this.$G$C, this.$H$C);
        this.$ZDC.$BPC(this.$I$C);
        this.$ZDC.$JPC(this.$J$C);
        this.$ZDC.$KPC(this.$K$C);
        this.$ZDC.$SMC(this.$L$C);
        return true;
    }
    
    public boolean setValues() {
        try {
            if (!this.$SVC() || !this.$U$C() || !this.$UVC() || !this.$O$C()) {
                return false;
            }
            this.$CBC.$IF(true);
            if (this.$C$C) {
                this.$CBC.$JIC(this.$ZDC);
                this.$C$C = false;
                return this.$D$C = true;
            }
            return true;
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
        catch (Error error) {
            $M4.print(error);
        }
        return false;
    }
}
