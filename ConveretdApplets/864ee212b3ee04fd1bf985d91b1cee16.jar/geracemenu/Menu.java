// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import sun.misc.Timeable;
import java.awt.Insets;
import geracemenu.parser.BoolValue;
import java.net.MalformedURLException;
import java.net.URL;
import geracemenu.parser.StringValue;
import geracemenu.parser.Type;
import java.util.Hashtable;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;
import sun.misc.Timer;
import java.awt.Color;
import geracemenu.util.VArray;

public class Menu extends MenuItem
{
    private static final long POPUP_DISPLAY_DELAY = 400L;
    private static /* synthetic */ Class class$Lgeracemenu$MenuItem;
    private VArray items;
    private transient PopupMenu popup;
    private transient Color popupBack;
    private transient boolean backIs3d;
    protected VImage pImage;
    private Timer popupTimer;
    protected Color subMenuRectUnselected;
    protected Color subMenuRectSelected;
    
    public final MenuItem[] getChildren() {
        return (MenuItem[])this.items.getTrimmedArray();
    }
    
    public final Timer getPopupTimer() {
        return this.popupTimer;
    }
    
    public Menu append(final MenuItem menuItem) {
        this.items.append(menuItem);
        return (Menu)(menuItem.parentItem = this);
    }
    
    public boolean isClickable() {
        return false;
    }
    
    public void setState(final boolean state) {
        super.setState(state);
        if (this.popup == null) {
            final MenuItem[] children = this.getChildren();
            if (children != null) {
                this.popup = new PopupMenu(this.getTopLevelFrame(), children, this.popupBack, this.backIs3d);
            }
        }
        if (state) {
            this.popupTimer.setRemainingTime(400L);
            this.popupTimer.cont();
        }
        else {
            this.popupTimer.stop();
            this.hidePopup();
        }
    }
    
    public void showPopup() {
        if (this.popup != null && !this.popup.isVisible()) {
            this.popup.show(this, this.getWidth(), 0);
        }
    }
    
    public void hidePopup() {
        if (this.popup != null) {
            this.popup.setVisible(false);
            this.popup.clearItemsSelection();
        }
    }
    
    public PopupMenu getPopup() {
        return this.popup;
    }
    
    public void paintItem(final Graphics graphics) {
        this.getLayout().paint(graphics, new Point(0, 0));
        this.paintSubMenuPointer(graphics);
    }
    
    protected void paintSubMenuPointer(final Graphics graphics) {
        final int n = this.getHeight() / 2;
        final int width = this.getWidth();
        if (this.pImage != null) {
            this.pImage.paint(graphics, new Point(width - this.pImage.getSize().width - 5, (this.getHeight() - this.pImage.getSize().height) / 2));
        }
        else {
            final Polygon polygon = new Polygon(new int[] { width - 14, width - 14, width - 10 }, new int[] { n - 4, n + 4, n }, 3);
            if (this.getState()) {
                graphics.setColor(this.subMenuRectSelected);
            }
            else {
                graphics.setColor(this.subMenuRectUnselected);
            }
            graphics.fillPolygon(polygon);
        }
    }
    
    /* synthetic */ Timer access$0() {
        return this.popupTimer;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public Menu(final String s) {
        this(s, (Hashtable)null);
    }
    
    public Menu(final String s, final Hashtable hashtable) {
        super(s, null, hashtable);
        this.items = new VArray((Menu.class$Lgeracemenu$MenuItem != null) ? Menu.class$Lgeracemenu$MenuItem : (Menu.class$Lgeracemenu$MenuItem = class$("geracemenu.MenuItem")));
        this.popup = null;
        this.popupBack = Color.lightGray;
        this.backIs3d = true;
        this.pImage = null;
        this.subMenuRectUnselected = Color.black;
        this.subMenuRectSelected = Color.white;
        if (hashtable != null) {
            final Type type;
            if ((type = hashtable.get("backgroundColor")) != null) {
                this.popupBack = GuiUtil.findColor((String)((StringValue)type).getValue(), this.popupBack);
            }
            final Type type2;
            if ((type2 = hashtable.get("pointerActiveColor")) != null) {
                this.subMenuRectSelected = GuiUtil.findColor((String)((StringValue)type2).getValue(), this.subMenuRectSelected);
            }
            final Type type3;
            if ((type3 = hashtable.get("pointerInactiveColor")) != null) {
                this.subMenuRectUnselected = GuiUtil.findColor((String)((StringValue)type3).getValue(), this.subMenuRectUnselected);
            }
            final Type type4;
            if ((type4 = hashtable.get("pointerImage")) != null) {
                try {
                    this.pImage = new VImage(new URL(parseMenuApplet.URLspec + (String)((StringValue)type4).getValue()));
                }
                catch (MalformedURLException ex) {}
            }
            final Type type5;
            if ((type5 = hashtable.get("backgroundEnable3d")) != null) {
                this.backIs3d = (boolean)((BoolValue)type5).getValue();
            }
        }
        super.insets = new Insets(5, 3, 5, (this.pImage == null) ? 21 : (this.pImage.getSize().width + 10));
        if (this == null) {
            throw null;
        }
        this.popupTimer = new Timer(new PopupDisplayPause(), 400L);
    }
    
    class PopupDisplayPause implements Timeable
    {
        public void tick(final Timer timer) {
            Menu.this.showPopup();
            Menu.this.access$0().stop();
        }
    }
}
