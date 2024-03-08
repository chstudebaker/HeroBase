package entity;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Powers> powersList;

    // Constructors, getters, and setters

    public Hero() {
    }

    public Hero(String codeName, String realName, String bio, String alignment, List<Powers> powersList) {
        this.codeName = codeName;
        this.realName = realName;
        this.bio = bio;
        this.alignment = alignment;
        this.powersList = powersList;
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

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public List<Powers> getPowersList() {
        return powersList;
    }

    public void setPowersList(List<Powers> powersList) {
        this.powersList = powersList;
    }
    public String getPowersAsString() {
        if (powersList != null && !powersList.isEmpty()) {
            return powersList.stream()
                    .map(Powers::getDescription)
                    .collect(Collectors.joining(", "));
        }
        return "";
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId=" + heroId +
                ", codeName='" + codeName + '\'' +
                ", realName='" + realName + '\'' +
                ", bio='" + bio + '\'' +
                ", alignment='" + alignment + '\'' +
                ", powersList=" + powersList +
                ", powersAsString='" + getPowersAsString() + '\'' +
                '}';
    }
}
