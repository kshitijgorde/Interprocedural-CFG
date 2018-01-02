// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import borland.jbcl.util.ArrayResourceBundle;

public class ResTable extends ArrayResourceBundle
{
    static String resourceName;
    
    protected Object[] getContents() {
        return new String[] { "Can't use FileDialog without a frame", "Bad vertical alignment: {0}", "Bad horizontal alignment: {0}", "Could not find file:  {0}", "Select an image file to load", "Column {0}", "{0} out of range", "firstRect and secondRect must be constructed Rectangle objects", "Illegal subfocus value (out of range).", "Stream does not support reset", "ItemEditor cannot post current value", "..." };
    }
    
    static {
        ResTable.resourceName = "borland.jbcl.view.ResTable";
    }
}
