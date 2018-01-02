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
            try {
                final String[] arr2 = arr1[1].split(c);
                if (arr2[0] != null || arr2[0] != "") {
                    return arr2[0];
                }
            }
            catch (Exception ex) {
                return arr1[1];
            }
            return arr1[1];
        }
        catch (Exception ex2) {
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
        try {
            super.init();
            String uParam = this.getParameter("u");
            final String uaParam = this.getParameter("ua");
            System.out.println("param u: " + uParam + "\r\n");
            System.out.println("param ua: " + uaParam + "\r\n");
            if (!uParam.contains("youtube.com")) {
                if (!uParam.contains("youtu.be")) {
                    if (uParam.contains("megavideo.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String video_id = this.inbtwn(pageSource, "flashvars.v = \"", "\"");
                            final String un = this.inbtwn(pageSource, "flashvars.un = \"", "\"");
                            final int k1 = Integer.valueOf(this.inbtwn(pageSource, "flashvars.k1 = \"", "\""));
                            final int k2 = Integer.valueOf(this.inbtwn(pageSource, "flashvars.k2 = \"", "\""));
                            final String s = this.inbtwn(pageSource, "flashvars.s = \"", "\"");
                            String title = this.inbtwn(pageSource, "flashvars.title = \"", "\"");
                            title = URLDecoder.decode(this.setHTMLEntity(title).replace("+", " "), "UTF-8");
                            final String imgsrc = this.inbtwn(pageSource, "flashvars.image = \"", "\"");
                            mainWindow.eval("kv_info('" + title + "', 'megavideo.com', 'http://www.megavideo.com/?v=" + video_id + "', '" + imgsrc + "');");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String sequence = URLDecoder.decode(this.inbtwn(pageSource, "\"sequence\",  \"", "\""), "UTF-8").replace("\\/", "/");
                            final String title2 = this.inbtwn(pageSource, "DMTITLE=", "&");
                            String imgsrc2 = this.inbtwn(pageSource, "og:image\" content=\"", "\"");
                            if (imgsrc2 == null) {
                                imgsrc2 = this.inbtwn(pageSource, "/jpeg\" href=\"", "\"");
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
                            String video_id2 = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "v=", "&");
                            if (video_id2 == null) {
                                video_id2 = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "/v/", "?");
                            }
                            if (video_id2 == null) {
                                video_id2 = this.inbtwn(URLDecoder.decode(this.getRedirUrl(uParam), "UTF-8"), "v=", "&");
                            }
                            String pageSource2 = URLDecoder.decode(this.getUrlContent("http://facebook.com/video/external_video.php?v=" + video_id2, "GET"), "UTF-8");
                            pageSource2 = pageSource2.replace("\\/", "/");
                            final String title2 = this.inbtwn(pageSource2, "\"video_title\":\"", "\"");
                            final String imgsrc2 = this.inbtwn(pageSource2, "\"thumb_url\":\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'facebook.com', 'http://www.facebook.com/video/video.php?v=" + video_id2 + "', '" + imgsrc2 + "');");
                            final String fb_mp4 = this.inbtwn(pageSource2, "\"video_src\":\"", "\"").replace("\\/", "/").toString();
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
                            String video_id2 = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "docid=", "&");
                            if (video_id2 == null) {
                                video_id2 = this.inbtwn(URLDecoder.decode(uParam, "UTF-8"), "docId=", "&");
                            }
                            final String pageSource2 = this.getUrlContent("http://video.google.com/docinfo?%7B\"docid\":\"" + video_id2 + "\"%7D", "GET");
                            String title2 = this.inbtwn(pageSource2, "\"Title\":\"", "\",");
                            title2 = this.setHTMLEntity(title2);
                            final String imgsrc2 = this.inbtwn(pageSource2, "\"thumbnail_url\":\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'video.google.com', 'http://video.google.com/videoplay?docid=" + video_id2 + "', '" + imgsrc2 + "');");
                            final String gv_flv = this.inbtwn(pageSource2, "\"streamer_url\":\"", "\"");
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
                            final String video_id3 = video_arr[1];
                            final String pageSource3 = this.getUrlContent("http://vimeo.com/moogaloop/load/clip:" + video_id3, "GET");
                            final String title3 = this.inbtwn(pageSource3, "<caption>", "</caption>");
                            final String imgsrc3 = this.inbtwn(pageSource3, "<thumbnail>", "</thumbnail>").replace("_640.jpg", "_100.jpg");
                            mainWindow.eval("kv_info('" + title3 + "', 'vimeo.com', 'http://vimeo.com/" + video_id3 + "', '" + imgsrc3 + "');");
                            final String rs = URLDecoder.decode(this.inbtwn(pageSource3, "<request_signature>", "</request_signature>"), "UTF-8");
                            final String rse = URLDecoder.decode(this.inbtwn(pageSource3, "<request_signature_expires>", "</request_signature_expires>"), "UTF-8");
                            final String vm_low = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + video_id3 + "/" + rs + "/" + rse + "/?q=sd");
                            final String vm_high = this.getRedirUrl("http://www.vimeo.com/moogaloop/play/clip:" + video_id3 + "/" + rs + "/" + rse + "/?q=hd");
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
                            final String video_id2 = this.inbtwn(uParam, "watch/", "/");
                            final String pageSource2 = this.getUrlContent(uParam, "GET");
                            final String title2 = this.inbtwn(pageSource2, "\"itemTitle\":\"", "\"");
                            final String imgsrc2 = this.inbtwn(pageSource2, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title2 + "', 'metacafe.com', '" + uParam + "', '" + imgsrc2 + "');");
                            final String flashvars = URLDecoder.decode(this.getRedirUrl("http://www.metacafe.com/fplayer/" + video_id2 + "/.swf"), "UTF-8").replace("\\/", "/");
                            String file = this.inbtwn(flashvars, "\"mediaURL\":\"", "\"");
                            if (file == null) {
                                file = this.inbtwn(flashvars, "mediaURL=", "&");
                            }
                            String mc_gdakey = mc_gdakey = this.inbtwn(flashvars, "\"key\":\"", "\"");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'photobucket.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String pb_flv = this.inbtwn(pageSource, "file=", "\"");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image\" content=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ehow.com', '" + uParam + "', '" + imgsrc4 + "');");
                            String eh_flv = this.inbtwn(pageSource, "data-video-id=\"", "\"");
                            if (eh_flv != null) {
                                eh_flv = this.inbtwn(pageSource, "&amp;source=", "&amp");
                            }
                            final String eh_flvhd = this.inbtwn(pageSource, "data-video-hd-id=\"", "\"");
                            if (eh_flv != null) {
                                mainWindow.eval("kv_ds('eh_flv', 'FLV', 'High Quality', '" + eh_flv + "', '');");
                            }
                            if (eh_flvhd != null) {
                                mainWindow.eval("kv_ds('eh_flvhd', 'FLV', 'High Definition', '" + eh_flvhd + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("ted.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "id=\"altHeadline\">", "</span>");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ted.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String ted_flv = this.inbtwn(pageSource, "vu=", "&");
                            final String ted_mp4 = "http://www.ted.com/" + this.inbtwn(pageSource, "<dt><a href=\"/", "\"");
                            final String ted_mp4hq = "http://www.ted.com/" + this.inbtwnmore(pageSource, "<dt><a href=\"/", "\"", 2);
                            if (ted_flv != null && ted_flv.contains(".mp4")) {
                                mainWindow.eval("kv_ds('ted_flv', 'MP4', 'Standard Quality', '" + ted_flv + "', '');");
                            }
                            else if (ted_flv != null) {
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image\" content=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'twitvid.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String tv_mp4 = URLDecoder.decode(this.inbtwn(pageSource, "file=", "&"), "UTF-8");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            String title4 = this.inbtwn(pageSource, "p.title = '", "'");
                            if (title4 == null) {
                                title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            }
                            String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            if (imgsrc4 == null) {
                                imgsrc4 = this.inbtwn(pageSource, "geo_thmb = '", "'").replace("_s.jpg", "_m.jpg");
                            }
                            mainWindow.eval("kv_info('" + title4 + "', 'flickr.com', '" + uParam + "', '" + imgsrc4 + "');");
                            String node_id = null;
                            final Pattern p = Pattern.compile("tagdiv([0-9]+-[0-9]+)-");
                            final Matcher m = p.matcher(pageSource);
                            if (m.find()) {
                                node_id = m.group(1);
                            }
                            String secret = this.inbtwn(pageSource, "photo_secret: '", "'");
                            if (secret == null) {
                                secret = this.inbtwn(pageSource, "page_p.secret = '", "'");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "<h1>", "</h1>");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'ning.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource, "video-url\" value=\"", "\"");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", " // Current\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'current.com', '" + uParam + "', '" + imgsrc4 + "');");
                            String file4 = URLDecoder.decode(this.inbtwn(pageSource, "assetUrl=", "&"), "UTF-8");
                            if (file4 == null) {
                                file4 = this.inbtwn(pageSource, "'assetUrl', '", "'");
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
                            final String pageSource2 = this.getUrlContent("http://www.clipfish.de/devxml/videoinfo/" + videoid, "GET");
                            final String title2 = this.inbtwn(pageSource2, "<title><!\\[CDATA\\[", "\\]\\]></title>");
                            final String imgsrc2 = this.inbtwn(pageSource2, "<imageurl>", "</imageurl>");
                            mainWindow.eval("kv_info('" + title2 + "', 'clipfish.de', '" + uParam + "', '" + imgsrc2 + "');");
                            final String file5 = this.inbtwn(pageSource2, "<filename>", "</filename>");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String video_id = this.inbtwn(pageSource, "data-posts-id=\"", "\"");
                            final String pageRss = this.getUrlContent("http://blip.tv/rss/flash/" + video_id, "GET");
                            final String[] arrt = pageRss.split("<item>");
                            final String title3 = this.inbtwn(arrt[1], "<title>", "</title>");
                            final String imgsrc3 = "http://a.images.blip.tv/" + this.inbtwn(pageRss, "thumbnail_src>", "<");
                            mainWindow.eval("kv_info('" + title3 + "', 'blip.tv', '" + uParam + "', '" + imgsrc3 + "');");
                            final String[] arr$2;
                            final String[] file_arr2 = arr$2 = pageRss.split("media:content url=");
                            for (final String file_a2 : arr$2) {
                                if (file_a2.contains("blip:role")) {
                                    final String[] arr3 = file_a2.split("\"");
                                    final String[] arr4 = arr3[1].split("\"");
                                    final String file6 = arr4[0];
                                    System.out.println(file6);
                                    final String res = this.inbtwn(file_a2, "height=\"", "\"");
                                    if (file6.contains(".f4v")) {
                                        mainWindow.eval("kv_ds('bt_f4v', 'F4V', '" + res + "p', '" + file6 + "', '');");
                                    }
                                    if (file6.contains(".flv")) {
                                        mainWindow.eval("kv_ds('bt_flv', 'FLV', '" + res + "p', '" + file6 + "', '');");
                                    }
                                    if (file6.contains(".m4v")) {
                                        mainWindow.eval("kv_ds('bt_m4v', 'M4V', '" + res + "p', '" + file6 + "', '');");
                                    }
                                    if (file6.contains(".mp4")) {
                                        mainWindow.eval("kv_ds('bt_mp4', 'MP4', '" + res + "p', '" + file6 + "', '');");
                                    }
                                    if (file6.contains(".mp3")) {
                                        mainWindow.eval("kv_ds('bt_mp3', 'MP3', 'Standard Quality', '" + file6 + "', '');");
                                    }
                                }
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("break.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "<h1>", "</h1>");
                            final String imgsrc4 = this.inbtwn(pageSource, "image\" content=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'break.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String fn = this.inbtwn(pageSource, "sGlobalFileName='", "'");
                            final String fp = this.inbtwn(pageSource, "sGlobalContentFilePath='", "'");
                            final String icon = this.inbtwn(pageSource, "flashVars.icon = \"", "\"");
                            final String file7 = fn;
                            mainWindow.eval("kv_ds('br_flv', 'FLV', 'Standard Quality', '" + file7 + ".flv?" + icon + "', '');");
                            mainWindow.eval("kv_ds('br_mp4', 'MP4', 'Standard Quality', '" + file7 + ".mp4?" + icon + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("videoweed.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String file8 = this.inbtwn(pageSource, ".file=\"", "\"");
                            mainWindow.eval("kv_ds('vw_flv', 'FLV', 'Standard Quality', '" + file8 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("watchme.tv")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String mid = this.inbtwn(pageSource, "mid=", "\"");
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
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String video_id = this.inbtwn(pageSource, "item-id\" content=\"", "\"");
                            final String feedSource = this.getUrlContent("http://www.veoh.com/rest/video/" + video_id + "/details", "GET");
                            final String title5 = this.inbtwn(pageSource, "title\" content=\"", "\"");
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
                            final String[] vid = uParam.split("/");
                            final String video_id = vid[6];
                            final String pageSource4 = this.getUrlContent("http://mediaservices.myspace.com/services/rss.ashx?type=video&videoID=" + video_id, "GET");
                            final String title5 = this.inbtwnmore(pageSource4, "<title>", "</title>", 2);
                            final String imgsrc5 = this.inbtwn(pageSource4, "thumbnail url=\"", "\"");
                            mainWindow.eval("kv_info('" + title5 + "', 'myspace.com', '" + uParam + "', '" + imgsrc5 + "');");
                            final String file = this.inbtwn(pageSource4, "content url=\"", "\"");
                            if (file.contains(".flv")) {
                                mainWindow.eval("kv_ds('ms_flv', 'FLV', 'High Quality', '" + file + "', '');");
                            }
                            if (file.contains(".mp4")) {
                                mainWindow.eval("kv_ds('ms_mp4', 'MP4', 'High Quality', '" + file + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("stagevu.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "name=\"movieTitle\" value=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "name=\"previewImage\" value=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'stagevu.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = this.inbtwn(pageSource, "name=\"src\" value=\"", "\"");
                            mainWindow.eval("kv_ds('sv_flv', 'AVI', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("rutube.ru")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'rutube.ru', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = URLDecoder.decode(this.inbtwn(pageSource, "&file=", "&"), "UTF-8");
                            mainWindow.eval("kv_ds('rt_flv', 'FLV', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("vbox7.com")) {
                        try {
                            final String video_id2 = this.inbtwn(uParam, "play:", "?");
                            final String xmlSource = this.getUrlContent("http://vbox7.com/etc/ext.do?key=" + video_id2, "GET");
                            String imgsrc4 = this.inbtwn(xmlSource, "jpg_addr=", "&");
                            if (imgsrc4 != null) {
                                imgsrc4 = "http://" + imgsrc4;
                            }
                            mainWindow.eval("kv_info('', 'vbox7.com', '" + uParam + "', '" + imgsrc4 + "');");
                            System.out.println(video_id2);
                            String file4 = this.inbtwn(xmlSource, "flv_addr=", "&");
                            if (file4 != null) {
                                file4 = "http://" + file4;
                            }
                            mainWindow.eval("kv_ds('vb_flv', 'FLV', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("5min.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "videoTitle\">", "<");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', '5min.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String file4 = URLDecoder.decode(this.inbtwn(pageSource, "videoUrl=", "&"), "UTF-8");
                            mainWindow.eval("kv_ds('m5_flv', 'FLV', 'High Quality', '" + file4 + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("collegehumor.com")) {
                        try {
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image\" content=\"", "\"");
                            final String vid2 = this.inbtwn(pageSource, "current_id: \"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'collegehumor.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String pageXml = this.getUrlContent("http://www.collegehumor.com/moogaloop/video/" + vid2, "GET");
                            final String file = this.inbtwn(pageXml, Pattern.quote("<file><![CDATA["), Pattern.quote("]]></file>"));
                            mainWindow.eval("kv_ds('ch_mp4', 'MP4', 'High Quality', '" + file + "', '');");
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("4shared.com")) {
                        try {
                            uParam = uParam.replace("/get/", "/video/");
                            final String pageSource = this.getUrlContent(uParam, "GET");
                            final String title4 = this.inbtwn(pageSource, "title\" content=\"", "\"");
                            final String imgsrc4 = this.inbtwn(pageSource, "image_src\" href=\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', '4shared.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String flv = this.inbtwn(pageSource, "flvLink = '", "'");
                            final String mp4 = this.inbtwn(pageSource, "mp4Link = '", "'");
                            final String ogg = this.inbtwn(pageSource, "oggLink = '", "'");
                            if (flv.contains(".flv")) {
                                mainWindow.eval("kv_ds('s4_flv', 'FLV', 'High Quality', '" + flv + "', '');");
                            }
                            if (mp4.contains(".mp4")) {
                                mainWindow.eval("kv_ds('s4_mp4', 'MP4', 'High Quality', '" + mp4 + "', '');");
                            }
                            else if (flv.contains(".flv")) {
                                mainWindow.eval("kv_ds('s4_mp4', 'MP4', 'High Quality', '" + flv.replace(".flv", ".mp4") + "', '');");
                            }
                            if (flv.contains(".mp3")) {
                                mainWindow.eval("kv_ds('s4_mp3', 'MP3', 'High Quality', '" + flv + "', '');");
                            }
                            if (ogg.contains(".ogg")) {
                                mainWindow.eval("kv_ds('s4_ogg', 'OGG', 'High Quality', '" + ogg + "', '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    if (uParam.contains("videobb.com")) {
                        try {
                            final String pageSource = this.getUrlContent("http://www.videobb.com/player_control/settings.php?v=" + this.inbtwn(uParam, "video/", "?"), "GET");
                            final String title4 = URLDecoder.decode(this.inbtwn(pageSource, "&title=", "&"), "UTF-8");
                            final String imgsrc4 = this.inbtwn(pageSource, "thumbnail\":\"", "\"");
                            mainWindow.eval("kv_info('" + title4 + "', 'videobb.com', '" + uParam + "', '" + imgsrc4 + "');");
                            final String p2 = this.inbtwn(pageSource, "\"240p\",\"u\":\"", "\"");
                            final String p3 = this.inbtwn(pageSource, "\"480p\",\"u\":\"", "\"");
                            if (p2 != "") {
                                mainWindow.eval("kv_ds('vb6_flv', 'FLV', '240p', d64('" + p2 + "'), '');");
                            }
                            if (p3 != "") {
                                mainWindow.eval("kv_ds('vb6_flv2', 'FLV', '480p', d64('" + p3 + "'), '');");
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        return;
                    }
                    mainWindow.eval("kv_error('Sorry, could not fetch download links on this page.')");
                    return;
                }
            }
            try {
                if (uParam.contains("youtu.be/")) {
                    uParam = uParam.replace("youtu.be/", "www.youtube.com/watch?v=").toString();
                }
                String pageSource = this.getUrlContent(uParam + "&fmt=18", "GET");
                String video_id = this.inbtwn(pageSource, "shortlink\" href=\"http://youtu.be/", "\"");
                if (video_id == null) {
                    video_id = this.inbtwn(pageSource, "'VIDEO_ID': \"", "\"");
                }
                if (video_id == null) {
                    System.out.println("Trying Alternate Method...");
                    video_id = this.inbtwn(uParam, "v=", "&");
                    pageSource = this.getUrlContent("http://www.youtube.com/get_video_info?video_id=" + video_id + "&asv=3&el=detailpage&hl=en_US", "GET");
                }
                System.out.println("Getting Title...");
                String title2 = this.inbtwn(pageSource, "'VIDEO_TITLE': '", "',");
                if (title2 == null) {
                    title2 = this.inbtwn(pageSource, "name=\"title\" content=\"", "\"");
                }
                if (title2 == null) {
                    title2 = URLDecoder.decode(this.inbtwn(pageSource, "&title=", "&"), "UTF-8").replace("+", " ");
                }
                try {
                    title2 = this.setHTMLEntity(title2);
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
                System.out.println("Title: " + title2 + "\r\n");
                mainWindow.eval("kv_info('" + title2 + "', 'youtube.com', 'http://www.youtube.com/watch?v=" + video_id + "', 'http://i.ytimg.com/vi/" + video_id + "/default.jpg');");
                String fmt_url_map = "";
                fmt_url_map = URLDecoder.decode(this.inbtwn(pageSource, "fmt_stream_map=", "&"), "UTF-8");
                if (fmt_url_map == null) {
                    fmt_url_map = this.inbtwn(pageSource, "fmt_stream_map\": \"", "\"").replace("\\/", "/");
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
                String dl_webm = null;
                String dl_webmhd = null;
                String dl_webmhd2 = null;
                final String[] arr$3;
                final String[] fmt_arr = arr$3 = fmt_url_map.split("url=");
                for (final String fmt : arr$3) {
                    final String[] parts = fmt.split(Pattern.quote("&qual"));
                    final String m_url = URLDecoder.decode(parts[0], "UTF-8");
                    if (m_url.contains("itag=13")) {
                        dl_3gplow = m_url;
                    }
                    if (m_url.contains("itag=17")) {
                        dl_3gpmed = m_url;
                    }
                    if (m_url.contains("itag=36")) {
                        dl_3gphigh = m_url;
                    }
                    if (m_url.contains("itag=5")) {
                        dl_flvlow = m_url;
                    }
                    if (m_url.contains("itag=34")) {
                        dl_flvmed = m_url;
                    }
                    if (m_url.contains("itag=6")) {
                        dl_flvmed2 = m_url;
                    }
                    if (m_url.contains("itag=35")) {
                        dl_flvhigh = m_url;
                    }
                    if (m_url.contains("itag=18")) {
                        dl_mp4high = m_url;
                    }
                    if (m_url.contains("itag=22")) {
                        dl_mp4hd = m_url;
                    }
                    if (m_url.contains("itag=37")) {
                        dl_mp4hd2 = m_url;
                    }
                    if (m_url.contains("itag=38")) {
                        dl_mp4hd3 = m_url;
                    }
                    if (m_url.contains("itag=43")) {
                        dl_webm = m_url;
                    }
                    if (m_url.contains("itag=44")) {
                        dl_webmhd = m_url;
                    }
                    if (m_url.contains("itag=45")) {
                        dl_webmhd2 = m_url;
                    }
                    System.out.println("URL: " + m_url);
                }
                if (error == null) {
                    if (dl_flvlow != null) {
                        mainWindow.eval("kv_ds('dl_flvlow', 'FLV', '240p', '" + dl_flvlow + "', '" + title2 + "');");
                    }
                    if (dl_flvmed2 != null) {
                        mainWindow.eval("kv_ds('dl_flvmed2', 'FLV', '360p', '" + dl_flvmed2 + "', '" + title2 + "');");
                    }
                    if (dl_flvmed != null) {
                        mainWindow.eval("kv_ds('dl_flvmed', 'FLV', '360p', '" + dl_flvmed + "', '" + title2 + "');");
                    }
                    if (dl_flvhigh != null) {
                        mainWindow.eval("kv_ds('dl_flvhigh', 'FLV', '480p', '" + dl_flvhigh + "', '" + title2 + "');");
                    }
                    if (dl_mp4high != null) {
                        mainWindow.eval("kv_ds('dl_mp4high', 'MP4', '(Max 480p)', '" + dl_mp4high + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd', 'MP4', '720p', '" + dl_mp4hd + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd2 != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd2', 'MP4', '1080p', '" + dl_mp4hd2 + "', '" + title2 + "');");
                    }
                    if (dl_mp4hd3 != null) {
                        mainWindow.eval("kv_ds('dl_mp4hd3', 'MP4', '(Original)', '" + dl_mp4hd3 + "', '" + title2 + "');");
                    }
                    if (dl_webm != null) {
                        mainWindow.eval("kv_ds('dl_webm', 'WebM', '360p', '" + dl_webm + "', '" + title2 + "');");
                    }
                    if (dl_webmhd != null) {
                        mainWindow.eval("kv_ds('dl_webmhd', 'WebM', '480p', '" + dl_webmhd + "', '" + title2 + "');");
                    }
                    if (dl_webmhd2 != null) {
                        mainWindow.eval("kv_ds('dl_webmhd2', 'WebM', '720p', '" + dl_webmhd2 + "', '" + title2 + "');");
                    }
                    try {
                        String pageMob = this.getUrlContent("http://m.youtube.com/watch?ajax=1&layout=mobile&tsp=1&v=" + video_id, "GET");
                        final String[] splmini = pageMob.split("\"related_videos\":");
                        pageMob = splmini[0];
                        dl_3gphigh = this.inbtwn(pageMob, "\"stream_url\": \"", "\"").replace("\\/", "/");
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                    if (dl_3gplow != null) {
                        mainWindow.eval("kv_ds('dl_3gplow', '3GP', '144p', '" + dl_3gplow + "', '" + title2 + "');");
                    }
                    if (dl_3gpmed != null) {
                        mainWindow.eval("kv_ds('dl_3gpmed', '3GP', '144p', '" + dl_3gpmed + "', '" + title2 + "');");
                    }
                    if (dl_3gphigh != null) {
                        mainWindow.eval("kv_ds('dl_3gphigh', '3GP', '240p', '" + dl_3gphigh + "', '" + title2 + "');");
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
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
    }
    
    private String getUrlContent(final String url, final String httpMethod) {
        try {
            final URL u = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setRequestMethod(httpMethod);
            if (url.contains("layout=mobile")) {
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
            }
            else {
                conn.setRequestProperty("User-Agent", this.getParameter("ua"));
            }
            if (url.contains("dailymotion.com")) {
                conn.setRequestProperty("Cookie", "family_filter=off;");
            }
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
            output = input.replace("&amp;", "").toString().replace("&lt;", "").toString().replace("&gt;", "").toString().replace("&#39;", "").toString().replace("&quot;", "").toString().replace("&", "").toString().replace("amp;", "").toString().replace("\\\"", "").toString().replace("\\'", "").toString().replace("'", "").toString().replace("'", "").toString().replace("<", "").toString().replace(">", "").toString().replace("?", "").toString().replace("/", "").toString().replace(":", "").toString().replace(";", "").toString().replace("#", "").toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return input;
        }
        return output;
    }
}
