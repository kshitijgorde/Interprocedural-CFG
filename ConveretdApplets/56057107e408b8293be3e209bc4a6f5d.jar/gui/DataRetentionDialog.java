// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.util.Enumeration;
import anon.util.XMLDuration;
import anon.infoservice.DataRetentionInformation;
import java.net.URL;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import anon.infoservice.MixCascade;
import java.awt.Component;

public class DataRetentionDialog
{
    public static final String MSG_DATA_RETENTION_EXPLAIN_SHORT;
    public static final String MSG_DATA_RETENTION_MIX_EXPLAIN_SHORT;
    public static final String MSG_DATA_RETENTION_EXPLAIN;
    public static final String MSG_RETENTION_PERIOD;
    public static final String MSG_INFO_TITLE;
    public static final String MSG_NO_LOGS;
    public static final String MSG_ENTRY_MIX_STORES;
    public static final String MSG_CASCADE_STORES;
    public static final String MSG_MIX_STORES;
    public static final String MSG_NO_CHANCE;
    public static final String MSG_NO_TARGET_ADDRESSES;
    public static final String MSG_IN_THE_SCOPE;
    public static final String MSG_WHETHER_CONNECTED;
    public static final String MSG_WHICH_TARGETED;
    public static final String MSG_WHETHER_TARGETED;
    public static final String MSG_WHETHER_USED;
    static /* synthetic */ Class class$gui$DataRetentionDialog;
    static /* synthetic */ Class class$anon$infoservice$DataRetentionInformation;
    
    public static void show(final Component component, final MixCascade mixCascade) {
        show(component, mixCascade, -1);
    }
    
