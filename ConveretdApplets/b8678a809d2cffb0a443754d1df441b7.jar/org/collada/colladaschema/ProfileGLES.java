// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlList;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "newparamsAndImages", "techniques", "extras" })
public class ProfileGLES
{
    protected Asset asset;
    @XmlElements({ @XmlElement(name = "image", type = Image.class), @XmlElement(name = "newparam", type = GlesNewparam.class) })
    protected List<Object> newparamsAndImages;
    @XmlElement(name = "technique", required = true)
    protected List<Technique> techniques;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String platform;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<Object> getNewparamsAndImages() {
        if (this.newparamsAndImages == null) {
            this.newparamsAndImages = new ArrayList<Object>();
        }
        return this.newparamsAndImages;
    }
    
    public List<Technique> getTechniques() {
        if (this.techniques == null) {
            this.techniques = new ArrayList<Technique>();
        }
        return this.techniques;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public String getPlatform() {
        if (this.platform == null) {
            return "PC";
        }
        return this.platform;
    }
    
    public void setPlatform(final String value) {
        this.platform = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "asset", "annotates", "imagesAndNewparamsAndSetparams", "pass", "extras" })
    public static class Technique
    {
        protected Asset asset;
        @XmlElement(name = "annotate")
        protected List<FxAnnotateCommon> annotates;
        @XmlElements({ @XmlElement(name = "setparam", type = Setparam.class), @XmlElement(name = "image", type = Image.class), @XmlElement(name = "newparam", type = GlesNewparam.class) })
        protected List<Object> imagesAndNewparamsAndSetparams;
        @XmlElement(required = true)
        protected List<Pass> pass;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sid;
        
        public Asset getAsset() {
            return this.asset;
        }
        
        public void setAsset(final Asset value) {
            this.asset = value;
        }
        
        public List<FxAnnotateCommon> getAnnotates() {
            if (this.annotates == null) {
                this.annotates = new ArrayList<FxAnnotateCommon>();
            }
            return this.annotates;
        }
        
        public List<Object> getImagesAndNewparamsAndSetparams() {
            if (this.imagesAndNewparamsAndSetparams == null) {
                this.imagesAndNewparamsAndSetparams = new ArrayList<Object>();
            }
            return this.imagesAndNewparamsAndSetparams;
        }
        
        public List<Pass> getPass() {
            if (this.pass == null) {
                this.pass = new ArrayList<Pass>();
            }
            return this.pass;
        }
        
        public List<Extra> getExtras() {
            if (this.extras == null) {
                this.extras = new ArrayList<Extra>();
            }
            return this.extras;
        }
        
        public String getId() {
            return this.id;
        }
        
        public void setId(final String value) {
            this.id = value;
        }
        
        public String getSid() {
            return this.sid;
        }
        
