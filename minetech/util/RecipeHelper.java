package mods.minetech.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHelper {
	
	/** 
	 * Adds a Sword recipe
	 * Par:result,ingot
	 * */
	public static void addSwordRecipe(Item result, Item ingot){
		GameRegistry.addRecipe(new ItemStack(result,1), " x "," x "," y ",'x',ingot,'y',Item.stick);
	}
	
	/**
	 * Adds a Shovel recipe
	 * Par:result,ingot
	 */
	public static void addShovelRecipe(Item result, Item ingot){
		GameRegistry.addRecipe(new ItemStack(result,1), " x "," y "," y ",'x',ingot,'y',Item.stick);
	}
	
	/**
	 * Adds a Pickaxe recipe
	 * Par:result,ingot
	 */
	public static void addPickaxeRecipe(Item result, Item ingot){
		GameRegistry.addRecipe(new ItemStack(result,1), "xxx"," y "," y ",'x',ingot,'y',Item.stick);
	}
	
	/**
	 * Adds an Axe recipe
	 * Par:result,ingot
	 */
	public static void addAxeRecipe(Item result, Item ingot){
		GameRegistry.addRecipe(new ItemStack(result,1), "xx ","xy "," y ",'x',ingot,'y',Item.stick);
	}
	
	/**
	 * Adds a Hoe recipe
	 * Par:result,ingot
	 */
	public static void addHoeRecipe(Item result, Item ingot){
		GameRegistry.addRecipe(new ItemStack(result,1), "xx "," y "," y ",'x',ingot,'y',Item.stick);
	}

}
