// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class ServletPluginHelper extends HttpServlet
{
    public static final String WRAPPER_CLASS_PARAM = "WrapperClass";
    protected ServletConfig config;
    protected PluginWrapper wrapper;
    
    public ServletPluginHelper() {
        this.config = null;
        this.wrapper = null;
    }
    
    public void init(final ServletConfig config) throws ServletException {
        try {
            super.init(config);
            this.config = config;
            (this.wrapper = this.createPluginWrapper()).init(config);
        }
        catch (Throwable e) {
            this.log("Failed to init plugin, " + e.getMessage());
        }
    }
    
    public void destroy() {
        if (this.wrapper != null) {
            this.wrapper.destroy();
        }
        super.destroy();
    }
    
    protected PluginWrapper createPluginWrapper() throws Exception {
        final String tmp = this.config.getInitParameter("WrapperClass");
        if (tmp != null && !"".equals(tmp)) {
            final Class clazz = Thread.currentThread().getContextClassLoader().loadClass(tmp);
            return clazz.newInstance();
        }
        return new BasePluginWrapper();
    }
}
