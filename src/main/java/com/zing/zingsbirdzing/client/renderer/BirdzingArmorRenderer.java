package com.zing.zingsbirdzing.client.renderer;

import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.model.EntityModel;
import com.zing.zingsbirdzing.client.model.Modelbirdzing;
import com.zing.zingsbirdzing.entity.BirdzingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class BirdzingArmorRenderer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    
    public BirdzingArmorRenderer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, 
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, 
                       float netHeadYaw, float headPitch) {
        
        // Loop through the armor slots
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor()) continue;

            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem armorItem)) continue;

            // 1. Determine the path to the texture.
            // Vanilla textures look like: minecraft:textures/models/armor/diamond_layer_1.png
            // Leggings use layer_2, everything else uses layer_1.
            String textureLayer = (slot == EquipmentSlot.BODY) ? "layer_2" : "layer_1";
            String materialName = armorItem.getMaterial().unwrapKey().map(key -> key.location().getPath()).orElse("iron");
            
            ResourceLocation textureLocation = ResourceLocation.withDefaultNamespace(
                    "textures/entity/birdzing/armor/" + "armor_birdzing_" + materialName + ".png"
            );

            // 2. Fetch color data if the item is dyeable (Leather Armor)
            int color = 0xFFFFFF; // Default white tint (no alteration)
            if (stack.has(DataComponents.DYED_COLOR)) {
                DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
                if (dyedColor != null) {
                    color = dyedColor.rgb();
                }
            }

            // Extract RGB components for rendering shaders
            float r = (float)(color >> 16 & 255) / 255.0F;
            float g = (float)(color >> 8 & 255) / 255.0F;
            float b = (float)(color & 255) / 255.0F;

            // 3. Render your custom model parts matching this armor slot
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(textureLocation));
            M entityModel = this.getParentModel();
            
            // Sync current animations to the model parts before rendering
            entityModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            
            // Draw the texture over your custom model using the calculated color tint
            entityModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
            
            // 4. Handle Leather Armor Overlay (The static brown seams/buckles on leather textures)
            if (stack.has(DataComponents.DYED_COLOR)) {
                ResourceLocation overlayLocation = ResourceLocation.withDefaultNamespace(
                        "textures/entity/birdzing/armor/" + "armor_birdzing_" + materialName + ".png"
                );
                VertexConsumer overlayConsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(overlayLocation));
                // Overlays ignore the dye color (render with standard white 1.0F parameters)
                entityModel.renderToBuffer(poseStack, overlayConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}