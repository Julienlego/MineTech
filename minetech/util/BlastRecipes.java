package mods.minetech.util;
import mods.minetech.MTCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlastRecipes{
    
    public static ItemStack getSmeltingResult(int i, int j){
        return getOutput(i, j);
    }
    
    private static ItemStack getOutput(int i, int j){
        //graphite flakes + steel ingot = carbon steel ingot
        if((i == MTCore.graphiteFlakes.itemID && j == MTCore.steelIngot.itemID) || (i == MTCore.steelIngot.itemID && j == MTCore.graphiteFlakes.itemID))
        {
            return new ItemStack(MTCore.carbonsteelIngot, 1);
        }
        //coal + iron ingot = steel ingot
        if((i == Item.coal.itemID && j == Item.ingotIron.itemID) || (i == Item.ingotIron.itemID && j == Item.coal.itemID))
        {
            return new ItemStack(MTCore.steelIngot, 1);
        }
        //obsidian block + titanium ingot = obsidian titanium ingot
        if((i == MTCore.titaniumIngot.itemID && j == MTCore.obsidianIngot.itemID) || (i == MTCore.obsidianIngot.itemID && j == MTCore.titaniumIngot.itemID))
        {
            return new ItemStack(MTCore.obsidiantitaniumIngot, 1);
        }
        //slimegel + magnesium dust = glycerin
        if((i == MTCore.slimegel.itemID && j == MTCore.magnesiumDust.itemID) || (i == MTCore.magnesiumDust.itemID && j == MTCore.slimegel.itemID)){
            return new ItemStack(MTCore.glycerin, 1);
        }
        
        //glass + rose red = red glass
        if((i == Block.glass.blockID && j == new ItemStack(Item.dyePowder, 1, 1).itemID) || (i == new ItemStack(Item.dyePowder, 1, 1).itemID && j == Block.glass.blockID)){
        	return new ItemStack(MTCore.redGlass, 1);
        }
        //glass + cactus green = green glass
        if((i == Block.glass.blockID && j == new ItemStack(Item.dyePowder, 1, 2).itemID) || (i == new ItemStack(Item.dyePowder, 1, 2).itemID && j == Block.glass.blockID)){
        	return new ItemStack(MTCore.greenGlass, 1);
        }
        //glass + lapis lazuli dye = blue glass
        if((i == Block.glass.blockID && j == new ItemStack(Item.dyePowder, 1, 4).itemID) || (i == new ItemStack(Item.dyePowder, 1, 4).itemID && j == Block.glass.blockID)){
        	return new ItemStack(MTCore.blueGlass, 1);
        }
        //glass+ dandelion yellow = yellow glass
        if((i == Block.glass.blockID && j == new ItemStack(Item.dyePowder, 1, 11).itemID) || (i == new ItemStack(Item.dyePowder, 1, 11).itemID && j == Block.glass.blockID)){
        	return new ItemStack(MTCore.yellowGlass, 1);
        }
        else{return null;}
    }
}
