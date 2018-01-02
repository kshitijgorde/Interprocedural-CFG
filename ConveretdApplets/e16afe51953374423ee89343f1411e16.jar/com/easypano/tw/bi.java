// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bi extends MouseMotionAdapter
{
    final /* synthetic */ bp a;
    
    bi(final bp a) {
        this.a = a;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point location = this.a.getLocation();
        final Point point = mouseEvent.getPoint();
        this.a.setLocation(location.x + point.x - bp.a(this.a).x, location.y + point.y - bp.a(this.a).y);
    }
}
