// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.exception.InternalException;
import org.jcodings.util.CaseInsensitiveBytesHash;

public class EncodingDB
{
    private static String[] builtin;
    static Entry ascii;
    static final CaseInsensitiveBytesHash<Entry> encodings;
    static final CaseInsensitiveBytesHash<Entry> aliases;
    
    static void boot() {
        declareBuiltins();
        EncodingDB.ascii = EncodingDB.encodings.get("ASCII-8BIT".getBytes());
        alias("BINARY", "ASCII-8BIT");
        replicate("IBM437", "ASCII-8BIT");
        alias("CP437", "IBM437");
        replicate("IBM737", "ASCII-8BIT");
        alias("CP737", "IBM737");
        replicate("IBM775", "ASCII-8BIT");
        alias("CP775", "IBM775");
        replicate("CP850", "ASCII-8BIT");
        alias("IBM850", "CP850");
        replicate("IBM852", "ASCII-8BIT");
        replicate("CP852", "IBM852");
        replicate("IBM855", "ASCII-8BIT");
        replicate("CP855", "IBM855");
        replicate("IBM857", "ASCII-8BIT");
        alias("CP857", "IBM857");
        replicate("IBM860", "ASCII-8BIT");
        alias("CP860", "IBM860");
        replicate("IBM861", "ASCII-8BIT");
        alias("CP861", "IBM861");
        replicate("IBM862", "ASCII-8BIT");
        alias("CP862", "IBM862");
        replicate("IBM863", "ASCII-8BIT");
        alias("CP863", "IBM863");
        replicate("IBM864", "ASCII-8BIT");
        alias("CP864", "IBM864");
        replicate("IBM865", "ASCII-8BIT");
        alias("CP865", "IBM865");
        replicate("IBM866", "ASCII-8BIT");
        alias("CP866", "IBM866");
        replicate("IBM869", "ASCII-8BIT");
        alias("CP869", "IBM869");
        replicate("Windows-1258", "ASCII-8BIT");
        alias("CP1258", "Windows-1258");
        replicate("GB1988", "ASCII-8BIT");
        replicate("macCentEuro", "ASCII-8BIT");
        replicate("macCroatian", "ASCII-8BIT");
        replicate("macCyrillic", "ASCII-8BIT");
        replicate("macGreek", "ASCII-8BIT");
        replicate("macIceland", "ASCII-8BIT");
        replicate("macRoman", "ASCII-8BIT");
        replicate("macRomania", "ASCII-8BIT");
        replicate("macThai", "ASCII-8BIT");
        replicate("macTurkish", "ASCII-8BIT");
        replicate("macUkraine", "ASCII-8BIT");
        alias("CP950", "BIG5");
        replicate("stateless-ISO-2022-JP", "Emacs-Mule");
        alias("eucJP", "EUC-JP");
        replicate("eucJP-ms", "EUC-JP");
        alias("euc-jp-ms", "eucJP-ms");
        replicate("CP51932", "EUC-JP");
        alias("eucKR", "EUC-KR");
        alias("eucTW", "EUC-TW");
        alias("EUC-CN", "GB2312");
        alias("eucCN", "GB2312");
        replicate("GB12345", "GB2312");
        alias("CP936", "GBK");
        dummy("ISO-2022-JP");
        alias("ISO2022-JP", "ISO-2022-JP");
        replicate("ISO-2022-JP-2", "ISO-2022-JP");
        alias("ISO2022-JP2", "ISO-2022-JP-2");
        alias("ISO8859-1", "ISO-8859-1");
        replicate("Windows-1252", "ISO-8859-1");
        alias("CP1252", "Windows-1252");
        alias("ISO8859-2", "ISO-8859-2");
        replicate("Windows-1250", "ISO-8859-2");
        alias("CP1250", "Windows-1250");
        alias("ISO8859-3", "ISO-8859-3");
        alias("ISO8859-4", "ISO-8859-4");
        alias("ISO8859-5", "ISO-8859-5");
        alias("ISO8859-6", "ISO-8859-6");
        replicate("Windows-1256", "ISO-8859-6");
        alias("CP1256", "Windows-1256");
        alias("ISO8859-7", "ISO-8859-7");
        replicate("Windows-1253", "ISO-8859-7");
        alias("CP1253", "Windows-1253");
        alias("ISO8859-8", "ISO-8859-8");
        replicate("Windows-1255", "ISO-8859-8");
        alias("CP1255", "Windows-1255");
        alias("ISO8859-9", "ISO-8859-9");
        replicate("Windows-1254", "ISO-8859-9");
        alias("CP1254", "Windows-1254");
        alias("ISO8859-10", "ISO-8859-10");
        alias("ISO8859-11", "ISO-8859-11");
        replicate("TIS-620", "ISO-8859-11");
        replicate("Windows-874", "ISO-8859-11");
        alias("CP874", "Windows-874");
        alias("ISO8859-13", "ISO-8859-13");
        replicate("Windows-1257", "ISO-8859-13");
        alias("CP1257", "Windows-1257");
        alias("ISO8859-14", "ISO-8859-14");
        alias("ISO8859-15", "ISO-8859-15");
        alias("ISO8859-16", "ISO-8859-16");
        alias("CP878", "KOI8-R");
        alias("SJIS", "Shift_JIS");
        replicate("Windows-31J", "Shift_JIS");
        alias("CP932", "Windows-31J");
        alias("csWindows31J", "Windows-31J");
        replicate("MacJapanese", "Shift_JIS");
        alias("MacJapan", "MacJapanese");
        alias("ASCII", "US-ASCII");
        alias("ANSI_X3.4-1968", "US-ASCII");
        alias("646", "US-ASCII");
        dummy("UTF-7");
        alias("CP65000", "UTF-7");
        alias("CP65001", "UTF-8");
        replicate("UTF8-MAC", "UTF-8");
        alias("UTF-8-MAC", "UTF8-MAC");
        alias("UCS-2BE", "UTF-16BE");
        alias("UCS-4BE", "UTF-32BE");
        alias("UCS-4LE", "UTF-32LE");
        alias("CP1251", "Windows-1251");
    }
    
