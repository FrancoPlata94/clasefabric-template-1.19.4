package com.franco.clasefabric;

import com.franco.clasefabric.GruposItems.ItemGroupsMod;
import com.franco.clasefabric.blocks.ModFLAMABLEBOCK;
import com.franco.clasefabric.blocks.Modblock;
import com.franco.clasefabric.items.ItemMod;
import com.franco.clasefabric.items.itemCombustible;
import com.franco.clasefabric.items.itemComida;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClaseFabric implements ModInitializer {
	public static final String MOD_ID = "clasefabric";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ItemMod.registerItems();
		ItemGroupsMod.registerItemGroups();
		Modblock.registerBlock();
		ModFLAMABLEBOCK.registerFlamableblock();
		itemCombustible.registerFuels();
		itemComida.RegistrandoItemsComida();

	}
}