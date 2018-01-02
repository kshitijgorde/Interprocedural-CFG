// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.ClassUtil;
import java.util.Observable;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import java.util.Observer;

public class BlacklistedCascadeIDEntry extends AbstractCascadeIDEntry
{
    public static final boolean DEFAULT_AUTO_BLACKLIST = false;
    public static final String XML_ELEMENT_NAME;
    public static final String XML_ELEMENT_CONTAINER_NAME = "BlacklistedCascades";
    public static final String XML_ATTR_AUTO_BLACKLIST_NEW_CASCADES = "autoBlacklistNewCascades";
    private static boolean m_bNewCascadesInBlacklist;
    private static Observer ms_observer;
    static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    
    public BlacklistedCascadeIDEntry(final MixCascade mixCascade) {
        super(mixCascade, Long.MAX_VALUE);
    }
    
    public BlacklistedCascadeIDEntry(final Element element) throws XMLParseException {
        super(element);
    }
    
    public static synchronized void putNewCascadesInBlacklist(final boolean bNewCascadesInBlacklist) {
        if (BlacklistedCascadeIDEntry.ms_observer == null) {
            BlacklistedCascadeIDEntry.ms_observer = new Observer() {
                static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
                static /* synthetic */ Class class$anon$infoservice$PreviouslyKnownCascadeIDEntry;
                
                public void update(final Observable observable, final Object o) {
                    final Class clazz = (BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry;
                    synchronized (clazz) {
                        final DatabaseMessage databaseMessage = (DatabaseMessage)o;
                        if (databaseMessage.getMessageData() == null || !(databaseMessage.getMessageData() instanceof MixCascade)) {
                            return;
                        }
                        final MixCascade mixCascade = (MixCascade)databaseMessage.getMessageData();
                        if (!mixCascade.isUserDefined() && Database.getInstance((BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).getEntryById(mixCascade.getMixIDsAsString()) == null) {
                            Database.getInstance((BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : BlacklistedCascadeIDEntry$1.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).update(new PreviouslyKnownCascadeIDEntry(mixCascade));
                            if (databaseMessage.getMessageCode() == 1 && BlacklistedCascadeIDEntry.m_bNewCascadesInBlacklist) {
                                Database.getInstance((BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : BlacklistedCascadeIDEntry$1.class$anon$infoservice$BlacklistedCascadeIDEntry).update(new BlacklistedCascadeIDEntry(mixCascade));
                            }
                        }
                    }
                }
                
                static /* synthetic */ Class class$(final String s) {
                    try {
                        return Class.forName(s);
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
            };
            Database.getInstance((BlacklistedCascadeIDEntry.class$anon$infoservice$MixCascade == null) ? (BlacklistedCascadeIDEntry.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : BlacklistedCascadeIDEntry.class$anon$infoservice$MixCascade).addObserver(BlacklistedCascadeIDEntry.ms_observer);
        }
        if (BlacklistedCascadeIDEntry.m_bNewCascadesInBlacklist != bNewCascadesInBlacklist) {
            BlacklistedCascadeIDEntry.m_bNewCascadesInBlacklist = bNewCascadesInBlacklist;
        }
    }
    
    public static synchronized boolean areNewCascadesInBlacklist() {
        return BlacklistedCascadeIDEntry.m_bNewCascadesInBlacklist;
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
        XML_ELEMENT_NAME = ClassUtil.getShortClassName((BlacklistedCascadeIDEntry.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (BlacklistedCascadeIDEntry.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : BlacklistedCascadeIDEntry.class$anon$infoservice$BlacklistedCascadeIDEntry);
        BlacklistedCascadeIDEntry.m_bNewCascadesInBlacklist = false;
    }
}
