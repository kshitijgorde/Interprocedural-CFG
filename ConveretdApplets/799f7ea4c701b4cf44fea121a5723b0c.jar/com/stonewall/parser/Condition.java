// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.util.Iterator;
import org.jdom.Attribute;
import org.jdom.xpath.XPath;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import org.xmodel.log.Log;
import java.util.Set;

public class Condition
{
    static String[] tagFilter;
    static Set<String> As;
    static Set<String> Bs;
    Action action;
    static Log log;
    
    static {
        Condition.tagFilter = new String[] { "action", "mapping", "dictionary", "model" };
        Condition.As = new HashSet<String>();
        Condition.Bs = new HashSet<String>();
        A[] values;
        for (int length = (values = A.values()).length, i = 0; i < length; ++i) {
            final A a = values[i];
            Condition.As.add(a.name());
        }
        B[] values2;
        for (int length2 = (values2 = B.values()).length, j = 0; j < length2; ++j) {
            final B b = values2[j];
            Condition.Bs.add(b.name());
        }
        Condition.log = Log.getLog(Condition.class);
    }
    
    Condition() {
        this.action = null;
    }
    
    boolean evaluate(final Action action) {
        this.action = action;
        boolean result = true;
        final String[] a = this.getAttr(Condition.As);
        switch (A.valueOf(a[0])) {
            case test: {
                result = this.test(a[1]);
                break;
            }
            case text: {
                result = this.text(a[1]);
                break;
            }
            case path: {
                result = this.path(a[1]);
                break;
            }
            case nopath: {
                result = this.nopath(a[1]);
                break;
            }
        }
        return result;
    }
    
    boolean text(final String a) {
        boolean result = false;
        final String[] b = this.getAttr(Condition.Bs);
        switch (B.valueOf(b[0])) {
            case eq: {
                result = this.eq(a, b[1]);
                break;
            }
            case neq: {
                result = this.neq(a, b[1]);
                break;
            }
            case lt: {
                result = this.lt(a, b[1]);
                break;
            }
            case gt: {
                result = this.gt(a, b[1]);
                break;
            }
            case match: {
                result = this.match(a, b[1]);
                break;
            }
            case find: {
                result = this.find(a, b[1]);
                break;
            }
            case contains: {
                result = this.contains(a, b[1]);
                break;
            }
            case startswith: {
                result = this.startswith(a, b[1]);
                break;
            }
            case endswith: {
                result = this.endswith(a, b[1]);
                break;
            }
        }
        return result;
    }
    
    boolean test(final String a) {
        return Boolean.parseBoolean(a.toLowerCase());
    }
    
    boolean eq(final String s, final String rhs) {
        return s.equals(rhs);
    }
    
    boolean neq(final String s, final String rhs) {
        return !this.eq(s, rhs);
    }
    
    boolean gt(final String s, final String rhs) {
        try {
            final int left = Integer.valueOf(s);
            final int right = Integer.valueOf(rhs);
            return left > right;
        }
        catch (Exception e) {
            Condition.log.debug(String.valueOf(s) + "," + rhs, e);
            return false;
        }
    }
    
    boolean lt(final String s, final String rhs) {
        try {
            final int left = Integer.valueOf(s);
            final int right = Integer.valueOf(rhs);
            return left < right;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    boolean contains(final String s, final String rhs) {
        return s.contains(rhs);
    }
    
    boolean startswith(final String s, final String rhs) {
        return s.startsWith(rhs);
    }
    
    boolean endswith(final String s, final String rhs) {
        return s.endsWith(rhs);
    }
    
    boolean match(final String s, final String rhs) {
        try {
            final Pattern p = this.context().patternStore().get(rhs);
            final Matcher m = p.matcher(s);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }
    }
    
    boolean find(final String s, final String rhs) {
        try {
            final Pattern p = this.context().patternStore().get(rhs);
            final Matcher m = p.matcher(s);
            return m.find();
        }
        catch (Exception e) {
            return false;
        }
    }
    
    boolean path(final String path) {
        boolean result = false;
        try {
            final XPath xpath = this.context().pathStore().get(path, (Map<String, String>)this.context().dynamicReferences());
            result = (xpath.selectSingleNode((Object)this.context().model()) != null);
            Condition.log.debug(String.valueOf(this.action.id()) + "\n\tpath=(" + path + ") result: " + result);
        }
        catch (Exception e) {
            final String msg = String.valueOf(this.action.id()) + "\n\tpath=(" + path + ")";
            Condition.log.error(msg, e);
        }
        return result;
    }
    
    boolean nopath(final String path) {
        return !this.path(path);
    }
    
    String[] getAttr(final Set<String> set) {
        final String[] result = { "none", "" };
        for (final Attribute a : this.root().getAttributes()) {
            final String n = a.getName();
            if (set.contains(a.getName())) {
                result[0] = n;
                result[1] = a.getValue();
                break;
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        final Format format = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(format);
        return p.outputString(this.root());
    }
    
    Context context() {
        return this.action.context();
    }
    
    Element root() {
        return this.action.root();
    }
    
    enum A
    {
        text("text", 0), 
        path("path", 1), 
        nopath("nopath", 2), 
        test("test", 3), 
        none("none", 4);
        
        private A(final String s, final int n) {
        }
    }
    
    enum B
    {
        eq("eq", 0), 
        neq("neq", 1), 
        lt("lt", 2), 
        gt("gt", 3), 
        contains("contains", 4), 
        startswith("startswith", 5), 
        endswith("endswith", 6), 
        match("match", 7), 
        find("find", 8), 
        none("none", 9);
        
        private B(final String s, final int n) {
        }
    }
    
    enum Continue
    {
        all("all", 0), 
        stop("stop", 1), 
        abort("abort", 2);
        
        private Continue(final String s, final int n) {
        }
    }
}
