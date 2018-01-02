// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Dimension;
import java.io.OutputStreamWriter;
import jlog.util.$XG.$TI;
import jlog.util.$XG.$IK;
import java.util.Hashtable;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Reader;
import jlog.io.$X2C;
import java.io.InputStreamReader;
import jlog.util.zip.$CD;
import java.io.StreamTokenizer;
import java.io.FileNotFoundException;
import jlog.util.$UD.$VD;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;
import java.io.InputStream;
import jlog.io.$LB;
import java.io.FileInputStream;
import jlog.util.zip.$EG;
import java.util.Date;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.net.URL;
import jlog.io.$P4;
import jlog.util.$XG.$GI;
import jlog.$BI.$M4;
import jlog.io.$JW;
import java.util.Dictionary;
import jlog.util.$XG.$AI;
import java.io.Writer;
import java.io.File;
import jlog.awt.$I8.$YNB;
import java.util.Properties;
import jlog.util.$UD.$XD;
import jlog.util.zip.$VF;

class $WYC implements $I7B, $Y7B, $U9B, $ADC, $VF
{
    $YAC $ZAC;
    $XD $UF;
    Properties $YPC;
    $YNB $YGC;
    File $EFC;
    File $M1C;
    private int ERROR;
    private int OK;
    private int $H7B;
    float $V1C;
    float $W1C;
    
