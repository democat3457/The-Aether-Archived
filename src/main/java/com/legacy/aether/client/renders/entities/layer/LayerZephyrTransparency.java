package com.legacy.aether.client.renders.entities.layer;

import com.legacy.aether.AetherConfig;
import com.legacy.aether.client.models.entities.ZephyrModel;
import com.legacy.aether.client.renders.entities.ZephyrRenderer;
import com.legacy.aether.entities.hostile.EntityZephyr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerZephyrTransparency implements LayerRenderer<EntityZephyr>
{
    private final ZephyrRenderer zephyrRenderer;
    private final ModelBase zephyrModel = new ZephyrModel();

    private static final ResourceLocation TRANS = new ResourceLocation("aether_legacy", "textures/entities/zephyr/zephyr_layer.png");

    
    public LayerZephyrTransparency(ZephyrRenderer zephyrRendererIn)
    {
        this.zephyrRenderer = zephyrRendererIn;
    }

    public void doRenderLayer(EntityZephyr entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.isInvisible() && !AetherConfig.oldMobsEnabled())
        {
        	RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();	
    		renderManager.renderEngine.bindTexture(TRANS);
    		
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.zephyrModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
            this.zephyrModel.setModelAttributes(this.zephyrRenderer.getMainModel());
            this.zephyrModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}