// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.util.Vector;
import com.postx.util.StringConversion;
import java.util.Map;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.io.Reader;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.text.ParseException;
import java.io.IOException;
import sun.misc.BASE64Decoder;
import java.text.ParsePosition;
import java.text.DecimalFormat;
import java.util.Hashtable;
import com.postx.util.logging.Logger;
import java.io.InputStream;

public class PayloadInputStream extends InputStream
{
    public static final String Ident = "$Id: PayloadInputStream.java,v 1.5 2011/01/10 05:13:52 blm Exp $";
    static final Logger log;
    private static final int BUF_SIZE = 1024;
    private static final int MAX_NUM_SIZE = 32;
    private static Hashtable specialProps;
    private ExtendedPushbackReader src;
    private DecimalFormat numberParser;
    private ParsePosition numberParsePosition;
    private BASE64Decoder decoder;
    private boolean atEOF;
    private char[] numBuf;
    private char[] temp;
    private byte[] inBuf;
    private int inBufStart;
    private int inBufEnd;
    private Hashtable props;
    private long flags;
    private String messageID;
    private byte[] salt;
    private int[] segments;
    private byte[] signature;
    private String sigKeyName;
    private int keySize;
    private String keyEncryptionAlgorithmName;
    private String keyVerificationAlgorithmName;
    private String dataEncryptionAlgorithmName;
    private String dataVerificationAlgorithmName;
    private byte[] keyEncryptionIV;
    private byte[] dataEncryptionIV;
    private int keyEncryptionPrerun;
    private int dataEncryptionPrerun;
    
    private void setSalt(final String s) {
        this.salt = base64DecodeToBytes(s);
    }
    
    public String getDataEncryptionAlgorithmName() {
        return this.dataEncryptionAlgorithmName;
    }
    
    public String getMessageID() {
        return this.messageID;
    }
    
    public Object getProperty(final String s) {
        return this.props.get(s);
    }
    
    private void setMessageID(final String messageID) {
        this.messageID = messageID;
    }
    
    public byte[] getKeyEncryptionIV() {
        return this.cloneByteArray(this.keyEncryptionIV);
    }
    
