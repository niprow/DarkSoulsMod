package dark_souls_mod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class Constants {
    /*-------------------------------- constants --------------------------------*/
    public static final String MOD_ID = "dark_souls_mod";

    public static final ResourceLocation BONFIRE = create("bonfire");
    public static final ResourceLocation FULL_ESTUS_FLASK = create("full_estus_flask");
    public static final ResourceLocation EMPTY_ESTUS_FLASK = create("empty_estus_flask");

    public static final ResourceLocation WORLD_CAPABILITY = create("world_capability");
    public static final ResourceLocation PLAYER_CAPABILITY = create("player_capability");

    public static final String NBT_ID = createNBT("id");
    public static final String NBT_NAME = createNBT("name");
    public static final String NBT_DIMENSION_KEY = createNBT("dimension_key");
    public static final String NBT_POS_X = createNBT("pos_x");
    public static final String NBT_POS_Y = createNBT("pos_y");
    public static final String NBT_POS_Z = createNBT("pos_z");
    public static final String NBT_LIT_BONFIRES_SIZE = createNBT("lit_bonfires_size");
    public static final String NBT_LIT_BONFIRES_KEY = createNBT("lit_bonfires_key_");
    public static final String NBT_LIT_BONFIRES_VALUE = createNBT("lit_bonfires_value_");
    public static final String NBT_ESTUS_LVL = createNBT("estus_lvl");

    public static final TranslationTextComponent TLT_CONTAINER_BONFIRE = createTranslation("container", "bonfire");

    /*-------------------------------- automation --------------------------------*/
    private static ResourceLocation create(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
    private static String createNBT(String key) { return MOD_ID + ":" + key; }
    private static ResourceLocation createGUI(String key) { return new ResourceLocation(MOD_ID, "textures/gui/" + key); }
    private static TranslationTextComponent createTranslation(String type, String key) {
        return new TranslationTextComponent(type + "." + MOD_ID + "." + key);
    }
}
