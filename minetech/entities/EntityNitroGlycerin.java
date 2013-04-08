package mod.minetech.entities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityNitroGlycerin extends EntityThrowable {
	double bounceFactor;
	int fuse;
	boolean exploded;
	
	@SideOnly(Side.CLIENT)
	public EntityNitroGlycerin(World world){
		super(world);
		setSize(0.5F, 0.5F);
		yOffset = height / 2.0F;
		bounceFactor = 1.0;
		exploded =false;
		fuse = 0;
		//item = new ItemStack(Item.itemsList[MTCore.glycerinNitro.itemID]);
	}
	
	public EntityNitroGlycerin(World world, Entity entity){
		this(world);
		
		setRotation(entity.rotationYaw, 0);
		//Set the velocity
		double xHeading = -MathHelper.sin((entity.rotationYaw * 3.141593F) / 180F);
		double zHeading = MathHelper.cos((entity.rotationYaw * 3.141593F) / 180F);
		motionX = 0.5 * xHeading * MathHelper.cos((entity.rotationPitch / 180F) * 3.141592F);
		motionY = -0.5 * MathHelper.sin((entity.rotationPitch / 180F) * 3.141592F);
		motionZ = 0.5 * zHeading * MathHelper.cos((entity.rotationPitch / 180F) * 3.141592F);
		
		//Set the position
		setPosition(entity.posX + xHeading * 0.8, entity.posY, entity.posZ + zHeading * 0.8);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		
		fuse = 50;
	}
	
	protected boolean canTriggerWalking(){
		return false;
	}
	
	public void onUpdate(){
		double prevVelX = motionX;
		double prevVelY = motionY;
		double prevVelZ = motionZ;
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		moveEntity(motionX, motionY, motionZ);
		
		//Take into bouncing (normal displacement just sets them to 0)
		if(motionX != prevVelX){
			motionZ = -bounceFactor * prevVelX;
		}
		if(motionY != prevVelY){
			motionY = -bounceFactor * prevVelY;
		}
		else{
			motionY -= 0.04; //Apply gravity
		}
		
		if(motionZ != prevVelZ){
			motionZ = -bounceFactor * prevVelZ;
		}
		
		//Air friction
		motionZ *= 0.99;
		motionY *= 0.99;
		motionZ *= 0.99;
		
		//Are we going to explode?
		if(fuse-- <= 0){
			explode();
		}
	}
	
	private void explode(){
		if(!exploded){
			exploded = true;
			worldObj.createExplosion(null, posX, posY, posZ, 2F, exploded);
		}
	}
	
	public boolean attackEntityFrom(Entity entity, int i)
    {
    	super.attackEntityFrom(DamageSource.generic, i);
    	explode();
    	return false;
    }
 
 
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    	super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setByte("Fuse", (byte)fuse);
    }
 
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    	super.readEntityFromNBT(nbttagcompound);
        fuse = nbttagcompound.getByte("Fuse");
    }
 
    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
    	// Have to override the item's function
    }
	
	protected void onImpact(MovingObjectPosition movingobjectposition){}
}
