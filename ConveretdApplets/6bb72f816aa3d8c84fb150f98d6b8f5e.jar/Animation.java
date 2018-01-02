// 
// Decompiled by Procyon v0.5.30
// 

public final class Animation
{
    public static String loc;
    public static final int[] ATTACK_ANIMS;
    public static Animation[] anims;
    public int anInt352;
    public int[] anIntArray353;
    public int[] anIntArray354;
    private int[] anIntArray355;
    public int anInt356;
    public int[] anIntArray357;
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int anInt364;
    public int anInt365;
    public static int anInt367;
    
    public static byte[] getData(final String s) {
        return FileOperations.ReadFile(s);
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(getData(Animation.loc + "seq.dat"));
        final int unsignedWord = stream.readUnsignedWord();
        if (Animation.anims == null) {
            Animation.anims = new Animation[unsignedWord + 5000];
        }
        for (int i = 0; i < unsignedWord; ++i) {
            if (Animation.anims[i] == null) {
                Animation.anims[i] = new Animation();
            }
            Animation.anims[i].readValues(stream);
            try {
                Animation.anims[15069] = new Animation();
                Animation.anims[15069].anInt352 = 24;
                Animation.anims[15069].anIntArray355 = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
                Animation.anims[15069].anIntArray353 = new int[] { 227803309, 227803140, 227803217, 227803251, 227803297, 227803222, 227803158, 227803280, 227803266, 227803224, 227803268, 227803288, 227803218, 227803157, 227803187, 227803152, 227803286, 227803179, 227803168, 227803229, 227803248, 227803213, 227803180, 227803284 };
                Animation.anims[15069].anInt363 = 0;
                Animation.anims[15069].anInt364 = 0;
                Animation.anims[15070] = new Animation();
                Animation.anims[15070].anInt352 = 24;
                Animation.anims[15070].anIntArray355 = new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                Animation.anims[15070].anIntArray353 = new int[] { 227803277, 227803235, 227803154, 227803141, 227803238, 227803321, 227803151, 227803203, 227803205, 227803299, 227803137, 227803262, 227803245, 227803174, 227803242, 227803139, 227803305, 227803317, 227803254, 227803167, 227803185, 227803267, 227803257, 227803265 };
                Animation.anims[15070].anInt363 = 0;
                Animation.anims[15070].anInt364 = 0;
                Animation.anims[15071] = new Animation();
                Animation.anims[15071].anInt352 = 16;
                Animation.anims[15071].anIntArray355 = new int[] { 4, 3, 3, 4, 4, 3, 2, 2, 1, 1, 2, 2, 3, 3, 3, 1 };
                Animation.anims[15071].anIntArray353 = new int[] { 227803302, 227803143, 227803240, 227803163, 227803159, 227803155, 227803323, 227803183, 227803276, 227803287, 227803285, 227803219, 227803210, 227803147, 227803204, 227803302 };
                Animation.anims[15071].anInt359 = 6;
                Animation.anims[15071].anInt362 = 1;
                Animation.anims[15071].anInt363 = 2;
                Animation.anims[15071].anInt364 = 2;
                Animation.anims[15072] = new Animation();
                Animation.anims[15072].anInt352 = 23;
                Animation.anims[15072].anIntArray355 = new int[] { 3, 4, 4, 3, 4, 2, 2, 1, 1, 1, 1, 1, 3, 3, 3, 2, 2, 2, 3, 3, 3, 4, 1 };
                Animation.anims[15072].anIntArray353 = new int[] { 227803212, 227803308, 227803199, 227803292, 227803227, 227803241, 227803271, 227803327, 227803296, 227803173, 227803314, 227803247, 227803263, 227803237, 227803243, 227803156, 227803138, 227803190, 227803198, 227803231, 227803304, 227803233, 227803212 };
                Animation.anims[15072].anInt359 = 6;
                Animation.anims[15072].anInt362 = 1;
                Animation.anims[15072].anInt363 = 2;
                Animation.anims[15072].anInt364 = 2;
                Animation.anims[15073] = new Animation();
                Animation.anims[15073].anInt352 = 16;
                Animation.anims[15073].anIntArray355 = new int[] { 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2 };
                Animation.anims[15073].anIntArray353 = new int[] { 227803319, 227803320, 227803223, 227803197, 227803177, 227803259, 227803211, 227803145, 227803312, 227803252, 227803279, 227803209, 227803166, 227803176, 227803207, 227803261 };
                Animation.anims[15073].anInt363 = 2;
                Animation.anims[15073].anInt364 = 2;
                Animation.anims[15074] = new Animation();
                Animation.anims[15074].anInt352 = 17;
                Animation.anims[15074].anIntArray355 = new int[] { 1, 2, 2, 2, 3, 3, 3, 4, 2, 1, 3, 1, 2, 2, 1, 1, 1 };
                Animation.anims[15074].anIntArray353 = new int[] { 227803221, 227803322, 227803275, 227803256, 227803283, 227803272, 227803293, 227803318, 227803303, 227803206, 227803164, 227803146, 227803289, 227803255, 227803225, 227803169, 227803221 };
                Animation.anims[15074].anInt362 = 1;
                Animation.anims[15074].anInt363 = 2;
                Animation.anims[15074].anInt364 = 2;
                Animation.anims[15075] = new Animation();
                Animation.anims[15075].anInt352 = 16;
                Animation.anims[15075].anIntArray355 = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
                Animation.anims[15075].anIntArray353 = new int[] { 227803194, 227803226, 227803171, 227803228, 227803232, 227803136, 227803253, 227803175, 227803316, 227803162, 227803186, 227803192, 227803294, 227803144, 227803189, 227803282 };
                Animation.anims[15075].anInt363 = 0;
                Animation.anims[15075].anInt364 = 0;
                Animation.anims[15076] = new Animation();
                Animation.anims[15076].anInt352 = 16;
                Animation.anims[15076].anIntArray355 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
                Animation.anims[15076].anIntArray353 = new int[] { 227803193, 227803298, 227803201, 227803234, 227803216, 227803160, 227803315, 227803306, 227803325, 227803220, 227803182, 227803149, 227803150, 227803313, 227803208, 227803258 };
                Animation.anims[15076].anInt363 = 0;
                Animation.anims[15076].anInt364 = 0;
                Animation.anims[15077] = new Animation();
                Animation.anims[15077].anInt352 = 16;
                Animation.anims[15077].anIntArray355 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
                Animation.anims[15077].anIntArray353 = new int[] { 227803148, 227803326, 227803290, 227803270, 227803202, 227803269, 227803244, 227803307, 227803260, 227803153, 227803250, 227803196, 227803278, 227803246, 227803236, 227803195 };
                Animation.anims[15077].anInt363 = 0;
                Animation.anims[15077].anInt364 = 0;
            }
            catch (Exception ex) {}
            if (i == 808) {
                final int[] array = { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3 };
                for (int j = 0; j < 24; ++j) {
                    Animation.anims[i].anIntArray355[j] = array[j];
                }
            }
            if (i == 12000) {
                final int[] array2 = { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3 };
                for (int k = 0; k < 24; ++k) {
                    Animation.anims[i].anIntArray355[k] = array2[k];
                }
            }
            if (i == 4591) {
                final int[] array3 = { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3 };
                for (int l = 0; l < 24; ++l) {
                    Animation.anims[i].anIntArray355[l] = array3[l];
                }
            }
            if (i == 4410) {
                final int[] array4 = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0 };
                for (int n = 0; n < array4.length - 1; ++n) {
                    Animation.anims[i].anIntArray355[n] = array4[n];
                    Animation.anims[i].anInt363 = 0;
                    Animation.anims[i].anInt364 = 0;
                }
            }
            final int[] attack_ANIMS = Animation.ATTACK_ANIMS;
            for (int length = attack_ANIMS.length, n2 = 0; n2 < length; ++n2) {
                if (i == attack_ANIMS[n2]) {
                    Animation.anims[i].anInt360 = 0;
                    Animation.anims[i].anInt361 = 0;
                }
            }
        }
        Animation.anims[4000].anInt352 = 28;
        Animation.anims[4000].anIntArray355 = new int[] { 3, 2, 2, 2, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 3, 3, 2, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
        Animation.anims[4000].anIntArray353 = new int[] { 223019263, 223019511, 223019120, 223019119, 223019242, 223019278, 223019027, 223019504, 223019417, 223019428, 223019705, 223019087, 223019664, 223019465, 223019589, 223019707, 223019322, 223019644, 223019174, 223019574, 223019108, 223019335, 223019521, 223019401, 223019300, 223019029, 223019276, 223019115 };
        Animation.anims[4000].anInt363 = 0;
        Animation.anims[4000].anInt364 = 0;
        Animation.anims[4001].anInt352 = 15;
        Animation.anims[4001].anIntArray355 = new int[] { 9, 3, 3, 3, 3, 3, 2, 2, 15, 4, 3, 3, 3, 3, 3 };
        Animation.anims[4001].anIntArray353 = new int[] { 219742346, 219742338, 219742330, 219742335, 219742341, 219742348, 219742322, 219742325, 219742318, 219742320, 219742323, 219742349, 219742334, 219742317, 219742347 };
        Animation.anims[4002].anInt352 = 40;
        Animation.anims[4002].anIntArray355 = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
        Animation.anims[4002].anIntArray353 = new int[] { 219742278, 219742256, 219742218, 219742282, 219742223, 219742222, 219742253, 219742232, 219742300, 219742239, 219742254, 219742252, 219742245, 219742224, 219742219, 219742294, 219742209, 219742241, 219742299, 219742230, 219742279, 219742238, 219742221, 219742214, 219742283, 219742305, 219742312, 219742280, 219742265, 219742211, 219742210, 219742208, 219742212, 219742234, 219742314, 219742240, 219742292, 219742313, 219742267, 219742263 };
    }
    
