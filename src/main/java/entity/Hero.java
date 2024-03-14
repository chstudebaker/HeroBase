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

    public void addPower(Powers power) {
        if (powers == null) {
            powers = new ArrayList<>();
        } else {
            // Remove existing powers before adding the new one
            powers.clear();
        }
        powers.add(power);
        power.setHero(this);
    }

    public void removePower(Powers power) {
        if (powers != null) {
            powers.remove(power);
            power.setHero(null);
        }
    }

    public List<Powers> getPowers() {
        return powers;
    }

    public void setPowers(List<Powers> powers) {
        this.powers = powers;
    }

    public Hero() {
        this.powers = new ArrayList<>();
    }

    public Hero(String codeName, String realName, String bio, String alignment, String descriptions, String personality) {
        this.codeName = codeName;
        this.realName = realName != null ? realName : ""; // Set a default value if realName is null
        this.bio = bio;
        this.alignment = alignment;
        this.descriptions = descriptions;
        this.personality = personality;
        this.images = images = images != null ? images : "";;
        this.powers = new ArrayList<>();
    }
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

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getImages() {return images;}

    public void setImages(String images) {this.images = images;}
    @Override
    public String toString() {
        return "Hero{" +
                "id=" + heroId +
                ", codeName='" + codeName + '\'' +
                ", realName='" + realName + '\'' +
                ", bio='" + bio + '\'' +
                ", alignment='" + alignment + '\'' +
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
