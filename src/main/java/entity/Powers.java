package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Powers")
public class Powers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PowerID")
    private int powerID;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Explanation")
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "HeroID")
    private Hero hero;

    // Constructors, getters, and setters

    public Powers() {
    }

    public Powers(String description, String explanation, Hero hero) {
        this.description = description;
        this.explanation = explanation;
        this.hero = hero;
    }

    public int getPowerID() {
        return powerID;
    }

    public void setPowerID(int powerID) {
        this.powerID = powerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    // Getter and setter for HeroID
    public int getHeroID() {
        return hero != null ? hero.getHeroId() : 0;
    }

    public void setHeroID(int heroID) {
        if (hero == null) {
            hero = new Hero();
        }
        hero.setHeroId(heroID);
    }

    @Override
    public String toString() {
        return "Powers{" +
                "powerID=" + powerID +
                ", description='" + description + '\'' +
                '}';
    }
}
