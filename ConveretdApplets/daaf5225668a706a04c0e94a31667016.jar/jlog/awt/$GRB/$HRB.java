// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$GRB;

import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import jlog.awt.$Z7.$HBB;
import java.awt.Insets;
import java.awt.Polygon;
import jlog.awt.$Z7.$ICB;
import java.awt.Adjustable;
import java.awt.event.ActionListener;
import java.awt.Container;

public class $HRB extends Container implements ActionListener, Adjustable
{
    $SUB $TUB;
    public static final String $UUB = "LINE_UP_ACTION";
    public static final String $VUB = "LINE_DOWN_ACTION";
    $ICB $WUB;
    $ICB $XUB;
    private static int[] $YUB;
    private static int[] $ZUB;
    public static final Polygon $AVB;
    private static int[] $BVB;
    private static int[] $CVB;
    public static final Polygon $DVB;
    private static int[] $EVB;
    private static int[] $FVB;
    public static final Polygon $GVB;
    private static int[] $HVB;
    private static int[] $IVB;
    public static final Polygon $JVB;
    private static Insets insets;
    
    void $KVB(final int n) {
        final $HBB $hbb = new $HBB();
        $hbb.$JBB = true;
        (this.$WUB = new $ICB("", "LINE_UP_ACTION", $hbb)).addActionListener(this);
        (this.$XUB = new $ICB("", "LINE_DOWN_ACTION", $hbb)).addActionListener(this);
        this.$LVB(n);
    }
    
    void $LVB(final int n) {
        if (n == 0) {
            this.$WUB.$JCB($HRB.$GVB, true);
            this.$XUB.$JCB($HRB.$JVB, true);
        }
        else {
            this.$WUB.$JCB($HRB.$AVB, true);
            this.$XUB.$JCB($HRB.$DVB, true);
        }
    }
    
    public void $NRB(final Color color) {
        this.$TUB.$NRB(color);
    }
    
    public void $TVB(final int n, final int n2) {
        this.$TUB.$TVB(n, n2);
    }
    
    static {
        $HRB.$YUB = new int[] { 4, 8, 0, 4 };
        $HRB.$ZUB = new int[] { 0, 8, 8, 0 };
        $AVB = new Polygon($HRB.$YUB, $HRB.$ZUB, 4);
        $HRB.$BVB = new int[] { 0, 8, 4, 0 };
        $HRB.$CVB = new int[] { 0, 0, 8, 0 };
        $DVB = new Polygon($HRB.$BVB, $HRB.$CVB, 4);
        $HRB.$EVB = new int[] { 0, 8, 8, 0 };
        $HRB.$FVB = new int[] { 4, 0, 8, 4 };
        $GVB = new Polygon($HRB.$EVB, $HRB.$FVB, 4);
        $HRB.$HVB = new int[] { 0, 8, 0, 0 };
        $HRB.$IVB = new int[] { 0, 4, 8, 0 };
        $JVB = new Polygon($HRB.$HVB, $HRB.$IVB, 4);
        $HRB.insets = new Insets(2, 4, 2, 4);
    }
    
    public $HRB(final int n) {
        (this.$TUB = new $SUB(n)).setSource(this);
        this.$KVB(n);
        this.setLayout(new BorderLayout());
        this.add("Center", this.$TUB);
        this.setUnitIncrement(1);
    }
    
    public $HRB(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n);
        this.setValues(n2, n3, n4, n5);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("LINE_UP_ACTION")) {
            this.$TUB.$UVB(this.getValue() - this.getUnitIncrement());
        }
        else if (actionCommand.equals("LINE_DOWN_ACTION")) {
            this.$TUB.$UVB(this.getValue() + this.getUnitIncrement());
        }
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.$TUB.addAdjustmentListener(adjustmentListener);
    }
    
    public int getBlockIncrement() {
        return this.$TUB.getBlockIncrement();
    }
    
    public int getMaximum() {
        return this.$TUB.getMaximum();
    }
    
    public int getMinimum() {
        return this.$TUB.getMinimum();
    }
    
    public int getOrientation() {
        return this.$TUB.getOrientation();
    }
    
    public String getText() {
        return this.$TUB.getText();
    }
    
    public int getUnitIncrement() {
        return this.$TUB.getUnitIncrement();
    }
    
    public int getValue() {
        return this.$TUB.getValue();
    }
    
    public int getVisibleAmount() {
        return this.$TUB.getVisibleAmount();
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.$TUB.removeAdjustmentListener(adjustmentListener);
    }
    
    public void setBlockIncrement(final int blockIncrement) {
        this.$TUB.setBlockIncrement(blockIncrement);
    }
    
    public void setMaximum(final int maximum) {
        this.$TUB.setMaximum(maximum);
    }
    
    public void setMinimum(final int minimum) {
        this.$TUB.setMinimum(minimum);
    }
    
    public void setText(final String text) {
        this.$TUB.setText(text);
    }
    
    public void setUnitIncrement(final int unitIncrement) {
        this.$TUB.setUnitIncrement(unitIncrement);
        final boolean b = unitIncrement != 0;
        if (b != (this.$WUB.getParent() != null)) {
            if (b) {
                if (this.getOrientation() == 0) {
                    this.add("West", this.$WUB);
                    this.add("East", this.$XUB);
                }
                else {
                    this.add("North", this.$WUB);
                    this.add("South", this.$XUB);
                }
                this.$WUB.invalidate();
                this.$XUB.invalidate();
            }
            else {
                this.remove(this.$WUB);
                this.remove(this.$XUB);
            }
            this.validate();
            this.repaint();
        }
    }
    
    public void setValue(final int value) {
        this.$TUB.setValue(value);
    }
    
    public void setValues(final int n, final int n2, final int n3, final int n4) {
        this.$TUB.setValues(n, n2, n3, n4);
    }
    
    public void setVisibleAmount(final int visibleAmount) {
        this.$TUB.setVisibleAmount(visibleAmount);
    }
}
