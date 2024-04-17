package hellocucumber.StepDefinitions;

import hellocucumber.domain.Borrower;
import hellocucumber.support.LibraryWorld;
import hellocucumber.util.SimpleCalendar;
import io.cucumber.java.ParameterType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomParamTypes {
    private final LibraryWorld world;
    public CustomParamTypes(LibraryWorld world){
        this.world=world;
    }
    @ParameterType("George Red") //custom parameter type for creating a borrower
    public Borrower borrower(String fullName) {  // type, name (from method)
        String[] names = fullName.split(" ");
        if(world.borrowerDao.find(names[0],names[1])!=null){
            return world.borrowerDao.find(names[0],names[1]);
        }
        Borrower borrower = new Borrower();
        borrower.setFirstName(names[0]);
        borrower.setLastName(names[1]);
        world.borrowerDao.save(borrower);
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