    public int read() throws IOException {
        if (this.src == null || this.atEOF || (this.inBufStart == this.inBufEnd && this.fillInBuf() == -1)) {
            return -1;
        }
        return this.inBuf[this.inBufStart++] & 0xFF;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this.src == null || this.atEOF || (this.inBufStart == this.inBufEnd && this.fillInBuf() == -1)) {
            return -1;
        }
        int n3 = this.inBufEnd - this.inBufStart;
        if (n2 < n3) {
            n3 = n2;
        }
        System.arraycopy(this.inBuf, this.inBufStart, array, n, n3);
        this.inBufStart += n3;
        return n3;
    }
    
    public String getKeyEncryptionAlgorithmName() {
        return this.keyEncryptionAlgorithmName;
    }
    
    public String getDataVerificationAlgorithmName() {
        return this.dataVerificationAlgorithmName;
    }
    
    public int getDataEncryptionPrerun() {
        return this.dataEncryptionPrerun;
    }
    
    public String getSigKeyName() {
        return this.sigKeyName;
    }
    
    private void setSigKeyName(final String sigKeyName) {
        this.sigKeyName = sigKeyName;
    }
    
    private Object javaScriptToObject(final boolean b) throws IOException, ParseException {
        this.src.skipWhitespace();
        final int peek = this.src.peek();
        switch (peek) {
            case 34:
            case 39: {
                return this.javaScriptToString();
            }
            case 91: {
                return this.javaScriptToArray();
            }
            case 123: {
                return this.javaScriptToHashtable(b);
            }
            default: {
                if ((48 <= peek && peek <= 57) || peek == 46 || peek == 45 || peek == 43) {
                    return this.javaScriptToNumber(true);
                }
                if (peek == 110 && this.src.at("null")) {
                    return new Null();
                }
                throw new ParseException("unrecognized construct", -1);
            }
        }
    }
    
    public void close() throws IOException {
        if (this.src != null) {
            this.src.close();
            this.src = null;
        }
    }
    
    public PayloadInputStream(final String s, final String s2) throws IOException, MalformedURLException, ParseException {
        this.numberParser = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
        this.numberParsePosition = new ParsePosition(0);
        this.decoder = new BASE64Decoder();
        this.atEOF = false;
        this.numBuf = new char[32];
        this.temp = new char[1024];
        this.inBufStart = 0;
        this.inBufEnd = 0;
        this.segments = new int[] { 1, 2, 3 };
        this.keySize = -1;
        this.keyEncryptionAlgorithmName = "ARC4";
        this.keyVerificationAlgorithmName = "CRC-32";
        this.dataEncryptionAlgorithmName = "ARC4";
        this.dataVerificationAlgorithmName = "CRC-32";
        this.keyEncryptionPrerun = -1;
        this.dataEncryptionPrerun = -1;
        if (PayloadInputStream.specialProps.isEmpty()) {
            throw new IllegalArgumentException("Couldn't get payload methods");
        }
        this.src = new ExtendedPushbackReader(new EnvelopePayloadReader(s, s2), 32);
        final Object javaScriptToObject = this.javaScriptToObject(true);
        if (!(javaScriptToObject instanceof Hashtable)) {
            throw new ParseException("Expected Hashtable as outer object, got " + ((Hashtable)javaScriptToObject).getClass().getName(), -1);
        }
        this.normalizeProperties(this.props = (Hashtable)javaScriptToObject);
    }
    
    private Hashtable javaScriptToHashtable(final boolean b) throws IOException, ParseException {
        final Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        this.src.read();
        while (this.src.skipWhitespace()) {
            final int read = this.src.read();
            if (read != 125) {
                if (hashtable.size() > 0) {
                    if (read != 44) {
                        throw new ParseException("',' or '}' expected", -1);
                    }
                    this.src.skipWhitespace();
                }
                else {
                    this.src.unread(read);
                }
                final String javaScriptToString = this.javaScriptToString();
                this.src.skipWhitespace();
                if (!this.src.at(':')) {
                    throw new ParseException("':' expected", -1);
                }
                if (!b || !javaScriptToString.equals("data")) {
                    this.src.skipWhitespace();
                    final Object javaScriptToObject = this.javaScriptToObject(false);
                    if (b) {
                        final Method method = PayloadInputStream.specialProps.get(javaScriptToString);
                        if (method != null) {
                            try {
                                method.invoke(this, javaScriptToObject);
                                continue;
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                throw new ParseException("can't set " + (Object)javaScriptToString + " property", -1);
                            }
                        }
                    }
                    hashtable.put(javaScriptToString, javaScriptToObject);
                    continue;
                }
                if (!this.src.skipWhitespace() || !this.src.at('[') || !this.src.skipWhitespace() || (!this.src.at('\'') && !this.src.at('\"'))) {
                    throw new ParseException("array of strings expected as value of data property", -1);
                }
            }
            return hashtable;
        }
        throw new ParseException("premature end of input parsing Map", -1);
    }
    
    public byte[] getSignature() {
        return this.cloneByteArray(this.signature);
    }
    
    private Number javaScriptToNumber(final boolean b) throws IOException, ParseException {
        this.src.at('+');
        final int read;
        if ((read = this.src.read(this.numBuf, 0, 32)) == -1) {
            throw new ParseException("number expected", -1);
        }
        this.numberParsePosition.setIndex(0);
        Number parse = this.numberParser.parse(new String(this.numBuf, 0, read), this.numberParsePosition);
        final int index;
        if (parse == null || (index = this.numberParsePosition.getIndex()) == 0) {
            throw new ParseException("number expected", -1);
        }
        if (index < read) {
            this.src.unread(this.numBuf, index, read - index);
        }
        if (b && (this.src.at('E') || this.src.at('e'))) {
            final double n = Math.pow(10.0, this.javaScriptToNumber(false).doubleValue()) * parse.doubleValue();
            final long n2 = (long)n;
            if (n2 != n) {
                parse = new Double(n);
            }
            else {
                parse = new Long(n2);
            }
        }
        return parse;
    }
    
    private int javaScriptToChar() throws IOException {
        int n = 3;
        int n2 = 0;
        while (n-- > 0) {
            final int read;
            if ((read = this.src.read()) == -1) {
                return -1;
            }
            if (read < 48 || 55 < read) {
                break;
            }
            n2 = n2 * 8 + (read & 0x7);
        }
        return n2;
    }
    
    private int javaScriptToChar(int n) throws IOException {
        int n2 = 0;
        while (n-- > 0) {
            final int read;
            if ((read = this.src.read()) == -1) {
                return -1;
            }
            final char c = (char)read;
            final int n3 = n2 << 4;
            if ('0' <= c && c <= '9') {
                n2 = n3 + (c & '\u000f');
            }
            else {
                if (('A' > c || c > 'F') && ('a' > c || c > 'f')) {
                    throw new IOException("Invalid hex character '" + c + "' (" + read + ")");
                }
                n2 = n3 + ((c & '\u000f') + '\t');
            }
        }
        return n2;
    }
    
    private void normalizeProperties(final Hashtable hashtable) {
        final Map<K, V> map;
        if ((map = (Map<K, V>)hashtable.get("algnames")) != null) {
            final Hashtable<Object, String> hashtable2;
            if ((hashtable2 = ((Hashtable<Object, Hashtable<Object, String>>)map).get("encryption")) != null) {
                final String keyEncryptionAlgorithmName;
                if ((keyEncryptionAlgorithmName = hashtable2.get("keys")) != null) {
                    this.keyEncryptionAlgorithmName = keyEncryptionAlgorithmName;
                }
                final String dataEncryptionAlgorithmName;
                if ((dataEncryptionAlgorithmName = hashtable2.get("data")) != null) {
                    this.dataEncryptionAlgorithmName = dataEncryptionAlgorithmName;
                }
            }
            final Hashtable<Object, String> hashtable3;
            if ((hashtable3 = ((Hashtable<Object, Hashtable<Object, String>>)map).get("verification")) != null) {
                final String s;
                if ((s = hashtable3.get("keys")) != null) {
                    this.keyVerificationAlgorithmName = (s.equals("SHA1") ? "SHA-1" : s);
                }
                final String s2;
                if ((s2 = hashtable3.get("data")) != null) {
                    this.dataVerificationAlgorithmName = (s2.equals("SHA1") ? "SHA-1" : s2);
                }
            }
        }
        final Object o;
        final Object o2;
        if ((o = hashtable.get("algparams")) != null && (o2 = ((Hashtable<K, Hashtable<K, Object>>)o).get("encryption")) != null) {
            Object o3;
            if ((o3 = ((Hashtable<K, Hashtable<K, Hashtable>>)o2).get("keys")) != null) {
                if (this.keyEncryptionAlgorithmName.equals("ARC4")) {
                    final Hashtable<K, Number> hashtable4 = (Hashtable<K, Number>)o3;
                    final Number n;
                    if ((n = hashtable4.get("prerun")) != null) {
                        this.keyEncryptionPrerun = n.intValue();
                    }
                    final Number n2;
                    if ((n2 = hashtable4.get("keysize")) != null) {
                        this.keySize = n2.intValue();
                    }
                }
                else if (this.keyEncryptionAlgorithmName.equals("AES")) {
                    if (o3 instanceof Hashtable) {
                        o3 = ((Hashtable<K, Hashtable>)o3).get("IV");
                    }
                    if (o3 != null) {
                        this.keyEncryptionIV = base64DecodeToBytes((String)o3);
                    }
                }
            }
            Object o4;
            if ((o4 = ((Hashtable<K, Hashtable<K, Hashtable>>)o2).get("data")) != null) {
                if (this.dataEncryptionAlgorithmName.equals("ARC4")) {
                    final Hashtable<K, Number> hashtable5 = (Hashtable<K, Number>)o4;
                    final Number n3;
                    if ((n3 = hashtable5.get("prerun")) != null) {
                        this.dataEncryptionPrerun = n3.intValue();
                    }
                    final Number n4;
                    if ((n4 = hashtable5.get("keysize")) != null) {
                        this.keySize = n4.intValue();
                    }
                }
                else if (this.dataEncryptionAlgorithmName.equals("AES")) {
                    if (o4 instanceof Hashtable) {
                        o4 = ((Hashtable<K, Hashtable>)o4).get("IV");
                    }
                    if (o4 != null) {
                        this.dataEncryptionIV = base64DecodeToBytes((String)o4);
                    }
                }
            }
        }
        if (this.keySize == -1) {
            final Number n5;
            if ((n5 = (Number)hashtable.get("keysize")) != null) {
                this.keySize = n5.intValue();
            }
            else {
                this.keySize = 0;
            }
        }
        if (this.keyEncryptionAlgorithmName.equals("ARC4") && this.keyEncryptionPrerun == -1) {
            this.keyEncryptionPrerun = (((this.flags & 0x8L) == 0x0L) ? 0 : 256);
        }
        if (this.dataEncryptionAlgorithmName.equals("ARC4") && this.dataEncryptionPrerun == -1) {
            this.dataEncryptionPrerun = (((this.flags & 0x8L) == 0x0L) ? 0 : 256);
        }
    }
    
    public String getKeyVerificationAlgorithmName() {
        return this.keyVerificationAlgorithmName;
    }
    
    public int getKeySize() {
        return this.keySize;
    }
    
    private void setKeySize(final Integer n) {
        this.keySize = n;
    }
    
    private void setSignature(final Object o) {
        this.signature = base64DecodeToBytes((String)((Object[])o)[0]);
    }
    
    public int getKeyEncryptionPrerun() {
        return this.keyEncryptionPrerun;
    }
    
    private String javaScriptToString() throws IOException, ParseException {
        final StringBuffer sb = new StringBuffer();
        final char c = (char)this.src.read();
        if (c != '\'' && c != '\"') {
            throw new ParseException("expected string", -1);
        }
        int n;
        while ((n = this.src.read()) != -1 && n != c) {
            if (n == '\\') {
                switch (n = this.src.read()) {
                    case 98: {
                        n = '\b';
                        break;
                    }
                    case 102: {
                        n = '\f';
                        break;
                    }
                    case 110: {
                        n = '\n';
                        break;
                    }
                    case 114: {
                        n = '\r';
                        break;
                    }
                    case 116: {
                        n = '\t';
                        break;
                    }
                    case 118: {
                        n = '\u0011';
                        break;
                    }
                    case 120: {
                        n = this.javaScriptToChar(2);
                        break;
                    }
                    case 117: {
                        n = this.javaScriptToChar(4);
                        break;
                    }
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55: {
                        n = this.javaScriptToChar();
                        break;
                    }
                }
            }
            if (n == -1) {
                break;
            }
            sb.append((char)n);
        }
        if (n == -1) {
            throw new ParseException("premature end of input parsing String", -1);
        }
        return sb.toString();
    }
    
    private static String base64Decode(final String s) {
        return StringConversion.bytesToString(base64DecodeToBytes(s));
    }
    
    static {
        log = Logger.global;
        PayloadInputStream.specialProps = new Hashtable() {
            private static /* synthetic */ Class class$com$postx$io$PayloadInputStream;
            private static /* synthetic */ Class class$java$lang$Long;
            private static /* synthetic */ Class class$java$lang$Integer;
            private static /* synthetic */ Class class$java$lang$String;
            private static /* synthetic */ Class class$java$lang$Object;
            
            {
                try {
                    final Class clazz = (PayloadInputStream$2.class$com$postx$io$PayloadInputStream != null) ? PayloadInputStream$2.class$com$postx$io$PayloadInputStream : (PayloadInputStream$2.class$com$postx$io$PayloadInputStream = class$("com.postx.io.PayloadInputStream"));
                    this.put("flags", clazz.getDeclaredMethod("setFlags", (PayloadInputStream$2.class$java$lang$Long != null) ? PayloadInputStream$2.class$java$lang$Long : (PayloadInputStream$2.class$java$lang$Long = class$("java.lang.Long"))));
                    this.put("keySize", clazz.getDeclaredMethod("setKeySize", (PayloadInputStream$2.class$java$lang$Integer != null) ? PayloadInputStream$2.class$java$lang$Integer : (PayloadInputStream$2.class$java$lang$Integer = class$("java.lang.Integer"))));
                    this.put("msgID", clazz.getDeclaredMethod("setMessageID", (PayloadInputStream$2.class$java$lang$String != null) ? PayloadInputStream$2.class$java$lang$String : (PayloadInputStream$2.class$java$lang$String = class$("java.lang.String"))));
                    this.put("salt", clazz.getDeclaredMethod("setSalt", (PayloadInputStream$2.class$java$lang$String != null) ? PayloadInputStream$2.class$java$lang$String : (PayloadInputStream$2.class$java$lang$String = class$("java.lang.String"))));
                    this.put("segments", clazz.getDeclaredMethod("setSegments", (PayloadInputStream$2.class$java$lang$Object != null) ? PayloadInputStream$2.class$java$lang$Object : (PayloadInputStream$2.class$java$lang$Object = class$("java.lang.Object"))));
                    this.put("sigkeyname", clazz.getDeclaredMethod("setSigKeyName", (PayloadInputStream$2.class$java$lang$String != null) ? PayloadInputStream$2.class$java$lang$String : (PayloadInputStream$2.class$java$lang$String = class$("java.lang.String"))));
                    this.put("signature", clazz.getDeclaredMethod("setSignature", (PayloadInputStream$2.class$java$lang$Object != null) ? PayloadInputStream$2.class$java$lang$Object : (PayloadInputStream$2.class$java$lang$Object = class$("java.lang.Object"))));
                }
                catch (NoSuchMethodException ex) {
                    PayloadInputStream.log.severe("Couldn't find method " + ex.getMessage());
                    this.clear();
                }
            }
            
            private static /* synthetic */ Class class$(final String s) {
                try {
                    return Class.forName(s);
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
        };
    }
    
    public boolean next() throws IOException {
        if (this.src == null) {
            return false;
        }
        if (!this.atEOF) {
            while (this.fillInBuf() != -1) {}
        }
        if (!this.src.at("',") || !this.src.skipWhitespace() || !this.src.at('\'')) {
            this.close();
            return false;
        }
        this.atEOF = false;
        final boolean b = false;
        this.inBufEnd = (b ? 1 : 0);
        this.inBufStart = (b ? 1 : 0);
        return true;
    }
    
    public int[] getSegments() {
        final int[] array = new int[this.segments.length];
        System.arraycopy(this.segments, 0, array, 0, array.length);
        return array;
    }
    
    private void setSegments(final Object o) {
        final Object[] array = (Object[])o;
        final int length = array.length;
        final int[] segments = new int[length];
        segments[0] = ((Number)array[0]).intValue();
        for (int i = 1; i < length; ++i) {
            segments[i] = segments[i - 1] + ((Number)array[i]).intValue();
        }
        this.segments = segments;
    }
    
    private Object[] javaScriptToArray() throws IOException, ParseException {
        final Vector vector = new Vector<Object>();
        this.src.read();
        while (true) {
            this.src.skipWhitespace();
            final int read = this.src.read();
            if (read == -1) {
                throw new ParseException("premature end of input parsing Array", -1);
            }
            if (read == 93) {
                final Object[] array = new Object[vector.size()];
                vector.copyInto(array);
                return array;
            }
            if (vector.size() > 0) {
                if (read != 44) {
                    throw new ParseException("',' or ']' expected", -1);
                }
            }
            else {
                this.src.unread(read);
            }
            vector.addElement(this.javaScriptToObject(false));
        }
    }
    
    private byte[] cloneByteArray(final byte[] array) {
        if (array == null) {
            return array;
        }
        final byte[] array2 = new byte[array.length];
        System.arraycopy(array, 0, array2, 0, array2.length);
        return array2;
    }
    
    private static byte[] base64DecodeToBytes(final String s) {
        try {
            return new BASE64Decoder().decodeBuffer(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private int fillInBuf() throws IOException {
        char[] temp;
        int n;
        int read;
        for (temp = this.temp, n = 0; n < temp.length && (read = this.src.read()) != -1; temp[n++] = (char)read) {
            if (read != 92 && !Character.isWhitespace((char)read)) {
                if (read == 39) {
                    this.src.unread(read);
                    break;
                }
            }
        }
        if (n == 0) {
            this.atEOF = true;
            return -1;
        }
        this.inBuf = this.decoder.decodeBuffer(new String(temp, 0, n));
        this.inBufStart = 0;
        return this.inBufEnd = this.inBuf.length;
    }
    
    public byte[] getDataEncryptionIV() {
        return this.cloneByteArray(this.dataEncryptionIV);
    }
    
    public long getFlags() {
        return this.flags;
    }
    
    private void setFlags(final Long n) {
        this.flags = n;
    }
    
    public byte[] getSalt() {
        return this.cloneByteArray(this.salt);
    }
    
    private static class Null
    {
        public String toString() {
            return "null";
        }
    }
}
