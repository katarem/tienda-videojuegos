package aed.practica2.backend;

import aed.practica2.backend.db.DBService;
import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Roles;
import aed.practica2.backend.utils.Security;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBService service = new DBService();
        List.of(
                new Cuenta("paco_loquito","contraseña123","paco@email.com","",Roles.USER),
                new Cuenta("katarem","admin_123","katarem@admin.com","",Roles.ADMIN),
                new Cuenta("paca_lolera","contraseña_Indescifrable","pacalolera@email.com","",Roles.USER)
        );
        var list = service.getCuentas();
        System.out.println(Security.login(list,"katarem","admin_123"));

    }
}
