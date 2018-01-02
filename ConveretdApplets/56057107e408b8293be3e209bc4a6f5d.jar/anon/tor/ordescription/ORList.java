// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import anon.tor.util.Base16;
import anon.util.Base64;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import logging.LogHolder;
import logging.LogType;
import java.text.DateFormat;
import java.util.Date;
import anon.crypto.MyRandom;
import java.util.Hashtable;
import java.util.Vector;

public final class ORList
{
    private Vector m_onionrouters;
    private Vector m_exitnodes;
    private Vector m_middlenodes;
    private Hashtable m_onionroutersWithNames;
    private MyRandom m_rand;
    private ORListFetcher m_orlistFetcher;
    private Date m_datePublished;
    private int m_countHibernate;
    private static final DateFormat ms_DateFormat;
    
    public ORList(final ORListFetcher orlistFetcher) {
        this.m_onionrouters = new Vector();
        this.m_exitnodes = new Vector();
        this.m_middlenodes = new Vector();
        this.m_onionroutersWithNames = new Hashtable();
        this.m_orlistFetcher = orlistFetcher;
        this.m_countHibernate = 0;
        this.m_rand = new MyRandom();
    }
    
    public synchronized int size() {
        return this.m_onionrouters.size();
    }
    
    public synchronized int active() {
        return this.size() - this.m_countHibernate;
    }
    
    public synchronized void setFetcher(final ORListFetcher orlistFetcher) {
        this.m_orlistFetcher = orlistFetcher;
    }
    
