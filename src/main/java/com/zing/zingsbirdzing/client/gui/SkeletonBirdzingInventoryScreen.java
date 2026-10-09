package net.mcreator.zingsbirdzing.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import net.mcreator.zingsbirdzing.world.inventory.SkeletonBirdzingInventoryMenu;
import net.mcreator.zingsbirdzing.procedures.EntityModelInventoryDisplaySkelBirdProcedure;
import net.mcreator.zingsbirdzing.init.ZingsBirdzingModScreens;

import com.mojang.blaze3d.platform.InputConstants;

public class SkeletonBirdzingInventoryScreen extends AbstractContainerScreen<SkeletonBirdzingInventoryMenu> implements ZingsBirdzingModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final Identifier BACKGROUND = Identifier.parse("zings_birdzing:textures/screens/skeleton_birdzing_inventory.png");
	private static final Identifier IMAGE_0 = Identifier.parse("zings_birdzing:textures/screens/birdzing_armor_gui_slot.png");

	public SkeletonBirdzingInventoryScreen(SkeletonBirdzingInventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 176, 168);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
		if (EntityModelInventoryDisplaySkelBirdProcedure.execute(world) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + -964, this.topPos + -948, this.leftPos + 1036, this.topPos + 1052, 30, -livingEntity.getBbHeight() / (2.0f * livingEntity.getScale()),
					0f + (float) Math.atan((this.leftPos + 36 - mouseX) / 40.0), (float) Math.atan((this.topPos + 3 - mouseY) / 40.0), livingEntity);
		}
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 98, this.topPos + 10, 0, 0, 16, 16, 16, 16);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		int key = InputConstants.getKey(event).getValue();
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
	}
}