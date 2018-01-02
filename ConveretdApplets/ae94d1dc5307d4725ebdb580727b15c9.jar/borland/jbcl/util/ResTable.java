// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class ResTable extends ArrayResourceBundle
{
    static String resourceName;
    
    protected Object[] getContents() {
        return new String[] { "Command line param {0} must be followed by a string", "Unexpected type: {0}  Expected type: {1}", "Invalid variant type: {0}", "Invalid variant name: {0}" };
    }
    
    static {
        ResTable.resourceName = "borland.jbcl.util.ResTable";
    }
}