    private void $A2C(final Writer writer) throws IOException {
        final $BBC $cbc = this.$ZAC.$CBC;
        final URL $vpc = $cbc.$VPC();
        String name = $cbc.getName();
        if (name == null || name.length() == 0) {
            name = "map1";
        }
        final $AI $ai = new $AI("map", null);
        $ai.put("name", name);
        $ai.put("MAP_SCALE_DEFAULT", new Float(this.$W1C).toString());
        final String description = $cbc.getDescription();
        if (!description.equals("")) {
            $ai.put("description", description);
        }
        if ($cbc.$MPC != null) {
            $ai.put("target", $cbc.$MPC);
        }
        if (!$cbc.vars.isEmpty()) {
            $ai.put("MAP_VARS", $cbc.vars.toString());
        }
        try {
            final URL url = this.$ZAC.$CBC.$PPC.getURL();
            if (url != null) {
                $ai.put("ZOOM_IMAGE_SRC", $JW.$BY(this.$ZAC.$CBC.$VPC().getFile(), url.getFile()));
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
        String s = "";
        final Enumeration $hk = $cbc.$LKC.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if (!$f8B.getName().equals("ALL_AREAS")) {
                if (s.length() != 0) {
                    s = String.valueOf(s) + "|";
                }
                final String string = String.valueOf(s) + $f8B.getName();
                final String description2 = $f8B.getDescription();
                final String string2 = String.valueOf(string) + ";" + ((description2 != null && !description2.equals("")) ? description2 : "-");
                final String id = $f8B.getID();
                String s2 = String.valueOf(string2) + ";" + ((id != null && !id.equals("")) ? id : "-");
                try {
                    s2 = String.valueOf(s2) + ";MARKER_COLOR=" + new $GI($f8B.getColor());
                }
                catch (Exception ex2) {
                    $M4.print(ex2);
                }
                if ($f8B.$XOC != null) {
                    try {
                        s2 = String.valueOf(s2) + ";XOR_COLOR=" + new $GI($f8B.$XOC);
                    }
                    catch (Exception ex3) {
                        $M4.print(ex3);
                    }
                }
                final int index = $f8B.getIndex();
                String s3 = String.valueOf(s2) + ";INDEX=";
                if ((index & 0x1) != 0x0) {
                    s3 = String.valueOf(s3) + " SELECTABLE";
                }
                if ((index & 0x40) != 0x0) {
                    s3 = String.valueOf(s3) + " LIST";
                }
                if ((index & 0x4) != 0x0) {
                    s3 = String.valueOf(s3) + " INVERSE";
                }
                if ((index & 0x10) != 0x0) {
                    s3 = String.valueOf(s3) + " BLINK";
                }
                else if (index == 0 || index == 4096) {
                    s3 = String.valueOf(s3) + "NULL";
                }
                final int $ckc = $f8B.$CKC();
                String s4 = String.valueOf(s3) + ";MARKER=";
                if (($ckc & 0x1) != 0x0) {
                    s4 = String.valueOf(s4) + " SELECTABLE";
                }
                if (($ckc & 0x2) != 0x0) {
                    s4 = String.valueOf(s4) + " SELECTED";
                }
                if (($ckc & 0x20) != 0x0) {
                    s4 = String.valueOf(s4) + " DEFAULT";
                }
                if (($ckc & 0x100) != 0x0) {
                    s4 = String.valueOf(s4) + " HIDENAME";
                }
                if (($ckc & 0x200) != 0x0) {
                    s4 = String.valueOf(s4) + " HIDEMARKER";
                }
                if (($ckc & 0x80) != 0x0) {
                    s4 = String.valueOf(s4) + " HIDELIST";
                }
                else if ($ckc == 0 || $ckc == 4096) {
                    s4 = String.valueOf(s4) + "NULL";
                }
                final int mouse = $f8B.getMouse();
                s = String.valueOf(s4) + ";MOUSE=";
                if ((mouse & 0x1) != 0x0) {
                    s = String.valueOf(s) + " SELECTABLE";
                }
                if ((mouse & 0x8) != 0x0) {
                    s = String.valueOf(s) + " POPUP";
                }
                if ((mouse & 0x4) != 0x0) {
                    s = String.valueOf(s) + " INVERSE";
                }
                else if (mouse == 0 || mouse == 4096) {
                    s = String.valueOf(s) + "NULL";
                }
                final URL $dpc = $f8B.$DPC();
                if ($dpc == null) {
                    continue;
                }
                s = String.valueOf(new StringBuffer(String.valueOf(s)).append(";ICON=").toString()) + $P4.$T$C($vpc, $dpc);
            }
        }
        $ai.put("RUBRIK", s);
        if ($cbc.$XOC != null) {
            try {
                $ai.put("MAP_XOR_COLOR", new $GI($cbc.$XOC));
            }
            catch (Exception ex4) {
                $M4.print(ex4);
            }
        }
        if ($cbc.$YMC != null) {
            $ai.put("STARTCARD_ATTRIBUTE", $cbc.$YMC);
        }
        writer.write("\n\t");
        $ai.output(writer);
        writer.write(10);
        this.$F2C(writer, $cbc, $vpc);
        writer.write("\t</map>\n");
    }
    
    void $F2C(final Writer writer, final $BBC $bbc, final URL url) throws IOException {
        final Enumeration $mic = $bbc.$MIC();
        while ($mic.hasMoreElements()) {
            final $EIC $eic = $mic.nextElement();
            if ($eic.$TOB != null && $eic.$TOB.length() != 0) {
                final $RKC $alc = this.$ZAC.$RFC.$ALC($eic);
                writer.write("\t\t");
                writer.write($alc.$ESC(url));
                writer.write(10);
            }
        }
        final Enumeration $mic2 = $bbc.$MIC();
        while ($mic2.hasMoreElements()) {
            final $EIC $eic2 = $mic2.nextElement();
            if ($eic2.$TOB == null || $eic2.$TOB.length() == 0) {
                final $RKC $alc2 = this.$ZAC.$RFC.$ALC($eic2);
                writer.write("\t\t");
                writer.write($alc2.$ESC(url));
                writer.write(10);
            }
        }
    }
    
    static String $H2C(final int n) {
        if (n > 9) {
            return String.valueOf(n);
        }
        return "0" + n;
    }
    
    void $HHC(final String text) {
        this.$YGC.setText(text);
        this.$ZAC.$EHC(text);
        this.$YGC.pack();
        this.$YGC.show();
    }
    
    void $I2C(final OutputStream outputStream) throws IOException {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("ARCHIVE_STARTDOCUMENT", this.$ZAC.$CBC.$ID);
        ((Hashtable<String, String>)properties).put("ARCHIVE_CREATED", new Date().toString());
        final String property = System.getProperty("user.name");
        if (property != null) {
            ((Hashtable<String, String>)properties).put("ARCHIVE_COMMENT", "by " + property);
        }
        properties.save(outputStream, "");
    }
    
    public boolean $J7B() throws Exception {
        int n = this.ERROR;
        try {
            this.$ZAC.$ZGC(true);
            n = this.$N1C();
            if (n != this.OK) {
                return false;
            }
            n = this.$O1C();
            if (n != this.OK) {
                return false;
            }
            n = this.$P1C();
            if (n != this.OK) {
                return false;
            }
            n = this.$Q1C();
            if (n != this.OK) {
                return false;
            }
            this.$R1C();
            this.$WTC();
            this.$ZAC.$CBC.$IF(false);
        }
        catch (Exception ex) {
            final Object[] array = new Object[2];
            if (this.$EFC != null) {
                array[0] = this.$EFC.getPath();
            }
            else {
                array[0] = "file";
            }
            array[1] = "\n \n" + ex.getMessage() + "\n " + ex.getClass().getName();
            this.$HHC(this.$ZAC.$TEC.getMessage("SAVE_NOT_SAVED", array));
            throw ex;
        }
        finally {
            if (this.$M1C != null && this.$M1C.exists()) {
                this.$M1C.delete();
            }
            this.$ZAC.$DHC();
        }
        return n == this.OK;
    }
    
    void $L2C(final $EG $eg) throws IOException {
        final $BBC $cbc = this.$ZAC.$CBC;
        final String substring = $cbc.$ID.substring(0, $cbc.$ID.lastIndexOf(47) + 1);
        final Vector $o2C = this.$O2C($cbc);
        final String[] list = $cbc.$DBC.list();
        for (int n = 0; list != null && n < list.length; ++n) {
            final File file = new File($cbc.$DBC, list[n]);
            final String string = String.valueOf(substring) + list[n];
            if ($o2C.contains(string) && file.isFile()) {
                final FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    final OutputStream $hg = $eg.$HG(string);
                    try {
                        $LB.copy(fileInputStream, $hg);
                    }
                    finally {
                        $hg.close();
                    }
                }
                finally {
                    fileInputStream.close();
                }
            }
        }
    }
    
