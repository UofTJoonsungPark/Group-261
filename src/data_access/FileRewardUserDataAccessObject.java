package data_access;

import entity.Badge;
import entity.BadgeFactory;
import use_case.reward.RewardDataAccessInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileRewardUserDataAccessObject implements RewardDataAccessInterface {
    private final String filePath;
    private final BadgeFactory badgeFactory;
    private String username = null;

    ArrayList<String> badges = new ArrayList<String>();

    public FileRewardUserDataAccessObject(BadgeFactory badgeFactory) {
        this.filePath = "DATA" + File.separator + "RewardDirectory";
        this.badgeFactory = badgeFactory;
    }

    private void makeCsvFile() {
        String fileName = username + ".csv";

        // Create a File object for the folder
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Create a File object for the CSV file within the folder
        File csvFile = new File(folder, fileName);

        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");
        this.badges.add("https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            int val = 0;
            while (badges.size() > val) {
                writer.write(badges.get(val));
                writer.newLine();
                val++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred when making the file.");
        }
    }

    @Override
    public ArrayList<Badge> getBadges(int point) {
        ArrayList<Badge> bgs = new ArrayList<Badge>();
        int val = 0;
        while (point / 25 > val) {
            bgs.add(new Badge(badges.get(val)));
            val++;
        }
        return bgs;
    }
}
