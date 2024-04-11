package hellocucumber.StepDefinitions;

import hellocucumber.domain.Book;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Item;
import hellocucumber.util.SimpleCalendar;
import io.cucumber.java.ParameterType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomParamTypes {
    @ParameterType("George Red") //custom parameter type for creating a borrower
    public Borrower borrower(String fullName) {  // type, name (from method)
        String[] names = fullName.split(" ");
        Borrower borrower = new Borrower();
        borrower.setFirstName(names[0]);
        borrower.setLastName(names[1]);
        return borrower;
    }
    @ParameterType("\\d{4}-\\d{2}-\\d{2}")
    public SimpleCalendar simpleCalendar(String date) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) { // Check if a match is found
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));

            return new SimpleCalendar(year, month, day);
        } else {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }
}
