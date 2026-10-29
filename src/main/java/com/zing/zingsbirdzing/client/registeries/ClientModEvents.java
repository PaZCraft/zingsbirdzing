package com.zing.zingsbirdzing.client.registeries;

import com.zing.zingsbirdzing.init.ZingsBirdzingModItems;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = "zings_birdzing", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.ItemTintSources event) {
        event.register((itemStack, tintIndex) -> {
            // tintIndex corresponds to the "tintindex" specified in your item model JSON.
            // tintIndex == 0 means we only color the specified layer.
            if (tintIndex == 0) {
                DyedItemColor dyedColor = itemStack.get(DataComponents.DYED_COLOR);
                return dyedColor != null ? dyedColor.rgb() : 0xA06540;
            }
            return -1; // -1 means do not apply any tint/coloration
        }, ZingsBirdzingModItems.LEATHER_BIRDZING_ARMOR.get());
    }
}