    public static final CaseInsensitiveBytesHash<Entry> getEncodings() {
        return EncodingDB.encodings;
    }
    
    public static final CaseInsensitiveBytesHash<Entry> getAliases() {
        return EncodingDB.aliases;
    }
    
    public static void declare(final String name, final String encodingClass) {
        final byte[] bytes = name.getBytes();
        if (EncodingDB.encodings.get(bytes) != null) {
            throw new InternalException("encoding already registerd <%n>", name);
        }
        EncodingDB.encodings.putDirect(bytes, new Entry(encodingClass));
    }
    
    public static void alias(final String alias, final String original) {
        final byte[] origBytes = original.getBytes();
        final Entry originalEntry = EncodingDB.encodings.get(origBytes);
        if (originalEntry == null) {
            throw new InternalException("no such encoding <%n>", original);
        }
        final byte[] aliasBytes = alias.getBytes();
        if (EncodingDB.aliases.get(aliasBytes) != null) {
            throw new InternalException("encoding alias already registerd <%n>", alias);
        }
        EncodingDB.aliases.putDirect(aliasBytes, originalEntry);
    }
    
    public static void replicate(final String replica, final String original) {
        final byte[] origBytes = original.getBytes();
        final Entry originalEntry = EncodingDB.encodings.get(origBytes);
        if (originalEntry == null) {
            throw new InternalException("no such encoding <%n>", original);
        }
        final byte[] replicaBytes = replica.getBytes();
        if (EncodingDB.encodings.get(replicaBytes) != null) {
            throw new InternalException("encoding replica already registerd <%n>", replica);
        }
        EncodingDB.encodings.putDirect(replicaBytes, new Entry(replicaBytes, originalEntry));
    }
    
