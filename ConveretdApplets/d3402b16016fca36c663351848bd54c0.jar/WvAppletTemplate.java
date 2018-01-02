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
        final String parameter = this.getParameter("vc_host");
        int int1 = 0;
        try {
            final String parameter2 = this.getParameter("vc_port");
            if (parameter2 != null) {
                int1 = Integer.parseInt(parameter2);
            }
        }
        catch (Exception ex) {}
        final String parameter3 = this.getParameter("cc_host");
        int int2 = 0;
        try {
            final String parameter4 = this.getParameter("cc_port");
            if (parameter4 != null) {
                int2 = Integer.parseInt(parameter4);
            }
        }
        catch (Exception ex2) {}
        this.connectVia(s, parameter, int1, parameter3, int2);
    }
    
    public String getPanLeftLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getMinValue(0, 1) / 100.0);
    }
    
    public String getPanRightLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getMaxValue(0, 1) / 100.0);
    }
    
    public String getTiltDownLimit() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getMinValue(1, 1) / 100.0);
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
        return Double.toString(this.wvDispatcher.getMinValue(2, 1) / 100);
    }
    
    public int selectPreset(final int n) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        final Object object = this.wvDispatcher.getObject("preset_data_" + n);
        if (object == null || !(object instanceof WvPTZB)) {
            return -1;
        }
        this.wvDispatcher.asyncStackCommand("OperateCamera".trim() + ((WvPTZB)object).getPresetCommand());
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
        catch (Exception ex) {
            ex.printStackTrace();
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
        this.wvDispatcher.asyncStackCommand("OperateCamera".trim() + "?" + s);
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
    
    public int setPTZ(final int panValue, final int tiltValue, final int zoomValue) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setPanValue(panValue);
        this.wvDispatcher.setTiltValue(tiltValue);
        this.wvDispatcher.setZoomValue(zoomValue);
        return 0;
    }
    
    public int setCameraBackLight(final int n) {
        if (n != 0 && n != 1) {
            return 2;
        }
        int n2 = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setBackLight(n != 0);
            n2 = 0;
        }
        return n2;
    }
    
    public int setBackLight(final int n) {
        if (!this.wvDispatcher.hasFloor() || (n != 0 && n != 1)) {
            return -1;
        }
        this.wvDispatcher.setBackLight(n != 1);
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
        if (this.wvDispatcher.cameraEnabled()) {
            return 1;
        }
        return 0;
    }
    
    public int setCameraPos(final float n, final float n2, final float n3) {
        final int panValue = (int)(n * 100.0f);
        final int tiltValue = (int)(n2 * 100.0f);
        final int zoomValue = (int)(n3 * 100.0f);
        int n4 = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setPanValue(panValue);
            this.wvDispatcher.setTiltValue(tiltValue);
            this.wvDispatcher.setZoomValue(zoomValue);
            n4 = 0;
        }
        return n4;
    }
    
    public int setCameraTilt(final float n) {
        final int tiltValue = (int)(n * 100.0f);
        int n2 = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setTiltValue(tiltValue);
            n2 = 0;
        }
        return n2;
    }
    
    public int setTilt(final int tiltValue) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setTiltValue(tiltValue);
        return 0;
    }
    
    public String getCameraTilt() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getPtzValue(1) / 100.0);
    }
    
    public String getCameraPos() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return new String("Pan:" + this.getCameraPan() + " Tilt:" + this.getCameraTilt() + " Zoom:" + this.getCameraZoom());
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
        final String idStr = this.wvDispatcher.getIdStr();
        if (idStr == null) {
            return "";
        }
        return idStr.substring(idStr.indexOf(61) + 1);
    }
    
    public int videoWidth() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("image_width");
    }
    
    public void connectVia(final String s, final String s2, final int n, final String s3, final int n2) {
        WvDebug.println("Connect Start! (" + s + ")");
        if (this.wvDispatcher.getWvCameraInfo().connected()) {
            this.disconnect();
        }
        this.wvDispatcher.getWvCameraInfo().setCameraState(7);
        this.wvDispatcher.setStatus(0);
        if (s != null) {
            if (s.charAt(s.length() - 1) == '/') {
                this.wvDispatcher.setUrlBaseStr(s + "-wvhttp-01-/");
            }
            else {
                this.wvDispatcher.setUrlBaseStr(s + "/-wvhttp-01-/");
            }
        }
        final Object object = this.wvDispatcher.getObject("version");
        String s4 = "unknown version";
        if (object != null && object instanceof String) {
            s4 = (String)object;
        }
        WvDebug.println("Version: " + s4);
        this.wvDispatcher.start(s2, s3, n, n2);
    }
    
    public int minVideoHeight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("min_image_height");
    }
    
    public void init() {
        final WvLocaleChecker wvLocaleChecker = new WvLocaleChecker(this);
        final String parameter;
        if ((parameter = this.getParameter("debug")) != null && parameter.equalsIgnoreCase("on")) {
            WvDebug.set(true);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("bg_color")) != null) {
            try {
                this.setBackground(new Color(Integer.parseInt(parameter2.substring(1, 3), 16), Integer.parseInt(parameter2.substring(3, 5), 16), Integer.parseInt(parameter2.substring(5, 7), 16)));
            }
            catch (Exception ex) {}
        }
        String[] array = null;
        final String parameter3;
        if ((parameter3 = this.getParameter("open_params")) != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter3);
            array = new String[stringTokenizer.countTokens()];
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = stringTokenizer.nextToken();
                ++n;
            }
        }
        String parameter4 = this.getParameter("url");
        if (parameter4 == null) {
            System.out.println("parameter url not specified");
        }
        else {
            parameter4 = ((parameter4.charAt(parameter4.length() - 1) == '/') ? (parameter4 + "-wvhttp-01-/") : (parameter4 + "/-wvhttp-01-/"));
        }
        this.wvDispatcher = new WvDispatcher(parameter4, this, array, wvLocaleChecker, wvLocaleChecker.getFontName());
        this.postInit();
        this.connectCheck = new WvConnectCheck(this, this.wvDispatcher);
    }
    
    public int cameraCount() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        return this.wvDispatcher.getIntegerObject("camera_count");
    }
    
    public int selectCamera(final int n) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.asyncStackCommand("OperateCamera".trim() + ("?camera_id=" + n));
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
    
    public int setVideoSize(final int n, int n2) {
        if (n2 != n * 3 / 4) {
            n2 = n * 3 / 4;
        }
        final String string = "?image_size=" + n + 'x' + n2;
        this.wvDispatcher.asyncStackCommand("ChangeImageSize".trim() + string);
        this.wvDispatcher.putObject("image_size", "image_size=" + string);
        return 0;
    }
    
    public void getCameraControl() {
        this.wvDispatcher.asyncStackCommand("GetCameraControl");
    }
    
    abstract void postInit();
    
    public String getVcHost() {
        return this.wvDispatcher.getVcHostStr();
    }
    
    public int setCameraPan(final float n) {
        final int panValue = (int)(n * 100.0f);
        int n2 = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setPanValue(panValue);
            n2 = 0;
        }
        return n2;
    }
    
    public int setPan(final int panValue) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        WvDebug.println(this + "pan value = " + panValue);
        this.wvDispatcher.setPanValue(panValue);
        return 0;
    }
    
    public String getCameraPan() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getPtzValue(0) / 100.0);
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
        return Double.toString(this.wvDispatcher.getMaxValue(2, 1) / 100.0);
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
        final Object object = this.wvDispatcher.getObject("auto_connect");
        if (object != null && object instanceof Boolean && !(boolean)object) {
            this.wvDispatcher.getWvVideoState().init();
            return;
        }
        final String parameter = this.getParameter("vc_host");
        int int1 = 0;
        try {
            final String parameter2 = this.getParameter("vc_port");
            if (parameter2 != null) {
                int1 = Integer.parseInt(parameter2);
            }
        }
        catch (Exception ex) {}
        final String parameter3 = this.getParameter("cc_host");
        int int2 = 0;
        try {
            final String parameter4 = this.getParameter("cc_port");
            if (parameter4 != null) {
                int2 = Integer.parseInt(parameter4);
            }
        }
        catch (Exception ex2) {}
        this.wvDispatcher.autostart(parameter, parameter3, int1, int2);
    }
    
    public void appletHostRestart(final String s, final String s2, final String s3, final String s4) {
        this.stop();
        this.wvDispatcher.putObject("hostname", s3);
        this.wvDispatcher.putObject("comment", s4);
        this.wvDispatcher.start(s, s2, 65310, 65311);
    }
    
    public String getCameraName(final int n) {
        if (!this.wvDispatcher.connected()) {
            return "";
        }
        final String stringObject = this.wvDispatcher.getStringObject("camera_name_" + ((n == 0) ? this.currentCamera() : n));
        if (stringObject == null) {
            return "";
        }
        return stringObject;
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
        return Double.toString(this.wvDispatcher.getMaxValue(1, 1) / 100.0);
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
    
    public int setCameraZoom(final float n) {
        final int zoomValue = (int)(n * 100.0f);
        int n2 = 1;
        if (this.wvDispatcher.hasFloor()) {
            this.wvDispatcher.setZoomValue(zoomValue);
            n2 = 0;
        }
        return n2;
    }
    
    public int setZoom(final int zoomValue) {
        if (!this.wvDispatcher.hasFloor()) {
            return -1;
        }
        this.wvDispatcher.setZoomValue(zoomValue);
        return 0;
    }
    
    public String getCameraZoom() {
        if (!this.wvDispatcher.connected()) {
            return "Unknown";
        }
        return Double.toString(this.wvDispatcher.getPtzValue(2) / 100.0);
    }
    
    public int backlight() {
        if (!this.wvDispatcher.connected()) {
            return Integer.MIN_VALUE;
        }
        if (this.wvDispatcher.getBackLight()) {
            return 1;
        }
        return 0;
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
    
    public void appletHostPortRestart(final String s, final String s2, final int n, final int n2, final String s3, final String s4) {
        this.stop();
        this.wvDispatcher.putObject("hostname", s3);
        this.wvDispatcher.putObject("comment", s4);
        this.wvDispatcher.start(s, s2, n, n2);
    }
    
    public String getPresetName(final int n) {
        if (!this.wvDispatcher.connected()) {
            return "";
        }
        final String stringObject = this.wvDispatcher.getStringObject("preset_name_" + n);
        if (stringObject == null) {
            return "";
        }
        return stringObject;
    }
}
