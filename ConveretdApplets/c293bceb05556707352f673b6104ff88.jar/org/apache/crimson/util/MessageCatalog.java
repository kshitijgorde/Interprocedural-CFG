// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.util;

import java.io.InputStream;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Hashtable;

public abstract class MessageCatalog
{
    private String bundleName;
    private Hashtable cache;
    
    protected MessageCatalog(final Class packageMember) {
        this(packageMember, "Messages");
    }
    
    private MessageCatalog(final Class packageMember, final String bundle) {
        this.cache = new Hashtable(5);
        this.bundleName = packageMember.getName();
        final int index = this.bundleName.lastIndexOf(46);
        if (index == -1) {
            this.bundleName = "";
        }
        else {
            this.bundleName = this.bundleName.substring(0, index) + ".";
        }
        this.bundleName = this.bundleName + "resources." + bundle;
    }
    
    public String getMessage(Locale locale, final String messageId) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle(this.bundleName, locale);
            return bundle.getString(messageId);
        }
        catch (MissingResourceException e) {
            return this.packagePrefix(messageId);
        }
    }
    
    private String packagePrefix(final String messageId) {
        String temp = this.getClass().getName();
        final int index = temp.lastIndexOf(46);
        if (index == -1) {
            temp = "";
        }
        else {
            temp = temp.substring(0, index);
        }
        return temp + '/' + messageId;
    }
    
    public String getMessage(Locale locale, final String messageId, final Object[] parameters) {
        if (parameters == null) {
            return this.getMessage(locale, messageId);
        }
        for (int i = 0; i < parameters.length; ++i) {
            if (!(parameters[i] instanceof String) && !(parameters[i] instanceof Number) && !(parameters[i] instanceof Date)) {
                if (parameters[i] == null) {
                    parameters[i] = "(null)";
                }
                else {
                    parameters[i] = parameters[i].toString();
                }
            }
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MessageFormat format;
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle(this.bundleName, locale);
            format = new MessageFormat(bundle.getString(messageId));
        }
        catch (MissingResourceException e) {
            String retval = this.packagePrefix(messageId);
            for (int j = 0; j < parameters.length; ++j) {
                retval += ' ';
                retval += parameters[j];
            }
            return retval;
        }
        format.setLocale(locale);
        StringBuffer result = new StringBuffer();
        result = format.format(parameters, result, new FieldPosition(0));
        return result.toString();
    }
    
    public Locale chooseLocale(String[] languages) {
        if ((languages = this.canonicalize(languages)) != null) {
            for (int i = 0; i < languages.length; ++i) {
                if (this.isLocaleSupported(languages[i])) {
                    return this.getLocale(languages[i]);
                }
            }
        }
        return null;
    }
    
    private String[] canonicalize(String[] languages) {
        boolean didClone = false;
        int trimCount = 0;
        if (languages == null) {
            return languages;
        }
        for (int i = 0; i < languages.length; ++i) {
            String lang = languages[i];
            final int len = lang.length();
            if (len != 2 && len != 5) {
                if (!didClone) {
                    languages = languages.clone();
                    didClone = true;
                }
                languages[i] = null;
                ++trimCount;
            }
            else if (len == 2) {
                lang = lang.toLowerCase();
                if (lang != languages[i]) {
                    if (!didClone) {
                        languages = languages.clone();
                        didClone = true;
                    }
                    languages[i] = lang;
                }
            }
            else {
                final char[] buf = { Character.toLowerCase(lang.charAt(0)), Character.toLowerCase(lang.charAt(1)), '_', Character.toUpperCase(lang.charAt(3)), Character.toUpperCase(lang.charAt(4)) };
                if (!didClone) {
                    languages = languages.clone();
                    didClone = true;
                }
                languages[i] = new String(buf);
            }
        }
        if (trimCount != 0) {
            final String[] temp = new String[languages.length - trimCount];
            int j = 0;
            trimCount = 0;
            while (j < temp.length) {
                while (languages[j + trimCount] == null) {
                    ++trimCount;
                }
                temp[j] = languages[j + trimCount];
                ++j;
            }
            languages = temp;
        }
        return languages;
    }
    
    private Locale getLocale(final String localeName) {
        final int index = localeName.indexOf(95);
        String language;
        String country;
        if (index == -1) {
            if (localeName.equals("de")) {
                return Locale.GERMAN;
            }
            if (localeName.equals("en")) {
                return Locale.ENGLISH;
            }
            if (localeName.equals("fr")) {
                return Locale.FRENCH;
            }
            if (localeName.equals("it")) {
                return Locale.ITALIAN;
            }
            if (localeName.equals("ja")) {
                return Locale.JAPANESE;
            }
            if (localeName.equals("ko")) {
                return Locale.KOREAN;
            }
            if (localeName.equals("zh")) {
                return Locale.CHINESE;
            }
            language = localeName;
            country = "";
        }
        else {
            if (localeName.equals("zh_CN")) {
                return Locale.SIMPLIFIED_CHINESE;
            }
            if (localeName.equals("zh_TW")) {
                return Locale.TRADITIONAL_CHINESE;
            }
            language = localeName.substring(0, index);
            country = localeName.substring(index + 1);
        }
        return new Locale(language, country);
    }
    
    public boolean isLocaleSupported(String localeName) {
        final Boolean value = this.cache.get(localeName);
        if (value != null) {
            return value;
        }
        ClassLoader loader = null;
        while (true) {
            String name = this.bundleName + "_" + localeName;
            try {
                Class.forName(name);
                this.cache.put(localeName, Boolean.TRUE);
                return true;
            }
            catch (Exception e) {
                if (loader == null) {
                    loader = this.getClass().getClassLoader();
                }
                name = name.replace('.', '/');
                name += ".properties";
                InputStream in;
                if (loader == null) {
                    in = ClassLoader.getSystemResourceAsStream(name);
                }
                else {
                    in = loader.getResourceAsStream(name);
                }
                if (in != null) {
                    this.cache.put(localeName, Boolean.TRUE);
                    return true;
                }
                final int index = localeName.indexOf(95);
                if (index <= 0) {
                    this.cache.put(localeName, Boolean.FALSE);
                    return false;
                }
                localeName = localeName.substring(0, index);
            }
        }
    }
}
