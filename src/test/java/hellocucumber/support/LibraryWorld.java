package hellocucumber.support;

import hellocucumber.contacts.Address;
import hellocucumber.contacts.ZipCode;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.Book;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.Item;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.EmailProviderStub;
import hellocucumber.service.LoanService;
import hellocucumber.service.NotificationService;
import hellocucumber.service.ReturnService;
import io.cucumber.java.an.E;
import io.cucumber.java.mk_latn.No;

import java.util.List;

public class LibraryWorld {
    public BorrowerDAO borrowerDao = new BorrowerDAOMemory();
    public LoanDAO loanDao = new LoanDAOMemory();
    public ItemDAO itemDao = new ItemDAOMemory();
    public NotificationService notificationService = new NotificationService();
    public LoanService loanService = new LoanService();
    public EmailProviderStub emailProvider = new EmailProviderStub();
    public ReturnService returnService = new ReturnService();

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

    public Address createAddress(String city, String street, String number, String zipCode) {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setZipCode(new ZipCode(zipCode));
        return address;
    }
    public Item createItem(String title, int number){
        Book book = new Book();
        book.setTitle(title);
        Item item = new Item();
        item.setBook(book);
        book.addItem(item);
        item.available();
        item.setItemNumber(number);
        itemDao.save(item);
        return item;
    }
}
