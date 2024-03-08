package entity;

import javax.persistence.*;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codeName")
    private String codeName;

    @Column(name = "powers")
    private String powers;

    @Column(name = "bio")
    private String bio;

    @Column(name = "alignment")
    private String alignment;

    @Column(name = "realName")
    private String realName;

    // Constructors
    public Hero() {
        // Default constructor
    }

    public Hero(String codeName, String powers, String bio, String alignment, String realName) {
        this.codeName = codeName;
        this.powers = powers;
        this.bio = bio;
        this.alignment = alignment;
        this.realName = realName;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    // toString method
    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", powers='" + powers + '\'' +
                ", bio='" + bio + '\'' +
                ", alignment='" + alignment + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
