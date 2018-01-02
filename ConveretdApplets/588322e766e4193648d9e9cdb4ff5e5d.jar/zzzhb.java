import java.awt.TextArea;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class zzzhb
{
    public static final int zzzl = 3;
    private static final int zzzm = 64;
    private int[] zzzn;
    private long[] zzzo;
    private long[] zzzp;
    private int[] zzzq;
    private int[] zzzr;
    private int[] zzzs;
    private int zzzt;
    private String[] zzzu;
    private int zzzv;
    private Vector zzzw;
    private int zzzx;
    private long[] zzzy;
    private static final int zzzz = 50;
    private String[] zzzab;
    private String zzzbb;
    private int zzzcb;
    private int zzzdb;
    private TextArea zzzeb;
    private anagram zzzfb;
    private int zzzgb;
    
    public zzzhb(final anagram zzzfb, final String[] zzzu, final int zzzv, final TextArea zzzeb) {
        this.zzzn = new int[26];
        this.zzzo = new long[3];
        this.zzzp = new long[3];
        this.zzzq = new int[26];
        this.zzzr = new int[26];
        this.zzzs = new int[26];
        this.zzzab = new String[50];
        this.zzzfb = zzzfb;
        this.zzzeb = zzzeb;
        this.zzzu = zzzu;
        this.zzzv = zzzv;
    }
    
    public void zzzi(final String zzzbb, final int zzzdb, final int zzzcb) throws zzzxf, InterruptedException {
        this.zzzbb = zzzbb;
        this.zzzcb = zzzcb;
        this.zzzdb = zzzdb;
        this.zzzeb.setText("");
        if (this.zzzcb == 16) {
            this.zzzcb = 50;
        }
        this.zzzj(zzzbb, this.zzzn);
        this.zzzd();
        this.zzzf();
        this.zzzw = null;
        this.zzzw = new Vector(3000, 1000);
        this.zzzc();
        this.zzzw.trimToSize();
        this.zzzy = null;
        this.zzzy = new long[(this.zzzt + 1) * this.zzzx];
        this.zzzh();
        this.zzze(zzzbb, this.zzzp, 0);
        this.zzzk(this.zzzgb = 0, this.zzzp, 0);
    }
    
    private void zzzc() {
        final int[] array = new int[26];
        this.zzzx = 0;
        int i = 0;
    Label_0089:
        while (i < this.zzzv) {
            final String s = this.zzzu[i];
            this.zzzj(s, array);
            boolean b = true;
            int n = 0;
            while (true) {
                while (array[n] <= this.zzzn[n]) {
                    if (++n >= 26) {
                        if (b) {
                            this.zzzw.addElement(s);
                            ++this.zzzx;
                        }
                        ++i;
                        continue Label_0089;
                    }
                }
                b = false;
                continue;
            }
        }
    }
    
    private void zzzd() throws zzzxf {
        int zzzt = 0;
        int n = 0;
        int n2 = 64;
        int n3 = 0;
        do {
            if (this.zzzn[n3] != 0) {
                final int zzzg = this.zzzg(this.zzzn[n3]);
                if (n + zzzg > n2) {
                    if (++zzzt >= 3) {
                        System.out.println("Sorry, phrase too long to handle");
                        throw new zzzxf("User's phrase too long to handle");
                    }
                    n = 0;
                    n2 = 64;
                }
                this.zzzq[n3] = zzzt;
                this.zzzr[n3] = n;
                this.zzzs[n3] = zzzg;
                n += zzzg;
                n2 -= zzzg;
            }
        } while (++n3 < 26);
        this.zzzt = zzzt;
    }
    
    private void zzzf() {
        int n = 0;
        do {
            this.zzzo[n] = 0L;
        } while (++n < 3);
        int n2 = 0;
        do {
            if (this.zzzn[n2] != 0) {
                final int n3 = this.zzzr[n2];
                final int n4 = this.zzzs[n2];
                final long[] zzzo = this.zzzo;
                final int n5 = this.zzzq[n2];
                zzzo[n5] += 1L << n3 + n4 - 1;
            }
        } while (++n2 < 26);
    }
    
    private void zzzk(int i, final long[] array, int n) throws InterruptedException {
        final long[] array2 = new long[this.zzzt + 1];
        while (i < this.zzzx) {
            if (this.zzzfb.zzzhd) {
                return;
            }
            final int n2 = i * (this.zzzt + 1);
            int n3 = 0;
            Label_0156: {
                switch (this.zzzt) {
                    case 2: {
                        final long n4 = array[2] - this.zzzy[n2 + 2];
                        if ((n4 & this.zzzo[2]) <= 0L) {
                            array2[2] = n4;
                            n3 |= (int)n4;
                            break Label_0156;
                        }
                        break;
                    }
                    case 1: {
                        final long n5 = array[1] - this.zzzy[n2 + 1];
                        if ((n5 & this.zzzo[1]) <= 0L) {
                            array2[1] = n5;
                            n3 |= (int)n5;
                            break Label_0156;
                        }
                        break;
                    }
                    case 0: {
                        final long n6 = array[0] - this.zzzy[n2];
                        if ((n6 & this.zzzo[0]) <= 0L) {
                            array2[0] = n6;
                            final int n7 = (int)(n3 | n6);
                            this.zzzab[n++] = this.zzzw.elementAt(i);
                            if (n7 == 0) {
                                if (n >= this.zzzdb) {
                                    if (this.zzzgb >= 100) {
                                        this.zzzfb.zzzid = false;
                                        this.zzzfb.zzzad.enable();
                                        try {
                                            while (!this.zzzfb.zzzid) {
                                                Thread.sleep(100L);
                                            }
                                        }
                                        catch (InterruptedException ex) {
                                            System.out.println("InterruptedException " + ex);
                                            throw ex;
                                        }
                                        if (this.zzzfb.zzzhd) {
                                            return;
                                        }
                                        this.zzzeb.setText("");
                                        this.zzzgb = 0;
                                    }
                                    for (int j = 0; j < n; ++j) {
                                        final String s = this.zzzab[j];
                                        if (j == 0) {
                                            this.zzzeb.appendText(Character.toUpperCase(s.charAt(0)) + "");
                                            if (s.length() > 1) {
                                                this.zzzeb.appendText(s.substring(1) + " ");
                                            }
                                        }
                                        else {
                                            this.zzzeb.appendText(this.zzzab[j] + ((j < n - 1) ? " " : ""));
                                        }
                                    }
                                    this.zzzeb.appendText("\n");
                                    ++this.zzzgb;
                                }
                            }
                            else if (n < this.zzzcb) {
                                this.zzzk(i + 1, array2, n);
                            }
                            --n;
                            break;
                        }
                        break;
                    }
                }
            }
            ++i;
        }
    }
    
    private void zzze(final String s, final long[] array, final int n) {
        final int[] array2 = new int[26];
        this.zzzj(s, array2);
        for (int i = 0; i <= this.zzzt; ++i) {
            array[n * (this.zzzt + 1) + i] = 0L;
        }
        int n2 = 0;
        do {
            if (array2[n2] != 0) {
                final long n3 = array2[n2] << this.zzzr[n2];
                final int n4 = n * (this.zzzt + 1) + this.zzzq[n2];
                array[n4] += n3;
            }
        } while (++n2 < 26);
    }
    
    private void zzzh() {
        for (int i = 0; i < this.zzzx; ++i) {
            this.zzze((String)this.zzzw.elementAt(i), this.zzzy, i);
        }
    }
    
    private void zzzj(final String s, final int[] array) {
        final int length = s.length();
        int n = 0;
        do {
            array[n] = 0;
        } while (++n < 26);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= 'a' && char1 <= 'z') {
                final char c = (char)(char1 & '\u007f');
                if (c >= 'a' && c <= 'z') {
                    final char c2 = (char)(c - 'a');
                    ++array[c2];
                }
                else {
                    System.out.println("Letter not in range 97 <= x <= 122");
                }
            }
        }
    }
    
    private int zzzg(int i) {
        int n;
        for (n = 0; i > 0; i >>= 1, ++n) {}
        return n + 1;
    }
}
