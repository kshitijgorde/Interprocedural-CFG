// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.awt.Component;

public class ConvertUtil
{
    private static Object getJCString(final Component component, final String s) {
        return JCString.parse(component, s);
    }
    
    public static JCVector toVector(final Component component, final String s, final char c, final boolean b) {
        if (s == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final JCVector jcVector = new JCVector(jcStringTokenizer.countTokens(c));
        jcStringTokenizer.strip_esc = false;
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            jcVector.setElementAt(n, toCellValue(component, jcStringTokenizer.nextToken(c), b));
            ++n;
        }
        return jcVector;
    }
    
    public static Object toCellValue(final Component component, final String s, final boolean b) {
        final int index;
        Object o;
        if (s != null && b && (index = s.indexOf(91)) != -1 && (index == 0 || (index > 0 && s.charAt(index - 1) != '\\'))) {
            o = getJCString(component, s);
        }
        else if (b) {
            o = JCUtilConverter.removeEscape(s);
        }
        else {
            o = s;
        }
        return o;
    }
    
    public static JCVector toVectorFromCSV(final Component component, final String s) {
        if (s == null) {
            return null;
        }
        final JCCSVTokenizer jccsvTokenizer = new JCCSVTokenizer(s);
        final JCVector jcVector = new JCVector(jccsvTokenizer.countTokens());
        int n = 0;
        while (jccsvTokenizer.hasMoreTokens()) {
            jcVector.setElementAt(n, jccsvTokenizer.nextToken());
            ++n;
        }
        return jcVector;
    }
}
