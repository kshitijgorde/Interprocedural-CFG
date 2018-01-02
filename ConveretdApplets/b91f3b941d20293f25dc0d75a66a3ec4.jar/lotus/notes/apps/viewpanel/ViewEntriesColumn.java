// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.awt.Color;

public class ViewEntriesColumn
{
    public int columnNumber;
    public String dataValue;
    public Color customBGColor;
    public Color customTextColor;
    public boolean isDateTime;
    public boolean isNumber;
    public boolean isMultiValue;
    public boolean isCategory;
    
    public ViewEntriesColumn() {
        this.columnNumber = -1;
        this.customBGColor = null;
        this.customTextColor = null;
        this.isDateTime = false;
        this.isNumber = false;
        this.isMultiValue = false;
        this.isCategory = false;
    }
}
