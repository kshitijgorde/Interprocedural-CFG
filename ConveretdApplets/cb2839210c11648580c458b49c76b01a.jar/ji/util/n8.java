// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.util.Hashtable;

public class n8
{
    private static Hashtable a;
    private static final String[][] b;
    
    public static final String a(final String s, final String s2) {
        return a(n8.a, s, s2);
    }
    
    private static final String a(final Hashtable hashtable, final String s, final String s2) {
        String s3 = hashtable.get(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2))).toLowerCase());
        if (s3 == null) {
            s3 = hashtable.get(s2.toLowerCase());
        }
        if ("application/octet-stream".equals(s)) {
            s3 = hashtable.get(s2.toLowerCase());
            if (s3 == null) {
                s3 = hashtable.get(s.toLowerCase());
            }
        }
        if (s3 == null) {
            s3 = "xebinary.v1";
        }
        return s3;
    }
    
    static {
        n8.a = new Hashtable();
        b = new String[][] { { "application/msword", "Microsoft Word", "xedocument.v1", "doc", "dot" }, { "application/msexcel", "Microsoft Excel", "xespreadsheet.v1", "xls" }, { "application/vnd.ms-excel", "Microsoft Excel", "xespreadsheet.v1", "xls", "xla", "xlc", "xlm", "xlt" }, { "application/mspowerpoint", "Microsoft PowerPoint", "xepresentation.v1", "ppt" }, { "application/vnd.ms-powerpoint", "Microsoft PowerPoint", "xepresentation.v1", "ppt", "pps", "pot" }, { "application/vnd.ms-project", "Microsoft Project", "xeproject.v1", "mpp" }, { "application/vnd.ms-works", "Microsoft Works", "xedocument.v1", "wcm", "wdb", "wks", "wps" }, { "application/vnd.openxmlformats-officedocuments.wordprocessingml.document", "Microsoft Word", "xedocument.v1", "docx" }, { "application/vnd.openxmlformats-officedocuments.spreadsheetml.sheet", "Microsoft Excel", "xespreadsheet.v1", "xlsx" }, { "application/vnd.openxmlformats-officedocuments.presentationml.presentation", "Microsoft PowerPoint", "xepresentation.v1", "pptx" }, { "application/vnd.adobe.photoshop", "Photoshop", "xeimage.v1", "psd" }, { "application/vnd.ms-outlook", "Microsoft Outlook", "xeemail.v1", "msg" }, { "application/pdf", "PDF", "xeacrobat.v1", "pdf" }, { "application/rft", "RTF", "xedocument.v1", "rtf" }, { "application/acad", "AutoCad (NCSA)", "xedesign.v1", "dwg" }, { "application/x-compress", "Compressed", "xezip.v1", "z" }, { "application/x-zip-compressed", "Compressed", "xezip.v1", "zip" }, { "application/x-gzip", "GNU Zip", "xezip.v1", "gz" }, { "application/x-compressed", "Compressed", "xezip.v1", "tgz" }, { "application/x-gzip", "GNU Zip", "xezip.v1", "gz" }, { "application/x-javascript", "JavaScript", "xejs.v1", "js" }, { "application/x-msaccess", "Microsoft Access", "xedatabase.v1", "mdb" }, { "application/x-mspublisher", "Microsoft Publisher", "xedtpublisher.v1", "pub" }, { "application/x-mswrite", "Microsoft Write", "xedocument.v1", "wri" }, { "application/zip", "ZIP", "xezip.v1", "zip" }, { "application/octet-stream", "Unknown", "xebinary.v1", "*" }, { "application/vnd.oasis.opendocument.chart", "Open Office Chart", "xespreadsheet.v1", "odc" }, { "application/vnd.oasis.opendocument.chart-template", "Open Office Chart Template", "xespreadsheet.v1", "otc" }, { "application/vnd.oasis.opendocument.presentation", "Open Office Presentation", "xepresentation.v1", "odp" }, { "application/vnd.oasis.opendocument.spreadsheet", "Open Office Spreadsheet", "xespreadsheet.v1", "ods" }, { "application/vnd.oasis.opendocument.text", "Open Office Text Document", "xedocument.v1", "odt" }, { "application/vnd.oasis.opendocument.text-web", "Open Office Web Document", "xedocument.v1", "oth" }, { "message/rfc822", "Standard Email", "xeemail.v1", "eml" }, { "image/vnd.dwg", "AutoCad", "xedesign.v1", "dwg" }, { "image/vnd.dxf", "AutoCad", "xedesign.v1", "dxf" }, { "image/bmp", "Bitmap", "xeimage.v1", "bmp" }, { "image/gif", "GIF", "xeimage.v1", "gif" }, { "image/jpeg", "JPEG", "xeimage.v1", "jpg", "jpeg", "jpo" }, { "image/svg+xml", "SVG", "xeimage.v1", "svg" }, { "image/tiff", "TIFF", "xeimage.v1", "tif", "tiff" }, { "image/png", "PNG", "xeimage.v1", "png" }, { "image/x-cmu-raster", "Raster", "xeimage.v1", "ras" }, { "text/plain", "Plain Text", "xetextdoc.v1", "txt" }, { "text/html", "HTML", "xeweb.v1", "html", "htm", "stm" }, { "text/xml", "XML", "xexml.v1", "xml" }, { "text/css", "XML", "xexml.v1", "xml" }, { "text/javascript", "JavaScript", "xejs.v1", "js" }, { "audio/basic", "basic audio", "xeaudio.v1", "au", "snd" }, { "audio/mid", "midi", "xeaudio.v1", "mid", "rmi" }, { "audio/mpeg", "MPEG audio", "xeaudio.v1", "mp3" }, { "audio/x-aiff", "AIFF audio", "xeaudio.v1", "aif", "aifc", "aiff" }, { "audio/x-mpegurl", "MPEG URL audio", "xeaudio.v1", "m3u" }, { "audio/x-pn-realaudio", "Real Audio", "xeaudio.v1", "ra", "ram" }, { "audio/x-wav", "Wave Audio", "xeaudio.v1", "wav" }, { "audio/x-ms-wma", "Microsoft Audio", "xeaudio.v1", "wma" }, { "audio/x-ms-wmv", "Microsoft Audio", "xeaudio.v1", "wmv" }, { "video/mpeg", "MPEG Video", "xevideo.v1", "mp2", "mpa", "mpe", "mpeg", "mpg", "mpv2" }, { "video/quicktime", "Quicktime Movie", "xevideo.v1", "mov", "qt" }, { "video/x-la-asf", "ASF Video", "xevideo.v1", "lsf", "lsx" }, { "video/x-ms-asf", "ASF Video", "xevideo.v1", "asf", "asr", "asx" }, { "video/x-msvideo", "AVI Video", "xevideo.v1", "avi" }, { "video/x-sgi-movie", "Movie", "xevideo.v1", "movie" } };
        for (int i = 0; i < n8.b.length; ++i) {
            final String[] array = n8.b[i];
            n8.a.put(array[0], array[2]);
            n8.a.put(String.valueOf(String.valueOf(array[0])).concat(String.valueOf(String.valueOf(array[3]))), array[2]);
            for (int j = 3; j < array.length; ++j) {
                n8.a.put(array[j], array[2]);
            }
        }
    }
}
