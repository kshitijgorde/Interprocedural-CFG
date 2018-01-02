import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

class LPParser implements Enumeration
{
    static final String PROP_ROOTTITLE = "ROOT";
    static final String VALUE_BREAK = "=";
    static final String NODE_BREAK = "\u0002";
    static final String PROPERTY_BREAK = "\u0003";
    static final String HEADER = "HR";
    static final String PROP_SYNC = "SYNC";
    private String m_szReceiveBuffer;
    private String m_szRootTitle;
    private String m_szSyncPath;
    private Vector m_Nodes;
    private int m_lIndex;
    
    public Object nextElement() {
        final Object element = this.m_Nodes.elementAt(this.m_lIndex);
        if (this.m_lIndex < this.m_Nodes.size()) {
            ++this.m_lIndex;
        }
        return element;
    }
    
    public int getNodeCount() {
        return this.m_Nodes.size();
    }
    
    public Enumeration enumerate() {
        return this;
    }
    
    public LPParser(final int n, final String s) {
        this.m_szReceiveBuffer = "";
        this.m_szRootTitle = "";
        this.m_szSyncPath = "";
        this.m_szReceiveBuffer = new String(s);
        this.m_Nodes = new Vector(n, 5);
        this.m_lIndex = 0;
    }
    
    public boolean hasMoreElements() {
        return this.m_lIndex < this.m_Nodes.size();
    }
    
    public void processServerResponce() {
        if (this.m_szReceiveBuffer != null && this.m_szReceiveBuffer.length() > 0) {
            this.parseHeader();
            for (String s = this.getNextNodeData(); s != null && s.length() > 0; s = this.getNextNodeData()) {
                final Properties properties = new Properties();
                this.parseInput(s, properties);
                this.m_Nodes.addElement(properties);
            }
        }
    }
    
    String getNextNodeData() {
        TocDebug.TraceL3("GCThread::getNextNodeData");
        String string = new String("");
        int n;
        for (n = 0; n < this.m_szReceiveBuffer.length() - 1 && this.m_szReceiveBuffer.charAt(n) != "\u0002".charAt(0); ++n) {
            string += String.valueOf(this.m_szReceiveBuffer.charAt(n));
        }
        if (this.m_szReceiveBuffer.length() > 1) {
            this.m_szReceiveBuffer = new String(this.m_szReceiveBuffer.substring(n + 1));
        }
        return string;
    }
    
    private int parseInput(final String s, final Properties properties) {
        TocDebug.TraceL3("GCThread::parseInput");
        String string = "";
        String string2 = "";
        int n = 0;
        int length = s.length();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == "=".charAt(0)) {
                if (n == 0) {
                    ++i;
                    n = 1;
                }
            }
            else if (s.charAt(i) == "\u0003".charAt(0)) {
                ((Hashtable<String, String>)properties).put(string, string2);
                string2 = (string = "");
                n = 0;
                ++i;
            }
            else if (s.charAt(i) == "\u0002".charAt(0)) {
                length = i;
                break;
            }
            if (n == 0) {
                string += String.valueOf(s.charAt(i));
            }
            else {
                string2 += String.valueOf(s.charAt(i));
            }
        }
        ((Hashtable<String, String>)properties).put(string, string2);
        return length;
    }
    
    public String getRootTitle() {
        return new String(this.m_szRootTitle);
    }
    
    private void parseHeader() {
        TocDebug.TraceL3("GCThread::parseHeader");
        final Properties properties = new Properties();
        if (this.m_szReceiveBuffer.substring(0, "HR".length()).compareTo("HR") == 0) {
            final int input = this.parseInput(this.m_szReceiveBuffer, properties);
            if (properties.size() == 0) {
                return;
            }
            final String property = properties.getProperty("SYNC");
            if (property != null) {
                this.m_szSyncPath = property;
                if (this.m_szSyncPath.charAt(0) != '/') {
                    this.m_szSyncPath = new String("/").concat(this.m_szSyncPath);
                }
            }
            this.m_szRootTitle = properties.getProperty("ROOT");
            this.m_szReceiveBuffer = new String(this.m_szReceiveBuffer.substring(input + 1));
        }
    }
    
    public String getSyncPath() {
        return new String(this.m_szSyncPath);
    }
}
