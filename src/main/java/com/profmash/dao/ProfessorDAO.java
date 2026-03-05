package com.profmash.dao;

import com.profmash.model.Professor;
import com.profmash.util.EloRating;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProfessorDAO {

    private static final Map<Integer, Professor> professors = new ConcurrentHashMap<>();
    private static final ProfessorDAO instance = new ProfessorDAO();

    private ProfessorDAO() { loadSampleData(); }

    public static ProfessorDAO getInstance() { return instance; }

    public List<Professor> getAllProfessors() { return new ArrayList<>(professors.values()); }

    public Professor getProfessorById(int id) { return professors.get(id); }

    public void saveProfessor(Professor prof) { professors.put(prof.getId(), prof); }

    public List<Professor> getLeaderboard() {
        return professors.values().stream()
                .sorted(Comparator.comparingDouble(Professor::getEloRating).reversed())
                .collect(Collectors.toList());
    }

    public Professor[] getRandomMatchup() {
        List<Professor> all = getAllProfessors();
        if (all.size() < 2) return null;
        Collections.shuffle(all);
        return new Professor[]{ all.get(0), all.get(1) };
    }

    public void recordVote(int winnerId, int loserId) {
        Professor winner = professors.get(winnerId);
        Professor loser  = professors.get(loserId);
        if (winner == null || loser == null) return;
        double[] newRatings = EloRating.updateRatings(winner.getEloRating(), loser.getEloRating());
        winner.setEloRating(newRatings[0]);
        winner.setWins(winner.getWins() + 1);
        winner.setTotalVotes(winner.getTotalVotes() + 1);
        loser.setEloRating(newRatings[1]);
        loser.setLosses(loser.getLosses() + 1);
        loser.setTotalVotes(loser.getTotalVotes() + 1);
        professors.put(winnerId, winner);
        professors.put(loserId, loser);
    }

    public int getTotalVotesCast() {
        return professors.values().stream().mapToInt(Professor::getWins).sum();
    }

    private void addProf(int id, String name, String dept, String subject, String photo) {
        professors.put(id, new Professor(id, name, dept, subject, "/profmash/images/professors/" + photo));
    }

    private void loadSampleData() {
        addProf(1,  "Vishal Meena",                "CS", "Programming",        "Vishal-Meena.jpg");
        addProf(2,  "Vikas Hassija",               "CS", "Networks",            "vikas-hassija.jpg");
        addProf(3,  "Vijay",                       "CS", "Algorithms",          "Vijay.jpg");
        addProf(4,  "Gautami Uppada",              "CS", "Data Science",        "Gautami-GAUTAMI-UPPADA.jpg");
        addProf(5,  "Swati",                       "CS", "Software Engg.",      "Swati.jpg");
        addProf(6,  "Tanik Saikh",                 "CS", "Machine Learning",    "Tanik-Saikh.jpg");
        addProf(7,  "Tanmoy Maitra",               "CS", "Computer Networks",   "Tanmoy-Maitra-FCE.jpg");
        addProf(8,  "Swagat Ranjan Sahoo",         "CS", "Database Systems",    "swagat-ranjan-sahoo.jpg");
        addProf(9,  "Susmita Das",                 "CS", "Operating Systems",   "kiit-Susmita-Das.jpg");
        addProf(10, "Sushruta Mishra",             "CS", "Cyber Security",      "Sushruta-Mishra.jpg");
        addProf(11, "Suneeta Mohanty",             "CS", "AI & ML",             "Suneeta-Mohanty.jpg");
        addProf(12, "Sunil Kumar Gouda",           "CS", "Computer Graphics",   "Sunil-Kumar-Gouda.jpg");
        addProf(13, "Suresh Chandra Satapathy",    "CS", "Data Mining",         "Suresh-Chandra-Satapathy.jpg");
        addProf(14, "Sujoy Roy",                   "CS", "Image Processing",    "SujoyJPG-Sujoy-Roy.jpg");
        addProf(15, "Sujoy Datta",                 "CS", "Cloud Computing",     "sujoy_datta-Sujoy-Datta.jpg");
        addProf(16, "Suchismita Das",              "CS", "Web Technology",      "Suchismita-Das-1.jpg");
        addProf(17, "Dr. Suchismita Rout",         "CS", "IoT",                 "SRout-Dr-Suchismita-Rout.jpg");
        addProf(18, "Sujata Swain",                "CS", "Compiler Design",     "Sujata-Swain.jpg");
        addProf(19, "Dr. Subhranshu Tripathy",     "CS", "Digital Systems",     "IMG-20191228-WA0002-Dr-Subhranshu-Sekhar-Tripathy.jpg");
        addProf(20, "Subhra",                      "CS", "Mathematics",         "Subhra-.jpg");
        addProf(21, "Dr. Suman Majumder",          "CS", "Computer Science",    "Suman-Dr.Suman-Majumder-1.jpg");
        addProf(22, "Subhashish Das",              "CS", "Computer Science",    "Subhashish-Das-1.jpg");
        addProf(23, "Sricheta Parui",              "CS", "Computer Science",    "Sricheta-Parui.jpg");
        addProf(24, "Subhadip Pramanik",           "CS", "Computer Science",    "subha-Subhadip-Pramanik.jpg");
        addProf(25, "Subhashree Darshana",         "CS", "Computer Science",    "GAT-Subhashree-Darshana.jpg");
        addProf(26, "Sovan Kumar Sahoo",           "CS", "Computer Science",    "Sovan-Kumar-Sahoo.jpg");
        addProf(27, "Sourav",                      "CS", "Computer Science",    "Sourav.jpg");
        addProf(28, "Mr. Sourabh Debnath",         "CS", "Computer Science",    "Mr.Sourabh-Debnath.jpg");
        addProf(29, "Dr. Soumya Ranjan Mishra",    "CS", "Computer Science",    "Dr-Soumya-Ranjan-Mishra.jpg");
        addProf(30, "Soumya Ranjan Nayak",         "CS", "Computer Science",    "Soumya-Ranjan-Nayak.jpg");
        addProf(31, "Soumya Sanket Patra",         "CS", "Computer Science",    "Soumya-Sanket-Patra.jpg");
        addProf(32, "Siddharth Routaray",          "CS", "Computer Science",    "sid-siddharth-routaray.jpg");
        addProf(33, "Shubhangi Shreya",            "CS", "Computer Science",    "Shubhangi-Shreya.jpg");
        addProf(34, "Shilpa Das",                  "CS", "Computer Science",    "ShilpaDas.jpg");
        addProf(35, "Sourabh",                     "CS", "Computer Science",    "sourabh.jpg");
        addProf(36, "Saurabh Jha",                 "CS", "Computer Science",    "Saurabh-Jha.jpg");
        addProf(37, "Shaswati Patra",              "CS", "Computer Science",    "Shaswati-Patra.jpg");
        addProf(38, "Satananda",                   "CS", "Computer Science",    "satyananda.webp");
        addProf(39, "Satarupa Mohanty",            "CS", "Computer Science",    "Satarupa-Mohanty-1.jpg");
        addProf(40, "Sarita Tripathy",             "CS", "Computer Science",    "Sarita-Tripathy-1.jpg");
        addProf(41, "Santosh Swain",               "CS", "Computer Science",    "santosh-swain.jpg");
        addProf(42, "Santwana Sagnika",            "CS", "Computer Science",    "Santwana-Sagnika-santwana-sagnika.jpg");
        addProf(43, "Sarita Mishra",               "CS", "Computer Science",    "Sarita-Mishra.jpg");
        addProf(44, "Santosh Pani",                "CS", "Computer Science",    "Santosh-Pani.jpg");
        addProf(45, "Santos Kumar Baliarsingh",    "CS", "Computer Science",    "Santos-Kumar-Baliarsingh.jpg");
        addProf(46, "Sankalp Nayak",               "CS", "Computer Science",    "Sankalp-Nayak.jpg");
        addProf(47, "Samaresh Mishra",             "CS", "Computer Science",    "Samaresh-Mishra-10-2-2021.jpg");
        addProf(48, "Sambit Praharaj",             "CS", "Computer Science",    "Sambit-Praharaj.jpg");
        addProf(49, "Sampriti Soor",               "CS", "Computer Science",    "Sampriti-Soor.jpg");
        addProf(50, "Roshni Pradhan",              "CS", "Computer Science",    "Roshni-Pradhan.jpg");
        addProf(51, "Ronali Padhy",                "CS", "Computer Science",    "Ronali-Padhy.jpg");
        addProf(52, "Rinku Datta Rakshit",         "CS", "Computer Science",    "Rinku-Datta-Rakshit.jpg");
        addProf(53, "Ramakanta Parida",            "CS", "Computer Science",    "Ramakanta-Parida.webp");
        addProf(54, "Rakesh Kumar",                "CS", "Computer Science",    "Rakesh-kumar.jpg");
        addProf(55, "Rajdeep Chatterjee",          "CS", "Computer Science",    "Rajdeep-Chatterjee-Rajdeep-Chatterjee.jpg");
        addProf(56, "Rajat Kumar Behera",          "CS", "Computer Science",    "Rajat-Kumar-Behera.jpg");
        addProf(57, "Raghunath Dey",               "CS", "Computer Science",    "Raghunath-Dey.jpg");
        addProf(58, "Mr. Pushkar Kishore",         "CS", "Computer Science",    "Mr.Pushkar-Kishore.jpg");
        addProf(59, "Priyanka",                    "CS", "Computer Science",    "Priyanka.webp");
        addProf(60, "Priyanka Roy",                "CS", "Computer Science",    "Priyanka-Roy.jpg");
        addProf(61, "Pratyusa Mukherjee",          "CS", "Computer Science",    "Pratyusa-Mukherjee.jpg");
        addProf(62, "Prasenjit Maiti",             "CS", "Computer Science",    "Prasenjit-Maiti.jpg");
        addProf(63, "Prachet Bhuyan",              "CS", "Computer Science",    "Prachet-Bhuyan.jpg");
        addProf(64, "Pradeep Kandula",             "CS", "Computer Science",    "pradeep-kandula.jpg");
        addProf(65, "Pradeep Kumar Mallick",       "CS", "Computer Science",    "Pradeep-Kumar-Mallick.jpg");
        addProf(66, "Prabhu Prasad Dev",           "CS", "Computer Science",    "Prabhu-Prasad-Dev-1.jpg");
        addProf(67, "Pinaki Chatterjee",           "CS", "Computer Science",    "pinaki-chatterjee.jpg");
        addProf(68, "Partha Sarathi Paul",         "CS", "Computer Science",    "Partha-Sarathi-Paul.jpg");
        addProf(69, "Nibedan Panda",               "CS", "Computer Science",    "Nibedan-Panda.jpg");
        addProf(70, "Niranjan Ray",                "CS", "Computer Science",    "Niranjan-Ray.jpg");
        addProf(71, "Partha Pratim Sarangi",       "CS", "Computer Science",    "Partha-pratim-Sarangi.jpg");
        addProf(72, "Namita Panda",                "CS", "Computer Science",    "Namita-Panda.jpg");
        addProf(73, "Nalini Prara Behera",         "CS", "Computer Science",    "Nalini-Prara-behera.jpg");
        addProf(74, "Nachiketa Tarasia",           "CS", "Computer Science",    "Nachiketa-Tarasia-1.jpg");
        addProf(75, "Murari Mandal",               "CS", "Computer Science",    "Murari-Mandal.jpg");
        addProf(76, "Biraja N Isac",               "CS", "Computer Science",    "biraja-N-Biraja-Isac.jpg");
        addProf(77, "Sangita Acharya",             "CS", "Computer Science",    "Sangita-Acharya.jpg");
        addProf(78, "Dr. Mukesh Kumar",            "CS", "Computer Science",    "mukesh_photo-Dr.-Mukesh-Kumar.jpg");
        addProf(79, "Monideepa Roy",               "CS", "Computer Science",    "monideepa-roy.jpg");
        addProf(80, "Mohit Ranjan Panda",          "CS", "Computer Science",    "Mohit-Ranjan-Panda.jpg");
        addProf(81, "Manoj Kumar Mishra",          "CS", "Computer Science",    "Manoj-Kumar-Mishra-1.jpg");
        addProf(82, "Meghana Madam",               "CS", "Computer Science",    "Meghana-Madam-CSE.jpg");
        addProf(83, "Minakhi Rout",                "CS", "Computer Science",    "Minakhi-Rout.jpg");
        addProf(84, "Mandakini",                   "CS", "Computer Science",    "Mandakini.jpg");
        addProf(85, "Manas Ranjan Nayak",          "CS", "Computer Science",    "Manas-Ranjan-Nayak-1.jpg");
        addProf(86, "Prasant Patnaik",             "CS", "Computer Science",    "prasant-patnaik.jpg");
        addProf(87, "Mahendra Kumar Gourisaria",   "CS", "Computer Science",    "Mahendra-Kumar-Gourisaria.jpg");
        addProf(88, "Madhaba Nanda",               "CS", "Computer Science",    "Madhaba-nanda.jpg");
        addProf(89, "Lipika Mohanty",              "CS", "Computer Science",    "Lipika-Mohanty-scaled.jpg");
        addProf(90, "Lalit Vashishtha",            "CS", "Computer Science",    "l-Lalit-Vashishtha.jpg");
        addProf(91, "Leena Das",                   "CS", "Computer Science",    "Leena-Das-2.jpg");
        addProf(92, "Lipika Dewangan",             "CS", "Computer Science",    "Lipika-Dewangan.jpg");
        addProf(93, "Kunal Anand",                 "CS", "Computer Science",    "Kunal-Anand-Kunal-Anand-1.jpg");
        addProf(94, "Kumar Devdutta",              "CS", "Computer Science",    "Kumar-Devdutta.jpg");
        addProf(95, "Krishna Chakravarty",         "CS", "Computer Science",    "Krishna-Chakravarty.jpg");
        addProf(96, "Krishnendu Maity",            "CS", "Computer Science",    "Krish_pic-Krishnendu-Maity.jpg");
        addProf(97, "Krutika Verma",               "CS", "Computer Science",    "Krutika-Verma.jpg");
        addProf(98, "Jyotiranjan",                 "CS", "Computer Science",    "Jyotiranjan.jpg");
        addProf(99, "Jyotiprakash Mishra",         "CS", "Computer Science",    "Jyotiprakash-Mishra.jpg");
        addProf(100,"Junali",                      "CS", "Computer Science",    "junali.jpg");
        addProf(101,"Jayeeta Chakraborty",         "CS", "Computer Science",    "JayeetaChakraborty.jpg");
        addProf(102,"Jhalak Hota",                 "CS", "Computer Science",    "Jhalak-Hota.jpg");
        addProf(103,"Joy Dutta",                   "CS", "Computer Science",    "Joy-Dutta-Joy-Dutta.jpg");
        addProf(104,"Jayanti",                     "CS", "Computer Science",    "Jayanti.jpg");
        addProf(105,"Jayanta Mondal",              "CS", "Computer Science",    "Jayanta-Mondal.jpg");
        addProf(106,"Dr. Jay Sarraf",              "CS", "Computer Science",    "Dr-Jay-Sarraf.jpg");
        addProf(107,"Jasaswi Prasad Mohanty",      "CS", "Computer Science",    "Jasaswi-Prasad-Mohanty.jpg");
        addProf(108,"Jagannath Singh",             "CS", "Computer Science",    "Jagannath-Singh.jpg");
        addProf(109,"Hrudaya Kumar Tripathy",      "CS", "Computer Science",    "Hrudaya-Kumar-Tripathy.jpg");
        addProf(110,"Indu Choudhury",              "CS", "Computer Science",    "Indu-choudhury.jpg");
        addProf(111,"Ipshita Paul",                "CS", "Computer Science",    "Ipshita-Paul.jpg");
        addProf(112,"Himansu",                     "CS", "Computer Science",    "Himansu.jpg");
        addProf(113,"Himanshu Ranjan",             "CS", "Computer Science",    "himanshu-ranjan.jpg");
        addProf(114,"Himadri",                     "CS", "Computer Science",    "Himadri.jpg");
        addProf(115,"Harish Kumar Patnaik",        "CS", "Computer Science",    "Harish-Kumar-Patnaik.jpg");
        addProf(116,"Gananatha Bhuyan",            "CS", "Computer Science",    "Gananatha-Bhuyan.jpg");
        addProf(117,"GB Mund",                     "CS", "Computer Science",    "gbmund-GB-Mund.jpeg");
        addProf(118,"Deependra Singh",             "CS", "Computer Science",    "Deependra-Singh.jpg");
        addProf(119,"Dipti Dash",                  "CS", "Computer Science",    "Dipti-Dash.jpg");
        addProf(120,"Debashis Hati",               "CS", "Computer Science",    "Debashis-Hati.jpg");
        addProf(121,"Debanjan Pathak",             "CS", "Computer Science",    "Debanjan-Pathak.jpg");
        addProf(122,"Debadatta Naik",              "CS", "Computer Science",    "Debadatta-Debadatta-Naik.jpg");
        addProf(123,"Chittaranjan Pradhan",        "CS", "Computer Science",    "Chittaranjan-Pradhan.jpg");
        addProf(124,"Dayal Kumar Behera",          "CS", "Computer Science",    "Dayal-Kumar-Behera.jpg");
        addProf(125,"Debachudamani Prusti",        "CS", "Computer Science",    "kiit-Debachudamani-Prusti.jpg");
        addProf(126,"Chandani Kumari",             "CS", "Computer Science",    "Chandani-Kumari.jpg");
        addProf(127,"Biswajit Sahoo",              "CS", "Computer Science",    "Biswajit-Sahoo-CSE.jpg");
        addProf(128,"Biswajeet Sethi",             "CS", "Computer Science",    "Biswajeet-Sethi.jpg");
        addProf(129,"Biraja Prasad",               "CS", "Computer Science",    "biraja-prasad.jpg");
        addProf(130,"Bindu Agarwal",               "CS", "Computer Science",    "Bindu-Agarwal.jpg");
        addProf(131,"Bhaswati Sahoo",              "CS", "Computer Science",    "Bhaswati-Sahoo.jpg");
        addProf(132,"BSP",                         "CS", "Computer Science",    "BSP_profile-photo.jpg");
        addProf(133,"Benazir Neha",                "CS", "Computer Science",    "benazir1-Benazir-Neha.jpeg");
        addProf(134,"Banchhanidhi Dash",           "CS", "Computer Science",    "Banchhanidhi-Dash.jpg");
        addProf(135,"Asif Uddin Khan",             "CS", "Computer Science",    "Asif-Uddin-Khan.jpg");
        addProf(136,"Ashish Singh",                "CS", "Computer Science",    "Ashish-Singh.jpg");
        addProf(137,"Arup Sarkar",                 "CS", "Computer Science",    "Arup-Sarkar.jpg");
        addProf(138,"Arup Acharya",                "CS", "Computer Science",    "Arup-Acharya.jpg");
        addProf(139,"Aradhana Behura",             "CS", "Computer Science",    "Aradhana-Behura.jpg");
        addProf(140,"Anuja Acharya",               "CS", "Computer Science",    "Anuja-Acharya.jpeg");
        addProf(141,"Ankit Raj",                   "CS", "Computer Science",    "Ankit-Raj.jpg");
        addProf(142,"Anjan",                       "CS", "Computer Science",    "anjan.jpg.jpg");
        addProf(143,"Dr. Anisha Kumari",           "CS", "Computer Science",    "Dr.-Anisha-Kumari.jpg");
        addProf(144,"Anil Kumar Swain",            "CS", "Computer Science",    "Anil-Kumar-Swain.jpg");
        addProf(145,"Ajit Kumar Sahoo",            "CS", "Computer Science",    "KIIT-Ajit-Kumar-Sahoo.jpg");
        addProf(146,"Manas Ranjan Biswal",         "CS", "Computer Science",    "Manas-Ranjan-Biswal.jpg");
        addProf(147,"Manik Biswas",                "CS", "Computer Science",    "Manik-Biswas.webp");
        addProf(148,"Hitesh",                      "CS", "Computer Science",    "hitesh-2025-2.webp");
    }
}
