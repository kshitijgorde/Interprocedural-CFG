// 
// Decompiled by Procyon v0.5.30
// 

package secuenciaFirma;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.applet.Applet;

public class AppletObtenSecuencial extends Applet implements Runnable
{
    private static final long serialVersionUID = 1235194792918910289L;
    static int totalBuenos;
    
    static {
        AppletObtenSecuencial.totalBuenos = 0;
    }
    
    public void run() {
    }
    
    public String obtenSecuencial(final String parametroSiteId, final String siteId, final String referer) {
        try {
            String urlCGI = "http://" + super.getCodeBase().getHost();
            if (super.getCodeBase().getPort() != -1) {
                urlCGI = String.valueOf(urlCGI) + ":" + super.getCodeBase().getPort();
            }
            urlCGI = String.valueOf(urlCGI) + "/imss/servlet/gob.imss.idse.acceso.modelos.ModeloSecuenciaFirma";
            urlCGI = String.valueOf(urlCGI) + "?" + parametroSiteId + "=" + siteId;
            System.out.println("Conectando a " + urlCGI);
            final URL url = new URL(urlCGI);
            final URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; YComp 5.0.2.6; .NET CLR 1.0.2914)");
            urlConn.setRequestProperty("referer", referer);
            urlConn.setDoInput(true);
            urlConn.setDoOutput(false);
            urlConn.setUseCaches(false);
            final InputStream in = urlConn.getInputStream();
            final ByteArrayOutputStream array = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int total = 0;
            while ((total = in.read(buffer)) != -1) {
                array.write(buffer, 0, total);
            }
            ++AppletObtenSecuencial.totalBuenos;
            System.out.println("Respuesta:" + array.toByteArray());
            return new String(array.toByteArray());
        }
        catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }
}
