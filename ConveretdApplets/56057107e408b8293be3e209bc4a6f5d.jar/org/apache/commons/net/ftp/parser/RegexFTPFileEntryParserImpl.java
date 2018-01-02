// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp.parser;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

public abstract class RegexFTPFileEntryParserImpl extends FTPFileEntryParserImpl
{
    private Pattern pattern;
    private MatchResult result;
    protected PatternMatcher _matcher_;
    
    public RegexFTPFileEntryParserImpl(final String regex) {
        this.pattern = null;
        this.result = null;
        this._matcher_ = null;
        try {
            this._matcher_ = new Perl5Matcher();
            this.pattern = new Perl5Compiler().compile(regex);
        }
        catch (MalformedPatternException e) {
            throw new IllegalArgumentException("Unparseable regex supplied:  " + regex);
        }
    }
    
    public boolean matches(final String s) {
        this.result = null;
        if (this._matcher_.matches(s.trim(), this.pattern)) {
            this.result = this._matcher_.getMatch();
        }
        return null != this.result;
    }
    
    public int getGroupCnt() {
        if (this.result == null) {
            return 0;
        }
        return this.result.groups();
    }
    
    public String group(final int matchnum) {
        if (this.result == null) {
            return null;
        }
        return this.result.group(matchnum);
    }
    
    public String getGroupsAsString() {
        final StringBuffer b = new StringBuffer();
        for (int i = 1; i <= this.result.groups(); ++i) {
            b.append(i).append(") ").append(this.result.group(i)).append(System.getProperty("line.separator"));
        }
        return b.toString();
    }
}
