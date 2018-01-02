// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.net.URL;

public final class Document
{
    private boolean gotDocumentInfo;
    private String contentType;
    private String contentEncoding;
    private long lastModified;
    private int contentLength;
    private URL url;
    private String fileName;
    private boolean gotTitle;
    private String title;
    private String documentInfo;
    private static Hashtable encodings;
    private static String[] MONTHS;
    private static final int ENCODING_BUFFER_LENGTH = 400;
    
    public Document(final URL url, final String contentEncoding) {
        this.url = url;
        this.contentEncoding = contentEncoding;
        this.contentLength = -1;
    }
    
    private static String normalizedEncoding(final String s) {
        return Document.encodings.get(s.toLowerCase());
    }
    
    public static URL buildUrl(final URL url, String substring) throws MalformedURLException {
        final int length = substring.length();
        URL url2;
        if (substring.startsWith("../")) {
            int n = 3;
            final String file = url.getFile();
            int n2 = file.lastIndexOf(47);
            if (n2 != -1) {
                n2 = file.lastIndexOf(47, n2 - 1);
            }
            while (substring.startsWith("../", n)) {
                n2 = file.lastIndexOf(47, n2 - 1);
                n += 3;
                if (n2 == -1) {
                    throw new MalformedURLException("Higher than root: " + url.toString() + " + " + substring);
                }
            }
            if (n != length) {
                substring = substring.substring(n, length);
            }
            else {
                substring = "";
            }
            String s;
            if (n2 != -1) {
                s = file.substring(0, n2 + 1);
                if (n != length) {
                    s += substring;
                }
            }
            else {
                s = "/" + substring;
            }
            url2 = new URL(url.getProtocol(), url.getHost(), s);
        }
        else if (substring.startsWith("/")) {
            url2 = new URL(url.getProtocol(), url.getHost(), substring);
        }
        else if (substring.startsWith("./")) {
            url2 = new URL(url, substring.substring(2, length));
        }
        else if (substring.startsWith("?")) {
            url2 = new URL(url.getProtocol(), url.getHost(), url.getFile() + substring);
        }
        else {
            url2 = new URL(url, substring);
        }
        return url2;
    }
    
    public URL buildUrl(final String s) throws MalformedURLException {
        return buildUrl(this.url(), s);
    }
    
    public URL url() {
        return this.url;
    }
    
    private static String getPath(final URL url) {
        String s = url.getFile();
        final int index = s.indexOf(63);
        if (index != -1) {
            s = s.substring(0, index);
        }
        final int index2 = s.indexOf(35);
        if (index2 != -1) {
            s = s.substring(0, index2);
        }
        return s;
    }
    
