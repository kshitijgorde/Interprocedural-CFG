// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import javax.xml.transform.TransformerException;
import org.apache.xml.serializer.utils.WrappedRuntimeException;
import org.apache.xml.serializer.utils.SystemIDResolver;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.xml.serializer.utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Hashtable;

final class CharInfo
{
    private Hashtable m_charToString;
    public static final String HTML_ENTITIES_RESOURCE = "org.apache.xml.serializer.HTMLEntities";
    public static final String XML_ENTITIES_RESOURCE = "org.apache.xml.serializer.XMLEntities";
    public static final char S_HORIZONAL_TAB = '\t';
    public static final char S_LINEFEED = '\n';
    public static final char S_CARRIAGERETURN = '\r';
    final boolean onlyQuotAmpLtGt;
    private static final int ASCII_MAX = 128;
    private boolean[] isSpecialAttrASCII;
    private boolean[] isSpecialTextASCII;
    private boolean[] isCleanTextASCII;
    private int[] array_of_bits;
    private static final int SHIFT_PER_WORD = 5;
    private static final int LOW_ORDER_BITMASK = 31;
    private int firstWordNotUsed;
    private CharKey m_charKey;
    private static Hashtable m_getCharInfoCache;
    static /* synthetic */ Class class$org$apache$xml$serializer$CharInfo;
    
    private CharInfo(final String entitiesResource, final String method) {
        this(entitiesResource, method, false);
    }
    
    private CharInfo(final String entitiesResource, final String method, final boolean internal) {
        this.m_charToString = new Hashtable();
        this.isSpecialAttrASCII = new boolean[128];
        this.isSpecialTextASCII = new boolean[128];
        this.isCleanTextASCII = new boolean[128];
        this.array_of_bits = this.createEmptySetOfIntegers(65535);
        this.m_charKey = new CharKey();
        ResourceBundle entities = null;
        boolean noExtraEntities = true;
        if (internal) {
            try {
                entities = ResourceBundle.getBundle(entitiesResource);
            }
            catch (Exception ex) {}
        }
        if (entities != null) {
            final Enumeration keys = entities.getKeys();
            while (keys.hasMoreElements()) {
                final String name = keys.nextElement();
                final String value = entities.getString(name);
                final int code = Integer.parseInt(value);
                this.defineEntity(name, (char)code);
                if (this.extraEntity(code)) {
                    noExtraEntities = false;
                }
            }
            this.set(10);
            this.set(13);
        }
        else {
            InputStream is = null;
            try {
                if (internal) {
                    is = ((CharInfo.class$org$apache$xml$serializer$CharInfo == null) ? (CharInfo.class$org$apache$xml$serializer$CharInfo = class$("org.apache.xml.serializer.CharInfo")) : CharInfo.class$org$apache$xml$serializer$CharInfo).getResourceAsStream(entitiesResource);
                }
                else {
                    final ClassLoader cl = ObjectFactory.findClassLoader();
                    if (cl == null) {
                        is = ClassLoader.getSystemResourceAsStream(entitiesResource);
                    }
                    else {
                        is = cl.getResourceAsStream(entitiesResource);
                    }
                    if (is == null) {
                        try {
                            final URL url = new URL(entitiesResource);
                            is = url.openStream();
                        }
                        catch (Exception ex2) {}
                    }
                }
                if (is == null) {
                    throw new RuntimeException(Utils.messages.createMessage("ER_RESOURCE_COULD_NOT_FIND", new Object[] { entitiesResource, entitiesResource }));
                }
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                }
                catch (UnsupportedEncodingException e2) {
                    reader = new BufferedReader(new InputStreamReader(is));
                }
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (line.length() != 0 && line.charAt(0) != '#') {
                        int index = line.indexOf(32);
                        if (index > 1) {
                            final String name2 = line.substring(0, index);
                            if (++index < line.length()) {
                                String value2 = line.substring(index);
                                index = value2.indexOf(32);
                                if (index > 0) {
                                    value2 = value2.substring(0, index);
                                }
                                final int code2 = Integer.parseInt(value2);
                                this.defineEntity(name2, (char)code2);
                                if (this.extraEntity(code2)) {
                                    noExtraEntities = false;
                                }
                            }
                        }
                    }
                }
                is.close();
                this.set(10);
                this.set(13);
            }
            catch (Exception e) {
                throw new RuntimeException(Utils.messages.createMessage("ER_RESOURCE_COULD_NOT_LOAD", new Object[] { entitiesResource, e.toString(), entitiesResource, e.toString() }));
            }
            finally {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        for (int ch = 0; ch < 128; ++ch) {
            if (((32 <= ch || 10 == ch || 13 == ch || 9 == ch) && !this.get(ch)) || 34 == ch) {
                this.isCleanTextASCII[ch] = true;
                this.isSpecialTextASCII[ch] = false;
            }
            else {
                this.isCleanTextASCII[ch] = false;
                this.isSpecialTextASCII[ch] = true;
            }
        }
        this.onlyQuotAmpLtGt = noExtraEntities;
        for (int i = 0; i < 128; ++i) {
            this.isSpecialAttrASCII[i] = this.get(i);
        }
        if ("xml".equals(method)) {
            this.isSpecialAttrASCII[9] = true;
        }
    }
    
