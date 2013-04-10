package mods.minetech.block;
import java.util.Random;

import mods.minetech.entities.EntityMiningTNTPrimed;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMiningTNT extends GenericBlock
{
	@SideOnly(Side.CLIENT)
    private Icon[] textures;
	
    public BlockMiningTNT(int par1)
    {
        super(par1, Material.tnt);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister){
    	textures = new Icon[3];
    	textures[0] = par1IconRegister.func_94245_a("minetech:miningtntTop");
    	textures[1] = par1IconRegister.func_94245_a("minetech:miningtntBot");
    	textures[2] = par1IconRegister.func_94245_a("minetech:miningtntSide");
    }
    
    @Override
    /**
     * Returns texture of given side
     */
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int blockSide){
    	return blockSide == 0 ? textures[1] : (blockSide == 1 ? textures[0] : textures[2]);
    		
    }


    @SideOnly(Side.CLIENT)
    @Override
    /**
     * Returns the Icon to render in the inventory
     */
    public Icon getBlockTextureFromSideAndMetadata(int blockSide, int par2)
    {
    	return blockSide == 0 ? textures[1] : (blockSide == 1 ? textures[0] : textures[2]);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.func_94571_i(par2, par3, par4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.func_94571_i(par2, par3, par4);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4)
    {
    	if(!par1World.isRemote){
	        EntityMiningTNTPrimed entityminingtntprimed = new EntityMiningTNTPrimed(par1World, (float)par2 + 0.5F, (float)par3 + 0.5F, (float)par4 + 0.5F);
	        entityminingtntprimed.fuse = par1World.rand.nextInt(entityminingtntprimed.fuse / 4) + entityminingtntprimed.fuse / 8;
	        par1World.spawnEntityInWorld(entityminingtntprimed);
    	}
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
    	this.func_94391_a(par1World, par2, par3, par4, par5, (EntityLiving)null);
    }
    
    public void func_94391_a(World par1World, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving)
    {
        if (!par1World.isRemote)
        {
            if ((par5 & 1) == 1)
            {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par6EntityLiving);
                par1World.spawnEntityInWorld(entitytntprimed);
                par1World.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
            }
        }
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
        {
            this.func_94391_a(par1World, par2, par3, par4, 1, par5EntityPlayer);
            par1World.func_94571_i(par2, par3, par4);
            return true;
        }
        else
        {
            return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return null;
    }
}
