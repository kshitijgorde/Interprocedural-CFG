// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.ClassUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;

public class PreviouslyKnownCascadeIDEntry extends AbstractCascadeIDEntry
{
    public static final String XML_ELEMENT_NAME;
    public static final String XML_ELEMENT_CONTAINER_NAME = "PreviouslyKnownCascades";
    static /* synthetic */ Class class$anon$infoservice$PreviouslyKnownCascadeIDEntry;
    
    public PreviouslyKnownCascadeIDEntry(final MixCascade mixCascade) {
        super(mixCascade, Long.MAX_VALUE);
    }
    
    public PreviouslyKnownCascadeIDEntry(final Element element) throws XMLParseException {
        super(element);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        XML_ELEMENT_NAME = ClassUtil.getShortClassName((PreviouslyKnownCascadeIDEntry.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (PreviouslyKnownCascadeIDEntry.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : PreviouslyKnownCascadeIDEntry.class$anon$infoservice$PreviouslyKnownCascadeIDEntry);
    }
}
