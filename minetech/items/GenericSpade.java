package mod.minetech.items;

import mod.minetech.MTEnumToolMaterial;
import net.minecraft.block.Block;

public class GenericSpade extends GenericItemTool{
	
	private static Block blocksEffectiveAgainst[] = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

    public GenericSpade(int i, MTEnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
        this.toolMaterial = enumtoolmaterial;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        this.efficiencyOnProperMaterial = enumtoolmaterial.getEfficiencyOnProperMaterial();
    }

    public boolean canHarvestBlock(Block block)
    {
        if(block == Block.snow)
        {
            return true;
        }
        return block == Block.blockSnow;
    }
}
