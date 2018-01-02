import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KeepVidDownloader extends JApplet
{
    private String mv_decrypt(final String input, int k1, int k2) {
        final LinkedList<Integer> req1 = new LinkedList<Integer>();
        for (int req2 = 0; req2 < input.length(); ++req2) {
            final char c = input.charAt(req2);
            switch (c) {
                case '0': {
                    req1.add(0);
                    req1.add(0);
                    req1.add(0);
                    req1.add(0);
                    break;
                }
                case '1': {
                    req1.add(0);
                    req1.add(0);
                    req1.add(0);
                    req1.add(1);
                    break;
                }
                case '2': {
                    req1.add(0);
                    req1.add(0);
                    req1.add(1);
                    req1.add(0);
                    break;
                }
                case '3': {
                    req1.add(0);
                    req1.add(0);
                    req1.add(1);
                    req1.add(1);
                    break;
                }
                case '4': {
                    req1.add(0);
                    req1.add(1);
                    req1.add(0);
                    req1.add(0);
                    break;
                }
                case '5': {
                    req1.add(0);
                    req1.add(1);
                    req1.add(0);
                    req1.add(1);
                    break;
                }
                case '6': {
                    req1.add(0);
                    req1.add(1);
                    req1.add(1);
                    req1.add(0);
                    break;
                }
                case '7': {
                    req1.add(0);
                    req1.add(1);
                    req1.add(1);
                    req1.add(1);
                    break;
                }
                case '8': {
                    req1.add(1);
                    req1.add(0);
                    req1.add(0);
                    req1.add(0);
                    break;
                }
                case '9': {
                    req1.add(1);
                    req1.add(0);
                    req1.add(0);
                    req1.add(1);
                    break;
                }
                case 'a': {
                    req1.add(1);
                    req1.add(0);
                    req1.add(1);
                    req1.add(0);
                    break;
                }
                case 'b': {
                    req1.add(1);
                    req1.add(0);
                    req1.add(1);
                    req1.add(1);
                    break;
                }
                case 'c': {
                    req1.add(1);
                    req1.add(1);
                    req1.add(0);
                    req1.add(0);
                    break;
                }
                case 'd': {
                    req1.add(1);
                    req1.add(1);
                    req1.add(0);
                    req1.add(1);
                    break;
                }
                case 'e': {
                    req1.add(1);
                    req1.add(1);
                    req1.add(1);
                    req1.add(0);
                    break;
                }
                case 'f': {
                    req1.add(1);
                    req1.add(1);
                    req1.add(1);
                    req1.add(1);
                    break;
                }
            }
        }
        final LinkedList<Integer> req3 = new LinkedList<Integer>();
        for (int req2 = 0; req2 < 384; ++req2) {
            k1 = (k1 * 11 + 77213) % 81371;
            k2 = (k2 * 17 + 92717) % 192811;
            req3.add((k1 + k2) % 128);
        }
        for (int req2 = 256; req2 >= 0; --req2) {
            final int req4 = req3.get(req2);
            final int req5 = req2 % 128;
            final int req6 = req1.get(req4);
            req1.set(req4, req1.get(req5));
            req1.set(req5, req6);
        }
        for (int req2 = 0; req2 < 128; ++req2) {
            req1.set(req2, req1.get(req2) ^ (req3.get(req2 + 256) & 0x1));
        }
        String out = "";
        for (int req2 = 0; req2 < req1.size(); req2 += 4) {
            int tmp = req1.get(req2) * 8;
            tmp += req1.get(req2 + 1) * 4;
            tmp += req1.get(req2 + 2) * 2;
            tmp += req1.get(req2 + 3);
            switch (tmp) {
                case 0: {
                    out += "0";
                    break;
                }
                case 1: {
                    out += "1";
                    break;
                }
                case 2: {
                    out += "2";
                    break;
                }
                case 3: {
                    out += "3";
                    break;
                }
                case 4: {
                    out += "4";
                    break;
                }
                case 5: {
                    out += "5";
                    break;
                }
                case 6: {
                    out += "6";
                    break;
                }
                case 7: {
                    out += "7";
                    break;
                }
                case 8: {
                    out += "8";
                    break;
                }
                case 9: {
                    out += "9";
                    break;
                }
                case 10: {
                    out += "a";
                    break;
                }
                case 11: {
                    out += "b";
                    break;
                }
                case 12: {
                    out += "c";
                    break;
                }
                case 13: {
                    out += "d";
                    break;
                }
                case 14: {
                    out += "e";
                    break;
                }
                case 15: {
                    out += "f";
                    break;
                }
            }
        }
        return out;
    }
    
    private String inbtwn(final String a, final String b, final String c) {
        try {
            final String[] arr1 = a.split(b);
            final String[] arr2 = arr1[1].split(c);
            if (arr2[0] == null) {
                return arr1[1];
            }
            return arr2[0];
        }
        catch (Exception ex) {
            return null;
        }
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
    
    private String pregMatch(final String regexp, final String haystack, final int mat) {
        try {
            final Matcher matcher = Pattern.compile(regexp).matcher(haystack);
            return matcher.group(mat);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public void init() {
        final JSObject mainWindow = JSObject.getWindow((Applet)this);
        mainWindow.eval("jloaded();");
        final String error = null;
        final String alternate = null;
        try {
            super.init();
            final String vParam = this.getParameter("v");
            String uParam = this.getParameter("u");
            final String siteParam = this.getParameter("site");
            final String uaParam = this.getParameter("ua");
            System.out.println("param u: " + uParam + "\r\n");
            System.out.println("param ua: " + uaParam + "\r\n");
            if (!uParam.contains("youtube.com")) {
                if (!uParam.contains("youtu.be")) {
                    if (uParam.contains("megavideo.com")) {
                        try {
                            String video_id = vParam;
                            if (video_id == null) {
                                video_id = this.inbtwn(URLDecoder.decode(this.getRedirUrl(uParam), "UTF-8"), "v=", "&");
                            }
                            final String pageSource = this.getUrlContent("http://www.megavideo.com/xml/videolink.php?v=" + video_id, "GET");
                            final String un = this.inbtwn(pageSource, " un=\"", "\"");
                            final int k1 = Integer.valueOf(this.inbtwn(pageSource, " k1=\"", "\""));
                            final int k2 = Integer.valueOf(this.inbtwn(pageSource, " k2=\"", "\""));
                            final String s = this.inbtwn(pageSource, " s=\"", "\"");
                            String title = this.inbtwn(pageSource, " title=\"", "\"");
                            title = URLDecoder.decode(this.setHTMLEntity(title).replace("+", " "), "UTF-8");
                            final String embed = URLDecoder.decode(this.inbtwn(pageSource, "embed=\"", "\""), "UTF-8");
                            final String imgsrc = this.inbtwn(embed, "\"http://www.megavideo.com/v/" + video_id, "\"");
                            mainWindow.eval("kv_info('" + title + "', 'megavideo.com', 'http://www.megavideo.com/?v=" + video_id + "', 'http://img1.megavideo.com/" + imgsrc + ".jpg');");
                            final String hashresult = this.mv_decrypt(un, k1, k2);
                            final String mv_flv = "http://www" + s + ".megavideo.com/files/" + hashresult + "/" + title + " [www.icyvideo.com].flv";
                            String hd_url = null;
                            try {
                                hd_url = URLDecoder.decode(this.inbtwn(pageSource, "hd_url=\"", "\""), "UTF-8");
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            if (hd_url != null) {
                                hd_url = hd_url + title + " [www.icyvideo.com].mp4";
                            }
                            if (mv_flv != null) {
                                mainWindow.eval("kv_ds('mv_flv', 'FLV', 'High Quality - 480x270', '" + mv_flv + "', '" + title + "');");
                            }
                            if (hd_url != null) {
                                mainWindow.eval("kv_ds('mv_mp4', 'MP4', 'High Definition - 1280x720', '" + hd_url + "', '" + title + "');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("dailymotion.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String sequence = URLDecoder.decode(this.inbtwn(pageSource2, "\"sequence\",  \"", "\""), "UTF-8").replace("\\/", "/");
                            final String title2 = this.inbtwn(pageSource2, "DMTITLE=", "&");
                            String imgsrc2 = this.inbtwn(pageSource2, "og:image\" content=\"", "\"");
                            if (imgsrc2 == null) {
                                imgsrc2 = this.inbtwn(pageSource2, "/jpeg\" href=\"", "\"");
                            }
                            mainWindow.eval("kv_info('" + title2 + "', 'dailymotion.com', '" + uParam + "', '" + imgsrc2 + "');");
                            final String dm_low = this.inbtwn(sequence, "\"sdURL\":\"", "\"");
                            final String dm_high = this.inbtwn(sequence, "\"hqURL\":\"", "\"");
                            if (dm_low != null && dm_low.contains("flv")) {
                                mainWindow.eval("kv_ds('dm_low', 'FLV', 'Standard Quality', '" + dm_low + "', '');");
                            }
                            if (dm_low != null && dm_low.contains("mp4")) {
                                mainWindow.eval("kv_ds('dm_low', 'MP4', 'Standard Quality', '" + dm_low + "', '');");
                            }
                            if (dm_high != null && dm_high.contains("flv")) {
                                mainWindow.eval("kv_ds('dm_high', 'FLV', 'High Quality', '" + dm_high + "', '');");
                            }
                            if (dm_high != null && dm_high.contains("mp4")) {
                                mainWindow.eval("kv_ds('dm_high', 'MP4', 'High Quality', '" + dm_high + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("facebook.com")) {
                        try {
                            String video_id = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "v=", "&");
                            if (video_id == null) {
                                video_id = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "/v/", "?");
                            }
                            if (video_id == null) {
                                video_id = this.inbtwn(URLDecoder.decode(this.getRedirUrl(uParam), "UTF-8"), "v=", "&");
                            }
                            String pageSource = URLDecoder.decode(this.getUrlContent("http://facebook.com/video/external_video.php?v=" + video_id, "GET"), "UTF-8");
                            pageSource = pageSource.replace("\\/", "/");
                            final String title2 = this.inbtwn(pageSource, "\"video_title\":\"", "\"");
                            final String imgsrc2 = this.inbtwn(pageSource, "\"thumb_url\":\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'facebook.com', 'http://www.facebook.com/video/video.php?v=" + video_id + "', '" + imgsrc2 + "');");
                            final String fb_mp4 = this.inbtwn(pageSource, "\"video_src\":\"", "\"").replace("\\/", "/").toString();
                            if (fb_mp4 != null) {
                                mainWindow.eval("kv_ds('fb_mp4', 'MP4', 'High Quality', '" + fb_mp4 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("google.com")) {
                        try {
                            String video_id = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "docid=", "&");
                            if (video_id == null) {
                                video_id = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "docId=", "&");
                            }
                            final String pageSource = this.getUrlContent("http://video.google.com/docinfo?%7B\"docid\":\"" + video_id + "\"%7D", "GET");
                            String title2 = this.inbtwn(pageSource, "\"Title\":\"", "\",");
                            title2 = this.setHTMLEntity(title2);
                            final String imgsrc2 = this.inbtwn(pageSource, "\"thumbnail_url\":\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'video.google.com', 'http://video.google.com/videoplay?docid=" + video_id + "', '" + imgsrc2 + "');");
                            final String gv_flv = this.inbtwn(pageSource, "\"streamer_url\":\"", "\"");
                            if (gv_flv != null) {
                                mainWindow.eval("kv_ds('gv_flv', 'FLV', 'High Quality', '" + gv_flv + "', '" + title2 + "');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("vimeo.com")) {
                        try {
                            final String url_id = uParam;
                            final String[] video_arr = url_id.split("com/");
                            final String video_id2 = video_arr[1];
                            final String pageSource3 = this.getUrlContent("http://vimeo.com/moogaloop/load/clip:" + video_id2, "GET");
                            final String title3 = this.inbtwn(pageSource3, "<caption>", "</caption>");
                            final String imgsrc3 = this.inbtwn(pageSource3, "<thumbnail>", "</thumbnail>").replace("_640.jpg", "_100.jpg");
                            mainWindow.eval("kv_info('" + title3 + "', 'vimeo.com', 'http://vimeo.com/" + video_id2 + "', '" + imgsrc3 + "');");
                            final String rs = URLDecoder.decode(this.inbtwn(pageSource3, "<request_signature>", "</request_signature>"), "UTF-8");
                            final String rse = URLDecoder.decode(this.inbtwn(pageSource3, "<request_signature_expires>", "</request_signature_expires>"), "UTF-8");
                            final String vm_low = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + video_id2 + "/" + rs + "/" + rse + "/?q=sd");
                            final String vm_high = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + video_id2 + "/" + rs + "/" + rse + "/?q=hd");
                            if (vm_low != null) {
                                String frmt = "FLV";
                                if (vm_low.contains(".avi?")) {
                                    frmt = "AVI";
                                }
                                if (vm_low.contains(".mp4?")) {
                                    frmt = "MP4";
                                }
                                mainWindow.eval("kv_ds('vm_low', '" + frmt + "', 'Standard Quality', '" + vm_low + "', '');");
                            }
                            if (vm_high != null) {
                                String frmt = "FLV";
                                if (vm_high.contains(".avi?")) {
                                    frmt = "AVI";
                                }
                                if (vm_high.contains(".mp4?")) {
                                    frmt = "MP4";
                                }
                                mainWindow.eval("kv_ds('vm_high', '" + frmt + "', 'High Definition', '" + vm_high + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("metacafe.com")) {
                        try {
                            final String video_id = this.inbtwn(uParam, "watch/", "/");
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title2 = this.inbtwn(pageSource, "\"itemTitle\":\"", "\"");
                            final String imgsrc2 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'metacafe.com', '" + uParam + "', '" + imgsrc2 + "');");
                            final String flashvars = URLDecoder.decode(this.getRedirUrl("http://www.metacafe.com/fplayer/" + video_id + "/.swf"), "UTF-8").replace("\\/", "/");
                            String file = this.inbtwn(flashvars, "\"mediaURL\":\"", "\"");
                            if (file == null) {
                                file = this.inbtwn(flashvars, "mediaURL=", "&");
                            }
                            String mc_gdakey = this.inbtwn(flashvars, "gdaKey=", "&");
                            if (mc_gdakey == null) {
                                mc_gdakey = this.inbtwn(flashvars, "\"key\":\"", "\"");
                            }
                            if (mc_gdakey != null) {
                                file = file + "?__gda__=" + mc_gdakey;
                            }
                            if (file.contains(".mp4")) {
                                mainWindow.eval("kv_ds('mc_mp4', 'MP4', 'High Quality', '" + file + "', '');");
                            }
                            else {
                                mainWindow.eval("kv_ds('mc_flv', 'FLV', 'Standard Quality', '" + file + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("photobucket.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "description\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'photobucket.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String pb_flv = this.inbtwn(pageSource2, "file=", "\"");
                            if (pb_flv != null) {
                                mainWindow.eval("kv_ds('pb_flv', 'FLV', 'High Quality', '" + pb_flv + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("ehow.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ehow.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String eh_flv = "http" + this.inbtwn(pageSource2, "id: 'http", "'");
                            if (eh_flv != null) {
                                mainWindow.eval("kv_ds('eh_flv', 'FLV', 'High Quality', '" + eh_flv + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("ted.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "id=\"altHeadline\">", "</span>");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ted.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String ted_flv = this.inbtwn(pageSource2, "vu=", "&");
                            final String ted_mp4 = "http://www.ted.com/" + this.inbtwn(pageSource2, "<dt><a href=\"/", "\"");
                            final String ted_mp4hq = "http://www.ted.com/" + this.inbtwnmore(pageSource2, "<dt><a href=\"/", "\"", 2);
                            if (ted_flv != null) {
                                mainWindow.eval("kv_ds('ted_flv', 'FLV', 'Standard Quality', '" + ted_flv + "', '');");
                            }
                            if (ted_mp4 != null) {
                                mainWindow.eval("kv_ds('ted_mp4', 'MP4', 'Standard Quality', '" + ted_mp4 + "', '');");
                            }
                            if (ted_mp4hq != null) {
                                mainWindow.eval("kv_ds('ted_mp4hq', 'MP4', 'High Quality', '" + ted_mp4hq + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("twitvid.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", " - TwitVid\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'twitvid.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String xmlSource = this.getUrlContent(URLDecoder.decode(this.inbtwn(pageSource2, "file=", "&"), "UTF-8"), "GET");
                            final String tv_mp4 = this.inbtwn(xmlSource, "httpBase\" content=\"", "\"") + this.inbtwn(xmlSource, "src=\"", "\"");
                            if (tv_mp4 != null) {
                                mainWindow.eval("kv_ds('tv_mp4', 'MP4', 'Standard Quality', '" + tv_mp4 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("tudou.com")) {
                        try {
                            final String firstSource = this.getUrlContent(uParam, "GET");
                            String iid = this.inbtwn(firstSource, "defaultIid = ", "\n");
                            if (iid == null) {
                                iid = this.inbtwn(firstSource, "iid: \"", "\"");
                            }
                            String maincons = null;
                            if (uParam.contains("hd.")) {
                                maincons = "http://v2.tudou.com/v2/kili?id=";
                            }
                            else {
                                maincons = "http://v2.tudou.com/v?it=";
                            }
                            final String pageSource3 = this.getUrlContent(maincons + iid, "GET");
                            final String[] arr$;
                            final String[] file_arr = arr$ = pageSource3.split("<f ");
                            for (final String file_a : arr$) {
                                if (file_a.contains("size=\"")) {
                                    final String[] arr1 = file_a.split(">");
                                    final String[] arr2 = arr1[1].split("</");
                                    final String file2 = arr2[0];
                                    System.out.println(file2);
                                    if (file2.contains(".f4v")) {
                                        mainWindow.eval("kv_ds('td_f4v', 'F4V', 'Standard Quality', '" + file2 + "', '');");
                                    }
                                    if (file2.contains(".flv")) {
                                        mainWindow.eval("kv_ds('td_flv', 'FLV', 'Standard Quality', '" + file2 + "', '');");
                                    }
                                    if (file2.contains(".m4v")) {
                                        mainWindow.eval("kv_ds('td_m4v', 'M4V', 'Standard Quality', '" + file2 + "', '');");
                                    }
                                    if (file2.contains(".mp4")) {
                                        mainWindow.eval("kv_ds('td_mp4', 'MP4', 'Standard Quality', '" + file2 + "', '');");
                                    }
                                }
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("flickr.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            String title4 = this.inbtwn(pageSource2, "p.title = '", "'");
                            if (title4 == null) {
                                title4 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            }
                            String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            if (imgsrc4 == null) {
                                imgsrc4 = this.inbtwn(pageSource2, "geo_thmb = '", "'").replace("_s.jpg", "_m.jpg");
                            }
                            mainWindow.eval("kv_info('" + title4 + "', 'flickr.com', '" + uParam + "', '" + imgsrc4 + "');");
                            String node_id = null;
                            final Pattern p = Pattern.compile("tagdiv([0-9]+-[0-9]+)-");
                            final Matcher m = p.matcher(pageSource2);
                            if (m.find()) {
                                node_id = m.group(1);
                            }
                            String secret = this.inbtwn(pageSource2, "photo_secret: '", "'");
                            if (secret == null) {
                                secret = this.inbtwn(pageSource2, "page_p.secret = '", "'");
                            }
                            final String configSource = this.getUrlContent("http://www.flickr.com/video_playlist.gne?node_id=" + node_id + "&secret=" + secret, "GET");
                            final String file3 = this.inbtwn(configSource, "APP=\"", "\"") + this.inbtwn(configSource, "FULLPATH=\"", "\"");
                            if (file3.contains(".flv")) {
                                mainWindow.eval("kv_ds('fl_flv', 'FLV', 'Standard Quality', '" + file3 + "', '');");
                            }
                            if (file3.contains(".mp4")) {
                                mainWindow.eval("kv_ds('fl_mp4', 'MP4', 'Standard Quality', '" + file3 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("ning.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "<h1>", "</h1>");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ning.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource2, "video-url\" value=\"", "\"");
                            if (file4.contains(".flv")) {
                                mainWindow.eval("kv_ds('ng_flv', 'FLV', 'Standard Quality', '" + file4 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("current.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", " // Current\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'current.com', '" + uParam + "', '" + imgsrc4 + "');");
                            String file4 = URLDecoder.decode(this.inbtwn(pageSource2, "assetUrl=", "&"), "UTF-8");
                            if (file4 == null) {
                                file4 = this.inbtwn(pageSource2, "'assetUrl', '", "'");
                            }
                            mainWindow.eval("kv_ds('cu_flv', 'FLV', 'Standard Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("clipfish.de")) {
                        try {
                            final String videoid = this.inbtwn(uParam, "video/", "/");
                            final String pageSource = this.getUrlContent("http://www.clipfish.de/devxml/videoinfo/" + videoid, "GET");
                            final String title2 = this.inbtwn(pageSource, "<title><!\\[CDATA\\[", "\\]\\]></title>");
                            final String imgsrc2 = this.inbtwn(pageSource, "<imageurl>", "</imageurl>");
                            mainWindow.eval("kv_info('" + title2 + "', 'clipfish.de', '" + uParam + "', '" + imgsrc2 + "');");
                            final String file5 = this.inbtwn(pageSource, "<filename>", "</filename>");
                            if (file5.contains(".mp4")) {
                                mainWindow.eval("kv_ds('cf_mp4', 'MP4', 'Standard Quality', '" + file5 + "', '');");
                            }
                            else {
                                mainWindow.eval("kv_ds('cf_flv', 'FLV', 'Standard Quality', '" + file5 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("blip.tv")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'blip.tv', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource2, "setPrimaryMediaUrl\\(\"", "\"");
                            if (file4.contains(".m4v")) {
                                mainWindow.eval("kv_ds('bt_m4v', 'M4V', 'Standard Quality', '" + file4 + "', '');");
                            }
                            else {
                                mainWindow.eval("kv_ds('bt_flv', 'FLV', 'Standard Quality', '" + file4 + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("break.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "<h1>", "</h1>");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image\" content=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'break.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String fn = this.inbtwn(pageSource2, "sGlobalFileName='", "'");
                            final String fp = this.inbtwn(pageSource2, "sGlobalContentFilePath='", "'");
                            final String icon = this.inbtwn(pageSource2, "flashVars.icon = \"", "\"");
                            final String file6 = "http://video1.break.com/dnet/media/" + fp + "/" + fn;
                            mainWindow.eval("kv_ds('br_flv', 'FLV', 'Standard Quality', '" + file6 + ".flv?" + icon + "', '');");
                            mainWindow.eval("kv_ds('br_mp4', 'MP4', 'Standard Quality', '" + file6 + ".mp4?" + icon + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("clipser.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String file7 = "http://videos.clipser.com//" + this.inbtwn(pageSource2, "=http://www.clipser.com/vimages/", "1.jpg") + ".flv";
                            mainWindow.eval("kv_ds('cs_flv', 'FLV', 'Standard Quality', '" + file7 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("videoweed.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'videoweed.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource2, "\"file\",\"", "\"");
                            mainWindow.eval("kv_ds('vw_flv', 'FLV', 'Standard Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("watchme.tv")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String mid = this.inbtwn(pageSource2, "mid=", "\"");
                            final String feedSource = this.getUrlContent("http://www.watchme.tv/feed/moviePlayList.php?mid=" + mid, "GET");
                            final String title5 = this.inbtwn(feedSource, "<!\\[CDATA\\[", "\\]");
                            final String imgsrc5 = this.inbtwn(feedSource, "<thumb1>", "</thumb1>");
                            mainWindow.eval("kv_info('" + title5 + "', 'watchme.tv', '" + uParam + "', '" + imgsrc5 + "');");
                            final String file = this.inbtwn(feedSource, "<fileUrl>", "</fileUrl>");
                            mainWindow.eval("kv_ds('wm_flv', 'MOV', 'High Quality', '" + file + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("veoh.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String video_id3 = this.inbtwn(pageSource2, "item-id\" content=\"", "\"");
                            final String feedSource = this.getUrlContent("http://www.veoh.com/rest/video/" + video_id3 + "/details", "GET");
                            final String title5 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            final String imgsrc5 = this.inbtwn(feedSource, "fullMedResImagePath=\"", "\"");
                            mainWindow.eval("kv_info('" + title5 + "', 'veoh.com', '" + uParam + "', '" + imgsrc5 + "');");
                            final String file = this.inbtwn(feedSource, "fullPreviewHashPath=\"", "\"");
                            mainWindow.eval("kv_ds('wm_flv', 'FLV', 'High Quality', '" + file + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("myspace.com")) {
                        try {
                            final String video_id = this.inbtwn(uParam.toLowerCase(), "videoid=", "&");
                            final String pageSource = this.getUrlContent("http://mediaservices.myspace.com/services/rss.ashx?type=video&videoID=" + video_id, "GET");
                            final String title2 = this.inbtwnmore(pageSource, "<title>", "</title>", 2);
                            final String imgsrc2 = this.inbtwn(pageSource, "thumbnail url=\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'myspace.com', '" + uParam + "', '" + imgsrc2 + "');");
                            final String file5 = this.inbtwn(pageSource, "content url=\"", "\"");
                            mainWindow.eval("kv_ds('ms_flv', 'FLV', 'High Quality', '" + file5 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("stagevu.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "name=\"movieTitle\" value=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "name=\"previewImage\" value=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'stagevu.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource2, "name=\"src\" value=\"", "\"");
                            mainWindow.eval("kv_ds('sv_flv', 'AVI', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("rutube.ru")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "h1 title=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource2, "&image=", "&");
                            mainWindow.eval("kv_info('" + title4 + "', 'rutube.ru', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource2, "&file=", "&");
                            mainWindow.eval("kv_ds('rt_flv', 'FLV', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("vbox7.com")) {
                        try {
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource2, "title\" content=\"", "\"");
                            final String video_id2 = this.inbtwn(pageSource2, "VideoMdkey = '", "'");
                            final String xmlSource = this.getUrlContent("http://vbox7.com/etc/ext.do?key=" + video_id2, "GET");
                            String imgsrc5 = this.inbtwn(xmlSource, "jpg_addr=", "&");
                            if (imgsrc5 != null) {
                                imgsrc5 = "http://" + imgsrc5;
                            }
                            mainWindow.eval("kv_info('" + title4 + "', 'vbox7.com', '" + uParam + "', '" + imgsrc5 + "');");
                            String file = this.inbtwn(xmlSource, "flv_addr=", "&");
                            if (file != null) {
                                file = "http://" + file;
                            }
                            mainWindow.eval("kv_ds('vb_flv', 'FLV', 'High Quality', '" + file + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    mainWindow.eval("kv_error('Sorry, no download links found on this page.'");
                    return;
                }
            }
            try {
                if (uParam.contains("youtu.be")) {
                    uParam = this.getRedirUrl(uParam);
                }
                final String pageSource2 = this.getUrlContent(uParam + "&fmt=18", "GET");
                String video_id3 = this.inbtwn(pageSource2, "\"video_id\": \"", "\"");
                if (video_id3 == null) {
                    video_id3 = this.inbtwn(pageSource2, "&video_id=", "&");
                }
                System.out.println("Getting Title...");
                String title2 = this.inbtwn(pageSource2, "'VIDEO_TITLE': '", "',");
                if (title2 == null) {
                    title2 = this.inbtwn(pageSource2, "name=\"title\" content=\"", "\"");
                }
                if (title2 == null) {
                    title2 = this.inbtwn(pageSource2, "&title=", "&");
                }
                try {
                    title2 = this.setHTMLEntity(title2);
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
                System.out.println("Title: " + title2 + "\r\n");
                mainWindow.eval("kv_info('" + title2 + "', 'youtube.com', 'http://www.youtube.com/watch?v=" + video_id3 + "', 'http://i.ytimg.com/vi/" + video_id3 + "/default.jpg');");
                String fmt_url_map = "";
                fmt_url_map = this.inbtwn(pageSource2, "&amp;fmt_url_map=", "&");
                if (fmt_url_map != null) {
                    fmt_url_map = URLDecoder.decode(fmt_url_map, "UTF-8");
                }
                if (fmt_url_map == null) {
                    fmt_url_map = this.inbtwn(pageSource2, "\"fmt_url_map\": \"", "\"").replace("\\/", "/");
                }
                System.out.println("fmt_url_map: " + fmt_url_map + "\r\n");
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
                final String dl_webmhd = null;
                final String dl_webmhd2 = null;
                final String[] arr$2;
                final String[] fmt_arr = arr$2 = fmt_url_map.split(",");
                for (final String fmt : arr$2) {
                    final String[] parts = fmt.split(Pattern.quote("|"));
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
                    if (m_qual.equals("43")) {
                        dl_mp4hd2 = m_url;
                    }
                    if (m_qual.equals("45")) {
                        dl_mp4hd3 = m_url;
                    }
                    System.out.println("URL: " + m_url);
                }
                if (error == null) {
                    if (dl_3gplow != null) {
                        mainWindow.eval("kv_ds('dl_3gplow', '3GP', 'Low Quality - 176\u00d7144', '" + dl_3gplow + "', '" + title2 + "');");
                    }
                    if (dl_3gpmed != null) {
                        mainWindow.eval("kv_ds('dl_3gpmed', '3GP', 'Medium Quality - 176\u00d7144', '" + dl_3gpmed + "', '" + title2 + "');");
                    }
                    if (dl_3gphigh != null) {
                        mainWindow.eval("kv_ds('dl_3gphigh', '3GP', 'High Quality - 320\u00d7240', '" + dl_3gphigh + "', '" + title2 + "');");
                    }
                    if (dl_flvlow != null) {
                        mainWindow.eval("kv_ds('dl_flvlow', 'FLV', 'Low Quality - 400\u00d7226', '" + dl_flvlow + "', '" + title2 + "');");
                    }
                    if (dl_flvmed2 != null) {
                        mainWindow.eval("kv_ds('dl_flvmed2', 'FLV', 'Medium Quality - 480\u00d7360', '" + dl_flvmed2 + "', '" + title2 + "');");
                    }
                    if (dl_flvmed != null) {
                        mainWindow.eval("kv_ds('dl_flvmed', 'FLV', 'Medium Quality - 640\u00d7360', '" + dl_flvmed + "', '" + title2 + "');");
                    }
                    if (dl_flvhigh != null) {
                        mainWindow.eval("kv_ds('dl_flvhigh', 'FLV', 'High Quality - 854\u00d7480', '" + dl_flvhigh + "', '" + title2 + "');");
                    }
                    if (dl_mp4high != null) {
                        mainWindow.eval("kv_ds('dl_mp4high', 'MP4', 'High Quality - 480\u00d7360', '" + dl_mp4high + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd', 'MP4', 'High Definition - 1280\u00d7720', '" + dl_mp4hd + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd2 != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd2', 'MP4', 'High Definition - 1920\u00d71080', '" + dl_mp4hd2 + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd3 != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd3', 'MP4', 'Epic Definition - 4096\u00d72304', '" + dl_mp4hd3 + "', '" + title2 + "');");
                    }
                    if (dl_webmhd != null) {
                        mainWindow.eval("kv_ds('dl_webmhd', 'WebM', 'High Definition - 854\u00d7480', '" + dl_webmhd + "', '" + title2 + "');");
                    }
                    if (dl_webmhd2 != null) {
                        mainWindow.eval("kv_ds('dl_webmhd', 'WebM', 'High Definition - 1280\u00d7720', '" + dl_webmhd2 + "', '" + title2 + "');");
                    }
                }
                else {
                    mainWindow.eval("kv_error('" + error + "');");
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
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
            try {
                this.getAppletContext().showDocument(new URL("javascript:jerror();"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
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
            try {
                this.getAppletContext().showDocument(new URL("javascript:jerror();"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return hdr;
    }
    
    private String setHTMLEntity(final String input) {
        String output = "";
        try {
            output = input.replace("&amp;", "&").toString();
            output = output.replace("&lt;", "_").toString();
            output = output.replace("&gt;", "_").toString();
            output = output.replace("&#39;", "_").toString();
            output = output.replace("&quot;", "_").toString();
            output = output.replace("&", "_").toString();
            output = output.replace("_amp;", "_").toString();
            output = output.replace("\\\"", "_").toString();
            output = output.replace("\\'", "_").toString();
            output = output.replace("'", "_").toString();
            output = output.replace("'", "_").toString();
            output = output.replace("<", "_").toString();
            output = output.replace(">", "_").toString();
            output = output.replace("?", "_").toString();
            output = output.replace("/", "_").toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return input;
        }
        return output;
    }
}
