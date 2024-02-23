package org.informationblitz;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DogDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("grooming")
    private String grooming;

    @JsonProperty("image_link")
    private String imageLink;

    @JsonProperty("good_with_children")
    private int goodWithChildren;

    @JsonProperty("good_with_other_dogs")
    private int goodWithOtherDogs;

    @JsonProperty("shedding")
    private int shedding;

    @JsonProperty("energy")
    private int energy;

    @JsonProperty("trainability")
    private int trainability;

    @JsonProperty("min_life_expectancy")
    private int minLifeExpectancy;

    @JsonProperty("max_life_expectancy")
    private int maxLifeExpectancy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrooming() {
        return grooming;
    }

    public void setGrooming(String grooming) {
        this.grooming = grooming;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getGoodWithChildren() {
        return goodWithChildren;
    }

    public void setGoodWithChildren(int goodWithChildren) {
        this.goodWithChildren = goodWithChildren;
    }

    public int getGoodWithOtherDogs() {
        return goodWithOtherDogs;
    }

    public void setGoodWithOtherDogs(int goodWithOtherDogs) {
        this.goodWithOtherDogs = goodWithOtherDogs;
    }

    public int getShedding() {
        return shedding;
    }

    public void setShedding(int shedding) {
        this.shedding = shedding;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getTrainability() {
        return trainability;
    }

    public void setTrainability(int trainability) {
        this.trainability = trainability;
    }

    public int getMinLifeExpectancy() {
        return minLifeExpectancy;
    }

    public void setMinLifeExpectancy(int minLifeExpectancy) {
        this.minLifeExpectancy = minLifeExpectancy;
    }

    public int getMaxLifeExpectancy() {
        return maxLifeExpectancy;
    }

    public void setMaxLifeExpectancy(int maxLifeExpectancy) {
        this.maxLifeExpectancy = maxLifeExpectancy;
    }
}
