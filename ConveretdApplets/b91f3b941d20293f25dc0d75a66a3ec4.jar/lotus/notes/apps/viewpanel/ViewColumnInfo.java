// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.awt.Color;
import java.awt.Font;

public class ViewColumnInfo
{
    public static final int RESORT_NONE = 0;
    public static final int RESORT_ASCENDING = 1;
    public static final int RESORT_DESCENDING = 2;
    public static final int RESORT_BOTH = 3;
    public static final int RESORT_TOVIEW = 4;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;
    public static final String DEFAULT_FONT_FACE = "Helvetica";
    public static final Font DEFAULT_COLUMN_FONT;
    public static final Font DEFAULT_HEADER_FONT;
    int colNumber;
    String title;
    boolean twistie;
    String twistieimage;
    boolean sort;
    boolean sortDescending;
    boolean sortCategorize;
    boolean resortAscending;
    boolean resortDescending;
    boolean resortToView;
    String resortViewUNID;
    int width;
    boolean response;
    boolean hidden;
    boolean resizable;
    boolean columnTotal;
    int alignment;
    int headerAlignment;
    int readingOrder;
    int headerReadingOrder;
    Font columnFont;
    Font headerFont;
    Color textColor;
    Color headerTextColor;
    boolean isIcon;
    char listSeparator;
    NumFormat numberFormat;
    DateTimeFormat dateTimeFormat;
    
    public ViewColumnInfo() {
        this.colNumber = 0;
        this.title = null;
        this.twistie = false;
        this.twistieimage = null;
        this.sort = false;
        this.sortDescending = false;
        this.sortCategorize = false;
        this.resortAscending = false;
        this.resortDescending = false;
        this.resortToView = false;
        this.resortViewUNID = null;
        this.width = 0;
        this.response = false;
        this.hidden = false;
        this.resizable = false;
        this.columnTotal = false;
        this.alignment = 0;
        this.headerAlignment = 0;
        this.readingOrder = 0;
        this.headerReadingOrder = 0;
        this.columnFont = ViewColumnInfo.DEFAULT_COLUMN_FONT;
        this.headerFont = ViewColumnInfo.DEFAULT_HEADER_FONT;
        this.textColor = Color.black;
        this.headerTextColor = Color.black;
        this.isIcon = false;
        this.listSeparator = ',';
        this.numberFormat = new NumFormat();
        this.dateTimeFormat = new DateTimeFormat();
    }
    
    static {
        DEFAULT_COLUMN_FONT = new Font("Helvetica", 0, 12);
        DEFAULT_HEADER_FONT = new Font("Helvetica", 1, 14);
    }
}
