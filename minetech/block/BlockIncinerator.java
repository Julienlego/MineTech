package mod.minetech.block;

import java.util.Random;

import mod.minetech.MTCore;
import mod.minetech.container.GenericBlockContainer;
import mod.minetech.tileentities.TileEntityBioCarbonizer;
import mod.minetech.tileentities.TileEntityIncinerator;
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

public class BlockIncinerator extends GenericBlockContainer
{
    private final boolean isActive;
    private Random furnaceRand;
    private static boolean keepFurnaceInventory = false;
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockIncinerator(int i, boolean flag)
    {
        super(i, Material.rock);
        this.furnaceRand = new Random();
        this.isActive = flag;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister){
    	textures = new Icon[4];
    }
    
    //This function tells Mine craft what item to drop when you break one of these blocks
    public int idDropped(int i, Random random, int j)
    {
        return MTCore.incineratorOff.blockID;
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
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTextureFromSide(int par1, int par2)
    {
    	if (par1 == 1 || par1 == 0)
        {
            return this.blockIndexInTexture + 3;
        }
        else
        {
            int var6 = iblockaccess.getBlockMetadata(i, j, k);
            return par1 != var6 ? this.blockIndexInTexture : (this.isActive ? this.blockIndexInTexture + 1 : this.blockIndexInTexture);
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

    public boolean onblockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.isRemote)
        {
            return true;
        }
        /*TileEntityIncinerator tileentityincinerator = (TileEntityIncinerator)world.getBlockTileEntity(i, j, k);
        if(tileentityincinerator != null)
        {
            entityplayer.displayGUIFurnace(tileentityincinerator);
        }
        */
        entityplayer.openGui(MTCore.instance, 2, world, i, j, k);
        return true;
    }

    public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepFurnaceInventory = true;
        if(flag)
        {
            world.func_94575_c(i, j, k, MTCore.incineratorOn.blockID);
        } else
        {
            world.func_94575_c(i, j, k, MTCore.incineratorOff.blockID);
        }
        keepFurnaceInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l, 2);
        if(tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityBioCarbonizer();
    }
    
    /**
     * Called when the block is placed in the world.
     */
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
    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int i, int j, int k, int x, int y)
    {
        //commented stuff out to debug method
        //delete commented out code if debug isn't working
        if(!keepFurnaceInventory){
            TileEntityIncinerator tileentityincinerator = (TileEntityIncinerator)world.getBlockTileEntity(i, j, k);
            if(tileentityincinerator != null){
                for(int l = 0; l < tileentityincinerator.getSizeInventory(); l++){
                    ItemStack itemstack = tileentityincinerator.getStackInSlot(l);
                    if(itemstack == null){
                        continue;
                    }
                    float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;//getting nullpointerexception here
                    float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    //do
                    while(itemstack.stackSize > 0){
                        //if(itemstack.stackSize <= 0){
                            //continue label0;
                        //}
                        
                        int i1 = this.furnaceRand.nextInt(21) + 10;
                        
                        if(i1 > itemstack.stackSize){
                            i1 = itemstack.stackSize;
                        }
                        
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound())
                        {
                        	entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        float f3 = 0.05F;
                        entityitem.motionX = (float)this.furnaceRand.nextGaussian() * f3;
                        entityitem.motionY = (float)this.furnaceRand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)this.furnaceRand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    } //while(true);
                }

            }
        }
        super.breakBlock(world, i, j, k, x, y);
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityIncinerator();
	}
}
