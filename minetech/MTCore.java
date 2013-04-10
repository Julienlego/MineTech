package mods.minetech;

import mods.minetech.block.*;
import mods.minetech.gui.GuiManager;
import mods.minetech.items.*;
import mods.minetech.util.RecipeManager;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Jules_Mod773", name = "minetech", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MTCore {
	
	@Instance("minetech")
	public static MTCore instance;
	@SidedProxy(clientSide = "mods.minetech.ClientProxy", serverSide = "mods.minetech.CommonProxy")
	public static CommonProxy proxy;
	
	/* switch if advanced tools are enabled*/
	private static boolean isAdvanced = false;
	
	//declares items
    public static final Item coke = new ItemCoke(700).setUnlocalizedName("coke").setCreativeTab(CreativeTabs.tabMisc);
    public static final Item copperIngot = new GenericItem(701).setUnlocalizedName("copperIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item aluminiumIngot = new GenericItem(702).setUnlocalizedName("aluminiumIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item graphiteFlakes = new GenericItem(703).setUnlocalizedName("graphiteDust").setCreativeTab(CreativeTabs.tabMisc);
    public static final Item steelIngot = new GenericItem(704).setUnlocalizedName("steelIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item carbonsteelIngot = new GenericItem(705).setUnlocalizedName("carbonSteelIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item titaniumSponge = new GenericItem(706).setUnlocalizedName("titaniumSponge").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item titaniumIngot = new GenericItem(707).setUnlocalizedName("titaniumIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item obsidiantitaniumIngot = new GenericItem(708).setUnlocalizedName("obsidianTitaniumIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item magnesiumDust = new GenericItem(709).setUnlocalizedName("magnesiumDust").setCreativeTab(CreativeTabs.tabMisc);
    public static final Item slimegel = new GenericItem(710).setUnlocalizedName("slimeGel").setCreativeTab(CreativeTabs.tabMisc);
    public static final Item glycerin = new GenericItem(711).setUnlocalizedName("glycerin").setCreativeTab(CreativeTabs.tabMisc);
    public static final Item glycerinNitro = new ItemNitroGlycerin(712).setUnlocalizedName("glycerinNitro").setCreativeTab(CreativeTabs.tabTools);
    public static final Item stickyTorch = new ItemStickyTorch(713).setUnlocalizedName("stickyTorchItem").setCreativeTab(CreativeTabs.tabTools);
    public static final Item obsidianIngot = new GenericItem(714).setUnlocalizedName("obsidianIngot").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item ash = new ItemAsh(715).setUnlocalizedName("ash").setCreativeTab(CreativeTabs.tabMisc);
    
    //declares weapons and tools
    public static final Item swordCopper = new GenericSword(716, MTEnumToolMaterial.Copper).setUnlocalizedName("copperSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeCopper = new GenericPickaxe(717, MTEnumToolMaterial.Copper).setUnlocalizedName("copperPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeCopper = new GenericAxe(718, MTEnumToolMaterial.Copper).setUnlocalizedName("copperAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeCopper = new GenericHoe(719, MTEnumToolMaterial.Copper).setUnlocalizedName("copperHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelCopper = new GenericSpade(720, MTEnumToolMaterial.Copper).setUnlocalizedName("copperSpade").setCreativeTab(CreativeTabs.tabTools);
    public static final Item swordAluminium = new GenericSword(721, MTEnumToolMaterial.Aluminium).setUnlocalizedName("aluminiumSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeAluminium = new GenericPickaxe(722, MTEnumToolMaterial.Aluminium).setUnlocalizedName("aluminiumPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeAluminium = new GenericAxe(723, MTEnumToolMaterial.Aluminium).setUnlocalizedName("aluminiumAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeAluminium = new GenericHoe(724, MTEnumToolMaterial.Aluminium).setUnlocalizedName("aluminiumHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelAluminium = new GenericSpade(725, MTEnumToolMaterial.Aluminium).setUnlocalizedName("aluminiumSpade").setCreativeTab(CreativeTabs.tabTools);
    public static final Item swordSteel = new GenericSword(726, MTEnumToolMaterial.Steel).setUnlocalizedName("steelSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeSteel = new GenericPickaxe(727, MTEnumToolMaterial.Steel).setUnlocalizedName("steelPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeSteel = new GenericAxe(728, MTEnumToolMaterial.Steel).setUnlocalizedName("steelAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeSteel = new GenericHoe(729, MTEnumToolMaterial.Steel).setUnlocalizedName("steelHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelSteel = new GenericSpade(730, MTEnumToolMaterial.Steel).setUnlocalizedName("steelSpade").setCreativeTab(CreativeTabs.tabTools);
    public static final Item swordCarbonSteel = new GenericSword(731, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName("carbonSteelSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeCarbonSteel = new GenericPickaxe(732, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName("carbonSteelPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeCarbonSteel = new GenericAxe(733, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName("carbonSteelAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeCarbonSteel = new GenericHoe(734, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName("carbonSteelHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelCarbonSteel = new GenericSpade(735, MTEnumToolMaterial.CarbonSteel).setUnlocalizedName("carbonSteelSpade").setCreativeTab(CreativeTabs.tabTools);
    public static final Item swordTitanium = new GenericSword(736, MTEnumToolMaterial.Titanium).setUnlocalizedName("titaniumSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeTitanium = new GenericPickaxe(737, MTEnumToolMaterial.Titanium).setUnlocalizedName("titaniumPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeTitanium = new GenericAxe(738, MTEnumToolMaterial.Titanium).setUnlocalizedName("titaniumAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeTitanium = new GenericHoe(739, MTEnumToolMaterial.Titanium).setUnlocalizedName("titaniumHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelTitanium = new GenericSpade(740, MTEnumToolMaterial.Titanium).setUnlocalizedName("titaniumSpade").setCreativeTab(CreativeTabs.tabTools);
    public static final Item swordObsidianTitanium = new GenericSword(741, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName("obsidianTitaniumSword").setCreativeTab(CreativeTabs.tabTools);
    public static final Item pickaxeObsidianTitanium = new GenericPickaxe(742, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName("obsidianTitaniumPickaxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item axeObsidianTitanium = new GenericAxe(743, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName("obsidianTitaniumAxe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item hoeObsidianTitanium = new GenericHoe(744, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName("obsidianTitaniumHoe").setCreativeTab(CreativeTabs.tabTools);
    public static final Item shovelObsidianTitanium = new GenericSpade(745, MTEnumToolMaterial.ObsidianTitanium).setUnlocalizedName("obsidianTitaniumSpade").setCreativeTab(CreativeTabs.tabTools);
    
    //declares blocks
    public static final Block bioCarbonizerOn = new BlockBioCarbonizer(900, true).setHardness(3.5F).setLightValue(0.875F);
    public static final Block bioCarbonizerOff = new BlockBioCarbonizer(901, false).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block bauxiteOre = new BlockBauxite(902).setUnlocalizedName("aluminiumOre").setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep);//aluminium ore
    public static final Block copperOre = new BlockCopper(903).setUnlocalizedName("copperOre").setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep);
    public static final Block titaniumOre = new BlockTitaniumOre(904, 6).setUnlocalizedName("titaniumOre").setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep);
    public static final Block dolomite = new BlockDolomite(905, 3).setUnlocalizedName("magnesiumOre").setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep);//magnesium ore
    public static final Block magnesiumBrick = new BlockMagnesiumBrick(906).setUnlocalizedName("magnesiumBrick").setHardness(0.3F).setResistance(10F).setLightValue(1.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block magnesiumBlock = new BlockMagnesium(907).setUnlocalizedName("magnesiumBlock").setHardness(0.1F).setStepSound(Block.soundClothFootstep).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block lumpgraphite = new BlockLumpGraphite(908).setUnlocalizedName("graphiteOre").setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep);//graphite ore
    public static final Block blastOn = new BlockBlast(909, true).setHardness(3.5F);
    public static final Block blastOff = new BlockBlast(910, false).setHardness(3.5F).setLightValue(0.875F).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block incineratorOn = new BlockIncinerator(911, true).setHardness(3.5F).setLightValue(0.875F);
    public static final Block incineratorOff = new BlockIncinerator(912, false).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block miningTnt = new BlockMiningTNT(913).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block stickyglowBlock = new BlockStickyTorch(914).setUnlocalizedName("stickyTorch").setLightValue(1.0F);
    public static final Block uraninite = new BlockUraniumOre(915).setUnlocalizedName("uraniumOre").setHardness(0.3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabDecorations).setLightValue(0.875F);
    public static final Block redGlass = new BlockStainedGlass(916).setUnlocalizedName("glassRed").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block blueGlass = new BlockStainedGlass(917).setUnlocalizedName("glassBlue").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block yellowGlass = new BlockStainedGlass(918).setUnlocalizedName("glassYellow").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    public static final Block greenGlass = new BlockStainedGlass(919).setUnlocalizedName("glassGreen").setHardness(0.3F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundGlassFootstep);
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event){
    	//proxy.registerRenderers();
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
    
    @Init
    public void loadMod(FMLInitializationEvent initEvent){
    	MTRegistry.registerNames();
		MTRegistry.registerBlocks();
		MTRegistry.registerAllEntities();
		RecipeManager.addAllRecipes();
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiManager());
		GameRegistry.registerWorldGenerator(new MTWorldGenerator());
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
    
    /** Returns true if advanced is enabled */
    public static boolean isAdvanced(){
    	return isAdvanced;
    }
}
