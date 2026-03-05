<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.profmash.model.Professor" %>
<%
    Professor profA = (Professor) request.getAttribute("profA");
    Professor profB = (Professor) request.getAttribute("profB");
    int totalVotes  = (Integer) request.getAttribute("totalVotes");
    String ctx      = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProfMash — Who's the Better Prof?</title>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<header class="site-header">
    <div class="header-inner">
        <div class="logo">PROF<span>MASH</span></div>
        <nav>
            <a href="<%= ctx %>/matchup" class="nav-link active">⚡ Vote</a>
            <a href="<%= ctx %>/leaderboard" class="nav-link">🏆 Rankings</a>
        </nav>
        <div class="vote-counter">
            <span class="vote-count"><%= totalVotes %></span>
            <span class="vote-label">votes cast</span>
        </div>
    </div>
</header>
<main class="matchup-page">
    <div class="prompt-text">WHO IS THE BETTER PROFESSOR?</div>
    <div class="arena">
        <div class="prof-card" id="cardA" onclick="vote(<%= profA.getId() %>, <%= profB.getId() %>)">
            <div class="card-glow glow-left"></div>
            <div class="prof-photo-wrap">
                <img src="<%= profA.getPhotoUrl() %>" alt="<%= profA.getName() %>" class="prof-photo" onerror="this.src='<%= ctx %>/images/default-prof.svg'"/>
            </div>
            <div class="prof-info">
                <h2 class="prof-name"><%= profA.getName() %></h2>
                <div class="prof-dept"><%= profA.getDepartment() %></div>
                <div class="prof-subject"><%= profA.getSubject() %></div>
                <div class="prof-stats">
                    <span class="elo-badge">⚡ <%= Math.round(profA.getEloRating()) %></span>
                    <span class="win-rate"><%= profA.getTotalVotes() > 0 ? Math.round(profA.getWinRate()) + "% wins" : "No votes yet" %></span>
                </div>
            </div>
            <div class="click-hint">CLICK TO VOTE</div>
        </div>
        <div class="vs-divider">
            <div class="vs-circle">VS</div>
            <div class="vs-line"></div>
        </div>
        <div class="prof-card" id="cardB" onclick="vote(<%= profB.getId() %>, <%= profA.getId() %>)">
            <div class="card-glow glow-right"></div>
            <div class="prof-photo-wrap">
                <img src="<%= profB.getPhotoUrl() %>" alt="<%= profB.getName() %>" class="prof-photo" onerror="this.src='<%= ctx %>/images/default-prof.svg'"/>
            </div>
            <div class="prof-info">
                <h2 class="prof-name"><%= profB.getName() %></h2>
                <div class="prof-dept"><%= profB.getDepartment() %></div>
                <div class="prof-subject"><%= profB.getSubject() %></div>
                <div class="prof-stats">
                    <span class="elo-badge">⚡ <%= Math.round(profB.getEloRating()) %></span>
                    <span class="win-rate"><%= profB.getTotalVotes() > 0 ? Math.round(profB.getWinRate()) + "% wins" : "No votes yet" %></span>
                </div>
            </div>
            <div class="click-hint">CLICK TO VOTE</div>
        </div>
    </div>
    <div class="skip-wrap">
        <a href="<%= ctx %>/matchup" class="skip-btn">↻ Skip this matchup</a>
    </div>
</main>
<form id="voteForm" action="<%= ctx %>/vote" method="post" style="display:none">
    <input type="hidden" id="winnerId" name="winnerId"/>
    <input type="hidden" id="loserId"  name="loserId"/>
</form>
<script src="<%= ctx %>/js/app.js"></script>
</body>
</html>
