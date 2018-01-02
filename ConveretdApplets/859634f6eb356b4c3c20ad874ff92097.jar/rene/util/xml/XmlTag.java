// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

public class XmlTag
{
    protected String Tag;
    String[] Param;
    String[] Value;
    int N;
    
    public XmlTag(final String s) {
        this.Tag = "";
        final int n = 0;
        int n2 = 0;
        int endItem;
        for (int i = this.skipBlanks(s, n); i < s.length(); i = this.skipBlanks(s, endItem)) {
            endItem = this.endItem(s, i);
            ++n2;
        }
        if (n2 == 0) {
            return;
        }
        final int skipBlanks = this.skipBlanks(s, 0);
        final int endItem2 = this.endItem(s, skipBlanks);
        this.Tag = s.substring(skipBlanks, endItem2);
        int n3 = endItem2;
        this.N = n2 - 1;
        this.Param = new String[this.N];
        this.Value = new String[this.N];
        for (int j = 0; j < this.N; ++j) {
            final int skipBlanks2 = this.skipBlanks(s, n3);
            final int endItem3 = this.endItem(s, skipBlanks2);
            final String substring = s.substring(skipBlanks2, endItem3);
            n3 = endItem3;
            final int index = substring.indexOf(61);
            if (index >= 0) {
                this.Param[j] = substring.substring(0, index);
                this.Value[j] = substring.substring(index + 1);
                if (this.Value[j].startsWith("\"") && this.Value[j].endsWith("\"")) {
                    this.Value[j] = this.Value[j].substring(1, this.Value[j].length() - 1);
                }
                else if (this.Value[j].startsWith("'") && this.Value[j].endsWith("'")) {
                    this.Value[j] = this.Value[j].substring(1, this.Value[j].length() - 1);
                }
            }
            else {
                this.Param[j] = substring;
                this.Value[j] = "";
            }
        }
    }
    
    int skipBlanks(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\t' && char1 != '\n') {
                break;
            }
            ++i;
        }
        return i;
    }
    
    int endItem(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (char1 == ' ' || char1 == '\t') {
                break;
            }
            if (char1 == '\n') {
                break;
            }
            Label_0103: {
                if (char1 == '\"') {
                    ++i;
                    while (i < s.length()) {
                        if (s.charAt(i) == '\"') {
                            break Label_0103;
                        }
                        ++i;
                    }
                    return i;
                }
                if (char1 == '\'') {
                    ++i;
                    while (i < s.length()) {
                        if (s.charAt(i) == '\'') {
                            break Label_0103;
                        }
                        ++i;
                    }
                    return i;
                }
            }
            ++i;
        }
        return i;
    }
    
    public String name() {
        return this.Tag;
    }
    
    public int countParams() {
        return this.N;
    }
    
    public String getParam(final int n) {
        return this.Param[n];
    }
    
    public String getValue(final int n) {
        return this.Value[n];
    }
    
    public boolean hasParam(final String s) {
        for (int i = 0; i < this.N; ++i) {
            if (this.Param[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public String getValue(final String s) {
        for (int i = 0; i < this.N; ++i) {
            if (this.Param[i].equals(s)) {
                return this.Value[i];
            }
        }
        return null;
    }
}
