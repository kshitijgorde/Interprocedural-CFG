// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.awt.image.BufferedImage;
import java.net.URL;
import org.collada.colladaschema.CommonNewparamType;
import java.util.Iterator;
import java.util.List;
import javax.media.j3d.Texture;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Texture2D;
import org.jdesktop.j3d.loaders.collada.utils.Collada14LoaderUtils;
import org.collada.colladaschema.FxSurfaceInitFromCommon;
import org.collada.colladaschema.Image;
import javax.vecmath.Color3f;
import org.collada.colladaschema.CommonFloatOrParamType;
import org.collada.colladaschema.CommonColorOrTextureType;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Node;
import org.collada.colladaschema.ProfileCOMMON;
import javax.media.j3d.Appearance;

public class PhongProcessor extends Processor
{
    private Appearance app;
    private ProfileCOMMON.Technique.Phong phong;
    
    public PhongProcessor(final ProfileCOMMON.Technique.Phong phong, final Processor parent) {
        super(phong, parent);
        this.app = null;
        this.phong = phong;
    }
    
    @Override
    public void create(final Node parent) {
        if (this.app != null) {
            ((Shape3D)parent).setAppearance(this.app);
            return;
        }
        if (parent instanceof Shape3D) {
            final Shape3D s3d = (Shape3D)parent;
            this.app = s3d.getAppearance();
        }
        if (this.app == null) {
            this.app = new Appearance();
        }
        if (this.phong != null) {
            this.app.setMaterial(new Material());
            CommonColorOrTextureType fccott = this.phong.getAmbient();
            if (fccott != null) {
                this.processColorOrTexture(fccott, this.app, Type.AMBIENT);
            }
            fccott = this.phong.getDiffuse();
            if (fccott != null) {
                this.processColorOrTexture(fccott, this.app, Type.DIFFUSE);
            }
            fccott = this.phong.getEmission();
            if (fccott != null) {
                this.processColorOrTexture(fccott, this.app, Type.EMISSION);
            }
            fccott = this.phong.getSpecular();
            if (fccott != null) {
                this.processColorOrTexture(fccott, this.app, Type.SPECULAR);
            }
            final CommonFloatOrParamType transparency = this.phong.getTransparency();
            if (transparency != null) {
                final CommonFloatOrParamType.Float f = transparency.getFloat();
                if (f != null) {
                    float fl = (float)f.getValue();
                    if (fl == 1.0f) {
                        fl = 0.0f;
                    }
                    if (fl > 0.0f) {
                        TransparencyAttributes ta = this.app.getTransparencyAttributes();
                        if (ta == null) {
                            ta = new TransparencyAttributes();
                            this.app.setTransparencyAttributes(ta);
                        }
                        ta.setTransparency(fl);
                        ta.setTransparencyMode(1);
                        ta.setTransparencyMode(2);
                    }
                }
            }
            final CommonFloatOrParamType shininess = this.phong.getShininess();
            if (shininess != null) {
                final CommonFloatOrParamType.Float f2 = shininess.getFloat();
                if (f2 != null && f2.getValue() > 0.0) {
                    this.app.getMaterial().setShininess((float)f2.getValue());
                }
            }
        }
        ((Shape3D)parent).setAppearance(this.app);
    }
    
    private void processColorOrTexture(final CommonColorOrTextureType colorOrTexture, final Appearance app, final Type t) {
        final CommonColorOrTextureType.Color color = colorOrTexture.getColor();
        if (color != null) {
            final Material mat = app.getMaterial();
            final List<Double> c = color.getValues();
            final float[] result = new float[c.size()];
            int i = 0;
            for (final Double d : c) {
                result[i++] = (float)(Object)d;
            }
            switch (t) {
                case AMBIENT: {
                    mat.setAmbientColor(new Color3f(result));
                    break;
                }
                case DIFFUSE: {
                    if (result.length == 3) {
                        mat.setDiffuseColor(new Color3f(result));
                        break;
                    }
                    mat.setDiffuseColor(result[0], result[1], result[2], result[3]);
                    break;
                }
                case EMISSION: {
                    mat.setEmissiveColor(new Color3f(result));
                    break;
                }
                case SPECULAR: {
                    mat.setSpecularColor(new Color3f(result));
                    break;
                }
            }
        }
        else {
            final CommonColorOrTextureType.Texture te = colorOrTexture.getTexture();
            String tex = te.getTexture();
            System.out.println(tex);
            final ElementCache ec = ElementCache.cache();
            CommonNewparamType cnt = ec.getNewParam(tex);
            tex = cnt.getSampler2D().getSource();
            cnt = ec.getNewParam(tex);
            final Image im = (Image)cnt.getSurface().getInitFroms().get(0).getValue();
            tex = im.getId();
            final String path = ElementCache.cache().getImage(tex);
            final URL url = ElementCache.cache().getLoadingURL();
            final BufferedImage image = Collada14LoaderUtils.readImage(url, path);
            if (image != null) {
                final Texture2D tex2d = (Texture2D)new TextureLoader(image).getTexture();
                app.setTexture((Texture)tex2d);
            }
        }
    }
    
    private enum Type
    {
        AMBIENT("AMBIENT", 0), 
        EMISSION("EMISSION", 1), 
        DIFFUSE("DIFFUSE", 2), 
        SPECULAR("SPECULAR", 3);
        
        private Type(final String s, final int n) {
        }
    }
}
