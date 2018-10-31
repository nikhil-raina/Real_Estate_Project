import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class demo {
    private static final int IMAGE_SIZE = 400;
    private static final int BLUR_SIZE = 50;

    private static BufferedImage blur(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight() - BLUR_SIZE + 1,
                BufferedImage.TYPE_INT_RGB);
        for (int b = 0; b < image.getRaster().getNumBands(); ++b) {
            for (int x = 0; x < result.getWidth(); ++x) {
                for (int y = 0; y < result.getHeight(); ++y) {
                    int sum = 0;
                    for (int y1 = 0; y1 < BLUR_SIZE; ++y1) {
                        sum += image.getRaster().getSample(x, y + y1, b);
                    }
                    result.getRaster().setSample(x, y, b, sum / BLUR_SIZE);
                }
            }
        }
        return result;
    }

    private static long calcContrast(Raster raster, int y0, int y1) {
        long result = 0;
        int xs = 8;
        for (int b = 0; b < raster.getNumBands(); ++b) {
            for (int y = y0; y < y1; ++y) {
                long prev = raster.getSample(0, y, b);
                for (int x = 1; x < raster.getWidth(); ++x) {
                    long current = raster.getSample(x, y, b);
                    result += Math.abs(current - prev) > 5 ? 1 : 0;
                    prev = current;
                }
            }
        }
        return result;
    }

    private static boolean isUp(File file) throws IOException {
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(ImageIO.read(file), 0, 0, image.getWidth(), image.getHeight(), null);
        graphics.dispose();
        image = blur(image);
        int halfHeight = image.getHeight() / 2;
        return calcContrast(image.getRaster(), 0, halfHeight) < calcContrast(image.getRaster(),
                image.getHeight() - halfHeight, image.getHeight());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(isUp(new File(args[0])) ? "Upgoat" : "Downgoat");
    }
}