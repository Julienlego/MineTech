package mods.minetech.util;
import java.util.HashMap;
import java.util.Map;

import mods.minetech.MTCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BioCarbonizerRecipes
{
    private static final BioCarbonizerRecipes smeltingBase = new BioCarbonizerRecipes();
    private Map smeltingList;
    
    public static final BioCarbonizerRecipes smelting()
    {
        return smeltingBase;
    }
    
    private BioCarbonizerRecipes()
    {
        this.smeltingList = new HashMap();
        addSmelting(Item.appleRed.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.bread.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.cake.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.cookie.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.fishCooked.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.fishRaw.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.leather.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.porkCooked.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.porkRaw.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.reed.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.seeds.itemID, new ItemStack(MTCore.coke));
        addSmelting(Block.sapling.blockID, new ItemStack(MTCore.coke));
        addSmelting(Item.wheat.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.melon.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.pumpkinSeeds.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.melonSeeds.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.beefRaw.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.beefCooked.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.rottenFlesh.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.chickenCooked.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.chickenRaw.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.egg.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.silk.itemID, new ItemStack(MTCore.coke));
        addSmelting(Item.spiderEye.itemID, new ItemStack(MTCore.coke));
    }

    public void addSmelting(int i, ItemStack itemstack)
    {
        this.smeltingList.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack getSmeltingResult(int i)
    {
        if(this.smeltingList.get(Integer.valueOf(i)) == null){
            return null;
        }
        return (ItemStack)this.smeltingList.get(Integer.valueOf(i));
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }
}