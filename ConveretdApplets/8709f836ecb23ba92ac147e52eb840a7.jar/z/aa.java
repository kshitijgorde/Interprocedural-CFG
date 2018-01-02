// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import nanoxml.XMLElement;

public class aa
{
    private static /* synthetic */ boolean a;
    
    public static XMLElement a(final String s) {
        if (!aa.a && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final XMLElement xmlElement;
        (xmlElement = new XMLElement()).parseString(s);
        return xmlElement;
    }
    
    public static XMLElement a(final InputStream inputStream) {
        final XMLElement xmlElement = new XMLElement();
        System.out.println(inputStream);
        xmlElement.parseFromReader((Reader)new InputStreamReader(inputStream));
        return xmlElement;
    }
    
    public static List a(final XMLElement xmlElement, final String s) {
        if (!aa.a && s.length() <= 0) {
            throw new AssertionError();
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<XMLElement> iterator = b(xmlElement, s).iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getContent());
        }
        return list;
    }
    
    public static List b(final XMLElement xmlElement, final String s) {
        if (!aa.a && s.length() <= 0) {
            throw new AssertionError();
        }
        final ArrayList list = new ArrayList();
        a(xmlElement, s, list);
        return list;
    }
    
    private static void a(final XMLElement xmlElement, final String s, final List list) {
        if (xmlElement == null) {
            return;
        }
        final String[] split;
        final String[] array = split = s.split("/");
        int i = 0;
        while (true) {
            while (i < split.length) {
                if (split[i].length() > 0) {
                    final int n = i;
                    final int n2 = n;
                    if (!aa.a && array[n2].equals("text()")) {
                        throw new AssertionError();
                    }
                    if (n2 == -1) {
                        return;
                    }
                    final boolean b = n2 == 0;
                    final boolean b2 = n2 == array.length - 1;
                    if (xmlElement.getName().equals(array[n2])) {
                        if (b2) {
                            list.add(xmlElement);
                        }
                        else {
                            final Iterator iterator = e(xmlElement, array[n2 + 1]).iterator();
                            while (iterator.hasNext()) {
                                a(iterator.next(), a(array), list);
                            }
                        }
                    }
                    if (!b) {
                        final Enumeration enumerateChildren = xmlElement.enumerateChildren();
                        while (enumerateChildren.hasMoreElements()) {
                            a(enumerateChildren.nextElement(), s, list);
                        }
                    }
                    return;
                }
                else {
                    ++i;
                }
            }
            final int n = -1;
            continue;
        }
    }
    
    private static List e(final XMLElement xmlElement, final String s) {
        final ArrayList<XMLElement> list = new ArrayList<XMLElement>();
        final Enumeration<XMLElement> elements = xmlElement.getChildren().elements();
        while (elements.hasMoreElements()) {
            final XMLElement nextElement;
            final XMLElement xmlElement2;
            if ((nextElement = elements.nextElement()) instanceof XMLElement && (xmlElement2 = nextElement).getName().equals(s)) {
                list.add(xmlElement2);
            }
        }
        return list;
    }
    
    public static XMLElement c(final XMLElement xmlElement, final String s) {
        if (!aa.a && xmlElement == null) {
            throw new AssertionError();
        }
        if (!aa.a && s == null) {
            throw new AssertionError();
        }
        final List b;
        if ((b = b(xmlElement, s)).size() > 0) {
            return b.get(0);
        }
        return null;
    }
    
    private static String a(final String[] array) {
        final StringBuilder sb = new StringBuilder();
        int n = 1;
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].length() > 0) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(array[i] + "/");
                }
            }
        }
        sb.append(array[array.length - 1]);
        return sb.toString();
    }
    
    public static XMLElement d(final XMLElement xmlElement, final String s) {
        final Enumeration<XMLElement> elements = xmlElement.getChildren().elements();
        while (elements.hasMoreElements()) {
            final XMLElement nextElement;
            final XMLElement xmlElement2;
            if ((nextElement = elements.nextElement()) instanceof XMLElement && (xmlElement2 = nextElement).getName().equals(s)) {
                return xmlElement2;
            }
        }
        return null;
    }
    
    static {
        aa.a = !aa.class.desiredAssertionStatus();
    }
}
