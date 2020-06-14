package dark_souls_mod.capability;

import dark_souls_mod.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerCapability
{
    /*-------------------------------- estus --------------------------------*/
    private int estus_level = 0; //up to 10

    public int getEstusLevel() {
        return this.estus_level;
    }

    public float getEstusFactor() {
        return (float) this.estus_level / 10.0f;
    }

    /*-------------------------------- capability --------------------------------*/
    public static void register()
    {
        CapabilityManager.INSTANCE.register(PlayerCapability.class, new PlayerCapability.Storage(), PlayerCapability::new);
    }

    public static PlayerCapability getInstance(@Nonnull PlayerEntity player)
    {
        if (player.world.isRemote) throw new IllegalArgumentException("world of Player {" + player + "} is remote");
        return player.getCapability(Capability.PLAYER_MANAGER_CAPABILITY)
                .orElseThrow(() -> new NullPointerException("PlayerCapability of Player {" + player + "} is null"));
    }

    public static class Capability implements
            ICapabilitySerializable<CompoundNBT>
    {
        @CapabilityInject(PlayerCapability.class)
        public static final net.minecraftforge.common.capabilities.Capability<PlayerCapability> PLAYER_MANAGER_CAPABILITY = null;
        private LazyOptional<PlayerCapability> instance = LazyOptional.of(PLAYER_MANAGER_CAPABILITY::getDefaultInstance);


        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> cap, @Nullable Direction side)
        {
            return PLAYER_MANAGER_CAPABILITY.orEmpty(cap, instance);
        }

        @Override
        public CompoundNBT serializeNBT()
        {
            return (CompoundNBT) PLAYER_MANAGER_CAPABILITY.getStorage()
                    .writeNBT(PLAYER_MANAGER_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be null")),
                            null);
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt)
        {
            PLAYER_MANAGER_CAPABILITY.getStorage()
                    .readNBT(PLAYER_MANAGER_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be null")),
                            null,
                            nbt);
        }
    }

    public static class Storage
            implements net.minecraftforge.common.capabilities.Capability.IStorage<PlayerCapability>
    {
        @Nullable
        @Override
        public INBT writeNBT(net.minecraftforge.common.capabilities.Capability<PlayerCapability> capability, PlayerCapability instance, Direction side)
        {
            CompoundNBT compound = new CompoundNBT();
            compound.putInt(Constants.NBT_ESTUS_LVL, instance.estus_level);
            return compound;
        }

        @Override
        public void readNBT(net.minecraftforge.common.capabilities.Capability<PlayerCapability> capability, PlayerCapability instance, Direction side, INBT nbt)
        {
            CompoundNBT compound = (CompoundNBT) nbt;
            instance.estus_level = compound.getInt(Constants.NBT_ESTUS_LVL);
        }
    }
}
