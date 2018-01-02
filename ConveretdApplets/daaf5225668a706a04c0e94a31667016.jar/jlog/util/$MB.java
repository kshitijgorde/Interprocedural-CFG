// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util;

import java.util.Locale;
import java.util.Vector;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class $MB
{
    MessageFormat format;
    ResourceBundle $H;
    Vector v;
    
    public void $NB(final $F $f) {
        if (this.v == null) {
            synchronized (this) {
                if (this.v == null) {
                    this.v = new Vector();
                }
            }
        }
        this.v.addElement($f);
    }
    
    public void $OB(final $F $f) {
        if (this.v != null) {
            this.v.removeElement($f);
        }
    }
    
    void $PB() {
        if (this.v != null) {
            final Vector vector = (Vector)this.v.clone();
            final ResourceBundle $h = this.$H;
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).$G($h);
            }
        }
    }
    
    public void $QB(final ResourceBundle $h) {
        if ($h != this.$H) {
            this.$H = $h;
            this.$PB();
        }
    }
    
    public ResourceBundle $RB() {
        return this.$H;
    }
    
    public static ResourceBundle $SB(final String s, final Locale locale) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final Vector<String> vector = new Vector<String>();
        $VB(vector, s, locale);
        if (!locale.equals(Locale.getDefault())) {
            $VB(vector, s, Locale.getDefault());
        }
        vector.addElement(s);
        int i = 0;
        while (i < vector.size()) {
            try {
                return (ResourceBundle)Class.forName(vector.elementAt(i)).newInstance();
            }
            catch (ClassNotFoundException ex) {
                ++i;
            }
        }
        throw new ClassNotFoundException(s);
    }
    
    private static void $VB(final Vector vector, final String s, final Locale locale) {
        final String language = locale.getLanguage();
        String string;
        if (language != null && language.length() != 0) {
            string = "_" + language;
        }
        else {
            string = "";
        }
        final String variant = locale.getVariant();
        String string2;
        if (variant != null && variant.length() != 0) {
            string2 = "_" + variant;
        }
        else {
            string2 = "";
        }
        final String country = locale.getCountry();
        String string3;
        if (country != null && country.length() != 0) {
            string3 = "_" + country;
        }
        else {
            string3 = "";
        }
        if (string2.length() != 0) {
            vector.addElement(String.valueOf(s) + string + string3 + string2);
        }
        if (string3.length() != 0) {
            vector.addElement(String.valueOf(s) + string + string3);
        }
        if (string.length() != 0) {
            vector.addElement(String.valueOf(s) + string);
        }
    }
    
    public $MB() {
        this(null);
    }
    
    public $MB(final ResourceBundle $h) {
        this.format = null;
        this.$H = null;
        this.v = null;
        this.format = new MessageFormat("");
        this.$H = $h;
    }
    
    public String getMessage(final String s, final int n) {
        return this.getMessage(s, new Integer(n));
    }
    
    public String getMessage(final String s, final Object o) {
        return this.getMessage(s, new Object[] { o });
    }
    
    public String getMessage(final String s, final Object[] array) {
        String s2 = s;
        if (this.$H != null) {
            try {
                s2 = this.getString(s);
                if (array != null && array.length != 0) {
                    if (s.equals(s2)) {
                        for (int i = 0; i < array.length; ++i) {
                            s2 = String.valueOf(s2) + "\n<" + array[i] + ">";
                        }
                    }
                    else {
                        synchronized (this.format) {
                            this.format.applyPattern(s2);
                            s2 = this.format.format(array);
                        }
                        // monitorexit(this.format)
                    }
                }
            }
            catch (Exception ex) {
                if (array != null && array.length != 0) {
                    for (int j = 0; j < array.length; ++j) {
                        s2 = String.valueOf(s2) + "\n<" + array[j] + ">";
                    }
                }
            }
        }
        return s2;
    }
    
    public String getString(final String s) {
        String string = s;
        try {
            final ResourceBundle $h = this.$H;
            if ($h != null) {
                string = $h.getString(s);
            }
        }
        catch (Exception ex) {}
        return string;
    }
}
