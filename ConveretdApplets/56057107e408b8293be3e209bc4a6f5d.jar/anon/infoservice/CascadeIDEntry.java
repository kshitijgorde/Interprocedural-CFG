// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.ClassUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;

public class CascadeIDEntry extends AbstractCascadeIDEntry
{
    public static final String XML_ELEMENT_NAME;
    public static final String XML_ELEMENT_CONTAINER_NAME = "KnownCascades";
    private static final long EXPIRE_TIME = 604800000L;
    static /* synthetic */ Class class$anon$infoservice$CascadeIDEntry;
    
    public CascadeIDEntry(final MixCascade mixCascade) throws IllegalArgumentException {
        super(mixCascade, System.currentTimeMillis() + 604800000L);
    }
    
    public CascadeIDEntry(final Element element) throws XMLParseException {
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
        XML_ELEMENT_NAME = ClassUtil.getShortClassName((CascadeIDEntry.class$anon$infoservice$CascadeIDEntry == null) ? (CascadeIDEntry.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : CascadeIDEntry.class$anon$infoservice$CascadeIDEntry);
    }
}