    private void defineEntity(final String name, final char value) {
        final StringBuffer sb = new StringBuffer("&");
        sb.append(name);
        sb.append(';');
        final String entityString = sb.toString();
        this.defineChar2StringMapping(entityString, value);
    }
    
    synchronized String getOutputStringForChar(final char value) {
        this.m_charKey.setChar(value);
        return this.m_charToString.get(this.m_charKey);
    }
    
    final boolean isSpecialAttrChar(final int value) {
        if (value < 128) {
            return this.isSpecialAttrASCII[value];
        }
        return this.get(value);
    }
    
    final boolean isSpecialTextChar(final int value) {
        if (value < 128) {
            return this.isSpecialTextASCII[value];
        }
        return this.get(value);
    }
    
    final boolean isTextASCIIClean(final int value) {
        return this.isCleanTextASCII[value];
    }
    
    private static CharInfo getCharInfoBasedOnPrivilege(final String entitiesFileName, final String method, final boolean internal) {
        return AccessController.doPrivileged((PrivilegedAction<CharInfo>)new PrivilegedAction() {
            public Object run() {
                return new CharInfo(entitiesFileName, method, internal, null);
            }
        });
    }
    
    static CharInfo getCharInfo(final String entitiesFileName, final String method) {
        CharInfo charInfo = CharInfo.m_getCharInfoCache.get(entitiesFileName);
        if (charInfo != null) {
            return charInfo;
        }
        try {
            charInfo = getCharInfoBasedOnPrivilege(entitiesFileName, method, true);
            CharInfo.m_getCharInfoCache.put(entitiesFileName, charInfo);
            return charInfo;
        }
        catch (Exception e) {
            try {
                return getCharInfoBasedOnPrivilege(entitiesFileName, method, false);
            }
            catch (Exception e) {
                if (entitiesFileName.indexOf(58) < 0) {
                    final String absoluteEntitiesFileName = SystemIDResolver.getAbsoluteURIFromRelative(entitiesFileName);
                }
                else {
                    try {
                        final String absoluteEntitiesFileName = SystemIDResolver.getAbsoluteURI(entitiesFileName, null);
                    }
                    catch (TransformerException te) {
                        throw new WrappedRuntimeException(te);
                    }
                }
                return getCharInfoBasedOnPrivilege(entitiesFileName, method, false);
            }
        }
    }
    
    private static int arrayIndex(final int i) {
        return i >> 5;
    }
    
    private static int bit(final int i) {
        final int ret = 1 << (i & 0x1F);
        return ret;
    }
    
    private int[] createEmptySetOfIntegers(final int max) {
        this.firstWordNotUsed = 0;
        final int[] arr = new int[arrayIndex(max - 1) + 1];
        return arr;
    }
    
    private final void set(final int i) {
        this.setASCIIdirty(i);
        final int j = i >> 5;
        final int k = j + 1;
        if (this.firstWordNotUsed < k) {
            this.firstWordNotUsed = k;
        }
        final int[] array_of_bits = this.array_of_bits;
        final int n = j;
        array_of_bits[n] |= 1 << (i & 0x1F);
    }
    
    private final boolean get(final int i) {
        boolean in_the_set = false;
        final int j = i >> 5;
        if (j < this.firstWordNotUsed) {
            in_the_set = ((this.array_of_bits[j] & 1 << (i & 0x1F)) != 0x0);
        }
        return in_the_set;
    }
    
    private boolean extraEntity(final int entityValue) {
        boolean extra = false;
        if (entityValue < 128) {
            switch (entityValue) {
                case 34:
                case 38:
                case 60:
                case 62: {
                    break;
                }
                default: {
                    extra = true;
                    break;
                }
            }
        }
        return extra;
    }
    
    private void setASCIIdirty(final int j) {
        if (0 <= j && j < 128) {
            this.isCleanTextASCII[j] = false;
            this.isSpecialTextASCII[j] = true;
        }
    }
    
    private void setASCIIclean(final int j) {
        if (0 <= j && j < 128) {
            this.isCleanTextASCII[j] = true;
            this.isSpecialTextASCII[j] = false;
        }
    }
    
    private void defineChar2StringMapping(final String outputString, final char inputChar) {
        final CharKey character = new CharKey(inputChar);
        this.m_charToString.put(character, outputString);
        this.set(inputChar);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        CharInfo.m_getCharInfoCache = new Hashtable();
    }
    
    private static class CharKey
    {
        private char m_char;
        
        public CharKey(final char key) {
            this.m_char = key;
        }
        
        public CharKey() {
        }
        
        public final void setChar(final char c) {
            this.m_char = c;
        }
        
        public final int hashCode() {
            return this.m_char;
        }
        
        public final boolean equals(final Object obj) {
            return ((CharKey)obj).m_char == this.m_char;
        }
    }
}
