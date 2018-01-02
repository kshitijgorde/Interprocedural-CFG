// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import org.xml.sax.SAXException;
import org.xml.sax.AttributeList;
import java.net.URLConnection;
import java.util.Hashtable;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.EmptyStackException;
import java.io.IOException;
import java.util.Vector;
import java.net.MalformedURLException;
import java.util.Stack;
import java.net.URL;

public class XmlRpcClient implements XmlRpcHandler
{
    protected URL url;
    private String auth;
    protected Stack pool;
    protected int workers;
    protected int asyncWorkers;
    private CallData first;
    private CallData last;
    
    public XmlRpcClient(final URL url) {
        this.pool = new Stack();
        this.workers = 0;
        this.asyncWorkers = 0;
        this.url = url;
    }
    
    public XmlRpcClient(final String s) throws MalformedURLException {
        this.pool = new Stack();
        this.workers = 0;
        this.asyncWorkers = 0;
        this.url = new URL(s);
    }
    
    public XmlRpcClient(final String s, final int n) throws MalformedURLException {
        this.pool = new Stack();
        this.workers = 0;
        this.asyncWorkers = 0;
        this.url = new URL("http://" + s + ':' + n + "/RPC2");
    }
    
    public URL getURL() {
        return this.url;
    }
    
    public void setBasicAuthentication(final String s, final String s2) {
        if (s == null || s2 == null) {
            this.auth = null;
        }
        else {
            this.auth = new String(Base64.encode((s + ':' + s2).getBytes())).trim();
        }
    }
    
    public Object execute(final String s, final Vector vector) throws XmlRpcException, IOException {
        final Worker worker = this.getWorker(false);
        try {
            return worker.execute(s, vector);
        }
        finally {
            this.releaseWorker(worker, false);
        }
    }
    
    public void executeAsync(final String s, final Vector vector, final AsyncCallback asyncCallback) {
        if (this.asyncWorkers >= 4) {
            this.enqueue(s, vector, asyncCallback);
            return;
        }
        try {
            this.getWorker(true).start(s, vector, asyncCallback);
        }
        catch (IOException ex) {
            this.enqueue(s, vector, asyncCallback);
        }
    }
    
    synchronized Worker getWorker(final boolean b) throws IOException {
        try {
            final Worker worker = this.pool.pop();
            if (b) {
                ++this.asyncWorkers;
            }
            else {
                ++this.workers;
            }
            return worker;
        }
        catch (EmptyStackException ex) {
            if (this.workers < XmlRpc.getMaxThreads()) {
                if (b) {
                    ++this.asyncWorkers;
                }
                else {
                    ++this.workers;
                }
                return new Worker();
            }
            throw new IOException("XML-RPC System overload");
        }
    }
    
    synchronized void releaseWorker(final Worker worker, final boolean b) {
        worker.result = null;
        worker.call = null;
        if (this.pool.size() < 20 && !worker.fault) {
            this.pool.push(worker);
        }
        if (b) {
            --this.asyncWorkers;
        }
        else {
            --this.workers;
        }
    }
    
    synchronized void enqueue(final String s, final Vector vector, final AsyncCallback asyncCallback) {
        final CallData callData = new CallData(s, vector, asyncCallback);
        if (this.last == null) {
            final CallData callData2 = callData;
            this.last = callData2;
            this.first = callData2;
        }
        else {
            this.last.next = callData;
            this.last = callData;
        }
    }
    
    synchronized CallData dequeue() {
        if (this.first == null) {
            return null;
        }
        final CallData first = this.first;
        if (this.first == this.last) {
            final CallData callData = null;
            this.last = callData;
            this.first = callData;
        }
        else {
            this.first = this.first.next;
        }
        return first;
    }
    
