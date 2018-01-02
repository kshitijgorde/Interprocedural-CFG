// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Observer;
import anon.crypto.MyRandom;
import anon.util.Util;
import java.lang.reflect.Constructor;
import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Vector;
import java.util.Hashtable;
import anon.util.IXMLEncodable;
import java.util.Observable;

public final class Database extends Observable implements IXMLEncodable
{
    private static String XML_ALL_DB_NAME;
    private static Hashtable ms_databases;
    private static IDistributor ms_distributor;
    private static boolean ms_bShutdown;
    private Class m_DatabaseEntryClass;
    private Thread m_dbThread;
    private final Object SYNC_THREAD;
    private Hashtable m_serviceDatabase;
    private Vector m_timeoutList;
    private volatile boolean m_bStopThread;
    private static final Object SYNC_EXTERNAL_DATABASE;
    private static String ms_dbURL;
    private static boolean ms_bIsLoading;
    static /* synthetic */ Class class$anon$infoservice$Database;
    static /* synthetic */ Class class$anon$infoservice$AbstractDatabaseEntry;
    static /* synthetic */ Class class$org$w3c$dom$Element;
    static /* synthetic */ Class class$anon$util$IXMLEncodable;
    static /* synthetic */ Class class$anon$infoservice$Database$IWebInfo;
    
    public static void registerDistributor(final IDistributor ms_distributor) {
        Database.ms_distributor = ms_distributor;
    }
    
    public static boolean registerExternalDatabase(final String s, final String s2) {
        synchronized (Database.SYNC_EXTERNAL_DATABASE) {
            try {
                return testDB(s, s2);
            }
            catch (Exception ex) {
                return false;
            }
        }
    }
    
    private static boolean testDB(final String s, final String ms_dbURL) throws Exception {
        Database.ms_dbURL = ms_dbURL;
        Class.forName(s);
        return true;
    }
    
    private static Database registerInstance(final Database database) {
        Database database2 = Database.ms_databases.get(database.getEntryClass());
        if (database2 == null && database != null) {
            Database.ms_databases.put(database.getEntryClass(), database);
            database2 = database;
        }
        return database2;
    }
    
    private static Database unregisterInstance(final Class clazz) {
        return Database.ms_databases.remove(clazz);
    }
    
    private static void unregisterInstances() {
        Database.ms_databases.clear();
    }
    
    public static Database getInstance(final Class clazz) throws IllegalArgumentException {
        Database database = null;
        final Class clazz2 = (Database.class$anon$infoservice$Database == null) ? (Database.class$anon$infoservice$Database = class$("anon.infoservice.Database")) : Database.class$anon$infoservice$Database;
        synchronized (clazz2) {
            database = Database.ms_databases.get(clazz);
            if (database == null) {
                database = new Database(clazz);
                if (!Database.ms_bShutdown) {
                    Database.ms_databases.put(clazz, database);
                }
            }
        }
        return database;
    }
    
