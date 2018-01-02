// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import anon.util.Util;
import java.util.Hashtable;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import java.util.Date;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Calendar;
import anon.util.IXMLEncodable;

public class PerformanceEntry extends AbstractDatabaseEntry implements IXMLEncodable
{
    public static final long WEEK_SEVEN_DAYS_TIMEOUT = 604800000L;
    public static final long ONE_DAY_TIMEOUT = 86400000L;
    private static final double BOUND_ROUNDING = 0.1;
    public static final String XML_ELEMENT_CONTAINER_NAME = "PerformanceInfo";
    public static final String XML_ELEMENT_NAME = "PerformanceEntry";
    private static final String XML_ELEMENT_DATA = "Data";
    private static final String XML_ATTR_ID = "id";
    public static final long LAST_TEST_DATA_TTL = 1200000L;
    private static final int PERFORMANCE_ENTRY_TTL = 3600000;
    public static final int SPEED = 0;
    public static final int DELAY = 1;
    public static final int USERS = 2;
    public static final int PACKETS = 3;
    private static final String[] ATTRIBUTES;
    public static final int[][] BOUNDARIES;
    private String m_strCascadeId;
    private Calendar m_current;
    private long m_lastUpdate;
    private long m_serial;
    private boolean m_bPassive;
    private long m_lastTestTime;
    private StabilityAttributes m_stabilityAttributes;
    private PerformanceAttributeEntry[][][] m_entries;
    private PerformanceAttributeFloatingTimeEntry[] m_floatingTimeEntries;
    private int[] m_lastTestAverage;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    
    public PerformanceEntry(final String s) {
        this(s, false);
    }
    
    public PerformanceEntry(final String strCascadeId, final boolean bPassive) {
        super(Long.MAX_VALUE);
        this.m_current = Calendar.getInstance();
        this.m_entries = new PerformanceAttributeEntry[PerformanceEntry.ATTRIBUTES.length][8][24];
        this.m_lastTestAverage = new int[4];
        this.m_strCascadeId = strCascadeId;
        this.m_lastUpdate = System.currentTimeMillis();
        this.m_serial = System.currentTimeMillis();
        this.m_bPassive = bPassive;
        this.m_floatingTimeEntries = new PerformanceAttributeFloatingTimeEntry[] { new PerformanceAttributeFloatingTimeEntry(0, !bPassive), new PerformanceAttributeFloatingTimeEntry(1, !bPassive), new PerformanceAttributeFloatingTimeEntry(2, !bPassive), new PerformanceAttributeFloatingTimeEntry(3, !bPassive) };
    }
    
