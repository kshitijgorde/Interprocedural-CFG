// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.util.Locale;

public class URLRecognizer implements WordRecognizer
{
    private boolean isAlpha(String lowerCase) {
        lowerCase = lowerCase.toLowerCase(Locale.ENGLISH);
        for (int i = 0; i < lowerCase.length(); ++i) {
            if (lowerCase.charAt(i) < 'a' || lowerCase.charAt(i) > 'z') {
                return false;
            }
        }
        return true;
    }
    
    public boolean recognize(final String s) {
        if (s.startsWith("http://")) {
            return true;
        }
        if (s.startsWith("ftp://")) {
            return true;
        }
        if (s.startsWith("www.")) {
            return true;
        }
        if (s.startsWith("ftp.")) {
            return true;
        }
        final int index = s.indexOf(46);
        if (index == -1) {
            return false;
        }
        final int lastIndex = s.lastIndexOf(46);
        if (index == lastIndex) {
            return false;
        }
        final String substring = s.substring(lastIndex + 1);
        return this.isAlpha(substring) && (substring.length() == 2 || substring.length() == 3);
    }
    
    public String getType() {
        return "url";
    }
}
