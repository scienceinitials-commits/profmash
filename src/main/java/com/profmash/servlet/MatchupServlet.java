package com.profmash.servlet;

import com.profmash.dao.ProfessorDAO;
import com.profmash.model.Professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "MatchupServlet", urlPatterns = {"/", "/matchup"})
public class MatchupServlet extends HttpServlet {

    private final ProfessorDAO dao = ProfessorDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Professor[] pair = dao.getRandomMatchup();

        if (pair == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Not enough professors to create a matchup.");
            return;
        }

        req.setAttribute("profA", pair[0]);
        req.setAttribute("profB", pair[1]);
        req.setAttribute("totalVotes", dao.getTotalVotesCast());

        req.getRequestDispatcher("/WEB-INF/jsp/matchup.jsp")
           .forward(req, resp);
    }
}
