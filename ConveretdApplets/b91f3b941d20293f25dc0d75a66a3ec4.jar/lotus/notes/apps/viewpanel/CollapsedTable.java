// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Enumeration;
import java.util.Hashtable;

class CollapsedTable extends Hashtable
{
    private static final boolean EXPAND_DEBUG = false;
    private boolean isCollapsed;
    
    CollapsedTable() {
        this.isCollapsed = true;
    }
    
    public void setCollapsed(final boolean isCollapsed) {
        this.clear();
        this.isCollapsed = isCollapsed;
    }
    
    public boolean isCollapsed() {
        return this.isCollapsed;
    }
    
    public void add(final String s) {
        this.put(s, s);
    }
    
    public boolean contains(final String s) {
        return this.containsKey(s);
    }
    
    public void expand(final String s) {
        if (this.isCollapsed) {
            try {
                this.remove(s);
            }
            catch (Exception ex) {}
        }
        else {
            this.add(s);
        }
    }
    
    public void collapse(final String s) {
        if (!this.isCollapsed) {
            try {
                this.remove(s);
            }
            catch (Exception ex) {}
        }
        else {
            this.add(s);
        }
    }
    
    public boolean isLineCollapsed(final String s) {
        return (this.isCollapsed && this.contains(s)) || (!this.isCollapsed && !this.contains(s));
    }
    
    public String getList() {
        String string = null;
        if (!this.isEmpty()) {
            final StringBuffer sb = new StringBuffer(this.size() * 38);
            final Enumeration<Object> keys = this.keys();
            while (keys.hasMoreElements()) {
                sb.append("unid=").append(keys.nextElement()).append('&');
            }
            sb.setLength(sb.length() - 1);
            string = sb.toString();
        }
        return string;
    }
}
