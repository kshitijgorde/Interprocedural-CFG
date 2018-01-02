// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.util.StringTokenizer;
import java.util.EmptyStackException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.net.ServerSocket;

public class WebServer implements Runnable
{
    protected XmlRpcServer xmlrpc;
    protected ServerSocket serverSocket;
    protected int port;
    protected Thread listener;
    protected boolean paranoid;
    protected Vector accept;
    protected Vector deny;
    protected Stack threadpool;
    protected ThreadGroup runners;
    protected static final byte[] ctype;
    protected static final byte[] clength;
    protected static final byte[] newline;
    protected static final byte[] doubleNewline;
    protected static final byte[] conkeep;
    protected static final byte[] conclose;
    protected static final byte[] ok;
    protected static final byte[] server;
    private static final String HTTP_11 = "HTTP/1.1";
    private static final String STAR = "*";
    static /* synthetic */ Class class$java$lang$Math;
    static /* synthetic */ Class class$org$apache$xmlrpc$WebServer;
    
    public static void main(final String[] array) {
        System.err.println("Usage: java " + ((WebServer.class$org$apache$xmlrpc$WebServer == null) ? (WebServer.class$org$apache$xmlrpc$WebServer = class$("org.apache.xmlrpc.WebServer")) : WebServer.class$org$apache$xmlrpc$WebServer).getName() + " <port>");
        int int1 = 8080;
        if (array.length > 0) {
            try {
                int1 = Integer.parseInt(array[0]);
            }
            catch (NumberFormatException ex2) {
                System.err.println("Error parsing port number: " + array[0]);
            }
        }
        XmlRpc.setKeepAlive(true);
        try {
            final WebServer webServer = new WebServer(int1);
            webServer.addHandler("string", "Welcome to XML-RPC!");
            webServer.addHandler("math", (WebServer.class$java$lang$Math == null) ? (WebServer.class$java$lang$Math = class$("java.lang.Math")) : WebServer.class$java$lang$Math);
            webServer.addHandler("auth", new AuthDemo());
            webServer.addHandler("$default", new Echo());
            webServer.addHandler("mttf", new XmlRpcClient("http://www.mailtothefuture.com:80/RPC2"));
            System.err.println("started web server on port " + int1);
        }
        catch (IOException ex) {
            System.err.println("Error creating web server: " + ex);
        }
    }
    
    public WebServer(final int n) throws IOException {
        this(n, null);
    }
    
    public WebServer(final int port, final InetAddress inetAddress) throws IOException {
        this.port = port;
        this.xmlrpc = new XmlRpcServer();
        this.accept = new Vector();
        this.deny = new Vector();
        this.threadpool = new Stack();
        this.runners = new ThreadGroup("XML-RPC Runner");
        try {
            this.setupServerSocket(port, 50, inetAddress);
        }
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
        this.start();
    }
    
    protected ServerSocket createServerSocket(final int n, final int n2, final InetAddress inetAddress) throws Exception {
        return new ServerSocket(n, n2, inetAddress);
    }
    
    public void setupServerSocket(final int n, final int n2, final InetAddress inetAddress) throws Exception {
        (this.serverSocket = this.createServerSocket(n, n2, inetAddress)).setSoTimeout(4096);
    }
    
    public void start() {
        (this.listener = new Thread(this, "XML-RPC Weblistener")).start();
    }
    
    public void addHandler(final String s, final Object o) {
        this.xmlrpc.addHandler(s, o);
    }
    
    public void removeHandler(final String s) {
        this.xmlrpc.removeHandler(s);
    }
    
    public void setParanoid(final boolean paranoid) {
        this.paranoid = paranoid;
    }
    
    public void acceptClient(final String s) throws IllegalArgumentException {
        try {
            this.accept.addElement(new AddressMatcher(s));
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("\"" + s + "\" does not represent a valid IP address");
        }
    }
    
    public void denyClient(final String s) throws IllegalArgumentException {
        try {
            this.deny.addElement(new AddressMatcher(s));
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("\"" + s + "\" does not represent a valid IP address");
        }
    }
    
