package mods.minetech.container;
import mods.minetech.slot.SlotBioCarbonizer;
import mods.minetech.tileentities.TileEntityIncinerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerIncinerator extends Container
{

    private TileEntityIncinerator incinerator;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerIncinerator(InventoryPlayer inventoryplayer, TileEntityIncinerator tileentityincinerator)
    {
        this.lastCookTime = 0;
        this.lastBurnTime = 0;
        this.lastItemBurnTime = 0;
        this.incinerator = tileentityincinerator;
        addSlotToContainer(new Slot(tileentityincinerator, 0, 56, 17));
        addSlotToContainer(new Slot(tileentityincinerator, 1, 56, 53));
        addSlotToContainer(new SlotBioCarbonizer(inventoryplayer.player, tileentityincinerator, 2, 116, 35));
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
    
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.incinerator.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.incinerator.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.incinerator.currentItemBurnTime);
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
            if(this.lastCookTime != this.incinerator.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.incinerator.furnaceCookTime);
            }
            if(this.lastBurnTime != this.incinerator.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.incinerator.furnaceBurnTime);
            }
            if(this.lastItemBurnTime != this.incinerator.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.incinerator.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.incinerator.furnaceCookTime;
        this.lastBurnTime = this.incinerator.furnaceBurnTime;
        this.lastItemBurnTime = this.incinerator.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            this.incinerator.furnaceCookTime = j;
        }
        if(i == 1)
        {
            this.incinerator.furnaceBurnTime = j;
        }
        if(i == 2)
        {
            this.incinerator.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.incinerator.isUseableByPlayer(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer player, int i)
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
                slot.onPickupFromSlot(player, itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }
}
