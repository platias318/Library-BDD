package hellocucumber;

import hellocucumber.domain.Borrower;
import io.cucumber.java.ParameterType;

public class CustomParamTypes {
    @ParameterType("George Red")
    public Borrower borrower(String firstName, String lastName) {  // type, name (from method)
        Borrower borrower = new Borrower();
        borrower.setFirstName(firstName);
        borrower.setLastName(lastName);
        return borrower;
    }
}
