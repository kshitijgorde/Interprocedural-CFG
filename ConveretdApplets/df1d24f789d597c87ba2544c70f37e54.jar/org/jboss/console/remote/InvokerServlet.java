// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletInputStream;
import org.jboss.invocation.InvocationException;
import org.jboss.mx.util.JMXExceptionDecoder;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import org.jboss.invocation.MarshalledValue;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import org.jboss.mx.util.MBeanServerLocator;
import javax.servlet.ServletConfig;
import javax.management.MBeanServer;
import org.jboss.logging.Logger;
import javax.servlet.http.HttpServlet;

public class InvokerServlet extends HttpServlet
{
    private static Logger log;
    private static String REQUEST_CONTENT_TYPE;
    private static String RESPONSE_CONTENT_TYPE;
    private MBeanServer mbeanServer;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.mbeanServer = MBeanServerLocator.locateJBoss();
        if (this.mbeanServer == null) {
            throw new ServletException("Failed to locate the MBeanServer");
        }
    }
    
    public void destroy() {
    }
    
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final boolean trace = InvokerServlet.log.isTraceEnabled();
        if (trace) {
            InvokerServlet.log.trace("processRequest, ContentLength: " + request.getContentLength());
            InvokerServlet.log.trace("processRequest, ContentType: " + request.getContentType());
        }
        try {
            response.setContentType(InvokerServlet.RESPONSE_CONTENT_TYPE);
            Object mi = request.getAttribute("RemoteMBeanInvocation");
            if (mi == null) {
                final ServletInputStream sis = request.getInputStream();
                final ObjectInputStream ois = new ObjectInputStream((InputStream)sis);
                mi = ois.readObject();
                ois.close();
            }
            Object value = null;
            if (mi instanceof RemoteMBeanInvocation) {
                final RemoteMBeanInvocation invocation = (RemoteMBeanInvocation)mi;
                value = this.mbeanServer.invoke(invocation.targetObjectName, invocation.actionName, invocation.params, invocation.signature);
            }
            else {
                final RemoteMBeanAttributeInvocation invocation2 = (RemoteMBeanAttributeInvocation)mi;
                value = this.mbeanServer.getAttribute(invocation2.targetObjectName, invocation2.attributeName);
            }
            final MarshalledValue mv = new MarshalledValue(value);
            final ServletOutputStream sos = response.getOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream((OutputStream)sos);
            oos.writeObject(mv);
            oos.close();
        }
        catch (Throwable t) {
            t = JMXExceptionDecoder.decode(t);
            final InvocationException appException = new InvocationException(t);
            InvokerServlet.log.debug("Invoke threw exception", t);
            response.resetBuffer();
            final MarshalledValue mv = new MarshalledValue(appException);
            final ServletOutputStream sos = response.getOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream((OutputStream)sos);
            oos.writeObject(mv);
            oos.close();
        }
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
    
    public String getServletInfo() {
        return "An HTTP to JMX MBeanServer servlet";
    }
    
    static {
        InvokerServlet.log = Logger.getLogger(InvokerServlet.class);
        InvokerServlet.REQUEST_CONTENT_TYPE = "application/x-java-serialized-object; class=org.jboss.console.remote.RemoteMBeanInvocation";
        InvokerServlet.RESPONSE_CONTENT_TYPE = "application/x-java-serialized-object; class=org.jboss.invocation.MarshalledValue";
    }
}
