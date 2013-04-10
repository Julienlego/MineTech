package mods.minetech;

public enum MTEnumToolMaterial {
	Copper("COPPER", 5, 1, 140, 5.0F, 1, 12),
    Aluminium("ALUMINIUM", 6, 1, 41, 3.0F, 1, 20),
    Titanium("TITANIUM", 7, 3, 1290, 10F, 5, 10),
    Steel("STEEL", 8, 3, 410, 7F, 3, 15),
    CarbonSteel("CARBONSTEEL", 9, 3, 960, 8F, 4, 14),
    ObsidianTitanium("OBSIDIANTITANIUM", 10, 3, 2123, 15F, 7, 4);
    
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private final int enchantability;
    private static final MTEnumToolMaterial[] allToolMaterials = {
        Copper, Steel, Aluminium, CarbonSteel, ObsidianTitanium
    };
    
    private MTEnumToolMaterial(String s, int i, int j, int k, float f, int l, int i1)
{
//    super(s, i);
    this.harvestLevel = j;
    this.maxUses = k;
    this.efficiencyOnProperMaterial = f;
    this.damageVsEntity = l;
    this.enchantability = i1;
}

public int getMaxUses()
{
    return this.maxUses;
}

public float getEfficiencyOnProperMaterial()
{
    return this.efficiencyOnProperMaterial;
}

public int getDamageVsEntity()
{
    return this.damageVsEntity;
}

public int getHarvestLevel()
{
    return this.harvestLevel;
}

public int getEnchantability()
{
    return this.enchantability;
}
}
