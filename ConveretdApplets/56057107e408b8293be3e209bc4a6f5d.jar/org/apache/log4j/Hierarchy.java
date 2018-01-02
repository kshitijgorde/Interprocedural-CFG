// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import java.util.ResourceBundle;
import java.util.Enumeration;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.LoggerRepository;

public class Hierarchy implements LoggerRepository, RendererSupport
{
    private LoggerFactory defaultFactory;
    private Vector listeners;
    Hashtable ht;
    Logger root;
    RendererMap rendererMap;
    int thresholdInt;
    Level threshold;
    boolean emittedNoAppenderWarning;
    boolean emittedNoResourceBundleWarning;
    
    public Hierarchy(final Logger root) {
        this.emittedNoAppenderWarning = false;
        this.emittedNoResourceBundleWarning = false;
        this.ht = new Hashtable();
        this.listeners = new Vector(1);
        this.root = root;
        this.setThreshold(Level.ALL);
        this.root.setHierarchy(this);
        this.rendererMap = new RendererMap();
        this.defaultFactory = new DefaultCategoryFactory();
    }
    
    public void addRenderer(final Class clazz, final ObjectRenderer objectRenderer) {
        this.rendererMap.put(clazz, objectRenderer);
    }
    
    public void addHierarchyEventListener(final HierarchyEventListener hierarchyEventListener) {
        if (this.listeners.contains(hierarchyEventListener)) {
            LogLog.warn("Ignoring attempt to add an existent listener.");
        }
        else {
            this.listeners.addElement(hierarchyEventListener);
        }
    }
    
    public void clear() {
        this.ht.clear();
    }
    
    public void emitNoAppenderWarning(final Category category) {
        if (!this.emittedNoAppenderWarning) {
            LogLog.warn("No appenders could be found for logger (" + category.getName() + ").");
            LogLog.warn("Please initialize the log4j system properly.");
            this.emittedNoAppenderWarning = true;
        }
    }
    
    public Logger exists(final String s) {
        final Logger value = this.ht.get(new CategoryKey(s));
        if (value instanceof Logger) {
            return value;
        }
        return null;
    }
    
    public void setThreshold(final String s) {
        final Level level = Level.toLevel(s, null);
        if (level != null) {
            this.setThreshold(level);
        }
        else {
            LogLog.warn("Could not convert [" + s + "] to Level.");
        }
    }
    
    public void setThreshold(final Level threshold) {
        if (threshold != null) {
            this.thresholdInt = threshold.level;
            this.threshold = threshold;
        }
    }
    
    public void fireAddAppenderEvent(final Category category, final Appender appender) {
        if (this.listeners != null) {
            for (int size = this.listeners.size(), i = 0; i < size; ++i) {
                ((HierarchyEventListener)this.listeners.elementAt(i)).addAppenderEvent(category, appender);
            }
        }
    }
    
    void fireRemoveAppenderEvent(final Category category, final Appender appender) {
        if (this.listeners != null) {
            for (int size = this.listeners.size(), i = 0; i < size; ++i) {
                ((HierarchyEventListener)this.listeners.elementAt(i)).removeAppenderEvent(category, appender);
            }
        }
    }
    
    public Level getThreshold() {
        return this.threshold;
    }
    
    public Logger getLogger(final String s) {
        return this.getLogger(s, this.defaultFactory);
    }
    
    public Logger getLogger(final String s, final LoggerFactory loggerFactory) {
        final CategoryKey categoryKey = new CategoryKey(s);
        synchronized (this.ht) {
            final Object value = this.ht.get(categoryKey);
            if (value == null) {
                final Logger newLoggerInstance = loggerFactory.makeNewLoggerInstance(s);
                newLoggerInstance.setHierarchy(this);
                this.ht.put(categoryKey, newLoggerInstance);
                this.updateParents(newLoggerInstance);
                return newLoggerInstance;
            }
            if (value instanceof Logger) {
                return (Logger)value;
            }
            if (value instanceof ProvisionNode) {
                final Logger newLoggerInstance2 = loggerFactory.makeNewLoggerInstance(s);
                newLoggerInstance2.setHierarchy(this);
                this.ht.put(categoryKey, newLoggerInstance2);
                this.updateChildren((ProvisionNode)value, newLoggerInstance2);
                this.updateParents(newLoggerInstance2);
                return newLoggerInstance2;
            }
            return null;
        }
    }
    
