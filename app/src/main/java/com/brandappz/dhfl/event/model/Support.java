package com.brandappz.dhfl.event.model;


public class Support extends ModelBase {

    public String gtumcaFirstName;
    public String gtumcaLastName;
    public String gtumcaBirthdate;
    public String getGtumcaEmail;
    public Support(String gtumcaFirstName, String gtumcaLastName, String gtumcaBirthdate, String getGtumcaEmail)
    {
        this.gtumcaFirstName = gtumcaFirstName;
        this.gtumcaLastName = gtumcaLastName;
        this.gtumcaBirthdate = gtumcaBirthdate;
        this.getGtumcaEmail = getGtumcaEmail;
    }

   /* protected String title1;
    protected String name1;
    protected Number number;
    protected String email;

    public String getTitle() {
        return title1;
    }
    public Number getNumber() {
        return number;
    }
    public String getName1() {
        return name1;
    }
    public String getEmail() {
        return email;
    }
    public void setTitle(){
        this.title1 = title1;
    }
    public void setName1(){
        this.name1=name1;
    }
    public void setNumber(){
        this.number=number;
    }
    public void setEmail(){
        this.email=email;
    }*/
}
