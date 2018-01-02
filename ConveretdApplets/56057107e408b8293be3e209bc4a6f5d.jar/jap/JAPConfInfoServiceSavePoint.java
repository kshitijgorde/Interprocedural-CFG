// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.InfoServiceHolder;
import anon.infoservice.Database;
import anon.infoservice.InfoServiceDBEntry;
import java.util.Vector;

public class JAPConfInfoServiceSavePoint implements IJAPConfSavePoint
{
    private Vector m_knownInfoServices;
    private InfoServiceDBEntry m_preferredInfoService;
    private boolean m_automaticInfoServiceRequestsDisabled;
    private boolean m_automaticInfoServiceChanges;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    
    public void createSavePoint() {
        this.m_knownInfoServices = Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).getEntryList();
        this.m_preferredInfoService = InfoServiceHolder.getInstance().getPreferredInfoService();
        this.m_automaticInfoServiceRequestsDisabled = JAPModel.isInfoServiceDisabled();
        this.m_automaticInfoServiceChanges = InfoServiceHolder.getInstance().isChangeInfoServices();
    }
    
    public void restoreSavePoint() {
        final Enumeration<InfoServiceDBEntry> elements = this.m_knownInfoServices.elements();
        while (elements.hasMoreElements()) {
            Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).update(elements.nextElement());
        }
        final Enumeration<InfoServiceDBEntry> elements2 = Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).getEntryList().elements();
        while (elements2.hasMoreElements()) {
            final InfoServiceDBEntry infoServiceDBEntry = elements2.nextElement();
            if (!this.m_knownInfoServices.contains(infoServiceDBEntry)) {
                Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).remove(infoServiceDBEntry);
            }
        }
        InfoServiceHolder.getInstance().setPreferredInfoService(this.m_preferredInfoService);
        JAPController.getInstance().setInfoServiceDisabled(this.m_automaticInfoServiceRequestsDisabled);
        InfoServiceHolder.getInstance().setChangeInfoServices(this.m_automaticInfoServiceChanges);
    }
    
    public void restoreDefaults() {
        synchronized (InfoServiceHolder.getInstance()) {
            Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).removeAll();
            try {
                final InfoServiceDBEntry[] defaultInfoServices = JAPController.createDefaultInfoServices();
                for (int i = 0; i < defaultInfoServices.length; ++i) {
                    Database.getInstance((JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoServiceSavePoint.class$anon$infoservice$InfoServiceDBEntry).update(defaultInfoServices[i]);
                }
                InfoServiceHolder.getInstance().setPreferredInfoService(defaultInfoServices[0]);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, "Cannot create the default infoservice.");
            }
            JAPController.getInstance().setInfoServiceDisabled(false);
            InfoServiceHolder.getInstance().setChangeInfoServices(true);
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
}
