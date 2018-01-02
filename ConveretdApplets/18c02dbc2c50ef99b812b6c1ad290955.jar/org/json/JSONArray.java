// 
// Decompiled by Procyon v0.5.30
// 

package org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;

public class JSONArray
{
    private ArrayList myArrayList;
    
    public JSONArray() {
        this.myArrayList = new ArrayList();
    }
    
    public JSONArray(final JSONTokener jsonTokener) throws JSONException {
        this();
        final char nextClean = jsonTokener.nextClean();
        char c;
        if (nextClean == '[') {
            c = ']';
        }
        else {
            if (nextClean != '(') {
                throw jsonTokener.syntaxError("A JSONArray text must start with '['");
            }
            c = ')';
        }
        if (jsonTokener.nextClean() == ']') {
            return;
        }
        jsonTokener.back();
        while (true) {
            if (jsonTokener.nextClean() == ',') {
                jsonTokener.back();
                this.myArrayList.add(null);
            }
            else {
                jsonTokener.back();
                this.myArrayList.add(jsonTokener.nextValue());
            }
            final char nextClean2 = jsonTokener.nextClean();
            switch (nextClean2) {
                case 44:
                case 59: {
                    if (jsonTokener.nextClean() == ']') {
                        return;
                    }
                    jsonTokener.back();
                    continue;
                }
                case 41:
                case 93: {
                    if (c != nextClean2) {
                        throw jsonTokener.syntaxError("Expected a '" + new Character(c) + "'");
                    }
                }
                default: {
                    throw jsonTokener.syntaxError("Expected a ',' or ']'");
                }
            }
        }
    }
    
    public JSONArray(final String s) throws JSONException {
        this(new JSONTokener(s));
    }
    
    public JSONArray(final Collection collection) {
        this.myArrayList = ((collection == null) ? new ArrayList() : new ArrayList(collection));
    }
    
    public JSONArray(final Collection collection, final boolean b) {
        this.myArrayList = new ArrayList();
        if (collection != null) {
            for (final Map next : collection) {
                if (next instanceof Map) {
                    this.myArrayList.add(new JSONObject(next, b));
                }
                else if (!JSONObject.isStandardProperty(next.getClass())) {
                    this.myArrayList.add(new JSONObject((Object)next, b));
                }
                else {
                    this.myArrayList.add(next);
                }
            }
        }
    }
    
