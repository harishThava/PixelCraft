import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rotate extends Converter {
    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File input = new File(inputFileName);
        BufferedImage original = ImageIO.read(input);
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage rotated = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = original.getRGB(x, y);
                rotated.setRGB(height - 1 - y, x, pixel);
            }
        }

        File output = new File(outputFileName);
        ImageIO.write(rotated, "PNG", output);
    }
}
