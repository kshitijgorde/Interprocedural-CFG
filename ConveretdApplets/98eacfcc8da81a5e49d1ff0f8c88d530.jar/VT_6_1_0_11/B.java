// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public final class B extends bO implements dv
{
    public B() {
    }
    
    public B(final URLConnection urlConnection, InputStream inputStream) {
        if (urlConnection.getHeaderField("encoding") != null) {
            final String headerField;
            if ((headerField = urlConnection.getHeaderField("avg_bit_rate")) != null) {
                this.i(Integer.parseInt(headerField));
            }
            final String headerField2;
            if ((headerField2 = urlConnection.getHeaderField("block_size")) != null) {
                this.h(Integer.parseInt(headerField2));
            }
            final String headerField3;
            if ((headerField3 = urlConnection.getHeaderField("channels")) != null) {
                this.c(Integer.parseInt(headerField3));
            }
            final String headerField4;
            if ((headerField4 = urlConnection.getHeaderField("data_size")) != null) {
                this.e(Integer.parseInt(headerField4));
            }
            final String headerField5;
            if ((headerField5 = urlConnection.getHeaderField("encoding")) != null) {
                this.b(Integer.parseInt(headerField5));
            }
            final String headerField6;
            if ((headerField6 = urlConnection.getHeaderField("file_format")) != null) {
                this.a(Integer.parseInt(headerField6));
            }
            final String headerField7;
            if ((headerField7 = urlConnection.getHeaderField("is_vbr")) != null) {
                this.a(headerField7.equalsIgnoreCase("true"));
            }
            final String headerField8;
            if ((headerField8 = urlConnection.getHeaderField("nb_samples")) != null) {
                this.f(Integer.parseInt(headerField8));
            }
            final String headerField9;
            if ((headerField9 = urlConnection.getHeaderField("sample_rate")) != null) {
                this.d(Integer.parseInt(headerField9));
            }
            final String headerField10;
            if ((headerField10 = urlConnection.getHeaderField("sample_size")) != null) {
                this.g(Integer.parseInt(headerField10));
            }
            final String headerField11;
            if ((headerField11 = urlConnection.getHeaderField("codec_params")) != null) {
                this.a(headerField11);
            }
        }
        else {
            try {
                inputStream = inputStream;
                inputStream.mark(200);
                try {
                    this.a(new x(inputStream));
                }
                catch (y y) {
                    throw new IllegalArgumentException("File format is not WAV. Only WAV is supported - " + y);
                }
                inputStream.reset();
            }
            catch (IOException ex) {
                throw new IllegalArgumentException("Error while reading in the InputStream to determine the audio headers - " + ex);
            }
        }
    }
    
    public B(final dv dv) {
        this.a(dv.c());
        this.b(dv.d());
        this.c(dv.f());
        this.d(dv.g());
        this.e(dv.h());
        this.g(dv.k());
        this.h(dv.l());
        this.i(dv.m());
        this.a(dv.j());
        this.f(dv.i());
        this.a(dv.e());
    }
    
    public B(final bE be) {
        this.b();
        final String b;
        if ((b = be.b("avg_bit_rate")) != null) {
            this.i(Integer.parseInt(b));
        }
        final String b2;
        if ((b2 = be.b("block_size")) != null) {
            this.h(Integer.parseInt(b2));
        }
        final String b3;
        if ((b3 = be.b("channels")) != null) {
            this.c(Integer.parseInt(b3));
        }
        final String b4;
        if ((b4 = be.b("data_size")) != null) {
            this.e(Integer.parseInt(b4));
        }
        final String b5;
        if ((b5 = be.b("encoding")) != null) {
            this.b(Integer.parseInt(b5));
        }
        final String b6;
        if ((b6 = be.b("file_format")) != null) {
            this.a(Integer.parseInt(b6));
        }
        final String b7;
        if ((b7 = be.b("is_vbr")) != null) {
            this.a(b7.equalsIgnoreCase("true"));
        }
        final String b8;
        if ((b8 = be.b("nb_samples")) != null) {
            this.f(Integer.parseInt(b8));
        }
        final String b9;
        if ((b9 = be.b("sample_rate")) != null) {
            this.d(Integer.parseInt(b9));
        }
        final String b10;
        if ((b10 = be.b("sample_size")) != null) {
            this.g(Integer.parseInt(b10));
        }
        final String b11;
        if ((b11 = be.b("codec_params")) != null) {
            this.a(b11);
        }
    }
    
    public final bE a() {
        final bE be = new bE();
        final int m;
        if ((m = this.m()) != -1) {
            be.a("avg_bit_rate", "" + m);
        }
        final int l;
        if ((l = this.l()) != -1) {
            be.a("block_size", "" + l);
        }
        final int f;
        if ((f = this.f()) != -1) {
            be.a("channels", "" + f);
        }
        final int h;
        if ((h = this.h()) != -1) {
            be.a("data_size", "" + h);
        }
        final int d;
        if ((d = this.d()) != -1) {
            be.a("encoding", "" + d);
        }
        final int c;
        if ((c = this.c()) != -1) {
            be.a("file_format", "" + c);
        }
        final boolean j;
        if (j = this.j()) {
            be.a("is_vbr", "" + j);
        }
        final int i;
        if ((i = this.i()) != -1) {
            be.a("nb_samples", "" + i);
        }
        final int g;
        if ((g = this.g()) != -1) {
            be.a("sample_rate", "" + g);
        }
        final int k;
        if ((k = this.k()) != -1) {
            be.a("sample_size", "" + k);
        }
        final String e;
        if ((e = this.e()) != null && !this.e().trim().equals("")) {
            be.a("codec_params", e);
        }
        return be;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("AudioFormat, file_format=" + this.c() + ", encoding=" + this.d() + ", channels=" + this.f() + ", sample_rate=" + this.g() + ", data_size=" + this.h() + ", sample_size=" + this.k() + ", block_size=" + this.l() + ", avg_bit_rate=" + this.m() + ", vbr=" + this.j() + ", nb_samples=" + this.i() + ", codec_params=" + this.e());
        return sb.toString();
    }
}
