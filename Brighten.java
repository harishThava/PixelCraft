import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Brighten extends Converter {
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
                int brighterRed = Math.min(color.red + 40, 255);
                int brighterGreen = Math.min(color.green + 40, 255);
                int brighterBlue = Math.min(color.blue + 40, 255);
                ARGB bright = new ARGB(color.alpha, brighterRed, brighterGreen, brighterBlue);
                output.setRGB(x, y, bright.toInt());
            }
        }

        ImageIO.write(output, "PNG", new File(outputFileName));
    }
}
