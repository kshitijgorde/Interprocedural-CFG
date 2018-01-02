// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.ClassUtil;
import java.security.SignatureException;
import anon.crypto.ExpiredSignatureException;
import java.util.Random;
import java.lang.reflect.Field;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import anon.pay.PaymentInstanceDBEntry;
import anon.util.Util;
import anon.terms.template.TermsAndConditionsTemplate;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import logging.LogHolder;
import logging.LogType;
import anon.util.ThreadPool;
import anon.util.IXMLEncodable;
import java.util.Observable;

public class InfoServiceHolder extends Observable implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "InfoserviceManagement";
    public static final String XML_ELEM_CHANGE_INFO_SERVICES = "ChangeInfoService";
    public static final int MAXIMUM_OF_ASKED_INFO_SERVICES = 10;
    public static final int DEFAULT_OF_ASKED_INFO_SERVICES = 4;
    private static final int GET_MIXCASCADES = 1;
    private static final int GET_INFOSERVICES = 2;
    private static final int GET_MIXINFO = 3;
    private static final int GET_STATUSINFO = 4;
    private static final int GET_NEWVERSIONNUMBER = 5;
    private static final int GET_JAPVERSIONINFO = 6;
    private static final int GET_TORNODESLIST = 7;
    private static final int GET_FORWARDER = 8;
    private static final int GET_PAYMENT_INSTANCES = 9;
    private static final int GET_PAYMENT_INSTANCE = 10;
    private static final int GET_MIXMINIONNODESLIST = 11;
    private static final int GET_CASCADEINFO = 12;
    private static final int GET_LATEST_JAVA = 13;
    private static final int GET_INFOSERVICE_SERIALS = 14;
    private static final int GET_MIXCASCADE_SERIALS = 15;
    private static final int GET_MESSAGES = 16;
    private static final int GET_LATEST_JAVA_SERIALS = 17;
    private static final int GET_MESSAGE_SERIALS = 18;
    private static final int GET_STATUSINFO_TIMEOUT = 19;
    private static final int GET_PERFORMANCE_INFO = 20;
    private static final int GET_TC_TEMPLATE = 21;
    private static final int GET_TCS = 22;
    private static final int GET_TC_SERIALS = 23;
    private static final int GET_EXIT_ADDRESSES = 24;
    private static final int GET_TC_TEMPLATES = 25;
    private static final int GET_MIXINFOS = 25;
    private static final String[] GETS;
    public static final boolean DEFAULT_INFOSERVICE_CHANGES = true;
    private static final String XML_ATTR_ASKED_INFO_SERVICES = "askedInfoservices";
    private static InfoServiceHolder ms_infoServiceHolderInstance;
    private ThreadPool m_poolFetchInformation;
    private InfoServiceDBEntry m_preferredInfoService;
    private boolean m_changeInfoServices;
    private int m_nrAskedInfoServices;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceHolder;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    
    private InfoServiceHolder() {
        this.m_poolFetchInformation = new ThreadPool("Fetch Information Thread Pool", 6, 1);
        this.m_nrAskedInfoServices = 4;
        this.m_preferredInfoService = null;
        this.m_changeInfoServices = true;
    }
    
    public static InfoServiceHolder getInstance() {
        final Class clazz = (InfoServiceHolder.class$anon$infoservice$InfoServiceHolder == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceHolder = class$("anon.infoservice.InfoServiceHolder")) : InfoServiceHolder.class$anon$infoservice$InfoServiceHolder;
        synchronized (clazz) {
            if (InfoServiceHolder.ms_infoServiceHolderInstance == null) {
                InfoServiceHolder.ms_infoServiceHolderInstance = new InfoServiceHolder();
            }
        }
        return InfoServiceHolder.ms_infoServiceHolderInstance;
    }
    
    public void shutdown() {
        this.m_poolFetchInformation.shutdown();
    }
    
    public static String getXmlSettingsRootNodeName() {
        return "InfoserviceManagement";
    }
    
    public synchronized void setPreferredInfoService(final InfoServiceDBEntry preferredInfoService) {
        if (preferredInfoService != null) {
            this.m_preferredInfoService = preferredInfoService;
            this.setChanged();
            this.notifyObservers(new InfoServiceHolderMessage(1, this.m_preferredInfoService));
            LogHolder.log(6, LogType.NET, "Preferred InfoService is now: " + this.m_preferredInfoService.getName());
        }
    }
    
    public InfoServiceDBEntry getPreferredInfoService() {
        return this.m_preferredInfoService;
    }
    
    public int getNumberOfAskedInfoServices() {
        return this.m_nrAskedInfoServices;
    }
    
    public void setNumberOfAskedInfoServices(int nrAskedInfoServices) {
        if (nrAskedInfoServices < 1) {
            this.m_nrAskedInfoServices = 1;
        }
        else if (nrAskedInfoServices > 10) {
            nrAskedInfoServices = 10;
        }
        else {
            this.m_nrAskedInfoServices = nrAskedInfoServices;
        }
    }
    
    public void setChangeInfoServices(final boolean changeInfoServices) {
        synchronized (this) {
            if (this.m_changeInfoServices != changeInfoServices) {
                this.m_changeInfoServices = changeInfoServices;
                this.setChanged();
                this.notifyObservers(new InfoServiceHolderMessage(2, new Boolean(this.m_changeInfoServices)));
            }
        }
    }
    
    public boolean isChangeInfoServices() {
        boolean changeInfoServices = true;
        synchronized (this) {
            changeInfoServices = this.m_changeInfoServices;
        }
        return changeInfoServices;
    }
    
    public Vector getInfoservicesWithForwarderList() {
        final Vector<InfoServiceDBEntry> vector = new Vector<InfoServiceDBEntry>();
        final InfoServiceDBEntry preferredInfoService = this.getPreferredInfoService();
        if (preferredInfoService.hasPrimaryForwarderList()) {
            vector.addElement(preferredInfoService);
        }
        final Enumeration<InfoServiceDBEntry> elements = Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).getEntryList().elements();
        while (elements.hasMoreElements()) {
            final InfoServiceDBEntry infoServiceDBEntry = elements.nextElement();
            if (infoServiceDBEntry.hasPrimaryForwarderList() && !infoServiceDBEntry.getId().equals(preferredInfoService.getId())) {
                vector.addElement(infoServiceDBEntry);
            }
        }
        return vector;
    }
    
    private Object fetchInformation(final int n, final Vector vector) {
        final InformationFetcher informationFetcher = new InformationFetcher(n, vector);
        try {
            this.m_poolFetchInformation.addRequestAndWait(informationFetcher);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            LogHolder.log(3, LogType.THREAD, ex);
        }
        return informationFetcher.getResult();
    }
    
    public Hashtable getMixCascades() {
        return (Hashtable)this.fetchInformation(1, null);
    }
    
    public Hashtable getMixCascades(final String s) {
        if (s == null) {
            return this.getMixCascades();
        }
        final Vector<String> vector = new Vector<String>();
        vector.addElement(s);
        return (Hashtable)this.fetchInformation(1, vector);
    }
    
    public Hashtable getMixCascadeSerials() {
        return (Hashtable)this.fetchInformation(15, null);
    }
    
    public Hashtable getMixCascadeSerials(final String s) {
        if (s == null) {
            return this.getMixCascadeSerials();
        }
        final Vector<String> vector = new Vector<String>();
        vector.addElement(s);
        return (Hashtable)this.fetchInformation(15, vector);
    }
    
    public TermsAndConditionsTemplate getTCTemplate(final String s) {
        return (TermsAndConditionsTemplate)this.fetchInformation(21, Util.toVector(s));
    }
    
    public Hashtable getTCTemplates() {
        return (Hashtable)this.fetchInformation(25, null);
    }
    
    public Hashtable getTermsAndConditions() {
        return (Hashtable)this.fetchInformation(22, null);
    }
    
    public Hashtable getTermsAndConditionsSerials() {
        return (Hashtable)this.fetchInformation(23, null);
    }
    
    public Hashtable getPerformanceInfos() {
        return (Hashtable)this.fetchInformation(20, null);
    }
    
    public void getExitAddresses() {
        this.fetchInformation(24, null);
    }
    
    public Hashtable getPaymentInstances() {
        return (Hashtable)this.fetchInformation(9, null);
    }
    
    public PaymentInstanceDBEntry getPaymentInstance(final String s) throws Exception {
        return (PaymentInstanceDBEntry)this.fetchInformation(10, Util.toVector(s));
    }
    
    public Hashtable getInfoServices() {
        return (Hashtable)this.fetchInformation(2, null);
    }
    
    public Hashtable getInfoServiceSerials() {
        return (Hashtable)this.fetchInformation(14, null);
    }
    
    public MixInfo getMixInfo(final String s) {
        return (MixInfo)this.fetchInformation(3, Util.toVector(s));
    }
    
    public Hashtable getMixInfos() {
        return (Hashtable)this.fetchInformation(25, null);
    }
    
    public StatusInfo getStatusInfo(final MixCascade mixCascade) {
        return (StatusInfo)this.fetchInformation(4, Util.toVector(mixCascade));
    }
    
    public StatusInfo getStatusInfo(final MixCascade mixCascade, final long n) {
        final Vector<Long> vector = new Vector<Long>();
        vector.addElement((Long)mixCascade);
        vector.addElement(new Long(n));
        return (StatusInfo)this.fetchInformation(19, vector);
    }
    
    public JAPMinVersion getNewVersionNumber() {
        return (JAPMinVersion)this.fetchInformation(5, null);
    }
    
    public Hashtable getLatestJavaVersions() {
        return (Hashtable)this.fetchInformation(13, null);
    }
    
    public Hashtable getLatestJavaVersionSerials() {
        return (Hashtable)this.fetchInformation(17, null);
    }
    
    public Hashtable getMessages() {
        return (Hashtable)this.fetchInformation(16, null);
    }
    
    public Hashtable getMessageSerials() {
        return (Hashtable)this.fetchInformation(18, null);
    }
    
    public JAPVersionInfo getJAPVersionInfo(final int n) {
        return (JAPVersionInfo)this.fetchInformation(6, Util.toVector(new Integer(n)));
    }
    
    public byte[] getTorNodesList() {
        return (byte[])this.fetchInformation(7, null);
    }
    
    public MixCascade getMixCascadeInfo(final String s) {
        return (MixCascade)this.fetchInformation(12, Util.toVector(s));
    }
    
    public byte[] getMixminionNodesList() {
        return (byte[])this.fetchInformation(11, null);
    }
    
    public Element getForwarder() {
        return (Element)this.fetchInformation(8, null);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("InfoserviceManagement");
        final Element xmlElement = Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).toXmlElement(document, "InfoServices");
        final Element element2 = document.createElement("PreferredInfoService");
        final Element element3 = document.createElement("ChangeInfoService");
        XMLUtil.setAttribute(element, "askedInfoservices", this.m_nrAskedInfoServices);
        synchronized (this) {
            final InfoServiceDBEntry preferredInfoService = this.getPreferredInfoService();
            if (preferredInfoService != null) {
                element2.appendChild(preferredInfoService.toXmlElement(document));
            }
            XMLUtil.setValue(element3, this.isChangeInfoServices());
        }
        element.appendChild(xmlElement);
        element.appendChild(element2);
        element.appendChild(element3);
        return element;
    }
    
    public void loadSettingsFromXml(final Element element, final boolean b) throws Exception {
        this.setNumberOfAskedInfoServices(XMLUtil.parseAttribute(element, "askedInfoservices", 4));
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "InfoServices");
        if (element2 == null) {
            throw new Exception("No InfoServices node found.");
        }
        Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).loadFromXml(element2, true);
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "PreferredInfoService");
        if (element3 == null) {
            throw new Exception("No PreferredInfoService node found.");
        }
        final Element element4 = (Element)XMLUtil.getFirstChildByName(element3, "InfoService");
        InfoServiceDBEntry preferredInfoService = null;
        if (element4 != null) {
            try {
                preferredInfoService = new InfoServiceDBEntry(element4, Long.MAX_VALUE);
            }
            catch (XMLParseException ex) {}
        }
        final Vector entryList = Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).getEntryList();
        final Vector vector = new Vector<String>();
        int n = 0;
        for (int i = 0; i < entryList.size(); ++i) {
            final InfoServiceDBEntry infoServiceDBEntry = entryList.elementAt(i);
            if (!infoServiceDBEntry.isBootstrap() && !infoServiceDBEntry.isUserDefined() && (!infoServiceDBEntry.isVerified() || !infoServiceDBEntry.isValid())) {
                Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).remove(infoServiceDBEntry.getId());
            }
            else if (infoServiceDBEntry.isBootstrap()) {
                vector.addElement(infoServiceDBEntry.getId());
            }
            else if (!infoServiceDBEntry.isUserDefined()) {
                ++n;
            }
        }
        if (n >= 3) {
            for (int j = 0; j < vector.size(); ++j) {
                Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).remove(vector.elementAt(j).toString());
            }
        }
        synchronized (this) {
            if (preferredInfoService != null) {
                this.setPreferredInfoService(preferredInfoService);
            }
            else if (this.getPreferredInfoService() == null) {
                this.setPreferredInfoService((InfoServiceDBEntry)Database.getInstance((InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceHolder.class$anon$infoservice$InfoServiceDBEntry).getRandomEntry());
            }
            if (b) {
                this.setChangeInfoServices(true);
            }
            else {
                this.setChangeInfoServices(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ChangeInfoService"), this.isChangeInfoServices()));
            }
        }
    }
    
    private static void filterServiceContext(final Hashtable hashtable, final String s) {
        if (s != null && hashtable != null) {
            try {
                final Enumeration<Object> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    final String context = hashtable.get(nextElement).getContext();
                    if ((context == null || !context.equals(s)) && (!context.startsWith("jondonym") || !context.equals("jondonym.premium"))) {
                        hashtable.remove(nextElement);
                    }
                }
            }
            catch (ClassCastException ex) {
                LogHolder.log(3, LogType.MISC, "Wrong type for filter specified", ex);
            }
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
        InfoServiceHolder.ms_infoServiceHolderInstance = null;
        final Field[] declaredFields = ((InfoServiceHolder.class$anon$infoservice$InfoServiceHolder == null) ? (InfoServiceHolder.class$anon$infoservice$InfoServiceHolder = class$("anon.infoservice.InfoServiceHolder")) : InfoServiceHolder.class$anon$infoservice$InfoServiceHolder).getDeclaredFields();
        GETS = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; ++i) {
            if (declaredFields[i].getName().startsWith("GET") && declaredFields[i].getType() == Integer.TYPE) {
                try {
                    InfoServiceHolder.GETS[declaredFields[i].getInt(null)] = declaredFields[i].getName();
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.DB, ex);
                    break;
                }
            }
        }
    }
    
    private class InformationFetcher implements Runnable
    {
        private int functionNumber;
        private Vector arguments;
        private Object m_result;
        static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
        
        public InformationFetcher(final int functionNumber, final Vector arguments) {
            this.functionNumber = functionNumber;
            this.arguments = arguments;
        }
        
        public Object getResult() {
            return this.m_result;
        }
        
        public void run() {
            final Random random = new Random(System.currentTimeMillis());
            int access$100 = 1;
            InfoServiceDBEntry preferredInfoService = InfoServiceHolder.this.getPreferredInfoService();
            Exception ex = null;
            Vector<InfoServiceDBEntry> entryList;
            if (InfoServiceHolder.this.m_changeInfoServices) {
                entryList = (Vector<InfoServiceDBEntry>)Database.getInstance((InformationFetcher.class$anon$infoservice$InfoServiceDBEntry == null) ? (InformationFetcher.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InformationFetcher.class$anon$infoservice$InfoServiceDBEntry).getEntryList();
                final Vector<InfoServiceDBEntry> vector = (Vector<InfoServiceDBEntry>)entryList.clone();
                for (int i = 0; i < vector.size(); ++i) {
                    final InfoServiceDBEntry infoServiceDBEntry = vector.elementAt(i);
                    boolean b = false;
                    if ((!infoServiceDBEntry.isBootstrap() && !infoServiceDBEntry.isUserDefined()) || infoServiceDBEntry.getCertPath() != null) {
                        if (infoServiceDBEntry.getCertPath() == null || !infoServiceDBEntry.getCertPath().isVerified() || (b = !infoServiceDBEntry.isValid())) {
                            if (b) {
                                if (ex == null || ex instanceof ExpiredSignatureException) {
                                    ex = new ExpiredSignatureException("Signature expired for IS " + infoServiceDBEntry.getId() + ".");
                                }
                            }
                            else {
                                ex = new SignatureException("No valid signature for IS " + infoServiceDBEntry.getId() + ".");
                            }
                            entryList.removeElement(infoServiceDBEntry);
                        }
                    }
                }
            }
            else {
                entryList = new Vector<InfoServiceDBEntry>();
                if (preferredInfoService != null) {
                    entryList.addElement(preferredInfoService);
                }
            }
            Object result = null;
            if (this.functionNumber == 2 || this.functionNumber == 1 || this.functionNumber == 25 || this.functionNumber == 14 || this.functionNumber == 15 || this.functionNumber == 12 || this.functionNumber == 17 || this.functionNumber == 13 || this.functionNumber == 16 || this.functionNumber == 18 || this.functionNumber == 9 || this.functionNumber == 20 || this.functionNumber == 22 || this.functionNumber == 23 || this.functionNumber == 24) {
                result = new Hashtable<String, AbstractDatabaseEntry>();
                if (10 == InfoServiceHolder.this.m_nrAskedInfoServices) {
                    access$100 = entryList.size() + 1;
                }
                else {
                    access$100 = InfoServiceHolder.this.m_nrAskedInfoServices;
                }
            }
            if (this.functionNumber == 4 || this.functionNumber == 19) {
                preferredInfoService = null;
            }
            while ((entryList.size() > 0 || preferredInfoService != null) && !Thread.currentThread().isInterrupted()) {
                if (preferredInfoService == null) {
                    preferredInfoService = entryList.elementAt(Math.abs(random.nextInt()) % entryList.size());
                }
                LogHolder.log(5, LogType.NET, "Trying InfoService: " + preferredInfoService.getName(), true);
                try {
                    Hashtable<String, PerformanceInfo> hashtable = null;
                    if (this.functionNumber == 1) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getMixCascades();
                        if (this.arguments != null) {
                            filterServiceContext(hashtable, this.arguments.firstElement());
                        }
                    }
                    else if (this.functionNumber == 2) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getInfoServices();
                    }
                    else if (this.functionNumber == 25) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getMixes(true);
                    }
                    else if (this.functionNumber == 3) {
                        result = preferredInfoService.getMixInfo(this.arguments.elementAt(0));
                    }
                    else if (this.functionNumber == 13) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getLatestJava();
                    }
                    else if (this.functionNumber == 17) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getLatestJavaSerials();
                    }
                    else if (this.functionNumber == 21) {
                        result = preferredInfoService.getTCTemplate(this.arguments.elementAt(0));
                    }
                    else if (this.functionNumber == 20) {
                        final PerformanceInfo performanceInfo = preferredInfoService.getPerformanceInfo();
                        hashtable = new Hashtable<String, PerformanceInfo>();
                        if (performanceInfo != null) {
                            hashtable.put(performanceInfo.getId(), performanceInfo);
                        }
                    }
                    else if (this.functionNumber == 16) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getMessages();
                    }
                    else if (this.functionNumber == 18) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getMessageSerials();
                    }
                    else if (this.functionNumber == 4) {
                        result = preferredInfoService.getStatusInfo(this.arguments.elementAt(0));
                    }
                    else if (this.functionNumber == 19) {
                        result = preferredInfoService.getStatusInfo(this.arguments.elementAt(0), this.arguments.elementAt(1));
                    }
                    else if (this.functionNumber == 15) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getMixCascadeSerials();
                        if (this.arguments != null) {
                            filterServiceContext(hashtable, this.arguments.firstElement());
                        }
                    }
                    else if (this.functionNumber == 14) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getInfoServiceSerials();
                    }
                    else if (this.functionNumber == 5) {
                        result = preferredInfoService.getNewVersionNumber();
                    }
                    else if (this.functionNumber == 6) {
                        result = preferredInfoService.getJAPVersionInfo(this.arguments.elementAt(0));
                    }
                    else if (this.functionNumber == 7) {
                        result = preferredInfoService.getTorNodesList();
                    }
                    else if (this.functionNumber == 11) {
                        result = preferredInfoService.getMixminionNodesList();
                    }
                    else if (this.functionNumber == 8) {
                        result = preferredInfoService.getForwarder();
                    }
                    else if (this.functionNumber == 9) {
                        hashtable = (Hashtable<String, PerformanceInfo>)preferredInfoService.getPaymentInstances();
                    }
                    else if (this.functionNumber == 10) {
                        result = preferredInfoService.getPaymentInstance(this.arguments.firstElement());
                    }
                    else if (this.functionNumber == 24) {
                        result = preferredInfoService.getExitAddresses();
                    }
                    else if (this.functionNumber == 12) {
                        final MixCascade mixCascadeInfo = preferredInfoService.getMixCascadeInfo(this.arguments.firstElement());
                        hashtable = new Hashtable<String, PerformanceInfo>();
                        if (mixCascadeInfo != null) {
                            hashtable.put(mixCascadeInfo.getId(), (PerformanceInfo)mixCascadeInfo);
                        }
                    }
                    if ((hashtable == null && result == null) || (hashtable != null && hashtable.size() == 0)) {
                        LogHolder.log(6, LogType.NET, "IS " + preferredInfoService.getName() + " did not have the requested info!");
                        entryList.removeElement(preferredInfoService);
                        preferredInfoService = null;
                    }
                    else {
                        if (hashtable == null) {
                            break;
                        }
                        final Enumeration<PerformanceInfo> elements = hashtable.elements();
                        while (elements.hasMoreElements()) {
                            AbstractDatabaseEntry abstractDatabaseEntry = elements.nextElement();
                            if (((Hashtable<String, AbstractDistributableDatabaseEntry.SerialDBEntry>)result).containsKey(abstractDatabaseEntry.getId())) {
                                final AbstractDatabaseEntry abstractDatabaseEntry2 = ((Hashtable<K, AbstractDatabaseEntry>)result).get(abstractDatabaseEntry.getId());
                                if (abstractDatabaseEntry instanceof AbstractDistributableDatabaseEntry.SerialDBEntry && abstractDatabaseEntry2 instanceof AbstractDistributableDatabaseEntry.SerialDBEntry) {
                                    AbstractDistributableDatabaseEntry.SerialDBEntry serialDBEntry = (AbstractDistributableDatabaseEntry.SerialDBEntry)abstractDatabaseEntry;
                                    final AbstractDistributableDatabaseEntry.SerialDBEntry serialDBEntry2 = (AbstractDistributableDatabaseEntry.SerialDBEntry)abstractDatabaseEntry2;
                                    if (serialDBEntry.getVersionNumber() != serialDBEntry2.getVersionNumber()) {
                                        LogHolder.log(4, LogType.NET, "InfoServices report different serial numbers for " + serialDBEntry.getId() + "!");
                                        serialDBEntry = new AbstractDistributableDatabaseEntry.SerialDBEntry(serialDBEntry.getId(), 0L, Long.MAX_VALUE, serialDBEntry.isVerified(), serialDBEntry.isValid(), serialDBEntry.getContext());
                                    }
                                    if (serialDBEntry.isVerified() != serialDBEntry2.isVerified()) {
                                        LogHolder.log(4, LogType.NET, "InfoServices report different verification status for " + ClassUtil.getShortClassName(((AbstractDistributableDatabaseEntry.SerialDBEntry)abstractDatabaseEntry).getClass()) + " with id " + serialDBEntry.getId() + "!");
                                        serialDBEntry = new AbstractDistributableDatabaseEntry.SerialDBEntry(serialDBEntry.getId(), serialDBEntry.getVersionNumber(), Long.MAX_VALUE, true, serialDBEntry.isValid(), serialDBEntry.getContext());
                                    }
                                    if (serialDBEntry.isValid() != serialDBEntry2.isValid()) {
                                        LogHolder.log(4, LogType.NET, "InfoServices report different validity status for " + serialDBEntry.getId() + "!");
                                        serialDBEntry = new AbstractDistributableDatabaseEntry.SerialDBEntry(serialDBEntry.getId(), serialDBEntry.getVersionNumber(), Long.MAX_VALUE, serialDBEntry.isVerified(), true, serialDBEntry.getContext());
                                    }
                                    abstractDatabaseEntry = serialDBEntry;
                                }
                                if (abstractDatabaseEntry2.getLastUpdate() > abstractDatabaseEntry.getLastUpdate()) {
                                    continue;
                                }
                            }
                            ((Hashtable<String, AbstractDistributableDatabaseEntry.SerialDBEntry>)result).put(abstractDatabaseEntry.getId(), (AbstractDistributableDatabaseEntry.SerialDBEntry)abstractDatabaseEntry);
                        }
                        if (--access$100 == 0) {
                            break;
                        }
                        entryList.removeElement(preferredInfoService);
                        preferredInfoService = null;
                    }
                }
                catch (Exception ex2) {
                    LogHolder.log(6, LogType.NET, "Contacting IS " + preferredInfoService.getName() + " produced an error!", ex2);
                    entryList.removeElement(preferredInfoService);
                    preferredInfoService = null;
                    if (ex == null) {
                        ex = ex2;
                    }
                    else {
                        if ((!(ex instanceof ExpiredSignatureException) && ex2 instanceof ExpiredSignatureException) || (!(ex instanceof SignatureException) && ex2 instanceof SignatureException)) {
                            continue;
                        }
                        ex = ex2;
                    }
                }
            }
            if (result != null && (!(result instanceof Hashtable) || ((Hashtable<String, AbstractDistributableDatabaseEntry.SerialDBEntry>)result).size() > 0)) {
                if (this.functionNumber == 12) {
                    result = ((Hashtable<String, AbstractDistributableDatabaseEntry.SerialDBEntry>)result).elements().nextElement();
                }
                this.m_result = result;
                return;
            }
            String string = null;
            if (InfoServiceHolder.GETS.length > this.functionNumber) {
                string = InfoServiceHolder.GETS[this.functionNumber];
            }
            if (string == null) {
                string = "the needed information (" + this.functionNumber + ")";
            }
            LogHolder.log(3, LogType.NET, "No InfoService with " + string + " available" + ((this.arguments == null || this.arguments.elementAt(0) == null) ? "." : (" for argument: " + this.arguments.elementAt(0))), true);
            this.m_result = null;
            if (ex != null && ex instanceof SignatureException) {
                LogHolder.log(2, LogType.CRYPTO, "Could not contact InfoServices due to certificate problems.", ex);
                synchronized (InfoServiceHolder.getInstance()) {
                    InfoServiceHolder.this.setChanged();
                    InfoServiceHolder.this.notifyObservers(new InfoServiceHolderMessage(3, ex));
                }
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
    }
}
