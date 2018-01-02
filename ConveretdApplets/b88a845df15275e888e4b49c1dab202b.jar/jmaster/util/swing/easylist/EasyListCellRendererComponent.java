// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.easylist;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Icon;
import jmaster.util.swing.icon.LinkedIcon;
import java.awt.Rectangle;
import jmaster.util.log.B;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import jmaster.util.swing.icon.TextIcon;
import jmaster.util.swing.icon.ResizingIcon;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;

public abstract class EasyListCellRendererComponent
{
    protected A F;
    protected GUIHelper M;
    protected EasyList S;
    protected Object L;
    protected int E;
    protected boolean K;
    protected boolean H;
    protected ListCellRenderer I;
    protected Component G;
    protected ResizingIcon R;
    protected TextIcon Q;
    protected boolean B;
    protected String N;
    protected boolean D;
    protected boolean J;
    protected float A;
    protected float T;
    protected Cursor C;
    protected String O;
    protected MouseEvent P;
    
    public EasyListCellRendererComponent() {
        this.F = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.M = GUIHelper.getInstance();
        this.R = new ResizingIcon();
        this.B = true;
        this.N = null;
        this.D = true;
        this.J = false;
        this.A = 1.0f;
        this.T = 1.0f;
        this.C = null;
        this.O = null;
    }
    
    public EasyList getList() {
        return this.S;
    }
    
    public void setList(final EasyList s) {
        this.S = s;
    }
    
    public Object getValue() {
        return this.L;
    }
    
    public void setValue(final Object l) {
        this.L = l;
    }
    
    public boolean isFocused() {
        return this.H;
    }
    
    public void setFocused(final boolean h) {
        this.H = h;
    }
    
    public int getIndex() {
        return this.E;
    }
    
    public void setIndex(final int e) {
        this.E = e;
    }
    
    public boolean isSelected() {
        return this.K;
    }
    
    public void setSelected(final boolean k) {
        this.K = k;
    }
    
    public float getAlpha() {
        return this.R.getAlpha();
    }
    
    public String getAnchor() {
        return this.R.getAnchor();
    }
    
    public int getHeight() {
        return this.R.getHeight();
    }
    
    public int getHorizontalSpace() {
        return this.R.getHorizontalSpace();
    }
    
    public int getIconHeight() {
        return this.R.getIconHeight();
    }
    
    public Rectangle getIconRect(final Component component, final Rectangle rectangle) {
        return this.R.getIconRect(component, rectangle);
    }
    
    public Rectangle getIconRect(final int n, final int n2, final Rectangle rectangle) {
        return this.R.getIconRect(n, n2, rectangle);
    }
    
    public int getIconWidth() {
        return this.R.getIconWidth();
    }
    
    public LinkedIcon getLinkedIcon() {
        return this.R.getLinkedIcon();
    }
    
    public int getVerticalSpace() {
        return this.R.getVerticalSpace();
    }
    
    public int getWidth() {
        return this.R.getWidth();
    }
    
    public void setAlpha(final float alpha) {
        this.R.setAlpha(alpha);
    }
    
    public void setAnchor(final String anchor) {
        this.R.setAnchor(anchor);
    }
    
    public void setHeight(final int height) {
        this.R.setHeight(height);
    }
    
    public void setHorizontalSpace(final int horizontalSpace) {
        this.R.setHorizontalSpace(horizontalSpace);
    }
    
    public void setSize(final int n, final int n2) {
        this.R.setSize(n, n2);
    }
    
    public void setVerticalSpace(final int verticalSpace) {
        this.R.setVerticalSpace(verticalSpace);
    }
    
    public void setWidth(final int width) {
        this.R.setWidth(width);
    }
    
    public Icon getIcon() {
        return this.R.getLinkedIcon().getDelegate();
    }
    
    public void setIcon(final Icon delegate) {
        this.R.getLinkedIcon().setDelegate(delegate);
    }
    
    public boolean isVisible() {
        return this.B;
    }
    
    public void setVisible(final boolean b) {
        this.B = b;
    }
    
    public ListCellRenderer getListCellRenderer() {
        return this.I;
    }
    
    public void setListCellRenderer(final ListCellRenderer i) {
        this.I = i;
    }
    
    public Component getListCellRendererComponent() {
        return this.G;
    }
    
    public void setListCellRendererComponent(final Component g) {
        this.G = g;
    }
    
    public boolean isActionEnabled() {
        return this.D;
    }
    
    public void setActionEnabled(final boolean d) {
        this.D = d;
    }
    
    public String getActionId() {
        return this.N;
    }
    
    public void setActionId(final String n) {
        this.N = n;
    }
    
    public Cursor getCursor() {
        return this.C;
    }
    
    public void setCursor(final Cursor c) {
        this.C = c;
    }
    
    public String getToolTipText() {
        return this.O;
    }
    
    public void setToolTipText(final String o) {
        this.O = o;
    }
    
    public Color getColor() {
        return this.Q.getColor();
    }
    
    public Font getFont() {
        return this.Q.getFont();
    }
    
    public int getHalign() {
        return this.Q.getHalign();
    }
    
    public String getText() {
        return this.Q.getText();
    }
    
    public int getValign() {
        return this.Q.getValign();
    }
    
    public boolean isAntialiasing() {
        return this.Q.isAntialiasing();
    }
    
    public boolean isRenderFrame() {
        return this.Q.isRenderFrame();
    }
    
    public void setAntialiasing(final boolean antialiasing) {
        this.Q.setAntialiasing(antialiasing);
    }
    
    public void setColor(final Color color) {
        this.Q.setColor(color);
    }
    
    public void setFont(final Font font) {
        this.Q.setFont(font);
    }
    
    public void setHalign(final int halign) {
        this.Q.setHalign(halign);
    }
    
    public void setRenderFrame(final boolean renderFrame) {
        this.Q.setRenderFrame(renderFrame);
    }
    
    public void setText(final String text) {
        this.Q.setText(text);
    }
    
    public void setValign(final int valign) {
        this.Q.setValign(valign);
    }
    
    public boolean isActionHover() {
        return this.J;
    }
    
    public void setActionHover(final boolean j) {
        this.J = j;
    }
    
    public float getAlphaDefault() {
        return this.A;
    }
    
    public void setAlphaDefault(final float a) {
        this.A = a;
    }
    
    public float getAlphaHover() {
        return this.T;
    }
    
    public void setAlphaHover(final float t) {
        this.T = t;
    }
    
    public void prepare() {
        this.setActionHover(this.isActionEnabled() && this.equals(this.S.getActiveComponent()) && this.L.equals(this.S.getActiveItem()));
        if (this.isActionEnabled()) {
            this.setAlpha(this.isActionHover() ? this.getAlphaHover() : this.getAlphaDefault());
        }
    }
    
    public void render(final Component component, final Graphics graphics) {
        if (this.B) {
            this.R.paintIcon(component, graphics);
        }
    }
    
    public void handleMouseEvent(final MouseEvent p6, final EasyList list, final int n, final Rectangle rectangle, final Point point, final ListCellRenderer listCellRenderer) {
        this.P = p6;
        if (this.isActionEnabled()) {
            if (p6.getID() == 505) {
                this.setActionHover(false);
            }
            else {
                final boolean contains = this.R.getIconRect((Component)listCellRenderer, null).contains(point);
                this.setActionHover(contains);
                if (contains && p6.getID() == 500) {
                    this.A();
                }
            }
        }
    }
    
    protected void A() {
    }
}
