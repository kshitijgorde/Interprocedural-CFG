// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

public final class URI
{
    private String fScheme;
    private String fAuthority;
    private String fPath;
    private String fQuery;
    private String fFragment;
    private static final byte[] fgSchemeCharMap;
    private static final byte[] fgURICharMap;
    private static final byte[] fgHexCharMap;
    
    private URI() {
    }
    
    public URI(final URI uri) {
        this.fScheme = uri.fScheme;
        this.fAuthority = uri.fAuthority;
        this.fPath = uri.fPath;
        this.fQuery = uri.fQuery;
        this.fFragment = uri.fFragment;
    }
    
    public static URI parse(final String s) {
        if (s == null) {
            return null;
        }
        return parseURI(s, true);
    }
    
    public static URI parse(final URI uri, final String s) {
        if (uri == null) {
            return null;
        }
        if (s == null) {
            return null;
        }
        final URI uri2 = parseURI(s, false);
        if (uri2 == null) {
            return null;
        }
        if (uri2.fScheme == null) {
            return uri2.resolveRelativeTo(uri);
        }
        return uri2;
    }
    
    public static URI parse(final String s, final String s2, final String s3) {
        final URI scheme = new URI().parseScheme(s);
        if (scheme == null) {
            return null;
        }
        final URI authority = scheme.parseAuthority(s2);
        if (authority == null) {
            return null;
        }
        return authority.parsePath(s3);
    }
    
    private static URI parseURI(final String s, final boolean b) {
        URI uri = new URI();
        final int length = s.length();
        int n = 47;
        int n2;
        int i = n2 = 0;
        while (i < length) {
            n = s.charAt(i);
            if (n == 58) {
                if (i == 0) {
                    break;
                }
                uri = uri.parseScheme(s.substring(n2, i));
                if (uri == null) {
                    return null;
                }
                if (++i == length) {
                    return uri.parsePath("");
                }
                n2 = i;
                n = s.charAt(i);
                break;
            }
            else {
                if (n == 47 || n == 63 || n == 35) {
                    i = 0;
                    n = s.charAt(i);
                    break;
                }
                ++i;
            }
        }
        if (b && uri.fScheme == null) {
            return null;
        }
        if (i + 1 < length && n == 47 && s.charAt(i + 1) == '/') {
            i += 2;
            final int n3 = i;
            while (i < length) {
                n = s.charAt(i);
                if (n == 47 || n == 63) {
                    break;
                }
                if (n == 35) {
                    break;
                }
                ++i;
            }
            uri = uri.parseAuthority(s.substring(n3, i));
            if (uri == null) {
                return null;
            }
            n2 = i;
        }
        while (i < length) {
            n = s.charAt(i);
            if (n == 63) {
                break;
            }
            if (n == 35) {
                break;
            }
            ++i;
        }
        URI uri2 = uri.parsePath(s.substring(n2, i));
        if (uri2 == null) {
            return null;
        }
        if (n == 63) {
            int n4;
            for (n4 = ++i; i < length; ++i) {
                n = s.charAt(i);
                if (n == 35) {
                    break;
                }
            }
            uri2 = uri2.parseQuery(s.substring(n4, i));
            if (uri2 == null) {
                return null;
            }
        }
        if (n == 35) {
            int n5;
            for (n5 = ++i; i < length && s.charAt(i) != '#'; ++i) {}
            uri2 = uri2.parseFragment(s.substring(n5, i));
            if (uri2 == null) {
                return null;
            }
        }
        return uri2;
    }
    
    private URI resolveRelativeTo(final URI uri) {
        this.fScheme = uri.fScheme;
        if (this.fAuthority != null) {
            return this;
        }
        this.fAuthority = uri.fAuthority;
        final int length = this.fPath.length();
        if (length == 0 && this.fQuery == null) {
            uri.fQuery = null;
            if (this.fFragment != null) {
                uri.fFragment = this.fFragment;
            }
            return uri;
        }
        if (length > 0 && this.fPath.charAt(0) == '/') {
            return this;
        }
        String s = uri.fPath;
        final int n = s.lastIndexOf(47) + 1;
        if (n > 0) {
            s = s.substring(0, n);
        }
        final int n2 = s.length() + length;
        final char[] array = new char[n2];
        s.getChars(0, n2 - length, array, 0);
        this.fPath.getChars(0, length, array, n2 - length);
        int n3 = 0;
        int i = 0;
        while (i < n2) {
            if ((array[n3++] = array[i++]) == '/') {
                if (i + 1 >= n2) {
                    continue;
                }
                if (array[i] != '.' || array[i + 1] != '/') {
                    continue;
                }
                i += 2;
            }
        }
        if (n3 > 1 && array[n3 - 1] == '.' && array[n3 - 2] == '/') {
            --n3;
        }
        final int n4 = n3;
        int n5 = 0;
        int j = 0;
        while (j < n4) {
            final char c = array[j++];
            array[n5++] = c;
            if (c == '/' && n5 != 1) {
                if (j + 2 >= n4) {
                    continue;
                }
                if (array[j] != '.' || array[j + 1] != '.' || array[j + 2] != '/') {
                    continue;
                }
                final int n6 = n5 - 4;
                if (n6 >= 0 && array[n6] == '/' && array[n6 + 1] == '.' && array[n6 + 2] == '.') {
                    continue;
                }
                j += 2;
                while (--n5 > 0 && array[n5 - 1] != '/') {}
                if (n5 <= 0 || array[n5 - 1] != '/') {
                    continue;
                }
                --n5;
            }
        }
        final int n7 = n5 - 3;
        if (n7 >= 0 && array[n7] == '/' && array[n7 + 1] == '.' && array[n7 + 2] == '.') {
            final int n8 = n7 - 3;
            if (n8 < 0 || array[n8] != '/' || array[n8 + 1] != '.' || array[n8 + 2] != '.') {
                for (n5 -= 3; n5 > 0 && array[n5 - 1] != '/'; --n5) {}
            }
        }
        this.fPath = new String(array, 0, n5);
        return this;
    }
    
