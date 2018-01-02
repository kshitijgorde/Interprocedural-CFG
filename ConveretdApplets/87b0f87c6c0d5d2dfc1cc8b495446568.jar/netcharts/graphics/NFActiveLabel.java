// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFUtil;
import netcharts.util.NFParam;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import netcharts.util.NFDebug;
import netcharts.util.NFJavaScriptUtil;
import java.applet.AppletContext;
import java.util.Vector;
import java.awt.Polygon;
import java.net.URL;

public class NFActiveLabel
{
    private static final boolean a = false;
    public boolean autoLabel;
    public String label;
    public URL url;
    public String target;
    public int xmin;
    public int ymin;
    public int xmax;
    public int ymax;
    public Polygon areaPoly;
    public int outlineThickness;
    public int outlineStyle;
    public NFArrow outlineArrow;
    public String selectedItemParam;
    public int selectedItemIndex;
    public Vector selectedItemLabels;
    
    public NFActiveLabel() {
        this.autoLabel = false;
        this.label = null;
        this.url = null;
        this.target = null;
        this.xmin = Integer.MIN_VALUE;
        this.ymin = Integer.MIN_VALUE;
        this.xmax = Integer.MIN_VALUE;
        this.ymax = Integer.MIN_VALUE;
        this.areaPoly = null;
        this.outlineThickness = 2;
        this.outlineStyle = 1;
        this.outlineArrow = null;
        this.selectedItemParam = null;
        this.selectedItemIndex = -1;
        this.selectedItemLabels = null;
    }
    
    public NFActiveLabel(final String label, final URL url, final String target) {
        this.autoLabel = false;
        this.label = null;
        this.url = null;
        this.target = null;
        this.xmin = Integer.MIN_VALUE;
        this.ymin = Integer.MIN_VALUE;
        this.xmax = Integer.MIN_VALUE;
        this.ymax = Integer.MIN_VALUE;
        this.areaPoly = null;
        this.outlineThickness = 2;
        this.outlineStyle = 1;
        this.outlineArrow = null;
        this.selectedItemParam = null;
        this.selectedItemIndex = -1;
        this.selectedItemLabels = null;
        this.setLabel(label);
        this.setURL(url);
        this.setTarget(target);
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void setURL(final URL url) {
        this.url = url;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
    
    public void setBounds(final int xmin, final int ymin, final int n, final int n2) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmin + n;
        this.ymax = ymin + n2;
        this.areaPoly = null;
    }
    
    public void setBounds(final Polygon areaPoly) {
        this.areaPoly = areaPoly;
    }
    
    public boolean inside(final int n, final int n2) {
        if (this.areaPoly == null) {
            return this.xmin <= n && n <= this.xmax && this.ymin <= n2 && n2 <= this.ymax;
        }
        return this.areaPoly.inside(n, n2);
    }
    
    public void reset() {
        this.areaPoly = null;
        final int n = Integer.MIN_VALUE;
        this.ymax = n;
        this.ymin = n;
        this.xmax = n;
        this.xmin = n;
    }
    
    public boolean isValid() {
        return this.areaPoly != null || this.xmin != Integer.MIN_VALUE || this.xmin != Integer.MIN_VALUE || this.ymin != Integer.MIN_VALUE || this.ymax != Integer.MIN_VALUE;
    }
    
    public void activate(final AppletContext appletContext) {
        if (this.url == null) {
            return;
        }
        try {
            if (NFJavaScriptUtil.isWrappedJavaScriptURL(this.url)) {
                NFJavaScriptUtil.showDocument(appletContext, this.url);
            }
            else if (this.target == null || this.target.length() < 1) {
                appletContext.showDocument(this.url);
            }
            else {
                appletContext.showDocument(this.url, this.target);
            }
        }
        catch (Exception ex) {
            NFDebug.print(16L, "NFActiveLabel: " + ex.getMessage());
        }
    }
    
    public void drawOutline(final Graphics graphics, final NFLabelIntf nfLabelIntf) {
        if (graphics == null) {
            return;
        }
        final Color color = graphics.getColor();
        Color color2;
        try {
            nfLabelIntf.getRegion();
            color2 = nfLabelIntf.getRegion().getColor();
        }
        catch (Exception ex) {
            color2 = color;
        }
        graphics.setColor(color2);
        if (this.outlineArrow == null) {
            (this.outlineArrow = new NFArrow()).setStyle(4);
            this.outlineArrow.setWidth(this.outlineThickness);
        }
        if (this.areaPoly != null) {
            for (int i = 1; i < this.areaPoly.npoints; ++i) {
                NFLine.draw(graphics, this.areaPoly.xpoints[i - 1], this.areaPoly.ypoints[i - 1], this.areaPoly.xpoints[i], this.areaPoly.ypoints[i], this.outlineThickness, this.outlineStyle, color2, this.outlineArrow, this.outlineArrow);
            }
        }
        else {
            NFLine.draw(graphics, this.xmin, this.ymin, this.xmax, this.ymin, this.outlineThickness, this.outlineStyle, color2, this.outlineArrow, this.outlineArrow);
            NFLine.draw(graphics, this.xmax, this.ymin, this.xmax, this.ymax, this.outlineThickness, this.outlineStyle, color2, this.outlineArrow, this.outlineArrow);
            NFLine.draw(graphics, this.xmin, this.ymax, this.xmax, this.ymax, this.outlineThickness, this.outlineStyle, color2, this.outlineArrow, this.outlineArrow);
            NFLine.draw(graphics, this.xmin, this.ymin, this.xmin, this.ymax, this.outlineThickness, this.outlineStyle, color2, this.outlineArrow, this.outlineArrow);
        }
        graphics.setColor(color);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final NFLabelIntf nfLabelIntf) {
        if (this.label == null) {
            return;
        }
        nfLabelIntf.setLabel(this.label);
        nfLabelIntf.draw(graphics, n, n2 - nfLabelIntf.getBounds(graphics).height / 2);
    }
    
    public void draw(final Graphics graphics, final NFLabelIntf nfLabelIntf) {
        if (this.label == null) {
            return;
        }
        nfLabelIntf.setLabel(this.label);
        final Dimension bounds = nfLabelIntf.getBounds(graphics);
        nfLabelIntf.draw(graphics, bounds.width / 2, bounds.height / 2);
    }
    
    public static Vector loadAllParams(final NFParam nfParam, final String s) {
        final Vector<NFActiveLabel> vector = new Vector<NFActiveLabel>();
        try {
            final Vector vector2 = (Vector)nfParam.get(s);
            for (int i = 0; i < vector2.size(); ++i) {
                vector.addElement(loadParams(nfParam, vector2.elementAt(i)));
            }
        }
        catch (Exception ex) {
            NFDebug.print(16L, ex.getMessage());
        }
        return vector;
    }
    
    public static NFActiveLabel loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFActiveLabel loadParams(final NFParam nfParam, final Object o, final int n) {
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        final NFActiveLabel nfActiveLabel = new NFActiveLabel();
        nfActiveLabel.label = NFUtil.getString(vector, n, null);
        final String string = NFUtil.getString(vector, n + 1, null);
        if (NFJavaScriptUtil.isJavaScriptURL(string) && NFJavaScriptUtil.needsToBeWrapped(string)) {
            nfActiveLabel.url = NFJavaScriptUtil.wrapJavaScriptURL(string);
        }
        else {
            nfActiveLabel.url = nfParam.stringToURL(string);
        }
        nfActiveLabel.target = NFUtil.getString(vector, n + 2, null);
        return nfActiveLabel;
    }
}
