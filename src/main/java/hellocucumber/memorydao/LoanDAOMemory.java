package hellocucumber.memorydao;


import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Loan;

import java.util.ArrayList;
import java.util.List;

/**
 * Η υλοποίηση των αντικείμενων πρόσβασης δεδομένων (DAO)
 * για την κλάση {@link Loan} όπου η εξωτερική πηγή δεδομένων
 * είναι η μνήμη.
 * @author ndia
 *
 */
public class LoanDAOMemory implements LoanDAO{

    protected static List<Loan> entities = new ArrayList<Loan>();
    
    public void delete(Loan entity) {
        entities.remove(entity);    
    }

    public List<Loan> findAll() {
        return new ArrayList<Loan>(entities);
    }
    public Loan findLoan(int itemNo){
        for(Loan loan:entities){
            if(loan.getItem().getItemNumber()==itemNo){
                return loan;
            }
        }
        return null;
    }


    public void save(Loan entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    
    public Loan findPending(int itemNo) {
        for(Loan loan : entities) {
            if (loan.getItem().getItemNumber() == itemNo &&
                    loan.isPending()) {
                return loan;
            }
        }
        return null;
    }

    public List<Loan> findAllPending() {
        List<Loan> allLoans = findAll();
        List<Loan> pending = new ArrayList<Loan>();
        
        for(Loan loan : allLoans) {
            if (loan.isPending()) {
                pending.add(loan);
            }
        }
        
        return pending;
    }
    
    
    
}