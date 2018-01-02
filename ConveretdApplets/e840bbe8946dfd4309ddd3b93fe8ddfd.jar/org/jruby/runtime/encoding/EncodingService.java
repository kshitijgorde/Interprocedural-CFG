// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.encoding;

import org.jcodings.ascii.AsciiTables;
import org.jcodings.util.Hash;
import org.jcodings.specific.ASCIIEncoding;
import java.nio.charset.Charset;
import org.jcodings.Encoding;
import org.jruby.exceptions.MainExitException;
import org.jruby.RubyConverter;
import org.jruby.util.ByteList;
import org.jruby.Ruby;
import org.jruby.RubyEncoding;
import org.jruby.runtime.builtin.IRubyObject;
import org.jcodings.EncodingDB;
import org.jcodings.util.CaseInsensitiveBytesHash;

public final class EncodingService
{
    private final CaseInsensitiveBytesHash<EncodingDB.Entry> encodings;
    private final CaseInsensitiveBytesHash<EncodingDB.Entry> aliases;
    private final IRubyObject[] encodingList;
    private RubyEncoding[] encodingIndex;
    private Ruby runtime;
    private static final ByteList LOCALE_BL;
    private static final ByteList EXTERNAL_BL;
    private static final ByteList INTERNAL_BL;
    private static final ByteList FILESYSTEM_BL;
    
    public EncodingService(final Ruby runtime) {
        this.encodingIndex = new RubyEncoding[4];
        this.runtime = runtime;
        this.encodings = EncodingDB.getEncodings();
        this.aliases = EncodingDB.getAliases();
        this.encodingList = new IRubyObject[this.encodings.size()];
        if (runtime.is1_9()) {
            RubyEncoding.createEncodingClass(runtime);
            RubyConverter.createConverterClass(runtime);
            this.defineEncodings();
            this.defineAliases();
            String encoding = runtime.getInstanceConfig().getExternalEncoding();
            if (encoding != null && !encoding.equals("")) {
                final Encoding loadedEncoding = this.loadEncoding(ByteList.create(encoding));
                if (loadedEncoding == null) {
                    throw new MainExitException(1, "unknown encoding name - " + encoding);
                }
                runtime.setDefaultExternalEncoding(loadedEncoding);
            }
            else {
                runtime.setDefaultExternalEncoding(this.getLocaleEncoding());
            }
            encoding = runtime.getInstanceConfig().getInternalEncoding();
            if (encoding != null && !encoding.equals("")) {
                final Encoding loadedEncoding = this.loadEncoding(ByteList.create(encoding));
                if (loadedEncoding == null) {
                    throw new MainExitException(1, "unknown encoding name - " + encoding);
                }
                runtime.setDefaultInternalEncoding(loadedEncoding);
            }
        }
    }
    
    public CaseInsensitiveBytesHash<EncodingDB.Entry> getEncodings() {
        return this.encodings;
    }
    
    public CaseInsensitiveBytesHash<EncodingDB.Entry> getAliases() {
        return this.aliases;
    }
    
