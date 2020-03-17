package brightspark.testmod.handler;

import brightspark.testmod.TestMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, value = Side.CLIENT)
public class KeyHandler {
	public static KeyBinding keyTest = new KeyBinding("key.testmod.test", Keyboard.KEY_H, "key.testmod.category");

	@SubscribeEvent
	public static void onKeyInput(InputEvent.KeyInputEvent event) {
		if (keyTest.isPressed()) {
			TestMod.LOG.info("Key Pressed!");
		}
	}
}
