import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class FlipHorizontal extends Converter {

    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        BufferedImage inputImage = ImageIO.read(new File(inputFileName));

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = inputImage.getRGB(x, y);
                outputImage.setRGB(width - 1 - x, y, pixel);
            }
        }

        ImageIO.write(outputImage, "jpeg", new File(outputFileName));
    }
}
