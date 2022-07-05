package water.mark;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WaterMarkPicture {
	
	public static void main(String[] args) {
		// the main image to be watermarked
		File sourceImageFile = new File("C:/Users/developer7/Pictures/discover event/Discover event 2.png");
		// watermark image
		File watermarkImageFile = new File("C:/Users/developer7/Pictures/Rolas.jpg");
		// a folder that stores images that have been watermarked
		File destImageFile = new File("C:/Users/developer7/Pictures/watermark/resultWatermarkImage.png");
		
		// call function
		addImageWatermark(watermarkImageFile, sourceImageFile, destImageFile);
	}
	
	/**
	 * Embeds an image watermark over a source image to produce 
	 * a watermarked one. 
	 * @param watermarkImageFile The image file used as the watermark.
	 * @param sourceImageFile The source image file.
	 * @param destImageFile The output image file.
	 */
	static void addImageWatermark(File watermarkImageFile, File sourceImageFile, File destImageFile) {
		try {
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			
			BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);
			
			// resize the watermark image according to the main image size
			Image resizeWatermarkImage = watermarkImage.getScaledInstance(sourceImage.getWidth(), sourceImage.getHeight(), Image.SCALE_AREA_AVERAGING);
			
			// initializes necessary graphic properties
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
			AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
			g2d.setComposite(alphaChannel);
			
			// calculates the coordinate where the image is painted
			int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 20;
			int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 15;
			
			// paints the image watermark, if you want to move it, you can use topLeftX and topLeftY
			g2d.drawImage(resizeWatermarkImage, 0, 0, null);
			
			ImageIO.write(sourceImage, "png", destImageFile);
			g2d.dispose();
			
			System.out.println("The image watermark is added to the image.");
			
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
