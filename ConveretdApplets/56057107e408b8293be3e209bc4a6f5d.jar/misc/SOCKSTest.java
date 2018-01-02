// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import HTTPClient.HTTPConnection;
import anon.infoservice.ImmutableProxyInterface;
import anon.util.IPasswordReader;
import anon.infoservice.ProxyInterface;
import anon.infoservice.ListenerInterface;
import anon.infoservice.HTTPConnectionFactory;
import java.util.Random;
import anon.infoservice.AbstractMixCascadeContainer;
import anon.infoservice.SimpleMixCascadeContainer;
import anon.infoservice.MixCascade;
import anon.crypto.SignatureVerifier;
import anon.tor.TorAnonServerDescription;
import anon.terms.TermsAndConditionConfirmation;
import anon.proxy.AnonProxy;
import anon.client.AnonClient;
import anon.infoservice.InfoServiceDBEntry;
import anon.infoservice.InfoServiceHolder;
import java.net.ServerSocket;
import logging.Log;
import logging.LogHolder;
import logging.LogType;
import jap.JAPDebug;
import java.io.IOException;

public final class SOCKSTest
{
    public static void main(final String[] array) throws IOException, Exception {
        new SOCKSTest().doIt();
    }
    
    public void doIt() throws IOException, Exception {
        final JAPDebug instance = JAPDebug.getInstance();
        instance.setLogLevel(7);
        instance.setLogType(LogType.TOR);
        LogHolder.setLogInstance(instance);
        LogHolder.setDetailLevel(0);
        LogHolder.log(7, LogType.TOR, "Start stress testr!");
        final ServerSocket serverSocket = new ServerSocket(4007);
        InfoServiceHolder.getInstance().setPreferredInfoService(new InfoServiceDBEntry("infoservice.inf.tu-dresden.de", 80));
        AnonClient.setLoginTimeout(40000);
        final AnonProxy anonProxy = new AnonProxy(serverSocket, null);
        final TorAnonServerDescription torParams = new TorAnonServerDescription("141.76.45.45", 9030, true);
        torParams.setMaxConnectionsPerRoute(100);
        torParams.setMaxRouteLen(2);
        anonProxy.setTorParams(torParams);
        SignatureVerifier.getInstance().setCheckSignatures(false);
        anonProxy.start(new SimpleMixCascadeContainer(new MixCascade("mix.inf.tu-dresden.de", 6544)));
        anonProxy.setDummyTraffic(60000);
        final Random random = new Random();
        Thread.sleep(20000L);
        LogHolder.log(7, LogType.TOR, "Stress test building connection");
        for (int i = 0; i < 1000; ++i) {
            new SocksHttpConnection("anon.inf.tu-dresden.de", 80, "/bl.gif");
            Thread.sleep(450L);
        }
    }
    
    private final class SocksHttpConnection implements Runnable
    {
        private String m_strHost;
        private int m_Port;
        private String m_strFile;
        
        SocksHttpConnection(final String strHost, final int port, final String strFile) {
            this.m_strHost = strHost;
            this.m_Port = port;
            this.m_strFile = strFile;
            final Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
        
        public void run() {
            try {
                final HTTPConnection httpConnection = HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(this.m_strHost, this.m_Port), new ProxyInterface("127.0.0.1", 4007, 3, null));
                httpConnection.setTimeout(60000);
                if (httpConnection.Get(this.m_strFile).getStatusCode() != 200) {
                    LogHolder.log(4, LogType.TOR, "Error getting Web page!");
                }
                else {
                    LogHolder.log(4, LogType.TOR, "Successfull getting Web page!");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                LogHolder.log(4, LogType.TOR, "Exception getting Web page! - " + ex.getMessage());
            }
        }
    }
}
