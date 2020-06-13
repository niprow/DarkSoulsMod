package dark_souls_mod.capability.data;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class ExactBlockPos extends BlockPos {
    ResourceLocation dimension_key;
    public ExactBlockPos(int x, int y, int z, ResourceLocation dimension_name) {
        super(x, y, z);
        this.dimension_key = dimension_name;
    }

    public ExactBlockPos(BlockPos pos, ResourceLocation dimension_name) {
        super(pos);
        this.dimension_key = dimension_name;
    }
}