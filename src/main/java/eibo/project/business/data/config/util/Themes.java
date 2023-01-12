package eibo.project.business.data.config.util;

public enum Themes {
	
	GOOD("good") {
		@Override
		public Themes nextTheme() {
			return EVIL;
		}
	},
	
	EVIL("evil") {
		@Override
		public Themes nextTheme() {
			return MINIMAL;
		}
	},
	
	MINIMAL("minimal") {
		@Override
		public Themes nextTheme() {
			return GOOD;
		}
	};
	
	private final String name;
	
	Themes(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract Themes nextTheme();
	
}
