// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.utils.StringPool;

public class XMLContentSpec
{
    public static final int CONTENTSPECNODE_LEAF = 0;
    public static final int CONTENTSPECNODE_ZERO_OR_ONE = 1;
    public static final int CONTENTSPECNODE_ZERO_OR_MORE = 2;
    public static final int CONTENTSPECNODE_ONE_OR_MORE = 3;
    public static final int CONTENTSPECNODE_CHOICE = 4;
    public static final int CONTENTSPECNODE_SEQ = 5;
    public static final int CONTENTSPECNODE_ANY = 6;
    public static final int CONTENTSPECNODE_ANY_OTHER = 7;
    public static final int CONTENTSPECNODE_ANY_LOCAL = 8;
    public static final int CONTENTSPECNODE_ANY_LAX = 22;
    public static final int CONTENTSPECNODE_ANY_OTHER_LAX = 23;
    public static final int CONTENTSPECNODE_ANY_LOCAL_LAX = 24;
    public static final int CONTENTSPECNODE_ANY_SKIP = 38;
    public static final int CONTENTSPECNODE_ANY_OTHER_SKIP = 39;
    public static final int CONTENTSPECNODE_ANY_LOCAL_SKIP = 40;
    public int type;
    public int value;
    public int otherValue;
    
    public XMLContentSpec() {
        this.clear();
    }
    
    public XMLContentSpec(final int n, final int n2, final int n3) {
        this.setValues(n, n2, n3);
    }
    
    public XMLContentSpec(final XMLContentSpec values) {
        this.setValues(values);
    }
    
    public XMLContentSpec(final Provider provider, final int n) {
        this.setValues(provider, n);
    }
    
    public void clear() {
        this.type = -1;
        this.value = -1;
        this.otherValue = -1;
    }
    
    public void setValues(final int type, final int value, final int otherValue) {
        this.type = type;
        this.value = value;
        this.otherValue = otherValue;
    }
    
    public void setValues(final XMLContentSpec xmlContentSpec) {
        this.type = xmlContentSpec.type;
        this.value = xmlContentSpec.value;
        this.otherValue = xmlContentSpec.otherValue;
    }
    
    public void setValues(final Provider provider, final int n) {
        if (!provider.getContentSpec(n, this)) {
            this.clear();
        }
    }
    
