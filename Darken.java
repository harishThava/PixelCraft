import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Darken extends Converter {
    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File input = new File(inputFileName);
        BufferedImage original = ImageIO.read(input);
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB color = new ARGB(original.getRGB(x, y));
                int darkerRed = Math.max(color.red - 40, 0);
                int darkerGreen = Math.max(color.green - 40, 0);
                int darkerBlue = Math.max(color.blue - 40, 0);
                ARGB dark = new ARGB(color.alpha, darkerRed, darkerGreen, darkerBlue);
                output.setRGB(x, y, dark.toInt());
            }
        }

        ImageIO.write(output, "PNG", new File(outputFileName));
    }
}
