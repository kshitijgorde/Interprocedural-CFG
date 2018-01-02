import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.applet.Applet;
import netscape.javascript.JSObject;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class YouTubeSnips extends JApplet
{
    private String inbtwn(final String input, final String startcut, final String finishcut) {
        String output = null;
        try {
            final String[] arr1 = input.split(startcut);
            final String[] arr2 = arr1[1].split(finishcut);
            output = arr2[0];
        }
        catch (Exception ex) {
            return null;
        }
        return output;
    }
    
    private String inbtwnmore(final String input, final String startcut, final String finishcut, final int times) {
        String output = null;
        try {
            final String[] arr1 = input.split(startcut);
            final String[] arr2 = arr1[times].split(finishcut);
            output = arr2[0];
        }
        catch (Exception ex) {
            return null;
        }
        return output;
    }
    
    public void init() {
        final JSObject mainWindow = JSObject.getWindow((Applet)this);
        mainWindow.eval("jloaded();");
        String error = null;
        String alternate = "";
        try {
            super.init();
            final String vParam = this.getParameter("v");
            final String uParam = this.getParameter("u");
            final String siteParam = this.getParameter("site");
            final String uaParam = this.getParameter("ua");
            System.out.println("param v: " + vParam + "\r\n");
            System.out.println("param u: " + uParam + "\r\n");
            System.out.println("param site: " + siteParam + "\r\n");
            System.out.println("param ua: " + uaParam + "\r\n");
            if (siteParam.contains("youtube.com")) {
                try {
                    String video_id = vParam;
                    if (video_id == null) {
                        video_id = this.inbtwn(URLDecoder.decode(this.getRedirUrl(uParam), "UTF-8"), "v=", "&");
                    }
                    String pageSource = this.getUrlContent("http://www.youtube.com/watch?v=" + video_id, "GET");
                    final String pageSource2 = this.getUrlContent("http://www.youtube.com/watch?v=" + video_id + "&fmt=18", "GET");
                    System.out.println("Getting Title...");
                    String title = this.inbtwn(pageSource, "'VIDEO_TITLE': '", "',");
                    if (title == null) {
                        title = this.inbtwn(pageSource, "name=\"title\" content=\"", "\"");
                    }
                    if (title == null) {
                        title = this.inbtwn(pageSource, "&title=", "&");
                    }
                    try {
                        title = this.setHTMLEntity(title);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Title: " + title + "\r\n");
                    String token = null;
                    try {
                        token = URLEncoder.encode(this.inbtwn(pageSource, "\"t\": \"", "\""), "UTF-8");
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    if (token == null) {
                        token = this.inbtwn(pageSource, "&t=", "&");
                    }
                    try {
                        if (!token.endsWith("%3D")) {
                            token = this.inbtwnmore(pageSource, "&t=", "&", 2);
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    String temp_fmt_url_map = "";
                    if (token == null) {
                        final String pageSourceU = this.getUrlContent("http://www.youtube.com/get_video_info?video_id=" + video_id, "GET");
                        pageSource = URLDecoder.decode(pageSourceU, "UTF-8");
                        if (pageSource.contains("status=fail")) {
                            error = this.inbtwn(pageSource, "&reason=", "&");
                        }
                        else {
                            token = this.inbtwn(pageSource, "&token=", "&");
                            temp_fmt_url_map = this.inbtwn(pageSourceU, "&fmt_url_map=", "&");
                            alternate = "yes";
                        }
                    }
                    System.out.println("token: " + token + "\r\n");
                    System.out.println("fmt_url_map 1: " + this.inbtwn(pageSource, "&amp;fmt_url_map=", "&") + "\r\n");
                    System.out.println("fmt_url_map 2: " + this.inbtwn(pageSource2, "&amp;fmt_url_map=", "&") + "\r\n");
                    String fmt_url_map = "";
                    fmt_url_map = this.inbtwn(pageSource, "&amp;fmt_url_map=", "&") + "%2C" + this.inbtwn(pageSource2, "&amp;fmt_url_map=", "&");
                    System.out.println("fmt_url_map: " + fmt_url_map + "\r\n");
                    if (alternate.equals("yes") && !temp_fmt_url_map.equals("") && this.inbtwn(pageSource, "&amp;fmt_url_map=", "&") == null && this.inbtwn(pageSource2, "&amp;fmt_url_map=", "&") == null) {
                        fmt_url_map = temp_fmt_url_map;
                        System.out.println("fmt_url_map: " + fmt_url_map + "\r\n");
                    }
                    String dl_3gplow = null;
                    String dl_3gpmed = null;
                    String dl_3gphigh = null;
                    String dl_flvlow = null;
                    String dl_flvmed = null;
                    String dl_flvmed2 = null;
                    String dl_flvhigh = null;
                    String dl_mp4high = null;
                    String dl_mp4hd = null;
                    String dl_mp4hd2 = null;
                    String dl_mp4hd3 = null;
                    final String[] arr$;
                    final String[] fmt_arr = arr$ = fmt_url_map.split("%2C");
                    for (final String fmt : arr$) {
                        final String[] parts = fmt.split("%7C");
                        final String m_qual = URLDecoder.decode(parts[0], "UTF-8");
                        final String m_url = URLDecoder.decode(parts[1], "UTF-8");
                        if (m_qual.equals("13")) {
                            dl_3gplow = m_url;
                        }
                        if (m_qual.equals("17")) {
                            dl_3gpmed = m_url;
                        }
                        if (m_qual.equals("36")) {
                            dl_3gphigh = m_url;
                        }
                        if (m_qual.equals("5")) {
                            dl_flvlow = m_url;
                        }
                        if (m_qual.equals("34")) {
                            dl_flvmed = m_url;
                        }
                        if (m_qual.equals("6")) {
                            dl_flvmed2 = m_url;
                        }
                        if (m_qual.equals("35")) {
                            dl_flvhigh = m_url;
                        }
                        if (m_qual.equals("18")) {
                            dl_mp4high = m_url;
                        }
                        if (m_qual.equals("22")) {
                            dl_mp4hd = m_url;
                        }
                        if (m_qual.equals("37")) {
                            dl_mp4hd2 = m_url;
                        }
                        if (m_qual.equals("38")) {
                            dl_mp4hd3 = m_url;
                        }
                        System.out.println("URL: " + m_url);
                    }
                    if (error == null) {
                        if (dl_3gplow == null) {
                            dl_3gplow = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=13&asv=2");
                        }
                        if (dl_3gplow != null) {
                            mainWindow.eval("kv_ds('dl_3gplow', '3GP', 'Low Quality (176x144)', '" + dl_3gplow + "', '" + title + "');");
                        }
                        if (dl_3gpmed == null) {
                            dl_3gpmed = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=17&asv=2");
                        }
                        if (dl_3gpmed != null) {
                            mainWindow.eval("kv_ds('dl_3gpmed', '3GP', 'Medium Quality (176x144)', '" + dl_3gpmed + "', '" + title + "');");
                        }
                        if (dl_3gphigh == null) {
                            dl_3gphigh = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=36&asv=2");
                        }
                        if (dl_3gphigh != null) {
                            mainWindow.eval("kv_ds('dl_3gphigh', '3GP', 'High Quality (320x240)', '" + dl_3gphigh + "', '" + title + "');");
                        }
                        if (dl_flvlow == null) {
                            dl_flvlow = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=5&asv=2");
                        }
                        if (dl_flvlow != null) {
                            mainWindow.eval("kv_ds('dl_flvlow', 'FLV', 'Low Quality (400x226)', '" + dl_flvlow + "', '" + title + "');");
                        }
                        if (dl_flvmed2 == null) {
                            dl_flvmed2 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=6&asv=2");
                        }
                        if (dl_flvmed2 != null) {
                            mainWindow.eval("kv_ds('dl_flvmed2', 'FLV', 'Medium Quality (480x360)', '" + dl_flvmed2 + "', '" + title + "');");
                        }
                        if (dl_flvmed == null) {
                            dl_flvmed = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=34&asv=2");
                        }
                        if (dl_flvmed != null) {
                            mainWindow.eval("kv_ds('dl_flvmed', 'FLV', 'Medium Quality (640x360)', '" + dl_flvmed + "', '" + title + "');");
                        }
                        if (dl_flvhigh == null) {
                            dl_flvhigh = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=35&asv=2");
                        }
                        if (dl_flvhigh != null) {
                            mainWindow.eval("kv_ds('dl_flvhigh', 'FLV', 'High Quality (854x480)', '" + dl_flvhigh + "', '" + title + "');");
                        }
                        if (dl_mp4high == null) {
                            dl_mp4high = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=18&asv=2");
                        }
                        if (dl_mp4high != null) {
                            mainWindow.eval("kv_ds('dl_mp4high', 'MP4', 'High Quality (480x360)', '" + dl_mp4high + "', '" + title + "');");
                        }
                        if (dl_mp4hd == null) {
                            dl_mp4hd = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=22&asv=2");
                        }
                        if (dl_mp4hd != null) {
                            mainWindow.eval("kv_ds('dl_mp4hd', 'MP4', 'High Definition (1280x720)', '" + dl_mp4hd + "', '" + title + "');");
                        }
                        if (dl_mp4hd2 == null) {
                            dl_mp4hd2 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=37&asv=2");
                        }
                        if (dl_mp4hd2 != null) {
                            mainWindow.eval("kv_ds('dl_mp4hd2', 'MP4', 'High Definition (1920x1080)', '" + dl_mp4hd2 + "', '" + title + "');");
                        }
                        if (dl_mp4hd3 == null) {
                            dl_mp4hd3 = this.getRedirUrl("http://www.youtube.com/get_video?video_id=" + video_id + "&t=" + token + "&fmt=38&asv=2");
                        }
                        if (dl_mp4hd3 != null) {
                            mainWindow.eval("kv_ds('dl_mp4hd3', 'MP4', 'Epic Definition (4096x2304)', '" + dl_mp4hd3 + "', '" + title + "');");
                        }
                    }
                    else {
                        mainWindow.eval("kv_error('" + error + "');");
                    }
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
            else {
                mainWindow.eval("kv_error('Sorry, no download links found on this page.'");
            }
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
    }
    
    private String getUrlContent(final String url, final String httpMethod) {
        try {
            final URL u = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("User-Agent", this.getParameter("ua"));
            final InputStream is = conn.getInputStream();
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String getRedirUrl(final String url) {
        String hdr = "";
        try {
            final HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("User-Agent", this.getParameter("ua"));
            hdr = conn.getHeaderField("location");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hdr;
    }
    
    private String setHTMLEntity(final String input) {
        String output = "";
        try {
            output = input.replace("&amp;", "_").toString();
            output = output.replace("&lt;", "_").toString();
            output = output.replace("&gt;", "_").toString();
            output = output.replace("&#39;", "_").toString();
            output = output.replace("&quot;", "_").toString();
            output = output.replace("&", "_").toString();
            output = output.replace("\\\"", "_").toString();
            output = output.replace("\\'", "_").toString();
            output = output.replace("'", "_").toString();
            output = output.replace("'", "_").toString();
            output = output.replace("<", "_").toString();
            output = output.replace(">", "_").toString();
            output = output.replace("?", "_").toString();
            output = output.replace("/", "_").toString();
            output = output.replace("+", "_").toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return input;
        }
        return output;
    }
}
