// 
// Decompiled by Procyon v0.5.30
// 

public final class SpotAnim
{
    public static String loc;
    private final int anInt400;
    public static SpotAnim[] cache;
    private int anInt404;
    private int anInt405;
    private int anInt406;
    public Animation aAnimation_407;
    private final int[] anIntArray408;
    private final int[] anIntArray409;
    public int anInt410;
    public int anInt411;
    public int anInt412;
    public int anInt413;
    public int anInt414;
    public static MRUNodes aMRUNodes_415;
    
    public static byte[] getData(final String s) {
        return FileOperations.ReadFile(s);
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(getData(SpotAnim.loc + "spotanim.dat"));
        final int unsignedWord = stream.readUnsignedWord();
        if (SpotAnim.cache == null) {
            SpotAnim.cache = new SpotAnim[unsignedWord];
        }
        for (int i = 0; i < unsignedWord; ++i) {
            if (SpotAnim.cache[i] == null) {
                SpotAnim.cache[i] = new SpotAnim();
            }
            SpotAnim.cache[i].anInt404 = i;
            SpotAnim.cache[i].readValues(stream);
        }
        SpotAnim.cache[1247] = new SpotAnim();
        SpotAnim.cache[1247].anInt405 = 60776;
        SpotAnim.cache[1247].anInt406 = 4001;
        SpotAnim.cache[1247].aAnimation_407 = Animation.anims[4001];
        SpotAnim.cache[1248] = new SpotAnim();
        SpotAnim.cache[1248].anInt405 = 60776;
        SpotAnim.cache[1248].anInt406 = 4002;
        SpotAnim.cache[1248].aAnimation_407 = Animation.anims[4002];
    }
    
    private void readValues(final Stream stream) {
        this.anInt406 = stream.readUnsignedWord();
        this.anInt405 = stream.readUnsignedWord();
        if (Animation.anims != null && this.anInt406 != 65535 && this.anInt406 != -1) {
            this.aAnimation_407 = Animation.anims[this.anInt406];
        }
        this.anInt410 = stream.readUnsignedByte();
        this.anInt411 = stream.readUnsignedByte();
        final int unsignedWord = stream.readUnsignedWord();
        if (unsignedWord != 65535) {
            for (int i = 0; i < unsignedWord; ++i) {
                this.anIntArray409[i] = stream.readUnsignedWord();
            }
            for (int j = 0; j < unsignedWord; ++j) {
                this.anIntArray408[j] = stream.readUnsignedWord();
            }
        }
    }
    
    public Model getModel() {
        final Model model = (Model)SpotAnim.aMRUNodes_415.insertFromCache(this.anInt404);
        if (model != null) {
            return model;
        }
        final Model method462 = Model.method462(this.anInt405);
        if (method462 == null) {
            return null;
        }
        for (int i = 0; i < 6; ++i) {
            if (this.anIntArray408[0] != 0) {
                method462.method476(this.anIntArray408[i], this.anIntArray409[i]);
            }
        }
        SpotAnim.aMRUNodes_415.removeFromCache(method462, this.anInt404);
        return method462;
    }
    
    private SpotAnim() {
        this.anInt400 = 9;
        this.anInt406 = -1;
        this.anIntArray408 = new int[6];
        this.anIntArray409 = new int[6];
        this.anInt410 = 128;
        this.anInt411 = 128;
    }
    
    static {
        SpotAnim.loc = SignLink.findcachedir() + "Data/Animation/";
        SpotAnim.aMRUNodes_415 = new MRUNodes(30);
    }
}
