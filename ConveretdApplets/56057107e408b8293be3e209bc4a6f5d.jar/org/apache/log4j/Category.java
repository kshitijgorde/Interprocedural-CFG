// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import org.apache.log4j.helpers.NullEnumeration;
import java.util.Enumeration;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.LoggerRepository;
import java.util.ResourceBundle;
import org.apache.log4j.spi.AppenderAttachable;

public class Category implements AppenderAttachable
{
    protected String name;
    protected volatile Level level;
    protected volatile Category parent;
    private static final String FQCN;
    protected ResourceBundle resourceBundle;
    protected LoggerRepository repository;
    AppenderAttachableImpl aai;
    protected boolean additive;
    static /* synthetic */ Class class$org$apache$log4j$Category;
    
    protected Category(final String name) {
        this.additive = true;
        this.name = name;
    }
    
    public synchronized void addAppender(final Appender appender) {
        if (this.aai == null) {
            this.aai = new AppenderAttachableImpl();
        }
        this.aai.addAppender(appender);
        this.repository.fireAddAppenderEvent(this, appender);
    }
    
    public void assertLog(final boolean b, final String s) {
        if (!b) {
            this.error(s);
        }
    }
    
    public void callAppenders(final LoggingEvent loggingEvent) {
        int n = 0;
        for (Category parent = this; parent != null; parent = parent.parent) {
            synchronized (parent) {
                if (parent.aai != null) {
                    n += parent.aai.appendLoopOnAppenders(loggingEvent);
                }
                if (!parent.additive) {
                    break;
                }
            }
        }
        if (n == 0) {
            this.repository.emitNoAppenderWarning(this);
        }
    }
    
    synchronized void closeNestedAppenders() {
        final Enumeration allAppenders = this.getAllAppenders();
        if (allAppenders != null) {
            while (allAppenders.hasMoreElements()) {
                final Appender appender = allAppenders.nextElement();
                if (appender instanceof AppenderAttachable) {
                    appender.close();
                }
            }
        }
    }
    
