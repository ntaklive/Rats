package com.github.alexthe666.rats.client.render.entity;

import com.github.alexthe666.rats.server.entity.EntityFeralRatlantean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LayerBasicOverlay implements LayerRenderer<EntityLiving> {
    private final RenderLiving ratRenderer;
    private final ResourceLocation texture;

    public LayerBasicOverlay(RenderLiving ratRendererIn, ResourceLocation texture) {
        this.ratRenderer = ratRendererIn;
        this.texture = texture;
    }

    public void doRenderLayer(EntityLiving rat, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.ratRenderer.bindTexture(texture);
        this.ratRenderer.getMainModel().render(rat, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
