import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RecursiveInvert extends Converter {

    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        BufferedImage original = ImageIO.read(new File(inputFileName));
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        invertPixel(original, output, 0, 0, width, height);

        ImageIO.write(output, "PNG", new File(outputFileName));
    }

  
    private void invertPixel(BufferedImage input, BufferedImage output, int x, int y, int width, int height) {
        if (y >= height) return;

        int pixel = input.getRGB(x, y);
        ARGB color = new ARGB(pixel);

        int invertedRed = 255 - color.red;
        int invertedGreen = 255 - color.green;
        int invertedBlue = 255 - color.blue;

        ARGB inverted = new ARGB(color.alpha, invertedRed, invertedGreen, invertedBlue);
        output.setRGB(x, y, inverted.toInt());

  
        if (x + 1 < width) {
            invertPixel(input, output, x + 1, y, width, height);
        } else {
            invertPixel(input, output, 0, y + 1, width, height);
        }
    }
}
