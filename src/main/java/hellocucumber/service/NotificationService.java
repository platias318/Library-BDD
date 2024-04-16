package hellocucumber.service;

import hellocucumber.LibraryException;
import hellocucumber.contacts.EmailAddress;
import hellocucumber.contacts.EmailMessage;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Book;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.LoanDAOMemory;

import java.util.List;

/**
 * Η υπηρεσία που ενημερώνει με μήνυμα ηλεκτρονικού
 * ταχυδρομείου όσους δανειζομένους
 * έχουν καθυστερήσει την επιστροφή κάποιων
 * αντιτύπων.
 * @author Νίκος Διαμαντίδης
 *
 */
public class NotificationService {
    private EmailProvider provider;

    /**
     * Θέτει τον πάροχο του ηλεκτρονικού ταχυδρομείου.
     * @param provider Ο πάροχος ηλεκτρονικού ταχυδρομείου.
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }

    /**
     * Ενημερώνει όσους δανειζομένους.
     * έχουν καθυστερήσει της επιστροφή.
     * κάποιου αντιτύπου.
     */
    public void notifyBorrowers() {
        if (provider == null) {
            throw new LibraryException();
        }

        LoanDAO loanDao = new LoanDAOMemory();
        List<Loan> allLoans = loanDao.findAllPending();
        for (Loan loan : allLoans) {
            if (loan.isOverdue() && loan.getBorrower().getEmail()!=null &&
            		loan.getBorrower().getEmail().isValid()) {
                String message = composeMessage(loan.getItem().getBook(),
                        -loan.daysToDue());
                sendEmail(loan.getBorrower(),
                        "Καθυστέρηση Αντιτύπου", message);
            }
        }
    }


    private boolean sendEmail(Borrower borrower,
            String subject, String message) {
        EmailAddress eMail = borrower.getEmail();
        if (eMail == null || !eMail.isValid()) {
            return false;
        }
        
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(eMail);
        emailMessage.setSubject(subject);
        emailMessage.setBody(message);
        provider.sendEmail(emailMessage);
        return true;
    }
    
    private String composeMessage(Book book, long overdue) {
        String message = "Έχετε καθυστερήσει το βιβλίο με Τίτλο ";
        message += book.getTitle();
        message += " για ";
        message += overdue;
        message += " ημέρες ";
        return message;
    }
}
