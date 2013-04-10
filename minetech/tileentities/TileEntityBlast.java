package mods.minetech.tileentities;

import mods.minetech.MTCore;
import mods.minetech.block.BlockBlast;
import mods.minetech.util.BlastRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityBlast extends TileEntityFurnace{
    
    private ItemStack blastItemStacks[];
    public int blastBurnTime;
    public int currentItemBurnTime;
    public int blastCookTime;

    public TileEntityBlast()
    {
        this.blastItemStacks = new ItemStack[4];
        this.blastBurnTime = 0;
        this.currentItemBurnTime = 0;
        this.blastCookTime = 0;
    }

    public int getSizeInventory()
    {
        return this.blastItemStacks.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        return this.blastItemStacks[i];
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(this.blastItemStacks[i] != null)
        {
            if(this.blastItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = this.blastItemStacks[i];
                this.blastItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.blastItemStacks[i].splitStack(j);
            if(this.blastItemStacks[i].stackSize == 0)
            {
                this.blastItemStacks[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        this.blastItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "Blast";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        this.blastItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if(byte0 >= 0 && byte0 < this.blastItemStacks.length)
            {
                this.blastItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.blastBurnTime = nbttagcompound.getShort("BurnTime");
        this.blastCookTime = nbttagcompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.blastItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)this.blastBurnTime);
        nbttagcompound.setShort("CookTime", (short)this.blastCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.blastItemStacks.length; i++)
        {
            if(this.blastItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.blastItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int i)
    {
        return (this.blastCookTime * i) / 200;
    }

    public int getBurnTimeRemainingScaled(int i)
    {
        if(this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }
        return (this.blastBurnTime * i) / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.blastBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = this.blastBurnTime > 0;
        boolean flag1 = false;
        if(this.blastBurnTime > 0)
        {
            this.blastBurnTime--;
        }
        if(!this.worldObj.isRemote)
        {
            if(this.blastBurnTime == 0 && canSmelt())
            {
                this.currentItemBurnTime = this.blastBurnTime = getItemBurnTime(this.blastItemStacks[2]);
                if(this.blastBurnTime > 0)
                {
                    flag1 = true;
                    if(this.blastItemStacks[2] != null)
                    {
                        if(this.blastItemStacks[2].getItem().hasContainerItem())
                        {
                            this.blastItemStacks[2] = new ItemStack(this.blastItemStacks[2].getItem().getContainerItem());
                        } else
                        {
                            this.blastItemStacks[2].stackSize--;
                        }
                        if(this.blastItemStacks[2].stackSize == 0)
                        {
                            this.blastItemStacks[2] = null;
                        }
                    }
                }
            }
            if(isBurning() && canSmelt())
            {
                this.blastCookTime++;
                if(this.blastCookTime == 200)
                {
                    this.blastCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else
            {
                this.blastCookTime = 0;
            }
            if(flag != (this.blastBurnTime > 0))
            {
                flag1 = true;
                BlockBlast.updateBlastBlockState(this.blastBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if(this.blastItemStacks[0]== null || blastItemStacks[1]== null)
        {
			//System.out.println("NO INPUT");
            return false;
        }
        ItemStack itemstack = BlastRecipes.getSmeltingResult(this.blastItemStacks[0].getItem().itemID, this.blastItemStacks[1].getItem().itemID);
        if(itemstack == null)
        {	
			//System.out.println("NO RECIPE");
            return false;
        }
        if(this.blastItemStacks[3] == null)
        {
            return true;
        }
		//System.out.println("NO SPACE");
        if(!this.blastItemStacks[3].isItemEqual(itemstack))
        {
			//System.out.println("WRONG PRODUCT");
            return false;
        }
        if(this.blastItemStacks[3].stackSize < getInventoryStackLimit() && this.blastItemStacks[3].stackSize < this.blastItemStacks[3].getMaxStackSize())
        {
            return true;
        }
        return this.blastItemStacks[3].stackSize < itemstack.getMaxStackSize();
    }

    public void smeltItem()
    {
        if(!canSmelt())
        {
            return;
        }
        ItemStack itemstack = BlastRecipes.getSmeltingResult(this.blastItemStacks[0].getItem().itemID, this.blastItemStacks[1].getItem().itemID);
        if(this.blastItemStacks[3] == null)
        {
            this.blastItemStacks[3] = itemstack.copy();
        }
		else if(this.blastItemStacks[3].itemID == itemstack.itemID)
        {
            this.blastItemStacks[3].stackSize++;
        }
		for(int i = 0; i < 2; i++)
		{
			if(this.blastItemStacks[i].getItem().hasContainerItem())
			{
				this.blastItemStacks[i] = new ItemStack(this.blastItemStacks[i].getItem().getContainerItem());
			}
			else
			{
				this.blastItemStacks[i].stackSize--;
			}
			if(this.blastItemStacks[i].stackSize <= 0)
			{
				this.blastItemStacks[i] = null;
			}
		}
    }

    public static int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().itemID;
        if(i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
        {
            return 300;
        }
        if(i == Item.stick.itemID)
        {
            return 100;
        }
        if(i == Item.coal.itemID)
        {
            return 1600;
        }
        if(i == MTCore.coke.itemID)
        {
            return 6400;
        }
        if(i == Item.bucketLava.itemID)
        {
            return 20000;
        }
        if(i == Block.sapling.blockID)
        {
            return 100;
        }
        if(i == Item.blazeRod.itemID)
        {
            return 2400;
        }
        return i != Block.sapling.blockID ? 0 : 100;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64D;
    }
}
