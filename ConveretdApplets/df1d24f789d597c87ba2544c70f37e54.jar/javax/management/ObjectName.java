// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import org.jboss.mx.util.QueryExpSupport;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.io.ObjectStreamField;
import org.jboss.mx.util.ObjectNamePatternHelper;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.Serializable;

public class ObjectName implements Serializable, QueryExp
{
    private transient boolean hasPattern;
    private transient boolean hasDomainPattern;
    private transient boolean hasPropertyPattern;
    private transient Hashtable propertiesHash;
    private transient ArrayList propertiesList;
    private transient String domain;
    private transient String kProps;
    private transient String ckProps;
    private transient String cName;
    private transient String constructorProps;
    private transient int hash;
    private transient ObjectNamePatternHelper.PropertyPattern propertyPattern;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public static ObjectName getInstance(final String name) throws MalformedObjectNameException, NullPointerException {
        return new ObjectName(name);
    }
    
    public static ObjectName getInstance(final String domain, final String key, final String value) throws MalformedObjectNameException, NullPointerException {
        return new ObjectName(domain, key, value);
    }
    
    public static ObjectName getInstance(final String domain, final Hashtable table) throws MalformedObjectNameException, NullPointerException {
        return new ObjectName(domain, table);
    }
    
    public static ObjectName getInstance(final ObjectName name) throws NullPointerException {
        return name;
    }
    
