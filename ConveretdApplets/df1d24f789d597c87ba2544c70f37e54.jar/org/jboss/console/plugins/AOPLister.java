// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.PluginManager;
import javax.servlet.ServletConfig;
import org.jboss.aop.advice.CFlowInterceptor;
import org.jboss.aop.advice.AbstractAdvice;
import org.jboss.console.manager.interfaces.ManageableResource;
import java.util.Map;
import org.jboss.aop.standalone.Package;
import org.jboss.aop.advice.AdviceBinding;
import org.jboss.aop.CallerConstructorInfo;
import org.jboss.aop.CallerMethodInfo;
import gnu.trove.TLongObjectHashMap;
import org.jboss.aop.MethodInfo;
import java.util.HashMap;
import org.jboss.aop.advice.Interceptor;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.jboss.aop.introduction.InterfaceIntroduction;
import org.jboss.aop.AspectManager;
import org.jboss.aop.metadata.ConstructorMetaData;
import org.jboss.aop.metadata.FieldMetaData;
import org.jboss.aop.metadata.MethodMetaData;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.jboss.aop.ClassAdvisor;
import java.util.Iterator;
import java.util.HashSet;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.aop.metadata.SimpleMetaData;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class AOPLister extends AbstractPluginWrapper
{
    Thread refreshPoller;
    
    TreeNode[] createMetaDataTree(final SimpleMetaData metaData, final String description, final String baseUrl) throws Exception {
        final HashSet groups = metaData.tags();
        if (groups.size() == 0) {
            return null;
        }
        final TreeNode[] nodes = new TreeNode[groups.size()];
        final Iterator it = groups.iterator();
        int i = 0;
        while (it.hasNext()) {
            final String group = it.next();
            nodes[i] = this.createTreeNode(group, description, "images/database.gif", baseUrl + "&group=" + group, null, null, null);
            ++i;
        }
        return nodes;
    }
    
    TreeNode[] loadDefaultMetaData(final ClassAdvisor advisor, final String classname) throws Exception {
        final SimpleMetaData metaData = advisor.getDefaultMetaData();
        return this.createMetaDataTree(metaData, "Default metadata for " + classname, "AOPDefaultMetaData.jsp?classname=" + classname);
    }
    
    TreeNode[] loadClassMetaData(final ClassAdvisor advisor, final String classname) throws Exception {
        final SimpleMetaData metaData = advisor.getClassMetaData();
        return this.createMetaDataTree(metaData, "Class metadata for " + classname, "AOPClassMetaData.jsp?classname=" + classname);
    }
    
    TreeNode[] loadMethodMetaData(final ClassAdvisor advisor, final String classname) throws Exception {
        final MethodMetaData metaData = advisor.getMethodMetaData();
        final Iterator it = metaData.getMethods();
        if (!it.hasNext()) {
            return null;
        }
        final ArrayList methods = new ArrayList();
        while (it.hasNext()) {
            final String method = it.next();
            final SimpleMetaData methodData = metaData.getMethodMetaData(method);
            final TreeNode[] methodNodes = this.createMetaDataTree(methodData, "Metadata for method " + method, "AOPMethodMetaData.jsp?classname=" + classname + "&method=" + URLEncoder.encode(method));
            methods.add(this.createTreeNode(method, "Metadata for method " + method, "images/starfolder.gif", null, null, methodNodes, null));
        }
        return methods.toArray(new TreeNode[methods.size()]);
    }
    
    TreeNode[] loadFieldMetaData(final ClassAdvisor advisor, final String classname) throws Exception {
        final FieldMetaData metaData = advisor.getFieldMetaData();
        final Iterator it = metaData.getFields();
        if (!it.hasNext()) {
            return null;
        }
        final ArrayList fields = new ArrayList();
        while (it.hasNext()) {
            final String field = it.next();
            final SimpleMetaData fieldData = metaData.getFieldMetaData(field);
            final TreeNode[] fieldNodes = this.createMetaDataTree(fieldData, "Metadata for field " + field, "AOPFieldMetaData.jsp?classname=" + classname + "&field=" + field);
            fields.add(this.createTreeNode(field, "Metadata for field " + field, "images/starfolder.gif", null, null, fieldNodes, null));
        }
        return fields.toArray(new TreeNode[fields.size()]);
    }
    
    TreeNode[] loadConstructorMetaData(final ClassAdvisor advisor, final String classname) throws Exception {
        final ConstructorMetaData metaData = advisor.getConstructorMetaData();
        final Iterator it = metaData.getConstructors();
        if (!it.hasNext()) {
            return null;
        }
        final ArrayList constructors = new ArrayList();
        while (it.hasNext()) {
            final String signature = it.next();
            final SimpleMetaData constructorData = metaData.getConstructorMetaData(signature);
            final TreeNode[] constructorNodes = this.createMetaDataTree(constructorData, "Metadata for constructor", "AOPConstructorMetaData.jsp?classname=" + classname + "&constructor=" + URLEncoder.encode(signature));
            constructors.add(this.createTreeNode(signature, "Metaata for constructor " + signature, "images/starfolder.gif", null, null, constructorNodes, null));
        }
        return constructors.toArray(new TreeNode[constructors.size()]);
    }
    
    TreeNode getMetaData(final String classname) throws Exception {
        final ClassAdvisor advisor = (ClassAdvisor)AspectManager.instance().getAdvisor(classname);
        final ArrayList nodes = new ArrayList();
        final TreeNode[] defaultMetaData = this.loadDefaultMetaData(advisor, classname);
        if (defaultMetaData != null) {
            nodes.add(this.createTreeNode("Default", "Default metadata for for " + classname, "images/starfolder.gif", null, null, defaultMetaData, null));
        }
        final TreeNode[] classMetaData = this.loadClassMetaData(advisor, classname);
        if (classMetaData != null) {
            nodes.add(this.createTreeNode("Class", "Class metadata for for " + classname, "images/starfolder.gif", null, null, classMetaData, null));
        }
        final TreeNode[] methodMetaData = this.loadMethodMetaData(advisor, classname);
        if (methodMetaData != null) {
            nodes.add(this.createTreeNode("Methods", "Method metadata for for " + classname, "images/starfolder.gif", null, null, methodMetaData, null));
        }
        final TreeNode[] fieldMetaData = this.loadFieldMetaData(advisor, classname);
        if (fieldMetaData != null) {
            nodes.add(this.createTreeNode("Fields", "Field metadata for for " + classname, "images/starfolder.gif", null, null, fieldMetaData, null));
        }
        final TreeNode[] constructorMetaData = this.loadConstructorMetaData(advisor, classname);
        if (constructorMetaData != null) {
            nodes.add(this.createTreeNode("Constructors", "Constructor metadata for for " + classname, "images/starfolder.gif", null, null, constructorMetaData, null));
        }
        if (nodes.size() == 0) {
            return null;
        }
        final TreeNode[] subnodes = nodes.toArray(new TreeNode[nodes.size()]);
        return this.createTreeNode("Metadata", "Metadata for " + classname, "images/starfolder.gif", null, null, subnodes, null);
    }
    
    TreeNode[] getIntroductions(final ClassAdvisor advisor) throws Exception {
        final ArrayList introductions = advisor.getInterfaceIntroductions();
        if (introductions == null || introductions.size() == 0) {
            return null;
        }
        final TreeNode[] nodes = new TreeNode[introductions.size()];
        for (int i = 0; i < introductions.size(); ++i) {
            final InterfaceIntroduction introduction = introductions.get(i);
            nodes[i] = this.createTreeNode("Introduction " + i, "Introduction for " + advisor.getName(), "images/service.gif", "AOPIntroductionPointcut.jsp?pointcut=" + URLEncoder.encode(introduction.getName()), null, null, null);
        }
        return nodes;
    }
    
    public static String shortenMethod(final String classname, final Method method) {
        return method.toString().replaceAll(classname + "." + method.getName(), method.getName());
    }
    
    public static String shortenConstructor(final String classname, final Constructor constructor) {
        final String base = classname.substring(classname.lastIndexOf(46) + 1);
        return constructor.toString().replaceAll(classname, base);
    }
    
    public static String shortenField(final String classname, final Field field) {
        return field.toString().replaceAll(classname + "." + field.getName(), field.getName());
    }
    
    public TreeNode[] createAdvisorNodes(final ClassAdvisor advisor) throws Exception {
        final ArrayList nodes = new ArrayList();
        this.populateIntroductions(advisor, nodes);
        this.populateConstructors(advisor, nodes);
        this.populateMethods(advisor, nodes);
        this.populateFields(advisor, nodes);
        final TreeNode metadata = this.getMetaData(advisor.getName());
        if (metadata != null) {
            nodes.add(metadata);
        }
        return nodes.toArray(new TreeNode[nodes.size()]);
    }
    
    private void populateFields(final ClassAdvisor advisor, final ArrayList nodes) throws Exception {
        if (advisor.getAdvisedFields() == null) {
            return;
        }
        final ArrayList fieldWriteNodes = new ArrayList();
        final ArrayList fieldReadNodes = new ArrayList();
        for (int i = 0; i < advisor.getAdvisedFields().length; ++i) {
            final Field f = advisor.getAdvisedFields()[i];
            Interceptor[] chain = advisor.getFieldWriteInterceptors()[i];
            if (chain != null && chain.length > 0) {
                fieldWriteNodes.add(this.createTreeNode(shortenField(advisor.getName(), f), "Field write interceptor chain", "images/service.gif", "AOPFieldChain.jsp?classname=" + URLEncoder.encode(advisor.getName()) + "&field=" + i + "&mode=write", null, null, null));
            }
            chain = advisor.getFieldReadInterceptors()[i];
            if (chain != null && chain.length > 0) {
                fieldReadNodes.add(this.createTreeNode(shortenField(advisor.getName(), f), "Field read interceptor chain", "images/service.gif", "AOPFieldChain.jsp?classname=" + URLEncoder.encode(advisor.getName()) + "&field=" + i + "&mode=read", null, null, null));
            }
        }
        if (fieldWriteNodes.size() > 0 && fieldWriteNodes.size() > 0) {
            final ArrayList fieldReadWriteNodes = new ArrayList();
            if (fieldWriteNodes.size() > 0) {
                final TreeNode[] cnodes = fieldWriteNodes.toArray(new TreeNode[fieldWriteNodes.size()]);
                fieldReadWriteNodes.add(this.createTreeNode("write interceptors", "field write info", "images/starfolder.gif", null, null, cnodes, null));
            }
            if (fieldReadNodes.size() > 0) {
                final TreeNode[] cnodes = fieldReadNodes.toArray(new TreeNode[fieldReadNodes.size()]);
                fieldReadWriteNodes.add(this.createTreeNode("read interceptors", "field read info", "images/starfolder.gif", null, null, cnodes, null));
            }
            final TreeNode[] fieldRwNodes = fieldReadWriteNodes.toArray(new TreeNode[fieldReadWriteNodes.size()]);
            nodes.add(this.createTreeNode("Fields", "field info", "images/starfolder.gif", null, null, fieldRwNodes, null));
        }
    }
    
    private void populateConstructors(final ClassAdvisor advisor, final ArrayList nodes) throws Exception {
        if (advisor.getConstructors() == null) {
            return;
        }
        if (advisor.getConstructorInterceptors() == null) {
            return;
        }
        if (advisor.getMethodCalledByConInterceptors() == null) {
            return;
        }
        final ArrayList constructorNodes = new ArrayList();
        for (int i = 0; i < advisor.getConstructors().length; ++i) {
            final Constructor con = advisor.getConstructors()[i];
            final Interceptor[] chain = advisor.getConstructorInterceptors()[i];
            final HashMap methodCallers = advisor.getMethodCalledByConInterceptors()[i];
            final HashMap conCallers = advisor.getConCalledByConInterceptors()[i];
            if ((chain != null && chain.length > 0) || methodCallers != null || conCallers != null) {
                final ArrayList conNodes = new ArrayList();
                if (chain != null && chain.length > 0) {
                    conNodes.add(this.createTreeNode("Interceptors", "Execution Interceptors", "images/service.gif", "AOPConstructorChain.jsp?classname=" + URLEncoder.encode(con.getDeclaringClass().getName()) + "&constructor=" + i, null, null, null));
                }
                if (conCallers != null) {
                    conNodes.add(this.createTreeNode("constructor callers", "constructor caller interceptions", "images/starfolder.gif", null, null, this.createConstructorConstructorCallers(i, advisor, conCallers), null));
                }
                if (methodCallers != null) {
                    conNodes.add(this.createTreeNode("method callers", "method caller interceptions", "images/starfolder.gif", null, null, this.createConstructorMethodCallers(i, advisor, methodCallers), null));
                }
                final TreeNode[] cnodes = conNodes.toArray(new TreeNode[conNodes.size()]);
                constructorNodes.add(this.createTreeNode(shortenConstructor(advisor.getName(), con), "constructor info", "images/starfolder.gif", null, null, cnodes, null));
            }
        }
        if (constructorNodes.size() > 0) {
            final TreeNode[] cnodes2 = constructorNodes.toArray(new TreeNode[constructorNodes.size()]);
            nodes.add(this.createTreeNode("Constructors", "constructor info", "images/starfolder.gif", null, null, cnodes2, null));
        }
    }
    
    private void populateMethods(final ClassAdvisor advisor, final ArrayList nodes) throws Exception {
        if (advisor.getMethodInterceptors() == null) {
            return;
        }
        final ArrayList methodNodes = new ArrayList();
        final long[] keys = advisor.getMethodInterceptors().keys();
        for (int i = 0; i < keys.length; ++i) {
            final long key = keys[i];
            final MethodInfo method = (MethodInfo)advisor.getMethodInterceptors().get(key);
            final HashMap methodCallers = (HashMap)advisor.getMethodCalledByMethodInterceptors().get(key);
            final HashMap conCallers = (HashMap)advisor.getConCalledByMethodInterceptors().get(key);
            if (method != null || methodCallers != null) {
                if (method != null && methodCallers == null) {
                    if (method.interceptors == null) {
                        continue;
                    }
                    if (method.interceptors.length < 1) {
                        continue;
                    }
                }
                final ArrayList mNodes = new ArrayList();
                if ((method.interceptors != null && method.interceptors.length > 0) || methodCallers != null || conCallers != null) {
                    mNodes.add(this.createTreeNode("Interceptors", "Execution Interceptors", "images/service.gif", "AOPMethodChain.jsp?classname=" + URLEncoder.encode(advisor.getName()) + "&method=" + keys[i], null, null, null));
                }
                if (conCallers != null) {
                    mNodes.add(this.createTreeNode("constructor callers", "constructor caller interceptions", "images/starfolder.gif", null, null, this.createMethodConstructorCallers(key, advisor, conCallers), null));
                }
                if (methodCallers != null) {
                    mNodes.add(this.createTreeNode("method callers", "method caller interceptions", "images/starfolder.gif", null, null, this.createMethodMethodCallers(key, advisor, methodCallers), null));
                }
                final TreeNode[] mnodes = mNodes.toArray(new TreeNode[mNodes.size()]);
                methodNodes.add(this.createTreeNode(shortenMethod(advisor.getName(), method.advisedMethod), "method info", "images/starfolder.gif", null, null, mnodes, null));
            }
        }
        if (methodNodes.size() > 0) {
            final TreeNode[] cnodes = methodNodes.toArray(new TreeNode[methodNodes.size()]);
            nodes.add(this.createTreeNode("Methods", "method info", "images/starfolder.gif", null, null, cnodes, null));
        }
    }
    
    private void populateIntroductions(final ClassAdvisor advisor, final ArrayList nodes) throws Exception {
        final ArrayList introductions = advisor.getInterfaceIntroductions();
        if (introductions != null && introductions.size() > 0) {
            final TreeNode[] introductionNodes = this.getIntroductions(advisor);
            final TreeNode introductionsNode = this.createTreeNode("Introductions", "Introductions for " + advisor.getName(), "images/starfolder.gif", null, null, introductionNodes, null);
            nodes.add(introductionsNode);
        }
    }
    
    public TreeNode[] createConstructorMethodCallers(final int index, final ClassAdvisor advisor, final HashMap called) throws Exception {
        final ArrayList nodes = new ArrayList();
        for (final String calledClass : called.keySet()) {
            final TLongObjectHashMap map = called.get(calledClass);
            final Object[] values = map.getValues();
            final long[] keys = map.keys();
            for (int i = 0; i < values.length; ++i) {
                final CallerMethodInfo caller = (CallerMethodInfo)values[i];
                nodes.add(this.createTreeNode(caller.getMethod().toString(), "caller interceptions", "images/service.gif", "AOPConstructorMethodCallerChain.jsp?index=" + index + "&hash=" + URLEncoder.encode(Long.toString(keys[i])) + "&classname=" + URLEncoder.encode(advisor.getName()) + "&calledclassname=" + URLEncoder.encode(calledClass), null, null, null));
            }
        }
        return nodes.toArray(new TreeNode[nodes.size()]);
    }
    
    public TreeNode[] createConstructorConstructorCallers(final int index, final ClassAdvisor advisor, final HashMap called) throws Exception {
        final ArrayList nodes = new ArrayList();
        for (final String calledClass : called.keySet()) {
            final TLongObjectHashMap map = called.get(calledClass);
            final Object[] values = map.getValues();
            final long[] keys = map.keys();
            for (int i = 0; i < values.length; ++i) {
                final CallerConstructorInfo caller = (CallerConstructorInfo)values[i];
                nodes.add(this.createTreeNode(caller.getConstructor().toString(), "caller interceptions", "images/service.gif", "AOPConstructorConstructorCallerChain.jsp?index=" + index + "&hash=" + URLEncoder.encode(Long.toString(keys[i])) + "&classname=" + URLEncoder.encode(advisor.getName()) + "&calledclassname=" + URLEncoder.encode(calledClass), null, null, null));
            }
        }
        return nodes.toArray(new TreeNode[nodes.size()]);
    }
    
    public TreeNode[] createMethodMethodCallers(final long callingHash, final ClassAdvisor advisor, final HashMap called) throws Exception {
        final ArrayList nodes = new ArrayList();
        for (final String calledClass : called.keySet()) {
            final TLongObjectHashMap map = called.get(calledClass);
            final Object[] values = map.getValues();
            final long[] keys = map.keys();
            for (int i = 0; i < values.length; ++i) {
                final CallerMethodInfo caller = (CallerMethodInfo)values[i];
                nodes.add(this.createTreeNode(caller.getMethod().toString(), "caller interceptions", "images/service.gif", "AOPMethodMethodCallerChain.jsp?callinghash=" + callingHash + "&hash=" + URLEncoder.encode(Long.toString(keys[i])) + "&classname=" + URLEncoder.encode(advisor.getName()) + "&calledclassname=" + URLEncoder.encode(calledClass), null, null, null));
            }
        }
        return nodes.toArray(new TreeNode[nodes.size()]);
    }
    
    public TreeNode[] createMethodConstructorCallers(final long callingHash, final ClassAdvisor advisor, final HashMap called) throws Exception {
        final ArrayList nodes = new ArrayList();
        for (final String calledClass : called.keySet()) {
            final TLongObjectHashMap map = called.get(calledClass);
            final Object[] values = map.getValues();
            final long[] keys = map.keys();
            for (int i = 0; i < values.length; ++i) {
                final CallerConstructorInfo caller = (CallerConstructorInfo)values[i];
                nodes.add(this.createTreeNode(caller.getConstructor().toString(), "caller interceptions", "images/service.gif", "AOPMethodConstructorCallerChain.jsp?callinghash=" + callingHash + "&hash=" + URLEncoder.encode(Long.toString(keys[i])) + "&classname=" + URLEncoder.encode(advisor.getName()) + "&calledclassname=" + URLEncoder.encode(calledClass), null, null, null));
            }
        }
        return nodes.toArray(new TreeNode[nodes.size()]);
    }
    
    public TreeNode[] getUnboundBindings() throws Exception {
        final ArrayList unbounded = new ArrayList();
        for (final AdviceBinding binding : AspectManager.instance().getBindings().values()) {
            if (!binding.hasAdvisors()) {
                unbounded.add(this.createTreeNode(binding.getName(), "Unbounded Binding", "images/service.gif", "AOPBinding.jsp?binding=" + URLEncoder.encode(binding.getName()), null, null, null));
            }
        }
        if (unbounded.size() == 0) {
            return null;
        }
        return unbounded.toArray(new TreeNode[unbounded.size()]);
    }
    
    TreeNode[] createAOPNodes(final Package root) throws Exception {
        final ArrayList nodes = new ArrayList();
        for (final Map.Entry entry : root.packages.entrySet()) {
            final String pkgName = entry.getKey();
            final Package p = entry.getValue();
            nodes.add(this.createTreeNode(pkgName, "Package " + pkgName, "images/starfolder.gif", null, null, this.createAOPNodes(p), null));
        }
        for (final Map.Entry entry : root.advisors.entrySet()) {
            final String classname = entry.getKey();
            final ClassAdvisor advisor = entry.getValue();
            nodes.add(this.createTreeNode(classname, "Class " + classname, "images/serviceset.gif", null, null, this.createAdvisorNodes(advisor), null));
        }
        TreeNode[] result;
        if (nodes.size() == 0) {
            result = null;
        }
        else {
            result = nodes.toArray(new TreeNode[nodes.size()]);
        }
        return result;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final TreeNode[] unbounded = this.getUnboundBindings();
            final TreeNode[] children = { this.createTreeNode("Classes", "Display all Classes", "images/serviceset.gif", null, null, this.createAOPNodes(Package.aopClassMap()), null), this.createTreeNode("Unbound Bindings", "Unbound Bindings", "images/serviceset.gif", null, null, unbounded, null) };
            return this.createTreeNode("AOP", "AOP Management", "images/spirale32.gif", null, null, children, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String outputChain(final Interceptor[] chain) {
        String output = "";
        for (int i = 0; i < chain.length; ++i) {
            output += "<tr>";
            if (chain[i] instanceof AbstractAdvice) {
                output = output + "<td><font size=\"1\">advice</font></td><td><font size=\"1\">" + chain[i].getName() + "</font></td>";
            }
            else if (chain[i] instanceof CFlowInterceptor) {
                output = output + "<td><font size=\"1\">cflow</font></td><td><font size=\"1\">" + ((CFlowInterceptor)chain[i]).getCFlowString() + "</font></td>";
            }
            else {
                output = output + "<td><font size=\"1\">interceptor</font></td><td><font size=\"1\">" + chain[i].getClass().getName() + "</font></td>";
            }
            output += "</tr>";
        }
        return output;
    }
    
    public void init(final ServletConfig servletConfig) throws Exception {
        super.init(servletConfig);
        (this.refreshPoller = new Thread(new Runnable() {
            public void run() {
                try {
                    int advisorCount = 0;
                    while (true) {
                        final int count = AspectManager.instance().getAdvisors().size();
                        if (count != advisorCount) {
                            AOPLister.this.pm.regenerateAdminTree();
                        }
                        advisorCount = count;
                        Thread.sleep(20000L);
                    }
                }
                catch (Throwable e) {}
            }
        })).setDaemon(true);
        this.refreshPoller.start();
    }
    
    public void destroy() {
        super.destroy();
    }
}
