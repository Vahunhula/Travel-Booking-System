package Application.Server.CustomerCommands;

import Application.DAOs.CustomerDaoInterface;
import Application.DTOs.Customer;
import Application.Exceptions.DaoException;
import Application.Protocol.Packet;
import Application.Server.Command;

public class InsertCustomerCommand implements Command {
    private CustomerDaoInterface customerDao;

    public InsertCustomerCommand(CustomerDaoInterface customerDao) {
        this.customerDao = customerDao;
    }

    public Packet execute(Object data) {
        Customer customer = (Customer) data;
        try {
            customerDao.insertCustomer(customer);
            return new Packet("Customer inserted.");
        } catch (DaoException e) {
            return new Packet(e, null);
        }
    }
}
