import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class InvertColors extends Converter {
    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File input = new File(inputFileName);
        BufferedImage original = ImageIO.read(input);

        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage inverted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = original.getRGB(x, y);
                ARGB color = new ARGB(pixel);

                
                int invertedRed = 255 - color.red;
                int invertedGreen = 255 - color.green;
                int invertedBlue = 255 - color.blue;

                ARGB invertedColor = new ARGB(color.alpha, invertedRed, invertedGreen, invertedBlue);
                inverted.setRGB(x, y, invertedColor.toInt());
            }
        }

        File output = new File(outputFileName);
        ImageIO.write(inverted, "PNG", output);
    }
}