    public Enumeration getCurrentLoggers() {
        final Vector<Object> vector = new Vector<Object>(this.ht.size());
        final Enumeration<Object> elements = this.ht.elements();
        while (elements.hasMoreElements()) {
            final Object nextElement = elements.nextElement();
            if (nextElement instanceof Logger) {
                vector.addElement(nextElement);
            }
        }
        return vector.elements();
    }
    
    public Enumeration getCurrentCategories() {
        return this.getCurrentLoggers();
    }
    
    public RendererMap getRendererMap() {
        return this.rendererMap;
    }
    
    public Logger getRootLogger() {
        return this.root;
    }
    
    public boolean isDisabled(final int n) {
        return this.thresholdInt > n;
    }
    
    public void overrideAsNeeded(final String s) {
        LogLog.warn("The Hiearchy.overrideAsNeeded method has been deprecated.");
    }
    
    public void resetConfiguration() {
        this.getRootLogger().setLevel(Level.DEBUG);
        this.root.setResourceBundle(null);
        this.setThreshold(Level.ALL);
        synchronized (this.ht) {
            this.shutdown();
            final Enumeration currentLoggers = this.getCurrentLoggers();
            while (currentLoggers.hasMoreElements()) {
                final Logger logger = currentLoggers.nextElement();
                logger.setLevel(null);
                logger.setAdditivity(true);
                logger.setResourceBundle(null);
            }
        }
        this.rendererMap.clear();
    }
    
    public void setDisableOverride(final String s) {
        LogLog.warn("The Hiearchy.setDisableOverride method has been deprecated.");
    }
    
    public void setRenderer(final Class clazz, final ObjectRenderer objectRenderer) {
        this.rendererMap.put(clazz, objectRenderer);
    }
    
    public void shutdown() {
        final Logger rootLogger = this.getRootLogger();
        rootLogger.closeNestedAppenders();
        synchronized (this.ht) {
            final Enumeration currentLoggers = this.getCurrentLoggers();
            while (currentLoggers.hasMoreElements()) {
                currentLoggers.nextElement().closeNestedAppenders();
            }
            rootLogger.removeAllAppenders();
            final Enumeration currentLoggers2 = this.getCurrentLoggers();
            while (currentLoggers2.hasMoreElements()) {
                currentLoggers2.nextElement().removeAllAppenders();
            }
        }
    }
    
    private final void updateParents(final Logger logger) {
        final String name = logger.name;
        final int length = name.length();
        boolean b = false;
        for (int i = name.lastIndexOf(46, length - 1); i >= 0; i = name.lastIndexOf(46, i - 1)) {
            final CategoryKey categoryKey = new CategoryKey(name.substring(0, i));
            final Object value = this.ht.get(categoryKey);
            if (value == null) {
                this.ht.put(categoryKey, new ProvisionNode(logger));
            }
            else {
                if (value instanceof Category) {
                    b = true;
                    logger.parent = (Category)value;
                    break;
                }
                if (value instanceof ProvisionNode) {
                    ((ProvisionNode)value).addElement(logger);
                }
                else {
                    new IllegalStateException("unexpected object type " + ((ProvisionNode)value).getClass() + " in ht.").printStackTrace();
                }
            }
        }
        if (!b) {
            logger.parent = this.root;
        }
    }
    
    private final void updateChildren(final ProvisionNode provisionNode, final Logger parent) {
        for (int size = provisionNode.size(), i = 0; i < size; ++i) {
            final Logger logger = provisionNode.elementAt(i);
            if (!logger.parent.name.startsWith(parent.name)) {
                parent.parent = logger.parent;
                logger.parent = parent;
            }
        }
    }
}
