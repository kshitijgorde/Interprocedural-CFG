// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.logging.Logger;
import javax.management.ObjectInstance;
import javax.management.MBeanServer;
import java.net.URL;
import java.io.Reader;
import java.io.InputStreamReader;
import org.jboss.console.manager.PluginManager;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.ManageableResource;
import java.lang.reflect.UndeclaredThrowableException;
import javax.servlet.ServletConfig;
import bsh.Interpreter;

public class BasePluginWrapper extends AbstractPluginWrapper
{
    protected Interpreter interpreter;
    protected String pluginName;
    protected String pluginVersion;
    protected String scriptName;
    protected String scriptContent;
    protected ScriptPlugin script;
    protected PluginContext pluginCtx;
    
    public BasePluginWrapper() {
        this.interpreter = null;
        this.pluginName = null;
        this.pluginVersion = null;
        this.scriptName = null;
        this.scriptContent = null;
        this.script = null;
        this.pluginCtx = null;
    }
    
    public void init(final ServletConfig servletConfig) throws Exception {
        super.init(servletConfig);
        this.loadScript(this.scriptName);
        this.pluginCtx = new SimplePluginContext();
    }
    
    public void readConfigurationParameters(final ServletConfig config) {
        super.readConfigurationParameters(config);
        this.scriptName = config.getInitParameter("ScriptName");
    }
    
    protected String getPluginIdentifier() {
        try {
            return this.script.getName(this.pluginCtx);
        }
        catch (UndeclaredThrowableException ute) {
            return "ServletPluginHelper Wrapping script '" + this.scriptName + "'";
        }
    }
    
    protected String getPluginVersion() {
        try {
            System.out.println("Version : " + this.script.getVersion(this.pluginCtx));
            return this.script.getVersion(this.pluginCtx);
        }
        catch (UndeclaredThrowableException ute) {
            return "unknown version";
        }
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final TreeNode result = this.script.getTreeForResource(resource, this.pluginCtx);
            return result;
        }
        catch (UndeclaredThrowableException ute) {
            ute.printStackTrace();
            return null;
        }
    }
    
    protected boolean isResourceToBeManaged(final ManageableResource resource) {
        if (this.checker != null) {
            return super.isResourceToBeManaged(resource);
        }
        try {
            return this.isResourceToBeManaged_Script(this.pm, resource);
        }
        catch (UndeclaredThrowableException ute) {
            ute.printStackTrace();
            return false;
        }
    }
    
    protected boolean isResourceToBeManaged_Script(final PluginManager master, final ManageableResource resource) throws UndeclaredThrowableException {
        return this.script.isResourceToBeManaged(resource, this.pluginCtx);
    }
    
    protected void loadScript(final String scriptName) throws Exception {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(scriptName);
        if (url == null) {
            throw new IllegalArgumentException("Resource not found: " + scriptName);
        }
        (this.interpreter = new Interpreter()).setClassLoader(Thread.currentThread().getContextClassLoader());
        this.interpreter.eval((Reader)new InputStreamReader(url.openStream()));
        this.script = (ScriptPlugin)this.interpreter.getInterface((Class)ScriptPlugin.class);
    }
    
    public class SimplePluginContext implements PluginContext
    {
        public String localizeUrl(final String source) {
            return BasePluginWrapper.this.fixUrl(source);
        }
        
        public MBeanServer getLocalMBeanServer() {
            return BasePluginWrapper.this.mbeanServer;
        }
        
        public ObjectInstance[] getMBeansForClass(final String scope, final String className) {
            return BasePluginWrapper.this.getMBeansForClass(scope, className);
        }
        
        public Logger getLogger() {
            return BasePluginWrapper.this.log;
        }
        
        public TreeNode createTreeNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes) throws Exception {
            return BasePluginWrapper.this.createTreeNode(name, description, iconUrl, defaultUrl, menuEntries, subNodes, subResNodes);
        }
        
        public ResourceTreeNode createResourceNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes, final String jmxObjectName, final String jmxClassName) throws Exception {
            return BasePluginWrapper.this.createResourceNode(name, description, iconUrl, defaultUrl, menuEntries, subNodes, subResNodes, jmxObjectName, jmxClassName);
        }
        
        public ResourceTreeNode createResourceNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes, final ManageableResource resource) throws Exception {
            return BasePluginWrapper.this.createResourceNode(name, description, iconUrl, defaultUrl, menuEntries, subNodes, subResNodes, resource);
        }
        
        public TreeNodeMenuEntry[] createMenus(final String[] content) throws Exception {
            return BasePluginWrapper.this.createMenus(content);
        }
        
        public String encode(final String source) {
            return BasePluginWrapper.this.encode(source);
        }
    }
}