    public EncodingDB.Entry findEncodingEntry(final ByteList bytes) {
        return this.encodings.get(bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public EncodingDB.Entry findAliasEntry(final ByteList bytes) {
        return this.aliases.get(bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getBegin() + bytes.getRealSize());
    }
    
    public EncodingDB.Entry findEncodingOrAliasEntry(final ByteList bytes) {
        final EncodingDB.Entry e = this.findEncodingEntry(bytes);
        return (e != null) ? e : this.findAliasEntry(bytes);
    }
    
    public Encoding getLocaleEncoding() {
        final EncodingDB.Entry entry = this.findEncodingOrAliasEntry(new ByteList(Charset.defaultCharset().name().getBytes()));
        return (entry == null) ? ASCIIEncoding.INSTANCE : entry.getEncoding();
    }
    
    public IRubyObject[] getEncodingList() {
        return this.encodingList;
    }
    
    public Encoding loadEncoding(final ByteList name) {
        final EncodingDB.Entry entry = this.findEncodingOrAliasEntry(name);
        if (entry == null) {
            return null;
        }
        final Encoding enc = entry.getEncoding();
        final int index = enc.getIndex();
        if (index >= this.encodingIndex.length) {
            final RubyEncoding[] tmp = new RubyEncoding[index + 4];
            System.arraycopy(this.encodingIndex, 0, tmp, 0, this.encodingIndex.length);
            this.encodingIndex = tmp;
        }
        this.encodingIndex[index] = (RubyEncoding)this.encodingList[entry.getIndex()];
        return enc;
    }
    
    public RubyEncoding getEncoding(Encoding enc) {
        final int index = enc.getIndex();
        final RubyEncoding rubyEncoding;
        if (index < this.encodingIndex.length && (rubyEncoding = this.encodingIndex[index]) != null) {
            return rubyEncoding;
        }
        enc = this.loadEncoding(new ByteList(enc.getName(), false));
        return this.encodingIndex[enc.getIndex()];
    }
    
    private void defineEncodings() {
        final Hash.HashEntryIterator hei = this.encodings.entryIterator();
        while (hei.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)hei.next();
            final EncodingDB.Entry ee = e.value;
            final RubyEncoding encoding = RubyEncoding.newEncoding(this.runtime, e.bytes, e.p, e.end, ee.isDummy());
            this.encodingList[ee.getIndex()] = encoding;
            this.defineEncodingConstants(this.runtime, encoding, e.bytes, e.p, e.end);
        }
    }
    
    private void defineAliases() {
        final Hash.HashEntryIterator hei = this.aliases.entryIterator();
        while (hei.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)hei.next();
            final EncodingDB.Entry ee = e.value;
            final RubyEncoding encoding = (RubyEncoding)this.encodingList[ee.getIndex()];
            this.defineEncodingConstants(this.runtime, encoding, e.bytes, e.p, e.end);
        }
    }
    
