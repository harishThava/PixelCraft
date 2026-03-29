import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Grayscale implements Converter {

    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);

        BufferedImage image = ImageIO.read(inputFile);
        if (image == null) {
            System.out.println("ImageIO.read() returned null — file may be unreadable or in an unsupported format.");
            return;
        }
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;

                int gray = (r + g + b) / 3;
                int newRGB = (a << 24) | (gray << 16) | (gray << 8) | gray;
                image.setRGB(x, y, newRGB);
            }
        }

        ImageIO.write(image, "png", new File(outputFileName));
    }
}
