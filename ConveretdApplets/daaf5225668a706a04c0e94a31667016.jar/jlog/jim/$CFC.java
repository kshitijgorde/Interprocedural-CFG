// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Frame;
import java.net.URL;
import jlog.io.$P4;
import jlog.$BI.$M4;
import java.io.IOException;
import java.util.Enumeration;
import java.io.OutputStream;
import jlog.io.$LB;
import java.io.FileOutputStream;
import java.io.InputStream;
import jlog.util.zip.$CD;
import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;
import jlog.util.$UD.$XD;
import jlog.awt.$I8.$YNB;
import jlog.util.zip.$VF;
import java.io.FilenameFilter;

class $CFC implements FilenameFilter, $V0B, $ADC, $DCC, $I7B, $S8B, $W5B, $VF
{
    int ERROR;
    int OK;
    int $H7B;
    $YAC $ZAC;
    $WGC $XGC;
    $YNB $YGC;
    $XD $UF;
    Properties $YPC;
    File $EFC;
    File $DBC;
    String $ZPC;
    
    int $AHC() {
        this.$XGC.setTitle(this.$ZAC.$TEC.getString("JECMDLoad.Title"));
        this.$ZAC.$EHC(this.$ZAC.$TEC.getString("SELECT_MAP_INFO"));
        this.$XGC.setFile("");
        this.$XGC.pack();
        this.$XGC.show();
        final String directory = this.$XGC.getDirectory();
        final String file = this.$XGC.getFile();
        if (file == null || directory == null || file.trim().length() == 0) {
            return this.$H7B;
        }
        this.$EFC = new File(directory.trim(), file.trim());
        return this.OK;
    }
    
    int $AQC() {
        try {
            final $CD $cd = new $CD(new FileInputStream(this.$EFC));
            try {
                try {
                    final InputStream openStream = $cd.openStream("archive.properties");
                    (this.$YPC = new Properties()).load(openStream);
                    openStream.close();
                    this.$ZPC = this.$YPC.getProperty("ARCHIVE_STARTDOCUMENT");
                    final String property = this.$UF.getProperty("DEFAULT_MAP_SUFFIX");
                    if (this.$ZPC != null && this.$ZPC.endsWith(property)) {
                        try {
                            this.$CQC($cd);
                            return this.OK;
                        }
                        catch (Exception ex) {
                            this.$HHC(this.$ZAC.$TEC.getMessage("CMDLOAD_ERROR_EXTRACT", new Object[] { this.$EFC.getName(), String.valueOf(ex.toString()) + " " + ex.getMessage() }));
                            return this.ERROR;
                        }
                    }
                }
                catch (Exception ex2) {}
                this.$HHC(this.$ZAC.$TEC.getMessage("CMDLOAD_NO_MAP_IN_JAR", this.$EFC.getName()));
                return this.ERROR;
            }
            finally {
                $cd.flush();
            }
        }
        catch (Exception ex3) {
            this.$HHC(this.$ZAC.$TEC.getMessage("CMDLOAD_ERROR_OPEN_JAR", this.$EFC.getName()));
            return this.ERROR;
        }
    }
    
    void $CQC(final $CD $cd) throws IOException {
        $cd.get("AllXWe");
        final File file = new File(System.getProperty("user.dir"));
        final String substring = this.$ZPC.substring(0, this.$ZPC.lastIndexOf(47));
        (this.$DBC = new File(file, substring.replace('/', File.separatorChar))).mkdirs();
        final String string = String.valueOf(substring) + '/';
        final Enumeration<String> keys = (Enumeration<String>)$cd.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (s.startsWith(string)) {
                final File file2 = new File(file, s.replace('/', File.separatorChar));
                final InputStream openStream = $cd.openStream(s);
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        $LB.copy(openStream, fileOutputStream);
                    }
                    finally {
                        fileOutputStream.close();
                    }
                }
                finally {
                    openStream.close();
                }
            }
        }
    }
    
    int $FFC() {
        try {
            return this.$AQC();
        }
        catch (Throwable t) {
            $M4.print(t);
            return this.ERROR;
        }
    }
    
    int $HFC() throws Exception {
        final URL $u4 = $P4.$U4(String.valueOf(System.getProperty("user.dir")) + File.separator + this.$ZPC);
        final $BBC $cbc = this.$ZAC.$CBC;
        if ($cbc != null && $u4.equals($cbc.$VPC())) {
            this.$HHC(this.$ZAC.$TEC.getMessage("JECMDLoad.ErrorImageMapSet", $u4.getFile()));
            return this.ERROR;
        }
        try {
            final $BBC $hfc = this.$ZAC.$HFC($u4);
            if ($hfc == null) {
                throw new IOException($u4.getFile());
            }
            $hfc.$ID = this.$ZPC;
            $hfc.$EFC = this.$EFC;
            $hfc.$DBC = this.$DBC;
            this.$ZAC.$CIC($hfc);
        }
        catch (IOException ex) {
            this.$HHC(this.$ZAC.$TEC.getMessage("JECMDLoad.ErrorLoadingMap", $u4.getFile()));
            return this.ERROR;
        }
        this.$ZAC.$CHC(this.$EFC.getPath());
        return this.OK;
    }
    
    void $HHC(final String text) {
        this.$YGC.setText(text);
        this.$ZAC.$EHC(text);
        this.$YGC.pack();
        this.$YGC.show();
    }
    
    public boolean $J7B() throws Exception {
        int i;
        try {
            do {
                this.$ZAC.$ZGC(true);
                i = this.$AHC();
                if (i == this.$H7B) {
                    return false;
                }
                if (i == this.ERROR) {
                    continue;
                }
                this.$ZAC.$CTB.$MTB(true);
                i = this.$FFC();
                if (i == this.$H7B) {
                    return false;
                }
                if (i == this.ERROR) {
                    continue;
                }
                i = this.$HFC();
                if (i == this.$H7B) {
                    return false;
                }
                if (i == this.ERROR) {}
            } while (i == this.ERROR);
        }
        finally {
            this.$ZAC.$DHC();
            this.$ZAC.$LJC("SELECT");
        }
        return i == this.OK;
    }
    
    $CFC(final $YAC $zac) {
        this.ERROR = -1;
        this.OK = 0;
        this.$H7B = 1;
        this.$EFC = null;
        this.$DBC = null;
        this.$ZAC = $zac;
        this.$UF = $zac.$UF;
        final Frame frame = $zac.getFrame();
        (this.$XGC = new $WGC(frame, "", 0)).setFilenameFilter(this);
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