    protected boolean checkSocket(final Socket socket) {
        final int size = this.deny.size();
        final byte[] address = socket.getInetAddress().getAddress();
        for (int i = 0; i < size; ++i) {
            if (((AddressMatcher)this.deny.elementAt(i)).matches(address)) {
                return false;
            }
        }
        for (int size2 = this.accept.size(), j = 0; j < size2; ++j) {
            if (((AddressMatcher)this.accept.elementAt(j)).matches(address)) {
                return true;
            }
        }
        return false;
    }
    
    public void run() {
        try {
            while (this.listener != null) {
                try {
                    final Socket accept = this.serverSocket.accept();
                    if (!this.paranoid || this.checkSocket(accept)) {
                        this.getRunner().handle(accept);
                    }
                    else {
                        accept.close();
                    }
                }
                catch (InterruptedIOException ex3) {}
                catch (Exception ex) {
                    System.err.println("Exception in XML-RPC listener loop (" + ex + ").");
                }
                catch (Error error) {
                    System.err.println("Error in XML-RPC listener loop (" + error + ").");
                }
            }
        }
        catch (Exception ex2) {
            System.err.println("Error accepting XML-RPC connections (" + ex2 + ").");
        }
        finally {
            System.err.println("Closing XML-RPC server socket.");
            try {
                this.serverSocket.close();
                this.serverSocket = null;
            }
            catch (IOException ex4) {}
        }
    }
    
    public void shutdown() {
        if (this.listener != null) {
            final Thread listener = this.listener;
            this.listener = null;
            listener.interrupt();
        }
        if (this.runners != null) {
            final ThreadGroup runners = this.runners;
            this.runners = null;
        }
    }
    
    protected Runner getRunner() {
        try {
            return this.threadpool.pop();
        }
        catch (EmptyStackException ex) {
            if (this.runners.activeCount() > 255) {
                throw new RuntimeException("System overload");
            }
            return new Runner();
        }
    }
    
    void releaseRunner(final Runner runner) {
        this.threadpool.push(runner);
    }
    
