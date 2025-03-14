/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import dal.UserDAO;
import model.User;
import org.apache.catalina.connector.Response;
/**
 *
 * @author Huy
 */
public class AccountService {
    UserDAO userDAO;
    public AccountService() {
        userDAO = new UserDAO();
    }
    
    
}
