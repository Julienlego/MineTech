package mod.minetech.tileentities;

import mod.minetech.MTCore;
import mod.minetech.block.BlockBioCarbonizer;
import mod.minetech.util.BioCarbonizerRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBioCarbonizer extends TileEntityFurnace implements IInventory{
	

    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    
    
    public TileEntityBioCarbonizer()
    {
        //ItemStack[0] is the mateial being smelted
        //ItemStack[1] is the fuel
        //ItemStack[2] is the result of the smelting process
        this.furnaceItemStacks = new ItemStack[3];
        this.furnaceBurnTime = 0;
        this.currentItemBurnTime = 0;
        this.furnaceCookTime = 0;
    }
    
    @Override
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }
    
    @Override
    public ItemStack getStackInSlot(int i)
    {
        return this.furnaceItemStacks[i];
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        if(this.furnaceItemStacks[i] != null)
        {
            if(this.furnaceItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = this.furnaceItemStacks[i];
                this.furnaceItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.furnaceItemStacks[i].splitStack(j);
            if(this.furnaceItemStacks[i].stackSize == 0)
            {
                this.furnaceItemStacks[i] = null;
            }
            return itemstack1;
        }
            return null;
        }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        this.furnaceItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }
    
    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.furnace";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
        {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if(byte0 >= 0 && byte0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = nbttagcompound.getShort("BurnTime");
        this.furnaceCookTime = nbttagcompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        nbttagcompound.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.furnaceItemStacks.length; i++)
        {
            if(this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int i)
    {
        //'200' must be same as heat generated per operation
        return (this.furnaceCookTime * i) / 200;
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int i)
    {
        if(this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }
        return (this.furnaceBurnTime * i) / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }
    
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        if(this.furnaceBurnTime > 0)
        {
            this.furnaceBurnTime--;
        }
        if(!this.worldObj.isRemote)
        {
            //if furnace is empty of heat and object can be smelted, smelting will occur
            if(this.furnaceBurnTime == 0 && canSmelt())
            {
                //gets burn time for item in inventory of furnace
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
                if(this.furnaceBurnTime > 0)
                {
                    flag1 = true;
                    //if there are items left to be smelted, smelt
                    if(this.furnaceItemStacks[1] != null)
                    {
                        if(this.furnaceItemStacks[1].getItem().hasContainerItem()){
                            this.furnaceItemStacks[1] = new ItemStack(this.furnaceItemStacks[1].getItem().getContainerItem());
                        }
                        else{
                        //decrease stack of item to smelt by 1
                        this.furnaceItemStacks[1].stackSize--;
                        }
                        //if there are no more items to smelt, smelting will stop
                        if(this.furnaceItemStacks[1].stackSize == 0)
                        {
                            this.furnaceItemStacks[1] = null;
                        }
                    }
                }
            }
            if(isBurning() && canSmelt())
            {
                this.furnaceCookTime++;
                //160 is faster
                if(this.furnaceCookTime == 200){
                    this.furnaceCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            }
            else{
                this.furnaceCookTime = 0;
            }
            if(flag != (this.furnaceBurnTime > 0)){
                flag1 = true;
                BlockBioCarbonizer.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
    }
    
    //checks to see if the item in furnace inventory can be smelted into something
    //checks to see if the furnace can smelt the item with no conflicts
    private boolean canSmelt(){ 
        //ItemStack[0] is the material being smelted
        //ItemStack[1] is the fuel being used
        //ItemStack[2] is the result of the smelting process
        
        //no material, no smelting
        if(furnaceItemStacks[0] == null)
        {
            return false;
        }
        ItemStack itemstack = BioCarbonizerRecipes.smelting().getSmeltingResult(furnaceItemStacks[0].getItem().itemID);

        //recipe for material doesn't exist, smelting won't happen
        if(itemstack == null)
        {
            return false;
        }
        //nothing has been smelted, smelting can occur
        if(furnaceItemStacks[2] == null)
        {
            return true;
        }
        //if what's been made doesn't equal what can be made from the material, then smelting won't happen
        if(!furnaceItemStacks[2].isItemEqual(itemstack))
        {
            return false;
        }
        //if the stack size of the result is under the max stack size, then smelting can occur
        if(furnaceItemStacks[2].stackSize < getInventoryStackLimit() && furnaceItemStacks[2].stackSize < furnaceItemStacks[2].getMaxStackSize())
        {
            return true;
        }
        return furnaceItemStacks[2].stackSize < itemstack.getMaxStackSize();
    }
    
    //actually smelts item
    //modify method to give 1/12 probability of success
    public void smeltItem()
    {
        int randint = (int)(12.0 * Math.random()) + 1;//possible numbers 1-12
        if(!canSmelt())
        {
            return;
        }
        ItemStack itemstack = BioCarbonizerRecipes.smelting().getSmeltingResult(furnaceItemStacks[0].getItem().itemID);//itemstack is the result of smelting
        //number that makes the smelt successful is 5
        if(randint == 5){
            if(furnaceItemStacks[2] == null){
                furnaceItemStacks[2] = itemstack.copy();
            }
            //if the result of the smelt is the same as the stack of what's been smelted, then add 1 more to the result stack
            else if(furnaceItemStacks[2].itemID == itemstack.itemID){
                furnaceItemStacks[2].stackSize++;
            }
            if(furnaceItemStacks[0].getItem().hasContainerItem()){
                furnaceItemStacks[0] = new ItemStack(furnaceItemStacks[0].getItem().getContainerItem());
            }
            else{
                furnaceItemStacks[0].stackSize--;//decrease the material stack by 1
            }
            //if there's no more material to smelt, then the stack is empty/null
            if(furnaceItemStacks[0].stackSize <= 0){
                furnaceItemStacks[0] = null;
            }
        }
        //if the smelt isn't successful, decrease the material stack by 1
        else{
            furnaceItemStacks[0].stackSize--;
        }
        //if the materials become negative, the stack becomes nonexistant
        if(furnaceItemStacks[0].stackSize <= 0){
            furnaceItemStacks[0] = null;
        }
    }
    
    //Burn time for items
    public static int getItemBurnTime(ItemStack itemstack){
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
        if(i == Item.blazeRod.itemID){
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
