// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.awt.datatransfer.Clipboard;

public interface VisualTransferPlugin extends VisualPlugin
{
    void copy(final Clipboard p0);
    
    void paste(final Clipboard p0);
}
