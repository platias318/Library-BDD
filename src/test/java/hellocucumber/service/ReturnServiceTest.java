package hellocucumber.service;

import java.time.LocalDate;
import java.util.List;



import hellocucumber.LibraryException;
import hellocucumber.dao.Initializer;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.ItemState;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.memorydao.MemoryInitializer;
import hellocucumber.util.Money;
import hellocucumber.util.SystemDateStub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ReturnServiceTest {
    
    @BeforeEach
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }

    @AfterEach
    public void restoreSystemDate() {
        SystemDateStub.reset();
    }
    
    @Test
    public void returnWhenNoLoanExist() {
        ReturnService service = new ReturnService();
        
        Assertions.assertThrows(LibraryException.class, ()->service.returnItem(2));
    }
    
    
    @Test
    public void confirmReturnedItem() {
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();        
        ReturnService service = new ReturnService();
        service.returnItem(Initializer.UML_USER_GUIDE_ID1); 
        LoanDAO loanDao = new LoanDAOMemory();
        
        List<Loan> loanList = loanDao.findAll();
        Loan loan = loanList.get(0);
        Assertions.assertEquals(LocalDate.of(2007,3,1), loan.getLoanDate());
        Assertions.assertEquals(LocalDate.of(2007,3,2), loan.getReturnDate());
        Assertions.assertEquals(Initializer.UML_USER_GUIDE_ID1, loan.getItem().getItemNumber());
        Assertions.assertEquals(ItemState.AVAILABLE, loan.getItem().getState());
    }
    
    
    @Test
    public void returnNoFine(){
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();
        ReturnService service = new ReturnService();
        Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        Assertions.assertNull(fine);
    }
    
    
    @Test
    public void returnWithFine(){
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo30thMarch2007();
        ReturnService service = new ReturnService();
        Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        Assertions.assertNotNull(fine);
    }
    
    
    private void borrowUMLUserGuideToDiamantidis() {
        LoanService service = new LoanService();
        service.findBorrower(2);
        service.borrow(1);
    }
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    

    private void setSystemDateTo2ndMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 2));
    }

    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 30));
    }

}