    public static void dummy(final String name) {
        final byte[] bytes = name.getBytes();
        if (EncodingDB.encodings.get(bytes) != null) {
            throw new InternalException("encoding already registerd <%n>", name);
        }
        EncodingDB.encodings.putDirect(bytes, new Entry(bytes));
    }
    
    static void declareBuiltins() {
        for (int i = 0; i < EncodingDB.builtin.length / 2; ++i) {
            declare(EncodingDB.builtin[i << 1], EncodingDB.builtin[(i << 1) + 1]);
        }
        EncodingDB.builtin = null;
    }
    
    static {
        EncodingDB.builtin = new String[] { "ASCII-8BIT", "ASCII", "Big5", "BIG5", "CP949", "CP949", "Emacs-Mule", "EmacsMule", "EUC-JP", "EUCJP", "EUC-KR", "EUCKR", "EUC-TW", "EUCTW", "GB18030", "GB18030", "GBK", "GBK", "ISO-8859-1", "ISO8859_1", "ISO-8859-2", "ISO8859_2", "ISO-8859-3", "ISO8859_3", "ISO-8859-4", "ISO8859_4", "ISO-8859-5", "ISO8859_5", "ISO-8859-6", "ISO8859_6", "ISO-8859-7", "ISO8859_7", "ISO-8859-8", "ISO8859_8", "ISO-8859-9", "ISO8859_9", "ISO-8859-10", "ISO8859_10", "ISO-8859-11", "ISO8859_11", "ISO-8859-13", "ISO8859_13", "ISO-8859-14", "ISO8859_14", "ISO-8859-15", "ISO8859_15", "ISO-8859-16", "ISO8859_16", "KOI8-R", "KOI8R", "KOI8-U", "KOI8U", "Shift_JIS", "SJIS", "US-ASCII", "USASCII", "UTF-8", "UTF8", "UTF-16BE", "UTF16BE", "UTF-16LE", "UTF16LE", "UTF-32BE", "UTF32BE", "UTF-32LE", "UTF32LE", "Windows-1251", "CP1251", "GB2312", "EUCKR" };
        encodings = new CaseInsensitiveBytesHash<Entry>(EncodingDB.builtin.length);
        aliases = new CaseInsensitiveBytesHash<Entry>(EncodingDB.builtin.length);
        boot();
    }
    
    public static final class Entry
    {
        private static int count;
        private final Entry base;
        private Encoding encoding;
        private final String encodingClass;
        private final int index;
        private final boolean isDummy;
        private final byte[] name;
        
        private Entry(final byte[] name, final String encodingClass, final Entry base, final boolean isDummy) {
            this.name = name;
            this.encodingClass = encodingClass;
            this.base = base;
            this.isDummy = isDummy;
            this.index = Entry.count++;
        }
        
        Entry(final String encodingClass) {
            this(null, encodingClass, null, false);
        }
        
        Entry(final byte[] name, final Entry base) {
            this(name, base.encodingClass, base, false);
        }
        
        Entry(final byte[] name) {
            this(name, EncodingDB.ascii.encodingClass, EncodingDB.ascii, true);
        }
        
        public int hashCode() {
            return this.encodingClass.hashCode();
        }
        
        public Entry getBase() {
            return this.base;
        }
        
        public Encoding getEncoding() {
            if (this.encoding == null) {
                if (this.name == null) {
                    this.encoding = Encoding.load(this.encodingClass);
                }
                else if (this.isDummy) {
                    this.encoding = ASCIIEncoding.DUMMY.replicate(this.name);
                }
                else {
                    this.encoding = Encoding.load(this.encodingClass).replicate(this.name);
                }
            }
            return this.encoding;
        }
        
        public String getEncodingClass() {
            return this.encodingClass;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public boolean isDummy() {
            return this.isDummy;
        }
    }
}
