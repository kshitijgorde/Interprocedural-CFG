// 
// Decompiled by Procyon v0.5.30
// 

package sexy.util;

import java.util.Hashtable;
import java.io.BufferedReader;

public class XMLParser
{
    private String mFileName;
    private String mErrorText;
    private int mLineNum;
    private BufferedReader mFile;
    private boolean mHasFailed;
    private String mBufferedText;
    private String mSection;
    
    public XMLParser(final BufferedReader mFile) {
        this.mFileName = "";
        this.mErrorText = "";
        this.mBufferedText = "";
        this.mSection = "";
        this.mFile = mFile;
        this.Init();
    }
    
    public XMLParser() {
        this.mFileName = "";
        this.mErrorText = "";
        this.mBufferedText = "";
        this.mSection = "";
        this.Init();
    }
    
    public String XMLDecodeString(final String s) {
        String string = new String();
        for (int i = 0; i < s.length(); ++i) {
            char char1 = s.charAt(i);
            if (char1 == '&') {
                final int index = s.indexOf(59, i);
                if (index != -1) {
                    final String substring = s.substring(i + 1, index);
                    i = index;
                    if (substring.equals("lt")) {
                        char1 = '<';
                    }
                    else if (substring.equals("amp")) {
                        char1 = '&';
                    }
                    else if (substring.equals("gt")) {
                        char1 = '>';
                    }
                    else if (substring.equals("quot")) {
                        char1 = '\"';
                    }
                    else if (substring.equals("apos")) {
                        char1 = '\'';
                    }
                    else if (substring.equals("nbsp")) {
                        char1 = ' ';
                    }
                    else if (substring.equals("cr")) {
                        char1 = '\n';
                    }
                }
            }
            string += char1;
        }
        return string;
    }
    
    public void SetStringSource(final String mBufferedText) {
        this.Init();
        this.mBufferedText = mBufferedText;
    }
    
