package mod.minetech.container;

import mod.minetech.slot.SlotBlast;
import mod.minetech.tileentities.TileEntityBlast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBlast extends Container
{
    
    private TileEntityBlast blast;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    
    public ContainerBlast(InventoryPlayer inventoryplayer, TileEntityBlast tileentityblast)
    {
        this.lastCookTime = 0;
        this.lastBurnTime = 0;
        this.lastItemBurnTime = 0;
        this.blast = tileentityblast;
        addSlotToContainer(new Slot(tileentityblast, 0, 38, 17));
        addSlotToContainer(new Slot(tileentityblast, 1, 74, 17));
        addSlotToContainer(new Slot(tileentityblast, 2, 56, 53));
        addSlotToContainer(new SlotBlast(inventoryplayer.player, tileentityblast, 3, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
            	addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
        	addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }
    
    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            if(this.lastCookTime != this.blast.blastCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.blast.blastCookTime);
            }
            if(this.lastBurnTime != this.blast.blastBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.blast.blastBurnTime);
            }
            if(this.lastItemBurnTime != this.blast.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.blast.currentItemBurnTime);
            }
        }

        this.lastCookTime = blast.blastCookTime;
        this.lastBurnTime = blast.blastBurnTime;
        this.lastItemBurnTime = blast.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            this.blast.blastCookTime = j;
        }
        if(i == 1)
        {
            this.blast.blastBurnTime = j;
        }
        if(i == 2)
        {
            this.blast.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.blast.isUseableByPlayer(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer entityplayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
                if(!mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
            } else
            if(i >= 3 && i < 30)
            {
                if(!mergeItemStack(itemstack1, 30, 39, false))
                {
                    return null;
                }
            } else
            if(i >= 30 && i < 39)
            {
                if(!mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(entityplayer, itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }
}