    public static String quote(final String value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("null value");
        }
        final StringBuffer buffer = new StringBuffer(value.length() + 10);
        buffer.append('\"');
        final char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            switch (chars[i]) {
                case '\"':
                case '*':
                case '?':
                case '\\': {
                    buffer.append('\\').append(chars[i]);
                    break;
                }
                case '\n': {
                    buffer.append('\\').append('n');
                    break;
                }
                default: {
                    buffer.append(chars[i]);
                    break;
                }
            }
        }
        buffer.append('\"');
        return buffer.toString();
    }
    
    public static String unquote(final String value) throws IllegalArgumentException, NullPointerException {
        if (value == null) {
            throw new NullPointerException("null value");
        }
        if (value.length() == 0) {
            throw new IllegalArgumentException("Empty value");
        }
        final StringBuffer buffer = new StringBuffer(value.length());
        final char[] chars = value.toCharArray();
        boolean inQuote = false;
        boolean escape = false;
        for (int i = 0; i < chars.length; ++i) {
            final char c = chars[i];
            if (escape) {
                switch (c) {
                    case '\"':
                    case '*':
                    case '?':
                    case '\\': {
                        escape = false;
                        buffer.append(c);
                        break;
                    }
                    case 'n': {
                        escape = false;
                        buffer.append('\n');
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("The value " + value + " contains an invalid escape sequence backslash " + c);
                    }
                }
            }
            else if (!inQuote && c == '\"') {
                inQuote = true;
            }
            else if (inQuote && c == '\"') {
                inQuote = false;
            }
            else if (inQuote && c == '\\') {
                escape = true;
            }
            else {
                if (!inQuote) {
                    throw new IllegalArgumentException("The value " + value + " is not enclosed in quotes");
                }
                switch (c) {
                    case '\"': {
                        throw new IllegalArgumentException("The value " + value + " cannot contain quote inside a quote pair, use backslash quote");
                    }
                    case '*':
                    case '?': {
                        throw new IllegalArgumentException("The value " + value + " cannot contain " + c + " inside a quote pair, use backslash " + c);
                    }
                    case '\n': {
                        throw new IllegalArgumentException("The value " + value + " cannot contain a new line inside a quote pair, use backslash n");
                    }
                    default: {
                        buffer.append(c);
                        break;
                    }
                }
            }
        }
        if (inQuote) {
            throw new IllegalArgumentException("Unterminated quote pair, missing quote");
        }
        if (escape) {
            throw new IllegalArgumentException("Unterminated escape, missing one of backslash quote asterisk question mark or n");
        }
        return buffer.toString();
    }
    
    public ObjectName(final String name) throws MalformedObjectNameException, NullPointerException {
        this.hasPattern = false;
        this.hasDomainPattern = false;
        this.hasPropertyPattern = false;
        this.propertiesHash = null;
        this.propertiesList = null;
        this.domain = null;
        this.kProps = null;
        this.ckProps = null;
        this.cName = null;
        this.init(name);
    }
    
    public ObjectName(final String domain, final String key, final String value) throws MalformedObjectNameException, NullPointerException {
        this.hasPattern = false;
        this.hasDomainPattern = false;
        this.hasPropertyPattern = false;
        this.propertiesHash = null;
        this.propertiesList = null;
        this.domain = null;
        this.kProps = null;
        this.ckProps = null;
        this.cName = null;
        if (null == key) {
            throw new NullPointerException("properties key cannot be null");
        }
        if (null == value) {
            throw new NullPointerException("properties value cannot be null");
        }
        if (key.length() == 0) {
            throw new MalformedObjectNameException("properties key cannot be empty");
        }
        if (value.length() == 0) {
            throw new MalformedObjectNameException("properties value cannot be empty");
        }
        this.initDomain(domain, null);
        final Hashtable ptable = new Hashtable();
        ptable.put(key, value);
        this.initProperties(ptable, null);
    }
    
    public ObjectName(final String domain, final Hashtable table) throws MalformedObjectNameException, NullPointerException {
        this.hasPattern = false;
        this.hasDomainPattern = false;
        this.hasPropertyPattern = false;
        this.propertiesHash = null;
        this.propertiesList = null;
        this.domain = null;
        this.kProps = null;
        this.ckProps = null;
        this.cName = null;
        if (table == null) {
            throw new NullPointerException("null table");
        }
        this.initDomain(domain, null);
        if (table.size() < 1) {
            throw new MalformedObjectNameException("empty table");
        }
        this.initProperties((Hashtable)table.clone(), null);
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object instanceof ObjectName) {
            final ObjectName oname = (ObjectName)object;
            return oname.hash == this.hash && this.domain.equals(oname.domain) && this.ckProps.equals(oname.ckProps);
        }
        return false;
    }
    
    public int hashCode() {
        return this.hash;
    }
    
    public String toString() {
        return this.getCanonicalName();
    }
    
    public boolean isPattern() {
        return this.hasPattern;
    }
    
    public String getCanonicalName() {
        if (this.cName == null) {
            this.cName = this.domain + ":" + this.ckProps;
            if (this.ckProps.length() == 0 && this.hasPropertyPattern) {
                this.cName += "*";
            }
            if (this.ckProps.length() > 0 && this.hasPropertyPattern) {
                this.cName += ",*";
            }
        }
        return this.cName;
    }
    
    public String getDomain() {
        return this.domain;
    }
    
    public String getKeyProperty(final String property) throws NullPointerException {
        return this.propertiesHash.get(property);
    }
    
    public Hashtable getKeyPropertyList() {
        return (Hashtable)this.propertiesHash.clone();
    }
    
    public String getKeyPropertyListString() {
        if (this.constructorProps != null) {
            return this.constructorProps;
        }
        if (this.kProps == null) {
            final StringBuffer buffer = new StringBuffer();
            Iterator it = this.propertiesHash.keySet().iterator();
            if (this.propertiesList != null) {
                it = this.propertiesList.iterator();
            }
            int i = 0;
            while (it.hasNext()) {
                if (i > 0) {
                    buffer.append(",");
                }
                final String key = it.next();
                final String val = this.propertiesHash.get(key);
                buffer.append(key + "=" + val);
                ++i;
            }
            this.kProps = buffer.toString();
        }
        return this.kProps;
    }
    
    public String getCanonicalKeyPropertyListString() {
        if (this.ckProps == null) {
            final StringBuffer buffer = new StringBuffer();
            final ArrayList list = new ArrayList(this.propertiesHash.keySet());
            Collections.sort((List<Comparable>)list);
            final Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (i > 0) {
                    buffer.append(",");
                }
                final String key = it.next();
                final String val = this.propertiesHash.get(key);
                buffer.append(key + "=" + val);
                ++i;
            }
            this.ckProps = buffer.toString();
        }
        return this.ckProps;
    }
    
    public boolean isPropertyPattern() {
        return this.hasPropertyPattern;
    }
    
    public boolean isDomainPattern() {
        return this.hasDomainPattern;
    }
    
    public boolean apply(final ObjectName name) throws NullPointerException {
        if (name.isPattern()) {
            return false;
        }
        if (this == name) {
            return true;
        }
        if (ObjectNamePatternHelper.patternMatch(name.getDomain(), this.getDomain())) {
            if (this.propertyPattern == null) {
                this.propertyPattern = new ObjectNamePatternHelper.PropertyPattern(this);
            }
            return this.propertyPattern.patternMatch(name);
        }
        return false;
    }
    
    public void setMBeanServer(final MBeanServer server) {
        QueryExpSupport.server.set(server);
    }
    
    private void init(String name) throws MalformedObjectNameException {
        if (name == null) {
            throw new NullPointerException("null name");
        }
        if (name.length() == 0) {
            name = "*:*";
        }
        final int domainSep = name.indexOf(58);
        if (-1 == domainSep) {
            throw new MalformedObjectNameException("missing domain");
        }
        this.initDomain(name.substring(0, domainSep), name);
        this.initProperties(this.constructorProps = name.substring(domainSep + 1), name);
        if (this.constructorProps.equals("*")) {
            this.constructorProps = "";
        }
        else if (this.constructorProps.startsWith("*,")) {
            this.constructorProps = this.constructorProps.substring(2);
        }
        else if (this.constructorProps.endsWith(",*")) {
            this.constructorProps = this.constructorProps.substring(0, this.constructorProps.length() - 2);
        }
    }
    
    private void initDomain(final String dstring, final String name) throws MalformedObjectNameException {
        if (null == dstring) {
            throw new NullPointerException("null domain");
        }
        this.checkIllegalDomain(dstring, name);
        if (dstring.indexOf(42) > -1 || dstring.indexOf(63) > -1) {
            this.hasPattern = true;
            this.hasDomainPattern = true;
        }
        this.domain = dstring;
    }
    
    private void initProperties(final String properties, final String name) throws MalformedObjectNameException {
        if (null == properties || properties.length() < 1) {
            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "null or empty properties");
        }
        final Hashtable ptable = new Hashtable();
        this.propertiesList = new ArrayList();
        final char[] chars = properties.toCharArray();
        String key = null;
        int start = 0;
        boolean inKey = true;
        boolean inQuote = false;
        boolean escape = false;
        boolean endOrTerminate = false;
        for (int current = 0; current < chars.length; ++current) {
            final char c = chars[current];
            if (escape) {
                escape = false;
            }
            else if (inQuote) {
                if (c == '\"') {
                    inQuote = false;
                    endOrTerminate = true;
                }
                else if (c == '\\') {
                    escape = true;
                }
            }
            else if (inKey) {
                if (c == '=' || c == ',') {
                    if (current == start) {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Empty key");
                    }
                    key = properties.substring(start, current);
                    if (c == ',') {
                        if (!key.equals("*")) {
                            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Invalid key/value data " + key);
                        }
                        if (this.hasPropertyPattern) {
                            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "A property pattern may only contain one * property");
                        }
                        this.hasPropertyPattern = true;
                        this.hasPattern = true;
                    }
                    else {
                        if (ptable.containsKey(key)) {
                            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Duplicate key " + key);
                        }
                        inKey = false;
                    }
                    start = current + 1;
                }
            }
            else if (c == '\"') {
                inQuote = true;
            }
            else if (c == ',') {
                if (current == start) {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Invalid key/value data " + key);
                }
                this.propertiesList.add(key);
                ptable.put(key, properties.substring(start, current));
                inKey = true;
                endOrTerminate = false;
                start = current + 1;
            }
            else if (endOrTerminate) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Invalid key/value data " + key);
            }
        }
        if (inKey) {
            if (start == chars.length) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "An ObjectName cannot end with a comma");
            }
            key = properties.substring(start, chars.length);
            if (!key.equals("*")) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Invalid key/value data " + key);
            }
            if (this.hasPropertyPattern) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "A property pattern may only contain one * property");
            }
            this.hasPropertyPattern = true;
            this.hasPattern = true;
        }
        if (!inKey) {
            if (start == chars.length) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Invalid key/value data " + key);
            }
            this.propertiesList.add(key);
            ptable.put(key, properties.substring(start, chars.length));
        }
        this.initProperties(ptable, name);
    }
    
    private void initProperties(final Hashtable properties, final String name) throws MalformedObjectNameException {
        if (null == properties || (!this.hasPropertyPattern && properties.size() < 1)) {
            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "null or empty properties");
        }
        Iterator it = properties.keySet().iterator();
        final ArrayList list = new ArrayList();
        while (it.hasNext()) {
            String key = null;
            try {
                key = it.next();
            }
            catch (ClassCastException e) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "key is not a string " + key);
            }
            String val = null;
            try {
                val = properties.get(key);
            }
            catch (ClassCastException e2) {
                throw new MalformedObjectNameException(this.addDebugObjectName(name) + "value is not a string " + val);
            }
            if (key.equals("*") && val.equals("*")) {
                it.remove();
                this.hasPropertyPattern = true;
                this.hasPattern = true;
            }
            else {
                if (key.length() == 0) {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "key has no length =" + val);
                }
                if (val.length() == 0) {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "value has no length =" + val);
                }
                this.checkIllegalKey(key, name);
                this.checkIllegalValue(val, name);
                list.add(new String(key + "=" + val));
            }
        }
        Collections.sort((List<Comparable>)list);
        final StringBuffer strBuffer = new StringBuffer();
        it = list.iterator();
        while (it.hasNext()) {
            strBuffer.append(it.next());
            if (it.hasNext()) {
                strBuffer.append(',');
            }
        }
        if (this.hasPropertyPattern) {
            if (properties.size() > 0) {
                strBuffer.append(",*");
            }
            else {
                strBuffer.append("*");
            }
        }
        this.propertiesHash = properties;
        this.kProps = this.getKeyPropertyListString();
        this.ckProps = this.getCanonicalKeyPropertyListString();
        this.hash = this.getCanonicalName().hashCode();
    }
    
    private void checkIllegalKey(final String key, final String name) throws MalformedObjectNameException {
        final char[] chars = key.toCharArray();
        int i = 0;
        while (i < chars.length) {
            switch (chars[i]) {
                case '*':
                case ',':
                case ':':
                case '=':
                case '?': {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The key " + key + " cannot contain a " + chars[i] + " character");
                }
                case '\n': {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The key " + key + " cannot contain a new line character");
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
    }
    
    private void checkIllegalValue(final String value, final String name) throws MalformedObjectNameException {
        final char[] chars = value.toCharArray();
        boolean inQuote = false;
        boolean escape = false;
        for (int i = 0; i < chars.length; ++i) {
            final char c = chars[i];
            if (escape) {
                switch (c) {
                    case '\"':
                    case '*':
                    case '?':
                    case '\\':
                    case 'n': {
                        escape = false;
                        break;
                    }
                    default: {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " contains an invalid escape sequence backslash " + c);
                    }
                }
            }
            else if (!inQuote && c == '\"') {
                inQuote = true;
            }
            else if (inQuote && c == '\"') {
                inQuote = false;
            }
            else if (inQuote && c == '\\') {
                escape = true;
            }
            else if (inQuote) {
                switch (c) {
                    case '\"': {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " cannot contain quote inside a quote pair, use backslash quote");
                    }
                    case '*':
                    case '?': {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " cannot contain " + c + " inside a quote pair, use backslash " + c);
                    }
                    case '\n': {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " cannot contain a new line inside a quote pair, use backslash n");
                    }
                }
            }
            else {
                switch (c) {
                    case '*':
                    case ',':
                    case ':':
                    case '=':
                    case '?': {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " cannot contain " + c + " use quote backslash " + c + " quote or ObjectName.quote(String)");
                    }
                    case '\n': {
                        throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The value " + value + " cannot contain a new line use quote backslash n quote or ObjectName.quote(String)");
                    }
                }
            }
        }
        if (inQuote) {
            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Unterminated quote pair, missing quote");
        }
        if (escape) {
            throw new MalformedObjectNameException(this.addDebugObjectName(name) + "Unterminated escape, missing one of backslash quote asterisk question mark or n");
        }
    }
    
    private void checkIllegalDomain(final String dom, final String name) throws MalformedObjectNameException {
        final char[] chars = dom.toCharArray();
        int i = 0;
        while (i < chars.length) {
            switch (chars[i]) {
                case ':': {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The domain " + dom + " cannot contain a : character");
                }
                case '\n': {
                    throw new MalformedObjectNameException(this.addDebugObjectName(name) + "The domain " + dom + " cannot contain a new line character");
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
    }
    
    private String addDebugObjectName(final String name) {
        if (name == null) {
            return "";
        }
        return name + " is not a valid ObjectName. ";
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                try {
                    final String name = (String)getField.get("canonicalName", null);
                    if (name == null) {
                        throw new StreamCorruptedException("No canonical name for jmx1.0?");
                    }
                    this.init(name);
                    return;
                }
                catch (MalformedObjectNameException e) {
                    throw new StreamCorruptedException(e.toString());
                }
                break;
            }
        }
        try {
            this.init((String)ois.readObject());
        }
        catch (MalformedObjectNameException e) {
            throw new StreamCorruptedException(e.toString());
        }
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        switch (Serialization.version) {
            case 10: {
                final ObjectOutputStream.PutField putField = oos.putFields();
                putField.put("domain", this.domain);
                putField.put("propertyList", this.propertiesHash);
                putField.put("propertyListString", this.ckProps);
                putField.put("canonicalName", this.domain + ":" + this.ckProps);
                putField.put("pattern", this.hasPattern);
                putField.put("propertyPattern", this.hasPropertyPattern);
                oos.writeFields();
                break;
            }
            default: {
                final String props = this.getKeyPropertyListString();
                final StringBuffer buffer = new StringBuffer(this.domain.length() + props.length() + 3);
                buffer.append(this.domain);
                buffer.append(':');
                buffer.append(props);
                if (this.hasPropertyPattern) {
                    if (this.propertiesHash.size() > 0) {
                        buffer.append(',');
                    }
                    buffer.append('*');
                }
                oos.writeObject(buffer.toString());
                break;
            }
        }
    }
    
    static {
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = -5467795090068647408L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("domain", String.class), new ObjectStreamField("propertyList", Hashtable.class), new ObjectStreamField("propertyListString", String.class), new ObjectStreamField("canonicalName", String.class), new ObjectStreamField("pattern", Boolean.TYPE), new ObjectStreamField("propertyPattern", Boolean.TYPE) };
                break;
            }
            default: {
                serialVersionUID = 1081892073854801359L;
                serialPersistentFields = new ObjectStreamField[0];
                break;
            }
        }
    }
}
