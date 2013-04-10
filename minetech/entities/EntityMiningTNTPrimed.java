package mods.minetech.entities;

import mods.minetech.ExplosionMiningTNT;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMiningTNTPrimed extends Entity
{
    /** How long the fuse is */
    public int fuse;

    public EntityMiningTNTPrimed(World par1World)
    {
        super(par1World);
        fuse = 0;
        preventEntitySpawning = true;
        setSize(0.98F, 0.98F);
        yOffset = height / 2.0F;
    }

    public EntityMiningTNTPrimed(World par1World, double par2, double par4, double par6)
    {
        this(par1World);
        setPosition(par2, par4, par6);
        float f = (float)(Math.random() * Math.PI * 2D);
        motionX = -(float)Math.sin(f) * 0.02F;
        motionY = 0.2D;
        motionZ = -(float)Math.cos(f) * 0.02F;
        fuse = 80;
        prevPosX = par2;
        prevPosY = par4;
        prevPosZ = par6;
    }

    protected void entityInit()
    {
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= 0.04D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.98D;
        motionY *= 0.98D;
        motionZ *= 0.98D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
            motionY *= -0.5D;
        }

        if (fuse-- <= 0)
        {
            if (!worldObj.isRemote)
            {
                setDead();
                explode();
            }
            else
            {
                setDead();
            }
        }
        else
        {
            worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        float f = 1F;
        //worldObj.createExplosion(null, posX, posY, posZ, f);
        
        //creates explosion
        ExplosionMiningTNT explosion = new ExplosionMiningTNT(worldObj, null, posX, posY, posZ, f);
        explosion.isFlaming = false;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setByte("Fuse", (byte)fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        fuse = par1NBTTagCompound.getByte("Fuse");
    }

    public float getShadowSize()
    {
        return 0.0F;
    }
}
