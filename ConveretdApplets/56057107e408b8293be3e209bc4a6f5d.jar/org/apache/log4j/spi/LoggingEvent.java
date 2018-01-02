// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.spi;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.Loader;
import java.lang.reflect.Method;
import java.io.ObjectInputStream;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.Level;
import java.util.Hashtable;
import org.apache.log4j.Priority;
import org.apache.log4j.Category;
import java.io.Serializable;

public class LoggingEvent implements Serializable
{
    private static long startTime;
    public final transient String fqnOfCategoryClass;
    private transient Category logger;
    public final String categoryName;
    public transient Priority level;
    private String ndc;
    private Hashtable mdcCopy;
    private boolean ndcLookupRequired;
    private boolean mdcCopyLookupRequired;
    private transient Object message;
    private String renderedMessage;
    private String threadName;
    private ThrowableInformation throwableInfo;
    public final long timeStamp;
    private LocationInfo locationInfo;
    static final long serialVersionUID = -868428216207166145L;
    static final Integer[] PARAM_ARRAY;
    static final String TO_LEVEL = "toLevel";
    static final Class[] TO_LEVEL_PARAMS;
    static final Hashtable methodCache;
    static /* synthetic */ Class class$org$apache$log4j$Level;
    
    public LoggingEvent(final String fqnOfCategoryClass, final Category logger, final Priority level, final Object message, final Throwable t) {
        this.ndcLookupRequired = true;
        this.mdcCopyLookupRequired = true;
        this.fqnOfCategoryClass = fqnOfCategoryClass;
        this.logger = logger;
        this.categoryName = logger.getName();
        this.level = level;
        this.message = message;
        if (t != null) {
            this.throwableInfo = new ThrowableInformation(t);
        }
        this.timeStamp = System.currentTimeMillis();
    }
    
    public LoggingEvent(final String fqnOfCategoryClass, final Category logger, final long timeStamp, final Priority level, final Object message, final Throwable t) {
        this.ndcLookupRequired = true;
        this.mdcCopyLookupRequired = true;
        this.fqnOfCategoryClass = fqnOfCategoryClass;
        this.logger = logger;
        this.categoryName = logger.getName();
        this.level = level;
        this.message = message;
        if (t != null) {
            this.throwableInfo = new ThrowableInformation(t);
        }
        this.timeStamp = timeStamp;
    }
    
    public LocationInfo getLocationInformation() {
        if (this.locationInfo == null) {
            this.locationInfo = new LocationInfo(new Throwable(), this.fqnOfCategoryClass);
        }
        return this.locationInfo;
    }
    
    public Level getLevel() {
        return (Level)this.level;
    }
    
    public String getLoggerName() {
        return this.categoryName;
    }
    
    public Object getMessage() {
        if (this.message != null) {
            return this.message;
        }
        return this.getRenderedMessage();
    }
    
    public String getNDC() {
        if (this.ndcLookupRequired) {
            this.ndcLookupRequired = false;
            this.ndc = NDC.get();
        }
        return this.ndc;
    }
    
    public Object getMDC(final String s) {
        if (this.mdcCopy != null) {
            final Object value = this.mdcCopy.get(s);
            if (value != null) {
                return value;
            }
        }
        return MDC.get(s);
    }
    
    public void getMDCCopy() {
        if (this.mdcCopyLookupRequired) {
            this.mdcCopyLookupRequired = false;
            final Hashtable context = MDC.getContext();
            if (context != null) {
                this.mdcCopy = (Hashtable)context.clone();
            }
        }
    }
    
    public String getRenderedMessage() {
        if (this.renderedMessage == null && this.message != null) {
            if (this.message instanceof String) {
                this.renderedMessage = (String)this.message;
            }
            else {
                final LoggerRepository loggerRepository = this.logger.getLoggerRepository();
                if (loggerRepository instanceof RendererSupport) {
                    this.renderedMessage = ((RendererSupport)loggerRepository).getRendererMap().findAndRender(this.message);
                }
                else {
                    this.renderedMessage = this.message.toString();
                }
            }
        }
        return this.renderedMessage;
    }
    
    public static long getStartTime() {
        return LoggingEvent.startTime;
    }
    
    public String getThreadName() {
        if (this.threadName == null) {
            this.threadName = Thread.currentThread().getName();
        }
        return this.threadName;
    }
    
    public ThrowableInformation getThrowableInformation() {
        return this.throwableInfo;
    }
    
    public String[] getThrowableStrRep() {
        if (this.throwableInfo == null) {
            return null;
        }
        return this.throwableInfo.getThrowableStrRep();
    }
    
    private void readLevel(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        final int int1 = objectInputStream.readInt();
        try {
            final String s = (String)objectInputStream.readObject();
            if (s == null) {
                this.level = Level.toLevel(int1);
            }
            else {
                Method declaredMethod = LoggingEvent.methodCache.get(s);
                if (declaredMethod == null) {
                    declaredMethod = Loader.loadClass(s).getDeclaredMethod("toLevel", (Class[])LoggingEvent.TO_LEVEL_PARAMS);
                    LoggingEvent.methodCache.put(s, declaredMethod);
                }
                LoggingEvent.PARAM_ARRAY[0] = new Integer(int1);
                this.level = (Level)declaredMethod.invoke(null, (Object[])LoggingEvent.PARAM_ARRAY);
            }
        }
        catch (Exception ex) {
            LogLog.warn("Level deserialization failed, reverting to default.", ex);
            this.level = Level.toLevel(int1);
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.readLevel(objectInputStream);
        if (this.locationInfo == null) {
            this.locationInfo = new LocationInfo(null, null);
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        this.getThreadName();
        this.getRenderedMessage();
        this.getNDC();
        this.getMDCCopy();
        this.getThrowableStrRep();
        objectOutputStream.defaultWriteObject();
        this.writeLevel(objectOutputStream);
    }
    
    private void writeLevel(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.level.toInt());
        final Class<? extends Priority> class1 = this.level.getClass();
        if (class1 == ((LoggingEvent.class$org$apache$log4j$Level == null) ? (LoggingEvent.class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : LoggingEvent.class$org$apache$log4j$Level)) {
            objectOutputStream.writeObject(null);
        }
        else {
            objectOutputStream.writeObject(class1.getName());
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
        LoggingEvent.startTime = System.currentTimeMillis();
        PARAM_ARRAY = new Integer[1];
        TO_LEVEL_PARAMS = new Class[] { Integer.TYPE };
        methodCache = new Hashtable(3);
    }
}
