package dev.sterner.mycophagy.api.enums;

public enum MushVariant {
	NORMAL,
	HARDY,
	MYSTIC;


	@Override
	public String toString() {
		return this == NORMAL ? "normal" : this == HARDY ? "hardy" : "mystic";
	}
}
