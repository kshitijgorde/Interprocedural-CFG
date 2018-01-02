// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.events.TypedEvent;

public class TextChangedEvent extends TypedEvent
{
    static final long serialVersionUID = 3258696524257835065L;
    
    public TextChangedEvent(final StyledTextContent styledTextContent) {
        super(styledTextContent);
    }
}
