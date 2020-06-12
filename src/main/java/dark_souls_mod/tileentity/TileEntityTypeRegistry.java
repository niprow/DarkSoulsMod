package dark_souls_mod.tileentity;

import com.google.common.collect.Lists;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class TileEntityTypeRegistry {
    /*-------------------------------- constants --------------------------------*/

    /*-------------------------------- automation --------------------------------*/
    public static final List<TileEntityType<?>> TILE_ENTITY_TYPES = Lists.newArrayList();
    public static <T extends TileEntity> TileEntityType<T> register(ResourceLocation tag, TileEntityType<?> tile_entity_type) {
        tile_entity_type.setRegistryName(tag);
        TILE_ENTITY_TYPES.add(tile_entity_type);
        return (TileEntityType<T>) tile_entity_type;
    }
    public static TileEntityType<?>[] getAll() {
        return TILE_ENTITY_TYPES.toArray(new TileEntityType[0]);
    }
}
