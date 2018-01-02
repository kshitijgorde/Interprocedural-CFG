import java.awt.Event;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvPresetChoice extends Choice implements WvEventListener, WvFloorListener
{
    private WvDispatcher wvDispatcher;
    private Hashtable presetHash;
    private String langAndCharset;
    private boolean bEnableRemoveAll;
    
    public void kickOff() {
        this.disable();
    }
    
    public void enabledCameraControl(final int i) {
        if (this.countItems() > 0) {
            this.enable();
        }
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
        if (flag) {
            if (this.bEnableRemoveAll && this.countItems() > 0) {
                try {
                    this.getClass().getMethod("removeAll", (Class<?>[])null);
                    this.removeAll();
                    this.presetHash.clear();
                }
                catch (Exception exception) {
                    WvDebug.println(this + " " + exception);
                    this.bEnableRemoveAll = false;
                }
            }
            this.getPresetList();
        }
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    private void getPresetList() {
        final String s = String.valueOf("GetPresetList".trim()) + this.langAndCharset;
        final String s2 = this.wvDispatcher.syncSendCommand(s);
        if (s2 == null) {
            return;
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s2, "\r\n");
        String s3 = null;
        final Object obj = null;
        final Object obj2 = null;
        String s4 = null;
        while (stringtokenizer.hasMoreTokens()) {
            s3 = stringtokenizer.nextToken();
            if (s3.startsWith("position")) {
                break;
            }
        }
        int i = 1;
        int j = 1;
        while (stringtokenizer.hasMoreTokens()) {
            final WvPTZB wvptzb = new WvPTZB();
            do {
                final String s5 = "position_";
                final int k = s3.indexOf(s5) + s5.length();
                final int l = s3.indexOf("=");
                final String s6 = s3.substring(0, l);
                final String s7 = s3.substring(l + 1);
                if (s3.startsWith("position")) {
                    s4 = s7;
                    j = Integer.parseInt(s3.substring(k, l));
                }
                else {
                    wvptzb.parseProperty(s6, s7);
                }
                s3 = (stringtokenizer.hasMoreTokens() ? stringtokenizer.nextToken() : null);
            } while (s3 != null && !s3.startsWith("position"));
            this.wvDispatcher.putObject("preset_name_" + j, s4);
            this.wvDispatcher.putObject("preset_data_" + j, wvptzb);
            ++i;
            if (this.countItems() == 0) {
                final String s8 = this.wvDispatcher.isEnglish() ? " - select camera presets -" : " - \u30d7\u30ea\u30bb\u30c3\u30c8\u3092\u9078\u629e\u3057\u3066\u4e0b\u3055\u3044 -";
                this.addItem(s8);
            }
            this.addItem(s4, wvptzb);
        }
        this.wvDispatcher.putObject("preset_count", new Integer(i - 1));
        this.wvDispatcher.putObject("last_preset_id", new Integer(j));
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    private void addItem(final String s, final Object obj) {
        if (this.presetHash.get(s) == null) {
            this.presetHash.put(s, obj);
            this.addItem(s);
        }
    }
    
    public void waitingCameraControl(final int i) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public WvPresetChoice(final WvDispatcher wvdispatcher) {
        this.presetHash = new Hashtable();
        this.wvDispatcher = wvdispatcher;
        if (wvdispatcher.isEnglish()) {
            this.langAndCharset = "?language=English&character_set=ascii";
        }
        else {
            this.langAndCharset = "?language=Japanese&character_set=unicode";
        }
        this.disable();
        this.bEnableRemoveAll = true;
    }
    
    public boolean action(final Event event, final Object obj) {
        if (event.target instanceof Choice) {
            final String s = this.getSelectedItem();
            final Object obj2 = this.presetHash.get(s);
            if (obj2 == null) {
                return false;
            }
            final Object obj3 = null;
            if (obj2 instanceof WvPTZB) {
                final WvPTZB wvptzb = (WvPTZB)obj2;
                final String s2 = wvptzb.getPresetCommand();
                this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + s2);
                WvDebug.println("PresetChoice : action() postEvent = [" + obj2 + "]");
                this.wvDispatcher.postEvent(new WvEvent(42, obj2));
            }
        }
        return false;
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
