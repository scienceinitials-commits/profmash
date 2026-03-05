# 🎓 ProfMash

> A FashMash-style professor rating app built with **Java Servlets + JSP + Apache Tomcat**.
> Vote between two professors, watch Elo ratings update in real-time, and see the leaderboard!

---

## 📁 Project Structure

```
profmash/
├── pom.xml                                    ← Maven build file
└── src/main/
    ├── java/com/profmash/
    │   ├── model/
    │   │   └── Professor.java                 ← Data model
    │   ├── dao/
    │   │   └── ProfessorDAO.java              ← In-memory data store + Elo logic
    │   ├── servlet/
    │   │   ├── MatchupServlet.java            ← GET /matchup  → shows two random profs
    │   │   ├── VoteServlet.java               ← POST /vote    → records vote
    │   │   └── LeaderboardServlet.java        ← GET /leaderboard → rankings
    │   └── util/
    │       └── EloRating.java                 ← Elo rating calculator
    └── webapp/
        ├── WEB-INF/
        │   ├── web.xml                        ← Servlet config
        │   └── jsp/
        │       ├── matchup.jsp                ← Main voting page
        │       ├── result.jsp                 ← Vote result + redirect
        │       └── leaderboard.jsp            ← Rankings table
        ├── css/style.css
        ├── js/app.js
        └── images/default-prof.svg
```

---

## 🚀 How to Run

### Prerequisites
- Java 11+ installed
- Maven installed
- Apache Tomcat 10.x downloaded

### Option A — Deploy on Tomcat Manually

1. **Build the WAR file:**
   ```bash
   cd profmash
   mvn clean package
   ```

2. **Copy WAR to Tomcat:**
   ```bash
   cp target/profmash.war /path/to/tomcat/webapps/
   ```

3. **Start Tomcat:**
   ```bash
   /path/to/tomcat/bin/startup.sh      # Linux/Mac
   /path/to/tomcat/bin/startup.bat     # Windows
   ```

4. **Open your browser:**
   ```
   http://localhost:8080/profmash
   ```

### Option B — Use IntelliJ IDEA (Easiest!)

1. Open the `profmash` folder in IntelliJ IDEA
2. Go to **Run → Edit Configurations**
3. Click **+** → **Tomcat Server → Local**
4. Set your Tomcat home directory
5. Under **Deployment** tab → click **+** → select `profmash:war exploded`
6. Set Application context to `/profmash`
7. Click **Run** ▶

### Option C — Use Eclipse

1. Import as **Existing Maven Project**
2. Right-click project → **Run As → Run on Server**
3. Select or configure a Tomcat 10 server

---

## ✏️ Adding Your Real Professors

Open `ProfessorDAO.java` and edit the `loadSampleData()` method:

```java
private void loadSampleData() {
    addProf(1, "Dr. Your Prof Name",  "Computer Science", "Subject Name", "photo_url_or_path");
    addProf(2, "Prof. Another Name",  "Mathematics",      "Calculus",     "photo_url_or_path");
    // ... add all your professors
}
```

For **photos**, you can:
- Use a URL to their department page photo
- Place images in `webapp/images/` and reference as `/profmash/images/prof1.jpg`
- Use the default avatar (auto-fallback) if no photo available

---

## 🗄️ Switching to a Real Database (MySQL)

The current `ProfessorDAO` uses in-memory storage (data resets on restart).

To add persistence, replace the in-memory map in `ProfessorDAO` with JDBC:

1. Add MySQL dependency to `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.1.0</version>
</dependency>
```

2. Create a `Connection` in DAO:
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/profmash", "user", "password");
```

3. Replace HashMap operations with SQL queries (`INSERT`, `UPDATE`, `SELECT`).

---

## ⚡ How Elo Rating Works

Each professor starts at **1200 Elo**.

When professor A beats professor B:
```
Expected score of A = 1 / (1 + 10^((B - A) / 400))
New A rating = A + 32 × (1 - expected)
New B rating = B + 32 × (0 - (1 - expected))
```

- Beating a higher-rated opponent gives **more points**
- Beating a lower-rated opponent gives **fewer points**
- The K-factor (32) controls how fast ratings change

---

## 🌐 URL Routes

| URL | Servlet | Description |
|-----|---------|-------------|
| `/profmash/` | MatchupServlet | Redirects to matchup |
| `/profmash/matchup` | MatchupServlet (GET) | Shows two random profs |
| `/profmash/vote` | VoteServlet (POST) | Records a vote |
| `/profmash/leaderboard` | LeaderboardServlet (GET) | Rankings page |

---

## 📦 Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 11 |
| Web Layer | Jakarta Servlets 5.0 |
| Templating | JSP + JSTL |
| Server | Apache Tomcat 10.x |
| Build | Maven |
| Storage | In-memory (ConcurrentHashMap) |
| Rating | Elo algorithm |
| CSS | Custom (no frameworks) |
| Fonts | Bebas Neue + DM Sans |
