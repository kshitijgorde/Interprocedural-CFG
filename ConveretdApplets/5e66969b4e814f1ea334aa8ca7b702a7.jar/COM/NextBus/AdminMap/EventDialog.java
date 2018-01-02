// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextArea;
import COM.NextBus.Applets.d;
import java.awt.Frame;
import java.awt.Component;

public class EventDialog extends DialogCommon
{
    private static final long serialVersionUID = -6979089259503540689L;
    private final O _mapInfo;
    protected final t _agencyManager;
    private final Component _component;
    private boolean populated;
    private boolean refreshedOnce;
    
    public EventDialog(final Frame frame, final String s, final Component component, final O mapInfo, final t agencyManager) {
        super(frame, s, component, mapInfo);
        this.populated = false;
        this.refreshedOnce = false;
        this._agencyManager = agencyManager;
        this._mapInfo = mapInfo;
        this._component = new TextArea();
    }
    
    public final void e() {
        if (!this.populated) {
            if (this._mapInfo.g == Integer.MIN_VALUE) {
                this._mapInfo.g = this._mapInfo.w().size() * -1 / 2;
            }
            this.populated = true;
            this.a();
            final Point locationOnScreen = this._parent.getLocationOnScreen();
            final Dimension size = this._parent.getSize();
            this.setLocation(locationOnScreen.x + size.width / 2 - 50 + this._mapInfo.g * 20, locationOnScreen.y + (int)(size.height / 2.6) - 30 + this._mapInfo.g * 20);
            final O mapInfo = this._mapInfo;
            ++mapInfo.g;
        }
    }
    
    public final void a() {
        this.add(this.b());
        new Vector();
        this.f();
        this.pack();
    }
    
    public Component b() {
        return this._component;
    }
    
    final void f() {
        if (!this.refreshedOnce) {
            this.refreshedOnce = true;
            final TextArea textArea;
            (textArea = (TextArea)this.b()).setEditable(false);
            textArea.setText("To view events you must update your browser's Java\nimplementation to the current Java plug-in.  The plug-in\nis available at:\n\n\thttp://java.sun.com/getjava\n\nClicking in this window will open the above page in\nanother browser window.\n");
            textArea.setForeground(Color.blue);
            textArea.setBackground(Color.white);
            textArea.addMouseListener(new S(this));
        }
    }
    
    public void c() {
    }
}
