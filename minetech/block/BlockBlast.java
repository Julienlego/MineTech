package mods.minetech.block;
import java.util.Random;

import mods.minetech.MTCore;
import mods.minetech.container.GenericBlockContainer;
import mods.minetech.tileentities.TileEntityBlast;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlast extends GenericBlockContainer{
    
    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final boolean isActive;
    
    /** True if this is an active furnace, false if idle */
    private Random blastRand;
    
    /**
     * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
     * furnace block changes from idle to active and vice-versa.
     */
    private static boolean keepBlastInventory = false;
    
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockBlast(int i, boolean flag)
    {
        super(i, Material.rock);
        this.blastRand = new Random();
        this.isActive = flag;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister){
    	textures = new Icon[4];
    	textures[0] = par1IconRegister.func_94245_a("minetech:blastTop");
    	textures[1] = par1IconRegister.func_94245_a("minetech:blastOn");
    	textures[2] = par1IconRegister.func_94245_a("minetech:blastOff");
    	textures[3] = par1IconRegister.func_94245_a("minetech:blastSide");
    }

      public int idDropped(int i, Random random, int j){
            return MTCore.blastOff.blockID;
      }
  
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        if(world.isRemote)
        {
            return;
        }
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }
        if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }
        if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }
        if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0, 2);
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * Returns the block texture based on the side being looked at.  Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
    	if (side == 1 || side == 0)
        {
            return textures[0];
        }
    	
        else
        {
            return side != meta ? textures[3] : (this.isActive ? textures[1] : textures[2]);
        }
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(!this.isActive)
        {
            return;
        }
        int l = world.getBlockMetadata(i, j, k);
        float f = (float)i + 0.5F;
        float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float)k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if(l == 4)
        {
            world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 5)
        {
            world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 2)
        {
            world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 3)
        {
            world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int d, float a, float b, float c)
    {
    	if (world.isRemote)
        {
            return true;
        }
    	
    	else{
    		/*
    		TileEntityBlast blast = (TileEntityBlast)world.getBlockTileEntity(i, j, k);
    		if(blast != null){
    			entityplayer.displayGUIFurnace(blast);
    		}
    		*/
    		entityplayer.openGui(MTCore.instance, 1, world, i, j, k);
    		return true;
    	}
    }

    public static void updateBlastBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepBlastInventory = true;
        if(flag){
        	world.func_94575_c(i, j, k, MTCore.blastOn.blockID);
        }
        else{
        	world.func_94575_c(i, j, k, MTCore.blastOff.blockID);
        }
        keepBlastInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l, 2);
        if(tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(i, j, k, tileentity);
        }
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5, 2);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3, 2);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4, 2);
        }
    }
    
    @Override
    public void breakBlock(World world, int i, int j, int k, int x, int y)
    {
        if(!keepBlastInventory)
        {
            TileEntityBlast tileentityblast = (TileEntityBlast)world.getBlockTileEntity(i, j, k);
            if(tileentityblast != null)
            {
                for(int l = 0; l < tileentityblast.getSizeInventory(); l++)
                {
                    ItemStack itemstack = tileentityblast.getStackInSlot(l);
                    if(itemstack == null)
                    {
                        continue;
                    }
                    float f = blastRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = blastRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = blastRand.nextFloat() * 0.8F + 0.1F;
                    while(itemstack.stackSize > 0)
                    {
                        /*if(itemstack.stackSize <= 0)
                        {
                            continue label0;
                        }*/
                        int i1 = blastRand.nextInt(21) + 10;
                        if(i1 > itemstack.stackSize)
                        {
                            i1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound())
                        {
                        	entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        float f3 = 0.05F;
                        entityitem.motionX = (float)blastRand.nextGaussian() * f3;
                        entityitem.motionY = (float)blastRand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)blastRand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    }
                }

            }
        }
        super.breakBlock(world, i, j, k, x, y);
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityBlast();
	}
}