        public void setSid(final String value) {
            this.sid = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "annotates", "colorTarget", "depthTarget", "stencilTarget", "colorClear", "depthClear", "stencilClear", "draw", "lightSpotCutofvesAndDepthTestEnablesAndPointSizes", "extras" })
        public static class Pass
        {
            @XmlElement(name = "annotate")
            protected List<FxAnnotateCommon> annotates;
            @XmlElement(name = "color_target")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String colorTarget;
            @XmlElement(name = "depth_target")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String depthTarget;
            @XmlElement(name = "stencil_target")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String stencilTarget;
            @XmlList
            @XmlElement(name = "color_clear", type = Double.class)
            protected List<Double> colorClear;
            @XmlElement(name = "depth_clear")
            protected Double depthClear;
            @XmlElement(name = "stencil_clear")
            protected Byte stencilClear;
            protected String draw;
            @XmlElements({ @XmlElement(name = "depth_mask", type = DepthMask.class), @XmlElement(name = "model_view_matrix", type = ModelViewMatrix.class), @XmlElement(name = "clip_plane", type = ClipPlane.class), @XmlElement(name = "color_material_enable", type = ColorMaterialEnable.class), @XmlElement(name = "depth_func", type = DepthFunc.class), @XmlElement(name = "material_shininess", type = MaterialShininess.class), @XmlElement(name = "light_spot_direction", type = LightSpotDirection.class), @XmlElement(name = "cull_face", type = CullFace.class), @XmlElement(name = "material_ambient", type = MaterialAmbient.class), @XmlElement(name = "line_width", type = LineWidth.class), @XmlElement(name = "fog_color", type = FogColor.class), @XmlElement(name = "point_fade_threshold_size", type = PointFadeThresholdSize.class), @XmlElement(name = "fog_end", type = FogEnd.class), @XmlElement(name = "polygon_offset", type = PolygonOffset.class), @XmlElement(name = "sample_alpha_to_coverage_enable", type = SampleAlphaToCoverageEnable.class), @XmlElement(name = "line_smooth_enable", type = LineSmoothEnable.class), @XmlElement(name = "fog_start", type = FogStart.class), @XmlElement(name = "dither_enable", type = DitherEnable.class), @XmlElement(name = "polygon_offset_fill_enable", type = PolygonOffsetFillEnable.class), @XmlElement(name = "front_face", type = FrontFace.class), @XmlElement(name = "light_ambient", type = LightAmbient.class), @XmlElement(name = "fog_density", type = FogDensity.class), @XmlElement(name = "light_enable", type = LightEnable.class), @XmlElement(name = "blend_enable", type = BlendEnable.class), @XmlElement(name = "sample_alpha_to_one_enable", type = SampleAlphaToOneEnable.class), @XmlElement(name = "point_size_min", type = PointSizeMin.class), @XmlElement(name = "depth_test_enable", type = DepthTestEnable.class), @XmlElement(name = "texture_pipeline_enable", type = TexturePipelineEnable.class), @XmlElement(name = "light_spot_exponent", type = LightSpotExponent.class), @XmlElement(name = "point_size", type = PointSize.class), @XmlElement(name = "stencil_mask", type = StencilMask.class), @XmlElement(name = "material_diffuse", type = MaterialDiffuse.class), @XmlElement(name = "light_constant_attenuation", type = LightConstantAttenuation.class), @XmlElement(name = "fog_enable", type = FogEnable.class), @XmlElement(name = "light_diffuse", type = LightDiffuse.class), @XmlElement(name = "point_size_max", type = PointSizeMax.class), @XmlElement(name = "light_position", type = LightPosition.class), @XmlElement(name = "fog_mode", type = FogMode.class), @XmlElement(name = "color_mask", type = ColorMask.class), @XmlElement(name = "point_distance_attenuation", type = PointDistanceAttenuation.class), @XmlElement(name = "light_spot_cutoff", type = LightSpotCutoff.class), @XmlElement(name = "scissor_test_enable", type = ScissorTestEnable.class), @XmlElement(name = "clear_depth", type = ClearDepth.class), @XmlElement(name = "shade_model", type = ShadeModel.class), @XmlElement(name = "clip_plane_enable", type = ClipPlaneEnable.class), @XmlElement(name = "sample_coverage_enable", type = SampleCoverageEnable.class), @XmlElement(name = "alpha_test_enable", type = AlphaTestEnable.class), @XmlElement(name = "clear_color", type = ClearColor.class), @XmlElement(name = "light_linear_attenutation", type = LightLinearAttenutation.class), @XmlElement(name = "normalize_enable", type = NormalizeEnable.class), @XmlElement(name = "stencil_op", type = StencilOp.class), @XmlElement(name = "clear_stencil", type = ClearStencil.class), @XmlElement(name = "scissor", type = Scissor.class), @XmlElement(name = "material_specular", type = MaterialSpecular.class), @XmlElement(name = "light_model_ambient", type = LightModelAmbient.class), @XmlElement(name = "logic_op", type = LogicOp.class), @XmlElement(name = "material_emission", type = MaterialEmission.class), @XmlElement(name = "projection_matrix", type = ProjectionMatrix.class), @XmlElement(name = "alpha_func", type = AlphaFunc.class), @XmlElement(name = "blend_func", type = BlendFunc.class), @XmlElement(name = "rescale_normal_enable", type = RescaleNormalEnable.class), @XmlElement(name = "light_specular", type = LightSpecular.class), @XmlElement(name = "depth_range", type = DepthRange.class), @XmlElement(name = "cull_face_enable", type = CullFaceEnable.class), @XmlElement(name = "color_logic_op_enable", type = ColorLogicOpEnable.class), @XmlElement(name = "multisample_enable", type = MultisampleEnable.class), @XmlElement(name = "light_model_two_side_enable", type = LightModelTwoSideEnable.class), @XmlElement(name = "stencil_test_enable", type = StencilTestEnable.class), @XmlElement(name = "point_smooth_enable", type = PointSmoothEnable.class), @XmlElement(name = "lighting_enable", type = LightingEnable.class), @XmlElement(name = "light_quadratic_attenuation", type = LightQuadraticAttenuation.class), @XmlElement(name = "texture_pipeline", type = TexturePipeline.class), @XmlElement(name = "stencil_func", type = StencilFunc.class) })
            protected List<Object> lightSpotCutofvesAndDepthTestEnablesAndPointSizes;
            @XmlElement(name = "extra")
            protected List<Extra> extras;
            @XmlAttribute
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String sid;
            
