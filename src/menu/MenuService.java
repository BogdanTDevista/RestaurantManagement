package menu;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuService {

    private MenuService menuService = null;

    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    Connection con = dbConnection.getConnection();


    private static class LazyHolder{
        static final MenuService INSTANCE = new MenuService();

    }

    private MenuService(){

    }
    public static MenuService getInstance(){
        return MenuService.LazyHolder.INSTANCE;
    }

    public MenuService getMenuItemService() {
        return menuService;
    }


    public List<Menu> findAll() {
        List<Menu> menus = Collections.emptyList();
        String sql = "SELECT * FROM MENU_CATEGORIES";

        try(
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
        ){

            menus= new ArrayList<>();
            while(rset.next()){
                Menu menu = new Menu();
                menu.setId(rset.getLong("mid"));
                menu.setCategory(rset.getString("Category"));
                menus.add(menu);
            }

        }
        catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return menus;
    }

    public Optional<Menu> findById(long mid) {

        Optional<Menu> menu = Optional.empty();
        String sql = "SELECT ID, CATEGORY FROM Menu_CATEGORIES WHERE ID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql);
        ){
            prepStmt.setLong(1, mid);

            try(ResultSet rset = prepStmt.executeQuery()){
                Menu resMenu = new Menu();

                if(rset.next()){
                    resMenu.setId(rset.getLong("ID"));
                    resMenu.setCategory(rset.getString("Category"));
                }
                menu = Optional.of(resMenu);
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return menu;
    }


    public Menu add(Menu menu) {
        String sql = "INSERT INTO MENU_CATEGORIES (CATEGORY) VALUE (?)";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setString(1, menu.getCategory());
            prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return menu;
    }

    public Menu update(Menu menu){
        String sql = "UPDATE MENU_CATEGORIES SET CATEGORY = ? WHERE MID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setString(1, menu.getCategory());
            prepStmt.setLong(2, menu.getId());

            prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return menu;
    }

    public int delete (Menu menu){
        int rowsAffected = 0;

        String sql = "DELETE FROM MENU_CATEGORIES WHERE MID = ?";

        try(
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            prepStmt.setLong(1, menu.getId());
            rowsAffected = prepStmt.executeUpdate();
        }
        catch (SQLException sqe) {sqe.printStackTrace();}
        return rowsAffected;
    }
}
