// 
// Decompiled by Procyon v0.5.30
// 

package org.utils;

import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.awt.AWTEvent;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import java.util.ArrayList;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;

public class CustomOrbitBehavior extends OrbitBehavior
{
    ArrayList<Transform3D> tList;
    
    public CustomOrbitBehavior(final Canvas3D c) {
        super(c);
    }
    
    public void addTransform(final Transform3D t3d) {
        if (this.tList == null) {
            this.tList = new ArrayList<Transform3D>();
        }
        this.tList.add(t3d);
    }
    
    protected synchronized void processAWTEvents(final AWTEvent[] arg0) {
        super.processAWTEvents(arg0);
        if (this.tList == null) {
            return;
        }
        final Transform3D home = new Transform3D();
        this.getHomeTransform(home);
        for (final Transform3D item : this.tList) {
            item.add(home);
        }
    }
    
    protected void processMouseEvent(final MouseEvent arg0) {
        super.processMouseEvent(arg0);
        if (this.tList == null) {
            return;
        }
        final Transform3D home = new Transform3D();
        this.getHomeTransform(home);
        for (final Transform3D item : this.tList) {
            item.add(home);
        }
    }
}
