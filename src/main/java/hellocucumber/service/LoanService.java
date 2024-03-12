package hellocucumber.service;


import hellocucumber.LibraryException;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Item;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;

import java.time.LocalDate;

/**
 * Η υπηρεσία του δανεισμού. Αναλαμβάνει την αναζήτηση
 * δανειζομένων και αντιτύπων και καταγράφει τους δανεισμούς
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanService {
    private Borrower borrower;

    /**
     * Αναζητά το δανειζόμενο με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου
     * @return {@code true} αν βρεθεί ο δανειζόμενος
     */
    public Boolean findBorrower(int borrowerNo) {
        BorrowerDAO borrowerDao = new BorrowerDAOMemory();
        borrower = borrowerDao.find(borrowerNo);
        return borrower != null;
    }

    /**
     * Πραγματοποιεί το δανεισμό.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου
     * @return Την προθεσμία επιστροφής.
     * Επιστρέφει {@code null} εάν ο δανειζόμενος δεν δικαιούται
     * να δανειστεί αντίτυπο.
     * @throws LibraryException Εάν δεν υπάρχει δανειζόμενος
     */
    public LocalDate borrow(int itemNo) {
        if (borrower == null) {
            throw new LibraryException();
        }
        if (!borrower.canBorrow()) {
            return null;
        }
        ItemDAO itemDao = new ItemDAOMemory();
        Item item = itemDao.find(itemNo);
        Loan loan = item.borrow(borrower);
        LoanDAO loanDao = new LoanDAOMemory();
        loanDao.save(loan);
        return loan.getDue();
    }

}

