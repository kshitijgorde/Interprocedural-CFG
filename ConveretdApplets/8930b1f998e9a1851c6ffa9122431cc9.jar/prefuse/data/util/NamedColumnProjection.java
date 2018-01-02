// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.column.Column;
import java.util.HashSet;

public class NamedColumnProjection extends AbstractColumnProjection
{
    private HashSet m_names;
    private boolean m_include;
    
    public NamedColumnProjection(final String s, final boolean include) {
        (this.m_names = new HashSet()).add(s);
        this.m_include = include;
    }
    
    public NamedColumnProjection(final String[] array, final boolean include) {
        this.m_names = new HashSet();
        for (int i = 0; i < array.length; ++i) {
            this.m_names.add(array[i]);
        }
        this.m_include = include;
    }
    
    public void addName(final String s) {
        this.m_names.add(s);
    }
    
    public boolean removeName(final String s) {
        return this.m_names.remove(s);
    }
    
    public boolean include(final Column column, final String s) {
        return !(this.m_include ^ this.m_names.contains(s));
    }
}
