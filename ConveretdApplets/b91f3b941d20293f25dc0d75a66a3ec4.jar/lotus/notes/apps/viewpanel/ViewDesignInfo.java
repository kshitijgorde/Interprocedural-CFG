// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Vector;
import java.awt.Color;

public class ViewDesignInfo
{
    public int headerLines;
    public int numColumns;
    public int rowsPerLine;
    public int rowSpacing;
    public int numCategories;
    public int direction;
    public boolean extendLastColumnWidth;
    public boolean hideMarginBorder;
    public boolean showTwistiesForResponses;
    public String bgImage;
    public int bgImageRepeat;
    public Color totalsColor;
    public Color altrowColor;
    public Color headerColor;
    public Color unreadColor;
    public Vector vColumnInfo;
    
    public ViewDesignInfo() {
        this.headerLines = 1;
        this.numColumns = 0;
        this.rowsPerLine = 1;
        this.rowSpacing = 1;
        this.numCategories = 0;
        this.direction = 0;
        this.extendLastColumnWidth = true;
        this.hideMarginBorder = false;
        this.showTwistiesForResponses = false;
        this.bgImage = null;
        this.bgImageRepeat = 0;
        this.totalsColor = Color.black;
        this.altrowColor = null;
        this.headerColor = Color.lightGray;
        this.unreadColor = Color.red;
        this.vColumnInfo = null;
    }
}
