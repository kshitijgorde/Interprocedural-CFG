// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.infoservice.PerformanceEntry;
import anon.infoservice.PerformanceInfo;
import anon.infoservice.StatusInfo;
import java.io.Serializable;
import anon.infoservice.ServiceOperator;
import java.security.SignatureException;
import anon.pay.PayAccountsFile;
import anon.infoservice.MixCascade;
import anon.infoservice.Database;
import java.util.Enumeration;
import anon.util.JAPMessages;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.Observer;
import java.util.Observable;
import logging.LogHolder;
import logging.LogType;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Hashtable;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class TrustModel extends BasicTrustModel implements IXMLEncodable
{
    public static final Integer NOTIFY_TRUST_MODEL_CHANGED;
    public static final Integer NOTIFY_TRUST_MODEL_ADDED;
    public static final Integer NOTIFY_TRUST_MODEL_REMOVED;
    public static final int FIRST_UNRESERVED_MODEL_ID = 5;
    public static final String XML_ELEMENT_NAME = "TrustModel";
    public static final String XML_ELEMENT_CONTAINER_NAME = "TrustModels";
    private static final String XML_ATTR_CURRENT_TRUST_MODEL = "currentTrustModel";
    private static final String XML_ATTR_NAME = "name";
    public static final TrustModel TRUST_MODEL_USER_DEFINED;
    private static TrustModel TRUST_MODEL_DEFAULT;
    private static TrustModel TRUST_MODEL_PREMIUM;
    private static TrustModel TRUST_MODEL_CUSTOM_FILTER;
    public static final int TRUST_ALWAYS = 0;
    public static final int TRUST_IF_NOT_TRUE = 1;
    public static final int TRUST_IF_TRUE = 2;
    public static final int TRUST_IF_AT_LEAST = 3;
    public static final int TRUST_IF_AT_MOST = 5;
    public static final int TRUST_IF_NOT_IN_LIST = 6;
    public static final int TRUST_RESERVED = 7;
    public static final long TRUST_MODEL_ALL = 0L;
    private static final String MSG_SERVICES_WITH_COSTS;
    public static final String MSG_SERVICES_WITHOUT_COSTS;
    private static final String MSG_SERVICES_USER_DEFINED;
    private static final String MSG_CASCADES_FILTER;
    private static final String MSG_ALL_SERVICES;
    private static final String MSG_SERVICES_BUSINESS;
    private static final String MSG_SERVICES_PREMIUM_PRIVATE;
    public static final String MSG_PI_UNAVAILABLE;
    public static final String MSG_BLACKLISTED;
    private static final String MSG_EXCEPTION_NO_SOCKS;
    private static final String MSG_EXCEPTION_DATA_RETENTION;
    private static final String MSG_EXCEPTION_PAY_CASCADE;
    public static final String MSG_EXCEPTION_FREE_CASCADE;
    private static final String MSG_EXCEPTION_WRONG_SERVICE_CONTEXT;
    public static final String MSG_EXCEPTION_NOT_ENOUGH_MIXES;
    private static final String MSG_EXCEPTION_EXPIRED_CERT;
    private static final String MSG_EXCEPTION_NOT_USER_DEFINED;
    private static final String MSG_EXCEPTION_TOO_FEW_COUNTRIES;
    private static final String MSG_EXCEPTION_NOT_INTERNATIONAL;
    private static final String MSG_EXCEPTION_INTERNATIONAL;
    private static final String MSG_EXCEPTION_NOT_ENOUGH_ANON;
    private static final String MSG_EXCEPTION_BLACKLISTED;
    private static final String MSG_EXCEPTION_NOT_ENOUGH_SPEED;
    private static final String MSG_EXCEPTION_RESPONSE_TIME_TOO_HIGH;
    private static Vector ms_trustModels;
    private static TrustModel ms_currentTrustModel;
    private static String ms_strContext;
    private static InnerObservable ms_trustModelObservable;
    private static final TrustModel CONTEXT_MODEL_PREMIUM;
    private static final TrustModel CONTEXT_MODEL_PREMIUM_PRIVATE;
    private static final TrustModel CONTEXT_MODEL_BUSINESS;
    private static final TrustModel CONTEXT_MODEL_FREE;
    private static final TrustModel CONTEXT_MODEL_ALL;
    private Hashtable m_trustAttributes;
    private String m_strName;
    private long m_id;
    private boolean m_bEditable;
    static /* synthetic */ Class class$anon$client$TrustModel;
    static /* synthetic */ Class class$anon$client$TrustModel$ContextAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$PremiumAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$NumberOfMixesAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$UserDefinedAttribute;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
    
    public static String getContext() {
        return TrustModel.ms_strContext;
    }
    
    public static boolean updateContext(final String ms_strContext) {
        synchronized (TrustModel.ms_trustModels) {
            boolean b = false;
            if (TrustModel.ms_strContext != ms_strContext && ms_strContext != null && (TrustModel.ms_strContext == null || !TrustModel.ms_strContext.equals(ms_strContext))) {
                if ((TrustModel.ms_strContext == null && !ms_strContext.equals("jondonym")) || TrustModel.ms_strContext != null) {
                    b = true;
                }
                TrustModel.ms_strContext = ms_strContext;
            }
            if (!b) {
                return false;
            }
            final TrustModel ms_currentTrustModel = TrustModel.ms_currentTrustModel;
            TrustModel.ms_trustModels.removeElement(TrustModel.CONTEXT_MODEL_ALL);
            TrustModel.ms_trustModels.removeElement(TrustModel.CONTEXT_MODEL_PREMIUM);
            TrustModel.ms_trustModels.removeElement(TrustModel.CONTEXT_MODEL_FREE);
            TrustModel.ms_trustModels.removeElement(TrustModel.CONTEXT_MODEL_PREMIUM_PRIVATE);
            TrustModel.ms_trustModels.removeElement(TrustModel.CONTEXT_MODEL_BUSINESS);
            if (TrustModel.ms_strContext.equals("jondonym")) {
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_FREE, 0);
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_PREMIUM, 0);
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_ALL, 0);
                TrustModel.ms_currentTrustModel = TrustModel.CONTEXT_MODEL_ALL;
            }
            else if (TrustModel.ms_strContext.startsWith("jondonym.premium")) {
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_PREMIUM_PRIVATE, 0);
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_BUSINESS, 0);
                TrustModel.ms_currentTrustModel = TrustModel.CONTEXT_MODEL_BUSINESS;
            }
            else {
                TrustModel.ms_trustModels.insertElementAt(TrustModel.CONTEXT_MODEL_BUSINESS, 0);
                TrustModel.ms_currentTrustModel = TrustModel.CONTEXT_MODEL_BUSINESS;
            }
            TrustModel.ms_trustModelObservable.setChanged();
            setCurrentTrustModel(ms_currentTrustModel.getId());
            return true;
        }
    }
    
    public TrustModel(final String s, final long n) {
        this(s, n, false);
    }
    
    private TrustModel(final String s, final long id, final boolean b) {
        this.m_trustAttributes = new Hashtable();
        if (!b && id < 5L) {
            throw new IllegalArgumentException("Trust model ID " + id + " is reserved!");
        }
        this.m_id = id;
        this.m_strName = ((s == null) ? "Default trust model" : s);
    }
    
    public TrustModel(final TrustModel trustModel) {
        this.m_trustAttributes = new Hashtable();
        this.copyFrom(trustModel);
    }
    
    public TrustModel(final Element element) throws XMLParseException {
        this.m_trustAttributes = new Hashtable();
        XMLUtil.assertNodeName(element, "TrustModel");
        XMLUtil.assertNotNull(element, "id");
        XMLUtil.assertNotNull(element, "name");
        this.m_id = XMLUtil.parseAttribute(element, "id", -1L);
        if (this.m_id < 5L) {
            throw new XMLParseException("Reserved model ID: " + this.m_id);
        }
        this.m_strName = XMLUtil.parseAttribute(element, "name", null);
        this.m_bEditable = true;
        for (int i = 0; i < element.getChildNodes().getLength(); ++i) {
            final Element element2 = (Element)element.getChildNodes().item(i);
            try {
                this.setAttribute(TrustAttribute.fromXmlElement(element2));
            }
            catch (XMLParseException ex) {
                LogHolder.log(3, LogType.MISC, ex);
            }
        }
    }
    
    public void copyFrom(final TrustModel trustModel) {
        if (trustModel == null) {
            throw new IllegalArgumentException("No argument given!");
        }
        this.m_id = trustModel.m_id;
        this.m_strName = trustModel.m_strName;
        this.m_bEditable = trustModel.m_bEditable;
        synchronized (this.m_trustAttributes) {
            this.m_trustAttributes = (Hashtable)trustModel.m_trustAttributes.clone();
        }
        synchronized (TrustModel.ms_trustModels) {
            if (TrustModel.ms_trustModels.contains(this)) {
                TrustModel.ms_trustModelObservable.setChanged();
                TrustModel.ms_trustModelObservable.notifyObservers(TrustModel.NOTIFY_TRUST_MODEL_CHANGED);
            }
        }
    }
    
    public static Observable getObservable() {
        return TrustModel.ms_trustModelObservable;
    }
    
    public static void addModelObserver(final Observer observer) {
        TrustModel.ms_trustModelObservable.addObserver(observer);
    }
    
    public static void deleteModelObserver(final Observer observer) {
        TrustModel.ms_trustModelObservable.deleteObserver(observer);
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof TrustModel && this.getId() == ((TrustModel)o).getId();
    }
    
    public int hashCode() {
        return (int)this.getId();
    }
    
    public static boolean addTrustModel(final TrustModel trustModel) {
        synchronized (TrustModel.ms_trustModels) {
            if (trustModel != null && !TrustModel.ms_trustModels.contains(trustModel)) {
                TrustModel.ms_trustModels.addElement(trustModel);
                TrustModel.ms_trustModelObservable.setChanged();
                TrustModel.ms_trustModelObservable.notifyObservers(TrustModel.NOTIFY_TRUST_MODEL_ADDED);
                return true;
            }
        }
        return false;
    }
    
    public static TrustModel removeTrustModel(final TrustModel trustModel) {
        if (!trustModel.isEditable()) {
            return null;
        }
        synchronized (TrustModel.ms_trustModels) {
            if (trustModel != null && TrustModel.ms_trustModels.removeElement(trustModel)) {
                TrustModel.ms_trustModelObservable.setChanged();
                TrustModel.ms_trustModelObservable.notifyObservers(TrustModel.NOTIFY_TRUST_MODEL_REMOVED);
                return trustModel;
            }
        }
        return null;
    }
    
    public TrustAttribute setAttribute(final Class clazz, final int n) {
        return this.setAttribute(clazz, n, false);
    }
    
    private TrustAttribute setAttribute(final Class clazz, final int n, final boolean b) {
        return this.setAttribute(clazz, n, null, b);
    }
    
    public TrustAttribute setAttribute(final Class clazz, final int n, final int n2) {
        return this.setAttribute(clazz, n, n2, false);
    }
    
    private TrustAttribute setAttribute(final Class clazz, final int n, final int n2, final boolean b) {
        return this.setAttribute(clazz, n, new Integer(n2), b);
    }
    
    public TrustAttribute setAttribute(final Class clazz, final int n, final Vector vector) {
        return this.setAttribute(clazz, n, vector, false);
    }
    
    public void setEditable(final boolean bEditable) {
        this.m_bEditable = bEditable;
    }
    
    public boolean isEditable() {
        return this.m_bEditable;
    }
    
    private TrustAttribute setAttribute(final Class clazz, final int n, final Object o, final boolean b) {
        return this.setAttribute(TrustAttribute.getInstance(clazz, n, o, b));
    }
    
    private TrustAttribute setAttribute(final TrustAttribute trustAttribute) {
        if (trustAttribute != null) {
            synchronized (this) {
                final TrustAttribute trustAttribute2;
                if ((trustAttribute2 = this.m_trustAttributes.get(trustAttribute)) != null && (trustAttribute2.getTrustCondition() != trustAttribute.getTrustCondition() || trustAttribute2.getConditionValue() != trustAttribute.getConditionValue())) {
                    this.m_trustAttributes.put(trustAttribute, trustAttribute2);
                    this.setChanged();
                }
                this.m_trustAttributes.put(trustAttribute.getClass(), trustAttribute);
                this.notifyObservers();
            }
        }
        return trustAttribute;
    }
    
    public TrustAttribute getAttribute(final Class clazz) {
        if (clazz == null) {
            return null;
        }
        synchronized (this.m_trustAttributes) {
            final TrustAttribute trustAttribute = this.m_trustAttributes.get(clazz);
            if (trustAttribute == null) {
                Integer n = new Integer(0);
                try {
                    n = (Integer)clazz.getMethod("getDefaultValue", (Class[])null).invoke(null, (Object[])null);
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.MISC, "Exception occured while trying to get the default value of a TrustAttribute: ", ex);
                }
                return this.setAttribute(clazz, 0, n);
            }
            return trustAttribute;
        }
    }
    
    public static Vector getTrustModels() {
        return (Vector)TrustModel.ms_trustModels.clone();
    }
    
    public static void forceFreeTrustModel() {
        synchronized (TrustModel.ms_trustModels) {
            for (int i = 0; i < TrustModel.ms_trustModels.size(); ++i) {
                if (TrustModel.ms_trustModels.elementAt(i) == TrustModel.CONTEXT_MODEL_BUSINESS) {
                    setCurrentTrustModel(TrustModel.CONTEXT_MODEL_BUSINESS);
                    break;
                }
                if (TrustModel.ms_trustModels.elementAt(i) == TrustModel.CONTEXT_MODEL_FREE) {
                    setCurrentTrustModel(TrustModel.CONTEXT_MODEL_FREE);
                    break;
                }
            }
        }
    }
    
    public static void setCurrentTrustModel(final long n) {
        if (n < 0L) {
            return;
        }
        synchronized (TrustModel.ms_trustModels) {
            for (int i = 0; i < TrustModel.ms_trustModels.size(); ++i) {
                if (((TrustModel)TrustModel.ms_trustModels.elementAt(i)).getId() == n) {
                    TrustModel.ms_currentTrustModel = (TrustModel)TrustModel.ms_trustModels.elementAt(i);
                    TrustModel.ms_trustModelObservable.setChanged();
                    break;
                }
            }
            TrustModel.ms_trustModelObservable.notifyObservers(TrustModel.NOTIFY_TRUST_MODEL_CHANGED);
        }
    }
    
    public static void setCurrentTrustModel(final TrustModel ms_currentTrustModel) {
        if (ms_currentTrustModel == null) {
            return;
        }
        synchronized (TrustModel.ms_trustModels) {
            if (!TrustModel.ms_trustModels.contains(ms_currentTrustModel)) {
                TrustModel.ms_trustModels.addElement(ms_currentTrustModel);
            }
            if (TrustModel.ms_currentTrustModel != ms_currentTrustModel) {
                TrustModel.ms_currentTrustModel = ms_currentTrustModel;
                TrustModel.ms_trustModelObservable.setChanged();
            }
            TrustModel.ms_trustModelObservable.notifyObservers(TrustModel.NOTIFY_TRUST_MODEL_CHANGED);
        }
    }
    
    public static TrustModel getTrustModelUserDefined() {
        return TrustModel.TRUST_MODEL_USER_DEFINED;
    }
    
    public static TrustModel getTrustModelPremium() {
        return TrustModel.TRUST_MODEL_PREMIUM;
    }
    
    public static TrustModel getTrustModelDefault() {
        return TrustModel.TRUST_MODEL_DEFAULT;
    }
    
    public static TrustModel getCurrentTrustModel() {
        synchronized (TrustModel.ms_trustModels) {
            return TrustModel.ms_currentTrustModel;
        }
    }
    
    public static TrustModel getCustomFilter() {
        return TrustModel.TRUST_MODEL_CUSTOM_FILTER;
    }
    
    public static void fromXmlElement(final Element element) {
        int n = 0;
        if (element != null && element.getNodeName().equals("TrustModels")) {
            final NodeList elementsByTagName = element.getElementsByTagName("TrustModel");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                try {
                    addTrustModel(TrustModel.TRUST_MODEL_CUSTOM_FILTER = new TrustModel((Element)elementsByTagName.item(i)));
                    ++n;
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.MISC, "Could not load trust model from XML!", ex);
                }
            }
            setCurrentTrustModel(XMLUtil.parseAttribute(element, "currentTrustModel", 0L));
        }
        if (n == 0) {
            final TrustModel trust_MODEL_CUSTOM_FILTER = new TrustModel("", 5L, true);
            trust_MODEL_CUSTOM_FILTER.setEditable(true);
            addTrustModel(TrustModel.TRUST_MODEL_CUSTOM_FILTER = trust_MODEL_CUSTOM_FILTER);
        }
    }
    
    public static void restoreDefault() {
        removeTrustModel(TrustModel.TRUST_MODEL_CUSTOM_FILTER);
        final TrustModel trust_MODEL_CUSTOM_FILTER = new TrustModel("", 5L, true);
        trust_MODEL_CUSTOM_FILTER.setEditable(true);
        addTrustModel(TrustModel.TRUST_MODEL_CUSTOM_FILTER = trust_MODEL_CUSTOM_FILTER);
        setCurrentTrustModel(TrustModel.TRUST_MODEL_DEFAULT);
    }
    
    public static Element toXmlElement(final Document document, final String s) {
        if (document == null || s == null) {
            return null;
        }
        final Element element = document.createElement(s);
        XMLUtil.setAttribute(element, "currentTrustModel", getCurrentTrustModel().getId());
        synchronized (TrustModel.ms_trustModels) {
            for (int i = 0; i < TrustModel.ms_trustModels.size(); ++i) {
                if (((TrustModel)TrustModel.ms_trustModels.elementAt(i)).isEditable()) {
                    element.appendChild(((TrustModel)TrustModel.ms_trustModels.elementAt(i)).toXmlElement(document));
                }
            }
        }
        return element;
    }
    
    public void setName(final String strName) throws IllegalArgumentException {
        if (strName == null || strName.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid name for trust model!");
        }
        if (strName.trim().equals(JAPMessages.getString(TrustModel.MSG_CASCADES_FILTER))) {
            this.m_strName = "";
        }
        else {
            this.m_strName = strName;
        }
    }
    
    public String getName() {
        if (this.m_strName == null || this.m_strName.trim().length() == 0) {
            return JAPMessages.getString(TrustModel.MSG_CASCADES_FILTER);
        }
        return JAPMessages.getString(this.m_strName);
    }
    
    public String toString() {
        return this.getName();
    }
    
    public long getId() {
        return this.m_id;
    }
    
    public Element toXmlElement(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("TrustModel");
        XMLUtil.setAttribute(element, "id", this.m_id);
        XMLUtil.setAttribute(element, "name", this.m_strName);
        synchronized (this.m_trustAttributes) {
            synchronized (this.m_trustAttributes) {
                final Enumeration<TrustAttribute> elements = this.m_trustAttributes.elements();
                while (elements.hasMoreElements()) {
                    element.appendChild(elements.nextElement().toXmlElement(document));
                }
            }
        }
        return element;
    }
    
    public boolean isPaymentForced() {
        final TrustAttribute attribute = this.getAttribute((TrustModel.class$anon$client$TrustModel$PremiumAttribute == null) ? (TrustModel.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : TrustModel.class$anon$client$TrustModel$PremiumAttribute);
        return attribute != null && attribute.getTrustCondition() == 2;
    }
    
    public boolean isAdded() {
        return true;
    }
    
    public boolean hasTrustedCascades() {
        final Vector entryList = Database.getInstance((TrustModel.class$anon$infoservice$MixCascade == null) ? (TrustModel.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : TrustModel.class$anon$infoservice$MixCascade).getEntryList();
        for (int i = 0; i < entryList.size(); ++i) {
            if (this.isTrusted(entryList.elementAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isBlacklisted(final MixCascade mixCascade) {
        return mixCascade != null && Database.getInstance((TrustModel.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (TrustModel.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : TrustModel.class$anon$infoservice$BlacklistedCascadeIDEntry).getEntryById(mixCascade.getMixIDsAsString()) != null;
    }
    
    public static boolean isNoPaymentInstanceFound(final MixCascade mixCascade) {
        return mixCascade != null && mixCascade.isPayment() && PayAccountsFile.getInstance().getBI(mixCascade.getPIID()) == null && PayAccountsFile.getInstance().getChargedAccount(mixCascade.getPIID()) != null;
    }
    
    public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
        if (mixCascade == null) {
            throw new TrustException("Null cascade!");
        }
        if (isBlacklisted(mixCascade)) {
            throw new TrustException(JAPMessages.getString(TrustModel.MSG_BLACKLISTED));
        }
        if (isNoPaymentInstanceFound(mixCascade)) {
            throw new TrustException(JAPMessages.getString(TrustModel.MSG_PI_UNAVAILABLE));
        }
        synchronized (this.m_trustAttributes) {
            final TrustAttribute trustAttribute = this.m_trustAttributes.get((TrustModel.class$anon$client$TrustModel$UserDefinedAttribute == null) ? (TrustModel.class$anon$client$TrustModel$UserDefinedAttribute = class$("anon.client.TrustModel$UserDefinedAttribute")) : TrustModel.class$anon$client$TrustModel$UserDefinedAttribute);
            if (trustAttribute != null && trustAttribute.getTrustCondition() == 2 && mixCascade.isUserDefined()) {
                return;
            }
        }
        super.checkTrust(mixCascade);
        synchronized (this.m_trustAttributes) {
            final Enumeration<TrustAttribute> elements = this.m_trustAttributes.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().checkTrust(mixCascade);
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
        NOTIFY_TRUST_MODEL_CHANGED = new Integer(0);
        NOTIFY_TRUST_MODEL_ADDED = new Integer(1);
        NOTIFY_TRUST_MODEL_REMOVED = new Integer(2);
        MSG_SERVICES_WITH_COSTS = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesWithCosts";
        MSG_SERVICES_WITHOUT_COSTS = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesWithoutCosts";
        MSG_SERVICES_USER_DEFINED = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesUserDefined";
        MSG_CASCADES_FILTER = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesFilter";
        MSG_ALL_SERVICES = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_allServices";
        MSG_SERVICES_BUSINESS = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesBusiness";
        MSG_SERVICES_PREMIUM_PRIVATE = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_servicesPremiumPrivate";
        MSG_PI_UNAVAILABLE = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_piUnavailable";
        MSG_BLACKLISTED = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_blacklisted";
        MSG_EXCEPTION_NO_SOCKS = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNoSocks";
        MSG_EXCEPTION_DATA_RETENTION = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionDataRetention";
        MSG_EXCEPTION_PAY_CASCADE = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionPayCascade";
        MSG_EXCEPTION_FREE_CASCADE = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionFreeCascade";
        MSG_EXCEPTION_WRONG_SERVICE_CONTEXT = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_wrongServiceContext";
        MSG_EXCEPTION_NOT_ENOUGH_MIXES = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNotEnoughMixes";
        MSG_EXCEPTION_EXPIRED_CERT = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionExpiredCert";
        MSG_EXCEPTION_NOT_USER_DEFINED = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNotUserDefined";
        MSG_EXCEPTION_TOO_FEW_COUNTRIES = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionTooFewCountries";
        MSG_EXCEPTION_NOT_INTERNATIONAL = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNotInternational";
        MSG_EXCEPTION_INTERNATIONAL = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionInternational";
        MSG_EXCEPTION_NOT_ENOUGH_ANON = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNotEnoughAnon";
        MSG_EXCEPTION_BLACKLISTED = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionBlacklisted";
        MSG_EXCEPTION_NOT_ENOUGH_SPEED = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionNotEnoughSpeed";
        MSG_EXCEPTION_RESPONSE_TIME_TOO_HIGH = ((TrustModel.class$anon$client$TrustModel == null) ? (TrustModel.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustModel.class$anon$client$TrustModel).getName() + "_exceptionResponseTimeTooHigh";
        TrustModel.ms_trustModels = new Vector();
        TrustModel.ms_strContext = "jondonym";
        TrustModel.ms_trustModelObservable = new InnerObservable();
        final TrustModel trustModel = new TrustModel(TrustModel.MSG_ALL_SERVICES, 0L, true);
        trustModel.setAttribute((TrustModel.class$anon$client$TrustModel$ContextAttribute == null) ? (TrustModel.class$anon$client$TrustModel$ContextAttribute = class$("anon.client.TrustModel$ContextAttribute")) : TrustModel.class$anon$client$TrustModel$ContextAttribute, 7);
        TrustModel.TRUST_MODEL_DEFAULT = trustModel;
        CONTEXT_MODEL_ALL = trustModel;
        TrustModel.ms_trustModels.addElement(trustModel);
        final TrustModel context_MODEL_BUSINESS = new TrustModel(TrustModel.MSG_SERVICES_BUSINESS, 0L, true);
        context_MODEL_BUSINESS.setAttribute((TrustModel.class$anon$client$TrustModel$PremiumAttribute == null) ? (TrustModel.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : TrustModel.class$anon$client$TrustModel$PremiumAttribute, 1);
        context_MODEL_BUSINESS.setAttribute((TrustModel.class$anon$client$TrustModel$ContextAttribute == null) ? (TrustModel.class$anon$client$TrustModel$ContextAttribute = class$("anon.client.TrustModel$ContextAttribute")) : TrustModel.class$anon$client$TrustModel$ContextAttribute, 2);
        CONTEXT_MODEL_BUSINESS = context_MODEL_BUSINESS;
        final TrustModel context_MODEL_PREMIUM_PRIVATE = new TrustModel(TrustModel.MSG_SERVICES_PREMIUM_PRIVATE, 2L, true);
        context_MODEL_PREMIUM_PRIVATE.setAttribute((TrustModel.class$anon$client$TrustModel$PremiumAttribute == null) ? (TrustModel.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : TrustModel.class$anon$client$TrustModel$PremiumAttribute, 2);
        context_MODEL_PREMIUM_PRIVATE.setAttribute((TrustModel.class$anon$client$TrustModel$ContextAttribute == null) ? (TrustModel.class$anon$client$TrustModel$ContextAttribute = class$("anon.client.TrustModel$ContextAttribute")) : TrustModel.class$anon$client$TrustModel$ContextAttribute, 1);
        context_MODEL_PREMIUM_PRIVATE.setAttribute((TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute == null) ? (TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute = class$("anon.client.TrustModel$NumberOfMixesAttribute")) : TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute, 3, 3);
        CONTEXT_MODEL_PREMIUM_PRIVATE = context_MODEL_PREMIUM_PRIVATE;
        final TrustModel trustModel2 = new TrustModel(TrustModel.MSG_SERVICES_WITH_COSTS, 2L, true);
        trustModel2.setAttribute((TrustModel.class$anon$client$TrustModel$PremiumAttribute == null) ? (TrustModel.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : TrustModel.class$anon$client$TrustModel$PremiumAttribute, 2);
        trustModel2.setAttribute((TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute == null) ? (TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute = class$("anon.client.TrustModel$NumberOfMixesAttribute")) : TrustModel.class$anon$client$TrustModel$NumberOfMixesAttribute, 3, 3);
        TrustModel.TRUST_MODEL_PREMIUM = trustModel2;
        CONTEXT_MODEL_PREMIUM = trustModel2;
        TrustModel.ms_trustModels.addElement(trustModel2);
        final TrustModel context_MODEL_FREE = new TrustModel(TrustModel.MSG_SERVICES_WITHOUT_COSTS, 3L, true);
        context_MODEL_FREE.setAttribute((TrustModel.class$anon$client$TrustModel$PremiumAttribute == null) ? (TrustModel.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : TrustModel.class$anon$client$TrustModel$PremiumAttribute, 1);
        CONTEXT_MODEL_FREE = context_MODEL_FREE;
        TrustModel.ms_trustModels.addElement(context_MODEL_FREE);
        final TrustModel trust_MODEL_USER_DEFINED = new TrustModel(TrustModel.MSG_SERVICES_USER_DEFINED, 4L, true) {
            static /* synthetic */ Class class$anon$infoservice$MixCascade;
            
            public boolean isAdded() {
                final Enumeration entrySnapshotAsEnumeration = Database.getInstance((TrustModel$1.class$anon$infoservice$MixCascade == null) ? (TrustModel$1.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : TrustModel$1.class$anon$infoservice$MixCascade).getEntrySnapshotAsEnumeration();
                while (entrySnapshotAsEnumeration.hasMoreElements()) {
                    if (entrySnapshotAsEnumeration.nextElement().isUserDefined()) {
                        return true;
                    }
                }
                return false;
            }
            
            static /* synthetic */ Class class$(final String s) {
                try {
                    return Class.forName(s);
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
        };
        trust_MODEL_USER_DEFINED.setAttribute((TrustModel.class$anon$client$TrustModel$UserDefinedAttribute == null) ? (TrustModel.class$anon$client$TrustModel$UserDefinedAttribute = class$("anon.client.TrustModel$UserDefinedAttribute")) : TrustModel.class$anon$client$TrustModel$UserDefinedAttribute, 2);
        TRUST_MODEL_USER_DEFINED = trust_MODEL_USER_DEFINED;
        TrustModel.ms_trustModels.addElement(trust_MODEL_USER_DEFINED);
        setCurrentTrustModel(TrustModel.ms_trustModels.elementAt(0));
    }
    
    public static class InnerObservable extends Observable
    {
        public void setChanged() {
            super.setChanged();
        }
    }
    
    public abstract static class TrustAttribute implements IXMLEncodable
    {
        public static final int CATEGORY_DEFAULT = 0;
        public static final String XML_ELEMENT_NAME = "TrustAttribute";
        public static final String XML_VALUE_ELEMENT_NAME = "ConditionValue";
        public static final String XML_VALUE_CONTAINER_ELEMENT_NAME = "ConditionValueList";
        public static final String XML_ATTR_NAME = "name";
        public static final String XML_ATTR_TRUST_CONDITION = "trustCondition";
        public static final String XML_ATTR_CONDITION_VALUE = "conditonValue";
        public static final String XML_ATTR_IGNORE_NO_DATA = "ignoreNoData";
        private int m_category;
        private boolean m_bIgnoreNoDataAvailable;
        private int m_trustCondition;
        private Object m_conditionValue;
        static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
        static /* synthetic */ Class class$anon$client$TrustModel;
        static /* synthetic */ Class class$java$lang$Object;
        
        protected TrustAttribute(final int trustCondition, final Object conditionValue, final boolean bIgnoreNoDataAvailable) {
            this.m_trustCondition = trustCondition;
            this.m_conditionValue = conditionValue;
            this.m_category = 0;
            this.m_bIgnoreNoDataAvailable = bIgnoreNoDataAvailable;
        }
        
        public static int getDefaultValue() {
            return 0;
        }
        
        public boolean isNoDataIgnored() {
            return this.m_bIgnoreNoDataAvailable;
        }
        
        public final int getCategory() {
            return this.m_category;
        }
        
        public int getTrustCondition() {
            return this.m_trustCondition;
        }
        
        public Object getConditionValue() {
            return this.m_conditionValue;
        }
        
        public boolean isTrusted(final MixCascade mixCascade) {
            try {
                this.checkTrust(mixCascade);
                return true;
            }
            catch (TrustException ex) {}
            catch (SignatureException ex2) {}
            return false;
        }
        
        public abstract void checkTrust(final MixCascade p0) throws TrustException, SignatureException;
        
        public Element toXmlElement(final Document document) {
            if (document == null) {
                return null;
            }
            final Element element = document.createElement("TrustAttribute");
            XMLUtil.setAttribute(element, "name", this.getClass().getName());
            XMLUtil.setAttribute(element, "trustCondition", this.m_trustCondition);
            XMLUtil.setAttribute(element, "ignoreNoData", this.m_bIgnoreNoDataAvailable);
            if (this.m_conditionValue instanceof Integer) {
                XMLUtil.setAttribute(element, "conditonValue", (int)this.m_conditionValue);
            }
            else if (this.m_conditionValue instanceof Vector) {
                final Vector vector = (Vector)this.m_conditionValue;
                final Element element2 = document.createElement("ConditionValueList");
                for (int i = 0; i < vector.size(); ++i) {
                    final Element element3 = document.createElement("ConditionValue");
                    XMLUtil.setValue(element3, vector.elementAt(i).getId());
                    element2.appendChild(element3);
                }
                element.appendChild(element2);
            }
            return element;
        }
        
        public static TrustAttribute fromXmlElement(final Element element) throws XMLParseException {
            if (element == null) {
                return null;
            }
            XMLUtil.assertNodeName(element, "TrustAttribute");
            XMLUtil.assertNotNull(element, "name");
            String s = XMLUtil.parseAttribute(element, "name", null);
            final int attribute = XMLUtil.parseAttribute(element, "trustCondition", 0);
            final int attribute2 = XMLUtil.parseAttribute(element, "conditonValue", 0);
            final boolean attribute3 = XMLUtil.parseAttribute(element, "ignoreNoData", false);
            TrustAttribute instance;
            try {
                Serializable s2;
                if (attribute == 6) {
                    final Node firstChildByName = XMLUtil.getFirstChildByName(element, "ConditionValueList");
                    XMLUtil.assertNotNull(firstChildByName);
                    final NodeList childNodes = firstChildByName.getChildNodes();
                    s2 = new Vector<Object>();
                    for (int i = 0; i < childNodes.getLength(); ++i) {
                        final ServiceOperator serviceOperator = (ServiceOperator)Database.getInstance((TrustAttribute.class$anon$infoservice$ServiceOperator == null) ? (TrustAttribute.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : TrustAttribute.class$anon$infoservice$ServiceOperator).getEntryById(XMLUtil.parseValue(childNodes.item(i), null));
                        if (serviceOperator != null) {
                            ((Vector<ServiceOperator>)s2).addElement(serviceOperator);
                        }
                    }
                }
                else {
                    s2 = new Integer(attribute2);
                }
                if (s != null && s.startsWith("jap.TrustModel")) {
                    s = ((TrustAttribute.class$anon$client$TrustModel == null) ? (TrustAttribute.class$anon$client$TrustModel = class$("anon.client.TrustModel")) : TrustAttribute.class$anon$client$TrustModel).getName() + s.substring("jap.TrustModel".length(), s.length());
                }
                instance = getInstance(Class.forName(s), attribute, s2, attribute3);
                if (instance == null) {
                    throw new XMLParseException("TrustAttribute", "Could not create TrustAttribute + " + s + "!");
                }
            }
            catch (Exception ex) {
                LogHolder.log(5, LogType.DB, ex);
                throw new XMLParseException("TrustAttribute", ex.getMessage());
            }
            return instance;
        }
        
        public static TrustAttribute getInstance(final Class clazz, final int n, final Object o, final boolean b) {
            try {
                return clazz.getConstructor(Integer.TYPE, (TrustAttribute.class$java$lang$Object == null) ? (TrustAttribute.class$java$lang$Object = class$("java.lang.Object")) : TrustAttribute.class$java$lang$Object, Boolean.TYPE).newInstance(new Integer(n), o, new Boolean(b));
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, "Could not create " + clazz);
                return null;
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
    
    public static class ContextAttribute extends TrustAttribute
    {
        public ContextAttribute(final int n, final Object o, final boolean b) {
            super(n, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            final String context = mixCascade.getContext();
            final String access$000 = TrustModel.ms_strContext;
            if (this.getTrustCondition() == 2 && !context.equals(access$000)) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_WRONG_SERVICE_CONTEXT));
            }
            if (this.getTrustCondition() == 1 && context.equals(access$000)) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_WRONG_SERVICE_CONTEXT));
            }
            if (!context.equals(access$000) && (!access$000.startsWith("jondonym") || !context.equals("jondonym.premium"))) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_WRONG_SERVICE_CONTEXT));
            }
        }
    }
    
    public static class PremiumAttribute extends TrustAttribute
    {
        public PremiumAttribute(final int n, final Object o, final boolean b) {
            super(n, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (mixCascade.isPayment()) {
                if (this.getTrustCondition() == 1) {
                    throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_PAY_CASCADE));
                }
            }
            else if (this.getTrustCondition() == 2) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_FREE_CASCADE));
            }
        }
    }
    
    public static class DataRetentionAttribute extends TrustAttribute
    {
        public DataRetentionAttribute(final int n, final Object o, final boolean b) {
            super(n, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (mixCascade.getDataRetentionInformation() != null && this.getTrustCondition() == 1) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_DATA_RETENTION));
            }
        }
    }
    
    public static class SocksAttribute extends TrustAttribute
    {
        public SocksAttribute(final int n, final Object o, final boolean b) {
            super(n, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (!mixCascade.isSocks5Supported() && this.getTrustCondition() == 2) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NO_SOCKS));
            }
        }
    }
    
    public static class NumberOfMixesAttribute extends TrustAttribute
    {
        public NumberOfMixesAttribute(final int n, final Object o, final boolean b) {
            super(3, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            final int intValue = (int)this.getConditionValue();
            if (this.getTrustCondition() == 3 && (mixCascade == null || mixCascade.getNumberOfOperators() < intValue)) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NOT_ENOUGH_MIXES));
            }
        }
    }
    
    public static class UserDefinedAttribute extends TrustAttribute
    {
        public UserDefinedAttribute(final int n, final Object o, final boolean b) {
            super(n, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (this.getTrustCondition() != 2) {
                return;
            }
            if (mixCascade.isUserDefined()) {
                return;
            }
            throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NOT_USER_DEFINED));
        }
    }
    
    public static class InternationalAttribute extends TrustAttribute
    {
        public InternationalAttribute(final int n, final Object o, final boolean b) {
            super(3, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (this.getTrustCondition() == 3 && mixCascade.getNumberOfCountries() < (int)this.getConditionValue()) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_TOO_FEW_COUNTRIES));
            }
        }
    }
    
    public static class AnonLevelAttribute extends TrustAttribute
    {
        static /* synthetic */ Class class$anon$infoservice$StatusInfo;
        
        public AnonLevelAttribute(final int n, final Object o, final boolean b) {
            super(3, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            final StatusInfo statusInfo = (StatusInfo)Database.getInstance((AnonLevelAttribute.class$anon$infoservice$StatusInfo == null) ? (AnonLevelAttribute.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : AnonLevelAttribute.class$anon$infoservice$StatusInfo).getEntryById(mixCascade.getId());
            if (this.getTrustCondition() == 3 && (statusInfo == null || statusInfo.getAnonLevel() < (int)this.getConditionValue())) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NOT_ENOUGH_ANON));
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
    
    public static class OperatorBlacklistAttribute extends TrustAttribute
    {
        public OperatorBlacklistAttribute(final int n, final Object o, final boolean b) {
            super(6, (o == null || !(o instanceof Vector)) ? new Vector() : o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            if (this.getTrustCondition() == 6) {
                for (int i = 0; i < mixCascade.getNumberOfMixes(); ++i) {
                    final Vector vector = (Vector)this.getConditionValue();
                    final ServiceOperator serviceOperator = mixCascade.getMixInfo(i).getServiceOperator();
                    if (vector.contains(serviceOperator)) {
                        throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_BLACKLISTED));
                    }
                    if (serviceOperator.getOrganization() != null) {
                        for (int j = 0; j < vector.size(); ++j) {
                            final ServiceOperator serviceOperator2 = vector.elementAt(j);
                            if (serviceOperator2.getOrganization() != null) {
                                if (serviceOperator2.getOrganization().equals(serviceOperator.getOrganization())) {
                                    throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_BLACKLISTED));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static class SpeedAttribute extends TrustAttribute
    {
        public SpeedAttribute(final int n, final Object o, final boolean b) {
            super(3, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException {
            final PerformanceEntry lowestCommonBoundEntry = PerformanceInfo.getLowestCommonBoundEntry(mixCascade.getId());
            final int intValue = (int)this.getConditionValue();
            if (intValue <= 0) {
                return;
            }
            if (lowestCommonBoundEntry == null || lowestCommonBoundEntry.getBound(0).getBound() == Integer.MAX_VALUE) {
                if (this.isNoDataIgnored()) {
                    return;
                }
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NOT_ENOUGH_SPEED));
            }
            else if (this.getTrustCondition() == 3 && (lowestCommonBoundEntry == null || lowestCommonBoundEntry.getBound(0).getBound() < intValue)) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_NOT_ENOUGH_SPEED));
            }
        }
    }
    
    public static class DelayAttribute extends TrustAttribute
    {
        public DelayAttribute(final int n, final Object o, final boolean b) {
            super(5, o, b);
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException {
            final PerformanceEntry lowestCommonBoundEntry = PerformanceInfo.getLowestCommonBoundEntry(mixCascade.getId());
            final int intValue = (int)this.getConditionValue();
            if (intValue == Integer.MAX_VALUE) {
                return;
            }
            if (lowestCommonBoundEntry == null || lowestCommonBoundEntry.getBound(1).getBound() == 0) {
                if (this.isNoDataIgnored()) {
                    return;
                }
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_RESPONSE_TIME_TOO_HIGH));
            }
            else if (this.getTrustCondition() == 5 && (lowestCommonBoundEntry == null || lowestCommonBoundEntry.getBound(1).getBound() < 0 || lowestCommonBoundEntry.getBound(1).getBound() > intValue)) {
                throw new TrustException(JAPMessages.getString(TrustModel.MSG_EXCEPTION_RESPONSE_TIME_TOO_HIGH));
            }
        }
        
        public static int getDefaultValue() {
            return Integer.MAX_VALUE;
        }
    }
}