    private URI parseScheme(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        if (length == 0) {
            return null;
        }
        final char char1 = s.charAt(0);
        if (char1 >= '\u0080' || URI.fgSchemeCharMap[char1] != 1) {
            return null;
        }
        for (int i = 1; i < length; ++i) {
            final char char2 = s.charAt(i);
            if (char2 >= '\u0080' || URI.fgSchemeCharMap[char2] == 0) {
                return null;
            }
        }
        this.fScheme = s.toLowerCase();
        return this;
    }
    
    private URI parseAuthority(final String fAuthority) {
        if (fAuthority == null) {
            return null;
        }
        for (int length = fAuthority.length(), i = 0; i < length; ++i) {
            final char char1 = fAuthority.charAt(i);
            if (char1 >= '\u0080') {
                return null;
            }
            switch (URI.fgURICharMap[char1]) {
                default: {
                    return null;
                }
                case 1: {
                    break;
                }
                case 2: {
                    if (char1 == '/' || char1 == '?') {
                        return null;
                    }
                    break;
                }
                case 3: {
                    if (i + 2 >= length) {
                        return null;
                    }
                    ++i;
                    final char char2 = fAuthority.charAt(i);
                    if (char2 >= '\u0080' || URI.fgHexCharMap[char2] == 0) {
                        return null;
                    }
                    ++i;
                    final char char3 = fAuthority.charAt(i);
                    if (char3 >= '\u0080' || URI.fgHexCharMap[char3] == 0) {
                        return null;
                    }
                    break;
                }
            }
        }
        this.fAuthority = fAuthority;
        return this;
    }
    
    private URI parsePath(final String fPath) {
        if (fPath == null) {
            return null;
        }
        for (int length = fPath.length(), i = 0; i < length; ++i) {
            final char char1 = fPath.charAt(i);
            if (char1 >= '\u0080') {
                return null;
            }
            switch (URI.fgURICharMap[char1]) {
                default: {
                    return null;
                }
                case 1: {
                    break;
                }
                case 2: {
                    if (char1 == '?') {
                        return null;
                    }
                    break;
                }
                case 3: {
                    if (i + 2 >= length) {
                        return null;
                    }
                    ++i;
                    final char char2 = fPath.charAt(i);
                    if (char2 >= '\u0080' || URI.fgHexCharMap[char2] == 0) {
                        return null;
                    }
                    ++i;
                    final char char3 = fPath.charAt(i);
                    if (char3 >= '\u0080' || URI.fgHexCharMap[char3] == 0) {
                        return null;
                    }
                    break;
                }
            }
        }
        this.fPath = fPath;
        return this;
    }
    
    private URI parseQuery(final String fQuery) {
        if (fQuery == null) {
            return null;
        }
        for (int length = fQuery.length(), i = 0; i < length; ++i) {
            final char char1 = fQuery.charAt(i);
            if (char1 >= '\u0080') {
                return null;
            }
            switch (URI.fgURICharMap[char1]) {
                default: {
                    return null;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    if (i + 2 >= length) {
                        return null;
                    }
                    ++i;
                    final char char2 = fQuery.charAt(i);
                    if (char2 >= '\u0080' || URI.fgHexCharMap[char2] == 0) {
                        return null;
                    }
                    ++i;
                    final char char3 = fQuery.charAt(i);
                    if (char3 >= '\u0080' || URI.fgHexCharMap[char3] == 0) {
                        return null;
                    }
                    break;
                }
            }
        }
        this.fQuery = fQuery;
        return this;
    }
    
    private URI parseFragment(final String fFragment) {
        if (fFragment == null) {
            return null;
        }
        for (int length = fFragment.length(), i = 0; i < length; ++i) {
            final char char1 = fFragment.charAt(i);
            if (char1 >= '\u0080') {
                return null;
            }
            switch (URI.fgURICharMap[char1]) {
                default: {
                    return null;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    if (i + 2 >= length) {
                        return null;
                    }
                    ++i;
                    final char char2 = fFragment.charAt(i);
                    if (char2 >= '\u0080' || URI.fgHexCharMap[char2] == 0) {
                        return null;
                    }
                    ++i;
                    final char char3 = fFragment.charAt(i);
                    if (char3 >= '\u0080' || URI.fgHexCharMap[char3] == 0) {
                        return null;
                    }
                    break;
                }
            }
        }
        this.fFragment = fFragment;
        return this;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.fScheme);
        sb.append(':');
        if (this.fAuthority != null) {
            sb.append('/');
            sb.append('/');
            sb.append(this.fAuthority);
        }
        sb.append(this.fPath);
        if (this.fQuery != null) {
            sb.append('?');
            sb.append(this.fQuery);
        }
        if (this.fFragment != null) {
            sb.append('#');
            sb.append(this.fFragment);
        }
        return sb.toString();
    }
    
    public static InputStream openStream(final String s) throws IOException {
        return new URL(s).openStream();
    }
    
    static {
        fgSchemeCharMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
        fgURICharMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 3, 2, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 2, 0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 2, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0 };
        fgHexCharMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
