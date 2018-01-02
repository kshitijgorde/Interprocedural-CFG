// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import java.util.Enumeration;
import ji.util.d;
import java.util.ArrayList;
import java.io.Reader;
import ji.xml.jiXMLDocHandler;
import ji.xml.jiParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import ji.v1event.af;
import ji.document.ad;
import java.util.Hashtable;

public class m9
{
    private int a;
    private Hashtable b;
    private ad c;
    private af d;
    
    public m9(final ad c, final af d) {
        this.b = null;
        this.c = c;
        this.d = d;
    }
    
    public df a(final int a, final String s) throws Exception {
        if (s == null) {
            return null;
        }
        this.a = a;
        final ze ze = new ze();
        jiParser.parse(ze, new InputStreamReader(new ByteArrayInputStream(s.getBytes())));
        return this.c.a(ze.a(), a, this.d);
    }
    
    public long a(final long n) {
        if (this.b != null) {
            final Long n2 = this.b.get(new Long(n));
            if (n2 != null) {
                return n2;
            }
        }
        return 0L;
    }
    
    private class ze implements jiXMLDocHandler
    {
        private ArrayList a;
        
        public ze() {
            this.a = new ArrayList();
        }
        
        public void startElement(final String s, final Hashtable hashtable) throws Exception {
            Label_0121: {
                if (s.equalsIgnoreCase("page")) {
                    try {
                        final int int1 = Integer.parseInt(hashtable.get("number"));
                        if (int1 != m9.this.a) {
                            if (m9.this.a != -1) {
                                break Label_0121;
                            }
                        }
                        try {
                            final long long1 = Long.parseLong(hashtable.get("CRC32checksumFile"));
                            if (m9.this.b == null) {
                                m9.this.b = new Hashtable();
                            }
                            m9.this.b.put(new Long(int1), new Long(long1));
                        }
                        catch (NumberFormatException ex) {}
                    }
                    catch (NumberFormatException ex2) {}
                }
            }
            if (s.equalsIgnoreCase("annotation")) {
                final ArrayList<String> list = new ArrayList<String>();
                final long n = ji.util.d.c(hashtable.get("page"), 0);
                if (m9.this.a == -1 || n == m9.this.a) {
                    Long n2 = null;
                    if (m9.this.b != null) {
                        n2 = m9.this.b.get(new Long(n));
                    }
                    if (n2 == null) {
                        try {
                            final Long long2 = Long.getLong(hashtable.get("CRC32checksumFile"));
                            if (m9.this.b == null) {
                                m9.this.b = new Hashtable();
                            }
                            m9.this.b.put(new Long(n), long2);
                        }
                        catch (NumberFormatException ex3) {}
                    }
                    list.add(String.valueOf(String.valueOf(new StringBuffer("[").append(hashtable.get("type")).append("]"))));
                    final Enumeration keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final String s2 = (String)keys.nextElement();
                        if (!s2.equals("type") && !s2.equals("CRC32checksumFile")) {
                            list.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("=").append(hashtable.get(s2)))));
                        }
                    }
                    list.add("");
                    this.a.add(list);
                }
            }
        }
        
        public void endElement(final String s) throws Exception {
        }
        
        public void startDocument() throws Exception {
        }
        
        public void endDocument() throws Exception {
        }
        
        public void text(final String s) throws Exception {
        }
        
        public String[] a() {
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = this.a.size() - 1; i >= 0; --i) {
                final ArrayList<Object> list2 = this.a.get(i);
                for (int j = 0; j < list2.size(); ++j) {
                    list.add(list2.get(j));
                }
            }
            final int size = list.size();
            final String[] array = new String[size];
            for (int k = 0; k < size; ++k) {
                array[k] = list.get(k);
            }
            return array;
        }
    }
}
