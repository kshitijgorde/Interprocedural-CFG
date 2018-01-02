// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.util.Hashtable;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.EmptyStackException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class XmlRpcClientLite extends XmlRpcClient
{
    static String auth;
    
    public XmlRpcClientLite(final URL url) {
        super(url);
    }
    
    public XmlRpcClientLite(final String s) throws MalformedURLException {
        super(s);
    }
    
    public XmlRpcClientLite(final String s, final int n) throws MalformedURLException {
        super(s, n);
    }
    
    synchronized Worker getWorker(final boolean b) throws IOException {
        try {
            final Worker worker = super.pool.pop();
            if (b) {
                ++super.asyncWorkers;
            }
            else {
                ++super.workers;
            }
            return worker;
        }
        catch (EmptyStackException ex) {
            if (super.workers < XmlRpc.getMaxThreads()) {
                if (b) {
                    ++super.asyncWorkers;
                }
                else {
                    ++super.workers;
                }
                return new LiteWorker();
            }
            throw new IOException("XML-RPC System overload");
        }
    }
    
    public static void main(final String[] array) throws Exception {
        try {
            final String s = array[0];
            final String s2 = array[1];
            final XmlRpcClientLite xmlRpcClientLite = new XmlRpcClientLite(s);
            final Vector<Integer> vector = new Vector<Integer>();
            for (int i = 2; i < array.length; ++i) {
                try {
                    vector.addElement(new Integer(Integer.parseInt(array[i])));
                }
                catch (NumberFormatException ex3) {
                    vector.addElement((Integer)array[i]);
                }
            }
            try {
                System.err.println(xmlRpcClientLite.execute(s2, vector));
            }
            catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        catch (Exception ex2) {
            System.err.println(ex2);
            System.err.println("Usage: java org.apache.xmlrpc.XmlRpcClient <url> <method> <arg> ....");
            System.err.println("Arguments are sent as integers or strings.");
        }
    }
    
    class HttpClient
    {
        String hostname;
        String host;
        int port;
        String uri;
        Socket socket;
        BufferedOutputStream output;
        BufferedInputStream input;
        boolean keepalive;
        byte[] buffer;
        
        public HttpClient(final URL url) throws IOException {
            this.socket = null;
            this.hostname = url.getHost();
            this.port = url.getPort();
            if (this.port < 1) {
                this.port = 80;
            }
            this.uri = url.getFile();
            if (this.uri == null || "".equals(this.uri)) {
                this.uri = "/";
            }
            this.host = ((this.port == 80) ? this.hostname : (this.hostname + ":" + this.port));
            this.initConnection();
        }
        
        protected void initConnection() throws IOException {
            this.socket = new Socket(this.hostname, this.port);
            this.output = new BufferedOutputStream(this.socket.getOutputStream());
            this.input = new BufferedInputStream(this.socket.getInputStream());
        }
        
        protected void closeConnection() {
            try {
                this.socket.close();
            }
            catch (Exception ex) {}
        }
        
        public InputStream sendRequest(final byte[] array) throws IOException {
            this.output.write(("POST " + this.uri + " HTTP/1.0\r\n").getBytes());
            this.output.write("User-Agent: Apache XML-RPC 1.0\r\n".getBytes());
            this.output.write(("Host: " + this.host + "\r\n").getBytes());
            if (XmlRpc.getKeepAlive()) {
                this.output.write("Connection: Keep-Alive\r\n".getBytes());
            }
            this.output.write("Content-Type: text/xml\r\n".getBytes());
            if (XmlRpcClientLite.auth != null) {
                this.output.write(("Authorization: Basic " + XmlRpcClientLite.auth + "\r\n").getBytes());
            }
            this.output.write(("Content-Length: " + array.length).getBytes());
            this.output.write("\r\n\r\n".getBytes());
            this.output.write(array);
            this.output.flush();
            final String line = this.readLine();
            if (XmlRpc.debug) {
                System.err.println(line);
            }
            int int1 = -1;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(line);
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken("\n\r");
                this.keepalive = (XmlRpc.getKeepAlive() && "HTTP/1.1".equals(nextToken));
                if (!"200".equals(nextToken2)) {
                    throw new IOException("Unexpected Response from Server: " + nextToken3);
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            catch (Exception ex2) {
                throw new IOException("Server returned invalid Response.");
            }
            String s;
            do {
                s = this.readLine();
                if (s != null) {
                    if (XmlRpc.debug) {
                        System.err.println(s);
                    }
                    s = s.toLowerCase();
                    if (s.startsWith("content-length:")) {
                        int1 = Integer.parseInt(s.substring(15).trim());
                    }
                    if (!s.startsWith("connection:")) {
                        continue;
                    }
                    this.keepalive = (XmlRpc.getKeepAlive() && s.indexOf("keep-alive") > -1);
                }
            } while (s != null && !s.equals(""));
            return new ServerInputStream(this.input, int1);
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
        
        protected void finalize() throws Throwable {
            this.closeConnection();
        }
    }
    
    class LiteWorker extends Worker implements Runnable
    {
        HttpClient client;
        
        public LiteWorker() {
            this.client = null;
        }
        
        Object execute(final String s, final Vector vector) throws XmlRpcException, IOException {
            final long currentTimeMillis = System.currentTimeMillis();
            super.fault = false;
            try {
                if (super.buffer == null) {
                    super.buffer = new ByteArrayOutputStream();
                }
                else {
                    super.buffer.reset();
                }
                final XmlWriter xmlWriter = new XmlWriter(this, super.buffer);
                this.writeRequest(xmlWriter, s, vector);
                xmlWriter.flush();
                final byte[] byteArray = super.buffer.toByteArray();
                if (this.client == null) {
                    this.client = new HttpClient(XmlRpcClientLite.this.url);
                }
                InputStream inputStream;
                try {
                    inputStream = this.client.sendRequest(byteArray);
                }
                catch (IOException ex) {
                    if (!this.client.keepalive) {
                        throw ex;
                    }
                    this.client.closeConnection();
                    this.client.initConnection();
                    inputStream = this.client.sendRequest(byteArray);
                }
                this.parse(inputStream);
                if (!this.client.keepalive) {
                    this.client.closeConnection();
                    this.client = null;
                }
                if (XmlRpc.debug) {
                    System.err.println("result = " + super.result);
                }
                if (super.errorLevel == 2) {
                    throw new Exception(super.errorMsg);
                }
            }
            catch (IOException ex2) {
                throw ex2;
            }
            catch (Exception ex3) {
                if (XmlRpc.debug) {
                    ex3.printStackTrace();
                }
                String s2 = ex3.getMessage();
                if (s2 == null || s2.length() == 0) {
                    s2 = ex3.toString();
                }
                throw new IOException(s2);
            }
            if (super.fault) {
                XmlRpcException ex4;
                try {
                    final Hashtable hashtable = (Hashtable)super.result;
                    ex4 = new XmlRpcException(Integer.parseInt(hashtable.get("faultCode").toString()), hashtable.get("faultString").trim());
                }
                catch (Exception ex5) {
                    throw new XmlRpcException(0, "Server returned an invalid fault response.");
                }
                throw ex4;
            }
            if (XmlRpc.debug) {
                System.err.println("Spent " + (System.currentTimeMillis() - currentTimeMillis) + " millis in request");
            }
            return super.result;
        }
    }
}
