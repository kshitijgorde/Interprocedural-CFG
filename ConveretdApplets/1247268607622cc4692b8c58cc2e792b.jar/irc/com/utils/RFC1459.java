// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.text.ParseException;
import java.text.Collator;
import java.text.RuleBasedCollator;

public class RFC1459
{
    static final String RFC1459_rules = "< '_'< '-'< '`'< 0< 1< 2< 3< 4< 5< 6< 7< 8< 9< a,A< b,B< c,C< d,D< e,E< f,F< g,G< h,H< i,I< j,J< k,K< l,L< m,M< n,N< o,O< p,P< q,Q< r,R< s,S< t,T< u,U< v,V< w,W< x,X< y,Y< z,Z< '{','['< '}',']'< '|','\\'< '^','~'";
    static final String RFC1459_chars = "_-`0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ[]|\\{}^";
    private static RuleBasedCollator rbc;
    
    public static String filterString(final String s) {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[charArray.length];
        int i;
        for (int n = i = 0; i < charArray.length; ++i) {
            if (isDeclaredChar(charArray[i])) {
                array[n++] = charArray[i];
            }
        }
        return String.valueOf(array);
    }
    
    public static Collator getCollator() {
        if (RFC1459.rbc != null) {
            return RFC1459.rbc;
        }
        try {
            RFC1459.rbc = new RuleBasedCollator("< '_'< '-'< '`'< 0< 1< 2< 3< 4< 5< 6< 7< 8< 9< a,A< b,B< c,C< d,D< e,E< f,F< g,G< h,H< i,I< j,J< k,K< l,L< m,M< n,N< o,O< p,P< q,Q< r,R< s,S< t,T< u,U< v,V< w,W< x,X< y,Y< z,Z< '{','['< '}',']'< '|','\\'< '^','~'");
        }
        catch (ParseException ex) {
            System.err.println(ex);
            return null;
        }
        catch (IllegalArgumentException ex2) {
            System.err.println(ex2);
            return null;
        }
        RFC1459.rbc.setStrength(1);
        return RFC1459.rbc;
    }
    
    public static boolean isDeclaredChar(final char c) {
        return "_-`0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ[]|\\{}^".indexOf(c) != -1;
    }
    
    static {
        RFC1459.rbc = null;
    }
}
