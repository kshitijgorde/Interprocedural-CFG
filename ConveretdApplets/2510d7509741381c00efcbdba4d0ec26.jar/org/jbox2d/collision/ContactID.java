// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class ContactID
{
    public int key;
    public Features features;
    
    public void zero() {
        this.key = 0;
        this.features.flip = 0;
        this.features.incidentEdge = 0;
        this.features.incidentVertex = 0;
        this.features.referenceFace = 0;
    }
    
    public ContactID() {
        this.key = 0;
        this.features = new Features();
    }
    
    public ContactID(final ContactID c) {
        this.key = c.key;
        this.features = new Features(c.features);
    }
    
    public class Features
    {
        public int referenceFace;
        public int incidentEdge;
        public int incidentVertex;
        public int flip;
        
        public Features() {
            final boolean b = false;
            this.flip = (b ? 1 : 0);
            this.incidentVertex = (b ? 1 : 0);
            this.incidentEdge = (b ? 1 : 0);
            this.referenceFace = (b ? 1 : 0);
        }
        
        public Features(final Features f) {
            this.referenceFace = f.referenceFace;
            this.incidentEdge = f.incidentEdge;
            this.incidentVertex = f.incidentVertex;
            this.flip = f.flip;
        }
        
        public void set(final Features f) {
            this.referenceFace = f.referenceFace;
            this.incidentEdge = f.incidentEdge;
            this.incidentVertex = f.incidentVertex;
            this.flip = f.flip;
        }
        
        public boolean isEqual(final Features f) {
            return this.referenceFace == f.referenceFace && this.incidentEdge == f.incidentEdge && this.incidentVertex == f.incidentVertex && this.flip == f.flip;
        }
        
        public String toString() {
            final String s = "Features: (" + this.flip + " ," + this.incidentEdge + " ," + this.incidentVertex + " ," + this.referenceFace + ")";
            return s;
        }
    }
}
