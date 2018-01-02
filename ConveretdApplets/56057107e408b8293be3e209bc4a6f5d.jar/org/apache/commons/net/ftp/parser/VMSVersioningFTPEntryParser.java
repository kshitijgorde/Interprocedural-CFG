// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp.parser;

import org.apache.oro.text.regex.MatchResult;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.List;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Matcher;

public class VMSVersioningFTPEntryParser extends VMSFTPEntryParser
{
    private Perl5Matcher _preparse_matcher_;
    private Pattern _preparse_pattern_;
    private static final String PRE_PARSE_REGEX = "(.*);([0-9]+)\\s*.*";
    
    public VMSVersioningFTPEntryParser() {
        this((FTPClientConfig)null);
    }
    
    public VMSVersioningFTPEntryParser(final FTPClientConfig config) {
        this.configure(config);
        try {
            this._preparse_matcher_ = new Perl5Matcher();
            this._preparse_pattern_ = new Perl5Compiler().compile("(.*);([0-9]+)\\s*.*");
        }
        catch (MalformedPatternException e) {
            throw new IllegalArgumentException("Unparseable regex supplied:  (.*);([0-9]+)\\s*.*");
        }
    }
    
    public List preParse(List original) {
        original = super.preParse(original);
        final HashMap existingEntries = new HashMap();
        final ListIterator iter = original.listIterator();
        while (iter.hasNext()) {
            final String entry = iter.next().trim();
            MatchResult result = null;
            if (this._preparse_matcher_.matches(entry, this._preparse_pattern_)) {
                result = this._preparse_matcher_.getMatch();
                final String name = result.group(1);
                final String version = result.group(2);
                final NameVersion nv = new NameVersion(name, version);
                final NameVersion existing = existingEntries.get(name);
                if (null != existing && nv.versionNumber < existing.versionNumber) {
                    iter.remove();
                }
                else {
                    existingEntries.put(name, nv);
                }
            }
        }
        while (iter.hasPrevious()) {
            final String entry = iter.previous().trim();
            MatchResult result = null;
            if (this._preparse_matcher_.matches(entry, this._preparse_pattern_)) {
                result = this._preparse_matcher_.getMatch();
                final String name = result.group(1);
                final String version = result.group(2);
                final NameVersion nv = new NameVersion(name, version);
                final NameVersion existing = existingEntries.get(name);
                if (null == existing || nv.versionNumber >= existing.versionNumber) {
                    continue;
                }
                iter.remove();
            }
        }
        return original;
    }
    
    protected boolean isVersioning() {
        return true;
    }
    
    private class NameVersion
    {
        String name;
        int versionNumber;
        
        NameVersion(final String name, final String vers) {
            this.name = name;
            this.versionNumber = Integer.parseInt(vers);
        }
    }
}
