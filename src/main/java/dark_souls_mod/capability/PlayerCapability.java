package dark_souls_mod.capability;

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
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional can not be null")),
                            null);
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt)
        {
            PLAYER_MANAGER_CAPABILITY.getStorage()
                    .readNBT(PLAYER_MANAGER_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional con no be null")),
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
            return new CompoundNBT();
        }

        @Override
        public void readNBT(net.minecraftforge.common.capabilities.Capability<PlayerCapability> capability, PlayerCapability instance, Direction side, INBT nbt)
        {

        }
    }
}
