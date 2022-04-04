package application;

import java.awt.Color;

import picture.Picture;

public class Filter {
	private Picture picture;

	public Filter(Picture picture) {
		this.picture = picture;
	}

	/**
	 * The input picture should be converted to a grey scale. To achieve a grey
	 * image we need to sum up the red, green and blue value and divide it by 3.
	 * 
	 * @return converted picture
	 */
	public Picture greyScaleFilter() {
		int height = picture.height();
		int width = picture.width();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r = picture.get(i, j).getRed();
				int g = picture.get(i, j).getGreen();
				int b = picture.get(i, j).getBlue();
				int grey = (r + g + b) / 3;
				picture.set(i, j, new Color(grey, grey, grey));
			}

		}


		return picture;
	}

	/**
	 * As we have a range until 255 inclusive getting the inverted image is easy.
	 * Just subtract the color value from 255.
	 * 
	 * @return converted picture
	 */
	public Picture revertColorFilter() {

		int height = picture.height();
		int width = picture.width();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r = 255 - picture.get(i, j).getRed();
				int g = 255 - picture.get(i, j).getGreen();
				int b = 255 - picture.get(i, j).getBlue();
				picture.set(i, j, new Color(r, g, b));
			}

		}

		return picture;
	}

	//@formatter:off
	/**
	 * A sepia filter is a little more difficult to calculate.
	 * to get the
	 * 
	 * sepia red: (current red * 0.393) + (current green * 0.769) + (current blue * 0.189)
	 * sepia green: (current red * 0.349) + (current green * 0.686) + (current blue * 0.168)
	 * sepia blue: (current red * 0.292) + (current green * 0.534) + (current blue * 0.131)
	 * 
	 * the new value is then Math.min(sepia red, 255) and so on.
	 * 
	 * @return converted picture
	 */
	//@formatter:on

	public Picture sepiaFilter() {
		int height = picture.height();
		int width = picture.width();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int sr = (int) ((picture.get(i, j).getRed() * 0.393) + (picture.get(i, j).getGreen() * 0.769) + (picture.get(i, j).getBlue() * 0.189));
				int r = Math.min(sr, 255);
				int sg = (int) ((picture.get(i, j).getRed() * 0.349) + (picture.get(i, j).getGreen() * 0.686) + (picture.get(i, j).getBlue() * 0.168));
				int g = Math.min(sg, 255);
				int sb = (int) ((picture.get(i, j).getRed() * 0.292) + (picture.get(i, j).getGreen() * 0.534) + (picture.get(i, j).getBlue() * 0.131));
				int b = Math.min(sb, 255);
				picture.set(i, j, new Color(r, g, b));
			}

		}

		return picture;
	}
}
