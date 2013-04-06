package mods.minetech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GenericBlock extends Block{
	
	/* location where the texture resides */
	protected String texturePathName = "minetech/common: ";
	
	public GenericBlock(int par1){
		super(par1, Material.iron);
	}
	
	public GenericBlock(int id, Material material){
		super(id,material);
	}
	
	/*public Block setIconCoord(int par1, int par2)
    {
        this.blockIndexInTexture = par1 + par2 * 16;
        return this;
    }*/
	
	/**
	 * Sets the unlocalized name of the block
	 */
	@Override
	public Block setUnlocalizedName(String par1Str)
    {
        this.texturePathName += par1Str;
        return this;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void func_94332_a(IconRegister parIconRegister){
		this.field_94336_cN = parIconRegister.func_94245_a(texturePathName);
	}
}
