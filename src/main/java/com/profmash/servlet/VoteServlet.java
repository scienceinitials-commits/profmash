package com.profmash.servlet;

import com.profmash.dao.ProfessorDAO;
import com.profmash.model.Professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "VoteServlet", urlPatterns = {"/vote"})
public class VoteServlet extends HttpServlet {

    private final ProfessorDAO dao = ProfessorDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String winnerIdStr = req.getParameter("winnerId");
        String loserIdStr  = req.getParameter("loserId");

        if (winnerIdStr == null || loserIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/matchup");
            return;
        }

        int winnerId = Integer.parseInt(winnerIdStr);
        int loserId  = Integer.parseInt(loserIdStr);

        // Record the vote & update Elo
        dao.recordVote(winnerId, loserId);

        Professor winner = dao.getProfessorById(winnerId);
        Professor loser  = dao.getProfessorById(loserId);

        // Show result page briefly, then next matchup
        req.setAttribute("winner", winner);
        req.setAttribute("loser",  loser);

        req.getRequestDispatcher("/WEB-INF/jsp/result.jsp")
           .forward(req, resp);
    }
}
