<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.profmash.model.Professor, java.util.List" %>
<%
    List<Professor> professors = (List<Professor>) request.getAttribute("professors");
    int totalVotes = (Integer) request.getAttribute("totalVotes");
    String ctx     = request.getContextPath();
    String[] medals = {"🥇","🥈","🥉"};
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProfMash — Rankings</title>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<header class="site-header">
    <div class="header-inner">
        <div class="logo">PROF<span>MASH</span></div>
        <nav>
            <a href="<%= ctx %>/matchup" class="nav-link">⚡ Vote</a>
            <a href="<%= ctx %>/leaderboard" class="nav-link active">🏆 Rankings</a>
        </nav>
        <div class="vote-counter">
            <span class="vote-count"><%= totalVotes %></span>
            <span class="vote-label">votes cast</span>
        </div>
    </div>
</header>
<main class="leaderboard-page">
    <h1 class="lb-title">PROFESSOR RANKINGS</h1>
    <p class="lb-subtitle">Ranked by Elo rating · Updated in real-time</p>
    <div class="lb-table-wrap">
        <table class="lb-table">
            <thead>
                <tr>
                    <th>#</th><th>Professor</th><th>Department</th><th>Subject</th>
                    <th>Elo ⚡</th><th>W / L</th><th>Win %</th>
                </tr>
            </thead>
            <tbody>
<%
    for (int i = 0; i < professors.size(); i++) {
        Professor prof = professors.get(i);
        String rankClass = i == 0 ? "rank-1" : i == 1 ? "rank-2" : i == 2 ? "rank-3" : "";
        String rank = i < 3 ? medals[i] : String.valueOf(i + 1);
        String winRate = prof.getTotalVotes() > 0 ? Math.round(prof.getWinRate()) + "%" : "—";
        int winRateInt = prof.getTotalVotes() > 0 ? (int) Math.round(prof.getWinRate()) : 0;
%>
                <tr class="lb-row <%= rankClass %>">
                    <td class="rank-cell"><%= rank %></td>
                    <td class="name-cell">
                        <img src="<%= prof.getPhotoUrl() %>" class="lb-avatar" alt="<%= prof.getName() %>"
                             onerror="this.src='<%= ctx %>/images/default-prof.svg'"/>
                        <span><%= prof.getName() %></span>
                    </td>
                    <td><%= prof.getDepartment() %></td>
                    <td><%= prof.getSubject() %></td>
                    <td class="elo-cell"><%= Math.round(prof.getEloRating()) %></td>
                    <td class="wl-cell">
                        <span class="wins"><%= prof.getWins() %>W</span> /
                        <span class="losses"><%= prof.getLosses() %>L</span>
                    </td>
                    <td class="winrate-cell">
                        <div class="winrate-bar-wrap">
                            <div class="winrate-bar" style="width:<%= winRateInt %>px;max-width:80px"></div>
                            <span class="winrate-label"><%= winRate %></span>
                        </div>
                    </td>
                </tr>
<% } %>
            </tbody>
        </table>
    </div>
    <div class="lb-cta">
        <a href="<%= ctx %>/matchup" class="btn-vote-now">⚡ Cast Your Vote</a>
    </div>
</main>
</body>
</html>
