package Properties;

import MainEntities.Player;
import Tiles.PropertyTile;

/**
 * Properties can be owned by players. This is an abstract class because there may be more properties other than normal
 * properties such as railroads and utilities. However, they would share some attributes and methods.
 */
public abstract class Property {
    private String name;
    private String abbreviation;
    private PropertyTile propertyTile;
    private Player owner;
    private boolean mortgaged;
    private int price;

    /**
     * Create a new NormalProperty object with these default parameters:
     * owner = Player.OWNERLESS
     * Mortgaged = false
     * propertyTile = null
     *
     * @param name the name of the property
     *
     * @param abbreviation the abbreviation of the name of the property, would be used in command lines
     *
     * @param price the price of the property
     */
    public Property(String name, String abbreviation, int price) {
        this.owner = Player.OWNERLESS;
        this.mortgaged = false;
        this.propertyTile = null;
        this.name = name;
        this.abbreviation = abbreviation;
        this.price = price;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPropertyTile(PropertyTile propertyTile) {
        this.propertyTile = propertyTile;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public Player getOwner() {
        return owner;
    }

    public PropertyTile getPropertyTile() {
        return propertyTile;
    }

    public int getPrice() {
        return price;
    }

    //other getters

    public abstract int getRent(Player target);

    public int getMortgageValue() {
        return this.price / 2;
    }

    public int getLiftMortgagePrice() {
        return (int)(getMortgageValue() * 1.1);
    }
}
