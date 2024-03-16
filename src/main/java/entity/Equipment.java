package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipmentId")
    private int equipmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "Images")
    private String images;

    @ManyToOne
    @JoinColumn(name = "heroId")
    private Hero hero;

    // Constructors, getters, and setters
    public Equipment() {
    }

    public Equipment(String name, String description, String images, Hero hero) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.hero = hero;
    }
    public int getHeroID() {
        return hero != null ? hero.getHeroId() : 0;
    }

    public void setHeroID(int heroID) {
        if (hero == null) {
            hero = new Hero();
        }
        hero.setHeroId(heroID);
    }

    // Getters and setters
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
