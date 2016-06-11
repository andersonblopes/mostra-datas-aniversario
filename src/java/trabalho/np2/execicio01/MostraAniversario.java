package trabalho.np2.execicio01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MostraAniversario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Calendar cal = Calendar.getInstance();
        Date datadigitada = null;
        Calendar dataNascimento = new GregorianCalendar();
        int idade;

        String data = request.getParameter("dia") + "/" + request.getParameter("mes") + "/" + request.getParameter("ano");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            datadigitada = df.parse(data);
        } catch (ParseException ex) {
        }

        cal.setTime(datadigitada);

        dataNascimento.setTime(datadigitada);

        idade = Calendar.getInstance().get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        cal.roll(Calendar.YEAR, idade);

        if (dataNascimento.get(Calendar.DAY_OF_YEAR) < Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) {
            cal.roll(Calendar.YEAR, 1);
            idade++;
        }

        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Confira abaixo suas próximas datas de aniversário!</h3>");
        out.println("<table border=\"1px\">");
        out.println("<tr style = \"color: white; background-color: black; text-align: center;\">"
                + "<td>Ano</td>"
                + "<td>Dia da semana</td>"
                + "<td>Idade</td>");

        for (int i = 0; i < 10; i++) {
            out.println("<tr>");
            String diatexto = null;
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    diatexto = "Domingo";
                    break;
                case 2:
                    diatexto = "Segunda-feira";
                    break;
                case 3:
                    diatexto = "Terça-feira";
                    break;
                case 4:
                    diatexto = "Quarta-feira";
                    break;
                case 5:
                    diatexto = "Quinta-feira";
                    break;
                case 6:
                    diatexto = "Sexta-feira";
                    break;
                case 7:
                    diatexto = "Sábado";
                    break;
            }
            out.println("<td style=\"width:10%; text-align: center;\">" + (cal.get(Calendar.YEAR)) + "</td><td style=\"width:20%;\">" + diatexto + "</td><td style=\"width:15%; text-align: center;\">" + idade + " Anos</td>");
            cal.roll(Calendar.YEAR, 1);
            idade++;
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<br><a href=\"index.html\">Nova consulta?</a>");
        out.println("</body>");
        out.println("</html>");
    }

}
