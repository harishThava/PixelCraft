import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Grayscale implements Converter {

    @Override
    public void convert(String inputFileName, String outputFileName) throws IOException {
        System.out.println("Trying to open file: " + inputFileName);
        File file = new File(inputFileName);
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("Exists: " + file.exists());

        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            System.out.println("ImageIO.read() returned null — unsupported format or bad file.");
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

                int newPixel = (a << 24) | (gray << 16) | (gray << 8) | gray;
                image.setRGB(x, y, newPixel);
            }
        }

        ImageIO.write(image, "png", new File(outputFileName));
        System.out.println("Conversion complete: " + outputFileName);
    }
}