    public synchronized boolean updateList() {
        try {
            final byte[] routerStatus;
            if (this.size() == 0 || (routerStatus = this.m_orlistFetcher.getRouterStatus()) == null) {
                final byte[] allDescriptors = this.m_orlistFetcher.getAllDescriptors();
                return allDescriptors != null && this.parseFirstDocument(allDescriptors);
            }
            return this.parseStatus(routerStatus, true);
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.TOR, "There was a problem with fetching the available ORRouters: " + t.getMessage());
            return false;
        }
    }
    
    public Vector getList() {
        return (Vector)this.m_onionrouters.clone();
    }
    
    public Date getPublished() {
        return this.m_datePublished;
    }
    
    public synchronized ORDescriptor getByName(final String s) {
        return this.m_onionroutersWithNames.get(s);
    }
    
    public synchronized void remove(final String s) {
        final ORDescriptor byName = this.getByName(s);
        if (byName == null) {
            return;
        }
        this.m_onionrouters.removeElement(byName);
        if (byName.isExitNode()) {
            this.m_exitnodes.removeElement(byName);
        }
        else {
            this.m_middlenodes.removeElement(byName);
        }
        this.m_onionroutersWithNames.remove(s);
    }
    
    public synchronized void add(final ORDescriptor orDescriptor) {
        if (orDescriptor.isExitNode()) {
            this.m_exitnodes.addElement(orDescriptor);
        }
        else {
            this.m_middlenodes.addElement(orDescriptor);
        }
        this.m_onionrouters.addElement(orDescriptor);
        this.m_onionroutersWithNames.put(orDescriptor.getName(), orDescriptor);
        LogHolder.log(7, LogType.TOR, "Added: " + orDescriptor);
    }
    
    public synchronized ORDescriptor getByRandom(final Vector vector) {
        if (this.active() == 0) {
            return null;
        }
        ORDescriptor byName;
        do {
            byName = this.getByName(vector.elementAt(this.m_rand.nextInt(vector.size())));
            if (byName == null) {
                return null;
            }
        } while (byName.getHibernate());
        return byName;
    }
    
    public synchronized ORDescriptor getByRandom() {
        if (this.active() == 0) {
            return null;
        }
        ORDescriptor orDescriptor;
        do {
            orDescriptor = this.m_onionrouters.elementAt(this.m_rand.nextInt(this.m_onionrouters.size()));
        } while (orDescriptor.getHibernate());
        return orDescriptor;
    }
    
    public synchronized ORDescriptor getByRandom(final int n) {
        if (this.active() == 0) {
            return null;
        }
        final int size = this.m_onionrouters.size();
        final int n2 = n * this.m_exitnodes.size() - size;
        final int n3 = (n - 1) * size * 2;
        ORDescriptor orDescriptor;
        do {
            if (this.m_rand.nextInt(n3) > n2) {
                orDescriptor = this.m_middlenodes.elementAt(this.m_rand.nextInt(this.m_middlenodes.size()));
            }
            else {
                orDescriptor = this.m_exitnodes.elementAt(this.m_rand.nextInt(this.m_exitnodes.size()));
            }
        } while (orDescriptor.getHibernate());
        return orDescriptor;
    }
    
    public synchronized ORDescriptor getORDescriptor(final String s) {
        if (this.m_onionroutersWithNames.containsKey(s)) {
            return this.m_onionroutersWithNames.get(s);
        }
        return null;
    }
    
    private boolean parseStatus(final byte[] array, final boolean b) throws Exception {
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new ByteArrayInputStream(array)));
        final String line = lineNumberReader.readLine();
        boolean b2 = false;
        if (line == null || !line.startsWith("network-status-version")) {
            return false;
        }
        while (true) {
            lineNumberReader.mark(200);
            final String line2 = lineNumberReader.readLine();
            if (line2 == null) {
                break;
            }
            if (line2.startsWith("published")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line2, " ");
                stringTokenizer.nextToken();
                ORList.ms_DateFormat.parse(stringTokenizer.nextToken() + " " + stringTokenizer.nextToken());
            }
            else {
                if (!line2.startsWith("r ")) {
                    continue;
                }
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line2, " ");
                stringTokenizer2.nextToken();
                final String nextToken = stringTokenizer2.nextToken();
                new StringBuffer().append(stringTokenizer2.nextToken()).append("=").toString();
                final String string = stringTokenizer2.nextToken() + "=";
                new StringBuffer().append(stringTokenizer2.nextToken()).append(" ").append(stringTokenizer2.nextToken()).toString();
                stringTokenizer2.nextToken();
                final Vector<String> vector = new Vector<String>();
                Integer.parseInt(stringTokenizer2.nextToken());
                lineNumberReader.mark(200);
                final String line3 = lineNumberReader.readLine();
                if (!line3.startsWith("s ")) {
                    lineNumberReader.reset();
                }
                else {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(line3);
                    stringTokenizer3.nextToken();
                    while (stringTokenizer3.hasMoreTokens()) {
                        vector.addElement(stringTokenizer3.nextToken());
                    }
                }
                final String line4 = lineNumberReader.readLine();
                if (line4.startsWith("v ")) {
                    line4.substring(2);
                }
                else if (line4.startsWith("opt v ")) {
                    line4.substring(6);
                }
                else {
                    lineNumberReader.reset();
                }
                final ORDescriptor orDescriptor = this.getORDescriptor(nextToken);
                final String encode = Base16.encode(Base64.decode(string));
                if (orDescriptor != null && orDescriptor.getHash() != null && encode.equals(orDescriptor.getHash())) {
                    continue;
                }
                final byte[] descriptor = this.m_orlistFetcher.getDescriptor(encode);
                if (descriptor == null) {
                    continue;
                }
                if (orDescriptor != null && orDescriptor.getHibernate()) {
                    b2 = true;
                }
                this.remove(nextToken);
                final ORDescriptor parse = ORDescriptor.parse(new LineNumberReader(new InputStreamReader(new ByteArrayInputStream(descriptor))));
                parse.setHash(encode);
                if (b2 && !parse.getHibernate()) {
                    --this.m_countHibernate;
                }
                this.add(parse);
            }
        }
        return true;
    }
    
    private synchronized boolean parseFirstDocument(final byte[] array) throws Exception {
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new ByteArrayInputStream(array)));
        final Date datePublished = new Date();
        lineNumberReader.mark(200);
        String s = lineNumberReader.readLine();
        if (s == null) {
            return false;
        }
        this.m_countHibernate = 0;
        this.m_onionrouters = new Vector();
        this.m_exitnodes = new Vector();
        this.m_middlenodes = new Vector();
        this.m_onionroutersWithNames = new Hashtable();
        do {
            if (s.startsWith("router ")) {
                lineNumberReader.reset();
                final ORDescriptor parse = ORDescriptor.parse(lineNumberReader);
                if (parse != null) {
                    if (parse.getHibernate()) {
                        ++this.m_countHibernate;
                    }
                    this.add(parse);
                }
            }
            lineNumberReader.mark(200);
            s = lineNumberReader.readLine();
            if (s == null) {
                break;
            }
        } while (s != null);
        LogHolder.log(7, LogType.TOR, "Exit Nodes : " + this.m_exitnodes.size() + " Non-Exit Nodes : " + this.m_middlenodes.size());
        this.m_datePublished = datePublished;
        return true;
    }
    
    static {
        (ms_DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
