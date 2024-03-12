package hellocucumber.memorydao;

import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.Initializer;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Item;
import hellocucumber.domain.Loan;

import java.util.List;

public class MemoryInitializer extends Initializer {

    @Override
    protected void eraseData() {
                
        List<Borrower> allBorrowers = getBorrowerDAO().findAll();
        for(Borrower borrower : allBorrowers) {
            getBorrowerDAO().delete(borrower);
        }
            
        List<Item> allItems = getItemDAO().findAll();        
        for(Item item : allItems) {
            getItemDAO().delete(item);
        }
        
        List<Loan> allLoans = getLoanDAO().findAll(); 
        for(Loan loan : allLoans) {
            getLoanDAO().delete(loan);
        }    
    }

	@Override
	protected BorrowerDAO getBorrowerDAO() {
		return new BorrowerDAOMemory();
	}

	@Override
	protected ItemDAO getItemDAO() {
		return new ItemDAOMemory();
	}

	@Override
	protected LoanDAO getLoanDAO() {
		return new LoanDAOMemory();
	}
    
}
