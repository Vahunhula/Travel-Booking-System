package Application.DAOs;

import Application.DTOs.Customer;
import Application.Exceptions.DaoException;

import java.util.List;

public interface CustomerDaoInterface {
//Feature 1 – Find all Entities e.g. findAllPlayers() to return a List of all the entities and display
//that list.
    public List<Customer> findAllCustomers() throws DaoException;

    //Feature 2 – Find and Display (a single) Entity by Key e.g. findPlayerById( id ) – return a single
    //entity and display its contents.
    public Customer findCustomerById(int customerId) throws DaoException;

    //Feature 3 – Delete an Entity by key e.g. deletePlayerById( id ) – remove entity from database boolean
    public boolean deleteCustomerById(int customerId) throws DaoException;

    //Feature 4 – Insert an Entity in the database using DAO (gather data from user, store in DTO
    //object, pass into method insertPlayer ( Player ), return new entity including assigned auto-id.
    public Customer insertCustomer(Customer customer) throws DaoException;
}
