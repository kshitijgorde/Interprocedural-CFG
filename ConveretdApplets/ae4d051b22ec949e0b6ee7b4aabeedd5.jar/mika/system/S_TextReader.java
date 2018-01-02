// 
// Decompiled by Procyon v0.5.30
// 

package mika.system;

import java.net.URL;
import java.io.InputStream;
import java.io.StreamTokenizer;

public class S_TextReader
{
    StreamTokenizer m_st;
    InputStream m_isStream;
    int m_type;
    public static final int UNDEFINED = 0;
    public static final int WORD = 0;
    public static final int NUMBER = 1;
    public static final int EOF = 2;
    
    public S_TextReader() {
        this.m_type = 2;
    }
    
    public S_TextReader(final URL url, final String s) throws Exception {
        this.m_type = 2;
        this.construct(url, s);
    }
    
    public S_TextReader(final URL url) throws Exception {
        this.m_type = 2;
        this.construct(url);
    }
    
    public S_TextReader(final InputStream inputStream) throws Exception {
        this.m_type = 2;
        this.construct(inputStream);
    }
    
    public void construct(final URL url, final String s) throws Exception {
        this.construct(new URL(url, s).openConnection().getInputStream());
    }
    
    public void construct(final URL url) throws Exception {
        this.construct(url.openConnection().getInputStream());
    }
    
    public void construct(final InputStream isStream) throws Exception {
        this.m_isStream = isStream;
        (this.m_st = new StreamTokenizer(isStream)).eolIsSignificant(false);
        this.m_st.commentChar(35);
        this.m_st.parseNumbers();
        this.m_st.slashSlashComments(false);
        this.m_st.slashStarComments(false);
    }
    
    public void destruct() throws Exception {
        this.m_isStream.close();
        this.m_isStream = null;
        this.m_st = null;
    }
    
    public int nextToken() throws Exception {
        this.m_st.nextToken();
        switch (this.m_st.ttype) {
            case -3: {
                this.m_type = 0;
                break;
            }
            case -2: {
                this.m_type = 1;
                break;
            }
            case -1: {
                this.m_type = 2;
                break;
            }
            default: {
                this.m_type = this.m_st.ttype;
                break;
            }
        }
        return this.m_type;
    }
    
    public int getToken() {
        return this.m_type;
    }
    
    public String getStringValue() {
        return this.m_st.sval;
    }
    
    public double getDoubleValue() {
        return this.m_st.nval;
    }
    
    public float getFloatValue() {
        return (float)this.m_st.nval;
    }
    
    public int getIntegerValue() {
        return (int)this.m_st.nval;
    }
    
    public int getCurrentLine() {
        return this.m_st.lineno();
    }
    
    public String readLine() throws Exception {
        this.m_st.wordChars(32, 32);
        this.m_st.wordChars(48, 57);
        final String stringValue = this.readStringValue();
        this.m_st.parseNumbers();
        this.m_st.whitespaceChars(32, 32);
        return stringValue;
    }
    
    public String readStringValue() throws Exception {
        this.m_st.wordChars(47, 47);
        this.m_st.wordChars(92, 92);
        this.m_st.wordChars(95, 95);
        this.m_st.wordChars(58, 58);
        this.m_st.wordChars(126, 126);
        this.m_st.wordChars(40, 40);
        this.m_st.wordChars(41, 41);
        this.m_st.wordChars(44, 44);
        this.m_st.wordChars(46, 46);
        this.m_st.wordChars(33, 33);
        this.m_st.wordChars(63, 63);
        this.m_st.wordChars(39, 39);
        this.m_st.wordChars(34, 34);
        this.m_st.wordChars(60, 60);
        this.m_st.wordChars(62, 62);
        final int nextToken = this.nextToken();
        String s = null;
        if (nextToken == 0) {
            s = this.getStringValue();
        }
        else if (nextToken == 1) {
            s = "" + this.getIntegerValue() + this.readStringValue();
        }
        else {
            S_Debug.print("[S_TextReader::readStringValue] String value not found! Line: " + this.m_st.lineno());
        }
        this.m_st.ordinaryChar(47);
        this.m_st.ordinaryChar(92);
        this.m_st.ordinaryChar(95);
        this.m_st.ordinaryChar(58);
        this.m_st.ordinaryChar(126);
        this.m_st.ordinaryChar(40);
        this.m_st.ordinaryChar(41);
        this.m_st.ordinaryChar(44);
        this.m_st.ordinaryChar(46);
        this.m_st.ordinaryChar(33);
        this.m_st.ordinaryChar(63);
        this.m_st.ordinaryChar(39);
        this.m_st.ordinaryChar(34);
        this.m_st.ordinaryChar(60);
        this.m_st.ordinaryChar(62);
        return s;
    }
    
    public int readIntegerValue() throws Exception {
        if (this.nextToken() == 1) {
            return this.getIntegerValue();
        }
        S_Debug.print("[S_TextReader::readIntegerValue] Integer value not found! Line: " + this.m_st.lineno());
        return 0;
    }
    
    public float readFloatValue() throws Exception {
        if (this.nextToken() == 1) {
            return this.getFloatValue();
        }
        S_Debug.print("[S_TextReader::readFloatValue] Float value not found! Line: " + this.m_st.lineno());
        return 0.0f;
    }
}
