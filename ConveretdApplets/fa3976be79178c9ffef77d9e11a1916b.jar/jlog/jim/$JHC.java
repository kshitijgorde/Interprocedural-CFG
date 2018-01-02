// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Cursor;
import jlog.util.$UD.$VD;
import jlog.$BI.$M4;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jlog.util.$MB;
import java.awt.Dimension;
import java.util.ResourceBundle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import jlog.awt.$X2B.$G3B;
import java.awt.event.ItemListener;
import jlog.util.$F;
import java.awt.Panel;

public class $JHC extends Panel implements $X5B, $F, ItemListener
{
    float $KHC;
    float $LHC;
    float $MHC;
    int $NHC;
    $G3B $H5B;
    Checkbox[] $OHC;
    CheckboxGroup $PHC;
    Label $QHC;
    Label $RHC;
    $H0B $SHC;
    
    MouseListener $A1() {
        return new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final Object source = mouseEvent.getSource();
                final float $a7 = $JHC.this.$H5B.$A7();
                if (source == $JHC.this.$QHC) {
                    $JHC.this.$T_B(Math.max($JHC.this.$LHC, $a7 - $JHC.this.$MHC));
                    $JHC.this.$WHC();
                }
                else if (source == $JHC.this.$RHC) {
                    $JHC.this.$T_B(Math.min($JHC.this.$KHC, $a7 + $JHC.this.$MHC));
                    $JHC.this.$WHC();
                }
            }
        };
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        this.$VHC();
    }
    
    void $T_B(float min) {
        final Dimension size = this.$SHC.$CBC.getSize();
        final float min2 = Math.min(1600.0f / size.width, 1600.0f / size.height);
        if (min2 < this.$KHC) {
            this.$KHC = min2;
            this.$MHC = (this.$KHC - this.$LHC) / this.$NHC;
        }
        min = Math.min(this.$KHC, min);
        this.$H5B.$T_B(min, min);
        this.$WHC();
    }
    
    void $VHC() {
        final $MB $tec = this.$SHC.$TEC;
        this.$QHC.setText($tec.getString("LBL_SMALLER"));
        this.$RHC.setText($tec.getString("LBL_BIGGER"));
    }
    
    public void $WHC() {
        final int max = Math.max(Math.min((int)((this.$H5B.$A7() - this.$LHC) / this.$MHC), this.$NHC - 1), 0);
        this.$PHC.setSelectedCheckbox(this.$OHC[max]);
        this.$QHC.setEnabled(max > 0);
        this.$RHC.setEnabled(max < this.$NHC - 1);
    }
    
    public $JHC(final $H0B $shc, final $G3B $h5B) {
        super(new BorderLayout());
        this.$KHC = 2.75f;
        this.$LHC = 0.5f;
        this.$MHC = 0.75f;
        this.$PHC = new CheckboxGroup();
        this.$SHC = $shc;
        this.$H5B = $h5B;
        try {
            this.$LHC = $shc.$UF.$YE("SCALE_MIN", this.$LHC);
            this.$KHC = $shc.$UF.$YE("SCALE_MAX", this.$KHC);
            this.$MHC = $shc.$UF.$YE("SCALE_STEP", this.$MHC);
        }
        catch ($VD $vd) {
            $M4.print($vd);
        }
        final Cursor predefinedCursor = Cursor.getPredefinedCursor(12);
        this.$NHC = (int)((this.$KHC - this.$LHC) / this.$MHC + 1.0f);
        final Panel panel = new Panel(new GridLayout(1, 0));
        this.$OHC = new Checkbox[this.$NHC];
        for (int i = 0; i < this.$NHC; ++i) {
            final Checkbox checkbox = new Checkbox("", this.$PHC, false);
            (this.$OHC[i] = checkbox).setCursor(predefinedCursor);
            checkbox.addItemListener(this);
            panel.add(checkbox);
        }
        panel.setForeground($shc.controls.getBackground());
        final MouseListener $a1 = this.$A1();
        (this.$QHC = new Label()).addMouseListener($a1);
        this.$QHC.setForeground(Color.blue);
        this.$QHC.setCursor(predefinedCursor);
        (this.$RHC = new Label()).addMouseListener($a1);
        this.$RHC.setForeground(Color.blue);
        this.$RHC.setCursor(predefinedCursor);
        this.add("West", this.$QHC);
        this.add("Center", panel);
        this.add("East", this.$RHC);
        this.$VHC();
        $shc.$TEC.$NB(this);
        this.$WHC();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() != 1) {
            return;
        }
        for (int i = 0; i < this.$NHC; ++i) {
            if (this.$OHC[i] == itemEvent.getSource()) {
                this.$T_B(this.$LHC + i * this.$MHC);
                return;
            }
        }
    }
}
