// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.io;

import java.util.ArrayList;
import java.io.PrintWriter;

public class XMLWriter
{
    private PrintWriter m_out;
    private int m_bias;
    private int m_tab;
    private ArrayList m_tagStack;
    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '\u007f';
    private static final char[] VALID_CHARS;
    private static final char[] INVALID;
    private static final String[] VALID;
    
    public XMLWriter(final PrintWriter printWriter) {
        this(printWriter, 2);
    }
    
    public XMLWriter(final PrintWriter out, final int n) {
        this.m_bias = 0;
        this.m_tagStack = new ArrayList();
        this.m_out = out;
        this.m_tab = 2;
    }
    
    public void print(final String s) {
        this.m_out.print(s);
    }
    
    public void println(final String s) {
        this.m_out.print(s);
        this.m_out.print("\n");
    }
    
    public void println() {
        this.m_out.print("\n");
    }
    
    public void begin() {
        this.m_out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        this.println();
    }
    
    public void begin(final String s, final int bias) {
        this.begin();
        this.m_out.print(s);
        this.m_bias = bias;
    }
    
    public void comment(final String s) {
        this.spacing();
        this.m_out.print("<!-- ");
        this.m_out.print(s);
        this.m_out.print(" -->");
        this.println();
    }
    
    protected void tag(final String s, final String[] array, final String[] array2, final int n, final boolean b) {
        this.spacing();
        this.m_out.print('<');
        this.m_out.print(s);
        for (int i = 0; i < n; ++i) {
            this.m_out.print(' ');
            this.m_out.print(array[i]);
            this.m_out.print('=');
            this.m_out.print('\"');
            this.escapeString(array2[i]);
            this.m_out.print('\"');
        }
        if (b) {
            this.m_out.print('/');
        }
        this.m_out.print('>');
        this.println();
        if (!b) {
            this.m_tagStack.add(s);
        }
    }
    
    public void tag(final String s, final String[] array, final String[] array2, final int n) {
        this.tag(s, array, array2, n, true);
    }
    
    public void start(final String s, final String[] array, final String[] array2, final int n) {
        this.tag(s, array, array2, n, false);
    }
    
    protected void tag(final String s, final String s2, final String s3, final boolean b) {
        this.spacing();
        this.m_out.print('<');
        this.m_out.print(s);
        this.m_out.print(' ');
        this.m_out.print(s2);
        this.m_out.print('=');
        this.m_out.print('\"');
        this.escapeString(s3);
        this.m_out.print('\"');
        if (b) {
            this.m_out.print('/');
        }
        this.m_out.print('>');
        this.println();
        if (!b) {
            this.m_tagStack.add(s);
        }
    }
    
    public void tag(final String s, final String s2, final String s3) {
        this.tag(s, s2, s3, true);
    }
    
    public void start(final String s, final String s2, final String s3) {
        this.tag(s, s2, s3, false);
    }
    
    protected void tag(final String s, final ArrayList list, final ArrayList list2, final int n, final boolean b) {
        this.spacing();
        this.m_out.print('<');
        this.m_out.print(s);
        for (int i = 0; i < n; ++i) {
            this.m_out.print(' ');
            this.m_out.print(list.get(i));
            this.m_out.print('=');
            this.m_out.print('\"');
            this.escapeString(list2.get(i));
            this.m_out.print('\"');
        }
        if (b) {
            this.m_out.print('/');
        }
        this.m_out.print('>');
        this.println();
        if (!b) {
            this.m_tagStack.add(s);
        }
    }
    
    public void tag(final String s, final ArrayList list, final ArrayList list2, final int n) {
        this.tag(s, list, list2, n, true);
    }
    
    public void start(final String s, final ArrayList list, final ArrayList list2, final int n) {
        this.tag(s, list, list2, n, false);
    }
    
    public void start(final String s) {
        this.tag(s, null, (String[])null, 0, false);
    }
    
    public void end() {
        final String s = this.m_tagStack.remove(this.m_tagStack.size() - 1);
        this.spacing();
        this.m_out.print('<');
        this.m_out.print('/');
        this.m_out.print(s);
        this.m_out.print('>');
        this.println();
    }
    
    public void contentTag(final String s, final String s2, final String s3, final String s4) {
        this.spacing();
        this.m_out.print('<');
        this.m_out.print(s);
        this.m_out.print(' ');
        this.m_out.print(s2);
        this.m_out.print('=');
        this.m_out.print('\"');
        this.escapeString(s3);
        this.m_out.print('\"');
        this.m_out.print('>');
        this.escapeString(s4);
        this.m_out.print('<');
        this.m_out.print('/');
        this.m_out.print(s);
        this.m_out.print('>');
        this.println();
    }
    
    public void contentTag(final String s, final String s2) {
        this.spacing();
        this.m_out.print('<');
        this.m_out.print(s);
        this.m_out.print('>');
        this.escapeString(s2);
        this.m_out.print('<');
        this.m_out.print('/');
        this.m_out.print(s);
        this.m_out.print('>');
        this.println();
    }
    
    public void content(final String s) {
        this.escapeString(s);
    }
    
    public void finish() {
        this.m_bias = 0;
        this.m_out.flush();
    }
    
    public void finish(final String s) {
        this.m_bias = 0;
        this.m_out.print(s);
        this.m_out.flush();
    }
    
    public void spacing() {
        for (int n = this.m_bias + this.m_tagStack.size() * this.m_tab, i = 0; i < n; ++i) {
            this.m_out.print(' ');
        }
    }
    
    protected void escapeString(final String s) {
        if (s == null) {
            this.m_out.print("null");
            return;
        }
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 < ' ' && char1 != XMLWriter.VALID_CHARS[0] && char1 != XMLWriter.VALID_CHARS[1] && char1 != XMLWriter.VALID_CHARS[2]) || char1 > '\u007f') {
                this.m_out.print("&#");
                this.m_out.print(Integer.toString(char1));
                this.m_out.print(';');
            }
            else {
                boolean b = true;
                for (int j = XMLWriter.INVALID.length - 1; j >= 0; --j) {
                    if (XMLWriter.INVALID[j] == char1) {
                        b = false;
                        this.m_out.print(XMLWriter.VALID[j]);
                        break;
                    }
                }
                if (b) {
                    this.m_out.print(char1);
                }
            }
        }
    }
    
    static {
        VALID_CHARS = new char[] { '\t', '\n', '\r' };
        INVALID = new char[] { '<', '>', '\"', '\'', '&' };
        VALID = new String[] { "&lt;", "&gt;", "&quot;", "&apos;", "&amp;" };
    }
}
