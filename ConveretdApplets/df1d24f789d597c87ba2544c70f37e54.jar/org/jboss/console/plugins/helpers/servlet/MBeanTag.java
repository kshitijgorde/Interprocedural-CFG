// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

import javax.servlet.jsp.JspTagException;
import org.jboss.mx.util.MBeanProxy;
import org.jboss.console.plugins.helpers.jmx.Server;
import javax.management.ObjectName;
import javax.servlet.jsp.tagext.TagSupport;

public class MBeanTag extends TagSupport
{
    protected String interfaceName;
    protected String variableName;
    protected String mbeanName;
    
    public MBeanTag() {
        this.interfaceName = null;
        this.variableName = null;
        this.mbeanName = null;
    }
    
    public String getIntf() {
        return this.interfaceName;
    }
    
    public void setIntf(final String intf) {
        this.interfaceName = intf;
    }
    
    public String getId() {
        return this.variableName;
    }
    
    public void setId(final String var) {
        this.variableName = var;
    }
    
    public String getMbean() {
        return this.mbeanName;
    }
    
    public void setMbean(final String mbean) {
        this.mbeanName = mbean;
    }
    
    public int doStartTag() throws JspTagException {
        try {
            ObjectName objName = null;
            if (this.mbeanName == null) {
                objName = new ObjectName(this.pageContext.getRequest().getParameter("ObjectName"));
            }
            else {
                objName = new ObjectName(this.mbeanName);
            }
            final Class type = Thread.currentThread().getContextClassLoader().loadClass(this.interfaceName);
            final Object result = MBeanProxy.get(type, objName, Server.getMBeanServer());
            this.pageContext.setAttribute(this.variableName, result);
            return 1;
        }
        catch (Exception e) {
            throw new JspTagException(e.toString());
        }
    }
    
    public int doEndTag() throws JspTagException {
        return 6;
    }
}
