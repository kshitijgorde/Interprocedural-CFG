// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Enumeration;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

public class NFHtmlFile extends Vector
{
    public void load(final String s) throws Exception {
        this.load(NFUtil.getFileURL(s));
    }
    
    public void load(final URL url) throws Exception {
        InputStream inputStream;
        if (url.getProtocol().equalsIgnoreCase("file") || NFContext.getUserAgentType() == 0) {
            inputStream = url.openStream();
        }
        else {
            inputStream = new NFHttpClient().getContentAsInputStream(url);
        }
        this.load(inputStream);
        inputStream.close();
    }
    
    public void load(final InputStream inputStream) throws Exception {
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        this.load(bufferedReader);
        bufferedReader.close();
        inputStreamReader.close();
    }
    
    public void load(final Reader reader) throws Exception {
        int n = 0;
        int n2 = 0;
        char c = '\'';
        final StringBuffer sb = new StringBuffer();
        while (true) {
            final int read = reader.read();
            if (read == -1) {
                break;
            }
            final char c2 = (char)read;
            if (c2 == '<' && n == 0) {
                if (sb.length() > 0) {
                    this.addElement(sb.toString());
                }
                sb.setLength(0);
                n = 1;
            }
            else if (n2 != 0) {
                sb.append(c2);
                if (c2 != c) {
                    continue;
                }
                n2 = 0;
            }
            else if (n != 0 && (c2 == '\'' || c2 == '\"')) {
                sb.append(c2);
                n2 = 1;
                c = c2;
            }
            else if (n != 0 && c2 == '>') {
                this.addElement(NFHtmlTag.parse(sb));
                sb.setLength(0);
                n = 0;
            }
            else {
                sb.append(c2);
            }
        }
        if (n != 0) {
            throw new Exception("Unexpected End of File");
        }
        if (sb.length() > 0) {
            this.addElement(sb.toString());
        }
    }
    
    public String dumpStr(String replace, final int n) {
        replace = replace.replace('\n', '|');
        if (n >= replace.length() + 3) {
            return replace;
        }
        final int length = replace.length();
        return replace.substring(0, n / 2) + "..." + replace.substring(length - n / 2, length);
    }
    
    public void dump(final Vector vector, final String s) {
        final int size = vector.size();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; ++i) {
            final String element = vector.elementAt(i);
            if (element instanceof String) {
                NFDebug.print(s + "STR: <" + this.dumpStr(element, 64) + ">");
            }
            else if (element instanceof NFHtmlMultiTag) {
                final NFHtmlMultiTag nfHtmlMultiTag = (NFHtmlMultiTag)element;
                NFDebug.print(s + "TAG: <" + nfHtmlMultiTag.beginTag.name + ">");
                this.dump(nfHtmlMultiTag, s + "\t");
                NFDebug.print(s + "TAG: <" + nfHtmlMultiTag.endTag.name + ">");
            }
            else if (element instanceof NFHtmlTag) {
                final NFHtmlTag nfHtmlTag = (NFHtmlTag)element;
                sb.setLength(0);
                sb.append(s);
                sb.append("TAG: <");
                sb.append(nfHtmlTag.name);
                final Enumeration<String> keys = nfHtmlTag.keys();
                while (keys.hasMoreElements()) {
                    final String s2 = keys.nextElement();
                    final String s3 = nfHtmlTag.get(s2);
                    sb.append(" ");
                    sb.append(s2);
                    if (s3.length() > 0) {
                        sb.append("=");
                        sb.append(s3);
                    }
                }
                sb.append(">");
                NFDebug.print(sb.toString());
            }
        }
    }
    
    public static void main(final String[] array) {
        final NFHtmlFile nfHtmlFile = new NFHtmlFile();
        for (int i = 0; i < array.length; ++i) {
            nfHtmlFile.removeAllElements();
            try {
                nfHtmlFile.load(array[i]);
                NFHtmlMultiTag.parse(nfHtmlFile, "a", "/a");
                NFHtmlMultiTag.parse(nfHtmlFile, "applet", "/applet");
                NFHtmlMultiTag.parse(nfHtmlFile, "body", "/body");
                NFHtmlMultiTag.parse(nfHtmlFile, "center", "/center");
                NFHtmlMultiTag.parse(nfHtmlFile, "dl", "/dl");
                nfHtmlFile.dump(nfHtmlFile, "");
            }
            catch (Exception ex) {
                NFDebug.print(array[i] + ": " + ex.getMessage());
            }
        }
    }
}
