// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import org.jboss.console.manager.interfaces.impl.SimpleFolderResource;
import java.util.HashMap;
import java.net.URLEncoder;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNodeMenuEntryImpl;
import org.jboss.console.manager.interfaces.impl.SeparatorTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.MBeanResource;
import org.jboss.console.manager.interfaces.impl.SimpleResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNode;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.management.QueryExp;
import java.util.Set;
import javax.management.MalformedObjectNameException;
import javax.management.ValueExp;
import javax.management.Query;
import javax.management.ObjectName;
import javax.management.ObjectInstance;
import org.jboss.system.Registry;
import org.jboss.mx.util.MBeanServerLocator;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.ManageableResource;
import javax.servlet.ServletConfig;
import org.jboss.logging.Logger;
import org.jboss.console.manager.PluginManager;
import javax.management.MBeanServer;
import org.jboss.console.manager.interfaces.ConsolePlugin;

public abstract class AbstractPluginWrapper implements PluginWrapper, ConsolePlugin
{
    public static final String OBJECT_NAME_PARAM = "ObjectName";
    public static final String FOLDER_NAME_PARAM = "FolderName";
    public static final String MBEAN_CLASS_PARAM = "MBeanClass";
    public static final String WRAPPER_CLASS_PARAM = "WrapperClass";
    public static final String SCRIPT_NAME_PARAM = "ScriptName";
    public static final String IS_ROOT_NODE_PARAM = "IsRootNode";
    protected MBeanServer mbeanServer;
    protected PluginManager pm;
    protected String pluginName;
    protected String pluginVersion;
    protected String objectName;
    protected String mbeanClass;
    protected String folderName;
    protected String rootContextName;
    protected Logger log;
    protected InternalResourceChecker checker;
    
    public AbstractPluginWrapper() {
        this.mbeanServer = null;
        this.pm = null;
        this.pluginName = null;
        this.pluginVersion = null;
        this.objectName = null;
        this.mbeanClass = null;
        this.folderName = null;
        this.rootContextName = null;
        this.log = Logger.getLogger(this.getClass());
        this.checker = null;
    }
    
    public void init(final ServletConfig servletConfig) throws Exception {
        this.findJBossMBeanServer();
        this.findPluginManager();
        this.readConfigurationParameters(servletConfig);
        this.pm.registerPlugin(this);
    }
    
    public void destroy() {
        if (this.pm != null) {
            this.pm.unregisterPlugin(this);
        }
    }
    
    public void readConfigurationParameters(final ServletConfig config) {
        this.pluginName = config.getInitParameter("PluginName");
        this.pluginVersion = config.getInitParameter("PluginVersion");
        this.folderName = config.getInitParameter("FolderName");
        this.objectName = config.getInitParameter("ObjectName");
        this.mbeanClass = config.getInitParameter("MBeanClass");
        this.rootContextName = config.getInitParameter("ContextName");
        String tmp = this.objectName;
        if (tmp != null && !"".equals(tmp)) {
            this.checker = new SingleMBeanChecker();
        }
        tmp = this.folderName;
        if (tmp != null && !"".equals(tmp)) {
            this.checker = new SubFolderChecker();
        }
        tmp = config.getInitParameter("IsRootNode");
        if (tmp != null && !"".equals(tmp) && "true".equalsIgnoreCase(tmp)) {
            this.checker = new RootTreeChecker();
        }
        tmp = this.mbeanClass;
        if (tmp != null && !"".equals(tmp)) {
            this.checker = new StandardMBeanChecker();
        }
    }
    
    public String getIdentifier() {
        if (this.pluginName != null) {
            return this.pluginName + " (Wrapped by ServletPluginHelper)";
        }
        return this.getPluginIdentifier();
    }
    
    public String getVersion() {
        if (this.pluginVersion != null) {
            return this.pluginVersion;
        }
        return this.getPluginVersion();
    }
    
    public String[] getSupportedProfiles() {
        return new String[] { "WEB" };
    }
    
    public TreeNode getSubTreeForResource(final PluginManager master, final String profile, final ManageableResource resource) {
        if (!"WEB".equalsIgnoreCase(profile)) {
            return null;
        }
        if (this.isResourceToBeManaged(resource)) {
            return this.getTreeForResource(profile, resource);
        }
        return null;
    }
    
    protected boolean isResourceToBeManaged(final ManageableResource resource) {
        return this.checker != null && this.checker.isResourceToBeManaged(resource);
    }
    
    protected abstract TreeNode getTreeForResource(final String p0, final ManageableResource p1);
    
    protected String getPluginIdentifier() {
        return "AbstractPluginWrapper (" + this.getClass() + ")";
    }
    
    protected String getPluginVersion() {
        return "unknown version";
    }
    
    protected void findJBossMBeanServer() {
        this.mbeanServer = MBeanServerLocator.locateJBoss();
    }
    
    protected void findPluginManager() {
        this.pm = (PluginManager)Registry.lookup(PluginManager.PLUGIN_MANAGER_NAME);
    }
    
    protected MBeanServer getMBeanServer() {
        return this.mbeanServer;
    }
    
    protected String fixUrl(final String source) {
        if (source == null) {
            return null;
        }
        if (source.toLowerCase().startsWith("http://") || source.toLowerCase().startsWith("https://")) {
            return source;
        }
        if (source.startsWith("/")) {
            return source;
        }
        return this.rootContextName + "/" + source;
    }
    
