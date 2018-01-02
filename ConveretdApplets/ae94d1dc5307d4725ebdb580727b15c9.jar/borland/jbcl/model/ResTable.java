// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.ArrayResourceBundle;

public class ResTable extends ArrayResourceBundle
{
    static String resourceName;
    
    protected Object[] getContents() {
        return new String[] { "true", "false", "DELETE FROM ", "INSERT INTO ", ") VALUES (", ":Partial", ":Next", ":Prior", ":CaseInsensitive", ":Closest", ":First", ":Last", ":Fast", " WHERE ", "UNKNOWN", "SELECT ", " AS ", "ItemFormatter: expected Variant as parameter", "Error in parsing string", "This formatter does not support this version of parse", "Attempt to access empty container", "Can't add {0} to {1} : it's already a child of {2}", "Tree circularity in children of {0} after {1}", "Tree node {0} already contains child {1}", "Tree node {0} is already a child of {1}", "Tree node {0} already contains sibling {1}", "Tree node {0} is not a child of {1}", "Tree node {0} not linked to parent {1}", "This model does not support variable size", "This model does not support variable rows", "This model does not support variable columns", "true;false", "Number must be between -32768 and 32767", "Number must be between -128 and 127" };
    }
    
    static {
        ResTable.resourceName = "borland.jbcl.model.ResTable";
    }
}
