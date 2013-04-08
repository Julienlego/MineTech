package mod.minetech.container;
import mod.minetech.slot.SlotBioCarbonizer;
import mod.minetech.tileentities.TileEntityBioCarbonizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBioCarbonizer extends Container
{

    private TileEntityBioCarbonizer biocarbonizer;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerBioCarbonizer(InventoryPlayer inventoryplayer, TileEntityBioCarbonizer tileentitybiocarbonizer)
    {
        this.biocarbonizer = tileentitybiocarbonizer;
        this.addSlotToContainer(new Slot(tileentitybiocarbonizer, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileentitybiocarbonizer, 1, 56, 53));
        this.addSlotToContainer(new SlotBioCarbonizer(inventoryplayer.player, tileentitybiocarbonizer, 2, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
            	this.addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
        	this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.biocarbonizer.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.biocarbonizer.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.biocarbonizer.currentItemBurnTime);
    }
    
    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if(this.lastCookTime != this.biocarbonizer.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.biocarbonizer.furnaceCookTime);
            }
            if(this.lastBurnTime != this.biocarbonizer.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.biocarbonizer.furnaceBurnTime);
            }
            if(this.lastItemBurnTime != this.biocarbonizer.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.biocarbonizer.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.biocarbonizer.furnaceCookTime;
        this.lastBurnTime = this.biocarbonizer.furnaceBurnTime;
        this.lastItemBurnTime = this.biocarbonizer.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            this.biocarbonizer.furnaceCookTime = j;
        }
        if(i == 1)
        {
            this.biocarbonizer.furnaceBurnTime = j;
        }
        if(i == 2)
        {
            this.biocarbonizer.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.biocarbonizer.isUseableByPlayer(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer entityplayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
                if(!this.mergeItemStack(itemstack1, 3, 39, true))
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
            if(!this.mergeItemStack(itemstack1, 3, 39, false))
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
