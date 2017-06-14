package brightspark.testmod.render;

import brightspark.testmod.tileentity.IRenderLater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

@Mod.EventBusSubscriber(Side.CLIENT)
public class RenderHandler
{
    private static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event)
    {
        if(event.side != Side.CLIENT || event.phase != TickEvent.Phase.START) return;

        ParticleRenderer.updateParticles();
    }

    @SubscribeEvent
    public static void onRenderLast(RenderWorldLastEvent event)
    {
        GlStateManager.pushMatrix();
        ParticleRenderer.renderParticles(event.getPartialTicks());
        GlStateManager.popMatrix();

        List<TileEntity> teList = mc.theWorld.loadedTileEntityList;
        GlStateManager.pushMatrix();
        for(TileEntity te : teList)
        {
            TileEntitySpecialRenderer tesr = TileEntityRendererDispatcher.instance.getSpecialRenderer(te);
            if(tesr instanceof IRenderLater)
            {
                EntityPlayerSP player = mc.thePlayer;
                float partialTicks = event.getPartialTicks();
                Vec3d tePos = new Vec3d(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
                Vec3d pos = new Vec3d(
                        player.lastTickPosX + partialTicks * (player.posX - player.lastTickPosX),
                        player.lastTickPosY + partialTicks * (player.posY - player.lastTickPosY),
                        player.lastTickPosZ + partialTicks * (player.posZ - player.lastTickPosZ)
                );
                tePos.subtract(pos);
                ((IRenderLater) tesr).renderLater(te, tePos, partialTicks);
            }
        }
        GlStateManager.popMatrix();
    }
}
