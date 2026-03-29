import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Blur extends Converter {

    
    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File input = new File(inputFileName);
        BufferedImage original = ImageIO.read(input);

        int width = original.getWidth();
        int height = original.getHeight();

        
        BufferedImage blurred = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int blurLength = 1000; 

        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int redSum = 0, greenSum = 0, blueSum = 0, alphaSum = 0;
                int count = 0;

                for (int i = 0; i < blurLength && (y + i) < height; i++) {
                    int pixel = original.getRGB(x, y + i);
                    ARGB color = new ARGB(pixel);

                    redSum += color.red;
                    greenSum += color.green;
                    blueSum += color.blue;
                    alphaSum += color.alpha;
                    count++;
                }

                ARGB blurredColor = new ARGB(
                    alphaSum / count,
                    redSum / count,
                    greenSum / count,
                    blueSum / count
                );
                blurred.setRGB(x, y, blurredColor.toInt());
            }
        }

        File output = new File(outputFileName);
        ImageIO.write(blurred, "png", output);
    }
}

