// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import geracemenu.parser.BoolValue;
import geracemenu.parser.IntValue;
import geracemenu.parser.StringValue;
import geracemenu.parser.Type;
import java.awt.Insets;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import geracemenu.richtext.ClickableTextActionEvent;
import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.Component;
import geracemenu.richtext.TextLayout;
import java.awt.Color;
import geracemenu.richtext.ClickableTextAction;

public class MenuItem extends TTComponent implements MenuItemModel
{
    private static final String ITEM = "item_rich_text";
    private transient ClickableTextAction actionCache;
    private boolean state;
    private boolean selectable;
    private MenuItemController controller;
    private Color selection;
    private float transparency;
    private LinkActionListener actionListener;
    private TextLayout layout;
    protected MenuItem parentItem;
    
    public MenuItem getParentItem() {
        return this.parentItem;
    }
    
    public boolean hasParentItem() {
        return this.parentItem != null;
    }
    
    public ClickableTextAction getClickableTextAction() {
        return this.layout.getClickableTextAction();
    }
    
    public boolean isClickable() {
        return this.layout.isClickable();
    }
    
    public String getItemText() {
        return this.layout.getRichText().toString();
    }
    
    public void setItemText(final String s) {
        this.layout = new TextLayout(this, s);
    }
    
    public MenuItemController getController() {
        return this.controller;
    }
    
    public MenuItem[] getChildren() {
        return null;
    }
    
    public final TextLayout getLayout() {
        return this.layout;
    }
    
    public boolean isSelectable() {
        return this.selectable;
    }
    
    public void setState(final boolean state) {
        if (!this.isSelectable()) {
            return;
        }
        if (state) {
            final ClickableTextAction clickableTextAction = this.getClickableTextAction();
            if (clickableTextAction != null && this.actionListener != null) {
                this.actionListener.actionPerformed(new LinkActionEvent(this, 2001, clickableTextAction.getActionCommand(), clickableTextAction.getActionTitle()));
            }
        }
        final boolean b = this.state != state;
        this.state = state;
        if (b) {
            this.repaintNow();
        }
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public synchronized void addActionListener(final LinkActionListener linkActionListener) {
        if (linkActionListener == null) {
            throw new NullPointerException("MenuItem, addActionListener(): null l");
        }
        this.actionListener = (LinkActionListener)AWTEventMulticaster.add(this.actionListener, linkActionListener);
        this.enableEvents(0L);
    }
    
    public synchronized void removeActionListener(final LinkActionListener linkActionListener) {
        if (linkActionListener == null) {
            throw new NullPointerException("MenuItem, removeActionListener(): null l");
        }
        this.actionListener = (LinkActionListener)AWTEventMulticaster.remove(this.actionListener, linkActionListener);
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        if (awtEvent instanceof ClickableTextActionEvent && this.actionListener != null) {
            final ClickableTextAction clickableTextAction = this.getClickableTextAction();
            if (clickableTextAction != null) {
                this.actionListener.actionPerformed(new LinkActionEvent(this, 2000, clickableTextAction.getActionCommand(), clickableTextAction.getActionTitle(), clickableTextAction.getActionTarget()));
            }
            return;
        }
        super.processEvent(awtEvent);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        final Dimension size = this.layout.getSize();
        return new Dimension(super.insets.left + super.insets.right + size.width, super.insets.top + super.insets.bottom + size.height);
    }
    
    protected synchronized void paintOn(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (this.getState() && this.isSelectable() && this.transparency >= 1.0f) {
            graphics.setColor(this.selection);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        this.paintItem(graphics);
    }
    
    public void paintItem(final Graphics graphics) {
        this.layout.paint(graphics, new Point(0, 0));
    }
    
    protected void filterOffscreenSource() {
        if (this.getState() && this.isSelectable() && this.transparency < 1.0f && super.offScreenBuffer != null) {
            super.offScreenBuffer = GuiUtil.filterImage(this, new TransparencyFilter(super.offScreenBuffer.getSource(), this.selection, this.transparency));
        }
    }
    
    public String toString() {
        final String itemText = this.getItemText();
        return "This is MenuItem [" + ((itemText == null) ? "no item text" : itemText) + ']';
    }
    
    public MenuItem() {
        this(null, null, null);
    }
    
    public MenuItem(final String s) {
        this(s, null, null);
    }
    
    public MenuItem(final Hashtable hashtable) {
        this(null, null, hashtable);
    }
    
    public MenuItem(final String s, final Hashtable hashtable) {
        this(s, null, hashtable);
    }
    
    public MenuItem(final String s, final LinkActionListener linkActionListener) {
        this(s, linkActionListener, null);
    }
    
    public MenuItem(final String s, final LinkActionListener linkActionListener, final Hashtable hashtable) {
        this(s, linkActionListener, hashtable, new Insets(5, 3, 5, 3));
    }
    
    public MenuItem(final String itemText, final LinkActionListener linkActionListener, final Hashtable hashtable, final Insets insets) {
        super(insets);
        this.actionCache = null;
        this.state = false;
        this.selectable = true;
        this.controller = null;
        this.selection = Color.gray;
        this.transparency = 1.0f;
        this.actionListener = null;
        this.parentItem = null;
        this.controller = new MenuItemController(this);
        if (itemText != null) {
            this.setItemText(itemText);
        }
        if (linkActionListener != null) {
            this.addActionListener(linkActionListener);
        }
        if (hashtable != null) {
            final Type type;
            if ((type = hashtable.get("selectionColor")) != null) {
                this.selection = GuiUtil.findColor((String)((StringValue)type).getValue(), this.selection);
            }
            final Type type2;
            if ((type2 = hashtable.get("transparency")) != null) {
                this.transparency = (int)((IntValue)type2).getValue() / 100.0f;
                if (this.transparency < 1.0f) {
                    this.setFiltered(true);
                }
            }
            final Type type3;
            if ((type3 = hashtable.get("selectable")) != null) {
                this.selectable = (boolean)((BoolValue)type3).getValue();
            }
        }
    }
}