    public String pathRelativeTo(final URL url) {
        if (!url.getHost().equals(this.url.getHost())) {
            throw new IllegalArgumentException("Unequal hosts: url.host = " + this.url.getHost() + " baseUrl.host = " + url.getHost());
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(getPath(url), "/");
        final StringTokenizer stringTokenizer2 = new StringTokenizer(getPath(this.url), "/");
        String nextToken = null;
        String s = null;
        int n = 0;
        boolean b = true;
        while (stringTokenizer.hasMoreTokens() && stringTokenizer2.hasMoreTokens()) {
            nextToken = stringTokenizer2.nextToken();
            s = stringTokenizer.nextToken();
            if (!nextToken.equals(s)) {
                n = 1;
                break;
            }
            b = false;
        }
        while (stringTokenizer.hasMoreTokens()) {
            s = stringTokenizer.nextToken();
            ++n;
        }
        if (s != null && s.indexOf(46) != -1 && !getPath(url).endsWith("/")) {
            --n;
        }
        final StringBuffer sb = new StringBuffer();
        if (b) {
            sb.append('/');
        }
        else {
            for (int i = 0; i < n; ++i) {
                sb.append("../");
            }
        }
        int n2 = 0;
        if (nextToken != null) {
            sb.append(nextToken);
            n2 = 1;
        }
        while (stringTokenizer2.hasMoreTokens()) {
            if (n2 != 0) {
                sb.append('/');
            }
            sb.append(stringTokenizer2.nextToken());
            n2 = 1;
        }
        return sb.toString();
    }
    
    public String fileName() {
        if (this.fileName == null) {
            final String file = this.url().getFile();
            final int lastIndex = file.lastIndexOf(47);
            if (lastIndex != -1) {
                this.fileName = file.substring(lastIndex + 1);
            }
            else {
                this.fileName = file;
            }
        }
        return this.fileName;
    }
    
    public String title() {
        return this.title;
    }
    
    public String titleOrFileNameIfNull() {
        if (this.title != null) {
            return this.title;
        }
        return this.fileName();
    }
    
    public long lastModified() {
        return this.lastModified;
    }
    
    public int contentLength() {
        return this.contentLength;
    }
    
    public String contentType() {
        return this.contentType;
    }
    
    public String contentEncoding() {
        return this.contentEncoding;
    }
    
    private Reader openConnection() throws IOException, SecurityException {
        final URLConnection openConnection = this.url.openConnection();
        if (!this.gotDocumentInfo) {
            this.gotDocumentInfo = true;
            this.lastModified = openConnection.getLastModified();
            if (this.contentEncoding == null) {
                this.contentEncoding = openConnection.getContentEncoding();
                if (this.contentEncoding != null) {
                    this.contentEncoding = normalizedEncoding(this.contentEncoding);
                }
            }
            this.contentType = openConnection.getContentType();
            this.contentLength = openConnection.getContentLength();
        }
        if (this.contentEncoding == null) {
            return this.guessEncoding(openConnection.getInputStream());
        }
        final InputStream inputStream = openConnection.getInputStream();
        try {
            return new BufferedReader(new InputStreamReader(inputStream, this.contentEncoding));
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return new BufferedReader(new InputStreamReader(inputStream));
        }
    }
    
    private static void copyByteToChar(final byte[] array, final int n, final char[] array2) {
        for (int i = 0; i < n; ++i) {
            array2[i] = (char)(0xFF & array[i]);
        }
    }
    
    private Reader guessEncoding(final InputStream inputStream) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        final byte[] array = new byte[400];
        final char[] array2 = new char[400];
        bufferedInputStream.mark(400);
        final SearchSieve searchSieve = new SearchSieve("<meta http-equiv=\"content-type\" content=\"text/html; charset=");
        final SearchSieve searchSieve2 = new SearchSieve("<meta http-equiv=content-type content=\"text/html; charset=");
        int n = 0;
        int read = 0;
        int n2 = -1;
        while (n < 400 && read != -1 && n2 == -1) {
            read = bufferedInputStream.read(array, 0, 400 - n);
            if (read != -1) {
                n += read;
                copyByteToChar(array, read, array2);
                n2 = searchSieve.scanLowerCase(array2, 0, read);
                if (n2 != -1) {
                    continue;
                }
                n2 = searchSieve2.scanLowerCase(array2, 0, read);
            }
        }
        if (n2 != -1) {
            this.contentEncoding = this.finishReadingEncoding(array2, n2 + 1, read, bufferedInputStream, n);
            this.contentEncoding = normalizedEncoding(this.contentEncoding);
        }
        bufferedInputStream.reset();
        if (this.contentEncoding != null) {
            try {
                return new InputStreamReader(bufferedInputStream, this.contentEncoding);
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                return new InputStreamReader(bufferedInputStream);
            }
        }
        return new InputStreamReader(bufferedInputStream);
    }
    
