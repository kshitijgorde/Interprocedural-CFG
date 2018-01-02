// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$YH;

import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.Panel;

public class $UQB extends Panel implements ItemListener
{
    Color $PRB;
    Color $QRB;
    public Color $ERB;
    Color[] $YH;
    String[] $ZH;
    String $RRB;
    String $SRB;
    $QQB $XQB;
    Choice $VQB;
    $SQB $TRB;
    String $MP;
    
    void $URB() {
        if (this.$TRB != null) {
            this.$TRB.$TQB(this);
        }
    }
    
    public void $VRB(final Color $qrb) {
        this.$QRB = $qrb;
        if (this.$PRB == null) {
            this.setColor(this.$PRB);
        }
    }
    
    public $UQB(final Color[] array, final String[] array2, final String s, final String s2, final $SQB $trb) {
        this.$PRB = null;
        this.$QRB = null;
        this.$ERB = null;
        this.$XQB = null;
        this.$VQB = null;
        this.$TRB = null;
        this.$MP = null;
        this.$XQB = new $QQB();
        this.$XQB.$RQB = true;
        this.$XQB.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                $UQB.this.$URB();
            }
        });
        this.$XQB.setCursor(Cursor.getPredefinedCursor(12));
        this.setLayout(new BorderLayout(4, 0));
        this.add("West", this.$XQB);
        this.setValues(array, array2, s, s2);
        this.$TRB = $trb;
    }
    
    public Color getColor() {
        return this.$PRB;
    }
    
    public String getLabel() {
        return this.$MP;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() != 1) {
            return;
        }
        int selectedIndex = this.$VQB.getSelectedIndex();
        if (this.$SRB != null) {
            --selectedIndex;
        }
        if (selectedIndex < 0) {
            this.setColor(null);
            if (this.$TRB != null) {
                this.$TRB.setColor(null, this.$MP);
            }
        }
        else if (selectedIndex < this.$YH.length) {
            this.setColor(this.$YH[selectedIndex]);
            if (this.$TRB != null) {
                this.$TRB.setColor(this.$YH[selectedIndex], this.$MP);
            }
        }
        else {
            this.setColor(this.$ERB);
            this.$URB();
        }
    }
    
    public void setColor(final Color color) {
        this.$PRB = color;
        if (color == null) {
            this.$VQB.select(0);
            this.$XQB.setBackground(this.$QRB);
            this.$XQB.repaint();
            return;
        }
        this.$XQB.setBackground(color);
        this.$XQB.repaint();
        for (int i = 0; i < this.$YH.length; ++i) {
            if (this.$YH[i].equals(color)) {
                int n = i;
                if (this.$SRB != null) {
                    ++n;
                }
                this.$VQB.select(n);
                return;
            }
        }
        this.$VQB.select(this.$VQB.getItemCount() - 1);
    }
    
    public void setLabel(final String $mp) {
        this.$MP = $mp;
    }
    
    public void setValues(final Color[] $yh, final String[] $zh, final String $rrb, final String $srb) {
        if (this.$VQB != null) {
            this.$VQB.removeItemListener(this);
            this.remove(this.$VQB);
        }
        this.$VQB = new Choice();
        if ($srb != null) {
            this.$VQB.addItem($srb);
        }
        for (int i = 0; i < $zh.length; ++i) {
            this.$VQB.addItem($zh[i]);
        }
        if ($rrb != null) {
            this.$VQB.addItem($rrb);
        }
        this.$YH = $yh;
        this.$ZH = $zh;
        this.$SRB = $srb;
        this.$RRB = $rrb;
        this.setColor(this.$PRB);
        this.$VQB.addItemListener(this);
        this.add("Center", this.$VQB);
        this.validate();
        this.repaint();
    }
}
