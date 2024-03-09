package entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "hero", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<HeroPower> heroPowers;

    // Constructors, getters, and setters

    public Hero() {
    }

    public Hero(String codeName, String realName, String bio, String alignment, List<HeroPower> heroPowers) {
        this.codeName = codeName;
        this.realName = realName;
        this.bio = bio;
        this.alignment = alignment;
        this.heroPowers = heroPowers;
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

    public List<HeroPower> getHeroPowers() {
        return heroPowers;
    }

    public void setHeroPowers(List<HeroPower> heroPowers) {
        this.heroPowers = heroPowers;
    }

    public String getPowersAsString() {
        StringBuilder powersAsString = new StringBuilder();

        if (heroPowers != null && !heroPowers.isEmpty()) {
            powersAsString.append("Powers: [");

            for (HeroPower heroPower : heroPowers) {
                powersAsString.append(heroPower.getPower().getDescription()).append(", ");
            }

            // Remove the trailing comma and space
            powersAsString.setLength(powersAsString.length() - 2);

            powersAsString.append("]");
        } else {
            powersAsString.append("No powers available.");
        }

        return powersAsString.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hero{")
                .append("heroId=").append(heroId)
                .append(", codeName='").append(codeName).append('\'')
                .append(", realName='").append(realName).append('\'')
                .append(", bio='").append(bio).append('\'')
                .append(", alignment='").append(alignment).append('\'')
                .append(", heroPowers=[");

        if (heroPowers != null) {
            for (HeroPower heroPower : heroPowers) {
                stringBuilder.append(heroPower).append(", ");
            }
            // Remove the trailing comma and space if the list is not empty
            if (!heroPowers.isEmpty()) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }
        }

        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