    public static void restoreFromXML(final Document document, final Class[] array) {
        if (document == null || array == null) {
            return;
        }
        final Element documentElement = document.getDocumentElement();
        if (documentElement == null) {
            return;
        }
        if (!documentElement.getNodeName().equals(Database.XML_ALL_DB_NAME)) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final Database instance = getInstance(array[i]);
            if (instance != null) {
                instance.loadFromXml(documentElement);
            }
        }
    }
    
    public static Document dumpToXML(final Class[] array) {
        if (array == null) {
            return null;
        }
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement(Database.XML_ALL_DB_NAME);
        final Class clazz = (Database.class$anon$infoservice$Database == null) ? (Database.class$anon$infoservice$Database = class$("anon.infoservice.Database")) : Database.class$anon$infoservice$Database;
        synchronized (clazz) {
            for (int i = 0; i < array.length; ++i) {
                final Element xmlElement = getInstance(array[i]).toXmlElement(document);
                if (xmlElement != null) {
                    element.appendChild(xmlElement);
                }
            }
        }
        document.appendChild(element);
        return document;
    }
    
    public static void shutdownDatabases() {
        final Class clazz = (Database.class$anon$infoservice$Database == null) ? (Database.class$anon$infoservice$Database = class$("anon.infoservice.Database")) : Database.class$anon$infoservice$Database;
        synchronized (clazz) {
            Database.ms_bShutdown = true;
            final Enumeration<Database> elements = (Enumeration<Database>)Database.ms_databases.elements();
            while (elements.hasMoreElements()) {
                final Database database = elements.nextElement();
                synchronized (database.SYNC_THREAD) {
                    database.stopThread();
                }
            }
            Database.ms_databases.clear();
        }
    }
    
    private Database(final Class databaseEntryClass) throws IllegalArgumentException {
        this.SYNC_THREAD = new Object();
        this.m_bStopThread = false;
        if (databaseEntryClass == null) {
            throw new NullPointerException("Invalid database class!");
        }
        if (!((Database.class$anon$infoservice$AbstractDatabaseEntry == null) ? (Database.class$anon$infoservice$AbstractDatabaseEntry = class$("anon.infoservice.AbstractDatabaseEntry")) : Database.class$anon$infoservice$AbstractDatabaseEntry).isAssignableFrom(databaseEntryClass)) {
            throw new IllegalArgumentException("There is no Database that can store entries of type " + databaseEntryClass.getName() + "!");
        }
        this.m_DatabaseEntryClass = databaseEntryClass;
        this.m_serviceDatabase = new Hashtable();
        this.m_timeoutList = new Vector();
    }
    
    private void startThread() {
        synchronized (this.SYNC_THREAD) {
            if (Database.ms_bShutdown || (!this.m_bStopThread && this.m_dbThread != null && this.m_dbThread.isAlive())) {
                return;
            }
            while (this.m_dbThread != null && this.m_bStopThread && this.m_dbThread.isAlive()) {
                LogHolder.log(3, LogType.DB, "Shutting down old database thread before starting new one (" + this.m_DatabaseEntryClass.toString() + ")");
                this.m_dbThread.interrupt();
                Thread.yield();
            }
            this.m_bStopThread = false;
            (this.m_dbThread = new Thread(new TimeoutThread(), "Database Thread: " + this.m_DatabaseEntryClass.toString())).setDaemon(true);
            this.m_dbThread.start();
        }
    }
    
    private void stopThread() {
        synchronized (this.SYNC_THREAD) {
            this.m_bStopThread = true;
            while (this.m_dbThread != null && this.m_dbThread.isAlive()) {
                LogHolder.log(6, LogType.DB, "Shutting down db thread for class: " + this.m_DatabaseEntryClass.toString());
                this.m_dbThread.interrupt();
                synchronized (this.m_serviceDatabase) {
                    this.m_serviceDatabase.notify();
                }
                Thread.yield();
            }
        }
    }
    
    public boolean update(final AbstractDatabaseEntry abstractDatabaseEntry) throws IllegalArgumentException {
        return this.update(abstractDatabaseEntry, true);
    }
    
    public boolean update(final AbstractDatabaseEntry abstractDatabaseEntry, final boolean b) throws IllegalArgumentException {
        if (abstractDatabaseEntry == null) {
            return false;
        }
        if (!this.m_DatabaseEntryClass.isAssignableFrom(abstractDatabaseEntry.getClass())) {
            throw new IllegalArgumentException("Database cannot store entries of type " + abstractDatabaseEntry.getClass().getName() + "!");
        }
        boolean newerThan = false;
        AbstractDatabaseEntry abstractDatabaseEntry2 = null;
        boolean b2 = false;
        boolean b3 = false;
        synchronized (this.SYNC_THREAD) {
            synchronized (this.m_serviceDatabase) {
                abstractDatabaseEntry2 = this.m_serviceDatabase.get(abstractDatabaseEntry.getId());
                newerThan = abstractDatabaseEntry.isNewerThan(abstractDatabaseEntry2);
                if (newerThan) {
                    if (abstractDatabaseEntry.getExpireTime() <= System.currentTimeMillis()) {
                        if (abstractDatabaseEntry.isPersistanceDeletionAllowed()) {
                            abstractDatabaseEntry.deletePersistence();
                        }
                        LogHolder.log(6, LogType.NET, "Received an expired db entry: '" + abstractDatabaseEntry.getId() + "' (" + this.m_DatabaseEntryClass.toString() + "). It was dropped immediatly.");
                        final AbstractDatabaseEntry abstractDatabaseEntry3 = this.m_serviceDatabase.remove(abstractDatabaseEntry.getId());
                        if (abstractDatabaseEntry3 != null) {
                            if (abstractDatabaseEntry3.isPersistanceDeletionAllowed()) {
                                this.removeExternal(abstractDatabaseEntry3);
                            }
                            this.setChanged();
                            this.notifyObservers(new DatabaseMessage(3, abstractDatabaseEntry3));
                            return true;
                        }
                        return false;
                    }
                    else {
                        while (this.m_timeoutList.removeElement(abstractDatabaseEntry.getId())) {}
                        if (abstractDatabaseEntry.isPersistanceDeletionAllowed()) {
                            this.addExternal(abstractDatabaseEntry);
                            abstractDatabaseEntry.deletePersistence();
                        }
                        this.m_serviceDatabase.put(abstractDatabaseEntry.getId(), abstractDatabaseEntry);
                        boolean b4 = false;
                        int n = 0;
                        while (!b4) {
                            if (n < this.m_timeoutList.size()) {
                                if (((AbstractDatabaseEntry)this.m_serviceDatabase.get(this.m_timeoutList.elementAt(n))).getExpireTime() >= abstractDatabaseEntry.getExpireTime()) {
                                    this.m_timeoutList.insertElementAt(abstractDatabaseEntry.getId(), n);
                                    b4 = true;
                                }
                            }
                            else {
                                this.m_timeoutList.addElement(abstractDatabaseEntry.getId());
                                b4 = true;
                            }
                            ++n;
                        }
                        if (n == 1) {
                            if (abstractDatabaseEntry.getExpireTime() == Long.MAX_VALUE) {
                                b2 = true;
                            }
                            else {
                                b3 = true;
                                this.m_serviceDatabase.notify();
                            }
                        }
                        LogHolder.log(7, LogType.MISC, "Added / updated entry '" + abstractDatabaseEntry.getId() + "' in the " + this.m_DatabaseEntryClass.getName() + " database. Now there are " + Integer.toString(this.m_serviceDatabase.size()) + " entries stored in this database. The new entry has position " + Integer.toString(n) + "/" + Integer.toString(this.m_timeoutList.size()) + " in the database-timeout list.");
                        if (abstractDatabaseEntry instanceof IDistributable && b) {
                            if (Database.ms_distributor != null) {
                                Database.ms_distributor.addJob((IDistributable)abstractDatabaseEntry);
                            }
                            else {
                                LogHolder.log(4, LogType.MISC, "No distributor specified - cannot distribute database entries!");
                            }
                        }
                    }
                }
                else if (abstractDatabaseEntry.isPersistanceDeletionAllowed()) {
                    abstractDatabaseEntry.deletePersistence();
                }
            }
            if (b2) {
                this.stopThread();
            }
            else if (b3) {
                this.startThread();
            }
        }
        if (newerThan) {
            this.setChanged();
            if (abstractDatabaseEntry2 == null) {
                this.notifyObservers(new DatabaseMessage(1, abstractDatabaseEntry));
            }
            else {
                this.notifyObservers(new DatabaseMessage(2, abstractDatabaseEntry));
            }
            return true;
        }
        return false;
    }
    
    public Class getEntryClass() {
        return this.m_DatabaseEntryClass;
    }
    
    public boolean remove(final String s) {
        if (s != null) {
            boolean b = false;
            boolean b2 = false;
            final AbstractDatabaseEntry abstractDatabaseEntry;
            synchronized (this.SYNC_THREAD) {
                synchronized (this.m_serviceDatabase) {
                    abstractDatabaseEntry = this.m_serviceDatabase.remove(s);
                    if (abstractDatabaseEntry != null) {
                        if (abstractDatabaseEntry.isPersistanceDeletionAllowed()) {
                            this.removeExternal(abstractDatabaseEntry);
                        }
                        this.m_timeoutList.removeElement(s);
                        if (this.m_timeoutList.size() > 0 && this.m_serviceDatabase.get(this.m_timeoutList.elementAt(0)).getExpireTime() == Long.MAX_VALUE) {
                            b = true;
                        }
                        else {
                            b2 = true;
                        }
                    }
                }
                if (b2) {
                    this.startThread();
                }
                else if (b) {
                    this.stopThread();
                }
            }
            if (abstractDatabaseEntry != null) {
                this.setChanged();
                this.notifyObservers(new DatabaseMessage(3, abstractDatabaseEntry));
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(final AbstractDatabaseEntry abstractDatabaseEntry) {
        return abstractDatabaseEntry != null && this.m_DatabaseEntryClass.isAssignableFrom(abstractDatabaseEntry.getClass()) && this.remove(abstractDatabaseEntry.getId());
    }
    
    public void removeAll() {
        synchronized (this.SYNC_THREAD) {
            synchronized (this.m_serviceDatabase) {
                this.m_serviceDatabase.clear();
                this.m_timeoutList.removeAllElements();
            }
        }
        this.setChanged();
        this.notifyObservers(new DatabaseMessage(4));
    }
    
    public int loadFromXml(final Element element) {
        return this.loadFromXml(element, false);
    }
    
    public int loadFromXml(final Element element, final boolean b) {
        int n = 0;
        final String xmlElementName = XMLUtil.getXmlElementName(this.m_DatabaseEntryClass);
        if (element == null || xmlElementName == null) {
            return n;
        }
        final NodeList elementsByTagName = element.getElementsByTagName(xmlElementName);
        Constructor<AbstractDatabaseEntry> constructor = null;
        try {
            constructor = this.m_DatabaseEntryClass.getConstructor((Database.class$org$w3c$dom$Element == null) ? (Database.class$org$w3c$dom$Element = class$("org.w3c.dom.Element")) : Database.class$org$w3c$dom$Element, Long.TYPE);
        }
        catch (Exception ex2) {
            LogHolder.log(5, LogType.DB, "No timeout constructor for " + this.m_DatabaseEntryClass + " available.");
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            try {
                AbstractDatabaseEntry abstractDatabaseEntry;
                if (constructor == null) {
                    abstractDatabaseEntry = this.m_DatabaseEntryClass.getConstructor((Database.class$org$w3c$dom$Element == null) ? (Database.class$org$w3c$dom$Element = class$("org.w3c.dom.Element")) : Database.class$org$w3c$dom$Element).newInstance(elementsByTagName.item(i));
                }
                else {
                    abstractDatabaseEntry = constructor.newInstance(elementsByTagName.item(i), new Long(Long.MAX_VALUE));
                }
                if (b && abstractDatabaseEntry instanceof ICertifiedDatabaseEntry && (!((ICertifiedDatabaseEntry)abstractDatabaseEntry).isVerified() || !((ICertifiedDatabaseEntry)abstractDatabaseEntry).isValid())) {
                    LogHolder.log(4, LogType.MISC, "XML entry " + elementsByTagName.item(i).getNodeName() + " for ID " + abstractDatabaseEntry.getId() + " could not be verified while being loaded!");
                }
                else {
                    this.update(abstractDatabaseEntry);
                    ++n;
                }
            }
            catch (Exception ex) {
                LogHolder.log(4, LogType.MISC, "Could not load db entry from XML!", ex);
            }
        }
        return n;
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElement(document, XMLUtil.getXmlElementContainerName(this.m_DatabaseEntryClass));
    }
    
    public Element toXmlElement(final Document document, final String s) {
        if (document == null || !((Database.class$anon$util$IXMLEncodable == null) ? (Database.class$anon$util$IXMLEncodable = class$("anon.util.IXMLEncodable")) : Database.class$anon$util$IXMLEncodable).isAssignableFrom(this.m_DatabaseEntryClass) || s == null || s.trim().length() == 0) {
            return null;
        }
        final Element element = document.createElement(s);
        synchronized (this.m_serviceDatabase) {
            final Enumeration<IXMLEncodable> elements = this.m_serviceDatabase.elements();
            while (elements.hasMoreElements()) {
                element.appendChild(elements.nextElement().toXmlElement(document));
            }
        }
        return element;
    }
    
    public Hashtable getEntryHash() {
        return (Hashtable)this.m_serviceDatabase.clone();
    }
    
    public Vector getEntryList() {
        final Vector<Object> vector = new Vector<Object>();
        synchronized (this.m_serviceDatabase) {
            final Enumeration<Object> elements = this.m_serviceDatabase.elements();
            while (elements.hasMoreElements()) {
                vector.addElement(elements.nextElement());
            }
        }
        return vector;
    }
    
    public Vector getSortedEntryList(final Util.Comparable comparable) {
        final Vector entryList = this.getEntryList();
        Util.sort(entryList, comparable);
        return entryList;
    }
    
    public Enumeration getEntrySnapshotAsEnumeration() {
        synchronized (this.m_serviceDatabase) {
            return this.getEntryList().elements();
        }
    }
    
    public int getNumberOfEntries() {
        return this.m_serviceDatabase.size();
    }
    
    public AbstractDatabaseEntry getEntryById(final String s) {
        if (s == null) {
            return null;
        }
        AbstractDatabaseEntry abstractDatabaseEntry = null;
        synchronized (this.m_serviceDatabase) {
            abstractDatabaseEntry = this.m_serviceDatabase.get(s);
        }
        return abstractDatabaseEntry;
    }
    
    public AbstractDatabaseEntry getRandomEntry() {
        AbstractDatabaseEntry abstractDatabaseEntry = null;
        synchronized (this.m_serviceDatabase) {
            if (this.m_timeoutList.size() > 0) {
                try {
                    abstractDatabaseEntry = this.m_serviceDatabase.get(this.m_timeoutList.elementAt(new MyRandom().nextInt(this.m_timeoutList.size())));
                }
                catch (Exception ex) {}
            }
        }
        return abstractDatabaseEntry;
    }
    
    public void addObserver(final Observer observer) {
        synchronized (this.m_serviceDatabase) {
            super.addObserver(observer);
            observer.update(this, new DatabaseMessage(5, this.getEntryList()));
        }
    }
    
    public boolean isEntryIdInTimeoutList(final String s) {
        return this.m_timeoutList.contains(s);
    }
    
    public int getTimeoutListSize() {
        return this.m_timeoutList.size();
    }
    
    public Document getWebInfos(final String s) {
        return getWebInfos(this.getEntryClass(), s);
    }
    
    public Document getWebInfos() {
        return getWebInfos(this.getEntryClass());
    }
    
    private static Document getWebInfos(final Class clazz, final String s) {
        if (!((Database.class$anon$infoservice$Database$IWebInfo == null) ? (Database.class$anon$infoservice$Database$IWebInfo = class$("anon.infoservice.Database$IWebInfo")) : Database.class$anon$infoservice$Database$IWebInfo).isAssignableFrom(clazz)) {
            LogHolder.log(0, LogType.DB, "Illegal class for web info: " + clazz);
            return null;
        }
        final Document document = XMLUtil.createDocument();
        final IWebInfo webInfo = (IWebInfo)getInstance(clazz).getEntryById(s);
        final Element element = (webInfo == null) ? null : webInfo.getWebInfo(document);
        if (element == null) {
            return null;
        }
        document.appendChild(element);
        return document;
    }
    
    private static Document getWebInfos(final Class clazz) {
        if (!((Database.class$anon$infoservice$Database$IWebInfo == null) ? (Database.class$anon$infoservice$Database$IWebInfo = class$("anon.infoservice.Database$IWebInfo")) : Database.class$anon$infoservice$Database$IWebInfo).isAssignableFrom(clazz)) {
            return null;
        }
        final String staticFieldValue = Util.getStaticFieldValue(clazz, "XML_ELEMENT_WEBINFO_CONTAINER");
        if (staticFieldValue == null) {
            return null;
        }
        final Document document = XMLUtil.createDocument();
        final Vector entryList = getInstance(clazz).getEntryList();
        final Element element = document.createElement(staticFieldValue);
        document.appendChild(element);
        for (int i = 0; i < entryList.size(); ++i) {
            final Element webInfo = entryList.elementAt(i).getWebInfo(document);
            if (webInfo != null) {
                element.appendChild(webInfo);
            }
        }
        return document;
    }
    
    private void addExternal(final AbstractDatabaseEntry abstractDatabaseEntry) {
        if (Database.ms_dbURL == null || !(abstractDatabaseEntry instanceof IXMLEncodable) || Database.ms_bIsLoading) {
            return;
        }
        synchronized (Database.SYNC_EXTERNAL_DATABASE) {
            try {
                this.addExternal_int(abstractDatabaseEntry);
            }
            catch (SQLException ex) {
                LogHolder.log(2, LogType.DB, ex);
            }
        }
    }
    
    private void addExternal_int(final AbstractDatabaseEntry abstractDatabaseEntry) throws SQLException {
        final Connection connection = DriverManager.getConnection(Database.ms_dbURL);
        final Statement statement = connection.createStatement();
        final String replaceAll = Util.replaceAll(abstractDatabaseEntry.getClass().getName(), ".", "__");
        statement.executeUpdate("create table if not exists " + replaceAll + " (id text, xml text);");
        statement.executeUpdate("CREATE UNIQUE INDEX if not exists indexname ON " + replaceAll + "(id);");
        statement.executeUpdate("replace into " + replaceAll + " values ('" + abstractDatabaseEntry.getId() + "', " + "'" + XMLUtil.toString(XMLUtil.toXMLDocument((IXMLEncodable)abstractDatabaseEntry)) + "');");
        connection.close();
    }
    
    private void removeExternal(final AbstractDatabaseEntry abstractDatabaseEntry) {
        if (Database.ms_dbURL == null || !(abstractDatabaseEntry instanceof IXMLEncodable) || Database.ms_bIsLoading) {
            return;
        }
        synchronized (Database.SYNC_EXTERNAL_DATABASE) {
            try {
                this.removeExternal_int(abstractDatabaseEntry);
            }
            catch (SQLException ex) {
                LogHolder.log(2, LogType.DB, ex);
            }
        }
    }
    
    private void removeExternal_int(final AbstractDatabaseEntry abstractDatabaseEntry) throws SQLException {
        final Connection connection = DriverManager.getConnection(Database.ms_dbURL);
        final Statement statement = connection.createStatement();
        final String replaceAll = Util.replaceAll(abstractDatabaseEntry.getClass().getName(), ".", "__");
        statement.executeUpdate("create table if not exists " + replaceAll + " (id text, xml text);");
        statement.executeUpdate("CREATE UNIQUE INDEX if not exists indexname ON " + replaceAll + "(id);");
        statement.executeUpdate("delete from " + replaceAll + " where id = ('" + abstractDatabaseEntry.getId() + "');");
        connection.close();
    }
    
    public static void loadFromExternalDatabase() {
        if (Database.ms_dbURL == null) {
            return;
        }
        synchronized (Database.SYNC_EXTERNAL_DATABASE) {
            LogHolder.log(5, LogType.DB, "Reading cached objects from external database...");
            Database.ms_bIsLoading = true;
            try {
                loadFromExternalDatabase_int();
                LogHolder.log(5, LogType.DB, "Cached objects were read from external database.");
            }
            catch (SQLException ex) {
                LogHolder.log(2, LogType.DB, ex);
            }
            Database.ms_bIsLoading = false;
        }
    }
    
    public static void loadFromExternalDatabase_int() throws SQLException {
        final Connection connection = DriverManager.getConnection(Database.ms_dbURL);
        final Statement statement = connection.createStatement();
        final Vector<String> vector = new Vector<String>();
        final ResultSet executeQuery = statement.executeQuery("select name from sqlite_master where type = 'table';");
        while (executeQuery.next()) {
            vector.addElement(executeQuery.getString("name"));
        }
        executeQuery.close();
        for (int i = 0; i < vector.size(); ++i) {
            final String string = vector.elementAt(i).toString();
            final String replaceAll = Util.replaceAll(string, "__", ".");
            boolean b = false;
            try {
                final Class<?> forName = Class.forName(replaceAll);
                final ResultSet executeQuery2 = statement.executeQuery("select xml from " + string + ";");
                while (executeQuery2.next()) {
                    final String string2 = executeQuery2.getString("xml");
                    Element documentElement;
                    try {
                        documentElement = XMLUtil.toXMLDocument(string2).getDocumentElement();
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.DB, "Could not load cached DB entries for class " + replaceAll + ".", ex);
                        b = true;
                        break;
                    }
                    AbstractDatabaseEntry abstractDatabaseEntry;
                    try {
                        abstractDatabaseEntry = (AbstractDatabaseEntry)forName.getConstructor((Database.class$org$w3c$dom$Element == null) ? (Database.class$org$w3c$dom$Element = class$("org.w3c.dom.Element")) : Database.class$org$w3c$dom$Element, Long.TYPE).newInstance(documentElement, new Long(Long.MAX_VALUE));
                    }
                    catch (Exception ex4) {
                        try {
                            abstractDatabaseEntry = (AbstractDatabaseEntry)forName.getConstructor((Database.class$org$w3c$dom$Element == null) ? (Database.class$org$w3c$dom$Element = class$("org.w3c.dom.Element")) : Database.class$org$w3c$dom$Element).newInstance(documentElement);
                        }
                        catch (Exception ex2) {
                            LogHolder.log(2, LogType.DB, "Could not load cached DB entries for class " + replaceAll + ".", ex2);
                            b = true;
                        }
                    }
                    LogHolder.log(6, LogType.DB, "Loading cached DB entry " + abstractDatabaseEntry.getClass().getName() + ":" + abstractDatabaseEntry.getId() + ".");
                    getInstance(forName).update(abstractDatabaseEntry);
                }
                executeQuery2.close();
            }
            catch (ClassNotFoundException ex3) {
                LogHolder.log(2, LogType.DB, "Could not load cached DB entries for class " + replaceAll + ".", ex3);
                b = true;
            }
            if (b) {
                statement.executeUpdate("drop table " + (Object)vector.elementAt(i) + ";");
            }
        }
        connection.close();
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
        Database.XML_ALL_DB_NAME = "InfoServiceDB";
        Database.ms_databases = new Hashtable();
        Database.ms_bShutdown = false;
        SYNC_EXTERNAL_DATABASE = new Object();
        Database.ms_bIsLoading = false;
    }
    
    private class TimeoutThread implements Runnable
    {
        public void run() {
            LogHolder.log(6, LogType.DB, "Starting timeout database thread for class " + Database.this.m_DatabaseEntryClass.toString() + ".");
            while (!Database.this.m_bStopThread && !Database.ms_bShutdown && !Thread.currentThread().isInterrupted()) {
                boolean b = true;
                synchronized (Database.this.m_serviceDatabase) {
                    while (!Database.this.m_bStopThread && !Database.ms_bShutdown && !Thread.currentThread().isInterrupted() && Database.this.m_timeoutList.size() > 0 && b) {
                        final AbstractDatabaseEntry abstractDatabaseEntry = Database.this.m_serviceDatabase.get(Database.this.m_timeoutList.firstElement());
                        if (System.currentTimeMillis() >= abstractDatabaseEntry.getExpireTime()) {
                            LogHolder.log(6, LogType.MISC, "DatabaseEntry (" + abstractDatabaseEntry.getClass().getName() + ")" + abstractDatabaseEntry.getId() + " has reached the expire time and is removed.");
                            final AbstractDatabaseEntry abstractDatabaseEntry2 = Database.this.m_serviceDatabase.remove(abstractDatabaseEntry.getId());
                            if (abstractDatabaseEntry2 != null && abstractDatabaseEntry2.isPersistanceDeletionAllowed()) {
                                Database.this.removeExternal(abstractDatabaseEntry2);
                            }
                            Database.this.m_timeoutList.removeElementAt(0);
                            Database.this.setChanged();
                            Database.this.notifyObservers(new DatabaseMessage(3, abstractDatabaseEntry));
                        }
                        else {
                            b = false;
                        }
                    }
                    if (Database.this.m_bStopThread || Database.ms_bShutdown || Thread.currentThread().isInterrupted()) {
                        return;
                    }
                }
                synchronized (Database.this.m_serviceDatabase) {
                    long n = 0L;
                    if (Database.this.m_timeoutList.size() > 0) {
                        n = Database.this.m_serviceDatabase.get(Database.this.m_timeoutList.firstElement()).getExpireTime() - System.currentTimeMillis();
                    }
                    if (n > 0L) {
                        try {
                            Database.this.m_serviceDatabase.wait(n);
                            LogHolder.log(7, LogType.MISC, "One entry could be expired. Wake up...");
                        }
                        catch (InterruptedException ex) {
                            if (Database.this.m_bStopThread || Database.ms_bShutdown || Thread.currentThread().isInterrupted()) {
                                return;
                            }
                        }
                    }
                    if (Database.this.m_timeoutList.size() != 0) {
                        continue;
                    }
                    try {
                        Database.this.m_serviceDatabase.wait();
                        LogHolder.log(7, LogType.MISC, "First entry in the database. Look when it expires. Wake up...");
                    }
                    catch (InterruptedException ex2) {
                        if (Database.this.m_bStopThread || Database.ms_bShutdown || Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    public interface IWebInfo
    {
        public static final String FIELD_XML_ELEMENT_WEBINFO_CONTAINER = "XML_ELEMENT_WEBINFO_CONTAINER";
        
        Element getWebInfo(final Document p0);
    }
}
