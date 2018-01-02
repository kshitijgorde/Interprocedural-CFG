// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.infoservice.InfoServiceDBEntry;
import java.util.Observable;

public class ServerSocketPropagandist extends Observable implements Runnable
{
    public static final int STATE_REGISTERED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_RECONNECTING = 2;
    public static final int STATE_HALTED = 3;
    public static final int RETURN_SUCCESS = 0;
    public static final int RETURN_VERIFICATION_ERROR = 1;
    public static final int RETURN_INFOSERVICE_ERROR = 2;
    public static final int RETURN_UNKNOWN_ERROR = 3;
    private static final int RETURN_FORWARDERID_ERROR = 4;
    private static final int FORWARDER_VERIFY_ERROR_CODE = 1;
    private static final int FORWARDER_RENEW_ERROR_CODE = 11;
    private static final long FORWARDER_RENEW_PERIOD = 600000L;
    private int m_portNumber;
    private InfoServiceDBEntry m_infoService;
    private String m_forwarderId;
    private int m_currentErrorCode;
    private Thread m_propagandaThread;
    private int m_currentConnectionState;
    
    public ServerSocketPropagandist(final int portNumber, final InfoServiceDBEntry infoService) {
        this.m_portNumber = portNumber;
        this.m_infoService = infoService;
        (this.m_propagandaThread = new Thread(this)).setDaemon(true);
        this.m_currentErrorCode = this.announceNewForwarder();
        if (this.m_currentErrorCode != 0) {
            this.m_currentConnectionState = 1;
        }
        else {
            this.m_currentConnectionState = 0;
        }
        this.m_propagandaThread.start();
    }
    
    public void stopPropaganda() {
        synchronized (this.m_propagandaThread) {
            try {
                this.m_propagandaThread.interrupt();
            }
            catch (Exception ex) {}
        }
    }
    
    public int getCurrentState() {
        return this.m_currentConnectionState;
    }
    
    public int getCurrentErrorCode() {
        return this.m_currentErrorCode;
    }
    
    public InfoServiceDBEntry getInfoService() {
        return this.m_infoService;
    }
    
    public void run() {
        int i = 0;
        while (i == 0) {
            synchronized (this.m_propagandaThread) {
                try {
                    i = (Thread.interrupted() ? 1 : 0);
                    if (i == 0) {
                        this.m_propagandaThread.wait(600000L);
                    }
                }
                catch (InterruptedException ex) {
                    i = 1;
                }
            }
            if (i == 0) {
                int n = 0;
                if (this.m_currentConnectionState == 0 && this.renewForwarder() != 0) {
                    this.m_currentConnectionState = 2;
                    n = 1;
                }
                if (this.m_currentConnectionState != 1 && this.m_currentConnectionState != 2) {
                    continue;
                }
                final int announceNewForwarder = this.announceNewForwarder();
                if (announceNewForwarder == 0) {
                    this.m_currentConnectionState = 0;
                    n = 1;
                }
                if (announceNewForwarder != this.m_currentErrorCode) {
                    n = 1;
                }
                this.m_currentErrorCode = announceNewForwarder;
                if (n != 1) {
                    continue;
                }
                this.setChanged();
                this.notifyObservers(null);
            }
        }
        this.m_currentConnectionState = 3;
        this.m_currentErrorCode = 0;
        this.setChanged();
        this.notifyObservers(null);
    }
    