            public List<FxAnnotateCommon> getAnnotates() {
                if (this.annotates == null) {
                    this.annotates = new ArrayList<FxAnnotateCommon>();
                }
                return this.annotates;
            }
            
            public String getColorTarget() {
                return this.colorTarget;
            }
            
            public void setColorTarget(final String value) {
                this.colorTarget = value;
            }
            
            public String getDepthTarget() {
                return this.depthTarget;
            }
            
            public void setDepthTarget(final String value) {
                this.depthTarget = value;
            }
            
            public String getStencilTarget() {
                return this.stencilTarget;
            }
            
            public void setStencilTarget(final String value) {
                this.stencilTarget = value;
            }
            
            public List<Double> getColorClear() {
                if (this.colorClear == null) {
                    this.colorClear = new ArrayList<Double>();
                }
                return this.colorClear;
            }
            
            public Double getDepthClear() {
                return this.depthClear;
            }
            
            public void setDepthClear(final Double value) {
                this.depthClear = value;
            }
            
            public Byte getStencilClear() {
                return this.stencilClear;
            }
            
            public void setStencilClear(final Byte value) {
                this.stencilClear = value;
            }
            
            public String getDraw() {
                return this.draw;
            }
            
            public void setDraw(final String value) {
                this.draw = value;
            }
            
            public List<Object> getLightSpotCutofvesAndDepthTestEnablesAndPointSizes() {
                if (this.lightSpotCutofvesAndDepthTestEnablesAndPointSizes == null) {
                    this.lightSpotCutofvesAndDepthTestEnablesAndPointSizes = new ArrayList<Object>();
                }
                return this.lightSpotCutofvesAndDepthTestEnablesAndPointSizes;
            }
            
            public List<Extra> getExtras() {
                if (this.extras == null) {
                    this.extras = new ArrayList<Extra>();
                }
                return this.extras;
            }
            
            public String getSid() {
                return this.sid;
            }
            
            public void setSid(final String value) {
                this.sid = value;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "func", "value" })
            public static class AlphaFunc
            {
                @XmlElement(required = true)
                protected Func func;
                @XmlElement(required = true)
                protected Value value;
                
                public Func getFunc() {
                    return this.func;
                }
                
                public void setFunc(final Func value) {
                    this.func = value;
                }
                
                public Value getValue() {
                    return this.value;
                }
                
