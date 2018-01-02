// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class RefinedSoundex implements StringEncoder
{
    public static final RefinedSoundex US_ENGLISH;
    public static final char[] US_ENGLISH_MAPPING;
    private char[] soundexMapping;
    
    public RefinedSoundex() {
        this(RefinedSoundex.US_ENGLISH_MAPPING);
    }
    
    public RefinedSoundex(final char[] mapping) {
        this.soundexMapping = mapping;
    }
    
    public int difference(final String s1, final String s2) throws EncoderException {
        return SoundexUtils.difference(this, s1, s2);
    }
    
    public Object encode(final Object pObject) throws EncoderException {
        if (!(pObject instanceof String)) {
            throw new EncoderException("Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
        }
        return this.soundex((String)pObject);
    }
    
    public String encode(final String pString) {
        return this.soundex(pString);
    }
    
    char getMappingCode(final char c) {
        if (!Character.isLetter(c)) {
            return '\0';
        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }
    
    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        str = SoundexUtils.clean(str);
        if (str.length() == 0) {
            return str;
        }
        final StringBuffer sBuf = new StringBuffer();
        sBuf.append(str.charAt(0));
        char last = '*';
        for (int i = 0; i < str.length(); ++i) {
            final char current = this.getMappingCode(str.charAt(i));
            if (current != last) {
                if (current != '\0') {
                    sBuf.append(current);
                }
                last = current;
            }
        }
        return sBuf.toString();
    }
    
    static {
        US_ENGLISH = new RefinedSoundex();
        US_ENGLISH_MAPPING = "01360240043788015936020505".toCharArray();
    }
}
