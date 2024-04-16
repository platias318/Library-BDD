package hellocucumber.service;

import java.util.List;

import hellocucumber.LibraryException;
import hellocucumber.dao.Initializer;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.ItemState;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.memorydao.MemoryInitializer;
import org.junit.*;

import com.mgiandia.library.dao.*;


public class LoanServiceTest {

    
    @Before
    public void setUp()  {        
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }


    @Test(expected= LibraryException.class)
    public void noBorrower() {
        LoanService loanService = new LoanService();
        loanService.findBorrower(99999);
        loanService.borrow(Initializer.UML_DISTILLED_ID1);
    }
    
    
    @Test
    public void testBorrow() {
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        
        LoanDAO loanDao = new LoanDAOMemory();
        List<Loan> loanList= loanDao.findAll();
        Loan loan = loanList.get(0);
        
        Assert.assertTrue(loan.isPending());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
        Assert.assertEquals(ItemState.LOANED, loan.getItem().getState());
        
        
    }
    
    @Test
    public void borrowMemoryDataBase() {        
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_REFACTORING_ID));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID2));
        Assert.assertNull(loanService.borrow(Initializer.UML_DISTILLED_ID2));    
    }    

}