    private int announceNewForwarder() {
        int n;
        try {
            final Document document = XMLUtil.createDocument();
            final Element element = document.createElement("JapForwarder");
            final Element element2 = document.createElement("PlainInformation");
            final Element element3 = document.createElement("Port");
            element3.appendChild(document.createTextNode(Integer.toString(this.m_portNumber)));
            element2.appendChild(element3);
            element.appendChild(element2);
            document.appendChild(element);
            try {
                final Element postNewForwarder = this.m_infoService.postNewForwarder(element);
                final NodeList elementsByTagName = postNewForwarder.getElementsByTagName("PlainInformation");
                if (elementsByTagName.getLength() == 0) {
                    final NodeList elementsByTagName2 = postNewForwarder.getElementsByTagName("ErrorInformation");
                    if (elementsByTagName2.getLength() == 0) {
                        n = 3;
                    }
                    else {
                        final NodeList elementsByTagName3 = ((Element)elementsByTagName2.item(0)).getElementsByTagName("Error");
                        if (elementsByTagName3.getLength() == 0) {
                            n = 3;
                        }
                        else {
                            final Element element4 = (Element)elementsByTagName3.item(0);
                            try {
                                if (Integer.parseInt(element4.getAttribute("code")) == 1) {
                                    n = 1;
                                }
                                else {
                                    n = 3;
                                    LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: The infoservice returned an unknwon error: Errorcode " + Integer.parseInt(element4.getAttribute("code")) + ": " + element4.getFirstChild().getNodeValue());
                                }
                            }
                            catch (Exception ex) {
                                n = 3;
                                LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: Error while parsing the error information returned by the infoservice: " + ex.toString());
                            }
                        }
                    }
                }
                else {
                    final NodeList elementsByTagName4 = ((Element)elementsByTagName.item(0)).getElementsByTagName("Forwarder");
                    if (elementsByTagName4.getLength() == 0) {
                        n = 3;
                        LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: Error while parsing the infoservice answer (Forwarder node).");
                    }
                    else {
                        final String attribute = ((Element)elementsByTagName4.item(0)).getAttribute("id");
                        if (attribute == null || new String("").equals(attribute)) {
                            n = 3;
                            LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: Got an invalid id from the infoservice.");
                        }
                        else {
                            this.m_forwarderId = attribute;
                            n = 0;
                        }
                    }
                }
            }
            catch (Exception ex2) {
                n = 2;
                LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: InfoService communication error: " + ex2.toString());
            }
        }
        catch (Exception ex3) {
            n = 3;
            LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: announceNewForwarder: Unexpected error while creating the request document: " + ex3.toString());
        }
        return n;
    }
    
    private int renewForwarder() {
        int n;
        try {
            final Document document = XMLUtil.createDocument();
            final Element element = document.createElement("JapForwarder");
            final Element element2 = document.createElement("PlainInformation");
            final Element element3 = document.createElement("Forwarder");
            element3.setAttribute("id", this.m_forwarderId);
            element2.appendChild(element3);
            element.appendChild(element2);
            document.appendChild(element);
            try {
                final NodeList elementsByTagName = this.m_infoService.postRenewForwarder(element).getElementsByTagName("ErrorInformation");
                if (elementsByTagName.getLength() == 0) {
                    n = 0;
                }
                else {
                    final NodeList elementsByTagName2 = ((Element)elementsByTagName.item(0)).getElementsByTagName("Error");
                    if (elementsByTagName2.getLength() == 0) {
                        n = 3;
                    }
                    else {
                        final Element element4 = (Element)elementsByTagName2.item(0);
                        try {
                            if (Integer.parseInt(element4.getAttribute("code")) == 11) {
                                n = 4;
                            }
                            else {
                                n = 3;
                                LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: renewForwarder: The infoservice returned an unknwon error: Errorcode " + Integer.parseInt(element4.getAttribute("code")) + ": " + element4.getFirstChild().getNodeValue());
                            }
                        }
                        catch (Exception ex) {
                            n = 3;
                            LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: renewForwarder: Error while parsing the error information returned by the infoservice: " + ex.toString());
                        }
                    }
                }
            }
            catch (Exception ex2) {
                n = 2;
                LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: renewForwarder: InfoService communication error: " + ex2.toString());
            }
        }
        catch (Exception ex3) {
            n = 3;
            LogHolder.log(3, LogType.NET, "ServerSocketPropagandist: renewForwarder: Unexpected error while creating the request document: " + ex3.toString());
        }
        return n;
    }
}