    public void debug(final Object o) {
        if (this.repository.isDisabled(10000)) {
            return;
        }
        if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.DEBUG, o, null);
        }
    }
    
    public void debug(final Object o, final Throwable t) {
        if (this.repository.isDisabled(10000)) {
            return;
        }
        if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.DEBUG, o, t);
        }
    }
    
    public void error(final Object o) {
        if (this.repository.isDisabled(40000)) {
            return;
        }
        if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.ERROR, o, null);
        }
    }
    
    public void error(final Object o, final Throwable t) {
        if (this.repository.isDisabled(40000)) {
            return;
        }
        if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.ERROR, o, t);
        }
    }
    
    public static Logger exists(final String s) {
        return LogManager.exists(s);
    }
    
    public void fatal(final Object o) {
        if (this.repository.isDisabled(50000)) {
            return;
        }
        if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.FATAL, o, null);
        }
    }
    
    public void fatal(final Object o, final Throwable t) {
        if (this.repository.isDisabled(50000)) {
            return;
        }
        if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.FATAL, o, t);
        }
    }
    
    protected void forcedLog(final String s, final Priority priority, final Object o, final Throwable t) {
        this.callAppenders(new LoggingEvent(s, this, priority, o, t));
    }
    
    public boolean getAdditivity() {
        return this.additive;
    }
    
    public synchronized Enumeration getAllAppenders() {
        if (this.aai == null) {
            return NullEnumeration.getInstance();
        }
        return this.aai.getAllAppenders();
    }
    
    public synchronized Appender getAppender(final String s) {
        if (this.aai == null || s == null) {
            return null;
        }
        return this.aai.getAppender(s);
    }
    
    public Level getEffectiveLevel() {
        for (Category parent = this; parent != null; parent = parent.parent) {
            if (parent.level != null) {
                return parent.level;
            }
        }
        return null;
    }
    
    public Priority getChainedPriority() {
        for (Category parent = this; parent != null; parent = parent.parent) {
            if (parent.level != null) {
                return parent.level;
            }
        }
        return null;
    }
    
    public static Enumeration getCurrentCategories() {
        return LogManager.getCurrentLoggers();
    }
    
    public static LoggerRepository getDefaultHierarchy() {
        return LogManager.getLoggerRepository();
    }
    
    public LoggerRepository getHierarchy() {
        return this.repository;
    }
    
    public LoggerRepository getLoggerRepository() {
        return this.repository;
    }
    
    public static Category getInstance(final String s) {
        return LogManager.getLogger(s);
    }
    
    public static Category getInstance(final Class clazz) {
        return LogManager.getLogger(clazz);
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final Category getParent() {
        return this.parent;
    }
    
    public final Level getLevel() {
        return this.level;
    }
    
    public final Level getPriority() {
        return this.level;
    }
    
    public static final Category getRoot() {
        return LogManager.getRootLogger();
    }
    
    public ResourceBundle getResourceBundle() {
        for (Category parent = this; parent != null; parent = parent.parent) {
            if (parent.resourceBundle != null) {
                return parent.resourceBundle;
            }
        }
        return null;
    }
    
    protected String getResourceBundleString(final String s) {
        final ResourceBundle resourceBundle = this.getResourceBundle();
        if (resourceBundle == null) {
            return null;
        }
        try {
            return resourceBundle.getString(s);
        }
        catch (MissingResourceException ex) {
            this.error("No resource is associated with key \"" + s + "\".");
            return null;
        }
    }
    
    public void info(final Object o) {
        if (this.repository.isDisabled(20000)) {
            return;
        }
        if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.INFO, o, null);
        }
    }
    
    public void info(final Object o, final Throwable t) {
        if (this.repository.isDisabled(20000)) {
            return;
        }
        if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.INFO, o, t);
        }
    }
    
    public boolean isAttached(final Appender appender) {
        return appender != null && this.aai != null && this.aai.isAttached(appender);
    }
    
    public boolean isDebugEnabled() {
        return !this.repository.isDisabled(10000) && Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel());
    }
    
    public boolean isEnabledFor(final Priority priority) {
        return !this.repository.isDisabled(priority.level) && priority.isGreaterOrEqual(this.getEffectiveLevel());
    }
    
    public boolean isInfoEnabled() {
        return !this.repository.isDisabled(20000) && Level.INFO.isGreaterOrEqual(this.getEffectiveLevel());
    }
    
    public void l7dlog(final Priority priority, final String s, final Throwable t) {
        if (this.repository.isDisabled(priority.level)) {
            return;
        }
        if (priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            String resourceBundleString = this.getResourceBundleString(s);
            if (resourceBundleString == null) {
                resourceBundleString = s;
            }
            this.forcedLog(Category.FQCN, priority, resourceBundleString, t);
        }
    }
    
    public void l7dlog(final Priority priority, final String s, final Object[] array, final Throwable t) {
        if (this.repository.isDisabled(priority.level)) {
            return;
        }
        if (priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            final String resourceBundleString = this.getResourceBundleString(s);
            String format;
            if (resourceBundleString == null) {
                format = s;
            }
            else {
                format = MessageFormat.format(resourceBundleString, array);
            }
            this.forcedLog(Category.FQCN, priority, format, t);
        }
    }
    
    public void log(final Priority priority, final Object o, final Throwable t) {
        if (this.repository.isDisabled(priority.level)) {
            return;
        }
        if (priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, priority, o, t);
        }
    }
    
    public void log(final Priority priority, final Object o) {
        if (this.repository.isDisabled(priority.level)) {
            return;
        }
        if (priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, priority, o, null);
        }
    }
    
    public void log(final String s, final Priority priority, final Object o, final Throwable t) {
        if (this.repository.isDisabled(priority.level)) {
            return;
        }
        if (priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(s, priority, o, t);
        }
    }
    
    public synchronized void removeAllAppenders() {
        if (this.aai != null) {
            this.aai.removeAllAppenders();
            this.aai = null;
        }
    }
    
    public synchronized void removeAppender(final Appender appender) {
        if (appender == null || this.aai == null) {
            return;
        }
        this.aai.removeAppender(appender);
    }
    
    public synchronized void removeAppender(final String s) {
        if (s == null || this.aai == null) {
            return;
        }
        this.aai.removeAppender(s);
    }
    
    public void setAdditivity(final boolean additive) {
        this.additive = additive;
    }
    
    final void setHierarchy(final LoggerRepository repository) {
        this.repository = repository;
    }
    
    public void setLevel(final Level level) {
        this.level = level;
    }
    
    public void setPriority(final Priority priority) {
        this.level = (Level)priority;
    }
    
    public void setResourceBundle(final ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
    
    public static void shutdown() {
        LogManager.shutdown();
    }
    
    public void warn(final Object o) {
        if (this.repository.isDisabled(30000)) {
            return;
        }
        if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.WARN, o, null);
        }
    }
    
    public void warn(final Object o, final Throwable t) {
        if (this.repository.isDisabled(30000)) {
            return;
        }
        if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Category.FQCN, Level.WARN, o, t);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        FQCN = ((Category.class$org$apache$log4j$Category == null) ? (Category.class$org$apache$log4j$Category = class$("org.apache.log4j.Category")) : Category.class$org$apache$log4j$Category).getName();
    }
}
