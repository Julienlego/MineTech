package mods.minetech.items;

import mods.minetech.MTEnumToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GenericSword extends GenericItemTool{
	
	private int weaponDamage;
    protected final MTEnumToolMaterial toolMaterial;

    public GenericSword(int i, MTEnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
        toolMaterial = enumtoolmaterial;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        this.weaponDamage = 4 + enumtoolmaterial.getDamageVsEntity();
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return block.blockID != Block.web.blockID ? 1.5F : 15F;
    }

    public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damageItem(1, entityliving1);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damageItem(2, entityliving);
        return true;
    }

    public int getDamageVsEntity(Entity entity)
    {
        return this.weaponDamage;
    }

    public boolean isFull3D()
    {
        return true;
    }

    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.block;
    }

    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public boolean canHarvestBlock(Block block)
    {
        return block.blockID == Block.web.blockID;
    }

    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
    
    public String func_77825_f()
    {
        return this.toolMaterial.toString();
    }
}
