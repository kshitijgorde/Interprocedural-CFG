// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.column.Column;

public class AcceptAllColumnProjection extends AbstractColumnProjection
{
    public boolean include(final Column column, final String s) {
        return true;
    }
}
