// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import java.io.InputStream;
import java.io.OutputStream;
import org.jboss.invocation.InvocationException;
import org.jboss.invocation.MarshalledValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jboss.logging.Logger;

public class Util
{
    private static String REQUEST_CONTENT_TYPE;
    private static Logger log;
    
    public static Object invoke(final URL externalURL, final RemoteMBeanInvocation mi) throws Exception {
        if (Util.log.isTraceEnabled()) {
            Util.log.trace("invoke, externalURL=" + externalURL);
        }
        final HttpURLConnection conn = (HttpURLConnection)externalURL.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("ContentType", Util.REQUEST_CONTENT_TYPE);
        conn.setRequestMethod("POST");
        final OutputStream os = conn.getOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(mi);
        oos.flush();
        final InputStream is = conn.getInputStream();
        final ObjectInputStream ois = new ObjectInputStream(is);
        final MarshalledValue mv = (MarshalledValue)ois.readObject();
        ois.close();
        oos.close();
        final Object value = mv.get();
        if (value instanceof InvocationException) {
            throw (Exception)((InvocationException)value).getTargetException();
        }
        if (value instanceof Exception) {
            throw (Exception)value;
        }
        return value;
    }
    
    public static Object getAttribute(final URL externalURL, final RemoteMBeanAttributeInvocation mi) throws Exception {
        if (Util.log.isTraceEnabled()) {
            Util.log.trace("invoke, externalURL=" + externalURL);
        }
        final HttpURLConnection conn = (HttpURLConnection)externalURL.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("ContentType", Util.REQUEST_CONTENT_TYPE);
        conn.setRequestMethod("POST");
        final OutputStream os = conn.getOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(mi);
        oos.flush();
        final InputStream is = conn.getInputStream();
        final ObjectInputStream ois = new ObjectInputStream(is);
        final MarshalledValue mv = (MarshalledValue)ois.readObject();
        ois.close();
        oos.close();
        final Object value = mv.get();
        if (value instanceof InvocationException) {
            throw (Exception)((InvocationException)value).getTargetException();
        }
        if (value instanceof Exception) {
            throw (Exception)value;
        }
        return value;
    }
    
    static {
        Util.REQUEST_CONTENT_TYPE = "application/x-java-serialized-object; class=org.jboss.console.remote.RemoteMBeanInvocation";
        Util.log = Logger.getLogger(Util.class);
    }
}
