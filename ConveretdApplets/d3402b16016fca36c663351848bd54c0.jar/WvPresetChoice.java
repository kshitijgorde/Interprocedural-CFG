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
    
    public void enabledCameraControl(final int n) {
        if (this.countItems() > 0) {
            this.enable();
        }
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean b) {
        if (b) {
            if (this.bEnableRemoveAll && this.countItems() > 0) {
                try {
                    this.getClass().getMethod("removeAll", (Class<?>[])null);
                    this.removeAll();
                    this.presetHash.clear();
                }
                catch (Exception ex) {
                    WvDebug.println(this + " " + ex);
                    this.bEnableRemoveAll = false;
                }
            }
            this.getPresetList();
        }
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    private void getPresetList() {
        final String syncSendCommand = this.wvDispatcher.syncSendCommand("GetPresetList".trim() + this.langAndCharset);
        if (syncSendCommand == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n");
        String nextToken = null;
        String s = null;
        while (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
            if (!nextToken.startsWith("position")) {
                continue;
            }
            break;
        }
        int n = 1;
        int int1 = 1;
        while (stringTokenizer.hasMoreTokens()) {
            final WvPTZB wvPTZB = new WvPTZB();
            do {
                final String s2 = "position_";
                final int n2 = nextToken.indexOf(s2) + s2.length();
                final int index = nextToken.indexOf("=");
                final String substring = nextToken.substring(0, index);
                final String substring2 = nextToken.substring(index + 1);
                if (nextToken.startsWith("position")) {
                    s = substring2;
                    int1 = Integer.parseInt(nextToken.substring(n2, index));
                }
                else {
                    wvPTZB.parseProperty(substring, substring2);
                }
                nextToken = (stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null);
            } while (nextToken != null && !nextToken.startsWith("position"));
            this.wvDispatcher.putObject("preset_name_" + int1, s);
            this.wvDispatcher.putObject("preset_data_" + int1, wvPTZB);
            ++n;
            if (this.countItems() == 0) {
                this.addItem(this.wvDispatcher.isEnglish() ? " - select camera presets -" : " - \u30d7\u30ea\u30bb\u30c3\u30c8\u3092\u9078\u629e\u3057\u3066\u4e0b\u3055\u3044 -");
            }
            this.addItem(s, wvPTZB);
        }
        this.wvDispatcher.putObject("preset_count", new Integer(n - 1));
        this.wvDispatcher.putObject("last_preset_id", new Integer(int1));
    }
    
    public void videoConnected(final boolean b) {
    }
    
    private void addItem(final String s, final Object o) {
        if (this.presetHash.get(s) == null) {
            this.presetHash.put(s, o);
            this.addItem(s);
        }
    }
    
    public void waitingCameraControl(final int n) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public WvPresetChoice(final WvDispatcher wvDispatcher) {
        this.presetHash = new Hashtable();
        this.wvDispatcher = wvDispatcher;
        if (wvDispatcher.isEnglish()) {
            this.langAndCharset = "?language=English&character_set=ascii";
        }
        else {
            this.langAndCharset = "?language=Japanese&character_set=unicode";
        }
        this.disable();
        this.bEnableRemoveAll = true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final WvPTZB value = this.presetHash.get(this.getSelectedItem());
            if (value == null) {
                return false;
            }
            if (value instanceof WvPTZB) {
                this.wvDispatcher.asyncStackCommand("OperateCamera".trim() + value.getPresetCommand());
                WvDebug.println("PresetChoice : action() postEvent = [" + value + "]");
                this.wvDispatcher.postEvent(new WvEvent(42, value));
            }
        }
        return false;
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
