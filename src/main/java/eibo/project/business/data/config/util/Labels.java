package eibo.project.business.data.config.util;

public enum Labels {
	
	AMOUNT("amount"),
	BUY("buy"),
	COST("cost"),
	FIRST_SCENE("first_scene"),
	GAIN("gain"),
	MUSIC("music"),
	NOT_ENOUGH("not_enough"),
	NOT_SUCCESSFUL("not_successful"),
	RESET("reset"),
	PER_CLICK("per_click"),
	SECOND_SCENE("second_scene"),
	SUCCESSFUL("successful"),
	THEME_SWITCH("theme_switch"),
	THIRD_SCENE("third_scene"),
	TIME_INTERVAL("time_interval"),
	TURN_OFF("turn_off"),
	TURN_ON("turn_on");

	private final String key;
	
	Labels(String key) {
		this.key = key;
	}
	
	public String key() {
		return key;
	}
	
}
