package brightspark.testmod.render;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class RenderHelper
{
    public static void renderBeam(VertexBuffer buf, Vec3d pos1, Vec3d pos2, Color colour1, Color colour2, double width)
    {
        float yaw = (float) Math.atan2(pos2.x - pos1.x, pos2.z - pos1.z);
        float pitch = (float) Math.atan2(pos2.y - pos1.y, Math.sqrt(Math.pow(pos2.x - pos1.x, 2) + Math.pow(pos2.z - pos1.z, 2)));

        double tX1 = width * MathHelper.cos(yaw);
        double tY1 = 0;
        double tZ1 = -width*(double) MathHelper.sin(yaw);

        double tX2 = width*(double)MathHelper.sin(yaw)*-(double)MathHelper.sin(pitch);
        double tY2 = width*(double)MathHelper.cos(pitch);
        double tZ2 = width*(double)MathHelper.cos(yaw)*-(double)MathHelper.sin(pitch);

        buf.pos(pos1.x-tX1,pos1.y-tY1,pos1.z-tZ1).tex(0.0, 0.0).color(colour1.getRed(), colour1.getGreen(), colour1.getBlue(), colour1.getAlpha()).endVertex();
        buf.pos(pos2.x-tX1,pos2.y-tY1,pos2.z-tZ1).tex(1.0, 0.0).color(colour2.getRed(), colour2.getGreen(), colour2.getBlue(), colour2.getAlpha()).endVertex();
        buf.pos(pos2.x+tX1,pos2.y+tY1,pos2.z+tZ1).tex(1.0, 1.0).color(colour2.getRed(), colour2.getGreen(), colour2.getBlue(), colour2.getAlpha()).endVertex();
        buf.pos(pos1.x+tX1,pos1.y+tY1,pos1.z+tZ1).tex(0.0, 1.0).color(colour1.getRed(), colour1.getGreen(), colour1.getBlue(), colour1.getAlpha()).endVertex();

        buf.pos(pos1.x-tX2,pos1.y-tY2,pos1.z-tZ2).tex(0.0, 0.0).color(colour1.getRed(), colour1.getGreen(), colour1.getBlue(), colour1.getAlpha()).endVertex();
        buf.pos(pos2.x-tX2,pos2.y-tY2,pos2.z-tZ2).tex(1.0, 0.0).color(colour2.getRed(), colour2.getGreen(), colour2.getBlue(), colour2.getAlpha()).endVertex();
        buf.pos(pos2.x+tX2,pos2.y+tY2,pos2.z+tZ2).tex(1.0, 1.0).color(colour2.getRed(), colour2.getGreen(), colour2.getBlue(), colour2.getAlpha()).endVertex();
        buf.pos(pos1.x+tX2,pos1.y+tY2,pos1.z+tZ2).tex(0.0, 1.0).color(colour1.getRed(), colour1.getGreen(), colour1.getBlue(), colour1.getAlpha()).endVertex();
    }
}
