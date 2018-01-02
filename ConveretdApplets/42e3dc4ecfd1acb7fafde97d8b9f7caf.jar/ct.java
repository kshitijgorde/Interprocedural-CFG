import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.Container;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ct implements AdjustmentListener
{
    private final /* synthetic */ cs a;
    
    public ct(final cs a) {
        this.a = a;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.a.b(adjustmentEvent.getValue());
    }
}
