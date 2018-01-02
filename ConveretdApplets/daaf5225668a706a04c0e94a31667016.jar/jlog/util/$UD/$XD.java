// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$UD;

import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Enumeration;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.awt.Font;
import java.awt.Color;
import java.util.Properties;

public class $XD extends Properties
{
    public Properties $PE;
    Properties $QE;
    Properties $YD;
    boolean $DE;
    
    public void $AF(final String s, final boolean b) {
        this.setProperty(s, String.valueOf(b));
    }
    
    public Color $BF(final String s) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return new Color(this.$VE(s, property));
        }
        throw new $VD("missing property " + s + "=<#RGBColor>");
    }
    
    public Color $BF(final String s, final Color color) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return new Color(this.$VE(s, property));
        }
        return color;
    }
    
    public void $CF(final String s, final Color color) {
        this.setProperty(s, "#" + Integer.toString(color.getRGB(), 16));
    }
    
    public Font $DF(final String s) throws $VD {
        final Font $df = this.$DF(s, null);
        if ($df != null) {
            return $df;
        }
        throw new $VD("missing property " + s + "=<#RGBColor>");
    }
    
    public Font $DF(final String s, final Font font) {
        final String property = this.getProperty(s);
        if (property == null || property.length() == 0) {
            return font;
        }
        int n = 0;
        int int1 = 12;
        final int index = property.indexOf(45);
        String substring;
        if (index == -1) {
            substring = property;
        }
        else {
            substring = property.substring(0, index);
            final String substring2 = property.substring(index + 1);
            final int index2 = substring2.indexOf(45);
            String substring3;
            if (index2 != -1) {
                substring3 = substring2.substring(0, index2);
            }
            else {
                substring3 = substring2;
            }
            if (substring3.equals("bold")) {
                n = 1;
            }
            else if (substring3.equals("italic")) {
                n = 2;
            }
            else if (substring3.equals("bolditalic")) {
                n = 3;
            }
            if (index2 != -1) {
                int1 = Integer.parseInt(substring2.substring(index2 + 1));
            }
        }
        return new Font(substring, n, int1);
    }
    
    public void $EE(final InputStream inputStream) throws IOException {
        this.$YD.load(new BufferedInputStream(inputStream));
    }
    
    public Properties $GF() {
        return this.$YD;
    }
    
    public Properties $HF() {
        return this.$QE;
    }
    
    public void $IE(final String s, final String s2) {
        ((Hashtable<String, String>)this.$QE).put(s, s2);
    }
    
    public void $IF(final boolean $de) {
        this.$DE = $de;
    }
    
    public boolean $KE() {
        return this.$DE;
    }
    
    public String $RE(final String s) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return property;
        }
        throw new $VD("missing property " + s + "=<string>");
    }
    
    int $VE(final String s, final String s2) throws $VD {
        try {
            if (s2.startsWith("#")) {
                return Integer.parseInt(s2.substring(1), 16);
            }
            return Integer.parseInt(s2);
        }
        catch (NumberFormatException ex) {
            throw new $VD("property " + s + " is not a valid int value: " + s2);
        }
    }
    
    public void $WE(final String s, final int n) {
        this.setProperty(s, String.valueOf(n));
    }
    
    float $XE(final String s, final String s2) throws $VD {
        try {
            return Float.valueOf(s2);
        }
        catch (NumberFormatException ex) {
            throw new $VD("property " + s + " is not a valid float value: " + s2);
        }
    }
    
    public float $YE(final String s) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return this.$XE(s, property);
        }
        throw new $VD("missing property " + s + "=<floatValue>");
    }
    
    public float $YE(final String s, final float n) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return this.$XE(s, property);
        }
        throw new IllegalArgumentException("missing property " + s);
    }
    
    public boolean $ZE(final String s) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return Boolean.valueOf(property);
        }
        throw new $VD("missing property " + s + "=<\"true\" | \"false\">");
    }
    
    public boolean $ZE(final String s, final boolean b) {
        final String property = this.getProperty(s);
        if (property != null) {
            return Boolean.valueOf(property);
        }
        return b;
    }
    
    public $XD() {
        this(null);
    }
    
    public $XD(final Properties properties) {
        this.$PE = null;
        this.$DE = false;
        this.$YD = new Properties(properties);
        this.$QE = new Properties(this);
    }
    
    public Object get(final Object o) {
        try {
            return this.getProperty((String)o);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int getIntProperty(final String s) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return this.$VE(s, property);
        }
        throw new $VD("missing property " + s + "=<intValue>");
    }
    
    public int getIntProperty(final String s, final int n) throws $VD {
        final String property = this.getProperty(s);
        if (property != null) {
            return this.$VE(s, property);
        }
        return n;
    }
    
    public String getProperty(final String s) {
        return this.getProperty(s, null);
    }
    
    public String getProperty(final String s, final String s2) {
        String s3 = ((Hashtable<K, String>)this.$QE).get(s);
        if (s3 == null) {
            s3 = super.get(s);
        }
        if (s3 == null) {
            s3 = this.$YD.getProperty(s);
        }
        if (s3 == null) {
            final Properties $pe = this.$PE;
            if ($pe != null) {
                s3 = $pe.getProperty(s);
            }
        }
        return (s3 == null) ? s2 : s3;
    }
    
    public Enumeration propertyNames() {
        final Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>(this.$YD.size() + this.size());
        final Object o = new Object();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)this.$QE).keys();
        while (keys.hasMoreElements()) {
            hashtable.put(keys.nextElement(), o);
        }
        final Enumeration<Object> keys2 = ((Hashtable<Object, V>)this).keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), o);
        }
        final Enumeration<?> propertyNames = this.$YD.propertyNames();
        while (propertyNames.hasMoreElements()) {
            hashtable.put(propertyNames.nextElement(), o);
        }
        return hashtable.keys();
    }
    
    public Object put(final Object o, final Object o2) {
        final Object put = super.put(o, o2);
        this.$DE |= (o2.equals(put) ^ true);
        this.$QE.remove(o);
        return put;
    }
    
    public Object remove(final String s) {
        final Object remove = super.remove(s);
        this.$DE |= (remove != null);
        this.$QE.remove(s);
        return remove;
    }
    
    public void save(final OutputStream outputStream, final String s) {
        super.save(outputStream, s);
        this.$DE = false;
    }
    
    public void setFloatProperty(final String s, final float n) {
        this.setProperty(s, String.valueOf(n));
    }
    
    public void setFontProperty(final String s, final Font font) {
        if (font == null) {
            this.remove(s);
        }
        else {
            this.setProperty(s, String.valueOf(font.getName()) + "-" + (font.isBold() ? "bold" : "") + (font.isItalic() ? "italic" : "") + "-" + font.getSize());
        }
    }
    
    public Object setProperty(final String s, final String s2) {
        return this.put(s, s2);
    }
}
