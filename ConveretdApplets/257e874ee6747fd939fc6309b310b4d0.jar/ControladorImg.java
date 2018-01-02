import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControladorImg
{
    private int altura;
    private int posicion;
    private String direccion;
    private Applet applet;
    
    ControladorImg(final int altura, final String direccion, final Menu applet) {
        this.altura = altura;
        this.direccion = direccion;
        this.posicion = this.posicion;
        this.applet = applet;
    }
    
    public void aumentaAltura() {
        ++this.altura;
    }
    
    public void setAltura(final int altura) {
        this.altura = altura;
    }
    
    public int getAltura() {
        return this.altura;
    }
    
    public String getDireccion() {
        return this.direccion;
    }
    
    public void comprobarImagen(final int n) {
        if (n > this.altura && n < this.altura + 28) {
            System.out.println(this.direccion);
            try {
                this.applet.getAppletContext().showDocument(new URL("http://" + this.direccion), "main");
            }
            catch (Exception ex) {
                this.applet.getAppletContext().showStatus("Enlace Roto");
            }
        }
    }
}
