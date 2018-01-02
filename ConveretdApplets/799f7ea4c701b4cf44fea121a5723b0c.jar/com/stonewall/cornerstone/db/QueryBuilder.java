// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.Date;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.utility.SecureDocument;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.utility.Encrypted;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class QueryBuilder
{
    protected static Comparator<String> lengthComparator;
    static final String _dateFormat = "yyyy-MM-dd HH:mm:ss";
    static final SimpleDateFormat dateFormat;
    protected String name;
    protected String content;
    protected List<String> functions;
    protected Map<String, String> parameters;
    protected Map<Integer, String> parameterIndexes;
    protected String[] collectionEnclosure;
    protected static final Log log;
    
    static {
        QueryBuilder.lengthComparator = new Comparator<String>() {
            @Override
            public int compare(final String a, final String b) {
                return b.length() - a.length();
            }
        };
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log = Log.getLog(QueryBuilder.class);
    }
    
    public QueryBuilder() {
        this.name = null;
        this.collectionEnclosure = new String[] { "(", ", ", ")" };
        this.parameters = null;
        this.functions = new ArrayList<String>();
        this.setContent("");
    }
    
    public QueryBuilder(final String content) {
        this();
        this.setContent(content);
    }
    
    public void addFunction(final String func) {
        this.functions.add(func);
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public void set(final String name, final String value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<String, String>();
        }
        this.parameters.put(name, (value == null) ? "null" : value.replaceAll("\\$", "\\\\\\$"));
    }
    
    public void set(final String name, Collection<String> value) {
        int n = 0;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.collectionEnclosure[0]);
        if (value.isEmpty()) {
            value = Collections.singletonList("__emptySet__");
        }
        for (final String s : value) {
            if (n > 0) {
                sb.append(this.collectionEnclosure[1]);
            }
            sb.append(s);
            ++n;
        }
        sb.append(this.collectionEnclosure[2]);
        this.set(name, sb.toString());
    }
    
    public void set(final String name, final int value) {
        this.set(name, String.valueOf(value));
    }
    
    public void set(final String name, final long value) {
        this.set(name, String.valueOf(value));
    }
    
    public void set(final String name, final boolean value) {
        this.set(name, String.valueOf(value));
    }
    
    public void set(final String name, String value, final boolean encrypt) {
        if (encrypt && value != null) {
            final Encrypted en = new Encrypted(value);
            value = en.encrypt();
        }
        this.set(name, value);
    }
    
    public void set(final String name, final IModelObject value) {
        final SecureDocument sd = new SecureDocument(value);
        sd.encrypt();
        final ModelBuilder builder = new ModelBuilder();
        final String s = builder.writeModel(value, IXmlIO.Style.printable);
        this.set(name, s);
    }
    
    public void set(final String name, final List<IModelObject> value) {
        for (final IModelObject e : value) {
            final SecureDocument sd = new SecureDocument(e);
            sd.encrypt();
        }
        final ModelBuilder builder = new ModelBuilder();
        final StringBuilder sb = new StringBuilder();
        for (final IModelObject object : value) {
            sb.append(builder.writeModel(object, IXmlIO.Style.printable));
            sb.append('\n');
        }
        this.set(name, sb.toString());
    }
    
    public void setString(final String name, final String value) {
        this.set(name, "'" + this.escape(value) + "'");
    }
    
    public void setString(final String name, String value, final boolean encrypt) {
        if (encrypt && value != null) {
            final Encrypted en = new Encrypted(value);
            value = en.encrypt();
        }
        this.setString(name, value);
    }
    
    public void setString(final String name, Collection<String> value) {
        int n = 0;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.collectionEnclosure[0]);
        if (value.isEmpty()) {
            value = Collections.singletonList("__emptySet__");
        }
        for (final String s : value) {
            if (n > 0) {
                sb.append(this.collectionEnclosure[1]);
            }
            sb.append("'");
            sb.append(s);
            sb.append("'");
            ++n;
        }
        sb.append(this.collectionEnclosure[2]);
        this.set(name, sb.toString());
    }
    
    public void setString(final String name, Collection<String> value, final boolean encrypt) {
        int n = 0;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.collectionEnclosure[0]);
        if (value.isEmpty()) {
            value = Collections.singletonList("__emptySet__");
        }
        for (final String s : value) {
            if (n > 0) {
                sb.append(this.collectionEnclosure[1]);
            }
            sb.append("'");
            sb.append(encrypt ? new Encrypted(s).encrypt() : s);
            sb.append("'");
            ++n;
        }
        sb.append(this.collectionEnclosure[2]);
        this.set(name, sb.toString());
    }
    
    public void setString(final String name, final IModelObject value) {
        final SecureDocument sd = new SecureDocument(value);
        sd.encrypt();
        final ModelBuilder builder = new ModelBuilder();
        final String s = builder.writeModel(value, IXmlIO.Style.printable);
        this.setString(name, s);
    }
    
    public void setString(final String name, final Date value) {
        if (value != null) {
            this.setString(name, QueryBuilder.dateFormat.format(value));
        }
        else {
            this.setString(name, "");
        }
    }
    
    public String date() {
        return XDate.date();
    }
    
    public String time() {
        return XDate.time();
    }
    
    public String dateTime() {
        return XDate.dateTime();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getNameComment());
        sb.append(this.getFunctions());
        sb.append(this.applyParameters());
        return sb.toString();
    }
    
    public void collectionEnclosure(final String[] characters) {
        if (characters.length != 3) {
            throw new IllegalArgumentException();
        }
        this.collectionEnclosure = characters;
    }
    
    public String[] collectionEnclosure() {
        return this.collectionEnclosure;
    }
    
    protected String applyParameters() {
        if (this.content == null || this.parameters == null) {
            return this.content;
        }
        final StringBuffer sb = new StringBuffer();
        int start = 0;
        final Map<Integer, String> sortedMap = this.sortedParameters();
        for (final int index : sortedMap.keySet()) {
            final String name = sortedMap.get(index);
            final String value = this.parameters.get(name);
            final int end = index + name.length() + 1;
            final String result = this.applyParameter(this.content.substring(start, end), name, value);
            sb.append(result);
            start = end;
        }
        if (start < this.content.length()) {
            sb.append(this.content.substring(start));
        }
        return sb.toString();
    }
    
    protected Map<Integer, String> sortedParameters() {
        final List<String> list = new ArrayList<String>(this.parameters.keySet());
        final Map<Integer, String> map = new TreeMap<Integer, String>();
        for (final String name : list) {
            final Pattern p = Pattern.compile("[$]\\b" + name + "\\b");
            final Matcher m = p.matcher(this.content);
            while (m.find()) {
                map.put(m.start(), name);
            }
        }
        return map;
    }
    
    protected String applyParameter(final String s, final String name, final String value) {
        return s.replaceAll("\\$" + name, value);
    }
    
    protected String getFunctions() {
        final StringBuilder sb = new StringBuilder();
        for (final String f : this.functions) {
            sb.append(f);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    protected String getNameComment() {
        final String value = (this.name == null) ? Integer.toHexString(this.content.hashCode()) : this.name;
        return "# name=" + value + "\n";
    }
    
    protected String extractName() {
        String result = null;
        final String query = this.toString();
        final String regex = "\\# name=[a-z0-9]+";
        try {
            final Pattern p = Pattern.compile("\\# name=[a-z0-9]+");
            final Matcher m = p.matcher(query);
            while (m.find()) {
                final String s = m.group();
                result = s.split("=")[1];
            }
        }
        catch (Exception e) {
            QueryBuilder.log.error("\\# name=[a-z0-9]+", e);
        }
        return result;
    }
    
    protected void addDENS(final StringBuilder sb) {
    }
    
    protected String escape(final String s) {
        return s;
    }
}
