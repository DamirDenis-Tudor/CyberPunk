package Components.StaticComponents.Components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapAsset {
    private final BufferedImage image;

    private final int width;

    private final int height;
    public MapAsset(String path , int width , int height) throws IOException {
        this.width= width;
        this.height = height;

        image = ImageIO.read(new File(path));
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage(){
        return image;
    }
}
