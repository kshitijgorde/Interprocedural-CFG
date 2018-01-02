import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class WvAppletTemplate extends Applet
{
    protected WvDispatcher wvDispatcher;
    WvConnectCheck connectCheck;
    boolean connectFlag;
    
    public void connectTo(final String s) {
        final Object obj = null;
        final String s2 = this.getParameter("vc_host");
        int i = 0;
        try {
            final String s3 = this.getParameter("vc_port");
            if (s3 != null) {
                i = Integer.parseInt(s3);
            }
        }
        catch (Exception ex) {}
        final String s4 = this.getParameter("cc_host");
        int j = 0;
        try {
            final String s5 = this.getParameter("cc_port");
            if (s5 != null) {
                j = Integer.parseInt(s5);
            }
        }
        catch (Exception ex2) {}
        this.connectVia(s, s2, i, s4, j);
    }
    
    public String getPanLeftLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMinValue(0, 1) / 100.0;
        return Double.toString(d);
    }
    
    public String getPanRightLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMaxValue(0, 1) / 100.0;
        return Double.toString(d);
    }
    
    public String getTiltDownLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMinValue(1, 1) / 100.0;
        return Double.toString(d);
    }
    
    public int wideLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMaxValue(2, 1);
    }
    
    public String getZoomTeleLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMinValue(2, 1) / 100;
        return Double.toString(d);
    }
    
    public int selectPreset(final int i) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        final Object obj = this.wvDispatcher.getObject("preset_data_" + i);
        if (obj == null || !(obj instanceof WvPTZB)) {
            return -1;
        }
        final String s = ((WvPTZB)obj).getPresetCommand();
        this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + s);
        return 0;
    }
    
    public void appletStart() {
        this.stop();
        this.start();
    }
    
    public int lastPreset() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("last_preset_id");
    }
    
    public int maxVideoWidth() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("max_image_width");
    }
    
    public void disconnect() {
        try {
            this.wvDispatcher.stopAll();
            this.postStop();
            this.wvDispatcher.closeWait();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        System.gc();
    }
    
    public int videoHeight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("image_height");
    }
    
    public int zoom() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getPtzValue(2);
    }
    
    public int operateCamera(final String s) {
        if (!this.wvDispatcher.hasFloor()) {
            return 1;
        }
        this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + "?" + s);
        return 0;
    }
    
    public int currentCamera() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getCurrentCameraId();
    }
    
    public int tilt() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getPtzValue(1);
    }
    
    public int setPTZ(final int i, final int j, final int k) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setPanValue(i);
        this.wvDispatcher.setTiltValue(j);
        this.wvDispatcher.setZoomValue(k);
        return 0;
    }
    
    public int setCameraBackLight(final int i) {
        if (i != 0 && i != 1) {
            return 2;
        }
        int j = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setBackLight(i != 0);
            j = 0;
        }
        return j;
    }
    
    public int setBackLight(final int i) {
        if (!this.wvDispatcher.hasFloor() || (i != 0 && i != 1)) {
            return -1;
        }
        this.wvDispatcher.setBackLight(i != 1);
        return 0;
    }
    
    public String getCameraBackLight() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        if (this.wvDispatcher.getBackLight()) {
            return "on";
        }
        return "off";
    }
    
    public int cameraStatus() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.cameraEnabled() ? 1 : 0;
    }
    
    public int setCameraPos(final float f, final float f1, final float f2) {
        final int i = (int)(f * 100.0f);
        final int j = (int)(f1 * 100.0f);
        final int k = (int)(f2 * 100.0f);
        int l = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setPanValue(i);
            this.wvDispatcher.setTiltValue(j);
            this.wvDispatcher.setZoomValue(k);
            l = 0;
        }
        return l;
    }
    
    public int setCameraTilt(final float f) {
        final int i = (int)(f * 100.0f);
        int j = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setTiltValue(i);
            j = 0;
        }
        return j;
    }
    
    public int setTilt(final int i) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setTiltValue(i);
        return 0;
    }
    
    public String getCameraTilt() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getPtzValue(1) / 100.0;
        return Double.toString(d);
    }
    
    public String getCameraPos() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final String s = new String("Pan:" + this.getCameraPan() + " Tilt:" + this.getCameraTilt() + " Zoom:" + this.getCameraZoom());
        return s;
    }
    
    public int getCameraControlStatus() {
        return this.wvDispatcher.getCameraControlStatus();
    }
    
    public int connectionStatus() {
        return this.wvDispatcher.getStatus();
    }
    
    public int controlStatus() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.controlStatus();
    }
    
    public void destroy() {
        WvDebug.println("WvAppletTemplate destroy enter");
        this.connectCheck.stop();
        WvDebug.println("WvAppletTemplate destroy exit");
    }
    
    public String connectionId() {
        final String s = this.wvDispatcher.getIdStr();
        if (s == null) {
            return "";
        }
        final String s2 = s.substring(s.indexOf(61) + 1);
        return s2;
    }
    
    public int videoWidth() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("image_width");
    }
    
    public void connectVia(final String s, final String s1, final int i, final String s2, final int j) {
        WvDebug.println("Connect Start! (" + s + ")");
        if (this.wvDispatcher.getWvCameraInfo().connected()) {
            this.disconnect();
        }
        this.wvDispatcher.getWvCameraInfo().setCameraState(7);
        this.wvDispatcher.setStatus(0);
        if (s != null) {
            if (s.charAt(s.length() - 1) == '/') {
                this.wvDispatcher.setUrlBaseStr(String.valueOf(s) + "-wvhttp-01-/");
            }
            else {
                this.wvDispatcher.setUrlBaseStr(String.valueOf(s) + "/-wvhttp-01-/");
            }
        }
        final Object obj = this.wvDispatcher.getObject("version");
        String s3 = "unknown version";
        if (obj != null && obj instanceof String) {
            s3 = (String)obj;
        }
        WvDebug.println("Version: " + s3);
        this.wvDispatcher.start(s1, s2, i, j);
    }
    
    public int minVideoHeight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("min_image_height");
    }
    
    public void init() {
        final WvLocaleChecker wvlocalechecker = new WvLocaleChecker(this);
        String s;
        if ((s = this.getParameter("debug")) != null && s.equalsIgnoreCase("on")) {
            WvDebug.set(true);
        }
        if ((s = this.getParameter("bg_color")) != null) {
            try {
                final int i = Integer.parseInt(s.substring(1, 3), 16);
                final int j = Integer.parseInt(s.substring(3, 5), 16);
                final int k = Integer.parseInt(s.substring(5, 7), 16);
                this.setBackground(new Color(i, j, k));
            }
            catch (Exception ex) {}
        }
        String[] as = null;
        if ((s = this.getParameter("open_params")) != null) {
            final StringTokenizer stringtokenizer = new StringTokenizer(s);
            as = new String[stringtokenizer.countTokens()];
            int l = 0;
            while (stringtokenizer.hasMoreTokens()) {
                as[l] = stringtokenizer.nextToken();
                ++l;
            }
        }
        s = this.getParameter("url");
        if (s == null) {
            WvDebug.println("parameter url not specified");
            s = this.getCodeBase().toString();
            final int slash = s.indexOf("/", 9);
            if (slash != -1) {
                s = s.substring(0, slash + 1);
            }
            WvDebug.println("url set to " + s);
        }
        s = ((s.charAt(s.length() - 1) != '/') ? (String.valueOf(s) + "/-wvhttp-01-/") : (String.valueOf(s) + "-wvhttp-01-/"));
        this.wvDispatcher = new WvDispatcher(s, this, as, wvlocalechecker, wvlocalechecker.getFontName());
        this.postInit();
        this.connectCheck = new WvConnectCheck(this, this.wvDispatcher);
    }
    
    public int cameraCount() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("camera_count");
    }
    
    public int selectCamera(final int i) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        final String s = "?camera_id=" + i;
        this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + s);
        return 0;
    }
    
    public int lastCamera() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("last_camera_id");
    }
    
    public void connect() {
        this.connectTo(null);
    }
    
    public void stop() {
        WvDebug.println("WvAppletTemplate stop enter");
        this.wvDispatcher.setRunnable(false);
        System.gc();
        this.disconnect();
        WvDebug.println("WvAppletTemplate stop exit");
    }
    
    public int setVideoSize(final int i, int j) {
        if (j != i * 3 / 4) {
            j = i * 3 / 4;
        }
        final String s = "?image_size=" + i + 'x' + j;
        this.wvDispatcher.asyncStackCommand(String.valueOf("ChangeImageSize".trim()) + s);
        this.wvDispatcher.putObject("image_size", "image_size=" + s);
        return 0;
    }
    
    public void getCameraControl() {
        final String s = "GetCameraControl";
        this.wvDispatcher.asyncStackCommand(s);
    }
    
    abstract void postInit();
    
    public String getVcHost() {
        return this.wvDispatcher.getVcHostStr();
    }
    
    public int setCameraPan(final float f) {
        final int i = (int)(f * 100.0f);
        int j = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setPanValue(i);
            j = 0;
        }
        return j;
    }
    
    public int setPan(final int i) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        WvDebug.println(this + "pan value = " + i);
        this.wvDispatcher.setPanValue(i);
        return 0;
    }
    
    public String getCameraPan() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getPtzValue(0) / 100.0;
        return Double.toString(d);
    }
    
    public int leftLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMinValue(0, 1);
    }
    
    public int downLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMinValue(1, 1);
    }
    
    public String getZoomWideLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMaxValue(2, 1) / 100.0;
        return Double.toString(d);
    }
    
    public int teleLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMinValue(2, 1);
    }
    
    public void start() {
        WvDebug.println("WvAppletTemplate start");
        if (this.wvDispatcher.getWvCameraInfo().connected()) {
            this.disconnect();
        }
        this.wvDispatcher.getWvCameraInfo().setCameraState(7);
        this.wvDispatcher.setStatus(0);
        final Object obj = this.wvDispatcher.getObject("auto_connect");
        if (obj != null && obj instanceof Boolean && !(boolean)obj) {
            this.wvDispatcher.getWvVideoState().init();
            return;
        }
        final Object obj2 = null;
        final String s2 = this.getParameter("vc_host");
        int i = 0;
        try {
            final String s3 = this.getParameter("vc_port");
            if (s3 != null) {
                i = Integer.parseInt(s3);
            }
        }
        catch (Exception ex) {}
        final String s4 = this.getParameter("cc_host");
        int j = 0;
        try {
            final String s5 = this.getParameter("cc_port");
            if (s5 != null) {
                j = Integer.parseInt(s5);
            }
        }
        catch (Exception ex2) {}
        this.wvDispatcher.autostart(s2, s4, i, j);
    }
    
    public void appletHostRestart(final String s, final String s1, final String s2, final String s3) {
        this.stop();
        this.wvDispatcher.putObject("hostname", s2);
        this.wvDispatcher.putObject("comment", s3);
        this.wvDispatcher.start(s, s1, 65310, 65311);
    }
    
    public String getCameraName(final int i) {
        if (!this.wvDispatcher.connected()) {
            return "";
        }
        final int j = (i != 0) ? i : this.currentCamera();
        final String s = this.wvDispatcher.getStringObject("camera_name_" + j);
        if (s == null) {
            return "";
        }
        return s;
    }
    
    public int rightLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMaxValue(0, 1);
    }
    
    public String getTiltUpLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getMaxValue(1, 1) / 100.0;
        return Double.toString(d);
    }
    
    public int upLimit() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getMaxValue(1, 1);
    }
    
    public int minVideoWidth() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("min_image_width");
    }
    
    public int setCameraZoom(final float f) {
        final int i = (int)(f * 100.0f);
        int j = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setZoomValue(i);
            j = 0;
        }
        return j;
    }
    
    public int setZoom(final int i) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setZoomValue(i);
        return 0;
    }
    
    public String getCameraZoom() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        final double d = this.wvDispatcher.getPtzValue(2) / 100.0;
        return Double.toString(d);
    }
    
    public int backlight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getBackLight() ? 1 : 0;
    }
    
    public int maxVideoHeight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("max_image_height");
    }
    
    public int presetCount() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("preset_count");
    }
    
    abstract void postStop();
    
    public void appletStop() {
        this.stop();
    }
    
    public String getCcHost() {
        return this.wvDispatcher.getCcHostStr();
    }
    
    public int pan() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getPtzValue(0);
    }
    
    public void appletHostPortRestart(final String s, final String s1, final int i, final int j, final String s2, final String s3) {
        this.stop();
        this.wvDispatcher.putObject("hostname", s2);
        this.wvDispatcher.putObject("comment", s3);
        this.wvDispatcher.start(s, s1, i, j);
    }
    
    public String getPresetName(final int i) {
        if (!this.wvDispatcher.connected()) {
            return "";
        }
        final String s = this.wvDispatcher.getStringObject("preset_name_" + i);
        if (s == null) {
            return "";
        }
        return s;
    }
}
