package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

/**
 * The Ability class represents an ability possessed by a Hero.
 */
@Entity
@Table(name = "ability")
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ability_id")
    private Long abilityId;

    @Column(name = "ability_name")
    private String abilityName;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    /**
     * Default constructor for the ability class.
     */
    public Ability() {
    }

    /**
     * Main Constructor for the Ability class.
     *
     * @param abilityId    The ability's ID.
     * @param abilityName  The ability's name.
     * @param hero         The hero associated with the ability.
     */
    public Ability(Long abilityId, String abilityName, Hero hero) {
        this.abilityId = abilityId;
        this.abilityName = abilityName;
        this.hero = hero;
    }

    // Getters and setters for all fields...

    public Long getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Long abilityId) {
        this.abilityId = abilityId;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