    public JSONArray(final Object o) throws JSONException {
        this();
        if (o.getClass().isArray()) {
            for (int length = Array.getLength(o), i = 0; i < length; ++i) {
                this.put(Array.get(o, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
    
    public JSONArray(final Object o, final boolean b) throws JSONException {
        this();
        if (o.getClass().isArray()) {
            for (int length = Array.getLength(o), i = 0; i < length; ++i) {
                final Object value = Array.get(o, i);
                if (JSONObject.isStandardProperty(value.getClass())) {
                    this.myArrayList.add(value);
                }
                else {
                    this.myArrayList.add(new JSONObject(value, b));
                }
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
    
    public Object get(final int n) throws JSONException {
        final Object opt = this.opt(n);
        if (opt == null) {
            throw new JSONException("JSONArray[" + n + "] not found.");
        }
        return opt;
    }
    
    public boolean getBoolean(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value.equals(Boolean.FALSE) || (value instanceof String && ((String)value).equalsIgnoreCase("false"))) {
            return false;
        }
        if (value.equals(Boolean.TRUE) || (value instanceof String && ((String)value).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONArray[" + n + "] is not a Boolean.");
    }
    
    public double getDouble(final int n) throws JSONException {
        final Object value = this.get(n);
        try {
            return (value instanceof Number) ? ((Number)value).doubleValue() : Double.valueOf((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONArray[" + n + "] is not a number.");
        }
    }
    
    public int getInt(final int n) throws JSONException {
        final Object value = this.get(n);
        return (value instanceof Number) ? ((Number)value).intValue() : ((int)this.getDouble(n));
    }
    
    public JSONArray getJSONArray(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONArray.");
    }
    
    public JSONObject getJSONObject(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONObject.");
    }
    
    public long getLong(final int n) throws JSONException {
        final Object value = this.get(n);
        return (value instanceof Number) ? ((Number)value).longValue() : ((long)this.getDouble(n));
    }
    
    public String getString(final int n) throws JSONException {
        return this.get(n).toString();
    }
    
    public boolean isNull(final int n) {
        return JSONObject.NULL.equals(this.opt(n));
    }
    
    public String join(final String s) throws JSONException {
        final int length = this.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }
    
    public int length() {
        return this.myArrayList.size();
    }
    
    public Object opt(final int n) {
        return (n < 0 || n >= this.length()) ? null : this.myArrayList.get(n);
    }
    
    public boolean optBoolean(final int n) {
        return this.optBoolean(n, false);
    }
    
    public boolean optBoolean(final int n, final boolean b) {
        try {
            return this.getBoolean(n);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public double optDouble(final int n) {
        return this.optDouble(n, Double.NaN);
    }
    
    public double optDouble(final int n, final double n2) {
        try {
            return this.getDouble(n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public int optInt(final int n) {
        return this.optInt(n, 0);
    }
    
    public int optInt(final int n, final int n2) {
        try {
            return this.getInt(n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public JSONArray optJSONArray(final int n) {
        final Object opt = this.opt(n);
        return (opt instanceof JSONArray) ? ((JSONArray)opt) : null;
    }
    
    public JSONObject optJSONObject(final int n) {
        final Object opt = this.opt(n);
        return (opt instanceof JSONObject) ? ((JSONObject)opt) : null;
    }
    
    public long optLong(final int n) {
        return this.optLong(n, 0L);
    }
    
    public long optLong(final int n, final long n2) {
        try {
            return this.getLong(n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public String optString(final int n) {
        return this.optString(n, "");
    }
    
    public String optString(final int n, final String s) {
        final Object opt = this.opt(n);
        return (opt != null) ? opt.toString() : s;
    }
    
    public JSONArray put(final boolean b) {
        this.put(b ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }
    
    public JSONArray put(final Collection collection) {
        this.put(new JSONArray(collection));
        return this;
    }
    
    public JSONArray put(final double n) throws JSONException {
        final Double n2 = new Double(n);
        JSONObject.testValidity(n2);
        this.put(n2);
        return this;
    }
    
    public JSONArray put(final int n) {
        this.put(new Integer(n));
        return this;
    }
    
    public JSONArray put(final long n) {
        this.put(new Long(n));
        return this;
    }
    
    public JSONArray put(final Map map) {
        this.put(new JSONObject(map));
        return this;
    }
    
    public JSONArray put(final Object o) {
        this.myArrayList.add(o);
        return this;
    }
    
    public JSONArray put(final int n, final boolean b) throws JSONException {
        this.put(n, b ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }
    
    public JSONArray put(final int n, final Collection collection) throws JSONException {
        this.put(n, new JSONArray(collection));
        return this;
    }
    
    public JSONArray put(final int n, final double n2) throws JSONException {
        this.put(n, new Double(n2));
        return this;
    }
    
    public JSONArray put(final int n, final int n2) throws JSONException {
        this.put(n, new Integer(n2));
        return this;
    }
    
    public JSONArray put(final int n, final long n2) throws JSONException {
        this.put(n, new Long(n2));
        return this;
    }
    
    public JSONArray put(final int n, final Map map) throws JSONException {
        this.put(n, new JSONObject(map));
        return this;
    }
    
    public JSONArray put(final int i, final Object o) throws JSONException {
        JSONObject.testValidity(o);
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < this.length()) {
            this.myArrayList.set(i, o);
        }
        else {
            while (i != this.length()) {
                this.put(JSONObject.NULL);
            }
            this.put(o);
        }
        return this;
    }
    
    public Object remove(final int n) {
        final Object opt = this.opt(n);
        this.myArrayList.remove(n);
        return opt;
    }
    
    public JSONObject toJSONObject(final JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0 || this.length() == 0) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); ++i) {
            jsonObject.put(jsonArray.getString(i), this.opt(i));
        }
        return jsonObject;
    }
    
    public String toString() {
        try {
            return '[' + this.join(",") + ']';
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String toString(final int n) throws JSONException {
        return this.toString(n, 0);
    }
    
    String toString(final int n, final int n2) throws JSONException {
        final int length = this.length();
        if (length == 0) {
            return "[]";
        }
        final StringBuffer sb = new StringBuffer("[");
        if (length == 1) {
            sb.append(JSONObject.valueToString(this.myArrayList.get(0), n, n2));
        }
        else {
            final int n3 = n2 + n;
            sb.append('\n');
            for (int i = 0; i < length; ++i) {
                if (i > 0) {
                    sb.append(",\n");
                }
                for (int j = 0; j < n3; ++j) {
                    sb.append(' ');
                }
                sb.append(JSONObject.valueToString(this.myArrayList.get(i), n, n3));
            }
            sb.append('\n');
            for (int k = 0; k < n2; ++k) {
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    public Writer write(final Writer writer) throws JSONException {
        try {
            int n = 0;
            final int length = this.length();
            writer.write(91);
            for (int i = 0; i < length; ++i) {
                if (n != 0) {
                    writer.write(44);
                }
                final Object value = this.myArrayList.get(i);
                if (value instanceof JSONObject) {
                    ((JSONObject)value).write(writer);
                }
                else if (value instanceof JSONArray) {
                    ((JSONArray)value).write(writer);
                }
                else {
                    writer.write(JSONObject.valueToString(value));
                }
                n = 1;
            }
            writer.write(93);
            return writer;
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
    }
}
