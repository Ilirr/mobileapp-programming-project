package com.example.project;

public class MSI {

    public String ID;
    public String Name;
    public String Company;
    public String Location;
    public String Category;
    public String Score; //auxdata
    public int Cost;

    public MSI (String ID, String Name, String Company, String Location, String Category, String Score, int Cost)
    {
        this.ID = ID;
        this.Name = Name;
        this.Company = Company;
        this.Location = Location;
        this.Category = Category;
        this.Score = Score;
        this.Cost = Cost;

    }

}
