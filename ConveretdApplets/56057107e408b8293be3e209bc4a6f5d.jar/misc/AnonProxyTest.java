// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import anon.proxy.AnonProxy;
import anon.infoservice.AbstractMixCascadeContainer;
import anon.infoservice.SimpleMixCascadeContainer;
import anon.infoservice.MixCascade;
import anon.crypto.SignatureVerifier;
import anon.infoservice.IProxyInterfaceGetter;
import anon.infoservice.IMutableProxyInterface;
import anon.infoservice.ProxyInterface;
import anon.infoservice.ImmutableProxyInterface;
import anon.util.IPasswordReader;
import java.net.ServerSocket;
import logging.Log;
import logging.LogHolder;
import logging.LogType;
import logging.SystemErrLog;

public class AnonProxyTest
{
    public static void main(final String[] array) {
        try {
            final SystemErrLog systemErrLog = new SystemErrLog();
            systemErrLog.setLogType(LogType.ALL);
            systemErrLog.setLogLevel(7);
            LogHolder.setLogInstance(new SystemErrLog());
            final ServerSocket serverSocket = new ServerSocket(4005);
            final IMutableProxyInterface mutableProxyInterface = new IMutableProxyInterface() {
                private final /* synthetic */ ProxyInterface val$p = new ProxyInterface("141.76.45.36", 3128, 1, "sk13", new IPasswordReader() {
                    public String readPassword(final ImmutableProxyInterface immutableProxyInterface) {
                        return "654321";
                    }
                }, true, true);
                
                public IProxyInterfaceGetter getProxyInterface(final boolean b) {
                    return new IProxyInterfaceGetter() {
                        private final /* synthetic */ AnonProxyTest$2 this$0 = this$0;
                        
                        public ImmutableProxyInterface getProxyInterface() {
                            return this.this$0.val$p;
                        }
                    };
                }
            };
            final AnonProxy anonProxy = null;
            SignatureVerifier.getInstance().setCheckSignatures(false);
            anonProxy.start(new SimpleMixCascadeContainer(new MixCascade(null, null, "mix.inf.tu-dresden.de", 6544)));
            synchronized (anonProxy) {
                anonProxy.wait();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
