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
    <title>ProfMash</title>
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

        <!-- Professor A -->
        <div class="prof-card" id="cardA">
            <div class="card-glow glow-left"></div>
            <div class="prof-photo-wrap">
                <img src="<%= profA.getPhotoUrl() %>" alt="<%= profA.getName() %>" class="prof-photo"
                     onerror="this.src='<%= ctx %>/images/default-prof.svg'"/>
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
            <!-- VOTE BUTTON - direct form submit -->
            <form action="<%= ctx %>/vote" method="post">
                <input type="hidden" name="winnerId" value="<%= profA.getId() %>"/>
                <input type="hidden" name="loserId"  value="<%= profB.getId() %>"/>
                <button type="submit" class="vote-btn">👆 VOTE</button>
            </form>
        </div>

        <div class="vs-divider">
            <div class="vs-circle">VS</div>
            <div class="vs-line"></div>
        </div>

        <!-- Professor B -->
        <div class="prof-card" id="cardB">
            <div class="card-glow glow-right"></div>
            <div class="prof-photo-wrap">
                <img src="<%= profB.getPhotoUrl() %>" alt="<%= profB.getName() %>" class="prof-photo"
                     onerror="this.src='<%= ctx %>/images/default-prof.svg'"/>
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
            <!-- VOTE BUTTON - direct form submit -->
            <form action="<%= ctx %>/vote" method="post">
                <input type="hidden" name="winnerId" value="<%= profB.getId() %>"/>
                <input type="hidden" name="loserId"  value="<%= profA.getId() %>"/>
                <button type="submit" class="vote-btn">👆 VOTE</button>
            </form>
        </div>

    </div>
    <div class="skip-wrap">
        <a href="<%= ctx %>/matchup" class="skip-btn">↻ Skip this matchup</a>
    </div>
</main>
<script src="<%= ctx %>/js/app.js"></script>
</body>
</html>
