package Application.Server.CustomerCommands;

import Application.DAOs.CustomerDaoInterface;
import Application.Exceptions.DaoException;
import Application.Protocol.Packet;
import com.google.gson.Gson;
import Application.Server.Command;


public class DeleteCustomerByNumberCommand implements Command{
    private CustomerDaoInterface customerDao;

    public DeleteCustomerByNumberCommand(CustomerDaoInterface customerDao) {
        this.customerDao = customerDao;
    }

    public Packet execute(Object data) {
        String customerNumber = (String) data;
        try {
            boolean deleted = customerDao.deleteCustomerByNumber(customerNumber);
            Gson gson = new Gson();
            return new Packet(gson.toJson(deleted));
        } catch (DaoException e) {
            return new Packet(e, null);
        }
    }
}
