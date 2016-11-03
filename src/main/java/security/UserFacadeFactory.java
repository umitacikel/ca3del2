/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import facades.UserFacade;
import javax.persistence.Persistence;


public class UserFacadeFactory {
    private static  UserFacade instance = new UserFacade( Persistence.createEntityManagerFactory( "seedMavenP" ));
    public static UserFacade getInstance(){
        return instance;
    }
}
