package hellocucumber.memorydao;

import hellocucumber.contacts.TelephoneNumber;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.domain.Borrower;

import java.util.ArrayList;
import java.util.List;

public class BorrowerDAOMemory implements BorrowerDAO {


    protected static List<Borrower> entities = new ArrayList<Borrower>();
    
    public void delete(Borrower entity) {
        entities.remove(entity);    
    }

    public List<Borrower> findAll() {
        return new ArrayList<Borrower>(entities);
    }


    public void save(Borrower entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    
    public Borrower find(int borrowerNo) {
        for(Borrower borrower : entities) {
            if (borrower.getBorrowerNo() == borrowerNo ) {
                return borrower;
            }
        }
        return null;
    }

    public Borrower find(String firstname, String lastname){
        for(Borrower borrower : entities) {
            if (borrower.getFirstName()==firstname && borrower.getLastName()==lastname){
                return borrower;
            }
        }
        return null;
    }

}
