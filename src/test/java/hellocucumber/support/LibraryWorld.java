package hellocucumber.support;

import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Item;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;

import java.util.List;

public class LibraryWorld {
    public BorrowerDAO borrowerDao = new BorrowerDAOMemory();
    public LoanDAO loanDao = new LoanDAOMemory();
    public ItemDAO itemDao = new ItemDAOMemory();

    public void clearBorrowers(){
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for (Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    public void clearLoans(){
        List<Loan> allLoans = loanDao.findAll();
        for(Loan loan : allLoans) {
            loanDao.delete(loan);
        }
    }
    public void clearItems(){
        List<Item> allItems = itemDao.findAll();
        for(Item item : allItems) {
            itemDao.delete(item);
        }
    }

    //methodous na dimiourgei biblio kai items kai borrower
}
