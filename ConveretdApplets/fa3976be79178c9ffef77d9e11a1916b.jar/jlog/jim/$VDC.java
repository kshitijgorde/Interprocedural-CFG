// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeEvent;
import java.awt.Component;
import jlog.$T5.$D7.ContainerSupport;
import java.awt.Color;
import jlog.awt.$W5;
import java.awt.Font;
import jlog.$H4;
import jlog.$T5.$D7.$WDC;
import java.beans.PropertyChangeListener;
import jlog.$T5.$D7.$XDC.$YDC;

public class $VDC extends $YDC implements PropertyChangeListener, $WDC, $H4
{
    private $F8B $ZDC;
    private $BBC $CBC;
    static Font $FF;
    
    public synchronized void $AEC(final $F8B $zdc) {
        if (this.$ZDC != null) {
            this.$ZDC.removePropertyChangeListener(this);
        }
        this.$ZDC = $zdc;
        if (this.$ZDC != null) {
            super.name = this.$ZDC.id;
            this.$CBC = this.$ZDC.$CBC;
            this.setImage(this.$ZDC.$BEC());
            super.$CEC = ((this.getImage() != null && this.getImage().getImage() != null) ^ true);
            this.setBackground(this.$ZDC.$YH);
            this.$ZDC.addPropertyChangeListener(this, false);
        }
        else {
            super.name = "*";
            this.setImage(null);
            this.setBackground(null);
            super.$CEC = true;
            this.$CBC = null;
        }
        this.$DEC();
    }
    
    private void $DEC() {
        if (!this.isShowing()) {
            return;
        }
        if (this.getParent() != null) {
            ContainerSupport.doLayout(this);
            this.repaint();
        }
    }
    
    public int $EEC() {
        return 2;
    }
    
    static {
        $VDC.$FF = null;
    }
    
    public $VDC(final $F8B $f8B) {
        super(($W5)null);
        this.$ZDC = null;
        this.$CBC = null;
        this.setForeground(Color.black);
        this.$AEC($f8B);
    }
    
    public boolean contains(final int n, final int n2) {
        return (this.$CBC == null || this.$CBC.getMode() == 0) && super.contains(n, n2);
    }
    
    public Font getFont() {
        if ($VDC.$FF != null) {
            return $VDC.$FF;
        }
        return super.getFont();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (newValue instanceof Boolean) {
            (boolean)newValue;
        }
        if (propertyChangeEvent.getSource() != this.$ZDC) {
            return;
        }
        if (propertyName.equals("id")) {
            super.name = (String)newValue;
            this.$DEC();
        }
        else if (propertyName.equals("map")) {
            this.$CBC = ($BBC)newValue;
        }
        else if (propertyName.equals("icon")) {
            this.setImage(this.$ZDC.$BEC());
            super.$CEC = ((this.getImage() != null && this.getImage().getImage() != null) ^ true);
            this.$DEC();
        }
        else if (propertyName.equals("color")) {
            this.setBackground((Color)newValue);
            this.repaint();
        }
    }
    
    public void setFont(final Font $ff) {
        $VDC.$FF = $ff;
    }
}
