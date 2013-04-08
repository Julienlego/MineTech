package mod.minetech.util;

import mod.minetech.MTCore;
import mod.minetech.MTCoreAdvanced;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Manages ALL recipes for Mod
 * */
public class RecipeManager{
		
	/** Adds all mod recipes */
	public static void addAllRecipes(){
		addItemRecipes();
		addBlockRecipes();
		addToolRecipes();
		addSmeltingRecipes();
	}
	
	/** Adds all Item recipes */
	private static void addItemRecipes(){
        GameRegistry.addShapelessRecipe(new ItemStack(MTCore.glycerinNitro, 1), new Object[]{Item.glassBottle, MTCore.glycerin});
        
        if(MTCore.isAdvanced()){
        	//GameRegistry.addShapelessRecipe(new ItemStack(MTCoreAdvanced.copperWireItem, 6), new Object[]{MTCore.copperIngot, MTCore.copperIngot, MTCore.copperIngot});
	        //GameRegistry.addShapelessRecipe(new ItemStack(MTCoreAdvanced.aluminiumWireItem, 6), new Object[]{MTCore.aluminiumIngot, MTCore.aluminiumIngot, MTCore.aluminiumIngot});
	        GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.twostrokeEngine, 1), new Object[]{" S ", "SSS", "SSS", Character.valueOf('S'), MTCore.steelIngot});
	        GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.alternator, 1), new Object[]{"SSS", "SES", "SSS", Character.valueOf('S'), MTCore.steelIngot, Character.valueOf('E'), MTCoreAdvanced.electricMotor});
	        GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.aluminiumCasing, 1), new Object[]{"   ", "A A", "AAA", Character.valueOf('A'), MTCore.aluminiumIngot});
	        //GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.electricMotor, 1), new Object[]{"CIC", "IWI", "CIC", Character.valueOf('C'), MTCoreAdvanced.aluminiumCasing, Character.valueOf('I'), Item.ingotIron, Character.valueOf('W'), MTCoreAdvanced.aluminiumWire});
	        //ModLoader.addRecipe(new ItemStack(MTCoreAdvanced.electricMotor, 1), new Object[]{});
        }
	}
	
	/** Adds all Block recipes */
	private static void addBlockRecipes(){
		GameRegistry.addRecipe(new ItemStack(MTCore.bioCarbonizerOff,1), new Object[]{"ICI", "ILI", "ICI",Character.valueOf('I'),Item.ingotIron, Character.valueOf('C'),Item.coal,Character.valueOf('L'),Item.bucketLava});
        GameRegistry.addRecipe(new ItemStack(MTCore.magnesiumBlock, 1), new Object[]{"DD", "DD",Character.valueOf('D'),MTCore.magnesiumDust});
        GameRegistry.addRecipe(new ItemStack(MTCore.blastOff,1), new Object[]{"SSS", "SFS", "SBS",Character.valueOf('S'),Block.stoneBrick,Character.valueOf('B'),Item.bucketLava,Character.valueOf('F'),Block.furnaceIdle});
        GameRegistry.addRecipe(new ItemStack(MTCore.magnesiumBrick, 1), new Object[]{"SDS" , "DSD", "SDS",Character.valueOf('S'),Block.gravel,Character.valueOf('D'),MTCore.magnesiumBlock});
        GameRegistry.addRecipe(new ItemStack(MTCore.stickyTorch, 1), new Object[]{"B","S","G",'B', MTCore.magnesiumBlock,'S', Item.stick,'G', MTCore.slimegel});
        GameRegistry.addRecipe(new ItemStack(MTCore.incineratorOff, 1), new Object[]{"BBB", "BFB", "BLB", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('F'), Block.furnaceIdle, Character.valueOf('L'), Item.bucketLava});
        //ModLoader.addRecipe(new ItemStack(MTCore.miningTnt, 1), new Object[]{"DGD", "GDG", "DGD", Character.valueOf('D'), MTCore.magnesiumDust, Character.valueOf('G'), Block.gravel});
        //GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.combGenOff, 1), new Object[]{"SSS", "SES", "SAS",'S', MTCore.steelIngot,'E', MTCoreAdvanced.twostrokeEngine,'A', MTCoreAdvanced.alternator});
        //GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.geoGenOff, 1), new Object[]{"OOO", "OBO", "OOO", Character.valueOf('O'), Block.obsidian, Character.valueOf('B'), MTCoreAdvanced.combGenOff});
        //GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.inductionFurnaceOff, 1), new Object[]{ "SSS", "SFS", "SWS", Character.valueOf('S'), MTCore.steelIngot, Character.valueOf('F'), Block.stoneOvenIdle, Character.valueOf('W'), MTCoreAdvanced.aluminiumWire});
        //GameRegistry.addRecipe(new ItemStack(MTCoreAdvanced.transformer, 1), new Object[]{"ISS", "IWS", "IAS", Character.valueOf('I'), Item.ingotIron, Character.valueOf('W'), Item.bucketWater, Character.valueOf('A'), MTCoreAdvanced.aluminiumWire});
	}
	
	/** Adds all tool and sword recipes */
	private static void addToolRecipes(){
        RecipeHelper.addPickaxeRecipe(MTCore.pickaxeCopper, MTCore.copperIngot);
        RecipeHelper.addAxeRecipe(MTCore.axeCopper, MTCore.copperIngot);
        RecipeHelper.addShovelRecipe(MTCore.shovelCopper, MTCore.copperIngot);
        RecipeHelper.addSwordRecipe(MTCore.swordCopper, MTCore.copperIngot);
        RecipeHelper.addHoeRecipe(MTCore.hoeCopper, MTCore.copperIngot);
        
        RecipeHelper.addPickaxeRecipe(MTCore.pickaxeAluminium, MTCore.aluminiumIngot);                
        RecipeHelper.addAxeRecipe(MTCore.axeAluminium, MTCore.aluminiumIngot);
        RecipeHelper.addShovelRecipe(MTCore.shovelAluminium, MTCore.aluminiumIngot);
        RecipeHelper.addSwordRecipe(MTCore.swordAluminium, MTCore.aluminiumIngot);                
        RecipeHelper.addHoeRecipe(MTCore.hoeAluminium, MTCore.aluminiumIngot);
        
        RecipeHelper.addPickaxeRecipe(MTCore.pickaxeTitanium, MTCore.titaniumIngot);
        RecipeHelper.addAxeRecipe(MTCore.axeTitanium, MTCore.titaniumIngot);
        RecipeHelper.addShovelRecipe(MTCore.shovelTitanium, MTCore.titaniumIngot);
        RecipeHelper.addSwordRecipe(MTCore.swordTitanium, MTCore.titaniumIngot);
        RecipeHelper.addHoeRecipe(MTCore.hoeTitanium, MTCore.titaniumIngot);

        RecipeHelper.addPickaxeRecipe(MTCore.pickaxeSteel, MTCore.steelIngot);
        RecipeHelper.addAxeRecipe(MTCore.axeSteel, MTCore.steelIngot);
        RecipeHelper.addShovelRecipe(MTCore.shovelSteel, MTCore.steelIngot);
        RecipeHelper.addSwordRecipe(MTCore.swordSteel, MTCore.steelIngot);
        RecipeHelper.addHoeRecipe(MTCore.hoeSteel, MTCore.steelIngot);
        
        RecipeHelper.addPickaxeRecipe(MTCore.pickaxeCarbonSteel, MTCore.carbonsteelIngot);
        RecipeHelper.addAxeRecipe(MTCore.axeCarbonSteel, MTCore.carbonsteelIngot);
        RecipeHelper.addShovelRecipe(MTCore.shovelCarbonSteel, MTCore.carbonsteelIngot);
        RecipeHelper.addSwordRecipe(MTCore.swordCarbonSteel, MTCore.carbonsteelIngot);
        RecipeHelper.addHoeRecipe(MTCore.hoeCarbonSteel, MTCore.carbonsteelIngot);
        
        GameRegistry.addRecipe(new ItemStack(MTCore.pickaxeObsidianTitanium, 1), new Object[]{
            "OOO", " | ", " | ", Character.valueOf('O'),MTCore.obsidiantitaniumIngot, Character.valueOf('|'),Item.bone});
        GameRegistry.addRecipe(new ItemStack(MTCore.axeObsidianTitanium, 1), new Object[]{
            "OO ", "O| ", " | ", Character.valueOf('O'),MTCore.obsidiantitaniumIngot, Character.valueOf('|'),Item.bone});
        GameRegistry.addRecipe(new ItemStack(MTCore.shovelObsidianTitanium, 1), new Object[]{
            " O ", " | ", " | ", Character.valueOf('O'),MTCore.obsidiantitaniumIngot, Character.valueOf('|'),Item.bone});
        GameRegistry.addRecipe(new ItemStack(MTCore.swordObsidianTitanium, 1), new Object[]{
            " O ", " O ", " | ", Character.valueOf('O'),MTCore.obsidiantitaniumIngot, Character.valueOf('|'),Item.bone});
        GameRegistry.addRecipe(new ItemStack(MTCore.hoeObsidianTitanium, 1), new Object[]{
            "OO ", " | ", " | ", Character.valueOf('O'),MTCore.obsidiantitaniumIngot, Character.valueOf('|'),Item.bone});
        
        if(MTCore.isAdvanced()){
        	
        }
	}
	
	/** Adds all Smelting recipes */
	private static void addSmeltingRecipes(){
		GameRegistry.addSmelting(MTCore.bauxiteOre.blockID, new ItemStack(MTCore.aluminiumIngot), 0.6F);
        GameRegistry.addSmelting(MTCore.copperOre.blockID, new ItemStack(MTCore.copperIngot), 0.5F);
        GameRegistry.addSmelting(MTCore.titaniumOre.blockID, new ItemStack(MTCore.titaniumSponge), 2F);
        GameRegistry.addSmelting(MTCore.titaniumSponge.itemID, new ItemStack(MTCore.titaniumIngot), 1.5F);
        GameRegistry.addSmelting(Item.slimeBall.itemID, new ItemStack(MTCore.slimegel), 1.0F);
        GameRegistry.addSmelting(Block.obsidian.blockID, new ItemStack(MTCore.obsidianIngot), 1.2F);
	}

}
