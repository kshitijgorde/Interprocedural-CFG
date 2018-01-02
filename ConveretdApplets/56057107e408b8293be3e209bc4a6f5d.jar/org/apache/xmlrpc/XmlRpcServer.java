// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import java.util.EmptyStackException;
import java.io.InputStream;
import java.util.Stack;
import java.util.Hashtable;

public class XmlRpcServer
{
    private static final byte[] EMPTY_BYTE_ARRAY;
    private Hashtable handlers;
    private Stack pool;
    private int workers;
    
    public XmlRpcServer() {
        this.handlers = new Hashtable();
        this.pool = new Stack();
        this.workers = 0;
    }
    
    public void addHandler(final String s, final Object o) {
        if (o instanceof XmlRpcHandler || o instanceof AuthenticatedXmlRpcHandler) {
            this.handlers.put(s, o);
        }
        else if (o != null) {
            this.handlers.put(s, new Invoker(o));
        }
    }
    
    public void removeHandler(final String s) {
        this.handlers.remove(s);
    }
    
    public byte[] execute(final InputStream inputStream) {
        return this.execute(inputStream, null, null);
    }
    
    public byte[] execute(final InputStream inputStream, final String s, final String s2) {
        final Worker worker = this.getWorker();
        final byte[] execute = worker.execute(inputStream, s, s2);
        this.pool.push(worker);
        return execute;
    }
    
    private final Worker getWorker() {
        try {
            return this.pool.pop();
        }
        catch (EmptyStackException ex) {
            final int maxThreads = XmlRpc.getMaxThreads();
            if (this.workers < maxThreads) {
                ++this.workers;
                if (this.workers >= maxThreads * 0.95) {
                    System.err.println("95% of XML-RPC server threads in use");
                }
                return new Worker();
            }
            throw new RuntimeException("System overload");
        }
    }
    
    static {
        EMPTY_BYTE_ARRAY = new byte[0];
    }
    
    class Worker extends XmlRpc
    {
        private Vector inParams;
        private ByteArrayOutputStream buffer;
        private XmlWriter writer;
        
        protected Worker() {
            this.inParams = new Vector();
            this.buffer = new ByteArrayOutputStream();
        }
        
        public byte[] execute(final InputStream inputStream, final String s, final String s2) {
            try {
                return this.executeInternal(inputStream, s, s2);
            }
            finally {
                this.buffer.reset();
                this.inParams.removeAllElements();
            }
        }
        
        private byte[] executeInternal(final InputStream inputStream, final String s, final String s2) {
            long currentTimeMillis = 0L;
            if (XmlRpc.debug) {
                currentTimeMillis = System.currentTimeMillis();
            }
            byte[] array;
            try {
                this.parse(inputStream);
                if (XmlRpc.debug) {
                    System.err.println("method name: " + super.methodName);
                    System.err.println("inparams: " + this.inParams);
                }
                if (super.errorLevel > 0) {
                    throw new Exception(super.errorMsg);
                }
                Object o = null;
                String substring = null;
                final int lastIndex = super.methodName.lastIndexOf(46);
                if (lastIndex > -1) {
                    substring = super.methodName.substring(0, lastIndex);
                    o = XmlRpcServer.this.handlers.get(substring);
                    if (o != null) {
                        super.methodName = super.methodName.substring(lastIndex + 1);
                    }
                }
                if (o == null) {
                    o = XmlRpcServer.this.handlers.get("$default");
                }
                if (o == null) {
                    if (lastIndex > -1) {
                        throw new Exception("RPC handler object \"" + substring + "\" not found and no default handler registered.");
                    }
                    throw new Exception("RPC handler object not found for \"" + super.methodName + "\": no default handler registered.");
                }
                else {
                    Object o2;
                    if (o instanceof AuthenticatedXmlRpcHandler) {
                        o2 = ((AuthenticatedXmlRpcHandler)o).execute(super.methodName, this.inParams, s, s2);
                    }
                    else {
                        o2 = ((XmlRpcHandler)o).execute(super.methodName, this.inParams);
                    }
                    if (XmlRpc.debug) {
                        System.err.println("outparam = " + o2);
                    }
                    this.writeResponse(o2, this.writer = new XmlWriter(this, this.buffer));
                    this.writer.flush();
                    array = this.buffer.toByteArray();
                }
            }
            catch (Exception ex) {
                if (XmlRpc.debug) {
                    ex.printStackTrace();
                }
                this.buffer.reset();
                this.writer = null;
                try {
                    this.writer = new XmlWriter(this, this.buffer);
                }
                catch (UnsupportedEncodingException ex2) {
                    System.err.println("XmlRpcServer attempted to use unsupported encoding: " + ex2);
                }
                catch (IOException ex3) {
                    System.err.println("XmlRpcServer experienced I/O error writing error response: " + ex3);
                }
                final String string = ex.toString();
                final int n = (ex instanceof XmlRpcException) ? ((XmlRpcException)ex).code : 0;
                try {
                    this.writeError(n, string, this.writer);
                    this.writer.flush();
                }
                catch (Exception ex4) {
                    System.err.println("Unable to send error response to client: " + ex4);
                }
                if (this.writer != null) {
                    array = this.buffer.toByteArray();
                }
                else {
                    array = XmlRpcServer.EMPTY_BYTE_ARRAY;
                }
                if (this.writer != null) {
                    try {
                        this.writer.close();
                    }
                    catch (IOException ex5) {
                        System.err.println("Exception closing output stream: " + ex5);
                    }
                }
            }
            finally {
                if (this.writer != null) {
                    try {
                        this.writer.close();
                    }
                    catch (IOException ex6) {
                        System.err.println("Exception closing output stream: " + ex6);
                    }
                }
            }
            if (XmlRpc.debug) {
                System.err.println("Spent " + (System.currentTimeMillis() - currentTimeMillis) + " millis in request");
            }
            return array;
        }
        
        void objectParsed(final Object o) {
            this.inParams.addElement(o);
        }
        
        void writeResponse(final Object o, final XmlWriter xmlWriter) throws XmlRpcException, IOException {
            xmlWriter.startElement("methodResponse");
            xmlWriter.startElement("params");
            xmlWriter.startElement("param");
            xmlWriter.writeObject(o);
            xmlWriter.endElement("param");
            xmlWriter.endElement("params");
            xmlWriter.endElement("methodResponse");
        }
        
        void writeError(final int n, final String s, final XmlWriter xmlWriter) throws XmlRpcException, IOException {
            final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
            hashtable.put("faultCode", new Integer(n));
            hashtable.put("faultString", (Integer)s);
            xmlWriter.startElement("methodResponse");
            xmlWriter.startElement("fault");
            xmlWriter.writeObject(hashtable);
            xmlWriter.endElement("fault");
            xmlWriter.endElement("methodResponse");
        }
    }
}
