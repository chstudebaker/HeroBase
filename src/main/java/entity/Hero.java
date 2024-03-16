package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heroId")
    private int heroId;

    @Column(name = "codeName", nullable = false)
    private String codeName;

    @Column(name = "realName", nullable = false)
    private String realName;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "Alignment")
    private String alignment;

    @Column(name = "Images")
    private String images;

    @Column(name = "Descriptions")
    private String descriptions;

    @Column(name = "Personality")
    private String personality;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Powers> powers;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipment> equipment;

    public Hero() {
        this.powers = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    public Hero(String codeName, String realName, String bio, String alignment, String images, String descriptions, String personality) {
        this.codeName = codeName;
        this.realName = realName;
        this.bio = bio;
        this.alignment = alignment;
        this.images = images;
        this.descriptions = descriptions;
        this.personality = personality;
        this.powers = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    // Getters and setters for other fields

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public List<Powers> getPowers() {
        return powers;
    }

    public void setPowers(List<Powers> powers) {
        this.powers = powers;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId=" + heroId +
                ", codeName='" + codeName + '\'' +
                ", realName='" + realName + '\'' +
                ", bio='" + bio + '\'' +
                ", alignment='" + alignment + '\'' +
                ", images='" + images + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", personality='" + personality + '\'' +
                ", powers=" + powers +
                ", equipment=" + equipment +
                '}';
    }

    public String getPowersAsString() {
        if (powers != null && !powers.isEmpty()) {
            return powers.stream()
                    .map(Powers::getDescription)
                    .collect(Collectors.joining(", "));
        } else {
            return "No powers available.";
        }
    }
}
