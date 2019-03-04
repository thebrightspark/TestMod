package brightspark.testmod.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler
{
    private static RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
    {
        float f = playerIn.rotationPitch;
        float f1 = playerIn.rotationYaw;
        double d0 = playerIn.posX;
        double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
        double d2 = playerIn.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = 5.0D;
        if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP)
        {
            d3 = ((net.minecraft.entity.player.EntityPlayerMP)playerIn).interactionManager.getBlockReachDistance();
        }
        Vec3d vec3d1 = vec3d.add((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
        return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
    }

    @SubscribeEvent
    public static void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        if(event.getType() != RenderGameOverlayEvent.ElementType.TEXT)
            return;

        //Raytrace for block looking at
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        RayTraceResult ray = rayTrace(world, mc.player, false);
        if(ray == null || ray.typeOfHit != RayTraceResult.Type.BLOCK || world.isAirBlock(ray.getBlockPos()))
            return;

        ScaledResolution res = event.getResolution();
        int xMid = res.getScaledWidth() / 2;
        int yMid = res.getScaledHeight() / 2 + 50;

        String blockName = world.getBlockState(ray.getBlockPos()).getBlock().getLocalizedName();
        FontRenderer fr = mc.fontRenderer;
        int textWidth = fr.getStringWidth(blockName);
        int textHeight = fr.FONT_HEIGHT;
        int minX = xMid - (textWidth / 2);
        int margin = 5;

        //Render rectangle for background
        Gui.drawRect(minX - margin, yMid - margin, xMid + (textWidth / 2) + margin, yMid + textHeight + margin, 0x88000000); //Colour is argb
        //Render name of block on screen
        fr.drawStringWithShadow(blockName, minX, yMid, 0xFFFFFF);
    }

    public static boolean screenshot = false;

    @SubscribeEvent
    public static void takeScreenshot(RenderGameOverlayEvent.Pre event)
    {
        if(!screenshot || event.getType() != RenderGameOverlayEvent.ElementType.VIGNETTE)
            return;
        screenshot = false;

        //Take screenshot
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.sendMessage(ScreenShotHelper.saveScreenshot(mc.gameDir, mc.displayWidth, mc.displayHeight, mc.getFramebuffer()));
    }
}
