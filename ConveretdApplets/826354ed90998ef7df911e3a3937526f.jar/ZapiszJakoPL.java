import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import zjpl.Base64;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZapiszJakoPL extends JApplet
{
    private String getRedirUrl(final String s) {
        String s2 = "";
        try {
            final HttpURLConnection httpurlconnection = (HttpURLConnection)new URL(s).openConnection();
            httpurlconnection.setInstanceFollowRedirects(false);
            httpurlconnection.addRequestProperty("User-Agent", this.getParameter("ua"));
            s2 = httpurlconnection.getHeaderField("location");
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return s2;
    }
    
    private String inbtwn(final String s, final String s1, final String s2) {
        String s3 = null;
        try {
            final String[] as = s.split(s1);
            final String[] as2 = as[1].split(s2);
            s3 = as2[0];
        }
        catch (Exception exception) {
            return null;
        }
        return s3;
    }
    
    private String getUrlContent(final String s, final String s1) {
        try {
            final URL url = new URL(s);
            final HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setRequestMethod(s1);
            httpurlconnection.addRequestProperty("User-Agent", this.getParameter("ua"));
            final InputStream inputstream = httpurlconnection.getInputStream();
            final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            final byte[] abyte0 = new byte[1024];
            int i = 0;
            while ((i = inputstream.read(abyte0)) != -1) {
                bytearrayoutputstream.write(abyte0, 0, i);
            }
            return bytearrayoutputstream.toString("UTF-8");
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    private String setHTMLEntity(final String s) {
        String s2 = "";
        try {
            s2 = s.replace("&amp;", "_").toString();
            s2 = s.replace("&lt;", "_").toString();
            s2 = s.replace("&gt;", "_").toString();
            s2 = s.replace("&#39;", "_").toString();
            s2 = s.replace("&quot;", "_").toString();
            s2 = s.replace("&", "_").toString();
            s2 = s2.replace("\\\"", "_").toString();
            s2 = s2.replace("\\'", "_").toString();
            s2 = s2.replace("'", "_").toString();
            s2 = s2.replace("'", "_").toString();
            s2 = s2.replace("<", "_").toString();
            s2 = s2.replace(">", "_").toString();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return s;
        }
        return s2;
    }
    
    private String setHTMLEntity2(final String s) {
        String s2 = "";
        try {
            s2 = s.replace("&amp;", "").toString();
            s2 = s.replace("&lt;", "").toString();
            s2 = s.replace("&gt;", "").toString();
            s2 = s.replace("&#39;", "").toString();
            s2 = s.replace("&quot;", "").toString();
            s2 = s.replace("&", "").toString();
            s2 = s2.replace("\\\"", "").toString();
            s2 = s2.replace("\\'", "").toString();
            s2 = s2.replace("'", "").toString();
            s2 = s2.replace("'", "").toString();
            s2 = s2.replace("<", "").toString();
            s2 = s2.replace(">", "").toString();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return s;
        }
        return s2;
    }
    
    private String Zakoduj(String s, final String title) {
        String s2 = "";
        final String s3 = "";
        final String s4 = "";
        if (title.length() > 0) {
            final String sb = s = s + "&title=" + title + "[zapiszjako.pl]";
        }
        try {
            s2 = Base64.encode(s.getBytes());
        }
        catch (Exception exception) {
            return null;
        }
        return s2.toString();
    }
    
    @Override
    public void init() {
        final Object obj = null;
        try {
            final String ss1 = this.getParameter("adres");
            final String ss2 = this.getParameter("strona");
            final String ss3 = this.getParameter("id");
            if (ss2.contains("youtube.com")) {
                try {
                    String s3 = ss3;
                    final String s4 = ss1;
                    if (s3 == null) {
                        s3 = this.inbtwn(URLDecoder.decode(this.getRedirUrl(s4), "UTF-8"), "v=", "&");
                    }
                    final String s5 = this.getUrlContent("http://www.youtube.com/get_video_info?video_id=" + s3, "GET");
                    final String s6 = this.getUrlContent("http://www.youtube.com/watch?v=" + s3, "GET");
                    String s7 = this.inbtwn(s6, "'VIDEO_TITLE': '", "',");
                    if (s7 == null) {
                        s7 = this.inbtwn(s6, "name=\"title\" content=\"", "\"");
                    }
                    if (s7 == null) {
                        s7 = this.inbtwn(s6, "<h3>&ldquo;", "&rdquo;</h3>");
                    }
                    s7 = this.setHTMLEntity(s7);
                    String s8 = this.inbtwn(s6, "\"t\": \"", "\"");
                    if (s8 == null) {
                        s8 = this.inbtwn(s6, "&t=", "&");
                    }
                    if (s8 == null) {
                        s8 = this.inbtwn(s5, "&token=", "&");
                    }
                    String urlmap = this.inbtwn(s5, "fmt_url_map=", "&");
                    if (urlmap == null) {
                        urlmap = this.inbtwn(s6, "fmt_url_map=", "&");
                    }
                    String s9 = this.inbtwn(urlmap, "13%7C", "%2C");
                    if (s9 == null) {
                        s9 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=13&asv=2");
                    }
                    if (s9 != null) {
                        s9 = URLDecoder.decode(s9);
                        final URL url10 = new URL("javascript:zapiszjakopl_append('3GP', 'Low Quality - 176x144', '" + s9 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url10);
                    }
                    String s10 = this.inbtwn(urlmap, "17%7C", "%2C");
                    if (s10 == null) {
                        s10 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=17&asv=2");
                    }
                    if (s10 != null) {
                        s10 = URLDecoder.decode(s10);
                        final URL url11 = new URL("javascript:zapiszjakopl_append('3GP', 'Medium Quality - 176x144', '" + s10 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url11);
                    }
                    String s11 = this.inbtwn(urlmap, "36%7C", "%2C");
                    if (s11 == null) {
                        s11 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=36&asv=2");
                    }
                    if (s11 != null) {
                        s11 = URLDecoder.decode(s11);
                        final URL url12 = new URL("javascript:zapiszjakopl_append('3GP', 'High Quality - 320x240', '" + s11 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url12);
                    }
                    String s12 = this.inbtwn(urlmap, "%2C5%7C", "%2C");
                    if (s12 == null) {
                        s12 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=5&asv=2");
                    }
                    if (s12 != null) {
                        s12 = URLDecoder.decode(s12);
                        final URL url13 = new URL("javascript:zapiszjakopl_append('FLV', 'Low Quality - 320x240', '" + s12 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url13);
                    }
                    String s13 = this.inbtwn(urlmap, "34%7C", "%2C");
                    if (s13 == null) {
                        s13 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=34&asv=2");
                    }
                    if (s13 != null) {
                        s13 = URLDecoder.decode(s13);
                        final URL url14 = new URL("javascript:zapiszjakopl_append('FLV', 'Medium Quality - 400x226', '" + s13 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url14);
                    }
                    String s14 = this.inbtwn(urlmap, "6%7C", "%2C");
                    if (s14 == null) {
                        s14 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=6&asv=2");
                    }
                    if (s14 != null) {
                        s14 = URLDecoder.decode(s14);
                        final URL url15 = new URL("javascript:zapiszjakopl_append('FLV', 'Medium Quality - 480x360', '" + s14 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url15);
                    }
                    String s15 = this.inbtwn(urlmap, "35%7C", "%2C");
                    if (s15 == null) {
                        s15 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=35&asv=2");
                    }
                    if (s15 != null) {
                        s15 = URLDecoder.decode(s15);
                        final URL url16 = new URL("javascript:zapiszjakopl_append('FLV', 'High Quality - 640x380', '" + URLDecoder.decode(s15) + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url16);
                    }
                    String s16 = this.inbtwn(urlmap, "18%7C", "%2C");
                    if (s16 == null) {
                        s16 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=18&asv=2");
                    }
                    if (s16 != null) {
                        s16 = URLDecoder.decode(s16);
                        final URL url17 = new URL("javascript:zapiszjakopl_append('MP4', 'High Quality - 480x360', '" + s16 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url17);
                    }
                    String s17 = this.inbtwn(urlmap, "22%7C", "%2C");
                    if (s17 == null) {
                        s17 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=22&asv=2");
                    }
                    if (s17 != null) {
                        s17 = URLDecoder.decode(s17);
                        final URL url18 = new URL("javascript:zapiszjakopl_append('MP4', 'High Quality - 1280x720', '" + s17 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url18);
                    }
                    String s18 = this.inbtwn(urlmap, "37%7C", "%2C");
                    if (s18 == null) {
                        s18 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=37&asv=2");
                    }
                    if (s18 != null) {
                        s18 = URLDecoder.decode(s18);
                        final URL url19 = new URL("javascript:zapiszjakopl_append('MP4', 'High Quality - 1920x1280', '" + s18 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url19);
                    }
                    String s19 = this.inbtwn(urlmap, "38%7C", "%2C");
                    if (s19 == null) {
                        s19 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + s3 + "&t=" + s8 + "&fmt=37&asv=2");
                    }
                    if (s19 != null) {
                        s19 = URLDecoder.decode(s19);
                        final URL url20 = new URL("javascript:zapiszjakopl_append('MP4', 'Mega Quality - 3820x2160', '" + s19 + "', '" + s7 + "');");
                        this.getAppletContext().showDocument(url20);
                    }
                }
                catch (Exception exception1) {}
            }
            else if (ss2.contains("wrzuta.pl")) {
                try {
                    if (ss1.contains("/audio/")) {
                        final String s20 = ss1.replace("/audio/", "/xml/plik/");
                        final String s10a = s20 + "/wrzuta.pl/undefined/";
                        String s21 = this.getUrlContent(s10a, "GET");
                        s21 = s21.replace("<![CDATA[", "").toString();
                        s21 = s21.replace("]]>", "").toString();
                        s21 = s21.replace("]]>", "").toString();
                        final String s22 = this.inbtwn(s21, "<fileId>", "</fileId>");
                        final String s23 = this.inbtwn(s21, "<name>", "</name>");
                        final String s24 = this.inbtwn(s21, "<filePageUrl>", "</filePageUrl>");
                        final String s25 = this.inbtwn(s21, "<key>", "</key>");
                        final String s26 = this.inbtwn(s21, "<login>", "</login>");
                        final String s27 = this.inbtwn(s21, "<size>", "</size>");
                        final String s28 = this.inbtwn(s21, "<duration>", "</duration>");
                        final String s204a = this.inbtwn(s21, "<tags>", "</tags>");
                        final String s29 = s204a.replaceAll("\\<.*?\\>", "");
                        try {
                            final URL url21 = new URL("javascript:zapiszjakopl_append('MP3', '', '" + s22 + "', '" + this.setHTMLEntity2(s23) + "');");
                            this.getAppletContext().showDocument(url21);
                        }
                        catch (MalformedURLException ex) {}
                        final String s30 = this.getUrlContent("http://www.zapiszjako.pl/baza/addwrzuta.phtml?typ=1&tytul=" + URLEncoder.encode(s23, "UTF-8") + "&id_org=" + s25 + "&login=" + s26 + "&wielkosc=" + s27 + "&czas=" + s28 + "&adres=" + URLEncoder.encode(s24, "UTF-8") + "&tagi=" + URLEncoder.encode(s29, "UTF-8"), "POST");
                    }
                    else if (ss1.contains("/film/")) {
                        final String s20 = ss1.replace("/film/", "/xml/plik/");
                        String s31 = this.getUrlContent(s20, "GET");
                        s31 = s31.replace("<![CDATA[", "").toString();
                        s31 = s31.replace("]]>", "").toString();
                        final String s32 = this.inbtwn(s31, "<fileId>", "</fileId>");
                        final String s33 = this.inbtwn(s31, "<name>", "</name>");
                        final String s34 = this.inbtwn(s31, "<filePageUrl>", "</filePageUrl>");
                        final String s35 = this.inbtwn(s31, "<key>", "</key>");
                        try {
                            final URL url22 = new URL("javascript:zapiszjakopl_append('FLV', '', '" + s32 + "', '" + this.setHTMLEntity2(s33) + "');");
                            this.getAppletContext().showDocument(url22);
                        }
                        catch (MalformedURLException ex2) {}
                        final String s36 = this.getUrlContent("http://www.zapiszjako.pl/baza/addwrzuta.phtml?typ=2&tytul=" + URLEncoder.encode(s33, "UTF-8") + "&id_org=" + s35 + "&adres=" + URLEncoder.encode(s34, "UTF-8"), "POST");
                    }
                }
                catch (Exception ex3) {}
            }
            try {
                final URL url23 = new URL(new StringBuilder().append("javascript:zapiszjakopl_done();").toString());
                this.getAppletContext().showDocument(url23);
            }
            catch (MalformedURLException ex4) {}
        }
        catch (Exception ex5) {}
    }
}
