// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.io.InputStream;
import java.io.BufferedInputStream;
import org.apache.xml.utils.FastStringBuffer;
import java.util.Vector;
import java.io.IOException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xalan.serialize.Encodings;
import org.apache.xml.utils.QName;
import java.util.Enumeration;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import java.util.Properties;
import java.util.Hashtable;

public class OutputProperties extends ElemTemplateElement implements Cloneable
{
    static final String S_XSLT_PREFIX = "xslt.output.";
    static final int S_XSLT_PREFIX_LEN;
    static final String S_XALAN_PREFIX = "org.apache.xslt.";
    static final int S_XALAN_PREFIX_LEN;
    static final String S_BUILTIN_EXTENSIONS_UNIVERSAL = "{http://xml.apache.org/xslt}";
    private transient Hashtable m_propertiesLevels;
    private Properties m_properties;
    public static String S_KEY_INDENT_AMOUNT;
    public static String S_KEY_CONTENT_HANDLER;
    public static String S_KEY_ENTITIES;
    public static String S_USE_URL_ESCAPING;
    private static Properties m_xml_properties;
    private static Properties m_html_properties;
    private static Properties m_text_properties;
    private static Integer m_synch_object;
    static /* synthetic */ Class class$org$apache$xalan$templates$OutputProperties;
    
    static {
        S_XSLT_PREFIX_LEN = "xslt.output.".length();
        S_XALAN_PREFIX_LEN = "org.apache.xslt.".length();
        OutputProperties.S_KEY_INDENT_AMOUNT = "{http://xml.apache.org/xslt}indent-amount";
        OutputProperties.S_KEY_CONTENT_HANDLER = "{http://xml.apache.org/xslt}content-handler";
        OutputProperties.S_KEY_ENTITIES = "{http://xml.apache.org/xslt}entities";
        OutputProperties.S_USE_URL_ESCAPING = "{http://xml.apache.org/xslt}use-url-escaping";
        OutputProperties.m_xml_properties = null;
        OutputProperties.m_html_properties = null;
        OutputProperties.m_text_properties = null;
        OutputProperties.m_synch_object = new Integer(1);
    }
    
    public OutputProperties() {
        this("xml");
    }
    
    public OutputProperties(final String method) {
        this.m_properties = null;
        this.m_properties = new Properties(getDefaultMethodProperties(method));
    }
    
    public OutputProperties(final Properties defaults) {
        this.m_properties = null;
        this.m_properties = new Properties(defaults);
    }
    
