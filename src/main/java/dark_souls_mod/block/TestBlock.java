package dark_souls_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TestBlock extends Block {
    public TestBlock() {
        super(Block.Properties.create(Material.ROCK)
                .lightValue(13)
                .hardnessAndResistance(3.0f, 4.0f)
        );
    }


}
