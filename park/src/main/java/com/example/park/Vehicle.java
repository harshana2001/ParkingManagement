package com.example.park;

//abstraction
public abstract class Vehicle {

    //Encapsulation
    private String ownerName;
    private String vehiNum;
    private String ownerId;

    Vehicle(String o_name, String v_num, String o_id){
        this.ownerName = o_name;
        this.vehiNum = v_num;
        this.ownerId = o_id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehiNum() {
        return vehiNum;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public abstract int getChargeRate();
}

//Inheritence
class Car extends Vehicle{
    private final int chargeRate;

    Car(String o_name, String v_num, String o_id) {
        super(o_name, v_num, o_id);
        this.chargeRate = 300;
    }
    @Override
    public int getChargeRate() {
        return chargeRate;
    }
}

class Bike extends Vehicle{
    private final int chargeRate;

    Bike(String o_name, String v_num, String o_id) {
        super(o_name, v_num, o_id);
        this.chargeRate = 200;
    }
    @Override
    public int getChargeRate() {
        return chargeRate;
    }

}