    public void $M2C(final $EG $eg) throws IOException {
        final PrintStream printStream = new PrintStream($eg.$HG("META-INF/MANIFEST.MF"));
        try {
            printStream.println("Manifest-Version: 1.0");
            printStream.println("Main-Class: jlog/jim/ViewerMain");
            printStream.println("Created-By: 1.0 (Java Imagemap Editor)");
        }
        finally {
            printStream.close();
        }
    }
    
    int $N1C() throws Exception {
        this.$EFC = this.$ZAC.$CBC.$EFC;
        if (this.$EFC == null) {
            this.$ZAC.$VEC();
            return this.$H7B;
        }
        final String name = this.$EFC.getName();
        this.$M1C = new File(this.$EFC.getParent(), String.valueOf(name.substring(0, name.lastIndexOf(46))) + ".tmp");
        this.$UF.$IE("archivefile", this.$EFC.getName());
        this.$UF.$IE("archivename", name);
        this.$UF.$IE("documentname", this.$ZAC.$CBC.getName());
        this.$UF.$IE("date", new Date().toString());
        return this.OK;
    }
    
    int $O1C() throws Exception {
        try {
            new FileOutputStream(this.$M1C).close();
            return this.OK;
        }
        catch (IOException ex) {
            this.$HHC(this.$ZAC.$TEC.getMessage("SAVE_NOT_SAVED", new Object[] { this.$EFC.getPath(), ex.getMessage() }));
            this.$ZAC.$VEC();
            return this.$H7B;
        }
    }
    
