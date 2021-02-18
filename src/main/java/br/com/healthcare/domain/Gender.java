package br.com.healthcare.domain;

public enum Gender {

	MALE("Masculino"),
	FAMALE("Feminino"),
	OTHERS("Outros");
	
	private final String displayName;

	private Gender(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
