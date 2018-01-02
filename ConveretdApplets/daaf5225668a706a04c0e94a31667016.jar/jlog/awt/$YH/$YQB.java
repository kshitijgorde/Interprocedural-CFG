// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$YH;

import java.util.Enumeration;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Container;
import java.util.Vector;
import java.awt.Color;
import jlog.awt.$GRB.$HRB;

public class $YQB
{
    $HRB[] $IRB;
    boolean $JRB;
    boolean $KRB;
    Color $YH;
    boolean $LRB;
    Vector v;
    
    public void $W3(final $R3 $r3) {
        if (this.v == null) {
            this.v = new Vector();
        }
        this.v.addElement($r3);
    }
    
    public void $X3(final $R3 $r3) {
        if (this.v == null) {
            return;
        }
        this.v.removeElement($r3);
    }
    
    public $YQB(final Container container) {
        this(container, true);
    }
    
    public $YQB(final Container container, final boolean $lrb) {
        this.$JRB = true;
        this.$KRB = true;
        this.$YH = Color.white;
        this.$LRB = true;
        this.v = null;
        this.$LRB = $lrb;
        if ($lrb) {
            container.setLayout(new GridLayout(3, 1));
        }
        else {
            container.setLayout(new GridLayout(1, 3));
        }
        this.$IRB = new $HRB[3];
        for (int i = 0; i < 3; ++i) {
            if ($lrb) {
                this.$IRB[i] = new $HRB(0, 0, 0, 0, 255);
            }
            else {
                this.$IRB[i] = new $HRB(1, 0, 0, 0, 255);
            }
            this.$IRB[i].setBlockIncrement(16);
            container.add(this.$IRB[i]);
            this.$IRB[i].addAdjustmentListener(new $MRB(i));
        }
        this.$IRB[0].$NRB(Color.red.darker());
        this.$IRB[1].$NRB(Color.green.darker());
        this.$IRB[2].$NRB(Color.blue.darker());
        this.setColor(Color.white);
    }
    
    public Color getColor() {
        return this.$YH;
    }
    
    public void setColor(final Color $yh) {
        if ($yh == this.$YH) {
            return;
        }
        if ($yh == null || this.$YH == null) {
            for (int i = 0; i < 3; ++i) {
                this.$IRB[i].setEnabled($yh != null);
            }
        }
        if ($yh != null && !$yh.equals(this.$YH)) {
            final int[] array = { $yh.getRed(), $yh.getGreen(), $yh.getBlue() };
            for (int j = 0; j < 3; ++j) {
                int value;
                if (this.$LRB) {
                    value = array[j];
                }
                else {
                    value = 255 - array[j];
                }
                this.$IRB[j].setValue(value);
                if (this.$JRB) {
                    String string = "";
                    String text;
                    if (this.$KRB) {
                        if (value < 16) {
                            string = String.valueOf(string) + "0";
                        }
                        text = (String.valueOf(string) + Integer.toHexString(value)).toUpperCase();
                    }
                    else {
                        text = String.valueOf(value);
                    }
                    this.$IRB[j].setText(text);
                }
            }
        }
        this.$YH = $yh;
    }
    
    class $MRB implements AdjustmentListener
    {
        int $N5;
        
        $MRB(final int $n5) {
            this.$N5 = $n5;
        }
        
        public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
            final Color $yh = $YQB.this.$YH;
            if ($yh == null) {
                return;
            }
            int value;
            if ($YQB.this.$LRB) {
                value = adjustmentEvent.getValue();
            }
            else {
                value = 255 - adjustmentEvent.getValue();
            }
            if ($YQB.this.$JRB) {
                String string = "";
                String text;
                if ($YQB.this.$KRB) {
                    if (value < 16) {
                        string = String.valueOf(string) + "0";
                    }
                    text = (String.valueOf(string) + Integer.toHexString(value)).toUpperCase();
                }
                else {
                    text = String.valueOf(value);
                }
                $YQB.this.$IRB[this.$N5].setText(text);
            }
            final int[] array = { $yh.getRed(), $yh.getGreen(), $yh.getBlue() };
            array[this.$N5] = value;
            $YQB.this.$YH = new Color(array[0], array[1], array[2]);
            if ($YQB.this.v != null && !$YQB.this.v.isEmpty()) {
                final $T3 $t3 = new $T3(this, $YQB.this.$YH);
                final Enumeration<$R3> elements = (Enumeration<$R3>)$YQB.this.v.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().$S3($t3);
                }
            }
        }
    }
}