    private void defineEncodingConstants(final Ruby runtime, final RubyEncoding encoding, final byte[] name, int p, final int end) {
        final Encoding enc = ASCIIEncoding.INSTANCE;
        int code = name[p] & 0xFF;
        if (enc.isDigit(code)) {
            return;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        if (enc.isUpper(code)) {
            hasUpper = true;
            while (++p < end && (enc.isAlnum(name[p] & 0xFF) || name[p] == 95)) {
                if (enc.isLower(name[p] & 0xFF)) {
                    hasLower = true;
                }
            }
        }
        boolean isValid = false;
        if (p >= end) {
            isValid = true;
            this.defineEncodingConstant(runtime, encoding, name, p, end);
        }
        if (!isValid || hasLower) {
            if (!hasLower || !hasUpper) {
                do {
                    code = (name[p] & 0xFF);
                    if (enc.isLower(code)) {
                        hasLower = true;
                    }
                    if (enc.isUpper(code)) {
                        hasUpper = true;
                    }
                } while (++p < end && (!hasLower || !hasUpper));
            }
            final byte[] constName = new byte[end - p];
            System.arraycopy(name, p, constName, 0, end - p);
            int s = 0;
            code = (constName[s] & 0xFF);
            if (!isValid) {
                if (enc.isLower(code)) {
                    constName[s] = AsciiTables.ToUpperCaseTable[code];
                }
                while (s < constName.length) {
                    if (!enc.isAlnum(constName[s] & 0xFF)) {
                        constName[s] = 95;
                    }
                    ++s;
                }
                if (hasUpper) {
                    this.defineEncodingConstant(runtime, encoding, constName, 0, constName.length);
                }
            }
            if (hasLower) {
                for (s = 0; s < constName.length; ++s) {
                    code = (constName[s] & 0xFF);
                    if (enc.isLower(code)) {
                        constName[s] = AsciiTables.ToUpperCaseTable[code];
                    }
                }
                this.defineEncodingConstant(runtime, encoding, constName, 0, constName.length);
            }
        }
    }
    
    private void defineEncodingConstant(final Ruby runtime, final RubyEncoding encoding, final byte[] constName, final int constP, final int constEnd) {
        runtime.getEncoding().defineConstant(new String(constName, constP, constEnd), encoding);
    }
    
    public IRubyObject getDefaultExternal() {
        IRubyObject defaultExternal = this.convertEncodingToRubyEncoding(this.runtime.getDefaultExternalEncoding());
        if (defaultExternal.isNil()) {
            final ByteList encodingName = ByteList.create("US-ASCII");
            final Encoding encoding = this.runtime.getEncodingService().loadEncoding(encodingName);
            this.runtime.setDefaultExternalEncoding(encoding);
            defaultExternal = this.convertEncodingToRubyEncoding(encoding);
        }
        return defaultExternal;
    }
    
    public IRubyObject getDefaultInternal() {
        return this.convertEncodingToRubyEncoding(this.runtime.getDefaultInternalEncoding());
    }
    
    public IRubyObject convertEncodingToRubyEncoding(final Encoding defaultEncoding) {
        return (defaultEncoding != null) ? this.getEncoding(defaultEncoding) : this.runtime.getNil();
    }
    
    public Encoding getEncodingFromObject(final IRubyObject arg) {
        if (arg == null) {
            return null;
        }
        Encoding encoding = null;
        if (arg instanceof RubyEncoding) {
            encoding = ((RubyEncoding)arg).getEncoding();
        }
        else if (!arg.isNil()) {
            encoding = arg.convertToString().toEncoding(this.runtime);
        }
        return encoding;
    }
    
    public Encoding findEncoding(final IRubyObject str) {
        final ByteList name = str.convertToString().getByteList();
        this.checkAsciiEncodingName(name);
        final SpecialEncoding special = SpecialEncoding.valueOf(name);
        if (special != null) {
            return special.toEncoding(this.runtime);
        }
        return this.findEncodingWithError(name);
    }
    
    public EncodingDB.Entry findEntry(final IRubyObject str) {
        final ByteList name = str.convertToString().getByteList();
        this.checkAsciiEncodingName(name);
        final SpecialEncoding special = SpecialEncoding.valueOf(name);
        if (special != null) {
            return this.findEntryFromEncoding(special.toEncoding(this.runtime));
        }
        return this.findEntryWithError(name);
    }
    
    public IRubyObject rubyEncodingFromObject(final IRubyObject str) {
        final EncodingDB.Entry entry = this.findEntry(str);
        if (entry == null) {
            return this.runtime.getNil();
        }
        return this.getEncodingList()[entry.getIndex()];
    }
    
    private void checkAsciiEncodingName(final ByteList name) {
        if (!name.getEncoding().isAsciiCompatible()) {
            throw this.runtime.newArgumentError("invalid name encoding (non ASCII)");
        }
    }
    
    private Encoding findEncodingWithError(final ByteList name) {
        return this.findEntryWithError(name).getEncoding();
    }
    
    private EncodingDB.Entry findEntryWithError(final ByteList name) {
        final EncodingDB.Entry e = this.findEncodingOrAliasEntry(name);
        if (e == null) {
            throw this.runtime.newArgumentError("unknown encoding name - " + (Object)name);
        }
        return e;
    }
    
    private EncodingDB.Entry findEntryFromEncoding(final Encoding e) {
        if (e == null) {
            return null;
        }
        return this.findEncodingEntry(new ByteList(e.getName()));
    }
    
    static {
        LOCALE_BL = ByteList.create("locale");
        EXTERNAL_BL = ByteList.create("external");
        INTERNAL_BL = ByteList.create("internal");
        FILESYSTEM_BL = ByteList.create("filesystem");
    }
    
    private enum SpecialEncoding
    {
        LOCALE, 
        EXTERNAL, 
        INTERNAL, 
        FILESYSTEM;
        
        public static SpecialEncoding valueOf(final ByteList name) {
            if (name.caseInsensitiveCmp(EncodingService.LOCALE_BL) == 0) {
                return SpecialEncoding.LOCALE;
            }
            if (name.caseInsensitiveCmp(EncodingService.EXTERNAL_BL) == 0) {
                return SpecialEncoding.EXTERNAL;
            }
            if (name.caseInsensitiveCmp(EncodingService.INTERNAL_BL) == 0) {
                return SpecialEncoding.INTERNAL;
            }
            if (name.caseInsensitiveCmp(EncodingService.FILESYSTEM_BL) == 0) {
                return SpecialEncoding.FILESYSTEM;
            }
            return null;
        }
        
        public Encoding toEncoding(final Ruby runtime) {
            final EncodingService service = runtime.getEncodingService();
            switch (this) {
                case LOCALE: {
                    return service.getLocaleEncoding();
                }
                case EXTERNAL: {
                    return runtime.getDefaultExternalEncoding();
                }
                case INTERNAL: {
                    return runtime.getDefaultInternalEncoding();
                }
                case FILESYSTEM: {
                    return runtime.getDefaultExternalEncoding();
                }
                default: {
                    throw new RuntimeException("invalid SpecialEncoding: " + this);
                }
            }
        }
    }
}
