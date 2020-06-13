package dark_souls_mod;

import net.minecraft.util.ResourceLocation;

public class Constants {
    /*-------------------------------- constants --------------------------------*/
    public static final String MOD_ID = "dark_souls_mod";

    public static final ResourceLocation BONFIRE = create("bonfire");
    public static final String NBT_ID = createNBT("id");

    /*-------------------------------- automation --------------------------------*/
    private static ResourceLocation create(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
    private static String createNBT(String key) { return MOD_ID + ":" + key; }
    private static ResourceLocation createGUI(String key) { return new ResourceLocation(MOD_ID, "textures/gui/" + key); }
}
