package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        // Declaramos la variable contador
        int contador = 0;

        // revisamos si existe la cookie contadorVisitas
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("contadorVisitas")) {
                    contador = Integer.parseInt(c.getValue()); // transformas el string a int
                    break;
                }
            }
        }
        // incrementamos el contador
        contador++;
        
        // Agregamos la respuesta al navegador
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        
        // La cookie se almacenara en el cliente por 1 hora (3600 segundos)
        c.setMaxAge(3600); // numero de segundos a expirar
        res.addCookie(c);
        
        // Mandamos el mensaje al navegador
        res.setContentType("text/html;charset=UTF-8");
        out.print("Contador de visitas de cada cliente: " +contador);
        
        
    }
}
