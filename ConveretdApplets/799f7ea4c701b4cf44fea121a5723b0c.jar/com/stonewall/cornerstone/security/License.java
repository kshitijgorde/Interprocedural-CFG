// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.zip.CRC32;
import org.jdom.xpath.XPath;
import java.util.Iterator;
import org.jdom.Namespace;
import java.util.Date;
import org.jdom.Content;
import org.jdom.Element;
import org.jdom.Document;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class License
{
    private long product;
    private long[] key;
    private long limit;
    private String name;
    private String company;
    private final Map<String, String> capabilities;
    
    public License(final long product, final int seed, final long limit) {
        this.product = 0L;
        this.key = new long[6];
        this.limit = 0L;
        this.capabilities = new HashMap<String, String>();
        this.limit = this.datePlus(limit);
        this.product = (product & 0xFFFFL);
        final long x = product | seed << 16;
        final Random r = new Random(x);
        this.key[0] = (r.nextLong() & 0x7FFFFFFFL);
        this.keyUpdated(this.key);
    }
    
    public License(final License obj) {
        this.product = 0L;
        this.key = new long[6];
        this.limit = 0L;
        this.capabilities = new HashMap<String, String>();
        this.product = obj.product;
        this.limit = obj.limit;
        this.key = obj.key;
        this.name = obj.name;
        this.company = obj.company;
        this.capabilities.clear();
        this.capabilities.putAll(obj.capabilities);
    }
    
    public License(final Document d) throws Exception {
        this.product = 0L;
        this.key = new long[6];
        this.limit = 0L;
        this.capabilities = new HashMap<String, String>();
        final Element root = d.getRootElement();
        final Element pE = root.getChild("product");
        this.product = Short.parseShort(pE.getAttributeValue("descriptor"));
        this.name = root.getChildText("name");
        this.company = root.getChildText("company");
        final String[] words = root.getChildText("key").split(" ");
        for (int i = 0; i < words.length && i < this.key.length; ++i) {
            this.key[i] = Long.parseLong(words[i], 36);
        }
        this.readCapabilities(pE);
        final String exp = root.getChild("expiration").getAttributeValue("limit");
        if (exp != null && exp.length() > 0) {
            this.limit = Long.parseLong(exp, 16);
        }
    }
    
    public Status evaluate() {
        return this.evaluate(this.product);
    }
    
    public Status evaluate(final long product) {
        return (this.validateKey() && this.validateLimit()) ? Status.Valid : Status.Invalid;
    }
    
    public boolean limited() {
        return this.limit > 0L;
    }
    
    public String[] toArray() {
        final String[] result = new String[this.key.length];
        for (int i = 0; i < this.key.length; ++i) {
            result[i] = this.encode(this.key[i]);
        }
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.key.length; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(this.encode(this.key[i]));
        }
        return sb.toString();
    }
    
    public Document getDocument() {
        final Element root = new Element("license");
        final Document d = new Document(root);
        final Element pE = new Element("product");
        pE.setAttribute("descriptor", String.valueOf(this.product));
        root.addContent((Content)pE);
        root.addContent((Content)new Element("key").setText(this.toString()));
        root.addContent((Content)new Element("name").setText(this.name));
        root.addContent((Content)new Element("company").setText(this.company));
        final Element exp = new Element("expiration").setText(this.getExpiration());
        root.addContent((Content)exp);
        if (this.limit > 0L) {
            exp.setAttribute("limit", Long.toHexString(this.limit).toUpperCase());
        }
        this.writeCapabilities(pE);
        return d;
    }
    
    public String getExpiration() {
        String result = "";
        if (this.limit > 0L) {
            result = new Date(this.limit).toString();
        }
        return result;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(final String value) {
        this.company = value;
    }
    
    public long getProduct() {
        return this.product;
    }
    
    public long getLimit() {
        return this.limit;
    }
    
    public void setCapability(final String n, final String v) {
        this.capabilities.put(n, v);
        this.keyUpdated(this.key);
    }
    
    public String getCapability(final String n) {
        return this.capabilities.get(n);
    }
    
    public Map<String, String> getCapabilities() {
        final Map<String, String> result = new HashMap<String, String>();
        result.putAll(this.capabilities);
        return result;
    }
    
    private void writeCapabilities(final Element root) {
        final Namespace ns = root.getNamespace();
        root.removeContent();
        for (final String k : this.capabilities.keySet()) {
            Element e = root;
            String[] split;
            for (int length = (split = k.split("\\.")).length, i = 0; i < length; ++i) {
                final String s = split[i];
                Element child = e.getChild(s, ns);
                if (child == null) {
                    child = new Element(s, ns);
                    e.addContent((Content)child);
                }
                e = child;
            }
            e.setText((String)this.capabilities.get(k));
        }
    }
    
    private void readCapabilities(final Element root) throws Exception {
        this.capabilities.clear();
        for (final Object o : XPath.selectNodes((Object)root, "*//*[not(boolean(*))]")) {
            Element node = (Element)o;
            final String value = node.getText();
            final StringBuilder sb = new StringBuilder();
            while (true) {
                sb.insert(0, node.getName());
                node = node.getParentElement();
                if (node == null || node == root) {
                    break;
                }
                sb.insert(0, '.');
            }
            this.capabilities.put(sb.toString(), value);
        }
    }
    
    private boolean validateKey() {
        final long[] k = new long[this.key.length];
        k[0] = this.key[0];
        this.keyUpdated(k);
        return this.key[1] == k[1] && this.key[2] == k[2] && this.key[3] == k[3] && this.key[4] == k[4] && this.key[5] == k[5];
    }
    
    private void keyUpdated(final long[] key) {
        key[1] = (this.getCrc32(key[0]) & 0x7FFFFFFFL);
        key[2] = (this.getCrc32(this.capabilities.hashCode()) & 0x7FFFFFFFL);
        key[3] = (this.getCrc32(key[2]) & 0x7FFFFFFFL);
        key[4] = (this.getCrc32(this.limit) & 0x7FFFFFFFL);
        key[5] = (this.getCrc32(key[4]) & 0x7FFFFFFFL);
    }
    
    private boolean validateLimit() {
        return this.limit == 0L || System.currentTimeMillis() < this.limit;
    }
    
    private String encode(final long n) {
        final StringBuilder sb = new StringBuilder(6);
        final String hexString = Long.toString(n, 36).toUpperCase();
        for (int i = hexString.length(); i < 6; ++i) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }
    
    private long getCrc32(final long n) {
        final CRC32 crc32 = new CRC32();
        crc32.update(this.bytes(n));
        return crc32.getValue() - this.modifier();
    }
    
    private byte[] bytes(final long n) {
        final byte[] result = new byte[8];
        for (int i = 0; i < result.length; ++i) {
            result[i] = (byte)(n >> i * 8 & 0xFFFFL);
        }
        return result;
    }
    
    private long modifier() {
        long result = this.product;
        for (int i = 0; i < this.key.length; ++i) {
            result += (this.key[0] | 15L << i);
        }
        return result;
    }
    
    private long datePlus(final long days) {
        return (days > 0L) ? (System.currentTimeMillis() + days * 86400000L) : 0L;
    }
    
    public enum Status
    {
        Invalid("Invalid", 0), 
        Valid("Valid", 1);
        
        private Status(final String s, final int n) {
        }
    }
}
