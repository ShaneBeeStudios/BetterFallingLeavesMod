package com.shanebeestudios.betterleaves;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundChangeGameModePacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GameType;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class BetterFallingLeaves implements ClientModInitializer {

	public static final String MOD_ID = "betterfallingleaves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean IS_BETTER_FALLING_LEAVES = true;

	@Override
	public void onInitializeClient() {
		LOGGER.info("Time for better falling leaves!");
		registerKeybind();
	}

	private static KeyMapping gamemodeSwitchKeybind;
	private static KeyMapping betterFallingKeybind;
	private static KeyMapping.Category CATEGORY;

	private static void registerKeybind() {
		CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "title"));
		gamemodeSwitchKeybind = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.betterfallingleaves.gamemode_change",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_O, CATEGORY));

		betterFallingKeybind = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.betterfallingleaves.better_leaves",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_M, CATEGORY));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			LocalPlayer player = client.player;
			assert player != null;
			if (gamemodeSwitchKeybind.consumeClick()) {
				GameType playerMode = player.gameMode();
				ServerboundChangeGameModePacket packet = new ServerboundChangeGameModePacket(
					playerMode == GameType.CREATIVE ? GameType.SURVIVAL : GameType.CREATIVE);
				player.connection.send(packet);
			}
			if (betterFallingKeybind.consumeClick()) {
				if (IS_BETTER_FALLING_LEAVES) {
					IS_BETTER_FALLING_LEAVES = false;
					player.sendSystemMessage(Component.literal("Better Falling Leaves toggled off!"));
				} else {
					IS_BETTER_FALLING_LEAVES = true;
					player.sendSystemMessage(Component.literal("Better Falling Leaves toggled on!"));
				}
			}
		});
	}
}
