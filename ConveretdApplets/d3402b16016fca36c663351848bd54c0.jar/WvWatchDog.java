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
    
    public WvWatchDog(final WvDispatcher wvDispatcher) {
        super(wvDispatcher);
    }
    
    private void parseStatusHeader(final int n) throws Exception {
        switch (n) {
            case 201:
            case 204:
            case 211:
            case 301:
            case 302:
            case 303: {
                super.wvDispatcher.postEvent(new WvEvent(n));
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
                super.wvDispatcher.stopAll(n);
            }
            default: {
                throw new Exception("unknown Livescope-Status:" + n);
            }
            case 212:
            case 404: {}
        }
    }
    
    private boolean parseNoticeHeader(final int n) throws Exception {
        switch (n) {
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
                super.wvDispatcher.postEvent(new WvEvent(n));
                break;
            }
            case 12:
            case 13:
            case 23: {
                super.wvDispatcher.stopAll(n);
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
                throw new Exception("unknown Livescope-Notice:" + n);
            }
        }
        return false;
    }
    
    public void run() {
        int n = 0;
        int n2 = 0;
        boolean noticeHeader = false;
        int n3 = 0;
        while (super.wvDispatcher.getRunnable()) {
            Thread.yield();
            try {
                String s = super.wvDispatcher.getUrlBaseStr() + "GetNotice".trim() + "?timeout=1800&" + super.idStr;
                if (n3 != 0) {
                    s += "&option=retry";
                }
                else {
                    ++WvWatchDog.seq;
                }
                final String string = s + "&seq=" + WvWatchDog.seq;
                WvDebug.println("WatchDog:" + string);
                final URLConnection openConnection = new URL(string).openConnection();
                openConnection.setUseCaches(false);
                openConnection.setDefaultUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                final String headerField = openConnection.getHeaderField("Livescope-Status");
                final String headerField2 = openConnection.getHeaderField("Livescope-Notice");
                if (headerField != null) {
                    n = Integer.parseInt(new StringTokenizer(headerField).nextToken());
                    WvDebug.println("WatchDog:Livescope-Status :" + headerField + " seq=" + WvWatchDog.seq + " " + super.idStr);
                    if (n != 0) {
                        this.parseStatusHeader(n);
                    }
                }
                if (headerField2 != null) {
                    n = Integer.parseInt(new StringTokenizer(headerField2).nextToken());
                    WvDebug.println("WatchDog:Livescope-Notice :" + headerField2 + " seq=" + WvWatchDog.seq + " " + super.idStr);
                    if (n != 0) {
                        noticeHeader = this.parseNoticeHeader(n);
                    }
                }
                if (noticeHeader) {
                    final StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = dataInputStream.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    dataInputStream.close();
                    this.parseContent(n, new String(sb));
                }
                n3 = 0;
                n2 = 0;
            }
            catch (Exception ex) {
                n3 = 1;
                if (ex instanceof IOException) {
                    if (super.wvDispatcher.syncSendCommand("GetVideoInfo") == null) {
                        super.wvDispatcher.stopAll();
                    }
                }
                else {
                    if (++n2 == 3) {
                        ex.printStackTrace();
                        super.wvDispatcher.stopAll();
                    }
                    if (n2 > 5) {
                        super.wvDispatcher.setRunnable(false);
                        return;
                    }
                }
                this.sleepWhile(3000L);
                WvDebug.println("WatchDog:run :" + ex);
            }
        }
    }
    
    private void parseContent(final int n, final String s) {
        WvEvent wvEvent = null;
        WvDebug.println("WvWatchDog : parseContent() Called!! code = [" + n + "], content = [" + s + "]");
        switch (n) {
            case 41:
            case 42: {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "=\r\n");
                stringTokenizer.nextElement();
                final WvPTZB wvPTZB = new WvPTZB();
                while (stringTokenizer.hasMoreTokens()) {
                    wvPTZB.parseProperty(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                }
                wvEvent = new WvEvent(n, wvPTZB);
                break;
            }
            case 31:
            case 33:
            case 44: {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "=\r\n");
                stringTokenizer2.nextElement();
                stringTokenizer2.nextElement();
                try {
                    wvEvent = new WvEvent(n, Integer.parseInt((String)stringTokenizer2.nextElement()));
                }
                catch (NumberFormatException ex) {
                    WvDebug.println("WatchDog:parseContent:" + ex);
                }
                break;
            }
            case 43:
            case 45: {
                wvEvent = new WvEvent(n, s);
                break;
            }
            case 61: {
                String nextToken = null;
                String nextToken2 = null;
                final StringTokenizer stringTokenizer3 = new StringTokenizer(s, "=\r\n");
                while (stringTokenizer3.hasMoreTokens()) {
                    final String nextToken3 = stringTokenizer3.nextToken();
                    if (nextToken3.equals("image_width")) {
                        nextToken = stringTokenizer3.nextToken();
                    }
                    if (nextToken3.equals("image_height")) {
                        nextToken2 = stringTokenizer3.nextToken();
                    }
                }
                wvEvent = new WvEvent(n, nextToken + "x" + nextToken2);
                break;
            }
            default: {
                return;
            }
        }
        super.wvDispatcher.postEvent(wvEvent);
    }
}
