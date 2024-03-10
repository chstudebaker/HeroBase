package entity;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Powers> powers;


    public void addPower(Powers power) {
        powers.add(power);
        power.setHero(this);
    }

    /**
     * Remove power.
     *
     * @param power the power
     */
    public void removePower(Powers power) {
        powers.remove(power);
        power.setHero(null);
    }

    /**
     * Gets powers.
     *
     * @return the powers
     */
    public List<Powers> getPowers() {
        return powers;
    }

    /**
     * Sets powers.
     *
     * @param powers the powers
     */
    public void setPowers(List<Powers> powers) {
        this.powers = powers;
    }


    // Constructors, getters, and setters

    public Hero() {
    }

    public Hero(String codeName, String realName, String bio, String alignment) {
        this.codeName = codeName;
        this.realName = realName;
        this.bio = bio;
        this.alignment = alignment;
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
    public String getPowersAsString() {
        if (powers != null && !powers.isEmpty()) {
            return powers.stream()
                    .map(Powers::getDescription)
                    .collect(Collectors.joining(", "));
        } else {
            return "No powers available.";
        }
    }


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

}
