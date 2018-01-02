import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.regex.Pattern;
import java.util.LinkedList;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FindtoyouVidDownloader extends JApplet
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
        try {
            final String[] split = s.split(s2);
            try {
                final String[] split2 = split[1].split(s3);
                if (split2[0] != null || split2[0] != "") {
                    return split2[0];
                }
            }
            catch (Exception ex) {
                return split[1];
            }
            return split[1];
        }
        catch (Exception ex2) {
            return null;
        }
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
    
    private String pregMatch(final String s, final String s2, final int n) {
        try {
            return Pattern.compile(s).matcher(s2).group(n);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @Override
    public void init() {
        final JSObject window = JSObject.getWindow((Applet)this);
        window.eval("jloaded();");
        final String s = null;
        try {
            super.init();
            String s2 = this.getParameter("u");
            final String parameter = this.getParameter("ua");
            System.out.println("param u: " + s2 + "\r\n");
            System.out.println("param ua: " + parameter + "\r\n");
            if (!s2.contains("youtube.com")) {
                if (!s2.contains("youtu.be")) {
                    window.eval("kv_error('Sorry, could not fetch download links on this page (site not supported).')");
                    return;
                }
            }
            try {
                if (s2.contains("youtu.be/")) {
                    s2 = s2.replace("youtu.be/", "www.youtube.com/watch?v=").toString();
                }
                String s3 = this.getUrlContent(s2 + "&fmt=18", "GET", null, null);
                String s4 = this.inbtwn(s3, "shortlink\" href=\"http://youtu.be/", "\"");
                if (s4 == null) {
                    s4 = this.inbtwn(s3, "'VIDEO_ID': \"", "\"");
                }
                if (s4 == null) {
                    System.out.println("Trying Alternate Method...");
                    s4 = this.inbtwn(s2, "v=", "&");
                    s3 = this.getUrlContent("http://www.youtube.com/get_video_info?video_id=" + s4 + "&asv=3&el=detailpage&hl=en_US", "GET", null, null);
                }
                System.out.println("Getting Title...");
                String htmlEntity = this.inbtwn(s3, "'VIDEO_TITLE': '", "',");
                if (htmlEntity == null) {
                    htmlEntity = this.inbtwn(s3, "name=\"title\" content=\"", "\"");
                }
                if (htmlEntity == null) {
                    htmlEntity = URLDecoder.decode(this.inbtwn(s3, "&title=", "&"), "UTF-8").replace("+", " ");
                }
                try {
                    htmlEntity = this.setHTMLEntity(htmlEntity);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Title: " + htmlEntity + "\r\n");
                window.eval("kv_info('" + htmlEntity + "', 'youtube.com', 'http://www.youtube.com/watch?v=" + s4 + "', 'http://i.ytimg.com/vi/" + s4 + "/default.jpg');");
                String s5 = URLDecoder.decode(this.inbtwn(s3, "fmt_stream_map=", "&"), "UTF-8");
                if (s5 == null) {
                    s5 = this.inbtwn(s3, "fmt_stream_map\": \"", "\"").replace("\\/", "/");
                }
                System.out.println("fmt_url_map: " + s5 + "\r\n");
                String s6 = null;
                String s7 = null;
                String replace = null;
                String s8 = null;
                String s9 = null;
                String s10 = null;
                String s11 = null;
                String s12 = null;
                String s13 = null;
                String s14 = null;
                String s15 = null;
                String s16 = null;
                String s17 = null;
                String s18 = null;
                final String[] split = s5.split("url=");
                for (int length = split.length, i = 0; i < length; ++i) {
                    final String decode = URLDecoder.decode(split[i].split(Pattern.quote("&qual"))[0], "UTF-8");
                    if (decode.contains("itag=13")) {
                        s6 = decode;
                    }
                    if (decode.contains("itag=17")) {
                        s7 = decode;
                    }
                    if (decode.contains("itag=36")) {
                        replace = decode;
                    }
                    if (decode.contains("itag=5")) {
                        s8 = decode;
                    }
                    if (decode.contains("itag=34")) {
                        s9 = decode;
                    }
                    if (decode.contains("itag=6")) {
                        s10 = decode;
                    }
                    if (decode.contains("itag=35")) {
                        s11 = decode;
                    }
                    if (decode.contains("itag=18")) {
                        s12 = decode;
                    }
                    if (decode.contains("itag=22")) {
                        s13 = decode;
                    }
                    if (decode.contains("itag=37")) {
                        s14 = decode;
                    }
                    if (decode.contains("itag=38")) {
                        s15 = decode;
                    }
                    if (decode.contains("itag=43")) {
                        s16 = decode;
                    }
                    if (decode.contains("itag=44")) {
                        s17 = decode;
                    }
                    if (decode.contains("itag=45")) {
                        s18 = decode;
                    }
                    System.out.println("URL: " + decode);
                }
                if (s == null) {
                    if (s8 != null) {
                        window.eval("kv_ds('dl_flvlow', 'FLV', '240p', '" + s8 + "', '" + htmlEntity + "');");
                    }
                    if (s10 != null) {
                        window.eval("kv_ds('dl_flvmed2', 'FLV', '360p', '" + s10 + "', '" + htmlEntity + "');");
                    }
                    if (s9 != null) {
                        window.eval("kv_ds('dl_flvmed', 'FLV', '360p', '" + s9 + "', '" + htmlEntity + "');");
                    }
                    if (s11 != null) {
                        window.eval("kv_ds('dl_flvhigh', 'FLV', '480p', '" + s11 + "', '" + htmlEntity + "');");
                    }
                    if (s12 != null) {
                        window.eval("kv_ds('dl_mp4high', 'MP4', '(Max 480p)', '" + s12 + "', '" + htmlEntity + "');");
                    }
                    if (s13 != null) {
                        window.eval("kv_ds('dl_mp4hd', 'MP4', '720p', '" + s13 + "', '" + htmlEntity + "');");
                    }
                    if (s14 != null) {
                        window.eval("kv_ds('dl_mp4hd2', 'MP4', '1080p', '" + s14 + "', '" + htmlEntity + "');");
                    }
                    if (s15 != null) {
                        window.eval("kv_ds('dl_mp4hd3', 'MP4', '(Original)', '" + s15 + "', '" + htmlEntity + "');");
                    }
                    if (s16 != null) {
                        window.eval("kv_ds('dl_webm', 'WebM', '360p', '" + s16 + "', '" + htmlEntity + "');");
                    }
                    if (s17 != null) {
                        window.eval("kv_ds('dl_webmhd', 'WebM', '480p', '" + s17 + "', '" + htmlEntity + "');");
                    }
                    if (s18 != null) {
                        window.eval("kv_ds('dl_webmhd2', 'WebM', '720p', '" + s18 + "', '" + htmlEntity + "');");
                    }
                    try {
                        replace = this.inbtwn(this.getUrlContent("http://m.youtube.com/watch?ajax=1&layout=mobile&tsp=1&v=" + s4, "GET", null, null).split("\"related_videos\":")[0], "\"stream_url\": \"", "\"").replace("\\/", "/");
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    if (s6 != null) {
                        window.eval("kv_ds('dl_3gplow', '3GP', '144p', '" + s6 + "', '" + htmlEntity + "');");
                    }
                    if (s7 != null) {
                        window.eval("kv_ds('dl_3gpmed', '3GP', '144p', '" + s7 + "', '" + htmlEntity + "');");
                    }
                    if (replace != null) {
                        window.eval("kv_ds('dl_3gphigh', '3GP', '240p', '" + replace + "', '" + htmlEntity + "');");
                    }
                }
                else {
                    window.eval("kv_error('" + s + "');");
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
    }
    
    private String getUrlContent(final String s, final String requestMethod, final String s2, final String s3) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            if (s3 != null) {
                httpURLConnection.setRequestProperty("Cookie", s3);
            }
            if (s.contains("layout=mobile")) {
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
            }
            else {
                httpURLConnection.setRequestProperty("User-Agent", this.getParameter("ua"));
            }
            if (s.contains("dailymotion.com")) {
                httpURLConnection.setRequestProperty("Cookie", "family_filter=off;");
            }
            InputStream inputStream;
            if (s.contains("www.tudou.com")) {
                inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
            }
            else {
                inputStream = httpURLConnection.getInputStream();
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            httpURLConnection.disconnect();
            return byteArrayOutputStream.toString("UTF8");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                this.getAppletContext().showDocument(new URL("javascript:jerror();"));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return null;
        }
    }
    
    private String postUrlContent(final String s, final String s2, final String s3) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            if (s3 != null) {
                httpURLConnection.setRequestProperty("Cookie", s3);
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", "" + Integer.toString(s2.getBytes().length));
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(s2);
            dataOutputStream.flush();
            dataOutputStream.close();
            final InputStream inputStream = httpURLConnection.getInputStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            httpURLConnection.disconnect();
            return byteArrayOutputStream.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                this.getAppletContext().showDocument(new URL("javascript:jerror();"));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
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
            try {
                this.getAppletContext().showDocument(new URL("javascript:jerror();"));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return null;
        }
        return headerField;
    }
    
    private String setHTMLEntity(final String s) {
        String string;
        try {
            string = s.replace("&amp;", "").toString().replace("&lt;", "").toString().replace("&gt;", "").toString().replace("&#39;", "").toString().replace("&quot;", "").toString().replace("&", "").toString().replace("amp;", "").toString().replace("\\\"", "").toString().replace("\\'", "").toString().replace("'", "").toString().replace("'", "").toString().replace("<", "").toString().replace(">", "").toString().replace("?", "").toString().replace("/", "").toString().replace(":", "").toString().replace(";", "").toString().replace("#", "").toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
        return string;
    }
}
