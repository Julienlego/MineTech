package mods.minetech;

import mod.minetech.block.BlockBauxite;
import mod.minetech.block.BlockBioCarbonizer;
import mod.minetech.block.BlockBlast;
import mod.minetech.block.BlockCopper;
import mod.minetech.block.BlockDolomite;
import mod.minetech.block.BlockIncinerator;
import mod.minetech.block.BlockLumpGraphite;
import mod.minetech.block.BlockMagnesium;
import mod.minetech.block.BlockMagnesiumBrick;
import mod.minetech.block.BlockMiningTNT;
import mod.minetech.block.BlockStainedGlass;
import mod.minetech.block.BlockStickyTorch;
import mod.minetech.block.BlockTitaniumOre;
import mod.minetech.block.BlockUraniumOre;
import mod.minetech.gui.GuiManager;
import mod.minetech.items.GenericAxe;
import mod.minetech.items.GenericHoe;
import mod.minetech.items.GenericItem;
import mod.minetech.items.GenericPickaxe;
import mod.minetech.items.GenericSpade;
import mod.minetech.items.GenericSword;
import mod.minetech.items.ItemAsh;
import mod.minetech.items.ItemCoke;
import mod.minetech.items.ItemNitroGlycerin;
import mod.minetech.items.ItemStickyTorch;
import mod.minetech.util.RecipeManager;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Mod773", name = "MineTech", version = "2.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MTCore {
	
	@Instance("MineTech")
	public static MTCore instance = new MTCore();
	@SidedProxy(clientSide = "minetech.common.ClientProxy", serverSide = "minetech.common.CommonProxy")
	public static CommonProxy proxy;
	
	/* switch if advanced tools are enabled*/
	private static boolean isAdvanced = false;
	
	//public static Logger logger;
	
	//declares items
    public static final Item coke = new ItemCoke(700).setUnlocalizedName(7,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Coke");
    public static final Item copperIngot = new GenericItem(701).setUnlocalizedName(6,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Copper Ingot");
    public static final Item aluminiumIngot = new GenericItem(702).setUnlocalizedName(7,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Aluminum Ingot");
    public static final Item graphiteFlakes = new GenericItem(703).setUnlocalizedName(8,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Graphite Flakes");
    public static final Item steelIngot = new GenericItem(704).setUnlocalizedName(8,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Steel Ingot");
    public static final Item carbonsteelIngot = new GenericItem(705).setUnlocalizedName(8,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Carbon-Steel Ingot");
    public static final Item titaniumSponge = new GenericItem(706).setUnlocalizedName(10,1).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Titanium Sponge");
    public static final Item titaniumIngot = new GenericItem(707).setUnlocalizedName(9,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Titanium Ingot");
    public static final Item obsidiantitaniumIngot = new GenericItem(708).setUnlocalizedName(111,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Obsidian-Titanium Ingot");
    public static final Item magnesiumDust = new GenericItem(709).setUnlocalizedName(8,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Magnesium Dust");
    public static final Item slimegel = new GenericItem(710).setUnlocalizedName(13,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Slime Gel");
    public static final Item glycerin = new GenericItem(711).setUnlocalizedName(12,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Glycerin");
    public static final Item glycerinNitro = new ItemNitroGlycerin(712).setUnlocalizedName(13,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Nitro-Glycerin");
    public static final Item stickyTorch = new ItemStickyTorch(713).setUnlocalizedName(14,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Sticky Torch");
    public static final Item obsidianIngot = new GenericItem(714).setUnlocalizedName(10,0).setCreativeTab(CreativeTabs.tabMaterials).setItemName("Obsidian Ingot");
    public static final Item ash = new ItemAsh(715).setUnlocalizedName(6,1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Ash");
    
    //declares weapons and tools
    public static final Item swordCopper = new GenericSword(716, MTEnumToolMaterial.Copper).setUnlocalizedName(0,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Copper Sword");
    public static final Item pickaxeCopper = new GenericPickaxe(717, MTEnumToolMaterial.Copper).setUnlocalizedName(0,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Copper Pickaxe");
    public static final Item axeCopper = new GenericAxe(718, MTEnumToolMaterial.Copper).setUnlocalizedName(0, 3).setCreativeTab(CreativeTabs.tabTools).setItemName("Copper Axe");
    public static final Item hoeCopper = new GenericHoe(719, MTEnumToolMaterial.Copper).setUnlocalizedName(0,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Copper Hoe");
    public static final Item shovelCopper = new GenericSpade(720, MTEnumToolMaterial.Copper).setUnlocalizedName(0,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Copper Shovel");
    public static final Item swordAluminium = new GenericSword(721, MTEnumToolMaterial.Aluminium).setUnlocalizedName(1,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Aluminium Sword");
    public static final Item pickaxeAluminium = new GenericPickaxe(722, MTEnumToolMaterial.Aluminium).setUnlocalizedName(1,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Aluminium Pickaxe");
    public static final Item axeAluminium = new GenericAxe(723, MTEnumToolMaterial.Aluminium).setUnlocalizedName(1,3).setCreativeTab(CreativeTabs.tabTools).setItemName("Aluminium Axe");
    public static final Item hoeAluminium = new GenericHoe(724, MTEnumToolMaterial.Aluminium).setUnlocalizedName(1,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Aluminium Hoe");
    public static final Item shovelAluminium = new GenericSpade(725, MTEnumToolMaterial.Aluminium).setUnlocalizedName(1,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Aluminium Shovel");
    public static final Item swordSteel = new GenericSword(726, MTEnumToolMaterial.Steel).setUnlocalizedName(2,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Steel Sword");
    public static final Item pickaxeSteel = new GenericPickaxe(727, MTEnumToolMaterial.Steel).setUnlocalizedName(2,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Sword Pickaxe");
    public static final Item axeSteel = new GenericAxe(728, MTEnumToolMaterial.Steel).setUnlocalizedName(2,3).setCreativeTab(CreativeTabs.tabTools).setItemName("Steel Axe");
    public static final Item hoeSteel = new GenericHoe(729, MTEnumToolMaterial.Steel).setUnlocalizedName(2,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Steel Hoe");
    public static final Item shovelSteel = new GenericSpade(730, MTEnumToolMaterial.Steel).setUnlocalizedName(2,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Steel Shovel");
    public static final Item swordCarbonSteel = new GenericSword(731, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName(3,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Carbon-Steel Sword");
    public static final Item pickaxeCarbonSteel = new GenericPickaxe(732, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName(3,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Carbon-Steel Pickaxe");
    public static final Item axeCarbonSteel = new GenericAxe(733, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName(3,3).setCreativeTab(CreativeTabs.tabTools).setItemName("Carbon-Steel Axe");
    public static final Item hoeCarbonSteel = new GenericHoe(734, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName(3,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Carbon-Steel Hoe");
    public static final Item shovelCarbonSteel = new GenericSpade(735, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName(3,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Carbon-Steel Shovel");
    public static final Item swordTitanium = new GenericSword(736, MTEnumToolMaterial.Titanium).setUnlocalizedName(4,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Titanium Sword");
    public static final Item pickaxeTitanium = new GenericPickaxe(737, MTEnumToolMaterial.Titanium).setUnlocalizedName(4,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Titanium Pickaxe");
    public static final Item axeTitanium = new GenericAxe(738, MTEnumToolMaterial.Titanium).setUnlocalizedName(4,3).setCreativeTab(CreativeTabs.tabTools).setItemName("Titanium Axe");
    public static final Item hoeTitanium = new GenericHoe(739, MTEnumToolMaterial.Titanium).setUnlocalizedName(4,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Titanium Hoe");
    public static final Item shovelTitanium = new GenericSpade(740, MTEnumToolMaterial.Titanium).setUnlocalizedName(4,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Titanium Shovel");
    public static final Item swordObsidianTitanium = new GenericSword(741, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName(5,0).setCreativeTab(CreativeTabs.tabTools).setItemName("Obsidian-Titanium Sword");
    public static final Item pickaxeObsidianTitanium = new GenericPickaxe(742, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName(5,2).setCreativeTab(CreativeTabs.tabTools).setItemName("Obsidian-Titanium Pickaxe");
    public static final Item axeObsidianTitanium = new GenericAxe(743, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName(5,3).setCreativeTab(CreativeTabs.tabTools).setItemName("Obsidian-Titanium Axe");
    public static final Item hoeObsidianTitanium = new GenericHoe(744, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName(5,4).setCreativeTab(CreativeTabs.tabTools).setItemName("Obsidian-Titanium Hoe");
    public static final Item shovelObsidianTitanium = new GenericSpade(745, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName(5,1).setCreativeTab(CreativeTabs.tabTools).setItemName("Obsidian-Titanium Shovel");
    
    //declares blocks
    public static final Block bioCarbonizerOn = new BlockBioCarbonizer(900, true).setHardness(3.5F).setLightValue(0.875F).setBlockName("Bio Carbonizer");
    public static final Block bioCarbonizerOff = new BlockBioCarbonizer(901, false).setUnlocalizedName(0,1).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("Bio Carbonizer");
    public static final Block bauxiteOre = new BlockBauxite(902).setUnlocalizedName(1,0).setHardness(3F).setResistance(5F).setBlockName("Bauxite").setStepSound(Block.soundStoneFootstep);//aluminium ore
    public static final Block copperOre = new BlockCopper(903).setUnlocalizedName(0,0).setHardness(3F).setResistance(5F).setBlockName("Porphyry Copper").setStepSound(Block.soundStoneFootstep);
    public static final Block titaniumOre = new BlockTitaniumOre(904, 6).setUnlocalizedName(4,0).setHardness(3F).setResistance(5F).setBlockName("Titanium Ore").setStepSound(Block.soundStoneFootstep);
    public static final Block dolomite = new BlockDolomite(905, 3).setUnlocalizedName(2,0).setHardness(3F).setResistance(5F).setBlockName("Dolomite").setStepSound(Block.soundStoneFootstep);//magnesium ore
    public static final Block magnesiumBrick = new BlockMagnesiumBrick(906).setUnlocalizedName(9,1).setHardness(0.3F).setResistance(10F).setLightValue(1.0F).setBlockName("Magnesium Brick").setStepSound(Block.soundStoneFootstep);
    public static final Block magnesiumBlock = new BlockMagnesium(907).setUnlocalizedName(8,1).setHardness(0.1F).setStepSound(Block.soundClothFootstep).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("Magnesium Block");
    public static final Block lumpgraphite = new BlockLumpGraphite(908).setUnlocalizedName(3,0).setHardness(3F).setResistance(5F).setBlockName("Lump Graphite").setStepSound(Block.soundStoneFootstep);//graphite ore
    public static final Block blastOn = new BlockBlast(909, true).setHardness(3.5F).setBlockName("Blast");
    public static final Block blastOff = new BlockBlast(910, false).setUnlocalizedName(4,1).setHardness(3.5F).setLightValue(0.875F).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("Blast");
    public static final Block incineratorOn = new BlockIncinerator(911, true).setHardness(3.5F).setLightValue(0.875F).setBlockName("Incinerator");
    public static final Block incineratorOff = new BlockIncinerator(912, false).setUnlocalizedName(8,2).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("Incinerator");
    public static final Block miningTnt = new BlockMiningTNT(913).setUnlocalizedName(1,3).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("Mining TNT");
    public static final Block stickyglowBlock = new BlockStickyTorch(914, 38).setUnlocalizedName(10,1).setBlockName("Sticky Glow Block").setLightValue(1.0F);
    public static final Block uraninite = new BlockUraniumOre(915).setUnlocalizedName(5,0).setHardness(0.3F).setResistance(5F).setBlockName("Uraninite").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabDecorations).setLightValue(0.875F);
    public static final Block redGlass = new BlockStainedGlass(916, 18).setUnlocalizedName(13,1).setBlockName("Red Glass").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block blueGlass = new BlockStainedGlass(917, 16).setUnlocalizedName(11,1).setBlockName("Blue Glass").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block yellowGlass = new BlockStainedGlass(918, 19).setUnlocalizedName(14,1).setBlockName("Yellow Glass").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block greenGlass = new BlockStainedGlass(919, 17).setUnlocalizedName(12,1).setBlockName("Green Glass").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event){
    	proxy.registerRenderers();
    	/* checks for MTCoreAdvanced */
    	try{
    		Class a = Class.forName("minetech.common.MTCoreAdvanced");
    		isAdvanced = false;
    		System.out.println("MineTech Core: Advanced Core found !");
    	}
    	catch (ClassNotFoundException e) {
    	      System.out.println("MineTech Core: Advanced Core not detected, reason: " + e);
    	}
    }
    
    @Mod.Init
    public void loadMod(FMLInitializationEvent initEvent){
    	MTRegistry.registerNames();
		MTRegistry.registerBlocks();
		MTRegistry.registerAllEntities();
		RecipeManager.addAllRecipes();
		NetworkRegistry.instance().registerGuiHandler(GuiManager.class, this.proxy);
		GameRegistry.registerWorldGenerator(new MTWorldGenerator());
    }
    
    /** Returns true if advanced is enabled */
    public static boolean isAdvanced(){
    	return isAdvanced;
    }
}
