// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.Hashtable;
import java.util.Enumeration;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.FastStringBuffer;
import java.util.Vector;
import org.apache.xml.serializer.OutputPropertyUtils;
import org.apache.xml.utils.QName;
import org.apache.xml.serializer.OutputPropertiesFactory;
import java.util.Properties;

public class OutputProperties extends ElemTemplateElement implements Cloneable
{
    private Properties m_properties;
    
    public OutputProperties() {
        this("xml");
    }
    
    public OutputProperties(final Properties defaults) {
        this.m_properties = null;
        this.m_properties = new Properties(defaults);
    }
    
    public OutputProperties(final String method) {
        this.m_properties = null;
        this.m_properties = new Properties(OutputPropertiesFactory.getDefaultMethodProperties(method));
    }
    
    public Object clone() {
        try {
            final OutputProperties cloned = (OutputProperties)super.clone();
            cloned.m_properties = (Properties)cloned.m_properties.clone();
            return cloned;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public void setProperty(final QName key, final String value) {
        this.setProperty(key.toNamespacedString(), value);
    }
    
    public void setProperty(String key, final String value) {
        if (key.equals("method")) {
            this.setMethodDefaults(value);
        }
        if (key.startsWith("{http://xml.apache.org/xslt}")) {
            key = "{http://xml.apache.org/xalan}" + key.substring(OutputPropertiesFactory.S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL_LEN);
        }
        ((Hashtable<String, String>)this.m_properties).put(key, value);
    }
    
    public String getProperty(final QName key) {
        return this.m_properties.getProperty(key.toNamespacedString());
    }
    
    public String getProperty(String key) {
        if (key.startsWith("{http://xml.apache.org/xslt}")) {
            key = "{http://xml.apache.org/xalan}" + key.substring(OutputPropertiesFactory.S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL_LEN);
        }
        return this.m_properties.getProperty(key);
    }
    
    public void setBooleanProperty(final QName key, final boolean value) {
        ((Hashtable<String, String>)this.m_properties).put(key.toNamespacedString(), value ? "yes" : "no");
    }
    
    public void setBooleanProperty(final String key, final boolean value) {
        ((Hashtable<String, String>)this.m_properties).put(key, value ? "yes" : "no");
    }
    
    public boolean getBooleanProperty(final QName key) {
        return this.getBooleanProperty(key.toNamespacedString());
    }
    
    public boolean getBooleanProperty(final String key) {
        return OutputPropertyUtils.getBooleanProperty(key, this.m_properties);
    }
    
    public void setIntProperty(final QName key, final int value) {
        this.setIntProperty(key.toNamespacedString(), value);
    }
    
    public void setIntProperty(final String key, final int value) {
        ((Hashtable<String, String>)this.m_properties).put(key, Integer.toString(value));
    }
    
    public int getIntProperty(final QName key) {
        return this.getIntProperty(key.toNamespacedString());
    }
    
    public int getIntProperty(final String key) {
        return OutputPropertyUtils.getIntProperty(key, this.m_properties);
    }
    
    public void setQNameProperty(final QName key, final QName value) {
        this.setQNameProperty(key.toNamespacedString(), value);
    }
    
    public void setMethodDefaults(final String method) {
        final String defaultMethod = this.m_properties.getProperty("method");
        if (null == defaultMethod || !defaultMethod.equals(method) || defaultMethod.equals("xml")) {
            final Properties savedProps = this.m_properties;
            final Properties newDefaults = OutputPropertiesFactory.getDefaultMethodProperties(method);
            this.m_properties = new Properties(newDefaults);
            this.copyFrom(savedProps, false);
        }
    }
    
    public void setQNameProperty(final String key, final QName value) {
        this.setProperty(key, value.toNamespacedString());
    }
    
    public QName getQNameProperty(final QName key) {
        return this.getQNameProperty(key.toNamespacedString());
    }
    
    public QName getQNameProperty(final String key) {
        return getQNameProperty(key, this.m_properties);
    }
    
    public static QName getQNameProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (null != s) {
            return QName.getQNameFromString(s);
        }
        return null;
    }
    
    public void setQNameProperties(final QName key, final Vector v) {
        this.setQNameProperties(key.toNamespacedString(), v);
    }
    
    public void setQNameProperties(final String key, final Vector v) {
        final int s = v.size();
        final FastStringBuffer fsb = new FastStringBuffer(9, 9);
        for (int i = 0; i < s; ++i) {
            final QName qname = v.elementAt(i);
            fsb.append(qname.toNamespacedString());
            if (i < s - 1) {
                fsb.append(' ');
            }
        }
        ((Hashtable<String, String>)this.m_properties).put(key, fsb.toString());
    }
    
    public Vector getQNameProperties(final QName key) {
        return this.getQNameProperties(key.toNamespacedString());
    }
    
    public Vector getQNameProperties(final String key) {
        return getQNameProperties(key, this.m_properties);
    }
    
    public static Vector getQNameProperties(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (null != s) {
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
                else if ('{' == c) {
                    inCurly = true;
                }
                else if ('}' == c) {
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
    
    public void recompose(final StylesheetRoot root) throws TransformerException {
        root.recomposeOutput(this);
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
    }
    
    public Properties getProperties() {
        return this.m_properties;
    }
    
    public void copyFrom(final Properties src) {
        this.copyFrom(src, true);
    }
    
    public void copyFrom(final Properties src, final boolean shouldResetDefaults) {
        final Enumeration keys = src.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            if (!isLegalPropertyKey(key)) {
                throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { key }));
            }
            final Object oldValue = ((Hashtable<K, Object>)this.m_properties).get(key);
            if (null == oldValue) {
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
                ((Hashtable<String, String>)this.m_properties).put(key, (String)oldValue + " " + ((Hashtable<K, String>)src).get(key));
            }
        }
    }
    
    public void copyFrom(final OutputProperties opsrc) throws TransformerException {
        this.copyFrom(opsrc.getProperties());
    }
    
    public static boolean isLegalPropertyKey(final String key) {
        return key.equals("cdata-section-elements") || key.equals("doctype-public") || key.equals("doctype-system") || key.equals("encoding") || key.equals("indent") || key.equals("media-type") || key.equals("method") || key.equals("omit-xml-declaration") || key.equals("standalone") || key.equals("version") || (key.length() > 0 && key.charAt(0) == '{' && key.lastIndexOf(123) == 0 && key.indexOf(125) > 0 && key.lastIndexOf(125) == key.indexOf(125));
    }
    
    public static Properties getDefaultMethodProperties(final String method) {
        return OutputPropertiesFactory.getDefaultMethodProperties(method);
    }
}
