package com.hbapi;

import java.util.List;

public class Hero{
	private String images;
	private int heroId;
	private String bio;
	private String weight;
	private List<Equipment> equipment;
	private String descriptions;
	private String realName;
	private String personality;
	private String codeName;
	private String powersAsString;
	private String alignment;
	private List<Powers> powers;
	private String emblem;
	private String height;

	public String getImages(){
		return images;
	}

	public int getHeroId(){
		return heroId;
	}

	public String getBio(){
		return bio;
	}

	public String getWeight(){
		return weight;
	}

	public List<Equipment> getEquipment(){
		return equipment;
	}

	public String getDescriptions(){
		return descriptions;
	}

	public String getRealName(){
		return realName;
	}

	public String getPersonality(){
		return personality;
	}

	public String getCodeName(){
		return codeName;
	}

	public String getAlignment(){
		return alignment;
	}

	public List<Powers> getPowers(){
		return powers;
	}

	public String getEmblem(){
		return emblem;
	}

	public String getHeight(){
		return height;
	}
}