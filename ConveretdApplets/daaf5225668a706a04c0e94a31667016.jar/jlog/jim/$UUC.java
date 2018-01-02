// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$T5.$D7.$SEC;
import java.util.ResourceBundle;
import java.beans.PropertyChangeEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import jlog.awt.$B4;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Insets;
import jlog.awt.$G4;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import jlog.awt.$L.$G9;
import jlog.$BI.$M4;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;
import jlog.awt.$L.$I9;
import java.util.Enumeration;
import java.util.Vector;
import jlog.$T5.$D7.$SGC;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import jlog.awt.$I8.$H9;
import java.awt.Panel;

public class $UUC extends Panel implements $I6B, $H9, PropertyChangeListener
{
    public static final String $BNC = "jlog.jim.JEAreaDialog";
    Component $PEC;
    $YAC $ZAC;
    private $EIC $FIC;
    private $BBC $CBC;
    private TextField $VUC;
    private TextArea $WUC;
    private TextField $XUC;
    private TextField $YUC;
    private Label $ZUC;
    private Label $AVC;
    private Label $BVC;
    private Label $CVC;
    private Button $DVC;
    static boolean $EVC;
    private Checkbox $FVC;
    private $SGC $GVC;
    private Vector $HVC;
    private String $IVC;
    private String $JVC;
    private String $KVC;
    private String $LVC;
    private boolean $MVC;
    
    public void $CIC(final $BBC $cbc) {
        if ($cbc == this.$CBC) {
            return;
        }
        if (this.$CBC != null) {
            final $XHC $lkc = this.$CBC.$LKC;
            if ($lkc != null) {
                $lkc.removePropertyChangeListener(this);
                final Enumeration $hk = $lkc.$HK();
                while ($hk.hasMoreElements()) {
                    $hk.nextElement().removePropertyChangeListener(this);
                }
            }
        }
        this.$CBC = $cbc;
        this.$GVC.$QVC("");
        this.$GVC.setSelection("");
        if (this.$CBC != null) {
            final $XHC $lkc2 = this.$CBC.$LKC;
            if ($lkc2 != null) {
                $lkc2.addPropertyChangeListener(this, true);
            }
        }
    }
    
