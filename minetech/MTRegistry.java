package mods.minetech;

import mods.minetech.entities.EntityNitroGlycerin;
import mods.minetech.entities.EntityStickyTorch;
import mods.minetech.tileentities.TileEntityBioCarbonizer;
import mods.minetech.tileentities.TileEntityBlast;
import mods.minetech.tileentities.TileEntityIncinerator;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MTRegistry{
	
	//Registers names for all blocks, items, entities
		public static void registerNames(){
			
	        LanguageRegistry.addName(MTCore.redGlass, "Red Glass");
	        LanguageRegistry.addName(MTCore.blueGlass, "Blue Glass");
	        LanguageRegistry.addName(MTCore.greenGlass, "Green Glass");
	        LanguageRegistry.addName(MTCore.yellowGlass, "Yellow Glass");
	        LanguageRegistry.addName(MTCore.coke, "Coke");
	        LanguageRegistry.addName(MTCore.bioCarbonizerOff, "Bio Carbonizer");
	        LanguageRegistry.addName(MTCore.blastOff, "Blast");
	        LanguageRegistry.addName(MTCore.bauxiteOre, "Bauxite");
	        LanguageRegistry.addName(MTCore.copperOre, "Porphyry Copper");
	        LanguageRegistry.addName(MTCore.aluminiumIngot, "Aluminum Ingot");
	        LanguageRegistry.addName(MTCore.copperIngot, "Copper Ingot");
	        LanguageRegistry.addName(MTCore.graphiteFlakes, "Graphite Flakes");
	        LanguageRegistry.addName(MTCore.lumpgraphite, "Lump Graphite");
	        LanguageRegistry.addName(MTCore.titaniumOre, "Titanium Ore");
	        LanguageRegistry.addName(MTCore.dolomite, "Dolomite");
	        LanguageRegistry.addName(MTCore.magnesiumBrick, "Magnesium Brick");
	        LanguageRegistry.addName(MTCore.titaniumIngot, "Titanium Ingot");
	        LanguageRegistry.addName(MTCore.titaniumSponge, "Titanium Sponge");
	        LanguageRegistry.addName(MTCore.magnesiumDust, "Magnesium Dust");
	        LanguageRegistry.addName(MTCore.magnesiumBlock, "Magnesium Block");
	        LanguageRegistry.addName(MTCore.steelIngot, "Steel Ingot");
	        LanguageRegistry.addName(MTCore.carbonsteelIngot, "Carbon-Steel Ingot");
	        LanguageRegistry.addName(MTCore.obsidiantitaniumIngot, "Obsidian-Titanium Ingot");
	        LanguageRegistry.addName(MTCore.swordCopper, "Copper Sword");
	        LanguageRegistry.addName(MTCore.pickaxeCopper, "Copper Pickaxe");
	        LanguageRegistry.addName(MTCore.axeCopper, "Copper Axe");
	        LanguageRegistry.addName(MTCore.hoeCopper, "Copper Hoe");
	        LanguageRegistry.addName(MTCore.shovelCopper, "Copper Shovel");
	        LanguageRegistry.addName(MTCore.swordAluminium, "Aluminium Sword");
	        LanguageRegistry.addName(MTCore.pickaxeAluminium, "Aluminium Pickaxe");
	        LanguageRegistry.addName(MTCore.axeAluminium, "Aluminium Axe");
	        LanguageRegistry.addName(MTCore.hoeAluminium, "Aluminium How");
	        LanguageRegistry.addName(MTCore.shovelAluminium, "Aluminium Shovel");
	        LanguageRegistry.addName(MTCore.swordTitanium, "Titanium Sword");
	        LanguageRegistry.addName(MTCore.pickaxeTitanium, "Titanium Pickaxe");
	        LanguageRegistry.addName(MTCore.axeTitanium, "Titanium Axe");
	        LanguageRegistry.addName(MTCore.hoeTitanium, "Titanium Hoe");
	        LanguageRegistry.addName(MTCore.shovelTitanium, "Ttitanium Shovel");
	        LanguageRegistry.addName(MTCore.swordSteel,"Steel Sword");
	        LanguageRegistry.addName(MTCore.pickaxeSteel, "Steel Pickaxe");
	        LanguageRegistry.addName(MTCore.axeSteel, "Steel Axe");
	        LanguageRegistry.addName(MTCore.hoeSteel, "Steel Hoe");
	        LanguageRegistry.addName(MTCore.shovelSteel, "Steel Shovel");
	        LanguageRegistry.addName(MTCore.swordCarbonSteel, "Carbon-Steel Sword");
	        LanguageRegistry.addName(MTCore.pickaxeCarbonSteel, "Carbon-Steel Pickaxe");
	        LanguageRegistry.addName(MTCore.axeCarbonSteel, "Carbon-Steel Axe");
	        LanguageRegistry.addName(MTCore.hoeCarbonSteel, "Carbon-Steel Hoe");
	        LanguageRegistry.addName(MTCore.shovelCarbonSteel, "Carbon-Steel Shovel");
	        LanguageRegistry.addName(MTCore.swordObsidianTitanium, "Obsidian-Titanium Sword");
	        LanguageRegistry.addName(MTCore.pickaxeObsidianTitanium, "Obsidian-Titanium Pickaxe");
	        LanguageRegistry.addName(MTCore.axeObsidianTitanium, "Obsidian-Titanium Axe");
	        LanguageRegistry.addName(MTCore.hoeObsidianTitanium, "Obsidian-Titanium Hoe");
	        LanguageRegistry.addName(MTCore.shovelObsidianTitanium, "Obsidian-Titanium Shovel");
	        LanguageRegistry.addName(MTCore.slimegel, "Slime Gel");
	        LanguageRegistry.addName(MTCore.glycerin, "Glycerin");
	        LanguageRegistry.addName(MTCore.stickyTorch, "Sticky Torch");
	        LanguageRegistry.addName(MTCore.uraninite, "Uranitite");
	        LanguageRegistry.addName(MTCore.obsidianIngot, "Obsidian Ingot");
	        LanguageRegistry.addName(MTCore.glycerinNitro, "Nitro-Glycerin");
	        LanguageRegistry.addName(MTCore.stickyglowBlock, "Sticky Torch Block");
	        LanguageRegistry.addName(MTCore.ash, "Ash");
	        LanguageRegistry.addName(MTCore.miningTnt, "Mining TNT");
	        LanguageRegistry.addName(MTCore.incineratorOff, "Incinerator");
	        
	        /* adds Advanced Machines if enabled */
	        if(MTCore.isAdvanced()){
	        	//LanguageRegistry.addName(MTCoreAdvanced.transformer, "Transformer");
		        //LanguageRegistry.addName(MTCoreAdvanced.inductionFurnaceOff, "Induction Furnace");
		        //LanguageRegistry.addName(MTCoreAdvanced.geoGenOff, "Thermal Generator");
		        //LanguageRegistry.addName(MTCoreAdvanced.combGenOff, "Combustion Generator");
		        //LanguageRegistry.addName(MTCoreAdvanced.copperWire, "Copper Wire");
		        //LanguageRegistry.addName(MTCoreAdvanced.aluminiumWireItem, "Aluminium Wire");
		        //LanguageRegistry.addName(MTCoreAdvanced.copperWireItem, "Copper Wire");
		        //LanguageRegistry.addName(MTCoreAdvanced.aluminiumWire, "Aluminium Wire");
	        	LanguageRegistry.addName(MTCoreAdvanced.twostrokeEngine, "2-Stroke Engine");
		        LanguageRegistry.addName(MTCoreAdvanced.alternator, "Alternator");
		        LanguageRegistry.addName(MTCoreAdvanced.electricMotor, "Electric Motor");
		        LanguageRegistry.addName(MTCoreAdvanced.aluminiumCasing, "Aluminium Casing");
	        }
		}
		
		//Registers all blocks
		public static void registerBlocks(){
	        GameRegistry.registerBlock(MTCore.miningTnt, "Mining TNT");
	        GameRegistry.registerBlock(MTCore.bauxiteOre, "Bauxite");
	        GameRegistry.registerBlock(MTCore.bioCarbonizerOff, "Bio Carbonizer");
	        GameRegistry.registerBlock(MTCore.copperOre, "Porphyry Copper");
	        GameRegistry.registerBlock(MTCore.lumpgraphite, "Lump Graphite");
	        GameRegistry.registerBlock(MTCore.dolomite, "Dolomite");
	        GameRegistry.registerBlock(MTCore.titaniumOre, "Titanium Ore");
	        GameRegistry.registerBlock(MTCore.magnesiumBrick, "Magnesium Brick");
	        GameRegistry.registerBlock(MTCore.magnesiumBlock, "Magnesium Block");
	        GameRegistry.registerBlock(MTCore.blastOff, "Blast");
	        GameRegistry.registerBlock(MTCore.uraninite, "Uraninite");
	        GameRegistry.registerBlock(MTCore.stickyglowBlock, "Sticky Glow Block");
	        GameRegistry.registerBlock(MTCore.redGlass, "Red Glass");
	        GameRegistry.registerBlock(MTCore.blueGlass, "Blue Glass");
	        GameRegistry.registerBlock(MTCore.greenGlass, "Green Glass");
	        GameRegistry.registerBlock(MTCore.yellowGlass, "Yellow Glass");
	        GameRegistry.registerBlock(MTCore.incineratorOff, "Incinerator");
	        
	        /* adds Advanced Machines if enabled */
	        if(MTCore.isAdvanced()){
	        	/*
	        	GameRegistry.registerBlock(MTCoreAdvanced.inductionFurnaceOff);
		        GameRegistry.registerBlock(MTCoreAdvanced.inductionFurnaceOn);
		        GameRegistry.registerBlock(MTCoreAdvanced.geoGenOff);
		        GameRegistry.registerBlock(MTCoreAdvanced.geoGenOn);
		        GameRegistry.registerBlock(MTCoreAdvanced.transformer);
		        GameRegistry.registerBlock(MTCoreAdvanced.combGenOff);
		        GameRegistry.registerBlock(MTCoreAdvanced.combGenOn);
		        GameRegistry.registerBlock(MTCoreAdvanced.copperWire);
		        GameRegistry.registerBlock(MTCoreAdvanced.aluminiumWire);
		        */
	        }
		}
		
		/** registers all entities and tile entities*/
		public static void registerAllEntities(){
			//EntityRegistry.registerModEntity(EntityNitroGlycerin.class, "nitroGlycerin", 1, MTCore.instance, 80, 3, false);
			//EntityRegistry.registerModEntity(EntityStickyTorch.class, "stickyTorch", 2, MTCore.instance, 80, 3, false);
			ModLoader.registerEntityID(EntityNitroGlycerin.class, "nitroGlycerin", 300);
			ModLoader.registerEntityID(EntityStickyTorch.class, "stickyTorch", 301);
			
			GameRegistry.registerTileEntity(TileEntityBioCarbonizer.class, "bioCarbonizer");
			GameRegistry.registerTileEntity(TileEntityBlast.class, "blast");
			GameRegistry.registerTileEntity(TileEntityIncinerator.class, "incinerator");
		}
		
		public static void OreRegistry(){
			OreDictionary.registerOre("ingotCopper", new ItemStack(MTCore.copperIngot));
			OreDictionary.registerOre("ingotAluminium", new ItemStack(MTCore.aluminiumIngot));
			OreDictionary.registerOre("ingotSteel", new ItemStack(MTCore.steelIngot));
			OreDictionary.registerOre("ingotCarbonSteel", new ItemStack(MTCore.carbonsteelIngot));
			OreDictionary.registerOre("ingotTitanium", new ItemStack(MTCore.titaniumIngot));
			OreDictionary.registerOre("oreCopper", MTCore.copperOre);
			OreDictionary.registerOre("oreAluminium", MTCore.bauxiteOre);
			OreDictionary.registerOre("oreMagnesium", MTCore.dolomite);
			OreDictionary.registerOre("oreTitanium", MTCore.titaniumOre);
			OreDictionary.registerOre("oreUranium", MTCore.uraninite);
		}
		
		public static void addSmeltingRecipes(){
			GameRegistry.addSmelting(MTCore.copperOre.blockID, new ItemStack(MTCore.copperIngot, 1), 1.0F);
			GameRegistry.addSmelting(MTCore.bauxiteOre.blockID, new ItemStack(MTCore.aluminiumIngot, 1), 1.0F);
			GameRegistry.addSmelting(MTCore.titaniumOre.blockID, new ItemStack(MTCore.titaniumSponge, 1), 2.0F);
			GameRegistry.addSmelting(MTCore.titaniumSponge.itemID, new ItemStack(MTCore.titaniumIngot, 1), 2F);
		}
}
