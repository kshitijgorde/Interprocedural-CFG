// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Component;
import java.awt.MediaTracker;
import jlog.awt.$W5;
import jlog.awt.$I8.$YNB;
import jlog.io.$P4;
import java.awt.Dialog;
import jlog.util.$UD.$XD;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.io.FilenameFilter;

class $BRC implements $I7B, $V0B, FilenameFilter, $P7B, $W5B, $DCC
{
    $YAC $ZAC;
    $WGC $XGC;
    URL $CRC;
    URL $DRC;
    File $ERC;
    File $FRC;
    Dimension $B_;
    Dimension $GRC;
    String $ZPC;
    File $HRC;
    $XD $UF;
    private int ERROR;
    private int OK;
    private int $H7B;
    Dialog $YGC;
    
    int $CIC() throws Exception {
        final URL $u4 = $P4.$U4(String.valueOf(System.getProperty("user.dir")) + File.separator + this.$ZPC);
        final $BBC $cbc = this.$ZAC.$CBC;
        if ($cbc != null && $u4.equals($cbc.$VPC())) {
            this.$HHC(this.$ZAC.$TEC.getMessage("JECMDNewMap.ErrorImageMapSet", $u4.getFile()));
            return this.ERROR;
        }
        final File $dbc = new File(this.$HRC.getParent());
        if (!$dbc.mkdirs()) {
            this.$HHC(this.$ZAC.$TEC.getMessage("ERROR_IMAGEMAP_MKDIRS", $u4.getFile()));
            return this.ERROR;
        }
        final URL url = new URL($u4, this.$ERC.getName());
        $P4.copy(this.$CRC, url);
        URL url2 = null;
        if (this.$DRC != null) {
            url2 = new URL($u4, this.$FRC.getName());
            $P4.copy(this.$DRC, url2);
        }
        final $BBC $bbc = new $BBC($u4, this.$ZAC, this.$ZAC.$XKB);
        $bbc.setSize(this.$B_);
        $bbc.$QKC.$JT(url);
        $bbc.$PPC.$JT(url2);
        $bbc.$ID = this.$ZPC;
        $bbc.$EFC = null;
        $bbc.$DBC = $dbc;
        this.$ZAC.$CIC($bbc);
        return this.OK;
    }
    
    void $HHC(final String text) {
        if (this.$YGC == null) {
            this.$YGC = new $YNB(this.$ZAC.getFrame(), text);
        }
        else {
            (($YNB)this.$YGC).setText(text);
        }
        this.$ZAC.$EHC(text);
        this.$YGC.pack();
        this.$YGC.show();
    }
    
    private int $IRC() throws Exception {
        this.$XGC.setTitle(this.$ZAC.$TEC.getString("JECMDNewMap.Title"));
        this.$ZAC.$EHC(this.$ZAC.$TEC.getString("SELECT_BACKGROUND_IMAGE_INFO"));
        this.$XGC.setFile("");
        this.$XGC.pack();
        this.$XGC.show();
        final String directory = this.$XGC.getDirectory();
        final String file = this.$XGC.getFile();
        if (file == null || directory == null || file.trim().length() == 0 || directory.trim().length() == 0) {
            return this.$H7B;
        }
        this.$ERC = new File(directory, file);
        this.$CRC = $P4.$U4(String.valueOf(this.$XGC.getDirectory()) + this.$XGC.getFile());
        return this.OK;
    }
    
    public boolean $J7B() throws Exception {
        int i;
        try {
            do {
                i = this.$IRC();
                if (i == this.OK) {
                    i = this.$JRC(this.$CRC, this.$B_);
                }
                else if (i == this.$H7B) {
                    break;
                }
                if (i == this.OK) {
                    i = this.$KRC();
                }
                if (i == this.OK) {
                    i = this.$JRC(this.$DRC, this.$GRC);
                }
                else if (i == this.$H7B) {
                    this.$DRC = null;
                    i = this.OK;
                }
                if (i == this.OK) {
                    i = this.$LRC();
                }
                if (i == this.OK) {
                    i = this.$CIC();
                }
            } while (i == this.ERROR);
        }
        finally {
            this.$ZAC.$DHC();
            this.$ZAC.$LJC("SELECT");
        }
        return i == this.OK;
    }
    
    int $JRC(final URL url, final Dimension dimension) {
        try {
            final Image image = $W5.createImage(url);
            try {
                final MediaTracker mediaTracker = new MediaTracker(this.$ZAC);
                mediaTracker.addImage(image, 1);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    throw new IOException(url.getFile());
                }
                dimension.width = image.getWidth(null);
                dimension.height = image.getHeight(null);
                if (dimension.width < 1 || dimension.height < 1) {
                    throw new IOException(url.getFile());
                }
                return this.OK;
            }
            finally {
                image.flush();
            }
        }
        catch (Exception ex) {
            this.$HHC(this.$ZAC.$TEC.getMessage("JECMDNewMap.ErrorLoadingImage", ex.getMessage()));
            return this.ERROR;
        }
        return this.OK;
    }
    
    private int $KRC() throws Exception {
        this.$XGC.setTitle(this.$ZAC.$TEC.getString("SELECT_ZOOM_IMAGE_TITLE"));
        this.$ZAC.$EHC(this.$ZAC.$TEC.getString("SELECT_ZOOM_IMAGE_INFO"));
        this.$XGC.setFile("");
        this.$XGC.pack();
        this.$XGC.show();
        final String directory = this.$XGC.getDirectory();
        final String file = this.$XGC.getFile();
        if (file == null || directory == null || file.trim().length() == 0) {
            return this.$H7B;
        }
        this.$FRC = new File(directory, file);
        this.$DRC = $P4.$U4(String.valueOf(this.$XGC.getDirectory()) + this.$XGC.getFile());
        return this.OK;
    }
    
    private int $LRC() throws Exception {
        final String name = this.$ERC.getName();
        final String property = this.$UF.getProperty("DEFAULT_MAP_SUFFIX");
        final int lastIndex = name.lastIndexOf(".");
        String substring;
        String s;
        if (lastIndex > 0) {
            substring = name.substring(0, lastIndex);
            s = String.valueOf(name.substring(0, lastIndex)) + property;
        }
        else {
            substring = name;
            s = String.valueOf(name) + property;
        }
        this.$HRC = new File(new File(substring, s).getAbsolutePath());
        if (this.$HRC.exists()) {
            this.$HHC(this.$ZAC.$TEC.getMessage("JECMDNewMap.ErrorImageMapExists", s));
            return this.ERROR;
        }
        this.$ZPC = String.valueOf(substring) + "/" + s;
        return this.OK;
    }
    
    $BRC(final $YAC $zac) {
        this.$B_ = new Dimension();
        this.$GRC = new Dimension();
        this.ERROR = -1;
        this.OK = 0;
        this.$H7B = 1;
        this.$ZAC = $zac;
        this.$UF = $zac.$UF;
        (this.$XGC = new $WGC($zac.getFrame(), "Select Image (.gif, .jpg)", 0)).setFilenameFilter(this);
    }
    
    public boolean accept(final File file, String lowerCase) {
        if (lowerCase == null) {
            return false;
        }
        lowerCase = lowerCase.toLowerCase();
        return lowerCase.endsWith(".gif") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png");
    }
}
