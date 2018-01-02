import java.awt.Component;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class zzzgf
{
    private final int zzzcf = 1024;
    private zzzb zzzdf;
    private String zzzef;
    private anagram zzzff;
    
    public zzzgf(final anagram zzzff, final zzzb zzzdf) {
        this.zzzcf = 1024;
        this.zzzef = "";
        this.zzzff = zzzff;
        this.zzzdf = zzzdf;
    }
    
    public int zzzze(final zzzpf[] array) throws MalformedURLException, IOException {
        int n = 0;
        try {
            DataInputStream dataInputStream;
            if (!this.zzzff.zzztc) {
                final URLConnection openConnection = new URL(this.zzzff.getCodeBase(), this.zzzff.zzzld).openConnection();
                openConnection.connect();
                dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream(), 71680));
            }
            else {
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.zzzff.zzzld), 71680));
            }
            final int available = dataInputStream.available();
            int n2 = 0;
            int n3 = -250;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (n2 > n3 + 500) {
                    n3 = n2;
                    if (available > 0) {
                        zzzwf.zzzuf = n2 * 100 / available;
                    }
                    else {
                        zzzwf.zzzuf = 50;
                    }
                    ((Component)this.zzzdf).repaint();
                }
                n2 += line.length();
                final String lowerCase = line.trim().toLowerCase();
                final int length = lowerCase.length();
                if (length <= 100) {
                    array[length].zzzkf(lowerCase);
                    ++n;
                }
                else {
                    System.out.println("Word > 100 characters ignored");
                }
            }
            if (available > 0) {
                zzzwf.zzzuf = n2 * 100 / available;
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("Caught MalformedURLException: " + ex);
            throw ex;
        }
        catch (IOException ex2) {
            System.out.println("Caught IOException: " + ex2);
            throw ex2;
        }
        return n;
    }
    
    public int zzzbf(final zzzpf[] array) throws MalformedURLException, IOException {
        final byte[] array2 = new byte[100];
        final byte[] array3 = new byte[1024];
        int n = 0;
        try {
            BufferedInputStream bufferedInputStream;
            if (!this.zzzff.zzztc) {
                final URLConnection openConnection = new URL(this.zzzff.getCodeBase(), this.zzzff.zzzld).openConnection();
                openConnection.connect();
                bufferedInputStream = new BufferedInputStream(openConnection.getInputStream(), 71680);
            }
            else {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(this.zzzff.zzzld), 71680);
            }
            int n2;
            if ((n2 = bufferedInputStream.available()) != this.zzzff.zzzod) {
                System.out.println("File size returned incorrectly " + n2 + " " + this.zzzff.zzzod);
                n2 = this.zzzff.zzzod;
            }
            int n3 = 0;
            int n4 = 0;
            int n5 = -250;
            int read;
            while ((read = bufferedInputStream.read(array3, 0, 1024)) != -1) {
                for (int i = 0; i < read; ++i) {
                    if (array3[i] < 0) {
                        n4 += n3 + 1;
                        if (n4 > n5 + 500) {
                            n5 = n4;
                            if (n2 > 0) {
                                zzzwf.zzzuf = n4 * 100 / n2;
                            }
                            else {
                                zzzwf.zzzuf = 50;
                            }
                            ((Component)this.zzzdf).repaint();
                        }
                        final String zzzaf = this.zzzaf(array2, n3, array3[i] & 0x7F);
                        final int length = zzzaf.length();
                        if (length <= 100) {
                            array[length].zzzkf(zzzaf);
                            ++n;
                        }
                        else {
                            System.out.println("When word uncompressed > 100 chars");
                        }
                        n3 = 0;
                    }
                    else if (n3 < 100) {
                        array2[n3++] = array3[i];
                    }
                    else {
                        System.out.println("Word > constants.MAX_COMPRESSED_WORD_SIZE");
                    }
                }
            }
            if (n2 > 0) {
                zzzwf.zzzuf = n4 * 100 / n2;
                this.zzzff.repaint();
            }
            bufferedInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("Caught MalformedURLException: " + ex);
            throw ex;
        }
        catch (IOException ex2) {
            System.out.println("Caught IOException: " + ex2);
            throw ex2;
        }
        return n;
    }
    
    private String zzzaf(final byte[] array, final int n, final int n2) {
        final int length = this.zzzef.length();
        final StringBuffer sb = new StringBuffer(100);
        for (final byte b : array) {
            if (b == 26) {
                sb.append('-');
            }
            else if (b == 27) {
                sb.append('\'');
            }
            else if (b >= 0 && b <= 25) {
                sb.append((char)(97 + b));
            }
            else if (b >= 28 && b <= 127) {
                sb.append(zzznc.zzzmc[b - 28]);
            }
        }
        String zzzef = sb.toString();
        if (n2 > length) {
            System.out.println("Error - prefix digit larger than length of previous word " + this.zzzef + n2);
        }
        else {
            zzzef = this.zzzef.substring(0, n2) + zzzef;
        }
        return this.zzzef = zzzef;
    }
}
