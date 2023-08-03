package menu;


import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuItemService {

    private MenuItemService menuItemService = null;

    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    Connection con = dbConnection.getConnection();


    private static class LazyHolder{
        static final MenuItemService INSTANCE = new MenuItemService();

    }

    private MenuItemService(){

    }
    public static MenuItemService getInstance(){
        return LazyHolder.INSTANCE;
    }

    public MenuItemService getMenuItemService() {
        return menuItemService;
    }


    public List<MenuItem> findAll() {
        List<MenuItem> menuItems = Collections.emptyList();
        String sql = "SELECT * FROM MENU_ITEMS";

        try(
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
        ){

            menuItems= new ArrayList<>();
            while(rset.next()){
                MenuItem menuItem = new MenuItem();
                menuItem.setName(rset.getString("name"));
                menuItem.setDescription(rset.getString("description"));
                menuItem.setPrice(rset.getLong("price"));
                menuItem.setAvailability(rset.getBoolean("availability"));
                menuItem.setCategory(rset.getString("mid"));
                menuItems.add(menuItem);
            }

        }
        catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return menuItems;
    }

    public Optional<MenuItem> findById(long mid) {

        Optional<MenuItem> menuItem = Optional.empty();
        String sql = "SELECT ID, NAME, DESCRIPTION, PRICE, AVAILABILITY, MID FROM Menu_Items WHERE ID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql);
        ){
            prepStmt.setLong(1, mid);

            try(ResultSet rset = prepStmt.executeQuery()){
                MenuItem resMenuItem = new MenuItem();

                if(rset.next()){
                    resMenuItem.setId(rset.getLong("ID"));
                    resMenuItem.setName(rset.getString("Name"));
                    resMenuItem.setDescription(rset.getString("description"));
                    resMenuItem.setPrice(rset.getLong("price"));
                    resMenuItem.setAvailability(rset.getBoolean("availability"));
                    resMenuItem.setCategory(rset.getString("mid"));
                }
                menuItem = Optional.of(resMenuItem);
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return menuItem;
    }


    public List<MenuItem> findByMId(long mid) {

        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT ID, NAME, DESCRIPTION, PRICE, AVAILABILITY, MID FROM Menu_Items WHERE MID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql);
        ){
            prepStmt.setLong(1, mid);

            try(ResultSet rset = prepStmt.executeQuery()){

                while (rset.next()){
                    MenuItem newMenuItem = new MenuItem();
                    newMenuItem.setId(rset.getLong("ID"));
                    newMenuItem.setName(rset.getString("Name"));
                    newMenuItem.setDescription(rset.getString("description"));
                    newMenuItem.setPrice(rset.getLong("price"));
                    newMenuItem.setAvailability(rset.getBoolean("availability"));
                    newMenuItem.setCategory(rset.getString("mid"));
                    menuItems.add(newMenuItem);
                }
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return menuItems;
    }

    public MenuItem add(MenuItem menuItem) {
        String sql = "INSERT INTO MENU_ITEMS (NAME, DESCRIPTION, PRICE, AVAILABILITY, MID) VALUE (?, ?, ?, ?, ?)";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setString(1, menuItem.getName());
            prepStmt.setString(2, menuItem.getDescription());
            prepStmt.setDouble(3, menuItem.getPrice());
            prepStmt.setBoolean(4, menuItem.getAvailability());
            prepStmt.setString(5, menuItem.getCategory());
            prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return menuItem;
    }

    public MenuItem update(MenuItem menuItem){
        String sql = "UPDATE MENU_ITEMS SET NAME = ?, DESCRIPTION = ?, PRICE = ?, AVAILABILITY = ?, MID =? WHERE ID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setString(1, menuItem.getName());
            prepStmt.setString(2, menuItem.getDescription());
            prepStmt.setDouble(3, menuItem.getPrice());
            prepStmt.setBoolean(4, menuItem.getAvailability());
            prepStmt.setString(5, menuItem.getCategory());
            prepStmt.setLong(6, menuItem.getId());

            prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return menuItem;
    }

    public int delete (MenuItem menuItem){
        int rowsAffected = 0;

        String sql = "DELETE FROM MENU_ITEMs WHERE ID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setLong(1, menuItem.getId());
            rowsAffected = prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return rowsAffected;
    }
}
