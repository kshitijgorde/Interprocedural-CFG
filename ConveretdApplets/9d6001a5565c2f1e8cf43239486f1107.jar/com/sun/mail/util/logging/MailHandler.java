// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.mail.util.logging;

import java.util.Hashtable;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import javax.mail.internet.AddressException;
import java.util.Date;
import javax.mail.Address;
import java.net.UnknownHostException;
import java.net.InetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.Transport;
import java.io.UnsupportedEncodingException;
import java.util.logging.LogManager;
import java.util.logging.ErrorManager;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.logging.SimpleFormatter;
import javax.mail.internet.ParameterList;
import javax.mail.internet.ContentType;
import javax.mail.MessagingException;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.ByteArrayInputStream;
import javax.mail.MessageContext;
import javax.activation.FileTypeMap;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.Comparator;
import java.util.logging.LogRecord;
import javax.mail.Session;
import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Handler;

public class MailHandler extends Handler
{
    private static final int offValue;
    private volatile boolean sealed;
    private boolean isWriting;
    private Properties mailProps;
    private Authenticator auth;
    private Session session;
    private LogRecord[] data;
    private int size;
    private int capacity;
    private Comparator comparator;
    private Formatter subjectFormatter;
    private Level pushLevel;
    private Filter pushFilter;
    private Filter[] attachmentFilters;
    private Formatter[] attachmentFormatters;
    private Formatter[] attachmentNames;
    private FileTypeMap contentTypes;
    static /* synthetic */ Class class$com$sun$mail$util$logging$MailHandler;
    static /* synthetic */ Class class$java$util$logging$ErrorManager;
    static /* synthetic */ Class class$javax$mail$Authenticator;
    static /* synthetic */ Class class$java$util$logging$Filter;
    static /* synthetic */ Class class$java$util$logging$Formatter;
    static /* synthetic */ Class class$java$util$Comparator;
    
    public MailHandler() {
        this.init();
        this.sealed = true;
    }
    
    public MailHandler(final int capacity) {
        this.init();
        this.sealed = true;
        this.setCapacity0(capacity);
    }
    
    public MailHandler(final Properties props) {
        this.init();
        this.sealed = true;
        this.setMailProperties0(props);
    }
    
    public boolean isLoggable(final LogRecord record) {
        final int levelValue = this.getLevel().intValue();
        if (record.getLevel().intValue() < levelValue || levelValue == MailHandler.offValue) {
            return false;
        }
        final Filter body = this.getFilter();
        return body == null || body.isLoggable(record) || this.isAttachmentLoggable(record);
    }
    
    public void publish(final LogRecord record) {
        if (this.isLoggable(record)) {
            record.getSourceMethodName();
            final boolean priority;
            MessageContext ctx;
            synchronized (this) {
                this.data[this.size] = record;
                ++this.size;
                priority = this.isPushable(record);
                if (priority || this.size >= this.capacity) {
                    ctx = this.writeLogRecords(1);
                }
                else {
                    ctx = null;
                    if (this.data.length == this.size) {
                        this.grow();
                    }
                }
            }
            if (ctx != null) {
                this.send(ctx, priority, 1);
            }
        }
    }
    
    public void push() {
        this.push(true, 2);
    }
    
    public void flush() {
        this.push(false, 2);
    }
    
    public void close() {
        MessageContext ctx = null;
        synchronized (this) {
            super.setLevel(Level.OFF);
            try {
                if (this.size > 0) {
                    ctx = this.writeLogRecords(3);
                }
            }
            finally {
                if (this.capacity > 0) {
                    this.capacity = -this.capacity;
                }
                if (this.size == 0 && this.data.length != 1) {
                    this.data = new LogRecord[1];
                }
            }
        }
        if (ctx != null) {
            this.send(ctx, false, 3);
        }
    }
    
    public synchronized void setLevel(final Level newLevel) {
        if (this.capacity > 0) {
            super.setLevel(newLevel);
        }
        else {
            if (newLevel == null) {
                throw new NullPointerException();
            }
            this.checkAccess();
        }
    }
    
    public final synchronized Level getPushLevel() {
        return this.pushLevel;
    }
    
    public final synchronized void setPushLevel(final Level level) {
        this.checkAccess();
        if (level == null) {
            throw new NullPointerException();
        }
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.pushLevel = level;
    }
    
    public final synchronized Filter getPushFilter() {
        return this.pushFilter;
    }
    
    public final synchronized void setPushFilter(final Filter filter) {
        this.checkAccess();
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.pushFilter = filter;
    }
    
    public final synchronized Comparator getComparator() {
        return this.comparator;
    }
    
    public final synchronized void setComparator(final Comparator c) {
        this.checkAccess();
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.comparator = c;
    }
    
    public final synchronized int getCapacity() {
        assert this.capacity != Integer.MIN_VALUE && this.capacity != 0 : this.capacity;
        return Math.abs(this.capacity);
    }
    
    public final synchronized Authenticator getAuthenticator() {
        this.checkAccess();
        return this.auth;
    }
    