    public PerformanceEntry(final Element element) throws XMLParseException {
        super(System.currentTimeMillis() + 3600000L);
        this.m_current = Calendar.getInstance();
        this.m_entries = new PerformanceAttributeEntry[PerformanceEntry.ATTRIBUTES.length][8][24];
        this.m_lastTestAverage = new int[4];
        this.m_floatingTimeEntries = new PerformanceAttributeFloatingTimeEntry[PerformanceEntry.ATTRIBUTES.length];
        XMLUtil.assertNodeName(element, "PerformanceEntry");
        this.m_strCascadeId = XMLUtil.parseAttribute(element, "id", "");
        if (this.m_strCascadeId == "") {
            throw new XMLParseException("PerformanceEntry: invalid id");
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(element, "Data");
        if (firstChildByName == null) {
            throw new XMLParseException("PerformanceEntry: Could not find node Data");
        }
        this.m_current.setTime(new Date(System.currentTimeMillis()));
        this.m_floatingTimeEntries[1] = new PerformanceAttributeFloatingTimeEntry(1, XMLUtil.getFirstChildByName(firstChildByName, PerformanceEntry.ATTRIBUTES[1]));
        this.m_floatingTimeEntries[0] = new PerformanceAttributeFloatingTimeEntry(0, XMLUtil.getFirstChildByName(firstChildByName, PerformanceEntry.ATTRIBUTES[0]));
        final Node firstChildByName2 = XMLUtil.getFirstChildByName(firstChildByName, "Stability");
        if (firstChildByName2 != null) {
            this.m_stabilityAttributes = new StabilityAttributes((Element)firstChildByName2);
        }
        else {
            this.m_stabilityAttributes = new StabilityAttributes(0, 0, 0, 0);
        }
        this.m_lastUpdate = System.currentTimeMillis();
        this.m_serial = System.currentTimeMillis();
    }
    
    public String getId() {
        return this.m_strCascadeId;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    public long getLastTestTime() {
        return this.m_lastTestTime;
    }
    
    public PerformanceAttributeEntry importValue(final int n, final long n2, final int n3) {
        return this.addPerformanceAttributeEntry(n, n2, n3, true);
    }
    
    public PerformanceEntry update(final PerformanceEntry performanceEntry) {
        boolean b = false;
        if (!this.m_bPassive) {
            return null;
        }
        for (int i = 0; i < PerformanceEntry.ATTRIBUTES.length; ++i) {
            this.setBound(i, performanceEntry.getBound(i));
            this.setBestBound(i, performanceEntry.getBestBound(i));
            if ((i == 1 && this.getBound(i).getBound() > 0) || (i != 1 && this.getBound(i).getBound() >= 0)) {
                b = true;
            }
        }
        this.setStabilityAttributes(performanceEntry.getStabilityAttributes());
        if (b) {
            this.m_lastUpdate = System.currentTimeMillis();
        }
        return this;
    }
    
    public Vector updateHourlyPerformanceAttributeEntries(final long n) {
        if (!this.m_bPassive) {
            return null;
        }
        final Vector<PerformanceAttributeEntry> vector = new Vector<PerformanceAttributeEntry>();
        for (int i = 0; i < PerformanceEntry.ATTRIBUTES.length; ++i) {
            int notRecoveredBound = this.getBound(i).getNotRecoveredBound();
            if (i == 0 && notRecoveredBound == Integer.MAX_VALUE) {
                notRecoveredBound = -1;
            }
            else if (i == 1 && notRecoveredBound == 0) {
                notRecoveredBound = -1;
            }
            final PerformanceAttributeEntry addPerformanceAttributeEntry = this.addPerformanceAttributeEntry(i, n, notRecoveredBound, false);
            if (addPerformanceAttributeEntry != null) {
                vector.addElement(addPerformanceAttributeEntry);
            }
        }
        return vector;
    }
    
    private PerformanceAttributeEntry addPerformanceAttributeEntry(final int n, long time, final int n2, final boolean b) {
        synchronized (this.m_entries) {
            if (System.currentTimeMillis() - time >= 604800000L) {
                return null;
            }
            if (time > System.currentTimeMillis()) {
                LogHolder.log(4, LogType.MISC, "Performance timestamp has future value and is ignored: " + time + " , current: " + System.currentTimeMillis());
                return null;
            }
            final Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(time));
            int value = instance.get(7);
            int value2 = instance.get(11);
            if (this.m_bPassive) {
                if (value2 > 0) {
                    --value2;
                }
                else if (value == 1) {
                    value = 7;
                    value2 = 23;
                }
                else {
                    --value;
                    value2 = 23;
                }
                instance.set(11, value2);
                instance.set(7, value);
                time = instance.getTime().getTime();
            }
            for (int i = value2; i < 24; ++i) {
                if (this.m_entries[n][value][i] != null) {
                    if (System.currentTimeMillis() - this.m_entries[n][value][i].getDayTimestamp() > 86400000L) {
                        this.m_entries[n][value][i] = null;
                    }
                }
            }
            PerformanceAttributeEntry performanceAttributeEntry = this.m_entries[n][value][value2];
            if (performanceAttributeEntry == null) {
                performanceAttributeEntry = (this.m_entries[n][value][value2] = new PerformanceAttributeEntry(n, this.m_bPassive));
            }
            else if (this.m_bPassive) {
                return null;
            }
            PerformanceAttributeEntry performanceAttributeEntry2;
            if (value2 > 0) {
                performanceAttributeEntry2 = this.m_entries[n][value][value2 - 1];
            }
            else if (value == 1) {
                performanceAttributeEntry2 = this.m_entries[n][7][23];
            }
            else {
                performanceAttributeEntry2 = this.m_entries[n][value - 1][23];
            }
            performanceAttributeEntry.addValue(time, n2, performanceAttributeEntry2);
            if (b && !this.m_bPassive) {
                this.m_floatingTimeEntries[n].addValue(time, n2);
            }
            return performanceAttributeEntry;
        }
    }
    
    public int addData(final int n, final Hashtable hashtable) {
        if (hashtable.isEmpty()) {
            LogHolder.log(1, LogType.MISC, "Empty performance data!");
            return -1;
        }
        int n2 = 0;
        long lastTestTime = -1L;
        long lastUpdate = -1L;
        final Enumeration<Long> keys = hashtable.keys();
        final Vector<Long> vector = new Vector<Long>();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        Util.sort(vector, new Util.LongSortAsc());
        final Enumeration<Long> elements = vector.elements();
        int n3 = 0;
        while (elements.hasMoreElements()) {
            final long longValue = elements.nextElement();
            final int intValue = hashtable.get(new Long(longValue));
            if (this.addPerformanceAttributeEntry(n, longValue, intValue, false) == null) {
                continue;
            }
            lastUpdate = longValue;
            this.m_floatingTimeEntries[n].addValue(longValue, intValue);
            if (intValue > 0) {
                if (intValue >= Integer.MAX_VALUE) {
                    continue;
                }
                if (n2 < 0) {
                    n2 = 0;
                }
                n2 += intValue;
                ++n3;
                lastTestTime = longValue;
            }
            else {
                if (n3 != 0) {
                    continue;
                }
                if (n2 == 0) {
                    n2 = -1;
                }
                lastTestTime = longValue;
            }
        }
        if (n3 > 0) {
            n2 /= n3;
        }
        if (lastTestTime >= 0L) {
            this.m_lastTestTime = lastTestTime;
            this.m_lastTestAverage[n] = n2;
        }
        if (lastUpdate >= 0L) {
            this.m_lastUpdate = lastUpdate;
        }
        return n2;
    }
    
    public int getLastTestAverage(final int n) {
        return this.m_lastTestAverage[n];
    }
    
    public void setStabilityAttributes(final StabilityAttributes stabilityAttributes) {
        this.m_stabilityAttributes = stabilityAttributes;
    }
    
    public void setBound(final int n, final Bound bound) {
        this.m_floatingTimeEntries[n].setBound(bound);
    }
    
    public void setBestBound(final int n, final int bestBound) {
        this.m_floatingTimeEntries[n].setBestBound(bestBound);
    }
    
    public Bound getBound(final int n) {
        return this.m_floatingTimeEntries[n].getBound();
    }
    
    public StabilityAttributes getStabilityAttributes() {
        if (this.m_stabilityAttributes != null) {
            return this.m_stabilityAttributes;
        }
        final StabilityAttributes stabilityAttributes;
        synchronized (this.m_floatingTimeEntries[3].m_Values) {
            synchronized (this.m_floatingTimeEntries[0].m_Values) {
                stabilityAttributes = new StabilityAttributes(this.m_floatingTimeEntries[3].m_Values.size(), this.m_floatingTimeEntries[0].m_iUnknown, this.m_floatingTimeEntries[0].m_iErrors, this.m_floatingTimeEntries[3].m_iResets);
            }
        }
        return stabilityAttributes;
    }
    
    public int getBestBound(final int n) {
        return this.m_floatingTimeEntries[n].getBestBound();
    }
    
    public int getAverage(final int n) {
        return this.m_floatingTimeEntries[n].getAverage();
    }
    
    public String delayToHTML(final int n) {
        return this.toHTML(1, "ms", n);
    }
    
    public String speedToHTML(final int n) {
        return this.toHTML(0, "kbit/s", n);
    }
    
    public String usersToHTML(final int n) {
        return this.toHTML(2, "", n);
    }
    
    private long getDayTimestamp(final int n, final int n2) {
        long dayTimestamp = -1L;
        for (int i = 0; i < 24; ++i) {
            if (this.m_entries[n][n2][i] != null) {
                dayTimestamp = this.m_entries[n][n2][i].getDayTimestamp();
                if (dayTimestamp != -1L) {
                    break;
                }
            }
        }
        return dayTimestamp;
    }
    
    private String toHTML(final int n, final String s, final int n2) {
        final MixCascade mixCascade = (MixCascade)Database.getInstance((PerformanceEntry.class$anon$infoservice$MixCascade == null) ? (PerformanceEntry.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : PerformanceEntry.class$anon$infoservice$MixCascade).getEntryById(this.m_strCascadeId);
        String s2 = ((mixCascade != null) ? mixCascade.getName() : "") + "<h2>" + this.m_strCascadeId + "</h2>";
        this.m_current.setTime(new Date(System.currentTimeMillis()));
        final int value = this.m_current.get(7);
        final Calendar instance = Calendar.getInstance();
        instance.add(7, -6);
        for (int i = 1; i <= 7; ++i) {
            final String format = new SimpleDateFormat("E yyyy-MM-dd").format(instance.getTime());
            if (instance.get(7) == n2) {
                s2 = s2 + "<b> " + ((instance.get(7) == value) ? "Today</b> " : (format + "</b> | "));
            }
            else {
                s2 = s2 + "<a href=\"/values/" + PerformanceEntry.ATTRIBUTES[n].toLowerCase() + "/" + this.m_strCascadeId + "/" + instance.get(7) + "\">" + ((instance.get(7) == value) ? "Today</a> " : (format + "</a> | "));
            }
            instance.add(7, 1);
        }
        String s3 = s2 + "<br /><br /><table width=\"100%\"><tr><th width=\"16%\">Hour</th><th>Average</th><th>Min</th><th>Max</th><th>Bound</th><th>% Std. Deviation</th><th>Err/Try/Total</th><th>Resets</th></tr>";
        for (int j = 0; j < 24; ++j) {
            final String string = s3 + "<tr><td CLASS=\"name\">" + j + ":00 - " + (j + 1) % 24 + ":00</td>";
            final PerformanceAttributeEntry performanceAttributeEntry = this.m_entries[n][n2][j];
            long dayTimestamp = 0L;
            if (performanceAttributeEntry != null) {
                dayTimestamp = performanceAttributeEntry.getDayTimestamp();
            }
            String s4;
            if (performanceAttributeEntry == null || System.currentTimeMillis() - dayTimestamp >= 604800000L) {
                s4 = string + "<td colspan=\"7\" align=\"center\">No data available</td>";
            }
            else {
                final NumberFormat instance2 = NumberFormat.getInstance(Constants.LOCAL_FORMAT);
                instance2.setMaximumFractionDigits(2);
                instance2.setMinimumFractionDigits(2);
                String s5 = string + "<td>" + performanceAttributeEntry.getAverageValue() + " " + s + "</td>" + "<td>" + performanceAttributeEntry.getMinValue() + " " + s + "</td>" + "<td>" + performanceAttributeEntry.getMaxValue() + " " + s + "</td>" + "<td>";
                final int n3 = (performanceAttributeEntry == null) ? -1 : performanceAttributeEntry.getBound();
                if (n == 1) {
                    if (n3 == Integer.MAX_VALUE) {
                        s5 = s5 + "> " + PerformanceEntry.BOUNDARIES[1][PerformanceEntry.BOUNDARIES[1].length - 2];
                    }
                    else if (n3 <= 0) {
                        s5 += "?";
                    }
                    else {
                        s5 += n3;
                    }
                }
                else if (n == 0) {
                    if (n3 == 0) {
                        s5 = s5 + "< " + PerformanceEntry.BOUNDARIES[0][1];
                    }
                    else if (n3 < 0 || n3 == Integer.MAX_VALUE) {
                        s5 += "?";
                    }
                    else {
                        s5 += n3;
                    }
                }
                final String string2 = s5 + " " + s + "</td>";
                String s6;
                if (performanceAttributeEntry == null || performanceAttributeEntry.getStdDeviation() == -1.0 || performanceAttributeEntry.getAverageValue() == 0) {
                    s6 = string2 + "<td></td>";
                }
                else {
                    s6 = string2 + "<td>" + instance2.format(100.0 * performanceAttributeEntry.getStdDeviation() / performanceAttributeEntry.getAverageValue()) + " %</td>";
                }
                double n4 = 0.0;
                double n5 = 0.0;
                if (performanceAttributeEntry != null && performanceAttributeEntry.getValueSize() != 0) {
                    n4 = performanceAttributeEntry.getErrors() / performanceAttributeEntry.getValueSize() * 100.0;
                    n5 = performanceAttributeEntry.getUnknown() / performanceAttributeEntry.getValueSize() * 100.0;
                }
                final String string3 = s6 + "<td>" + performanceAttributeEntry.getErrors() + " / " + performanceAttributeEntry.getUnknown() + " / " + performanceAttributeEntry.getValueSize() + " (" + NumberFormat.getInstance(Constants.LOCAL_FORMAT).format(n4) + " % / " + NumberFormat.getInstance(Constants.LOCAL_FORMAT).format(n5) + " %)</td>";
                final PerformanceAttributeEntry performanceAttributeEntry2 = this.m_entries[3][n2][j];
                if (performanceAttributeEntry2 != null && performanceAttributeEntry2.getResets() > 0) {
                    s4 = string3 + "<td>" + performanceAttributeEntry2.getResets() + "</td>";
                }
                else {
                    s4 = string3 + "<td></td>";
                }
            }
            s3 = s4 + "</tr>";
        }
        return s3 + "</table>";
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("PerformanceEntry");
        XMLUtil.setAttribute(element, "id", this.getId());
        final Element element2 = document.createElement("Data");
        element2.appendChild(this.m_floatingTimeEntries[1].toXmlElement(document));
        element2.appendChild(this.m_floatingTimeEntries[0].toXmlElement(document));
        element2.appendChild(this.getStabilityAttributes().toXmlElement(document));
        element.appendChild(element2);
        return element;
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
        ATTRIBUTES = new String[] { "Speed", "Delay", "Users", "Packets" };
        BOUNDARIES = new int[][] { { 0, 50, 100, 200, 300, 400, 500, 750, 1000, 1500 }, { 500, 750, 1000, 2000, 2500, 3000, 4000, 8000, Integer.MAX_VALUE }, { 0 }, { 0 } };
    }
    
    private static class PerformanceAttributeFloatingTimeEntry implements IXMLEncodable
    {
        public static final long DEFAULT_TIMEFRAME = 3600000L;
        public static final String XML_ELEMENT_VALUES = "Values";
        public static final String XML_ELEMENT_VALUE = "Value";
        public static final String XML_ATTR_BEST = "best";
        public static final String XML_ATTR_BOUND = "bound";
        public static final String XML_ATTR_NOT_RECOVERED_BOUND = "notRecovered";
        private int m_iLastValue;
        private long m_iLastTimestamp;
        public int m_attribute;
        public long m_lastUpdate;
        private Hashtable m_Values;
        private Bound m_lBoundValue;
        private int m_lBestBoundValue;
        private int m_iResets;
        private int m_iErrors;
        private int m_iUnknown;
        private boolean m_bInfoService;
        
        public PerformanceAttributeFloatingTimeEntry(final int attribute, final boolean bInfoService) {
            this.m_iLastValue = -1;
            this.m_iLastTimestamp = -1L;
            this.m_Values = new Hashtable();
            this.m_lBoundValue = new Bound(-1, -1);
            this.m_lBestBoundValue = -1;
            this.m_iResets = 0;
            this.m_iErrors = 0;
            this.m_iUnknown = 0;
            this.m_attribute = attribute;
            this.m_bInfoService = bInfoService;
        }
        
        public PerformanceAttributeFloatingTimeEntry(final int attribute, final Node node) {
            this.m_iLastValue = -1;
            this.m_iLastTimestamp = -1L;
            this.m_Values = new Hashtable();
            this.m_lBoundValue = new Bound(-1, -1);
            this.m_lBestBoundValue = -1;
            this.m_iResets = 0;
            this.m_iErrors = 0;
            this.m_iUnknown = 0;
            this.m_attribute = attribute;
            this.m_bInfoService = false;
            final long attribute2 = XMLUtil.parseAttribute(node, "bound", -1L);
            int n;
            if (attribute2 > 2147483647L) {
                n = Integer.MAX_VALUE;
            }
            else {
                n = (int)attribute2;
            }
            final long attribute3 = XMLUtil.parseAttribute(node, "notRecovered", -1L);
            int n2;
            if (attribute3 > 2147483647L) {
                n2 = Integer.MAX_VALUE;
            }
            else {
                n2 = (int)attribute3;
            }
            this.m_lBoundValue = new Bound(n, n2);
            final long attribute4 = XMLUtil.parseAttribute(node, "best", -2L);
            if (attribute4 == -2L) {
                if (attribute == 0) {
                    this.m_lBestBoundValue = Integer.MAX_VALUE;
                }
                else {
                    this.m_lBestBoundValue = 0;
                }
            }
            else if (attribute4 > 2147483647L) {
                this.m_lBestBoundValue = Integer.MAX_VALUE;
            }
            else {
                this.m_lBestBoundValue = (int)attribute4;
            }
        }
        
        public void addValue(final long iLastTimestamp, int intValue) {
            if (System.currentTimeMillis() - iLastTimestamp > 3600000L) {
                return;
            }
            synchronized (this.m_Values) {
                final Vector<Long> vector = new Vector<Long>();
                boolean b = false;
                if (intValue < 0 || intValue == Integer.MAX_VALUE) {
                    if (intValue < 0) {
                        ++this.m_iErrors;
                    }
                    else if (intValue == Integer.MAX_VALUE) {
                        ++this.m_iUnknown;
                    }
                }
                else if (this.m_attribute == 3) {
                    if (this.m_iLastTimestamp < iLastTimestamp) {
                        if (intValue < this.m_iLastValue) {
                            ++this.m_iResets;
                            b = true;
                        }
                        this.m_iLastValue = intValue;
                        this.m_iLastTimestamp = iLastTimestamp;
                    }
                    else {
                        LogHolder.log(4, LogType.MISC, "Unordered timestamps for floating PACKETS. Timestamp new: " + iLastTimestamp + " Timestamp old: " + this.m_iLastTimestamp + " Value: " + intValue);
                    }
                    if (b) {
                        intValue = 1;
                    }
                    else {
                        intValue = 0;
                    }
                }
                this.m_Values.put(new Long(iLastTimestamp), new Integer(intValue));
                final Enumeration<Long> keys = this.m_Values.keys();
                while (keys.hasMoreElements()) {
                    final Long n = keys.nextElement();
                    if (System.currentTimeMillis() - n > 3600000L) {
                        vector.addElement(n);
                    }
                }
                for (int i = 0; i < vector.size(); ++i) {
                    intValue = (int)this.m_Values.get(vector.elementAt(i));
                    if (intValue < 0 || intValue == Integer.MAX_VALUE) {
                        if (intValue < 0) {
                            --this.m_iErrors;
                        }
                        else if (intValue == Integer.MAX_VALUE) {
                            --this.m_iUnknown;
                        }
                    }
                    else if (this.m_attribute == 3 && intValue == 1) {
                        --this.m_iResets;
                    }
                    this.m_Values.remove(vector.elementAt(i));
                }
                vector.removeAllElements();
            }
        }
        
        public void setBound(final Bound lBoundValue) {
            if (!this.m_bInfoService) {
                this.m_lBoundValue = lBoundValue;
            }
        }
        
        public void setBestBound(final int lBestBoundValue) {
            if (!this.m_bInfoService) {
                this.m_lBestBoundValue = lBestBoundValue;
            }
        }
        
        public Bound getBound() {
            if (!this.m_bInfoService) {
                return this.m_lBoundValue;
            }
            final Vector vector = new Vector<Object>();
            final Hashtable hashtable = (Hashtable)this.m_Values.clone();
            final int calculateBound = this.calculateBound(hashtable, vector);
            int max = Math.max((int)Math.floor(vector.size() * 0.1), 2);
            int calculateBound2 = calculateBound;
            Util.sort(vector, new Util.LongSortDesc());
            for (int i = 0; i < vector.size(); ++i) {
                final int intValue = hashtable.get(vector.elementAt(i));
                if (intValue < 0 || intValue == Integer.MAX_VALUE) {
                    ++max;
                }
                else {
                    int n = calculateBound2;
                    if (this.m_attribute == 1) {
                        int j = PerformanceEntry.BOUNDARIES[this.m_attribute].length - 1;
                        while (j >= 0) {
                            if (PerformanceEntry.BOUNDARIES[this.m_attribute][j] == calculateBound2) {
                                if (j > 0) {
                                    n = PerformanceEntry.BOUNDARIES[this.m_attribute][j - 1];
                                    break;
                                }
                                break;
                            }
                            else {
                                --j;
                            }
                        }
                    }
                    else {
                        int k = 0;
                        while (k < PerformanceEntry.BOUNDARIES[this.m_attribute].length) {
                            if (PerformanceEntry.BOUNDARIES[this.m_attribute][k] == calculateBound2) {
                                if (k + 1 < PerformanceEntry.BOUNDARIES[this.m_attribute].length) {
                                    n = PerformanceEntry.BOUNDARIES[this.m_attribute][k + 1];
                                    break;
                                }
                                break;
                            }
                            else {
                                ++k;
                            }
                        }
                    }
                    if (n != calculateBound2 && ((this.m_attribute == 0 && intValue < n) || (this.m_attribute == 1 && intValue > n))) {
                        if (i > max) {
                            for (int l = i; l < vector.size(); ++l) {
                                hashtable.remove(vector.elementAt(l));
                            }
                            vector.removeAllElements();
                            calculateBound2 = this.calculateBound(hashtable, vector);
                            break;
                        }
                        break;
                    }
                }
            }
            return new Bound(calculateBound2, calculateBound);
        }
        
        private int calculateBound(final Hashtable hashtable, final Vector vector) {
            int n = 0;
            long n2 = 0L;
            final Vector vector2 = new Vector<Integer>();
            final Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final Long n3 = keys.nextElement();
                if (System.currentTimeMillis() - n3 > 3600000L) {
                    continue;
                }
                vector.addElement(n3);
                final Integer n4 = hashtable.get(n3);
                if (n4 < 0) {
                    ++n2;
                }
                else {
                    if (n4 == Integer.MAX_VALUE) {
                        continue;
                    }
                    ++n;
                    vector2.addElement(n4);
                }
            }
            if (n == 0) {
                if (n2 > 0L) {
                    return -1;
                }
                if (this.m_attribute == 1) {
                    return 0;
                }
                return Integer.MAX_VALUE;
            }
            else {
                if (this.m_attribute == 1) {
                    Util.sort(vector2, new Util.IntegerSortDesc());
                }
                else {
                    Util.sort(vector2, new Util.IntegerSortAsc());
                }
                for (int n5 = (int)Math.floor(vector2.size() * 0.1), i = 0; i < n5; ++i) {
                    vector2.removeElementAt(0);
                }
                if (vector2.size() > 0) {
                    return this.getBoundFromValue(vector2.elementAt(0));
                }
                return -1;
            }
        }
        
        public int getBestBound() {
            if (!this.m_bInfoService) {
                return this.m_lBestBoundValue;
            }
            int n = 0;
            long n2 = 0L;
            int n3;
            if (this.m_attribute == 1) {
                n3 = Integer.MAX_VALUE;
            }
            else {
                n3 = 0;
            }
            synchronized (this.m_Values) {
                final Enumeration<Long> keys = (Enumeration<Long>)this.m_Values.keys();
                while (keys.hasMoreElements()) {
                    final Long n4 = keys.nextElement();
                    if (System.currentTimeMillis() - n4 > 3600000L) {
                        continue;
                    }
                    final Integer n5 = this.m_Values.get(n4);
                    if (n5 < 0) {
                        ++n2;
                    }
                    else {
                        if (n5 == Integer.MAX_VALUE) {
                            continue;
                        }
                        ++n;
                        if (this.m_attribute == 1) {
                            if (n5 >= n3) {
                                continue;
                            }
                            n3 = n5;
                        }
                        else {
                            if (n5 <= n3) {
                                continue;
                            }
                            n3 = n5;
                        }
                    }
                }
            }
            if (n != 0) {
                return this.getBoundFromValue(n3);
            }
            if (n2 > 0L) {
                return -1;
            }
            if (this.m_attribute == 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }
        
        private int getBoundFromValue(final int n) {
            if (this.m_attribute == 1) {
                for (int i = 0; i < PerformanceEntry.BOUNDARIES[this.m_attribute].length; ++i) {
                    if (n <= PerformanceEntry.BOUNDARIES[this.m_attribute][i]) {
                        return PerformanceEntry.BOUNDARIES[this.m_attribute][i];
                    }
                }
                return PerformanceEntry.BOUNDARIES[this.m_attribute][PerformanceEntry.BOUNDARIES[this.m_attribute].length - 1];
            }
            for (int j = PerformanceEntry.BOUNDARIES[this.m_attribute].length - 1; j >= 0; --j) {
                if (n >= PerformanceEntry.BOUNDARIES[this.m_attribute][j]) {
                    return PerformanceEntry.BOUNDARIES[this.m_attribute][j];
                }
            }
            return PerformanceEntry.BOUNDARIES[this.m_attribute][0];
        }
        
        public int getAverage() {
            if (!this.m_bInfoService) {
                return -1;
            }
            int n = 0;
            int n2 = 0;
            long n3 = 0L;
            synchronized (this.m_Values) {
                final Enumeration<Long> keys = this.m_Values.keys();
                while (keys.hasMoreElements()) {
                    final Long n4 = keys.nextElement();
                    if (System.currentTimeMillis() - n4 > 3600000L) {
                        continue;
                    }
                    final int intValue = this.m_Values.get(n4);
                    if (intValue < 0) {
                        ++n3;
                    }
                    else {
                        if (intValue == Integer.MAX_VALUE) {
                            continue;
                        }
                        ++n;
                        n2 += intValue;
                    }
                }
            }
            if (n3 > 0L && n == 0) {
                return -1;
            }
            if (n == 0) {
                return 0;
            }
            return n2 / n;
        }
        
        public double getStdDeviation() {
            if (!this.m_bInfoService) {
                return -1.0;
            }
            int n = 0;
            long n2 = 0L;
            long n3 = 0L;
            synchronized (this.m_Values) {
                final Enumeration<Long> keys = this.m_Values.keys();
                while (keys.hasMoreElements()) {
                    final Long n4 = keys.nextElement();
                    if (System.currentTimeMillis() - n4 > 3600000L) {
                        continue;
                    }
                    final int intValue = this.m_Values.get(n4);
                    if (intValue < 0) {
                        ++n2;
                    }
                    else {
                        if (intValue == Integer.MAX_VALUE) {
                            continue;
                        }
                        ++n;
                        n3 += (long)Math.pow(intValue - this.getAverage(), 2.0);
                    }
                }
            }
            if (n2 > 0L && n == 0) {
                return -1.0;
            }
            if (n == 0) {
                return 0.0;
            }
            return Math.sqrt(n3 / n);
        }
        
        public Element toXmlElement(final Document document) {
            final Element element = document.createElement(PerformanceEntry.ATTRIBUTES[this.m_attribute]);
            final Bound bound = this.getBound();
            XMLUtil.setAttribute(element, "bound", bound.getBound());
            XMLUtil.setAttribute(element, "notRecovered", bound.getNotRecoveredBound());
            XMLUtil.setAttribute(element, "best", this.getBestBound());
            return element;
        }
    }
    
    public static class PerformanceAttributeEntry
    {
        private int m_iLastValue;
        private long m_iLastTimestamp;
        private int m_lMaxValue;
        private int m_iMinValue;
        private int m_lAverageValue;
        private int m_lBound;
        private double m_lStdDeviation;
        private long m_lastUpdate;
        private Hashtable m_Values;
        private int m_iErrors;
        private int m_iResets;
        private int m_iUnknown;
        private int m_iSuccess;
        private int m_attribute;
        private boolean m_bPassive;
        
        private PerformanceAttributeEntry(final int attribute, final boolean bPassive) {
            this.m_iLastValue = -1;
            this.m_iLastTimestamp = -1L;
            this.m_lMaxValue = -1;
            this.m_iMinValue = -1;
            this.m_lAverageValue = -1;
            this.m_lBound = -1;
            this.m_lStdDeviation = 0.0;
            this.m_lastUpdate = -1L;
            this.m_Values = new Hashtable();
            this.m_iErrors = 0;
            this.m_iResets = 0;
            this.m_iUnknown = 0;
            this.m_iSuccess = 0;
            this.m_attribute = attribute;
            this.m_bPassive = bPassive;
        }
        
        private void addValue(final long n, final int n2, final PerformanceAttributeEntry performanceAttributeEntry) {
            if (System.currentTimeMillis() - n >= 604800000L) {
                return;
            }
            this.m_lastUpdate = n;
            if (n2 < 0 || (!this.m_bPassive && n2 == Integer.MAX_VALUE)) {
                if (n2 < 0) {
                    ++this.m_iErrors;
                    if (n2 < -1) {
                        LogHolder.log(4, LogType.MISC, "Got negative performance value (" + n2 + ") for timestamp " + n + ".");
                    }
                }
                else if (n2 == Integer.MAX_VALUE) {
                    ++this.m_iUnknown;
                }
                if (this.m_Values.size() == 0) {
                    this.m_lAverageValue = -1;
                    this.m_iMinValue = -1;
                    this.m_lMaxValue = -1;
                    this.m_lStdDeviation = -1.0;
                    this.m_lBound = -1;
                }
                return;
            }
            this.m_Values.put(new Long(n), new Integer(n2));
            ++this.m_iSuccess;
            if (this.m_attribute == 3) {
                if (this.m_iLastTimestamp < 0L && performanceAttributeEntry != null) {
                    this.m_iLastTimestamp = performanceAttributeEntry.m_iLastTimestamp;
                    this.m_iLastValue = performanceAttributeEntry.m_iLastValue;
                }
                if (this.m_iLastTimestamp < n) {
                    if (n2 < this.m_iLastValue) {
                        ++this.m_iResets;
                    }
                    this.m_iLastValue = n2;
                    this.m_iLastTimestamp = n;
                }
                else {
                    LogHolder.log(4, LogType.MISC, "Unordered timestamps for hourly attribute " + this.m_attribute + "." + "Timestamp new: " + n + " Timestamp old: " + this.m_iLastTimestamp + " Value: " + n2);
                }
            }
            int n3 = 0;
            double n4 = 0.0;
            synchronized (this.m_Values) {
                final Enumeration<Integer> elements = this.m_Values.elements();
                while (elements.hasMoreElements()) {
                    n3 += elements.nextElement();
                }
                this.m_lAverageValue = n3 / this.m_Values.size();
                final Enumeration<Integer> elements2 = this.m_Values.elements();
                while (elements2.hasMoreElements()) {
                    n4 += Math.pow(elements2.nextElement() - this.m_lAverageValue, 2.0);
                }
                n4 /= this.m_Values.size();
            }
            this.m_lStdDeviation = Math.sqrt(n4);
            if (n4 < 0.0) {
                LogHolder.log(0, LogType.MISC, "Negative mean square error! " + n4);
            }
            if (n2 < 0) {
                LogHolder.log(3, LogType.MISC, "Negative attribute value! " + n2);
            }
            if (this.m_iMinValue == 0 || this.m_iMinValue == -1) {
                this.m_iMinValue = n2;
            }
            else {
                this.m_iMinValue = Math.min(this.m_iMinValue, n2);
            }
            this.m_lMaxValue = Math.max(this.m_lMaxValue, n2);
            final Vector vector = new Vector<Integer>();
            synchronized (this.m_Values) {
                final Enumeration<Integer> elements3 = this.m_Values.elements();
                while (elements3.hasMoreElements()) {
                    final Integer n5 = elements3.nextElement();
                    if (n5 >= 0) {
                        vector.addElement(n5);
                    }
                }
            }
            if (this.m_attribute == 0) {
                Util.sort(vector, new Util.IntegerSortAsc());
            }
            else {
                Util.sort(vector, new Util.IntegerSortDesc());
            }
            for (int n6 = (int)Math.floor(vector.size() * 0.1), n7 = 0; n7 < n6 && vector.size() > 1; ++n7) {
                vector.removeElementAt(0);
            }
            if (vector.size() <= 0) {
                this.m_lBound = -1;
                return;
            }
            final int intValue = vector.elementAt(0);
            if (this.m_attribute == 0) {
                for (int i = PerformanceEntry.BOUNDARIES[this.m_attribute].length - 1; i >= 0; --i) {
                    if (intValue >= PerformanceEntry.BOUNDARIES[this.m_attribute][i]) {
                        this.m_lBound = PerformanceEntry.BOUNDARIES[this.m_attribute][i];
                        return;
                    }
                }
                this.m_lBound = PerformanceEntry.BOUNDARIES[this.m_attribute][0];
                return;
            }
            for (int j = 0; j < PerformanceEntry.BOUNDARIES[this.m_attribute].length; ++j) {
                if (intValue <= PerformanceEntry.BOUNDARIES[this.m_attribute][j]) {
                    this.m_lBound = PerformanceEntry.BOUNDARIES[this.m_attribute][j];
                    return;
                }
            }
            this.m_lBound = PerformanceEntry.BOUNDARIES[this.m_attribute][PerformanceEntry.BOUNDARIES[this.m_attribute].length - 1];
        }
        
        public int getAverageValue() {
            return this.m_lAverageValue;
        }
        
        public int getMinValue() {
            return this.m_iMinValue;
        }
        
        public int getMaxValue() {
            return this.m_lMaxValue;
        }
        
        public int getBound() {
            return this.m_lBound;
        }
        
        public double getStdDeviation() {
            return this.m_lStdDeviation;
        }
        
        public void setErrors(final int iErrors) {
            this.m_iErrors = iErrors;
        }
        
        public int getErrors() {
            return this.m_iErrors;
        }
        
        public void setResets(final int iResets) {
            this.m_iResets = iResets;
        }
        
        public int getResets() {
            return this.m_iResets;
        }
        
        public int getUnknown() {
            return this.m_iUnknown;
        }
        
        public void setUnknown(final int iUnknown) {
            this.m_iUnknown = iUnknown;
        }
        
        public void setSuccess(final int iSuccess) {
            this.m_iSuccess = iSuccess;
        }
        
        public int getSuccess() {
            return this.m_iSuccess;
        }
        
        public int getValueSize() {
            return this.getSuccess() + this.m_iErrors + this.m_iUnknown;
        }
        
        public long getDayTimestamp() {
            final Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(this.m_lastUpdate));
            return this.m_lastUpdate - instance.get(11) * 60 * 60 * 1000 - instance.get(12) * 60 * 1000 - instance.get(13) * 1000 - instance.get(14);
        }
    }
    
    public static class StabilityAttributes
    {
        public static final String XML_ELEMENT_NAME = "Stability";
        private static final String XML_ATTR_TOTAL = "total";
        private static final String XML_ATTR_UNKNOWN = "unknown";
        private static final String XML_ATTR_ERRORS = "errors";
        private static final String XML_ATTR_RESETS = "resets";
        private static final String XML_ATTR_BOUND_UNKNOWN = "boundUnknown";
        private static final String XML_ATTR_BOUND_ERRORS = "boundErrors";
        private static final String XML_ATTR_BOUND_RESETS = "boundResets";
        private static final double BOUND = 5.0;
        private int m_iSize;
        private int m_iErrors;
        private int m_iResets;
        private int m_iUnknown;
        private int m_boundUnknown;
        private int m_boundErrors;
        private int m_boundResets;
        
        private StabilityAttributes(final Element element) throws XMLParseException {
            XMLUtil.assertNodeName(element, "Stability");
            this.m_iSize = XMLUtil.parseAttribute(element, "total", 0);
            this.m_iUnknown = XMLUtil.parseAttribute(element, "unknown", 0);
            this.m_iErrors = XMLUtil.parseAttribute(element, "errors", 0);
            this.m_iResets = XMLUtil.parseAttribute(element, "resets", 0);
            this.m_boundUnknown = XMLUtil.parseAttribute(element, "boundUnknown", 0);
            this.m_boundErrors = XMLUtil.parseAttribute(element, "boundErrors", 0);
            this.m_iResets = XMLUtil.parseAttribute(element, "boundResets", 0);
        }
        
        public StabilityAttributes(final int iSize, final int iUnknown, final int iErrors, final int iResets) {
            this.m_iSize = iSize;
            this.m_iUnknown = iUnknown;
            this.m_iErrors = iErrors;
            this.m_iResets = iResets;
            if (iSize == 0) {
                this.m_boundUnknown = 0;
                this.m_boundErrors = 0;
                this.m_boundResets = 0;
                return;
            }
            final double n = 100.0 * this.m_iUnknown / this.m_iSize;
            final double n2 = 100.0 * iErrors / this.m_iSize;
            this.m_boundUnknown = (int)Math.ceil(n / 5.0) * 5;
            this.m_boundErrors = (int)Math.ceil(n2 / 5.0) * 5;
            this.m_boundResets = (int)Math.ceil(100.0 * iResets / iSize / 5.0) * 5;
        }
        
        public int getBoundErrors() {
            return this.m_boundErrors;
        }
        
        public int getBoundResets() {
            return this.m_boundResets;
        }
        
        public int getBoundUnknown() {
            return this.m_boundUnknown;
        }
        
        public int getValueSize() {
            return this.m_iSize;
        }
        
        public Element toXmlElement(final Document document) {
            final Element element = document.createElement("Stability");
            XMLUtil.setAttribute(element, "total", this.m_iSize);
            XMLUtil.setAttribute(element, "unknown", this.m_iUnknown);
            XMLUtil.setAttribute(element, "errors", this.m_iErrors);
            XMLUtil.setAttribute(element, "resets", this.m_iResets);
            XMLUtil.setAttribute(element, "boundUnknown", this.m_boundUnknown);
            XMLUtil.setAttribute(element, "boundErrors", this.m_boundErrors);
            XMLUtil.setAttribute(element, "boundResets", this.m_boundResets);
            return element;
        }
    }
    
    public static class Bound
    {
        private int m_bound;
        private int m_nonRecoveredBound;
        
        public Bound(final int bound, final int nonRecoveredBound) {
            this.m_bound = bound;
            this.m_nonRecoveredBound = nonRecoveredBound;
        }
        
        public int getBound() {
            return this.m_bound;
        }
        
        public int getNotRecoveredBound() {
            return this.m_nonRecoveredBound;
        }
    }
}