    Vector $O2C(final $BBC $bbc) {
        final Vector<String> vector = new Vector<String>();
        final String substring = $bbc.$ID.substring(0, $bbc.$ID.lastIndexOf(47) + 1);
        final URL url = $bbc.$QKC.getURL();
        if (url != null) {
            final String replace = url.getFile().replace(File.separatorChar, '/');
            vector.addElement(String.valueOf(substring) + replace.substring(replace.lastIndexOf(47) + 1));
        }
        final URL url2 = $bbc.$PPC.getURL();
        if (url2 != null) {
            final String replace2 = url2.getFile().replace(File.separatorChar, '/');
            vector.addElement(String.valueOf(substring) + replace2.substring(replace2.lastIndexOf(47) + 1));
        }
        final Enumeration $hk = $bbc.$LKC.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if ($f8B.$DPC() != null) {
                final String replace3 = $f8B.$DPC().getFile().replace(File.separatorChar, '/');
                final String string = String.valueOf(substring) + replace3.substring(replace3.lastIndexOf(47) + 1);
                if (vector.contains(string)) {
                    continue;
                }
                vector.addElement(string);
            }
        }
        return vector;
    }
    
    int $P1C() throws IOException {
        final $EG $eg = new $EG();
        final OutputStream $hg = $eg.$HG("archive.properties");
        try {
            this.$I2C($hg);
        }
        finally {
            $hg.close();
        }
        this.$L2C($eg);
        if (this.$UF.$ZE("SAVE_VIEWER_CLASSES", true)) {
            this.$VTC($eg);
            this.$M2C($eg);
        }
        final OutputStream $hg2 = $eg.$HG(this.$ZAC.$CBC.$ID);
        try {
            this.$X1C($hg2);
        }
        finally {
            $hg2.close();
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(this.$M1C);
        this.$ZAC.$EHC(this.$ZAC.$TEC.getString("WRITE_ARCHIVE"));
        try {
            $eg.save(fileOutputStream);
        }
        finally {
            fileOutputStream.close();
        }
        this.$ZAC.$EHC(this.$ZAC.$TEC.getString("WRITE_ARCHIVE_DONE"));
        return this.OK;
    }
    
    int $Q1C() throws Exception {
        if (this.$EFC.exists()) {
            final boolean $ze = this.$UF.$ZE("SAVE_BACKUP", false);
            final boolean $ze2 = this.$UF.$ZE("SAVE_BACKUP_VERSIONS", false);
            final String parent = this.$EFC.getParent();
            final String name = this.$EFC.getName();
            final String substring = name.substring(0, name.lastIndexOf(46));
            if ($ze2) {
                final File file = new File(parent, String.valueOf(substring) + "-" + getDateString() + ".bak");
                if (file.exists()) {
                    file.delete();
                }
                if (!this.$EFC.renameTo(file)) {
                    throw new IOException("rename " + this.$EFC.getPath() + " to " + file.getPath() + " failed.");
                }
            }
            else if ($ze) {
                final File file2 = new File(parent, String.valueOf(substring) + ".bak");
                if (file2.exists()) {
                    file2.delete();
                }
                if (!this.$EFC.renameTo(file2)) {
                    throw new IOException("rename " + this.$EFC.getPath() + " to " + file2.getPath() + " failed.");
                }
            }
            else {
                this.$EFC.delete();
            }
        }
        if (!this.$M1C.renameTo(this.$EFC)) {
            throw new IOException("rename " + this.$M1C.getPath() + " to " + this.$EFC.getPath() + " failed.");
        }
        return this.OK;
    }
    
    void $R1C() throws IOException, $VD {
        if (!this.$UF.$ZE("SAVE_VIEWER_CLASSES", true) || (!this.$UF.$ZE("SAVE_HTML", true) && !this.$UF.$ZE("SAVE_HTML_PLUGIN", true))) {
            return;
        }
        String s = this.$EFC.getPath();
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex > -1) {
            s = s.substring(0, lastIndex);
        }
        if (this.$UF.$ZE("SAVE_HTML", true)) {
            this.$XTC(String.valueOf(s) + ".html", false);
        }
        if (this.$UF.$ZE("SAVE_HTML_PLUGIN", true)) {
            this.$XTC(String.valueOf(s) + "PlugIn.html", true);
        }
    }
    
    public void $VTC(final $EG $eg) throws IOException {
        $CD $cd = null;
        final InputStream resourceAsStream = this.$ZAC.$PD.getResourceAsStream("jlog/jim/ViewerResources.txt");
        if (resourceAsStream == null) {
            throw new FileNotFoundException("jlog/jim/ViewerResources.txt");
        }
        try {
            this.$ZAC.$EHC(this.$ZAC.$TEC.getString("SAVEING_STARTUP_CLASSES"));
            final StreamTokenizer streamTokenizer = new StreamTokenizer(resourceAsStream);
            streamTokenizer.resetSyntax();
            streamTokenizer.commentChar(35);
            streamTokenizer.whitespaceChars(0, 32);
            streamTokenizer.wordChars(33, 127);
            while (streamTokenizer.nextToken() != -1) {
                if (streamTokenizer.ttype == -3) {
                    final String sval = streamTokenizer.sval;
                    final OutputStream $hg = $eg.$HG(sval);
                    try {
                        InputStream inputStream = this.$ZAC.$PD.getResourceAsStream(sval);
                        if (inputStream == null) {
                            if ($cd == null) {
                                $cd = new $CD(new FileInputStream(String.valueOf(System.getProperty("user.dir")) + File.separatorChar + "jim_edit.jar"));
                            }
                            inputStream = $cd.openStream(sval);
                            if (inputStream == null) {
                                throw new FileNotFoundException(sval);
                            }
                        }
                        try {
                            $LB.copy(inputStream, $hg);
                        }
                        finally {
                            inputStream.close();
                        }
                    }
                    finally {
                        $hg.close();
                    }
                }
            }
        }
        finally {
            resourceAsStream.close();
            if ($cd != null) {
                $cd.flush();
            }
        }
    }
    
    void $WTC() throws IOException, $VD {
        if (!this.$UF.$ZE("SAVE_STARTER_WINDOWS", true)) {
            return;
        }
        String s = this.$EFC.getName();
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex > -1) {
            s = s.substring(0, lastIndex);
        }
        final String string = String.valueOf(s) + ".bat";
        final File file = new File(this.$EFC.getParent(), "Start" + string.substring(0, 1).toUpperCase() + string.substring(1));
        if (file.exists()) {
            return;
        }
        final String s2 = "jlog/jim/TemplateStartBat.txt";
        final InputStream resourceAsStream = this.$ZAC.$PD.getResourceAsStream(s2);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(s2);
        }
        final InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        try {
            this.$ZAC.$EHC(String.valueOf(this.$ZAC.$TEC.getString("SAVEING_STARTUP_BAT")) + " " + file.getPath());
            final $X2C $x2C = new $X2C(inputStreamReader, this.$UF);
            final FileWriter fileWriter = new FileWriter(file.getPath());
            try {
                $LB.copy($x2C, fileWriter);
            }
            finally {
                fileWriter.close();
            }
        }
        finally {
            inputStreamReader.close();
        }
    }
    
    void $X1C(final OutputStream outputStream) throws IOException {
        final PrintWriter printWriter = new PrintWriter(outputStream);
        this.$V1C = this.$ZAC.$RFC.$A7();
        this.$W1C = this.$ZAC.$RFC.$B7();
        try {
            this.$ZAC.$RFC.$T_B(1.0f, 1.0f);
            this.$Z1C(printWriter);
            this.$A2C(printWriter);
        }
        finally {
            this.$ZAC.$RFC.$T_B(this.$W1C, this.$V1C);
            this.$ZAC.$RFC.validate();
            printWriter.flush();
        }
    }
    
    void $XTC(final String s, final boolean b) throws IOException, $VD {
        final File file = new File(s);
        if (file.exists()) {
            return;
        }
        final String s2 = "jlog.jim.ViewerApplet";
        final int intProperty = this.$UF.getIntProperty("SAVE_HTML_APPLET_WIDTH");
        final int intProperty2 = this.$UF.getIntProperty("SAVE_HTML_APPLET_HEIGHT");
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("archive", this.$EFC.getName());
        final Hashtable hashtable2 = new Hashtable();
        $TI $ti;
        if (b) {
            $ti = new $IK(s2, intProperty, intProperty2, hashtable, hashtable2);
        }
        else {
            $ti = new $TI(s2, intProperty, intProperty2, hashtable, hashtable2);
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            this.$ZAC.$EHC(String.valueOf(this.$ZAC.$TEC.getString("SAVEING_STARTUP_HTML")) + " " + file.getPath());
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("<html>\n");
            outputStreamWriter.write("<head><title>Map " + this.$ZAC.$CBC.getName());
            if (b) {
                outputStreamWriter.write(" (Java Plug-in)");
            }
            outputStreamWriter.write("</title></head>\n");
            outputStreamWriter.write("<body>\n");
            outputStreamWriter.write("<center>\n");
            outputStreamWriter.flush();
            if (b) {
                outputStreamWriter.write("<!--webbot bot=\"HTMLMarkup\" startspan -->");
            }
            $ti.write(fileOutputStream);
            if (b) {
                outputStreamWriter.write("<!--webbot bot=\"HTMLMarkup\" endspan -->");
            }
            outputStreamWriter.write("</center>\n");
            outputStreamWriter.write("</body>\n");
            outputStreamWriter.write("</html>\n");
            outputStreamWriter.flush();
        }
        finally {
            fileOutputStream.close();
        }
    }
    
    private void $Z1C(final Writer writer) throws IOException {
        final URL url = this.$ZAC.$CBC.$QKC.getURL();
        if (url != null) {
            final Dimension size = this.$ZAC.$CBC.getSize();
            final String $by = $JW.$BY(this.$ZAC.$CBC.$VPC().getFile(), url.getFile());
            String name = this.$ZAC.$CBC.getName();
            if (name == null || name.length() == 0) {
                name = "map1";
            }
            writer.write("\t<IMG SRC=" + $by + " USEMAP=#" + name + " ISMAP" + ((size.width > 0) ? (" WIDTH=" + size.width) : "") + ((size.height > 0) ? (" HEIGHT=" + size.height) : "") + " BORDER=0" + ">\n");
        }
    }
    
    $WYC(final $YAC $zac) {
        this.$EFC = null;
        this.$M1C = null;
        this.ERROR = -1;
        this.OK = 0;
        this.$H7B = 1;
        this.$ZAC = $zac;
        this.$UF = $zac.$UF;
        this.$YGC = new $YNB($zac.getFrame(), "");
    }
    
    static String getDateString() {
        final Date date = new Date();
        return String.valueOf(date.getYear() + 1900) + "-" + $H2C(date.getMonth() + 1) + "-" + $H2C(date.getDate()) + "-" + $H2C(date.getHours()) + "-" + $H2C(date.getMinutes());
    }
}
