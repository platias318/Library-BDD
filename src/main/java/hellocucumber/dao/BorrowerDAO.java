package hellocucumber.dao;

import hellocucumber.domain.Borrower;

import java.util.List;

/**
 * Η διεπαφή DAO για την κλάση {@link Borrower}.
 * @author Νίκος Διαμαντίδης
 *
 */
public interface BorrowerDAO {

    /**
     * Η αναζήτηση ενός δανειζομένου με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου.
     * @return Το δανειζόμενο ή {@code null} εάν αυτός δεν βρεθεί.
     */
    Borrower find(int borrowerNo);
    /**
     * Η αναζήτηση ενός δανειζομένου με βάση το όνομα και το επίθετό του.
     * @param firstname το μικρό όνομα του δανειζόμενου
     * @param lastname το επίθετο του δανειζόμενου
     * @return Το δανειζόμενο ή {@code null} εάν αυτός δεν βρεθεί.
     */
    Borrower find(String firstname, String lastname);

    /**
     * Αποθηκεύει ένα αντικείμενο στην εξωτερική πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.66
     * @param entity Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην εξωτερική πηγή δεδομένων.
     */
    boolean save(Borrower entity);
    
    /**
     * Διαγράφει το αντικείμενο από την εξωτερική πηγή δεδομένων.
     * @param entity Το αντικείμενο προς διαγραφή.
     */
    boolean delete(Borrower entity);
    
    /**
     * Επιστρέφει όλα τα αντικείμενα  από την εξωτερική πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    List<Borrower> findAll();

}
