// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Frame;
import jlog.$BI.$M4;
import java.io.File;
import jlog.awt.$I8.$YNB;
import jlog.util.$UD.$XD;
import java.io.FilenameFilter;

class $VGC implements $I7B, $B7B, FilenameFilter
{
    $YAC $ZAC;
    $WGC $XGC;
    $XD $UF;
    $YNB $YGC;
    private int ERROR;
    private int OK;
    private int $H7B;
    
    int $AHC() {
        this.$XGC.setTitle(this.$ZAC.$TEC.getMessage("SAVE_AS_TITLE", this.$ZAC.$CBC.getName()));
        this.$ZAC.$EHC(this.$ZAC.$TEC.getMessage("SAVE_AS_INFO", this.$ZAC.$CBC.getName()));
        final File $efc = this.$ZAC.$CBC.$EFC;
        if ($efc == null) {
            this.$XGC.setFile("");
        }
        else {
            this.$XGC.setDirectory($efc.getParent());
            this.$XGC.setFile($efc.getName());
        }
        this.$XGC.pack();
        this.$XGC.show();
        final String directory = this.$XGC.getDirectory();
        String s = this.$XGC.getFile();
        if (s == null || directory == null || s.trim().length() == 0) {
            return this.$H7B;
        }
        if (!s.endsWith(".jar")) {
            if (s.indexOf(46) != -1) {
                this.$HHC(this.$ZAC.$TEC.getString("ERROR_SAVE_SUFFIX"));
                return this.ERROR;
            }
            s = String.valueOf(s) + ".jar";
        }
        this.$ZAC.$CBC.$EFC = new File(directory.trim(), s.trim());
        return this.OK;
    }
    
    int $BHC() {
        try {
            this.$ZAC.$CBC.$IF(true);
            if (this.$ZAC.$IGC()) {
                return this.OK;
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
        this.$HHC(this.$ZAC.$TEC.getString("SAVE_AS_NOT_SAVED"));
        return this.ERROR;
    }
    
    void $HHC(final String text) {
        this.$YGC.setText(text);
        this.$ZAC.$EHC(text);
        this.$YGC.pack();
        this.$YGC.show();
    }
    
    public boolean $J7B() throws Exception {
        int i = this.ERROR;
        try {
            while (i == this.ERROR) {
                this.$ZAC.$ZGC(true);
                i = this.$AHC();
                if (i == this.$H7B) {
                    return false;
                }
                if (i == this.ERROR) {
                    continue;
                }
                i = this.$BHC();
                if (i == this.$H7B) {
                    return false;
                }
                if (i == this.ERROR) {
                    continue;
                }
                this.$ZAC.$CHC(this.$ZAC.$CBC.$EFC.getPath());
            }
        }
        finally {
            this.$ZAC.$DHC();
        }
        return i == this.OK;
    }
    
    $VGC(final $YAC $zac) {
        this.ERROR = -1;
        this.OK = 0;
        this.$H7B = 1;
        this.$ZAC = $zac;
        this.$UF = $zac.$UF;
        final Frame frame = $zac.getFrame();
        (this.$XGC = new $WGC(frame, "", 1)).setFilenameFilter(this);
        this.$YGC = new $YNB(frame, "");
    }
    
    public boolean accept(final File file, String lowerCase) {
        if (lowerCase == null) {
            return false;
        }
        lowerCase = lowerCase.toLowerCase();
        return lowerCase.endsWith(".jar");
    }
}