    public static String toString(final Provider provider, final StringPool stringPool, final int n) {
        final XMLContentSpec xmlContentSpec = new XMLContentSpec();
        if (provider.getContentSpec(n, xmlContentSpec)) {
            final StringBuffer sb = new StringBuffer();
            final int n2 = xmlContentSpec.type & 0xF;
            switch (n2) {
                case 0: {
                    sb.append('(');
                    if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue == -1) {
                        sb.append("#PCDATA");
                    }
                    else {
                        sb.append(stringPool.toString(xmlContentSpec.value));
                    }
                    sb.append(')');
                    break;
                }
                case 1: {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    final int type = xmlContentSpec.type;
                    if (type == 0) {
                        sb.append('(');
                        sb.append(stringPool.toString(xmlContentSpec.value));
                        sb.append(')');
                    }
                    else if (type == 3 || type == 2 || type == 1) {
                        sb.append('(');
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                        sb.append(')');
                    }
                    else {
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    }
                    sb.append('?');
                    break;
                }
                case 2: {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    final int type2 = xmlContentSpec.type;
                    if (type2 == 0) {
                        sb.append('(');
                        if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue == -1) {
                            sb.append("#PCDATA");
                        }
                        else if (xmlContentSpec.otherValue != -1) {
                            sb.append("##any:uri=" + stringPool.toString(xmlContentSpec.otherValue));
                        }
                        else if (xmlContentSpec.value == -1) {
                            sb.append("##any");
                        }
                        else {
                            appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                        }
                        sb.append(')');
                    }
                    else if (type2 == 3 || type2 == 2 || type2 == 1) {
                        sb.append('(');
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                        sb.append(')');
                    }
                    else {
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    }
                    sb.append('*');
                    break;
                }
                case 3: {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    final int type3 = xmlContentSpec.type;
                    if (type3 == 0) {
                        sb.append('(');
                        if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue == -1) {
                            sb.append("#PCDATA");
                        }
                        else if (xmlContentSpec.otherValue != -1) {
                            sb.append("##any:uri=" + stringPool.toString(xmlContentSpec.otherValue));
                        }
                        else if (xmlContentSpec.value == -1) {
                            sb.append("##any");
                        }
                        else {
                            sb.append(stringPool.toString(xmlContentSpec.value));
                        }
                        sb.append(')');
                    }
                    else if (type3 == 3 || type3 == 2 || type3 == 1) {
                        sb.append('(');
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                        sb.append(')');
                    }
                    else {
                        appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    }
                    sb.append('+');
                    break;
                }
                case 4:
                case 5: {
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    break;
                }
                case 6: {
                    sb.append("##any");
                    if (xmlContentSpec.otherValue != -1) {
                        sb.append(":uri=");
                        sb.append(stringPool.toString(xmlContentSpec.otherValue));
                        break;
                    }
                    break;
                }
                case 7: {
                    sb.append("##other:uri=");
                    sb.append(stringPool.toString(xmlContentSpec.otherValue));
                    break;
                }
                case 8: {
                    sb.append("##local");
                    break;
                }
                default: {
                    sb.append("???");
                    break;
                }
            }
            return sb.toString();
        }
        return null;
    }
    
    public int hashCode() {
        return this.type << 16 | this.value << 8 | this.otherValue;
    }
    
    public boolean equals(final Object o) {
        if (o != null && o instanceof XMLContentSpec) {
            final XMLContentSpec xmlContentSpec = (XMLContentSpec)o;
            return this.type == xmlContentSpec.type && this.value == xmlContentSpec.value && this.otherValue == xmlContentSpec.otherValue;
        }
        return false;
    }
    
    private static void appendContentSpec(final Provider provider, final StringPool stringPool, final XMLContentSpec xmlContentSpec, final StringBuffer sb, final boolean b, final int n) {
        final int n2 = xmlContentSpec.type & 0xF;
        switch (n2) {
            case 0: {
                if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue == -1) {
                    sb.append("#PCDATA");
                    break;
                }
                if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue != -1) {
                    sb.append("##any:uri=" + stringPool.toString(xmlContentSpec.otherValue));
                    break;
                }
                if (xmlContentSpec.value == -1) {
                    sb.append("##any");
                    break;
                }
                sb.append(stringPool.toString(xmlContentSpec.value));
                break;
            }
            case 1: {
                if (n == 3 || n == 2 || n == 1) {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    sb.append('(');
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    sb.append(')');
                }
                else {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                }
                sb.append('?');
                break;
            }
            case 2: {
                if (n == 3 || n == 2 || n == 1) {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    sb.append('(');
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    sb.append(')');
                }
                else {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                }
                sb.append('*');
                break;
            }
            case 3: {
                if (n == 3 || n == 2 || n == 1) {
                    sb.append('(');
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                    sb.append(')');
                }
                else {
                    provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                    appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                }
                sb.append('+');
                break;
            }
            case 4:
            case 5: {
                if (b) {
                    sb.append('(');
                }
                final int type = xmlContentSpec.type;
                final int otherValue = xmlContentSpec.otherValue;
                provider.getContentSpec(xmlContentSpec.value, xmlContentSpec);
                appendContentSpec(provider, stringPool, xmlContentSpec, sb, xmlContentSpec.type != type, n2);
                if (type == 4) {
                    sb.append('|');
                }
                else {
                    sb.append(',');
                }
                provider.getContentSpec(otherValue, xmlContentSpec);
                appendContentSpec(provider, stringPool, xmlContentSpec, sb, true, n2);
                if (b) {
                    sb.append(')');
                    break;
                }
                break;
            }
            case 6: {
                sb.append("##any");
                if (xmlContentSpec.otherValue != -1) {
                    sb.append(":uri=");
                    sb.append(stringPool.toString(xmlContentSpec.otherValue));
                    break;
                }
                break;
            }
            case 7: {
                sb.append("##other:uri=");
                sb.append(stringPool.toString(xmlContentSpec.otherValue));
                break;
            }
            case 8: {
                sb.append("##local");
                break;
            }
            default: {
                sb.append("???");
                break;
            }
        }
    }
    
    public interface Provider
    {
        boolean getContentSpec(final int p0, final XMLContentSpec p1);
    }
}
