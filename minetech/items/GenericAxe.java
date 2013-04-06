package mods.minetech.items;
import mods.minetech.MTEnumToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class GenericAxe extends GenericItemTool{
	
	private static Block blocksEffectiveAgainst[] = {
		Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern
	};
	protected final MTEnumToolMaterial toolMaterial;

    public GenericAxe(int i, MTEnumToolMaterial enumtoolmaterial)
    {
        super(i,enumtoolmaterial);
        toolMaterial = enumtoolmaterial;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
    	return block != null && (block.blockMaterial == Material.wood || block.blockMaterial == Material.plants || block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(itemstack, block);
    }
    
    public int getDamageVsEntity(Entity entity)
    {
        return 2 + this.toolMaterial.getDamageVsEntity();
    }
    
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

}
