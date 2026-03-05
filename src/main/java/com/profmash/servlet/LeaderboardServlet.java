package com.profmash.servlet;

import com.profmash.dao.ProfessorDAO;
import com.profmash.model.Professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LeaderboardServlet", urlPatterns = {"/leaderboard"})
public class LeaderboardServlet extends HttpServlet {

    private final ProfessorDAO dao = ProfessorDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Professor> ranked = dao.getLeaderboard();
        req.setAttribute("professors", ranked);
        req.setAttribute("totalVotes", dao.getTotalVotesCast());

        req.getRequestDispatcher("/WEB-INF/jsp/leaderboard.jsp")
           .forward(req, resp);
    }
}
