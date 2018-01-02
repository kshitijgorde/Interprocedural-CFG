// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import org.apache.commons.httpclient.util.Base64;
import java.io.File;

public class MimeHeader
{
    private MyFile fFile;
    private String boundary;
    private String newline;
    private String tagName;
    
    MimeHeader(final MyFile f, final String tagname, final String boundary) {
        this.newline = System.getProperty("line.separator");
        this.debug("MimeHeader() file=" + f + " tag=" + tagname + " boundary=" + boundary);
        this.fFile = f;
        this.tagName = tagname;
        this.boundary = boundary;
    }
    
    public MimeHeader(final MyFile f) {
        this.newline = System.getProperty("line.separator");
        this.fFile = f;
    }
    
    public long getContentLength() {
        return this.fFile.length();
    }
    
    public File getFile() {
        return this.fFile;
    }
    
    public String getFooter() {
        final String strFooter = String.valueOf(this.newline) + "--" + this.boundary;
        return strFooter;
    }
    
    public String getHeader() {
        String strHeader = this.newline;
        strHeader = String.valueOf(strHeader) + "content-disposition: attachment;";
        strHeader = String.valueOf(strHeader) + " ";
        strHeader = String.valueOf(strHeader) + "name=\"";
        strHeader = String.valueOf(strHeader) + this.tagName;
        strHeader = String.valueOf(strHeader) + "\";";
        strHeader = String.valueOf(strHeader) + " ";
        strHeader = String.valueOf(strHeader) + "filename=\"";
        strHeader = String.valueOf(strHeader) + this.fFile.getName();
        strHeader = String.valueOf(strHeader) + "\";";
        strHeader = String.valueOf(strHeader) + " ";
        strHeader = String.valueOf(strHeader) + "modification-date=\"";
        strHeader = String.valueOf(strHeader) + this.fFile.lastModified();
        strHeader = String.valueOf(strHeader) + "\"";
        strHeader = String.valueOf(strHeader) + this.newline;
        strHeader = String.valueOf(strHeader) + "Content-Length: " + this.fFile.length() + this.newline;
        strHeader = String.valueOf(strHeader) + "Content-Description: " + this.fFile.getAbsolutePath() + this.newline;
        strHeader = String.valueOf(strHeader) + "Content-Transfer-Encoding: binary" + this.newline;
        if (Configurator.getUseRecursivePaths()) {
            final String test = this.fFile.getRelativeFilename();
            final byte[] test2 = Base64.encode(test.getBytes());
            final String test3 = new String(test2);
            strHeader = String.valueOf(strHeader) + "Content-Type: jupload/" + test3 + this.newline;
        }
        else {
            strHeader = String.valueOf(strHeader) + "Content-Type: " + this.getContentType() + this.newline;
        }
        strHeader = String.valueOf(strHeader) + this.newline;
        return strHeader;
    }
    
