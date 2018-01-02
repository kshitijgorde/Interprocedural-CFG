// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

public class myCert
{
    public byte[] encoded;
    public byte[] common;
    public int valid;
    
    public myCert(final byte[] array) {
        this.valid = 0;
        try {
            this.encoded = new byte[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.encoded[i] = array[i];
            }
            final ASN1Decoder asn1Decoder = new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(array).value).value).remaining).remaining).remaining).remaining).remaining).remaining).value).remaining);
            final byte[] array2 = new byte[asn1Decoder.value.length - 1];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = asn1Decoder.value[j + 1];
            }
            final ASN1Decoder asn1Decoder2 = new ASN1Decoder(new ASN1Decoder(array2).value);
            this.common = new byte[asn1Decoder2.value.length - 1];
            for (int k = 0; k < this.common.length; ++k) {
                this.common[k] = asn1Decoder2.value[k + 1];
            }
        }
        catch (Exception ex) {
            this.valid = -1;
        }
    }
    
    public void print() {
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
}