    protected ObjectInstance[] getMBeansForClass(final String scope, final String className) {
        try {
            final Set result = this.mbeanServer.queryMBeans(new ObjectName(scope), Query.eq(Query.classattr(), Query.value(className)));
            return result.toArray(new ObjectInstance[result.size()]);
        }
        catch (MalformedObjectNameException e) {
            this.log.debug(e);
            return new ObjectInstance[0];
        }
    }
    
    protected ObjectInstance[] getMBeansForQuery(final String scope, final QueryExp query) {
        try {
            final Set result = this.mbeanServer.queryMBeans(new ObjectName(scope), query);
            return result.toArray(new ObjectInstance[result.size()]);
        }
        catch (MalformedObjectNameException e) {
            this.log.debug(e);
            return new ObjectInstance[0];
        }
    }
    
    protected SimpleTreeNode createTreeNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes) throws Exception {
        final TreeAction action = new HttpLinkTreeAction(this.fixUrl(defaultUrl));
        return new SimpleTreeNode(name, description, this.fixUrl(iconUrl), action, menuEntries, subNodes, subResNodes);
    }
    
    protected SimpleResourceTreeNode createResourceNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes, final String jmxObjectName, final String jmxClassName) throws Exception {
        final TreeAction action = new HttpLinkTreeAction(this.fixUrl(defaultUrl));
        final ManageableResource res = new MBeanResource(new ObjectName(jmxObjectName), jmxClassName);
        return new SimpleResourceTreeNode(name, description, this.fixUrl(iconUrl), action, menuEntries, subNodes, subResNodes, res);
    }
    
    protected SimpleResourceTreeNode createResourceNode(final String name, final String description, final String iconUrl, final String defaultUrl, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] subResNodes, final ManageableResource resource) throws Exception {
        final TreeAction action = new HttpLinkTreeAction(this.fixUrl(defaultUrl));
        return new SimpleResourceTreeNode(name, description, this.fixUrl(iconUrl), action, menuEntries, subNodes, subResNodes, resource);
    }
    
    protected TreeNodeMenuEntry[] createMenus(final String[] content) throws Exception {
        TreeNodeMenuEntry[] menuEntries = null;
        if (content != null && content.length > 0) {
            menuEntries = new TreeNodeMenuEntry[content.length];
            int i = 0;
            while (i < content.length) {
                if (content[i] == null) {
                    menuEntries[i] = new SeparatorTreeNodeMenuEntry();
                    ++i;
                }
                else {
                    final String text = content[i];
                    final TreeAction action = new HttpLinkTreeAction(this.fixUrl(content[i + 1]));
                    menuEntries[i] = new SimpleTreeNodeMenuEntryImpl(text, action);
                    i += 2;
                }
            }
        }
        else {
            menuEntries = new TreeNodeMenuEntry[0];
        }
        return menuEntries;
    }
    
    protected String encode(final String source) {
        try {
            return URLEncoder.encode(source);
        }
        catch (Exception e) {
            return source;
        }
    }
    
    public class StandardMBeanChecker implements InternalResourceChecker
    {
        protected Class targetClass;
        public HashMap knownAnswers;
        
        public StandardMBeanChecker() {
            this.targetClass = null;
            this.knownAnswers = new HashMap();
            try {
                this.targetClass = Thread.currentThread().getContextClassLoader().loadClass(AbstractPluginWrapper.this.mbeanClass);
            }
            catch (Exception displayed) {
                displayed.printStackTrace();
            }
        }
        
        public boolean isResourceToBeManaged(final ManageableResource resource) {
            if (resource instanceof MBeanResource) {
                final MBeanResource mbr = (MBeanResource)resource;
                Boolean result = this.knownAnswers.get(mbr.getClassName());
                if (result == null) {
                    try {
                        final Class resourceClass = Thread.currentThread().getContextClassLoader().loadClass(mbr.getClassName());
                        result = new Boolean(this.targetClass.isAssignableFrom(resourceClass));
                    }
                    catch (Exception e) {
                        result = Boolean.FALSE;
                    }
                    this.knownAnswers.put(mbr.getClassName(), result);
                }
                return result;
            }
            return false;
        }
    }
    
    public class RootTreeChecker implements InternalResourceChecker
    {
        public boolean isResourceToBeManaged(final ManageableResource resource) {
            return resource != null && resource.equals(AbstractPluginWrapper.this.pm.getBootstrapResource());
        }
    }
    
    public class SingleMBeanChecker implements InternalResourceChecker
    {
        public boolean isResourceToBeManaged(final ManageableResource resource) {
            if (AbstractPluginWrapper.this.objectName != null && resource instanceof MBeanResource) {
                final MBeanResource mbr = (MBeanResource)resource;
                return AbstractPluginWrapper.this.objectName.equals(mbr.getObjectName().toString());
            }
            return false;
        }
    }
    
    public class SubFolderChecker implements InternalResourceChecker
    {
        public boolean isResourceToBeManaged(final ManageableResource resource) {
            return resource != null && resource instanceof SimpleFolderResource && AbstractPluginWrapper.this.folderName.equals(resource.getId());
        }
    }
    
    public interface InternalResourceChecker
    {
        boolean isResourceToBeManaged(final ManageableResource p0);
    }
}
