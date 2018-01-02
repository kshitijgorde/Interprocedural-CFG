// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import java.util.HashMap;

public final class dA
{
    private HashMap a;
    private HashMap b;
    
    public dA() {
        this.a = new HashMap();
        this.b = new HashMap();
    }
    
    public dA(final String s, final String s2, final String s3) {
        this.a = new HashMap();
        this.b = new HashMap();
        this.a.put("door_repository", s);
        this.a.put("door_name", s2);
        this.a.put("door_version", s3);
        this.a.put("door_args", "");
    }
    
    public final boolean a() {
        final String s = "ENABLE_WSP";
        return this.a.containsKey(s) && String.valueOf(this.a.get(s)).equals("1");
    }
    
    public final String a(final String s) {
        if (!this.a.containsKey(s)) {
            com.hw.client.util.a.d("unable to retrieve key from params hashmap, key => " + s);
            return null;
        }
        return String.valueOf(this.a.get(s));
    }
    
    public final String b(final String s) {
        if (!this.b.containsKey(s)) {
            com.hw.client.util.a.d("unable to retrieve key from args hashmap, key => " + s);
            return null;
        }
        return String.valueOf(this.b.get(s));
    }
}
