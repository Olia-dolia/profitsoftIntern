package FirstModule.SecondTask.ViolationOfTrafficRules;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Violation {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateTime;
    private String firstName;
    private String lastName;
    private String typeOfVio;
    private Double amount;

    public Violation(String dateTime, String firstName, String lastName, String typeOfVio, Double amount) {
        this.dateTime = dateTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfVio = typeOfVio;
        this.amount = amount;
    }

    public Violation() {

    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeOfVio() {
        return typeOfVio;
    }

    public void setTypeOfVio(String typeOfVio) {
        this.typeOfVio = typeOfVio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString(){
        return "First Name: " + firstName + "\n"+
                 "Last Name: " + lastName + "\n"+
                 "Date & Time: " + dateTime + "\n"+
                 "Type of Vio: " + typeOfVio + "\n"+
                 "Amount: " + amount + "\n";
    }
}
