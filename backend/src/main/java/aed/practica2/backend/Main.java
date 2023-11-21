package aed.practica2.backend;

import aed.practica2.backend.db.DBService;
import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Generos;
import aed.practica2.backend.models.Producto;
import aed.practica2.backend.models.Roles;
import aed.practica2.backend.utils.Security;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBService service = new DBService();
        ArrayList<Producto> juegosXD = new ArrayList<>();
        generateGames(juegosXD);
        List.of(
                new Cuenta("paco_loquito","contraseña123","paco@email.com","",Roles.USER,null),
                new Cuenta("katarem","admin_123","katarem@admin.com","",Roles.ADMIN, juegosXD),
                new Cuenta("paca_lolera","contraseña_Indescifrable","pacalolera@email.com","",Roles.USER,null)
        );
        juegosXD.forEach(service::addProducto);
        service.close();
    }



    private static void generateGames(ArrayList<Producto> games){

        games.addAll(List.of(
                new Producto("Call of Duty Black Ops Cold War",1,49.99,List.of(Generos.Accion,Generos.Multijugador,Generos.FPS),"https://image.api.playstation.com/vulcan/ap/rnd/202112/0618/cFLNC3xYfEdDl7D1Wvu4MVb1.png"),
                new Producto("GTA San Andreas",2,9.99,List.of(Generos.Aventura,Generos.Sandbox,Generos.Mundo_Abierto),"https://im.ziffdavisinternational.com/ign_es/screenshot/default/gta_p5nt.jpg"),
                new Producto("ELDEN RING",3,69.99,List.of(Generos.Accion,Generos.Mundo_Abierto,Generos.Aventura,Generos.RPG),"https://media.vandal.net/100/74234/elden-ring-202161213154579_1b.jpg"),
                new Producto("Red Dead Redemption 2",4,80.00,List.of(Generos.Mundo_Abierto,Generos.Aventura,Generos.Accion,Generos.Multijugador),"https://image.api.playstation.com/cdn/UP1004/CUSA03041_00/Hpl5MtwQgOVF9vJqlfui6SDB5Jl4oBSq.png"),
                new Producto("Baldur's Gate 3",5,69.99,List.of(Generos.Simulacion,Generos.RPG,Generos.Accion),"https://assetsio.reedpopcdn.com/co670h.jpg?width=1200&height=1200&fit=bounds&quality=70&format=jpg&auto=webp"),
                new Producto("Grand Theft Auto V",6,49.99,List.of(Generos.Mundo_Abierto,Generos.Accion,Generos.Multijugador,Generos.Sandbox),"https://i.pinimg.com/originals/5c/09/09/5c0909173a1524c8270bf87557e1a7c6.jpg"),
                new Producto("Portal 2",7,19.99,List.of(Generos.Puzzle,Generos.Multijugador),"https://media.vandal.net/100/8775/20101225101630_1b.jpg"),
                new Producto("Forza Horizon 4",8,59.99,List.of(Generos.Carreras,Generos.Multijugador,Generos.Mundo_Abierto),"https://media.vandal.net/100/59798/forza-horizon-4-2018102103230_1b.jpg"),
                new Producto("Half-Life 2",9,19.99,List.of(Generos.FPS,Generos.Horror),"https://media.vandal.net/100/1913/2004112403546_1b.jpg"),
                new Producto("StarCraft II: Wings of Liberty",10,9.99,List.of(Generos.Estrategia),"https://media.vandal.net/100/7225/2010729161757_1b.jpg"),
                new Producto("BioShock",11,14.99,List.of(Generos.FPS,Generos.Accion,Generos.Horror),"https://media.vandal.net/100/3990/bioshock-201429143616_1b.jpg")
        ));


    }
}

