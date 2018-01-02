// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

public class $CRB extends Panel implements $WWB, $BXB
{
    $DXB $EXB;
    
    public void $XHB(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public Dimension $YHB() {
        return this.size();
    }
    
    public $CRB() {
        this(new FlowLayout());
    }
    
    public $CRB(final LayoutManager layout) {
        this.$EXB = new $DXB(this, this);
        this.setLayout(layout);
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.$EXB.flush();
    }
    
    public void paint(final Graphics graphics) {
        this.$EXB.paint(graphics);
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.$EXB.flush();
    }
    
    public void update(final Graphics graphics) {
        this.$EXB.update(graphics);
    }
}
