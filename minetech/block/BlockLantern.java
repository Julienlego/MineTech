package mods.minetech.block;

import net.minecraft.block.material.Material;

public class BlockLantern extends GenericBlock{
	
	public BlockLantern(int par1){
		 super(par1, Material.iron);
		 float var3 = 0.4F;
	     this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

}
