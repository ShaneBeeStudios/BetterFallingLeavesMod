package com.shanebeestudios.betterleaves;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterFallingLeaves implements ModInitializer {

	public static final String MOD_ID = "betterfallingleaves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Time for better falling leaves!");
	}

}
