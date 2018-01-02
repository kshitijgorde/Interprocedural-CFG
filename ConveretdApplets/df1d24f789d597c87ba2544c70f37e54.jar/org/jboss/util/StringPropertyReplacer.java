// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.File;
import java.util.Properties;

public final class StringPropertyReplacer
{
    public static final String NEWLINE;
    private static final String FILE_SEPARATOR;
    private static final String PATH_SEPARATOR;
    private static final String FILE_SEPARATOR_ALIAS = "/";
    private static final String PATH_SEPARATOR_ALIAS = ":";
    private static final int NORMAL = 0;
    private static final int SEEN_DOLLAR = 1;
    private static final int IN_BRACKET = 2;
    
    public static String replaceProperties(final String string) {
        return replaceProperties(string, null);
    }
    
    public static String replaceProperties(final String string, final Properties props) {
        final char[] chars = string.toCharArray();
        final StringBuffer buffer = new StringBuffer();
        boolean properties = false;
        int state = 0;
        int start = 0;
        for (int i = 0; i < chars.length; ++i) {
            final char c = chars[i];
            if (c == '$' && state != 2) {
                state = 1;
            }
            else if (c == '{' && state == 1) {
                buffer.append(string.substring(start, i - 1));
                state = 2;
                start = i - 1;
            }
            else if (state == 1) {
                state = 0;
            }
            else if (c == '}' && state == 2) {
                if (start + 2 == i) {
                    buffer.append("${}");
                }
                else {
                    String value = null;
                    final String key = string.substring(start + 2, i);
                    if ("/".equals(key)) {
                        value = StringPropertyReplacer.FILE_SEPARATOR;
                    }
                    else if (":".equals(key)) {
                        value = StringPropertyReplacer.PATH_SEPARATOR;
                    }
                    else {
                        if (props != null) {
                            value = props.getProperty(key);
                        }
                        else {
                            value = System.getProperty(key);
                        }
                        if (value == null) {
                            final int colon = key.indexOf(58);
                            if (colon > 0) {
                                final String realKey = key.substring(0, colon);
                                if (props != null) {
                                    value = props.getProperty(realKey);
                                }
                                else {
                                    value = System.getProperty(realKey);
                                }
                                if (value == null) {
                                    value = resolveCompositeKey(realKey, props);
                                    if (value == null) {
                                        value = key.substring(colon + 1);
                                    }
                                }
                            }
                            else {
                                value = resolveCompositeKey(key, props);
                            }
                        }
                    }
                    if (value != null) {
                        properties = true;
                        buffer.append(value);
                    }
                }
                start = i + 1;
                state = 0;
            }
        }
        if (!properties) {
            return string;
        }
        if (start != chars.length) {
            buffer.append(string.substring(start, chars.length));
        }
        return buffer.toString();
    }
    
    private static String resolveCompositeKey(final String key, final Properties props) {
        String value = null;
        final int comma = key.indexOf(44);
        if (comma > -1) {
            if (comma > 0) {
                final String key2 = key.substring(0, comma);
                if (props != null) {
                    value = props.getProperty(key2);
                }
                else {
                    value = System.getProperty(key2);
                }
            }
            if (value == null && comma < key.length() - 1) {
                final String key3 = key.substring(comma + 1);
                if (props != null) {
                    value = props.getProperty(key3);
                }
                else {
                    value = System.getProperty(key3);
                }
            }
        }
        return value;
    }
    
    static {
        NEWLINE = SysPropertyActions.getProperty("line.separator", "\n");
        FILE_SEPARATOR = File.separator;
        PATH_SEPARATOR = File.pathSeparator;
    }
}
