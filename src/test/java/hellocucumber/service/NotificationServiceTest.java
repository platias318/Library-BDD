package hellocucumber.service;

import java.time.LocalDate;



import hellocucumber.LibraryException;
import hellocucumber.contacts.EmailMessage;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.Initializer;
import hellocucumber.domain.Borrower;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.MemoryInitializer;
import hellocucumber.util.SystemDateStub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationServiceTest {
    
    
    private EmailProviderStub provider;
    
 
    
    @BeforeEach
    public void setUp() {
        provider = new EmailProviderStub();
        
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
     
    }
    
    @AfterEach
    public void tearDown() {
        SystemDateStub.reset();
    }
    
    @Test
    public void serviceWhenNotifierIsNull() {
        NotificationService service = new NotificationService();
        service.setProvider(null);
        
        Assertions.assertThrows(LibraryException.class, ()->service.notifyBorrowers());
        
    }
    
    
    /**
     * Πραγματοποιούμε δύο δανεισμούς. Για τον πρώτο έχει παρέλθει η
     * προθεσμία επιστροφής, ενώ για το δεύτερο όχι.
     * Περιμένουμε την αποστολή μόνο ενός μηνύματος καθυστέρησης
     * για τον πρώτο δανεισμό.
     */
    @Test
    public void sendMessageOnOverdue() {
        // Ρυθμίζουμε την ημερομηνία του συστήματος για
        // την 1η Μαρτίου 2007 και δανείζουμε ένα αντίτυπο

        setSystemDateTo1stMarch2007();            
        borrowUMLUserGuideToDiamantidis();

        // Ρυθμίζουμε την ημερομηνία του συστήματος για
        // την 1η Σεπτεμβρίου 20007 και δανειζουμε ένα αντίτυπο
        
        setSystemDateTo1stSeptember2007();
        borrowRefactoringToGiakoumakis();
        
        // ρυθμίζουμε την ημερομηνία για την 1η Νοεμβρίου
        setSystemDateTo1stNovember2007();                      
        NotificationService service = new NotificationService();        
        service.setProvider(provider);                        
        service.notifyBorrowers();
        
        BorrowerDAO borrowerDao = new BorrowerDAOMemory();
        
        Borrower diamantidis = borrowerDao.find(Initializer.DIAMANTIDIS_ID);
        Assertions.assertEquals(1,provider.allMessages.size());
        EmailMessage message = provider.getAllEmails().get(0);
        Assertions.assertEquals(diamantidis.getEmail() , message.getTo());
    }
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    
    
    private void setSystemDateTo1stNovember2007() {
        SystemDateStub.setStub(LocalDate.of(2007, 11, 1));
    }
    
    
    private void setSystemDateTo1stSeptember2007() {
        SystemDateStub.setStub(LocalDate.of(2007, 9, 1));
    }
    
    private void borrowUMLUserGuideToDiamantidis() {
        LoanService service = new LoanService();
        service.findBorrower(Initializer.DIAMANTIDIS_ID);
        service.borrow(Initializer.UML_USER_GUIDE_ID1);
    }
    
    
    private void borrowRefactoringToGiakoumakis() {
        LoanService service = new LoanService();
        service.findBorrower(Initializer.GIAKOUMAKIS_ID);
        service.borrow(Initializer.UML_REFACTORING_ID);
    }
    
}
