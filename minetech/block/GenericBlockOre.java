package mods.minetech.block;

import net.minecraft.block.material.Material;

public class GenericBlockOre extends GenericBlock{
	
	public GenericBlockOre(int par1){
		super(par1, Material.iron);
	}
	
	public GenericBlockOre(int id, Material material){
		super(id,material);
	}
}
