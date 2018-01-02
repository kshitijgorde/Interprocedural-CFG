// 
// Decompiled by Procyon v0.5.30
// 

package zylom;

import com.zylom.thirdparty.GameProperties;

public class Properties extends GameProperties
{
    public void initProperties() {
    }
    
    public boolean getBoolean(final String id, final boolean b) {
        try {
            return Properties.properties.getBoolean(id);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public String getString(final String id, final String s) {
        try {
            return Properties.properties.getString(id);
        }
        catch (Exception ex) {
            return s;
        }
    }
}
