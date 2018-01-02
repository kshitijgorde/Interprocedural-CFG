// 
// Decompiled by Procyon v0.5.30
// 

package buildService;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class VirtualTable extends HashMap
{
    final MailAgent data;
    final HashSet cntr;
    
    public VirtualTable(final MailAgent rows, final HashSet cnt) {
        this.data = rows;
        this.cntr = cnt;
    }
    
    public Set entrySet() {
        return this.cntr;
    }
}
