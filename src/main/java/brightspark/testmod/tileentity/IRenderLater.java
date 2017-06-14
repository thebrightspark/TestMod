package brightspark.testmod.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;

public interface IRenderLater
{
    void renderLater(TileEntity te, Vec3d renderPos, float partialTicks);
}
