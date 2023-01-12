package eibo.project.presentation.uicomponents.images;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UpgradeIcon extends ImageView {
	
	private static final String DEFAULT_PATH = "themes/default/default.png";
	private static final double DEFAULT_HEIGHT = 150;
	private static final double DEFAULT_WIDTH = 150;
	
	private Image defaultImage;
	
	private double standardHeight;
	private double standardWidth;

	public UpgradeIcon() {	
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
	}
	
	public UpgradeIcon(String path) {
		this();
		setUpgradeIcon(path);
	}
	
	public UpgradeIcon(double height, double width, String path) {
		this(height, width);
		setUpgradeIcon(path);
	}
	
	public UpgradeIcon(double height, double width) {
		
		super();
		
		this.standardHeight = height;
		this.standardWidth = width;
		
		try (FileInputStream fis = new FileInputStream(DEFAULT_PATH)) {
			defaultImage = new Image(fis, standardWidth, standardHeight, true, true);
			setImage(defaultImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreserveRatio(true);
		
	}
	
	public void setUpgradeIcon(String path) {
		
		try (FileInputStream fis = new FileInputStream(path)) {
			var img = new Image(fis, standardWidth, standardHeight, true, true);
			setImage(img);
		} catch (IOException e) {
			setImage(defaultImage);
		}
		
	}

}