// 
// Decompiled by Procyon v0.5.30
// 

package comprimeArchivos;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import java.applet.Applet;

public class ComprimeArchivos extends Applet
{
    private static final long serialVersionUID = 5184851757290032497L;
    private static char[] alphabet;
    private static byte[] codes;
    
    static {
        ComprimeArchivos.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        ComprimeArchivos.codes = new byte[256];
        for (int i = 0; i < 256; ++i) {
            ComprimeArchivos.codes[i] = -1;
        }
        for (int j = 65; j <= 90; ++j) {
            ComprimeArchivos.codes[j] = (byte)(j - 65);
        }
        for (int k = 97; k <= 122; ++k) {
            ComprimeArchivos.codes[k] = (byte)(26 + k - 97);
        }
        for (int l = 48; l <= 57; ++l) {
            ComprimeArchivos.codes[l] = (byte)(52 + l - 48);
        }
        ComprimeArchivos.codes[43] = 62;
        ComprimeArchivos.codes[47] = 63;
    }
    
    public String comprimeDocB64(final String archivoBase64, final String nombreArchivo) {
        byte[] archivo = this.decodeB64(archivoBase64.toCharArray());
        archivo = this.comprime(archivo, nombreArchivo);
        return this.encodeB64(archivo);
    }
    
    public byte[] comprimeBinario(final byte[] datos, final String nombreArchivo) {
        return this.comprime(datos, nombreArchivo);
    }
    
    public byte[] comprimeArchivoTexto(final String texto, final String nombreArchivo) {
        return this.comprime(texto.getBytes(), nombreArchivo);
    }
    
    private byte[] comprime(final byte[] datos, final String nombreArchivo) {
        try {
            System.out.println("Tamano Original " + datos.length);
            final ByteArrayOutputStream compreso = new ByteArrayOutputStream();
            final ZipOutputStream zipOut = new ZipOutputStream(compreso);
            zipOut.putNextEntry(new ZipEntry(nombreArchivo));
            zipOut.write(datos);
            zipOut.close();
            compreso.close();
            System.out.println("Tamano compreso " + compreso.toByteArray().length);
            return compreso.toByteArray();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String encodeB64(final byte[] abyte0) {
        System.out.println("Antes de encodeB64 " + abyte0.length);
        final char[] ac = new char[(abyte0.length + 2) / 3 * 4];
        for (int i = 0, j = 0; i < abyte0.length; i += 3, j += 4) {
            boolean flag = false;
            boolean flag2 = false;
            int k = 0xFF & abyte0[i];
            k <<= 8;
            if (i + 1 < abyte0.length) {
                k |= (0xFF & abyte0[i + 1]);
                flag2 = true;
            }
            k <<= 8;
            if (i + 2 < abyte0.length) {
                k |= (0xFF & abyte0[i + 2]);
                flag = true;
            }
            ac[j + 3] = ComprimeArchivos.alphabet[flag ? (k & 0x3F) : 64];
            k >>= 6;
            ac[j + 2] = ComprimeArchivos.alphabet[flag2 ? (k & 0x3F) : 64];
            k >>= 6;
            ac[j + 1] = ComprimeArchivos.alphabet[k & 0x3F];
            k >>= 6;
            ac[j] = ComprimeArchivos.alphabet[k & 0x3F];
        }
        System.out.println("Despues de encodeB64 " + ac.length);
        return new String(ac);
    }
    
    public byte[] decodeB64(final char[] ac) {
        int i = (ac.length + 3) / 4 * 3;
        if (ac.length > 0 && ac[ac.length - 1] == '=') {
            --i;
        }
        if (ac.length > 1 && ac[ac.length - 2] == '=') {
            --i;
        }
        final byte[] abyte0 = new byte[i];
        int j = 0;
        int k = 0;
        int l = 0;
        for (int i2 = 0; i2 < ac.length; ++i2) {
            final byte byte0 = ComprimeArchivos.codes[ac[i2] & '\u00ff'];
            if (byte0 >= 0) {
                k <<= 6;
                j += 6;
                k |= byte0;
                if (j >= 8) {
                    j -= 8;
                    abyte0[l++] = (byte)(k >> j & 0xFF);
                }
            }
        }
        if (l != abyte0.length) {
            throw new Error("miscalculated data length!");
        }
        return abyte0;
    }
}
