package mod.minetech.items;
import mod.minetech.MTEnumToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class GenericItemTool extends GenericItem{
	
	private Block blocksEffectiveAgainst[];
    protected float efficiencyOnProperMaterial = 4F;
    protected int damageVsEntity;
    protected MTEnumToolMaterial toolMaterial;

    protected GenericItemTool(int i, MTEnumToolMaterial enumtoolmaterial, Block ablock[])
    {
        super(i);
        this.efficiencyOnProperMaterial = 4F;
        this.toolMaterial = enumtoolmaterial;
        this.blocksEffectiveAgainst = ablock;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        this.efficiencyOnProperMaterial = enumtoolmaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = enumtoolmaterial.getDamageVsEntity();
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    public GenericItemTool(int i, MTEnumToolMaterial enumtoolmaterial){
    	super(i);
    	this.efficiencyOnProperMaterial = 4F;
        this.toolMaterial = enumtoolmaterial;
        this.maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        this.efficiencyOnProperMaterial = enumtoolmaterial.getEfficiencyOnProperMaterial();
	}

	public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        for(int i = 0; i < this.blocksEffectiveAgainst.length; i++)
        {
            if(this.blocksEffectiveAgainst[i] == block)
            {
                return this.efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damageItem(2, entityliving1);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damageItem(1, entityliving);
        return true;
    }

    public int getDamageVsEntity(Entity entity)
    {
        return this.damageVsEntity;
    }

    public boolean isFull3D()
    {
        return true;
    }

    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

}