    public boolean NextElement(final XMLElement xmlElement) {
        boolean b;
        do {
            b = false;
            xmlElement.mType = 0;
            xmlElement.mSection = this.mSection;
            xmlElement.mValue = "";
            xmlElement.mInstruction = "";
            (xmlElement.mAttributes = new Hashtable()).clear();
            int n = 0;
            boolean b2 = false;
            int n2 = 0;
            int n3 = 0;
            String s = "";
            String s2 = "";
            while (true) {
                char char1 = 'z';
                int n4;
                if (this.mBufferedText.length() > 0) {
                    char1 = this.mBufferedText.charAt(0);
                    this.mBufferedText = this.mBufferedText.substring(1);
                    n4 = 1;
                }
                else {
                    try {
                        char1 = (char)this.mFile.read();
                        n4 = 1;
                    }
                    catch (Exception ex) {
                        n4 = 0;
                    }
                }
                if (n4 != 1) {
                    if (xmlElement.mType != 0) {
                        this.Fail("Unexpected End of File");
                    }
                    return false;
                }
                boolean b3 = false;
                if (char1 == '\n') {
                    ++this.mLineNum;
                }
                if (xmlElement.mType == 4) {
                    boolean b4 = true;
                    String s3 = xmlElement.mValue;
                    if ((xmlElement.mInstruction != null && xmlElement.mInstruction.length() != 0) || Character.isWhitespace(char1)) {
                        b4 = false;
                        s3 = xmlElement.mInstruction;
                        if (s3 == null) {
                            s3 = "";
                        }
                    }
                    final String string = s3 + char1;
                    final int length = string.length();
                    if (char1 == '>' && length >= 2 && string.charAt(length - 2) == '?') {
                        final String substring = string.substring(0, length - 2);
                        if (b4) {
                            xmlElement.mValue = substring;
                            break;
                        }
                        xmlElement.mInstruction = substring;
                        break;
                    }
                    else if (b4) {
                        xmlElement.mValue = string;
                    }
                    else {
                        xmlElement.mInstruction = string;
                    }
                }
                else {
                    if (char1 == '\"') {
                        b2 = !b2;
                    }
                    else if (!b2 || xmlElement.mType == 3) {
                        if (char1 == '<') {
                            if (xmlElement.mType == 3) {
                                this.mBufferedText = "" + char1 + this.mBufferedText;
                                break;
                            }
                            if (xmlElement.mType != 0) {
                                this.Fail("Unexpected '<'");
                                return false;
                            }
                            xmlElement.mType = 1;
                        }
                        else if (char1 == '>') {
                            if (xmlElement.mType == 1) {
                                if (xmlElement.mValue.equals("!--")) {
                                    b = true;
                                    break;
                                }
                                final int length2 = xmlElement.mValue.length();
                                if (length2 > 0 && xmlElement.mValue.charAt(length2 - 1) == '/') {
                                    xmlElement.mValue = xmlElement.mValue.substring(0, length2 - 1);
                                    this.mBufferedText = "</" + xmlElement.mValue + ">" + this.mBufferedText;
                                }
                                if (this.mSection.length() != 0) {
                                    this.mSection += "/";
                                }
                                this.mSection += xmlElement.mValue;
                                break;
                            }
                            else {
                                if (xmlElement.mType != 2) {
                                    this.Fail("Unexpected '>'");
                                    return false;
                                }
                                final int lastIndex = this.mSection.lastIndexOf(47);
                                if (lastIndex == -1 && this.mSection.length() == 0) {
                                    this.Fail("Unexpected End");
                                    return false;
                                }
                                final String substring2 = this.mSection.substring(lastIndex + 1);
                                if (!substring2.equals(xmlElement.mValue)) {
                                    this.Fail("End '" + xmlElement.mValue + "' Doesn't Match Start '" + substring2 + "'");
                                    return false;
                                }
                                if (lastIndex == -1) {
                                    this.mSection = "";
                                    break;
                                }
                                this.mSection = this.mSection.substring(0, lastIndex);
                                break;
                            }
                        }
                        else if (char1 == '/' && xmlElement.mType == 1 && xmlElement.mValue == "") {
                            xmlElement.mType = 2;
                        }
                        else if (char1 == '?' && xmlElement.mType == 1 && xmlElement.mValue == "") {
                            xmlElement.mType = 4;
                        }
                        else if (Character.isWhitespace(char1)) {
                            if (!xmlElement.mValue.equals("")) {
                                n = 1;
                            }
                        }
                        else {
                            if (char1 <= ' ') {
                                this.Fail("Illegal Character");
                                return false;
                            }
                            b3 = true;
                        }
                    }
                    else {
                        b3 = true;
                    }
                    if (!b3) {
                        continue;
                    }
                    if (xmlElement.mType == 0) {
                        xmlElement.mType = 3;
                    }
                    if (xmlElement.mType == 1) {
                        if (n != 0) {
                            if (n2 == 0 || (n3 != 0 && s2.length() > 0)) {
                                if (n2 != 0) {
                                    xmlElement.mAttributes.put(this.XMLDecodeString(s), this.XMLDecodeString(s2));
                                    s = "";
                                    s2 = "";
                                }
                                else {
                                    n2 = 1;
                                }
                                n3 = 0;
                            }
                            n = 0;
                        }
                        String mValue = "";
                        int n5 = 0;
                        if (n2 == 0) {
                            mValue = xmlElement.mValue;
                            n5 = 0;
                        }
                        else if (char1 == '=') {
                            n3 = 1;
                        }
                        else if (n3 == 0) {
                            mValue = s;
                            n5 = 1;
                        }
                        else {
                            n5 = 2;
                            mValue = s2;
                        }
                        final String string2 = mValue + char1;
                        if (n5 == 0 && !string2.equals("=")) {
                            xmlElement.mValue = string2;
                        }
                        else if (n5 == 1 && !string2.equals("=")) {
                            s = string2;
                        }
                        else {
                            if (string2.equals("=")) {
                                continue;
                            }
                            s2 = string2;
                        }
                    }
                    else {
                        if (n != 0) {
                            xmlElement.mValue += " ";
                            n = 0;
                        }
                        xmlElement.mValue += char1;
                    }
                }
            }
            if (s.length() > 0) {
                xmlElement.mAttributes.put(this.XMLDecodeString(s), this.XMLDecodeString(s2));
            }
            xmlElement.mValue = this.XMLDecodeString(xmlElement.mValue);
        } while (b);
        return true;
    }
    
    public boolean HasFailed() {
        return this.mHasFailed;
    }
    
    protected void Fail(final String mErrorText) {
        this.mHasFailed = true;
        this.mErrorText = mErrorText;
    }
    
    public int GetCurrentLineNum() {
        return this.mLineNum;
    }
    
    protected void Init() {
        this.mSection = "";
        this.mLineNum = 1;
        this.mHasFailed = false;
        this.mErrorText = "";
    }
    
    public String GetErrorText() {
        return this.mErrorText;
    }
}
