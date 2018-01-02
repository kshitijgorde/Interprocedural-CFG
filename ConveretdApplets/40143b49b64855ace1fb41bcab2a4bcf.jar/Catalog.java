import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Catalog
{
    Vector RECORDs;
    Field[] FIELDs;
    Index[] Indexes;
    int[] FieldsIndexed;
    int[] FieldsFiltered;
    int[] FieldsVisible;
    int[] FieldsSORTED;
    int[] FieldsWIDTH;
    int[] FieldsAcWIDTH;
    
    public void AddToCatalog(final Record record) {
        this.RECORDs.addElement(record);
        for (int i = 0; i < this.Indexes.length; ++i) {
            this.Indexes[i].AddToIndex(record, record.FIELDvalue[this.FieldsIndexed[i]]);
        }
    }
    
    public Catalog(final Field[] fielDs) {
        this.RECORDs = new Vector();
        this.FIELDs = fielDs;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.FIELDs.length; ++i) {
            if (this.FIELDs[i].INDEXED) {
                ++n;
            }
            if (this.FIELDs[i].FILTER) {
                ++n2;
            }
            if (this.FIELDs[i].VISIBLE) {
                ++n3;
            }
            if (this.FIELDs[i].SORTED) {
                ++n4;
            }
        }
        this.Indexes = new Index[n];
        this.FieldsIndexed = new int[n];
        this.FieldsFiltered = new int[n2];
        this.FieldsVisible = new int[n3];
        this.FieldsSORTED = new int[n4];
        this.FieldsAcWIDTH = new int[n3];
        this.FieldsWIDTH = new int[n3];
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        for (int j = 0; j < this.FIELDs.length; ++j) {
            if (this.FIELDs[j].VISIBLE) {
                n9 += this.FIELDs[j].Width;
                this.FieldsAcWIDTH[n7] = n9;
                this.FieldsWIDTH[n7] = this.FIELDs[j].Width;
                this.FieldsVisible[n7] = j;
                ++n7;
            }
            if (this.FIELDs[j].INDEXED) {
                this.FieldsIndexed[n5] = j;
                this.Indexes[n5] = new Index(this.FIELDs[j].Name);
                if (this.FIELDs[j].FILTER) {
                    this.FieldsFiltered[n6] = n5;
                    ++n6;
                }
                ++n5;
            }
            if (this.FIELDs[j].SORTED) {
                this.FieldsSORTED[n8] = j;
                ++n8;
            }
        }
    }
    
    public int getFIELD(final String s) {
        int n;
        for (n = 0; n < this.FIELDs.length && !s.equalsIgnoreCase(this.FIELDs[n].Name); ++n) {}
        if (n < this.FIELDs.length) {
            return n;
        }
        return -1;
    }
}