                public void setValue(final Value value) {
                    this.value = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Func
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlFuncType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlFuncType getValue() {
                        if (this.value == null) {
                            return GlFuncType.ALWAYS;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFuncType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Value
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected Float value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public float getValue() {
                        if (this.value == null) {
                            return 0.0f;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final Float value) {
                        this.value = value;
                    }
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class AlphaTestEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class BlendEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "src", "dest" })
            public static class BlendFunc
            {
                @XmlElement(required = true)
                protected Src src;
                @XmlElement(required = true)
                protected Dest dest;
                
                public Src getSrc() {
                    return this.src;
                }
                
                public void setSrc(final Src value) {
                    this.src = value;
                }
                
                public Dest getDest() {
                    return this.dest;
                }
                
                public void setDest(final Dest value) {
                    this.dest = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Dest
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlBlendType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlBlendType getValue() {
                        if (this.value == null) {
                            return GlBlendType.ZERO;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlBlendType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Src
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlBlendType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlBlendType getValue() {
                        if (this.value == null) {
                            return GlBlendType.ONE;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlBlendType value) {
                        this.value = value;
                    }
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ClearColor
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ClearDepth
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public Double getValue() {
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ClearStencil
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Long value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public Long getValue() {
                    return this.value;
                }
                
                public void setValue(final Long value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ClipPlane
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Boolean> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Boolean> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Boolean>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ClipPlaneEnable
            {
                @XmlAttribute
                protected Integer index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public Integer getIndex() {
                    return this.index;
                }
                
                public void setIndex(final Integer value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ColorLogicOpEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ColorMask
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Boolean> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Boolean> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Boolean>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ColorMaterialEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value == null || this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class CullFace
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlFaceType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlFaceType getValue() {
                    if (this.value == null) {
                        return GlFaceType.BACK;
                    }
                    return this.value;
                }
                
                public void setValue(final GlFaceType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class CullFaceEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DepthFunc
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlFuncType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlFuncType getValue() {
                    if (this.value == null) {
                        return GlFuncType.ALWAYS;
                    }
                    return this.value;
                }
                
                public void setValue(final GlFuncType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DepthMask
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DepthRange
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DepthTestEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DitherEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogColor
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogDensity
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogEnd
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogMode
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlFogType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlFogType getValue() {
                    if (this.value == null) {
                        return GlFogType.EXP;
                    }
                    return this.value;
                }
                
                public void setValue(final GlFogType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FogStart
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 0.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class FrontFace
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlFrontFaceType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlFrontFaceType getValue() {
                    if (this.value == null) {
                        return GlFrontFaceType.CCW;
                    }
                    return this.value;
                }
                
                public void setValue(final GlFrontFaceType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightAmbient
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightConstantAttenuation
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightDiffuse
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightEnable
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightLinearAttenutation
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightModelAmbient
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightModelTwoSideEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightPosition
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightQuadraticAttenuation
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightSpecular
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightSpotCutoff
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 180.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightSpotDirection
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightSpotExponent
            {
                @XmlAttribute(required = true)
                protected int index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public int getIndex() {
                    return this.index;
                }
                
                public void setIndex(final int value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 0.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightingEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LineSmoothEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LineWidth
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LogicOp
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlLogicOpType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlLogicOpType getValue() {
                    if (this.value == null) {
                        return GlLogicOpType.COPY;
                    }
                    return this.value;
                }
                
                public void setValue(final GlLogicOpType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MaterialAmbient
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MaterialDiffuse
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MaterialEmission
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MaterialShininess
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 0.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MaterialSpecular
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ModelViewMatrix
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class MultisampleEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class NormalizeEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointDistanceAttenuation
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointFadeThresholdSize
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointSize
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointSizeMax
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 1.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointSizeMin
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public double getValue() {
                    if (this.value == null) {
                        return 0.0;
                    }
                    return this.value;
                }
                
                public void setValue(final Double value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PointSmoothEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PolygonOffset
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class PolygonOffsetFillEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ProjectionMatrix
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Double> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Double>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class RescaleNormalEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class SampleAlphaToCoverageEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class SampleAlphaToOneEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class SampleCoverageEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Scissor
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Long> values;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public List<Long> getValues() {
                    if (this.values == null) {
                        this.values = new ArrayList<Long>();
                    }
                    return this.values;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ScissorTestEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ShadeModel
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlShadeModelType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlShadeModelType getValue() {
                    if (this.value == null) {
                        return GlShadeModelType.SMOOTH;
                    }
                    return this.value;
                }
                
                public void setValue(final GlShadeModelType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "func", "ref", "mask" })
            public static class StencilFunc
            {
                @XmlElement(required = true)
                protected Func func;
                @XmlElement(required = true)
                protected Ref ref;
                @XmlElement(required = true)
                protected Mask mask;
                
                public Func getFunc() {
                    return this.func;
                }
                
                public void setFunc(final Func value) {
                    this.func = value;
                }
                
                public Ref getRef() {
                    return this.ref;
                }
                
                public void setRef(final Ref value) {
                    this.ref = value;
                }
                
                public Mask getMask() {
                    return this.mask;
                }
                
                public void setMask(final Mask value) {
                    this.mask = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Func
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlFuncType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlFuncType getValue() {
                        if (this.value == null) {
                            return GlFuncType.ALWAYS;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFuncType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Mask
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected Short value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public short getValue() {
                        if (this.value == null) {
                            return 255;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final Short value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Ref
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected Short value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public short getValue() {
                        if (this.value == null) {
                            return 0;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final Short value) {
                        this.value = value;
                    }
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class StencilMask
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Long value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public long getValue() {
                    if (this.value == null) {
                        return 4294967295L;
                    }
                    return this.value;
                }
                
                public void setValue(final Long value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "fail", "zfail", "zpass" })
            public static class StencilOp
            {
                @XmlElement(required = true)
                protected Fail fail;
                @XmlElement(required = true)
                protected Zfail zfail;
                @XmlElement(required = true)
                protected Zpass zpass;
                
                public Fail getFail() {
                    return this.fail;
                }
                
                public void setFail(final Fail value) {
                    this.fail = value;
                }
                
                public Zfail getZfail() {
                    return this.zfail;
                }
                
                public void setZfail(final Zfail value) {
                    this.zfail = value;
                }
                
                public Zpass getZpass() {
                    return this.zpass;
                }
                
                public void setZpass(final Zpass value) {
                    this.zpass = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Fail
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlesStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlesStencilOpType getValue() {
                        if (this.value == null) {
                            return GlesStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlesStencilOpType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Zfail
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlesStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlesStencilOpType getValue() {
                        if (this.value == null) {
                            return GlesStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlesStencilOpType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Zpass
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlesStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlesStencilOpType getValue() {
                        if (this.value == null) {
                            return GlesStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlesStencilOpType value) {
                        this.value = value;
                    }
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class StencilTestEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "value" })
            public static class TexturePipeline
            {
                protected GlesTexturePipeline value;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                
                public GlesTexturePipeline getValue() {
                    return this.value;
                }
                
                public void setValue(final GlesTexturePipeline value) {
                    this.value = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class TexturePipelineEnable
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public boolean isValue() {
                    return this.value != null && this.value;
                }
                
                public void setValue(final Boolean value) {
                    this.value = value;
                }
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "annotates", "float4X1", "float1X1", "_enum", "bool3", "float1X3", "bool2", "bool", "float3", "float1X2", "float2X2", "float3X1", "textureUnit", "float2", "float4", "bool4", "float2X1", "float1X4", "float2X4", "int2", "float4X2", "float3X4", "samplerState", "_float", "float4X3", "float4X4", "int3", "float3X2", "float3X3", "float2X3", "_int", "texturePipeline", "surface", "int4" })
        public static class Setparam
        {
            @XmlElement(name = "annotate")
            protected List<FxAnnotateCommon> annotates;
            @XmlList
            @XmlElement(name = "float4x1", type = Double.class)
            protected List<Double> float4X1;
            @XmlElement(name = "float1x1")
            protected Double float1X1;
            @XmlElement(name = "enum")
            protected String _enum;
            @XmlList
            @XmlElement(type = Boolean.class)
            protected List<Boolean> bool3;
            @XmlList
            @XmlElement(name = "float1x3", type = Double.class)
            protected List<Double> float1X3;
            @XmlList
            @XmlElement(type = Boolean.class)
            protected List<Boolean> bool2;
            protected Boolean bool;
            @XmlList
            @XmlElement(type = Double.class)
            protected List<Double> float3;
            @XmlList
            @XmlElement(name = "float1x2", type = Double.class)
            protected List<Double> float1X2;
            @XmlList
            @XmlElement(name = "float2x2", type = Double.class)
            protected List<Double> float2X2;
            @XmlList
            @XmlElement(name = "float3x1", type = Double.class)
            protected List<Double> float3X1;
            @XmlElement(name = "texture_unit")
            protected GlesTextureUnit textureUnit;
            @XmlList
            @XmlElement(type = Double.class)
            protected List<Double> float2;
            @XmlList
            @XmlElement(type = Double.class)
            protected List<Double> float4;
            @XmlList
            @XmlElement(type = Boolean.class)
            protected List<Boolean> bool4;
            @XmlList
            @XmlElement(name = "float2x1", type = Double.class)
            protected List<Double> float2X1;
            @XmlList
            @XmlElement(name = "float1x4", type = Double.class)
            protected List<Double> float1X4;
            @XmlList
            @XmlElement(name = "float2x4", type = Double.class)
            protected List<Double> float2X4;
            @XmlList
            @XmlElement(type = Long.class)
            protected List<Long> int2;
            @XmlList
            @XmlElement(name = "float4x2", type = Double.class)
            protected List<Double> float4X2;
            @XmlList
            @XmlElement(name = "float3x4", type = Double.class)
            protected List<Double> float3X4;
            @XmlElement(name = "sampler_state")
            protected GlesSamplerState samplerState;
            @XmlElement(name = "float")
            protected Double _float;
            @XmlList
            @XmlElement(name = "float4x3", type = Double.class)
            protected List<Double> float4X3;
            @XmlList
            @XmlElement(name = "float4x4", type = Double.class)
            protected List<Double> float4X4;
            @XmlList
            @XmlElement(type = Long.class)
            protected List<Long> int3;
            @XmlList
            @XmlElement(name = "float3x2", type = Double.class)
            protected List<Double> float3X2;
            @XmlList
            @XmlElement(name = "float3x3", type = Double.class)
            protected List<Double> float3X3;
            @XmlList
            @XmlElement(name = "float2x3", type = Double.class)
            protected List<Double> float2X3;
            @XmlElement(name = "int")
            protected Long _int;
            @XmlElement(name = "texture_pipeline")
            protected GlesTexturePipeline texturePipeline;
            protected FxSurfaceCommon surface;
            @XmlList
            @XmlElement(type = Long.class)
            protected List<Long> int4;
            @XmlAttribute(required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String ref;
            
            public List<FxAnnotateCommon> getAnnotates() {
                if (this.annotates == null) {
                    this.annotates = new ArrayList<FxAnnotateCommon>();
                }
                return this.annotates;
            }
            
            public List<Double> getFloat4X1() {
                if (this.float4X1 == null) {
                    this.float4X1 = new ArrayList<Double>();
                }
                return this.float4X1;
            }
            
            public Double getFloat1X1() {
                return this.float1X1;
            }
            
            public void setFloat1X1(final Double value) {
                this.float1X1 = value;
            }
            
            public String getEnum() {
                return this._enum;
            }
            
            public void setEnum(final String value) {
                this._enum = value;
            }
            
            public List<Boolean> getBool3() {
                if (this.bool3 == null) {
                    this.bool3 = new ArrayList<Boolean>();
                }
                return this.bool3;
            }
            
            public List<Double> getFloat1X3() {
                if (this.float1X3 == null) {
                    this.float1X3 = new ArrayList<Double>();
                }
                return this.float1X3;
            }
            
            public List<Boolean> getBool2() {
                if (this.bool2 == null) {
                    this.bool2 = new ArrayList<Boolean>();
                }
                return this.bool2;
            }
            
            public Boolean isBool() {
                return this.bool;
            }
            
            public void setBool(final Boolean value) {
                this.bool = value;
            }
            
            public List<Double> getFloat3() {
                if (this.float3 == null) {
                    this.float3 = new ArrayList<Double>();
                }
                return this.float3;
            }
            
            public List<Double> getFloat1X2() {
                if (this.float1X2 == null) {
                    this.float1X2 = new ArrayList<Double>();
                }
                return this.float1X2;
            }
            
            public List<Double> getFloat2X2() {
                if (this.float2X2 == null) {
                    this.float2X2 = new ArrayList<Double>();
                }
                return this.float2X2;
            }
            
            public List<Double> getFloat3X1() {
                if (this.float3X1 == null) {
                    this.float3X1 = new ArrayList<Double>();
                }
                return this.float3X1;
            }
            
            public GlesTextureUnit getTextureUnit() {
                return this.textureUnit;
            }
            
            public void setTextureUnit(final GlesTextureUnit value) {
                this.textureUnit = value;
            }
            
            public List<Double> getFloat2() {
                if (this.float2 == null) {
                    this.float2 = new ArrayList<Double>();
                }
                return this.float2;
            }
            
            public List<Double> getFloat4() {
                if (this.float4 == null) {
                    this.float4 = new ArrayList<Double>();
                }
                return this.float4;
            }
            
            public List<Boolean> getBool4() {
                if (this.bool4 == null) {
                    this.bool4 = new ArrayList<Boolean>();
                }
                return this.bool4;
            }
            
            public List<Double> getFloat2X1() {
                if (this.float2X1 == null) {
                    this.float2X1 = new ArrayList<Double>();
                }
                return this.float2X1;
            }
            
            public List<Double> getFloat1X4() {
                if (this.float1X4 == null) {
                    this.float1X4 = new ArrayList<Double>();
                }
                return this.float1X4;
            }
            
            public List<Double> getFloat2X4() {
                if (this.float2X4 == null) {
                    this.float2X4 = new ArrayList<Double>();
                }
                return this.float2X4;
            }
            
            public List<Long> getInt2() {
                if (this.int2 == null) {
                    this.int2 = new ArrayList<Long>();
                }
                return this.int2;
            }
            
            public List<Double> getFloat4X2() {
                if (this.float4X2 == null) {
                    this.float4X2 = new ArrayList<Double>();
                }
                return this.float4X2;
            }
            
            public List<Double> getFloat3X4() {
                if (this.float3X4 == null) {
                    this.float3X4 = new ArrayList<Double>();
                }
                return this.float3X4;
            }
            
            public GlesSamplerState getSamplerState() {
                return this.samplerState;
            }
            
            public void setSamplerState(final GlesSamplerState value) {
                this.samplerState = value;
            }
            
            public Double getFloat() {
                return this._float;
            }
            
            public void setFloat(final Double value) {
                this._float = value;
            }
            
            public List<Double> getFloat4X3() {
                if (this.float4X3 == null) {
                    this.float4X3 = new ArrayList<Double>();
                }
                return this.float4X3;
            }
            
            public List<Double> getFloat4X4() {
                if (this.float4X4 == null) {
                    this.float4X4 = new ArrayList<Double>();
                }
                return this.float4X4;
            }
            
            public List<Long> getInt3() {
                if (this.int3 == null) {
                    this.int3 = new ArrayList<Long>();
                }
                return this.int3;
            }
            
            public List<Double> getFloat3X2() {
                if (this.float3X2 == null) {
                    this.float3X2 = new ArrayList<Double>();
                }
                return this.float3X2;
            }
            
            public List<Double> getFloat3X3() {
                if (this.float3X3 == null) {
                    this.float3X3 = new ArrayList<Double>();
                }
                return this.float3X3;
            }
            
            public List<Double> getFloat2X3() {
                if (this.float2X3 == null) {
                    this.float2X3 = new ArrayList<Double>();
                }
                return this.float2X3;
            }
            
            public Long getInt() {
                return this._int;
            }
            
            public void setInt(final Long value) {
                this._int = value;
            }
            
            public GlesTexturePipeline getTexturePipeline() {
                return this.texturePipeline;
            }
            
            public void setTexturePipeline(final GlesTexturePipeline value) {
                this.texturePipeline = value;
            }
            
            public FxSurfaceCommon getSurface() {
                return this.surface;
            }
            
            public void setSurface(final FxSurfaceCommon value) {
                this.surface = value;
            }
            
            public List<Long> getInt4() {
                if (this.int4 == null) {
                    this.int4 = new ArrayList<Long>();
                }
                return this.int4;
            }
            
            public String getRef() {
                return this.ref;
            }
            
            public void setRef(final String value) {
                this.ref = value;
            }
        }
    }
}
