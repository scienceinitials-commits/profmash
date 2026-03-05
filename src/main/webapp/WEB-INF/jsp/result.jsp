<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.profmash.model.Professor" %>
<%
    Professor winner = (Professor) request.getAttribute("winner");
    Professor loser  = (Professor) request.getAttribute("loser");
    String ctx       = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProfMash — Vote Recorded!</title>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
    <meta http-equiv="refresh" content="2;url=<%= ctx %>/matchup">
</head>
<body class="result-body">
<header class="site-header">
    <div class="header-inner">
        <div class="logo">PROF<span>MASH</span></div>
        <nav>
            <a href="<%= ctx %>/matchup" class="nav-link active">⚡ Vote</a>
            <a href="<%= ctx %>/leaderboard" class="nav-link">🏆 Rankings</a>
        </nav>
    </div>
</header>
<main class="result-page">
    <div class="result-card">
        <div class="result-crown">👑</div>
        <h1 class="result-title">YOU VOTED FOR</h1>
        <div class="result-winner-name"><%= winner.getName() %></div>
        <div class="result-dept"><%= winner.getDepartment() %> · <%= winner.getSubject() %></div>
        <div class="result-elo-wrap">
            <div class="elo-change winner-elo">
                <span class="elo-label">New Rating</span>
                <span class="elo-value">⚡ <%= Math.round(winner.getEloRating()) %></span>
            </div>
            <div class="elo-vs">vs</div>
            <div class="elo-change loser-elo">
                <span class="elo-label"><%= loser.getName() %></span>
                <span class="elo-value">⚡ <%= Math.round(loser.getEloRating()) %></span>
            </div>
        </div>
        <div class="result-redirect">
            Next matchup loading…
            <div class="progress-bar"><div class="progress-fill"></div></div>
        </div>
        <a href="<%= ctx %>/matchup" class="btn-next">Vote Again →</a>
    </div>
</main>
</body>
</html>
