package com.example.collegehelper;

import java.util.Date;

public class State {

    private String name; // название
    private String capital;  // столица

    public State(String name, String capital){

        this.name=name;
        this.capital=capital;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