    void $GNC() {
        new $I9(this).$W9(new $K2B() {
            public void $Z9(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 27) {
                    try {
                        $UUC.this.$ZAC.$DHC();
                    }
                    catch (Throwable t) {
                        $M4.print(t);
                    }
                }
            }
        });
    }
    
    void $NVC() {
        final Panel panel = new Panel(new FlowLayout(1, 6, 3));
        this.$QEC(panel);
        final Panel panel2 = new Panel();
        final $G4 $g4 = new $G4(panel2);
        final GridBagConstraints constraints = $g4.getConstraints();
        constraints.insets = new Insets(4, 2, 4, 2);
        constraints.weightx = 1.0;
        constraints.anchor = 17;
        constraints.fill = 2;
        $g4.add(this.$ZUC, 0, 0, 1, 1);
        $g4.add(this.$VUC, 1, 0, 3, 1);
        this.$PEC = this.$VUC;
        $g4.add(this.$BVC, 0, 2, 1, 1);
        $g4.add(this.$YUC = new TextField(), 1, 2, 3, 1);
        this.$YUC.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                try {
                    $UUC.this.$OVC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        $g4.add(this.$CVC, 0, 3, 1, 1);
        $g4.add(this.$XUC, 1, 3, 1, 1);
        constraints.fill = 1;
        constraints.weighty = 1.0;
        constraints.anchor = 10;
        $g4.add(this.$GVC = new $SGC(), 0, 6, 4, 1);
        constraints.fill = 2;
        $g4.add(new $B4(), 0, 5, 4, 1);
        $g4.add(new $B4(), 0, 1, 4, 1);
        $g4.add(new $B4(), 0, 7, 4, 1);
        constraints.fill = 2;
        constraints.anchor = 18;
        $g4.add(this.$AVC, 0, 10, 1, 1);
        $g4.add(this.$WUC, 1, 10, 3, 1);
        $g4.add(new $B4(), 0, 11, 4, 1);
        $g4.add(this.$FVC, 0, 12, 4, 1);
        this.add("Center", panel2);
        this.add("South", panel);
        this.$GNC();
    }
    
    boolean $OVC() {
        final String text = this.$YUC.getText();
        if (this.$XVC(text)) {
            this.$YUC.setText(this.$KVC);
            this.$YUC.requestFocus();
            return false;
        }
        this.$FIC.$XSC(text);
        return true;
    }
    
    void $QEC(final Container container) {
        final Button button = new Button("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    if ($UUC.this.setValues()) {
                        $UUC.this.$ZAC.$DHC();
                    }
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        container.add(button);
        final Button button2 = new Button("Cancel");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $UUC.this.$ZAC.$DHC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        container.add(button2);
    }
    
    boolean $SVC() {
        final String trim = this.$VUC.getText().trim();
        if (this.$XVC(trim)) {
            this.$VUC.setText(this.$IVC);
            this.$VUC.requestFocus();
            return false;
        }
        this.$FIC.setName(trim);
        return true;
    }
    
    public void $TKC(final $EIC $fic) {
        if (this.$FIC == $fic) {
            return;
        }
        if (this.$FIC != null) {
            this.$FIC.removePropertyChangeListener(this);
        }
        this.$FIC = $fic;
        this.$MVC = $UUC.$EVC;
        this.$WUC.setText("");
        if (this.$FIC == null) {
            this.$CIC(null);
            return;
        }
        this.$IVC = this.$FIC.getName();
        this.$JVC = this.$FIC.getDescription();
        this.$KVC = this.$FIC.$VOB();
        this.$LVC = this.$FIC.$EP();
        this.$HVC = this.$FIC.$ZSC();
        this.$CIC(this.$FIC.$UJC());
        this.$GVC.$PVC(this.$GVC.getSelection());
        this.$FIC.addPropertyChangeListener(this, true);
        this.$PEC.requestFocus();
    }
    
    boolean $TVC() {
        final String trim = this.$XUC.getText().trim();
        if (this.$XVC(trim)) {
            this.$XUC.requestFocus();
            this.$XUC.setText(this.$LVC);
            return false;
        }
        this.$FIC.$IT(trim);
        return true;
    }
    
    public $BBC $UJC() {
        return this.$CBC;
    }
    
    boolean $UVC() {
        final String trim = this.$WUC.getText().trim();
        if (this.$XVC(trim)) {
            this.$WUC.setText(this.$JVC);
            this.$WUC.requestFocus();
            return false;
        }
        this.$FIC.setDescription(trim);
        return true;
    }
    
    boolean $VVC() {
        try {
            final String[] items = this.$GVC.$WVC().getItems();
            int length = items.length;
            while (length-- != 0) {
                final $F8B $iic = this.$CBC.$IIC(items[length]);
                if (this.$FIC.$YSC($iic)) {
                    this.$FIC.$LIC($iic);
                    $iic.$KIC(this.$FIC);
                }
            }
            final String[] items2 = this.$GVC.getListSelection().getItems();
            int length2 = items2.length;
            while (length2-- != 0) {
                final $F8B $iic2 = this.$CBC.$IIC(items2[length2]);
                if (!this.$FIC.$YSC($iic2)) {
                    this.$FIC.$JIC($iic2);
                    $iic2.$DIC(this.$FIC);
                }
            }
            return true;
        }
        catch (Throwable t) {
            $M4.print(t);
            return false;
        }
    }
    
    private boolean $XVC(final String s) {
        return s.indexOf(59) != -1 || s.indexOf(44) != -1 || s.indexOf(124) != -1;
    }
    
    static {
        $UUC.$EVC = true;
    }
    
    public $UUC(final $YAC $zac) {
        super(new BorderLayout());
        this.$CBC = null;
        this.$VUC = new TextField();
        this.$WUC = new TextArea(3, 40);
        this.$XUC = new TextField();
        this.$YUC = new TextField();
        this.$ZUC = new Label();
        this.$AVC = new Label();
        this.$BVC = new Label();
        this.$CVC = new Label();
        this.$DVC = new Button();
        this.$FVC = new Checkbox("open dialog for created object", true);
        this.$ZAC = $zac;
        this.$NVC();
        this.setLanguage($zac.$TEC.$RB());
        this.$TKC(null);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object oldValue = propertyChangeEvent.getOldValue();
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        final Object source = propertyChangeEvent.getSource();
        if (newValue instanceof Boolean) {
            (boolean)newValue;
        }
        if (propertyName.equals("name")) {
            if (source instanceof $F8B) {
                final String s = (String)newValue;
                if (oldValue == null) {
                    if (!s.equals("ALL_AREAS")) {
                        this.$GVC.$PVC(s);
                    }
                }
                else {
                    this.$GVC.rename((String)oldValue, s);
                }
            }
            else if (source == this.$FIC) {
                this.$VUC.setText((String)newValue);
            }
        }
        else if (propertyName.equals("+RL_RUB")) {
            final $F8B $f8B = ($F8B)newValue;
            final String name = $f8B.getName();
            if (!name.equals("ALL_AREAS")) {
                this.$GVC.$PVC(name);
            }
            $f8B.addPropertyChangeListener(this, true);
        }
        else {
            if (propertyName.equals("-RL_RUB")) {
                final $F8B $f8B2 = ($F8B)newValue;
                final String name2 = $f8B2.getName();
                $f8B2.removePropertyChangeListener(this);
                this.$GVC.remove(name2);
                this.$HVC.removeElement($f8B2);
                return;
            }
            if (source != this.$FIC) {
                return;
            }
            if (propertyName.equals("R_ADD")) {
                final String name3 = (($F8B)newValue).getName();
                if (!name3.equals("ALL_AREAS")) {
                    this.$GVC.addSelection(name3);
                }
                return;
            }
            if (propertyName.equals("R_REM")) {
                final String name4 = (($F8B)newValue).getName();
                if (!name4.equals("ALL_AREAS")) {
                    this.$GVC.$PVC(name4);
                }
            }
            else if (propertyName.equals("desc")) {
                this.$WUC.setText((String)newValue);
            }
            else if (propertyName.equals("PROP_HREF")) {
                this.$YUC.setText((String)newValue);
            }
            else if (propertyName.equals("target")) {
                this.$XUC.setText((String)newValue);
            }
            else if (propertyName.equals("map")) {
                this.$CIC(($BBC)newValue);
            }
        }
    }
    
    public void setLanguage(final ResourceBundle resourceBundle) {
        this.$ZUC.setText("AREA_NAME");
        this.$BVC.setText("AREA_URL");
        this.$CVC.setText("AREA_TARGET");
        this.$AVC.setText("AREA_DESCRIPTION");
        this.$GVC.setLabels("DEFINED ATTRIBUTES", "AREA ATTRIBUTES");
        this.$DVC.setLabel("AREA_NEW_RUBRIK");
        this.$FVC.setLabel("open dialog for created object");
        $SEC.setLanguage(this, resourceBundle);
    }
    
    public boolean setUndo() {
        if (this.$FIC == null) {
            return true;
        }
        this.$FIC.setName(this.$IVC);
        this.$FIC.$XSC(this.$KVC);
        this.$FIC.$IT(this.$LVC);
        this.$FIC.$ATC(this.$HVC);
        $UUC.$EVC = this.$MVC;
        this.$FVC.setState($UUC.$EVC);
        return true;
    }
    
    public boolean setValues() {
        if (this.$FIC == null) {
            return true;
        }
        try {
            if (!this.$SVC() || !this.$OVC() || !this.$TVC() || !this.$UVC() || !this.$VVC()) {
                return false;
            }
            $UUC.$EVC = this.$FVC.getState();
        }
        catch (Exception ex) {
            $M4.print(ex);
            return false;
        }
        if (this.$CBC != null) {
            this.$CBC.$IF(true);
        }
        return true;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.$VUC.requestFocus();
            this.$VUC.selectAll();
        }
    }
}
