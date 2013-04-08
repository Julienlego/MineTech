package mod.minetech.items;

import mod.minetech.MTCore;
import mod.minetech.MTEnumToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class GenericPickaxe extends GenericItemTool{
	
	private static Block blocksEffectiveAgainst[]  = {
		Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel,
		Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis,
		Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, MTCore.bauxiteOre, MTCore.copperOre,
		MTCore.dolomite, MTCore.lumpgraphite, MTCore.magnesiumBrick, MTCore.titaniumOre
    };
	protected MTEnumToolMaterial toolMaterial;

    public GenericPickaxe(int i, MTEnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
        this.toolMaterial = enumtoolmaterial;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        this.efficiencyOnProperMaterial = enumtoolmaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = (2 + enumtoolmaterial.getDamageVsEntity());
    }

    public boolean canHarvestBlock(Block block)
    {
        if(block == Block.obsidian)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        if(block == Block.blockDiamond || block == Block.oreDiamond)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if(block == Block.blockGold || block == Block.oreGold)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if(block == Block.blockSteel || block == Block.oreIron)
        {
            return this.toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == Block.blockLapis || block == Block.oreLapis)
        {
            return this.toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == Block.oreRedstone || block == Block.oreRedstoneGlowing)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if(block.blockMaterial == Material.rock)
        {
            return true;
        }
        if(block == MTCore.bauxiteOre){
            return this.toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == MTCore.copperOre){
            return this.toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == MTCore.dolomite || block == MTCore.magnesiumBlock){
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if(block == MTCore.titaniumOre){
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        if(block == MTCore.lumpgraphite){
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        return block.blockMaterial == Material.iron;
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        if(block != null && (block.blockMaterial == Material.iron || block.blockMaterial == Material.rock))
        {
            return this.efficiencyOnProperMaterial;
        } else
        {
            return super.getStrVsBlock(itemstack, block);
        }
    }
    
    public int getDamageVsEntity(Entity entity)
    {
        return 2 + this.damageVsEntity;
    }
    
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
}