    public final void setAuthenticator(final Authenticator auth) {
        this.checkAccess();
        final Session settings;
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.auth = auth;
            settings = this.fixUpSession();
        }
        this.verifySettings(settings);
    }
    
    public final void setMailProperties(final Properties props) {
        this.setMailProperties0(props);
    }
    
    private void setMailProperties0(Properties props) {
        this.checkAccess();
        props = (Properties)props.clone();
        final Session settings;
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.mailProps = props;
            settings = this.fixUpSession();
        }
        this.verifySettings(settings);
    }
    
    public final Properties getMailProperties() {
        this.checkAccess();
        final Properties props;
        synchronized (this) {
            props = this.mailProps;
        }
        return (Properties)props.clone();
    }
    
    public final Filter[] getAttachmentFilters() {
        return this.readOnlyAttachmentFilters().clone();
    }
    
    public final void setAttachmentFilters(Filter[] filters) {
        this.checkAccess();
        filters = filters.clone();
        synchronized (this) {
            if (this.attachmentFormatters.length != filters.length) {
                throw attachmentMismatch(this.attachmentFormatters.length, filters.length);
            }
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.attachmentFilters = filters;
        }
    }
    
    public final Formatter[] getAttachmentFormatters() {
        final Formatter[] formatters;
        synchronized (this) {
            formatters = this.attachmentFormatters;
        }
        return formatters.clone();
    }
    
    public final void setAttachmentFormatters(Formatter[] formatters) {
        this.checkAccess();
        formatters = formatters.clone();
        for (int i = 0; i < formatters.length; ++i) {
            if (formatters[i] == null) {
                throw new NullPointerException(atIndexMsg(i));
            }
        }
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.attachmentFormatters = formatters;
            this.fixUpAttachmentFilters();
            this.fixUpAttachmentNames();
        }
    }
    
    public final Formatter[] getAttachmentNames() {
        final Formatter[] formatters;
        synchronized (this) {
            formatters = this.attachmentNames;
        }
        return formatters.clone();
    }
    
    public final void setAttachmentNames(final String[] names) {
        this.checkAccess();
        final Formatter[] formatters = new Formatter[names.length];
        for (int i = 0; i < names.length; ++i) {
            final String name = names[i];
            if (name == null) {
                throw new NullPointerException(atIndexMsg(i));
            }
            if (name.length() <= 0) {
                throw new IllegalArgumentException(atIndexMsg(i));
            }
            formatters[i] = new TailNameFormatter(name);
        }
        synchronized (this) {
            if (this.attachmentFormatters.length != names.length) {
                throw attachmentMismatch(this.attachmentFormatters.length, names.length);
            }
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.attachmentNames = formatters;
        }
    }
    
    public final void setAttachmentNames(Formatter[] formatters) {
        this.checkAccess();
        formatters = formatters.clone();
        for (int i = 0; i < formatters.length; ++i) {
            if (formatters[i] == null) {
                throw new NullPointerException(atIndexMsg(i));
            }
        }
        synchronized (this) {
            if (this.attachmentFormatters.length != formatters.length) {
                throw attachmentMismatch(this.attachmentFormatters.length, formatters.length);
            }
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.attachmentNames = formatters;
        }
    }
    
    public final synchronized Formatter getSubject() {
        return this.subjectFormatter;
    }
    
    public final void setSubject(final String subject) {
        if (subject != null) {
            this.setSubject(new TailNameFormatter(subject));
            return;
        }
        this.checkAccess();
        throw new NullPointerException();
    }
    
    public final void setSubject(final Formatter format) {
        this.checkAccess();
        if (format == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.subjectFormatter = format;
        }
    }
    
    protected void reportError(final String msg, final Exception ex, final int code) {
        if (msg != null) {
            super.reportError(Level.SEVERE.getName() + ": " + msg, ex, code);
        }
        else {
            super.reportError(null, ex, code);
        }
    }
    
    final void checkAccess() {
        if (this.sealed) {
            LogManagerProperties.manager.checkAccess();
        }
    }
    
    final String contentTypeOf(String head) {
        if (head != null && head.length() > 0) {
            final int MAX_CHARS = 15;
            if (head.length() > 15) {
                head = head.substring(0, 15);
            }
            try {
                final String encoding = this.getEncoding();
                ByteArrayInputStream in;
                if (encoding == null) {
                    in = new ByteArrayInputStream(head.getBytes());
                }
                else {
                    in = new ByteArrayInputStream(head.getBytes(encoding));
                }
                assert in.markSupported() : in.getClass().getName();
                return URLConnection.guessContentTypeFromStream(in);
            }
            catch (IOException IOE) {
                this.reportError(IOE.getMessage(), IOE, 5);
            }
        }
        return null;
    }
    
    private String getContentType(final String name) {
        assert Thread.holdsLock(this);
        final String type = this.contentTypes.getContentType(name);
        if ("application/octet-stream".equalsIgnoreCase(type)) {
            return null;
        }
        return type;
    }
    
    private void setContent(final MimeBodyPart part, final StringBuffer buf, String type) throws MessagingException {
        final String encoding = this.getEncoding();
        if (type != null && !"text/plain".equals(type)) {
            if (encoding == null) {
                type = this.contentWithDefault(type);
            }
            else {
                type = this.contentWithEncoding(type, encoding);
            }
            try {
                final DataSource source = new ByteArrayDataSource(buf.toString(), type);
                part.setDataHandler(new DataHandler(source));
            }
            catch (IOException IOE) {
                this.reportError(IOE.getMessage(), IOE, 5);
                part.setText(buf.toString(), encoding);
            }
        }
        else {
            part.setText(buf.toString(), encoding);
        }
    }
    
    private String contentWithEncoding(String type, String encoding) {
        try {
            final ContentType ct = new ContentType(type);
            ct.setParameter("charset", encoding);
            encoding = ct.toString();
            if (encoding != null) {
                type = encoding;
            }
        }
        catch (MessagingException ME) {
            this.reportError(type, ME, 5);
        }
        return type;
    }
    
    private String contentWithDefault(String type) {
        try {
            final ContentType ct = new ContentType(type);
            if (ct.getParameter("charset") != null) {
                final ParameterList list = ct.getParameterList();
                list.remove("charset");
                ct.setParameterList(list);
                final String newType = ct.toString();
                if (newType != null) {
                    type = newType;
                }
            }
        }
        catch (MessagingException ME) {
            this.reportError(type, ME, 5);
        }
        return type;
    }
    
    private synchronized void setCapacity0(final int newCapacity) {
        if (newCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        if (this.capacity < 0) {
            this.capacity = -newCapacity;
        }
        else {
            this.capacity = newCapacity;
        }
    }
    
    private synchronized Filter[] readOnlyAttachmentFilters() {
        return this.attachmentFilters;
    }
    
    private void fixUpAttachmentFormatters() {
        assert Thread.holdsLock(this);
        for (int attachments = this.attachmentFormatters.length, i = 0; i < attachments; ++i) {
            if (this.attachmentFormatters[i] == null) {
                final NullPointerException NPE = new NullPointerException(atIndexMsg(i));
                this.attachmentFormatters[i] = new SimpleFormatter();
                this.reportError("attachment formatter.", NPE, 4);
            }
            else if (this.attachmentFormatters[i] instanceof TailNameFormatter) {
                final ClassNotFoundException CNFE = new ClassNotFoundException(this.attachmentFormatters[i].toString());
                this.attachmentFormatters[i] = new SimpleFormatter();
                this.reportError("attachment formatter.", CNFE, 4);
            }
        }
    }
    
    private boolean fixUpAttachmentNames() {
        assert Thread.holdsLock(this);
        boolean fixed = false;
        final int expect = this.attachmentFormatters.length;
        final int current = this.attachmentNames.length;
        if (current != expect) {
            this.attachmentNames = (Formatter[])copyOf(this.attachmentNames, expect);
            fixed = (current != 0);
        }
        for (int i = 0; i < expect; ++i) {
            if (this.attachmentNames[i] == null) {
                this.attachmentNames[i] = new TailNameFormatter(this.toString(this.attachmentFormatters[i]));
            }
        }
        return fixed;
    }
    
    private boolean fixUpAttachmentFilters() {
        assert Thread.holdsLock(this);
        final int expect = this.attachmentFormatters.length;
        final int current = this.attachmentFilters.length;
        if (current != expect) {
            this.attachmentFilters = (Filter[])copyOf(this.attachmentFilters, expect);
            return current != 0;
        }
        return false;
    }
    
    private static Object[] copyOf(final Object[] a, final int size) {
        final Object[] copy = (Object[])Array.newInstance(a.getClass().getComponentType(), size);
        System.arraycopy(a, 0, copy, 0, Math.min(a.length, size));
        return copy;
    }
    
    private void reset() {
        assert Thread.holdsLock(this);
        Arrays.fill(this.data, 0, this.size, null);
        this.size = 0;
    }
    
    private void grow() {
        assert Thread.holdsLock(this);
        final int len = this.data.length;
        int newCapacity = len + (len >> 1) + 1;
        if (newCapacity > this.capacity || newCapacity < len) {
            newCapacity = this.capacity;
        }
        assert len != this.capacity : len;
        this.data = (LogRecord[])copyOf(this.data, newCapacity);
    }
    
    private synchronized void init() {
        final LogManager manager = LogManagerProperties.manager;
        final String p = this.getClass().getName();
        this.mailProps = new Properties();
        this.contentTypes = FileTypeMap.getDefaultFileTypeMap();
        final ErrorManager em = (ErrorManager)this.initObject(p.concat(".errorManager"), (MailHandler.class$java$util$logging$ErrorManager == null) ? (MailHandler.class$java$util$logging$ErrorManager = class$("java.util.logging.ErrorManager")) : MailHandler.class$java$util$logging$ErrorManager);
        if (em != null) {
            this.setErrorManager(em);
        }
        this.initLevel(manager, p);
        this.initFilter(p);
        this.initCapacity(manager, p);
        this.auth = (Authenticator)this.initObject(p.concat(".authenticator"), (MailHandler.class$javax$mail$Authenticator == null) ? (MailHandler.class$javax$mail$Authenticator = class$("javax.mail.Authenticator")) : MailHandler.class$javax$mail$Authenticator);
        final Session settings = this.fixUpSession();
        this.initEncoding(manager, p);
        this.initFormatter(p);
        this.initComparator(p);
        this.initPushLevel(manager, p);
        this.pushFilter = (Filter)this.initObject(p.concat(".pushFilter"), (MailHandler.class$java$util$logging$Filter == null) ? (MailHandler.class$java$util$logging$Filter = class$("java.util.logging.Filter")) : MailHandler.class$java$util$logging$Filter);
        this.initSubject(p);
        this.attachmentFormatters = (Formatter[])this.initArray(p.concat(".attachment.formatters"), (MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter);
        this.attachmentFilters = (Filter[])this.initArray(p.concat(".attachment.filters"), (MailHandler.class$java$util$logging$Filter == null) ? (MailHandler.class$java$util$logging$Filter = class$("java.util.logging.Filter")) : MailHandler.class$java$util$logging$Filter);
        this.attachmentNames = (Formatter[])this.initArray(p.concat(".attachment.names"), (MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter);
        this.fixUpAttachmentFormatters();
        if (this.fixUpAttachmentFilters()) {
            this.reportError("attachment filters.", attachmentMismatch("length mismatch"), 4);
        }
        if (this.fixUpAttachmentNames()) {
            this.reportError("attachment names.", attachmentMismatch("length mismatch"), 4);
        }
        this.verifySettings(settings);
    }
    
    private Object objectFromNew(final String name, final Class type) throws NoSuchMethodException {
        final Object obj = null;
        try {
            try {
                try {
                    final Class clazz = LogManagerProperties.findClass(name);
                    if (type.isAssignableFrom(clazz)) {
                        return clazz.getConstructor((Class<?>[])null).newInstance((Object[])null);
                    }
                    throw new ClassCastException(clazz.getName() + " cannot be cast to " + type.getName());
                }
                catch (NoClassDefFoundError NCDFE) {
                    throw (ClassNotFoundException)new ClassNotFoundException(NCDFE.getMessage()).initCause(NCDFE);
                }
            }
            catch (ClassNotFoundException CNFE) {
                if (type == ((MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter)) {
                    return new TailNameFormatter(name);
                }
                throw CNFE;
            }
            catch (ClassCastException CCE) {
                if (type == ((MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter)) {
                    return new TailNameFormatter(name);
                }
                throw CCE;
            }
        }
        catch (NoSuchMethodException NSME) {
            throw NSME;
        }
        catch (Exception E) {
            this.reportError(E.getMessage(), E, 4);
            return obj;
        }
    }
    
    private Object initObject(final String key, final Class type) {
        final String name = LogManagerProperties.manager.getProperty(key);
        if (name != null && name.length() > 0 && !"null".equalsIgnoreCase(name)) {
            try {
                return this.objectFromNew(name, type);
            }
            catch (NoSuchMethodException E) {
                this.reportError(E.getMessage(), E, 4);
            }
        }
        return null;
    }
    
    private Object[] initArray(final String key, final Class type) {
        final String list = LogManagerProperties.manager.getProperty(key);
        if (list != null && list.length() > 0) {
            final String[] names = list.split(",");
            final Object[] a = (Object[])Array.newInstance(type, names.length);
            for (int i = 0; i < a.length; ++i) {
                names[i] = names[i].trim();
                if (!"null".equalsIgnoreCase(names[i])) {
                    try {
                        a[i] = this.objectFromNew(names[i], type);
                    }
                    catch (NoSuchMethodException E) {
                        this.reportError(E.getMessage(), E, 4);
                    }
                }
            }
            return a;
        }
        return (Object[])Array.newInstance(type, 0);
    }
    
    private void initLevel(final LogManager manager, final String p) {
        assert Thread.holdsLock(this);
        try {
            final String val = manager.getProperty(p.concat(".level"));
            if (val != null) {
                super.setLevel(Level.parse(val));
            }
            else {
                super.setLevel(Level.WARNING);
            }
        }
        catch (SecurityException SE) {
            throw SE;
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
            try {
                super.setLevel(Level.WARNING);
            }
            catch (RuntimeException fail) {
                this.reportError(fail.getMessage(), fail, 4);
            }
        }
    }
    
    private void initFilter(final String p) {
        assert Thread.holdsLock(this);
        try {
            super.setFilter((Filter)this.initObject(p.concat(".filter"), (MailHandler.class$java$util$logging$Filter == null) ? (MailHandler.class$java$util$logging$Filter = class$("java.util.logging.Filter")) : MailHandler.class$java$util$logging$Filter));
        }
        catch (SecurityException SE) {
            throw SE;
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
        }
    }
    
    private void initCapacity(final LogManager manager, final String p) {
        assert Thread.holdsLock(this);
        final int DEFAULT_CAPACITY = 1000;
        try {
            final String value = manager.getProperty(p.concat(".capacity"));
            if (value != null) {
                this.setCapacity0(Integer.parseInt(value));
            }
            else {
                this.setCapacity0(1000);
            }
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
        }
        if (this.capacity <= 0) {
            this.capacity = 1000;
        }
        this.data = new LogRecord[1];
    }
    
    private void initEncoding(final LogManager manager, final String p) {
        assert Thread.holdsLock(this);
        try {
            super.setEncoding(manager.getProperty(p.concat(".encoding")));
        }
        catch (SecurityException SE) {
            throw SE;
        }
        catch (UnsupportedEncodingException UEE) {
            this.reportError(UEE.getMessage(), UEE, 4);
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
        }
    }
    
    private void initFormatter(final String p) {
        assert Thread.holdsLock(this);
        try {
            final Formatter formatter = (Formatter)this.initObject(p.concat(".formatter"), (MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter);
            if (formatter != null && !(formatter instanceof TailNameFormatter)) {
                super.setFormatter(formatter);
            }
            else {
                super.setFormatter(new SimpleFormatter());
            }
        }
        catch (SecurityException SE) {
            throw SE;
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
            try {
                super.setFormatter(new SimpleFormatter());
            }
            catch (RuntimeException fail) {
                this.reportError(fail.getMessage(), fail, 4);
            }
        }
    }
    
    private void initComparator(final String p) {
        assert Thread.holdsLock(this);
        try {
            this.comparator = (Comparator)this.initObject(p.concat(".comparator"), (MailHandler.class$java$util$Comparator == null) ? (MailHandler.class$java$util$Comparator = class$("java.util.Comparator")) : MailHandler.class$java$util$Comparator);
        }
        catch (Exception RE) {
            this.reportError(RE.getMessage(), RE, 4);
        }
    }
    
    private void initPushLevel(final LogManager manager, final String p) {
        assert Thread.holdsLock(this);
        try {
            final String val = manager.getProperty(p.concat(".pushLevel"));
            if (val != null) {
                this.pushLevel = Level.parse(val);
            }
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 4);
        }
        if (this.pushLevel == null) {
            this.pushLevel = Level.OFF;
        }
    }
    
    private void initSubject(final String p) {
        assert Thread.holdsLock(this);
        this.subjectFormatter = (Formatter)this.initObject(p.concat(".subject"), (MailHandler.class$java$util$logging$Formatter == null) ? (MailHandler.class$java$util$logging$Formatter = class$("java.util.logging.Formatter")) : MailHandler.class$java$util$logging$Formatter);
        if (this.subjectFormatter == null) {
            this.subjectFormatter = new TailNameFormatter("");
        }
    }
    
    private boolean isAttachmentLoggable(final LogRecord record) {
        final Filter[] filters = this.readOnlyAttachmentFilters();
        for (int i = 0; i < filters.length; ++i) {
            final Filter f = filters[i];
            if (f == null || f.isLoggable(record)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isPushable(final LogRecord record) {
        assert Thread.holdsLock(this);
        final int value = this.getPushLevel().intValue();
        if (value == MailHandler.offValue || record.getLevel().intValue() < value) {
            return false;
        }
        final Filter filter = this.getPushFilter();
        return filter == null || filter.isLoggable(record);
    }
    
    private void push(final boolean priority, final int code) {
        MessageContext ctx = null;
        synchronized (this) {
            if (this.size > 0) {
                ctx = this.writeLogRecords(code);
            }
        }
        if (ctx != null) {
            this.send(ctx, priority, code);
        }
    }
    
    private void send(final MessageContext ctx, final boolean priority, final int code) {
        final Message msg = ctx.getMessage();
        try {
            this.envelopeFor(ctx, priority);
            Transport.send(msg);
        }
        catch (Exception E) {
            try {
                super.reportError(this.toRawString(msg), E, code);
            }
            catch (MessagingException rawMe) {
                this.reportError(this.toMsgString(rawMe), E, code);
            }
            catch (IOException rawIo) {
                this.reportError(this.toMsgString(rawIo), E, code);
            }
        }
    }
    
    private void sort() {
        assert Thread.holdsLock(this);
        if (this.comparator != null) {
            try {
                Arrays.sort(this.data, 0, this.size, this.comparator);
            }
            catch (RuntimeException RE) {
                this.reportError(RE.getMessage(), RE, 5);
            }
        }
    }
    
    private synchronized MessageContext writeLogRecords(final int code) {
        assert this.size > 0;
        if (this.isWriting) {
            return null;
        }
        this.isWriting = true;
        try {
            final MimeMessage msg = new MimeMessage(this.session);
            this.sort();
            MimeBodyPart[] parts = new MimeBodyPart[this.attachmentFormatters.length];
            final StringBuffer[] buffers = new StringBuffer[parts.length];
            String contentType = null;
            StringBuffer buf = null;
            this.appendSubject(msg, this.head(this.subjectFormatter));
            final Formatter bodyFormat = this.getFormatter();
            final Filter bodyFilter = this.getFilter();
            for (int ix = 0; ix < this.size; ++ix) {
                final LogRecord r = this.data[ix];
                this.data[ix] = null;
                this.appendSubject(msg, this.format(this.subjectFormatter, r));
                if (bodyFilter == null || bodyFilter.isLoggable(r)) {
                    if (buf == null) {
                        buf = new StringBuffer();
                        final String head = this.head(bodyFormat);
                        buf.append(head);
                        contentType = this.contentTypeOf(head);
                    }
                    buf.append(this.format(bodyFormat, r));
                }
                for (int i = 0; i < parts.length; ++i) {
                    final Filter af = this.attachmentFilters[i];
                    if (af == null || af.isLoggable(r)) {
                        if (parts[i] == null) {
                            parts[i] = this.createBodyPart(i);
                            (buffers[i] = new StringBuffer()).append(this.head(this.attachmentFormatters[i]));
                            this.appendFileName(parts[i], this.head(this.attachmentNames[i]));
                        }
                        this.appendFileName(parts[i], this.format(this.attachmentNames[i], r));
                        buffers[i].append(this.format(this.attachmentFormatters[i], r));
                    }
                }
            }
            this.size = 0;
            for (int j = parts.length - 1; j >= 0; --j) {
                if (parts[j] != null) {
                    this.appendFileName(parts[j], this.tail(this.attachmentNames[j], "err"));
                    buffers[j].append(this.tail(this.attachmentFormatters[j], ""));
                    if (buffers[j].length() > 0) {
                        String name = parts[j].getFileName();
                        if (name == null || name.length() == 0) {
                            name = this.toString(this.attachmentFormatters[j]);
                            parts[j].setFileName(name);
                        }
                        this.setContent(parts[j], buffers[j], this.getContentType(name));
                    }
                    else {
                        parts[j] = null;
                    }
                    buffers[j] = null;
                }
            }
            if (buf != null) {
                buf.append(this.tail(bodyFormat, ""));
            }
            else {
                buf = new StringBuffer(0);
            }
            this.appendSubject(msg, this.tail(this.subjectFormatter, ""));
            final MimeMultipart multipart = new MimeMultipart();
            final MimeBodyPart body = this.createBodyPart();
            final String altType = this.getContentType(bodyFormat.getClass().getName());
            this.setContent(body, buf, (altType == null) ? contentType : altType);
            buf = null;
            multipart.addBodyPart(body);
            for (int k = 0; k < parts.length; ++k) {
                if (parts[k] != null) {
                    multipart.addBodyPart(parts[k]);
                }
            }
            parts = null;
            msg.setContent(multipart);
            return new MessageContext(msg);
        }
        catch (RuntimeException re) {
            this.reportError(re.getMessage(), re, code);
        }
        catch (Exception e) {
            this.reportError(e.getMessage(), e, code);
        }
        finally {
            this.isWriting = false;
            if (this.size > 0) {
                this.reset();
            }
        }
        return null;
    }
    
    private void verifySettings(final Session session) {
        final String key = "verify";
        final Properties props = session.getProperties();
        String value;
        synchronized (props) {
            value = ((Hashtable<K, String>)props).get(key);
            if (value != null) {
                return;
            }
            value = props.getProperty(key);
            ((Hashtable<String, String>)props).put(key, "");
        }
        if (value != null && value.length() > 0 && !value.equals("null")) {
            this.verifySettings0(session, value);
        }
    }
    
    private void verifySettings0(final Session session, final String verify) {
        assert verify != null : null;
        if (!"local".equals(verify) && !"remote".equals(verify)) {
            this.reportError("Verify must be 'local' or 'remote'.", new IllegalArgumentException(verify), 4);
            return;
        }
        final MimeMessage abort = new MimeMessage(session);
        this.envelopeFor(new MessageContext(abort), true);
        String msg;
        if (InternetAddress.getLocalAddress(session) == null) {
            msg = "Local address is null.";
        }
        else {
            msg = "";
        }
        try {
            final Address[] all = abort.getAllRecipients();
            Transport t;
            try {
                if (all == null || all.length <= 0) {
                    final MessagingException me = new MessagingException("No recipient addresses.");
                    this.reportError(msg, me, 4);
                    throw me;
                }
                t = session.getTransport(all[0]);
                session.getProperty("mail.transport.protocol");
            }
            catch (MessagingException protocol) {
                try {
                    t = session.getTransport();
                }
                catch (MessagingException fail) {
                    fail.setNextException(protocol);
                    throw fail;
                }
            }
            if ("remote".equals(verify)) {
                t.connect();
                try {
                    t.sendMessage(abort, all);
                    this.reportError(msg, new MessagingException("An empty message was sent."), 1);
                }
                catch (MessagingException expectNoContent) {}
                finally {
                    t.close();
                }
            }
            else {
                final String protocol2 = t.getURLName().getProtocol();
                session.getProperty("mail.host");
                session.getProperty("mail.user");
                session.getProperty("mail." + protocol2 + ".host");
                session.getProperty("mail." + protocol2 + ".port");
                session.getProperty("mail." + protocol2 + ".user");
            }
            final Address[] from = abort.getFrom();
            final Address sender = abort.getSender();
            if (abort.getHeader("From", ",") != null) {
                assert from != null;
                for (int i = 0; i < from.length; ++i) {
                    if (from[i].equals(sender)) {
                        this.reportError(msg, new MessagingException("Sender address equals from address."), 4);
                        break;
                    }
                }
            }
            if (all != null) {
                for (int i = 0; i < all.length; ++i) {
                    final Address a = all[i];
                    if (a instanceof InternetAddress) {
                        ((InternetAddress)a).validate();
                    }
                }
            }
            if ("local".equals(verify)) {
                try {
                    if (InetAddress.getLocalHost().getHostName().length() == 0) {
                        throw new UnknownHostException();
                    }
                }
                catch (IOException IOE) {
                    this.reportError(msg, IOE, 4);
                }
            }
        }
        catch (MessagingException ME) {
            this.reportError(msg, ME, 4);
        }
        catch (RuntimeException RE) {
            this.reportError(msg, RE, 4);
        }
    }
    
    private Session fixUpSession() {
        assert Thread.holdsLock(this);
        final String p = this.getClass().getName();
        final LogManagerProperties proxy = new LogManagerProperties(this.mailProps, p);
        return this.session = Session.getInstance(proxy, this.auth);
    }
    
    private void envelopeFor(final MessageContext ctx, final boolean priority) {
        final Message msg = ctx.getMessage();
        final Properties proxyProps = ctx.getSession().getProperties();
        this.setFrom(msg, proxyProps);
        this.setRecipient(msg, proxyProps, "mail.to", Message.RecipientType.TO);
        this.setRecipient(msg, proxyProps, "mail.cc", Message.RecipientType.CC);
        this.setRecipient(msg, proxyProps, "mail.bcc", Message.RecipientType.BCC);
        this.setReplyTo(msg, proxyProps);
        this.setSender(msg, proxyProps);
        this.setMailer(msg);
        this.setPriority(msg, priority);
        try {
            msg.setSentDate(new Date());
            msg.saveChanges();
        }
        catch (MessagingException ME) {
            this.reportError(ME.getMessage(), ME, 5);
        }
    }
    
    private MimeBodyPart createBodyPart() throws MessagingException {
        final MimeBodyPart part = new MimeBodyPart();
        part.setDisposition("inline");
        part.setDescription(this.descriptionFrom(this.getFormatter(), this.getFilter()));
        return part;
    }
    
    private MimeBodyPart createBodyPart(final int index) throws MessagingException {
        assert Thread.holdsLock(this);
        final MimeBodyPart part = new MimeBodyPart();
        part.setDisposition("attachment");
        part.setDescription(this.descriptionFrom(this.attachmentFormatters[index], this.attachmentFilters[index]));
        return part;
    }
    
    private String descriptionFrom(final Formatter formatter, final Filter filter) {
        return "Formatted using " + formatter.getClass().getName() + " and filtered with " + ((filter == null) ? "no filter" : filter.getClass().getName()) + '.';
    }
    
    private String toString(final Formatter f) {
        final String name = f.toString();
        if (name != null && name.length() > 0) {
            return name;
        }
        return f.getClass().getName();
    }
    
    private void appendFileName(final Part part, final String chunk) {
        if (chunk != null) {
            if (chunk.length() > 0) {
                this.appendFileName0(part, chunk);
            }
        }
        else {
            this.reportNullError(5);
        }
    }
    
    private void appendFileName0(final Part part, final String chunk) {
        try {
            final String old = part.getFileName();
            part.setFileName((old != null) ? old.concat(chunk) : chunk);
        }
        catch (MessagingException ME) {
            this.reportError(ME.getMessage(), ME, 5);
        }
    }
    
    private void appendSubject(final Message msg, final String chunk) {
        if (chunk != null) {
            if (chunk.length() > 0) {
                this.appendSubject0(msg, chunk);
            }
        }
        else {
            this.reportNullError(5);
        }
    }
    
    private void appendSubject0(final Message msg, final String chunk) {
        try {
            final String old = msg.getSubject();
            assert msg instanceof MimeMessage;
            ((MimeMessage)msg).setSubject((old != null) ? old.concat(chunk) : chunk, this.getEncoding());
        }
        catch (MessagingException ME) {
            this.reportError(ME.getMessage(), ME, 5);
        }
    }
    
    private void reportNullError(final int code) {
        this.reportError("null", new NullPointerException(), code);
    }
    
    private String head(final Formatter f) {
        try {
            return f.getHead(this);
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 5);
            return "";
        }
    }
    
    private String format(final Formatter f, final LogRecord r) {
        try {
            return f.format(r);
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 5);
            return "";
        }
    }
    
    private String tail(final Formatter f, final String def) {
        try {
            return f.getTail(this);
        }
        catch (RuntimeException RE) {
            this.reportError(RE.getMessage(), RE, 5);
            return def;
        }
    }
    
    private void setMailer(final Message msg) {
        try {
            final Class mail = (MailHandler.class$com$sun$mail$util$logging$MailHandler == null) ? (MailHandler.class$com$sun$mail$util$logging$MailHandler = class$("com.sun.mail.util.logging.MailHandler")) : MailHandler.class$com$sun$mail$util$logging$MailHandler;
            final Class k = this.getClass();
            String value;
            if (k == mail) {
                value = mail.getName();
            }
            else {
                value = mail.getName() + " using the " + k.getName() + " extension.";
            }
            msg.setHeader("X-Mailer", value);
        }
        catch (MessagingException ME) {
            this.reportError(ME.getMessage(), ME, 5);
        }
    }
    
    private void setPriority(final Message msg, final boolean priority) {
        if (priority) {
            try {
                msg.setHeader("Importance", "High");
                msg.setHeader("Priority", "urgent");
                msg.setHeader("X-Priority", "2");
            }
            catch (MessagingException ME) {
                this.reportError(ME.getMessage(), ME, 5);
            }
        }
    }
    
    private void setFrom(final Message msg, final Properties props) {
        final String from = props.getProperty("mail.from");
        if (from != null && from.length() > 0) {
            try {
                final InternetAddress[] address = InternetAddress.parse(from, false);
                if (address == null || address.length == 0) {
                    this.setDefaultFrom(msg);
                }
                else if (address.length == 1) {
                    msg.setFrom(address[0]);
                }
                else {
                    msg.addFrom(address);
                }
            }
            catch (MessagingException ME) {
                this.reportError(ME.getMessage(), ME, 5);
                this.setDefaultFrom(msg);
            }
        }
        else {
            this.setDefaultFrom(msg);
        }
    }
    
    private void setDefaultFrom(final Message msg) {
        try {
            msg.setFrom();
        }
        catch (MessagingException ME) {
            this.reportError(ME.getMessage(), ME, 5);
        }
    }
    
    private void setReplyTo(final Message msg, final Properties props) {
        final String reply = props.getProperty("mail.reply.to");
        if (reply != null && reply.length() > 0) {
            try {
                final InternetAddress[] address = InternetAddress.parse(reply, false);
                if (address != null && address.length > 0) {
                    msg.setReplyTo(address);
                }
            }
            catch (MessagingException ME) {
                this.reportError(ME.getMessage(), ME, 5);
            }
        }
    }
    
    private void setSender(final Message msg, final Properties props) {
        assert msg instanceof MimeMessage : msg;
        final String sender = props.getProperty("mail.sender");
        if (sender != null && sender.length() > 0) {
            try {
                final InternetAddress[] address = InternetAddress.parse(sender, false);
                if (address != null && address.length > 0) {
                    ((MimeMessage)msg).setSender(address[0]);
                    if (address.length > 1) {
                        this.reportError("Ignoring other senders.", tooManyAddresses(address, 1), 5);
                    }
                }
            }
            catch (MessagingException ME) {
                this.reportError(ME.getMessage(), ME, 5);
            }
        }
    }
    
    private static AddressException tooManyAddresses(final Address[] address, final int offset) {
        final String msg = Arrays.asList(address).subList(offset, address.length).toString();
        return new AddressException(msg);
    }
    
    private void setRecipient(final Message msg, final Properties props, final String key, final Message.RecipientType type) {
        final String value = props.getProperty(key);
        if (value != null && value.length() > 0) {
            try {
                final InternetAddress[] address = InternetAddress.parse(value, false);
                if (address != null && address.length > 0) {
                    msg.setRecipients(type, address);
                }
            }
            catch (MessagingException ME) {
                this.reportError(ME.getMessage(), ME, 5);
            }
        }
    }
    
    private String toRawString(final Message msg) throws MessagingException, IOException {
        if (msg != null) {
            final int nbytes = Math.max(msg.getSize() + 1024, 1024);
            final ByteArrayOutputStream out = new ByteArrayOutputStream(nbytes);
            msg.writeTo(out);
            return out.toString("US-ASCII");
        }
        return null;
    }
    
    private String toMsgString(final Throwable t) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        final PrintStream ps = new PrintStream(out);
        ps.println(t.getMessage());
        t.printStackTrace(ps);
        ps.flush();
        return out.toString();
    }
    
    private static RuntimeException attachmentMismatch(final String msg) {
        return new IndexOutOfBoundsException(msg);
    }
    
    private static RuntimeException attachmentMismatch(final int expected, final int found) {
        return attachmentMismatch("Attachments mismatched, expected " + expected + " but given " + found + '.');
    }
    
    private static String atIndexMsg(final int i) {
        return "At index: " + i + '.';
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        $assertionsDisabled = !((MailHandler.class$com$sun$mail$util$logging$MailHandler == null) ? (MailHandler.class$com$sun$mail$util$logging$MailHandler = class$("com.sun.mail.util.logging.MailHandler")) : MailHandler.class$com$sun$mail$util$logging$MailHandler).desiredAssertionStatus();
        offValue = Level.OFF.intValue();
    }
    
    private static final class TailNameFormatter extends Formatter
    {
        private final String name;
        
        TailNameFormatter(final String name) {
            assert name != null;
            this.name = name;
        }
        
        public final String format(final LogRecord record) {
            return "";
        }
        
        public final String getTail(final Handler h) {
            return this.name;
        }
        
        public final boolean equals(final Object o) {
            return o instanceof TailNameFormatter && this.name.equals(((TailNameFormatter)o).name);
        }
        
        public final int hashCode() {
            return this.getClass().hashCode() + this.name.hashCode();
        }
        
        public final String toString() {
            return this.name;
        }
        
        static {
            $assertionsDisabled = !((MailHandler.class$com$sun$mail$util$logging$MailHandler == null) ? (MailHandler.class$com$sun$mail$util$logging$MailHandler = MailHandler.class$("com.sun.mail.util.logging.MailHandler")) : MailHandler.class$com$sun$mail$util$logging$MailHandler).desiredAssertionStatus();
        }
    }
}