    public static void show(final Component component, final MixCascade mixCascade, final int n) {
        final String s = "";
        JAPDialog.ILinkedInformation linkedInformation = null;
        DataRetentionInformation dataRetentionInformation = null;
        DataRetentionInformation dataRetentionInformation2 = null;
        DataRetentionInformation dataRetentionInformation3 = null;
        boolean b = false;
        boolean b2 = false;
        if (n >= 0) {
            if (mixCascade != null && mixCascade.getMixInfo(n) != null) {
                dataRetentionInformation = mixCascade.getMixInfo(n).getDataRetentionInformation();
            }
        }
        else if (mixCascade != null) {
            dataRetentionInformation = mixCascade.getDataRetentionInformation();
        }
        if (dataRetentionInformation == null || mixCascade == null) {
            return;
        }
        final URL url = dataRetentionInformation.getURL(JAPMessages.getLocale().getLanguage());
        if (url != null) {
            linkedInformation = new JAPDialog.AbstractLinkedURLAdapter() {
                public URL getUrl() {
                    return url;
                }
                
                public String getMessage() {
                    return JAPMessages.getString(JAPDialog.ILinkedInformation.MSG_MORE_INFO);
                }
            };
        }
        if (mixCascade.getMixInfo(0) != null) {
            dataRetentionInformation2 = mixCascade.getMixInfo(0).getDataRetentionInformation();
            if (n < 0 && dataRetentionInformation.isLogged(1) && dataRetentionInformation2 != null) {
                b = true;
            }
        }
        final DataRetentionInformation dataRetentionInformation4 = mixCascade.getDataRetentionInformation();
        if (mixCascade.getMixInfo(mixCascade.getNumberOfMixes() - 1) != null) {
            dataRetentionInformation3 = mixCascade.getMixInfo(mixCascade.getNumberOfMixes() - 1).getDataRetentionInformation();
        }
        String s2;
        if (n < 0 && (dataRetentionInformation2 == null || dataRetentionInformation2.isLogged(1))) {
            s2 = s + JAPMessages.getString(DataRetentionDialog.MSG_NO_LOGS, "<i>" + mixCascade.getName() + "</i>");
        }
        else {
            String s3;
            if (b) {
                s3 = JAPMessages.getString(DataRetentionDialog.MSG_ENTRY_MIX_STORES, "<i>" + mixCascade.getName() + "</i>");
            }
            else if (n < 0) {
                s3 = JAPMessages.getString(DataRetentionDialog.MSG_CASCADE_STORES, "<i>" + mixCascade.getName() + "</i>");
            }
            else {
                String organization = "unknown";
                if (mixCascade.getMixInfo(n).getServiceOperator() != null) {
                    organization = mixCascade.getMixInfo(n).getServiceOperator().getOrganization();
                }
                s3 = JAPMessages.getString(DataRetentionDialog.MSG_MIX_STORES, new String[] { "<i>" + mixCascade.getMixInfo(n).getName() + "</i>", "<i>" + organization + "</i>" });
            }
            String s4 = s3 + "<ul>";
            for (int i = 0; i < DataRetentionInformation.getLoggedElementsLength(); ++i) {
                if (!dataRetentionInformation.isLogged(DataRetentionInformation.getLoggedElementID(i))) {
                    if (n >= 0) {
                        continue;
                    }
                    if (dataRetentionInformation2 == null || (DataRetentionInformation.getLoggedElementID(i) != 32 && DataRetentionInformation.getLoggedElementID(i) != 64 && DataRetentionInformation.getLoggedElementID(i) != 2 && DataRetentionInformation.getLoggedElementID(i) != 4) || !dataRetentionInformation2.isLogged(i)) {
                        if (dataRetentionInformation3 == null || (DataRetentionInformation.getLoggedElementID(i) != 128 && DataRetentionInformation.getLoggedElementID(i) != 256 && DataRetentionInformation.getLoggedElementID(i) != 512 && DataRetentionInformation.getLoggedElementID(i) != 1024) || !dataRetentionInformation3.isLogged(i)) {
                            continue;
                        }
                    }
                }
                else if (n >= 0 && mixCascade.getNumberOfOperatorsShown() > 1) {
                    if (n == 0) {
                        if (DataRetentionInformation.getLoggedElementID(i) == 128 || DataRetentionInformation.getLoggedElementID(i) == 256 || DataRetentionInformation.getLoggedElementID(i) == 512) {
                            continue;
                        }
                        if (DataRetentionInformation.getLoggedElementID(i) == 1024) {
                            continue;
                        }
                    }
                    if (n >= 0) {
                        if (DataRetentionInformation.getLoggedElementID(i) == 32) {
                            s4 = s4 + "<li>" + JAPMessages.getString(((DataRetentionDialog.class$anon$infoservice$DataRetentionInformation == null) ? (DataRetentionDialog.class$anon$infoservice$DataRetentionInformation = class$("anon.infoservice.DataRetentionInformation")) : DataRetentionDialog.class$anon$infoservice$DataRetentionInformation).getName() + "_" + "INPUT_SOURCE_IP_ADDRESS_MIX") + "</li>";
                            continue;
                        }
                        if (DataRetentionInformation.getLoggedElementID(i) == 64) {
                            s4 = s4 + "<li>" + JAPMessages.getString(((DataRetentionDialog.class$anon$infoservice$DataRetentionInformation == null) ? (DataRetentionDialog.class$anon$infoservice$DataRetentionInformation = class$("anon.infoservice.DataRetentionInformation")) : DataRetentionDialog.class$anon$infoservice$DataRetentionInformation).getName() + "_" + "INPUT_SOURCE_IP_PORT_MIX") + "</li>";
                            continue;
                        }
                    }
                }
                s4 = s4 + "<li>" + JAPMessages.getString(((DataRetentionDialog.class$anon$infoservice$DataRetentionInformation == null) ? (DataRetentionDialog.class$anon$infoservice$DataRetentionInformation = class$("anon.infoservice.DataRetentionInformation")) : DataRetentionDialog.class$anon$infoservice$DataRetentionInformation).getName() + "_" + DataRetentionInformation.getLoggedElementName(i)) + "</li>";
            }
            String s5 = s4 + "</ul>" + "<p>" + JAPMessages.getString(DataRetentionDialog.MSG_RETENTION_PERIOD) + ": ";
            XMLDuration xmlDuration;
            if (b) {
                xmlDuration = dataRetentionInformation2.getDuration();
            }
            else {
                xmlDuration = dataRetentionInformation.getDuration();
            }
            final Enumeration fields = xmlDuration.getFields();
            while (fields.hasMoreElements()) {
                final Object nextElement = fields.nextElement();
                s5 = s5 + xmlDuration.getField(nextElement).intValue() + " " + JAPMessages.getString(XMLDuration.getFieldName(nextElement));
                if (fields.hasMoreElements()) {
                    s5 += ", ";
                }
            }
            final String string = s5 + "</p>" + "<br>";
            if (dataRetentionInformation4 != null && dataRetentionInformation2 != null && dataRetentionInformation2.isLogged(32)) {
                s2 = string + JAPMessages.getString(DataRetentionDialog.MSG_IN_THE_SCOPE) + " ";
                if (b || !dataRetentionInformation4.isLogged(8) || !dataRetentionInformation4.isLogged(16)) {
                    if (dataRetentionInformation2.isLogged(2) || dataRetentionInformation2.isLogged(8)) {
                        s2 += JAPMessages.getString(DataRetentionDialog.MSG_WHETHER_USED, "<i>" + mixCascade.getName() + "</i>");
                    }
                    else {
                        s2 += JAPMessages.getString(DataRetentionDialog.MSG_WHETHER_CONNECTED, "<i>" + mixCascade.getName() + "</i>");
                    }
                }
                else if (dataRetentionInformation4.isLogged(2)) {
                    if (dataRetentionInformation3.isLogged(1024) || dataRetentionInformation3.isLogged(512)) {
                        b2 = true;
                        s2 += JAPMessages.getString(DataRetentionDialog.MSG_WHICH_TARGETED);
                    }
                    else if (dataRetentionInformation3.isLogged(256)) {
                        s2 += JAPMessages.getString(DataRetentionDialog.MSG_WHETHER_TARGETED, "<i>" + mixCascade.getName() + "</i>");
                    }
                }
                else {
                    s2 += JAPMessages.getString(DataRetentionDialog.MSG_WHETHER_CONNECTED, "<i>" + mixCascade.getName() + "</i>");
                }
                if (!b2) {
                    s2 = s2 + " <b>" + JAPMessages.getString(DataRetentionDialog.MSG_NO_TARGET_ADDRESSES) + "</b>";
                }
            }
            else {
                s2 = string + JAPMessages.getString(DataRetentionDialog.MSG_NO_CHANCE, "<i>" + mixCascade.getName() + "</i>");
            }
        }
        JAPDialog.showWarningDialog(component, s2, JAPMessages.getString(DataRetentionDialog.MSG_INFO_TITLE), linkedInformation);
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
        MSG_DATA_RETENTION_EXPLAIN_SHORT = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_explainShort";
        MSG_DATA_RETENTION_MIX_EXPLAIN_SHORT = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_explainShortMix";
        MSG_DATA_RETENTION_EXPLAIN = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_explain";
        MSG_RETENTION_PERIOD = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_retentionPeriod";
        MSG_INFO_TITLE = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_info";
        MSG_NO_LOGS = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_noLogs";
        MSG_ENTRY_MIX_STORES = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_entryMixStores";
        MSG_CASCADE_STORES = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_cascadeStores";
        MSG_MIX_STORES = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_mixStores";
        MSG_NO_CHANCE = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_noChance";
        MSG_NO_TARGET_ADDRESSES = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_noTargetAdresses";
        MSG_IN_THE_SCOPE = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_inTheScope";
        MSG_WHETHER_CONNECTED = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_whetherConnected";
        MSG_WHICH_TARGETED = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_whichTargeted";
        MSG_WHETHER_TARGETED = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_whetherTargeted";
        MSG_WHETHER_USED = ((DataRetentionDialog.class$gui$DataRetentionDialog == null) ? (DataRetentionDialog.class$gui$DataRetentionDialog = class$("gui.DataRetentionDialog")) : DataRetentionDialog.class$gui$DataRetentionDialog).getName() + "_whetherUsed";
    }
}