    public int method258(final int n) {
        int n2 = this.anIntArray355[n];
        if (n2 == 0) {
            final Class36 method531 = Class36.method531(this.anIntArray353[n]);
            if (method531 != null) {
                final int[] anIntArray355 = this.anIntArray355;
                final int anInt636 = method531.anInt636;
                anIntArray355[n] = anInt636;
                n2 = anInt636;
            }
        }
        if (n2 == 0) {
            n2 = 1;
        }
        return n2;
    }
    
    public void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                this.anInt352 = stream.readUnsignedWord();
                this.anIntArray353 = new int[this.anInt352];
                this.anIntArray354 = new int[this.anInt352];
                this.anIntArray355 = new int[this.anInt352];
                for (int i = 0; i < this.anInt352; ++i) {
                    this.anIntArray353[i] = stream.readDWord();
                    this.anIntArray354[i] = -1;
                }
                for (int j = 0; j < this.anInt352; ++j) {
                    this.anIntArray355[j] = stream.readUnsignedByte();
                }
            }
            else if (unsignedByte == 2) {
                this.anInt356 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 3) {
                final int unsignedByte2 = stream.readUnsignedByte();
                this.anIntArray357 = new int[unsignedByte2 + 1];
                for (int k = 0; k < unsignedByte2; ++k) {
                    this.anIntArray357[k] = stream.readUnsignedByte();
                }
                this.anIntArray357[unsignedByte2] = 9999999;
            }
            else if (unsignedByte == 4) {
                this.aBoolean358 = true;
            }
            else if (unsignedByte == 5) {
                this.anInt359 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 6) {
                this.anInt360 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 7) {
                this.anInt361 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 8) {
                this.anInt362 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 9) {
                this.anInt363 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 10) {
                this.anInt364 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 11) {
                this.anInt365 = stream.readUnsignedByte();
            }
            else {
                System.out.println("Unrecognized seq.dat config code: " + unsignedByte);
            }
        }
        if (this.anInt352 == 0) {
            this.anInt352 = 1;
            (this.anIntArray353 = new int[1])[0] = -1;
            (this.anIntArray354 = new int[1])[0] = -1;
            (this.anIntArray355 = new int[1])[0] = -1;
        }
        if (this.anInt363 == -1) {
            if (this.anIntArray357 != null) {
                this.anInt363 = 2;
            }
            else {
                this.anInt363 = 0;
            }
        }
        if (this.anInt364 == -1) {
            if (this.anIntArray357 != null) {
                this.anInt364 = 2;
                return;
            }
            this.anInt364 = 0;
        }
    }
    
    private Animation() {
        this.anInt356 = -1;
        this.aBoolean358 = false;
        this.anInt359 = 5;
        this.anInt360 = -1;
        this.anInt361 = -1;
        this.anInt362 = 99;
        this.anInt363 = -1;
        this.anInt364 = -1;
        this.anInt365 = 2;
    }
    
    static {
        Animation.loc = SignLink.findcachedir() + "Data/Animation/";
        ATTACK_ANIMS = new int[] { 12589, 12573, 12575, 829, 883, 624, 827, 791, 881, 832, 12565, 8939, 8941, 9599, 4959, 4981, 4971, 4979, 4939, 4951, 4975, 4977, 4965, 4967, 4947, 9597, 8525, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 1368, 1131, 1129, 1130, 1128, 2127, 2128, 2836, 3544, 3543, 4276, 4278, 4280, 4275, 7272, 6111, 7531, 2414, 8770, 9990, 10530 };
    }
}
