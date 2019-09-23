package com.piotrek.apps.orderHaircutApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salon_rating")
public class SalonRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "opinion")
    private String opinion;

    //klient
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_TO_SALON_RATING_FK"))
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "salon_id", foreignKey = @ForeignKey(name = "HAIR_SALON_TO_SALON_RATING_FK"))
    @JsonIgnore
    private HairSalon hairSalon;


    public SalonRating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HairSalon getHairSalon() {
        return hairSalon;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }

    @Override
    public String toString() {
        return "SalonRating{" +
                "id=" + id +
                ", rating=" + rating +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}
