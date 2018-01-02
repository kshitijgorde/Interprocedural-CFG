// 
// Decompiled by Procyon v0.5.30
// 

package bbpi.dnc.simstats;

import bbpi.dnc.util.TagUtils;

public class Record
{
    private String versao;
    private StringBuffer inputs;
    private StringBuffer outputs;
    private StringBuffer auxs;
    
    public Record() {
        this.inputs = new StringBuffer();
        this.outputs = new StringBuffer();
        this.auxs = new StringBuffer();
        this.versao = null;
    }
    
    public void addAux(final String s, final String s2) {
        this.auxs.append(TagUtils.createTag("field", "name", s, s2));
    }
    
    public void addInput(final String s, final String s2) {
        this.inputs.append(TagUtils.createTag("field", "name", s, s2));
    }
    
    public void addOutput(final String s, final String s2) {
        this.outputs.append(TagUtils.createTag("field", "name", s, s2));
    }
    
    public void clear() {
        this.inputs = new StringBuffer();
        this.outputs = new StringBuffer();
        this.auxs = new StringBuffer();
    }
    
    public String getVersao() {
        return this.versao;
    }
    
    public void setVersao(final String versao) {
        this.versao = versao;
    }
    
    public String toString() {
        String tag;
        if (this.versao == null) {
            tag = "";
        }
        else {
            tag = TagUtils.createTag("versao", this.versao);
        }
        String tag2;
        if (this.inputs.length() == 0) {
            tag2 = "";
        }
        else {
            tag2 = TagUtils.createTag("input", this.inputs.toString());
        }
        String tag3;
        if (this.outputs.length() == 0) {
            tag3 = "";
        }
        else {
            tag3 = TagUtils.createTag("output", this.outputs.toString());
        }
        String tag4;
        if (this.auxs.length() == 0) {
            tag4 = "";
        }
        else {
            tag4 = TagUtils.createTag("aux", this.auxs.toString());
        }
        return "<?xml version=\"1.0\" ?>" + TagUtils.createTag("simulacao", String.valueOf(tag) + tag2 + tag3 + tag4);
    }
}