    private String finishReadingEncoding(final char[] array, final int n, final int n2, final InputStream inputStream, final int n3) throws IOException {
        int n4 = -1;
        for (int i = n; i < n2; ++i) {
            if (array[i] == '\"') {
                n4 = i;
                break;
            }
        }
        if (n4 != -1) {
            return new String(array, n, n4 - n);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(array, n, n2 - n);
        for (int n5 = 400 - n3, j = 0; j < n5; ++j) {
            final int read = inputStream.read();
            if (read == -1) {
                break;
            }
            if (read == 34) {
                break;
            }
            sb.append((char)read);
        }
        return sb.toString();
    }
    
    private StringBuffer createBufferIfTitleBegins(final SearchSieve searchSieve, final char[] array, final int n) {
        final int scanLowerCase = searchSieve.scanLowerCase(array, 0, n);
        StringBuffer sb = null;
        if (scanLowerCase != -1) {
            int n2 = -1;
            for (int i = scanLowerCase + 1; i < n; ++i) {
                if (array[i] == '<') {
                    n2 = i;
                    break;
                }
            }
            if (n2 != -1) {
                this.title = new String(array, scanLowerCase + 1, n2 - scanLowerCase - 1);
                this.gotTitle = true;
            }
            else {
                sb = new StringBuffer();
                sb.append(array, 0, n - scanLowerCase - 1);
            }
        }
        return sb;
    }
    
    private boolean appendUpTo(final StringBuffer sb, final char[] array, final int n, final char c) {
        int n2;
        for (n2 = 0; n2 < n && array[n2] != '<'; ++n2) {}
        sb.append(array, 0, n2);
        return n2 == n;
    }
    
    public void beginReadingFor(final DocumentScanner documentScanner) throws IOException, SecurityException {
        final char[] buffer = documentScanner.buffer(this);
        final Reader openConnection = this.openConnection();
        SearchSieve searchSieve = null;
        StringBuffer bufferIfTitleBegins = null;
        if (!this.gotTitle) {
            searchSieve = new SearchSieve("<title>");
        }
        while (documentScanner.shouldContinue(this)) {
            final int read = openConnection.read(buffer);
            if (read == -1) {
                break;
            }
            if (!this.gotTitle) {
                if (bufferIfTitleBegins == null) {
                    bufferIfTitleBegins = this.createBufferIfTitleBegins(searchSieve, buffer, read);
                    if (bufferIfTitleBegins != null) {
                        searchSieve = null;
                    }
                }
                else if (this.appendUpTo(bufferIfTitleBegins, buffer, read, '<')) {
                    this.title = bufferIfTitleBegins.toString();
                    this.gotTitle = true;
                    bufferIfTitleBegins = null;
                }
            }
            documentScanner.scan(buffer, read, this);
        }
        this.gotTitle = true;
        openConnection.close();
    }
    
    public String toString() {
        return this.url().toString();
    }
    
    public boolean equals(final Object o) {
        return o instanceof Document && this.url.equals(((Document)o).url());
    }
    
    public int hashCode() {
        return this.url.hashCode();
    }
    
    static {
        (Document.encodings = new Hashtable()).put("iso-8859-1", "ISO8859_1");
        Document.encodings.put("iso8859_1", "ISO8859_1");
        Document.encodings.put("macintosh", "MacRoman");
        Document.encodings.put("macroman", "MacRoman");
        Document.encodings.put("gb2312", "EUC_CN");
        Document.encodings.put("euc_cn", "EUC_CN");
        Document.encodings.put("big5", "Big5");
        Document.encodings.put("utf-8", "UTF8");
        Document.encodings.put("utf8", "UTF8");
        Document.encodings.put("utf-16", "UTF-16");
        Document.encodings.put("utf16", "UTF-16");
        Document.encodings.put("windows-1251", "Cp1251");
        Document.encodings.put("cp1251", "Cp1251");
        Document.encodings.put("windows-1252", "Cp1252");
        Document.encodings.put("cp1252", "Cp1252");
        Document.encodings.put("cp1256", "Cp1256");
        Document.encodings.put("big5-hkscs", "Big5_HKSCS");
        Document.encodings.put("big5_hkscs", "Big5_HKSCS");
        Document.encodings.put("ibm037", "Cp037");
        Document.encodings.put("cp037", "Cp037");
        Document.encodings.put("ebcdic-cp-us", "Cp037");
        Document.encodings.put("ebcdic-cp-ca", "Cp037");
        Document.encodings.put("ebcdic-cp-wt", "Cp037");
        Document.encodings.put("ebcdic-cp-nl", "Cp037");
        Document.encodings.put("csibm037", "Cp037");
        Document.encodings.put("ibm273", "Cp273");
        Document.encodings.put("cp273", "Cp273");
        Document.encodings.put("csIBM273", "Cp273");
        Document.encodings.put("iso-8859-2", "ISO8859_2");
        Document.encodings.put("iso8859_2", "ISO8859_2");
        Document.encodings.put("iso-8859-5", "ISO8859_5");
        Document.encodings.put("iso8859_5", "ISO8859_5");
        Document.encodings.put("euc-jp", "JISAutoDetect");
        Document.encodings.put("iso-2022-jp", "JISAutoDetect");
        Document.encodings.put("shift_jis", "JISAutoDetect");
        Document.encodings.put("ibm857", "Cp857");
        Document.encodings.put("cp857", "Cp857");
        Document.encodings.put("windows-1254", "Cp1254");
        Document.encodings.put("cp1254", "Cp1254");
        Document.MONTHS = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    }
}
