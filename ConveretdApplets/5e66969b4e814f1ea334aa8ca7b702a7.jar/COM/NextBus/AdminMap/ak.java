// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.MouseEvent;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import COM.NextBus.Predictor2.rmi.StopTupleBean;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.awt.geom.GeneralPath;
import COM.NextBus.Predictor2Comm.Location;
import COM.NextBus.HttpMapClient.e;
import java.util.Iterator;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import COM.NextBus.a.a;
import java.awt.Cursor;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Dimension;
import java.util.Set;
import java.awt.Point;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedHashMap;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Panel;

final class ak implements Runnable
{
    private /* synthetic */ MapCanvas a;
    
    ak(final MapCanvas a) {
        this.a = a;
    }
    
    public final void run() {
        while (true) {
            this.a.i();
            if (this.a._mapInfo.O() && this.a._mapInfo.d != null) {
                this.a._mapInfo.b.f();
            }
            try {
                long n = 100L;
                if (this.a._mapInfo.d != null && this.a._mapInfo.d.f() != 0) {
                    n /= this.a._mapInfo.d.f();
                }
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
}
