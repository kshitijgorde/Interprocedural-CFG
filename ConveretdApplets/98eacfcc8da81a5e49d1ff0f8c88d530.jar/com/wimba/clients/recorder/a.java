// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import javax.swing.BoundedRangeModel;
import VT_6_1_0_11.F;
import java.awt.event.MouseListener;
import VT_6_1_0_11.bs;

final class a extends bs implements MouseListener
{
    private F a;
    private final c b;
    
    public a(final c b, final BoundedRangeModel boundedRangeModel) {
        this.b = b;
        super(boundedRangeModel, 1);
        (this.a = new F("/images/recorder/volume_slider_min.png", "/images/recorder/volume_slider_max.png", this.b())).b(1);
        this.addMouseListener(this);
    }
    
    protected final void a(final Graphics graphics) {
        graphics.translate(8, 2);
        this.a.paintComponent(graphics);
        graphics.translate(-8, -2);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            com.wimba.clients.recorder.c.a(this.b, true);
            com.wimba.clients.recorder.c.a(this.b).stop();
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            com.wimba.clients.recorder.c.a(this.b, false);
            if (!com.wimba.clients.recorder.c.b(this.b)) {
                com.wimba.clients.recorder.c.a(this.b).restart();
            }
        }
    }
}
