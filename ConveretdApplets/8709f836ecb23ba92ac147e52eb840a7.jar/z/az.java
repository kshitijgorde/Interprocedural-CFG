// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Enumeration;
import java.util.List;
import nanoxml.XMLElement;
import java.util.Hashtable;
import java.util.ArrayList;

public final class az
{
    private ArrayList a;
    private Hashtable b;
    
    public az(final XMLElement xmlElement) {
        this.a = new ArrayList();
        this.b = new Hashtable();
        System.out.println("\nBuilding string table...");
        final List b;
        if ((b = aa.b(xmlElement, "//key")).size() == 0) {
            throw new RuntimeException("Error parsing string table: no keys found");
        }
        for (int i = 0; i < b.size(); ++i) {
            final XMLElement xmlElement2;
            final String stringAttribute = (xmlElement2 = b.get(i)).getStringAttribute("value");
            final Enumeration elements = xmlElement2.getChildren().elements();
            while (elements.hasMoreElements()) {
                final XMLElement xmlElement3;
                final String name = (xmlElement3 = elements.nextElement()).getName();
                if (i == 0) {
                    final String s = stringAttribute;
                    final String s2 = name;
                    final String s3 = s;
                    if (this.a.contains(s2)) {
                        throw new RuntimeException("Duplicated language entry (" + s3 + ", " + s2 + ")");
                    }
                    this.a.add(s2);
                }
                final String d = ap.d(xmlElement3.getContent());
                final String s4 = stringAttribute;
                final String s5 = name;
                String s6 = d;
                final String s7 = s5;
                final String s8 = s4;
                if (s6.contains("\\h")) {
                    s6 = "<HTML>" + s6.replace("\\h", "") + "</HTML>";
                }
                if (s6.contains("\\n")) {
                    if (!s6.startsWith("<HTML")) {
                        s6 = "<HTML>" + s6 + "</HTML>";
                    }
                    s6 = s6.replace("\\n", "<BR>");
                }
                ArrayList<?> list;
                if (!this.b.containsKey(s8)) {
                    list = new ArrayList<Object>(this.a.size());
                    this.b.put(s8, list);
                }
                else {
                    list = this.b.get(s8);
                }
                list.add(this.a(s7), s6);
            }
        }
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public final int b() {
        return this.b.size();
    }
    
    private int a(final String s) {
        if (!this.a.contains(s)) {
            throw new RuntimeException("Unexpected language: " + s);
        }
        return this.a.indexOf(s);
    }
    
    public final String a(String substring, final String s) {
        if (substring.startsWith("$")) {
            substring = substring.substring(1);
        }
        if (!this.b.containsKey(substring)) {
            throw new RuntimeException("String table: unknown key: " + substring);
        }
        return this.b.get(substring).get(this.a(s));
    }
}
