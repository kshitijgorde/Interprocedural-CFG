// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import java.util.Enumeration;
import java.util.Vector;
import org.w3c.dom.Document;
import anon.util.XMLUtil;
import jap.JAPModel;
import org.w3c.dom.Element;
import jap.IJAPConfSavePoint;

public class JAPConfForwardingServerSavePoint implements IJAPConfSavePoint
{
    private int m_forwardingServerPort;
    private Element m_connectionClassSettings;
    private Element m_availableMixCascadesSettings;
    private Element m_registrationInfoServicesSettings;
    
    public JAPConfForwardingServerSavePoint() {
        this.m_forwardingServerPort = -1;
        this.m_connectionClassSettings = null;
        this.m_availableMixCascadesSettings = null;
        this.m_registrationInfoServicesSettings = null;
    }
    
    public void createSavePoint() {
        this.m_forwardingServerPort = JAPModel.getInstance().getRoutingSettings().getServerPort();
        final Document document = XMLUtil.createDocument();
        this.m_connectionClassSettings = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getSettingsAsXml(document);
        this.m_availableMixCascadesSettings = JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().getSettingsAsXml(document);
        this.m_registrationInfoServicesSettings = JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().getSettingsAsXml(document);
    }
    
    public void restoreSavePoint() {
        if (this.m_forwardingServerPort != -1) {
            JAPModel.getInstance().getRoutingSettings().setServerPort(this.m_forwardingServerPort);
        }
        if (this.m_connectionClassSettings != null) {
            JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().loadSettingsFromXml(this.m_connectionClassSettings);
        }
        if (this.m_availableMixCascadesSettings != null) {
            JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().loadSettingsFromXml(this.m_availableMixCascadesSettings);
        }
        if (this.m_registrationInfoServicesSettings != null) {
            JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().loadSettingsFromXml(this.m_registrationInfoServicesSettings);
        }
    }
    
    public void restoreDefaults() {
        final Enumeration<JAPRoutingConnectionClass> elements = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getConnectionClasses().elements();
        while (elements.hasMoreElements()) {
            final JAPRoutingConnectionClass japRoutingConnectionClass = elements.nextElement();
            if (japRoutingConnectionClass.getIdentifier() == 8) {
                japRoutingConnectionClass.setMaximumBandwidth(16000);
            }
            japRoutingConnectionClass.setRelativeBandwidth(50);
        }
        JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().setCurrentConnectionClass(2);
        JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().setAllowAllAvailableMixCascades(true);
        JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().setAllowedMixCascades(new Vector());
        JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().setRegisterAtAllAvailableInfoServices(true);
        JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().setRegistrationInfoServices(new Vector());
    }
}