    public String getLastFooter() {
        final String strFooter = String.valueOf(this.newline) + "--" + this.boundary + "--";
        return strFooter;
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
    
    public int length() {
        return this.getHeader().length();
    }
    
    private String getContentType() {
        final String ext = this.fFile.getName().toLowerCase();
        final String[][] mimes = { { "application/andrew-inset", "ez" }, { "application/mac-binhex40", "hqx" }, { "application/mac-compactpro", "cpt" }, { "application/msword", "doc" }, { "application/octet-stream", "bin", "dms", "lha", "lzh", "exe", "class", "so", "dll" }, { "application/oda", "oda" }, { "application/pdf", "pdf" }, { "application/postscript", "ai", "eps", "ps" }, { "application/smil", "smi", "smil" }, { "application/vnd.wap.wbxml", "wbxml" }, { "application/vnd.wap.wmlc", "wmlc" }, { "application/vnd.wap.wmlscriptc", "wmlsc" }, { "application/x-bcpio", "bcpio" }, { "application/x-cdlink", "vcd" }, { "application/x-chess-pgn", "pgn" }, { "application/x-cpio", "cpio" }, { "application/x-csh", "csh" }, { "application/x-director", "dcr", "dir", "dxr" }, { "application/x-dvi", "dvi" }, { "application/x-futuresplash", "spl" }, { "application/x-gtar", "gtar" }, { "application/x-hdf", "hdf" }, { "application/x-javascript", "js" }, { "application/x-koan", "skp", "skd", "skt", "skm" }, { "application/x-latex", "latex" }, { "application/x-netcdf", "nc", "cdf" }, { "application/x-sh", "sh" }, { "application/x-shar", "shar" }, { "application/x-shockwave-flash", "swf" }, { "application/x-stuffit", "sit" }, { "application/x-sv4cpio", "sv4cpio" }, { "application/x-sv4crc", "sv4crc" }, { "application/x-tar", "tar" }, { "application/x-tcl", "tcl" }, { "application/x-tex", "tex" }, { "application/x-texinfo", "texinfo", "texi" }, { "application/x-troff", "t", "tr", "roff" }, { "application/x-troff-man", "man" }, { "application/x-troff-me", "me" }, { "application/x-troff-ms", "ms" }, { "application/x-ustar", "ustar" }, { "application/x-wais-source", "src" }, { "application/xhtml+xml", "xhtml", "xht" }, { "application/zip", "zip" }, { "audio/basic", "au", "snd" }, { "audio/midi", "mid", "midi", "kar" }, { "audio/mpeg", "mpga", "mp2", "mp3" }, { "audio/x-aiff", "aif", "aiff", "aifc" }, { "audio/x-mpegurl", "m3u" }, { "audio/x-pn-realaudio", "ram", "rm" }, { "audio/x-pn-realaudio-plugin", "rpm" }, { "audio/x-realaudio", "ra" }, { "audio/x-wav", "wav" }, { "chemical/x-pdb", "pdb" }, { "chemical/x-xyz", "xyz" }, { "image/bmp", "bmp" }, { "image/gif", "gif" }, { "image/ief", "ief" }, { "image/jpeg", "jpeg", "jpg", "jpe" }, { "image/png", "png" }, { "image/tiff", "tiff", "tif" }, { "image/vnd.djvu", "djvu", "djv" }, { "image/vnd.wap.wbmp", "wbmp" }, { "image/x-cmu-raster", "ras" }, { "image/x-portable-anymap", "pnm" }, { "image/x-portable-bitmap", "pbm" }, { "image/x-portable-graymap", "pgm" }, { "image/x-portable-pixmap", "ppm" }, { "image/x-rgb", "rgb" }, { "image/x-xbitmap", "xbm" }, { "image/x-xpixmap", "xpm" }, { "image/x-xwindowdump", "xwd" }, { "model/iges", "igs", "iges" }, { "model/mesh", "msh", "mesh", "silo" }, { "model/vrml", "wrl", "vrml" }, { "text/css", "css" }, { "text/html", "html", "htm" }, { "text/plain", "asc", "txt" }, { "text/richtext", "rtx" }, { "text/rtf", "rtf" }, { "text/sgml", "sgml", "sgm" }, { "text/tab-separated-values", "tsv" }, { "text/vnd.wap.wml", "wml" }, { "text/vnd.wap.wmlscript", "wmls" }, { "text/x-setext", "etx" }, { "text/xml", "xml", "xsl" }, { "video/mpeg", "mpeg", "mpg", "mpe" }, { "video/quicktime", "qt", "mov" }, { "video/vnd.mpegurl", "mxu" }, { "video/x-msvideo", "avi" }, { "video/x-sgi-movie", "movie" }, { "x-conference/x-cooltalk", "ice" } };
        String type = "application/octet-stream";
        for (int i = 0; i < mimes.length; ++i) {
            for (int j = 1; j < mimes[i].length; ++j) {
                if (ext.endsWith(mimes[i][j])) {
                    type = mimes[i][0];
                }
            }
        }
        return type;
    }
    
    public String getPutHeader(final String strHost, final long maxlength, final long offset) {
        String strPutHeader = "";
        final String strRessourceURI = this.getFile().getName();
        if (ProxyConfig.useProxy) {
            strPutHeader = String.valueOf(strPutHeader) + "PUT " + Configurator.getActionURL() + strRessourceURI + " HTTP/1.1" + this.newline;
        }
        else {
            strPutHeader = String.valueOf(strPutHeader) + "PUT " + Configurator.getActionURL().getPath() + strRessourceURI + " HTTP/1.1" + this.newline;
        }
        strPutHeader = String.valueOf(strPutHeader) + "Host: " + strHost + this.newline;
        strPutHeader = String.valueOf(strPutHeader) + "User-Agent: JUpload (www.haller-systemservice.net)" + this.newline;
        long len = 0L;
        final long rest = 0L;
        if (this.getContentLength() <= maxlength) {
            len = this.getContentLength();
        }
        else {
            len = maxlength;
        }
        if (offset + maxlength > this.getContentLength()) {
            len = this.getContentLength() - offset;
        }
        strPutHeader = String.valueOf(strPutHeader) + "Content-Length: " + len + this.newline;
        strPutHeader = String.valueOf(strPutHeader) + "Content-Range: bytes " + offset + "-" + (offset + len) + "/" + this.getContentLength() + this.newline;
        strPutHeader = String.valueOf(strPutHeader) + "Content-Type: " + this.getContentType() + this.newline;
        strPutHeader = String.valueOf(strPutHeader) + "Connection: keep-alive" + this.newline;
        if (ProxyConfig.useProxy) {
            strPutHeader = String.valueOf(strPutHeader) + "Proxy-Connection: keep-alive" + this.newline;
        }
        strPutHeader = String.valueOf(strPutHeader) + this.newline;
        return strPutHeader;
    }
    
    public String getHeadHeader(final String strHost) {
        String strHeadHeader = "";
        final String strRessourceURI = this.getFile().getName();
        if (ProxyConfig.useProxy) {
            strHeadHeader = String.valueOf(strHeadHeader) + "HEAD " + Configurator.getActionURL() + strRessourceURI + " HTTP/1.1" + this.newline;
        }
        else {
            strHeadHeader = String.valueOf(strHeadHeader) + "HEAD " + Configurator.getActionURL().getPath() + strRessourceURI + " HTTP/1.1" + this.newline;
        }
        strHeadHeader = String.valueOf(strHeadHeader) + "Host: " + strHost + this.newline;
        strHeadHeader = String.valueOf(strHeadHeader) + "User-Agent: JUpload (www.haller-systemservice.net)" + this.newline;
        strHeadHeader = String.valueOf(strHeadHeader) + "Connection: keep-alive" + this.newline;
        if (ProxyConfig.useProxy) {
            strHeadHeader = String.valueOf(strHeadHeader) + "Proxy-Connection: keep-alive" + this.newline;
        }
        strHeadHeader = String.valueOf(strHeadHeader) + this.newline;
        return strHeadHeader;
    }
}
