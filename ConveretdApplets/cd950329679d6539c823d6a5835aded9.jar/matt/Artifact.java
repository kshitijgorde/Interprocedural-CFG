// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class Artifact
{
    public static final int NO_ARTIFACT = 0;
    public static final int SAME_ARTIFACT = 1;
    public static final int ROLL_ARTIFACT = 2;
    public static final int CUT_ARTIFACT = 3;
    public static final int CRAN_ARTIFACT = 4;
    public static final int TRILL_ARTIFACT = 5;
    public static final int VARIATION_ARTIFACT = 6;
    public static final int ORNAMENT_ARTIFACT = 7;
    public static final int BREATH_ARTIFACT = 8;
    public static final int ACCIDENTAL_ARTIFACT = 9;
    public static final int TRIPLET_ARTIFACT = 10;
    public static final int POSTFIX_ARTIFACT = 11;
    public static final int PREFIX_ARTIFACT = 12;
    private int id;
    private int bar;
    private int position;
    private String part;
    private int typeID;
    private String type;
    private String unornamentedNGram;
    private String ornamentedNGram;
    private int duration;
    private String rhythm;
    private String tuneName;
    private String musician;
    private String key;
    public static final String[] ARTIFACTS;
    public static int nextId;
    
    public Artifact() {
    }
    
    public Artifact(final int bar, final String part, final int typeID, final int duration, final int position, final String uNGram, final String oNGram, final String key, final String tuneName, final String musician, final String rhythm) {
        this.id = Artifact.nextId++;
        this.bar = bar;
        this.part = part;
        this.typeID = typeID;
        this.duration = duration;
        this.position = position;
        this.unornamentedNGram = uNGram;
        this.ornamentedNGram = oNGram;
        this.key = key;
        this.tuneName = tuneName;
        this.musician = musician;
        this.rhythm = rhythm;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getBar() {
        return this.bar;
    }
    
    public void setBar(final int bar) {
        this.bar = bar;
    }
    
    public String getPart() {
        return this.part;
    }
    
    public void setPart(final String part) {
        this.part = part;
    }
    
    public int getTypeID() {
        return this.typeID;
    }
    
    public void setTypeID(final int typeID) {
        this.typeID = typeID;
    }
    
    public String getType() {
        return Artifact.ARTIFACTS[this.typeID];
    }
    
    public String getUnornamentedNGram() {
        return this.unornamentedNGram;
    }
    
    public void setUnornamentedNGram(final String unornamentedNGram) {
        this.unornamentedNGram = unornamentedNGram;
    }
    
    public String getOrnamentedNGram() {
        return this.ornamentedNGram;
    }
    
    public void setOrnamentedNGram(final String ornamentedNGram) {
        this.ornamentedNGram = ornamentedNGram;
    }
    
    public void writeTag(final String tagName, final String tagValue, final StringBuffer to) {
        to.append("\t<" + tagName + ">" + tagValue + "</" + tagName + ">\n");
    }
    
    public String getTuneName() {
        return this.tuneName;
    }
    
    public void setTuneName(final String tuneName) {
        this.tuneName = tuneName;
    }
    
    public String getMusician() {
        return this.musician;
    }
    
    public void setMusician(final String musician) {
        this.musician = musician;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public String getRhythm() {
        return this.rhythm;
    }
    
    public void setRhythm(final String rhythm) {
        this.rhythm = rhythm;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(final int duration) {
        this.duration = duration;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<case name=\"" + this.id + "\">\n");
        this.writeTag("tuneName", this.tuneName, sb);
        this.writeTag("musician", this.musician, sb);
        this.writeTag("key", this.key, sb);
        this.writeTag("rhythm", this.rhythm, sb);
        this.writeTag("artifact", this.getType(), sb);
        this.writeTag("part", this.part, sb);
        this.writeTag("bar", new Integer(this.bar).toString(), sb);
        this.writeTag("position", new Integer(this.position).toString(), sb);
        this.writeTag("duration", new Integer(this.duration).toString(), sb);
        this.writeTag("unornamented", this.unornamentedNGram, sb);
        this.writeTag("ornamented", this.ornamentedNGram, sb);
        sb.append("</case>\n\n");
        return sb.toString();
    }
    
    static {
        ARTIFACTS = new String[] { "Nothing", "Same", "Roll", "Cut", "Cran", "Trill", "Variation", "Ornament", "Breath", "Accidental", "Triplet", "Postfix", "Prefix" };
        Artifact.nextId = 0;
    }
}
