import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RecursiveGrayscale extends Converter {

    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        BufferedImage original = ImageIO.read(new File(inputFileName));
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        convertPixel(original, output, 0, 0, width, height);

        ImageIO.write(output, "PNG", new File(outputFileName));
    }

   
    private void convertPixel(BufferedImage input, BufferedImage output, int x, int y, int width, int height) {
        if (y >= height) return; // done

        int pixel = input.getRGB(x, y);
        ARGB color = new ARGB(pixel);
        int avg = (color.red + color.green + color.blue) / 3;
        ARGB gray = new ARGB(color.alpha, avg, avg, avg);
        output.setRGB(x, y, gray.toInt());


        if (x + 1 < width) {
            convertPixel(input, output, x + 1, y, width, height);
        } else {
            convertPixel(input, output, 0, y + 1, width, height);
        }
    }
}