    static {
        ctype = "Content-Type: text/xml\r\n".getBytes();
        clength = "Content-Length: ".getBytes();
        newline = "\r\n".getBytes();
        doubleNewline = "\r\n\r\n".getBytes();
        conkeep = "Connection: Keep-Alive\r\n".getBytes();
        conclose = "Connection: close\r\n".getBytes();
        ok = " 200 OK\r\n".getBytes();
        server = "Server: Apache XML-RPC 1.0\r\n".getBytes();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class AddressMatcher
    {
        int[] pattern;
        
        public AddressMatcher(final String s) throws Exception {
            this.pattern = new int[4];
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
            if (stringTokenizer.countTokens() != 4) {
                throw new Exception("\"" + s + "\" does not represent a valid IP address");
            }
            for (int i = 0; i < 4; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                if ("*".equals(nextToken)) {
                    this.pattern[i] = 256;
                }
                else {
                    this.pattern[i] = (byte)Integer.parseInt(nextToken);
                }
            }
        }
        
        public boolean matches(final byte[] array) {
            for (int i = 0; i < 4; ++i) {
                if (this.pattern[i] <= 255) {
                    if (this.pattern[i] != array[i]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    class Connection implements Runnable
    {
        private Socket socket;
        private BufferedInputStream input;
        private BufferedOutputStream output;
        private String user;
        private String password;
        byte[] buffer;
        
        public Connection(final Socket socket) throws IOException {
            socket.setSoTimeout(30000);
            this.socket = socket;
            this.input = new BufferedInputStream(socket.getInputStream());
            this.output = new BufferedOutputStream(socket.getOutputStream());
        }
        
        public void run() {
            try {
                int i;
                do {
                    final String s = null;
                    this.password = s;
                    this.user = s;
                    String s2 = this.readLine();
                    if (s2 != null && s2.length() == 0) {
                        s2 = this.readLine();
                    }
                    if (XmlRpc.debug) {
                        System.err.println(s2);
                    }
                    int int1 = -1;
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2);
                    final String nextToken = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    final String nextToken2 = stringTokenizer.nextToken();
                    i = ((XmlRpc.getKeepAlive() && "HTTP/1.1".equals(nextToken2)) ? 1 : 0);
                    String line;
                    do {
                        line = this.readLine();
                        if (line != null) {
                            if (XmlRpc.debug) {
                                System.err.println(line);
                            }
                            final String lowerCase = line.toLowerCase();
                            if (lowerCase.startsWith("content-length:")) {
                                int1 = Integer.parseInt(line.substring(15).trim());
                            }
                            if (lowerCase.startsWith("connection:")) {
                                i = ((XmlRpc.getKeepAlive() && lowerCase.indexOf("keep-alive") > -1) ? 1 : 0);
                            }
                            if (!lowerCase.startsWith("authorization: basic ")) {
                                continue;
                            }
                            this.parseAuth(line);
                        }
                    } while (line != null && line.length() != 0);
                    if ("POST".equalsIgnoreCase(nextToken)) {
                        final byte[] execute = WebServer.this.xmlrpc.execute(new ServerInputStream(this.input, int1), this.user, this.password);
                        this.output.write(nextToken2.getBytes());
                        this.output.write(WebServer.ok);
                        this.output.write(WebServer.server);
                        if (i != 0) {
                            this.output.write(WebServer.conkeep);
                        }
                        else {
                            this.output.write(WebServer.conclose);
                        }
                        this.output.write(WebServer.ctype);
                        this.output.write(WebServer.clength);
                        this.output.write(Integer.toString(execute.length).getBytes());
                        this.output.write(WebServer.doubleNewline);
                        this.output.write(execute);
                        this.output.flush();
                    }
                    else {
                        this.output.write(nextToken2.getBytes());
                        this.output.write(" 400 Bad Request\r\n".getBytes());
                        this.output.write(WebServer.server);
                        this.output.write("\r\n".getBytes());
                        this.output.write(("Method " + nextToken + " not implemented (try POST)").getBytes());
                        this.output.flush();
                        i = 0;
                    }
                } while (i != 0);
            }
            catch (Exception ex) {
                if (XmlRpc.debug) {
                    System.err.println(ex);
                    ex.printStackTrace();
                }
            }
            finally {
                try {
                    if (this.socket != null) {
                        this.socket.close();
                    }
                }
                catch (IOException ex2) {}
            }
        }
        
        private String readLine() throws IOException {
            if (this.buffer == null) {
                this.buffer = new byte[2048];
            }
            int i = 0;
            do {
                final int read = this.input.read();
                if (read < 0 || read == 10) {
                    return new String(this.buffer, 0, i);
                }
                if (read == 13) {
                    continue;
                }
                this.buffer[i++] = (byte)read;
            } while (i < this.buffer.length);
            throw new IOException("HTTP Header too long");
        }
        
        private void parseAuth(final String s) {
            try {
                final String s2 = new String(Base64.decode(s.substring(21).getBytes()));
                final int index = s2.indexOf(58);
                this.user = s2.substring(0, index);
                this.password = s2.substring(index + 1);
            }
            catch (Throwable t) {}
        }
    }
    
    class Runner implements Runnable
    {
        Thread thread;
        Connection con;
        int count;
        
        public synchronized void handle(final Socket socket) throws IOException {
            this.con = new Connection(socket);
            this.count = 0;
            if (this.thread == null || !this.thread.isAlive()) {
                (this.thread = new Thread(WebServer.this.runners, this)).start();
            }
            else {
                this.notify();
            }
        }
        
        public void run() {
            while (this.con != null && Thread.currentThread() == this.thread) {
                this.con.run();
                ++this.count;
                this.con = null;
                if (this.count > 200 || WebServer.this.threadpool.size() > 20) {
                    return;
                }
                synchronized (this) {
                    WebServer.this.releaseRunner(this);
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
