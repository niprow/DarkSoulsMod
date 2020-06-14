package dark_souls_mod.inventory.container;

import com.google.common.collect.Lists;
import dark_souls_mod.Constants;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ContainerTypeRegistry {
    /*-------------------------------- constants --------------------------------*/
    public static final List<ContainerType<?>> CONTAINER_TYPES = Lists.newArrayList();
    public static final ContainerType<BonfireContainer> BONFIRE_CONTAINER_TYPE = register(Constants.BONFIRE, new ModdedContainerType<>(BonfireContainer::new));

    /*-------------------------------- automation --------------------------------*/
    public static <T extends Container> ContainerType<T> register(ResourceLocation tag, ContainerType<T> container_type) {
        container_type.setRegistryName(tag);
        CONTAINER_TYPES.add(container_type);
        return container_type;
    }
    public static ContainerType<?>[] getAll() {
        return CONTAINER_TYPES.toArray(new ContainerType<?>[0]);
    }
}