    private void checkDuplicates(final OutputProperties newProps) throws TransformerException {
        if (this.m_propertiesLevels == null) {
            this.m_propertiesLevels = new Hashtable();
        }
        final int newPrecedence = newProps.getStylesheetComposed().getImportCountComposed();
        final Properties p = newProps.getProperties();
        final Enumeration enum1 = p.keys();
        while (enum1.hasMoreElements()) {
            final String key = enum1.nextElement();
            if (!key.equals("cdata-section-elements")) {
                final Integer oldPrecedence = this.m_propertiesLevels.get(key);
                if (oldPrecedence == null) {
                    this.m_propertiesLevels.put(key, new Integer(newPrecedence));
                }
                else {
                    if (newPrecedence < oldPrecedence) {
                        continue;
                    }
                    final String oldValue = ((Hashtable<K, String>)this.m_properties).get(key);
                    final String newValue = ((Hashtable<K, String>)newProps.m_properties).get(key);
                    if ((oldValue == null && newValue != null) || !oldValue.equals(newValue)) {
                        final String msg = String.valueOf(key) + " can not be multiply defined at the same " + "import level! Old value = " + oldValue + "; New value = " + newValue;
                        throw new TransformerException(msg, newProps);
                    }
                    continue;
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public Object clone() {
        try {
            final OutputProperties cloned = (OutputProperties)super.clone();
            cloned.m_properties = (Properties)cloned.m_properties.clone();
            return cloned;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void compose() {
        super.compose();
        this.m_propertiesLevels = null;
    }
    
    public void copyFrom(final Properties src) {
        this.copyFrom(src, true);
    }
    
    public void copyFrom(final Properties src, final boolean shouldResetDefaults) {
        final Enumeration enum1 = src.keys();
        while (enum1.hasMoreElements()) {
            final String key = enum1.nextElement();
            final Object oldValue = ((Hashtable<K, Object>)this.m_properties).get(key);
            if (oldValue == null) {
                final String val = ((Hashtable<K, String>)src).get(key);
                if (shouldResetDefaults && key.equals("method")) {
                    this.setMethodDefaults(val);
                }
                ((Hashtable<String, String>)this.m_properties).put(key, val);
            }
            else {
                if (!key.equals("cdata-section-elements")) {
                    continue;
                }
                ((Hashtable<String, String>)this.m_properties).put(key, String.valueOf(oldValue) + " " + ((Hashtable<K, String>)src).get(key));
            }
        }
    }
    
    public void copyFrom(final OutputProperties opsrc) throws TransformerException {
        this.checkDuplicates(opsrc);
        this.copyFrom(opsrc.getProperties());
    }
    
    private static String fixupPropertyString(String s, final boolean doClipping) {
        if (doClipping && s.startsWith("xslt.output.")) {
            s = s.substring(OutputProperties.S_XSLT_PREFIX_LEN);
        }
        if (s.startsWith("org.apache.xslt.")) {
            s = "{http://xml.apache.org/xslt}" + s.substring(OutputProperties.S_XALAN_PREFIX_LEN);
        }
        final int index;
        if ((index = s.indexOf("\\u003a")) > 0) {
            final String temp = s.substring(index + 6);
            s = String.valueOf(s.substring(0, index)) + ":" + temp;
        }
        return s;
    }
    
    public boolean getBooleanProperty(final String key) {
        return getBooleanProperty(key, this.m_properties);
    }
    
    public static boolean getBooleanProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        return s != null && s.equals("yes");
    }
    
    public boolean getBooleanProperty(final QName key) {
        return this.getBooleanProperty(key.toNamespacedString());
    }
    
    public static Properties getDefaultMethodProperties(final String method) {
        Properties defaultProperties = null;
        try {
            if (OutputProperties.m_xml_properties == null) {
                synchronized (OutputProperties.m_synch_object) {
                    if (OutputProperties.m_xml_properties == null) {
                        OutputProperties.m_xml_properties = loadPropertiesFile("output_xml.properties", null);
                    }
                }
                // monitorexit(OutputProperties.m_synch_object)
            }
            if (method.equals("xml")) {
                defaultProperties = OutputProperties.m_xml_properties;
            }
            else if (method.equals("html")) {
                if (OutputProperties.m_html_properties == null) {
                    synchronized (OutputProperties.m_synch_object) {
                        if (OutputProperties.m_html_properties == null) {
                            OutputProperties.m_html_properties = loadPropertiesFile("output_html.properties", OutputProperties.m_xml_properties);
                        }
                    }
                    // monitorexit(OutputProperties.m_synch_object)
                }
                defaultProperties = OutputProperties.m_html_properties;
            }
            else if (method.equals("text")) {
                if (OutputProperties.m_text_properties == null) {
                    synchronized (OutputProperties.m_synch_object) {
                        if (OutputProperties.m_text_properties == null) {
                            OutputProperties.m_text_properties = loadPropertiesFile("output_text.properties", null);
                            if (OutputProperties.m_text_properties.getProperty("encoding") == null) {
                                final String mimeEncoding = Encodings.getMimeEncoding(null);
                                ((Hashtable<String, String>)OutputProperties.m_text_properties).put("encoding", mimeEncoding);
                            }
                        }
                    }
                    // monitorexit(OutputProperties.m_synch_object)
                }
                defaultProperties = OutputProperties.m_text_properties;
            }
            else {
                defaultProperties = OutputProperties.m_xml_properties;
            }
        }
        catch (IOException ioe) {
            throw new WrappedRuntimeException(ioe);
        }
        return defaultProperties;
    }
    
    public int getIntProperty(final String key) {
        return getIntProperty(key, this.m_properties);
    }
    
    public static int getIntProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (s == null) {
            return 0;
        }
        return Integer.parseInt(s);
    }
    
    public int getIntProperty(final QName key) {
        return this.getIntProperty(key.toNamespacedString());
    }
    
    public Properties getProperties() {
        return this.m_properties;
    }
    
    public String getProperty(final String key) {
        return this.m_properties.getProperty(key);
    }
    
    public String getProperty(final QName key) {
        return this.m_properties.getProperty(key.toNamespacedString());
    }
    
    public Vector getQNameProperties(final String key) {
        return getQNameProperties(key, this.m_properties);
    }
    
    public static Vector getQNameProperties(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (s != null) {
            final Vector v = new Vector();
            final int l = s.length();
            boolean inCurly = false;
            final FastStringBuffer buf = new FastStringBuffer();
            for (int i = 0; i < l; ++i) {
                final char c = s.charAt(i);
                if (Character.isWhitespace(c)) {
                    if (!inCurly) {
                        if (buf.length() > 0) {
                            final QName qname = QName.getQNameFromString(buf.toString());
                            v.addElement(qname);
                            buf.reset();
                        }
                        continue;
                    }
                }
                else if (c == '{') {
                    inCurly = true;
                }
                else if (c == '}') {
                    inCurly = false;
                }
                buf.append(c);
            }
            if (buf.length() > 0) {
                final QName qname2 = QName.getQNameFromString(buf.toString());
                v.addElement(qname2);
                buf.reset();
            }
            return v;
        }
        return null;
    }
    
    public Vector getQNameProperties(final QName key) {
        return this.getQNameProperties(key.toNamespacedString());
    }
    
    public QName getQNameProperty(final String key) {
        return getQNameProperty(key, this.m_properties);
    }
    
    public static QName getQNameProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (s != null) {
            return QName.getQNameFromString(s);
        }
        return null;
    }
    
    public QName getQNameProperty(final QName key) {
        return this.getQNameProperty(key.toNamespacedString());
    }
    
    public boolean isLegalPropertyKey(final String key) {
        return key.equals("cdata-section-elements") || key.equals("doctype-public") || key.equals("doctype-system") || key.equals("encoding") || key.equals("indent") || key.equals("media-type") || key.equals("method") || key.equals("omit-xml-declaration") || key.equals("standalone") || key.equals("version") || (key.length() > 0 && key.charAt(0) == '{');
    }
    
    private static Properties loadPropertiesFile(final String resourceName, final Properties defaults) throws IOException {
        final Properties props = new Properties(defaults);
        final InputStream is = ((OutputProperties.class$org$apache$xalan$templates$OutputProperties != null) ? OutputProperties.class$org$apache$xalan$templates$OutputProperties : (OutputProperties.class$org$apache$xalan$templates$OutputProperties = class$("org.apache.xalan.templates.OutputProperties"))).getResourceAsStream(resourceName);
        final BufferedInputStream bis = new BufferedInputStream(is);
        props.load(bis);
        final Enumeration keys = props.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            String value;
            if ((value = System.getProperty(key)) == null) {
                value = ((Hashtable<K, String>)props).get(key);
            }
            final String newKey = fixupPropertyString(key, true);
            String newValue;
            if ((newValue = System.getProperty(newKey)) == null) {
                newValue = fixupPropertyString(value, false);
            }
            else {
                newValue = fixupPropertyString(newValue, false);
            }
            if (key != newKey || value != newValue) {
                props.remove(key);
                ((Hashtable<String, String>)props).put(newKey, newValue);
            }
        }
        return props;
    }
    
    public void recompose(final StylesheetRoot root) throws TransformerException {
        root.recomposeOutput(this);
    }
    
    public void setBooleanProperty(final String key, final boolean value) {
        ((Hashtable<String, String>)this.m_properties).put(key, value ? "yes" : "no");
    }
    
    public void setBooleanProperty(final QName key, final boolean value) {
        ((Hashtable<String, String>)this.m_properties).put(key.toNamespacedString(), value ? "yes" : "no");
    }
    
    public void setIntProperty(final String key, final int value) {
        ((Hashtable<String, String>)this.m_properties).put(key, Integer.toString(value));
    }
    
    public void setIntProperty(final QName key, final int value) {
        this.setIntProperty(key.toNamespacedString(), value);
    }
    
    public void setMethodDefaults(final String method) {
        final String defaultMethod = this.m_properties.getProperty("method");
        if (defaultMethod == null || !defaultMethod.equals(method)) {
            final Properties savedProps = this.m_properties;
            final Properties newDefaults = getDefaultMethodProperties(method);
            this.m_properties = new Properties(newDefaults);
            this.copyFrom(savedProps, false);
        }
    }
    
    public void setProperty(final String key, final String value) {
        if (key.equals("method")) {
            this.setMethodDefaults(value);
        }
        ((Hashtable<String, String>)this.m_properties).put(key, value);
    }
    
    public void setProperty(final QName key, final String value) {
        this.setProperty(key.toNamespacedString(), value);
    }
    
    public void setQNameProperties(final String key, final Vector v) {
        final int s = v.size();
        final FastStringBuffer fsb = new FastStringBuffer();
        for (int i = 0; i < s; ++i) {
            final QName qname = v.elementAt(i);
            fsb.append(qname.toNamespacedString());
            if (i < s - 1) {
                fsb.append(' ');
            }
        }
        ((Hashtable<String, String>)this.m_properties).put(key, fsb.toString());
    }
    
    public void setQNameProperties(final QName key, final Vector v) {
        this.setQNameProperties(key.toNamespacedString(), v);
    }
    
    public void setQNameProperty(final String key, final QName value) {
        this.setProperty(key, value.toNamespacedString());
    }
    
    public void setQNameProperty(final QName key, final QName value) {
        this.setQNameProperty(key.toNamespacedString(), value);
    }
}
