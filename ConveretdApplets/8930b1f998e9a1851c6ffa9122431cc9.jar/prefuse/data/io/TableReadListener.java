// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.data.parser.DataParseException;

public interface TableReadListener
{
    void readValue(final int p0, final int p1, final String p2) throws DataParseException;
}
