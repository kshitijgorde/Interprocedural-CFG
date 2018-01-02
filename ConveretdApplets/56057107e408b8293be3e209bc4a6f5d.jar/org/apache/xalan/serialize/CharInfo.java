// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.xml.utils.CharKey;
import java.util.Hashtable;
import java.util.BitSet;

public class CharInfo
{
    private BitSet m_specialsMap;
    private Hashtable m_charToEntityRef;
    public static String HTML_ENTITIES_RESOURCE;
    public static String XML_ENTITIES_RESOURCE;
    public static char S_LINEFEED;
    public static char S_CARRIAGERETURN;
    private CharKey m_charKey;
    static /* synthetic */ Class class$org$apache$xalan$serialize$CharInfo;
    
    static {
        CharInfo.HTML_ENTITIES_RESOURCE = "HTMLEntities.res";
        CharInfo.XML_ENTITIES_RESOURCE = "XMLEntities.res";
        CharInfo.S_LINEFEED = '\n';
        CharInfo.S_CARRIAGERETURN = '\r';
    }
    
    public CharInfo(final String entitiesResource) {
        this.m_specialsMap = new BitSet(65535);
        this.m_charToEntityRef = new Hashtable();
        this.m_charKey = new CharKey();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = ((CharInfo.class$org$apache$xalan$serialize$CharInfo != null) ? CharInfo.class$org$apache$xalan$serialize$CharInfo : (CharInfo.class$org$apache$xalan$serialize$CharInfo = class$("org.apache.xalan.serialize.CharInfo"))).getResourceAsStream(entitiesResource);
            if (is == null) {
                final URL url = new URL(entitiesResource);
                is = url.openStream();
            }
            if (is == null) {
                throw new RuntimeException("The resource [" + entitiesResource + "] could not be found.\n" + entitiesResource);
            }
            reader = new BufferedReader(new InputStreamReader(is));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.length() != 0 && line.charAt(0) != '#') {
                    int index = line.indexOf(32);
                    if (index > 1) {
                        final String name = line.substring(0, index);
                        if (++index < line.length()) {
                            String value = line.substring(index);
                            index = value.indexOf(32);
                            if (index > 0) {
                                value = value.substring(0, index);
                            }
                            final int code = Integer.parseInt(value);
                            this.defineEntity(name, (char)code);
                        }
                    }
                }
            }
            is.close();
            this.m_specialsMap.set(CharInfo.S_LINEFEED);
            this.m_specialsMap.set(CharInfo.S_CARRIAGERETURN);
        }
        catch (Exception except) {
            throw new RuntimeException("The resource [" + entitiesResource + "] could not load: " + except.toString() + "\n" + entitiesResource + "\t" + except.toString());
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (Exception ex) {}
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
    
    protected void defineEntity(final String name, final char value) {
        final CharKey character = new CharKey(value);
        this.m_charToEntityRef.put(character, name);
        this.m_specialsMap.set(value);
    }
    
    public String getEntityNameForChar(final char value) {
        this.m_charKey.setChar(value);
        return this.m_charToEntityRef.get(this.m_charKey);
    }
    
    public boolean isSpecial(final char value) {
        return this.m_specialsMap.get(value);
    }
}
