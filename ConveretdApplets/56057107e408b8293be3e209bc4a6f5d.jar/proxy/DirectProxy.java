// 
// Decompiled by Procyon v0.5.30
// 

package proxy;

import anon.util.JAPMessages;
import jap.JAPConfAnonGeneral;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;
import anon.infoservice.ImmutableProxyInterface;
import java.net.Socket;
import jap.JAPModel;
import java.io.PushbackInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import java.util.Hashtable;
import anon.AnonServiceFactory;
import anon.infoservice.MixCascade;
import anon.terms.TermsAndConditionConfirmation;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import anon.infoservice.IMutableProxyInterface;
import anon.AnonServiceEventListener;
import java.net.ConnectException;
import java.io.InputStream;
import java.io.OutputStream;
import anon.AnonChannel;
import java.net.ServerSocket;
import anon.AnonService;

public final class DirectProxy implements Runnable, AnonService
{
    private static final String GENERAL_RULE = "*";
    private static AllowProxyConnectionCallback ms_callback;
    private AnonService m_tor;
    private ServerSocket m_socketListener;
    private final Object THREAD_SYNC;
    private volatile Thread threadRunLoop;
    
    public DirectProxy(final ServerSocket socketListener) {
        this.THREAD_SYNC = new Object();
        this.m_socketListener = socketListener;
    }
    
    public static void setAllowUnprotectedConnectionCallback(final AllowProxyConnectionCallback ms_callback) {
        DirectProxy.ms_callback = ms_callback;
    }
    
