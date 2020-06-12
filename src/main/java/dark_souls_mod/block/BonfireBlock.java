package dark_souls_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BonfireBlock extends Block {
    public BonfireBlock() {
        super(Block.Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(4.0f, 4.0f)
                .lightValue(7)
        );
    }
}
