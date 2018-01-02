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
import COM.NextBus.a.a;
import java.awt.Cursor;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Dimension;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedHashMap;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Panel;
import java.awt.Image;
import java.awt.Color;
import java.text.NumberFormat;
import java.awt.Point;

final class as implements aq
{
    private /* synthetic */ MapCanvas a;
    
    as(final MapCanvas a) {
        this.a = a;
    }
    
    public final String[] f() {
        final String[] array = { null };
        final Point a;
        final double n = (a = this.a.a(new Point(this.a._mouseX, this.a._mouseY))).x / 6000000.0;
        final double n2 = a.y / 6000000.0;
        final NumberFormat instance;
        (instance = NumberFormat.getInstance()).setGroupingUsed(false);
        instance.setMaximumFractionDigits(5);
        instance.setMinimumFractionDigits(5);
        instance.setMaximumIntegerDigits(3);
        instance.setMinimumIntegerDigits(1);
        final NumberFormat numberFormat = instance;
        array[0] = "Lat/Lon of Mouse: (" + numberFormat.format(n) + ", " + numberFormat.format(n2) + ")";
        return array;
    }
    
    public final String i() {
        return "LatLong";
    }
    
    public final Color g() {
        return Color.black;
    }
    
    public final Color h() {
        return Color.white;
    }
    
    public final Image j() {
        return null;
    }
}