    public AnonChannel createChannel(final int n) throws ConnectException {
        return new AnonChannel() {
            public boolean isClosed() {
                return false;
            }
            
            public void close() {
            }
            
            public OutputStream getOutputStream() {
                return null;
            }
            
            public InputStream getInputStream() {
                return null;
            }
            
            public int getOutputBlockSize() {
                return 1;
            }
        };
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListeners() {
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public int setProxy(final IMutableProxyInterface mutableProxyInterface) {
        return 0;
    }
    
    public boolean isConnected() {
        synchronized (this.THREAD_SYNC) {
            if (this.m_tor != null) {
                return this.m_tor.isConnected() && this.threadRunLoop != null;
            }
            return this.threadRunLoop != null;
        }
    }
    
    public int initialize(final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        if (!(anonServerDescription instanceof MixCascade) || anonServerDescription == null) {
            return -5;
        }
        this.m_tor = AnonServiceFactory.getAnonServiceInstance("TOR");
        return 0;
    }
    
    public synchronized boolean startService() {
        if (this.m_socketListener == null) {
            return false;
        }
        synchronized (this.THREAD_SYNC) {
            if (this.threadRunLoop != null && this.threadRunLoop.isAlive()) {
                return true;
            }
            this.shutdown(true);
            (this.threadRunLoop = new Thread(this, "JAP - Direct Proxy")).setDaemon(true);
            this.threadRunLoop.start();
            return true;
        }
    }
    
    public void run() {
        final Hashtable hashtable = new Hashtable<Object, RememberedRequestRight>();
        try {
            this.m_socketListener.setSoTimeout(2000);
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.NET, "Could not set accept time out!", ex);
        }
        while (!Thread.currentThread().isInterrupted()) {
            Socket accept;
            try {
                accept = this.m_socketListener.accept();
            }
            catch (InterruptedIOException ex5) {
                Thread.yield();
                continue;
            }
            catch (SocketException ex2) {
                LogHolder.log(3, LogType.NET, "Accept socket exception: " + ex2);
                break;
            }
            catch (IOException ex3) {
                LogHolder.log(2, LogType.NET, "Socket could not accept!" + ex3);
                break;
            }
            try {
                accept.setSoTimeout(0);
            }
            catch (SocketException ex4) {
                LogHolder.log(3, LogType.NET, "Could not set socket to blocking mode! Excpetion: " + ex4);
                continue;
            }
            PushbackInputStream pushbackInputStream = null;
            try {
                pushbackInputStream = new PushbackInputStream(accept.getInputStream(), 200);
            }
            catch (IOException ex6) {}
            final RequestInfo uri = DirectProxyConnection.getURI(pushbackInputStream, 200);
            RememberedRequestRight rememberedRequestRight = hashtable.get("*");
            if (rememberedRequestRight == null && !JAPModel.getInstance().isAskForAnyNonAnonymousRequest()) {
                hashtable.clear();
            }
            if (rememberedRequestRight != null && rememberedRequestRight.isTimedOut()) {
                hashtable.remove("*");
                rememberedRequestRight = null;
            }
            if (rememberedRequestRight == null) {
                rememberedRequestRight = hashtable.get(uri.getURI());
            }
            if (rememberedRequestRight != null && rememberedRequestRight.isTimedOut()) {
                hashtable.remove(uri.getURI());
                rememberedRequestRight = null;
            }
            if (rememberedRequestRight == null) {
                final AllowProxyConnectionCallback ms_callback = DirectProxy.ms_callback;
                AllowProxyConnectionCallback.Answer callback;
                if (ms_callback != null) {
                    callback = ms_callback.callback(uri);
                }
                else {
                    callback = new AllowProxyConnectionCallback.Answer(false, false);
                }
                if (JAPModel.getInstance().isAskForAnyNonAnonymousRequest() && !callback.isRemembered()) {
                    rememberedRequestRight = new RememberedRequestRight(uri.getURI(), !callback.isAllowed(), false);
                    hashtable.put(uri.getURI(), rememberedRequestRight);
                }
                else {
                    rememberedRequestRight = new RememberedRequestRight("*", !callback.isAllowed(), false);
                    hashtable.clear();
                    hashtable.put("*", rememberedRequestRight);
                }
            }
            if (!rememberedRequestRight.isWarningShown() && !JAPModel.isSmallDisplay()) {
                if (this.getProxyInterface() != null && this.getProxyInterface().isValid() && this.getProxyInterface().getProtocol() == 1) {
                    new Thread(new DirectConViaHTTPProxy(accept, pushbackInputStream)).start();
                }
                else {
                    new DirectProxyConnection(accept, pushbackInputStream, this);
                }
            }
            else {
                new Thread(new SendAnonWarning(accept, pushbackInputStream, (hashtable.size() > 0) ? rememberedRequestRight : null)).start();
            }
        }
        LogHolder.log(6, LogType.NET, "Direct Proxy Server stopped.");
    }
    
    public synchronized void shutdown(final boolean b) {
        synchronized (this.THREAD_SYNC) {
            if (this.threadRunLoop == null) {
                return;
            }
            int n = 0;
            while (this.threadRunLoop.isAlive()) {
                this.threadRunLoop.interrupt();
                Thread.yield();
                try {
                    this.threadRunLoop.join(1000L);
                }
                catch (InterruptedException ex) {}
                if (n > 3) {
                    try {
                        this.threadRunLoop.stop();
                    }
                    catch (Exception ex2) {}
                }
                ++n;
            }
            this.threadRunLoop = null;
        }
    }
    
    protected ImmutableProxyInterface getProxyInterface() {
        if (this.m_tor != null) {
            return JAPModel.getInstance().getTorProxyInterface();
        }
        return JAPModel.getInstance().getProxyInterface();
    }
    
    public static class RequestInfo
    {
        private String m_strURI;
        private String m_strMethod;
        private int m_port;
        
        protected RequestInfo(final String strURI, final String strMethod, final int port) {
            this.m_strURI = strURI;
            this.m_strMethod = strMethod;
            this.m_port = port;
        }
        
        public String getURI() {
            return this.m_strURI;
        }
        
        public String getMethod() {
            return this.m_strMethod;
        }
        
        public int getPort() {
            return this.m_port;
        }
    }
    
    public abstract static class AllowProxyConnectionCallback
    {
        public abstract Answer callback(final RequestInfo p0);
        
        public static class Answer
        {
            private boolean m_bRemembered;
            private boolean m_bAllow;
            
            public Answer(final boolean bAllow, final boolean bRemembered) {
                this.m_bAllow = bAllow;
                this.m_bRemembered = bRemembered;
            }
            
            public boolean isRemembered() {
                return this.m_bRemembered;
            }
            
            public boolean isAllowed() {
                return this.m_bAllow;
            }
        }
    }
    
    private static final class SendAnonWarning implements Runnable
    {
        private static final String MSG_BLOCKED;
        private static final String MSG_BLOCKED_ALL;
        private static final String MSG_BLOCKED_DOMAIN;
        private static final String MSG_COUNTDOWN;
        private static final String MSG_RELOAD;
        private static final String MSG_BLOCKED_PERMANENTLY;
        private static final String MSG_ANON_MODE_OFF;
        private Socket s;
        private SimpleDateFormat dateFormatHTTP;
        private InputStream m_clientInputStream;
        private RememberedRequestRight m_requestRight;
        static /* synthetic */ Class class$proxy$DirectProxy$SendAnonWarning;
        
        public SendAnonWarning(final Socket s, final InputStream clientInputStream, final RememberedRequestRight requestRight) {
            this.s = s;
            this.m_requestRight = requestRight;
            this.m_clientInputStream = clientInputStream;
            (this.dateFormatHTTP = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US)).setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        
        public void run() {
            try {
                if (this.m_clientInputStream != null) {
                    this.m_clientInputStream.read();
                }
                else {
                    this.s.getInputStream().read();
                }
            }
            catch (IOException ex2) {}
            try {
                final String format = this.dateFormatHTTP.format(new Date());
                final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
                bufferedWriter.write("HTTP/1.1 200 OK\r\n");
                bufferedWriter.write("Content-type: text/html\r\n");
                bufferedWriter.write("Expires: " + format + "\r\n");
                bufferedWriter.write("Date: " + format + "\r\n");
                bufferedWriter.write("Pragma: no-cache\r\n");
                bufferedWriter.write("Cache-Control: no-cache\r\n\r\n");
                bufferedWriter.write("<HTML><TITLE>JAP</TITLE>\n");
                bufferedWriter.write("<PRE>" + format + "</PRE>\n");
                if (this.m_requestRight == null) {
                    bufferedWriter.write(JAPMessages.getString(SendAnonWarning.MSG_ANON_MODE_OFF, JAPMessages.getString(JAPConfAnonGeneral.MSG_DENY_NON_ANONYMOUS_SURFING)));
                }
                else {
                    final long countDown = this.m_requestRight.getCountDown();
                    String s;
                    if (this.m_requestRight.getURI().equals("*")) {
                        s = JAPMessages.getString(SendAnonWarning.MSG_BLOCKED_ALL);
                    }
                    else {
                        s = JAPMessages.getString(SendAnonWarning.MSG_BLOCKED_DOMAIN, "<code>" + this.m_requestRight.getURI() + "</code>");
                    }
                    String[] array;
                    if (countDown == Long.MAX_VALUE) {
                        array = new String[] { s, JAPMessages.getString(SendAnonWarning.MSG_BLOCKED_PERMANENTLY) + "<BR>" + JAPMessages.getString(SendAnonWarning.MSG_RELOAD) };
                    }
                    else if (countDown / 1000L == 0L) {
                        array = new String[] { s, JAPMessages.getString(SendAnonWarning.MSG_RELOAD) };
                    }
                    else {
                        array = new String[] { s, JAPMessages.getString(SendAnonWarning.MSG_COUNTDOWN, new String[] { "" + countDown / 1000L, "<BR>" + JAPMessages.getString(SendAnonWarning.MSG_RELOAD) }) };
                    }
                    bufferedWriter.write(JAPMessages.getString(SendAnonWarning.MSG_BLOCKED, array));
                }
                bufferedWriter.write("</HTML>\n");
                bufferedWriter.flush();
                bufferedWriter.close();
                this.s.close();
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.NET, ex);
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
        
        static {
            MSG_BLOCKED = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_blocked";
            MSG_BLOCKED_ALL = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_blockedAll";
            MSG_BLOCKED_DOMAIN = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_blockedDomain";
            MSG_COUNTDOWN = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_countdown";
            MSG_RELOAD = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_reload";
            MSG_BLOCKED_PERMANENTLY = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_blockedPermanently";
            MSG_ANON_MODE_OFF = ((SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning == null) ? (SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning = class$("proxy.DirectProxy$SendAnonWarning")) : SendAnonWarning.class$proxy$DirectProxy$SendAnonWarning).getName() + "_htmlAnonModeOff";
        }
    }
    
    private final class RememberedRequestRight
    {
        public static final boolean REMEMBER_WARNING = true;
        public static final boolean REMEMBER_NO_WARNING = false;
        public static final boolean SET_TIMEOUT = true;
        public static final boolean SET_UNLIMITED = false;
        private static final long TEMPORARY_REMEMBER_TIME = 60000L;
        private static final long TEMPORARY_REMEMBER_TIME_NO_WARNING = 300000L;
        private long m_timeRemembered;
        private boolean m_bWarn;
        private String m_URI;
        
        public RememberedRequestRight(final String uri, final boolean bWarn, final boolean b) {
            this.m_URI = uri;
            if (b) {
                if (bWarn) {
                    this.m_timeRemembered = System.currentTimeMillis() + 60000L;
                }
                else {
                    this.m_timeRemembered = System.currentTimeMillis() + 300000L;
                }
            }
            else {
                this.m_timeRemembered = Long.MAX_VALUE;
            }
            this.m_bWarn = bWarn;
        }
        
        public String getURI() {
            return this.m_URI;
        }
        
        public boolean isWarningShown() {
            return this.m_bWarn;
        }
        
        public long getCountDown() {
            if (this.m_timeRemembered == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            long n = this.m_timeRemembered - System.currentTimeMillis();
            if (n < 0L) {
                n = 0L;
            }
            return n;
        }
        
        public boolean isTimedOut() {
            return this.m_timeRemembered < System.currentTimeMillis();
        }
    }
    
    private final class DirectConViaHTTPProxy implements Runnable
    {
        private Socket m_clientSocket;
        private InputStream m_clientInputStream;
        
        public DirectConViaHTTPProxy(final Socket clientSocket, final InputStream clientInputStream) {
            this.m_clientSocket = clientSocket;
            this.m_clientInputStream = clientInputStream;
        }
        
        public void run() {
            try {
                InputStream inputStream;
                if (this.m_clientInputStream != null) {
                    inputStream = this.m_clientInputStream;
                }
                else {
                    inputStream = this.m_clientSocket.getInputStream();
                }
                final Socket socket = new Socket(DirectProxy.this.getProxyInterface().getHost(), DirectProxy.this.getProxyInterface().getPort());
                final Thread thread = new Thread(new DirectProxyResponse(socket.getInputStream(), this.m_clientSocket.getOutputStream()), "JAP - DirectProxyResponse");
                thread.start();
                final OutputStream outputStream = socket.getOutputStream();
                if (DirectProxy.this.getProxyInterface().isAuthenticationUsed()) {
                    outputStream.write((DirectProxyConnection.readLine(inputStream) + "\r\n").getBytes());
                    outputStream.write(DirectProxy.this.getProxyInterface().getProxyAuthorizationHeaderAsString().getBytes());
                    outputStream.flush();
                }
                final byte[] array = new byte[1000];
                int read;
                while ((read = inputStream.read(array)) != -1) {
                    if (read > 0) {
                        outputStream.write(array, 0, read);
                        outputStream.flush();
                    }
                }
                thread.join();
                outputStream.close();
                inputStream.close();
                socket.close();
            }
            catch (IOException ex2) {}
            catch (Exception ex) {
                LogHolder.log(2, LogType.NET, "JAPDirectConViaProxy: Exception: " + ex);
            }
        }
    }
}
