package eibo.project.presentation.uicomponents.images;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClickerIcon extends ImageView {
	
	private ObjectProperty<Image> clickableImageProperty;
	private ObjectProperty<Image> clickedImageProperty;
	
	public ClickerIcon() {	
		
		super();
		
		clickableImageProperty = new SimpleObjectProperty<>();
		clickedImageProperty = new SimpleObjectProperty<>();
		
		this.setPreserveRatio(true);
		
	}
	
	public ObjectProperty<Image> clickableImageProperty() {
		return clickableImageProperty;
	}
	
	public ObjectProperty<Image> clickedImageProperty() {
		return clickedImageProperty;
	}
	
	public void setClicked() {
		setImage(clickedImageProperty.get());
	}
	
	public void setClickable() {
		setImage(clickableImageProperty.get());
	}

}

