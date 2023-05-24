package com.example.project;

public class MSI {

    public String ID;
    public String name;
    public String company;
    public String location;
    public String category;
    public String auxdata; //auxdata
    public int cost;

    public MSI (String ID, String Name, String Company, String Location, String Category, String auxdata, int Cost)
    {
        this.ID = ID;
        this.name = Name;
        this.company = Company;
        this.location = Location;
        this.category = Category;
        this.auxdata = auxdata;
        this.cost = Cost;

    }

}
