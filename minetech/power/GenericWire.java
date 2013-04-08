package mod.minetech.power;

import mod.minetech.block.GenericBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class GenericWire extends GenericBlock{
	
	private int volts;
	private int amps;
	
	public GenericWire(int par1){
		super(par1, Material.circuits);
	}
	
	/**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 5;
    }
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
    }

}
