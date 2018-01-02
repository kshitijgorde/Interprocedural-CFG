// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$C0;

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Container;

public class $NAB extends Container
{
    Vector $EAB;
    Vector $FAB;
    
    public void $GAB(final $CAB $cab) {
        if (this.$EAB == null) {
            this.$EAB = new Vector();
        }
        this.$EAB.addElement($cab);
    }
    
    public void $HAB(final $CAB $cab) {
        if (this.$EAB != null) {
            this.$EAB.removeElement($cab);
        }
    }
    
    public void $IAB(final $CAB $cab) {
        if (this.$FAB == null) {
            this.$FAB = new Vector();
        }
        this.$FAB.addElement($cab);
    }
    
    public void $JAB(final $CAB $cab) {
        if (this.$FAB != null) {
            this.$FAB.removeElement($cab);
        }
    }
    
    public void $KAB(Graphics create) {
        create = create.create();
        try {
            if (this.$EAB != null && this.$EAB.size() != 0) {
                this.$OAB(this.$EAB, create);
                return;
            }
            for (Container container = this.getParent(); container != null; container = container.getParent()) {
                if (container instanceof $NAB) {
                    final $NAB $nab = ($NAB)container;
                    if ($nab.$EAB != null && $nab.$EAB.size() > 0) {
                        $nab.$KAB(create);
                        return;
                    }
                }
            }
            final Rectangle clipRect = create.getClipRect();
            create.setColor(this.getBackground());
            create.fillRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
        }
        finally {
            create.dispose();
        }
    }
    
    public void $LAB(final Graphics graphics) {
        this.$OAB(this.$FAB, graphics);
    }
    
    void $OAB(final Vector vector, final Graphics graphics) {
        for (int n = 0; vector != null && n < vector.size(); ++n) {
            final Graphics create = graphics.create();
            try {
                vector.elementAt(n).paint(create, this);
            }
            finally {
                create.dispose();
            }
        }
    }
    
    public $NAB() {
        this.$EAB = null;
        this.$FAB = null;
    }
    
    public $NAB(final LayoutManager layout) {
        this.$EAB = null;
        this.$FAB = null;
        this.setLayout(layout);
    }
    
    public void paint(Graphics create) {
        create = create.create();
        try {
            this.$KAB(create);
            super.paint(create);
            this.$LAB(create);
        }
        finally {
            create.dispose();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
