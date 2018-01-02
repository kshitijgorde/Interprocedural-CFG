// 
// Decompiled by Procyon v0.5.30
// 

package zylom;

import com.zylom.thirdparty.GameProperties;

public class Properties extends GameProperties
{
    public boolean getBoolean(final String id, final boolean def) {
        try {
            return GameProperties.properties.getBoolean(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }
    
    public String getString(final String id, final String def) {
        try {
            return GameProperties.properties.getString(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }
    
    public void initProperties() {
    }
}
