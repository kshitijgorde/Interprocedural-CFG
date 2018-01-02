import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class StarFld
{
    private static final double TWOPI = 6.283185307179586;
    int itsnumber_of_stars;
    Particle[] elements;
    
    public void draw_star_field(final Graphics graphics, final int n, final int n2, final Array3D array3D) {
        for (int i = 0; i < this.itsnumber_of_stars; ++i) {
            this.elements[i].draw_particles(graphics, n, n2, array3D);
        }
    }
    
    public void set_star_positions_for_level(final int n) {
        switch (n) {
            case 1:
            case 4: {
                int n2 = 0;
                do {
                    int n3 = 0;
                    for (int i = 0; i < this.itsnumber_of_stars / 8; ++i) {
                        this.elements[i].com[n2].centre.set_Array3D(-123 + n3 * 27, 150.0, 200 - n2 * 10);
                        ++n3;
                    }
                    int n4 = 0;
                    for (int j = this.itsnumber_of_stars / 8; j < 2 * this.itsnumber_of_stars / 8; ++j) {
                        this.elements[j].com[n2].centre.set_Array3D(-123 + n4 * 27, -150.0, 200 - n2 * 10);
                        ++n4;
                    }
                    int n5 = 0;
                    for (int k = 2 * this.itsnumber_of_stars / 8; k < 3 * this.itsnumber_of_stars / 8; ++k) {
                        this.elements[k].com[n2].centre.set_Array3D(150.0, -123 + n5 * 27, 200 - n2 * 10);
                        ++n5;
                    }
                    int n6 = 0;
                    for (int l = 3 * this.itsnumber_of_stars / 8; l < 4 * this.itsnumber_of_stars / 8; ++l) {
                        this.elements[l].com[n2].centre.set_Array3D(-150.0, -123 + n6 * 27, 200 - n2 * 10);
                        ++n6;
                    }
                    int n7 = 0;
                    for (int n8 = 4 * this.itsnumber_of_stars / 8; n8 < 5 * this.itsnumber_of_stars / 8; ++n8) {
                        this.elements[n8].com[n2].centre.set_Array3D(-123 + n7 * 27, 150.0, 0 - n2 * 10);
                        ++n7;
                    }
                    int n9 = 0;
                    for (int n10 = 5 * this.itsnumber_of_stars / 8; n10 < 6 * this.itsnumber_of_stars / 8; ++n10) {
                        this.elements[n10].com[n2].centre.set_Array3D(-123 + n9 * 27, -150.0, 0 - n2 * 10);
                        ++n9;
                    }
                    int n11 = 0;
                    for (int n12 = 6 * this.itsnumber_of_stars / 8; n12 < 7 * this.itsnumber_of_stars / 8; ++n12) {
                        this.elements[n12].com[n2].centre.set_Array3D(150.0, -123 + n11 * 27, 0 - n2 * 10);
                        ++n11;
                    }
                    int n13 = 0;
                    for (int n14 = 7 * this.itsnumber_of_stars / 8; n14 < 8 * this.itsnumber_of_stars / 8; ++n14) {
                        this.elements[n14].com[n2].centre.set_Array3D(-150.0, -123 + n13 * 27, 0 - n2 * 10);
                        ++n13;
                    }
                } while (++n2 < 3);
            }
            case 2: {
                int n15 = 0;
                do {
                    int n16 = 0;
                    for (int n17 = 0; n17 < this.itsnumber_of_stars / 2; ++n17) {
                        this.elements[n17].com[n15].centre.set_Array3D(180.0 * Math.sin(0.017453292519943295 * n16 * 9.0), 180.0 * Math.cos(0.017453292519943295 * n16 * 9.0), 200 - n15 * 10);
                        ++n16;
                    }
                    int n18 = 0;
                    for (int n19 = this.itsnumber_of_stars / 2; n19 < 2 * this.itsnumber_of_stars / 2; ++n19) {
                        this.elements[n19].com[n15].centre.set_Array3D(180.0 * Math.sin(0.017453292519943295 * n18 * 9.0), 180.0 * Math.cos(0.017453292519943295 * n18 * 9.0), 0 - n15 * 10);
                        ++n18;
                    }
                } while (++n15 < 3);
            }
            case 3: {
                int n20 = 0;
                do {
                    int n21 = 0;
                    for (int n22 = 0; n22 < this.itsnumber_of_stars / 8; ++n22) {
                        this.elements[n22].com[n20].centre.set_Array3D(-123 + n21 * 27, 150.0, 200 - n20 * 10);
                        ++n21;
                    }
                    int n23 = 0;
                    for (int n24 = this.itsnumber_of_stars / 8; n24 < 2 * this.itsnumber_of_stars / 8; ++n24) {
                        this.elements[n24].com[n20].centre.set_Array3D(-123 + n23 * 27, -150.0, 200 - n20 * 10);
                        ++n23;
                    }
                    int n25 = 0;
                    for (int n26 = 2 * this.itsnumber_of_stars / 8; n26 < 3 * this.itsnumber_of_stars / 8; ++n26) {
                        this.elements[n26].com[n20].centre.set_Array3D(150.0, -123 + n25 * 27, 200 - n20 * 10);
                        ++n25;
                    }
                    int n27 = 0;
                    for (int n28 = 3 * this.itsnumber_of_stars / 8; n28 < 4 * this.itsnumber_of_stars / 8; ++n28) {
                        this.elements[n28].com[n20].centre.set_Array3D(-150.0, -123 + n27 * 27, 200 - n20 * 10);
                        ++n27;
                    }
                    int n29 = 0;
                    for (int n30 = this.itsnumber_of_stars / 2; n30 < this.itsnumber_of_stars; ++n30) {
                        this.elements[n30].com[n20].centre.set_Array3D(180.0 * Math.sin(0.017453292519943295 * n29 * 9.0), 180.0 * Math.cos(0.017453292519943295 * n29 * 9.0), 0 - n20 * 10);
                        ++n29;
                    }
                } while (++n20 < 3);
            }
            default: {}
        }
    }
    
    public StarFld(final int itsnumber_of_stars) {
        this.itsnumber_of_stars = itsnumber_of_stars;
        this.elements = new Particle[itsnumber_of_stars];
        int n = 0;
        for (int i = 0; i < itsnumber_of_stars / 8; ++i) {
            this.elements[i] = new Particle(-123 + n * 27, 150, 200, 10, 2);
            ++n;
        }
        int n2 = 0;
        for (int j = itsnumber_of_stars / 8; j < 2 * itsnumber_of_stars / 8; ++j) {
            this.elements[j] = new Particle(-123 + n2 * 27, -150, 200, 10, 2);
            ++n2;
        }
        int n3 = 0;
        for (int k = 2 * itsnumber_of_stars / 8; k < 3 * itsnumber_of_stars / 8; ++k) {
            this.elements[k] = new Particle(150, -123 + n3 * 27, 200, 10, 2);
            ++n3;
        }
        int n4 = 0;
        for (int l = 3 * itsnumber_of_stars / 8; l < 4 * itsnumber_of_stars / 8; ++l) {
            this.elements[l] = new Particle(-150, -123 + n4 * 27, 200, 10, 2);
            ++n4;
        }
        int n5 = 0;
        for (int n6 = 4 * itsnumber_of_stars / 8; n6 < 5 * itsnumber_of_stars / 8; ++n6) {
            this.elements[n6] = new Particle(-123 + n5 * 27, 150, 0, 10, 2);
            ++n5;
        }
        int n7 = 0;
        for (int n8 = 5 * itsnumber_of_stars / 8; n8 < 6 * itsnumber_of_stars / 8; ++n8) {
            this.elements[n8] = new Particle(-123 + n7 * 27, -150, 0, 10, 2);
            ++n7;
        }
        int n9 = 0;
        for (int n10 = 6 * itsnumber_of_stars / 8; n10 < 7 * itsnumber_of_stars / 8; ++n10) {
            this.elements[n10] = new Particle(150, -123 + n9 * 27, 0, 10, 2);
            ++n9;
        }
        int n11 = 0;
        for (int n12 = 7 * itsnumber_of_stars / 8; n12 < 8 * itsnumber_of_stars / 8; ++n12) {
            this.elements[n12] = new Particle(-150, -123 + n11 * 27, 0, 10, 2);
            ++n11;
        }
    }
    
    public int get_number_of_stars() {
        return this.itsnumber_of_stars;
    }
}
