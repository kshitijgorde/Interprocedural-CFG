// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CParaStyle extends CDoubleLinkList
{
    private CParaStyleMgr cManager;
    private String cName;
    private CParaStyle cParent;
    private CFormatInfo cInfo;
    private CFormatInfo cEffectiveInfo;
    private CParaSettings cSettings;
    private CParaSettings cEffectiveSettings;
    private boolean cRebuilt;
    
    CParaStyle(final CParaStyleMgr cManager, final CParaStyle cParent, final String cName) {
        this.cManager = cManager;
        this.cName = cName;
        this.cParent = cParent;
        this.cInfo = new CFormatInfo();
        this.cSettings = new CParaSettings();
        this.rebuild(true);
        this.rebuild(this.cRebuilt = false);
    }
    
    final CFormatInfo getFormatInfo() {
        return this.cEffectiveInfo;
    }
    
    void apply(final CFormatInfo cFormatInfo) {
        cFormatInfo.apply(this.cInfo);
        this.cManager.update(this, true);
    }
    
    void setFormatInfo(final CFormatInfo cInfo) {
        this.cInfo = cInfo;
        this.cManager.update(this, true);
    }
    
    final CParaSettings getParaSettings() {
        return this.cEffectiveSettings;
    }
    
    void apply(final CParaSettings cParaSettings) {
        cParaSettings.apply(this.cSettings);
        this.cManager.update(this, false);
    }
    
    void setParaSettings(final CParaSettings cSettings) {
        this.cSettings = cSettings;
        this.cManager.update(this, false);
    }
    
    final String getName() {
        return this.cName;
    }
    
    final CParaStyle getParent() {
        return this.cParent;
    }
    
    private void rebuild(final boolean b) {
        if (!this.cRebuilt) {
            if (b) {
                if (this.cParent == null) {
                    this.cEffectiveInfo = new CFormatInfo();
                }
                else {
                    this.cEffectiveInfo = (CFormatInfo)this.cParent.cEffectiveInfo.clone();
                }
                this.cInfo.apply(this.cEffectiveInfo);
            }
            else {
                if (this.cParent == null) {
                    this.cEffectiveSettings = new CParaSettings();
                }
                else {
                    this.cEffectiveSettings = (CParaSettings)this.cParent.cEffectiveSettings.clone();
                }
                this.cSettings.apply(this.cEffectiveSettings);
            }
            this.cRebuilt = true;
        }
    }
    
    boolean update(final CParaStyle cParaStyle, final boolean b) {
        if (this == cParaStyle) {
            this.rebuild(b);
            return true;
        }
        if (this.cParent == null) {
            return false;
        }
        final boolean update = this.cParent.update(cParaStyle, b);
        if (update) {
            this.rebuild(b);
        }
        return update;
    }
    
    final void clearRebuilt() {
        this.cRebuilt = false;
    }
}