    public static void main(final String[] array) throws Exception {
        try {
            final String s = array[0];
            final String s2 = array[1];
            final Vector<Integer> vector = new Vector<Integer>();
            for (int i = 2; i < array.length; ++i) {
                try {
                    vector.addElement(new Integer(Integer.parseInt(array[i])));
                }
                catch (NumberFormatException ex3) {
                    vector.addElement((Integer)array[i]);
                }
            }
            final XmlRpcClientLite xmlRpcClientLite = new XmlRpcClientLite(s);
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
    
    class CallData
    {
        String method;
        Vector params;
        AsyncCallback callback;
        CallData next;
        
        public CallData(final String method, final Vector params, final AsyncCallback callback) {
            this.method = method;
            this.params = params;
            this.callback = callback;
            this.next = null;
        }
    }
    
    class Worker extends XmlRpc implements Runnable
    {
        boolean fault;
        Object result;
        ByteArrayOutputStream buffer;
        CallData call;
        
        public Worker() {
            this.result = null;
        }
        
        public void start(final String s, final Vector vector, final AsyncCallback asyncCallback) {
            this.call = new CallData(s, vector, asyncCallback);
            new Thread(this).start();
        }
        
        public void run() {
            while (this.call != null) {
                this.executeAsync(this.call.method, this.call.params, this.call.callback);
                this.call = XmlRpcClient.this.dequeue();
            }
            XmlRpcClient.this.releaseWorker(this, true);
        }
        
        void executeAsync(final String s, final Vector vector, final AsyncCallback asyncCallback) {
            try {
                final Object execute = this.execute(s, vector);
                if (asyncCallback != null) {
                    asyncCallback.handleResult(execute, XmlRpcClient.this.url, s);
                }
            }
            catch (Exception ex) {
                if (asyncCallback != null) {
                    try {
                        asyncCallback.handleError(ex, XmlRpcClient.this.url, s);
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        
        Object execute(final String s, final Vector vector) throws XmlRpcException, IOException {
            this.fault = false;
            long currentTimeMillis = 0L;
            if (XmlRpc.debug) {
                System.err.println("Client calling procedure '" + s + "' with parameters " + vector);
                currentTimeMillis = System.currentTimeMillis();
            }
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (this.buffer == null) {
                    this.buffer = new ByteArrayOutputStream();
                }
                else {
                    this.buffer.reset();
                }
                final XmlWriter xmlWriter = new XmlWriter(this, this.buffer);
                this.writeRequest(xmlWriter, s, vector);
                xmlWriter.flush();
                final byte[] byteArray = this.buffer.toByteArray();
                final URLConnection openConnection = XmlRpcClient.this.url.openConnection();
                openConnection.setDoInput(true);
                openConnection.setDoOutput(true);
                openConnection.setUseCaches(false);
                openConnection.setAllowUserInteraction(false);
                openConnection.setRequestProperty("Content-Length", Integer.toString(byteArray.length));
                openConnection.setRequestProperty("Content-Type", "text/xml");
                if (XmlRpcClient.this.auth != null) {
                    openConnection.setRequestProperty("Authorization", "Basic " + XmlRpcClient.this.auth);
                }
                final OutputStream outputStream = openConnection.getOutputStream();
                outputStream.write(byteArray);
                outputStream.flush();
                outputStream.close();
                this.parse(openConnection.getInputStream());
            }
            catch (Exception ex) {
                if (XmlRpc.debug) {
                    ex.printStackTrace();
                }
                throw new IOException(ex.getMessage());
            }
            if (this.fault) {
                XmlRpcException ex2;
                try {
                    final Hashtable hashtable = (Hashtable)this.result;
                    ex2 = new XmlRpcException(Integer.parseInt(hashtable.get("faultCode").toString()), hashtable.get("faultString").trim());
                }
                catch (Exception ex3) {
                    throw new XmlRpcException(0, "Invalid fault response");
                }
                throw ex2;
            }
            if (XmlRpc.debug) {
                System.err.println("Spent " + (System.currentTimeMillis() - currentTimeMillis) + " in request");
            }
            return this.result;
        }
        
        void objectParsed(final Object result) {
            this.result = result;
        }
        
        void writeRequest(final XmlWriter xmlWriter, final String s, final Vector vector) throws IOException, XmlRpcException {
            xmlWriter.startElement("methodCall");
            xmlWriter.startElement("methodName");
            xmlWriter.write(s);
            xmlWriter.endElement("methodName");
            xmlWriter.startElement("params");
            for (int size = vector.size(), i = 0; i < size; ++i) {
                xmlWriter.startElement("param");
                xmlWriter.writeObject(vector.elementAt(i));
                xmlWriter.endElement("param");
            }
            xmlWriter.endElement("params");
            xmlWriter.endElement("methodCall");
        }
        
        public void startElement(final String s, final AttributeList list) throws SAXException {
            if ("fault".equals(s)) {
                this.fault = true;
            }
            else {
                super.startElement(s, list);
            }
        }
    }
}
