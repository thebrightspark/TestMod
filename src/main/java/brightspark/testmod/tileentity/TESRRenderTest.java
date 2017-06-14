package brightspark.testmod.tileentity;

import brightspark.testmod.render.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TESRRenderTest extends TileEntitySpecialRenderer<TileRenderTest> implements IRenderLater
{
    @Override
    public void renderLater(TileEntity te, Vec3d renderPos, float partialTicks)
    {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.depthMask(false);
        GlStateManager.disableCull();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buf = tess.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        RenderHelper.renderBeam(buf, renderPos.addVector(0, 1, 0), renderPos.addVector(0, 5, 0), new Color(50, 50, 255), new Color(255, 255, 255), 5);
        tess.draw();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
    }
}
