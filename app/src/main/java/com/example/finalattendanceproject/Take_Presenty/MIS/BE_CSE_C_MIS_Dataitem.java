package com.example.finalattendanceproject.Take_Presenty.MIS;

public class BE_CSE_C_MIS_Dataitem {
    String name;
    String roll_no;
    public BE_CSE_C_MIS_Dataitem()
    {

    }

    public BE_CSE_C_MIS_Dataitem(String name, String roll_no) {
        this.name = name;
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }
}
