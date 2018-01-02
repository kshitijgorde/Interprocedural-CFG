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
import java.awt.event.AdjustmentListener;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.Container;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class cu extends ComponentAdapter
{
    private final /* synthetic */ cs a;
    
    public cu(final cs a) {
        this.a = a;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.e();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.a.ab = false;
        this.a.repaint();
    }
}
