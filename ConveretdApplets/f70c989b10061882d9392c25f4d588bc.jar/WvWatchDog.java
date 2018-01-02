import java.net.URLConnection;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class WvWatchDog extends WvHttpTalker
{
    private static long seq;
    
    protected String getThreadName() {
        return "LiveApplet-WatchDog";
    }
    
    public WvWatchDog(final WvDispatcher wvdispatcher) {
        super(wvdispatcher);
    }
    
    private void parseStatusHeader(final int i) throws Exception {
        switch (i) {
            case 201:
            case 204:
            case 211:
            case 301:
            case 302:
            case 303: {
                super.wvDispatcher.postEvent(new WvEvent(i));
            }
            case 101:
            case 102:
            case 202:
            case 203:
            case 205:
            case 207:
            case 208:
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 505:
            case 506:
            case 507:
            case 601:
            case 602: {
                super.wvDispatcher.stopAll(i);
            }
            default: {
                throw new Exception("unknown Livescope-Status:" + i);
            }
            case 212:
            case 404: {}
        }
    }
    
    private boolean parseNoticeHeader(final int i) throws Exception {
        switch (i) {
            case 11:
            case 14:
            case 15:
            case 16:
            case 21:
            case 22:
            case 32:
            case 34:
            case 46:
            case 209: {
                super.wvDispatcher.postEvent(new WvEvent(i));
                break;
            }
            case 12:
            case 13:
            case 23: {
                super.wvDispatcher.stopAll(i);
                break;
            }
            case 31:
            case 33:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 61: {
                return true;
            }
            default: {
                throw new Exception("unknown Livescope-Notice:" + i);
            }
        }
        return false;
    }
    
    public void run() {
        final Object obj = null;
        int i = 0;
        int j = 0;
        boolean flag = false;
        boolean flag2 = false;
        while (super.wvDispatcher.getRunnable()) {
            Thread.yield();
            try {
                String s2 = String.valueOf(super.wvDispatcher.getUrlBaseStr()) + "GetNotice".trim() + "?timeout=1800&" + super.idStr;
                if (flag2) {
                    s2 = String.valueOf(s2) + "&option=retry";
                }
                else {
                    ++WvWatchDog.seq;
                }
                s2 = String.valueOf(s2) + "&seq=" + WvWatchDog.seq;
                WvDebug.println("WatchDog:" + s2);
                final URL url = new URL(s2);
                final URLConnection urlconnection = url.openConnection();
                urlconnection.setUseCaches(false);
                urlconnection.setDefaultUseCaches(false);
                final DataInputStream datainputstream = new DataInputStream(urlconnection.getInputStream());
                final String s3 = urlconnection.getHeaderField("Livescope-Status");
                final String s4 = urlconnection.getHeaderField("Livescope-Notice");
                if (s3 != null) {
                    final StringTokenizer stringtokenizer = new StringTokenizer(s3);
                    i = Integer.parseInt(stringtokenizer.nextToken());
                    WvDebug.println("WatchDog:Livescope-Status :" + s3 + " seq=" + WvWatchDog.seq + " " + super.idStr);
                    if (i != 0) {
                        this.parseStatusHeader(i);
                    }
                }
                if (s4 != null) {
                    final StringTokenizer stringtokenizer2 = new StringTokenizer(s4);
                    i = Integer.parseInt(stringtokenizer2.nextToken());
                    WvDebug.println("WatchDog:Livescope-Notice :" + s4 + " seq=" + WvWatchDog.seq + " " + super.idStr);
                    if (i != 0) {
                        flag = this.parseNoticeHeader(i);
                    }
                }
                if (flag) {
                    final StringBuffer stringbuffer = new StringBuffer();
                    String s5 = null;
                    while ((s5 = datainputstream.readLine()) != null) {
                        stringbuffer.append(s5).append("\n");
                    }
                    datainputstream.close();
                    this.parseContent(i, new String(stringbuffer));
                }
                flag2 = false;
                j = 0;
            }
            catch (Exception exception) {
                flag2 = true;
                if (exception instanceof IOException) {
                    final String s6 = super.wvDispatcher.syncSendCommand("GetVideoInfo");
                    if (s6 == null) {
                        super.wvDispatcher.stopAll();
                    }
                }
                else {
                    if (++j == 3) {
                        exception.printStackTrace();
                        super.wvDispatcher.stopAll();
                    }
                    if (j > 5) {
                        super.wvDispatcher.setRunnable(false);
                        return;
                    }
                }
                this.sleepWhile(3000L);
                WvDebug.println("WatchDog:run :" + exception);
            }
        }
    }
    
    private void parseContent(final int i, final String s) {
        WvEvent wvevent = null;
        WvDebug.println("WvWatchDog : parseContent() Called!! code = [" + i + "], content = [" + s + "]");
        switch (i) {
            case 41:
            case 42: {
                final StringTokenizer stringtokenizer = new StringTokenizer(s, "=\r\n");
                stringtokenizer.nextElement();
                final WvPTZB wvptzb = new WvPTZB();
                while (stringtokenizer.hasMoreTokens()) {
                    wvptzb.parseProperty(stringtokenizer.nextToken(), stringtokenizer.nextToken());
                }
                wvevent = new WvEvent(i, wvptzb);
                break;
            }
            case 31:
            case 33:
            case 44: {
                final StringTokenizer stringtokenizer2 = new StringTokenizer(s, "=\r\n");
                stringtokenizer2.nextElement();
                stringtokenizer2.nextElement();
                try {
                    final int j = Integer.parseInt((String)stringtokenizer2.nextElement());
                    wvevent = new WvEvent(i, j);
                }
                catch (NumberFormatException numberformatexception) {
                    WvDebug.println("WatchDog:parseContent:" + numberformatexception);
                }
                break;
            }
            case 43:
            case 45: {
                wvevent = new WvEvent(i, s);
                break;
            }
            case 61: {
                String s2 = null;
                String s3 = null;
                final StringTokenizer stringtokenizer3 = new StringTokenizer(s, "=\r\n");
                while (stringtokenizer3.hasMoreTokens()) {
                    final String s4 = stringtokenizer3.nextToken();
                    if (s4.equals("image_width")) {
                        s2 = stringtokenizer3.nextToken();
                    }
                    if (s4.equals("image_height")) {
                        s3 = stringtokenizer3.nextToken();
                    }
                }
                wvevent = new WvEvent(i, String.valueOf(s2) + "x" + s3);
                break;
            }
            default: {
                return;
            }
        }
        super.wvDispatcher.postEvent(wvevent);
    }
}
