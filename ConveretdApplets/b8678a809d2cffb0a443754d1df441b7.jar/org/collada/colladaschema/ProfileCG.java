// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlList;
import java.math.BigInteger;
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
@XmlType(name = "", propOrder = { "asset", "includesAndCodes", "imagesAndNewparams", "techniques", "extras" })
public class ProfileCG
{
    protected Asset asset;
    @XmlElements({ @XmlElement(name = "include", type = FxIncludeCommon.class), @XmlElement(name = "code", type = FxCodeProfile.class) })
    protected List<Object> includesAndCodes;
    @XmlElements({ @XmlElement(name = "image", type = Image.class), @XmlElement(name = "newparam", type = CgNewparam.class) })
    protected List<Object> imagesAndNewparams;
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
    
    public List<Object> getIncludesAndCodes() {
        if (this.includesAndCodes == null) {
            this.includesAndCodes = new ArrayList<Object>();
        }
        return this.includesAndCodes;
    }
    
    public List<Object> getImagesAndNewparams() {
        if (this.imagesAndNewparams == null) {
            this.imagesAndNewparams = new ArrayList<Object>();
        }
        return this.imagesAndNewparams;
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
    @XmlType(name = "", propOrder = { "asset", "annotates", "codesAndIncludes", "newparamsAndImagesAndSetparams", "pass", "extras" })
    public static class Technique
    {
        protected Asset asset;
        @XmlElement(name = "annotate")
        protected List<FxAnnotateCommon> annotates;
        @XmlElements({ @XmlElement(name = "code", type = FxCodeProfile.class), @XmlElement(name = "include", type = FxIncludeCommon.class) })
        protected List<Object> codesAndIncludes;
        @XmlElements({ @XmlElement(name = "newparam", type = CgNewparam.class), @XmlElement(name = "image", type = Image.class), @XmlElement(name = "setparam", type = CgSetparam.class) })
        protected List<Object> newparamsAndImagesAndSetparams;
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
        
        public List<Object> getCodesAndIncludes() {
            if (this.codesAndIncludes == null) {
                this.codesAndIncludes = new ArrayList<Object>();
            }
            return this.codesAndIncludes;
        }
        
        public List<Object> getNewparamsAndImagesAndSetparams() {
            if (this.newparamsAndImagesAndSetparams == null) {
                this.newparamsAndImagesAndSetparams = new ArrayList<Object>();
            }
            return this.newparamsAndImagesAndSetparams;
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
        @XmlType(name = "", propOrder = { "annotates", "colorTargets", "depthTargets", "stencilTargets", "colorClears", "depthClears", "stencilClears", "draw", "projectionMatrixesAndStencilOpSeparatesAndLightSpotDirections", "extras" })
        public static class Pass
        {
            @XmlElement(name = "annotate")
            protected List<FxAnnotateCommon> annotates;
            @XmlElement(name = "color_target")
            protected List<FxColortargetCommon> colorTargets;
            @XmlElement(name = "depth_target")
            protected List<FxDepthtargetCommon> depthTargets;
            @XmlElement(name = "stencil_target")
            protected List<FxStenciltargetCommon> stencilTargets;
            @XmlElement(name = "color_clear")
            protected List<FxClearcolorCommon> colorClears;
            @XmlElement(name = "depth_clear")
            protected List<FxCleardepthCommon> depthClears;
            @XmlElement(name = "stencil_clear")
            protected List<FxClearstencilCommon> stencilClears;
            protected String draw;
            @XmlElements({ @XmlElement(name = "light_quadratic_attenuation", type = LightQuadraticAttenuation.class), @XmlElement(name = "depth_test_enable", type = DepthTestEnable.class), @XmlElement(name = "texture2D_enable", type = Texture2DEnable.class), @XmlElement(name = "light_model_ambient", type = LightModelAmbient.class), @XmlElement(name = "depth_bounds", type = DepthBounds.class), @XmlElement(name = "cull_face_enable", type = CullFaceEnable.class), @XmlElement(name = "stencil_func", type = StencilFunc.class), @XmlElement(name = "material_shininess", type = MaterialShininess.class), @XmlElement(name = "texture_env_mode", type = TextureEnvMode.class), @XmlElement(name = "texture2D", type = Texture2D.class), @XmlElement(name = "alpha_test_enable", type = AlphaTestEnable.class), @XmlElement(name = "depth_bounds_enable", type = DepthBoundsEnable.class), @XmlElement(name = "point_size", type = PointSize.class), @XmlElement(name = "light_model_local_viewer_enable", type = LightModelLocalViewerEnable.class), @XmlElement(name = "textureCUBE_enable", type = TextureCUBEEnable.class), @XmlElement(name = "polygon_offset_line_enable", type = PolygonOffsetLineEnable.class), @XmlElement(name = "clip_plane_enable", type = ClipPlaneEnable.class), @XmlElement(name = "rescale_normal_enable", type = RescaleNormalEnable.class), @XmlElement(name = "fog_coord_src", type = FogCoordSrc.class), @XmlElement(name = "point_smooth_enable", type = PointSmoothEnable.class), @XmlElement(name = "polygon_mode", type = PolygonMode.class), @XmlElement(name = "fog_enable", type = FogEnable.class), @XmlElement(name = "textureDEPTH", type = TextureDEPTH.class), @XmlElement(name = "textureDEPTH_enable", type = TextureDEPTHEnable.class), @XmlElement(name = "fog_mode", type = FogMode.class), @XmlElement(name = "point_fade_threshold_size", type = PointFadeThresholdSize.class), @XmlElement(name = "polygon_offset_point_enable", type = PolygonOffsetPointEnable.class), @XmlElement(name = "clip_plane", type = ClipPlane.class), @XmlElement(name = "auto_normal_enable", type = AutoNormalEnable.class), @XmlElement(name = "stencil_func_separate", type = StencilFuncSeparate.class), @XmlElement(name = "point_distance_attenuation", type = PointDistanceAttenuation.class), @XmlElement(name = "stencil_op", type = StencilOp.class), @XmlElement(name = "textureCUBE", type = TextureCUBE.class), @XmlElement(name = "light_spot_cutoff", type = LightSpotCutoff.class), @XmlElement(name = "sample_coverage_enable", type = SampleCoverageEnable.class), @XmlElement(name = "texture3D_enable", type = Texture3DEnable.class), @XmlElement(name = "sample_alpha_to_one_enable", type = SampleAlphaToOneEnable.class), @XmlElement(name = "scissor", type = Scissor.class), @XmlElement(name = "polygon_smooth_enable", type = PolygonSmoothEnable.class), @XmlElement(name = "material_specular", type = MaterialSpecular.class), @XmlElement(name = "light_ambient", type = LightAmbient.class), @XmlElement(name = "cull_face", type = CullFace.class), @XmlElement(name = "blend_equation", type = BlendEquation.class), @XmlElement(name = "stencil_test_enable", type = StencilTestEnable.class), @XmlElement(name = "front_face", type = FrontFace.class), @XmlElement(name = "lighting_enable", type = LightingEnable.class), @XmlElement(name = "textureRECT_enable", type = TextureRECTEnable.class), @XmlElement(name = "light_position", type = LightPosition.class), @XmlElement(name = "multisample_enable", type = MultisampleEnable.class), @XmlElement(name = "depth_mask", type = DepthMask.class), @XmlElement(name = "texture1D_enable", type = Texture1DEnable.class), @XmlElement(name = "material_diffuse", type = MaterialDiffuse.class), @XmlElement(name = "fog_end", type = FogEnd.class), @XmlElement(name = "blend_func", type = BlendFunc.class), @XmlElement(name = "depth_range", type = DepthRange.class), @XmlElement(name = "logic_op", type = LogicOp.class), @XmlElement(name = "projection_matrix", type = ProjectionMatrix.class), @XmlElement(name = "color_material", type = ColorMaterial.class), @XmlElement(name = "point_size_min", type = PointSizeMin.class), @XmlElement(name = "light_constant_attenuation", type = LightConstantAttenuation.class), @XmlElement(name = "blend_enable", type = BlendEnable.class), @XmlElement(name = "clear_depth", type = ClearDepth.class), @XmlElement(name = "polygon_offset_fill_enable", type = PolygonOffsetFillEnable.class), @XmlElement(name = "stencil_mask", type = StencilMask.class), @XmlElement(name = "light_model_two_side_enable", type = LightModelTwoSideEnable.class), @XmlElement(name = "material_emission", type = MaterialEmission.class), @XmlElement(name = "light_spot_direction", type = LightSpotDirection.class), @XmlElement(name = "point_size_max", type = PointSizeMax.class), @XmlElement(name = "fog_start", type = FogStart.class), @XmlElement(name = "depth_clamp_enable", type = DepthClampEnable.class), @XmlElement(name = "logic_op_enable", type = LogicOpEnable.class), @XmlElement(name = "shade_model", type = ShadeModel.class), @XmlElement(name = "blend_equation_separate", type = BlendEquationSeparate.class), @XmlElement(name = "textureRECT", type = TextureRECT.class), @XmlElement(name = "line_smooth_enable", type = LineSmoothEnable.class), @XmlElement(name = "sample_alpha_to_coverage_enable", type = SampleAlphaToCoverageEnable.class), @XmlElement(name = "light_linear_attenuation", type = LightLinearAttenuation.class), @XmlElement(name = "clear_color", type = ClearColor.class), @XmlElement(name = "blend_func_separate", type = BlendFuncSeparate.class), @XmlElement(name = "line_width", type = LineWidth.class), @XmlElement(name = "light_specular", type = LightSpecular.class), @XmlElement(name = "clear_stencil", type = ClearStencil.class), @XmlElement(name = "line_stipple_enable", type = LineStippleEnable.class), @XmlElement(name = "blend_color", type = BlendColor.class), @XmlElement(name = "stencil_op_separate", type = StencilOpSeparate.class), @XmlElement(name = "alpha_func", type = AlphaFunc.class), @XmlElement(name = "light_spot_exponent", type = LightSpotExponent.class), @XmlElement(name = "normalize_enable", type = NormalizeEnable.class), @XmlElement(name = "light_enable", type = LightEnable.class), @XmlElement(name = "texture_env_color", type = TextureEnvColor.class), @XmlElement(name = "dither_enable", type = DitherEnable.class), @XmlElement(name = "gl_hook_abstract"), @XmlElement(name = "color_logic_op_enable", type = ColorLogicOpEnable.class), @XmlElement(name = "polygon_stipple_enable", type = PolygonStippleEnable.class), @XmlElement(name = "material_ambient", type = MaterialAmbient.class), @XmlElement(name = "line_stipple", type = LineStipple.class), @XmlElement(name = "fog_density", type = FogDensity.class), @XmlElement(name = "model_view_matrix", type = ModelViewMatrix.class), @XmlElement(name = "scissor_test_enable", type = ScissorTestEnable.class), @XmlElement(name = "texture1D", type = Texture1D.class), @XmlElement(name = "texture3D", type = Texture3D.class), @XmlElement(name = "fog_color", type = FogColor.class), @XmlElement(name = "light_model_color_control", type = LightModelColorControl.class), @XmlElement(name = "light_diffuse", type = LightDiffuse.class), @XmlElement(name = "color_material_enable", type = ColorMaterialEnable.class), @XmlElement(name = "shader", type = Shader.class), @XmlElement(name = "polygon_offset", type = PolygonOffset.class), @XmlElement(name = "color_mask", type = ColorMask.class), @XmlElement(name = "stencil_mask_separate", type = StencilMaskSeparate.class), @XmlElement(name = "depth_func", type = DepthFunc.class) })
            protected List<Object> projectionMatrixesAndStencilOpSeparatesAndLightSpotDirections;
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
            
            public List<FxColortargetCommon> getColorTargets() {
                if (this.colorTargets == null) {
                    this.colorTargets = new ArrayList<FxColortargetCommon>();
                }
                return this.colorTargets;
            }
            
            public List<FxDepthtargetCommon> getDepthTargets() {
                if (this.depthTargets == null) {
                    this.depthTargets = new ArrayList<FxDepthtargetCommon>();
                }
                return this.depthTargets;
            }
            
            public List<FxStenciltargetCommon> getStencilTargets() {
                if (this.stencilTargets == null) {
                    this.stencilTargets = new ArrayList<FxStenciltargetCommon>();
                }
                return this.stencilTargets;
            }
            
            public List<FxClearcolorCommon> getColorClears() {
                if (this.colorClears == null) {
                    this.colorClears = new ArrayList<FxClearcolorCommon>();
                }
                return this.colorClears;
            }
            
            public List<FxCleardepthCommon> getDepthClears() {
                if (this.depthClears == null) {
                    this.depthClears = new ArrayList<FxCleardepthCommon>();
                }
                return this.depthClears;
            }
            
            public List<FxClearstencilCommon> getStencilClears() {
                if (this.stencilClears == null) {
                    this.stencilClears = new ArrayList<FxClearstencilCommon>();
                }
                return this.stencilClears;
            }
            
            public String getDraw() {
                return this.draw;
            }
            
            public void setDraw(final String value) {
                this.draw = value;
            }
            
            public List<Object> getProjectionMatrixesAndStencilOpSeparatesAndLightSpotDirections() {
                if (this.projectionMatrixesAndStencilOpSeparatesAndLightSpotDirections == null) {
                    this.projectionMatrixesAndStencilOpSeparatesAndLightSpotDirections = new ArrayList<Object>();
                }
                return this.projectionMatrixesAndStencilOpSeparatesAndLightSpotDirections;
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
            public static class AutoNormalEnable
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
            public static class BlendColor
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
            @XmlType(name = "")
            public static class BlendEquation
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlBlendEquationType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlBlendEquationType getValue() {
                    if (this.value == null) {
                        return GlBlendEquationType.FUNC_ADD;
                    }
                    return this.value;
                }
                
                public void setValue(final GlBlendEquationType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "rgb", "alpha" })
            public static class BlendEquationSeparate
            {
                @XmlElement(required = true)
                protected Rgb rgb;
                @XmlElement(required = true)
                protected Alpha alpha;
                
                public Rgb getRgb() {
                    return this.rgb;
                }
                
                public void setRgb(final Rgb value) {
                    this.rgb = value;
                }
                
                public Alpha getAlpha() {
                    return this.alpha;
                }
                
                public void setAlpha(final Alpha value) {
                    this.alpha = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Alpha
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlBlendEquationType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlBlendEquationType getValue() {
                        if (this.value == null) {
                            return GlBlendEquationType.FUNC_ADD;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlBlendEquationType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Rgb
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlBlendEquationType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlBlendEquationType getValue() {
                        if (this.value == null) {
                            return GlBlendEquationType.FUNC_ADD;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlBlendEquationType value) {
                        this.value = value;
                    }
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
            @XmlType(name = "", propOrder = { "srcRgb", "destRgb", "srcAlpha", "destAlpha" })
            public static class BlendFuncSeparate
            {
                @XmlElement(name = "src_rgb", required = true)
                protected SrcRgb srcRgb;
                @XmlElement(name = "dest_rgb", required = true)
                protected DestRgb destRgb;
                @XmlElement(name = "src_alpha", required = true)
                protected SrcAlpha srcAlpha;
                @XmlElement(name = "dest_alpha", required = true)
                protected DestAlpha destAlpha;
                
                public SrcRgb getSrcRgb() {
                    return this.srcRgb;
                }
                
                public void setSrcRgb(final SrcRgb value) {
                    this.srcRgb = value;
                }
                
                public DestRgb getDestRgb() {
                    return this.destRgb;
                }
                
                public void setDestRgb(final DestRgb value) {
                    this.destRgb = value;
                }
                
                public SrcAlpha getSrcAlpha() {
                    return this.srcAlpha;
                }
                
                public void setSrcAlpha(final SrcAlpha value) {
                    this.srcAlpha = value;
                }
                
                public DestAlpha getDestAlpha() {
                    return this.destAlpha;
                }
                
                public void setDestAlpha(final DestAlpha value) {
                    this.destAlpha = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class DestAlpha
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
                public static class DestRgb
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
                public static class SrcAlpha
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
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class SrcRgb
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
                
                public long getValue() {
                    if (this.value == null) {
                        return 0L;
                    }
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
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class ClipPlaneEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            @XmlType(name = "", propOrder = { "face", "mode" })
            public static class ColorMaterial
            {
                @XmlElement(required = true)
                protected Face face;
                @XmlElement(required = true)
                protected Mode mode;
                
                public Face getFace() {
                    return this.face;
                }
                
                public void setFace(final Face value) {
                    this.face = value;
                }
                
                public Mode getMode() {
                    return this.mode;
                }
                
                public void setMode(final Mode value) {
                    this.mode = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Face
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
                            return GlFaceType.FRONT_AND_BACK;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFaceType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Mode
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlMaterialType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlMaterialType getValue() {
                        if (this.value == null) {
                            return GlMaterialType.AMBIENT_AND_DIFFUSE;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlMaterialType value) {
                        this.value = value;
                    }
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
            public static class DepthBounds
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
            public static class DepthBoundsEnable
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
            public static class DepthClampEnable
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
                    return this.value == null || this.value;
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
                    return this.value == null || this.value;
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
            public static class FogCoordSrc
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlFogCoordSrcType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlFogCoordSrcType getValue() {
                    if (this.value == null) {
                        return GlFogCoordSrcType.FOG_COORDINATE;
                    }
                    return this.value;
                }
                
                public void setValue(final GlFogCoordSrcType value) {
                    this.value = value;
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class LightLinearAttenuation
            {
                @XmlAttribute(required = true)
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class LightModelColorControl
            {
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected GlLightModelColorControlType value;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlLightModelColorControlType getValue() {
                    if (this.value == null) {
                        return GlLightModelColorControlType.SINGLE_COLOR;
                    }
                    return this.value;
                }
                
                public void setValue(final GlLightModelColorControlType value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class LightModelLocalViewerEnable
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class LightSpecular
            {
                @XmlAttribute(required = true)
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Double value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class LineStipple
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
            public static class LineStippleEnable
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
            public static class LogicOpEnable
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
            @XmlType(name = "", propOrder = { "face", "mode" })
            public static class PolygonMode
            {
                @XmlElement(required = true)
                protected Face face;
                @XmlElement(required = true)
                protected Mode mode;
                
                public Face getFace() {
                    return this.face;
                }
                
                public void setFace(final Face value) {
                    this.face = value;
                }
                
                public Mode getMode() {
                    return this.mode;
                }
                
                public void setMode(final Mode value) {
                    this.mode = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Face
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
                            return GlFaceType.FRONT_AND_BACK;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFaceType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Mode
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlPolygonModeType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlPolygonModeType getValue() {
                        if (this.value == null) {
                            return GlPolygonModeType.FILL;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlPolygonModeType value) {
                        this.value = value;
                    }
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
            public static class PolygonOffsetLineEnable
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
            public static class PolygonOffsetPointEnable
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
            public static class PolygonSmoothEnable
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
            public static class PolygonStippleEnable
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
            @XmlType(name = "", propOrder = { "annotates", "compilerTarget", "compilerOptions", "name", "binds" })
            public static class Shader
            {
                @XmlElement(name = "annotate")
                protected List<FxAnnotateCommon> annotates;
                @XmlElement(name = "compiler_target")
                protected CompilerTarget compilerTarget;
                @XmlElement(name = "compiler_options")
                protected String compilerOptions;
                @XmlElement(required = true)
                protected Name name;
                @XmlElement(name = "bind")
                protected List<Bind> binds;
                @XmlAttribute
                protected CgPipelineStage stage;
                
                public List<FxAnnotateCommon> getAnnotates() {
                    if (this.annotates == null) {
                        this.annotates = new ArrayList<FxAnnotateCommon>();
                    }
                    return this.annotates;
                }
                
                public CompilerTarget getCompilerTarget() {
                    return this.compilerTarget;
                }
                
                public void setCompilerTarget(final CompilerTarget value) {
                    this.compilerTarget = value;
                }
                
                public String getCompilerOptions() {
                    return this.compilerOptions;
                }
                
                public void setCompilerOptions(final String value) {
                    this.compilerOptions = value;
                }
                
                public Name getName() {
                    return this.name;
                }
                
                public void setName(final Name value) {
                    this.name = value;
                }
                
                public List<Bind> getBinds() {
                    if (this.binds == null) {
                        this.binds = new ArrayList<Bind>();
                    }
                    return this.binds;
                }
                
                public CgPipelineStage getStage() {
                    return this.stage;
                }
                
                public void setStage(final CgPipelineStage value) {
                    this.stage = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "int1X3", "float3X2", "half4X2", "float1X1", "fixed3X4", "int4X1", "sampler3D", "fixed3X3", "half3X1", "bool3", "half3", "bool2X3", "half1", "float4X4", "int1X1", "int2X3", "int1", "fixed2X4", "fixed2X1", "half1X2", "sampler1D", "float2X3", "half1X3", "float4X1", "param", "bool4X4", "int4X2", "float3X4", "float2", "bool4X1", "float3X3", "half2", "int3", "samplerRECT", "half", "fixed2", "half2X3", "half4", "fixed2X3", "bool3X1", "bool1X4", "string", "half4X1", "bool4X2", "fixed3X1", "half1X1", "half1X4", "int4X4", "half3X3", "bool", "fixed1X2", "int3X1", "float4X2", "bool2X2", "sampler2D", "int1X2", "bool4", "float2X1", "float1X4", "half2X2", "float1X2", "int2", "fixed1X3", "int4", "fixed4", "fixed2X2", "samplerCUBE", "fixed1", "float2X4", "fixed1X4", "fixed4X4", "bool3X2", "bool2", "fixed", "_float", "_enum", "_int", "half3X4", "surface", "int1X4", "half3X2", "half2X4", "samplerDEPTH", "fixed1X1", "float1", "bool4X3", "float3", "float4", "fixed4X2", "int4X3", "float1X3", "float3X1", "int2X2", "int3X3", "fixed3X2", "bool1X1", "fixed4X1", "half4X4", "bool1X3", "bool1X2", "half2X1", "bool3X4", "fixed4X3", "half4X3", "int2X4", "int3X2", "int3X4", "fixed3", "bool3X3", "bool2X4", "float2X2", "float4X3", "bool2X1", "int2X1", "bool1" })
                public static class Bind
                {
                    @XmlList
                    @XmlElement(name = "int1x3", type = Integer.class)
                    protected List<Integer> int1X3;
                    @XmlList
                    @XmlElement(name = "float3x2", type = Float.class)
                    protected List<Float> float3X2;
                    @XmlList
                    @XmlElement(name = "half4x2", type = Float.class)
                    protected List<Float> half4X2;
                    @XmlList
                    @XmlElement(name = "float1x1", type = Float.class)
                    protected List<Float> float1X1;
                    @XmlList
                    @XmlElement(name = "fixed3x4", type = Float.class)
                    protected List<Float> fixed3X4;
                    @XmlList
                    @XmlElement(name = "int4x1", type = Integer.class)
                    protected List<Integer> int4X1;
                    protected CgSampler3D sampler3D;
                    @XmlList
                    @XmlElement(name = "fixed3x3", type = Float.class)
                    protected List<Float> fixed3X3;
                    @XmlList
                    @XmlElement(name = "half3x1", type = Float.class)
                    protected List<Float> half3X1;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool3;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> half3;
                    @XmlList
                    @XmlElement(name = "bool2x3", type = Boolean.class)
                    protected List<Boolean> bool2X3;
                    protected Float half1;
                    @XmlList
                    @XmlElement(name = "float4x4", type = Float.class)
                    protected List<Float> float4X4;
                    @XmlList
                    @XmlElement(name = "int1x1", type = Integer.class)
                    protected List<Integer> int1X1;
                    @XmlList
                    @XmlElement(name = "int2x3", type = Integer.class)
                    protected List<Integer> int2X3;
                    protected Integer int1;
                    @XmlList
                    @XmlElement(name = "fixed2x4", type = Float.class)
                    protected List<Float> fixed2X4;
                    @XmlList
                    @XmlElement(name = "fixed2x1", type = Float.class)
                    protected List<Float> fixed2X1;
                    @XmlList
                    @XmlElement(name = "half1x2", type = Float.class)
                    protected List<Float> half1X2;
                    protected CgSampler1D sampler1D;
                    @XmlList
                    @XmlElement(name = "float2x3", type = Float.class)
                    protected List<Float> float2X3;
                    @XmlList
                    @XmlElement(name = "half1x3", type = Float.class)
                    protected List<Float> half1X3;
                    @XmlList
                    @XmlElement(name = "float4x1", type = Float.class)
                    protected List<Float> float4X1;
                    protected Param param;
                    @XmlList
                    @XmlElement(name = "bool4x4", type = Boolean.class)
                    protected List<Boolean> bool4X4;
                    @XmlList
                    @XmlElement(name = "int4x2", type = Integer.class)
                    protected List<Integer> int4X2;
                    @XmlList
                    @XmlElement(name = "float3x4", type = Float.class)
                    protected List<Float> float3X4;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float2;
                    @XmlList
                    @XmlElement(name = "bool4x1", type = Boolean.class)
                    protected List<Boolean> bool4X1;
                    @XmlList
                    @XmlElement(name = "float3x3", type = Float.class)
                    protected List<Float> float3X3;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> half2;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int3;
                    protected CgSamplerRECT samplerRECT;
                    protected Float half;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> fixed2;
                    @XmlList
                    @XmlElement(name = "half2x3", type = Float.class)
                    protected List<Float> half2X3;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> half4;
                    @XmlList
                    @XmlElement(name = "fixed2x3", type = Float.class)
                    protected List<Float> fixed2X3;
                    @XmlList
                    @XmlElement(name = "bool3x1", type = Boolean.class)
                    protected List<Boolean> bool3X1;
                    @XmlList
                    @XmlElement(name = "bool1x4", type = Boolean.class)
                    protected List<Boolean> bool1X4;
                    protected String string;
                    @XmlList
                    @XmlElement(name = "half4x1", type = Float.class)
                    protected List<Float> half4X1;
                    @XmlList
                    @XmlElement(name = "bool4x2", type = Boolean.class)
                    protected List<Boolean> bool4X2;
                    @XmlList
                    @XmlElement(name = "fixed3x1", type = Float.class)
                    protected List<Float> fixed3X1;
                    @XmlList
                    @XmlElement(name = "half1x1", type = Float.class)
                    protected List<Float> half1X1;
                    @XmlList
                    @XmlElement(name = "half1x4", type = Float.class)
                    protected List<Float> half1X4;
                    @XmlList
                    @XmlElement(name = "int4x4", type = Integer.class)
                    protected List<Integer> int4X4;
                    @XmlList
                    @XmlElement(name = "half3x3", type = Float.class)
                    protected List<Float> half3X3;
                    protected Boolean bool;
                    @XmlList
                    @XmlElement(name = "fixed1x2", type = Float.class)
                    protected List<Float> fixed1X2;
                    @XmlList
                    @XmlElement(name = "int3x1", type = Integer.class)
                    protected List<Integer> int3X1;
                    @XmlList
                    @XmlElement(name = "float4x2", type = Float.class)
                    protected List<Float> float4X2;
                    @XmlList
                    @XmlElement(name = "bool2x2", type = Boolean.class)
                    protected List<Boolean> bool2X2;
                    protected CgSampler2D sampler2D;
                    @XmlList
                    @XmlElement(name = "int1x2", type = Integer.class)
                    protected List<Integer> int1X2;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool4;
                    @XmlList
                    @XmlElement(name = "float2x1", type = Float.class)
                    protected List<Float> float2X1;
                    @XmlList
                    @XmlElement(name = "float1x4", type = Float.class)
                    protected List<Float> float1X4;
                    @XmlList
                    @XmlElement(name = "half2x2", type = Float.class)
                    protected List<Float> half2X2;
                    @XmlList
                    @XmlElement(name = "float1x2", type = Float.class)
                    protected List<Float> float1X2;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int2;
                    @XmlList
                    @XmlElement(name = "fixed1x3", type = Float.class)
                    protected List<Float> fixed1X3;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int4;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> fixed4;
                    @XmlList
                    @XmlElement(name = "fixed2x2", type = Float.class)
                    protected List<Float> fixed2X2;
                    protected CgSamplerCUBE samplerCUBE;
                    protected Float fixed1;
                    @XmlList
                    @XmlElement(name = "float2x4", type = Float.class)
                    protected List<Float> float2X4;
                    @XmlList
                    @XmlElement(name = "fixed1x4", type = Float.class)
                    protected List<Float> fixed1X4;
                    @XmlList
                    @XmlElement(name = "fixed4x4", type = Float.class)
                    protected List<Float> fixed4X4;
                    @XmlList
                    @XmlElement(name = "bool3x2", type = Boolean.class)
                    protected List<Boolean> bool3X2;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool2;
                    protected Float fixed;
                    @XmlElement(name = "float")
                    protected Float _float;
                    @XmlElement(name = "enum")
                    protected String _enum;
                    @XmlElement(name = "int")
                    protected Integer _int;
                    @XmlList
                    @XmlElement(name = "half3x4", type = Float.class)
                    protected List<Float> half3X4;
                    protected CgSurfaceType surface;
                    @XmlList
                    @XmlElement(name = "int1x4", type = Integer.class)
                    protected List<Integer> int1X4;
                    @XmlList
                    @XmlElement(name = "half3x2", type = Float.class)
                    protected List<Float> half3X2;
                    @XmlList
                    @XmlElement(name = "half2x4", type = Float.class)
                    protected List<Float> half2X4;
                    protected CgSamplerDEPTH samplerDEPTH;
                    @XmlList
                    @XmlElement(name = "fixed1x1", type = Float.class)
                    protected List<Float> fixed1X1;
                    protected Float float1;
                    @XmlList
                    @XmlElement(name = "bool4x3", type = Boolean.class)
                    protected List<Boolean> bool4X3;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float3;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float4;
                    @XmlList
                    @XmlElement(name = "fixed4x2", type = Float.class)
                    protected List<Float> fixed4X2;
                    @XmlList
                    @XmlElement(name = "int4x3", type = Integer.class)
                    protected List<Integer> int4X3;
                    @XmlList
                    @XmlElement(name = "float1x3", type = Float.class)
                    protected List<Float> float1X3;
                    @XmlList
                    @XmlElement(name = "float3x1", type = Float.class)
                    protected List<Float> float3X1;
                    @XmlList
                    @XmlElement(name = "int2x2", type = Integer.class)
                    protected List<Integer> int2X2;
                    @XmlList
                    @XmlElement(name = "int3x3", type = Integer.class)
                    protected List<Integer> int3X3;
                    @XmlList
                    @XmlElement(name = "fixed3x2", type = Float.class)
                    protected List<Float> fixed3X2;
                    @XmlList
                    @XmlElement(name = "bool1x1", type = Boolean.class)
                    protected List<Boolean> bool1X1;
                    @XmlList
                    @XmlElement(name = "fixed4x1", type = Float.class)
                    protected List<Float> fixed4X1;
                    @XmlList
                    @XmlElement(name = "half4x4", type = Float.class)
                    protected List<Float> half4X4;
                    @XmlList
                    @XmlElement(name = "bool1x3", type = Boolean.class)
                    protected List<Boolean> bool1X3;
                    @XmlList
                    @XmlElement(name = "bool1x2", type = Boolean.class)
                    protected List<Boolean> bool1X2;
                    @XmlList
                    @XmlElement(name = "half2x1", type = Float.class)
                    protected List<Float> half2X1;
                    @XmlList
                    @XmlElement(name = "bool3x4", type = Boolean.class)
                    protected List<Boolean> bool3X4;
                    @XmlList
                    @XmlElement(name = "fixed4x3", type = Float.class)
                    protected List<Float> fixed4X3;
                    @XmlList
                    @XmlElement(name = "half4x3", type = Float.class)
                    protected List<Float> half4X3;
                    @XmlList
                    @XmlElement(name = "int2x4", type = Integer.class)
                    protected List<Integer> int2X4;
                    @XmlList
                    @XmlElement(name = "int3x2", type = Integer.class)
                    protected List<Integer> int3X2;
                    @XmlList
                    @XmlElement(name = "int3x4", type = Integer.class)
                    protected List<Integer> int3X4;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> fixed3;
                    @XmlList
                    @XmlElement(name = "bool3x3", type = Boolean.class)
                    protected List<Boolean> bool3X3;
                    @XmlList
                    @XmlElement(name = "bool2x4", type = Boolean.class)
                    protected List<Boolean> bool2X4;
                    @XmlList
                    @XmlElement(name = "float2x2", type = Float.class)
                    protected List<Float> float2X2;
                    @XmlList
                    @XmlElement(name = "float4x3", type = Float.class)
                    protected List<Float> float4X3;
                    @XmlList
                    @XmlElement(name = "bool2x1", type = Boolean.class)
                    protected List<Boolean> bool2X1;
                    @XmlList
                    @XmlElement(name = "int2x1", type = Integer.class)
                    protected List<Integer> int2X1;
                    protected Boolean bool1;
                    @XmlAttribute(required = true)
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String symbol;
                    
                    public List<Integer> getInt1X3() {
                        if (this.int1X3 == null) {
                            this.int1X3 = new ArrayList<Integer>();
                        }
                        return this.int1X3;
                    }
                    
                    public List<Float> getFloat3X2() {
                        if (this.float3X2 == null) {
                            this.float3X2 = new ArrayList<Float>();
                        }
                        return this.float3X2;
                    }
                    
                    public List<Float> getHalf4X2() {
                        if (this.half4X2 == null) {
                            this.half4X2 = new ArrayList<Float>();
                        }
                        return this.half4X2;
                    }
                    
                    public List<Float> getFloat1X1() {
                        if (this.float1X1 == null) {
                            this.float1X1 = new ArrayList<Float>();
                        }
                        return this.float1X1;
                    }
                    
                    public List<Float> getFixed3X4() {
                        if (this.fixed3X4 == null) {
                            this.fixed3X4 = new ArrayList<Float>();
                        }
                        return this.fixed3X4;
                    }
                    
                    public List<Integer> getInt4X1() {
                        if (this.int4X1 == null) {
                            this.int4X1 = new ArrayList<Integer>();
                        }
                        return this.int4X1;
                    }
                    
                    public CgSampler3D getSampler3D() {
                        return this.sampler3D;
                    }
                    
                    public void setSampler3D(final CgSampler3D value) {
                        this.sampler3D = value;
                    }
                    
                    public List<Float> getFixed3X3() {
                        if (this.fixed3X3 == null) {
                            this.fixed3X3 = new ArrayList<Float>();
                        }
                        return this.fixed3X3;
                    }
                    
                    public List<Float> getHalf3X1() {
                        if (this.half3X1 == null) {
                            this.half3X1 = new ArrayList<Float>();
                        }
                        return this.half3X1;
                    }
                    
                    public List<Boolean> getBool3() {
                        if (this.bool3 == null) {
                            this.bool3 = new ArrayList<Boolean>();
                        }
                        return this.bool3;
                    }
                    
                    public List<Float> getHalf3() {
                        if (this.half3 == null) {
                            this.half3 = new ArrayList<Float>();
                        }
                        return this.half3;
                    }
                    
                    public List<Boolean> getBool2X3() {
                        if (this.bool2X3 == null) {
                            this.bool2X3 = new ArrayList<Boolean>();
                        }
                        return this.bool2X3;
                    }
                    
                    public Float getHalf1() {
                        return this.half1;
                    }
                    
                    public void setHalf1(final Float value) {
                        this.half1 = value;
                    }
                    
                    public List<Float> getFloat4X4() {
                        if (this.float4X4 == null) {
                            this.float4X4 = new ArrayList<Float>();
                        }
                        return this.float4X4;
                    }
                    
                    public List<Integer> getInt1X1() {
                        if (this.int1X1 == null) {
                            this.int1X1 = new ArrayList<Integer>();
                        }
                        return this.int1X1;
                    }
                    
                    public List<Integer> getInt2X3() {
                        if (this.int2X3 == null) {
                            this.int2X3 = new ArrayList<Integer>();
                        }
                        return this.int2X3;
                    }
                    
                    public Integer getInt1() {
                        return this.int1;
                    }
                    
                    public void setInt1(final Integer value) {
                        this.int1 = value;
                    }
                    
                    public List<Float> getFixed2X4() {
                        if (this.fixed2X4 == null) {
                            this.fixed2X4 = new ArrayList<Float>();
                        }
                        return this.fixed2X4;
                    }
                    
                    public List<Float> getFixed2X1() {
                        if (this.fixed2X1 == null) {
                            this.fixed2X1 = new ArrayList<Float>();
                        }
                        return this.fixed2X1;
                    }
                    
                    public List<Float> getHalf1X2() {
                        if (this.half1X2 == null) {
                            this.half1X2 = new ArrayList<Float>();
                        }
                        return this.half1X2;
                    }
                    
                    public CgSampler1D getSampler1D() {
                        return this.sampler1D;
                    }
                    
                    public void setSampler1D(final CgSampler1D value) {
                        this.sampler1D = value;
                    }
                    
                    public List<Float> getFloat2X3() {
                        if (this.float2X3 == null) {
                            this.float2X3 = new ArrayList<Float>();
                        }
                        return this.float2X3;
                    }
                    
                    public List<Float> getHalf1X3() {
                        if (this.half1X3 == null) {
                            this.half1X3 = new ArrayList<Float>();
                        }
                        return this.half1X3;
                    }
                    
                    public List<Float> getFloat4X1() {
                        if (this.float4X1 == null) {
                            this.float4X1 = new ArrayList<Float>();
                        }
                        return this.float4X1;
                    }
                    
                    public Param getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final Param value) {
                        this.param = value;
                    }
                    
                    public List<Boolean> getBool4X4() {
                        if (this.bool4X4 == null) {
                            this.bool4X4 = new ArrayList<Boolean>();
                        }
                        return this.bool4X4;
                    }
                    
                    public List<Integer> getInt4X2() {
                        if (this.int4X2 == null) {
                            this.int4X2 = new ArrayList<Integer>();
                        }
                        return this.int4X2;
                    }
                    
                    public List<Float> getFloat3X4() {
                        if (this.float3X4 == null) {
                            this.float3X4 = new ArrayList<Float>();
                        }
                        return this.float3X4;
                    }
                    
                    public List<Float> getFloat2() {
                        if (this.float2 == null) {
                            this.float2 = new ArrayList<Float>();
                        }
                        return this.float2;
                    }
                    
                    public List<Boolean> getBool4X1() {
                        if (this.bool4X1 == null) {
                            this.bool4X1 = new ArrayList<Boolean>();
                        }
                        return this.bool4X1;
                    }
                    
                    public List<Float> getFloat3X3() {
                        if (this.float3X3 == null) {
                            this.float3X3 = new ArrayList<Float>();
                        }
                        return this.float3X3;
                    }
                    
                    public List<Float> getHalf2() {
                        if (this.half2 == null) {
                            this.half2 = new ArrayList<Float>();
                        }
                        return this.half2;
                    }
                    
                    public List<Integer> getInt3() {
                        if (this.int3 == null) {
                            this.int3 = new ArrayList<Integer>();
                        }
                        return this.int3;
                    }
                    
                    public CgSamplerRECT getSamplerRECT() {
                        return this.samplerRECT;
                    }
                    
                    public void setSamplerRECT(final CgSamplerRECT value) {
                        this.samplerRECT = value;
                    }
                    
                    public Float getHalf() {
                        return this.half;
                    }
                    
                    public void setHalf(final Float value) {
                        this.half = value;
                    }
                    
                    public List<Float> getFixed2() {
                        if (this.fixed2 == null) {
                            this.fixed2 = new ArrayList<Float>();
                        }
                        return this.fixed2;
                    }
                    
                    public List<Float> getHalf2X3() {
                        if (this.half2X3 == null) {
                            this.half2X3 = new ArrayList<Float>();
                        }
                        return this.half2X3;
                    }
                    
                    public List<Float> getHalf4() {
                        if (this.half4 == null) {
                            this.half4 = new ArrayList<Float>();
                        }
                        return this.half4;
                    }
                    
                    public List<Float> getFixed2X3() {
                        if (this.fixed2X3 == null) {
                            this.fixed2X3 = new ArrayList<Float>();
                        }
                        return this.fixed2X3;
                    }
                    
                    public List<Boolean> getBool3X1() {
                        if (this.bool3X1 == null) {
                            this.bool3X1 = new ArrayList<Boolean>();
                        }
                        return this.bool3X1;
                    }
                    
                    public List<Boolean> getBool1X4() {
                        if (this.bool1X4 == null) {
                            this.bool1X4 = new ArrayList<Boolean>();
                        }
                        return this.bool1X4;
                    }
                    
                    public String getString() {
                        return this.string;
                    }
                    
                    public void setString(final String value) {
                        this.string = value;
                    }
                    
                    public List<Float> getHalf4X1() {
                        if (this.half4X1 == null) {
                            this.half4X1 = new ArrayList<Float>();
                        }
                        return this.half4X1;
                    }
                    
                    public List<Boolean> getBool4X2() {
                        if (this.bool4X2 == null) {
                            this.bool4X2 = new ArrayList<Boolean>();
                        }
                        return this.bool4X2;
                    }
                    
                    public List<Float> getFixed3X1() {
                        if (this.fixed3X1 == null) {
                            this.fixed3X1 = new ArrayList<Float>();
                        }
                        return this.fixed3X1;
                    }
                    
                    public List<Float> getHalf1X1() {
                        if (this.half1X1 == null) {
                            this.half1X1 = new ArrayList<Float>();
                        }
                        return this.half1X1;
                    }
                    
                    public List<Float> getHalf1X4() {
                        if (this.half1X4 == null) {
                            this.half1X4 = new ArrayList<Float>();
                        }
                        return this.half1X4;
                    }
                    
                    public List<Integer> getInt4X4() {
                        if (this.int4X4 == null) {
                            this.int4X4 = new ArrayList<Integer>();
                        }
                        return this.int4X4;
                    }
                    
                    public List<Float> getHalf3X3() {
                        if (this.half3X3 == null) {
                            this.half3X3 = new ArrayList<Float>();
                        }
                        return this.half3X3;
                    }
                    
                    public Boolean isBool() {
                        return this.bool;
                    }
                    
                    public void setBool(final Boolean value) {
                        this.bool = value;
                    }
                    
                    public List<Float> getFixed1X2() {
                        if (this.fixed1X2 == null) {
                            this.fixed1X2 = new ArrayList<Float>();
                        }
                        return this.fixed1X2;
                    }
                    
                    public List<Integer> getInt3X1() {
                        if (this.int3X1 == null) {
                            this.int3X1 = new ArrayList<Integer>();
                        }
                        return this.int3X1;
                    }
                    
                    public List<Float> getFloat4X2() {
                        if (this.float4X2 == null) {
                            this.float4X2 = new ArrayList<Float>();
                        }
                        return this.float4X2;
                    }
                    
                    public List<Boolean> getBool2X2() {
                        if (this.bool2X2 == null) {
                            this.bool2X2 = new ArrayList<Boolean>();
                        }
                        return this.bool2X2;
                    }
                    
                    public CgSampler2D getSampler2D() {
                        return this.sampler2D;
                    }
                    
                    public void setSampler2D(final CgSampler2D value) {
                        this.sampler2D = value;
                    }
                    
                    public List<Integer> getInt1X2() {
                        if (this.int1X2 == null) {
                            this.int1X2 = new ArrayList<Integer>();
                        }
                        return this.int1X2;
                    }
                    
                    public List<Boolean> getBool4() {
                        if (this.bool4 == null) {
                            this.bool4 = new ArrayList<Boolean>();
                        }
                        return this.bool4;
                    }
                    
                    public List<Float> getFloat2X1() {
                        if (this.float2X1 == null) {
                            this.float2X1 = new ArrayList<Float>();
                        }
                        return this.float2X1;
                    }
                    
                    public List<Float> getFloat1X4() {
                        if (this.float1X4 == null) {
                            this.float1X4 = new ArrayList<Float>();
                        }
                        return this.float1X4;
                    }
                    
                    public List<Float> getHalf2X2() {
                        if (this.half2X2 == null) {
                            this.half2X2 = new ArrayList<Float>();
                        }
                        return this.half2X2;
                    }
                    
                    public List<Float> getFloat1X2() {
                        if (this.float1X2 == null) {
                            this.float1X2 = new ArrayList<Float>();
                        }
                        return this.float1X2;
                    }
                    
                    public List<Integer> getInt2() {
                        if (this.int2 == null) {
                            this.int2 = new ArrayList<Integer>();
                        }
                        return this.int2;
                    }
                    
                    public List<Float> getFixed1X3() {
                        if (this.fixed1X3 == null) {
                            this.fixed1X3 = new ArrayList<Float>();
                        }
                        return this.fixed1X3;
                    }
                    
                    public List<Integer> getInt4() {
                        if (this.int4 == null) {
                            this.int4 = new ArrayList<Integer>();
                        }
                        return this.int4;
                    }
                    
                    public List<Float> getFixed4() {
                        if (this.fixed4 == null) {
                            this.fixed4 = new ArrayList<Float>();
                        }
                        return this.fixed4;
                    }
                    
                    public List<Float> getFixed2X2() {
                        if (this.fixed2X2 == null) {
                            this.fixed2X2 = new ArrayList<Float>();
                        }
                        return this.fixed2X2;
                    }
                    
                    public CgSamplerCUBE getSamplerCUBE() {
                        return this.samplerCUBE;
                    }
                    
                    public void setSamplerCUBE(final CgSamplerCUBE value) {
                        this.samplerCUBE = value;
                    }
                    
                    public Float getFixed1() {
                        return this.fixed1;
                    }
                    
                    public void setFixed1(final Float value) {
                        this.fixed1 = value;
                    }
                    
                    public List<Float> getFloat2X4() {
                        if (this.float2X4 == null) {
                            this.float2X4 = new ArrayList<Float>();
                        }
                        return this.float2X4;
                    }
                    
                    public List<Float> getFixed1X4() {
                        if (this.fixed1X4 == null) {
                            this.fixed1X4 = new ArrayList<Float>();
                        }
                        return this.fixed1X4;
                    }
                    
                    public List<Float> getFixed4X4() {
                        if (this.fixed4X4 == null) {
                            this.fixed4X4 = new ArrayList<Float>();
                        }
                        return this.fixed4X4;
                    }
                    
                    public List<Boolean> getBool3X2() {
                        if (this.bool3X2 == null) {
                            this.bool3X2 = new ArrayList<Boolean>();
                        }
                        return this.bool3X2;
                    }
                    
                    public List<Boolean> getBool2() {
                        if (this.bool2 == null) {
                            this.bool2 = new ArrayList<Boolean>();
                        }
                        return this.bool2;
                    }
                    
                    public Float getFixed() {
                        return this.fixed;
                    }
                    
                    public void setFixed(final Float value) {
                        this.fixed = value;
                    }
                    
                    public Float getFloat() {
                        return this._float;
                    }
                    
                    public void setFloat(final Float value) {
                        this._float = value;
                    }
                    
                    public String getEnum() {
                        return this._enum;
                    }
                    
                    public void setEnum(final String value) {
                        this._enum = value;
                    }
                    
                    public Integer getInt() {
                        return this._int;
                    }
                    
                    public void setInt(final Integer value) {
                        this._int = value;
                    }
                    
                    public List<Float> getHalf3X4() {
                        if (this.half3X4 == null) {
                            this.half3X4 = new ArrayList<Float>();
                        }
                        return this.half3X4;
                    }
                    
                    public CgSurfaceType getSurface() {
                        return this.surface;
                    }
                    
                    public void setSurface(final CgSurfaceType value) {
                        this.surface = value;
                    }
                    
                    public List<Integer> getInt1X4() {
                        if (this.int1X4 == null) {
                            this.int1X4 = new ArrayList<Integer>();
                        }
                        return this.int1X4;
                    }
                    
                    public List<Float> getHalf3X2() {
                        if (this.half3X2 == null) {
                            this.half3X2 = new ArrayList<Float>();
                        }
                        return this.half3X2;
                    }
                    
                    public List<Float> getHalf2X4() {
                        if (this.half2X4 == null) {
                            this.half2X4 = new ArrayList<Float>();
                        }
                        return this.half2X4;
                    }
                    
                    public CgSamplerDEPTH getSamplerDEPTH() {
                        return this.samplerDEPTH;
                    }
                    
                    public void setSamplerDEPTH(final CgSamplerDEPTH value) {
                        this.samplerDEPTH = value;
                    }
                    
                    public List<Float> getFixed1X1() {
                        if (this.fixed1X1 == null) {
                            this.fixed1X1 = new ArrayList<Float>();
                        }
                        return this.fixed1X1;
                    }
                    
                    public Float getFloat1() {
                        return this.float1;
                    }
                    
                    public void setFloat1(final Float value) {
                        this.float1 = value;
                    }
                    
                    public List<Boolean> getBool4X3() {
                        if (this.bool4X3 == null) {
                            this.bool4X3 = new ArrayList<Boolean>();
                        }
                        return this.bool4X3;
                    }
                    
                    public List<Float> getFloat3() {
                        if (this.float3 == null) {
                            this.float3 = new ArrayList<Float>();
                        }
                        return this.float3;
                    }
                    
                    public List<Float> getFloat4() {
                        if (this.float4 == null) {
                            this.float4 = new ArrayList<Float>();
                        }
                        return this.float4;
                    }
                    
                    public List<Float> getFixed4X2() {
                        if (this.fixed4X2 == null) {
                            this.fixed4X2 = new ArrayList<Float>();
                        }
                        return this.fixed4X2;
                    }
                    
                    public List<Integer> getInt4X3() {
                        if (this.int4X3 == null) {
                            this.int4X3 = new ArrayList<Integer>();
                        }
                        return this.int4X3;
                    }
                    
                    public List<Float> getFloat1X3() {
                        if (this.float1X3 == null) {
                            this.float1X3 = new ArrayList<Float>();
                        }
                        return this.float1X3;
                    }
                    
                    public List<Float> getFloat3X1() {
                        if (this.float3X1 == null) {
                            this.float3X1 = new ArrayList<Float>();
                        }
                        return this.float3X1;
                    }
                    
                    public List<Integer> getInt2X2() {
                        if (this.int2X2 == null) {
                            this.int2X2 = new ArrayList<Integer>();
                        }
                        return this.int2X2;
                    }
                    
                    public List<Integer> getInt3X3() {
                        if (this.int3X3 == null) {
                            this.int3X3 = new ArrayList<Integer>();
                        }
                        return this.int3X3;
                    }
                    
                    public List<Float> getFixed3X2() {
                        if (this.fixed3X2 == null) {
                            this.fixed3X2 = new ArrayList<Float>();
                        }
                        return this.fixed3X2;
                    }
                    
                    public List<Boolean> getBool1X1() {
                        if (this.bool1X1 == null) {
                            this.bool1X1 = new ArrayList<Boolean>();
                        }
                        return this.bool1X1;
                    }
                    
                    public List<Float> getFixed4X1() {
                        if (this.fixed4X1 == null) {
                            this.fixed4X1 = new ArrayList<Float>();
                        }
                        return this.fixed4X1;
                    }
                    
                    public List<Float> getHalf4X4() {
                        if (this.half4X4 == null) {
                            this.half4X4 = new ArrayList<Float>();
                        }
                        return this.half4X4;
                    }
                    
                    public List<Boolean> getBool1X3() {
                        if (this.bool1X3 == null) {
                            this.bool1X3 = new ArrayList<Boolean>();
                        }
                        return this.bool1X3;
                    }
                    
                    public List<Boolean> getBool1X2() {
                        if (this.bool1X2 == null) {
                            this.bool1X2 = new ArrayList<Boolean>();
                        }
                        return this.bool1X2;
                    }
                    
                    public List<Float> getHalf2X1() {
                        if (this.half2X1 == null) {
                            this.half2X1 = new ArrayList<Float>();
                        }
                        return this.half2X1;
                    }
                    
                    public List<Boolean> getBool3X4() {
                        if (this.bool3X4 == null) {
                            this.bool3X4 = new ArrayList<Boolean>();
                        }
                        return this.bool3X4;
                    }
                    
                    public List<Float> getFixed4X3() {
                        if (this.fixed4X3 == null) {
                            this.fixed4X3 = new ArrayList<Float>();
                        }
                        return this.fixed4X3;
                    }
                    
                    public List<Float> getHalf4X3() {
                        if (this.half4X3 == null) {
                            this.half4X3 = new ArrayList<Float>();
                        }
                        return this.half4X3;
                    }
                    
                    public List<Integer> getInt2X4() {
                        if (this.int2X4 == null) {
                            this.int2X4 = new ArrayList<Integer>();
                        }
                        return this.int2X4;
                    }
                    
                    public List<Integer> getInt3X2() {
                        if (this.int3X2 == null) {
                            this.int3X2 = new ArrayList<Integer>();
                        }
                        return this.int3X2;
                    }
                    
                    public List<Integer> getInt3X4() {
                        if (this.int3X4 == null) {
                            this.int3X4 = new ArrayList<Integer>();
                        }
                        return this.int3X4;
                    }
                    
                    public List<Float> getFixed3() {
                        if (this.fixed3 == null) {
                            this.fixed3 = new ArrayList<Float>();
                        }
                        return this.fixed3;
                    }
                    
                    public List<Boolean> getBool3X3() {
                        if (this.bool3X3 == null) {
                            this.bool3X3 = new ArrayList<Boolean>();
                        }
                        return this.bool3X3;
                    }
                    
                    public List<Boolean> getBool2X4() {
                        if (this.bool2X4 == null) {
                            this.bool2X4 = new ArrayList<Boolean>();
                        }
                        return this.bool2X4;
                    }
                    
                    public List<Float> getFloat2X2() {
                        if (this.float2X2 == null) {
                            this.float2X2 = new ArrayList<Float>();
                        }
                        return this.float2X2;
                    }
                    
                    public List<Float> getFloat4X3() {
                        if (this.float4X3 == null) {
                            this.float4X3 = new ArrayList<Float>();
                        }
                        return this.float4X3;
                    }
                    
                    public List<Boolean> getBool2X1() {
                        if (this.bool2X1 == null) {
                            this.bool2X1 = new ArrayList<Boolean>();
                        }
                        return this.bool2X1;
                    }
                    
                    public List<Integer> getInt2X1() {
                        if (this.int2X1 == null) {
                            this.int2X1 = new ArrayList<Integer>();
                        }
                        return this.int2X1;
                    }
                    
                    public Boolean isBool1() {
                        return this.bool1;
                    }
                    
                    public void setBool1(final Boolean value) {
                        this.bool1 = value;
                    }
                    
                    public String getSymbol() {
                        return this.symbol;
                    }
                    
                    public void setSymbol(final String value) {
                        this.symbol = value;
                    }
                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "")
                    public static class Param
                    {
                        @XmlAttribute(required = true)
                        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                        protected String ref;
                        
                        public String getRef() {
                            return this.ref;
                        }
                        
                        public void setRef(final String value) {
                            this.ref = value;
                        }
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "value" })
                public static class CompilerTarget
                {
                    @XmlValue
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String value;
                    
                    public String getValue() {
                        return this.value;
                    }
                    
                    public void setValue(final String value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "value" })
                public static class Name
                {
                    @XmlValue
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String value;
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String source;
                    
                    public String getValue() {
                        return this.value;
                    }
                    
                    public void setValue(final String value) {
                        this.value = value;
                    }
                    
                    public String getSource() {
                        return this.source;
                    }
                    
                    public void setSource(final String value) {
                        this.source = value;
                    }
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
            @XmlType(name = "", propOrder = { "front", "back", "ref", "mask" })
            public static class StencilFuncSeparate
            {
                @XmlElement(required = true)
                protected Front front;
                @XmlElement(required = true)
                protected Back back;
                @XmlElement(required = true)
                protected Ref ref;
                @XmlElement(required = true)
                protected Mask mask;
                
                public Front getFront() {
                    return this.front;
                }
                
                public void setFront(final Front value) {
                    this.front = value;
                }
                
                public Back getBack() {
                    return this.back;
                }
                
                public void setBack(final Back value) {
                    this.back = value;
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
                public static class Back
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
                public static class Front
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
            @XmlType(name = "", propOrder = { "face", "mask" })
            public static class StencilMaskSeparate
            {
                @XmlElement(required = true)
                protected Face face;
                @XmlElement(required = true)
                protected Mask mask;
                
                public Face getFace() {
                    return this.face;
                }
                
                public void setFace(final Face value) {
                    this.face = value;
                }
                
                public Mask getMask() {
                    return this.mask;
                }
                
                public void setMask(final Mask value) {
                    this.mask = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Face
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
                            return GlFaceType.FRONT_AND_BACK;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFaceType value) {
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
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
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
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
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
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
                        this.value = value;
                    }
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "face", "fail", "zfail", "zpass" })
            public static class StencilOpSeparate
            {
                @XmlElement(required = true)
                protected Face face;
                @XmlElement(required = true)
                protected Fail fail;
                @XmlElement(required = true)
                protected Zfail zfail;
                @XmlElement(required = true)
                protected Zpass zpass;
                
                public Face getFace() {
                    return this.face;
                }
                
                public void setFace(final Face value) {
                    this.face = value;
                }
                
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
                public static class Face
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
                            return GlFaceType.FRONT_AND_BACK;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlFaceType value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Fail
                {
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String param;
                    @XmlAttribute
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
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
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
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
                    protected GlStencilOpType value;
                    
                    public String getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final String value) {
                        this.param = value;
                    }
                    
                    public GlStencilOpType getValue() {
                        if (this.value == null) {
                            return GlStencilOpType.KEEP;
                        }
                        return this.value;
                    }
                    
                    public void setValue(final GlStencilOpType value) {
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
            @XmlType(name = "", propOrder = { "param", "value" })
            public static class Texture1D
            {
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                protected GlSampler1D value;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlSampler1D getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSampler1D value) {
                    this.value = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Texture1DEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            @XmlType(name = "", propOrder = { "value", "param" })
            public static class Texture2D
            {
                protected GlSampler2D value;
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public GlSampler2D getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSampler2D value) {
                    this.value = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Texture2DEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            @XmlType(name = "", propOrder = { "value", "param" })
            public static class Texture3D
            {
                protected GlSampler3D value;
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public GlSampler3D getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSampler3D value) {
                    this.value = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Texture3DEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            @XmlType(name = "", propOrder = { "value", "param" })
            public static class TextureCUBE
            {
                protected GlSamplerCUBE value;
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public GlSamplerCUBE getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSamplerCUBE value) {
                    this.value = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class TextureCUBEEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            @XmlType(name = "", propOrder = { "param", "value" })
            public static class TextureDEPTH
            {
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                protected GlSamplerDEPTH value;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public GlSamplerDEPTH getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSamplerDEPTH value) {
                    this.value = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class TextureDEPTHEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class TextureEnvColor
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(name = "value")
                protected List<Double> values;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
            public static class TextureEnvMode
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected String value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public String getValue() {
                    return this.value;
                }
                
                public void setValue(final String value) {
                    this.value = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "value", "param" })
            public static class TextureRECT
            {
                protected GlSamplerRECT value;
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute(required = true)
                protected BigInteger index;
                
                public GlSamplerRECT getValue() {
                    return this.value;
                }
                
                public void setValue(final GlSamplerRECT value) {
                    this.value = value;
                }
                
                public String getParam() {
                    return this.param;
                }
                
                public void setParam(final String value) {
                    this.param = value;
                }
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
                    this.index = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class TextureRECTEnable
            {
                @XmlAttribute
                protected BigInteger index;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String param;
                @XmlAttribute
                protected Boolean value;
                
                public BigInteger getIndex() {
                    return this.index;
                }
                
                public void setIndex(final BigInteger value) {
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
        }
    }
}
