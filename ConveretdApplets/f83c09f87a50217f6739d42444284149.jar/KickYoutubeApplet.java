import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KickYoutubeApplet extends JApplet
{
    private String mv_decrypt(final String s, int n, int n2) {
        final LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '0': {
                    list.add(0);
                    list.add(0);
                    list.add(0);
                    list.add(0);
                    break;
                }
                case '1': {
                    list.add(0);
                    list.add(0);
                    list.add(0);
                    list.add(1);
                    break;
                }
                case '2': {
                    list.add(0);
                    list.add(0);
                    list.add(1);
                    list.add(0);
                    break;
                }
                case '3': {
                    list.add(0);
                    list.add(0);
                    list.add(1);
                    list.add(1);
                    break;
                }
                case '4': {
                    list.add(0);
                    list.add(1);
                    list.add(0);
                    list.add(0);
                    break;
                }
                case '5': {
                    list.add(0);
                    list.add(1);
                    list.add(0);
                    list.add(1);
                    break;
                }
                case '6': {
                    list.add(0);
                    list.add(1);
                    list.add(1);
                    list.add(0);
                    break;
                }
                case '7': {
                    list.add(0);
                    list.add(1);
                    list.add(1);
                    list.add(1);
                    break;
                }
                case '8': {
                    list.add(1);
                    list.add(0);
                    list.add(0);
                    list.add(0);
                    break;
                }
                case '9': {
                    list.add(1);
                    list.add(0);
                    list.add(0);
                    list.add(1);
                    break;
                }
                case 'a': {
                    list.add(1);
                    list.add(0);
                    list.add(1);
                    list.add(0);
                    break;
                }
                case 'b': {
                    list.add(1);
                    list.add(0);
                    list.add(1);
                    list.add(1);
                    break;
                }
                case 'c': {
                    list.add(1);
                    list.add(1);
                    list.add(0);
                    list.add(0);
                    break;
                }
                case 'd': {
                    list.add(1);
                    list.add(1);
                    list.add(0);
                    list.add(1);
                    break;
                }
                case 'e': {
                    list.add(1);
                    list.add(1);
                    list.add(1);
                    list.add(0);
                    break;
                }
                case 'f': {
                    list.add(1);
                    list.add(1);
                    list.add(1);
                    list.add(1);
                    break;
                }
            }
        }
        final LinkedList<Integer> list2 = new LinkedList<Integer>();
        for (int j = 0; j < 384; ++j) {
            n = (n * 11 + 77213) % 81371;
            n2 = (n2 * 17 + 92717) % 192811;
            list2.add((n + n2) % 128);
        }
        for (int k = 256; k >= 0; --k) {
            final int intValue = list2.get(k);
            final int n3 = k % 128;
            final int intValue2 = list.get(intValue);
            list.set(intValue, list.get(n3));
            list.set(n3, intValue2);
        }
        for (int l = 0; l < 128; ++l) {
            list.set(l, list.get(l) ^ (list2.get(l + 256) & 0x1));
        }
        String s2 = "";
        for (int n4 = 0; n4 < list.size(); n4 += 4) {
            switch (list.get(n4) * 8 + list.get(n4 + 1) * 4 + list.get(n4 + 2) * 2 + list.get(n4 + 3)) {
                case 0: {
                    s2 += "0";
                    break;
                }
                case 1: {
                    s2 += "1";
                    break;
                }
                case 2: {
                    s2 += "2";
                    break;
                }
                case 3: {
                    s2 += "3";
                    break;
                }
                case 4: {
                    s2 += "4";
                    break;
                }
                case 5: {
                    s2 += "5";
                    break;
                }
                case 6: {
                    s2 += "6";
                    break;
                }
                case 7: {
                    s2 += "7";
                    break;
                }
                case 8: {
                    s2 += "8";
                    break;
                }
                case 9: {
                    s2 += "9";
                    break;
                }
                case 10: {
                    s2 += "a";
                    break;
                }
                case 11: {
                    s2 += "b";
                    break;
                }
                case 12: {
                    s2 += "c";
                    break;
                }
                case 13: {
                    s2 += "d";
                    break;
                }
                case 14: {
                    s2 += "e";
                    break;
                }
                case 15: {
                    s2 += "f";
                    break;
                }
            }
        }
        return s2;
    }
    
    private String inbtwn(final String s, final String s2, final String s3) {
        String s4;
        try {
            s4 = s.split(s2)[1].split(s3)[0];
        }
        catch (Exception ex) {
            return null;
        }
        return s4;
    }
    
    private String inbtwnmore(final String s, final String s2, final String s3, final int n) {
        String s4;
        try {
            s4 = s.split(s2)[n].split(s3)[0];
        }
        catch (Exception ex) {
            return null;
        }
        return s4;
    }
    
    public void init() {
        try {
            final String parameter = this.getParameter("v");
            final String parameter2 = this.getParameter("u");
            final String parameter3 = this.getParameter("site");
            if (parameter3.contains("youtube.com")) {
                try {
                    super.init();
                    String inbtwn = parameter;
                    final String parameter4 = this.getParameter("u");
                    if (inbtwn == null) {
                        inbtwn = this.inbtwn(URLDecoder.decode(this.getRedirUrl(parameter4), "UTF-8"), "v=", "&");
                    }
                    final String urlContent = this.getUrlContent("http://www.youtube.com/get_video_info?video_id=" + inbtwn, "GET");
                    final String urlContent2 = this.getUrlContent("http://www.youtube.com/watch?v=" + inbtwn, "GET");
                    String htmlEntity = this.inbtwn(urlContent2, "'VIDEO_TITLE': '", "',");
                    if (htmlEntity == null) {
                        htmlEntity = this.inbtwn(urlContent2, "name=\"title\" content=\"", "\"");
                    }
                    if (htmlEntity == null) {
                        htmlEntity = this.inbtwn(urlContent2, "<h3>&ldquo;", "&rdquo;</h3>");
                    }
                    final String setHTMLEntity = this.setHTMLEntity(htmlEntity);
                    String s = this.inbtwn(urlContent2, "\"t\": \"", "\"");
                    if (s == null) {
                        s = this.inbtwn(urlContent2, "&t=", "&");
                    }
                    if (s == null) {
                        s = this.inbtwn(urlContent, "&token=", "&");
                    }
                    final String redirUrl = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=13");
                    if (redirUrl != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('3GP', 'Low Quality - 176x144', '" + redirUrl + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl2 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=17");
                    if (redirUrl2 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('3GP', 'Medium Quality - 176x144', '" + redirUrl2 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl3 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=36");
                    if (redirUrl3 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('3GP', 'High Quality - 320x240', '" + redirUrl3 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl4 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=5");
                    if (redirUrl4 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Low Quality - 320x240', '" + redirUrl4 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl5 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=34");
                    if (redirUrl5 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Medium Quality - 400x226', '" + redirUrl5 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl6 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=6");
                    if (redirUrl6 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Medium Quality - 480x360', '" + redirUrl6 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl7 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=35");
                    if (redirUrl7 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'High Quality - 640x380', '" + redirUrl7 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl8 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=18");
                    if (redirUrl8 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'High Quality - 480x360', '" + redirUrl8 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl9 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=22");
                    if (redirUrl9 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'High Quality - 1280x720', '" + redirUrl9 + "', '" + setHTMLEntity + "');"));
                    }
                    final String redirUrl10 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + inbtwn + "&t=" + s + "&fmt=37");
                    if (redirUrl10 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'High Quality - 1920x1280', '" + redirUrl10 + "', '" + setHTMLEntity + "');"));
                    }
                }
                catch (Exception ex) {}
            }
            else if (parameter3.contains("megavideo.com")) {
                try {
                    super.init();
                    String inbtwn2 = parameter;
                    final String parameter5 = this.getParameter("u");
                    if (inbtwn2 == null) {
                        inbtwn2 = this.inbtwn(URLDecoder.decode(this.getRedirUrl(parameter5), "UTF-8"), "v=", "&");
                    }
                    final String urlContent3 = this.getUrlContent("http://www.megavideo.com/xml/videolink.php?v=" + inbtwn2, "GET");
                    final String inbtwn3 = this.inbtwn(urlContent3, " un=\"", "\"");
                    final int intValue = Integer.valueOf(this.inbtwn(urlContent3, " k1=\"", "\""));
                    final int intValue2 = Integer.valueOf(this.inbtwn(urlContent3, " k2=\"", "\""));
                    final String inbtwn4 = this.inbtwn(urlContent3, " s=\"", "\"");
                    final String setHTMLEntity2 = this.setHTMLEntity(this.inbtwn(urlContent3, " title=\"", "\""));
                    final String string = "http://www" + inbtwn4 + ".megavideo.com/files/" + this.mv_decrypt(inbtwn3, intValue, intValue2) + "/" + setHTMLEntity2 + ".flv";
                    if (string != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'High Quality', '" + string + "', '" + setHTMLEntity2 + "');"));
                    }
                }
                catch (Exception ex2) {}
            }
            else if (parameter3.contains("dailymotion.com")) {
                try {
                    super.init();
                    final String decode = URLDecoder.decode(this.inbtwn(this.getUrlContent(parameter2, "GET"), "\"sequence\",  \"", "\""), "UTF-8");
                    final String string2 = this.inbtwn(decode, "\"sdURL\":\"", "\"").replace("\\/", "/").toString();
                    final String string3 = this.inbtwn(decode, "\"hqURL\":\"", "\"").replace("\\/", "/").toString();
                    if (string2 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Standard Quality', '" + string2 + "', '');"));
                    }
                    if (string3 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'High Quality', '" + string3 + "', '');"));
                    }
                }
                catch (Exception ex3) {}
            }
            else if (parameter3.contains("vimeo.com")) {
                try {
                    super.init();
                    final String s2 = parameter2.split("com/")[1];
                    final String urlContent4 = this.getUrlContent("http://vimeo.com/moogaloop/load/clip:" + s2, "GET");
                    final String decode2 = URLDecoder.decode(this.inbtwn(urlContent4, "<request_signature>", "</request_signature>"), "UTF-8");
                    final String decode3 = URLDecoder.decode(this.inbtwn(urlContent4, "<request_signature_expires>", "</request_signature_expires>"), "UTF-8");
                    final String redirUrl11 = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + s2 + "/" + decode2 + "/" + decode3 + "/?q=sd");
                    final String redirUrl12 = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + s2 + "/" + decode2 + "/" + decode3 + "/?q=hd");
                    if (redirUrl11 != null) {
                        String s3 = "FLV";
                        if (redirUrl11.contains(".avi?")) {
                            s3 = "AVI";
                        }
                        if (redirUrl11.contains(".mp4?")) {
                            s3 = "MP4";
                        }
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('" + s3 + "', 'Standard Quality', '" + redirUrl11 + "', '');"));
                    }
                    if (redirUrl12 != null) {
                        String s4 = "FLV";
                        if (redirUrl12.contains(".avi?")) {
                            s4 = "AVI";
                        }
                        if (redirUrl12.contains(".mp4?")) {
                            s4 = "MP4";
                        }
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('" + s4 + "', 'High Quality', '" + redirUrl12 + "', '');"));
                    }
                }
                catch (Exception ex4) {}
            }
            else if (parameter3.contains("metacafe.com")) {
                try {
                    super.init();
                    final String decode4 = URLDecoder.decode(this.inbtwn(this.getUrlContent(parameter2, "GET"), "name=\"flashvars\" value=\"", "\""), "UTF-8");
                    String s5 = this.inbtwn(decode4, "\"mediaURL\":\"", "\"").replace("\\/", "/").toString();
                    if (s5 == null) {
                        s5 = this.inbtwn(decode4, "mediaURL=", "&").replace("\\/", "/").toString();
                    }
                    if (s5 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Standard Quality', '" + s5 + "', '');"));
                    }
                }
                catch (Exception ex5) {}
            }
            else if (parameter3.contains("ehow.com")) {
                try {
                    super.init();
                    final String string4 = "http" + this.inbtwn(this.getUrlContent(parameter2, "GET"), "id: 'http", "'");
                    if (string4 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'High Quality', '" + string4 + "', '');"));
                    }
                }
                catch (Exception ex6) {}
            }
            else if (parameter3.contains("ted.com")) {
                try {
                    super.init();
                    final String urlContent5 = this.getUrlContent(parameter2, "GET");
                    final String inbtwn5 = this.inbtwn(urlContent5, "vu=", "&");
                    final String string5 = "http://www.ted.com/" + this.inbtwn(urlContent5, "<dt><a href=\"/", "\"");
                    final String string6 = "http://www.ted.com/" + this.inbtwnmore(urlContent5, "<dt><a href=\"/", "\"", 2);
                    if (inbtwn5 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Standard Quality', '" + inbtwn5 + "', '');"));
                    }
                    if (string5 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'Standard Quality', '" + string5 + "', '');"));
                    }
                    if (string6 != null) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'High Quality', '" + string6 + "', '');"));
                    }
                }
                catch (Exception ex7) {}
            }
            else if (parameter3.contains("tudou.com")) {
                try {
                    super.init();
                    final String s6 = parameter2;
                    final String urlContent6 = this.getUrlContent(s6, "GET");
                    String s7 = this.inbtwn(urlContent6, "defaultIid = ", "\n");
                    if (s7 == null) {
                        s7 = this.inbtwn(urlContent6, "iid: \"", "\"");
                    }
                    String s8;
                    if (s6.contains("hd.")) {
                        s8 = "http://v2.tudou.com/v2/kili?id=";
                    }
                    else {
                        s8 = "http://v2.tudou.com/v?it=";
                    }
                    for (final String s9 : this.getUrlContent(s8 + s7, "GET").split("<f ")) {
                        if (s9.contains("size=\"")) {
                            final String s10 = s9.split(">")[1].split("</")[0];
                            if (s10.contains(".f4v")) {
                                this.getAppletContext().showDocument(new URL("javascript:freedown_append('F4V', 'Standard Quality', '" + s10 + "', '');"));
                            }
                            if (s10.contains(".flv")) {
                                this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Standard Quality', '" + s10 + "', '');"));
                            }
                            if (s10.contains(".m4v")) {
                                this.getAppletContext().showDocument(new URL("javascript:freedown_append('M4V', 'Standard Quality', '" + s10 + "', '');"));
                            }
                            if (s10.contains(".mp4")) {
                                this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'Standard Quality', '" + s10 + "', '');"));
                            }
                        }
                    }
                }
                catch (Exception ex8) {}
            }
            else if (parameter3.contains("facebook.com")) {
                try {
                    super.init();
                    final String urlContent7 = this.getUrlContent(parameter2, "GET");
                    System.out.println("pageSource: " + urlContent7 + "\r\n");
                    final String inbtwn6 = this.inbtwn(urlContent7, "video_src\", \"", "\"");
                    System.out.println("file: " + inbtwn6 + "\r\n");
                    if (inbtwn6.contains(".mp4")) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('MP4', 'Standard Quality', '" + inbtwn6 + "', '');"));
                    }
                    if (inbtwn6.contains(".flv")) {
                        this.getAppletContext().showDocument(new URL("javascript:freedown_append('FLV', 'Standard Quality', '" + inbtwn6 + "', '');"));
                    }
                }
                catch (Exception ex9) {}
            }
            this.getAppletContext().showDocument(new URL(new StringBuilder().append("javascript:freedown_done();").toString()));
        }
        catch (Exception ex10) {}
    }
    
    private String getUrlContent(final String s, final String requestMethod) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("User-Agent", this.getParameter("ua"));
            final InputStream inputStream = httpURLConnection.getInputStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            return byteArrayOutputStream.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String getRedirUrl(final String s) {
        String headerField;
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.addRequestProperty("User-Agent", this.getParameter("ua"));
            headerField = httpURLConnection.getHeaderField("location");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return headerField;
    }
    
    private String setHTMLEntity(final String s) {
        String string;
        try {
            s.replace("&amp;", "_").toString();
            s.replace("&lt;", "_").toString();
            s.replace("&gt;", "_").toString();
            s.replace("&#39;", "_").toString();
            s.replace("&quot;", "_").toString();
            string = s.replace("&", "_").toString().replace("\\\"", "_").toString().replace("\\'", "_").toString().replace("'", "_").toString().replace("'", "_").toString().replace("<", "_").toString().replace(">", "_").toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
        return string;
    }
}
